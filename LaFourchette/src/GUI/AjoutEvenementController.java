/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Evenement;
import java.io.File;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.EvenementCrud;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjoutEvenementController implements Initializable {

    @FXML
    private AnchorPane EvenementAnchorPane;
    @FXML
    private TextField TextField_ID;
    @FXML
    private TextField TextField_Desig;
    @FXML
    private TextField TextField_DESC_Evenement;
    @FXML
    private TextField TextField_participant;
    @FXML
    private DatePicker DatePicker_Date;
    @FXML
    private Button ajoutEvenement;
    @FXML
    private Label file_path;
    @FXML
    private Button button_inserer_image;
    @FXML
    private ImageView imageview_Evenemnt;
    @FXML
    private Button clear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TextField_ID.setDisable(true);
    }    
public void Notificationmanager(int mode) {
           Notifications not = Notifications.create()      
                 .graphic(null)
                 .hideAfter(Duration.seconds(10))
                 .position(Pos.BOTTOM_RIGHT)
                 .onAction(new EventHandler<ActionEvent>(){
         @Override
         public void handle (ActionEvent event) {
             System.out.println("clicked on notification");
         }
         }) ;
           not.darkStyle();
          switch(mode) {
  case 0:
   
   not.title("Success");
                 not.text("Membre ajouter et notifier par un mail" );
                 not.showInformation();
    break;
  case 1:
    
    not.title("Success ");
                 not.text("Suppression terminée");
                 not.showWarning();
    break;
    case 2:
     
                 not.text("Modification terminée");
                 not.title("Success");
                 not.showInformation();
    break;
    case 3:
     
                 not.text("Evenement crée ");
                 not.title("Success");
                 not.showConfirm();
    break;
    
  default:
   
}  
           
    }
    @FXML
    private void AjouterEvenement(ActionEvent event) {
        
        if (DatePicker_Date.getEditor().getText().isEmpty()
| TextField_Desig.getText().isEmpty()
| TextField_participant.getText().isEmpty()
| TextField_DESC_Evenement.getText().isEmpty()

| imageview_Evenemnt.getImage() == null) {

Alert alert = new Alert(Alert.AlertType.ERROR);

alert.setTitle("Error Message");
alert.setHeaderText(null);
alert.setContentText("Veuillez remplir tous les champs!");
alert.showAndWait();

} else {
   try{String str2 = DatePicker_Date.getValue().toString();
String string = TextField_participant.getText();
int nbparticipant= parseInt(string);
Date datepub1 = Date.valueOf(str2);
EvenementCrud erd= new EvenementCrud();
Evenement e = new Evenement(1,  TextField_Desig.getText(), TextField_DESC_Evenement.getText(),datepub1, file_path.getText(),nbparticipant);
erd.ajouter(e);

Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Alert");
alert.setHeaderText(null);
alert.setContentText("Evenement Ajouté!");
alert.showAndWait();
 //clear();
Notificationmanager(3);
       
   }   catch(NumberFormatException e)
   {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Alert");
alert.setHeaderText(null);
alert.setContentText("Numbre !");
alert.showAndWait();
   }
       

 //Stage stage = (Stage) button_inserer_image.getScene().getWindow();
    // do what you have to do
   // stage.close();   
 }
    }

    @FXML
    private void insertImage(ActionEvent event) {
        FileChooser open = new FileChooser();

        Stage stage = (Stage) EvenementAnchorPane.getScene().getWindow();

        File file = open.showOpenDialog(stage);

        if (file != null) {

            String path = file.getAbsolutePath();

            path = path.replace("\\", "\\\\");

            file_path.setText(path);

            Image image = new Image(file.toURI().toString(), 110, 110, false, true);

            imageview_Evenemnt.setImage(image);

        } else {

            System.out.println("NO DATA EXIST!");

        }
    }

    @FXML
    private void clear(ActionEvent event) {
        

        TextField_ID.setText("");
        DatePicker_Date.getEditor().getText();
        TextField_DESC_Evenement.setText("");
        TextField_Desig.setText("");
        TextField_participant.setText("");
        
        imageview_Evenemnt.setImage(null);
        file_path.setText("");

    }

    }
    

