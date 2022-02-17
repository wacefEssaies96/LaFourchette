/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.UtilisateurE;
import entities.Evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;
/**
 *
 * @author user
 */
public class EvenementCrud {
     Connection cnx2;
     
     
     public EvenementCrud() {
        
         cnx2= MyConnection.getInstance().getCnx();
    }
       public void AjoutEvenement2(Evenement e)
    {
     String requete2 = "INSERT INTO evenement(designationE,descriptionE,imageE,nbrParticipants,idU)"
                    + "VALUES (?,?,?,?,?) ";   
        try {
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, e.getDesignation());
            pst.setString(2,e.getDescription());
            pst.setString(3,e.getImageE());
            pst.setInt(4,e.getNbr_Participants());
            pst.setInt(5,e.getIdU());
            
            pst.executeUpdate();
            System.out.println("Votre evenement est ajouté!!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
    }
    }
     
   public List<Evenement>afficherEvenement(){
        List<Evenement>myList = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM evenement join Utilisateur WHERE Utilisateur.idU=Evenement.idU";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
                Evenement  e =new Evenement();
                e.setIdE(rs.getInt(1));
                e.setDesignation(rs.getString("designationE"));
                e.setDescription(rs.getString("descriptionE"));
                e.setImageE(rs.getString("imageE"));
                e.setNbr_Participants(rs.getInt(5));
                e.setIdU(rs.getInt(6));
                myList.add(e);
            }
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       return myList;  
    }
   public void supprimparId(int idE)
    {
        try {
            String requete4= "DELETE  FROM evenement WHERE idE="+idE;
            PreparedStatement pst = cnx2.prepareStatement(requete4);
            pst.executeUpdate();
            System.out.println("Votre evenement est supprimé!!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
       
    }
    public void updateE(int idE,String designationE,String descriptionE,String imageE,int nbrParticipants, int idU)
    {
        try {
            String requete5="UPDATE evenement SET designationE='"+designationE+"', descriptionE='"+descriptionE+"',imageE='"+imageE+"' ,nbrParticipants='"+nbrParticipants+"',idU='"+idU+"' WHERE idE='"+idE+"'";
            
            PreparedStatement pst = cnx2.prepareStatement(requete5);          
            pst.executeUpdate();
            System.out.println("Votre evenement est modifiée!!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
      
}





 