package com.javabootcamp.customer;

import com.javabootcamp.customer.address.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService customerService) {

    @PostMapping(path = "/customer")
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("new customer registration {}", customerRegistrationRequest);
        customerService.registerCustomer(customerRegistrationRequest);
    }

    @GetMapping(path = "/customer/{id}")
    public Customer getCustomerDetailByCustomerId(@PathVariable("id") Integer id) {
        return customerService.getCustomerDetailById(id);
    }

    @GetMapping(path = "/customer/{id}/addresses")
    public List<Address> getCustomerAddressListByCustomerId(Integer id) {
        return customerService.getCustomerAddressListById(id);
    }

    @PostMapping(path = "/customer/{id}/product")
    public void addProductToCart(@PathVariable("id") Integer id,
                                 @RequestParam(value = "productId") Integer productId) {
        customerService.addProductToCustomerCart(id, productId);
    }

}
