/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import domain.Client;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.ClientService;

/**
 *
 * @author 769293
 */
public class ManageClientServlet extends HttpServlet
{

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
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManageClientServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageClientServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally
        {
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
            throws ServletException, IOException
    {

        ClientService cs = new ClientService();

        ArrayList<Client> clientList = cs.getAllClients();
        if (clientList != null)
        {
            request.setAttribute("clientList", clientList);
        }
        getServletContext().getRequestDispatcher("/scripts/clients.jsp").forward(request, response);
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
            throws ServletException, IOException
    {

        String selectedClient = request.getParameter("selectedClient");

        String action = request.getParameter("action");
        ClientService cs = new ClientService();
        if (action.equals("delete"))
        {
            if (!selectedClient.equals("undefined"))
            {
                cs.deleteClient(Integer.parseInt(selectedClient));
            } else
            {
                response.sendRedirect("ManageClientServlet");
            }
        } else
        {
            String clientName = request.getParameter("clientName");
            String clientEmail = request.getParameter("clientEmail");
            String clientAddress = request.getParameter("clientAddress");
            String clientCompany = request.getParameter("clientCompany");
            String clientPhone = request.getParameter("clientPhone");
            Client client = null;
            System.out.println(selectedClient);
            if (selectedClient.equals("undefined")) // Adding client
            {
                int highestId = cs.findHighestClientId() + 1;
                client = new Client(highestId, clientName, clientCompany, clientAddress, clientEmail, clientPhone);
                cs.insertClient(client);
                //response.sendRedirect("ManageClientServlet");
            } else
            {
                client = new Client(Integer.parseInt(selectedClient), clientName, clientCompany, clientAddress, clientEmail, clientPhone);
                cs.updateClient(client);

            }
        }
        response.sendRedirect("ManageClientServlet");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
