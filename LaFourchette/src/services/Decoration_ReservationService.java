/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reservation;
import entities.Decoration;
import entities.Decoration_Reservation;
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
public class Decoration_ReservationService {

    Connection cnx;
    
    public Decoration_ReservationService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    
    public void ajouter(Decoration_Reservation dr) {
        
        try {
            String query="INSERT INTO Decoration_Reservation(IdR,IdD) values(?,?)";
            PreparedStatement smt = cnx.prepareStatement(query);
            smt.setInt(1, dr.getIdR());
            smt.setInt(2, dr.getIdD());
            smt.executeUpdate();
            System.out.println(" Decoration_Reservation ajouter avec succée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void modifier(Decoration_Reservation dr) {
        try {
            String query2="update decoration_reservation set  IdR=?, IdD=? where IdDR=?";
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setInt(1, dr.getIdR());
            smt.setInt(2, dr.getIdD());
            smt.setInt(3, dr.getIdDR());
            if(smt.executeUpdate() == 1){
                smt.executeUpdate();
                System.out.println("Decoration_Reservation modifier avec succée");
            }else{
                System.out.println("Problem : Decoration_Reservation modification echoue \n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void supprimer(Decoration_Reservation dr) {
        try {
            String query3="delete from Decoration_Reservation where IdDR=?";
            PreparedStatement smt = cnx.prepareStatement(query3);
            smt.setInt(1, dr.getIdDR());
            if(smt.executeUpdate() == 1){
                smt.executeUpdate();
                System.out.println("Decoration_Reservation supprimer avec succée");
            }else{
                System.out.println("Problem : Decoration_Reservation supprission echoue \n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public List<Decoration_Reservation> find() {
        ArrayList l=new ArrayList(); 
        
        try {
            String query4="SELECT * FROM Decoration_Reservation dr left join reservation r on r.IdR = dr.IdR left JOIN decoration d on d.IdD = dr.IdD";
            
            PreparedStatement smt = cnx.prepareStatement(query4);
            Reservation RES;
            Decoration D;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               RES=new Reservation(rs.getInt("IdR"),rs.getInt("IdU"),rs.getDate("DateCreation"),rs.getDate("DateModification"));
               D=new Decoration(rs.getInt("IdD"),rs.getString("Nom"),rs.getDouble("Prix"),rs.getString("ImageD"));
               l.add(RES);
               l.add(D);
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
    
    
     public List<Decoration> mesReservation(int id) {
        ArrayList l=new ArrayList(); 
        
        try {
            String query5="SELECT * FROM Decoration_Reservation dr left join decoration d on d.IdD = dr.IdD where dr.IdR = '"+id+"'";
            
            PreparedStatement smt = cnx.prepareStatement(query5);
            Decoration D;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               D=new Decoration(rs.getInt("IdD"),rs.getString("Nom"),rs.getDouble("Prix"),rs.getString("ImageD"));
               l.add(D);
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
}
