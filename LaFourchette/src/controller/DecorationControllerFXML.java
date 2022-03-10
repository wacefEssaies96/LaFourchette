/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Decoration;
import entities.Decoration_Reservation;
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
import services.DecorationService;
import services.Decoration_ReservationService;
import tests.DecorationListener;
import tests.MainClass;

/**
 * FXML Controller class
 *
 * @author Iheb
 */
public class DecorationControllerFXML implements Initializable {

    @FXML
    private Button btn_Decoration;
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private Button btn_ajDec;
    @FXML
    private ScrollPane scroll1;
    @FXML
    private GridPane grid1;
    @FXML
    private TextField chercherD;
    @FXML
    private ComboBox<?> caractere_chercher_D;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    
    
    
    ButtonType Oui = new ButtonType("Oui", ButtonBar.ButtonData.YES);
    ButtonType Annuler = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
    int IdU=2;
    public static int IdR=0;
    int cpt;
    private Image image;
    private DecorationListener DListener;
    
    DecorationService SER_D = new DecorationService();
    Decoration_ReservationService SER_RESD =new Decoration_ReservationService();
    Decoration D =new Decoration();
    
    List<Decoration> Liste_D = new ArrayList();  
    List<Decoration> Liste_D_aRes = new ArrayList();
    List<Decoration> Liste_D_Rester = new ArrayList();  

    
    
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
    
    ObservableList chercherOptionD = FXCollections.observableArrayList("Nom","Prix");



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        Liste_D = SER_D.find();
        //item choisit
        if (Liste_D.size() > 0) {
            this.D=Liste_D.get(0);
            setChosenDecoration(Liste_D.get(0));
            DListener = new DecorationListener() {
                @Override
                public void onClickListener(Decoration D) {
                    setChosenDecoration(D);
                }
            };
        }
        
        modiflisteD(Liste_D_aRes);
        
    }    

    private void setChosenDecoration(Decoration D) {
        
        caractere_chercher_D.setItems(chercherOptionD);
        
        this.D=D;
        fruitNameLable.setText(String.valueOf(D.getNom()));
        fruitPriceLabel.setText(MainClass.CURRENCY + String.valueOf(D.getPrix()));
        

        String imageD = D.getImageD();
        String  path = imageD.replace("\\", "/");
        
        image = new Image("file:"+path, 110, 110, false, true);
        fruitImg.setImage(image);
        Random rn = new Random();
        int colvar = rn.nextInt(9) + 1;
        chosenFruitCard.setStyle("-fx-background-color: #"
                + couleurs.get(colvar)
                + ";\n    -fx-background-radius: 30;");
       
    }
    
    private void Decoration_aReserver(List<Decoration> ll,GridPane g,int ligne){
        g.getChildren().clear();
        int column = 0;
        int row = 1;
        try {
            
            for (int i = 0; i < ll.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../gui/ItemDecorationFXML.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();


                ItemDecorationFXMLController itemController = fxmlLoader.getController();
                itemController.setData(ll.get(i),DListener);


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
        
        if(Liste_D_aRes.isEmpty()){
            ButtonType Oui = new ButtonType("D'accord", ButtonBar.ButtonData.YES);
            Alert alert = new Alert(Alert.AlertType.WARNING,
                " Ajouter une decoration avant de confirmer",
                Oui);
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Les decorations ont ete ajouter au reservation.",
                    Oui,
                    Annuler);

            alert.setTitle("Votre reservation effectue avec succes");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.orElse(Annuler) == Oui) {
                ajouterReservation_Decoration(alert);
                // Interface decoration
                
                
                try {
                    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../gui/MesReservationFXML.fxml"));
                    Parent root;
                    root = fxmlLoader.load();
                            //FXMLLoader.load(getClass().getResource("../gui/DecorationFXML.fxml"));
                    Scene scene= new Scene(root);
                    stage.setScene(scene);
                    //stage.initStyle(StageStyle.UTILITY);
                    stage.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                
            }
        }
        
    }
    public void ajouterReservation_Decoration(Alert alert){
        
            
            // ajouter tous les Decoration selectione Liste_D_aRes 
            
            Decoration_Reservation DR;
            for(int i=0;i<Liste_D_aRes.size();i++){
                DR = new Decoration_Reservation(1, IdR, Liste_D_aRes.get(i).getIdD());
                SER_RESD.ajouter(DR);
            }
            
            alert = new Alert(AlertType.INFORMATION,
                " Votre Decoration ajouter avec succes",
                Oui);
            alert.showAndWait();
    }

    @FXML
    private void ajDecoration(ActionEvent event) {
        
            int O=0;
            for(int i=0;i<Liste_D_Rester.size();i++){
                if(Liste_D_Rester.get(i)==D){

                    //btn_ajRes.setText("Ajouter au Reservation");
                    O+=1;
                    Liste_D_aRes.add(D);
                    modiflisteD(Liste_D_aRes);
                }
            }
            if(O==0){
                //annuler
                for(int i=0;i<Liste_D_aRes.size();i++){
                    if(Liste_D_aRes.get(i)==D){

                        //btn_ajRes.setText("Annuler cette Decoration");
                        Liste_D_Rester.add(D);
                        Liste_D_aRes.remove(D);
                        //Table a reserver
                        Decoration_aReserver(Liste_D_aRes,grid1,1);
                        //Table a rester disponible
                        Decoration_aReserver(Liste_D_Rester,grid,3);
                    }
                }

            }


            System.out.println(" Liste_D_aRes : "+Liste_D_aRes.toString());
        
    }
    private void modiflisteD(List<Decoration> LDaRes){
        
        
        
        //Decoration a reserver
        Decoration_aReserver(LDaRes,grid1,1);
        
        Liste_D_Rester.clear();
                
        for(int i=0;i<Liste_D.size();i++){
            cpt=0;
            for(int j=0;j<LDaRes.size();j++){
                if(Liste_D.get(i)== LDaRes.get(j)){
                    cpt+=1;
                }
            }
            if(cpt==0){
                Liste_D_Rester.add(Liste_D.get(i));
            }  
            
        }
        //Table a rester disponible
        Decoration_aReserver(Liste_D_Rester,grid,3);
        
    }

   
    @FXML
    private void chercherD(KeyEvent event) {
        
        List<Decoration> Liste_D_Rester_Chercher = new ArrayList();
        
        Decoration_aReserver(Liste_D,grid,3);
        if (caractere_chercher_D.getSelectionModel().getSelectedItem()=="Prix" ){
            Liste_D_Rester_Chercher= Liste_D_Rester.stream().filter((e)->String.valueOf(e.getPrix()).contains(chercherD.getText())).collect(Collectors.toList());

            Decoration_aReserver(Liste_D_Rester_Chercher,grid,3);
            
        }
        if (caractere_chercher_D.getSelectionModel().getSelectedItem()=="Nom" ){
            Liste_D_Rester_Chercher= Liste_D_Rester.stream().filter((e)->String.valueOf(e.getNom().toLowerCase()).contains(chercherD.getText().toLowerCase())).collect(Collectors.toList());

            Decoration_aReserver(Liste_D_Rester_Chercher,grid,3);
            
        }
    }
}
