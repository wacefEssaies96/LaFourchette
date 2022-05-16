/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.commandeplat;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//import java.util.logging.Logger;
import utils.MyConnection;

/**
 *
 * @author lenovo
 */


public class gestioncommandeplat {
      Connection cnx;
      public gestioncommandeplat () {
         cnx = MyConnection.getInstance().getCnx();
    }


    
      public void ajouter(commandeplat cp) {
        
        try {
            String query="INSERT INTO Commandeplat(idC,reference) values(?,?)";
            PreparedStatement smt = cnx.prepareStatement(query);
            smt.setInt(1,cp.getIdC());
            smt.setString(2, cp.getReference());
            smt.executeUpdate();
            System.out.println(" ajoutcommandeplat ajouter avec succée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       

}