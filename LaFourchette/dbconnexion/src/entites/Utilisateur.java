/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author anice
 */
public class Utilisateur {
    private int id_U,Telephone;
    private String nom_prenom;
    private String email;
    private String mdp;
    private String role;
    private String adresse;
    
    public Utilisateur(int id_U, String nom_prenom, int Telephone, String email, String mdp, String role, String adresse) {
        this.id_U = id_U;
        this.Telephone = Telephone;
        this.nom_prenom = nom_prenom;
        this.email = email;
        this.mdp = mdp;
        this.role = role;
        this.adresse = adresse;
    }

    public int getId_U() {
        return id_U;
    }

    public int getTelephone() {
        return Telephone;
    }

    public String getNom_prenom() {
        return nom_prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }

    public String getRole() {
        return role;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setId_U(int id_U) {
        this.id_U = id_U;
    }

    public void setTelephone(int Telephone) {
        this.Telephone = Telephone;
    }

    public void setNom_prenom(String nom_prenom) {
        this.nom_prenom = nom_prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id_U=" + id_U + ", Telephone=" + Telephone + ", nom_prenom=" + nom_prenom + ", email=" + email + ", mdp=" + mdp + ", role=" + role + ", adresse=" + adresse + '}';
    }

    
    
   

    
    
    
}
