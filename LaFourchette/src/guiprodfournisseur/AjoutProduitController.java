/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiprodfournisseur;

import entities.Produit;
import java.io.File;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ProduitService;

/**
 *
 * @author wacef
 */
public class AjoutProduitController implements Initializable {
       
    @FXML private TextField nomProd;
    @FXML private TextField quantite;
    @FXML private TextField prix;
    @FXML private Button ajouter;
    @FXML private Button annuler;
    @FXML private Label welcome;
    @FXML private ImageView image;
    @FXML private Button btnImage;
    @FXML private AnchorPane pane;
    
    String path_image;
    
    ProduitService ps = new ProduitService();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    private void redirect(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ListProduit.fxml"));
            Stage mainStage = new Stage();
            mainStage.setTitle("Liste des Produits");
            mainStage.setResizable(false);
            Scene scene= new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
             Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void insert(ActionEvent event) {
        if(nomProd.getText().isEmpty() || quantite.getText().isEmpty() || prix.getText().isEmpty()){
            Alerts.ajoutAlertFailControl();
        }
        else{
            try{
                int q = Integer.parseInt(quantite.getText());
                double pr = Double.parseDouble(prix.getText());
                Produit p = new Produit(nomProd.getText(),q,this.path_image,pr);
                if(ps.ajouterProduit(p)){
                    Alerts.ajoutAlertSuccess();
                }else{
                    Alerts.ajoutAlertFail();
                }
            }catch(NumberFormatException e){
                Alerts.ajoutAlertFailControl();
            }
            ajouter.getScene().getWindow().hide();
            this.redirect();
        }
    }
    @FXML
    private void annuler(){
        annuler.getScene().getWindow().hide();
        this.redirect();
    }   

    @FXML
    private void insertImage(ActionEvent event) {
        FileChooser open = new FileChooser();
        Stage stage = (Stage) pane.getScene().getWindow();
        File file = open.showOpenDialog(stage);
        if (file != null) {
            this.path_image = file.getAbsolutePath();
            Image img = new Image(file.toURI().toString(), 110, 110, false, true);
            image.setImage(img);
        } else {
            System.out.println("NO DATA EXIST!");
        }
    }
}
