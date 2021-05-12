/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anuramotors;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;

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
    private TableColumn<HomeModel, String> colPrice;
    @FXML
    private TableView<HomeModel> table;
    @FXML
    private TableColumn<?, ?> colDelete;
    
     ObservableList<HomeModel> options = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> cBoxName;
    @FXML
    private ComboBox<String> cBoxBrand;
    @FXML
    private Button btnNewCustomer;
    @FXML
    private Text txtCustomerSelectionWarning;
    @FXML
    private TextField intputQty;
    @FXML
    private TextField inputDiscount;
    @FXML
    private Button btnAdd;
    @FXML
    private Text txtUnitPrice;
    @FXML
    private Button btnDone;
    @FXML
    private Button btnOrderCancel;
    @FXML
    private Text txtTotal;
    @FXML
    private ComboBox<String> cBoxMCategory;
    @FXML
    private ComboBox<String> cBoxSCategory;
    
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
    
    public double total=0.0;
    public void fillcBox(){
        //set the list of numbers to combo box.
        cBoxPhone.setItems(FXCollections.observableArrayList(HomeModel.getDataphone()));
        
        //set list of vehicle numbers to the combo box.
        cBoxVehicle.setItems(FXCollections.observableArrayList(HomeModel.getDataVehicle()));
        
        //set list of item names to the combo box.
        cBoxName.setItems(FXCollections.observableArrayList(HomeModel.getItemName()));
        
        //set list of brand names to the combo box.
        cBoxBrand.setItems(FXCollections.observableArrayList(HomeModel.getItemBrand()));
    }
    
    public void filltable(){
        table.getItems().clear();
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
            DecimalFormat df = new DecimalFormat("#.00");              
            while (set.next()) {
                 
                 total = total+set.getDouble("price");
                 System.out.println(total);
                 
                 String Price = String.valueOf(df.format(set.getDouble("price")));
                options.add(new HomeModel(index,set.getString("name"),set.getString("brand"),
                        set.getInt("qty"),Price));
                index++;
                
            }
        colNo.setCellValueFactory(new PropertyValueFactory<>("no"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        table.setItems(options);
        String Total = String.valueOf(df.format(total));
        txtTotal.setText(Total);
        
            statement.close();
            set.close();
            System.out.println(options);
            // Return the List
           // return options;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
     //set name, phone, vehicle number of newly added customer to the feilds ..
    public void newCustomerData (String name, String phone, String vehicle){
        //customerName.setText("Name");
//        cBoxPhone.getSelectionModel().select(phone);
//        cBoxVehicle.getSelectionModel().select(vehicle);
        System.out.println("method called" + name +" "+ phone+ " "+ vehicle);
        
    }

    @FXML //select and suggest customer details by phone number
    private void phoneSelect(ActionEvent event) {
        String number = cBoxPhone.getSelectionModel().getSelectedItem();
        Pair<String, String> p = HomeModel.customerName(number);
        String customName = p.getKey();
        String vehicle = p.getValue();
        customerName.setText(customName);
        cBoxVehicle.getSelectionModel().select(vehicle);
        txtCustomerSelectionWarning.setText("");
        
    }

    @FXML //select and suggest customer details by vehicle number
    private void vehicleSelect(ActionEvent event) {
        String vehicle = cBoxVehicle.getSelectionModel().getSelectedItem();
        Pair<String, String> p = HomeModel.customerNameFromVehicle(vehicle);
        String customName = p.getKey();
        String phone = p.getValue();
        
        customerName.setText(customName);
        cBoxPhone.getSelectionModel().select(phone);
        txtCustomerSelectionWarning.setText("");
    }

    //open new window to register customer..
    @FXML
    private void newCustomer(ActionEvent event) throws IOException {
        FXMLLoader fxml = new FXMLLoader();
                    Parent root = fxml.load(getClass().getResource("customerReg.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene); 
                    stage.initStyle(StageStyle.UNDECORATED);
                     stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
    }

    @FXML
    private void onclickName(ActionEvent event){
        txtCustomerSelectionWarning.setText("");
        //check wether customer select or not.. 
        if(customerName.getText()==""){
            txtCustomerSelectionWarning.setText("Please select a customer");
            cBoxName.getSelectionModel().clearSelection();
        }
        else{
            String itemName = cBoxName.getSelectionModel().getSelectedItem();
            cBoxBrand.setItems(FXCollections.observableArrayList(HomeModel.setItemBrand(itemName)));

            //check whether item exist or not...
            if(HomeModel.checkCategory(itemName)){
                //System.out.println(HomeModel.getItemCategory(itemName));
                cBoxMCategory.setItems(FXCollections.observableArrayList(HomeModel.getItemCategory(itemName)));
            }
            else{
             //set list of brand names to the combo box.
             
            }
        }
    }

    @FXML
    private void onclickBrand(ActionEvent event) {
        if(!"Please select a customer".equals(txtCustomerSelectionWarning.getText())){
           txtCustomerSelectionWarning.setText(""); 
        }
        
        //*************check whether item has Categories..***********
        
        //if item has not a category..
        if(cBoxMCategory.getSelectionModel().isEmpty() && cBoxSCategory.getSelectionModel().isEmpty()){
            System.out.println("no category");
            String itemName = cBoxName.getSelectionModel().getSelectedItem();
            String itemBrand = cBoxBrand.getSelectionModel().getSelectedItem();
            Pair<Integer, Double> p = HomeModel.itemData(itemName, itemBrand);
            int qty = p.getKey();
            Double unitPrice = p.getValue();
            DecimalFormat df = new DecimalFormat("#.00");
            String uPrice = String.valueOf(df.format(unitPrice));

            intputQty.setPromptText("Available : "+qty);
            txtUnitPrice.setText(uPrice);
        }
        //if item has Main category without subcategories..
        else if(!cBoxMCategory.getSelectionModel().isEmpty() && cBoxSCategory.getSelectionModel().isEmpty()){
            System.out.println("only main category");
            String itemName = cBoxName.getSelectionModel().getSelectedItem();
            String itemBrand = cBoxBrand.getSelectionModel().getSelectedItem();
            String category = cBoxMCategory.getSelectionModel().getSelectedItem();
            Pair<Integer, Double> p = HomeModel.itemDataforMCategory(itemName, itemBrand, category);
            int qty = p.getKey();
            Double unitPrice = p.getValue();
            
            DecimalFormat df = new DecimalFormat("#.00");
            String uPrice = String.valueOf(df.format(unitPrice));

            intputQty.setPromptText("Available : "+qty);
            txtUnitPrice.setText(uPrice);
        }
        //if item has both categories...
        else{
            System.out.println("category exist");
            System.out.println("only main category");
            String itemName = cBoxName.getSelectionModel().getSelectedItem();
            String itemBrand = cBoxBrand.getSelectionModel().getSelectedItem();
            String Mcategory = cBoxMCategory.getSelectionModel().getSelectedItem();
            String Scategory = cBoxSCategory.getSelectionModel().getSelectedItem();
            Pair<Integer, Double> p = HomeModel.itemDataforSCategory(itemName, itemBrand, Mcategory, Scategory);
            int qty = p.getKey();
            Double unitPrice = p.getValue();
            
            DecimalFormat df = new DecimalFormat("#.00");
            String uPrice = String.valueOf(df.format(unitPrice));

            intputQty.setPromptText("Available : "+qty);
            txtUnitPrice.setText(uPrice);
        }
        
        
        
    }

    @FXML
    private void onclickAdd(ActionEvent event) throws SQLException {
        
        //validation..
        if(cBoxName.getSelectionModel().isEmpty()||cBoxBrand.getSelectionModel().isEmpty()||!intputQty.getText().matches("[0-9]+")){
            
            txtCustomerSelectionWarning.setText("Please check your inputs..");
        }
        else{
            
            //initialization... 
            String itemName = cBoxName.getSelectionModel().getSelectedItem();
            String itemBrand = cBoxBrand.getSelectionModel().getSelectedItem();
            String qty = intputQty.getText();
            String unitPrice = txtUnitPrice.getText();
           // String discount = inputDiscount.getText();
            double uPrice = Double.parseDouble(unitPrice);
            int Qty =Integer.parseInt(qty);  
            int itemId = HomeModel.findItemId(itemName, itemBrand);
            String customerId = cBoxPhone.getSelectionModel().getSelectedItem();
            Double price = uPrice*Qty;
            if(HomeModel.checkDuplicateItem(itemId)){
                txtCustomerSelectionWarning.setText("Duplicate input...");
            }
            else{
                HomeModel.addItemtoOrder(customerId, itemId, Qty, price);
                filltable();
            }
        }
    }

    

    @FXML
    private void onclickQty(MouseEvent event) {
        if(!"Please select a customer".equals(txtCustomerSelectionWarning.getText())){
           txtCustomerSelectionWarning.setText(""); 
        }
    }

    @FXML
    private void openConfirm(ActionEvent event) throws IOException {
        FXMLLoader fxml = new FXMLLoader();
                    Parent root = fxml.load(getClass().getResource("ConfirmOrder.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene); 
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
    }

    @FXML
    private void onclickMCategory(ActionEvent event) {
        String mainCategory = cBoxMCategory.getSelectionModel().getSelectedItem();
        if(HomeModel.checkSubCategory(mainCategory)){
            //set sub categories according to main category..
            cBoxSCategory.setItems(FXCollections.observableArrayList(HomeModel.getItemSubCategory(mainCategory)));
        }
        else{
            //
        }
    }

    @FXML
    private void onclickSCategory(ActionEvent event) {
        
    }
    
   
}
