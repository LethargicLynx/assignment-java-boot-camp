package com.javabootcamp.customer.cart.product;

import com.javabootcamp.customer.cart.Cart;
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
public class CartProduct {

    @Id
    @SequenceGenerator(
            name = "cartproduct_id_sequence",
            sequenceName = "cartproduct_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cartproduct_id_sequence"
    )
    private Integer id;
    private Integer productId;

    @ManyToOne
    @JoinColumn(
            name="cart_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "cart_cartproduct_fk"
            )
    )
    private Cart cart;

    public CartProduct(Integer productId, Cart cart) {
        this.productId = productId;
        this.cart = cart;
    }
}
