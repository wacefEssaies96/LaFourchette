/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import utils.MyConnection;
import entities.Commentaire; 
import entities.Utilisateur;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * FXML Controller class
 *
 * @author user
 */
public class AjoutcommantaireController implements Initializable {

    @FXML
    private TextField commentaire;
    @FXML
    private Button btnsave;
    @FXML
    private Button btnclear;

    /**
     * Initializes the controller class.
     */
     String query = null;
    Connection cnx;
    PreparedStatement preparedStatement;
   // Commentaire commantaire = null;
    private boolean update;
    
     int idEvent=0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   public void setidEvent(int idEvent)
   {
       this.idEvent=idEvent; 
   }
    @FXML
    
    private void save(MouseEvent event) {
        
        
        cnx = MyConnection.getInstance().getCnx();
        PreparedStatement preparedStatement;
        String name = commentaire.getText();
         try {
             query = "INSERT INTO commentaire(commantaire,idevent,idU) VALUES (?,?,?)";
            preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setString(1, commentaire.getText());
            preparedStatement.setInt(2, this.idEvent);
            preparedStatement.setInt(3,Utilisateur.Current_User.getIdU());
            
            
           
            preparedStatement.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
               
                alert.setTitle("Commentaire");
                alert.setHeaderText(null);
                alert.setContentText("Commentaire ajouté ");
                alert.showAndWait();

        } catch (SQLException ex) {
          System.out.println(ex.getMessage());
        }

      
    }
    @FXML
    private void clear(MouseEvent event) {
         commentaire.setText(null);
    }
    }
    


