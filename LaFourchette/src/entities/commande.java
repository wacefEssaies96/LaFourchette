/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author lenovo
 */

public class commande {
    private int idC;
    private  String referenceplat;
    private  int idU;
 
    private int quantity;
    private String livraison;
    private double  prixC;
    public  commande() {};

    @Override
    public String toString() {
        return "commande{" + "idC=" + idC + ", referenceplat=" + referenceplat + ", idU=" + idU + ", quantity=" + quantity + ", livraison=" + livraison + ", prixC=" + prixC + '}';
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public void setReferenceplat(String referenceplat) {
        this.referenceplat = referenceplat;
    }

    public void setIdU(int idU) {
        this.idU = idU;
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

    public int getIdC() {
        return idC;
    }

    public String getReferenceplat() {
        return referenceplat;
    }

    public int getIdU() {
        return idU;
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

    public commande(int idC, String referenceplat, int idU, int quantity, String livraison, double prixC) {
        this.idC = idC;
        this.referenceplat = referenceplat;
        this.idU = idU;
        this.quantity = quantity;
        this.livraison = livraison;
        this.prixC = prixC;
    }

}