/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiprodfournisseur;

import entities.Fournisseur;
import entities.Produit;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.FournisseurService;
import services.ProduitService;

/**
 *
 * @author wacef
 */
public class AjoutFournisseurController implements Initializable {
       
    @FXML
    private TextField nom;
    @FXML
    private TextField tel;
    @FXML
    private TextField email;
    @FXML
    private Button ajouter;
    
    FournisseurService fs = new FournisseurService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void insert(ActionEvent event) {
        Fournisseur f = new Fournisseur(nom.getText(),Integer.parseInt(tel.getText()),email.getText());
        if(fs.ajouterFournisseur(f)){
            Alerts.ajoutAlertSuccess();
        }else{
            Alerts.ajoutAlertFail();
        }
        ajouter.getScene().getWindow().hide();
        try {
           Parent root = FXMLLoader.load(getClass().getResource("ListFournisseurs.fxml"));
            Stage mainStage = new Stage();
            Scene scene= new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
