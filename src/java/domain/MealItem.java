/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;

/**
 *
 * @author 783385
 */
public class MealItem {

    private int nextMealItemNumber;
    private int mealItemNumber;
    private String mealName;
    private int mealShelfLife;
    private Date mealDateProduced;
    private boolean mealExpired;
    private MealRecipe mealRecipe;
    private double quantity;

    /**
     * Builds an empty MealItem
     */
    public MealItem ()
    {
    }
    
    /**
     * Builds a MealItem according to the provided parameters.
     * @param mealItemNumber Meal's number
     * @param mealName Meal's name
     * @param mealShelfLife Meal's shelf life
     * @param mealDateProduced Meal's date produced
     * @param mealExpired Whether the meal is expired.
     * @param mealRecipe Meal's corresponding MealRecipe object
     * @param quantity Meal's quantity
     */
    public MealItem(int mealItemNumber, String mealName, int mealShelfLife,
            Date mealDateProduced, boolean mealExpired, MealRecipe mealRecipe, double quantity) 
    {
        super();
        //this.nextMealItemNumber = nextMealItemNumber;
        this.mealItemNumber = mealItemNumber;
        this.mealName = mealName;
        this.mealShelfLife = mealShelfLife;
        this.mealDateProduced = mealDateProduced;
        this.mealExpired = mealExpired;
        this.mealRecipe = mealRecipe;
        this.quantity = quantity;
    }

    /**
     * Returns the meal's number
     * @return mealItemNumber
     */
    public int getMealItemNumber() 
    {
        return mealItemNumber;
    }

    /**
     * Sets the meal's number
     * @param mealItemNumber meal's number
     */
    public void setMealItemNumber(int mealItemNumber) 
    {
        this.mealItemNumber = mealItemNumber;
    }

    /**
     * Returns the meal's name
     * @return mealName
     */
    public String getMealName() 
    {
        return mealName;
    }

    /**
     * Sets the meal's name
     * @param mealName meal's name
     */
    public void setMealName(String mealName) 
    {
        this.mealName = mealName;
    }

    /**
     * Returns the meal's shelf life.
     * @return mealShelfLife.
     */
    public int getMealShelfLife() 
    {
        return mealShelfLife;
    }

    /**
     * Sets the meal's shelf life.
     * @param mealShelfLife
     */
    public void setMealShelfLife(int mealShelfLife) 
    {
        this.mealShelfLife = mealShelfLife;
    }

    /**
     * Returns the date the meal was produced
     * @return mealDateProduced
     */
    public Date getMealDateProduced() 
    {
        return mealDateProduced;
    }

    /**
     * Sets the date the meal was produced
     * @param mealDateProduced Date the meal was produced
     */
    public void setMealDateProduced(Date mealDateProduced) 
    {
        this.mealDateProduced = mealDateProduced;
    }

    /**
     * Returns whether the meal is expired.
     * @return mealExpired
     */
    public boolean isMealExpired() 
    {
        return mealExpired;
    }

    /**
     * Sets whether the meal is expired.
     * @param mealExpired Whether the meal is expired
     */
    public void setMealExpired(boolean mealExpired) 
    {
        this.mealExpired = mealExpired;
    }

    /**
     * Returns the MealRecipe object corresponding to the Meal
     * @return mealRecipe
     */
    public MealRecipe getMealRecipe() 
    {
        return mealRecipe;
    }

    /**
     * Sets the MealRecipe object corresponding to the meal
     * @param mealRecipe MealRecipe belonging to this Meal
     */
    public void setMealRecipe(MealRecipe mealRecipe) 
    {
        this.mealRecipe = mealRecipe;
    }

    /**
     * Returns the quantity of the meal
     * @return quantity
     */
    public double getQuantity() 
    {
        return quantity;
    }

    /**
     * Sets the quantity of the meal
     * @param quantity meal quantity
     */
    public void setQuantity(double quantity) 
    {
        this.quantity = quantity;
    }
}