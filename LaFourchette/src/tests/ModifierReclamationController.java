/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import services.ReclamationCRUD;


/**
 * FXML Controller class
 *
 * @author barki
 */
public class ModifierReclamationController implements Initializable {

    @FXML
    private TextField mtypeRec;
    @FXML
    private TextField midU;
    @FXML
    private TextField mdescription;
    @FXML
    private TextField midRec;
    @FXML
    private TextField metatRec;
    
     String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Reclamation t = null ; 
    private boolean update;
    int idRec=1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setText(Reclamation R)
    {
     
       // System.out.println(t.getId());
        String idrec =String.valueOf(t.getIdRec());
        midRec.setText(idrec);
        mtypeRec.setText(t.getTypeRec());
        mdescription.setText(t.getDescription());
        metatRec.setText(t.getEtatRec());
        String idu =String.valueOf(t.getIdU());
        midU.setText(idu);
        R=t;
       
       
       
    }
 
    public void setTextFields(Reclamation R){
        idRec=R.getIdRec();
       midRec.setText(String.valueOf(R.getIdRec()));
        mtypeRec.setText(R.getTypeRec());
        midU.setText(String.valueOf(R.getIdU()));
        mdescription.setText(R.getDescription());
        metatRec.setText(R.getEtatRec());
    }

    @FXML
    private void clearRec(ActionEvent event) {
    }

    @FXML
    private void modifierRec(ActionEvent event) {
          ReclamationCRUD rec= new ReclamationCRUD();
        
        
         Integer idRec=Integer.parseInt(midRec.getText());
         Integer idU=Integer.parseInt(midU.getText());
         String typeRec= mtypeRec.getText();
         String description= mdescription.getText();
         String etatRec= metatRec.getText();
         
         Reclamation R;
           R = new Reclamation(idRec,typeRec,idU,description,etatRec);
           rec.modifierReclamation(R);
             Notifications notificationBuilder = Notifications.create()
                       .title("Réclamation")
                       .text("Réclamation Modifiée")
                       .darkStyle()
                       //.graphic(new ImageView(img))
                       .position(Pos.TOP_CENTER)
                       .hideAfter(javafx.util.Duration.seconds(5));
                       //.graphic(new ImageView(img))
            notificationBuilder.showConfirm();
           Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
           stage.close();
          
    }

    @FXML
    private void clearRec(MouseEvent event) {
        mdescription.clear();
        metatRec.clear();
       
        
    }

   
    
}
