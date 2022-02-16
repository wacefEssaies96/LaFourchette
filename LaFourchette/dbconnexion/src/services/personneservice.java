/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.personne;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.mydb;


public class personneservice implements iservice<personne>{
    Connection cnx;
     public personneservice() {
         cnx = mydb.getInstance().getConncetion();
    }
   
    @Override
    public void ajouter(personne t) {
        
            try {
          
           String query="INSERT INTO personnes(nom,prenom,num) values(?,?,?)";
                PreparedStatement smt = cnx.prepareStatement(query);
                smt.setString(1, t.getNom());
                smt.setString(2, t.getPrenom());
                smt.setInt(3, t.getNum());
                smt.executeUpdate();
                System.out.println("ajout avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
           
       }
    }

   
   
    @Override
    public void modifier(personne t) {
         try {
       String query2="update personnes set nom=?, prenom=?, num=? where id=?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                
                smt.setString(1, t.getNom());
                smt.setString(2, t.getPrenom());
                smt.setInt(3, t.getNum());
                smt.setInt(4, t.getId());
                smt.executeUpdate();
                System.out.println("modification avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}
    
    @Override
    public void supprimer(personne t) {
         try {
       String query2="delete from personnes where id=?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                smt.setInt(1, t.getId());
                smt.executeUpdate();
                System.out.println("suppression avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}

    @Override
    public List<personne> find() {
        ArrayList l=new ArrayList(); 
        
        try {
       String query2="select * from personnes";
                PreparedStatement smt = cnx.prepareStatement(query2);
                personne p;
                ResultSet rs= smt.executeQuery();
                while(rs.next()){
                   p=new personne(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getInt("num"));
                   l.add(p);
                }
                System.out.println(l);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }

        return l;

    


    
    
    
}}
