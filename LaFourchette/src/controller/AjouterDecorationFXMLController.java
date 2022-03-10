/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
import static controller.AjouterDecorationFXMLController.update;
import entities.Decoration;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.DecorationService;

/**
 * FXML Controller class
 *
 * @author Iheb
 */
public class AjouterDecorationFXMLController implements Initializable {

    public static boolean update;
    int IdD;
    DecorationService SER_D = new DecorationService();
    
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField colnom;
    @FXML
    private JFXTextField colprix;
    @FXML
    private ImageView img;
    @FXML
    private Label file_path;
    @FXML
    private Button Selectioner;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public static void setUpdate(boolean update) {
        AjouterDecorationFXMLController.update = update;
    }
    public void setTextFields(Decoration D){
        IdD=D.getIdD();
        colnom.setText(D.getNom());
        file_path.setText(D.getImageD());
        colprix.setText(String.valueOf(D.getPrix()));

        String imageD = file_path.getText();
        String  path = imageD.replace("\\", "/");
        Image imageDview = new Image("file:"+path, 110, 110, false, true);
        img.setImage(imageDview);
    }
    @FXML
    private void homeD(MouseEvent event) {
        try{
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            Parent root =FXMLLoader.load(getClass().getResource("../gui/ListeDecorationFXML.fxml"));
            Scene scene= new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Liste des Decorations");
            stage.show();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @FXML
    private void insertImage(ActionEvent event) {
        
        FileChooser open = new FileChooser();

        Stage stage = (Stage) pane.getScene().getWindow();

        File file = open.showOpenDialog(stage);

        if (file != null) {

            String path = file.getAbsolutePath();

            file_path.setText(path);

            Image image = new Image(file.toURI().toString(), 110, 110, false, true);

            img.setImage(image);

        } else {

            System.out.println("NO DATA EXIST!");

        }
    }
    
    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        
        String Nom = colnom.getText();
        String ImageD = file_path.getText();
        String PrixTR = colprix.getText();
        
        if (Nom.isEmpty()||ImageD.isEmpty()||PrixTR.isEmpty()||img.getImage()== null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Champ(s) Vide(s)");
            alert.setHeaderText("Probleme");
            alert.setContentText("Remplir Tous Les Champs !");
            alert.showAndWait();
        }
        else{
            Decoration D =new Decoration();
            try {
                D.setNom((Nom));
                D.setImageD(ImageD);
                D.setPrix(Double.parseDouble(PrixTR));
                if(update == false){
                    SER_D.ajouter(D);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ajouter une Decoration"+D.getIdD());
                    alert.setHeaderText("Succées");
                    alert.setContentText("Ajout effectué avec Succées !");
                    alert.showAndWait();
                }
                else{
                    D.setIdD(IdD);
                    System.out.println(" modification \n"+D.toString());
                    
                    
                    
                    SER_D.modifier(D);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Modifier une Decoration"+D.getIdD());
                    alert.setHeaderText("Succées");
                    alert.setContentText("Modification effectué avec Succées !");
                    alert.showAndWait();
                }
                
                try{
                    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    if(update==true){
                        stage.close();
                    }else{
                        Parent root =FXMLLoader.load(getClass().getResource("../gui/ListeDecorationFXML.fxml"));
                        Scene scene= new Scene(root);
                        stage.setScene(scene);
                        //stage.initStyle(StageStyle.UTILITY);
                        stage.show();
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            } catch (NumberFormatException exception) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Exception");
                alert.setHeaderText("NumberFormatException");
                alert.setContentText(exception.toString());
                alert.showAndWait();
            } catch (Exception exception) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Exception");
                alert.setHeaderText("Other Exception");
                alert.setContentText(exception.toString());
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void clean(ActionEvent event) {
        colnom.setText(null);
        file_path.setText(null);
        img.setImage(null);
        colprix.setText(null);
    }
}
