/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entities.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.MyConnection;
import java.sql.ResultSet;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import tests.NewInterfaces2Controller;
import tests.NewTwoController;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class NewOneController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private TextField idEM;
    @FXML
    private TextField nom_prenom;
    @FXML
    private TextField adresse;
    @FXML
    private Label file_path;
    @FXML
    private Button insert;
    @FXML
    private Button update;
    @FXML
    private Button clear;
    @FXML
    private Button delet;
    @FXML
    private Button print;
    @FXML
    private ImageView image_view;
    @FXML
    private Button insert_image;
    @FXML
    private TextField telephone;
    @FXML
    private TextField salaire;
    @FXML
    private ComboBox<String> job_EM;
Connection cnx;
 public URL url;
              public ResourceBundle rb;
    @FXML
    private Button dddv;
    /**
     * Initializes the controller class.
     */
       
  @FXML
    
    private void Ajouter_User() {
        cnx = MyConnection.getInstance().getCnx();
        
        String query="INSERT INTO employer  values(?,?,?,?,?,?,?)";
       
        try {
            if(idEM.getText().isEmpty() | nom_prenom.getText().isEmpty()
                    | telephone.getText().isEmpty()

                    | adresse.getText().isEmpty()
                    | salaire.getText().isEmpty()
                    |job_EM.getSelectionModel().isEmpty()
                   
                    |image_view.getImage()==null ){
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("La fourchette :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Entrer all blank fields !!");
                alert.showAndWait();
            }else{
           
                PreparedStatement smt = cnx.prepareStatement(query);
                smt.setString(1, idEM.getText());
               smt.setString(2, nom_prenom.getText());
               smt.setString(3, telephone.getText());
               smt.setString(4, adresse.getText());
               smt.setString(5, file_path.getText());
               smt.setString(6, salaire.getText());
              smt.setString(7, job_EM.getSelectionModel().getSelectedItem());                
                
                smt.executeUpdate();
               
                System.out.println("ajout avec succee");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("La fourchette :: Employer");
                alert.setHeaderText(null);
                alert.setContentText("Employer Ajouter !!");
                alert.showAndWait();
                 //showUser();
                 initialize( url, rb);
                 
                //clear();
            }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
           
       }
        
        
      
    }

     @FXML
    private void modifier() {
         cnx = MyConnection.getInstance().getCnx(); 
         String query2 = "UPDATE employer SET `nom_prenom` = '"
                 + nom_prenom.getText() + "', `telephone` = '"
                 + telephone.getText() + "', `salaire` = '" 
                 + salaire.getText() + "', `adresse` = '"
                 + adresse.getText() + "', `job_EM` = '"
                 + job_EM.getSelectionModel().getSelectedItem()
                 + "', `picture` = '" + file_path.getText()
                 + "' WHERE idEM= '" + idEM.getText() + "'";
                 
         try{
               if(idEM.getText().isEmpty() | nom_prenom.getText().isEmpty()
                    | adresse.getText().isEmpty()
                    |job_EM.getSelectionModel().isEmpty()
                    |image_view.getImage()==null ){
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("La fourchette :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Entrer all blank fields !!");
                alert.showAndWait();
               }else{
             
                PreparedStatement smt = cnx.prepareStatement(query2);
                smt.executeUpdate();
                System.out.println("modification avec succee");
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("La fourchette :: Utilisateur");
                alert.setHeaderText(null);
                alert.setContentText("Utilisateur Modifier !!");
                alert.showAndWait();
                //showUser();
                //clear();
               }
     
         }catch (SQLException ex) {
                System.out.println(ex.getMessage());
         }
    }
     

    @FXML
    private void clear() {
        idEM.setText("");
         nom_prenom.setText("");
          telephone.setText("");
           salaire.setText("");
        
         adresse.setText("");
        job_EM.getSelectionModel().clearSelection();
         image_view.setImage(null);
    }
 JComboBox ComboBox = new JComboBox();
      JPanel panel =new JPanel();
     public void fillCombo(){
         int i=0;
         cnx = MyConnection.getInstance().getCnx();
         String query2="select * from jobem";
         try{
              PreparedStatement smt = cnx.prepareStatement(query2); 
              ResultSet rs= smt.executeQuery();
              ObservableList list = FXCollections.observableArrayList();
               while(rs.next()){
                  //String job_EM=rs.getString("job_EM");
                  
                   
                    //list.add(rs.getString("job_EM"));
                     /*
                 
                    list.add(new String(rs.getString("job_EM")));
                    for(i=0;i<list.size();i++){
                      list.add(new String(rs.getString("job_EM")));  
                    }
                    
                             while(list.size()>i){
                        
                        i++;
                    } 
                     list.add(new String(rs.getString("job_EM"))); 
*/        
                     list.add(new String(rs.getString("job_EM"))); 
                    System.out.println(list.get(i));
                         
                          //ComboBox.addItem(rs.getString(1));
                         
                }
                job_EM.setItems(list);
          
         }catch(Exception ex){
             System.out.println(ex.getMessage());
         }
     }
     
     
    @FXML
    private void delet() {
        cnx = MyConnection.getInstance().getCnx(); 
          String query2="delete from employer where idEM= " + idEM.getText() + "";
       //String query2 ="delet from `admiin` where `id` = '" + id.getText() + "'";
        try{
           
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("La fourchette :: Utilisateur");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure that you want to delet it !!");
                Optional<ButtonType> buttonType = alert.showAndWait();
                if(buttonType.get() == ButtonType.OK){
                   PreparedStatement smt = cnx.prepareStatement(query2);
                smt.executeUpdate();  
                }else{
                    return;
                }
                System.out.println("Suppression avec succee");
                //showUser();
                clear(); 
            
        }catch (SQLException ex) {
                System.out.println(ex.getMessage());
         }
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
     @Override
    public void initialize(URL url, ResourceBundle rb) {
         fillCombo();
         
         
         
    } 

    @FXML
    private void inter(ActionEvent event) throws IOException {
        Parent root3 = FXMLLoader.load(getClass().getResource("NewInterfaces2.fxml"));
           Scene scene = new Scene(root3);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
        
        stage.show();
    }

    private ObservableList<Utilisateur> find() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
