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
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author Vikum
 */
public class HomeModel {
    Connection conection;
    String name,brand;
    int qty,no;
    String Price;
    
    //constructor for variables...
    public HomeModel(int no,String name, String brand, int qty, String Price) {
        this.no = no;
        this.name = name;
        this.brand = brand;
        this.qty = qty;
        this.Price = Price;
    }
   public HomeModel(){
       conection = sqliteConnection.ConnectDB();
        if(conection == null){
            System.exit(1);
        } 
   }
   
  //getters and setters for table variables.. 
   public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }
    
   //list loder for phone numbers...
   List<String> getDataphone() {

        // Define the data you will be returning, in this case, a List of Strings for the ComboBox
        List<String> options = new ArrayList<>();

        try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "select phone from customer";
            PreparedStatement statement = conection.prepareStatement(query);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                options.add(set.getString("phone"));
            }

            statement.close();
            set.close();

            // Return the List
            return options;

        } catch (SQLException ex) {
            return null;
        }
   }
   
   //list loder for vehicle numbers...
   List<String> getDataVehicle() {

        // Define the data you will be returning, in this case, a List of Strings for the ComboBox
        List<String> options = new ArrayList<>();

        try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "select vehicalNo from customer";
            PreparedStatement statement = conection.prepareStatement(query);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                options.add(set.getString("vehicalNo"));
            }

            statement.close();
            set.close();

            // Return the List
            return options;

        } catch (SQLException ex) {
            return null;
        }
   }
   
   //List loder for itemNames..
List<String> getItemName() {

        // Define the data you will be returning, in this case, a List of Strings for the ComboBox
        List<String> options = new ArrayList<>();

        try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "select name from item";
            PreparedStatement statement = conection.prepareStatement(query);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                options.add(set.getString("name"));
            }

            statement.close();
            set.close();

            // Return the List
            return options;

        } catch (SQLException ex) {
            return null;
        }
   }

//List loder for itemBrand..
List<String> getItemBrand() {

        // Define the data you will be returning, in this case, a List of Strings for the ComboBox
        List<String> options = new ArrayList<>();

        try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "select brand from item";
            PreparedStatement statement = conection.prepareStatement(query);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                options.add(set.getString("brand"));
            }

            statement.close();
            set.close();

            // Return the List
            return options;

        } catch (SQLException ex) {
            return null;
        }
   }

// return customer name and vehicle number number by taking phone number..

//"Pair" use to return couple of values....
public Pair<String, String> customerName(String phone){
    //System.out.println("ee");
    String customerName = "";
    String vehicle = "";
     try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "select customerName,vehicalNo from customer where phone = ?";
            PreparedStatement statement = conection.prepareStatement(query);
            statement.setString(1, phone);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
               customerName = set.getString("customerName");
               vehicle = set.getString("vehicalNo");
            }

            statement.close();
            set.close();

            // Return the List
            return new Pair<>(customerName, vehicle);

        } catch (SQLException ex) {
            return null;
        }
}


// return customer name and phone number by taking vahicle number..
public Pair<String, String> customerNameFromVehicle(String vehicle){
    System.out.println(vehicle);
    String customerName = "x";
    String phone = "y";
     try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "select customerName,phone from customer where vehicalNo = ?";
            PreparedStatement statement = conection.prepareStatement(query);
            statement.setString(1, vehicle);
            ResultSet set = statement.executeQuery();
            
            while (set.next()) {
               customerName = set.getString("customerName");
               phone = set.getString("phone");
               
               
            }
            System.out.println(customerName + " "+ phone);

            statement.close();
            set.close();

            // Return the List
            return new Pair<>(customerName, phone);

        } catch (SQLException ex) {
            
            return null;
        }
}
   // Add new customer to database...
  public boolean addCustomer(String name, String phone, String vehicle){
      try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "INSERT INTO customer(customerName,phone,vehicalNo,discount,regDate) VALUES(?,?,?,?,datetime('now'))";
            PreparedStatement statement = conection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, phone);
            statement.setString(3, vehicle);
            statement.setString(4, "No");
            //statement.setString(5,datetime('now'));
            statement.executeUpdate();     
            statement.close();
            return true;

        } catch (SQLException ex) {
           System.out.println(ex);
           return false;
        }
  }
  
  //set brands according to item name..
    
  List<String> setItemBrand(String name) {

        // Define the data you will be returning, in this case, a List of Strings for the ComboBox
        List<String> options = new ArrayList<>();

        try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "select brand from item where name =?";
            PreparedStatement statement = conection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                options.add(set.getString("brand"));
            }

            statement.close();
            set.close();

            // Return the List
            return options;

        } catch (SQLException ex) {
            return null;
        }
   }
  
  public Pair<Integer, Double> itemData(String itemName, String itemBrand){
      int avlQty =0;
      Double unitPrice= 0.0;
      try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "select qty,currentUnitPrice from item where name = ? and brand =?";
            PreparedStatement statement = conection.prepareStatement(query);
            statement.setString(1,itemName );
            statement.setString(2,itemBrand );
            ResultSet set = statement.executeQuery();
            
            while (set.next()) {
               avlQty = set.getInt("qty");
               unitPrice = set.getDouble("currentUnitPrice");
               
               
            }
            statement.close();
            set.close();

            // Return the List
            return new Pair<>(avlQty, unitPrice);

        } catch (SQLException ex) {
            
            return null;
        }
   }
  
  //return itemId..
  public int findItemId (String itemName, String itemBrand){
      int itemId =0;
      try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "select itemId from item where name = ? and brand =?";
            PreparedStatement statement = conection.prepareStatement(query);
            statement.setString(1,itemName );
            statement.setString(2,itemBrand );
            ResultSet set = statement.executeQuery();
            
            while (set.next()) {
               itemId = set.getInt("itemId");
               
            }
            statement.close();
            set.close();

            // Return the itemId
            return itemId;

        } catch (SQLException ex) {
            return 0;
        }
      
  }
  
  public void addItemtoOrder(String customerId, int itemId, int qty, Double price){
      try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "INSERT INTO tempOrder(tempId,customerId,itemId,qty,price) VALUES(null,?,?,?,?)";
            PreparedStatement statement = conection.prepareStatement(query);
            statement.setString(1, customerId);
            statement.setInt(2, itemId);
            statement.setInt(3, qty);
            statement.setDouble(4, price);
            //statement.setString(5,datetime('now'));
            statement.executeUpdate();     
            statement.close();
           // return true;

        } catch (SQLException ex) {
           System.out.println(ex);
          // return false;
        }
  }
  
  //check wether adding item is duplicate or not... 
  public boolean checkDuplicateItem(int itemId) throws SQLException{
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            
            String quary = "select * from tempOrder where itemId = ?";
        try {    
            preparedStatement = conection.prepareStatement(quary);
            preparedStatement.setInt(1, itemId);
            
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
    
}
