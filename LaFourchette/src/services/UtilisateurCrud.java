/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.UtilisateurE;

import utils.MyConnection;

/**
 *
 * @author user
 */
public class UtilisateurCrud {
    
    Connection cnx2;

    public UtilisateurCrud() {
        
         cnx2= MyConnection.getInstance().getCnx();
    }
    
    
    public void AjoutUtilisateur2(UtilisateurE u)
    {
     String requete2 = "INSERT INTO utilisateur(nom_prenom,email,mdp,role,adresse,telephone)"
                    + "VALUES (?,?,?,?,?,?)";   
        try {
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, u.getNom_Prenom());
            pst.setString(2,u.getEmail());
            pst.setString(3,u.getMdp());
            pst.setString(4,u.getRole());
            pst.setString(5,u.getAddresse());
            pst.setInt(6,u.getTelephone());
            pst.executeUpdate();
            System.out.println("Votre utilisateur est ajouté!!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
    }
    }
    public List<UtilisateurE>afficherUtilisateur(){
        List<UtilisateurE>myList = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM utilisateur";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
                UtilisateurE u =new UtilisateurE();
                u.setIdU(rs.getInt(1));
                u.setNom_Prenom(rs.getString("nom_prenom"));
                u.setEmail(rs.getString("email"));
                u.setMdp(rs.getString("mdp"));
                u.setRole(rs.getString("role"));
                u.setTelephone(rs.getInt(7));
                myList.add(u);
            }
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       return myList;  
    }
    
    public void supprimparId(int idU)
    {
        try {
            String requete4= "DELETE  FROM utilisateur WHERE idU="+idU;
            PreparedStatement pst = cnx2.prepareStatement(requete4);
            pst.executeUpdate();
            System.out.println("Votre utilisateur est supprimé!!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
       
    }
    public void update(int idU,String nom_prenom,String email,String mdp,String role,String adresse, int telephone)
    {
        try {
            String requete5="UPDATE utilisateur SET nom_prenom='"+nom_prenom+"', email='"+email+"',mdp='"+mdp+"' ,role='"+role+"',adresse='"+adresse+"',telephone='"+telephone+"' WHERE idU='"+idU+"'";
            
            PreparedStatement pst = cnx2.prepareStatement(requete5);          
            pst.executeUpdate();
            System.out.println("Votre utilisateur est modifiée!!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    
    
}
