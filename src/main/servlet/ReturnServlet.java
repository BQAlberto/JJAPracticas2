package com.svalero.biblioteca.servlet;

import com.svalero.biblioteca.dao.LoansDao;
import com.svalero.biblioteca.dao.BooksDao;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import static com.svalero.biblioteca.dao.Database.jdbi;

@WebServlet("/return")
public class ReturnServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int idLoan = Integer.parseInt(request.getParameter("idLoan"));
        try {
            jdbi.inTransaction(handle -> {
                LoansDao loansDao = handle.attach(LoansDao.class);
                BooksDao booksDao = handle.attach(BooksDao.class);


                boolean updated = loansDao.updateReturnDate(idLoan, new Date(System.currentTimeMillis()));
                if (!updated) {
                    throw new IllegalStateException("No se pudo actualizar la fecha de devolución.");
                }


                int idMovie = handle.createQuery("SELECT idMovie FROM loans WHERE idLoan = :idLoan")
                        .bind("idLoan", idLoan)
                        .mapTo(Integer.class)
                        .findOnly();



                return null;


            });
            response.sendRedirect("listLoans?success= Movie returned");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Error processing book return");
        }
    }
}