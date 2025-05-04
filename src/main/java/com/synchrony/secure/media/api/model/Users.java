package com.synchrony.secure.media.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String email;

    @ElementCollection
    @CollectionTable(name = "users_image_urls")
    @Column(name = "image_urls", length = 1000) // Increase VARCHAR size
    private List<String> imageUrls = new ArrayList<>();
}
