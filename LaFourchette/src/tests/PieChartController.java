/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author barki
 */
public class PieChartController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private PieChart pcRecs;
    Connection cnx;
    int i ;
    ArrayList<String> List = new ArrayList<String>();
    ObservableList<String> listrec = FXCollections.observableArrayList();
    //ObservableList<String> listp = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
   
    @FXML
    private void LoadPieChart(ActionEvent event) {
        
       /* for (i = 0 ; i < listrec.size() ; i++) {
            
            System.out.println( i listrec[i]);
        }*/
           for(String e:listrec)
               System.out.println();
           int nb=4;
           cnx = MyConnection.getInstance().getCnx();
           
           ArrayList<Integer> listocc = new ArrayList<Integer>();
           ArrayList<String> listsum = new ArrayList<String>();
            ArrayList<String> listnbr = new ArrayList<String>();
        ObservableList<Data> listp = FXCollections.observableArrayList();
        String requete1 = "SELECT typeRec FROM reclam";
        try {
             Statement st1 = cnx.createStatement();
             ResultSet rs1 = st1.executeQuery(requete1);
             while(rs1.next()){
             listnbr.add(rs1.getString("typeRec"));
            }
             String requete2 = "SELECT typeRec FROM type_rec";     
             Statement st2 = cnx.createStatement();
             ResultSet rs2 = st2.executeQuery(requete2);
             while(rs2.next()){
             listp.add(new PieChart.Data(rs2.getString("typeRec"),Collections.frequency(listnbr,rs2.getString("typeRec"))));
             }
             String requete3= "SELECT typeRec FROM type_rec";
      
             Statement st3 = cnx.createStatement();
             ResultSet rs3 = st3.executeQuery(requete3);
             while(rs3.next()){
             listsum.add(rs3.getString("typeRec"));
            }
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        
        pcRecs.setData(listp);
        for(final PieChart.Data data : pcRecs.getData()){
            
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,new  EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event){
                    ArrayList<String> listnbrr = new ArrayList<String>();
       
                    String requete4 = "SELECT typeRec FROM reclam";
                    try {
             Statement st4 = cnx.createStatement();
             ResultSet rs4 = st4.executeQuery(requete4);
             while(rs4.next()){
             listnbrr.add(rs4.getString("typeRec"));
             }
            } catch (Exception ex) {
            System.err.println(ex.getMessage());
           }
                   // double op1;
                    float op1,op2;
                    String Var,Varf; 
                    int op3;
                    op1=(float)data.getPieValue()/(listnbrr.size());
                    op2=(float)op1*100;
                    op3=Math.round(op2);
                    Varf=String.valueOf(op3)+" %";
                    label.setText(Varf);
                }
            });
        }
    }

    
}
