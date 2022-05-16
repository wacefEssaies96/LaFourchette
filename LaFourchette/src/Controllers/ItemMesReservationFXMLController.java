/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Decoration;
import entities.Reservation;
import entities.Table_Resto;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import services.DecorationService;
import services.Decoration_ReservationService;
import services.ReservationService;
import services.Reservation_Table_resto_Service;
import services.Table_RestoService;

/**
 * FXML Controller class
 *
 * @author Iheb
 */
public class ItemMesReservationFXMLController implements Initializable {

    @FXML
    private Label dateLabel;
    @FXML
    private ScrollPane scrollTR;
    @FXML
    private GridPane gridTR;
    @FXML
    private ScrollPane scrollD;
    @FXML
    private GridPane gridD;
    @FXML
    private Label user;
    
    ReservationService SER_RES =new ReservationService();
    Table_RestoService SER_TR =new Table_RestoService();
    Reservation_Table_resto_Service SER_RTR =new Reservation_Table_resto_Service();
    DecorationService SER_D =new DecorationService();
    Decoration_ReservationService SER_DR =new Decoration_ReservationService();

//    List<Reservation> Liste_RES =  new ArrayList();
//    List<Table_Resto> Liste_TR = SER_TR.find(); //new ArrayList();
//    List<Decoration> Liste_D =  SER_D.find();// new ArrayList();
    
    
    List Liste_RES =  new ArrayList();
    List<Table_Resto> Liste_TR = new ArrayList();
    List<Decoration>  Liste_D =  new ArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(List<Reservation> l) {
        
       // dateLabel.setText(Integer.toString(l.toString().indexOf("DateModification=")));
       // dateLabel.setText(l.toString());
       
       dateLabel.setText(l.get(0).getDateCreation().toString());
       user.setText(Integer.toString(l.get(0).getIdU()));
////        Liste_RES=SER_RES.afficher();
//        Liste_TR = SER_TR.find();
//        Liste_D =  SER_D.find();
//        
        for(int i = 0 ; i<l.size() ; i++){
            Liste_TR=SER_RTR.mesReservation(l.get(i).getIdR());
            Liste_D=SER_DR.mesReservation(l.get(i).getIdR());
//            if(l.get(i) instanceof Decoration){
//                Liste_D.add(l.get(i));
//            }
//            if(l.get(i) instanceof Table_Resto){
//                Liste_TR.add(l.get(i));
//            }
        }
        System.out.println(Liste_D);
        Table_Reserver(Liste_TR,gridTR,1);
        Decoration_Reserver(Liste_D,gridD,1);
        
    }
    private void Table_Reserver(List<Table_Resto> ll,GridPane g,int ligne){
        g.getChildren().clear();
        int column = 0;
        int row = 1;
        try {
            
            for (int i = 0; i < ll.size(); i++) {
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../GUI/ItemReserverFXML.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();


                ItemReserverFXMLController itemController = fxmlLoader.getController();
                itemController.setData(ll.get(i));


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
    
    
    private void Decoration_Reserver(List<Decoration> ll,GridPane g,int ligne){
        g.getChildren().clear();
        int column = 0;
        int row = 1;
        try {
            
            for (int i = 0; i < ll.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../GUI/ItemDecorationFXML.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();


                ItemDecorationFXMLController itemController = fxmlLoader.getController();
                itemController.setData(ll.get(i));


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

}
