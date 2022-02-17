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
import entities.Reservation;
import entities.Table_Resto;
import java.util.ArrayList;
import services.EvenementCrud;
import services.ReservationService;
import services.Table_RestoService;
import utils.MyConnection;
import entities.Reclamation;
import entities.TypeReclamation;
import services.ReclamationCRUD;
import services.TypeReclamationCRUD;
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


          
           ArrayList EtatTableResto=new ArrayList();
       EtatTableResto.add("Disponible");
       EtatTableResto.add("Reserve");
        
        
      Table_RestoService SER_TAB_RES = new Table_RestoService() ;
      Table_Resto TAB_RES = new Table_Resto(1,5,EtatTableResto.get(0).toString());
      Table_Resto TAB_RES1 = new Table_Resto(2,2,EtatTableResto.get(1).toString());
      Table_Resto TAB_RES2 = new Table_Resto(3,2,EtatTableResto.get(1).toString());
      Table_Resto TAB_RES3 = new Table_Resto(4,2,EtatTableResto.get(1).toString());
      Table_Resto TAB_RES4 = new Table_Resto(10,2,EtatTableResto.get(1).toString());
      
      //SER_TAB_RES.ajouter(TAB_RES1);
      //SER_TAB_RES.modifier(TAB_RES);
      //SER_TAB_RES.find();
      //SER_TAB_RES.supprimer(TAB_RES4);
      
      ReservationService SER_RES = new ReservationService() ;
      java.util.Date utilDate = new java.util.Date();
      java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
      Reservation RES = new Reservation(1, 1, 2, sqlDate,sqlDate);
      Reservation RES1 = new Reservation(2, 10, 10, sqlDate,sqlDate);
      Reservation RES2 = new Reservation(3, 3, 2, sqlDate,sqlDate);
      
      //SER_RES.ajouter(RES);
      //SER_RES.modifier(RES1);
      //SER_RES.find();

      TypeReclamationCRUD tr = new TypeReclamationCRUD() ;
      ReclamationCRUD R = new ReclamationCRUD() ;
      TypeReclamation t = new TypeReclamation("Réclamation technique","T013");
      TypeReclamation t2 = new TypeReclamation("Réclamation service","TE50");
      Reclamation Rec = new Reclamation(3,"Réclamation technique",22,"qwerty","En cours");
        //tr.ajouterTypeReclamation(t);
        //tr.supprimerTypeReclamation(t);
        //tr.modifierTypeReclamation(t2);
        //System.out.println(tr.afficherTypeReclamation());
        //R.ajouterReclamation(Rec);
        //R.modifierReclamation(Rec);
        //R.supprimerReclamation(Rec);
        //System.out.println(R.afficherReclamation());
            


        

  }  
}
