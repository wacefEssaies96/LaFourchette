/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class CalculatriceController implements Initializable {

    @FXML
    private Label result;
    @FXML
    private Label prevnum;

    private double num1=0;
       private double total=0;
          private String operator="";
             private boolean check=true;
    @FXML
    private Button clear;
    
    public Double create(double num1,double num2 ,String operator){
        switch(operator){
            case"+":
                return (num1 + num2);
                case"-":
                return (num1 - num2);
                case"*":
                return (num1 * num2);
                case"/":
                    if(num2==0)
                        return 0.0;
                return (num1 / num2);
                default :
                    break;
        }
        return 0.0;
    }
    public void number(String number){
        result.setText(result.getText() + number);
    }
    public void prevOperator(String operator){
        prevnum.setText(prevnum.getText() + " " + operator + " ");
    }
    
    public void prevnumber(String number){
        prevnum.setText(prevnum.getText() + number);
    }
    @FXML
    public void computeProcess(ActionEvent event){
        if(check){
            result.setText("");
            prevnum.setText(""); 
            check=false;
        }
        Button button = (Button)event.getSource();
        String value =button.getText();
        number(value);
        prevnumber(value);
                
    }
    @FXML
    public void operatorProcess(ActionEvent event){
        Button button =(Button)event.getSource();
        String  value = button.getText();
        if(!value.equals("Ans")){
            if(!operator.isEmpty())
            return;
            operator = value;
            prevOperator(operator);
            num1=Double.parseDouble(result.getText());
            result.setText("");
        }else{
            if(operator.isEmpty())
                return ;
            double num2 =Double.parseDouble(result.getText());
            total = create(num1,num2,operator);
            result.setText(String.valueOf(total));
            operator = " ";
            check =true ;
        }
        
        
    }
    public void clear(ActionEvent event){
        if(event.getSource() == clear){
            result.setText("0");
            prevnum.setText("");
        }
        
    }
    public void exit(){
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
