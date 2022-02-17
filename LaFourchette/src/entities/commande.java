/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author lenovo
 */

public class commande {
    private int idC;
    private int idU;
    private String reference;
    private String etatC;
    private String livraison;
    private double  prixC;
    public  commande() {};

    public commande(int idC, int idU, String reference, String etatC, String livraison, double prix) {
        this.idC = idC;
        this.idU = idU;
        this.reference = reference;
        this.etatC = etatC;
        this.livraison = livraison;
        this.prixC = prixC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setEtatC(String etatC) {
        this.etatC = etatC;
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

    public int getIdU() {
        return idU;
    }

    public String getReference() {
        return reference;
    }

    public String getEtatC() {
        return etatC;
    }

    public String getLivraison() {
        return livraison;
    }

    public double getPrixC() {
        return prixC;
    }

    @Override
    public String toString() {
        return "commande{" + "idC=" + idC + ", idU=" + idU + ", reference=" + reference + ", etatC=" + etatC + ", livraison=" + livraison + ", prix=" + prixC + '}';
    }

     
    
}
