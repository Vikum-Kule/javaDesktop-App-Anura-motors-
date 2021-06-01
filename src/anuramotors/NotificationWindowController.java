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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vikum
 */
public class NotificationWindowController implements Initializable {

    @FXML
    private Text txtTopic;
    @FXML
    private ImageView imgClose;
    @FXML
    private Text tstMessage;
    @FXML
    private Button btnOk;
    private final MakeOrderController MakeOrderController;
    private final HomeModel HomeModel;

    public NotificationWindowController() {
        this.MakeOrderController = new MakeOrderController();
        this.HomeModel = new HomeModel();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tstMessage.setText(MakeOrderController.note);
    }    

    @FXML
    private void onclickClose(MouseEvent event) {
        Stage stage1 = (Stage) imgClose.getScene().getWindow();
        stage1.close();
    }

    @FXML
    private void onclickOk(ActionEvent event) {
        HomeModel.deleteDataFromTempOrder();
        Stage stage1 = (Stage) btnOk.getScene().getWindow();
        stage1.close();
    }
    
}
