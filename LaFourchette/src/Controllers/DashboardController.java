/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class DashboardController implements Initializable {
    @FXML
    private Button btn;
    @FXML
    private Circle c1;
    @FXML
    private Circle c2;
    @FXML
    private Circle c3;

    public void button() throws IOException{
         btn.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("/tests/NewInterface1.fxml"));
         Scene scene = new Scene(root);
         
      Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setRotate(c1,true,360,10);
        setRotate(c2,true,180,18);
        setRotate(c3,true,145,24);
    }  
    private void setRotate(Circle c ,boolean reserve,int angle,int duration){
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(duration),c);
        rotateTransition.setAutoReverse(reserve);
        rotateTransition.setByAngle(angle);
        rotateTransition.setDelay(Duration.seconds(0));
        rotateTransition.setCycleCount(18);
        rotateTransition.play();
    }
    
}
