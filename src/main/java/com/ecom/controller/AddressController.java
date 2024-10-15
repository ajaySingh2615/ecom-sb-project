package com.ecom.controller;

import com.ecom.model.User;
import com.ecom.payload.address.AddressDTO;
import com.ecom.service.address.AddressService;
import com.ecom.util.AuthUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {
    private final AuthUtil authUtil;
    private final AddressService addressService;

    public AddressController(AuthUtil authUtil, AddressService addressService) {
        this.authUtil = authUtil;
        this.addressService = addressService;
    }

    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO) {
        User user = authUtil.loggedInUser();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Handle unauthorized access
        }

        try {
            AddressDTO savedAddressDTO = addressService.createAddress(addressDTO, user);
            return new ResponseEntity<>(savedAddressDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Handle bad request scenario
        } catch (Exception e) {
            // Log the exception (consider using a logging framework)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Handle other unexpected errors
        }
    }

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>> getAddresses() {
        List<AddressDTO> addressList = addressService.getAddresses();
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @GetMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> getAddressesById(@PathVariable Long addressId) {
        AddressDTO addressDTO = addressService.getAddressesById(addressId);
        return new ResponseEntity<>(addressDTO, HttpStatus.OK);
    }


}
