/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Fournisseur;
import entities.ProduitFournisseur;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.FournisseurService;
import services.ProduitFournisseurService;

/**
 *
 * @author wacef
 */
public class ListFournisseurController implements Initializable {

    @FXML private TableView tableview;
    @FXML private TableColumn<Fournisseur, String> id;
    @FXML private TableColumn<Fournisseur, String> nom;
    @FXML private TableColumn<Fournisseur, String> tel;
    @FXML private TableColumn<Fournisseur, String> email;
    @FXML private TableColumn<ProduitFournisseur, String> produits;
    @FXML private Button ajouter;
    @FXML private Button modifier;
    @FXML private Button supprimer;
    @FXML private Button afficheproduit;
    @FXML private Button ajoutProduit;
    @FXML Label label;
    
    FournisseurService fs = new FournisseurService();
    ProduitFournisseurService pfs = new ProduitFournisseurService();
    
    private void refresh(){
        List fournisseur = fs.afficherListFournisseur();
        ObservableList list = FXCollections.observableArrayList(fournisseur);
        tableview.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("idF"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nomF"));
        tel.setCellValueFactory(new PropertyValueFactory<>("telephoneF"));
        email.setCellValueFactory(new PropertyValueFactory<>("emailF"));
        produits.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
        
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
        label.setText("Liste des fournisseurs");
    }
    @FXML
    private void ajoutProd(){
        Fournisseur f = (Fournisseur) tableview.getSelectionModel().getSelectedItem();
        if(f == null)
            Alerts.selectAlertFailControl();
        else{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterFournisseurProd.fxml"));
                Parent root = loader.load();
                AjouterFournisseurProdController controller = loader.getController();
                controller.setFournisseur(f);
                ajoutProduit.getScene().setRoot(root);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } 
    }
    @FXML
    private void suppProd(ActionEvent event){
        ProduitFournisseur pf = (ProduitFournisseur) tableview.getSelectionModel().getSelectedItem();
        if(pf == null){
            Alerts.selectAlertFailControl();
        }
        else{
            if(pfs.supprimerProduitFournisseur(pf)){
                Alerts.suppressionAlertSuccess();
                refresh();
            }else{
                Alerts.suppressionAlertFail();
            }  
        }
    }
    @FXML
    private void ajouter(ActionEvent event){
        try{
            ajouter.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("AjouterFournisseur.fxml"));
            Stage mainStage = new Stage();
            mainStage.setTitle("Ajouter un fournisseur");
            mainStage.setResizable(false);
            Scene scene= new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e);  
        }
    }
     @FXML
    private void modifier(ActionEvent event){
        Fournisseur f = (Fournisseur) tableview.getSelectionModel().getSelectedItem();
        if(f == null)
            Alerts.selectAlertFailControl();
        else{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierFournisseur.fxml"));
                Parent root = loader.load();
                ModifierFournisseurController controller = loader.getController();
                controller.setFournisseur(f);
                modifier.getScene().setRoot(root);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }    
    }
    @FXML
    private void supprimer(ActionEvent event){
        Fournisseur f = (Fournisseur) tableview.getSelectionModel().getSelectedItem();
        if(f == null){
            Alerts.selectAlertFailControl();
        }
        else{
            if(fs.supprimerFournisseur(f)){
                Alerts.suppressionAlertSuccess();
                refresh();
            }else{
                Alerts.suppressionAlertFail();
            }  
        }
    }
    @FXML
    private void afficheProduits(ActionEvent event){
        try{
            afficheproduit.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("ListProduit.fxml"));
            Stage mainStage = new Stage();
            mainStage.setTitle("Liste des produits");
            mainStage.setResizable(false);
            Scene scene= new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e);  
        }
    }
    
}
