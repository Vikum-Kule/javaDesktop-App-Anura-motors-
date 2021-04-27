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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Vikum
 */
public class HomeController implements Initializable {

    @FXML
    private StackPane contentArea;
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

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }

    @FXML
    private void homepage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    @FXML
    private void orders(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("order.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    
    
    
    
}
