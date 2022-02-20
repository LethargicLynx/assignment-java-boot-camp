package com.javabootcamp.customer;

import com.javabootcamp.customer.address.Address;
import com.javabootcamp.customer.cart.Cart;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )
    private Integer id;
    private String fullName;
    private String email;
    private String phoneNumber;

    @OneToOne(
            mappedBy = "customer",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}

    )
    private Cart cart;

    @ToString.Exclude
    @OneToMany(
            mappedBy = "customer",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Address> addresses = new ArrayList<>();

    public void addAddress(Address address) {
        if(!this.addresses.contains(address)) {
            this.addresses.add(address);
            address.setCustomer(this);
        }
    }

    public void removeAddress(Address address) {
        if (this.addresses.contains(address)) {
            this.addresses.remove(address);
            address.setCustomer(null);
        }
    }

    public List<Address> getAddresses() {
        return addresses;
    }

}
