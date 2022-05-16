/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Plat;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import tests.MainClass;
import tests.PlatListener;

/**
 * FXML Controller class
 *
 * @author Iheb
 */
public class ItemPlatFXMLController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    private Plat P;
    private PlatListener PListener;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click(MouseEvent mouseEvent) {
        PListener.onClickListener(P);
    }
    

    public void setData(Plat P, PlatListener PListener) {
        this.P = P;
        this.PListener = PListener;
        nameLabel.setText(String.valueOf(P.getDesignation()));
        priceLable.setText(MainClass.CURRENCY + P.getPrix());
        String imageTR = P.getImageP();
        String  path = imageTR.replace("\\", "/");
        
        Image image = new Image("file:"+path, 110, 110, false, true);
        //Image image = new Image(getClass().getResourceAsStream(TR.getImageTable()));
        img.setImage(image);
    }
    
}
