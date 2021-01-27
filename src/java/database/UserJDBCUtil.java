/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import domain.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 783385
 */
public class UserJDBCUtil {

    /**
     * To get a connection to the database
     *
     * @return connection
     */
    private Connection getConnection() {
        DBConnection dbconn = new DBConnection();
        Connection conn = dbconn.getConnection();
        return conn;
    }

    /**
     * To insert a user into the database
     *
     * @param user the user to be added
     * @return 1 if inserted successfully
     */
    public int insertUser(Users user) {
        Connection conn = getConnection();
        String insertString = "INSERT INTO Users VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(insertString);

            ps.setInt(1, user.getUserNo());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, String.valueOf(user.getRoleID()));
            ps.execute();

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return 1;
    }

    /**
     * To update a user
     *
     * @param user the user to be updated
     * @return 1 if updated successfully
     */
    public int updateUser(Users user) {
        Connection conn = getConnection();
        String updateString = "UPDATE Users SET userNo = ?, username = ?, password = ?, roleID = ? WHERE userNo = ?";
        try {
            int userNo = user.getUserNo();

            PreparedStatement ps = conn.prepareStatement(updateString);
            ps.setInt(1, user.getUserNo());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, String.valueOf(user.getRoleID()));
            ps.setInt(5, userNo);
            ps.executeUpdate();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(UserJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

        return 1;
    }

    /**
     * To delete a user from the database
     *
     * @param userNo the user number of the user to be deleted
     * @return 1 if removed successfully
     */
    public int deleteUser(int userNo) {
        Connection conn = getConnection();
        String deleteString = "DELETE FROM Users WHERE userNo = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(deleteString);
            ps.setInt(1, userNo);
            ps.execute();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return 1;
    }

    /**
     * Method to get a specified user
     *
     * @param userNo to the user number of the user to be retrieved
     * @return selected user
     */
    public Users getUser(int userNo) {
        Users user = null;
        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM Users WHERE userNo = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userNo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4).charAt(0));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
      /**
     * Method to get a specified user
     *
     * @param userName to the user name of the user to be retrieved
     * @return selected user
     */
    public Users getUserName(String userName) {
        Users user = null;
        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM Users WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4).charAt(0));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public List<Users> getAll(){
        List<Users> users = new ArrayList<Users>();
        Connection conn = getConnection();
        try
        {
            ResultSet rs = null;
            Statement stmt = null;
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Users;");

            while (rs.next())
            {   
                Users user = new Users(rs.getInt("userNo"), rs.getString("username"), rs.getString("password"), rs.getString("roleID").charAt(0));
                users.add(user);
            }
            conn.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(UserJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }
}
