/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anuramotors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    
    List<String> MainCategory(){
       List<String> option = new ArrayList<>();
       
        try {
            String quary = "Select Category from MainCategory";
            PreparedStatement ps = conection.prepareStatement(quary) ;
            ResultSet set =  ps.executeQuery();
            
            while(set.next()){
                option.add(set.getString("Category"));
            }
             ps.close();
             set.close();
             return option;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    List<String> SubCategory(){
       List<String> option = new ArrayList<>();
       
        try {
            String quary = "Select Scategory from SubCategory";
            PreparedStatement ps = conection.prepareStatement(quary) ;
            ResultSet set =  ps.executeQuery();
            
            while(set.next()){
                option.add(set.getString("Scategory"));
            }
             ps.close();
             set.close();
             return option;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    //check whether item name and brand exist in the table..
    public boolean checkItem(String item, String brand) throws SQLException{
            
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            
            String quary = "select * from item where name = ? and brand = ?";
        try {    
            preparedStatement = conection.prepareStatement(quary);
            preparedStatement.setString(1, item);
            preparedStatement.setString(2, brand);
            
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
            else{    
                return false;
            }
        } catch (SQLException e) {
            System.out.println( e );
            return false;
        } finally{
            preparedStatement.close();
            resultSet.close();
            
         }
  
    
    }
    
    //insert new item ,brand into item table..
    public int addItem_MCategory(int id, String MCategory,String SCategory, int qty, Double price ){
      try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "INSERT INTO MainCategory(itemId, Category, SubCategory, qty, price) VALUES(?,?,?,?,?)";
            PreparedStatement statement = conection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, MCategory);
            statement.setString(3, SCategory);
            statement.setInt(4, qty);
            statement.setDouble(5, price);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            statement.close();
            return generatedKey;

        } catch (SQLException ex) {
           System.out.println(ex);
           return 0;
        }
  }
   
     //insert new item ,brand into item table..
    public int addItem(String name, String ctegory, String brand, int qty, String unitType, Double price ){
      try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "INSERT INTO item(name, Category, brand, barcode, qty, unitType, currentUnitPrice) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement statement = conection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setString(2, ctegory);
            statement.setString(3, brand);
            statement.setString(4, "");
            statement.setInt(5, qty);
            statement.setString(6, unitType);
            statement.setDouble(7, price);
            //statement.setString(5,datetime('now'));
            statement.executeUpdate();  
            ResultSet rs = statement.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            statement.close();
            return generatedKey;

        } catch (SQLException ex) {
           System.out.println(ex);
           return 0;
        }
  }
    
     //insert data into sub category table..
    public boolean addData_subCategory(String SCategory, int MCategory_id, int qty, String unitType, Double price ){
      try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "INSERT INTO SubCategory(Scategory, MainCategoryId, qty, unit, price) VALUES(?,?,?,?,?)";
            PreparedStatement statement = conection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, SCategory);
            statement.setInt(2, MCategory_id);
            statement.setInt(3, qty);
            statement.setString(4, unitType);
            statement.setDouble(5, price);
            statement.executeUpdate();
            statement.close();
            return true;

        } catch (SQLException ex) {
           System.out.println(ex);
           return false;
        }
  }
}
