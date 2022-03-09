/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Evenement;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import services.EvenementCrud;

/**
 * FXML Controller class
 *
 * @author user
 */
public class StatEvenetController implements Initializable {

    @FXML
    private PieChart myPiechart;
    @FXML
    private Label caption;
    @FXML
    private Label numstat;
    int nbrtotale=0;
    double st;
    @FXML
    private Rectangle rectangle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList();
        EvenementCrud ev = new EvenementCrud();
        
        List<Evenement>ListeDesEvent = ev.afficherListeEvenement();
       for(Evenement e: ListeDesEvent){
           pieChartData.add(new Data(e.getDesignationE(),e.getNbrParticipants()));
           nbrtotale+=e.getNbrParticipants();
       }
        myPiechart.setData(pieChartData);
caption.setTextFill(Color.DARKORANGE);
caption.setStyle("-fx-font: 24 arial;");

for (final PieChart.Data data : myPiechart.getData()) {
    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
        new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                caption.setTranslateX(e.getSceneX());
                caption.setTranslateY(e.getSceneY());
                caption.setText(String.valueOf(Math.round(data.getPieValue())));
                numstat.setText(String.valueOf((data.getPieValue()/nbrtotale)*100 + " %"));
                System.out.println(nbrtotale);
               
             }
        });
    
}

    }    
    
}
