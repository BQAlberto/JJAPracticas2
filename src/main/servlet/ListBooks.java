package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.Database;
import com.svalero.biblioteca.dao.BooksDao;
import com.svalero.biblioteca.domain.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listBooks")
public class ListBooks  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            List<Book> books = Database.getInstance().withExtension(BooksDao.class, BooksDao::getAllBooks);
            request.setAttribute("books", books);
            request.getRequestDispatcher("/library.jsp").forward(request, response);
        } catch (Exception e){
            e.printStackTrace();
            throw new ServletException("Error retrieving movies", e);
        }
    }
}