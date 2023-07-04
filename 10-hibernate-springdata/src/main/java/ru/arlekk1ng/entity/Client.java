package ru.arlekk1ng.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "clients")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    // @JsonIgnore
    private String email;

    @Column(nullable = false)
    // @JsonIgnore
    private String login;

    @Column(nullable = false)
    // @JsonIgnore
    private String password;

    @OneToOne
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "FK_CLIENTS_CART"))
    private Cart cart;
}
