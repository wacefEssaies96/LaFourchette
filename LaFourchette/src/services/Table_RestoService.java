/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Table_Resto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

/**
 *
 * @author Iheb
 */
public class Table_RestoService {

    Connection cnx;
    
    public Table_RestoService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    
    
    public void ajouter(Table_Resto t) {
        
        try {
            String query="INSERT INTO Table_Resto(NbrPlace,Etat,ImageTable,Vip,Prix) values(?,?,?,?,?)";
            PreparedStatement smt = cnx.prepareStatement(query);
            smt.setInt(1, t.getNbrPlace());
            smt.setString(2, t.getEtat());
            smt.setString(3, t.getImageTable());
            smt.setString(4, t.getVip());
            smt.setDouble(5, t.getPrix());
            smt.executeUpdate();
            System.out.println(" Table_Resto ajouter avec succée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void modifier(Table_Resto t) {
        try {
            
            String query2="update Table_Resto set  NbrPlace=?, Etat=?,ImageTable=?,Vip=?,Prix=? where IdT=?";
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setInt(1, t.getNbrPlace());
            smt.setString(2, t.getEtat());
            smt.setString(3, t.getImageTable());
            smt.setString(4, t.getVip());
            smt.setDouble(5, t.getPrix());
            smt.setInt(6, t.getIdT());
            if(smt.executeUpdate() == 1){
                smt.executeUpdate();
                System.out.println("Table_Resto modifier avec succée");
            }else{
                System.out.println("Problem : Table_Resto modification echoue \n");
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void supprimer(Table_Resto t) {
        try {
            String query3="delete from Table_Resto where IdT=?";
            PreparedStatement smt = cnx.prepareStatement(query3);
            smt.setInt(1, t.getIdT());
            if(smt.executeUpdate() == 1){
                smt.executeUpdate();
                System.out.println("Table_Resto supprimer avec succée");
            }else{
                System.out.println("Problem : Table_Resto supprission echoue \n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Table_Resto detailleTable_Resto(int idt) {
        try {
            String query5="select * from Table_Resto where IdT=?";
            PreparedStatement smt = cnx.prepareStatement(query5);
            smt.setInt(1, idt);
            Table_Resto TR;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               TR=new Table_Resto(rs.getInt("IdT"),rs.getInt("NbrPlace"),rs.getString("Etat"),rs.getString("ImageTable"),rs.getString("Vip"),rs.getDouble("Prix"));
               System.out.println(TR.toString());
               return TR;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    public List<Table_Resto> find() {
        ArrayList l=new ArrayList(); 
        
        try {
            String query4="select * from Table_Resto";
            PreparedStatement smt = cnx.prepareStatement(query4);
            Table_Resto TR;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               TR=new Table_Resto(rs.getInt("IdT"),rs.getInt("NbrPlace"),rs.getString("Etat"),rs.getString("ImageTable"),rs.getString("Vip"),rs.getDouble("Prix"));
               l.add(TR);
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
    

    
    public List<Table_Resto> TR_Dispo() {
        ArrayList l=new ArrayList(); 
        
        try {
            String query6="select * from Table_Resto where Etat = 'Disponible'";
            PreparedStatement smt = cnx.prepareStatement(query6);
            Table_Resto TR;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               TR=new Table_Resto(rs.getInt("IdT"),rs.getInt("NbrPlace"),rs.getString("Etat"),rs.getString("ImageTable"),rs.getString("Vip"),rs.getDouble("Prix"));
               l.add(TR);
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
    public List<Table_Resto> TR_Dispo_Vip() {
        ArrayList l=new ArrayList(); 
        
        try {
            String query7="select * from Table_Resto where Etat = 'Disponible' and Vip='Oui'";
            PreparedStatement smt = cnx.prepareStatement(query7);
            Table_Resto TR;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               TR=new Table_Resto(rs.getInt("IdT"),rs.getInt("NbrPlace"),rs.getString("Etat"),rs.getString("ImageTable"),rs.getString("Vip"),rs.getDouble("Prix"));
               l.add(TR);
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
    
    public void table_Reserve(Table_Resto t) {
        try {
            
            String query2="update Table_Resto set  Etat=? where IdT=?";
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setString(1, "Reserve");
            smt.setInt(2, t.getIdT());
            if(smt.executeUpdate() == 1){
                smt.executeUpdate();
                System.out.println("Table_Resto reserve avec succée");
            }else{
                System.out.println("Problem : Table_Resto reserve echoue \n");
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
