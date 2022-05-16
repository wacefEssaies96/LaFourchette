/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Reservation;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import services.ReservationService;

/**
 * FXML Controller class
 *
 * @author Iheb
 */
public class ReservationAdminFXMLController implements Initializable {
    
    int IdU=2;
    ReservationService SER_RES = new ReservationService();
    List mesRes;
    
    
    @FXML
    private ScrollPane scrollMesR;
    @FXML
    private GridPane gridMesR;
    @FXML
    private TextField chercherRes;
    @FXML private ComboBox<?> caractere_chercher;

    ObservableList chercherOptionRes = FXCollections.observableArrayList("Date","Utilisateur");
    @FXML
    private Button btn_navD;
    @FXML
    private Button btn_navTR;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        caractere_chercher.setItems(chercherOptionRes);
        mesRes=SER_RES.afficher();
        listeMesRes(mesRes,gridMesR,1);
    }   
    
    private void listeMesRes(List ll,GridPane g,int ligne){
        g.getChildren().clear();
        
        int column = 0;
        int row = 1;
        try {
            
            for (int i = 0; i < ll.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../GUI/ItemMesReservationFXML.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();


                ItemMesReservationFXMLController imrc = fxmlLoader.getController();
                List lo=new ArrayList();
                lo.add(ll.get(i));
                imrc.setData(lo);


                if (column == ligne) {
                    column = 0;
                    row++;
                }
//                Random rn = new Random();
//                int colvar = rn.nextInt(9) + 1;
//                System.out.println(" colvar"+colvar);
//                anchorPane.setStyle("-fx-background-color: #"
//                        + couleurs.get(i%10)
//                        + ";\n    -fx-background-radius: 30;");
                g.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                g.setMinWidth(Region.USE_COMPUTED_SIZE);
                g.setPrefWidth(Region.USE_COMPUTED_SIZE);
                g.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                g.setMinHeight(Region.USE_COMPUTED_SIZE);
                g.setPrefHeight(Region.USE_COMPUTED_SIZE);
                g.setMaxHeight(Region.USE_PREF_SIZE);

                
                GridPane.setMargin(anchorPane, new Insets(10));
                
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void Reserver(ActionEvent event) {
        
                
        try {
            Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ReserverFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Mes Reservation");
            stage.initStyle(StageStyle.UTILITY);
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void chercherRes(KeyEvent event) {
        
        List<Reservation> Liste_mesRes_Chercher = new ArrayList();
        
        Liste_mesRes_Chercher=SER_RES.MesResrvation(IdU);
        
        listeMesRes(Liste_mesRes_Chercher,gridMesR,1);
        
        
        if (caractere_chercher.getSelectionModel().getSelectedItem()=="Date" ){
            Liste_mesRes_Chercher= Liste_mesRes_Chercher.stream().filter((e)->String.valueOf(e.getDateCreation().toString()).contains(chercherRes.getText())).collect(Collectors.toList());

            listeMesRes(Liste_mesRes_Chercher,gridMesR,1);
            
        }
        if (caractere_chercher.getSelectionModel().getSelectedItem()=="Utilisateur" ){
           
            
            Liste_mesRes_Chercher= Liste_mesRes_Chercher.stream().filter((e)->String.valueOf(e.getIdU()).contains(chercherRes.getText())).collect(Collectors.toList());

            listeMesRes(Liste_mesRes_Chercher,gridMesR,1);
            System.out.println(" user chercher "+Liste_mesRes_Chercher.toString());
            
            
        }
            
        
    }

    @FXML
    private void navD(ActionEvent event) {
         try{
            btn_navD.getScene().getWindow().hide();
                Parent root =FXMLLoader.load(getClass().getResource("../GUI/ListeDecorationFXML.fxml"));
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
    private void navTR(ActionEvent event) {
         try{
            btn_navTR.getScene().getWindow().hide();
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
    private void homeIner(MouseEvent event)  throws IOException{
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
