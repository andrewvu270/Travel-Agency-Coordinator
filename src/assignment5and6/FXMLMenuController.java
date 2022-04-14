/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5and6;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hoang Vu <vuhoan@sheridancollege.ca> <andrewvu270@gmail.com>
 */
public class FXMLMenuController implements Initializable {
    
    @FXML
    private void openUI(ActionEvent event) throws IOException{
        Parent root1 = FXMLLoader.load(getClass().getResource("FXMLApp.fxml"));
        Stage stage = new Stage();      
        stage.setScene(new Scene(root1));
        stage.setTitle("VUTravel");
        stage.show();
    }
    
    @FXML
    private void aboutUs(ActionEvent event){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About Us");
        alert.setHeaderText("This is VUTravel");
        String s = "We are an award-winning travel agency. "
                + "Our growth and success of our company depends upon fulfilling our clients needs every day. "
                + "We provide info on: \n\n"
                + "¤Destination Name\n¤Duration of Stay\n¤Went With\n¤Year\n¤Comments";
        alert.setContentText(s);
        alert.getDialogPane().setPrefSize(350, 320);
        alert.show();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
