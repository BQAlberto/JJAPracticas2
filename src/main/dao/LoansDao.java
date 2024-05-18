package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.domain.Loan;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import java.util.Date;
import java.util.List;

public interface LoansDao {

    @SqlQuery("SELECT * FROM LOANS")
    @UseRowMapper(LoansMapper.class)
    List<Loan> getAllLoans();

    @SqlQuery("SELECT * FROM LOANS WHERE idLoan LIKE CONCAT('%',:searchTerm,'%') " +
            "OR idBook LIKE CONCAT('%',:searchTerm,'%') OR idUser LIKE CONCAT('%',:searchTerm,'%')")
    @UseRowMapper(LoansMapper.class)
    List<Loan> getLoans(@Bind("searchTerm") String searchTerm);

    @SqlQuery("SELECT * FROM LOANS WHERE idLoan = ?")
    @UseRowMapper(LoansMapper.class)
    Loan getLoan(int idLoan);

    @SqlUpdate ("INSERT INTO LOANS (idBook, idUser, startDate, expectedDate) VALUES (?, ?, ?, ?, ?)")
    void addLoan( int idBook, int idUser, Date loanDate,Date expectedDate);

    @SqlUpdate("UPDATE LOANS SET idLoan = ?, idBook = ?, idUser = ?, startDate = ?, expectedDate = ?, returnDate = ? WHERE idLoan = ?")
    void updateLoan(int idLoan, int idBook, int idUser, Date loanDate, Date expectedDate, Date returnDate);

    @SqlUpdate("DELETE FROM LOANS WHERE idLoan = ?")
    void removeLoan(int idLoan);

    @SqlQuery("SELECT l.*, m.title as bookTitle FROM LOANS l JOIN BOOKS m ON l.idBook = m.idBook WHERE l.idUser = :idUser")
    @UseRowMapper(LoansMapper.class)
    List<Loan> findLoansByidUser(@Bind("idUser") int idUser);

    @SqlUpdate("UPDATE LOANS SET returnDate = :returnDate WHERE idLoan = :idLoan AND returnDate IS NULL")
    boolean updateReturnDate(@Bind("idLoan") int idLoan, @Bind("returnDate") Date returnDate);
}
