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
import java.util.List;
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
public class ModifierProduitController implements Initializable {
    
    @FXML private TextField nomProd;
    @FXML private TextField quantite;
    @FXML private TextField prix;
    @FXML private Button modifier;
    @FXML private Button annuler;
    @FXML private AnchorPane pane;
    @FXML private Label welcome;
    @FXML private ImageView image;
    @FXML private Button btnImage;
    
    ProduitService ps = new ProduitService();
    Produit p;
    List f;
    String path_image;
    

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
                Produit p = new Produit(nomProd.getText(),q,this.path_image,pr);
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
        this.path_image = p.getImage();
        String imageD = p.getImage();
        String  path = imageD.replace("\\", "/");
        Image imageDview = new Image("file:"+path, 110, 110, false, true);
        image.setImage(imageDview);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
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
