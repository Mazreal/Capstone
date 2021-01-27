package domain;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author 783385
 */
public class POItems extends PurchaseOrder {


    //attributes
    private ArrayList<Integer> poItemNo;
    private ArrayList<Double> quantity;
    private ArrayList<String> menuItem;
    
    /**
     * Default constructor for POItems
     */
    public POItems()
    {
        
    }
    
    /**
     * Constructor with fields
     * @param poNo
     * @param clientNo
     * @param poDatePlaced
     * @param poDateRequired
     * @param poNotes
     * @param empName
     * @param empEmail
     * @param delivery
     * @param completed
     * @param poItemNo
     * @param quantity
     * @param menuItem 
     */
    public POItems(int poNo, int clientNo, Date poDatePlaced, Date poDateRequired, String poNotes, String empName,
            String empEmail, boolean delivery, boolean completed, ArrayList<Integer> poItemNo, ArrayList<Double> quantity, ArrayList<String> menuItem) {
        
        super(poNo, clientNo, poDatePlaced, poDateRequired, poNotes, empName, empEmail, delivery, completed);
        this.poItemNo = poItemNo;
        this.quantity = quantity;
        this.menuItem = menuItem;
    }

    /**
     * Method to poItemNo
     * @param poItemNo 
     */
    public void setPoItemNo(ArrayList<Integer> poItemNo) {
        this.poItemNo = poItemNo;
    }
    
    /**
     * method get the PO Item numbers
     * @return list of PO item numbers
     */
    public ArrayList<Integer> getPoItemNo() {
        return poItemNo;
    }

    /**
     * Method to set the quantity of the meals
     * @param quantity 
     */
    public void setQuantity(ArrayList<Double> quantity) {
        this.quantity = quantity;
    }
    
    /**
     * Method to set meal items of the purchase order
     * @param menuItem 
     */
    public void setMenuItem(ArrayList<String> menuItem) {
        this.menuItem = menuItem;
    }
    
    /**
     * Method to get the quantity of the quantity of meal items within a purchase order
     * @return quantity list
     */
    public ArrayList<Double> getQuantity() {
        return quantity;
    }

    /**
     * Method to get the meal items within a purchase order
     * @return menuItem list
     */
    public ArrayList<String> getMenuItem() {
        return menuItem;
    }

}
