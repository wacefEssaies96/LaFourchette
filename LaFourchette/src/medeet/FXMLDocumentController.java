/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medeet;

import entities.Reclamation;
import entities.TypeReclamation;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Notifications;
import services.ReclamationCRUD;
import utils.MyConnection;



/**
 *
 * @author barki
 */
public class FXMLDocumentController implements Initializable {
    Connection cnx;
    @FXML
    private Label label;
    private Button ajouter_réclamation;
    @FXML
    private TextField tfidU;
    @FXML
    private TextField tfetatRec;
    @FXML
    private TextArea tdescription;
    @FXML
    private ComboBox<String> cbtypeRec;
    
    //ReclamationCRUD rt =new ReclamationCRUD();
    ObservableList<String> list = FXCollections.observableArrayList();
  // ObservableList<String> list = FXCollections.observableArrayList("Réclamation event","Réclamation plat","Réclamation service","Réclamation technique","Réclamation commande","Réclamation livraison","Réclamation paiemenet");
    ObservableList<String> listrec = FXCollections.observableArrayList();
  //  ObservableList<String> list = FXCollections.observableArrayList(rt.joinRec());
    @FXML
    private Button Clear;
   
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listrec = FXCollections.observableArrayList();
        cnx = MyConnection.getInstance().getCnx();
        listrec = FXCollections.observableArrayList();
         String requete = "SELECT typeRec FROM type_rec";
        try {
           
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
             listrec.add(rs.getString("typeRec"));
             
            }
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        
     
        cbtypeRec.setItems(listrec);
        System.out.println(listrec);
    }    

    @FXML
    private void ajouter_réclamation(ActionEvent event) {
      /*  ReclamationCRUD R = new ReclamationCRUD() ;
        Reclamation rec= new Reclamation();
        rec.setTypeRec(String.valueOf(cbtypeRec.getValue()));
        rec.setDescription(tdescription.getText());
        rec.setEtatRec(tfetatRec.getText());
        String idu = tfidU.getText();
        rec.setIdU(Integer.parseInt(idu));
        R.ajouterReclamation(rec);*/
            String query = "INSERT INTO reclam (typeRec,idU,description,etatRec) VALUES (?,?,?,?)";
            cnx = MyConnection.getInstance().getCnx();
        try{
           
            if(  tdescription.getText().isEmpty() & tfidU.getText().isEmpty() & cbtypeRec.getSelectionModel().isEmpty()){
                tdescription.lookup(".content").setStyle("-fx-border-color:#ef451b;"+"-fx-background-color:#fff5f0;");
                tfidU.setStyle("-fx-border-color:#ef451b;"+"-fx-background-color:#fff5f0;");
                cbtypeRec.setStyle("-fx-border-color:#ef451b;"+"-fx-background-color:#fff5f0;");
                Alert alert = new Alert(AlertType.ERROR);
               
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs !");
                alert.showAndWait();
                
            }else if (cbtypeRec.getSelectionModel().isEmpty() & tdescription.getText().isEmpty()) {
                tdescription.lookup(".content").setStyle("-fx-border-color:#ef451b;"+"-fx-background-color:#fff5f0;");
                cbtypeRec.setStyle("-fx-border-color:#ef451b;"+"-fx-background-color:#fff5f0;");
                tfidU.setStyle("-fx-border-color:#C3C3C3;"+"-fx-background-color:#F3F1F0;");
                Alert alert = new Alert(AlertType.ERROR);
               
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir le champ Id et le champ Description !");
                alert.showAndWait();
                
                   
            }else if (cbtypeRec.getSelectionModel().isEmpty() & tfidU.getText().isEmpty()) {
                cbtypeRec.setStyle("-fx-border-color:#ef451b;"+"-fx-background-color:#fff5f0;");
                tfidU.setStyle("-fx-border-color:#ef451b;"+"-fx-background-color:#fff5f0;");
                tdescription.lookup(".content").setStyle("-fx-border-color:#C3C3C3;"+"-fx-background-color:#F3F1F0;");
                Alert alert = new Alert(AlertType.ERROR);
               
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir le champ Id et le champ Description !");
                alert.showAndWait();
                
                   
            }else if (tdescription.getText().isEmpty() & tfidU.getText().isEmpty()) {
                tdescription.lookup(".content").setStyle("-fx-border-color:#ef451b;"+"-fx-background-color:#fff5f0;");
                tfidU.setStyle("-fx-border-color:#ef451b;"+"-fx-background-color:#fff5f0;");
                cbtypeRec.setStyle("-fx-border-color:#C3C3C3;"+"-fx-background-color:#F3F1F0;");
                Alert alert = new Alert(AlertType.ERROR);
               
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir le champ Id et le champ Description !");
                alert.showAndWait();
                
                   
            }else if (tdescription.getText().isEmpty()) {
                tdescription.lookup(".content").setStyle("-fx-border-color:#ef451b;"+"-fx-background-color:#fff5f0;");
                cbtypeRec.setStyle("-fx-border-color:#C3C3C3;"+"-fx-background-color:#F3F1F0;");
                tfidU.setStyle("-fx-border-color:#C3C3C3;"+"-fx-background-color:#F3F1F0;");
                Alert alert = new Alert(AlertType.ERROR);
               
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir le champ description !");
                alert.showAndWait();
                
                   
            }else if (tfidU.getText().isEmpty()){
                tfidU.setStyle("-fx-border-color:#ef451b;"+"-fx-background-color:#fff5f0;");
                cbtypeRec.setStyle("-fx-border-color:#C3C3C3;"+"-fx-background-color:#F3F1F0;");
                tdescription.lookup(".content").setStyle("-fx-border-color:#C3C3C3;"+"-fx-background-color:#F3F1F0;");
                Alert alert = new Alert(AlertType.ERROR);
               
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir le champ Id !");
                alert.showAndWait();
               
            }else if (cbtypeRec.getSelectionModel().isEmpty()){
                cbtypeRec.setStyle("-fx-border-color:#ef451b;"+"-fx-background-color:#fff5f0;");
                tdescription.lookup(".content").setStyle("-fx-border-color:#C3C3C3;"+"-fx-background-color:#F3F1F0;");
                tfidU.setStyle("-fx-border-color:#C3C3C3;"+"-fx-background-color:#F3F1F0;");
                Alert alert = new Alert(AlertType.ERROR);
               
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez choisir un type de réclamation !");
                alert.showAndWait();
            }else {
                PreparedStatement pst = cnx.prepareStatement(query);
           pst.setString(1, (String) cbtypeRec.getSelectionModel().getSelectedItem());
           //String idu = tfidU.getText();
          
            //int iduu= Integer.parseInt(idu);
            pst.setString(2, tfidU.getText());
            pst.setString(4, tfetatRec.getText());
            pst.setString(3, tdescription.getText());
            System.out.println("succé");
            pst.executeUpdate();
            //Image img = new Image ("/medeet.images/succés.png");
            Notifications notificationBuilder = Notifications.create()
                       .title("Réclamation")
                       .text("Réclamation envoyée")
                       .darkStyle()
                       //.graphic(new ImageView(img))
                       .position(Pos.TOP_CENTER)
                       .hideAfter(javafx.util.Duration.seconds(5));
                       //.graphic(new ImageView(img))
            notificationBuilder.showConfirm();
                
            }
        }catch(Exception e){
        System.out.println(e.getMessage());
        }
       
    }
  

    @FXML
    private void Clear_réclamation(ActionEvent event) {
         tfidU.clear();
         tfetatRec.clear();
         tdescription.clear();
    }

     private void remplirTypeRec(){
      listrec = FXCollections.observableArrayList();
         String requete = "SELECT typeRec FROM typeRec";
        try {
           
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
             listrec.add(rs.getString("typeRec"));
             
            }
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        
      cbtypeRec.setItems(null);
      cbtypeRec.setItems(listrec);
    }
    
}
