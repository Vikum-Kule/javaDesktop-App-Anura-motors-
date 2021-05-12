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
public class OrderModel {
    Connection conection;
    
    public OrderModel(){
       conection = sqliteConnection.ConnectDB();
        if(conection == null){
            System.exit(1);
        } 
   }
    
}
