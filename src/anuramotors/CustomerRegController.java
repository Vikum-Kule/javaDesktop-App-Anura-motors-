/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anuramotors;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vikum
 */
public class CustomerRegController implements Initializable {
    public HomeModel HomeModel = new HomeModel();
    public MakeOrderController MakeOrderController = new MakeOrderController();
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtCustomerName;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtVehicle;
    @FXML
    private Text warningText;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addCustomer(ActionEvent event) {
        String customerName = txtCustomerName.getText();
        String phone = txtPhone.getText();
        String vehicle = txtVehicle.getText();
        //MakeOrderController.newCustomerData (customerName, phone, vehicle);
        //input validations...
        if (!customerName.matches("[ a-zA-Z_]+")) {
            warningText.setText("please check name.. ");
        }
        else{
            if (!phone.matches("[0-9]+") && phone.length()!=10) {
                warningText.setText("Invalid phone number..");
            }
            else{
                if(HomeModel.addCustomer(customerName, phone, vehicle)){
                   // MakeOrderController.newCustomerData (customerName, phone, vehicle);
                    Stage stage1 = (Stage) btnCancel.getScene().getWindow();
                    stage1.close();
                    
                   
                }
            }
            
        }
        
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage1 = (Stage) btnCancel.getScene().getWindow();
        stage1.close(); 
    }
    
}
