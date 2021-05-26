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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
   
    private final OrderModel OrderModel;
    @FXML
    private TableColumn<OrderModel, String> colPayment;
    public OrderController() {
        this.OrderModel = new OrderModel();
        
    }

    ObservableList<OrderModel> options = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillTable();
        // TODO
    }    
    
    public void fillTable(){
            Table.getItems().clear();
        //database connection
        Connection conection =OrderModel.conection;
        
        //initialize table row indexes
        int index = 1;
        try {
            String query = "select orderId,customer.customerName,customer.phone,dateTime,total,payment "
                    + "from 'order' "
                    + "inner join customer on customerId = customer.phone";
            PreparedStatement statement = conection.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            DecimalFormat df = new DecimalFormat("#.00");              
            while (set.next()) {
                 
                String Price = String.valueOf(df.format(set.getDouble("total")));
            options.add(new OrderModel(index,set.getInt("orderId"), set.getString("customerName"), set.getString("phone"), Price,
                    set.getString("dateTime"), set.getString("payment")));
                index++;
                
            }
        colNo.setCellValueFactory(new PropertyValueFactory<>("no"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOrderNo.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        Table.setItems(options);
        
            statement.close();
            set.close();
            System.out.println(options);
            // Return the List
           // return options;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }
    
}
