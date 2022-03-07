/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiprodfournisseur;

import entities.Produit;
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
import services.ProduitService;

/**
 *
 * @author wacef
 */
public class ModifierProduitController implements Initializable {
    
    @FXML private TextField nomProd;
    @FXML private TextField quantite;
    @FXML private TextField prix;
    @FXML private Button modifier;
    @FXML private Button annuler;
    
    ProduitService ps = new ProduitService();
    Produit p;
    List f;
    
    private void redirect(){
        try {
           Parent root = FXMLLoader.load(getClass().getResource("ListProduit.fxml"));
            Stage mainStage = new Stage();
            mainStage.setTitle("Liste des produits");
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
        if(nomProd.getText().isEmpty() || quantite.getText().isEmpty() || prix.getText().isEmpty()){
            Alerts.ajoutAlertFailControl();
        }
        else{
            try{
                int q = Integer.parseInt(quantite.getText());
                double pr = Double.parseDouble(prix.getText());
                Produit p = new Produit(nomProd.getText(),q,"",pr);
                if(ps.modifierProduit(p)){
                    Alerts.modifAlertSuccess();
                    ps.verifProduit(p);
                }else{
                    Alerts.modifAlertFail();
                }
            }catch(NumberFormatException e){
                Alerts.modifAlertFailControl();
            }
        }
        modifier.getScene().getWindow().hide();
        this.redirect();
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
    @FXML
    private void annuler(){
        annuler.getScene().getWindow().hide();
        this.redirect();
    } 
}
