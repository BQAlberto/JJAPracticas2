package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.domain.Users;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersMapper implements RowMapper<Users>{
    @Override
    public Users map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Users(rs.getInt("idUser"),
                rs.getString("firstName"),
                rs.getString("secondName"),
                rs.getString("email"),
                rs.getString("phoneNumber"),
                rs.getString("password")
        );
    }
}
