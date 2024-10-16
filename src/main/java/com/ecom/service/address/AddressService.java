package com.ecom.service.address;

import com.ecom.model.User;
import com.ecom.payload.address.AddressDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface AddressService {
    @Transactional
    AddressDTO createAddress(AddressDTO addressDTO, User user);

    List<AddressDTO> getAddresses();

    AddressDTO getAddressesById(Long addressId);

    List<AddressDTO> getUserAddresses(User user);

    @Transactional
    AddressDTO updateAddress(Long addressId, AddressDTO addressDTO);

    String deleteAddress(Long addressId);
}
