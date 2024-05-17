package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.domain.Books;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BooksMapper implements RowMapper<Books>{

    @Override
    public Books map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Books(rs.getInt("idBook"),
                rs.getString("title"),
                rs.getString("isbn"),
                rs.getString("author"),
                rs.getString("path"),
                rs.getInt("quantity")
        );
    }
}
