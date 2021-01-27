/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;

/**
 *
 * @author 783385
 */
public class MealRecipe {

    private int mealItemNumber;
    private ArrayList<Ingredient> ingredientList;
    private ArrayList<Double> ingredientQuantity;

    /**
     * Builds an empty MealRecipe
     */
    public MealRecipe()
    {
        // default
    }
    
    /**
     * Builds a MealRecipe according to the provided parameters
     * @param mealItemNumber The Meal number for the recipe
     * @param ingredientList ArrayList of ingredients for the recipe
     * @param ingredientQuantity ArrayList of ingredient quantities for the recipe
     */
    public MealRecipe(int mealItemNumber, ArrayList<Ingredient> ingredientList, ArrayList<Double> ingredientQuantity) {
        super();
        this.mealItemNumber = mealItemNumber;
        this.ingredientList = ingredientList;
        this.ingredientQuantity = ingredientQuantity;
    }

    /**
     * Returns the meal number for the recipe
     * @return mealItemNumber
     */
    public int getMealItemNumber() 
    {
        return mealItemNumber;
    }

    /**
     * Sets the meal number for the recipe
     * @param mealItemNumber meal number for the recipe
     */
    public void setMealItemNumber(int mealItemNumber) 
    {
        this.mealItemNumber = mealItemNumber;
    }

    /**
     * Returns an ArrayList of the ingredients for this recipe
     * @return ingredientList
     */
    public ArrayList<Ingredient> getIngredientList() 
    {
        return ingredientList;
    }

    /**
     * Returns an ArrayList of the ingredients for this recipe
     * @param ingredientList List of ingredients for this recipe
     */
    public void setIngredientList(ArrayList<Ingredient> ingredientList) 
    {
        this.ingredientList = ingredientList;
    }

    /**
     * Returns an ArrayList of quantities that each ingredient is required for the recipe
     * @return ingredientQuantity
     */
    public ArrayList<Double> getIngredientQuantity() 
    {
        return ingredientQuantity;
    }

    /**
     * Sets an ArrayList of quantities that each ingredient is required for the recipe
     * @param ingredientQuantity ArrayList of quantities that each ingredient is required for the recipe
     */
    public void setIngredientQuantity(ArrayList<Double> ingredientQuantity) 
    {
        this.ingredientQuantity = ingredientQuantity;
    }
}
