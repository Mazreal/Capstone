package servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.Ingredient;
import domain.MealItem;
import domain.MealRecipe;
import domain.Station;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.IngredientService;
import services.MealService;

/**
 *
 * @author 769293
 */
public class MealList extends HttpServlet {


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
                Logger.getLogger(MealList.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (meals != null || recipes != null)
            {
                // request.setAttribute("message", recipes.toString());
                request.setAttribute("MealRecipe", recipes);
                request.setAttribute("mealitems", meals);
            }
            /* TESTING
            for (int i = 0; i < recipes.size(); i++)
            {
                System.out.println("--------------MEAL--------------------------");
                System.out.println(recipes.get(i).getMealItemNumber());
                System.out.println("-----------INGREDIENTS----------------------");
                for (int j = 0; j < recipes.get(i).getIngredientList().size(); j++)
                {
                    System.out.println(recipes.get(i).getIngredientList().get(j).getIngredientName() + " ---- " + recipes.get(i).getIngredientQuantity().get(j));
                }

            }
            */
            getServletContext().getRequestDispatcher("/scripts/add.jsp").forward(request, response);
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
        
        // Get Values from Modal
        String mealName = request.getParameter("mealName");

        String [] ingredientNames = request.getParameterValues("ingredientName");
        String [] weights = request.getParameterValues("weight");
        String [] shelfLifes = request.getParameterValues("shelfLife");
        String [] stationNos = request.getParameterValues("stationNo");
        String [] pars = request.getParameterValues("par");
        
        ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
        ArrayList<Double> ingredientQuantity = new ArrayList<Double>();
        
        MealService ms = new MealService();
        IngredientService is = new IngredientService();
        int ingredientNoMax = is.getHighestIngredientNo() +1;
        int ingredientSL_Min = 999999;
        
        Ingredient ing;
        for(int i = 0; i< ingredientNames.length; i++)
        {
            //System.out.println(ingredientNames[i] +", " + weights[i] + ", " + stationNos[i]);
            int shelfLife = Integer.parseInt(shelfLifes[i]);
            Station station = is.getStation(Integer.parseInt(stationNos[i]));
            ing = new Ingredient(ingredientNoMax, ingredientNames[i], shelfLife,null,false,pars[i],station,0);
            ingredientNoMax++;
            is.insertIngredient(ing);
            ingredientList.add(ing);
            ingredientQuantity.add(Double.parseDouble(weights[i]));
            
            if(shelfLife < ingredientSL_Min)
            {
                ingredientSL_Min = shelfLife;
            }
        }
        System.out.println("List size: "+ingredientList.size() +", "+ ingredientQuantity.size());
        
        int mealNoMax = ms.getHighestMealNo()+1;
        MealRecipe mr = new MealRecipe(mealNoMax, ingredientList, ingredientQuantity);
        MealItem mi = new MealItem(mealNoMax, mealName, ingredientSL_Min, null, false, mr, 0);
        ms.insert(mi);
        ms.insertMealRecipe(mr);
        response.sendRedirect("mealServlet");
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
