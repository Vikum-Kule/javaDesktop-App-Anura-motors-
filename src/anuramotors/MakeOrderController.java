/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anuramotors;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
    } 
    
    
    public void fillcBox(){
        //set the list of numbers to combo box.
        cBoxPhone.setItems(FXCollections.observableArrayList(HomeModel.getDataphone()));
        
        //set list of vehicle numbers to the combo box.
        cBoxVehicle.setItems(FXCollections.observableArrayList(HomeModel.getDataVehicle()));
    }
    
}
