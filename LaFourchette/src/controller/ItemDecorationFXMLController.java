/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Decoration;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import tests.DecorationListener;
import tests.MainClass;

/**
 * FXML Controller class
 *
 * @author Iheb
 */
public class ItemDecorationFXMLController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    
    private Decoration D;
    private DecorationListener DListener;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click(MouseEvent mouseEvent) {
        DListener.onClickListener(D);
    }
    
    

    public void setData(Decoration D, DecorationListener DListener) {
        
        this.D = D;
        this.DListener = DListener;
        nameLabel.setText(String.valueOf(D.getNom()));
        priceLable.setText(MainClass.CURRENCY + D.getPrix());
        String imageD = D.getImageD();
        String  path = imageD.replace("\\", "/");
        
        Image image = new Image("file:"+path, 110, 110, false, true);
        
        img.setImage(image);
    }
    public void setData(Decoration D) {
        
        this.D = D;
        nameLabel.setText(String.valueOf(D.getNom()));
        priceLable.setText(MainClass.CURRENCY + D.getPrix());
        String imageD = D.getImageD();
        String  path = imageD.replace("\\", "/");
        
        Image image = new Image("file:"+path, 110, 110, false, true);
        
        img.setImage(image);
    }
    
}
