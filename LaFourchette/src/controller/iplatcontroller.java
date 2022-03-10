/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Plat;
import java.sql.SQLException;

/**
 *
 * @author lenovo
 */
public interface iplatcontroller {
       public void ajouter(Plat t) throws SQLException ,ClassNotFoundException;
    public void modifier(Plat t)throws SQLException,ClassNotFoundException;
    public void supprimer(Plat t)throws SQLException,ClassNotFoundException;
    
}
