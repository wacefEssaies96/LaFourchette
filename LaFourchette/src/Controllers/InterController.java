/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.util.ResourceBundle;
import static javafx.application.ConditionalFeature.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.Node;
import javax.mail.internet.AddressException;
import javax.swing.JOptionPane;
import utils.MyConnection;
/**
/**
 * FXML Controller class
 *
 * @author pc
 */
public class InterController implements Initializable {

  @FXML
    private Label tunivators1;

    @FXML
    private Button user_button;

    @FXML
    private Button employees_button;
    @FXML
    private Button reservation_btn;
    @FXML
    private Button commande_btn;
    @FXML
    private Button evenement_btn;
    @FXML
    private Button stock_btn;
    @FXML
    private Button reclamation_btn;
    @FXML
    private Button plat;
    
     private void homeTR(MouseEvent event) throws Exception {
        try{
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            Parent root =FXMLLoader.load(getClass().getResource("../GUI/ListeTableFXML.fxml"));
            Scene scene= new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Liste des Tables");
            stage.show();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
     }
    @FXML
    public void click(){
        try{
            employees_button.getScene().getWindow().hide();
                    Parent root2= FXMLLoader.load(getClass().getResource("/tests/NewInterfaces2.fxml"));
                     Scene scene;
                     
                    scene = new Scene(root2);
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
      
                    stage.show();
        }catch(Exception ex){
             System.out.println(ex.getMessage());
        }
    }

    @FXML
     public void click1(){
        try{
            user_button.getScene().getWindow().hide();
                    Parent root1 = FXMLLoader.load(getClass().getResource("/tests/NewInterface.fxml"));
                     Scene scene;
                     
                    scene = new Scene(root1);
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
      
                    stage.show();
        }catch(Exception ex){
             System.out.println(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void clickReservation(ActionEvent event) {
        try{
            employees_button.getScene().getWindow().hide();
                    Parent root2= FXMLLoader.load(getClass().getResource("/GUI/ReservationAdminFXML.fxml"));
                     Scene scene;
                     
                    scene = new Scene(root2);
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
      
                    stage.show();
        }catch(Exception ex){
             System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void clickCommande(ActionEvent event) {
        try{
            employees_button.getScene().getWindow().hide();
                    Parent root2= FXMLLoader.load(getClass().getResource("/Controllers/commande.fxml"));
                     Scene scene;
                     
                    scene = new Scene(root2);
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
      
                    stage.show();
        }catch(Exception ex){
             System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void clickEvenement(ActionEvent event) {
        try{
            employees_button.getScene().getWindow().hide();
                    Parent root2= FXMLLoader.load(getClass().getResource("/GUI/Evenement.fxml"));
                     Scene scene;
                     
                    scene = new Scene(root2);
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
      
                    stage.show();
        }catch(Exception ex){
             System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void clickStock(ActionEvent event) {
        try{
            stock_btn.getScene().getWindow().hide();
                    Parent root2= FXMLLoader.load(getClass().getResource("/GUI/ListProduit.fxml"));
                     Scene scene;
                     
                    scene = new Scene(root2);
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
      
                    stage.show();
        }catch(Exception ex){
             System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void clickReclmation(ActionEvent event) {
         try{
            employees_button.getScene().getWindow().hide();
                    Parent root2= FXMLLoader.load(getClass().getResource("/tests/dashboard_reclamation.fxml"));
                     Scene scene;
                     
                    scene = new Scene(root2);
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
      
                    stage.show();
        }catch(Exception ex){
             System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Settings_btn(ActionEvent event) {
        try{
            employees_button.getScene().getWindow();
                    Parent root2= FXMLLoader.load(getClass().getResource("/tests/Music.fxml"));
                     Scene scene;
                     
                    scene = new Scene(root2);
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.UTILITY);
                    stage.setScene(scene);
      
                    stage.show();
        }catch(Exception ex){
             System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void clickPlat(ActionEvent event) {
        try{
            employees_button.getScene().getWindow().hide();
                    Parent root2= FXMLLoader.load(getClass().getResource("/Controllers/platgui.fxml"));
                     Scene scene;
                     
                    scene = new Scene(root2);
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.UTILITY);
                    stage.setScene(scene);
      
                    stage.show();
        }catch(Exception ex){
             System.out.println(ex.getMessage());
        }
    }
    

    @FXML
    private void déconnecterAdmin(ActionEvent event) throws IOException {
        try {
               Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../tests/NewInterface1.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
           System.out.println(ex.getMessage());
        }
        
      //    Parent root = FXMLLoader.load(getClass().getResource("/test/NewInterface1.fxml"));
       // AdminPanel.setCenter(root);
    }
}
