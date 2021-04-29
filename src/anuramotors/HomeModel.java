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
import java.util.logging.Logger;

/**
 *
 * @author Vikum
 */
public class HomeModel {
    Connection conection;
   public HomeModel(){
       conection = sqliteConnection.ConnectDB();
        if(conection == null){
            System.exit(1);
        } 
   }
    
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
