package com.javabootcamp.customer;

import com.javabootcamp.customer.address.Address;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record CustomerService(CustomerRepository customerRepository) {

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .fullName(customerRegistrationRequest.firstName())
                .email(customerRegistrationRequest.email())
                .build();

        // todo: check if email valid
        // todo: check if email not taken

        customerRepository.save(customer);
    }

    public Customer getCustomerDetailById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow();
    }

    public List<Address> getCustomerAddressListById(Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if(optionalCustomer.isEmpty()) {
            return null;
        }

        return optionalCustomer.get().getAddresses();
    }
}


