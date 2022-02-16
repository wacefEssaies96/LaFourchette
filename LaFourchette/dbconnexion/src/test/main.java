/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entites.Utilisateur;
import services.UtilisateurService;

import utils.mydb;

/**
 *
 * @author anice
 */
public class main {
    public static void main(String[] args) {
        
        UtilisateurService sp = new UtilisateurService() ;
     
       Utilisateur a = new Utilisateur(2, "chiheb chiheb", 55353632, "chiheb.boubakri@esprit.tn","fzefzefez","Admiin","Ariana Soghra");
       //sp.ajouter(a);
        //sp.find();
        //sp.supprimer(a);
      sp.modifier(a);
        
        
    }

   
    
        
    
    
   
    
}
