package com.javabootcamp.customer;

import com.javabootcamp.clients.product.ProductClient;
import com.javabootcamp.customer.address.Address;
import com.javabootcamp.customer.cart.Cart;
import com.javabootcamp.customer.cart.CartRepository;
import com.javabootcamp.customer.cart.product.CartProduct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record CustomerService(CustomerRepository customerRepository,
                              CartRepository cartRepository,
                              ProductClient productClient) {

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

    public void addProductToCustomerCart(Integer customerId, Integer productId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();

        if(productClient.isProductExists(productId).isExist()) {
            // todo: throw error product NotFoundException
            return ;
        }

        Optional<Cart> optionalCart = cartRepository.findByCustomerId(customerId);
        Cart cart;
        if(optionalCart.isEmpty()) {
            // todo: throw error customer NotFoundException
            cart = new Cart(customer);
            customer.setCart(cart);
            customerRepository.saveAndFlush(customer);
        } else {
            cart = optionalCart.get();
        }

        cart.addCartProduct(new CartProduct(productId, cart));
        cartRepository.save(cart);
    }
}


