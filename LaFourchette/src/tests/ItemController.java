/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import entities.Employer;
/**
 *
 * @author anice
 */
public class ItemController {
     @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private ImageView image_view;
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(employer);
    }

    private Employer employer;
    private MyListener myListener;

    public void setData(Employer employer, MyListener myListener) {
        this.employer = employer;
        this.myListener = myListener;
        nameLabel.setText(String.valueOf( employer.getIdEM()));
        priceLable.setText(employer.getNom_prenom());
        
     
            String picture = "file:" + employer.getPicture();

        Image image = new Image(picture, 110, 110, false, true);

        image_view.setImage(image);

        String path =  employer.getPicture();

       //file_path.setText(path);
        
        //Image image = new Image(getClass().getResourceAsStream(fruit.getImgSrc()));
        //img.setImage(image);
    }
}
