package domain;

/**
 *
 * @author 783385
 */
import java.util.Date;

public class PurchaseOrder {
    
    //attributes
    private int poNo;
    private int clientNo;
    private Date poDatePlaced;
    private Date poDateRequired;
    private String poNotes;
    private String empName;
    private String empEmail;
    private boolean delivery;
    private boolean completed;

    /**
     * Default Constructor
     */
    public PurchaseOrder() {

    }

    /**
     * Constructor using fields
     * @param poNo
     * @param clientNo
     * @param poDatePlaced
     * @param poDateRequired
     * @param poNotes
     * @param empName
     * @param empEmail
     * @param delivery
     * @param completed 
     */
    public PurchaseOrder(int poNo, int clientNo, Date poDatePlaced, Date poDateRequired, String poNotes, String empName,
            String empEmail, boolean delivery, boolean completed) {
        super();
        this.poNo = poNo;
        this.clientNo = clientNo;
        this.poDatePlaced = poDatePlaced;
        this.poDateRequired = poDateRequired;
        this.poNotes = poNotes;
        this.empName = empName;
        this.empEmail = empEmail;
        this.delivery = delivery;
        this.completed = completed;
    }
    
    /**
     * Method to get the purchase order number
     * @return purchase order number
     */
    public int getPoNo() {
        return poNo;
    }

    /**
     * Method to set the purchase order number
     * @param poNo 
     */
    public void setPoNo(int poNo) {
        this.poNo = poNo;
    }

    /**
     * method to get client number
     * @return client number
     */
    public int getClientNo() {
        return clientNo;
    }

    /**
     * Method to set client number
     * @param clientNo 
     */
    public void setClientNo(int clientNo) {
        this.clientNo = clientNo;
    }

    /**
     * Method to get the purchase order date placed
     * @return date placed
     */
    public Date getPoDatePlaced() {
        return poDatePlaced;
    }

    /**
     * Method to set the date placed
     * @param poDatePlaced 
     */
    public void setPoDatePlaced(Date poDatePlaced) {
        this.poDatePlaced = poDatePlaced;
    }

    /**
     * Method to get the date that the order is needed
     * @return date required
     */
    public Date getPoDateRequired() {
        return poDateRequired;
    }

    /**
     * Method to set the date that the purchase order is required
     * @param poDateRequired 
     */
    public void setPoDateRequired(Date poDateRequired) {
        this.poDateRequired = poDateRequired;
    }

    /**
     * Method to get purchase order notes
     * @return purchase order notes
     */
    public String getPoNotes() {
        return poNotes;
    }

    /**
     * Method to set notes for the Purchase Order
     * @param poNotes 
     */
    public void setPoNotes(String poNotes) {
        this.poNotes = poNotes;
    }

    /**
     * Method to get the name of the employer who placed the purchase order
     * @return employer name
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * Method to set the name of the employer
     * @param empName 
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    /**
     * Method to get the email of the employer
     * @return 
     */
    public String getEmpEmail() {
        return empEmail;
    }

    /**
     * Method to set the employer email
     * @param empEmail 
     */
    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    /**
     * Method to check if the purchase order is delivered
     * @return delivery status
     */
    public boolean isDelivery() {
        return delivery;
    }

    /**
     * Method to set the delivery status
     * @param delivery 
     */
    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    /**
     * Method to check if a purchase order has been completed
     * @return completion status
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Method to set the completion status of a purchase order
     * @param completed 
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
