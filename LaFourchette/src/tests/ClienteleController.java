/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.IOException;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ClienteleController implements Initializable {

    @FXML
    private Button evenementC;
     @FXML
    private AnchorPane area;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    private void Loadpage(String page) throws IOException {
        Parent root2=null;
        try{
         root2 = FXMLLoader.load(getClass().getResource(page+".fxml"));
        }catch(Exception ex){
            
        }
        area.setClip(root2);
          
        
        
    }

    @FXML
    private void farah(ActionEvent event) throws IOException {
        Loadpage("EvenementFront");
    }
    
}
