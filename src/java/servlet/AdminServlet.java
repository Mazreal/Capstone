/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import database.UserJDBCUtil;
import domain.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.UserService;

/**
 *
 * @author 769293
 */
public class AdminServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
       // processRequest(request, response);
        UserService us = new UserService();
        String action = request.getParameter("action");
        if (action != null && action.equals("view")) {
            int selectedUserNo = Integer.parseInt(request.getParameter("selectedUserNo"));
            try {
                Users user = us.getUser(selectedUserNo);
                request.setAttribute("selectedUser", user);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        List<Users> users = null;
        try {
            users = us.getAll();
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/scripts/admin.jsp").forward(request, response);
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
       // processRequest(request, response);
         HttpSession session = request.getSession();
        ArrayList<String> list = (ArrayList<String>) session.getAttribute("users");
        UserJDBCUtil uj = new UserJDBCUtil();
         UserService us = new UserService();
        Users user = null;
        //int userNo = Integer.parseInt(request.getParameter("userNo"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("roleID");
        String action = request.getParameter("action");
        if (list == null) {
            list = new ArrayList();
        }
        if (action.equals("delete"))
        {
            int selectedUserNo = Integer.parseInt(request.getParameter("selectedUserNo"));
            uj.deleteUser(selectedUserNo);
            System.out.println("hrllo " + selectedUserNo);
        }
        else if (action.equals("add")) 
        {
            if(username.equals("") || password.equals(""))
            {
                request.setAttribute("invalid", "Please fill the form.");
            }
            else
            {
                char roleID = role.charAt(0);
                us.insertUser(username, password, roleID);
            }
        } else if (action.equals("edit")) 
        {
            if(username.equals("") || password.equals(""))
            {
                request.setAttribute("invalid", "Please fill the form.");
            }
            else {
            int userNo = Integer.parseInt(request.getParameter("userNo"));
            char roleID = role.charAt(0);
            user = new Users(userNo, username, password, roleID);
            uj.updateUser(user);
        }
        }

        List<Users> users = null;
        try {
            users = us.getAll();
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/scripts/admin.jsp").forward(request, response);
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
