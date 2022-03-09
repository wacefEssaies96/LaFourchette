/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import utils.MyConnection;


public class UtilisateurService implements iservice<Utilisateur>{
       Connection cnx=MyConnection.getInstance().getCnx();
     
        
         public static UtilisateurService instance ;
         
         
         public static UtilisateurService getInstance(){
             
             
             if (instance==null)
                 instance=new UtilisateurService();
             return instance ;
         }
    public ObservableList<Utilisateur> find;

    @Override
    public void ajouter(Utilisateur t) {
              
            try {
          
           String query="INSERT INTO Utilisateur(nom_prenom,Telephone,email,mdp,role,adresse) values(?,?,?,?,?,?)";
                PreparedStatement smt = cnx.prepareStatement(query);
                smt.setString(1, t.getNom_prenom());
                smt.setInt(2, t.getTelephone());
                smt.setString(3, t.getEmail());
                smt.setString(4, t.getMdp());
                smt.setString(5, t.getRole());
                smt.setString(6, t.getAdresse());
                smt.executeUpdate();
                System.out.println("ajout avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
           
       }
    }

    @Override
    public void modifier(Utilisateur t) {
           try {
       String query2="update Utilisateur set  nom_prenom=?, Telephone=?, email=?, mdp=?, role=?, adresse=? where idU=?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                
                 smt.setString(1, t.getNom_prenom());
                smt.setInt(2, t.getTelephone());
                smt.setString(3, t.getEmail());
                smt.setString(4, t.getMdp());
                smt.setString(5, t.getRole());
                smt.setString(6, t.getAdresse());
                smt.setInt(7, t.getIdU());
                smt.executeUpdate();
                System.out.println("modification avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }
    }

    @Override
    public void supprimer(Utilisateur t) {
             try {
       String query2="delete from Utilisateur where idU=?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                smt.setInt(1, t.getIdU());
                smt.executeUpdate();
                System.out.println("suppression avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }
    }

    @Override
    public List<Utilisateur> find() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
         
         
         
         
    }
   
    
