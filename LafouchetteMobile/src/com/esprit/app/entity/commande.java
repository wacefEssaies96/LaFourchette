/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.app.entity;

/**
 *
 * @author lenovo
 */

public class commande {
    private int idC;
 
    private int idU;
    private String referenceplat;
   // private String referenceP;
    private int quantity;

    public int getIdC() {
        return idC;
    }

    public int getIdU() {
        return idU;
    }

    public String getReferenceplat() {
        return referenceplat;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getLivraison() {
        return livraison;
    }

    public double getPrixC() {
        return prixC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public void setReferenceplat(String referenceplat) {
        this.referenceplat = referenceplat;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setLivraison(String livraison) {
        this.livraison = livraison;
    }

    public void setPrixC(double prixC) {
        this.prixC = prixC;
    }

    public commande(int idC, int idU, String referenceplat, int quantity, String livraison, double prixC) {
        this.idC = idC;
        this.idU = idU;
        this.referenceplat = referenceplat;
        this.quantity = quantity;
        this.livraison = livraison;
        this.prixC = prixC;
    }
    private String livraison;
    private double  prixC;
    public  commande() {};

    public commande(int idC, int idU, String etatC, String livraison, double prixC) {
        this.idC = idC;
      //  this.idU = idU;
     //   this.referenceP = referenceP;
     //   this.etatC = etatC;
        this.livraison = livraison;
        this.prixC = prixC;
    }

  

   

    
      

    

     
    
}
