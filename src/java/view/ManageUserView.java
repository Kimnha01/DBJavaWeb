/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.AccountDAO;
import dto.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kim Nha
 */
public class ManageUserView extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            AccountDAO d = new AccountDAO();
            ArrayList<Account> list = (ArrayList<Account>) request.getAttribute("listUser");
            if (list.isEmpty()) {
                out.print("<p>No data</p>");
            } else if (list != null) {
                out.print("<form action='SearchAccountServlet' method='post'>");
                out.print("<input type='text' name='txtemail' placeholder='enter email'/>");
                out.print("<input type='submit' value='search'/>");
                out.print("</form>");
                out.print("<table>");
                out.print("<tr><th>ID</th><th>NAME</th><th>EMAIL</th><th>ACTION</th></tr>");
                for (Account a : list) {
                    out.print("<tr>");
                    out.print("<td>" + a.getId() + "</td>");
                    out.print("<td>" + a.getFullname() + "</td>");
                    out.print("<td>" + a.getEmail() + "</td>");
                    out.print("<td><a href='RemoveAccountServlet?accId=" + a.getId() + "' onclick='return window.confirm(\"Are you sure to delete?\")'>Remove Account</a></td>");
                    out.print("</tr>");
                }
                out.print("</table>");

            }
            else
                response.sendRedirect("index.html");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
