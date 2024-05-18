package com.svalero.biblioteca.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    private int idLoan;
    private int idBook;
    private int idUser;
    private Date startDate;
    private Date expectedReturnDate;
    private Date returnDate;
}