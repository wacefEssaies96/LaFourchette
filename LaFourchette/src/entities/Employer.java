/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author pc
 */
public class Employer{
     float salaire;
     String job_EM;
    String nom_prenom;
String picture;
int telephone;
        String adresse;
        int idEM;
        
        
    public Employer(float salaire, String job_EM, String nom_prenom, String picture, int telephone, String adresse, int idEM) {
        this.salaire = salaire;
        this.job_EM = job_EM;
        this.nom_prenom = nom_prenom;
        this.picture = picture;
        this.telephone = telephone;
        this.adresse = adresse;
        this.idEM = idEM;
    }

    public Employer() {
        
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public String getJob_EM() {
        return job_EM;
    }

    public void setJob_EM(String job_EM) {
        this.job_EM = job_EM;
    }

    public String getNom_prenom() {
        return nom_prenom;
    }

    public void setNom_prenom(String nom_prenom) {
        this.nom_prenom = nom_prenom;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getIdEM() {
        return idEM;
    }

    public void setIdEM(int idEM) {
        this.idEM = idEM;
    }

    @Override
    public String toString() {
        return "Employer{" + "salaire=" + salaire + ", job_EM=" + job_EM + ", nom_prenom=" + nom_prenom + ", picture=" + picture + ", telephone=" + telephone + ", adresse=" + adresse + ", idEM=" + idEM + '}';
    }
 

  
  
    
    
    
}
