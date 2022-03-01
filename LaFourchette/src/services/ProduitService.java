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
    }
   
    public boolean ajouterProduit(Produit t) {
        try {
            String requete="INSERT INTO produit(nomProd,quantite,image,prix) values(?,?,?,?)";
            PreparedStatement smt = cnx.prepareStatement(requete);
            smt.setString(1, t.getNomProd());
            smt.setInt(2, t.getQuantite());
            smt.setString(3, t.getImage());
            smt.setDouble(4, t.getPrix());
            smt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }
    public boolean modifierProduit(Produit t) {
        try {
            String query2="update produit set quantite=?, image=?, prix=? where nomProd=?";
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setInt(1, t.getQuantite());
            smt.setString(2, t.getImage());
            smt.setDouble(3, t.getPrix());
            smt.setString(4, t.getNomProd());
            smt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public boolean supprimerProduit(Produit t) {
        try {
            String query2="delete from produit where nomProd=?";
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setString(1, t.getNomProd());
            smt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public List<Produit> afficherListeProduits() {
        ArrayList l=new ArrayList();
        try {
            //SELECT * FROM produit p left join produit_fournisseur pf ON p.nomProd = pf.nomProd UNION select * from fournisseur f left JOIN produit_fournisseur pf ON f.idF = pf.idF
//            SELECT * FROM produit p left join produit_fournisseur pf ON p.nomProd = pf.nomProd left JOIN fournisseur f ON f.idF = pf.idF
            String query2="SELECT * FROM produit";
            PreparedStatement smt = cnx.prepareStatement(query2);
            Produit p;
            Fournisseur f;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               p = new Produit(rs.getString("nomProd"),rs.getInt("quantite"),rs.getString("image"),rs.getDouble("prix"));
               l.add(p);
//               f=new Fournisseur(rs.getInt("idF"),rs.getString("nomF"),rs.getInt("telephoneF"),rs.getString("emailF"));
//               l.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
}
