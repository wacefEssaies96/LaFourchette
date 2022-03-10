/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author wacef
 * @author barki
 */
public class NewFXMain extends Application {
    
    @Override
//    public void start(Stage stage) {
//      try {
//            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            scene.getStylesheets().add(getClass().getResource("tableview.css").toExternalForm());
//            stage.setTitle("La Fourchette");
//            stage.show();
//        } catch (IOException ex) {
//           System.out.println(ex.getMessage());
//        }
//    }
//    public void start(Stage primaryStage) throws IOException {
// //Parent root = FXMLLoader.load(getClass().getResource("../GUI/EvenementFront.fxml"));
//Parent root = FXMLLoader.load(getClass().getResource("../GUI/Evenement.fxml"));
//            Scene scene = new Scene(root);
//            primaryStage.setScene(scene);
////            primaryStage.show();
    public void start(Stage stage) {
      try {
            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("tableview.css").toExternalForm());
            stage.setTitle("La Fourchette");
            stage.show();
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
