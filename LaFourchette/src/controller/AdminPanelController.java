
package controller;

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
import javafx.scene.layout.BorderPane;

public class AdminPanelController implements Initializable {

    @FXML
    private BorderPane AdminPanel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

          try {
            Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));            
            AdminPanel.setCenter(root);
            
        } catch (IOException ex) {
            Logger.getLogger(AdminPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    
    @FXML
    private void AddFoodAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("platgui.fxml"));
        AdminPanel.setCenter(root);
    }

    @FXML
    private void menuAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        AdminPanel.setCenter(root);
        
    }
    @FXML
   
    

    
    
    private void commandeaction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("commande.fxml"));
        AdminPanel.setCenter(root);
        
    }
    
   
/*
    @FXML
    private void FoodItemAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FoodItemPanel.fxml"));
        AdminPanel.setCenter(root);
    }

    @FXML
    private void AddEmployeeAction(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("EmployeePanel.fxml"));
        AdminPanel.setCenter(root);
    }

    @FXML
    private void AllEmployeeAction(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("AllEmployee.fxml"));
        AdminPanel.setCenter(root);
    }

    @FXML
    private void StatisticsAction(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("Statistics.fxml"));
        AdminPanel.setCenter(root);
    }

    @FXML
    private void AddUserAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddApplicationUser.fxml"));
        AdminPanel.setCenter(root);
    }
    */

   
    

    



    @FXML
    private void AllEmployeeAction(ActionEvent event) {
    }

    @FXML
    private void StatisticsAction(ActionEvent event) {
    }

    @FXML
    private void AddUserAction(ActionEvent event) {
    }

    
}
