/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.app.entity;

/**
 *
 * @author wacef
 */
public class Plat {
    
    private String reference;
    private String designation;
    private double prix;
    private String description;
    private String imageP;
    private String nomProd;

    public Plat(String reference, String designation, double prix, String description, String imageP, String nomProd) {
        this.reference = reference;
        this.designation = designation;
        this.prix = prix;
        this.description = description;
        this.imageP = imageP;
        this.nomProd = nomProd;
    }
public Plat(){
}

    

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageP() {
        return imageP;
    }

    public void setImageP(String imageP) {
        this.imageP = imageP;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    @Override
    public String toString() {
        return "Plat{" + "reference=" + reference + ", designation=" + designation + ", prix=" + prix + ", description=" + description + ", imageP=" + imageP + ", nomProd=" + nomProd + '}';
    }
    
    
    
}
