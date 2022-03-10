/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Decoration;
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
public class DecorationService {

    Connection cnx;
    
    public DecorationService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    
    public void ajouter(Decoration d) {
        
        try {
            String query="INSERT INTO Decoration(Nom,Prix,ImageD) values(?,?,?)";
            PreparedStatement smt = cnx.prepareStatement(query);
            smt.setString(1, d.getNom());
            smt.setDouble(2, d.getPrix());
            smt.setString(3, d.getImageD());
            smt.executeUpdate();
            System.out.println(" Decoration ajouter avec succée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void modifier(Decoration d) {
        try {
            String query2="update Decoration set  Nom=?, Prix=?, ImageD=? where IdD=?";
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setString(1, d.getNom());
            smt.setDouble(2, d.getPrix());
            smt.setString(3, d.getImageD());
            smt.setInt(4, d.getIdD());
            if(smt.executeUpdate() == 1){
                smt.executeUpdate();
                System.out.println("Decoration modifier avec succée");
            }else{
                System.out.println("Problem : Decoration modification echoue \n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void supprimer(Decoration d) {
        try {
            String query3="delete from Decoration where IdD=?";
            PreparedStatement smt = cnx.prepareStatement(query3);
            smt.setInt(1, d.getIdD());
            if(smt.executeUpdate() == 1){
                smt.executeUpdate();
                System.out.println("Decoration supprimer avec succée");
            }else{
                System.out.println("Problem : Decoration supprission echoue \n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public List<Decoration> find() {
        ArrayList l=new ArrayList(); 
        
        try {
            String query4="select * from Decoration ";
            PreparedStatement smt = cnx.prepareStatement(query4);
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
