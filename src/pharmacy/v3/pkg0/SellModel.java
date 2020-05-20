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
public class SellModel {
    private String id;
    private String EN_Name;
    private String AR_Name;
    private String Effect;
    private String EXP;
    private String idope;
    private int Quantity;
    private int Tablets;
    private int Tablets_Per_Box;
    private double Sell;
    private double Discount;
    private String Time;
    private String Date;
    private String User;

    public SellModel(String id, String EN_Name, String AR_Name, String Effect, String EXP, String idope, int Quantity, int Tablets, int Tablets_Per_Box, double Sell, double Discount, String Time, String Date, String User, double Retail, String Company) {
        this.id = id;
        this.EN_Name = EN_Name;
        this.AR_Name = AR_Name;
        this.Effect = Effect;
        this.EXP = EXP;
        this.idope = idope;
        this.Quantity = Quantity;
        this.Tablets = Tablets;
        this.Tablets_Per_Box = Tablets_Per_Box;
        this.Sell = Sell;
        this.Discount = Discount;
        this.Time = Time;
        this.Date = Date;
        this.User = User;
        this.Retail = Retail;
        this.Company = Company;
    }

    public SellModel(String id, String idope, int Quantity) {
        this.id = id;
        this.idope = idope;
        this.Quantity = Quantity;
    }

    public SellModel(String id, String EN_Name, String AR_Name, String Effect, String EXP, String idope, int Quantity, int Tablets, int Tablets_Per_Box, double Sell, double Discount, double Retail, String Company) {
        this.id = id;
        this.EN_Name = EN_Name;
        this.AR_Name = AR_Name;
        this.Effect = Effect;
        this.EXP = EXP;
        this.idope = idope;
        this.Quantity = Quantity;
        this.Tablets = Tablets;
        this.Tablets_Per_Box = Tablets_Per_Box;
        this.Sell = Sell;
        this.Discount = Discount;
        this.Retail = Retail;
        this.Company = Company;
    }
    private double Retail;
    private String Company;

    public SellModel(String id, String EN_Name, String AR_Name, String idope, 
            int Quantity, int Tablets, int Tablets_Per_Box) {
        this.id = id;
        this.EN_Name = EN_Name;
        this.AR_Name = AR_Name;
        this.idope = idope;
        this.Quantity = Quantity;
        this.Tablets = Tablets;
        this.Tablets_Per_Box = Tablets_Per_Box;
    }

    public SellModel(String idope) {
        this.idope = idope;
    }

    public SellModel(String id, String idope) {
        this.id = id;
        this.idope = idope;
    }

    public SellModel() {
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEN_Name() {
        return EN_Name;
    }

    public void setEN_Name(String EN_Name) {
        this.EN_Name = EN_Name;
    }

    public String getAR_Name() {
        return AR_Name;
    }

    public void setAR_Name(String AR_Name) {
        this.AR_Name = AR_Name;
    }

    public String getEffect() {
        return Effect;
    }

    public void setEffect(String Effect) {
        this.Effect = Effect;
    }

    public String getEXP() {
        return EXP;
    }

    public void setEXP(String EXP) {
        this.EXP = EXP;
    }

    public String getIdope() {
        return idope;
    }

    public void setIdope(String idope) {
        this.idope = idope;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getTablets() {
        return Tablets;
    }

    public void setTablets(int Tablets) {
        this.Tablets = Tablets;
    }

    public int getTablets_Per_Box() {
        return Tablets_Per_Box;
    }

    public void setTablets_Per_Box(int Tablets_Per_Box) {
        this.Tablets_Per_Box = Tablets_Per_Box;
    }

    public double getSell() {
        return Sell;
    }

    public void setSell(double Sell) {
        this.Sell = Sell;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double Discount) {
        this.Discount = Discount;
    }

    public double getRetail() {
        return Retail;
    }

    public void setRetail(double Retail) {
        this.Retail = Retail;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String Company) {
        this.Company = Company;
    }
    
    
}
