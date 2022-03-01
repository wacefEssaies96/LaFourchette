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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.FournisseurService;

/**
 *
 * @author wacef
 */
public class ListFournisseurController implements Initializable {
        @FXML
    private AnchorPane pane;
    @FXML
    private TableView<Fournisseur> tableview;
    @FXML
    private TableColumn<Fournisseur, String> id;
    @FXML
    private TableColumn<Fournisseur, String> nom;
    @FXML
    private TableColumn<Fournisseur, String> tel;
    @FXML
    private TableColumn<Fournisseur, String> email;
    @FXML
    private TableColumn<Fournisseur, String> produits;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button afficheproduit;
    @FXML
    Label label;
    
    FournisseurService fs = new FournisseurService();
    private void refresh(){
        List fournisseur = fs.afficherFournisseur();
        ObservableList list = FXCollections.observableArrayList(fournisseur);
        tableview.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("idF"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nomF"));
        tel.setCellValueFactory(new PropertyValueFactory<>("telephoneF"));
        email.setCellValueFactory(new PropertyValueFactory<>("emailF"));
        //tel.setCellValueFactory(new PropertyValueFactory<>("prix"));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
        label.setText("Liste des fournisseurs");
    }
    @FXML
    private void ajouter(ActionEvent event){
        try{
            ajouter.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("AjouterFournisseur.fxml"));
            Stage mainStage = new Stage();
            Scene scene= new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e);  
        }
    }
     @FXML
    private void modifier(ActionEvent event){
        Fournisseur f = tableview.getSelectionModel().getSelectedItem();
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
    @FXML
    private void supprimer(ActionEvent event){
        Fournisseur f = tableview.getSelectionModel().getSelectedItem();
        if(fs.supprimerFournisseur(f)){
            Alerts.suppressionAlertSuccess();
            refresh();
        }else{
            Alerts.suppressionAlertFail();
        }
    }
    @FXML
    private void afficheProduits(ActionEvent event){
        try{
            afficheproduit.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("ListProduit.fxml"));
            Stage mainStage = new Stage();
            Scene scene= new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e);  
        }
    }
    
}
