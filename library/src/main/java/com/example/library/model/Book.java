package com.example.library.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book {
    @Id @GeneratedValue
    private Long bookId;
    private String title;
    private String author;
    private String category;
    private boolean availability = true;
}
