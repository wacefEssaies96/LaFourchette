/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import entities.Employer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.MyConnection;
/**
 * FXML Controller class
 *
 * @author pc
 */
public class NewTwoController implements Initializable {

    private TextField idEM;
    private TextField nom_prenom;
    private TextField adresse;
    private Label file_path;
    private ImageView image_view;
    private TextField telephone;
    private TextField salaire;
    private ComboBox<String> job_EM;
    @FXML
    private TableView<Employer> table_view;
    @FXML
    private TableColumn<Employer, Integer> col_idU;
    @FXML
    private TableColumn<Employer, String> col_nom_prenom;
    @FXML
    private TableColumn<Employer, String> col_telephone;
    @FXML
    private TableColumn<Employer, String> col_adresse;
    @FXML
    private TableColumn<Employer, String> col_picture;
    @FXML
    private TableColumn<Employer, Float> col_salaire;
    @FXML
    private TableColumn<Employer, String> col_job;
 Connection cnx;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showUser();
    }    
 public  ObservableList<Employer> find() {
            cnx = MyConnection.getInstance().getCnx();
        //ObservableList l=new ArrayList(); 
        ObservableList<Employer> DataList = FXCollections.observableArrayList();
        try {
            String query2="SELECT * FROM employer left join jobem on employer.job_EM = jobem.job_EM ";
      // String query2="SELECT * FROM    employer";
                PreparedStatement smt = cnx.prepareStatement(query2);
                Employer p;
                ResultSet rs= smt.executeQuery();
                while(rs.next()){
                   p=new Employer(rs.getFloat("salaire"),rs.getString("job_EM"),rs.getString("nom_prenom"),rs.getString("picture"),rs.getInt("telephone"),rs.getString("adresse"),rs.getInt("idEM"));
                  DataList.add(p);
                }
                System.out.println(DataList);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }

        return DataList;
   
     }
    //***********Combo**********
     /*
     ComboBox ComboBox = new ComboBox(list);
    HBox hbox =new HBox(5);
    public void fillCombo(){
          cnx = MyConnection.getInstance().getCnx();
         String query2="select * from jobem";
         try{
              PreparedStatement smt = cnx.prepareStatement(query2); 
              ResultSet rs= smt.executeQuery();
         
               while(rs.next()){
                  
                  list.add(rs.getString("job_EM"));
                          job_EM.setItems(list);
                }
               smt.close();
               rs.close();
         }catch(Exception ex){
              System.out.println(ex.getMessage());
         }
  
    }
        public void comboBox(){
        
         ObservableList< String> list = FXCollections.observableArrayList();
         job_EM.setItems(list);
    }
     
     */
     
     
     

     public void showUser(){
         ObservableList<Employer> showList = find();
         col_idU.setCellValueFactory(new PropertyValueFactory<>("idEM"));
         col_nom_prenom.setCellValueFactory(new PropertyValueFactory<>("nom_prenom"));
         col_telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
           col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            col_picture.setCellValueFactory(new PropertyValueFactory<>("picture"));
         col_salaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
       
         col_job.setCellValueFactory(new PropertyValueFactory<>("job_EM"));
         table_view.setItems(showList);
     }
      @FXML

    private void select() {
        Employer  employer =table_view.getSelectionModel().getSelectedItem();
        int num;
        num = table_view.getSelectionModel().getSelectedIndex();
         if(-1 > (num-1)){
             return;
         } else {
             idEM.setText(String.valueOf( employer.getIdEM()));
             nom_prenom.setText( employer.getNom_prenom());
             telephone.setText(String.valueOf( employer.getTelephone()));
             
              salaire.setText(String.valueOf(employer.getSalaire()));
             adresse.setText( employer.getAdresse());
               job_EM.getSelectionModel().clearSelection();
           
             String picture ="file :" + employer.getPicture();
             Image image = new Image(picture, 110, 110, false, true);
           image_view.setImage(image);
            String path =  employer.getPicture();
             file_path.setText(path);
               
    }

    }

  
    
}
