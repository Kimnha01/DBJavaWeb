package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dto.Order;
import dto.OrderDetail;
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
public class ManageOrderView extends HttpServlet {

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
            String[] arr = {"", "Pending", "Processing", "Completed", "Cancel"};
            ArrayList<Order> list = (ArrayList<Order>) request.getAttribute("listOrder");
            if (list != null) {
                out.print("<script src='mycode.js'></script>");
                out.print("<table>");
                out.print("<tr><th>ID</th><th>ORDER DATE</th><th>CUSTOMER</th><th>STATUS</th></tr>");
                for (Order o: list) {
                    out.print("<tr>");
                    out.print("<td>" + o.getId() + "</td>");
                    out.print("<td>" + o.getOrderDate().toString() + "</td>");
                    out.print("<td>" + o.getAccId() + "</td>");
                    
                    out.print("<td>" + arr[o.getStatus()] + "</td>");
                    
                    out.print("<td><a href='ChangeStatusServlet?status=2&id=" + o.getId() + "'>confirm</a></td>");
                    out.print("<td><a href='ChangeStatusServlet?status=3&id=" + o.getId() + "'>cancel</a></td>");
                    out.print("<td><a href='ChangeStatusServlet?status=4&id=" + o.getId() + "'>completed</a></td>");
                    
                    out.print("<td><a href='#' onclick='displayDetail(" + o.getId() + ")'>detail</a></td>");
                    out.print("</tr>");
                    
                    out.print("<tr id='" + o.getId() + "' style='display:none'>");
                    out.print("<td colspan='5'>");
                        out.print("<table>");
                        for (OrderDetail detail: o.getDetails()) {
                            out.print("<tr>");
                            out.print("<td>" + detail.getItemId() + "</td>");
                            out.print("<td>" + detail.getQuantity() + "</td>");
                            out.print("</tr>");
                        }
                            
                        out.print("</table>");
                    out.print("</td>");
                    out.print("</tr>");
                }
                out.print("</table>");
            }
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
