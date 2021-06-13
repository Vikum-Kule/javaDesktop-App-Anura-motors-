/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anuramotors;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Vikum
 */
public class AddToStockController implements Initializable {

    public HomeModel HomeModel = new HomeModel();
    
    @FXML
    private Button btnClose;
    @FXML
    private TextField txtPrice_Add;
    @FXML
    private Button btnNewSupplier_Add;
    @FXML
    private TextField txtItem_Add;
    @FXML
    private TextField txtBrand_Add;
    @FXML
    private TextField txtMCategory_Add;
    @FXML
    private TextField txtSCategory_Add;
    @FXML
    private TextField txtQty_Add;
    @FXML
    private TextField txtSupplier;
    @FXML
    private TextField txtQty1;
    @FXML
    private TextField txtPrice1;
    @FXML
    private Button btnNewSupplier1;
    @FXML
    private Button btnNewUnit1;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    
        ArrayList<String> name = (ArrayList<String>) HomeModel.getItemName();
        ArrayList<String> brand = (ArrayList<String>) HomeModel.getItemBrand();
        
        TextFields.bindAutoCompletion(txtItem_Add, name);
        TextFields.bindAutoCompletion(txtItem_Add, brand);
        
    }    

    @FXML
    private void onclickClose(ActionEvent event) {
    }

    @FXML
    private void onclickNewSupplier(ActionEvent event) {
    }

    @FXML
    private void onclickNewUnit(ActionEvent event) {
    }
    
}
