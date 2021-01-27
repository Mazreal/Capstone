/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.purchOrderJDBCUtil;
import domain.PurchaseOrder;
import java.util.ArrayList;
import java.util.List;
import domain.POItems;
import java.util.Date;

/**
 *
 * @author 783385
 */
public class POServices {

    private static int pin = 0;
    private static ArrayList<Integer> poItemNo = new ArrayList<Integer>();
    private purchOrderJDBCUtil poDB;

    public POServices() {
        poDB = new purchOrderJDBCUtil();
    }

    public int insertPO(PurchaseOrder po) {
        return poDB.insertPO(po);
    }

    public int insertPOItem(int poNo, int clientNo, Date poDatePlaced, Date poDateRequired, String poNotes, String empName, 
            String empEmail, boolean delivery, boolean completed, ArrayList<Double> quantity, ArrayList<String> menuItem) {
        poItemNo.add(pin);
        POItems poi = new POItems(poNo, clientNo, poDatePlaced, poDateRequired, poNotes, empName, empEmail, delivery, completed,
        poItemNo, quantity, menuItem);
        return poDB.insertPOItems(poi);
    }

    public int updatePO(PurchaseOrder po) {
        return poDB.updatePO(po);
    }

    public int updatePOItems(POItems poi) {
        return poDB.updatePOItems(poi);
    }

    public int deletePO(int poNo) {
        PurchaseOrder deletedPo = poDB.getPO(poNo);
        System.out.println("deleted po num is:" + deletedPo);
        return poDB.deletePO(poNo);
    }

    public int deletePOItems(int poNo) {
        PurchaseOrder deletedPo = poDB.getPO(poNo);
        System.out.println("deleted po num is:" + deletedPo);
        return poDB.deletePOItems(poNo);
    }

    public PurchaseOrder get(int poNo) throws Exception {
        return poDB.getPO(poNo);
    }

    public POItems getPOItem(int poNo) throws Exception {
        return poDB.getPOItem(poNo);
    }

    public List<PurchaseOrder> getAllPO() throws Exception {
        return poDB.getAll();
    }

    public ArrayList<POItems> getAllPOItems() throws Exception {
        return poDB.getAllPOItems();
    }

}
