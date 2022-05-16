/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Table_Resto;
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
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import services.Table_RestoService;

/**
 * FXML Controller class
 *
 * @author Iheb
 */
public class ListeTableFXMLController implements Initializable {

    @FXML
    private Button btn_ajouter;

    @FXML
    private AnchorPane pane;
    @FXML
    private TableView<Table_Resto> TR_Table;
    @FXML
    private TableColumn<Table_Resto, Integer> NbrPlace;
    @FXML
    private TableColumn<Table_Resto, String> Etat;
    @FXML
    private TableColumn<Table_Resto, Image> ImageTable;
    @FXML
    private TableColumn<Table_Resto, String> Vip;
    @FXML
    private TableColumn<Table_Resto, Double> Prix;
    @FXML
    private TableColumn<Table_Resto, String> Operation;
    @FXML
    private Button btn_navD;
    @FXML
    private TextField chercherTR;
    @FXML
    private ComboBox<?> caractere_chercher;
    ObservableList chercherOptionTR = FXCollections.observableArrayList("Place","Prix");
    @FXML
    private Button btn_navRes;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
        caractere_chercher.setItems(chercherOptionTR);
        
        Table_RestoService SER_TR = new Table_RestoService();
        List Liste_TR = SER_TR.find();
        ObservableList list = FXCollections.observableArrayList(Liste_TR);
        
        TR_Table.setItems(list);
        NbrPlace.setCellValueFactory(new PropertyValueFactory<>("NbrPlace"));
        Etat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
        ImageTable.setCellValueFactory(new PropertyValueFactory<>("ImageTable"));
        Vip.setCellValueFactory(new PropertyValueFactory<>("Vip"));
        Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        
        
        
         Callback<TableColumn<Table_Resto, String>, TableCell<Table_Resto, String>> cellFoctory = (TableColumn<Table_Resto, String> param) -> {
            final TableCell<Table_Resto, String> cell = new TableCell<Table_Resto, String>() {
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
                                Table_Resto TR = TR_Table.getSelectionModel().getSelectedItem();
                                Table_RestoService SER_TAB_RES = new Table_RestoService() ;
                                SER_TAB_RES.supprimer(TR);
                                
                                refreshTable();
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Supprimer une Table Resto");
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
                            
                            Table_Resto TR = new Table_Resto();
                            TR = TR_Table.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../GUI/AjouterTableFXML.fxml"));
                            try {
                                loader.load();
                            } catch (Exception ex) {
                                System.err.println(ex.getMessage());
                            }
                            
                            AjouterTableFXMLController atc = loader.getController();
                            atc.setUpdate(true);
                            
                            atc.setTextFields(TR);
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
        list = FXCollections.observableArrayList(Liste_TR);
        TR_Table.setItems(list);
    
    }    
    public void refreshTable(){
        Table_RestoService SER_TR = new Table_RestoService();
        List Liste_TR = SER_TR.find();
        ObservableList list = FXCollections.observableArrayList(Liste_TR);
        TR_Table.setItems(list);
    }

    @FXML
    private void ajouter(ActionEvent event) throws Exception {
        try{
            btn_ajouter.getScene().getWindow().hide();
                Parent root =FXMLLoader.load(getClass().getResource("../GUI/AjouterTableFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                mainStage.setTitle("Ajouter Table");
                mainStage.initStyle(StageStyle.UTILITY);
                mainStage.show();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            
        }
    }

    private void modifier(ActionEvent event)throws Exception {
        
        try{
            
            Table_Resto TAB_RES = TR_Table.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../GUI/AjouterTableFXML.fxml"));
            Parent root = loader.load();
            AjouterTableFXMLController atc = loader.getController();
            atc.setUpdate(true);
            atc.setTextFields(TAB_RES);
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Modifier une Table");
            btn_ajouter.getScene().getWindow().hide();
            stage.show();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selectioner l'element a modifier");
            alert.setHeaderText("Aucun ligne n'a été selectione");
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
        
    }

    private void supprimer(ActionEvent event)throws Exception {
        try{
            Table_Resto TR = TR_Table.getSelectionModel().getSelectedItem();
            Table_RestoService SER_TAB_RES = new Table_RestoService() ;
            SER_TAB_RES.supprimer(TR);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Supprimer une Table Resto");
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
    }

    @FXML
    private void navD(ActionEvent event)throws Exception  {
        try{
            btn_navD.getScene().getWindow().hide();
                Parent root =FXMLLoader.load(getClass().getResource("../GUI/ListeDecorationFXML.fxml"));
                Stage DecorationStage = new Stage();
                Scene scene= new Scene(root);
                DecorationStage.setScene(scene);
                DecorationStage.setTitle("Liste des Decorations");
                DecorationStage.initStyle(StageStyle.UTILITY);
                DecorationStage.show();
        }catch(Exception e){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Problem here ");
            alert.setHeaderText("!!!");
            alert.setContentText(e.toString());
            alert.showAndWait();
            
        }
    }

    @FXML
    private void chercherTR(KeyEvent event) {
        
        List<Table_Resto> Liste_TR_Rester_Chercher = new ArrayList();
        Table_RestoService SER_TR = new Table_RestoService();
        Liste_TR_Rester_Chercher = SER_TR.find();
        ObservableList list = FXCollections.observableArrayList(Liste_TR_Rester_Chercher);
        TR_Table.setItems(list);
        
        if (caractere_chercher.getSelectionModel().getSelectedItem()=="Prix" ){
            Liste_TR_Rester_Chercher= Liste_TR_Rester_Chercher.stream().filter((e)->String.valueOf(e.getPrix()).contains(chercherTR.getText())).collect(Collectors.toList());

            list = FXCollections.observableArrayList(Liste_TR_Rester_Chercher);
            TR_Table.setItems(list);
            
        }
        if (caractere_chercher.getSelectionModel().getSelectedItem()=="Place" ){
            Liste_TR_Rester_Chercher= Liste_TR_Rester_Chercher.stream().filter((e)->String.valueOf(e.getNbrPlace()).contains(chercherTR.getText())).collect(Collectors.toList());

            list = FXCollections.observableArrayList(Liste_TR_Rester_Chercher);
            TR_Table.setItems(list);
            
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
