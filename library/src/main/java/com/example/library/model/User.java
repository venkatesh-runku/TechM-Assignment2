package com.example.library.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id @GeneratedValue
    private Long userId;
    private String name;
    private String membershipType;
}
