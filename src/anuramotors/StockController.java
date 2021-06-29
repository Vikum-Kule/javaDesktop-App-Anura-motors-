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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Vikum
 */
public class StockController implements Initializable {

    @FXML
    private TableColumn<stockModel, String> colItem;
    @FXML
    private TableColumn<stockModel, String> colBrand;
    @FXML
    private TableColumn<stockModel, String> colMCategory;
    @FXML
    private TableColumn<stockModel, String> colSCategory;
    @FXML
    private TableColumn<stockModel, Integer> colQty;
    @FXML
    private TableColumn<stockModel, String> colPrice;
    @FXML
    private TableView<stockModel> itemTable;
    @FXML
    private TableColumn<stockModel, Integer> colNo;
    @FXML
    private TableColumn<?, ?> colItemId;
    @FXML
    private TableColumn<?, ?> colMCategoryId;
    @FXML
    private TableColumn<?, ?> colSCategoryId;
    
    
    private final stockModel stockModel;
    @FXML
    private Button btnAddItem;
    
    
    
    public StockController() {
        this.stockModel = new stockModel();
        
    }
    
    ObservableList<stockModel> options = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillNoCatgory();
        fillMCatgory();
        // TODO
    }
    
    int index= 1;
    //table fill with non category items..
    public void fillNoCatgory(){
        Connection conection = stockModel.conection;
        
        try {
            String query = "select itemId,name,brand,qty,currentUnitPrice from item "
                    + "where Category = 'no'";
            PreparedStatement statement = conection.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            DecimalFormat df = new DecimalFormat("#.00");
            
            while(set.next()){
                String Price = String.valueOf(df.format(set.getDouble("currentUnitPrice")));
                options.add(new stockModel(index,set.getString("name"), set.getString("brand"),"no","no", set.getInt("qty"), Price,
                    set.getInt("itemId"),0,0));
                index++;
            }
            colNo.setCellValueFactory(new PropertyValueFactory<>("no"));
            colItem.setCellValueFactory(new PropertyValueFactory<>("itemName"));
            colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
            colMCategory.setCellValueFactory(new PropertyValueFactory<>("MCategory"));
            colSCategory.setCellValueFactory(new PropertyValueFactory<>("SCategroy"));
            colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
            colMCategoryId.setCellValueFactory(new PropertyValueFactory<>("MCategoryId"));
            colSCategoryId.setCellValueFactory(new PropertyValueFactory<>("SCategoryId"));
            itemTable.setItems(options);
            
            statement.close();
            set.close();
            System.out.println(options);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
    }
    
    
     //table fill with Main category items..
    public void fillMCatgory(){
        Connection conection = stockModel.conection;
        
        try {
            String query = "select item.itemId,item.name,item.brand,MainCategory.qty, MainCategory.price, MainCategory.categoryNo, MainCategory.Category  "
                    + "from MainCategory inner join item on MainCategory.itemId = item.itemId "
                    + "where item.Category = 'yes' and MainCategory.SubCategory = 'no'";
            PreparedStatement statement = conection.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            DecimalFormat df = new DecimalFormat("#.00");
            
            while(set.next()){
                String Price = String.valueOf(df.format(set.getDouble("price")));
                options.add(new stockModel(index,set.getString("name"), set.getString("brand"),set.getString("Category"),"no", set.getInt("qty"), Price,
                    set.getInt("itemId"),set.getInt("categoryNo"),0));
                index++;
            }
            colNo.setCellValueFactory(new PropertyValueFactory<>("no"));
            colItem.setCellValueFactory(new PropertyValueFactory<>("itemName"));
            colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
            colMCategory.setCellValueFactory(new PropertyValueFactory<>("MCategory"));
            colSCategory.setCellValueFactory(new PropertyValueFactory<>("SCategroy"));
            colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
            colMCategoryId.setCellValueFactory(new PropertyValueFactory<>("MCategoryId"));
            colSCategoryId.setCellValueFactory(new PropertyValueFactory<>("SCategoryId"));
            itemTable.setItems(options);
            
            statement.close();
            set.close();
            System.out.println(options);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
    }

    @FXML
    private void onclickAddItem(ActionEvent event) throws IOException {
        FXMLLoader fxml = new FXMLLoader();
        Parent root = fxml.load(getClass().getResource("test.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene); 
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
