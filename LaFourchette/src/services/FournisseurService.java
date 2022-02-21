/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Fournisseur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

/**
 *
 * @author wacef
 */
public class FournisseurService {
    Connection cnx;
    public FournisseurService() {
         cnx = MyConnection.getInstance().getCnx();
    }
    public void ajouterFournisseur(Fournisseur t) {
        try {
            String query="INSERT INTO fournisseur(nomF,telephoneF,categorie,emailF) values(?,?,?,?)";
            PreparedStatement smt = cnx.prepareStatement(query);
            smt.setString(1, t.getNomF());
            smt.setInt(2, t.getTelephoneF());
            smt.setString(3, t.getCategorie());
            smt.setString(4, t.getEmailF());
            smt.executeUpdate();
            System.out.println("ajout avec succee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
       }
    }
    public void modifierFournisseur(Fournisseur t) {
        try {
            String query2="update fournisseur set  nomF=?, telephoneF=?, categorie=?, emailF=? where idF=?";
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setString(1, t.getNomF());
            smt.setInt(2, t.getTelephoneF());
            smt.setString(3, t.getCategorie());
            smt.setString(4, t.getEmailF());
            smt.setInt(5, t.getIdF());
            smt.executeUpdate();
            System.out.println("modification avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    }
    
    public void supprimerFournisseur(Fournisseur t) {
        try {
            String query2="delete from fournisseur where idF=?";
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setInt(1, t.getIdF());
            smt.executeUpdate();
            System.out.println("suppression avec succee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Fournisseur> afficherListeFournisseur() {
        ArrayList l=new ArrayList(); 
        try {
            String query2="select * from fournisseur";
            PreparedStatement smt = cnx.prepareStatement(query2);
            Fournisseur p;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               p=new Fournisseur(rs.getInt("idF"),rs.getString("nomF"),rs.getInt("telephoneF"),rs.getString("categorie"),rs.getString("emailF"));
               l.add(p);
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
}
