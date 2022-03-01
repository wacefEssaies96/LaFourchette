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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author wacef
 */
public class ListProduitController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private TableView<Produit> tableview;
    @FXML
    private TableColumn<Produit, String> nom_prod;
    @FXML
    private TableColumn<Produit, String> quantite;
    @FXML
    private TableColumn<Produit, String> prix;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    ProduitService ps = new ProduitService();

    /**
     * Initializes the controller class.
     */
    private void refresh(){
        List personnes = ps.afficherListeProduits();
        ObservableList list = FXCollections.observableArrayList(personnes);
        tableview.setItems(list);
        nom_prod.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refresh();
    }
    
    @FXML
    private void ajouter(ActionEvent event){
        try{
            ajouter.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("AjouterProduit.fxml"));
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
        Produit p = tableview.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierProduit.fxml"));
            Parent root = loader.load();
            ModifierProduitController controller = loader.getController();
            controller.setProduit(p);
            modifier.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    @FXML
    private void supprimer(ActionEvent event){
        Produit p = tableview.getSelectionModel().getSelectedItem();
        if(ps.supprimerProduit(p)){
            Alerts.suppressionAlertSuccess();
            refresh();
        }else{
            Alerts.suppressionAlertFail();
        }
    }
    
    

}
