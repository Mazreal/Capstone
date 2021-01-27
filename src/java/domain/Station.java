/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.List;

/**
 *
 * @author 783385
 */
public class Station {

    private int stationID;
    private String stationName;
    private List<Ingredient> ingredientList;
    
    /**
     * Default constructor
     */
    public Station()
    {
    }
    
    /**
     * Constructor
     * 
     * @param stationID station id
     * @param stationName station name
     */
    public Station(int stationID, String stationName)
    {
        this.stationID = stationID;
        this.stationName = stationName;
    }
    
    /**
     * Constructor
     * 
     * @param stationID station id
     * @param stationName station name 
     * @param ingredientList ingredient list
     */
    public Station(int stationID, String stationName, List<Ingredient> ingredientList) 
    {
        super();
        this.stationID = stationID;
        this.stationName = stationName;
        this.ingredientList = ingredientList;
    }
    
    /**
     * Returns the station id
     * 
     * @return the station id
     */
    public int getStationID() 
    {
        return stationID;
    }

    /**
     * Sets the station id
     * 
     * @param stationID station id to be set
     */
    public void setStationID(int stationID) 
    {
        this.stationID = stationID;
    }

    /**
     * Returns the station name
     * 
     * @return the station name
     */
    public String getStationName() 
    {
        return stationName;
    }

    /**
     * Sets the station name
     * 
     * @param stationName station name to be set
     */
    public void setStationName(String stationName) 
    {
        this.stationName = stationName;
    }

    /**
     * Returns the list of ingredients
     * 
     * @return list of ingredients
     */
    public List<Ingredient> getIngredientList() 
    {
        return ingredientList;
    }
    
    /**
     * Sets the ingredient list
     * 
     * @param ingredientList ingredient list to be set
     */
    public void setIngredientList(List<Ingredient> ingredientList) 
    {
        this.ingredientList = ingredientList;
    }

}
