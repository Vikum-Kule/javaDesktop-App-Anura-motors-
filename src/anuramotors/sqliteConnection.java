/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anuramotors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Vikum
 */
public class sqliteConnection {
    public static Connection ConnectDB(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection con= DriverManager.getConnection("jdbc:sqlite:infomation.db");
            return con;
        }
        catch(ClassNotFoundException | SQLException e){
           
           JOptionPane.showMessageDialog(null, e);
           
            return null;
        }
    }
}
