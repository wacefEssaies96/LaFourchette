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

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainClass extends Application{
    
    public static final String CURRENCY = "DT";
    @Override
    public void start(Stage primaryStage) {
        
       FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/MesReservationFXML.fxml"));
       //FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ReserverFXML.fxml"));
       //FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/DecorationFXML.fxml"));
       
       //FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ListeTableFXML.fxml"));
                
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Reserver");
            primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

  }  
}
