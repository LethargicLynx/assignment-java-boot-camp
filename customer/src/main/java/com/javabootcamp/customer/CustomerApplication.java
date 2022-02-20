package com.javabootcamp.customer;

import com.github.javafaker.Faker;
import com.javabootcamp.customer.address.Address;
import com.javabootcamp.customer.address.AddressRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            CustomerRepository customerRepository,
            AddressRepository addressRepository) {
        return args -> {
            Faker faker = new Faker();
            String fullName = faker.name().firstName() + " " + faker.name().lastName();
            String email = String.format("%s.%s@skooldio.edu", faker.name().firstName(), faker.name().lastName());
            String phoneNumber = faker.phoneNumber().phoneNumber();

            Customer customer = Customer.builder()
                    .fullName(fullName)
                    .email(email)
                    .phoneNumber(phoneNumber)
                    .build();

            Address address = Address.builder()
                    .street("71/10-2 Moo 4 Bangwaek Road Klongkhwang")
                    .postalCode("10160")
                    .district("Saphan Sung")
                    .province("Bangkok")
                    .country("Thailand")
                    .build();

            customer.setAddresses(new ArrayList<>());

            customer.addAddress(address);
            customerRepository.save(customer);
        };
    }
}
