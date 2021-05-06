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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillTable();
    }    
    
    
    public void fillTable(){
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
                           
            while (set.next()) {
                 System.out.println(set.getDouble("price"));
                 DecimalFormat df = new DecimalFormat("#.00");
                 String Price = String.valueOf(df.format(set.getDouble("price")));
                options.add(new HomeModel(index,set.getString("name"),set.getString("brand"),
                        set.getInt("qty"),Price));
                index++;
                
            }
        colNo.setCellValueFactory(new PropertyValueFactory<>("no"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
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
    @FXML
    private void confirmRecheck(ActionEvent event) {
    }

    @FXML
    private void closeRecheck(ActionEvent event) {
        Stage stage1 = (Stage) btnCancel.getScene().getWindow();
        stage1.close();
    }
    
}
