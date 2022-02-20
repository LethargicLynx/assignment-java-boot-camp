package com.javabootcamp.product;

import com.javabootcamp.product.comment.Comment;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_id_sequence",
            sequenceName = "product_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_id_sequence"
    )
    private Integer id;
    private String name;
    private String description;
    private Double normalPrice;
    private Double discountPrice;
    private Integer remains;
    private Double discountPercentage;

    @Transient
    private Double avgRating;

    @ToString.Exclude
    @OneToMany(
            mappedBy = "product",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        if(!this.comments.contains(comment)) {
            this.comments.add(comment);
            comment.setProduct(this);
        }
    }

    public void removeComment(Comment address) {
        if (this.comments.contains(address)) {
            this.comments.remove(address);
            address.setProduct(null);
        }
    }

    public List<Comment> getComments() {
        return comments;
    }


}
