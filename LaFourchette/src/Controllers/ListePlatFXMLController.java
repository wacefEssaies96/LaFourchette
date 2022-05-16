/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Plat;
import entities.Reservation;
import entities.Reservation_Table_resto;
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
import javafx.scene.control.Button;
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
import services.gestionPlat;
import services.gestioncommandeplat;
import tests.MainClass;
import tests.PlatListener;
import tests.ReservationListener;

/**
 * FXML Controller class
 *
 * @author Iheb
 */
public class ListePlatFXMLController implements Initializable {

    @FXML
    private Button btn_Commander;
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private Button btn_ajPanier;
    @FXML
    private ScrollPane scroll1;
    @FXML
    private GridPane grid1;
    @FXML
    private TextField chercherP;
    @FXML
    private ComboBox<?> caractere_chercher;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
   
    int IdU=2;
    int IdRes;
    int cpt;
    private Image image;
    private PlatListener PListener;    

    gestioncommandeplat SER_CP = new gestioncommandeplat();
    gestionPlat SER_P =new gestionPlat();
    Plat P =new Plat();
    
    List<Plat> Liste_P =  new ArrayList();
    List<Plat> Liste_P_aRes = new ArrayList();
    List<Plat> Liste_P_Rester = new ArrayList();
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
    
    ObservableList chercherOptionP = FXCollections.observableArrayList("Designation","Prix");
    
    
    ButtonType Oui = new ButtonType("Oui", ButtonBar.ButtonData.YES);
    ButtonType Non = new ButtonType("Non", ButtonBar.ButtonData.OK_DONE);
    ButtonType Annuler = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        caractere_chercher.setItems(chercherOptionP);
        
        Liste_P = SER_P.getplatlist();
        //item choisit
        if (Liste_P.size() > 0) {
            this.P=Liste_P.get(0);
            setChosenPlat(Liste_P.get(0));
            PListener = new PlatListener() {
                @Override
                public void onClickListener(Plat P) {
                    setChosenPlat(P);
                }
            };
        }
        modifliste(Liste_P_aRes);
    }  

    

    private void setChosenPlat(Plat P) {
        this.P=P;
        fruitNameLable.setText(String.valueOf(P.getDesignation()));
        fruitPriceLabel.setText(MainClass.CURRENCY + String.valueOf(P.getPrix()));
        

        String imageP = P.getImageP();
        String  path = imageP.replace("\\", "/");
        
        image = new Image("file:"+path, 110, 110, false, true);
        fruitImg.setImage(image);
        Random rn = new Random();
        int colvar = rn.nextInt(9) + 1;
        chosenFruitCard.setStyle("-fx-background-color: #"
                + couleurs.get(colvar)
                + ";\n    -fx-background-radius: 30;");
    }
    private void Plat_aReserver(List<Plat> ll,GridPane g,int ligne){
        g.getChildren().clear();
        int column = 0;
        int row = 1;
        try {
            
            for (int i = 0; i < ll.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../GUI/ItemPlatFXML.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();


                ItemPlatFXMLController itemController = fxmlLoader.getController();
                itemController.setData(ll.get(i),PListener);


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
    private void Commander(ActionEvent event) {
        
        if(Liste_P_aRes.isEmpty()){
            ButtonType Oui = new ButtonType("D'accord", ButtonBar.ButtonData.YES);
            Alert alert = new Alert(Alert.AlertType.WARNING,
                " Ajouter un Plat puis Commander",
                Oui);
            alert.showAndWait();
        }
        else{
            
            ButtonType Oui = new ButtonType("D'accord", ButtonBar.ButtonData.YES);
            Alert alert = new Alert(Alert.AlertType.WARNING,
                " Commander",
                Oui);
            alert.showAndWait();
//            Alert alert = new Alert(Alert.AlertType.INFORMATION,
//                    "Voulez vous ajouter des decoration a votre reservation.",
//                    Oui,
//                    Non,
//                    Annuler);
//
//            alert.setTitle("Ajouter Decoration");
//            Optional<ButtonType> result = alert.showAndWait();
//
//            if (result.orElse(Annuler) == Oui) {
//                ajouterReservation_Table_resto(alert);
//                // Interface decoration
//                
//                
//                try {
//                    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//                    FXMLLoader fxmlLoader = new FXMLLoader();
//                    fxmlLoader.setLocation(getClass().getResource("../gui/DecorationFXML.fxml"));
//
//                    DecorationControllerFXML dc = fxmlLoader.getController();
//                    dc.IdR=this.IdRes;
//                    Parent root;
//                    root = fxmlLoader.load();
//                    Scene scene= new Scene(root);
//                    stage.setScene(scene);
//                    stage.show();
//                } catch (Exception e) {
//                    System.out.println(e.getMessage());
//                }
//                
//            }
//            if (result.orElse(Annuler) == Non) {
//                ajouterReservation_Table_resto(alert);
//            }
        }
    }
    
    public void ajouterCommande_Palt(Alert alert){
        
            
        
            //SER_RES.ajouter(RES);
            //ajouer commande
            
            //recuperer l'id du commande ajouter maintenant Cbon
            
            //RES=SER_RES.RecuperedernierReservation(IdU);
            //this.IdRes=RES.getIdR();
            
            // ajouter tous les plat selectione Liste_P_aRes --> rquantite diminue cbon
            
//            Reservation_Table_resto RESTR;
//            for(int i=0;i<Liste_TR_aRes.size();i++){
//                RESTR = new Reservation_Table_resto(1,RES.getIdR(),Liste_TR_aRes.get(i).getIdT());
//                SER_RESTR.ajouter(RESTR);
//                SER_TR.table_Reserve(Liste_TR_aRes.get(i));
//            }
            
            alert = new Alert(Alert.AlertType.INFORMATION,
                " Votre Commande ajouter avec succes",
                Oui);
            alert.showAndWait();
    }

    @FXML
    private void ajPanier(ActionEvent event) {
        
            int O=0;
            for(int i=0;i<Liste_P_Rester.size();i++){
                if(Liste_P_Rester.get(i)==P){

                    O+=1;
                    Liste_P_aRes.add(P);
                    modifliste(Liste_P_aRes);
                }
            }
            if(O==0){
                //annuler
                for(int i=0;i<Liste_P_aRes.size();i++){
                    if(Liste_P_aRes.get(i)==P){

                        
                        Liste_P_Rester.add(P);
                        Liste_P_aRes.remove(P);
                        
                        Plat_aReserver(Liste_P_aRes,grid1,1);
                        
                        Plat_aReserver(Liste_P_Rester,grid,3);
                    }
                }

            }


            System.out.println(" Liste_P_aRes : "+Liste_P_aRes.toString());
        
    }
    
    private void modifliste(List<Plat> LaC){
        
        
        
        //Plat a reserver
        Plat_aReserver(LaC,grid1,1);
        
        Liste_P_Rester.clear();
                
        for(int i=0;i<Liste_P.size();i++){
            cpt=0;
            for(int j=0;j<LaC.size();j++){
                if(Liste_P.get(i)== LaC.get(j)){
                    cpt+=1;
                }
            }
            if(cpt==0){
                Liste_P_Rester.add(Liste_P.get(i));
            }  
            
        }
        //Plat a rester 
        Plat_aReserver(Liste_P_Rester,grid,3);
        
    }
    

    @FXML
    private void chercherP(KeyEvent event) {
        
        List<Plat> Liste_Plat_Chercher = new ArrayList();
        
        Plat_aReserver(Liste_P,grid,3);
        if (caractere_chercher.getSelectionModel().getSelectedItem()=="Prix" ){
            Liste_Plat_Chercher= Liste_Plat_Chercher.stream().filter((e)->String.valueOf(e.getPrix()).contains(chercherP.getText())).collect(Collectors.toList());

            Plat_aReserver(Liste_Plat_Chercher,grid,3);
            
        }
        if (caractere_chercher.getSelectionModel().getSelectedItem()=="Designation" ){
            Liste_Plat_Chercher= Liste_Plat_Chercher.stream().filter((e)->String.valueOf(e.getDesignation()).contains(chercherP.getText())).collect(Collectors.toList());

            Plat_aReserver(Liste_Plat_Chercher,grid,3);
            
        }
    }

    
}
