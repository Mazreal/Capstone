/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author 783385
 */
public class Users {

    private int userNo;
    private String username;
    private String password;
    private char roleID;

    /**
     * Default constructor
     */
    public Users() 
    {

    }

    /**
     * Constructor
     * 
     * @param userNo user number
     * @param username username
     * @param password password
     * @param roleID role id
     */
    public Users(int userNo, String username, String password, char roleID) 
    {
        super();
        this.userNo = userNo;
        this.username = username;
        this.password = password;
        this.roleID = roleID;
    }

    /**
     * Returns the user number
     * 
     * @return user number 
     */
    public int getUserNo() 
    {
        return userNo;
    }

    /**
     * Sets the user number
     * 
     * @param userNo user number to be set
     */
    public void setUserNo(int userNo) 
    {
        this.userNo = userNo;
    }

    /**
     * Returns the username
     * 
     * @return username 
     */
    public String getUsername() 
    {
        return username;
    }

    /**
     * Sets the username
     * 
     * @param username username to be set
     */
    public void setUsername(String username) 
    {
        this.username = username;
    }

    /**
     * Sets the password
     * 
     * @return password
     */
    public String getPassword() 
    {
        return password;
    }

    /**
     * Sets the user's password
     * 
     * @param password password to be st
     */
    public void setPassword(String password) 
    {
        this.password = password;
    }

    /**
     * Returns the role id
     * 
     * @return the role id
     */
    public char getRoleID() 
    {
        return roleID;
    }

    /**
     * Sets the role id
     * 
     * @param roleID role id to be set
     */
    public void setRoleID(char roleID) 
    {
        this.roleID = roleID;
    }
}
