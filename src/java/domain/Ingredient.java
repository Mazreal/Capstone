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
public class Ingredient {

    private int nextIngredientNumber;
    private int ingredientNumber;
    private String ingredientName;
    private int ingredientShelfLife;
    private Date ingredientDateProduced;
    private boolean expired;
    private String par;
    private Station station;
    private double quantity;
    
    public Ingredient()
    {
    }
    
    public Ingredient(int ingredientNumber, String ingredientName, int ingredientShelfLife,
            Date ingredientDateProduced, boolean expired, String par, Station station,
            double quantity) 
    {
        super();
        this.ingredientNumber = ingredientNumber;
        this.ingredientName = ingredientName;
        this.ingredientShelfLife = ingredientShelfLife;
        this.ingredientDateProduced = ingredientDateProduced;
        this.expired = expired;
        this.par = par;
        this.station = station;
        this.quantity = quantity;
    }

    public int getNextIngredientNumber() 
    {
        return nextIngredientNumber;
    }

    public void setNextIngredientNumber(int nextIngredientNumber) 
    {
        this.nextIngredientNumber = nextIngredientNumber;
    }

    public int getIngredientNumber() 
    {
        return ingredientNumber;
    }

    public void setIngredientNumber(int ingredientNumber) 
    {
        this.ingredientNumber = ingredientNumber;
    }

    public String getIngredientName() 
    {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) 
    {
        this.ingredientName = ingredientName;
    }

    public int getIngredientShelfLife() 
    {
        return ingredientShelfLife;
    }

    public void setIngredientShelfLife(int ingredientShelfLife) 
    {
        this.ingredientShelfLife = ingredientShelfLife;
    }

    public Date getIngredientDateProduced() 
    {
        return ingredientDateProduced;
    }

    public void setIngredientDateProduced(Date ingredientDateProduced) 
    {
        this.ingredientDateProduced = ingredientDateProduced;
    }

    public boolean isExpired() 
    {
        return expired;
    }

    public void setExpired(boolean expired) 
    {
        this.expired = expired;
    }

    public String getPar() 
    {
        return par;
    }

    public void setPar(String par) 
    {
        this.par = par;
    }

    public Station getStation() 
    {
        return station;
    }

    public void setStation(Station station) 
    {
        this.station = station;
    }

    public double getQuantity() 
    {
        return quantity;
    }

    public void setQuantity(double quantity) 
    {
        this.quantity = quantity;
    }

}
