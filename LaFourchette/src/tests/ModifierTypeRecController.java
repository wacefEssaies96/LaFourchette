/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entities.TypeReclamation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import services.TypeReclamationCRUD;

/**
 * FXML Controller class
 *
 * @author barki
 */
public class ModifierTypeRecController implements Initializable {

    @FXML
    private TextField motypeRec;

    @FXML
    private TextField mrefT;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
     public void setTextFields(TypeReclamation TR){
       
        motypeRec.setText(TR.getTypeRec());
        mrefT.setText(TR.getRefT());
    }
    @FXML
    void clearTypeRec(ActionEvent event) {
        mrefT.clear();
        motypeRec.clear();

    }

    @FXML
    void modifierTypeRec(ActionEvent event) {
         TypeReclamationCRUD rec= new TypeReclamationCRUD();
         String typeRec= motypeRec.getText();
         String refT= mrefT.getText();
        
         
         TypeReclamation TR;
           TR = new TypeReclamation(typeRec,refT);
           rec.modifierTypeReclamation(TR);
             Notifications notificationBuilder = Notifications.create()
                       .title("Type Réclamation")
                       .text("Type Réclamation Modifiée")
                       .darkStyle()
                       //.graphic(new ImageView(img))
                       .position(Pos.TOP_CENTER)
                       .hideAfter(javafx.util.Duration.seconds(5));
                       //.graphic(new ImageView(img))
            notificationBuilder.showConfirm();
           Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
           stage.close();

    }
    
}
