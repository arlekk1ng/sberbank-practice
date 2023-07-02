package ru.arlekk1ng.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "carts")
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String promoCode;

    @OneToMany(mappedBy = "cart")
    private List<CartProduct> cartProductList;

    @OneToOne(mappedBy = "cart")
    @JsonIgnore
    private Client client;
}
