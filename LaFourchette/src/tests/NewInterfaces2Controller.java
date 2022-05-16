/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;


import static com.lowagie.text.pdf.PdfFileSpecification.url;
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
import java.awt.Component;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import tests.ItemController;
import tests.MyListener;
import tests.NewOneController;
/**
 * FXML Controller class
 *
 * @author pc
 */
public class NewInterfaces2Controller implements Initializable {

    private TableView<Employer> table_view;
    private TableColumn<Employer, Integer> col_idU;
    private TableColumn<Employer, String> col_nom_prenom;
    private TableColumn<Employer, String> col_telephone;
    private TableColumn<Employer, String> col_adresse;
    private TableColumn<Employer, String> col_picture;
    private TableColumn<Employer, Float> col_salaire;
    private TableColumn<Employer, String> col_job;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private MyListener myListener;
    private List<Employer> employer = new ArrayList<>();
   //final   ObservableList< String> list = FXCollections.observableArrayList();
    public URL url;
    public ResourceBundle rb;
    Connection cnx;
    @FXML
    private Button btn_change;
    @FXML
    private Button details;
    @FXML
    private Button job;
    


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

    @FXML
    private void change_interface(ActionEvent event) {
                 try {
            Parent parent = FXMLLoader.load(getClass().getResource("/tests/NewOne.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            //stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(NewInterfaces2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
         private List<Employer> getLivre() {
        // List<Livre> livlist = FXCollections.observableArrayList();
        List<Employer> livress = find();
        System.out.println("hhhhhhhhhhhh" + livress);
        return livress;
    }
   
     @Override
    public void initialize(URL url, ResourceBundle rb) {
       
      cnx = MyConnection.getInstance().getCnx();
        //showUser();
     employer = getLivre();
        int column = 0;
        int row = 1;
        try {
         
            System.out.println("colonne1111" + column);
            System.out.println("row" + row);
            for (int i = 0; i < employer.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/tests/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(employer.get(i), myListener);

                if (column == 4) {
                    column = 0;
                    row++;

                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));

            }
            
            System.out.println("colonne" + column);
            System.out.println("row" + row);

        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }

    @FXML
    private void details_btn(ActionEvent event) {
                    try {
            Parent parent = FXMLLoader.load(getClass().getResource("/tests/NewTwo.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            //stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(NewInterfaces2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void job_btn(ActionEvent event) {
                 try {
            Parent parent = FXMLLoader.load(getClass().getResource("/tests/Job.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            //stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(NewInterfaces2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*private void Refresh_Table(ActionEvent event) {
        
            cnx = MyConnection.getInstance().getCnx();
        //ObservableList l=new ArrayList(); 
        ObservableList<Employer> DataList = FXCollections.observableArrayList();
       
        try{
      // String query2="SELECT * FROM    employer";
      
       String query2="SELECT * FROM `employer`";
                PreparedStatement smt = cnx.prepareStatement(query2);
                 ResultSet rs= smt.executeQuery();
                while(rs.next()){
                    Employer p;
                   p=new Employer(rs.getFloat("salaire"),rs.getString("job_EM"),rs.getString("nom_prenom"),rs.getString("picture"),rs.getInt("telephone"),rs.getString("adresse"),rs.getInt("idEM"));
                  DataList.add(p);
                }
                
        }catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        
        
    }
*/
    @FXML
    private void Refresh_Table(MouseEvent event) {
        
         List<Employer> showList = getLivre();
           cnx = MyConnection.getInstance().getCnx();
        //showUser();
     employer = getLivre();
        int column = 0;
        int row = 1;
        try {
         
            System.out.println("colonne1111" + column);
            System.out.println("row" + row);
            for (int i = 0; i < employer.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/tests/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(employer.get(i), myListener);

                if (column == 4) {
                    column = 0;
                    row++;

                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));

            }
            
            System.out.println("colonne" + column);
            System.out.println("row" + row);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
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
  



   

