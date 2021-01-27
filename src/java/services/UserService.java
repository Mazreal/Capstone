/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.UserJDBCUtil;
import domain.Users;
import java.util.List;

/**
 *
 * @author 783385
 */
public class UserService {

    private UserJDBCUtil userDB;

    public UserService() {
        userDB = new UserJDBCUtil();
    }

    /**
     * Inserts a user 
     * 
     * @param user 
     * @return 
     */
    public int insertUser(Users user) {
        return userDB.insertUser(user);
    }

    /**
     * Gets the user number
     * 
     * @param userNo number of user
     * @return user number
     */
    public Users getUser(int userNo) {
        return userDB.getUser(userNo);
    }
    
    /**
     * Gets the username of a user
     * 
     * @param username of the user
     * @return username of the user
     * @throws NullPointerException if no users exists in the database
     */
    public Users get(String username) throws Exception {
        return userDB.getUserName(username);
    }
    
    /**
     * Gets all the users in the database
     * 
     * @return list of users
     * @throws NullPointerException if no users exists in the database
     */
    public List<Users> getAll() throws Exception {
        return userDB.getAll();
    }

   /**
    * Updates an existing user
    * 
    * @param userNo
    * @param username of the user
    * @param password of the password
    * @param roleID 
    */
    public void updateUser(int userNo, String username, String password, char roleID) {
        //Users user = new Users(userNo, username, password, roleID);
        Users user = getUser(userNo);
        user.setUsername(username);
        user.setPassword(password);
        user.setRoleID(roleID);
    }

    /**
     * Insert a new user in the database
     * 
     * @param username of the user
     * @param password of the user
     * @param roleID
     * @return 
     */
    public int insertUser(String username, String password, char roleID) {
         int noteid = 0;
        Users user = new Users(noteid, username, password, roleID);
        return userDB.insertUser(user);
    }

}
