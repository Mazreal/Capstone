/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.POServices;
import domain.PurchaseOrder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpSession;
import database.purchOrderJDBCUtil;
import domain.POItems;

/**
 *
 * @author 783385
 */
public class addPO extends HttpServlet {

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
            out.println("<title>Servlet addPO</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addPO at " + request.getContextPath() + "</h1>");
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
        POServices ps = new POServices();
        String action = request.getParameter("action");
        if (action != null && action.equals("view")) {
            int selectedUserNo = Integer.parseInt(request.getParameter("selectedPoNo"));
            try {
                //PurchaseOrder po = ps.get(selectedUserNo);
                POItems poi = ps.getPOItem(selectedUserNo);
                request.setAttribute("selectedPo", poi);
                int leng = poi.getMenuItem().size() - 1;
                request.setAttribute("end", leng);

            } catch (Exception ex) {
                Logger.getLogger(addPO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        List<PurchaseOrder> pos = null;
        try {
            pos = ps.getAllPO();
        } catch (Exception ex) {
            Logger.getLogger(addPO.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("pos", pos);
        getServletContext().getRequestDispatcher("/scripts/createPO.jsp").forward(request, response);

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

        HttpSession session = request.getSession();
        POServices ps = new POServices();
        purchOrderJDBCUtil pj = new purchOrderJDBCUtil();
        ArrayList<String> list = (ArrayList<String>) session.getAttribute("pos");
        String poNo = request.getParameter("poNo");
        String datePlaced = request.getParameter("datePlaced");
        String dateRequired = request.getParameter("dateRequired");
        String notes = request.getParameter("notes");
        String empName = request.getParameter("empName");
        String empEmail = request.getParameter("empEmail");
        String delivery = request.getParameter("delivery");
        String completed = request.getParameter("completed");
        String[] menuItem = request.getParameterValues("mealItems[]");
        String[] q = request.getParameterValues("quantity[]");
        ArrayList<Double> qty = new ArrayList<Double>();
        ArrayList<String> mi = new ArrayList<String>();

        String action = request.getParameter("action");
        if (list == null) {
            list = new ArrayList();
        }
        if (action.equals("delete")) {
            int selectedPoNo = Integer.parseInt(request.getParameter("selectedPoNo"));
            ps.deletePOItems(selectedPoNo);
            ps.deletePO(selectedPoNo);

        } else if (action.equals("add")) {

            int po = Integer.parseInt(poNo);
            String clientNo = request.getParameter("clientNo");
            int cl = Integer.parseInt(clientNo);
            Date dp = null;
            Date dr = null;
            try {
                dp = new SimpleDateFormat("yyyy-MM-dd").parse(datePlaced);
                dr = new SimpleDateFormat("yyyy-MM-dd").parse(dateRequired);
            } catch (ParseException ex) {
                Logger.getLogger(addPO.class.getName()).log(Level.SEVERE, null, ex);
            }
            PurchaseOrder p = new PurchaseOrder(po, cl, dp, dr, notes, empName, empEmail, false, false);
            POItems poi = null;
            double[] quantity = new double[q.length];

            for (int i = 0; i < quantity.length; i++) {
                quantity[i] = Double.parseDouble(q[i]);
                qty.add(quantity[i]);
                System.out.println(qty.get(i));
            }

            for (int x = 0; x < menuItem.length; x++) {

                mi.add(menuItem[x]);

            }

            ps.insertPO(p);
            ps.insertPOItem(po, cl, dp, dr, notes, empName, empEmail, false, false, qty, mi);

        } else if (action.equals("edit")) {

            int po = Integer.parseInt(poNo);
            String clientNo = request.getParameter("clientNo");
            String[] pin = request.getParameterValues("poItemNo");
            int[] poItem = new int[pin.length];
            ArrayList<Integer> poItemNo = new ArrayList<Integer>();
            ArrayList<Double> newQty = new ArrayList<Double>();
            ArrayList<String> newMeal = new ArrayList<String>();
            for (int i = 0; i < poItem.length; i++) {
                poItem[i] = Integer.parseInt(pin[i]);
                poItemNo.add(poItem[i]);
                System.out.println(poItemNo.get(i));
            }
            boolean d = false;
            boolean c = false;
            if (delivery.equalsIgnoreCase("true")) {
                d = true;
            }
            if (completed.equalsIgnoreCase("true")) {
                c = true;
            }
            int cl = Integer.parseInt(clientNo);
            Date dp = null;
            Date dr = null;
            try {
                dp = new SimpleDateFormat("yyyy-MM-dd").parse(datePlaced);
                dr = new SimpleDateFormat("yyyy-MM-dd").parse(dateRequired);
            } catch (ParseException ex) {
                Logger.getLogger(addPO.class.getName()).log(Level.SEVERE, null, ex);
            }
            POItems poi = null;
            double[] quantity = new double[q.length];

            for (int i = 0; i < quantity.length; i++) {
                quantity[i] = Double.parseDouble(q[i]);
                qty.add(quantity[i]);
                System.out.println(qty.get(i));
            }

            for (int x = 0; x < menuItem.length; x++) {

                mi.add(menuItem[x]);
                System.out.println(mi.get(x));
            }
            int size1 = mi.size();
            int size2 = poItemNo.size();
            System.out.println(size1 + " " + size2);
            if (size1 > size2) {
                newQty.add(qty.get(qty.size() - 1));
                newMeal.add(mi.get(mi.size() - 1));
                ps.insertPOItem(po, cl, dp, dr, notes, empName, empEmail, false, false, newQty, newMeal);
            } else {
                PurchaseOrder p = new PurchaseOrder(po, cl, dp, dr, notes, empName, empEmail, d, c);
                poi = new POItems(po, cl, dp, dr, notes, empName, empEmail, d, c, poItemNo, qty, mi);
                ps.updatePO(p);
                ps.updatePOItems(poi);
            }

        }

        List<PurchaseOrder> pos = null;
        try {

            pos = ps.getAllPO();

        } catch (Exception ex) {
            Logger.getLogger(addPO.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("pos", pos);
        getServletContext().getRequestDispatcher("/scripts/purchaseOrder.jsp").forward(request, response);
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
