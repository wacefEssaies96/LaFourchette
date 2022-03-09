package Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entities.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.UtilisateurService;
import utils.MyConnection;
import entities.Employer;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class InterfacesController implements Initializable {

    @FXML
    private Button deconnecter;
    @FXML
    private AnchorPane left_main;
    @FXML
    private TextField idU;
    @FXML
    private TextField nom_prenom;
    @FXML
    private TextField adresse;
    @FXML
    private ComboBox<String> role;
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
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TableView<Utilisateur> table_view;
    @FXML
    private TableColumn<Utilisateur, Integer> col_idU;
    @FXML
    private TableColumn<Utilisateur, String> col_nom_prenom;
    @FXML
    private TableColumn<Utilisateur, String> col_telephone;
    @FXML
    private TableColumn<Utilisateur, String> col_email;
    @FXML
    private TableColumn<Utilisateur, Integer> col_password;
    @FXML
    private TableColumn<Utilisateur, String> col_adresse;
    @FXML
    private TableColumn<Utilisateur, String> col_role;
    @FXML
    private TableColumn<Utilisateur, String> col_picture;
    @FXML
    private TextField recherche_text;
    
        private double x=0;
        private double y=0;
    /**
     * Initializes the controller class.
     */
       Connection cnx;
    @FXML
    private Button Non_bloc;
    @FXML
    private Button bloc;
    @FXML
    private TableColumn<?, ?> verif;
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
    private void Ajouter_User() {
         cnx = MyConnection.getInstance().getCnx();
        String query="INSERT INTO utilisateur  values(?,?,?,?,?,?,?,?,'Non_bloc')";
       
        try {
            if(idU.getText().isEmpty() | nom_prenom.getText().isEmpty()
                    | telephone.getText().isEmpty()
                    | email.getText().isEmpty()
                    | password.getText().isEmpty()
                    | adresse.getText().isEmpty()
                    |role.getSelectionModel().isEmpty()
                    |image_view.getImage()==null ){
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("La fourchette :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Entrer all blank fields !!");
                alert.showAndWait();
            }else{
           
                PreparedStatement smt = cnx.prepareStatement(query);
                smt.setString(1, idU.getText());
               smt.setString(2, nom_prenom.getText());
               smt.setString(7, telephone.getText());
               smt.setString(3, email.getText());
               smt.setString(4, password.getText());
               smt.setString(6, adresse.getText());
                smt.setString(5, role.getSelectionModel().getSelectedItem());
                 smt.setString(8, file_path.getText());
                smt.executeUpdate();
               
                System.out.println("ajout avec succee");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("La fourchette :: Utilisateur");
                alert.setHeaderText(null);
                alert.setContentText("Utilisateur Ajouter !!");
                alert.showAndWait();
                 //showUser();
                clear();
                 searchEmploye();
            }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
           
       }
    }
    public  ObservableList<Utilisateur> find() {
            cnx = MyConnection.getInstance().getCnx();
        //ObservableList l=new ArrayList(); 
        ObservableList<Utilisateur> DataList = FXCollections.observableArrayList();
        try {
       String query2="SELECT * FROM  utilisateur";
                PreparedStatement smt = cnx.prepareStatement(query2);
                Utilisateur p;
                ResultSet rs= smt.executeQuery();
                while(rs.next()){
                   p=new Utilisateur(rs.getInt("idU"),rs.getString("nom_prenom"),rs.getInt("telephone"),rs.getString("email"),rs.getString("password"),rs.getString("adresse"),rs.getString("role"),rs.getString("Picture"),rs.getString("verif"));
                  DataList.add(p);
                }
                System.out.println(DataList);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }

        return DataList;
   
     }
     public void showUser(){
         ObservableList<Utilisateur> showList = find();
         col_idU.setCellValueFactory(new PropertyValueFactory<>("idU"));
         col_nom_prenom.setCellValueFactory(new PropertyValueFactory<>("nom_prenom"));
         col_telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
         col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
         col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
         col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
         col_role.setCellValueFactory(new PropertyValueFactory<>("role"));
         col_picture.setCellValueFactory(new PropertyValueFactory<>("picture"));
          verif.setCellValueFactory(new PropertyValueFactory<>("verif"));
         table_view.setItems(showList);
     }
    @FXML
     public void exit(){
     System.exit(0);
    }
     
    @FXML
     public void modifier(){
        cnx = MyConnection.getInstance().getCnx(); 
         String query2 = "UPDATE utilisateur SET `nom_prenom` = '"
                 + nom_prenom.getText() + "', `telephone` = '"
                 + telephone.getText() + "', `email` = '" 
                 + email.getText() + "', `password` = '" 
                 + password.getText()+ "', `adresse` = '"
                 + adresse.getText() + "', `role` = '"
                 + role.getSelectionModel().getSelectedItem()
                 + "', `picture` = '" + file_path.getText()
                 + "' WHERE idU = '" + idU.getText() + "'";
                 
         try{
               if(idU.getText().isEmpty() | nom_prenom.getText().isEmpty()
                    | adresse.getText().isEmpty()
                    |role.getSelectionModel().isEmpty()
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
                showUser();
                clear();
               }
     
         }catch (SQLException ex) {
                System.out.println(ex.getMessage());
         }
        }

    @FXML
   public void clear(){
         idU.setText("");
         nom_prenom.setText("");
          telephone.setText("");
           email.setText("");
            password.setText("");
         adresse.setText("");
         role.getSelectionModel().clearSelection();
         image_view.setImage(null);
         
         }
   
    @FXML
   public void deconnecter() {
        
       try{
           
        deconnecter.getScene().getWindow().hide();
          Parent root = FXMLLoader.load(getClass().getResource("/tests/NewInterface1.fxml"));
          Stage stage = new Stage();
          root.setOnMousePressed(event -> {
          x=event.getSceneX();
          y=event.getSceneY();
          
          });
          root.setOnMouseDragged(event -> {
          stage.setX(event.getScreenX() - x);
          stage.setY(event.getScreenY() -y);
          
          });
           Scene scene = new Scene(root);
            
           stage.setScene(scene);
      stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
     
      
       
      
        stage.show();
       
           
           
           
       }catch(Exception ex){
           System.out.println(ex.getMessage());
      }
   }
   public void blocker (Utilisateur t) {
    
    try {
       String query2="update utilisateur set verif= 'Blocked' where idU=?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                smt.setInt(1, t.getIdU());
                smt.executeUpdate();
                System.out.println("Blockage avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    } 
       
}
public void deblocker (Utilisateur t) {
    
    try {
       String query2="update utilisateur set verif= 'Non_bloc' where idU=?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                smt.setInt(1, t.getIdU());
                smt.executeUpdate();
                System.out.println("Deblockage avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    } 
}
    @FXML
    public void delet(){
        cnx = MyConnection.getInstance().getCnx(); 
          String query2="delete from utilisateur where idU= " + idU.getText() + "";
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
                showUser();
                clear(); 
            
        }catch (SQLException ex) {
                System.out.println(ex.getMessage());
         }
    }
    

    @FXML
    public void select(){
         
         Utilisateur  Utilisateur =table_view.getSelectionModel().getSelectedItem();
        int num;
        num = table_view.getSelectionModel().getSelectedIndex();
         if(-1 > (num-1)){
             return;
         } else {
             idU.setText(String.valueOf( Utilisateur.getIdU()));
             nom_prenom.setText( Utilisateur.getNom_prenom());
             telephone.setText(String.valueOf( Utilisateur.getTelephone()));
              password.setText( Utilisateur.getMdp());
              email.setText( Utilisateur.getEmail());
             adresse.setText( Utilisateur.getAdresse());
               role.getSelectionModel().clearSelection();
           
             String picture ="file :" +  Utilisateur.getPicture();
             Image image = new Image(picture, 110, 110, false, true);
           image_view.setImage(image);
            String path =  Utilisateur.getPicture();
             file_path.setText(path);
            //file_path.setText(user.getPicture());
             
             
         }
         
         
     }
    
    
    public void comboBox(){
        
         ObservableList< String> list = FXCollections.observableArrayList("Admin","User","Others");
         role.setItems(list);
    }
    public void TExtFieldDesign(){
        if(idU.isFocused()){
                 idU.setStyle("-fx-border-width:2px;-fx-background-color:#fff");
                 nom_prenom.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 telephone.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 email.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 password.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 adresse.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 role.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
        }else if(nom_prenom.isFocused()){
                 idU.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 nom_prenom.setStyle("-fx-border-width:2px;-fx-background-color:#fff");
                 telephone.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 email.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 password.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 adresse.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
             
              role.setStyle("-fx-border-width:1px;-fx-background-color:transparent"); 
        }else if(telephone.isFocused()){
                 idU.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 nom_prenom.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 telephone.setStyle("-fx-border-width:2px;-fx-background-color:#fff");
                 email.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 password.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 adresse.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
              role.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
        } else if(email.isFocused()){
                 idU.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 nom_prenom.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 telephone.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 email.setStyle("-fx-border-width:2px;-fx-background-color:#fff");
                 password.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 adresse.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 role.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
        }else if(password.isFocused()){
                 idU.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 nom_prenom.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 telephone.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 email.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 password.setStyle("-fx-border-width:2px;-fx-background-color:#fff");
                 adresse.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 role.setStyle("-fx-border-width:1px;-fx-background-color:transparent");   
            
        }else if(adresse.isFocused()){
                 idU.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 nom_prenom.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 telephone.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 email.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 password.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 adresse.setStyle("-fx-border-width:2px;-fx-background-color:#ffft");
                 role.setStyle("-fx-border-width:1px;-fx-background-color:transparent");   
            
        }else if(role.isFocused()){
                 idU.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 nom_prenom.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 telephone.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 email.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 password.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 adresse.setStyle("-fx-border-width:1px;-fx-background-color:transparent");
                 role.setStyle("-fx-border-width:2px;-fx-background-color:#fff");   
            
        }
    
    }
    
    @FXML
    public void print(){
        try{
        JasperDesign jDesign =JRXmlLoader.load("C:\\Users\\pc\\Documents\\NetBeansProjects\\LaFourchette\\LaFourchette\\src\\tests\\report.jrxml");
        JasperReport jReport =JasperCompileManager.compileReport(jDesign);
      
        JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, cnx);
        JasperViewer viewer = new JasperViewer(jPrint,false);
        viewer.setTitle("lafourchette");
        viewer.show();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        }
//************recherche!!!!*************************
   public void searchEmploye(){
       ObservableList<Utilisateur> showList = find();
        
        col_idU.setCellValueFactory(new PropertyValueFactory<>("idU"));
         col_nom_prenom.setCellValueFactory(new PropertyValueFactory<>("nom_prenom"));
         col_telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
         col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
         col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
         col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
         col_role.setCellValueFactory(new PropertyValueFactory<>("role"));
         col_picture.setCellValueFactory(new PropertyValueFactory<>("picture"));
         
       
            
          table_view.setItems(showList);
          FilteredList<Utilisateur> filtredData = new FilteredList<>(showList, b -> true);
          recherche_text.textProperty().addListener((observable, olValue, newValue)->{
             filtredData.setPredicate(person-> {
                 if(newValue == null|| newValue.isEmpty()){
                     return true;
                 }
                 String lowerCaseFilter= newValue.toLowerCase();
                 if(person.getNom_prenom().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                     return true;
                 
                 }
                 else if(String.valueOf(person.getTelephone()).indexOf(lowerCaseFilter)!=-1)
                     return true;
                     else
                     return false;
                 });
             });
         SortedList<Utilisateur> sortedData = new SortedList<>(filtredData);
         sortedData.comparatorProperty().bind(table_view.comparatorProperty());
         table_view.setItems(sortedData);

                  
    }  
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       comboBox();
       showUser();
       searchEmploye();
    }  

    @FXML
    private void deblock(ActionEvent event) {
          Utilisateur user = new Utilisateur();
       
           
            int id=Integer.parseInt(idU.getText());
            user.setIdU(id);
            System.out.println(id);
           deblocker(user);
        
       ObservableList<Utilisateur> showList = find();
         col_idU.setCellValueFactory(new PropertyValueFactory<>("idU"));
         col_nom_prenom.setCellValueFactory(new PropertyValueFactory<>("nom_prenom"));
         col_telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
         col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
         col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
         col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
         col_role.setCellValueFactory(new PropertyValueFactory<>("role"));
         col_picture.setCellValueFactory(new PropertyValueFactory<>("picture"));
         table_view.setItems(showList);  
        
        
    }

    @FXML
    private void block(ActionEvent event) {
        Utilisateur user = new Utilisateur();
       
           
            int id=Integer.parseInt(idU.getText());
            user.setIdU(id);
            System.out.println(id);
           blocker(user);
        
       ObservableList<Utilisateur> showList = find();
         col_idU.setCellValueFactory(new PropertyValueFactory<>("idU"));
         col_nom_prenom.setCellValueFactory(new PropertyValueFactory<>("nom_prenom"));
         col_telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
         col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
         col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
         col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
         col_role.setCellValueFactory(new PropertyValueFactory<>("role"));
         col_picture.setCellValueFactory(new PropertyValueFactory<>("picture"));
         table_view.setItems(showList);  
        
    }
    
    
    
    
}
