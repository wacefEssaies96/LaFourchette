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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;
import java.sql.Statement;
/**
 *
 * @author user
 */
public class CommentaireCrud {
    Connection cnx;
    
    
    public CommentaireCrud() {
        cnx = MyConnection.getInstance().getCnx();
    }
    
    public void ajouter(Commentaire C) {
        
        try {
            String query="INSERT INTO commentaire(idCO,commantaire,nbrlike,idU,idEvenet)values(?,?,?,?,?)";
            PreparedStatement smt = cnx.prepareStatement(query);
            smt.setInt(1, C.getIdCO());
            smt.setString(2, C.getCommentaire());
            smt.setInt(3, C.getNbrlike());
            smt.setInt(4,C.getIdU());
            smt.setInt(5,C.getIdEvent());            
            smt.executeUpdate();
            System.out.println(" Commentaire  ajouté avec succée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
  public List<Commentaire>afficherCommentaire(){
        List<Commentaire>myList = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM commentaire join Utilisateur WHERE Utilisateur.idU=Commentaire.idU";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
                Commentaire C =new Commentaire();
                C.setCommentaire(rs.getString("commantaire"));
                C.setIdEvent(rs.getInt("idevent"));
                C.setNbrlike(rs.getInt("nbrlike"));
                C.setIdU(rs.getInt("idU"));
                myList.add(C);
            }
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       return myList;  
    }
 
    /*
    public List<Commentaire> afficherCommentaire () {
        ArrayList l=new ArrayList(); 
        try {
            String query2="SELECT * FROM commentaire c inner join commevenement ce ON c.idCO = ce.idCO inner JOIN evenement e ON e.idE = ce.idE";
            PreparedStatement smt = cnx.prepareStatement(query2);
            Commentaire C ;
            Evenement E;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
                C= new Commentaire(); 
               C.setCommentaire(rs.getString("commantaire"));
                C.setReponse(rs.getString("reponse"));
                C.setNbrlike(rs.getInt(3));
                C.setNbrreponse(rs.getInt(4));
                C.setIdU(rs.getInt(5));
               l.add(C);
                 E = new Evenement(); 
                 E.setDesignationE(rs.getString("designationE"));
                E.setDescriptionE(rs.getString("descriptionE"));
                E.setDateE(rs.getDate("dateE"));
                E.setImageE(rs.getString("imageE"));
                E.setNbrParticipants(rs.getInt("nbrParticipants"));
                //.setCommentaire(rs.getString("commentaire"));
                E.setIdE(rs.getInt("idE"));
                //E.setIdU(rs.getInt("idU"));
               l.add(E);
               
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
    
    */
    public void modifierCommentaire(Commentaire C) {
        try { System.out.println("ouhhhhhhhhhhhh");
            String requete2="UPDATE commentaire SET   commantaire=?, nbrlike=?,idU=? , idEvenet=? WHERE idCO=?";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1, C.getCommentaire());
            pst.setInt(2,C.getNbrlike());
            pst.setInt(3, C.getIdU()); 
            pst.setInt(4, C.getIdEvent());
            pst.setInt(5, C.getIdCO());
            
            pst.executeUpdate();
            System.out.println("commentaire  modifié");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }
    }
    public void supprimerCommentaire(Commentaire C) {
        try {
            String query2="delete from commentaire where idCO=?";
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setInt(1, C.getIdCO());
            smt.executeUpdate();
            System.out.println("suppression avec succee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
