/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author 791894
 * 
 * EDIT AND DELETE OF REPORT
 */
public class report extends HttpServlet {

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

        getServletContext().getRequestDispatcher("/scripts/report_new.jsp").forward(request, response);
         String report = request.getParameter("typeReport");
        String action = request.getParameter("action");
         String path = getServletContext().getRealPath("/WEB-INF/Templates/");
        
        if(action.equals("add")){
                if(report.equals("expiredmeals")){
                    String filename = "Expired meals Template.xlsx";
                    Desktop.getDesktop().open(new File(path+filename));
                }if (report.equals("invoice")){
                    String filename = "Invoice Template.xlsx";
                    Desktop.getDesktop().open(new File(path+filename));
                } if (report.equals("grablist")){
                    String filename = "Grab List Template.xlsx";
                    Desktop.getDesktop().open(new File(path+filename));
                } if (report.equals("purchase")){
                    String filename = "PO Template.xlsx";
                    Desktop.getDesktop().open(new File(path+filename));
                }
            }
         getServletContext().getRequestDispatcher("/scripts/report_new.jsp").forward(request, response);
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
        
        
        String action = request.getParameter("action");
        String path1 = getServletContext().getRealPath("/WEB-INF/Expired meals/");
        String path2 = getServletContext().getRealPath("/WEB-INF/Grab List/");
        String path3 = getServletContext().getRealPath("/WEB-INF/Invoices/");
        String path4 = getServletContext().getRealPath("/WEB-INF/Purchase orders/");
        
       System.out.println("debug"+action);
             if(action.equals("openG")){
                    Desktop.getDesktop().open(new File(path2));
                    
            }
            
              if(action.equals("openI")){
                    Desktop.getDesktop().open(new File(path3));
            }
              
               if(action.equals("openE")){
                    Desktop.getDesktop().open(new File(path1));
            }
        
                if(action.equals("openP")){
                    Desktop.getDesktop().open(new File(path4));
            }
           
         getServletContext().getRequestDispatcher("/scripts/report_new.jsp").forward(request, response);
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