/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class DashboardController implements Initializable {

    @FXML
    private Text FoodItem;
    @FXML
    private Text TodayProfit;
    @FXML
    private Text TotalEmployee;
    @FXML
    private Text TotalSell;
    @FXML
    private Text TotalProfit;
    @FXML
    private Text TotalEmployeeSalary;
    @FXML
    private Text TodaySell;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
