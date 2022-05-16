/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Plat;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.gestionPlat;
import utils.MyConnection;

public class PlatguiController implements Initializable {
   
       private Label label;
    @FXML
    private TextField tfreference;
    @FXML
    private TextField tfdesignation;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfproduits;
    @FXML
    private TableView<Plat> tvplat;
    @FXML
    private TableColumn<Plat,String> colnom;
    @FXML
    private TableColumn<Plat, String> coldesignation;
    @FXML
    private TableColumn<Plat,Double> colprix;
    @FXML
    private TableColumn<Plat, String> coldescription;
    @FXML
    private TableColumn<Plat, String> colimage;
    @FXML
    private TableColumn<Plat, String> colproduit;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button insertimagebutton;
    @FXML
    private ImageView imageview;
    @FXML
    private Label imagepath;
    @FXML
    private AnchorPane leftmain;
    @FXML
    private Button btvoirmenu;
    @FXML
    private TextField tfrecherche;
    @FXML
    private Button openvideo;
    
    
     
@Override
    public void initialize(URL location, ResourceBundle resources) {
        
           rechecheplataction();
             //  showplat();
               //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           
    }
    
       @FXML
 private void ajouterplat(javafx.event.ActionEvent event) throws Exception{
    
        
      if(tfreference.getText().isEmpty() & tfdesignation.getText().isEmpty() & tfdescription.getText().isEmpty() & tfproduits.getText().isEmpty() & tfprix.getText().isEmpty()  & imagepath.getText().isEmpty() )
      {
            
           Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs !");
                alert.showAndWait();
      }else if(tfreference.getText().isEmpty() && tfdesignation.getText().isEmpty() && tfdescription.getText().isEmpty() && tfproduits.getText().isEmpty() && tfprix.getText().isEmpty() && imagepath.getText().isEmpty() ) {
           
                 Alert alert = new Alert(Alert.AlertType.ERROR);
               
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir les autres champs ! !");
                alert.showAndWait();
          
      }else if(tfreference.getText().isEmpty() && tfdesignation.getText().isEmpty() && tfdescription.getText().isEmpty() & tfproduits.getText().isEmpty() && tfprix.getText().isEmpty()  ) {
           
                 Alert alert = new Alert(Alert.AlertType.ERROR);
               
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir nom,designation,description,produit et prix ! !");
                alert.showAndWait();
      }else if(tfreference.getText().isEmpty() && tfdesignation.getText().isEmpty() && tfdescription.getText().isEmpty() && tfprix.getText().isEmpty() ) {
           
                 Alert alert = new Alert(Alert.AlertType.ERROR);
               
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir nom,designation,et description  ! !");
                alert.showAndWait();
      }else if(tfreference.getText().isEmpty() && tfdesignation.getText().isEmpty()    ) {
           
                 Alert alert = new Alert(Alert.AlertType.ERROR);
               
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir nom etdesignation  ! !");
                alert.showAndWait();
                }else if(tfreference.getText().isEmpty()   ) {
           
                 Alert alert = new Alert(Alert.AlertType.ERROR);
               
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir nom ! !");
                alert.showAndWait();
                }
      else  { 
          
     
                
         try{
      gestionPlat gp= new gestionPlat();
      String reference= tfreference.getText();
      String designation= tfdesignation.getText();
         double prix = Double.parseDouble(tfprix.getText());
        String description= tfdescription.getText();
         String nomProd= tfproduits.getText() ;
         String imageP= imagepath.getText();
         
   
           
       Plat p;
           p = new Plat(reference,designation,prix,description,imageP,nomProd);
      gp.ajouter(p);
      showplat();
      rechecheplataction();
      }
    catch(NumberFormatException e){ Alert alert = new Alert(Alert.AlertType.ERROR);
               
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Le champ prix doit etre un nombre ! !");
                alert.showAndWait();
                        
        }
 }
 }
  
 
 
       @FXML
     public void insertImage(){
        
        FileChooser open = new FileChooser();
        
        Stage stage = (Stage)leftmain.getScene().getWindow();
        
        File file = open.showOpenDialog(stage);
        
        if(file != null){
            
            String path = file.getAbsolutePath();
            
            path = path.replace("\\", "\\\\");
            
            imagepath.setText(path);

            Image image = new Image(file.toURI().toString(), 110, 110, false, true);
            
            imageview.setImage(image);
            
        }else{
            
            System.out.println("NO DATA EXIST!");
            
        }
    }
       @FXML
     public void modifierplat(javafx.event.ActionEvent event) throws IOException, ParseException {
         Plat p=(Plat) tvplat.getSelectionModel().getSelectedItem();
          //Parent root = FXMLLoader.load(getClass().getResource("/controller/Edit.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
               "/Controllers/Edit.fxml"));
            Parent root = (Parent) loader.load();
     Scene scene =new Scene(root);
     Stage stage =new Stage();
     stage.setScene(scene);
     stage.show();
     
       EditController modifier=loader.getController() ;
modifier.setText(p);
       
        

     

   




    }
  /*private void ajouterplat() {
        String query = "INSERT INTO plat VALUES ('" + tfreference.getText() + "','" + tfdesignation.getText() + "'," + tfprix.getText() + ",'"+ tfdescription.getText() + "','" + tfimage.getText()+"','" + tfproduits.getText() + "')";
   
       executeQuery(query);
        //executeQuery(query);
        showplat();
    }
    private void executeQuery(String query) {
        Connection cnx = MyConnection.getInstance().getCnx();
        
        try{
            PreparedStatement smt = cnx.prepareStatement(query);
            smt.executeUpdate(query);
        }catch(SQLException ex){
                        System.out.println(ex.getMessage());
        }
    }
*/
 


   






    
    public void showplat() {
        
        gestionPlat gp=new gestionPlat();
        try {
        ObservableList<Plat> showlist ;
        showlist=gp.getplatlist();
        //colnom.setCellValueFactory(value);
        colnom.setCellValueFactory(new PropertyValueFactory<>("reference"));
        
        coldesignation.setCellValueFactory( new PropertyValueFactory<>("designation"));
        colprix.setCellValueFactory( new PropertyValueFactory<>("prix"));
        coldescription.setCellValueFactory( new PropertyValueFactory<>("description"));
        colimage.setCellValueFactory( new PropertyValueFactory<>("imageP"));
        colproduit.setCellValueFactory( new PropertyValueFactory<>("nomProd"));
        
                // col_nom_prenom.setCellValueFactory(new PropertyValueFactory<>("nom_prenom"));
                tvplat.setItems(showlist);
        } catch (Exception ex) {
               Logger.getLogger(PlatguiController.class.getName()).log(Level.SEVERE, null, ex);
           }
        
        
   }
    @FXML
     private void menuadminAction(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menuadmin.fxml"));
         
            
     Scene scene =new Scene(root);
     Stage stage =new Stage();
     stage.setScene(scene);
     stage.show();
        
        
    }
    private void supprimer() throws SQLException {

        
        gestionPlat gp=new gestionPlat();
        Plat t = tvplat.getSelectionModel().getSelectedItem();
      //  Plat p = new Plat(c.getreference());
        gp.supprimer(t);
        showplat();
        

        //  showReview();
    }
    @FXML
    private void handleButtonAction(javafx.event.ActionEvent event) throws SQLException 
  {System.out.println("you clicked me");
  
        
       
        if (event.getSource()==btnsupprimer){
            supprimer();
    }    
     
        
    }
   
    private void rechecheplataction() {
         colnom.setCellValueFactory(new PropertyValueFactory<>("reference"));
        
        coldesignation.setCellValueFactory( new PropertyValueFactory<>("designation"));
        colprix.setCellValueFactory( new PropertyValueFactory<>("prix"));
        coldescription.setCellValueFactory( new PropertyValueFactory<>("description"));
        colimage.setCellValueFactory( new PropertyValueFactory<>("imageP"));
        colproduit.setCellValueFactory( new PropertyValueFactory<>("nomProd"));
         gestionPlat gp=new gestionPlat();
          ObservableList<Plat> showlist ;
        showlist=gp.getplatlist();
       
         tvplat.setItems(showlist);
         FilteredList<Plat> filteredData = new FilteredList<>(showlist,b->true);
         tfrecherche.textProperty().addListener((observable,oldValue,newValue)-> {
             filteredData.setPredicate(P-> {
                 if (newValue == null || newValue.isEmpty()){
                     return true;
                 }
                 String lowerCaseFilter = newValue.toLowerCase();
                 if (P.getReference().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                 return true;
                 }else if (P.getDesignation().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                 return true;
                 }
                 else
                 return false ;
                 
             });
         });
         SortedList<Plat> sortedData = new SortedList<>(filteredData);
         sortedData.comparatorProperty().bind(tvplat.comparatorProperty());
         tvplat.setItems(sortedData);
         
    }

   
       @FXML
    public String openfilemethode() {
         
             FileChooser fileChooser = new FileChooser();
         
//        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a .mp4 file", ".mp4");
//        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
            String file1=file.toURI().toString();
     return file1;
    }

    @FXML
    private void homeIner(MouseEvent event) {
         try{
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            Parent root =FXMLLoader.load(getClass().getResource("/tests/inter.fxml"));
            Scene scene= new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Liste des Tables");
            stage.show();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }


   

   

    

    

   

  
}

    


    