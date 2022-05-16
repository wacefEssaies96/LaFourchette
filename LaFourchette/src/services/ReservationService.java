/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Decoration;
import entities.Reservation;
import entities.Table_Resto;
import entities.Utilisateur;
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

    
    public void ajouter(Reservation r) {
        
        try {
            String query="INSERT INTO Reservation(IdU,DateCreation,DateModification) values(?,?,?)";
            PreparedStatement smt = cnx.prepareStatement(query);
            smt.setInt(1, Utilisateur.Current_User.getIdU());
            smt.setDate(2, r.getDateCreation());
            smt.setDate(3, r.getDateModification());
            smt.executeUpdate();
            System.out.println(" Reservation ajouter avec succée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void modifier(Reservation r) {
        try {
            String query2="update Reservation set  IdU=?, DateCreation=?, DateModification=? where IdR=?";
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setInt(1, Utilisateur.Current_User.getIdU());
            smt.setDate(2, r.getDateCreation());
            smt.setDate(3, r.getDateModification());
            smt.setInt(4, r.getIdR());
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

    
    public void supprimer(Reservation r) {
        try {
            String query3="delete from Reservation where IdR=?";
            PreparedStatement smt = cnx.prepareStatement(query3);
            smt.setInt(1, r.getIdR());
            if(smt.executeUpdate() == 1){
                smt.executeUpdate();
                System.out.println("Reservation supprimer avec succée");
            }else{
                System.out.println("Problem : Reservation supprission echoue \n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public List<Reservation> find() {
        ArrayList l=new ArrayList(); 
        
        try {
            String query4="SELECT * FROM reservation r left join reservation_table_resto rtr on r.IdR = rtr.IdR left JOIN table_resto tr on tr.IdT = rtr.IdT left join decoration_reservation dr on dr.IdR = r.IdR left join decoration d on d.IdD = dr.IdD";
            
            PreparedStatement smt = cnx.prepareStatement(query4);
            Reservation RES;
            Table_Resto TR;
            Decoration D;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               RES=new Reservation(rs.getInt("IdR"),rs.getInt("IdU"),rs.getDate("DateCreation"),rs.getDate("DateModification"));
               TR=new Table_Resto(rs.getInt("IdT"),rs.getInt("NbrPlace"),rs.getString("Etat"),rs.getString("ImageTable"),rs.getString("Vip"),rs.getDouble("Prix"));
               D=new Decoration(rs.getInt("IdD"),rs.getString("Nom"),rs.getDouble("Prix"),rs.getString("ImageD"));
               l.add(RES);
               l.add(TR);
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }//Utilisateur.Current_User.getIdU()
    public List<Reservation> MesResrvation(int iduser) {
        ArrayList l=new ArrayList(); 
        
        try {
            String query4="SELECT * FROM reservation r inner join reservation_table_resto rtr on r.IdR = rtr.IdR inner JOIN table_resto tr on tr.IdT = rtr.IdT left join decoration_reservation dr on dr.IdR = r.IdR left join decoration d on d.IdD = dr.IdD where IdU=?  ";
            
            PreparedStatement smt = cnx.prepareStatement(query4);
            smt.setInt(1, iduser);
            Reservation RES;
            Table_Resto TR;
            Decoration D;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               RES=new Reservation(rs.getInt("IdR"),rs.getInt("IdU"),rs.getDate("DateCreation"),rs.getDate("DateModification"));
              TR=new Table_Resto(rs.getInt("IdT"),rs.getInt("NbrPlace"),rs.getString("Etat"),rs.getString("ImageTable"),rs.getString("Vip"),rs.getDouble("Prix"));
              D=new Decoration(rs.getInt("IdD"),rs.getString("Nom"),rs.getDouble("Prix"),rs.getString("ImageD"));
               l.add(RES);
//               l.add(TR);
//               
//               l.add(D);
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
    
    public List<Reservation> afficher() {
        ArrayList l=new ArrayList(); 
        
        try {
            String query5="SELECT * FROM reservation ";
            
            PreparedStatement smt = cnx.prepareStatement(query5);
            Reservation RES;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               RES=new Reservation(rs.getInt("IdR"),rs.getInt("IdU"),rs.getDate("DateCreation"),rs.getDate("DateModification"));
               l.add(RES);
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }//Utilisateur.Current_User.getIdU() and max id
    public Reservation RecuperedernierReservation(int iduser) {
        Reservation SeulRes=new Reservation(); 
        
        try {
            String query5="SELECT * FROM reservation where idU=?";
            
            PreparedStatement smt = cnx.prepareStatement(query5);
            smt.setInt(1, iduser);
            Reservation RES;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               RES=new Reservation(rs.getInt("IdR"),rs.getInt("IdU"),rs.getDate("DateCreation"),rs.getDate("DateModification"));
               SeulRes=RES;
            }
            System.out.println(SeulRes.toString());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return SeulRes;
    }
}
