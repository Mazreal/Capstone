/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import database.Warehouse_KitchenJDBCUtil;
import domain.Ingredient;
import domain.Ingredient;
import domain.MealItem;
import domain.MealItem;
import domain.MealRecipe;
import domain.MealRecipe;
import domain.Station;
import domain.Station;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.IngredientService;
import services.MealService;

/**
 *
 * @author 783385
 */
@WebServlet(name = "addMealServelt", urlPatterns =
{
    "/addMeal"
})
public class addMealServlet extends HttpServlet
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
            out.println("<title>Servlet addMealServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addMealServlet at " + request.getContextPath() + "</h1>");
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
        {
            getServletContext().getRequestDispatcher("/WEB-INF/addMeal.jsp").forward(request, response);
        }
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
        HttpSession session = request.getSession();
        ArrayList<String> list = (ArrayList<String>) session.getAttribute("ingredienList");
        IngredientService is = new IngredientService();
        Warehouse_KitchenJDBCUtil wk = new Warehouse_KitchenJDBCUtil();
        Ingredient ingredient = null;
        MealItem mealItem = null;
        Station station = new Station();
        MealRecipe mr = new MealRecipe();
        String action = request.getParameter("action");
        if (list == null)
        {
            list = new ArrayList();
        }
        ArrayList<String> ingredientList = (ArrayList<String>) session.getAttribute("list");
        if (ingredientList == null)
        {
            ingredientList = new ArrayList<String>();
        }
        if (action.equals("save"))
        {
            ingredientList.add(request.getParameter("ingredientName"));
            session.setAttribute("list", ingredientList);
        }

        if (action.equals("add"))
        {
            String ingredients = request.getParameter("ingredientName");
            session.setAttribute("ingredients", list);
            String mealItemName = request.getParameter("mealItemName");
            session.setAttribute("mealItemName", mealItemName);

            // SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            Date date = new Date();
            int ingredientNo = 7;
            int mealItemNumber = 4;
            
             mealItem = new MealItem(mealItemNumber, mealItemName, 3, date, false, wk.getRecipe(mealItemNumber), 2.0);
                wk.insertMeal(mealItem);
                
            for (int i = 0; i < ingredientList.size(); i++)
            {
                ingredient = new Ingredient(ingredientNo, ingredientList.get(i), 3, date, false, "10", wk.getStation(2), 4.0);
                wk.insertIngredient(ingredient);
                
               

                mr.setMealItemNumber(mealItem.getMealItemNumber());
                ArrayList<Ingredient> iList = new ArrayList<Ingredient>();
                iList.add(ingredient);
                ArrayList<Double> qList = new ArrayList<Double>();
                qList.add(1.5);

            }

        }

        getServletContext().getRequestDispatcher("/scripts/Meal_Items.jsp").forward(request, response);
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
