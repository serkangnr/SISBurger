package com.serkanguner.service;

import com.serkanguner.entity.Address;
import com.serkanguner.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public void save(Address address) {
        addressRepository.save(address);
    }

    public void delete(Address address) {
        addressRepository.delete(address);
    }

    public void update(Address address) {
        addressRepository.save(address);
    }

    public Address findByUserId(String userid) {
        return addressRepository.findById(userid).orElse(null);
    }

    public Address findById(String id) {
        return addressRepository.findById(id).orElse(null);
    }
}
