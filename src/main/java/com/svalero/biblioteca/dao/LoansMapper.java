package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.domain.Loans;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoansMapper implements RowMapper<LoansDao> {

    @Override
    public Loans map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new loans(rs.getInt("idLoan"),
                rs.getInt("idBook"),
                rs.getInt("idUser"),
                rs.getDate("startDate"),
                rs.getDate("expectedDate"),
                rs.getDate("returnDate")
        );
    }

}
