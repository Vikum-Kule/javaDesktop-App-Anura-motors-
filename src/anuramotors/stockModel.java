/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anuramotors;

import java.sql.Connection;

/**
 *
 * @author Vikum
 */
public class stockModel {
    
    Connection conection;
    
    int no;
    String itemName;
    String brand;
    String MCategory;
    String SCategroy;
    int qty;
    String price;
    int itemId;
    int MCategoryId;
    int SCategoryId;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getMCategoryId() {
        return MCategoryId;
    }

    public void setMCategoryId(int MCategoryId) {
        this.MCategoryId = MCategoryId;
    }

    public int getSCategoryId() {
        return SCategoryId;
    }

    public void setSCategoryId(int SCategoryId) {
        this.SCategoryId = SCategoryId;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMCategory() {
        return MCategory;
    }

    public void setMCategory(String MCategory) {
        this.MCategory = MCategory;
    }

    public String getSCategroy() {
        return SCategroy;
    }

    public void setSCategroy(String SCategroy) {
        this.SCategroy = SCategroy;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public stockModel(int no, String itemName, String brand, String MCategory, String SCategroy, int qty, String price
        ,int itemId, int MCategoryId, int SCategoryId) {
        this.no = no;
        this.itemName = itemName;
        this.brand = brand;
        this.MCategory = MCategory;
        this.SCategroy = SCategroy;
        this.qty = qty;
        this.price = price;
        this.itemId = itemId;
        this.MCategoryId = MCategoryId;
        this.SCategoryId = SCategoryId;
    }
    
    public stockModel(){
       conection = sqliteConnection.ConnectDB();
        if(conection == null){
            System.exit(1);
        }
    }
    
}
