/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import domain.POItems;
import domain.PurchaseOrder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 783385
 */
public class purchOrderJDBCUtil {

    /**
     * This method provides connection to the database
     * @return connection
     */
    private Connection getConnection() {
        DBConnection dbconn = new DBConnection();
        Connection conn = dbconn.getConnection();
        return conn;
    }
    
    /**
     * Method to insert a purchase order into the database
     * @param purchaseOrder the purchase order to be inserted
     * @return row count
     */
    public int insertPO(PurchaseOrder purchaseOrder) {
        int rowcount = 0;
        Connection conn = getConnection();
        int poNo = purchaseOrder.getPoNo();
        int clientNo = purchaseOrder.getClientNo();
        Date dp = purchaseOrder.getPoDatePlaced();
        Date dr = purchaseOrder.getPoDateRequired();
        String poNotes = purchaseOrder.getPoNotes();
        String empName = purchaseOrder.getEmpName();
        String empEmail = purchaseOrder.getEmpEmail();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String datePlaced = formatter.format(dp);
        String dateRequired = formatter.format(dr);

        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO Purchase_Order "
                    + "VALUES('" + poNo + "',"
                    + "'" + datePlaced + "',"
                    + "'" + dateRequired + "',"
                    + "'" + poNotes + "',"
                    + "'" + empName + "',"
                    + "'" + empEmail + "',"
                    + "'" + 0 + "',"
                    + "'" + 0 + "',"
                    + "'" + clientNo + "')";
            rowcount = stmt.executeUpdate(sql);
            System.out.println("A PO row is inserted");
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Insertion Failed");
            System.out.println(ex.getMessage());
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowcount;
    }

    /**
     * Method to insert the items of a purchase order into the database
     * @param poi the purchase order items to be inserted
     * @return 1 if inserted successfully
     */
    public int insertPOItems(POItems poi) {
        Connection conn = getConnection();
        String insertString = "INSERT INTO PO_Items VALUES(?,?,?,?,?)";
        System.out.println(poi.getMenuItem().size());
        System.out.println(poi.getQuantity().size());
        try {
            PreparedStatement ps = conn.prepareStatement(insertString);
            for (int i = 0; i < poi.getMenuItem().size(); i++) {
                ps.setInt(1, poi.getPoItemNo().get(i));
                ps.setInt(2, poi.getPoNo());
                ps.setString(3, poi.getEmpName());
                ps.setDouble(4, poi.getQuantity().get(i));
                ps.setString(5, poi.getMenuItem().get(i));
                ps.execute();
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Warehouse_KitchenJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return 1;
    }
    /**
     * Method to update the purchase order
     * @param po the purchase order to be updated
     * @return 1 if inserted successfully
     */
    public int updatePO(PurchaseOrder po) {
        Connection conn = getConnection();
        String updateString = "UPDATE Purchase_Order SET "
                + "poDateRequired = ?,"
                + "poNotes = ?,"
                + "empName = ?,"
                + "empEmail = ?,"
                + "delivery = ?,"
                + "completed = ? "
                + "WHERE poNo = ?;";
        try {
            Date date = po.getPoDateRequired();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            int d = 0, c = 0;
            if (po.isDelivery() == true) {
                d = 1;
            }
            if (po.isCompleted() == true) {
                c = 1;
            }

            PreparedStatement ps = conn.prepareStatement(updateString);
            ps.setDate(1, sqlDate);
            ps.setString(2, po.getPoNotes());
            ps.setString(3, po.getEmpName());
            ps.setString(4, po.getEmpEmail());
            ps.setInt(5, d);
            ps.setInt(6, c);
            ps.setInt(7, po.getPoNo());
            ps.executeUpdate();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(purchOrderJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

        return 1;
    }

    /**
     * Method to update purchase order items
     * @param poi the purchase order items to be updated
     * @return 1 if inserted successfully
     */
    public int updatePOItems(POItems poi) {
        Connection conn = getConnection();
        String updateString = "UPDATE PO_Items SET "
                + "quantity = ?,"
                + "menuItem = ? "
                + "WHERE poItemNo = ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(updateString);
            for (int i = 0; i < poi.getQuantity().size(); i++) {
                ps.setDouble(1, poi.getQuantity().get(i));
                ps.setString(2, poi.getMenuItem().get(i));
                ps.setInt(3, poi.getPoItemNo().get(i));
                ps.executeUpdate();

            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(purchOrderJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

        return 1;
    }
    
    /**
     * Method to get a specific purchase order from the database
     * @param poNo the purchase order to be retrieved
     * @return purchase order
     */
    public PurchaseOrder getPO(int poNo) {
        PurchaseOrder po = null;
        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM Purchase_Order WHERE poNo = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, poNo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                boolean delivery = false;
                boolean completed = false;
                if (rs.getInt("delivery") == 1) {
                    delivery = true;
                }
                if (rs.getInt("completed") == 1) {
                    completed = true;
                }
                poNo = rs.getInt("poNo");
                Date datePlaced = rs.getDate("poDatePlaced");
                Date dateRequired = rs.getDate("poDateRequired");
                String poNotes = rs.getString("poNotes");
                String empName = rs.getString("empName");
                String empEmail = rs.getString("empEmail");
                int clientNo = rs.getInt("clientNo");
                po = new PurchaseOrder(poNo, clientNo, datePlaced, dateRequired, poNotes, empName, empEmail, delivery, completed);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(purchOrderJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return po;
    }

    /**
     * Method to get the Items of a purchase order
     * @param poNo the purchase order items to be retrieved
     * @return purchase order items 
     */
    public POItems getPOItem(int poNo) {
        Connection conn = getConnection();
        ArrayList<Double> quantity = new ArrayList<Double>();
        ArrayList<String> menuItem = new ArrayList<String>();
        ArrayList<Integer> poItemNo = new ArrayList<Integer>();
        PurchaseOrder po = new PurchaseOrder();
        POItems poi = null;
        try {
            Statement stmt = null;
            stmt = conn.createStatement();
            String query = "SELECT * FROM PO_Items WHERE poNo = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, poNo);
            ResultSet rs = ps.executeQuery();

            String query1 = "SELECT * FROM Purchase_Order WHERE poNo = ?";
            PreparedStatement ps1 = conn.prepareStatement(query1);
            ps1.setInt(1, poNo);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                boolean delivery = false;
                boolean completed = false;
                if (rs1.getInt("delivery") == 1) {
                    delivery = true;
                }
                if (rs1.getInt("completed") == 1) {
                    completed = true;
                }
                poNo = rs1.getInt("poNo");
                Date datePlaced = rs1.getDate("poDatePlaced");
                Date dateRequired = rs1.getDate("poDateRequired");
                String poNotes = rs1.getString("poNotes");
                String empName = rs1.getString("empName");
                String empEmail = rs1.getString("empEmail");
                int clientNo = rs1.getInt("clientNo");

                while (rs.next()) {
                    poItemNo.add(rs.getInt("poItemNo"));
                    quantity.add(rs.getDouble("quantity"));
                    menuItem.add(rs.getString("menuItem"));

                    poi = new POItems(poNo, clientNo,
                            datePlaced, dateRequired, poNotes,
                            empName, empEmail, delivery, completed, poItemNo, quantity, menuItem);

                }
            }

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(purchOrderJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return poi;
    }
    
    /**
     * Method to get all the purchase orders from the database
     * @return all the purchase orders
     */
    public List<PurchaseOrder> getAll() {
        List<PurchaseOrder> pos = new ArrayList<PurchaseOrder>();
        Connection conn = getConnection();
        try {
            ResultSet rs = null;
            Statement stmt = null;
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Purchase_Order;");

            while (rs.next()) {
                boolean delivery = false;
                boolean completed = false;
                if (rs.getInt("delivery") == 1) {
                    delivery = true;
                }
                if (rs.getInt("completed") == 1) {
                    completed = true;
                }
                PurchaseOrder po = new PurchaseOrder(rs.getInt("poNo"), rs.getInt("clientNo"),
                        rs.getDate("poDatePlaced"), rs.getDate("poDateRequired"), rs.getString("poNotes"),
                        rs.getString("empName"), rs.getString("empEmail"), delivery, completed);
                pos.add(po);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(purchOrderJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pos;
    }

    /**
     * Method to get the items for all the purchase orders
     * @return purchase order items
     */
    public ArrayList<POItems> getAllPOItems() {
        Connection conn = getConnection();
        ArrayList<Double> quantity = new ArrayList<Double>();
        ArrayList<String> menuItem = new ArrayList<String>();
        ArrayList<Integer> poItemNo = new ArrayList<Integer>();
        ArrayList<POItems> pos = new ArrayList<POItems>();
        try {

            ResultSet rs = null;
            Statement stmt = null;
            stmt = conn.createStatement();
            String query = "SELECT poNo, poDatePlaced, poDateRequired, poNotes, Purchase_Order.empName, empEmail, delivery, completed, clientNo, menuItem, quantity "
                    + "FROM Purchase_Order JOIN PO_Items using (poNo);";
            PreparedStatement ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                boolean delivery = false;
                boolean completed = false;
                poItemNo.add(rs.getInt("poItemNo"));
                quantity.add(rs.getDouble("quantity"));
                menuItem.add(rs.getString("menuItem"));
                if (rs.getInt("delivery") == 1) {
                    delivery = true;
                }
                if (rs.getInt("completed") == 1) {
                    completed = true;
                }
                POItems po = new POItems(rs.getInt("poNo"), rs.getInt("clientNo"),
                        rs.getDate("poDatePlaced"), rs.getDate("poDateRequired"), rs.getString("poNotes"),
                        rs.getString("empName"), rs.getString("empEmail"), delivery, completed, poItemNo, quantity, menuItem);
                pos.add(po);

            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(purchOrderJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pos;
    }

    /**
     * Method to delete a purchase order from the database
     * @param poNo the purchase order to be deleted
     * @return 1 if successfully deleted
     */
    public int deletePO(int poNo) {
        Connection conn = getConnection();
        String deleteString = "DELETE FROM Purchase_Order WHERE poNo = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(deleteString);
            ps.setInt(1, poNo);
            ps.execute();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(purchOrderJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return 1;
    }

    /**
     * Method to delete the purchase order items
     * @param poNo the purchase order items to be deleted
     * @return 1 if successfully deleted
     */
    public int deletePOItems(int poNo) {
        Connection conn = getConnection();
        String deleteString = "DELETE FROM PO_Items WHERE poNo = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(deleteString);
            ps.setInt(1, poNo);
            ps.execute();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(purchOrderJDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return 1;
    }

}
