package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.domain.Book;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BooksMapper implements RowMapper<Book>{

    @Override
    public Book map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Book(rs.getInt("idBook"),
                rs.getString("title"),
                rs.getString("isbn"),
                rs.getString("author"),
                rs.getString("path"),
                rs.getInt("quantity")
        );
    }
}
