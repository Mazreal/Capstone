/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.Warehouse_KitchenJDBCUtil;
import domain.Ingredient;
import domain.Station;

/**
 *
 * @author 673970
 */
public class IngredientService
{
    private  Warehouse_KitchenJDBCUtil ingredientDB;
    
    public IngredientService()
    {
        ingredientDB = new Warehouse_KitchenJDBCUtil();
    }
    public int insertIngredient(Ingredient ingredient)
    {
        return ingredientDB.insertIngredient(ingredient);
    }
    
    public int updateIngredient(Ingredient ingredient)
    {
        return ingredientDB.updateIngredient(ingredient);
    }
    
    public Ingredient getIngredient(int ingredientNumber)
    {
        return ingredientDB.getIngredient(ingredientNumber);
    }
    
    public int getHighestIngredientNo()
    {
        return ingredientDB.getHighestIngredientNo();
    }
    
    public Station getStation(int stationNo)
    {
        return ingredientDB.getStation(stationNo);
    }
    
    public int deleteIngredient(int deleteIngNo)
    {
        return ingredientDB.deleteIngredient(deleteIngNo);
    }
    public int getIngredientNo(String ingredientName)
    {
        return ingredientDB.getIngredientMealNo(ingredientName);
    }
    public int insertIngredientItem(Ingredient ingredient)
    {
        return ingredientDB.insertIngredientItem(ingredient);
    }
   
}
