package com.javabootcamp.customer.address;

import com.javabootcamp.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @SequenceGenerator(
            name = "address_sequence",
            sequenceName = "address_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "address_sequence"
    )
    private Integer id;
    private String street;
    private String postalCode;
    private String district;
    private String country;
    private String province;

    @ManyToOne
    @JoinColumn(
            name="customer_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "customer_address_fk"
            )
    )
    private Customer customer;
}
