package com.javabootcamp.customer.address;

import org.springframework.stereotype.Service;

@Service
public record AddressService(AddressRepository addressRepository) {
}
