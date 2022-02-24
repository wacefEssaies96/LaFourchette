/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Fournisseur;
import entities.Produit;


import java.sql.Connection;
import utils.MyConnection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author wacef
 */
public class ProduitService {
    Connection cnx;
    public ProduitService() {
        cnx = MyConnection.getInstance().getCnx();
        System.out.println(cnx);
    }
   
    public void ajouterProduit(Produit t) {
        System.out.println(cnx);
        
        try {
            String requete="INSERT INTO produit(nomProd,quantite,image,prix) values(?,?,?,?)";
            PreparedStatement smt = cnx.prepareStatement(requete);
            smt.setString(1, t.getNomProd());
            smt.setInt(2, t.getQuantite());
            smt.setString(3, t.getImage());
            smt.setDouble(4, t.getPrix());
            smt.executeUpdate();
            System.out.println("ajout avec succee");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void modifierProduit(Produit t) {
        try {
            String query2="update produit set quantite=?, image=?, prix=? where nomProd=?";
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setInt(1, t.getQuantite());
            smt.setString(2, t.getImage());
            smt.setDouble(3, t.getPrix());
            smt.setString(4, t.getNomProd());
            smt.executeUpdate();
            System.out.println("modification avec succee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void supprimerProduit(Produit t) {
        try {
            String query2="delete from produit where nomProd=?";
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setString(1, t.getNomProd());
            smt.executeUpdate();
            System.out.println("suppression avec succee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Produit> afficherListeProduits() {
        ArrayList l=new ArrayList();
        try {
            String query2="SELECT * FROM produit p inner join produit_fournisseur pf ON p.nomProd = pf.nomProd inner JOIN fournisseur f ON f.idF = pf.idF";
            PreparedStatement smt = cnx.prepareStatement(query2);
            Produit p;
            Fournisseur f;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               p = new Produit(rs.getString("nomProd"),rs.getInt("quantite"),rs.getString("image"),rs.getDouble("prix"));
               l.add(p);
               f=new Fournisseur(rs.getInt("idF"),rs.getString("nomF"),rs.getInt("telephoneF"),rs.getString("emailF"));
               l.add(f);
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
}
