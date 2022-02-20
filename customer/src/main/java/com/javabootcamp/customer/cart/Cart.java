package com.javabootcamp.customer.cart;

import com.javabootcamp.customer.Customer;
import com.javabootcamp.customer.cart.product.CartProduct;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {

    @Id
    @SequenceGenerator(
            name = "cart_id_sequence",
            sequenceName = "cart_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_id_sequence"
    )
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "customer_id_fk"
            )
    )
    private Customer customer;


    @ToString.Exclude
    @OneToMany(
            mappedBy = "cart",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<CartProduct> cartProducts = new ArrayList<>();

    public void addCartProduct(CartProduct cartProduct) {
        if(!this.cartProducts.contains(cartProduct)) {
            this.cartProducts.add(cartProduct);
            cartProduct.setCart(this);
        }
    }

    public void removeCartProduct(CartProduct cartProduct) {
        if (this.cartProducts.contains(cartProduct)) {
            this.cartProducts.remove(cartProduct);
            cartProduct.setCart(null);
        }
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

}
