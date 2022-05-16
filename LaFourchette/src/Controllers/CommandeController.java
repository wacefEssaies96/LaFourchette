/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Plat;
import entities.commande;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
//import net.sf.jasperreports.view.JasperViewer;


import services.gestionCommande;
import services.gestionPlat;
import tests.Gmail;
import tests.GmailCommande;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class CommandeController implements Initializable { 

    @FXML
    private TableView<?> tvcommande;
    @FXML
    private TableColumn<?, ?> colidC;
    @FXML
    private TableColumn<?, ?> colidU;
    @FXML
    
    private TableColumn<?,?> colreferenceplat;
    @FXML
    private TableColumn<?, ?> colquantity;
    @FXML
    private TableColumn<?, ?> collivraison;
    @FXML
    private TableColumn<?, ?> colprixC;

    /**
     * Initializes the controller class.
     */
      // commandeplat c = null;
   
    gestionCommande fs = new gestionCommande();
    
    @FXML
    private Button btsupprimercommande;
    @FXML
    private Button btsendmail;
    @FXML
    private Button btpdf;
    
    public void refresh(){
        List commande = fs.afficherListcommande();
        ObservableList list = FXCollections.observableArrayList(commande);
        tvcommande.setItems(list);
        colidC.setCellValueFactory(new PropertyValueFactory<>("idC"));
        
        colidU.setCellValueFactory( new PropertyValueFactory<>("idU"));
        colreferenceplat.setCellValueFactory( new PropertyValueFactory<>("referenceplat"));
        colquantity.setCellValueFactory( new PropertyValueFactory<>("quantity"));
        collivraison.setCellValueFactory( new PropertyValueFactory<>("livraison"));
        
        colprixC.setCellValueFactory( new PropertyValueFactory<>("prixC"));
       // tfnomplat.setText(c1.getReference());
       
    }
    @FXML
    public void modifiercommande(javafx.event.ActionEvent event) throws IOException, ParseException {
         commande c=(commande) tvcommande.getSelectionModel().getSelectedItem();
          //Parent root = FXMLLoader.load(getClass().getResource("/controller/Edit.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
               "/Controllers/EditC.fxml"));
            Parent root = (Parent) loader.load();
     Scene scene =new Scene(root);
     Stage stage =new Stage();
     stage.setScene(scene);
     stage.show();
     
       EditCController modifier=loader.getController() ;
       modifier.setText(c);
       refresh();

    }
     
    @FXML
   private void supprimer(ActionEvent event) throws SQLException{

        
        gestionCommande gc=new gestionCommande();
   //       commandeplat t = tvcommande.getSelectionModel().getSelectedItem();
        commande t = (commande) tvcommande.getSelectionModel().getSelectedItem();
      //  Plat p = new Plat(c.getreference());
        gc.supprimer(t);
        refresh();

        //  showReview();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     refresh();
        
    } 
    private void smsaction(ActionEvent event) throws IOException {
      
      
     
    }
   /* public void showplat() {
        
        commandeplat gc=new commandeplat();
        try {
        ObservableList<commande> showlist ;
         showlist=gc.affichercommandeplat();
        //colnom.setCellValueFactory(value);
        colidC.setCellValueFactory(new PropertyValueFactory<>("idC"));
        
        colidU.setCellValueFactory( new PropertyValueFactory<>("idU"));
        colreference.setCellValueFactory( new PropertyValueFactory<>("reference"));
        coletatC.setCellValueFactory( new PropertyValueFactory<>("etat"));
        collivraison.setCellValueFactory( new PropertyValueFactory<>("livraison"));
        colprixC.setCellValueFactory( new PropertyValueFactory<>("prixC"));
        
                // col_nom_prenom.setCellValueFactory(new PropertyValueFactory<>("nom_prenom"));
                tvcommande.setItems(showlist);
        } catch (SQLException ex) {
               Logger.getLogger(PlatguiController.class.getName()).log(Level.SEVERE, null, ex);
           }
        
   }*/
    Connection cnx;
   
       
    @FXML
    private void sendmailaction(ActionEvent event) {
        
        GmailCommande.sendMail("noureddine.krichen@esprit.tn");
        
        
    }

    @FXML
    private void pdfaction(ActionEvent event) { 
        cnx = MyConnection.getInstance().getCnx();
        try{
            JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\Iheb\\Desktop\\LaFourchette\\src\\tests\\reportCommande.jrxml");
       
            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
           
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, cnx);
           
            JasperViewer viewer = new JasperViewer(jPrint, false);
           
            viewer.setTitle("Liste des Commandes");
            viewer.show();
           
        }catch(Exception e){
            System.out.println(e.getMessage());
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
