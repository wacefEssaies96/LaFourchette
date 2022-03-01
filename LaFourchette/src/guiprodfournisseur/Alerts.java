/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiprodfournisseur;

import javafx.scene.control.Alert;

/**
 *
 * @author wacef
 */
public class Alerts {

    //Ajout
    public static void ajoutAlertSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajout");
        alert.setHeaderText(null);
        alert.setContentText("Ajout avec succées !");
        alert.showAndWait();
    }
    public static void ajoutAlertFail() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ajout");
        alert.setHeaderText(null);
        alert.setContentText("Ajout échoué !");
        alert.showAndWait();
    }
    
    //Modif
    public static void modifAlertSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modif");
        alert.setHeaderText(null);
        alert.setContentText("Modification avec succées !");
        alert.showAndWait();
    }
    public static void modifAlertFail() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Modif");
        alert.setHeaderText(null);
        alert.setContentText("Modification échouée !");
        alert.showAndWait();
    }
    
    //Suppresion
    public static void suppressionAlertSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Suppression");
        alert.setHeaderText(null);
        alert.setContentText("Suppression avec succées !");
        alert.showAndWait();
    }
    public static void suppressionAlertFail() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Suppression");
        alert.setHeaderText(null);
        alert.setContentText("Suppression échoué !");
        alert.showAndWait();
    }
    
    
}
