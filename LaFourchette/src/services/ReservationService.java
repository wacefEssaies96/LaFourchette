/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reservation;
import entities.Table_Resto;
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
public class ReservationService {

    Connection cnx;
    
    public ReservationService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    
    public void ajouter(Reservation t) {
        
        try {
            String query="INSERT INTO Reservation(idT,idU,dateCreation,dateModification) values(?,?,?,?)";
            PreparedStatement smt = cnx.prepareStatement(query);
            smt.setInt(1, t.getIdT());
            smt.setInt(2, t.getIdU());
            smt.setDate(3, t.getDateCreation());
            smt.setDate(4, t.getDateModification());
            smt.executeUpdate();
            System.out.println(" Reservation ajouter avec succée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void modifier(Reservation t) {
        try {
            String query2="update Reservation set  idT=?, idU=?, dateCreation=?, dateModification=? where idR=?";
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setInt(1, t.getIdT());
            smt.setInt(2, t.getIdU());
            smt.setDate(3, t.getDateCreation());
            smt.setDate(4, t.getDateModification());
            smt.setInt(5, t.getIdR());
            if(smt.executeUpdate() == 1){
                smt.executeUpdate();
                System.out.println("Reservation modifier avec succée");
            }else{
                System.out.println("Problem : Reservation modification echoue \n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void supprimer(Reservation t) {
        try {
            String query3="delete from Reservation where idR=?";
            PreparedStatement smt = cnx.prepareStatement(query3);
            smt.setInt(1, t.getIdR());
            if(smt.executeUpdate() == 1){
                smt.executeUpdate();
                System.out.println("Table_Resto supprimer avec succée");
            }else{
                System.out.println("Problem : Table_Resto supprission echoue \n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public List<Reservation> find() {
        ArrayList l=new ArrayList(); 
        
        try {
            String query4="select * from Reservation join Table_Resto where Reservation.idT=Table_Resto.idT";
            PreparedStatement smt = cnx.prepareStatement(query4);
            Reservation RES;
            Table_Resto TR;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               RES=new Reservation(rs.getInt("idR"),rs.getInt("idT"),rs.getInt("idU"),rs.getDate("dateCreation"),rs.getDate("dateModification"));
               TR=new Table_Resto(rs.getInt("idT"),rs.getInt("nbrPlace"),rs.getString("Etat"));
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
