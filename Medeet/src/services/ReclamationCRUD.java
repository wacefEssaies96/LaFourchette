/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author barki
 */
import entities.Reclamation;
import entities.TypeReclamation;
import java.sql.Connection;
import utils.MyConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;

public class ReclamationCRUD {
     Connection cnx2;
    public ReclamationCRUD(){
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    public void ajouterReclamation(Reclamation R){
        try {
            String requete = "INSERT INTO reclam (typeRec,idU,description,etatRec) "
                    + "VALUES (?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setString(1, R.getTypeRec());
            pst.setInt(2, R.getIdU());
            pst.setString(3, R.getDescription());
            pst.setString(4, R.getEtatRec());
            pst.executeUpdate();
            System.out.println("Réclamation est ajouté");
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }
    }
    
    public void modifierReclamation(Reclamation R) {
        try {
            String requete2="update reclam set typeRec=?,idU=?,description=?,etatRec=? where idRec=?";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, R.getTypeRec());
            pst.setInt(2, R.getIdU());
            pst.setString(3, R.getDescription());
            pst.setString(4, R.getEtatRec());
            pst.setInt(5, R.getIdRec());
            pst.executeUpdate();
           
            System.out.println("Réclamation est modifié");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}
    
    public void supprimerReclamation(Reclamation R) {
        try {
            String requete="delete from reclam where idRec=?";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setInt(1,R.getIdRec());
            pst.executeUpdate();
           
            System.out.println("Réclamation est supprimée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}
         
    public List<Reclamation> afficherReclamation(){
        List<Reclamation> myList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM reclam";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Reclamation R = new Reclamation();
                R.setIdRec(rs.getInt("idRec"));
                R.setTypeRec(rs.getString("typeRec"));
                R.setIdU(rs.getInt("idU"));
                R.setDescription(rs.getString("description"));
                R.setEtatRec(rs.getString("etatRec"));
                myList.add(R);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
    public List joinRec(){
        List myList = new ArrayList<>();
        try {
            
            String requete = "SELECT * FROM reclam r left join type_rec tr on r.typeRec=tr.typeRec";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Reclamation R = new Reclamation();
                 TypeReclamation Tr = new TypeReclamation();
                Tr.setTypeRec(rs.getString("typeRec"));
                Tr.setRefT(rs.getString("refT"));
                R.setIdRec(rs.getInt("idRec"));
                R.setTypeRec(rs.getString("typeRec"));
                R.setIdU(rs.getInt("idU"));
                R.setDescription(rs.getString("description"));
                R.setEtatRec(rs.getString("etatRec"));
                myList.add(R);
                myList.add(Tr);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
}
