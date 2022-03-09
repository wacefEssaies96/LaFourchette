/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Evenement;
import entities.Commentaire;
import entities.Utilisateur;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

/**
 *
 * @author user
 */
public class EvenementCrud {
    Connection cnx;
    
    
    public EvenementCrud() {
        cnx = MyConnection.getInstance().getCnx();
    }
    
    public void ajouter(Evenement E) {
        
        try {
            String query="INSERT INTO evenement(designationE,descriptionE,dateE,imageE,nbrParticipants)values(?,?,?,?,?)";
            PreparedStatement smt = cnx.prepareStatement(query);
           // smt.setInt(1, E.getIdE());
            smt.setString(1, E.getDesignationE());
            smt.setString(2, E.getDescriptionE());
           // smt.setString(4, E.getCommentaire());
            smt.setDate(3, E.getDateE());
            smt.setString(4, E.getImageE());
            smt.setInt(5, E.getNbrParticipants());
            smt.executeUpdate();
            System.out.println(" Evenement  ajouté avec succée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /*public List<Evenement>afficherEvenement(){
        List<Evenement>myList = new ArrayList<>();
        try {
//String requete3 = "SELECT * FROM evenement JOIN utilisateur ON utilisateur.idU=evenement.idU JOIN commentaire ON commentaire.commentaire=evenement.commentaire and utilisateur.idU=commentaire.idU";
            String requete3= "SELECT * FROM evenement JOIN commentaire where commentaire.commentaire=evenement.commentaire"; 
           Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
              
                Evenement E= new Evenement();
                E.setDesignationE(rs.getString("designationE"));
                E.setDescriptionE(rs.getString("descriptionE"));
                E.setDateE(rs.getDate("dateE"));
                E.setImageE(rs.getString("imageE"));
                E.setNbrParticipants(rs.getInt("nbrParticipants"));
                E.setCommentaire(rs.getString("commentaire"));
                E.setIdE(rs.getInt("idE"));
                E.setIdU(rs.getInt("idU"));
                myList.add(E);
            }
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       return myList;  
    }*/
    public List<Evenement> afficherListeEvenement() {
        ArrayList l=new ArrayList(); 
        try {
                String query2="SELECT * FROM evenement ";
             PreparedStatement smt = cnx.prepareStatement(query2);
            Evenement E;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               
                 E = new Evenement(rs.getInt("idE"),rs.getString("designationE"),rs.getString("descriptionE"),rs.getDate("dateE"),rs.getString("imageE"),rs.getInt("nbrParticipants"));

                l.add(E);
            }
           // System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
    
    
    
    
    
    
    
    
    
    
    
     public void modifierEvenement(Evenement E) {
        try { System.out.println("ouhhhhhhhhhhhh");
            String requete2="UPDATE evenement  SET  designationE=?, descriptionE=?,dateE=?,imageE=?,nbrParticipants=? WHERE idE=?";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1, E.getDesignationE());
            pst.setString(2, E.getDescriptionE());
          //  pst.setString(3,E.getCommentaire());
            pst.setDate(3, E.getDateE());
            pst.setString(4,E.getImageE());
            pst.setInt(5, E.getNbrParticipants());
            
            pst.setInt(6, E.getIdE());
            
            pst.executeUpdate();
            System.out.println("evenement  modifié");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }
    }
    
     
         public void supprimerEvenement(int idE) {
        try {
             String req = "delete from evenement  where idE = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idE);
            ps.executeUpdate();
            System.out.println("Evenement  Supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
   public List <Evenement > tri() throws SQLException
    {
        Statement stm = cnx.createStatement(); 
        String query= "SELECT * from evenement ORDER BY dateE";
        
               List <Evenement> list = new ArrayList<>(); 

        
        Evenement E  = new Evenement();
        try {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
            E = new Evenement(rs.getInt("idE"),rs.getString("designationE"),rs.getString("descriptionE"),rs.getDate("dateE"),rs.getString("imageE"),rs.getInt("nbrParticipants"));
           
            list.add(E);
            }  
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;


    }
           
    
    
    
    
    
    
    
    
    
    
         
         
         
         
         
    
    
    
}







