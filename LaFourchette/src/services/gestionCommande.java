/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entites.commande;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
//import java.util.logging.Logger;
import utils.MyConnection;
/**
 *
 * @author lenovo
 */
public class gestionCommande {
    Connection cnx;
   public gestionCommande () {
         cnx = MyConnection.getInstance().getCnx();
    }
   
     /* public void ajoutercommande(){
          try {
              String requete="INSERT INTO commande (idU,reference,etatC,livraison,prixC)" +
                      "VALUES ('78','fff','en cour','non','500)";
               
             Statement st = new database().getConnection().createStatement();
             st.executeUpdate(requete);      
             
             //executeupdate que pour les requete qui maj les donnee
             System.out.println("crud mrigel");
          } catch (SQLException ex) {
              Logger.getLogger(Servicepersonne.class.getName()).log(Level.SEVERE, null, ex);
          }
         */   

      public void ajoutercommande(commande c){
          try {
              String requete="INSERT INTO Commande (idU,reference,etatC,livraison,prixC)" +
                      "VALUES (?,?,?,?,?)";
              //requete dynamique requete pre compilé 
              PreparedStatement pst = cnx.prepareStatement(requete);
              pst.setInt(1,c.getIdU());
              pst.setString(2,c.getReference());
              pst.setString(3,c.getEtatC());
               pst.setString(4,c.getLivraison());
                pst.setDouble(5,c.getPrixC());
                 
              pst.executeUpdate(); // requete 2 a ete inserer dans prepareStatment
            System.out.println("commande ajoutée"); 
            
          } catch (SQLException ex) {
              System.err.println(ex.getMessage());
          }
           
          
      }
      public void modifier(commande c) {
         try {
       String query2="update Commande set idU=?,reference=?,etatC=?,livraison=?,prixC=?  where idC=?";
                PreparedStatement pst = cnx.prepareStatement(query2);
                
               pst.setInt(1,c.getIdU());
              pst.setString(2,c.getReference());
              pst.setString(3,c.getEtatC());
               pst.setString(4,c.getLivraison());
                pst.setDouble(5,c.getPrixC());
                pst.setInt(6,c.getIdC());
                pst.executeUpdate();
                System.out.println("modification mrigla");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}
    
    
    public void supprimer(commande t) {
         try {
       String query2="delete from commande where idC=?";
                PreparedStatement pst = cnx.prepareStatement(query2);
                pst.setInt(1, t.getIdC());
                pst.executeUpdate();
                System.out.println("suppression jawha behy");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}

public List<commande> affichercommande(){
                  List<commande> myList = new ArrayList<>();
          try {
              String requete3="SELECT * FROM personne";
              Statement st =cnx.prepareStatement(requete3);
             ResultSet rs= st.executeQuery(requete3); 
                    // resultSet type de retour de executeQuery
                while(rs.next()){
                    commande p =new commande();
                    p.setIdC(rs.getInt("idC")); //num de la colonne
                    p.setIdU(rs.getInt("idU"));
                    p.setReference(rs.getString("reference"));
                    p.setLivraison(rs.getString("livraison"));
                    p.setEtatC(rs.getString("EtatC"));   
                    p.setPrixC(rs.getDouble("prixC"));
                    myList.add(p);
                }    
          } 
          catch (SQLException ex) {
               System.err.println(ex.getMessage());
         }
        return myList;   //mylist n'est pas visible dans le try
}
}