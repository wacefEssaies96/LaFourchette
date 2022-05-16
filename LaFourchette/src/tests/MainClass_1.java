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

import entities.Commentaire;
import entities.Evenement; 
import services.CommentaireCrud;
import services.EvenementCrud; 
import entities.Utilisateur;
//import services.UtilisateurService;

import java.util.ArrayList;

import utils.MyConnection;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

  import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainClass_1 {
  public static void main(String[] args) throws SQLException, AWTException {
      //UtilisateurService sp = new UtilisateurService() ;
     
      // Utilisateur a = new Utilisateur(10, "chiheb ", 4019, "chiheb.boubakri@esprit.tn","fzefzefez","Admiin","Ariana Soghra");
        //sp.ajouter(a);
        //sp.find();
        //sp.supprimer(a);
    // sp.modifier(a);
     ////////Evenelment et Commentaire///
    /* CommentaireCrud crd= new CommentaireCrud(); 
     Commentaire C1 = new Commentaire (1,"evnementtayara ","je confirme",50,1,1);
     //Commentaire C2 = new Commentaire (2,"evnement 7ala ","maaadech jey",50,1,9);*/
  // crd.ajouter(C2);
   //crd.ajouter(C1);
    //System.out.println(crd.afficherCommentaire()); 
   // crd.modifierCommentaire(C2);
   //System.out.println(crd.afficherCommentaire()); 
   // crd.supprimerCommentaire(C1);
    // System.out.println(crd.afficherCommentaire());    
    EvenementCrud erd= new EvenementCrud(); 
    java.util.Date utilDate2 = new java.util.Date();
     
     
       SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date parsed;
        Date date2; 
      try {
          
           
        
          parsed = format.parse("20120505");
          //date2 = format.parse("20001219");
          java.sql.Date sql = new java.sql.Date(parsed.getTime());
          //java.sql.Date sql2= new java.sql.Date(parsed.getTime());
         Evenement E1= new Evenement(1," an ","tbadel",sql,"C:\\Users\\user\\Downloads\\LaFourchette-main\\LaFourchette\\src\\image\\image.jpg",1200);
         Evenement E2= new Evenement(2," an ","tbadel",sql,"C:\\Users\\user\\Downloads\\LaFourchette-main\\LaFourchette\\src\\image\\image.jpg",1200);
         Evenement E3= new Evenement(3," anniveraire ","anniveraire",sql,"C:\\Users\\user\\Downloads\\LaFourchette-main\\LaFourchette\\src\\image\\image.jpg",1200);        
//erd.ajouter(E3);
          //erd.modifierEvenement(E1);
          //System.out.println( erd.tri());
   System.out.println(erd.afficherListeEvenement());

      } catch (ParseException ex) {
          Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
      }
        
      
/*CommevenementCrud com= new CommevenementCrud  (); 
  Commevenement cc= new Commevenement(1,1,1);
  Commevenement cc1= new Commevenement(2,1,2);
  Commevenement cc2= new Commevenement(3,1,3);*/
 //com.ajouter(cc2);
   
    
      //erd.supprimerEvenement(E1);
    
       /*ArrayList EtatTableResto=new ArrayList();
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
      Reservation RES = new Reservation(1, 1, 9, sqlDate,sqlDate);
      Reservation RES1 = new Reservation(2, 10, 10, sqlDate,sqlDate);
      Reservation RES2 = new Reservation(3, 3, 2, sqlDate,sqlDate);
      
     // SER_RES.ajouter(RES);
      //SER_RES.modifier(RES1);
     // SER_RES.find();

      TypeReclamationCRUD tr = new TypeReclamationCRUD() ;
      ReclamationCRUD R = new ReclamationCRUD() ;
      TypeReclamation t = new TypeReclamation("Réclamation service","T013");
      TypeReclamation t2 = new TypeReclamation("Réclamation service","TE50");
      Reclamation Rec = new Reclamation(3,"Réclamation technique",22,"qwerty","En cours");
        //tr.ajouterTypeReclamation(t);
        //tr.supprimerTypeReclamation(t);
        //tr.modifierTypeReclamation(t);
        //System.out.println(tr.afficherTypeReclamation());
        //R.ajouterReclamation(Rec);
        //R.modifierReclamation(Rec);
        //R.supprimerReclamation(Rec);
        //System.out.println(R.afficherReclamation());
            


      */  

  }  
}
