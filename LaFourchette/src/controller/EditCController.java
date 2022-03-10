/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Plat;
import entities.commande;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.gestionCommande;
import service.gestionPlat;


/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class EditCController implements Initializable {
    boolean flag = false;
    private TextField tfreferenceedit;
    private TextField tfdesignationedit;
    private TextField tfprixedit;
    private TextField tfdescriptionedit;
    private TextField tfproduitsedit;
    @FXML
    private Button editbutton;
    private ImageView imageviewedit;
    private Label imagepath;
    commande P=null;
    @FXML
    private BorderPane BorderPane;
@FXML
    private TextField tfidU;
    @FXML
    private TextField tfetatC;
    @FXML
    private TextField tflivraison;
    @FXML
    private TextField tfprixC;
    @FXML
    private TextField tfidC;
    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    
    
    public void setText(commande t) 
    {
      
       // System.out.println(t.getId());
         String idC =String.valueOf(t.getIdC());
         
        tfidC.setText(idC);
        String idU =String.valueOf(t.getIdU());
         tfidU.setText(idU);
       
         tfetatC.setText(t.getEtatC());
          tflivraison.setText(t.getLivraison());
        
       String prix =String.valueOf(t.getPrixC());
        tfprixC.setText(prix);
        
        
       
        
      P=t;
       
        
       
    }
    
    @FXML
    private void modifierFormateur(ActionEvent event) throws SQLException, IOException {
        gestionCommande p =new gestionCommande();
         
         int idU = Integer.parseInt(tfidU.getText());
          int idC = Integer.parseInt(tfidC.getText());
       
     
           double prixC = Double.parseDouble(tfprixC.getText());
        String etatC= tfetatC.getText();
         String livraison= tflivraison.getText() ;
         
         
                   
         commande c;
           c = new commande(idC,idU,etatC,livraison,prixC) ;
           p.modifier(c);
           
          
       
            


        
    }

    @FXML
    private void onCancel(ActionEvent event) {
    }

   
    
    
    
}
