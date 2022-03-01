/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiprodfournisseur;

import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ProduitService;

/**
 *
 * @author wacef
 */
public class ModifierProduitController implements Initializable {
    @FXML
    private TextField nomProd;
    @FXML
    private TextField quantite;
    @FXML
    private TextField prix;
    @FXML
    private Button modifier;
    ProduitService ps = new ProduitService();
    Produit p;
    @FXML
    private void modif(){
        Produit p = new Produit(nomProd.getText(),Integer.parseInt(quantite.getText()),"",Double.parseDouble(prix.getText()));
        if(ps.modifierProduit(p)){
            Alerts.modifAlertSuccess();
        }else{
            Alerts.modifAlertFail();
        }
        modifier.getScene().getWindow().hide();
        try {
           Parent root = FXMLLoader.load(getClass().getResource("ListProduit.fxml"));
            Stage mainStage = new Stage();
            Scene scene= new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setProduit(Produit p){
        this.p = p;
        nomProd.setText(p.getNomProd());
        quantite.setText(String.valueOf(String.valueOf(p.getQuantite())));
        prix.setText(String.valueOf(p.getPrix()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
