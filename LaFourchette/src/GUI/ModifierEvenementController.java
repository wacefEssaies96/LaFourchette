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
public class ModifierEvenementController implements Initializable {

    @FXML
    private AnchorPane EvenementAnchorPane;
    @FXML
    private Label file_path;
    @FXML
    private Button button_inserer_image;
    @FXML
    private ImageView imageview_Evenemnt;
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
    int idE=0;
    @FXML
    private Button ModifierEvenement;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        private void ModifierEvenement(ActionEvent event) {
         
        if (DatePicker_Date.getEditor().getText().isEmpty()
                | TextField_Desig.getText().isEmpty()
                | TextField_participant.getText().isEmpty()
                | TextField_DESC_Evenement.getText().isEmpty()
                | TextField_ID.getText().isEmpty()
                | imageview_Evenemnt.getImage() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs!");
            alert.showAndWait();


            } else {
            
            String str2 = DatePicker_Date.getValue().toString();
             String string = TextField_participant.getText();
             int nbparticipant= Integer.parseInt(string);
             String string2=TextField_ID.getText();
             int id=parseInt(string2);
            Date datepub1 = Date.valueOf(str2);
            EvenementCrud erd= new EvenementCrud(); 
            Evenement e = new Evenement(id,  TextField_Desig.getText(), TextField_DESC_Evenement.getText(),datepub1, file_path.getText(),nbparticipant
);       
           
           
      
      e.setIdE(idE);
            erd.modifierEvenement(e);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText("Evenement Modifié!");
                alert.showAndWait();
      
                ///AffichageEvenement();
               // clear();
                Notificationmanager(2);
              Notificationmanager(2);
              //Stage stage = (Stage) button_inserer_image.getScene().getWindow();
    // do what you have to do
   // stage.close();   
            }
        
        
        
        
        
    }
    public void selectEvenement(Evenement e)
    {
//        //TextField_ID.setText(String.valueOf(e.getIdE()));
         idE=e.getIdE();
         TextField_ID.setText(String.valueOf(e.getIdE()));
        TextField_Desig.setText(e.getDesignationE());
        TextField_DESC_Evenement.setText(e.getDescriptionE());
        DatePicker_Date.getEditor().setText(String.valueOf(e.getDateE()));
        
        TextField_participant.setText(String.valueOf(e.getNbrParticipants()));

        String imageE = "file:" + e.getImageE();

        Image image = new Image(imageE, 110, 110, false, true);

     imageview_Evenemnt.setImage(image);

        String path = e.getImageE();

       file_path.setText(path);
 

        
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

    
   
    
}
