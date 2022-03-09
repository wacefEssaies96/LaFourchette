/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

/**
 *
 * @author user
 */

import entities.Utilisateur;
import services.UtilisateurService;
import entities.UtilisateurE;
import services.UtilisateurCrud;
import entities.Evenement;
import entities.Reservation;
import entities.Table_Resto;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.EvenementCrud;
import services.ReservationService;
import services.Table_RestoService;
import utils.MyConnection;

import services.FournisseurService;
import services.ProduitFournisseurService;
import services.ProduitService;

public class MainClass extends Application {
     private double x=0;
    private double y=0;
    public void start(Stage stage) throws IOException {
        
         //Parent root = FXMLLoader.load(getClass().getResource("/tests/NewInterface1.fxml"));
         /*
         Parent root1 = FXMLLoader.load(getClass().getResource("/tests/NewInterface.fxml"));
         
         Scene scene;
         
        scene = new Scene(root1);
         stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
      
        stage.setScene(scene);
         */
        /*
          Scene scene = new Scene(root);
        
          root.setOnMousePressed(event -> {
          x=event.getSceneX();
          y=event.getSceneY();
          
          });
          root.setOnMouseDragged(event -> {
          stage.setX(event.getScreenX() - x);
          stage.setY(event.getScreenY() -y);
          
          });
        
         stage.initStyle(StageStyle.TRANSPARENT);
       
        stage.setScene(scene);
      
        stage.setScene(scene);
     */
        
        
        Parent root2 = FXMLLoader.load(getClass().getResource("/tests/Dashboard.fxml"));
           Scene scene = new Scene(root2);
            stage.setScene(scene);
      stage.initStyle(StageStyle.TRANSPARENT);
        
        stage.show();
   
     /*
   try{
       Parent root3 = FXMLLoader.load(getClass().getResource("/tests/NewInterfaces2.fxml"));
           Scene scene = new Scene(root3);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
        
        stage.show();
   }catch(IOException ex){
        // System.out.println(ex.getMessage());
         ex.printStackTrace();
   }
   */
   
   //**********************Calulatrice********************
   /*
    try{
       Parent root3 = FXMLLoader.load(getClass().getResource("/tests/Calculatrice.fxml"));
           Scene scene = new Scene(root3);
           
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
          
        stage.setScene(scene);
        stage.show();
   }catch(IOException ex){
        // System.out.println(ex.getMessage());
         ex.printStackTrace();
   }
    */
   
    }
public class MainClass {
    public static void main(String[] args) {
        
        ProduitService ps = new ProduitService();
        FournisseurService fs = new FournisseurService();
        ProduitFournisseurService pfs = new ProduitFournisseurService();
        
        //System.out.println(ps.afficherListeProduits());
        //System.out.println(fs.afficherListFournisseur());
        //fs.afficherListFournisseur();


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
       // bd.getInstance();
        //personneservice sp = new personneservice() ;
     
       //User a = new User(1,"chiheb","boubakri","Male","csczec");
      // sp.ajouter(a);
        //sp.find();
        //sp.supprimer(a);
      //sp.modifier(a);
    }
    
}

