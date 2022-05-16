/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.entities;

/**
 *
 * @author pc
 */
public class Utilisateur {
     private int idU,telephone;
    private String nom_prenom;
    private String email;
    private String password;
    private String role;
    private String addresse;
    private String picture;
    public static Utilisateur Current_User;
    private String verif;
    
    
    
    
    
     public Utilisateur(int idU, int telephone, String nom_prenom, String email, String password, String role, String addresse, String picture, String verif) {
        this.idU = idU;
        this.telephone = telephone;
        this.nom_prenom = nom_prenom;
        this.email = email;
        this.password = password;
        this.role = role;
        this.addresse = addresse;
        this.picture = picture;
        this.verif = verif;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getNom_prenom() {
        return nom_prenom;
    }

    public String getAddresse() {
        return addresse;
    }

//    public String getNom_prenom() {
//        return nom_prenom;
//    }

    public void setUsername(String nom_prenom) {
        this.nom_prenom = nom_prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAdresse() {
        return addresse;
    }

    public void setAdresse(String adresse) {
        this.addresse = adresse;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public static Utilisateur getCurrent_User() {
        return Current_User;
    }

    public static void setCurrent_User(Utilisateur Current_User) {
        Utilisateur.Current_User = Current_User;
    }

    public String getVerif() {
        return verif;
    }

    public void setVerif(String verif) {
        this.verif = verif;
    }

   
}
