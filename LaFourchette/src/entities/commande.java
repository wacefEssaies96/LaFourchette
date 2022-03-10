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
    private int idU;
   // private String referenceP;
    private String etatC;
    private String livraison;
    private double  prixC;
    public  commande() {};

    public commande(int idC, int idU, String etatC, String livraison, double prixC) {
        this.idC = idC;
        this.idU = idU;
     //   this.referenceP = referenceP;
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

   /* public void setReferenceP(String referenceP) {
        this.referenceP = referenceP;
    }
*/
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
/*
    public String getReferenceP() {
        return referenceP;
    }*/

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
        return "commande{" + "idC=" + idC + ", idU=" + idU + ", etatC=" + etatC + ", livraison=" + livraison + ", prixC=" + prixC + '}';
    }

    

     
    
}
