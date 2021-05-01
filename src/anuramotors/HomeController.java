/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anuramotors;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vikum
 */
public class HomeController implements Initializable {
//initialization items in Homepage....
    @FXML
    private StackPane contentArea;// to load pages... according to navigation
    @FXML
    private Button btnHome;
    @FXML
    private Button btnOrder;
    @FXML
    private Button btnStock;
    @FXML
    private Button btnNotification;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnAnalysis;
    @FXML
    private Button btnSettings;
    @FXML
    private ImageView minimize;
    @FXML
    private ImageView maximize;
    @FXML
    private ImageView cancel;
    @FXML
    private Button btnMakeOrder;
    @FXML
    private ImageView btn;
    @FXML
    private HBox titleBar;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //remove all the children and load homepage ..
        try {
            // TODO
            Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
    
    //load make order page
    @FXML
    private void makeOrder(ActionEvent event) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("makeOrder.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root); 
    }

    //load homepage  page
    @FXML
    private void homepage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }
    
    //load order page
    @FXML
    private void orders(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("order.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
        
    }
    
    //minimz window
    @FXML
    private void min(MouseEvent event) {
        Stage s = (Stage)((Node)event.getSource()).getScene().getWindow();
        s.setIconified(true);
        
        
    }

    //maximize window
    @FXML
    private void max(MouseEvent event) {
        Stage s = (Stage)((Node)event.getSource()).getScene().getWindow();
        s.setFullScreen(true);
        
    }
    
    //close homepage..
    @FXML
    private void close(MouseEvent event) {
        Stage s = (Stage)((Node)event.getSource()).getScene().getWindow();
        s.close();
    }

    
    
    
    
    
}
