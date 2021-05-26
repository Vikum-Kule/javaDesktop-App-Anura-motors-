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
    int no;
    int orderId;
    String customerName;
    String phone;
    String total;
    String date;
    String payment;

    public OrderModel(int no,int orderId, String customerName, String phone, String total, String date, String payment) {
        this.no = no;
        this.orderId = orderId;
        this.customerName = customerName;
        this.phone = phone;
        this.total = total;
        this.date = date;
        this.payment = payment;
    }
    
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
    
    public OrderModel(){
       conection = sqliteConnection.ConnectDB();
        if(conection == null){
            System.exit(1);
        } 
   }
    
}
