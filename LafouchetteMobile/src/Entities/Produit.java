/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author wacef
 */
public class Produit {
    private String nomProd;
    private int quantite;
    private String image;
    private double prix;

    public Produit(String nomProd, int quantite, String image, double prix) {
        this.nomProd = nomProd;
        this.quantite = quantite;
        this.image = image;
        this.prix = prix;
    }

    public Produit() {
       
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" + "nomProd=" + nomProd + ", quantite=" + quantite + ", image=" + image + ", prix=" + prix + '}';
    }
    
    
}
