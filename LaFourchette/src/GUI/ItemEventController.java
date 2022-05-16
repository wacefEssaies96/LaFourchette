/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Commentaire;
import entities.Evenement;
import GUI.Gmail;
import entities.Utilisateur;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ItemEventController implements Initializable {

    @FXML
    private Text Des;
    @FXML
    private Text Description;
    @FXML
    private ImageView image;
    private Evenement ev;
    @FXML
    private Text date;
    @FXML
    private Button commentaire;
    @FXML
    private AnchorPane tablefrontcommentaire;
    @FXML
    private Label nbrParticipants;
    @FXML
    private Button Participer;

    /**
     * Initializes the controller class.
     */
     String query = null;
    Connection cnx;
    PreparedStatement preparedStatement;
    
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
     }    

    @FXML
    private void commentaire(MouseEvent event) {
        
         
        try {
            FXMLLoader loader  =  new FXMLLoader();
            loader.setLocation(getClass().getResource("../GUI/ajoutcommantaire.fxml"));
           
           System.out.println(this.ev.getIdE());
            Parent root=loader.load();
            AjoutcommantaireController AC= loader.getController();
            AC.setidEvent(this.ev.getIdE());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            //stage.initStyle(StageStyle.UTILITY);
            stage.show();
        
            //Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ItemEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    @FXML
    private void participer(ActionEvent event) {
        cnx = MyConnection.getInstance().getCnx();
        PreparedStatement preparedStatement;
        String name = commentaire.getText();
        
        int nbrp = this.ev.getNbrParticipants();
        
        if(nbrp >= 0){
           try {
               nbrp = this.ev.getNbrParticipants()-1;
               int id = this.ev.getIdE();
                query = "UPDATE evenement  SET  nbrParticipants=? WHERE idE=?";
               preparedStatement = cnx.prepareStatement(query);
               preparedStatement.setInt(1, nbrp);
               preparedStatement.setInt(2, id);
               
               preparedStatement.execute();

           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Alert");
           alert.setHeaderText(null);
           alert.setContentText("Votre participation est confirmée");
           alert.showAndWait();

           Gmail.sendMail(Utilisateur.Current_User.getEmail());
           } catch (SQLException ex) {
             System.out.println(ex.getMessage());
           }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Alert");
           alert.setHeaderText(null);
           alert.setContentText(" Tous les places sont reservé");
           alert.showAndWait();
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
    //}
        
        
    
    } 

