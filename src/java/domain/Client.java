/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author 673970
 */
public class Client
{
    private int clientNo;
    private String clientName;
    private String clientCompany;
    private String clientAddress;
    private String clientEmail;
    private String clientPhone;

    public Client(){};
    
    /**
     * Constructor
     * 
     * @param clientNo client number of the client
     * @param clientName client name 
     * @param clientCompany client company
     * @param clientAddress client address
     * @param clientEmail client email
     * @param clientPhone client phone
     */
    public Client(int clientNo, String clientName, String clientCompany, String clientAddress, String clientEmail, String clientPhone)
    {
        this.clientNo = clientNo;
        this.clientName = clientName;
        this.clientCompany = clientCompany;
        this.clientAddress = clientAddress;
        this.clientEmail = clientEmail;
        this.clientPhone = clientPhone;
    }

    /**
     * Returns the client number
     * 
     * @return client number
     */
    public int getClientNo()
    {
        return clientNo;
    }

    /**
     * Sets the client number
     * 
     * @param clientNo client number to be set
     */
    public void setClientNo(int clientNo)
    {
        this.clientNo = clientNo;
    }
    
    /**
     * Returns the client name
     * 
     * @return client name
     */
    public String getClientName()
    {
        return clientName;
    }

    /**
     * Sets the client name
     * 
     * @param clientName name to be set
     */
    public void setClientName(String clientName)
    {
        this.clientName = clientName;
    }

    /**
     * Returns the client company name
     * 
     * @return the client company name
     */
    public String getClientCompany()
    {
        return clientCompany;
    }

    /**
     * Sets the client company name
     * 
     * @param clientCompany company to be set
     */
    public void setClientCompany(String clientCompany)
    {
        this.clientCompany = clientCompany;
    }

    /**
     * Returns the client address
     * 
     * @return client address
     */
    public String getClientAddress()
    {
        return clientAddress;
    }

    /**
     * Sets the client address
     * 
     * @param clientAddress address to be set
     */
    public void setClientAddress(String clientAddress)
    {
        this.clientAddress = clientAddress;
    }

    /**
     * Returns the client email
     * 
     * @return the client email
     */
    public String getClientEmail()
    {
        return clientEmail;
    }

    /**
     * Sets the client email
     * 
     * @param clientEmail email to be set
     */
    public void setClientEmail(String clientEmail)
    {
        this.clientEmail = clientEmail;
    }

    /**
     * Returns client phone number
     * 
     * @return client phone number
     */
    public String getClientPhone()
    {
        return clientPhone;
    }

    /**
     * Sets the client's phone number
     * 
     * @param clientPhone phone number to be set
     */
    public void setClientPhone(String clientPhone)
    {
        this.clientPhone = clientPhone;
    }
    


}
