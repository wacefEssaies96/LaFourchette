/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import entities.Reservation;
import entities.Table_Resto;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.ReservationService;

/**
 * FXML Controller class
 *
 * @author Iheb
 */
public class MesReservationFXMLController implements Initializable {

    
    
    int IdU=2;
    ReservationService SER_RES = new ReservationService();
    List mesRes;
    
    
    @FXML
    private Button btn_Reserver;
    @FXML
    private ScrollPane scrollMesR;
    @FXML
    private GridPane gridMesR;
    @FXML
    private TextField chercherRes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mesRes=SER_RES.MesResrvation(IdU);
        listeMesRes(mesRes,gridMesR,1);
    }   
    
    private void listeMesRes(List ll,GridPane g,int ligne){
        g.getChildren().clear();
        
        int column = 0;
        int row = 1;
        try {
            
            for (int i = 0; i < ll.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../gui/ItemMesReservationFXML.fxml"));
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

    @FXML
    private void Reserver(ActionEvent event) {
        
                
        try {
            Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ReserverFXML.fxml"));
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
        
        Liste_mesRes_Chercher= Liste_mesRes_Chercher.stream().filter((e)->String.valueOf(e.getDateCreation().toString()).contains(chercherRes.getText())).collect(Collectors.toList());

        listeMesRes(Liste_mesRes_Chercher,gridMesR,1);
            
        
    }
    
}
