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
            String query="INSERT INTO Table_Resto(nbrPlace,Etat) values(?,?)";
            PreparedStatement smt = cnx.prepareStatement(query);
            smt.setInt(1, t.getNbrPlace());
            smt.setString(2, t.getEtat());
            smt.executeUpdate();
            System.out.println(" Table_Resto ajouter avec succée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void modifier(Table_Resto t) {
        try {
            
            String query2="update Table_Resto set  nbrPlace=?, Etat=? where idT=?";
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setInt(1, t.getNbrPlace());
            smt.setString(2, t.getEtat());
            smt.setInt(3, t.getIdT());
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
            String query3="delete from Table_Resto where idT=?";
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

    
    public List<Table_Resto> find() {
        ArrayList l=new ArrayList(); 
        
        try {
            String query4="select * from Table_Resto";
            PreparedStatement smt = cnx.prepareStatement(query4);
            Table_Resto TR;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               TR=new Table_Resto(rs.getInt("idT"),rs.getInt("nbrPlace"),rs.getString("Etat"));
               l.add(TR);
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
    
}
