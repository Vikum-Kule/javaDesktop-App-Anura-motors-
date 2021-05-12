/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anuramotors;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Vikum
 */
public class OrderController implements Initializable {

    @FXML
    private TableColumn<OrderModel, Integer> colNo;
    @FXML
    private TableColumn<OrderModel, String> colCustomer;
    @FXML
    private TableColumn<OrderModel, String> colDate;
    @FXML
    private TableColumn<OrderModel, Integer> colOrderNo;
    @FXML
    private TableColumn<OrderModel, Double> colTotal;
    @FXML
    private TableView<OrderModel> Table;
    @FXML
    private TableColumn<OrderModel, String> colPhone;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void fillTable(){
//            Table.getItems().clear();
//        //database connection
//        Connection conection =OrderModel.conection;
//        
//        //initialize table row indexes
//        int index = 1;
//        try {
//            String query = "";
//            PreparedStatement statement = conection.prepareStatement(query);
//            ResultSet set = statement.executeQuery();
//            DecimalFormat df = new DecimalFormat("#.00");              
//            while (set.next()) {
//                 
//                 total = total+set.getDouble("price");
//                 System.out.println(total);
//                 
//                 String Price = String.valueOf(df.format(set.getDouble("price")));
//                options.add(new HomeModel(index,set.getString("name"),set.getString("brand"),
//                        set.getInt("qty"),Price));
//                index++;
//                
//            }
//        colNo.setCellValueFactory(new PropertyValueFactory<>("no"));
//        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
//        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
//        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
//        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
//        table.setItems(options);
//        String Total = String.valueOf(df.format(total));
//        txtTotal.setText(Total);
//        
//            statement.close();
//            set.close();
//            System.out.println(options);
//            // Return the List
//           // return options;
//
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
    
    }
    
}
