package com.svalero.biblioteca.dao;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

public interface BooksDao {
    @SqlQuery("SELECT * FROM BOOKS")
    @UseRowMapper(BooksMapper.class)
    List<Books> getAllBooks();

    @SqlQuery("SELECT * FROM BOOKS WHERE idBooks LIKE CONCAT('%',:searchTerm,'%') " +
            "OR title LIKE CONCAT('%',:searchTerm,'%') OR author LIKE CONCAT('%',:searchTerm,'%')")
    @UseRowMapper(BooksMapper.class)
    List<Books> getBooks(@Bind("searchTerm") String searchTerm);

    @SqlQuery("SELECT * FROM BOOKS WHERE idBook = ?")
    @UseRowMapper(BooksMapper.class)
    Books getBook(int idBook);

    @SqlUpdate ("INSERT INTO BOOKS (idBook, title, isbn, author, path, quantity) VALUES (?, ?, ?, ?, ?, ?)")
    int addBook(int idBook, String title, String isbn, String author, String path, int quantity);

    @SqlUpdate("UPDATE BOOKS SET idBook = ?, title = ?, isbn = ?, author = ?, path = ?, quantity = ? WHERE idBook = ?")
    int updateBook(int idBook, String title, String isbn, String author, String path, int quantity);

    @SqlUpdate("DELETE FROM BOOKS WHERE idBook = ?")
    int removeBook(int idBook);

}
