/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Produit;
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
public class ProduitService {
    Connection cnx;
    public ProduitService() {
         cnx = MyConnection.getInstance().getCnx();
    }
   
    public void ajouter(Produit t) {
        try {
            String query="INSERT INTO produit(quantite,image,prix) values(?,?,?)";
            PreparedStatement smt = cnx.prepareStatement(query);
            smt.setInt(1, t.getQuantite());
            smt.setString(2, t.getImage());
            smt.setDouble(3, t.getPrix());
            smt.executeUpdate();
            System.out.println("ajout avec succee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void modifier(Produit t) {
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
    public void supprimer(Produit t) {
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

    public List<Produit> find() {
        ArrayList l=new ArrayList();
        try {
            String query2="select * from Utilisateur";
            PreparedStatement smt = cnx.prepareStatement(query2);
            Produit p;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
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
