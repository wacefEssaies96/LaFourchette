/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Evenement;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import services.EvenementCrud;
import tests.EListener;

/**
 * FXML Controller class
 *
 * @author user
 */
public class EvenementFrontController implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private TextField recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       EvenementCrud ev = new EvenementCrud();
        Evenement event = new Evenement();
        EListener  Listener;
        List<Evenement>ListeDesEvent = ev.afficherListeEvenement();
        //TextField_ID.setDisable(true);
        
        if (ListeDesEvent.size() > 0) {
            event=ListeDesEvent.get(0);
            //setChosenTable_Resto(ListeDesEvent.get(0));
            Listener = new EListener() {
                @Override
                public void onClickListener(Evenement e) {
                // setChooseEvenement  (e);
                   //naaml stage 
                   
                }
            };
        }
        
        
         int col=0;
       int row=0;
        
       try{
        for (Evenement e : ListeDesEvent){
            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("ItemEvent.fxml"));
            AnchorPane anchorPane = fxmlloader.load();
           
                   ItemEventController itemControler = fxmlloader.getController();
                itemControler.SetData(e);
                
           if(col==2){
              col=0;
              row++;
           }
           grid.add(anchorPane,col++,row);
           //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                grid.setHgap(200);

           GridPane.setMargin(anchorPane, new Insets(10));
        }
       }catch(IOException e){
            e.printStackTrace();
            
        }
      
       
    } 

    @FXML
    private void recherche(KeyEvent event) {
        
        
        grid.getChildren().clear();

        EvenementCrud evcrud = new EvenementCrud();

        List<Evenement>ListeDesEvent = evcrud.afficherListeEvenement();
       ListeDesEvent= ListeDesEvent.stream().filter((e)->e.getDesignationE().toLowerCase().contains(recherche.getText().toLowerCase())).collect(Collectors.toList());
       
        int col=0;
       int row=0;
        
       try{
        
        for (Evenement e : ListeDesEvent){
            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("ItemE.fxml"));
            AnchorPane anchorPane = fxmlloader.load();
           
                   ItemEController itemControler = fxmlloader.getController();
                itemControler.SetData(e);
                
           if(col==2){
              col=0;
              row++;
           }
           grid.add(anchorPane,col++,row);
           //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                grid.setHgap(150);
           GridPane.setMargin(anchorPane, new Insets(10,10,10,10));
        }
       }catch(IOException e){
            e.printStackTrace();
            
        }
        
    }
       
    
}
