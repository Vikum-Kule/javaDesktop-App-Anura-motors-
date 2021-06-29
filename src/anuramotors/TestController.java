/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anuramotors;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Vikum
 */
public class TestController implements Initializable {
    
    public HomeModel HomeModel = new HomeModel();
    public stockModel stockModel = new stockModel();
    
    @FXML
    private Button btnClose;
    @FXML
    private TextField txt_New_Supplier;
    @FXML
    private TextField txt_New_Item;
    @FXML
    private TextField txt_New_Brand;
    @FXML
    private TextField txt_New_MCtegory;
    @FXML
    private TextField txt_New_SCategory;
    @FXML
    private TextField txt_New_Qty;
    @FXML
    private TextField txt_New_Unit;
    @FXML
    private TextField txt_New_UPrice;
    @FXML
    private TextField txt_Re_Supplier;
    @FXML
    private TextField txt_Re_Item;
    @FXML
    private TextField txt_Re_Brand;
    @FXML
    private TextField txt_Re_MCtegory;
    @FXML
    private TextField txt_Re_SCategory;
    @FXML
    private TextField txt_Re_Qty;
    @FXML
    private TextField txt_Re_Unit;
    @FXML
    private TextField txt_Re_UPrice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //callling to input initialization function fro auto complete
        input_dataset();
        
        
    }    

    @FXML
    private void onclickClose(ActionEvent event) {
        System.out.println("hello");
        Stage stage1 = (Stage) btnClose.getScene().getWindow();
        stage1.close(); 
    }
    
    public void input_dataset(){
        ArrayList<String> name = (ArrayList<String>) HomeModel.getItemName();
        ArrayList<String> brand = (ArrayList<String>) HomeModel.getItemBrand();
        ArrayList<String> MCategory = (ArrayList<String>) stockModel.MainCategory();
        ArrayList<String> SCategory = (ArrayList<String>) stockModel.SubCategory();
        
        //for set all list to new.. to autocomplete
        TextFields.bindAutoCompletion(txt_New_Item, name);
        TextFields.bindAutoCompletion(txt_New_Brand, brand);
        TextFields.bindAutoCompletion(txt_New_MCtegory, MCategory);
        TextFields.bindAutoCompletion(txt_New_SCategory, SCategory);
        
        //for set all list to Stock refill.. to autocomplete
        TextFields.bindAutoCompletion(txt_Re_Item, name);
        TextFields.bindAutoCompletion(txt_Re_Brand, brand);
        TextFields.bindAutoCompletion(txt_Re_MCtegory, MCategory);
        TextFields.bindAutoCompletion(txt_Re_SCategory, SCategory);
    }
    
}
