/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Table_Resto;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.Table_RestoService;

/**
 * FXML Controller class
 *
 * @author Iheb
 */
public class AjouterTableFXMLController implements Initializable {

    ObservableList<String> EtatTable = FXCollections.observableArrayList( "Disponible","Reserve");
    ObservableList<String> TableVip = FXCollections.observableArrayList( "Oui","Non");
    public static boolean update;
    int IdTR;
    Table_RestoService SER_TR = new Table_RestoService();
    
    @FXML
    private TextField colnbrplace;
    @FXML
    private ChoiceBox coletat;
    @FXML
    private ChoiceBox colvip;
    @FXML
    private TextField colprix;
    @FXML
    private AnchorPane pane;
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
        coletat.setValue("Disponible");
        coletat.setItems(EtatTable);
        colvip.setValue("Non");
        colvip.setItems(TableVip);
        
        
    }    
    

    @FXML
    private void ajouter(ActionEvent event)throws IOException {
        
        String NbrPlaceTR = colnbrplace.getText();
        String ImageTR = file_path.getText();
        String PrixTR = colprix.getText();
        
        if (NbrPlaceTR.isEmpty()||ImageTR.isEmpty()||PrixTR.isEmpty()||img.getImage()== null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Champ(s) Vide(s)");
            alert.setHeaderText("Probleme");
            alert.setContentText("Remplir Tous Les Champs !");
            alert.showAndWait();
        }
        else{
            Table_Resto TR =new Table_Resto();
            try {
                TR.setNbrPlace(Integer.parseInt(NbrPlaceTR));
                TR.setEtat(coletat.getValue().toString());
                TR.setImageTable(ImageTR);
                TR.setVip(colvip.getValue().toString());
                TR.setPrix(Double.parseDouble(PrixTR));
                if(update == false){
                    SER_TR.ajouter(TR);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ajouter une Table Resto"+TR.getIdT());
                    alert.setHeaderText("Succées");
                    alert.setContentText("Ajout effectué avec Succées !");
                    alert.showAndWait();
                }
                else{
                    TR.setIdT(IdTR);
                    System.out.println(" modification \n"+TR.toString());
                    
                    
                    
                    SER_TR.modifier(TR);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Modifier une Table Resto"+TR.getIdT());
                    alert.setHeaderText("Succées");
                    alert.setContentText("Modification effectué avec Succées !");
                    alert.showAndWait();
                }
                
                try{
                    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    if(update==true){
                        stage.close();
                    }else{
                        Parent root =FXMLLoader.load(getClass().getResource("../GUI/ListeTableFXML.fxml"));
                        Scene scene= new Scene(root);
                        stage.setScene(scene);
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
    private void clean() {
        colnbrplace.setText(null);
        file_path.setText(null);
        img.setImage(null);
        colprix.setText(null);
        
    }

    public static void setUpdate(boolean update) {
        AjouterTableFXMLController.update = update;
    }
    public void setTextFields(Table_Resto TAB_RES){
        IdTR=TAB_RES.getIdT();
        colnbrplace.setText(String.valueOf(TAB_RES.getNbrPlace()));
        coletat.setValue(TAB_RES.getEtat());
        file_path.setText(TAB_RES.getImageTable());
        colvip.setValue(TAB_RES.getVip());
        colprix.setText(String.valueOf(TAB_RES.getPrix()));

        String imageTR = file_path.getText();
        String  path = imageTR.replace("\\", "/");
        Image imageTRview = new Image("file:"+path, 110, 110, false, true);
        img.setImage(imageTRview);
    }

    @FXML
    private void homeTR(MouseEvent event) throws IOException {
        try{
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            Parent root =FXMLLoader.load(getClass().getResource("../GUI/ListeTableFXML.fxml"));
            Scene scene= new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Liste des Tables");
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
    
}
