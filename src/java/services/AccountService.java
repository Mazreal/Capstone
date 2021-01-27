/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import domain.Users;

/**
 *
 * @author 769293
 */
public class AccountService {
    
         public Users login(String username, String password) {
        try {
            
                UserService us = new UserService();
                Users user = us.get(username);

            if (user.getPassword().equals(password) && user.getUsername().equals(username)) {
                return user;
            }
        } catch (Exception e) {

        }
        return null;
     }
        
    
}
