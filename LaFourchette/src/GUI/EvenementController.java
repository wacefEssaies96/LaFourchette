/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import doryan.windowsnotificationapi.fr.Notification;
import entities.Commentaire;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import entities.Evenement; 
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import static javax.management.remote.JMXConnectorFactory.connect;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.Notifications;
import services.CommentaireCrud;
import services.EvenementCrud;
import tests.EListener;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author user
 */
public class EvenementController implements Initializable {

    private TextField TextField_ID;
Connection cnx; 
MyConnection mycnx;

    private Statement statement;
    @FXML
    private Label file_path;
    @FXML
    private AnchorPane EvenementAnchorPane;
    @FXML
    private GridPane grid;
    /**
     * Initializes the controller class.
     */
    Evenement ev; 
    @FXML
    private Button AjouterEvenement1;
    @FXML
    private Button tri;
    @FXML
    private Button pdf;
    @FXML
    private TextField recherche;
   private List<Evenement>ListeDesEvent =new ArrayList(); 
   private EListener listen; 
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        EvenementCrud ev = new EvenementCrud();
        Evenement event = new Evenement();
        EListener  Listener;
        List<Evenement>ListeDesEvent = ev.afficherListeEvenement();
        //TextField_ID.setDisable(true);
        
        grid.getChildren().clear();
        if (ListeDesEvent.size() > 0) {
            event=ListeDesEvent.get(0);
            //setChosenTable_Resto(ListeDesEvent.get(0));
            Listener = new EListener() {
                @Override
                public void onClickListener(Evenement e) {
                 setChooseEvenement  (e);
                   //naaml stage 
                   
                }
            };
        }
         int col=0;
       int row=0;
       
       try{
        
        for (Evenement e : ListeDesEvent)
      {
            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("ItemE.fxml"));
            AnchorPane anchorPane = fxmlloader.load();
           
                   ItemEController itemControler = fxmlloader.getController();
                itemControler.SetData(e);
                //itemControler.SetData(ev.get(i),listen);
                
           if(col==3){
              col=0;
              row++;
           }
           grid.add(anchorPane,col++,row);
           //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);//set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
               grid.setVgap(150);
           GridPane.setMargin(anchorPane, new Insets(10,10,10,10));
        }
       }catch(Exception e){
            System.out.println(e.getMessage());
            
        }
    }    
        /*private void AffichageEvenement() {
        
         ObservableList<Evenement> EvenementList = EvenementList();

        idevenement.setCellValueFactory(new PropertyValueFactory<>("idE"));
        dateE.setCellValueFactory(new PropertyValueFactory<>("dateE"));
        designation.setCellValueFactory(new PropertyValueFactory<>("designationE"));
        description.setCellValueFactory(new PropertyValueFactory<>("descriptionE"));
        nbrParticipants.setCellValueFactory(new PropertyValueFactory<>("nbrParticipants"));
        
        imageE.setCellValueFactory(new PropertyValueFactory<>("imageE"));

        AffichageEvenement.setItems(EvenementList);*/
        
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


    
          private void setChooseEvenement(Evenement e)
          {
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText("selectionné!");
                alert.showAndWait();
          }
                  
        
    
    
    public ObservableList<Evenement> EvenementList() {

       
       cnx = MyConnection.getInstance().getCnx();

        ObservableList<Evenement> EvenementList = FXCollections.observableArrayList();

        String req = "SELECT * FROM evenement";

        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            Evenement e;

            while (rs.next()) {

                e= new Evenement(rs.getInt("idE"),rs.getString("designationE"),rs.getString("descriptionE"),rs.getDate("dateE"),rs.getString("imageE"),rs.getInt("nbrParticipants"));

                EvenementList.add(e);

            }

        } catch (SQLException e) {
        }

        return EvenementList;

    }

   /* private void SupprimerEvenement(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Voulez vous supprimer cet evenemnt  ?");

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.get() == ButtonType.OK) {

                EvenementCrud erd= new EvenementCrud();
               
                String idE = TextField_ID.getText();
                erd.supprimerEvenement(parseInt(idE));
                

            } else {

                return;

            }

           // AffichageEvenement();
          //  clear();
          //  Notificationmanager(1);
        
        
        
        
    }*/

    @FXML
    private void ajoutEV(ActionEvent event) {
        
        
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/AjoutEvenement.fxml"));
//Parent root = FXMLLoader.load(getClass().getResource("../GUI/modifierEvenement.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        
            //Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ItemEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void tri(ActionEvent event) {
        grid.getChildren().clear();
        EvenementCrud evcrud = new EvenementCrud();

        List<Evenement>ListeDesEvent = evcrud.afficherListeEvenement();
       Collections.sort(ListeDesEvent, (e1,e2)->e1.getNbrParticipants()-e2.getNbrParticipants());
       int col=0;
       int row=0;
        
       try{
        
        for (Evenement e : ListeDesEvent){
            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("ItemE.fxml"));
            AnchorPane anchorPane = fxmlloader.load();
           
                   ItemEController itemControler = fxmlloader.getController();
                itemControler.SetData(e);
                
           if(col==2){
              col=0;
              row++;
           }
           grid.add(anchorPane,col++,row);
           //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                grid.setHgap(150);
           GridPane.setMargin(anchorPane, new Insets(10,10,10,10));
        }
       }catch(IOException e){
            e.printStackTrace();
            
        }
        
    }

    @FXML
    private void pdf(ActionEvent event)throws SQLException, IOException {
       cnx = MyConnection.getInstance().getCnx();

        try{
        JasperDesign jDesign =JRXmlLoader.load("C:\\Users\\user\\Downloads\\LaFourchette-main\\LaFourchette\\src\\tests\\report.jrxml");
        JasperReport jReport =JasperCompileManager.compileReport(jDesign);
      
        JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, cnx);
        JasperViewer viewer = new JasperViewer(jPrint,false);
        viewer.setTitle("lafourchette");
        viewer.show();
        }catch(Exception ex){
            System.out.println(ex.getMessage());}

    }

    @FXML
    private void recherche(KeyEvent event) {
                grid.getChildren().clear();

        EvenementCrud evcrud = new EvenementCrud();

        List<Evenement>ListeDesEvent = evcrud.afficherListeEvenement();
       ListeDesEvent= ListeDesEvent.stream().filter((e)->e.getDesignationE().toLowerCase().contains(recherche.getText().toLowerCase())).collect(Collectors.toList());
       
        int col=0;
       int row=0;
        
       try{
        
        for (Evenement e : ListeDesEvent){
            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("ItemE.fxml"));
            AnchorPane anchorPane = fxmlloader.load();
           
                   ItemEController itemControler = fxmlloader.getController();
                itemControler.SetData(e);
                
           if(col==2){
              col=0;
              row++;
           }
           grid.add(anchorPane,col++,row);
           //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                grid.setHgap(150);
           GridPane.setMargin(anchorPane, new Insets(10,10,10,10));
        }
       }catch(IOException e){
            e.printStackTrace();
            
        }
        
    }

    @FXML
    private void stat(MouseEvent event) {
        
        FXMLLoader loader = new FXMLLoader ();
                           loader.setLocation(getClass().getResource("../GUI/StatEvenet.fxml"));
                            try {
                                loader.load();
                                
                            } catch (Exception ex) {
                                System.err.println(ex.getMessage());
                            }
                         
                            StatEvenetController atc = loader.getController();
                            

      
                Parent root = loader.getRoot();
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
    }
    
    
    
    
    
    
}


