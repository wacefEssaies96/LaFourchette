/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author user
 */
public class UtilisateurE {
    int idU,telephone; 
    String nom_prenom,email,mdp,role,adresse;

    public UtilisateurE() {
    }

    public UtilisateurE(int idU, int telephone, String nom_prenom, String email, String mdp, String role, String adresse) {
        this.idU = idU;
        this.telephone = telephone;
        this.nom_prenom = nom_prenom;
        this.email = email;
        this.mdp = mdp;
        this.role = role;
        this.adresse = adresse;
    }

    public UtilisateurE(int telephone, String nom_prenom, String email, String mdp, String role, String adresse) {
        this.telephone = telephone;
        this.nom_prenom = nom_prenom;
        this.email = email;
        this.mdp = mdp;
        this.role = role;
        this.adresse = adresse;
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

    public String getNom_Prenom() {
        return nom_prenom;
    }

    public void setNom_Prenom(String nom_prenom) {
        this.nom_prenom = nom_prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddresse() {
        return adresse;
    }

    public void setAddresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "idU=" + idU + ", telephone=" + telephone + ", Nom_Prenom=" + nom_prenom + ", Email=" + email + ", Mdp=" + mdp + ", Role=" + role + ", Addresse=" + adresse + '}';
    }
    
    
}
