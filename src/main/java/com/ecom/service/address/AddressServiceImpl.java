package com.ecom.service.address;

import com.ecom.model.Address;
import com.ecom.model.User;
import com.ecom.payload.address.AddressDTO;
import com.ecom.repositories.AddressRepository;
import com.ecom.repositories.UserRepository;
import com.ecom.util.AuthUtil;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    private final ModelMapper modelMapper;
    private final AddressRepository addressRepository;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;

    public AddressServiceImpl(ModelMapper modelMapper, AddressRepository addressRepository, AuthUtil authUtil, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.addressRepository = addressRepository;
        this.authUtil = authUtil;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public AddressDTO createAddress(AddressDTO addressDTO, User user) {
        if (addressDTO == null || user == null) {
            throw new IllegalArgumentException("AddressDTO and User must not be null");
        }

        Address address = modelMapper.map(addressDTO, Address.class);
        address.setUser(user);

        List<Address> addressesList = user.getAddresses();
        if (addressesList == null) {
            addressesList = new ArrayList<>(); // Initialize if null
        }

        addressesList.add(address);
        user.setAddresses(addressesList);

        // Save the user to persist the updated addresses list if necessary
        userRepository.save(user); // Uncomment if needed

        Address savedAddress = addressRepository.save(address);
        return modelMapper.map(savedAddress, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream()
                .map(address -> modelMapper.map(address, AddressDTO.class))
                .toList();
    }

}
