/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reservation;
import entities.Table_Resto;
import entities.Reservation_Table_resto;
import java.sql.Connection;
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
public class Reservation_Table_resto_Service {

    Connection cnx;
    
    public Reservation_Table_resto_Service() {
        cnx = MyConnection.getInstance().getCnx();
    }

    
    public void ajouter(Reservation_Table_resto rtr) {
        
        try {
            String query="INSERT INTO Reservation_Table_resto(IdR,IdT) values(?,?)";
            PreparedStatement smt = cnx.prepareStatement(query);
            smt.setInt(1, rtr.getIdR());
            smt.setInt(2, rtr.getIdT());
            smt.executeUpdate();
            System.out.println(" Reservation_Table_resto ajouter avec succée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void modifier(Reservation_Table_resto rtr) {
        try {
            String query2="update reservation_table_resto set  IdR=?, IdT=?, where IdRTR=?";
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setInt(1, rtr.getIdR());
            smt.setInt(2, rtr.getIdT());
            smt.setInt(3, rtr.getIdRTR());
            if(smt.executeUpdate() == 1){
                smt.executeUpdate();
                System.out.println("Reservation_Table_resto modifier avec succée");
            }else{
                System.out.println("Problem : Reservation_Table_resto modification echoue \n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void supprimer(Reservation_Table_resto rtr) {
        try {
            String query3="delete from reservation_table_resto where IdRTR=?";
            PreparedStatement smt = cnx.prepareStatement(query3);
            smt.setInt(1, rtr.getIdRTR());
            if(smt.executeUpdate() == 1){
                smt.executeUpdate();
                System.out.println("Reservation_Table_resto supprimer avec succée");
            }else{
                System.out.println("Problem : Reservation_Table_resto supprission echoue \n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public List<Reservation_Table_resto> find() {
        ArrayList l=new ArrayList(); 
        
        try {
            String query4="SELECT * FROM reservation_table_resto rtr left join reservation r on r.IdR = rtr.IdR left JOIN table_resto tr on tr.IdT = rtr.IdT";
            
            PreparedStatement smt = cnx.prepareStatement(query4);
            Reservation RES;
            Table_Resto TR;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               RES=new Reservation(rs.getInt("IdR"),rs.getInt("IdU"),rs.getDate("DateCreation"),rs.getDate("DateModification"));
               TR=new Table_Resto(rs.getInt("IdT"),rs.getInt("NbrPlace"),rs.getString("Etat"),rs.getString("ImageTable"),rs.getString("Vip"),rs.getDouble("Prix"));
               l.add(RES);
               l.add(TR);
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
    
}
