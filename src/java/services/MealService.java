package services;

import domain.MealItem;
import domain.MealRecipe;
import database.Warehouse_KitchenJDBCUtil;
import domain.Ingredient;
import java.util.ArrayList;

public class MealService {
    
    private static int mealItemNumber = 0;
    private Warehouse_KitchenJDBCUtil mealItemDB;

    public MealService() {
        mealItemDB = new Warehouse_KitchenJDBCUtil();
    }
    
    public MealItem getMeal(int mealItemNumber) throws Exception {
        return mealItemDB.getMealItem(mealItemNumber);
    }
    public MealRecipe getRecipe(int mealItemNumber)
    {
        return mealItemDB.getRecipe(mealItemNumber);
    }
    public int getHighestMealNo()
    {
        return mealItemDB.getHighestMealNo();
    }
    
    public ArrayList<MealItem> getAllMeals() throws Exception {
        return mealItemDB.getAllMeals();
    }
    
    public ArrayList<MealItem> getAllMeals2() throws Exception {
        return mealItemDB.getAllMeals2();
    }
    
    public ArrayList<MealRecipe> getAllRecipes()
    {
        return mealItemDB.getAllRecipes();
    }

    public ArrayList<Ingredient> getAllIngredients()
    {
        return mealItemDB.getAllIngredients();
    }
    
    public int insert(MealItem mealItem)
    {
        return mealItemDB.insertMeal(mealItem);
    }
    public int insertMealRecipe(MealRecipe mealRecipe)
    {
        return mealItemDB.insertRecipe(mealRecipe);
    }
    public int deleteMeal(int mealItemNumber)
    {
        return mealItemDB.deleteMeal(mealItemNumber);
    }
    public int deleteMealRecipe(int mealItemNumber)
    {
        return mealItemDB.deleteRecipe(mealItemNumber);
    }
    
    public int updateMeal(MealItem meal)
    {
        return mealItemDB.updateMealItem(meal);
    }
    
    public int getMealItemNo(String mealName)
    {
        return mealItemDB.getMealItemNo(mealName);
    }
    
    public int insertMealItem(MealItem meal)
    {
        return mealItemDB.insertMealItem(meal);
    }
    
    public int pullExpiredItems()
    {
        return mealItemDB.pullExpiredItems();
    }
    
    public int updateExpiredItems()
    {
        mealItemDB.updateExpiredItems();
        return mealItemDB.insertExpired();
    }
    
}
