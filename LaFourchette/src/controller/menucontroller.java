/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Plat;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import service.gestionCommande;
import service.gestionPlat;
import service.snake;
import static test.Launch.stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
 
public class menucontroller implements Initializable {

    @FXML
    private GridPane gridpanecommande;
    @FXML
    private Button btpanier;
    public List<Plat> Listepanier= new ArrayList();
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Button tfvideo;
    @FXML
    private Button btjouer;
     

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
      

    @FXML
    private void panieraction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
//    loader.setLocation(getClass().getResource("/controller/itemmenu.fxml"));
    //  ItemmenuController imc =loader.getController() ;
    //  List<Plat> Lpanier=imc.getListepanier();
             //   Lpanier=gp.getplatlist();
       loader.setLocation(getClass().getResource("/controller/panier.fxml"));
       PanierController pc =loader.getController() ;
          pc.setListepanier(this.Listepanier);
            Parent root = (Parent) loader.load();
     Scene scene =new Scene(root);
     Stage stage =new Stage();
     stage.setScene(scene);
     stage.show();
    
            }

    public void setListepanier(List<Plat> Listepanier) {
        this.Listepanier = Listepanier;
    }

    @FXML
    private void openvideo(ActionEvent event) throws IOException {
   
     FXMLLoader loader = new FXMLLoader(getClass().getResource(
               "/controller/MediaPlayer.fxml"));
            Parent root = (Parent) loader.load();
     Scene scene =new Scene(root);
     Stage stage =new Stage();
     stage.setScene(scene);
     stage.show();
     
    } 

    @FXML
    private void joueraction(ActionEvent event) {
        snake snake0=new snake();
        snake0.start(stage);
        
       
        
    }
}
    

