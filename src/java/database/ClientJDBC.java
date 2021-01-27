/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import domain.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 673970
 */
public class ClientJDBC
{
    private Connection getConnection()
    {
        DBConnection dbconn = new DBConnection();
        Connection conn = dbconn.getConnection();
        return conn;
    }
    
    /**
     * Insert a new client
     * 
     * @param client 
     * @return 
     */
    public int insertClient(Client client)
    {
        Connection conn = getConnection();
        String insertString = "INSERT INTO Client VALUES(?,?,?,?,?,?)";
        try
        {
            PreparedStatement ps = conn.prepareStatement(insertString);
            ps.setInt(1, client.getClientNo());
            ps.setString(2, client.getClientName());
            ps.setString(3,client.getClientCompany());
            ps.setString(4,client.getClientAddress());
            ps.setString(5,client.getClientEmail());
            ps.setString(6,client.getClientPhone());
            ps.execute();
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(ClientJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
    
    /**
     * Updates an existing client in the database
     * 
     * @param client
     * @return 
     */
    public int updateClient(Client client)
    {
        Connection conn = getConnection();
        String updateString = "UPDATE Client SET "+
                "clientName = ?, "+
                "clientCompany = ?, " +
                "clientAddress = ?, " +
                "clientEmail = ?," +
                "clientPhoneNumber = ?" +
                "WHERE clientNo = ?";
        try
        {
            PreparedStatement ps = conn.prepareStatement(updateString);
            
            ps.setString(1, client.getClientName());
            ps.setString(2,client.getClientCompany());
            ps.setString(3,client.getClientAddress());
            ps.setString(4,client.getClientEmail());
            ps.setString(5,client.getClientPhone());
            ps.setInt(6, client.getClientNo());
            ps.execute();
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(ClientJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
    
    /**
     * Deletes a client in the database
     * 
     * @param clientNo
     * @return 
     */
    public int deleteClient(int clientNo)
    {
        Connection conn = getConnection();
        String deleteString = "DELETE FROM Client WHERE clientNo=?";
        try
        {
            PreparedStatement ps = conn.prepareStatement(deleteString);
            ps.setInt(1, clientNo);
            ps.execute();
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(ClientJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
    
    /**
     * Gets a specific client in the database
     * 
     * @param clientNo
     * @return 
     */
    public Client getClient(int clientNo)
    {
        Connection conn = getConnection();
        String query = "SELECT * FROM Client WHERE clientNo=?";
        Client client = null;
        try
        {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,clientNo);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                client = new Client(rs.getInt("clientNo"), rs.getString("clientName"), rs.getString("clientCompany"), rs.getString("clientAddress"), rs.getString("clientEmail"), rs.getString("clientPhoneNumber"));
            }
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(ClientJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return client;
    }
    
    /**
     * Gets all the clients in the database
     * 
     * @return all the clients in the database 
     */
    public ArrayList<Client> getAllClients()
    {
        ArrayList<Client> clientList = new ArrayList<Client>();
        Connection conn = getConnection();
        String query = "SELECT * FROM Client";
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Client client = null;
            while(rs.next())
            {
                client = new Client(rs.getInt("clientNo"), rs.getString("clientName"), rs.getString("clientCompany"), rs.getString("clientAddress"), rs.getString("clientEmail"), rs.getString("clientPhoneNumber"));
                clientList.add(client);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(ClientJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
             
        return clientList;
    }
    
    /**
     * 
     * @return 
     */
    public int findHighestClientId()
    {
        Connection conn = getConnection();
        String query = "SELECT MAX(clientNo) FROM Client";
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                return rs.getInt(1);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(ClientJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
