/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Fournisseur;
import entities.Produit;
import entities.ProduitFournisseur;
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
         System.out.println(cnx);
    }
    public void ajouterFournisseur(Fournisseur t) {
        try {
            String query="INSERT INTO fournisseur(nomF,telephoneF,emailF) values(?,?,?)";
            PreparedStatement smt = cnx.prepareStatement(query);
            smt.setString(1, t.getNomF());
            smt.setInt(2, t.getTelephoneF());
            smt.setString(3, t.getEmailF());
            smt.executeUpdate();
            System.out.println("ajout avec succee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
       }
    }
    public void modifierFournisseur(Fournisseur t) {
        try {
            String query2="update fournisseur set  nomF=?, telephoneF=?, emailF=? where idF=?";
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setString(1, t.getNomF());
            smt.setInt(2, t.getTelephoneF());
            smt.setString(3, t.getEmailF());
            smt.setInt(4, t.getIdF());
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
            String query2="SELECT * FROM fournisseur f inner join produit_fournisseur pf ON f.idF = pf.idF inner JOIN produit p ON p.nomProd = pf.nomProd";
            PreparedStatement smt = cnx.prepareStatement(query2);
            Fournisseur f;
            Produit p;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               f=new Fournisseur(rs.getInt("idF"),rs.getString("nomF"),rs.getInt("telephoneF"),rs.getString("emailF"));
               l.add(f);
               p = new Produit(rs.getString("nomProd"),rs.getInt("quantite"),rs.getString("image"),rs.getDouble("prix"));
               l.add(p);
               
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
}
