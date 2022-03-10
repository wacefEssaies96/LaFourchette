/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Reservation;
import entities.Reservation_Table_resto;
import tests.ReservationListener;
import entities.Table_Resto;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ReservationService;
import services.Reservation_Table_resto_Service;
import services.Table_RestoService;
import tests.MainClass;

/**
 * FXML Controller class
 *
 * @author Iheb
 */
public class ReserverControllerFXML implements Initializable {

    @FXML private VBox chosenFruitCard;
    @FXML private Label fruitNameLable;
    @FXML private Label fruitPriceLabel;
    @FXML private ImageView fruitImg;
    @FXML private ScrollPane scroll;
    @FXML private GridPane grid;
    @FXML private ScrollPane scroll1;
    @FXML private GridPane grid1;
    @FXML private TextField chercherTR;
    @FXML private ComboBox<?> caractere_chercher;
    
    int IdU=2;
    int IdRes;
    int cpt;
    private Image image;
    private ReservationListener RListener;    

    Reservation_Table_resto_Service SER_RESTR = new Reservation_Table_resto_Service();
    Table_RestoService SER_TR =new Table_RestoService();
    Table_Resto TR =new Table_Resto();
    ReservationService SER_RES = new ReservationService();
    
    List<Table_Resto> Liste_TR =  new ArrayList();
    List<Table_Resto> Liste_TR_aRes = new ArrayList();
    List<Table_Resto> Liste_TR_Rester = new ArrayList();
    ArrayList couleurs = new ArrayList() {{
        add("6A7324");
        add("A7F45B");//a5dher bared
        add("291536");//violet
        add("C16C31");//orange rame9
        add("A16B31");//bouni
        add("111111");//noire
        add("FB5D03");//orange
        add("36B605");//a5dher
        add("5F060E");//a7mer game9
        add("E7C00F");//asfer
   }};   
    
    ObservableList chercherOptionTR = FXCollections.observableArrayList("Place","Prix");
    
    
    ButtonType Oui = new ButtonType("Oui", ButtonBar.ButtonData.YES);
    ButtonType Non = new ButtonType("Non", ButtonBar.ButtonData.OK_DONE);
    ButtonType Annuler = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        caractere_chercher.setItems(chercherOptionTR);
        
        Liste_TR = SER_TR.TR_Dispo();
        //item choisit
        if (Liste_TR.size() > 0) {
            this.TR=Liste_TR.get(0);
            setChosenTable_Resto(Liste_TR.get(0));
            RListener = new ReservationListener() {
                @Override
                public void onClickListener(Table_Resto TR) {
                    setChosenTable_Resto(TR);
                }
            };
        }
        modifliste(Liste_TR_aRes);
    }  

    

    private void setChosenTable_Resto(Table_Resto TR) {
        this.TR=TR;
        fruitNameLable.setText(String.valueOf(TR.getNbrPlace()));
        fruitPriceLabel.setText(MainClass.CURRENCY + String.valueOf(TR.getPrix()));
        

        String imageTR = TR.getImageTable();
        String  path = imageTR.replace("\\", "/");
        
        image = new Image("file:"+path, 110, 110, false, true);
        fruitImg.setImage(image);
        Random rn = new Random();
        int colvar = rn.nextInt(9) + 1;
        chosenFruitCard.setStyle("-fx-background-color: #"
                + couleurs.get(colvar)
                + ";\n    -fx-background-radius: 30;");
    }
    private void Table_dispo_aReserver(List<Table_Resto> ll,GridPane g,int ligne){
        g.getChildren().clear();
        int column = 0;
        int row = 1;
        try {
            
            for (int i = 0; i < ll.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../gui/ItemReserverFXML.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();


                ItemReserverFXMLController itemController = fxmlLoader.getController();
                itemController.setData(ll.get(i),RListener);


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
                g.add(anchorPane, column++, row); 
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
        
        if(Liste_TR_aRes.isEmpty()){
            ButtonType Oui = new ButtonType("D'accord", ButtonBar.ButtonData.YES);
            Alert alert = new Alert(Alert.AlertType.WARNING,
                " Ajouter une reservation puis Reserver",
                Oui);
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(AlertType.INFORMATION,
                    "Voulez vous ajouter des decoration a votre reservation.",
                    Oui,
                    Non,
                    Annuler);

            alert.setTitle("Ajouter Decoration");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.orElse(Annuler) == Oui) {
                ajouterReservation_Table_resto(alert);
                // Interface decoration
                
                
                try {
                    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../gui/DecorationFXML.fxml"));

                    DecorationControllerFXML dc = fxmlLoader.getController();
                    dc.IdR=this.IdRes;
                    Parent root;
                    root = fxmlLoader.load();
                    Scene scene= new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                
            }
            if (result.orElse(Annuler) == Non) {
                ajouterReservation_Table_resto(alert);
            }
        }
    }
    
    public void ajouterReservation_Table_resto(Alert alert){
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            
            // date et user id
            Reservation RES = new Reservation(1, IdU, sqlDate,sqlDate);
            SER_RES.ajouter(RES);
            
            //recuperer l'id du reservatoion ajouter maintenant Cbon
//            List<Reservation> listeRes =null;
            RES=SER_RES.RecuperedernierReservation(IdU);
            this.IdRes=RES.getIdR();
            
            // ajouter tous les reservation selectione Liste_TR_aRes --> remplacer TR.getIdT() disponible --> non disponible cbon
            
            Reservation_Table_resto RESTR;
            for(int i=0;i<Liste_TR_aRes.size();i++){
                RESTR = new Reservation_Table_resto(1,RES.getIdR(),Liste_TR_aRes.get(i).getIdT());
                SER_RESTR.ajouter(RESTR);
                SER_TR.table_Reserve(Liste_TR_aRes.get(i));
            }
            
            alert = new Alert(AlertType.INFORMATION,
                " Votre reservation ajouter avec succes",
                Oui);
            alert.showAndWait();
    }

    @FXML
    private void ajReserver(ActionEvent event) {
        
            int O=0;
            for(int i=0;i<Liste_TR_Rester.size();i++){
                if(Liste_TR_Rester.get(i)==TR){

                    //btn_ajRes.setText("Ajouter au Reservation");
                    O+=1;
                    Liste_TR_aRes.add(TR);
                    modifliste(Liste_TR_aRes);
                }
            }
            if(O==0){
                //annuler
                for(int i=0;i<Liste_TR_aRes.size();i++){
                    if(Liste_TR_aRes.get(i)==TR){

                        //btn_ajRes.setText("Annuler cette Reservation");
                        Liste_TR_Rester.add(TR);
                        Liste_TR_aRes.remove(TR);
                        //Table a reserver
                        Table_dispo_aReserver(Liste_TR_aRes,grid1,1);
                        //Table a rester disponible
                        Table_dispo_aReserver(Liste_TR_Rester,grid,3);
                    }
                }

            }


            System.out.println(" Liste_TR_aRes : "+Liste_TR_aRes.toString());
        
    }
    
    private void modifliste(List<Table_Resto> LaRes){
        
        
        
        //Table a reserver
        Table_dispo_aReserver(LaRes,grid1,1);
        
        Liste_TR_Rester.clear();
                
        for(int i=0;i<Liste_TR.size();i++){
            cpt=0;
            for(int j=0;j<LaRes.size();j++){
                if(Liste_TR.get(i)== LaRes.get(j)){
                    cpt+=1;
                }
            }
            if(cpt==0){
                Liste_TR_Rester.add(Liste_TR.get(i));
            }  
            
        }
        //Table a rester disponible
        Table_dispo_aReserver(Liste_TR_Rester,grid,3);
        
    }
    @FXML
    private void chercherTR(KeyEvent event) {
        
        List<Table_Resto> Liste_TR_Rester_Chercher = new ArrayList();
        
        Table_dispo_aReserver(Liste_TR,grid,3);
        if (caractere_chercher.getSelectionModel().getSelectedItem()=="Prix" ){
            Liste_TR_Rester_Chercher= Liste_TR_Rester.stream().filter((e)->String.valueOf(e.getPrix()).contains(chercherTR.getText())).collect(Collectors.toList());

            Table_dispo_aReserver(Liste_TR_Rester_Chercher,grid,3);
            
        }
        if (caractere_chercher.getSelectionModel().getSelectedItem()=="Place" ){
            Liste_TR_Rester_Chercher= Liste_TR_Rester.stream().filter((e)->String.valueOf(e.getNbrPlace()).contains(chercherTR.getText())).collect(Collectors.toList());

            Table_dispo_aReserver(Liste_TR_Rester_Chercher,grid,3);
            
        }
    }
    

    

}
