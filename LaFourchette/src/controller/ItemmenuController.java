/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import  entities.Plat;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ItemmenuController implements Initializable {

    @FXML
    private ImageView imageview;
    @FXML
    private Text referenceview;
    @FXML
    private Text prixview;
    
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Button btajouterpanier;
    public List<Plat> Listepanier=new ArrayList();

    
     Plat Pl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public List<Plat> getListepanier() {
        return Listepanier;
    }
    void SetData(Plat Pl){
        this.Pl=Pl;
        
      referenceview.setText(Pl.getReference());
        String prix =String.valueOf(Pl.getPrix());
        prixview.setText(prix);
       
       
   String imageE = "file:" + Pl.getImageP();

        Image imageg = new Image(imageE, 110, 110, false, true);

        imageview.setImage(imageg);

  
    }
    
    

    @FXML
    private void ajouterpanieraction(ActionEvent event) {
        
        //this.Listepanier.add(this.Pl);
       // Listepanier.toString();
       this.Listepanier.add(this.Pl);
        FXMLLoader loader = new FXMLLoader();
       loader.setLocation(getClass().getResource("/controller/menucontroller.fxml"));
       menucontroller mc =loader.getController() ;
    mc.setListepanier(this.Listepanier);
        this.Pl.toString();
    }
    
}
