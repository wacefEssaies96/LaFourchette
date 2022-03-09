/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

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
import entities.Utilisateur;
import entities.job;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class JobController implements Initializable {

    @FXML
    private AnchorPane left_main;
      @FXML
    private TextField job_EM;
    @FXML
    private TextField nb_heure;
    @FXML
    private Button insert;
    @FXML
    private Button update;
    @FXML
    private Button clear;
    @FXML
    private Button delet;
    @FXML
    private TableView<job> table_view;
    @FXML
    private TableColumn<job, String> col_nom_job;
    @FXML
    private TableColumn<job, Integer> col_nb_heure;

    /**
     * Initializes the controller class.
     */
      Connection cnx;
    @FXML
    private void Ajouter_User() {
       cnx = MyConnection.getInstance().getCnx();
        String query="INSERT INTO jobem  values(?,?)";
       
        try {
            
            
           
                PreparedStatement smt = cnx.prepareStatement(query);
                smt.setString(1,job_EM.getText());
               smt.setString(2, nb_heure.getText());
               
                smt.executeUpdate();
               
                System.out.println("ajout avec succee");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("La fourchette :: Job"
                        + "");
                alert.setHeaderText(null);
                alert.setContentText("job Ajouter !!");
                alert.showAndWait();
                 showJob();
            
            
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
           
       } 
    }
   
    public  ObservableList<job> find() {
            cnx = MyConnection.getInstance().getCnx();
        //ObservableList l=new ArrayList(); 
        ObservableList<job> DataList = FXCollections.observableArrayList();
        try {
       String query2="SELECT * FROM  jobem";
                PreparedStatement smt = cnx.prepareStatement(query2);
                job p;
                ResultSet rs= smt.executeQuery();
                while(rs.next()){
                   p=new job(rs.getInt("nb_heure"),rs.getString("job_EM"));
                  DataList.add(p);
                }
                System.out.println(DataList);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }

        return DataList;
   
     }

     public void showJob(){
         ObservableList<job> showList = find();
         col_nom_job.setCellValueFactory(new PropertyValueFactory<>("job_EM"));
        col_nb_heure.setCellValueFactory(new PropertyValueFactory<>("nb_heure"));
        
         table_view.setItems(showList);
     }

 

    @FXML
    private void delet() {
                cnx = MyConnection.getInstance().getCnx(); 
          String query2="delete from jobem where nb_heure= " + nb_heure.getText() + "";
      
        try{
           
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("La fourchette :: Utilisateur");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure that you want to delet it !!");
                Optional<ButtonType> buttonType = alert.showAndWait();
                if(buttonType.get() == ButtonType.OK){
                   PreparedStatement smt = cnx.prepareStatement(query2);
                smt.executeUpdate(); 
                showJob();
                }else{
                    return;
                }
                System.out.println("Suppression avec succee");
                showJob();
                
            
        }catch (SQLException ex) {
                System.out.println(ex.getMessage());
         }
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showJob();
    }    

    
}
