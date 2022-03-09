/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Evenement;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.EvenementCrud;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ItemEController implements Initializable {

    @FXML
    private AnchorPane tableback;
    @FXML
    private ImageView image;
    @FXML
    private Text Des;
    @FXML
    private Text Description;
   Evenement ev= null; 
    @FXML
    private Button supprimerBouton;
    @FXML
    private Button modifierEvenement;
    @FXML
    private Text date;
    @FXML
    private Label nbrParticipants;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    void SetData(Evenement ev){
        this.ev=ev; 
      Des.setText(this.ev.getDesignationE());
       Description.setText(this.ev.getDescriptionE());
        date.setText(String.valueOf(this.ev.getDateE()));
       nbrParticipants.setText(String.valueOf(this.ev.getNbrParticipants()));
       
   String imageE = "file:" + this.ev.getImageE();

        Image imageg = new Image(imageE, 110, 110, false, true);

        image.setImage(imageg);

  
    }

    @FXML
    private void modifierEvenement(ActionEvent event)  {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText("Voulez-vous modifier l 'evenement");
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader ();
                           loader.setLocation(getClass().getResource("../GUI/modifierEvenement.fxml"));
                            try {
                                loader.load();
                                
                            } catch (Exception ex) {
                                System.err.println(ex.getMessage());
                            }
                            
                            ModifierEvenementController atc = loader.getController();
                           // atc.setUpdate(true);
                            
                            atc.selectEvenement(ev);
                
                
               // ModifierEvenementController me=new ModifierEvenementController();
                // me.selectEvenement(ev);
                Parent root = loader.getRoot();
//Parent root = FXMLLoader.load(getClass().getResource("../GUI/modifierEvenement.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
            
    }

    @FXML
    private void supprimerEvenement(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
               
               
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Voulez vous supprimer cet evenemnt  ?");

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.get() == ButtonType.OK) {

                EvenementCrud erd= new EvenementCrud();
            
             
               int idE= this.ev.getIdE();
                erd.supprimerEvenement(idE);
//stage.close();
    // do what you have to do
   
            } else {

                return;

                
        
    }
    
}

    @FXML
    private void consulterCommentaire(MouseEvent event) {
          FXMLLoader loader = new FXMLLoader ();
                           loader.setLocation(getClass().getResource("../GUI/CommentaireView.fxml"));
                            try {
                                loader.load();
                                
                            } catch (Exception ex) {
                                System.err.println(ex.getMessage());
                            }
                         
                            CommentaireViewController atc = loader.getController();
                            
atc.SetData(ev.getIdE());
      
                Parent root = loader.getRoot();
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
    
    }
}