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


import services.FournisseurService;
import services.ProduitFournisseurService;
import services.ProduitService;

public class MainClass {
    public static void main(String[] args) {
        
        ProduitService ps = new ProduitService();
        FournisseurService fs = new FournisseurService();
        ProduitFournisseurService pfs = new ProduitFournisseurService();
        
        //System.out.println(ps.afficherListeProduits());
        //System.out.println(fs.afficherListFournisseur());
        //fs.afficherListFournisseur();


  }  
}
