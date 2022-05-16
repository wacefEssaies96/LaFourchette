/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Decoration;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import services.DecorationService;

/**
 * FXML Controller class
 *
 * @author Iheb
 */
public class ListeDecorationFXMLController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private Button btn_ajouter;
    @FXML
    private TableView<Decoration> D_Table;
    @FXML
    private TableColumn<Decoration, String> Nom;
    @FXML
    private TableColumn<Decoration, String> ImageD;
    @FXML
    private TableColumn<Decoration, String> Prix;
    @FXML
    private TableColumn<Decoration, String> Operation;
    @FXML
    private Button btn_navTR;
    @FXML
    private TextField chercherD;
    @FXML
    private ComboBox<?> caractere_chercher;
    ObservableList chercherOptionD = FXCollections.observableArrayList("Nom","Prix");
    @FXML
    private Button btn_navRes;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        caractere_chercher.setItems(chercherOptionD);
        
        refreshTable();
        DecorationService SER_D = new DecorationService();
        List Liste_D = SER_D.find();
        ObservableList list = FXCollections.observableArrayList(Liste_D);
        
        D_Table.setItems(list);
        Nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        ImageD.setCellValueFactory(new PropertyValueFactory<>("ImageD"));
        Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        
        
        
         Callback<TableColumn<Decoration, String>, TableCell<Decoration, String>> cellFoctory = (TableColumn<Decoration, String> param) -> {
            final TableCell<Decoration, String> cell = new TableCell<Decoration, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            try{
                                Decoration D = D_Table.getSelectionModel().getSelectedItem();
                                DecorationService SER_D = new DecorationService() ;
                                SER_D.supprimer(D);
                                
                                refreshTable();
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Supprimer une Decoration");
                                alert.setHeaderText("Succées");
                                alert.setContentText("Supprission effectué avec Succées !");
                                alert.showAndWait();
                            }catch(Exception e){
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Selectioner l'element a Supprimer");
                                alert.setHeaderText("Aucun ligne n'a été selectione");
                                alert.setContentText(e.toString());
                                alert.showAndWait();
                            }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            Decoration D = new Decoration();
                            D = D_Table.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../GUI/AjouterDecorationFXML.fxml"));
                            try {
                                loader.load();
                            } catch (Exception ex) {
                                System.err.println(ex.getMessage());
                            }
                            
                            AjouterDecorationFXMLController adc = loader.getController();
                            adc.setUpdate(true);
                            
                            adc.setTextFields(D);
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };
            
            return cell;
        };
        Operation.setCellFactory(cellFoctory);
         Liste_D = SER_D.find();
        list = FXCollections.observableArrayList(Liste_D);
        D_Table.setItems(list);
    }    

    public void refreshTable(){
        DecorationService SER_D = new DecorationService();
        List Liste_D = SER_D.find();
        ObservableList list = FXCollections.observableArrayList(Liste_D);
        D_Table.setItems(list);
    }
    @FXML
    private void ajouter(ActionEvent event) {
        try{
            btn_navTR.getScene().getWindow().hide();
                Parent root =FXMLLoader.load(getClass().getResource("../GUI/AjouterDecorationFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                mainStage.setTitle("Ajouter Decoration");
                mainStage.initStyle(StageStyle.UTILITY);
                mainStage.show();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            
        }
    }

    @FXML
    private void navTR(ActionEvent event) {
        try{
            btn_ajouter.getScene().getWindow().hide();
                Parent root =FXMLLoader.load(getClass().getResource("../GUI/ListeTableFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                mainStage.setTitle("Liste des Tables");
                mainStage.initStyle(StageStyle.UTILITY);
                mainStage.show();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            
        }
    }

    @FXML
    private void chercherD(KeyEvent event) {
        List<Decoration> Liste_D_Rester_Chercher = new ArrayList();
         DecorationService SER_D = new DecorationService();
        Liste_D_Rester_Chercher = SER_D.find();
        ObservableList list = FXCollections.observableArrayList(Liste_D_Rester_Chercher);
        D_Table.setItems(list);
        
        if (caractere_chercher.getSelectionModel().getSelectedItem()=="Prix" ){
            Liste_D_Rester_Chercher= Liste_D_Rester_Chercher.stream().filter((e)->String.valueOf(e.getPrix()).contains(chercherD.getText())).collect(Collectors.toList());

            list = FXCollections.observableArrayList(Liste_D_Rester_Chercher);
            D_Table.setItems(list);
            
        }
        if (caractere_chercher.getSelectionModel().getSelectedItem()=="Nom" ){
            Liste_D_Rester_Chercher= Liste_D_Rester_Chercher.stream().filter((e)->String.valueOf(e.getNom().toLowerCase()).contains(chercherD.getText().toLowerCase())).collect(Collectors.toList());
            
            list = FXCollections.observableArrayList(Liste_D_Rester_Chercher);
            D_Table.setItems(list);
            
        }
    }

    @FXML
    private void navRes(ActionEvent event) {
        try{
            btn_ajouter.getScene().getWindow().hide();
                Parent root =FXMLLoader.load(getClass().getResource("../GUI/ReservationAdminFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                mainStage.setTitle("Liste des Tables");
                mainStage.initStyle(StageStyle.UTILITY);
                mainStage.show();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            
        }
    }
     @FXML
    private void homeIner(MouseEvent event) throws IOException {
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
