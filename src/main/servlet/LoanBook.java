package com.svalero.biblioteca.servlet;

import oracle.jdbc.proxy.annotation.Post;
import com.svalero.biblioteca.dao.Database;
import com.svalero.biblioteca.dao.LoansDao;
import com.svalero.biblioteca.dao.BooksDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/loanBook")
public class LoanBook extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        if (request.getSession().getAttribute("idUser") == null){
            // si no hay login
            response.sendRedirect("/login"+ URLEncoder.encode("Please, sign in to loan a book.", StandardCharsets.UTF_8));
            return;
        }
        int idMovie = Integer.parseInt(request.getParameter("idBook"));
        int idUser = Integer.parseInt(request.getSession().getAttribute("idUser").toString());
        java.sql.Date startDate = new java.sql.Date(System.currentTimeMillis());
        java.sql.Date expectedDate = new java.sql.Date(System.currentTimeMillis()+604800000);
        String status = "Start Loan";

        try{
            Database.getInstance().inTransaction(handle -> {
                LoansDao loansDao = handle.attach(LoansDao.class);
                BooksDao booksDao = handle.attach(BooksDao.class);

                int getActualStock = booksDao.getActualStock(idMovie);
                if (getActualStock == 0) {
                    throw new IllegalStateException("Can't proccess the loan, there is no stock available");
                }

                loansDao.addLoan(idMovie,idUser,startDate,expectedDate);
                return null;
            });
            response.sendRedirect("listBooks");
        }catch (Exception e){

            response.sendRedirect("error" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8));
        }
    }
}