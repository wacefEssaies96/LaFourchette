/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Plat;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author anice
 */
public interface iplat {
    public void ajouter(Plat t) throws SQLException;
    public void modifier(Plat t)throws SQLException;
    public void supprimer(Plat t)throws SQLException;
}
