package com.javabootcamp.customer;

import com.github.javafaker.Faker;
import com.javabootcamp.customer.address.Address;
import com.javabootcamp.customer.address.AddressRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.javabootcamp.clients"
)
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

            Customer customer = Customer.builder()
                    .fullName(faker.name().firstName() + " " + faker.name().lastName())
                    .email(String.format("%s.%s@skooldio.edu", faker.name().firstName(), faker.name().lastName()))
                    .phoneNumber(faker.phoneNumber().phoneNumber())
                    .build();

            Address address = Address.builder()
                    .street(faker.address().streetName())
                    .postalCode(faker.address().zipCode())
                    .district(faker.address().city())
                    .province(faker.address().city())
                    .country(faker.address().country())
                    .build();

            customer.setAddresses(new ArrayList<>());

            customer.addAddress(address);
            customerRepository.save(customer);
        };
    }
}
