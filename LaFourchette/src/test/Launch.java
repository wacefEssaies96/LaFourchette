/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import controller.PlatguiController;

import entities.commande;
import entities.Plat;
//
import service.gestionCommande;
import utiles.MyConnection;
import service.gestionPlat;
import entities.commandeplat;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.sql.Connection;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Launch extends Application {

    public static Stage stage = null;

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/controller/AdminPanel.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //stage.initStyle(StageStyle.UNDECORATED);
        this.stage = stage;
        
        stage.show();
    }
    /**
     * @param args the command line arguments
     */
   
        
        
        
        
    
    public static void main(String[] args) {
        
        launch(args);
    }

}