/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 673970
 */
public class DBConnection
{
    private Connection dbConn = null;
    
    public Connection getConnection()
    {
        //aakv0tqjuufxyg.ccpxbxjp1rrs.us-west-2.rds.amazonaws.com
        try
        {
            String dbDriver = "com.mysql.jdbc.Driver";
            String dbURL = "jdbc:mysql://aa61mp8bvw7cop.c5ifuf9alg8p.us-west-2.rds.amazonaws.com:3306/fit";
            String userName = "PmJjDc2020";
            String password = "Cf0Df9Jl.Jf6MePrS";

            Class.forName(dbDriver);
            dbConn = (Connection) DriverManager.getConnection(dbURL,userName,password);
            //System.out.println("DB Connection Succeeds");
        } catch (SQLException e)
        {
            //System.out.println("DB Connection Fails");
            //System.out.println(e.getMessage());
            
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dbConn;
    }
}
