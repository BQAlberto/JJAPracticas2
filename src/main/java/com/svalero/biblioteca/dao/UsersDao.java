package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.domain.Users;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import java.util.List;
public interface UsersDao {

    @SqlQuery("SELECT * FROM USERS")
    @UseRowMapper(UsersMapper.class)
    List<Users> getAllUsers();

    @SqlQuery("SELECT * FROM USERS WHERE idUser LIKE CONCAT('%',:searchTerm,'%') " +
            "OR firstName LIKE CONCAT('%',:searchTerm,'%') OR secondName LIKE CONCAT('%',:searchTerm,'%')")
    @UseRowMapper(UsersMapper.class)
    List<Users> getUsers(@Bind("searchTerm") String searchTerm);

    @SqlQuery("SELECT * FROM USERS WHERE idUser = ?")
    @UseRowMapper(UsersMapper.class)
    Users getUser(int idUser);

    @SqlUpdate ("INSERT INTO USERS (idUser, firstName, secondName, email, phoneNumber, password) VALUES (?, ?, ?, ?, ?, ?)")
    int addUser(int idUser, String firstName, String secondName, String email, String phoneNumber, String password);

    @SqlUpdate("UPDATE USERS SET idUser = ?, firstName = ?, secondName = ?, email = ?, phoneNumber = ?, password = ? WHERE idUser = ?")
    int updateUser(int idUser, String firstName, String secondName, String email, String phoneNumber, String password);

    @SqlUpdate("DELETE FROM USERS WHERE idUser = ?")
    int removeUser(int idUser);
}
