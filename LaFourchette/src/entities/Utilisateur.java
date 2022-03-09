/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author anice
 */
public class Utilisateur {
    private int idU,telephone;
    private String nom_prenom;
    private String email;
    private String password;
    private String role;
    private String adresse;
    private String picture;
    public static Utilisateur Current_User;
    private String verif;

    
     public Utilisateur(int idU, String nom_prenom, int telephone, String email, String password,String adresse, String role,String picture) {
        this.idU = idU;
        this.telephone = telephone;
        this.nom_prenom = nom_prenom;
        this.email = email;
        this.password = password;
         this.adresse = adresse;
        this.role = role;
         this.picture = picture;
     
       
    }

    public static Utilisateur getCurrent_User() {
        return Current_User;
    }

    public static void setCurrent_User(Utilisateur Current_User) {
        Utilisateur.Current_User = Current_User;
    }
    
    public Utilisateur(int idU, String nom_prenom, int telephone, String email, String password,String adresse, String role,String picture,String verif) {
        this.idU = idU;
        this.telephone = telephone;
        this.nom_prenom = nom_prenom;
        this.email = email;
        this.password = password;
         this.adresse = adresse;
        this.role = role;
         this.picture = picture;
         this.verif = verif;
       
    }
     public Utilisateur() {
        
       
    }
     public String getVerif() {
        return verif;
    }

    public void setVerif(String verif) {
        this.verif = verif;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }

    public int getIdU() {
        return idU;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getNom_prenom() {
        return nom_prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public void setTelephone(int Telephone) {
        this.telephone = Telephone;
    }

    public void setNom_prenom(String nom_prenom) {
        this.nom_prenom = nom_prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {
        this.password = mdp;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "idU=" + idU + ", telephone=" + telephone + ", nom_prenom=" + nom_prenom + ", email=" + email + ", password=" + password + ", role=" + role + ", adresse=" + adresse + ", picture=" + picture + ", verif=" + verif + '}';
    }

    
    
   

    
    
    
}
