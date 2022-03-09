/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.TypeReclamation;
import java.sql.Connection;
import utils.MyConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author barki
 */
public class TypeReclamationCRUD {
    Connection cnx2;
    public TypeReclamationCRUD(){
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    public void ajouterTypeReclamation(TypeReclamation Tr){
        try {
            String requete = "INSERT INTO type_rec (typeRec,refT) "
                    + "VALUES (?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setString(1, Tr.getTypeRec());
            pst.setString(2, Tr.getRefT());
            pst.executeUpdate();
            System.out.println("Type réclamation est ajouté");
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }
    }
    
    public void modifierTypeReclamation(TypeReclamation Tr) {
        try {
            String requete2="update type_rec set refT=? where typeRec=?";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, Tr.getRefT());
            pst.setString(2, Tr.getTypeRec());
            pst.executeUpdate();
            System.out.println("Type réclamation est modifié");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}
    
    public void supprimerTypeReclamation(TypeReclamation Tr) {
        try {
            String requete="delete from type_rec where typeRec=?";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setString(1,Tr.getTypeRec());
            pst.executeUpdate();
            System.out.println("Type réclamation est supprimé");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}
         
    public List<TypeReclamation> afficherTypeReclamation(){
        List<TypeReclamation> myList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM type_rec";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                TypeReclamation Tr = new TypeReclamation();
                Tr.setTypeRec(rs.getString("typeRec"));
                Tr.setRefT(rs.getString("refT"));
                myList.add(Tr);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
}
