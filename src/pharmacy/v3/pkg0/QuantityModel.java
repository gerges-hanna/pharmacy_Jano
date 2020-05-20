/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy.v3.pkg0;

/**
 *
 * @author gerges Fci-H
 */
public class QuantityModel {
    String getEN;
    String getID_ope;
    String getQuantity;
    String getTables;
    double Quantity;
    double Tables;

    public QuantityModel(String getEN, String getID_ope, String getQuantity, String getTables, double Quantity, double Tables) {
        this.getEN = getEN;
        this.getID_ope = getID_ope;
        this.getQuantity = getQuantity;
        this.getTables = getTables;
        this.Quantity = Quantity;
        this.Tables = Tables;
    }
    
    

    public String getGetEN() {
        return getEN;
    }

    public void setGetEN(String getEN) {
        this.getEN = getEN;
    }

    public String getGetID_ope() {
        return getID_ope;
    }

    public void setGetID_ope(String getID_ope) {
        this.getID_ope = getID_ope;
    }

    public String getGetQuantity() {
        return getQuantity;
    }

    public void setGetQuantity(String getQuantity) {
        this.getQuantity = getQuantity;
    }

    public String getGetTables() {
        return getTables;
    }

    public void setGetTables(String getTables) {
        this.getTables = getTables;
    }

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double Quantity) {
        this.Quantity = Quantity;
    }

    public double getTables() {
        return Tables;
    }

    public void setTables(double Tables) {
        this.Tables = Tables;
    }
    
}
