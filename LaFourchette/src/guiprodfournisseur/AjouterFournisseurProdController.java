/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiprodfournisseur;

import entities.Fournisseur;
import entities.Produit;
import entities.ProduitFournisseur;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ProduitFournisseurService;
import services.ProduitService;

/**
 *
 * @author wacef
 */
public class AjouterFournisseurProdController implements Initializable {
    @FXML private TextField nom;
    @FXML private TextField tel;
    @FXML private TextField email;
    @FXML private Button ajouter;
    @FXML private Button annuler;    
    @FXML private TableView<Produit> tableview;
    @FXML private TableColumn<Produit, String> nom_prod;
    
    Fournisseur f;
    ProduitService ps = new ProduitService();
    ProduitFournisseurService pfs = new ProduitFournisseurService();
    
    public void setFournisseur(Fournisseur f){
        this.f = f;
        nom.setText(this.f.getNomF());
        tel.setText(String.valueOf(this.f.getTelephoneF()));
        email.setText(String.valueOf(this.f.getEmailF()));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List listProd = ps.afficherListeProduits();
        ObservableList list = FXCollections.observableArrayList(listProd);
        tableview.setItems(list);
        nom_prod.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
    }
    
    @FXML
    private void insert(ActionEvent event){
        Produit p = tableview.getSelectionModel().getSelectedItem();
        if(p == null)
            Alerts.selectAlertFailControl();
        else{
            ProduitFournisseur pp = new ProduitFournisseur(p.getNomProd(),this.f.getIdF());
            if(pfs.ajouterProduitFournisseur(pp)){
                    Alerts.ajoutAlertSuccess();
                }else{
                    Alerts.ajoutAlertFail();
                }
            ajouter.getScene().getWindow().hide();
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
    }
    
    @FXML
    private void annuler(ActionEvent event){
        annuler.getScene().getWindow().hide();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ListFournisseurs.fxml"));
            Stage mainStage = new Stage();
            mainStage.setTitle("Liste des fournisseur");
            mainStage.setResizable(false);
            Scene scene= new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
