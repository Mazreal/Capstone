/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import database.DBConnection;
import domain.MealItem;
import domain.MealRecipe;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.CSS;
import services.MealService;
import services.MealService;

/**
 *
 * @author 673970
 */
public class mealServlet extends HttpServlet
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
            out.println("<title>Servlet mealServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet mealServlet at " + request.getContextPath() + "</h1>");
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
            // Set services
           
            // Create Array Lists
            ArrayList<MealItem> meals = new ArrayList<MealItem>();
            ArrayList<MealRecipe> recipes = new ArrayList<MealRecipe>(); 
            
            MealService ms = new MealService();
            try
            {
                //meals = ms.getAllMeals();
                meals = ms.getAllMeals2();
                recipes = ms.getAllRecipes();
                System.out.println(recipes.size());
                
            } catch (Exception ex)
            {
                Logger.getLogger(mealServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(meals != null || recipes != null)
            {
                // request.setAttribute("message", recipes.toString());
                request.setAttribute("MealRecipe", recipes);
                request.setAttribute("mealitems", meals);
            }
            /*
            for(int i = 0; i < recipes.size(); i++)
            {
                System.out.println("--------------MEAL--------------------------");
                System.out.println(recipes.get(i).getMealItemNumber());
                System.out.println("-----------INGREDIENTS----------------------");
                for(int j = 0; j < recipes.get(i).getIngredientList().size(); j++)
                {
                    System.out.println(recipes.get(i).getIngredientList().get(j).getIngredientName() + " ---- " + recipes.get(i).getIngredientQuantity().get(j));
                }
                
            }
            */
            getServletContext().getRequestDispatcher("/scripts/Meal_Items.jsp").forward(request, response);
        }
      
        //getServletContext().getRequestDispatcher("/WEB-INF/test.jsp").forward(request, response);
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
