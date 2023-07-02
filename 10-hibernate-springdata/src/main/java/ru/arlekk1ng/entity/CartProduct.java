package ru.arlekk1ng.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "carts_products")
@Data
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

//    @Column(nullable = false)
//    private long cartId;
    @ManyToOne
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "FK_CARTS_PRODUCTS_CART"))
    @JsonIgnore
    private Cart cart;

//    @Column(nullable = false)
//    private long productId;
    @ManyToOne
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "FK_CARTS_PRODUCTS_PRODUCT"))
    private Product product;

    @Column(nullable = false)
    private int productCount;
}
