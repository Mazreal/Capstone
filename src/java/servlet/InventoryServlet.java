/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import domain.Ingredient;
import domain.MealItem;
import domain.MealRecipe;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.IngredientService;
import services.MealService;

/**
 *
 * @author 673970
 */
public class InventoryServlet extends HttpServlet
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
            out.println("<title>Servlet InventoryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InventoryServlet at " + request.getContextPath() + "</h1>");
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
        ArrayList<MealItem> meals = new ArrayList<MealItem>();
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

        MealService ms = new MealService();
        try
        {
            meals = ms.getAllMeals();
            ingredients = ms.getAllIngredients();
            ms.updateExpiredItems();

        } catch (Exception ex)
        {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (meals != null || ingredients != null)
        {
            request.setAttribute("ingredientList", ingredients);
            request.setAttribute("mealitems", meals);
        }
        getServletContext().getRequestDispatcher("/scripts/inventory.jsp").forward(request, response);
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

        String action = request.getParameter("action");
        MealService ms = new MealService();
        ArrayList<String> mealRejects = new ArrayList<String>();
        ArrayList<String> ingredientRejects = new ArrayList<String>();
        if (action.equals("pullExpired"))
        {
           ms.pullExpiredItems();
            System.out.println("Yep");

        } else if (action.equals("addMeal"))
        {
            String[] mealNames = request.getParameterValues("mealName[]");
            String[] mealQuantities = request.getParameterValues("mealQuantity[]");
            String[] mealDates = request.getParameterValues("mealDate[]");

            MealItem meal = null;

            for (int i = 0; i < mealNames.length; i++)
            {
                int mealNo = ms.getMealItemNo(mealNames[i]);

                if (mealNo == -1)
                {
                    mealRejects.add(mealNames[i]);
                } else
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date mealDate = null;

                    try
                    {
                        mealDate = sdf.parse(mealDates[i]);
                        meal = new MealItem(mealNo, mealNames[i], 0, mealDate, false, null, Double.parseDouble(mealQuantities[i]));
                        ms.insertMealItem(meal);
                    } catch (ParseException ex)
                    {
                        Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        } else if (action.equals("addIngredient"))
        {
            String[] ingredientNames = request.getParameterValues("ingredientName[]");
            String[] ingredientQuantities = request.getParameterValues("quantity[]");
            String[] ingredientDates = request.getParameterValues("dateProduced[]");

            IngredientService is = new IngredientService();
            Ingredient ingredient = null;
            for (int i = 0; i < ingredientNames.length; i++)
            {
                int ingredientNo = is.getIngredientNo(ingredientNames[i]);
                if (ingredientNo == -1)
                {
                    ingredientRejects.add(ingredientNames[i]);
                } else
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date ingredientDate = null;

                    try
                    {
                        ingredientDate = sdf.parse(ingredientDates[i]);
                        ingredient = new Ingredient(ingredientNo, ingredientNames[i], 0, ingredientDate, false, null, null, Double.parseDouble(ingredientQuantities[i]));
                        is.insertIngredientItem(ingredient);
                    } catch (ParseException ex)
                    {
                        Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }

        ArrayList<MealItem> meals = new ArrayList<MealItem>();
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

        try
        {
            meals = ms.getAllMeals();
            ingredients = ms.getAllIngredients();

        } catch (Exception ex)
        {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (meals != null || ingredients != null)
        {
            request.setAttribute("ingredientList", ingredients);
            request.setAttribute("mealitems", meals);
        }
        if (mealRejects.size() > 0)
        {
            request.setAttribute("message", "The following Meals were invalid: " + mealRejects);
        }
        if (ingredientRejects.size() > 0)
        {
            request.setAttribute("message", "The following Ingredients were invalid: " + ingredientRejects);
        }

        getServletContext().getRequestDispatcher("/scripts/inventory.jsp").forward(request, response);

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
