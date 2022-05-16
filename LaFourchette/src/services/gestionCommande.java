/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Plat;
import entities.commande;
import entities.commandeplat;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
               
       
              String requete="INSERT INTO Commande (etatC,livraison,prixC)" +
                      "VALUES (?,?,?,?,?)";
              //requete dynamique requete pre compilé 
              PreparedStatement pst = cnx.prepareStatement(requete);
              pst.setInt(1,c.getIdU());
              pst.setString(2,c.getReferenceplat());
              pst.setInt(3,c.getQuantity());
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
       String query2="update commande set  idU=?,referenceplat=?,quantity=?,livraison=?,prixC=?  where idC=?";
                PreparedStatement pst = cnx.prepareStatement(query2);
                pst.setInt(6,c.getIdC()); 
             pst.setInt(1,c.getIdU());
              
         pst.setString(2,c.getReferenceplat());
              pst.setInt(3,c.getQuantity());
               pst.setString(4,c.getLivraison());
                pst.setDouble(5,c.getPrixC());
                
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

    
    
    public List<commande> afficherCommande() throws SQLException{
        ArrayList l=new ArrayList(); 
       
        try { 
       
            String query2="select * from commande  ";
            PreparedStatement smt = cnx.prepareStatement(query2);
            commande co;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
              // co=new commande(rs.getInt("idC"),rs.getInt("idU"),rs.getString("referenceP"),rs.getString("etatC"),rs.getString("livraison"),rs.getDouble("prixC"));
               co=new commande(rs.getInt("idC"),rs.getString("referenceplat"),rs.getInt("idU"),rs.getInt("quantity"),rs.getString("livraison"),rs.getDouble("prixC"));
               l.add(co);
            }
          //  System.out.println();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
    
    public List<gestioncommandeplat> getListecommande() {
        ArrayList l=new ArrayList(); 
        try {
            
            String query2="SELECT DISTINCT * FROM commande c left join commandeplat cp ON c.idC = cp.idC left JOIN plat p ON p.reference = cp.reference order BY c.idC";
            PreparedStatement smt = cnx.prepareStatement(query2);
            commande c;
            Plat p;
            commandeplat cp;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
             //  c=new commande(rs.getInt("idC"),rs.getInt("idU"),rs.getString("referenceP"),rs.getString("etatC"),rs.getString("livraison"),rs.getDouble("prixC"));
               c=new commande(rs.getInt("idC"),rs.getString("referenceplat"),rs.getInt("idU"),rs.getInt("quantity"),rs.getString("livraison"),rs.getDouble("prixC"));
               l.add(c);
               cp= new commandeplat(rs.getInt("id"),rs.getInt("idC"),rs.getString("reference")); 
               l.add(cp);
               p= new Plat(rs.getString("reference"), rs.getString("designation"), rs.getDouble("prix"),rs.getString("description"),rs.getString ("imageP"), rs.getString("nomProd"));
              
              
             l.add(p); 
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
    
 public  List<commande> afficherListcommande(){
        List l = getListecommande();
        List lf = new ArrayList();
        List lp = new ArrayList();
        for(int i=0 ; i<l.size() ; i++){
            if(l.get(i) instanceof commande){
                lf.add(l.get(i));
            }
            if(l.get(i) instanceof commandeplat){
                lp.add(l.get(i));
            }
        }
        List ll = new ArrayList();
        List listFinal = new ArrayList();
        for(int i=0 ; i<lf.size() ; i++){
            if(!verifcommande(ll, (commande) lf.get(i))){
                ll.add(lf.get(i));
                listFinal.add(lf.get(i));
            }
            listFinal.add(lp.get(i));
        }
        return listFinal;
    
}
  private boolean verifcommande(List<commande> lf,commande f){
        for(int i=0 ; i<lf.size() ; i++){
            if(f.getIdC() == lf.get(i).getIdC()){
                return true;
            }
        }return false;
  } 



 public List<commande> affichercommande(){
                  List<commande> myList = new ArrayList<>();
          try {
              String requete3="SELECT * FROM Commande";
              Statement st =cnx.prepareStatement(requete3);
             ResultSet rs= st.executeQuery(requete3); 
                    // resultSet type de retour de executeQuery
                while(rs.next()){
                    commande p =new commande();
                    p.setIdC(rs.getInt("idC")); //num de la colonne
                //    p.setIdU(rs.getInt("idU"));
                 //   p.setReferenceP(rs.getString("referenceP"));
                    p.setLivraison(rs.getString("livraison"));
                    p.setQuantity(rs.getInt("Quantity"));   
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
