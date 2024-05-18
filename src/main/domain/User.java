package com.svalero.biblioteca.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private int idUser;
    private String firstName;
    private String secondName;
    private String email;
    private String phoneNumber;
    private String password;
}
