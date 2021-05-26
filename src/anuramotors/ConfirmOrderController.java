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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vikum
 */
public class ConfirmOrderController implements Initializable {
    public HomeModel HomeModel = new HomeModel();
    public MakeOrderController MakeOrderController = new MakeOrderController();
    
    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnCancel;
    @FXML
    private Text txtTotal;
    @FXML
    private TableColumn<HomeModel, Integer> colNo;
    @FXML
    private TableColumn<HomeModel, String> colName;
    @FXML
    private TableColumn<HomeModel, String> colBrand;
    @FXML
    private TableColumn<HomeModel, String> colPrice;
    @FXML
    private TableView<HomeModel> table;
    @FXML
    private TableColumn<HomeModel, Integer> colQty;
    
     ObservableList<HomeModel> options = FXCollections.observableArrayList();
    @FXML
    private ToggleGroup tgl1;
    @FXML
    private RadioButton btnCash;
    @FXML
    private RadioButton btnCheque;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnCash.setSelected(true);
        fillTable();
    }    
    
    
    public void fillTable(){
        table.getItems().clear();
        //database connection
        Connection conection =HomeModel.conection;
        
        //initialize table row indexes
        int index = 1;
        Double total =0.0;
        try {
            String query = "select item.name, item.brand, tempOrder.customerId, tempOrder.MCategory, tempOrder.SCategory, tempOrder.qty, tempOrder.price " +
" from tempOrder " +
"inner join item on tempOrder.itemId = item.itemId";
            PreparedStatement statement = conection.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            DecimalFormat df = new DecimalFormat("#.00");  
                           
            while (set.next()) {
                String name;
                if(set.getString("MCategory")== null && set.getString("SCategory")== null){
                    name =set.getString("name");
                }
                else if(set.getString("MCategory")!= null && set.getString("SCategory")== null){
                    name =set.getString("name")+ " ( "+set.getString("MCategory")+" )";
                }
                else{
                    name =set.getString("name")+ " ( "+set.getString("MCategory")+" / "+set.getString("SCategory")+" ) ";
                }
                 total = total+set.getDouble("price");
                 
                 String Price = String.valueOf(df.format(set.getDouble("price")));
                options.add(new HomeModel(index,name,set.getString("brand"),
                        set.getInt("qty"),Price));
                index++;
                
            }
        colNo.setCellValueFactory(new PropertyValueFactory<>("no"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        table.setItems(options);
        txtTotal.setText(String.valueOf(df.format(total)));
            statement.close();
            set.close();
            System.out.println(options);
            // Return the List
           // return options;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    @FXML
    private void confirmRecheck(ActionEvent event) {
        String payment = "";
        
        //check selected radio button to select payment method...
       if(btnCash.isSelected()){
          // System.out.println("Cash");
           payment = "Cash";
       }
       else{
           // System.out.println("Cheque");
            payment = "Cheque";
       }
       
       String customerId = HomeModel.findCustomerId();
       //System.out.println("Customer id "+ customerId);
       Double total = Double.parseDouble(txtTotal.getText());
       //pass data to method to insert order table..
       int orderId = HomeModel.addOrderTable(customerId, total, payment);
        System.out.println("orderId "+orderId);
        if(orderId != 0){
        Connection con = HomeModel.conection;
        try {
           String query ="SELECT itemId, qty, price, SCategory, MCategory FROM tempOrder";
           PreparedStatement statement = con.prepareStatement(query);
           ResultSet set = statement.executeQuery();
           while(set.next()){
               int itemId = set.getInt("itemId");
               int qty = set.getInt("qty");
               Double price = set.getDouble("price");
               int SCategory = set.getInt("SCategory");
               int MCategory = set.getInt("MCategory");
               
               //Add items to the OderItem table....
               HomeModel.addItem_ItemOrder(orderId, itemId, qty, price, 0, MCategory, SCategory);
                Stage stage1 = (Stage) btnCancel.getScene().getWindow();
                stage1.close();
           }
        } catch (Exception e) {
            System.out.println(e);
        }
            
        }
        
        
    }

    @FXML
    private void closeRecheck(ActionEvent event) {
        Stage stage1 = (Stage) btnCancel.getScene().getWindow();
        stage1.close();
    }
    
    
    
}
