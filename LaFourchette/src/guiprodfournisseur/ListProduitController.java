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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
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

    @FXML private TableView<Produit> tableview;
    @FXML private TableColumn<Produit, String> nom_prod;
    @FXML private TableColumn<Produit, String> quantite;
    @FXML private TableColumn<Produit, String> prix;
    @FXML private Button ajouter;
    @FXML private Button modifier;
    @FXML private Button supprimer;
    @FXML private Button affichefournisseur;
    @FXML Label label;
    @FXML private AnchorPane pane;
    @FXML private Button recu;
    @FXML private TextField recherche;
    
    ProduitService ps = new ProduitService();

    /**
     * Initializes the controller class.
     */
    private void refresh(){
        label.setText("Liste des produits");
        List produits = ps.afficherListeProduits();
        ObservableList list = FXCollections.observableArrayList(produits);
        tableview.setItems(list);
        nom_prod.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        ObservableList<Produit> dataList = FXCollections.observableArrayList(produits);
        tableview.setItems(dataList);
        FilteredList<Produit> filteredData = new FilteredList<>(dataList,b->true);
        recherche.textProperty().addListener((observable,oldValue,newValue)-> {
            filteredData.setPredicate(rec-> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (rec.getNomProd().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                    return true;
                }
                else
                return false;
            });
        });
        SortedList<Produit> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());
        tableview.setItems(sortedData); 

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
            mainStage.setTitle("Ajouter un produit");
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
        Produit p = tableview.getSelectionModel().getSelectedItem();
        if(p == null){
            Alerts.selectAlertFailControl();
        }
        else{
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
        
    }
    @FXML
    private void supprimer(ActionEvent event){
        Produit p = tableview.getSelectionModel().getSelectedItem();
        if(p == null){
            Alerts.selectAlertFailControl();
        }
        else{
          if(ps.supprimerProduit(p)){
            Alerts.suppressionAlertSuccess();
            refresh();
            }else{
                Alerts.suppressionAlertFail();
            }  
        }
        
    }
    @FXML
    private void afficheFournisseurs(ActionEvent event){
        try{
            affichefournisseur.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("ListFournisseurs.fxml"));
            Stage mainStage = new Stage();
            mainStage.setTitle("Liste des fournisseurs");
            mainStage.setResizable(false);
            Scene scene= new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e);  
        }
    }

    @FXML
    private void genererPdf(ActionEvent event) {
        try{
            recu.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("genererPdf.fxml"));
            Stage mainStage = new Stage();
            mainStage.setTitle("Génerer un reçu");
            mainStage.setResizable(false);
            Scene scene= new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e);  
        }
    }
    
    private void searchProd() {
        nom_prod.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        List produits = ps.afficherListeProduits();
        ObservableList<Produit> dataList = FXCollections.observableArrayList(produits);
        tableview.setItems(dataList);
        FilteredList<Produit> filteredData = new FilteredList<>(dataList,b->true);
        recherche.textProperty().addListener((observable,oldValue,newValue)-> {
            filteredData.setPredicate(rec-> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (rec.getNomProd().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                    return true;
                }
                else
                return false;
            });
        });
        SortedList<Produit> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());
        tableview.setItems(sortedData); 
    }
    
}
