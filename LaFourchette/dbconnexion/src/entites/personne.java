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
public class personne {
    private int id,num;
    private String nom,prenom;

   

    public personne(int id, String nom, String prenom, int num) {
        this.id = id;
        
        this.nom = nom;
        this.prenom = prenom;
        this.num = num;
    }
    public personne(  String nom, String prenom,int num) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.num = num;
    }
    public int getId() {
        return id;
    }

    public int getNum() {
        return num;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "personne{" + "id=" + id + ", num=" + num + ", nom=" + nom + ", prenom=" + prenom + '}';
    }
    
    
}
