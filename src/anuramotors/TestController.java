/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anuramotors;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
    @FXML
    private Button btn_New_Submit;
    @FXML
    private Tab btn_Re_Submit;
    @FXML
    private Text txtWarning;

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
    
    public void addtoStock_New() throws SQLException{
        int QTY = 0;
        Double Uprice = 0.0;
        
        //check empty feilds...
        
        String warning = "";
        if(txt_New_Supplier.getText().isEmpty()){
            warning = "Supllier ";
        }
        if(txt_New_Item.getText().isEmpty()){
            warning = warning +"item ";
        }
        if(txt_New_Brand.getText().isEmpty()){
            warning = warning +"brand ";
        }
        if(txt_New_Qty.getText().isEmpty()){
            warning = warning +"quentity ";
        }
        if(txt_New_Unit.getText().isEmpty()){
            warning = warning +"unit ";
        }
        else{
          QTY = Integer.valueOf(txt_New_Qty.getText());  
        }
        if(txt_New_UPrice.getText().isEmpty()){
            warning = warning +"unit price ";
        }
        else{
          Uprice = Double.valueOf(txt_New_UPrice.getText());  
        }
        
        if(warning == ""){
            
            String supplier = txt_New_Supplier.getText();
            String item = txt_New_Item.getText();
            String brand = txt_New_Brand.getText();
            String MCategory = txt_New_MCtegory.getText();
            String SCategroy = txt_New_SCategory.getText();
            String unit = txt_New_Unit.getText();
            
            //check whether item is new or not...
            if( stockModel.checkItem(item, brand)){
                System.out.println("yes..");
            }
            else{
                if(txt_New_MCtegory.getText().isEmpty()){
                    int id = stockModel.addItem(item,"no", brand, QTY, unit, Uprice);
                    if(id != 0){
                        System.out.println("Done..");
                    }
                }
                else if(!txt_New_MCtegory.getText().isEmpty() && txt_New_SCategory.getText().isEmpty()){
                    int id = stockModel.addItem(item,"yes", brand, 0, unit, 0.0 );
                    if(id != 0){
                        int category_id = stockModel.addItem_MCategory(id, MCategory, "no", QTY, Uprice);
                    }
                }
                else{
                    int id = stockModel.addItem(item,"yes", brand, 0, unit, 0.0 );
                    if(id != 0){
                        int category_id = stockModel.addItem_MCategory(id, MCategory, "yes", 0, 0.0);
                        if(category_id!=0){
                            boolean result = stockModel.addData_subCategory(SCategroy, category_id, QTY, unit, Uprice);
                            if(result){
                                System.out.println("Done..");
                            }
                            else{
                                System.out.println("Something went wrong");
                            }
                        }
                    }
                }
            }
            
        }
        else{
           txtWarning.setText("Please fill "+ warning+ "feilds");
        }
        
    }

    @FXML
    private void onclick_NewSubmit(ActionEvent event) throws SQLException {
        addtoStock_New();
    }

    @FXML
    private void onclick_ReSubmit(Event event) {
    }
    
}
