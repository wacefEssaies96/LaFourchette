/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

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
public class ProduitFournisseurService {
    Connection cnx;
    public ProduitFournisseurService() {
         cnx = MyConnection.getInstance().getCnx();
    }
   
    public void ajouterProduitFournisseur(ProduitFournisseur t) {
        try {
            String query="INSERT INTO produit_fournisseur(nomProd,idF) values(?,?)";
            PreparedStatement smt = cnx.prepareStatement(query);
            smt.setString(1, t.getNomProd());
            smt.setInt(2, t.getIdF());
            smt.executeUpdate();
            System.out.println("ajout avec succee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void supprimerProduitFournisseur(ProduitFournisseur t) {
        try {
            String query2="delete from produit_fournisseur where id=?";
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setInt(1, t.getId());
            smt.executeUpdate();
            System.out.println("suppression avec succee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
