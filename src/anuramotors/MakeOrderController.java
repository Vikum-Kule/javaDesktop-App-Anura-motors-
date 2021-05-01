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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Vikum
 */
public class MakeOrderController implements Initializable {
    public HomeModel HomeModel;
    @FXML
    private Text customerName;
    @FXML
    private ComboBox<String> cBoxPhone;
    @FXML
    private ComboBox<String> cBoxVehicle;
    //initialize table objects,,
    @FXML
    private TableColumn<HomeModel, Integer> colNo;
    @FXML
    private TableColumn<HomeModel, String> colItemName;
    @FXML
    private TableColumn<HomeModel, String> colBrand;
    @FXML
    private TableColumn<HomeModel, Integer> colQty;
    @FXML
    private TableColumn<HomeModel, Double> colPrice;
    @FXML
    private TableView<HomeModel> table;
    @FXML
    private TableColumn<?, ?> colDelete;
    
     ObservableList<HomeModel> options = FXCollections.observableArrayList();
    
    public MakeOrderController() {
        this.HomeModel = new HomeModel();
        
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //calling to fill combo Boxes..
        fillcBox();
        
        //calling to fill table...with order items
        filltable();
        
        

    } 
    
    
    public void fillcBox(){
        //set the list of numbers to combo box.
        cBoxPhone.setItems(FXCollections.observableArrayList(HomeModel.getDataphone()));
        
        //set list of vehicle numbers to the combo box.
        cBoxVehicle.setItems(FXCollections.observableArrayList(HomeModel.getDataVehicle()));
    }
    
    public void filltable(){
        //database connection
        Connection conection =HomeModel.conection;
        
        //initialize table row indexes
        int index = 1;
        try {
            String query = "select item.name, item.brand, tempOrder.customerId, tempOrder.qty, tempOrder.price " +
" from tempOrder " +
"inner join item on tempOrder.itemId = item.itemId";
            PreparedStatement statement = conection.prepareStatement(query);
            ResultSet set = statement.executeQuery();
                           
            while (set.next()) {
                 System.out.println("Hello");
                options.add(new HomeModel(index,set.getString("name"),set.getString("brand"),
                        set.getInt("qty"),set.getDouble("price")));
                index++;
                
            }
        colNo.setCellValueFactory(new PropertyValueFactory<>("no"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        table.setItems(options);

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
