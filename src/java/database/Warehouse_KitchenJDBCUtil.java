package database;

import domain.Ingredient;
import domain.MealItem;
import domain.MealRecipe;
import domain.Station;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains methods to perform CRUD operations directly with database table data.
 * @author 673970
 */
public class Warehouse_KitchenJDBCUtil
{

    /**
     * Acquires the database connection via DBConnection class.
     * @return Connection received via DBConnection method getConnection().
     */
    private Connection getConnection()
    {
        DBConnection dbconn = new DBConnection();
        Connection conn = dbconn.getConnection();
        return conn;
    }

    // INSERTS BELOW---------------------------------------------------------------------------------------------------------------------
    /**
     * Inserts a Meal into the Meal table and returns a corresponding integer value.
     * @param mealItem MealItem to be inserted.
     * @return Integer to determine if successful(1) or incomplete(-1)
     */
    public int insertMeal(MealItem mealItem) // MEAL TO THE MENU!
    {
        Connection conn = getConnection();
        String insertString = "INSERT INTO Meal VALUES(?,?,?)";
        try
        {
            PreparedStatement ps = conn.prepareStatement(insertString);
            ps.setInt(1, mealItem.getMealItemNumber());
            ps.setString(2, mealItem.getMealName());
            ps.setInt(3, mealItem.getMealShelfLife());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
            //return -1;
        }
        return 1;
    }

    /**
     * Inserts an ingredient into the Ingredient table and returns a corresponding integer value.
     * @param ingredient Ingredient to be inserted.
     * @return Integer to determine if successful(1) or incomplete(-1)
     */
    public int insertIngredient(Ingredient ingredient) // INGREDIENT TO THE RECIPE
    {
        Connection conn = getConnection();
        String insertString = "INSERT INTO Ingredient VALUES(?,?,?,?,?)";
        try
        {
            PreparedStatement ps = conn.prepareStatement(insertString);
            ps.setInt(1, ingredient.getIngredientNumber());
            ps.setString(2, ingredient.getIngredientName());
            ps.setInt(3, ingredient.getIngredientShelfLife());
            ps.setString(4, ingredient.getPar());
            ps.setInt(5, ingredient.getStation().getStationID());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

    /**
     * Inserts a MealRecipe object into the Meal_Recipe table and returns a corresponding integer value.
     * @param recipe Recipe object to be inserted.
     * @return Integer to determine if successful(1) or incomplete(-1)
     */
    public int insertRecipe(MealRecipe recipe)
    {
        Connection conn = getConnection();
        String insertString = "INSERT INTO Meal_Recipe VALUES(?,?,?)";
        try
        {
            PreparedStatement ps = conn.prepareStatement(insertString);
            for (int i = 0; i < recipe.getIngredientList().size(); i++)
            {
                ps.setInt(1, recipe.getMealItemNumber());
                ps.setInt(2, recipe.getIngredientList().get(i).getIngredientNumber());
                ps.setDouble(3, recipe.getIngredientQuantity().get(i));
                ps.execute();
            }
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return 1;
    }

    /**
     * Inserts a MealItem into the Meal_Item table and returns a corresponding integer value.
     * @param mealItem MealItem to be inserted.
     * @return Integer to determine if successful(1) or incomplete(-1)
     */
    public int insertMealItem(MealItem mealItem)
    {
        Connection conn = getConnection();
        String insertString = "INSERT INTO Meal_Item VALUES(?,?,?,?)";

        try
        {
            java.sql.Date sqlDate = new java.sql.Date(mealItem.getMealDateProduced().getTime());

            PreparedStatement ps = conn.prepareStatement(insertString);
            ps.setInt(1, mealItem.getMealItemNumber());
            ps.setDate(2, sqlDate);
            ps.setInt(3, 0);
            ps.setDouble(4, mealItem.getQuantity());
            ps.execute();
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

        return 1;
    }
    
    /**
     * Inserts an Ingredient into the Ingredient_Item table and returns a corresponding integer value.
     * @param ingredient Ingredient to be inserted.
     * @return Integer to determine if successful(1) or incomplete(-1).
     */
    public int insertIngredientItem(Ingredient ingredient)
    {
        Connection conn = getConnection();
        String insertquery = "INSERT INTO Ingredient_Item VALUES(?,?,?,?)";

        try
        {
            java.sql.Date sqlDate = new java.sql.Date(ingredient.getIngredientDateProduced().getTime());

            PreparedStatement ps = conn.prepareStatement(insertquery);
            ps.setInt(1, ingredient.getIngredientNumber());
            ps.setDate(2, sqlDate);
            ps.setInt(3, 0);
            ps.setDouble(4, ingredient.getQuantity());
            ps.execute();
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
            //return -1;
        }

        return 1;
    }
    // INSERTS ABOVE---------------------------------------------------------------------------------------------------------------------
    // UPDATES BELOW---------------------------------------------------------------------------------------------------------------------

    /**
     * Updates a MealItem in the Meal_Item table according to a specific mealItemNo value via @param meal.
     * @param meal Meal item to be updated.
     * @return Integer to determine if successful(1) or incomplete(-1)
     */
    public int updateMealItem(MealItem meal) // UPDATE MEAL IN INVENTORY COUNT
    {
        Connection conn = getConnection();
        String updateString = "UPDATE Meal_Item SET "
                + "mealDateProduced = ?,"
                + "mealExpired = ?,"
                + "quantity = ?"
                + "WHERE mealItemNo = ?";
        try
        {
            Date date = meal.getMealDateProduced();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            int expired = 0;
            if (meal.isMealExpired() == true)
            {
                expired = 1;
            }
            PreparedStatement ps = conn.prepareStatement(updateString);
            ps.setDate(1, sqlDate);
            ps.setInt(2, expired);
            ps.setDouble(3, meal.getQuantity());
            ps.setInt(4, meal.getMealItemNumber());
            ps.executeUpdate();
            conn.close();

        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

        return 1;
    }

    /**
     * Updates a MealItem in the Meal table and returns a corresponding integer value.
     * @param meal MealItem to be updated
     * @return Integer to determine if successful(1) or incomplete(-1)
     */
    public int updateMeal(MealItem meal) // UPDATE MEAL AS SEEN IN THE MENU
    {
        Connection conn = getConnection();
        String updateString = "UPDATE Meal SET "
                + "mealName = ?,"
                + "mealShelfLife = ?"
                + "WHERE mealItemNo = ?";
        try
        {
            Date date = meal.getMealDateProduced();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            int expired = 0;
            if (meal.isMealExpired() == true)
            {
                expired = 1;
            }
            PreparedStatement ps = conn.prepareStatement(updateString);
            ps.setString(1, meal.getMealName());
            ps.setInt(2, meal.getMealShelfLife());
            ps.setInt(3, meal.getMealItemNumber());
            ps.executeUpdate();
            conn.close();

        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

        return 1;
    }

    /**
     * Updates an ingredient in the Ingredient_Item table and returns a corresponding integer value.
     * @param ingredient Ingredient to be updated.
     * @return Integer to determine if successful(1) or incomplete(-1)
     */
    public int updateIngredientItem(Ingredient ingredient)
    {
        Connection conn = getConnection();
        String updateString = "UPDATE Ingredient_Item SET "
                + "ingredientDateProduced = ?,"
                + "expired = ?,"
                + "quantity = ?"
                + "WHERE ingredientNo = ?";
        try
        {
            Date date = ingredient.getIngredientDateProduced();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            int expired = 0;
            if (ingredient.isExpired() == true)
            {
                expired = 1;
            }

            PreparedStatement ps = conn.prepareStatement(updateString);
            ps.setDate(1, sqlDate);
            ps.setInt(2, expired);
            ps.setDouble(3, ingredient.getQuantity());
            ps.setInt(4, ingredient.getIngredientNumber());
            ps.executeUpdate();
            conn.close();

        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

        return 1;
    }

    /**
     * Updates an ingredient in the Ingredient table and returns a corresponding integer value.
     * @param ingredient Ingredient to be updated.
     * @return Integer to determine if successful(1) or incomplete(-1)
     */
    public int updateIngredient(Ingredient ingredient) // UPDATE INGREDIENT AS SEEN IN RECIPE
    {
        Connection conn = getConnection();
        String updateString = "UPDATE Ingredient SET "
                + "ingredientName = ?,"
                + "ingredientShelfLife = ?,"
                + "par = ?,"
                + "stationNo = ?"
                + "WHERE ingredientNo = ?";
        try
        {
            int stationNo = ingredient.getStation().getStationID();

            PreparedStatement ps = conn.prepareStatement(updateString);
            ps.setString(1, ingredient.getIngredientName());
            ps.setInt(2, ingredient.getIngredientShelfLife());
            ps.setString(3, ingredient.getPar());
            ps.setInt(4, stationNo);
            ps.setInt(5, ingredient.getIngredientNumber());
            ps.executeUpdate();
            conn.close();

        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

        return 1;
    }

    // UPDATES ABOVE---------------------------------------------------------------------------------------------------------------------
    // DELETES BELOW---------------------------------------------------------------------------------------------------------------------
    
    /**
     * Delete a Meal Item according to a specified mealNo and dateProduced parameter value and returns a corresponding integer value.
     * @param mealNo A Meal Items mealNo
     * @param dateProduced A Meal Items specified produced date.
     * @return Integer to determine if successful(1) or incomplete(-1)
     */
    public int deleteMealItem(int mealNo, Date dateProduced) // DELETING MEAL INSTANCE ACCORDING TO DATE AND NUMBER
    {
        java.sql.Date sqlDate = new java.sql.Date(dateProduced.getTime());
        Connection conn = getConnection();
        String deleteString = "DELETE FROM Meal_Item WHERE mealItemNo = ? AND mealDateProduced = ?";
        try
        {
            PreparedStatement ps = conn.prepareStatement(deleteString);
            ps.setInt(1, mealNo);
            ps.setDate(2, sqlDate);
            ps.execute();
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

        return 1;
    }
    
    /**
     * Deletes a Meal entirely (including any Meal_Item) and returns a corresponding integer value. 
     * @param mealNo Specified Meal's mealNo to delete.
     * @return Integer to determine if successful(1) or incomplete(-1)
     */
    public int deleteMeal(int mealNo) // DELETING THE ACTUAL MEAL OBJECT FROM MENU
    {
        Connection conn = getConnection();

        String deleteString1 = "DELETE FROM Meal WHERE mealItemNo = ?";
        String deleteString2 = "DELETE FROM Meal_Item WHERE mealItemNo = ?";
        try
        {
            PreparedStatement ps1 = conn.prepareStatement(deleteString1);
            PreparedStatement ps2 = conn.prepareStatement(deleteString2);
            ps2.setInt(1, mealNo);
            ps2.execute();
            ps1.setInt(1, mealNo);
            ps1.execute();
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

        return 1;
    }

    /**
     * Deletes an Ingredient Item from the Ingredient_Item table according to the items ingredientNo and dateProduced. Returns a corresponding integer value.
     * @param ingredientNo Ingredient Item's ingredient number
     * @param dateProduced Ingredient Item's date produced.
     * @return Integer to determine if successful(1) or incomplete(-1)
     */
    public int deleteIngredientItem(int ingredientNo, Date dateProduced) // DELETE INGREDIENT INSTANCE ACCORDING TO NUMBER AND DATE
    {
        Connection conn = getConnection();
        if (dateProduced != null)
        {

            java.sql.Date sqlDate = new java.sql.Date(dateProduced.getTime());

            String deleteString = "DELETE FROM Ingredient_Item WHERE ingredientNo = ? AND ingredientDateProduced = ?";
            try
            {
                PreparedStatement ps = conn.prepareStatement(deleteString);
                ps.setInt(1, ingredientNo);
                ps.setDate(2, sqlDate);
                ps.execute();
                conn.close();
            } catch (SQLException ex)
            {
                Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            }
        } else
        {
            String deleteString = "DELETE FROM Ingredient_Item WHERE ingredientNo = ?";
            PreparedStatement ps;
            try
            {
                ps = conn.prepareStatement(deleteString);
                ps.setInt(1, ingredientNo);
                ps.execute();
                conn.close();
            } catch (SQLException ex)
            {
                Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            }
        }
        return 1;
    }

    /**
     * Removes an ingredient entirely (including ingredient items) and any corresponding integer values.
     * @param ingredientNo Ingredient's ingredient number to be deleted.
     * @return Integer to determine if successful(1) or incomplete(-1)
     */
    public int deleteIngredient(int ingredientNo) // DELETE INGREDIENT FROM RECIPES
    {
        Connection conn = getConnection();
        deleteIngredientItem(ingredientNo, null);
        String deleteString = "DELETE FROM Ingredient WHERE ingredientNo = ?";
        try
        {
            PreparedStatement ps = conn.prepareStatement(deleteString);
            ps.setInt(1, ingredientNo);
            ps.execute();
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return 1;
    }

    /**
     * Deletes Meal's recipe according to the Meal mealItemNo and returns a corresponding integer value.
     * @param recipeNo Meal Item's meal item number to specify which recipe's to be deleted.
     * @return Integer to determine if successful(1) or incomplete(-1)
     */
    public int deleteRecipe(int recipeNo) // DELETE RECIPE WHEN RECIPE CHANGES TO ENSURE PERSISTENCE 
    {
        Connection conn = getConnection();
        String deleteString = "DELETE FROM Meal_Recipe WHERE mealItemNo = ?";
        try
        {
            PreparedStatement ps = conn.prepareStatement(deleteString);
            ps.setInt(1, recipeNo);
            ps.execute();
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return 1;
    }
    // DELETES ABOVE---------------------------------------------------------------------------------------------------------------------
    // GETS BELOW --------------------------------------------------------------------------------------------

    /**
     * Returns the current highest ingredientNo in the Ingredient table.
     * @return Highest ingredientNo value.
     */
    public int getHighestIngredientNo()
    {
        Connection conn = getConnection();
        int result = 0;
        String query = "SELECT MAX(ingredientNo) FROM Ingredient";
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
            {
                result = rs.getInt(1);
            }
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Returns the highest mealItemNo in the Meal table.
     * @return Highest mealItemNo value.
     */
    public int getHighestMealNo()
    {
        Connection conn = getConnection();
        int result = 0;
        String query = "SELECT MAX(mealItemNo) FROM Meal";
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
            {
                result = rs.getInt(1);
            }
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Returns a station object built from the Station table according to a specified stationNo.
     * @param stationNo A Station object's stationNo.
     * @return Station object of stationNo.
     */
    public Station getStation(int stationNo)
    {
        Station s = null;
        try
        {
            Connection conn = getConnection();
            String query = "SELECT * FROM Station WHERE stationNo = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, stationNo);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                s = new Station(rs.getInt(1), rs.getString(2));
            }
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    /**
     * Returns an Ingredient object according to the specified ingredientNo.
     * @param ingredientNo The Ingredient object's ingredientNo.
     * @return Ingredient object.
     */
    public Ingredient getIngredient(int ingredientNo)
    {
        Connection conn = getConnection();
        Ingredient i = null;
        try
        {
            String query = "SELECT * FROM Ingredient i LEFT JOIN Ingredient_Item ii ON(i.ingredientNo = ii.ingredientNo) WHERE i.ingredientNo=? UNION SELECT * FROM Ingredient i RIGHT JOIN Ingredient_Item ii ON(i.ingredientNo = ii.ingredientNo) WHERE i.ingredientNo=?"
                    + "";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, ingredientNo);
            ps.setInt(2, ingredientNo);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                boolean expired = false;
                if (rs.getInt("expired") == 1)
                {
                    expired = true;
                }
                Station station = getStation(rs.getInt("stationNo"));
                i = new Ingredient(rs.getInt("ingredientNo"), rs.getString("ingredientName"), rs.getInt("ingredientShelfLife"), rs.getDate("ingredientDateProduced"), expired, rs.getString("par"), station, rs.getDouble("quantity"));

            }
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    /**
     * Finds a specified Meal item number in the Meal_Recipe table according to an ingredient's number 
     * @param ingredientNo Specified ingredient number
     * @return A mealItemNumber that the ingredient belongs to.
     */
    private int findMeal(int ingredientNo)
    {
        int mealItemNo = -1;
        try
        {
            Connection conn = getConnection();
            String query = "SELECT mealItemNo FROM Meal_Recipe WHERE ingredientNo = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, ingredientNo);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                mealItemNo = rs.getInt("mealItemNo");
            }
            conn.close();

        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mealItemNo;
    }

    /**
     * Returns a MealRecipe object according to a MealItem's mealItemNo.
     * @param mealItemNo Specified MealItem's mealItemNo.
     * @return A MealRecipe object.
     */
    public MealRecipe getRecipe(int mealItemNo)
    {
        MealRecipe mr = null;
        Connection conn = getConnection();
        try
        {
            ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
            ArrayList<Double> ingredientQuantity = new ArrayList<Double>();

            String query = "SELECT * FROM Meal_Recipe WHERE mealItemNo = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, mealItemNo);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                ingredientList.add(getIngredient(rs.getInt(2)));
                ingredientQuantity.add(rs.getDouble(3));
            }
            mr = new MealRecipe(mealItemNo, ingredientList, ingredientQuantity);
            conn.close();

        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mr;

    }

    /**
     * Returns a MealItem object according to a provided mealItemNumber. 
     * @param mealItemNumber Specified mealItemNo.
     * @return MealItem object.
     */
    public MealItem getMealItem(int mealItemNumber)
    {
        Connection conn = getConnection();
        MealItem mi = null;

        try
        {
            String query = "SELECT * FROM Meal mi RIGHT JOIN Meal_Item m ON(mi.mealItemNo = m.mealItemNo) WHERE mi.mealItemNo=? UNION  SELECT * FROM Meal mi LEFT JOIN Meal_Item m ON(mi.mealItemNo = m.mealItemNo) WHERE mi.mealItemNo=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, mealItemNumber);
            ps.setInt(2, mealItemNumber);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                boolean exp = false;
                if (rs.getInt("mealExpired") == 1)
                {
                    exp = true;
                }
                mealItemNumber = rs.getInt("mealItemNo");
                String mealName = rs.getString("mealName");
                int mealShelfLife = rs.getInt("mealShelfLife");
                Date mealDateProduced = rs.getDate("mealDateProduced");
                Double quantity = rs.getDouble("quantity");
                MealRecipe mealRecipe = getRecipe(mealItemNumber);

                mi = new MealItem(mealItemNumber, mealName, mealShelfLife, mealDateProduced, exp, mealRecipe, quantity);
                //System.out.println(mi.getMealName());
            }
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mi;
    }

    /**
     * Gets a MealItem's mealItemNo according to its specific mealName.
     * @param mealName Specified MealName.
     * @return MealItem's mealItemNo.
     */
    public int getMealItemNo(String mealName)
    {
        Connection conn = getConnection();
        String query = "SELECT mealItemNo FROM Meal WHERE mealName = ?";
        int mealNo = -1;
        try
        {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, mealName);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                mealNo = rs.getInt("mealItemNo");
            }
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mealNo;

    }
    
    /**
     * Returns an ingredientNo according to its ingredientName
     * @param ingredientName Specific IngredientName
     * @return Ingredient's ingredientMealNo
     */
    public int getIngredientMealNo(String ingredientName)
    {
        Connection conn = getConnection();
        String query = "SELECT ingredientNo FROM Ingredient WHERE ingredientName = ?";
        int ingredientNo = -1;
        try
        {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, ingredientName);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                ingredientNo = rs.getInt("ingredientNo");
            }
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ingredientNo;
    }

    /**
     * Returns a list all MealItem's from both Meal and Meal_Item
     * @return ArrayList of all MealItems
     */
    public ArrayList<MealItem> getAllMeals()
    {
        Connection conn = getConnection();
        ResultSet rs = null;
        Statement stmt = null;
        int mealItemNumber;
        String mealName;
        int mealShelfLife;
        Date mealDateProduced;
        boolean mealExpired;
        MealRecipe mealRecipe;
        double quantity;
        try
        {
            stmt = conn.createStatement();
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            rs = stmt.executeQuery("SELECT * FROM Meal mi RIGHT JOIN Meal_Item m ON(mi.mealItemNo = m.mealItemNo) UNION  SELECT * FROM Meal mi LEFT JOIN Meal_Item m ON(mi.mealItemNo = m.mealItemNo)");
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<MealItem> mealItems = new ArrayList<MealItem>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        try
        {
            while (rs.next())
            {
                mealItemNumber = rs.getInt("mealItemNo");
                mealName = rs.getString("mealName");
                mealShelfLife = rs.getInt("mealShelfLife");
                mealDateProduced = rs.getDate("mealDateProduced");
                mealExpired = rs.getBoolean("mealExpired");
                quantity = rs.getDouble("quantity");

                mealRecipe = getRecipe(mealItemNumber);

                mealItems.add(new MealItem(mealItemNumber, mealName, mealShelfLife, mealDateProduced, mealExpired, mealRecipe, quantity));
            }
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mealItems;
    }
    
    /**
     * Returns an ArrayList of all MealItems from Meal
     * @return ArrayList of all MealItems
     */
    public ArrayList<MealItem> getAllMeals2()
    {
        Connection conn = getConnection();
        ArrayList<MealItem> mealList = new ArrayList<MealItem>();
        String query = "SELECT * FROM Meal";
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            MealItem mi = null;
            while(rs.next())
            {
                mi = new MealItem(rs.getInt("mealItemNo"), rs.getString("mealName"), rs.getInt("mealShelfLife"), null, false, null, 0);
                mealList.add(mi);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mealList;
    }
    
    /**
     * Builds MealRecipes according to a all meal recipes which includes the Ingredient objects 
     * and their corresponding required weight then returns an ArrayList of all MealRecipes
     * @return ArrayList of all MealRecipes
     */
    public ArrayList<MealRecipe> getAllRecipes()
    {
        Connection conn = getConnection();
        ArrayList<MealRecipe> recipeList = new ArrayList<MealRecipe>();
        ArrayList<Ingredient> ingredientList = null;
        ArrayList<Double> ingredientQuantity = null;
        try
        {

            ResultSet rs = null;
            Statement stmt = null;
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Meal_Recipe ORDER BY mealItemNo");

            MealRecipe mr = null;
            // meanItemNumber
            // ingredientList
            // ingredientQuantity
            int mealItemNumber = 0;
            int mealItemNumberPrev = 0;
            // Iterate Result Set
            while (rs.next())
            {
                mealItemNumber = rs.getInt(1);
                if (rs.isFirst())
                {
                    //System.out.println("In First ==" + mealItemNumber);
                    mr = new MealRecipe();
                    mr.setMealItemNumber(rs.getInt(1));
                    mealItemNumberPrev = mealItemNumber;

                    ingredientList = new ArrayList<Ingredient>();
                    ingredientQuantity = new ArrayList<Double>();

                    ingredientList.add(getIngredient(rs.getInt(2)));
                    ingredientQuantity.add(rs.getDouble(3));
                } else
                {
                    if (mealItemNumber == mealItemNumberPrev) // If still under same recipe
                    {
                        ingredientList.add(getIngredient(rs.getInt(2)));
                        ingredientQuantity.add(rs.getDouble(3));
                        // System.out.println("INGList size = " + ingredientList.size());
                        if (rs.isLast())
                        {
                            mr.setIngredientList(ingredientList);
                            mr.setIngredientQuantity(ingredientQuantity);
                            recipeList.add(mr);
                        }

                    } else // If new recipe starting, save and add lists
                    {
                        mr.setIngredientList(ingredientList);
                        mr.setIngredientQuantity(ingredientQuantity);
                        recipeList.add(mr);
                        //System.out.println("Recipe Size = " + recipeList.size());

                        // Clear lists and objects.
                        mr = null;

//                        ingredientList.clear();
//                        ingredientQuantity.clear();
                        ingredientList = new ArrayList<Ingredient>();
                        ingredientQuantity = new ArrayList<Double>();

                        // Start New Recipe
                        mr = new MealRecipe();
                        mr.setMealItemNumber(mealItemNumber);
                        mealItemNumberPrev = mealItemNumber;

                        ingredientList.add(getIngredient(rs.getInt(2)));
                        ingredientQuantity.add(rs.getDouble(3));

                        if (rs.isLast())
                        {
                            mr.setIngredientList(ingredientList);
                            mr.setIngredientQuantity(ingredientQuantity);
                            recipeList.add(mr);
                        }
                    }
                }

            }
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recipeList;
    }

    /**
     * Returns an ArrayList of all Ingredients in both Ingredient and Ingredient_Item
     * @return ArrayList of all Ingredients
     */
    public ArrayList<Ingredient> getAllIngredients()
    {
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        Connection conn = getConnection();
        try
        {
            ResultSet rs = null;
            Statement stmt = null;
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Ingredient i LEFT JOIN Ingredient_Item ii ON(i.ingredientNo = ii.ingredientNo) UNION SELECT * FROM Ingredient i RIGHT JOIN Ingredient_Item ii ON(i.ingredientNo = ii.ingredientNo)");

            while (rs.next())
            {
                boolean expired = false;
                if (rs.getInt("expired") == 1)
                {
                    expired = true;
                }
                Station station = getStation(rs.getInt("stationNo"));

                Ingredient i = new Ingredient(rs.getInt("ingredientNo"), rs.getString("ingredientName"), rs.getInt("ingredientShelfLife"), rs.getDate("ingredientDateProduced"), expired, rs.getString("par"), station, rs.getDouble("quantity"));
                ingredients.add(i);
            }
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ingredients;
    }
    
   
    //EXPIRED FUNC BELOW-------------------------------------------------------------
    /**
     * Inserts expired meals with the date they expired into the Expired Log table and returns
     * a corresponding integer value.
     * @return Integer to determine if successful(1) or incomplete(-1)
     */
    public int insertExpired()
    {
        Connection conn = getConnection();
        String insertString1 = "INSERT INTO Expired_Log (productNo, productName, quantity, dateExpired)\n"
                + "    SELECT mi.mealItemNo AS m, mealName, quantity,\n"
                + "    DATE_ADD(mealDateProduced,INTERVAL (SELECT mealShelfLife FROM Meal WHERE mealItemNo = m) DAY) as Date_Expired\n"
                + "    FROM Meal_Item mi JOIN Meal me ON (mi.mealItemNo = me.mealItemNo)\n"
                + "    HAVING Date_Expired < CURDATE()\n"
                + "    ORDER by Date_Expired;";
        
        String insertString2 = "INSERT INTO Expired_Log (productNo, productName, quantity,dateExpired)\n"
                + "    SELECT ii.ingredientNo AS ing, ingredientName, quantity,\n"
                + "    DATE_ADD(ingredientDateProduced,INTERVAL (SELECT ingredientShelfLife FROM Ingredient WHERE ingredientNo = ing) DAY) as Date_Expired\n"
                + "    FROM Ingredient_Item ii JOIN Ingredient ig ON (ii.ingredientNo = ig.ingredientNo)\n"
                + "    HAVING Date_Expired < CURDATE()\n"
                + "    ORDER by Date_Expired;";
        try
        {
            Statement stmt1 = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            stmt1.execute(insertString1);
            stmt2.execute(insertString2);
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 1;
    }
    
    /**
     * Updates the expired column for Ingredient items and Meal items if its dateProduced and shelfLife is older than the current date
     * and returns a corresponding integer value.
     * @return Integer to determine if successful(1) or incomplete(-1)
     */
    public int updateExpiredItems()
    {
        Connection conn = getConnection();
        String updateMealString = "UPDATE Meal_Item mi SET mealExpired = 1 WHERE mealDateProduced < CURDATE()-(SELECT mealShelfLife FROM Meal WHERE mealItemNo = mi.mealItemNo)";
        String updateIngredientString = "UPDATE Ingredient_Item ii SET expired = 1 WHERE ingredientDateProduced < CURDATE()-(SELECT ingredientShelfLife FROM Ingredient WHERE ingredientNo = ii.ingredientNo)";

        try
        {
            Statement stmt1 = conn.createStatement();
            Statement stmt2 = conn.createStatement();

            stmt1.executeUpdate(updateMealString);
            stmt2.executeUpdate(updateIngredientString);
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

    public int pullExpiredItems()
    {
        Connection conn = getConnection();
        String deleteString1 = "DELETE FROM Ingredient_Item WHERE expired = 1";
        String deleteString2 = "DELETE FROM Meal_Item WHERE mealExpired = 1";
        try
        {
            Statement stmt1 = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            stmt1.execute(deleteString1);
            stmt2.execute(deleteString2);
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

}