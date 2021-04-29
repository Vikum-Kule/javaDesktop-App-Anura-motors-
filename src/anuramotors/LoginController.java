/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anuramotors;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.*;

/**
 * FXML Controller class
 *
 * @author Vikum
 */
public class LoginController implements Initializable {
    public loginModel loginModel = new loginModel();

    @FXML
    private TextField txtUserName;
    @FXML
    private Text txtForgotPasss;
    @FXML
    private Button btnSignin;
    @FXML
    private ImageView imgClose;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label warning;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
        if(loginModel.isDbConnected()){
            System.out.println("Connected");
        }
        else{
            System.out.println("Disconnected");
        }
        // TODO
    }    

    @FXML
    private void closeWindow(MouseEvent event) {
       Stage stage = (Stage) imgClose.getScene().getWindow();
        stage.close(); 
    }

    @FXML
    private void login(ActionEvent event)  throws IOException {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        
        
        
        if(userName.equals("")|| password.equals("")){
            warning.setText("Username or Password emplty..");
        }
        else{
            try {
                
                if(loginModel.checkLogin(userName, password)){
                    System.out.println("Login Sucessful..");
                    Stage stage1 = (Stage) btnSignin.getScene().getWindow();
                    stage1.close(); 
                    FXMLLoader fxml = new FXMLLoader();
                    Parent root = fxml.load(getClass().getResource("Home.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setFullScreen(true);
                    stage.setScene(scene); 
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
              
                    
                }
                else{
                    System.out.println("Login Faild..");
                    warning.setText("Login Faild..");
                }
            } catch (SQLException ex) {
                warning.setText("Login Faild..");
                System.out.println("Login Faild..");
            }
        }
    }
    
    public void refreshWarning(){
        warning.setText("");
    }
    
}
