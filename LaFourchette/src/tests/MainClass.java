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
import entities.UtilisateurE;
import services.UtilisateurCrud;
import entities.Evenement;
import services.EvenementCrud;
import utils.MyConnection;
public class MainClass {
  public static void main(String[] args) {
      /*UtilisateurService sp = new UtilisateurService() ;
     
       Utilisateur a = new Utilisateur(6, "chiheb boubakri", 55353632, "chiheb.boubakri@esprit.tn","fzefzefez","Admiin","Ariana Soghra");
        //sp.ajouter(a);
        //sp.find();
        //sp.supprimer(a);
     sp.modifier(a);*/
     ////////Evenelment et client///
     UtilisateurCrud pcd = new  UtilisateurCrud();
       //pcd.AjoutPersonne();
       
       
       UtilisateurE u1= new UtilisateurE(5555555,"dfff","c@ff" ,"ffff","client","Mourouj");
   //   pcd.AjoutUtilisateur2(u1);
      System.out.println(pcd.afficherUtilisateur());
     pcd.supprimparId(6);
     System.out.println(pcd.afficherUtilisateur());
    pcd.update(7,"farah","farah@esprit.tn","ff","client", "nacer",45258455);
            System.out.println(pcd.afficherUtilisateur());

     EvenementCrud ecd= new EvenementCrud(); 
        Evenement e1= new Evenement(20,7,"desig","desc","C:\\Users\\user\\Documents\\NetBeansProjects\\JavaApplication5\\src\\image\\image.jpg");
         ecd.AjoutEvenement2(e1);
        System.out.println(ecd.afficherEvenement());
        ecd.supprimparId(3);
       System.out.println(ecd.afficherEvenement());
       ecd.updateE(4,"d2","descriptionb","C:\\user\\Documents\\NetBeansProjects\\JavaApplication5\\src\\image_image.jpg",40,7);
        
          System.out.println(ecd.afficherEvenement());  
            


        

  }  
}
