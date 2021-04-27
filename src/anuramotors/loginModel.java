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

/**
 *
 * @author Vikum
 */
public class loginModel {
    Connection conection;
    
    //create connection...
    public loginModel(){
        conection = sqliteConnection.ConnectDB();
        if(conection == null){
            System.exit(1);
        }   
    }
    
    //check the connection of the database.
    public boolean isDbConnected(){
       try{
         return !conection.isClosed();// by using !(not mark) returns true according to is.closed() function...
       } catch (SQLException e){
         e.printStackTrace();
         return false;
       }
    }
    
    //check password and user name.
    public boolean checkLogin(String uname, String pass) throws SQLException{
            System.out.println( uname + "  "+pass );
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            
            String quary = "select * from user where userName = ? and password = ?";
        try {    
            preparedStatement = conection.prepareStatement(quary);
            preparedStatement.setString(1, uname);
            preparedStatement.setString(2, pass);
            
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
            System.out.println("hello");
            preparedStatement.close();
            resultSet.close();
            
         }
  
    
    }
}
