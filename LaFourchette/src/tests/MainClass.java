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
import entities.Fournisseur;
import entities.Plat;
import entities.Produit;
import entities.ProduitFournisseur;
import entities.Reservation;
import entities.Table_Resto;
import java.util.ArrayList;
import services.EvenementCrud;
import services.ReservationService;
import services.Table_RestoService;
import utils.MyConnection;
import entities.Reclamation;
import entities.TypeReclamation;
import java.sql.Connection;
import java.util.List;
import services.FournisseurService;
import services.ProduitFournisseurService;
import services.ProduitService;
import services.ReclamationCRUD;
import services.TypeReclamationCRUD;
public class MainClass {
    public static void main(String[] args) {
        
        ProduitService ps = new ProduitService();
        FournisseurService fs = new FournisseurService();
        ProduitFournisseurService pfs = new ProduitFournisseurService();
        
        //System.out.println(ps.afficherListeProduits());
        
        System.out.println(fs.afficherListFournisseur());
        //fs.afficherListFournisseur();


  }  
}
