/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiprodfournisseur;

import entities.Fournisseur;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.FournisseurService;

/**
 *
 * @author wacef
 */
public class ModifierFournisseurController implements Initializable {

       @FXML
    private TextField nom;
    @FXML
    private TextField tel;
    @FXML
    private TextField email;
    @FXML
    private Button modifier;
    
    FournisseurService fs = new FournisseurService();
    Fournisseur f;
    List l;
    @FXML
    private void modif(){
        Fournisseur f = new Fournisseur(this.f.getIdF(),nom.getText(),Integer.parseInt(tel.getText()),email.getText());
        if(fs.modifierFournisseur(f)){
            Alerts.modifAlertSuccess();
        }else{
            Alerts.modifAlertFail();
        }
        modifier.getScene().getWindow().hide();
        try {
           Parent root = FXMLLoader.load(getClass().getResource("ListFournisseurs.fxml"));
            Stage mainStage = new Stage();
            Scene scene= new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setFournisseur(Fournisseur f){
        this.f = f;
        nom.setText(f.getNomF());
        tel.setText(String.valueOf(f.getTelephoneF()));
        email.setText(String.valueOf(f.getEmailF()));
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    
}
