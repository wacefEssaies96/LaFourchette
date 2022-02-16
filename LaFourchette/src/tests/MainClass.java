/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

/**
 *
 * @author user
 */

import entities.Utilisateur;
import services.UtilisateurService;
import utils.MyConnection;
public class MainClass {
  public static void main(String[] args) {
      UtilisateurService sp = new UtilisateurService() ;
     
       Utilisateur a = new Utilisateur(6, "chiheb boubakri", 55353632, "chiheb.boubakri@esprit.tn","fzefzefez","Admiin","Ariana Soghra");
        //sp.ajouter(a);
        //sp.find();
        //sp.supprimer(a);
     sp.modifier(a);
        

  }  
}
