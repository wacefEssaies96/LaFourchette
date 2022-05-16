/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Datetimetr;
import entities.DatetimetrTableResto;
import entities.Decoration;
import entities.Reservation;
import entities.Table_Resto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

/**
 *
 * @author Iheb
 */
public class DatetimetrService {
 

    Connection cnx;
    
    public DatetimetrService() {
        cnx = MyConnection.getInstance().getCnx();
    }
    
 
    public List find() {
        ArrayList l=new ArrayList(); 
        
        try {
            //String query1="SELECT * FROM Datetimetr d left join DatetimetrTableResto dtr on d.iddt = dtr.iddt left JOIN table_resto tr on tr.IdT = dtr.IdT where d.etat='Disponible'";
                
            String query1="SELECT * FROM table_resto tr left join datetimetr_table_resto dtr on tr.IdT = dtr.IdT  left JOIN Datetimetr d on d.iddt = dtr.iddt where d.etat='Disponible'";
            //  where d.etat='Disponible'

            PreparedStatement smt = cnx.prepareStatement(query1);
            Datetimetr D;
            Table_Resto TR;
            DatetimetrTableResto DTR;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
                
               D=new Datetimetr(rs.getInt("iddt"),rs.getDate("date"),rs.getString("etat"));
               TR=new Table_Resto(rs.getInt("IdT"),rs.getInt("NbrPlace"),rs.getString("Etat"),rs.getString("ImageTable"),rs.getString("Vip"),rs.getDouble("Prix"));
               DTR=new DatetimetrTableResto(rs.getInt("idDTR"),rs.getInt("idt"),rs.getInt("iddt"));
               l.add(TR);
               l.add(DTR);
               l.add(D);
            }
            System.out.println(" table dispo par date avant :");
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
    public List<Table_Resto> find_Date_TR(int idt) {
        ArrayList l=new ArrayList(); 
        
        try {
            
            String query1="SELECT * FROM table_resto tr left join datetimetr_table_resto dtr on tr.IdT = dtr.IdT  left JOIN Datetimetr d on d.iddt = dtr.iddt where d.etat='Disponible' && tr.IdT =?";
            

            PreparedStatement smt = cnx.prepareStatement(query1);
            smt.setInt(1, idt);
            Datetimetr D;
            Table_Resto TR;
            DatetimetrTableResto DTR;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
                
               D=new Datetimetr(rs.getInt("iddt"),rs.getDate("date"),rs.getString("etat"));
               TR=new Table_Resto(rs.getInt("IdT"),rs.getInt("NbrPlace"),rs.getString("Etat"),rs.getString("ImageTable"),rs.getString("Vip"),rs.getDouble("Prix"));
               DTR=new DatetimetrTableResto(rs.getInt("idDTR"),rs.getInt("idT"),rs.getInt("iddt"));
               l.add(TR);
               //l.add(DTR);
               //l.add(D);
            }
            System.out.println(" table dispo par date :");
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
}
