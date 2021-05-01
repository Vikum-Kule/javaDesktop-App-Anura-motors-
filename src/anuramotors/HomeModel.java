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

/**
 *
 * @author Vikum
 */
public class HomeModel {
    Connection conection;
    String name,brand;
    int qty,no;
    double price;
    
    //constructor for variables...
    public HomeModel(int no,String name, String brand, int qty, double price) {
        this.no = no;
        this.name = name;
        this.brand = brand;
        this.qty = qty;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

   
     
    
}
