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
   
   public boolean checkCategory(String itemName){
       
       String Category="";
       try {
            String query = "SELECT Category FROM item WHERE name = ?";
            PreparedStatement statement = conection.prepareStatement(query);
            statement.setString(1, itemName);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
               Category = set.getString("Category");
            }

            statement.close();
            set.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
         
            if("yes".equals(Category)){
                return true;
            }
            else{
                return false;
            }
           
   }
   
   //check sub categories....
   public boolean checkSubCategory(String category){
       
       String Category="";
       try {
            String query = "SELECT SubCategory FROM MainCategory WHERE Category = ?";
            PreparedStatement statement = conection.prepareStatement(query);
            statement.setString(1, category);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
               Category = set.getString("SubCategory");
                
            }

            statement.close();
            set.close();
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }
       
            if("yes".equals(Category)){
                return true;
            }
            else{
                return false;
            }
   }
   
   //List loder for Category..
List<String> getItemCategory(String itemName) {
        
        // Define the data you will be returning, in this case, a List of Strings for the ComboBox
        List<String> options = new ArrayList<>();

        try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "SELECT MainCategory.Category FROM MainCategory "
                    + "INNER JOIN item ON item.itemId = MainCategory.itemId "
                    + "WHERE item.name = ?";
            PreparedStatement statement = conection.prepareStatement(query);
            statement.setString(1, itemName);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                System.out.println(set.getString("Category"));
                options.add(set.getString("Category"));
            }

            statement.close();
            set.close();
            // Return the List
            return options;

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
   }
   
    //load sub categories...
    List<String> getItemSubCategory(String Category) {
        
        // Define the data you will be returning, in this case, a List of Strings for the ComboBox
        List<String> options = new ArrayList<>();

        try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "SELECT SubCategory.Scategory FROM SubCategory "
                    + "INNER JOIN MainCategory ON MainCategory.categoryNo = SubCategory.MainCategoryId "
                    + "WHERE MainCategory.Category = ?";
            PreparedStatement statement = conection.prepareStatement(query);
            statement.setString(1, Category);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                System.out.println(set.getString("Scategory"));
                options.add(set.getString("Scategory"));
            }

            statement.close();
            set.close();
            // Return the List
            return options;

        } catch (SQLException ex) {
            System.out.println(ex);
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
  
  //return available qty and unit price for without category items..
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
  
  //return available qty and unit price for only main category items..
  public Pair<Integer, Double> itemDataforMCategory(String itemName, String itemBrand, String category){
      int avlQty =0;
      Double unitPrice= 0.0;
      try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "select MainCategory.qty,MainCategory.price from MainCategory "
                    + "inner join item on item.itemId = MainCategory.itemId"
                    + " where item.name = ? and item.brand =? and MainCategory.Category=?";
            PreparedStatement statement = conection.prepareStatement(query);
            statement.setString(1,itemName );
            statement.setString(2,itemBrand );
            statement.setString(3,category);
            ResultSet set = statement.executeQuery();
            
            while (set.next()) {
               avlQty = set.getInt("qty");
               unitPrice = set.getDouble("price");
               
               
            }
            statement.close();
            set.close();

            // Return the List
            return new Pair<>(avlQty, unitPrice);

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
   }
  
 
   //return available qty and unit price for wiht main category and sub category items..
  public Pair<Integer, Double> itemDataforSCategory(String itemName, String itemBrand, String Mcategory, String Scategory){
      int avlQty =0;
      Double unitPrice= 0.0;
      try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "select SubCategory.qty, SubCategory.price from SubCategory "
                    + "inner join item on item.itemId = MainCategory.itemId"
                    + " inner join MainCategory on MainCategory.categoryNo = SubCategory.MainCategoryId"
                    + " where item.name = ? and item.brand =? and MainCategory.Category=? and SubCategory.Scategory=?";
            PreparedStatement statement = conection.prepareStatement(query);
            statement.setString(1,itemName );
            statement.setString(2,itemBrand );
            statement.setString(3,Mcategory);
            statement.setString(4,Scategory);
            ResultSet set = statement.executeQuery();
            
            while (set.next()) {
               avlQty = set.getInt("qty");
               unitPrice = set.getDouble("price");
               
               
            }
            statement.close();
            set.close();

            // Return the List
            return new Pair<>(avlQty, unitPrice);

        } catch (SQLException ex) {
            System.out.println(ex);
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
  
  //find Main Category id..
  public int findMCategoryId(int itemId, String Category){
      int MCategoryId =0;
      try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "select categoryNo from MainCategory where itemId =? and Category =?";
            PreparedStatement statement = conection.prepareStatement(query);
            statement.setInt(1,itemId );
            statement.setString(2,Category );
            ResultSet set = statement.executeQuery();
            
            while (set.next()) {
               MCategoryId = set.getInt("categoryNo");
               
            }
            statement.close();
            set.close();

            // Return the itemId
            return MCategoryId;

        } catch (SQLException ex) {
            System.out.println(ex);
            return 0;
        }
      
  }
  
  //add items to tempOrder table with MCategory...
  public void addItemtoOrderwithMCategory(String customerId, int itemId, int qty, Double price, int MCategory){
      try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "INSERT INTO tempOrder(tempId,customerId,itemId,qty,price,MCategory) VALUES(null,?,?,?,?,?)";
            PreparedStatement statement = conection.prepareStatement(query);
            statement.setString(1, customerId);
            statement.setInt(2, itemId);
            statement.setInt(3, qty);
            statement.setDouble(4, price);
            statement.setInt(5, MCategory);
            //statement.setString(5,datetime('now'));
            statement.executeUpdate();     
            statement.close();
           // return true;

        } catch (SQLException ex) {
           System.out.println(ex);
          // return false;
        }
  }
  
  //add items to tempOrder table with MCategory and SCategory...
  public void addItemtoOrderwithCategory(String customerId, int itemId, int qty, Double price, int MCategory, String SCategory){
      try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "INSERT INTO tempOrder(tempId,customerId,itemId,qty,price,MCategory,SCategory) VALUES(null,?,?,?,?,?,?)";
            PreparedStatement statement = conection.prepareStatement(query);
            statement.setString(1, customerId);
            statement.setInt(2, itemId);
            statement.setInt(3, qty);
            statement.setDouble(4, price);
            statement.setInt(5, MCategory);
            statement.setString(6, SCategory);
            //statement.setString(5,datetime('now'));
            statement.executeUpdate();     
            statement.close();
           // return true;

        } catch (SQLException ex) {
           System.out.println(ex);
          // return false;
        }
  }
  
  
  //check wether adding item(Non category) is duplicate or not... 
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
  
    //insert data to order table..
  public int addOrderTable(String customerId, Double total, String payment){
      //System.out.println("customerId "+ customerId +"total "+total);
      int Id= 0;
       try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "INSERT INTO 'order'(customerId,dateTime,total,payment) VALUES(?,datetime('now'),?,?)";
            PreparedStatement statement = conection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, customerId);
            statement.setDouble(2, total);
            statement.setString(3, payment);
            //statement.setString(5,datetime('now'));
            statement.executeUpdate();  
            ResultSet rs = statement.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            //take last enterd row id....
            System.out.println("generatedKey "+ generatedKey);
            Id = generatedKey;
            statement.close();
            return Id;

        } catch (SQLException ex) {
           System.out.println(ex);
           return Id;
        }
  }
   
  
  //check wether adding item(only main category) is duplicate or not... 
  public boolean checkDuplicateItemwithMainCategory(int itemId,int MCategory) throws SQLException{
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            
            String quary = "select * from tempOrder where itemId = ? and MCategory =?";
        try {    
            preparedStatement = conection.prepareStatement(quary);
            preparedStatement.setInt(1, itemId);
            preparedStatement.setInt(2, MCategory);
            
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
  
  //check wether adding item(with both category) is duplicate or not... 
  public boolean checkDuplicateItemwithCategory(int itemId,int MCategory, String SCategory) throws SQLException{
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            
            String quary = "select * from tempOrder where itemId = ? and MCategory =? and SCategory=?";
        try {    
            preparedStatement = conection.prepareStatement(quary);
            preparedStatement.setInt(1, itemId);
            preparedStatement.setInt(2, MCategory);
            preparedStatement.setString(3, SCategory);
            
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
  
  public String findCustomerId (){
      String customerId = "";
      try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "select customerId from tempOrder Limit 1";
            PreparedStatement statement = conection.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            
            while (set.next()) {
               customerId = set.getString("customerId");
               
            }
            statement.close();
            set.close();

            // Return the itemId
            return customerId;

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
      
  }
  
  public void addItem_ItemOrder(int OrderId, int ItemId, int Qty, Double price, int discount, int MCategory, int SCategory){
      try {
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con = DriverManager.getConnection(connectionUrl);
            String query = "INSERT INTO OrderItem(OrderId, ItemId, Qty, price, discount, MCategory, SCategory) "
                    + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement statement = conection.prepareStatement(query);
            statement.setInt(1, OrderId);
            statement.setInt(2, ItemId);
            statement.setInt(3, Qty);
            statement.setDouble(4, price);
            statement.setInt(5, discount);
            statement.setInt(6, MCategory);
            statement.setInt(7, SCategory);
            //statement.setString(5,datetime('now'));
            statement.executeUpdate();     
            statement.close();
           // return true;

        } catch (SQLException ex) {
           System.out.println(ex);
          // return false;
        }
  }
  

}
