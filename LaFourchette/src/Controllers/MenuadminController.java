/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Plat;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import services.gestionPlat;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
 
public class MenuadminController implements Initializable {

    @FXML
    private GridPane gridpanecommande;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
          
           try{
               
               
               int col=0;
               int row=0;
               gestionPlat gp = new gestionPlat();
               
               List<Plat>ListeDesplat = gp.getplatlist();
               try{
                   for (Plat Pl : ListeDesplat){
                       FXMLLoader fxmlloader = new FXMLLoader();
                       fxmlloader.setLocation(getClass().getResource("itemmenu.fxml"));
                       AnchorPane anchorPane = fxmlloader.load();
                       
                       ItemmenuController itemControler = fxmlloader.getController();
                       itemControler.SetData(Pl);
                       if(col==3){
                           col=0;
                           row++;
                       }
                       gridpanecommande.add(anchorPane,col++,row);
                       GridPane.setMargin(anchorPane, new Insets(10));
                   }
               }catch(IOException e){
                   e.printStackTrace();
                   
               }
               
               
           }catch(Exception ex){
               Logger.getLogger(menucontroller.class.getName()).log(Level.SEVERE, null, ex);
               
           }
       
            
        }
    }    
