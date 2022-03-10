/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import entities.Table_Resto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import tests.MainClass;
import tests.ReservationListener;

/**
 * FXML Controller class
 *
 * @author Iheb
 */
public class ItemReserverFXMLController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;

    private Table_Resto TR;
    private ReservationListener RListener;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click(MouseEvent mouseEvent) {
        RListener.onClickListener(TR);
    }
    

    public void setData(Table_Resto TR, ReservationListener RListener) {
        this.TR = TR;
        this.RListener = RListener;
        nameLabel.setText(String.valueOf(TR.getNbrPlace()));
        priceLable.setText(MainClass.CURRENCY + TR.getPrix());
        String imageTR = TR.getImageTable();
        String  path = imageTR.replace("\\", "/");
        
        Image image = new Image("file:"+path, 110, 110, false, true);
        //Image image = new Image(getClass().getResourceAsStream(TR.getImageTable()));
        img.setImage(image);
    }


    public void setData(Table_Resto TR) {
        this.TR = TR;
        nameLabel.setText(String.valueOf(TR.getNbrPlace()));
        priceLable.setText(MainClass.CURRENCY + TR.getPrix());
        String imageTR = TR.getImageTable();
        String  path = imageTR.replace("\\", "/");
        
        Image image = new Image("file:"+path, 110, 110, false, true);
        //Image image = new Image(getClass().getResourceAsStream(TR.getImageTable()));
        img.setImage(image);
    }
    
}
