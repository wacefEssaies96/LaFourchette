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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.FournisseurService;

/**
 *
 * @author wacef
 */
public class ModifierFournisseurController implements Initializable {

    @FXML private TextField nom;
    @FXML private TextField tel;
    @FXML private TextField email;
    @FXML private Button modifier;
    @FXML private Button annuler;
    @FXML private Label welcome;
    @FXML private ComboBox<Integer> level;
    
    FournisseurService fs = new FournisseurService();
    Fournisseur f;
    List l;
    ObservableList<Integer> options = FXCollections.observableArrayList(0,1,2,3,4);
    private void redirect(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ListFournisseurs.fxml"));
            Stage mainStage = new Stage();
            mainStage.setTitle("Liste des fournisseurs");
            mainStage.setResizable(false);
            Scene scene= new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void modif(){
        if(nom.getText().isEmpty() || tel.getText().isEmpty() || email.getText().isEmpty() || level.getSelectionModel().isEmpty()){
            Alerts.ajoutAlertFailControl();
        }
        else{
            try{
                int t = Integer.parseInt(tel.getText());
                Fournisseur f = new Fournisseur(this.f.getIdF(),nom.getText(),t,email.getText(),level.getValue());
                if(fs.modifierFournisseur(f)){
                    Alerts.modifAlertSuccess();
                }else{
                    Alerts.modifAlertFail();
                } 
            }catch(NumberFormatException e){
                Alerts.modifAlertFailControl();
            }
            modifier.getScene().getWindow().hide();
            this.redirect();
        }   
    }
    public void setFournisseur(Fournisseur f){
        this.f = f;
        nom.setText(f.getNomF());
        tel.setText(String.valueOf(f.getTelephoneF()));
        email.setText(String.valueOf(f.getEmailF()));
        level.setValue(f.getLvl());
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        level.setItems(options);
    }
    @FXML
    private void annuler(){
        annuler.getScene().getWindow().hide();
        this.redirect();
    } 
    
}
