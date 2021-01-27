/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import domain.Ingredient;
import domain.MealItem;
import domain.MealRecipe;
import domain.Station;
import java.io.IOException;
import java.util.ArrayList;
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
 * @author 769293
 */
public class MealEdit extends HttpServlet
{

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

        MealService ms = new MealService();
        String selectedMealNo = request.getParameter("selectedMeal");

        ArrayList<MealItem> meals = new ArrayList<MealItem>();
        ArrayList<MealRecipe> recipes = new ArrayList<MealRecipe>();
        MealItem selectedMeal;
        MealRecipe selectedRecipe;

        try
        {
            selectedMeal = ms.getMeal(Integer.parseInt(selectedMealNo));
            selectedRecipe = ms.getRecipe(Integer.parseInt(selectedMealNo));
            if (selectedMeal != null || selectedRecipe != null)
            {

                request.setAttribute("selectedMeal", selectedMeal);
                request.setAttribute("selectedRecipe", selectedRecipe);
            }

            meals = ms.getAllMeals2();
            recipes = ms.getAllRecipes();
            //System.out.println(recipes.size());

        } catch (Exception ex)
        {
            Logger.getLogger(mealServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (meals != null || recipes != null)
        {
            // request.setAttribute("message", recipes.toString());
            request.setAttribute("MealRecipe", recipes);
            request.setAttribute("mealitems", meals);
        }

        getServletContext().getRequestDispatcher("/scripts/edit.jsp").forward(request, response);
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
        MealService ms = new MealService();
        IngredientService is = new IngredientService();
        String selectedMealNo = request.getParameter("selectedMeal");

        String action = request.getParameter("action");
        if (action.equals("delete"))
        {
            System.out.println("WTF");
            try
            {
                MealItem selectedMeal = ms.getMeal(Integer.parseInt(selectedMealNo));
                MealRecipe selectedRecipe = selectedMeal.getMealRecipe();
                ms.deleteMealRecipe(selectedMeal.getMealItemNumber());
                for (int i = 0; i < selectedRecipe.getIngredientList().size(); i++)
                {
                    is.deleteIngredient(selectedRecipe.getIngredientList().get(i).getIngredientNumber());
                }
                ms.deleteMeal(selectedMeal.getMealItemNumber());

            } catch (Exception ex)
            {
                Logger.getLogger(MealEdit.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equals("save"))
        {
            System.out.println("This");
            String mealName = request.getParameter("mealName");
            String[] selectedIngredients = request.getParameterValues("selectedIngredient");
            String[] ingredientNames = request.getParameterValues("ingredientName");
            String[] weights = request.getParameterValues("weight");
            String[] shelfLifes = request.getParameterValues("shelfLife");
            String[] stationNos = request.getParameterValues("stationNo");
            String[] pars = request.getParameterValues("par");
            for (int i = 0; i < selectedIngredients.length; i++)
            {
                System.out.println("Ingredient Number: " + selectedIngredients[i]);
            }
            ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
            ArrayList<Double> ingredientQuantity = new ArrayList<Double>();
            MealRecipe mr = ms.getRecipe(Integer.parseInt(selectedMealNo));

            Ingredient oldIngredient;
            Ingredient newIngredient;

            for (int i = 0; i < selectedIngredients.length; i++)
            {
                Station station = is.getStation(Integer.parseInt(stationNos[i]));
                oldIngredient = is.getIngredient(Integer.parseInt(selectedIngredients[i]));
                newIngredient = new Ingredient(oldIngredient.getIngredientNumber(), ingredientNames[i], Integer.parseInt(shelfLifes[i]), null, false, pars[i], station, 0);
                boolean equals = true;
                // Check Fields
                if (oldIngredient.getIngredientShelfLife() != newIngredient.getIngredientShelfLife())
                {
                    equals = false;
                }
                if (oldIngredient.getPar() != newIngredient.getPar())
                {
                    equals = false;
                }
                if (oldIngredient.getStation().getStationID() != newIngredient.getStation().getStationID())
                {
                    equals = false;
                }
                int weightIndex = -1;
                for (int j = 0; j < mr.getIngredientList().size(); j++)
                {
                    if (mr.getIngredientList().get(j).getIngredientNumber() == oldIngredient.getIngredientNumber())
                    {
                        weightIndex = j;
                    }
                }

                System.out.println("Weight Index :" + weightIndex);
                if (mr.getIngredientQuantity().get(weightIndex)
                        != Double.parseDouble(weights[i]))
                {
                    equals = false;
                }
                if (equals = false)
                {
                    is.updateIngredient(newIngredient);
                }
                ingredientList.add(newIngredient);
                ingredientQuantity.add(Double.parseDouble(weights[i]));

            }
            int ingredientNoMax = is.getHighestIngredientNo() + 1;

            int newIngs = ingredientNames.length - selectedIngredients.length;
            if (newIngs > 0)
            {
                System.out.println("# New Ingredients:" + newIngs);
                for (int i = selectedIngredients.length; i < ingredientNames.length; i++)
                {
                    System.out.println("New ingredient: "+ingredientNames[i]);
                    Station station = is.getStation(Integer.parseInt(stationNos[i]));
                    newIngredient = new Ingredient(ingredientNoMax, ingredientNames[i], Integer.parseInt(shelfLifes[i]), null, false, pars[i], station, 0);
                    ingredientNoMax++;
                    is.insertIngredient(newIngredient);
                    ingredientList.add(newIngredient);
                    ingredientQuantity.add(Double.parseDouble(weights[i]));
                }
            }
            try
            {
                MealItem mealItem = ms.getMeal(Integer.parseInt(selectedMealNo));
                if(!mealItem.getMealName().equals(mealName))
                {
                    mealItem.setMealName(mealName);
                    ms.updateMeal(mealItem);
                }
            } catch (Exception ex)
            {
                Logger.getLogger(MealEdit.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            ms.deleteMealRecipe(Integer.parseInt(selectedMealNo));
            mr = new MealRecipe(Integer.parseInt(selectedMealNo), ingredientList, ingredientQuantity);
            ms.insertMealRecipe(mr);

        }
        response.sendRedirect("mealServlet");

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