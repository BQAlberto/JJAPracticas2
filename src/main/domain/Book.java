package com.svalero.biblioteca.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    private int idMovie;
    private String title;
    private String isbn;
    private String author;
    private String path;
    private int quantity;
}