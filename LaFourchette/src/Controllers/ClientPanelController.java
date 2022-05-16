/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
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
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ClientPanelController implements Initializable {

    @FXML
    private BorderPane AdminPanel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddFoodAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/EvenementFront.fxml"));
        AdminPanel.setCenter(root);
    }

    @FXML
    private void menuAction(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/tests/FXMLDocument.fxml"));
        AdminPanel.setCenter(root);
    }

    @FXML
    private void commandeaction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Controllers/menu.fxml"));
        AdminPanel.setCenter(root);
    }

    @FXML
    private void réserverClient(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/MesReservationFXML.fxml"));
        AdminPanel.setCenter(root);
    }

    @FXML
    private void déconnecterClient(ActionEvent event) throws IOException {
        try {
               Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../tests/NewInterface1.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
           System.out.println(ex.getMessage());
        }
        
      //    Parent root = FXMLLoader.load(getClass().getResource("/test/NewInterface1.fxml"));
       // AdminPanel.setCenter(root);
    }
    
}


   
    

    


    
    

