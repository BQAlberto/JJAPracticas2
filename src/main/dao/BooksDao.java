package com.svalero.biblioteca.dao;

import com.svalero.biblioteca.domain.Book;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import java.util.List;

public interface BooksDao {
    @SqlQuery("SELECT * FROM BOOKS")
    @UseRowMapper(BooksMapper.class)
    List<Book> getAllBooks();

    @SqlQuery("SELECT * FROM BOOKS WHERE idBook LIKE CONCAT('%',:searchTerm,'%') " +
            "OR title LIKE CONCAT('%',:searchTerm,'%') OR author LIKE CONCAT('%',:searchTerm,'%')")
    @UseRowMapper(BooksMapper.class)
    List<Book> getBooks(@Bind("searchTerm") String searchTerm);

    @SqlQuery("SELECT * FROM BOOKS WHERE idBook = ?")
    @UseRowMapper(BooksMapper.class)
    Book getBook(int idBook);

    @SqlUpdate ("INSERT INTO BOOKS (idBook, title, isbn, author, path, quantity) VALUES (?, ?, ?, ?, ?, ?)")
    void addBook(int idBook, String title, String isbn, String author, String path, int quantity);

    @SqlUpdate("UPDATE BOOKS SET idBook = ?, title = ?, isbn = ?, author = ?, path = ?, quantity = ? WHERE idBook = ?")
    void updateBook(int idBook, String title, String isbn, String author, String path, int quantity);

    @SqlUpdate("DELETE FROM BOOKS WHERE idBook = ?")
    void removeBook(int idBook);

    @SqlUpdate("SELECT " +
            "    (SELECT quantity FROM MOVIES WHERE idBook = :idMovie) - " +
            "    (SELECT COUNT(idLoan) FROM LOANS WHERE idBook = :idMovie AND returnDate IS NULL) AS available_quantity" +
            "FROM DUAL")
    int getActualStock(@Bind("idBook") int idMovie);


}
