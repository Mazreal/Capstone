/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.AccountService;
import services.UserService;

/**
 *
 * @author 791894
 */
public class login extends HttpServlet {

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
        //processRequest(request, response);
         HttpSession session = request.getSession();
        session.invalidate();
        
        getServletContext().getRequestDispatcher("/scripts/login.jsp").forward(request, response);
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
        //processRequest(request, response);
         String username = request.getParameter("username");
        String password = request.getParameter("password");
        AccountService ac = new AccountService();
        UserService us = new UserService();
        if (ac.login(username, password) != null) {
            HttpSession session = request.getSession();
             try {
                 if(us.get(username).getRoleID() == 'A')
                 {
                     session.setAttribute("username", username);
                     response.sendRedirect("AdminServlet");
                 }
                 else
                 {
                     session.setAttribute("username", username);
                     response.sendRedirect("mainServlet");
                 }
             } catch (Exception ex) {
                 Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
             }
        } 
        else{
             request.setAttribute("errorMessage", "Please enter a valid username and password");
            getServletContext().getRequestDispatcher("/scripts/login.jsp").forward(request, response);
        }
    
         //getServletContext().getRequestDispatcher("/scripts/login.jsp").forward(request, response);
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