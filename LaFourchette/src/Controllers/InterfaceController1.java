/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Utilisateur;
import java.awt.event.MouseEvent;
import java.io.File;
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
import javax.mail.internet.AddressException;
import utils.MyConnection;
/**
 * FXML Controller class
 *
 * @author pc
 */
public class InterfaceController1 implements Initializable {

    @FXML
    private AnchorPane login_F;
    @FXML
    private Button login_btn;
    @FXML
    private Hyperlink create_acc;
    @FXML
    private TextField nom_prenom;
    @FXML
    private TextField password;
    @FXML
    private Label tunivators;
    @FXML
    private AnchorPane signup_form;
    @FXML
    private Label tunivators1;
    @FXML
    private TextField su_nom_prenom;
    @FXML
    private TextField su_email;
    @FXML
    private PasswordField su_password;
    @FXML
    private Button signup_btn;
    @FXML
    private Hyperlink login_acc;
    @FXML
    private ComboBox<String> su_role;
    @FXML
    private TextField su_adresse;

    @FXML
    private TextField su_tel;
    @FXML
    private Label file_path;

    @FXML
    private ImageView image_view;

    @FXML
    private Button insert_image;
    @FXML
    private AnchorPane left_main;
    
    /**
     * Initializes the controller class.
     */
   Connection cnx;
    @FXML
    private Hyperlink pass;
   
    @FXML
     public void textfieldDesign(){
        if(nom_prenom.isFocused()) {
            nom_prenom.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-radius:2px");
            password.setStyle("-fx-background-color:transparent;" +
                    "-fx-border-radius:1px");
        } else if(password.isFocused()){
            password.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-radius:2px");
            nom_prenom.setStyle("-fx-background-color:transparent;" +
                    "-fx-border-radius:1px");
        }
    }
    @FXML
        public void textfieldDesign1(){
        if(su_nom_prenom.isFocused()) {
            su_nom_prenom.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-radius:2px");
            su_nom_prenom.setStyle("-fx-background-color:transparent;" +
                    "-fx-border-radius:1px");
               su_email.setStyle("-fx-background-color:transparent;" +
                    "-fx-border-radius:1px");
               su_adresse.setStyle("-fx-background-color:transparent;" +
                    "-fx-border-radius:1px");
               su_tel.setStyle("-fx-background-color:transparent;" +
                    "-fx-border-radius:1px");
        } else if(su_email.isFocused()){
           su_nom_prenom.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-radius:1px");
            su_password.setStyle("-fx-background-color:transparent;" +
                    "-fx-border-radius:1px");
               su_email.setStyle("-fx-background-color:#fff;" +
                    "-fx-border-radius:2px");
                su_adresse.setStyle("-fx-background-color:transparent;" +
                    "-fx-border-radius:1px");
               su_tel.setStyle("-fx-background-color:transparent;" +
                    "-fx-border-radius:1px");
        }else if(su_password.isFocused()){
            su_nom_prenom.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-radius:1px");
            su_password.setStyle("-fx-background-color:#fff;" +
                    "-fx-border-radius:2px");
               su_email.setStyle("-fx-background-color:transparent;" +
                    "-fx-border-radius:1px");
                su_adresse.setStyle("-fx-background-color:transparent;" +
                    "-fx-border-radius:1px");
               su_tel.setStyle("-fx-background-color:transparent;" +
                    "-fx-border-radius:1px");
            
        }else if(su_adresse.isFocused()){
            su_nom_prenom.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-radius:1px");
            su_password.setStyle("-fx-background-color:transparent;" +
                    "-fx-border-radius:1px");
               su_email.setStyle("-fx-background-color:transparent;" +
                    "-fx-border-radius:1px");
                su_adresse.setStyle("-fx-background-color:#fff;" +
                    "-fx-border-radius:2px");
               su_tel.setStyle("-fx-background-color:transparent;" +
                    "-fx-border-radius:1px");
    }else if(su_tel.isFocused()){
            su_nom_prenom.setStyle("-fx-background-color:transparent;"
                    + "-fx-border-radius:1px");
            su_password.setStyle("-fx-background-color:transparent;" +
                    "-fx-border-radius:1px");
               su_email.setStyle("-fx-background-color:transparent;" +
                    "-fx-border-radius:1px");
                su_adresse.setStyle("-fx-background-color:transparent;" +
                    "-fx-border-radius:1px");
               su_tel.setStyle("-fx-background-color:#fff;" +
                    "-fx-border-radius:2px");
        
        
}}
 public void dropShadowEffect(){
     DropShadow original = new DropShadow (20,Color.valueOf("#ae44a5"));
     original.setRadius(30);
     tunivators.setEffect(original);
     tunivators1.setEffect(original);
     
     tunivators.setOnMouseEntered( event-> {
         DropShadow shadow = new DropShadow (60,Color.valueOf("#e03ed5"));
         tunivators.setCursor(Cursor.HAND);
     tunivators.setStyle("-fx-text-fill:#ee5fe4");
     tunivators.setEffect(shadow);
     });
     tunivators.setOnMouseExited(event -> {
         DropShadow shadow;
         shadow = new DropShadow(30,Color.valueOf("#ae44a5"));
         shadow.setRadius(30);
     tunivators.setStyle("-fx-text-fill:#000");
     tunivators.setEffect(shadow);
     
     
     });
      tunivators1.setOnMouseEntered( event-> {
         DropShadow shadow = new DropShadow (60,Color.valueOf("#e03ed5"));
         tunivators1.setCursor(Cursor.HAND);
     tunivators1.setStyle("-fx-text-fill:#ee5fe4");
     tunivators1.setEffect(shadow);
     });
     
      tunivators1.setOnMouseExited(event -> {
         DropShadow shadow;
         shadow = new DropShadow(30,Color.valueOf("#ae44a5"));
         shadow.setRadius(30);
     tunivators1.setStyle("-fx-text-fill:#000");
     tunivators1.setEffect(shadow);
     
     
     });
     
 }

    @FXML
    public void exit(){
      System.exit(0);
    }
    @FXML
    public void changeForm(ActionEvent event){
        if (event.getSource() == create_acc){
          signup_form.setVisible(true);
          login_F.setVisible(false);
        }else if(event.getSource()==login_acc){
          login_F.setVisible(true);
          signup_form.setVisible(false);
        }
    }
    
    public boolean permiision(Utilisateur user){
        if(user.getVerif().equalsIgnoreCase("Blocked")){
            return false;
        }
        return true;
    }
    
    @FXML
    public void login(){
    
       String query2="select * from utilisateur where nom_prenom=?  and password=?";
      cnx = MyConnection.getInstance().getCnx();
      try{
          PreparedStatement smt = cnx.prepareStatement(query2);
       
               smt.setString(1,nom_prenom.getText());
               smt.setString(2,password.getText());
               ResultSet rs= smt.executeQuery();
               Utilisateur p;
                if(rs.next()){
                     p=new Utilisateur(rs.getInt("idU"),rs.getString("nom_prenom"),rs.getInt("telephone"),rs.getString("email"),rs.getString("password"),rs.getString("adresse"),rs.getString("role"),rs.getString("Picture"),rs.getString("verif"));
                     Utilisateur.setCurrent_User(p);
                     System.out.println(Utilisateur.Current_User.getNom_prenom());
                     
                     //permiision
                     if( permiision(Utilisateur.Current_User)){
                         System.out.println("bonjours"+Utilisateur.Current_User.toString());
                         
                     if(Utilisateur.Current_User.getRole().equals("Admin") /*&& Utilisateur.Current_User.getVerif().equals("Non_bloc")*/){
                         
                         
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("La fourchette :: Message");
                alert.setHeaderText(null);
                alert.setContentText("Welcome to our restaurent  !!");
                alert.showAndWait(); 
                    login_btn.getScene().getWindow().hide();
                    Parent root1 = FXMLLoader.load(getClass().getResource("/tests/inter.fxml"));
                     Scene scene;
                     
                    scene = new Scene(root1);
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
      
                    stage.show();
                         
                     }else{
                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("La fourchette :: Message");
                alert.setHeaderText(null);
                alert.setContentText("vous etes Simple Utilisateur !!");
                alert.showAndWait();  
                     }
                     }
                    
                }else{
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("La fourchette :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Username/Password !!");
                alert.showAndWait();  
                }
          
      }catch(Exception ex){
           System.out.println(ex.getMessage());
      }

}
public boolean ValidationEmail(){
    Pattern pattern = null ;
    Pattern .compile("[a-zA-z0-9][a-zA-z0-9._]*@[a-zA-z0-9]+([.][a-zA-z0-9._]+)+");
       Matcher match = pattern.matcher(su_email.getText());
       if(match.find() && match.group().equals(su_email.getText())){
           return true;
       }else{
           Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("La fourchette :: ERREUR");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Email !!");
                alert.showAndWait();
           return false;
       }
       
}

  public void comboBox(){
        
         ObservableList<String> liste = FXCollections.observableArrayList("Admin","User","Others");
         su_role.setItems(liste);
    }
    @FXML
   public void InsertImage(){
         FileChooser open = new FileChooser();
        Stage stage =(Stage)left_main.getScene().getWindow();
        File file = open.showOpenDialog(stage);
        if(file != null){
            String path = file.getAbsolutePath();
            
            file_path.setText(path);
            Image image = new Image(file.toURI().toString(), 110,110,false,true);
            image_view.setImage(image);
        }else{
            System.out.println("No FILE EXIST");
        }
    }
  
    @FXML
    public void  signUp(){
      
    cnx = MyConnection.getInstance().getCnx();
    String query="INSERT INTO utilisateur (nom_prenom,password,email,role,adresse,telephone,picture,verif) values(?,?,?,?,?,?,?,'Non_bloc')";
    
    try{
        
          if( su_nom_prenom.getText().isEmpty()
                    | su_tel.getText().isEmpty()
                    | su_email.getText().isEmpty()
                    | su_password.getText().isEmpty()
                    | su_adresse.getText().isEmpty()
                    |su_role.getSelectionModel().isEmpty()
                    |image_view.getImage() == null ){
              
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("La fourchette :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Entrer all blank fields !!");
                alert.showAndWait();
    
        
         
          }else if(su_password.getText().length() < 8){
              
              Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("La fourchette :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Password doit etre sup 8 caractéres !!");
                alert.showAndWait();
                
          }/*
          else if(ValidationEmail()){
              Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("La fourchette :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Email !!");
                alert.showAndWait();
         
          
          
          }
*/
          else{
        PreparedStatement smt = cnx.prepareStatement(query);
        smt.setString(1, su_nom_prenom.getText());
            smt.setString(3, su_email.getText());
            smt.setString(2, su_password.getText());
              smt.setString(4, su_role.getSelectionModel().getSelectedItem());
           smt.setString(5, su_adresse.getText());
           smt.setString(6, su_tel.getText());
            smt.setString(7, file_path.getText());
             smt.executeUpdate();
             
                System.out.println("ajout avec succee");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("La fourchette :: BIENVENNUE");
                alert.setHeaderText(null);
                alert.setContentText("Vous Etes Inscrit !!");
                alert.showAndWait();
            
          }}catch(SQLException ex){
         System.out.println(ex.getMessage());
    }
 }
    //************send email *****************
 
    void sendPassword(){
        System.out.println("cxcccccccccccccccccc");
                String query2="select * from utilisateur where nom_prenom=? ";
                String email1="empty";
                 try {
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setString(1, nom_prenom.getText());
             ResultSet rs= smt.executeQuery();
                if(rs.next()){
                   email1=rs.getString("email");
                     System.out.println(email1);
                }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
                 sendMail(email1);
    }
    public  void sendMail(String recepient){
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        
        String myAccountEmail = "lafourchette.esprit@gmail.com";
        String passwordd = "lafourchette123";
        
        Session session = Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail,passwordd);
            }
        });
        Message message =preparedMessage(session,myAccountEmail,recepient);
        try{
            Transport.send(message);
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("La fourchette :: Boite Mail");
                alert.setHeaderText(null);
                alert.setContentText("consulter votre boite mail !!");
                alert.showAndWait();  
            
        }catch(Exception ex){
            ex.printStackTrace();
            
        }
                
    }
     private Message preparedMessage(Session session, String myAccountEmail, String recepient){
         String query2="select * from utilisateur where nom_prenom=?";
         String userEmail="null" ;
         String pass="empty";
        try {
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setString(1, nom_prenom.getText());
             ResultSet rs= smt.executeQuery();
             System.out.println(rs);
                if(rs.next()){
                   pass=rs.getString("password");
                   userEmail=rs.getString("email");                }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.print("c est en cours");
        String text="Votre mot de pass est :"+pass+"";
        String object ="Recupération de mot de passe";
        Message message = new MimeMessage(session);
        try{
        message.setFrom(new InternetAddress(myAccountEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
        message.setSubject(object);
        String htmlcode ="<h1> "+text+" </h1> <h2> <b> </b2> </h2> ";
        message.setContent(htmlcode, "text/html");
         System.out.println("Message envoyeer");
         
           System.out.println(pass);
           
        return message;
        
        }catch(MessagingException ex){
            ex.printStackTrace();
        }
    return null;
    }

     @Override
     public void initialize(URL url, ResourceBundle rb) {
        dropShadowEffect();
           comboBox();
    }    

    @FXML
    private void sendPassword_btn(ActionEvent event) {
        sendMail(nom_prenom.getText());
        //sendPassword(nom_prenom.getText());
    }
   
    
    

}
