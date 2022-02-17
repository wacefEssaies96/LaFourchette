/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author wacef
 */
public class Fournisseur {
    
    private int idF;
    private String nomF;
    private int telephoneF;
    private String categorie;
    private String emailF;

    public Fournisseur(int idF, String nomF, int telephoneF, String categorie, String emailF) {
        this.idF = idF;
        this.nomF = nomF;
        this.telephoneF = telephoneF;
        this.categorie = categorie;
        this.emailF = emailF;
    }

    public int getIdF() {
        return idF;
    }

    public void setIdF(int idF) {
        this.idF = idF;
    }

    public String getNomF() {
        return nomF;
    }

    public void setNomF(String nomF) {
        this.nomF = nomF;
    }

    public int getTelephoneF() {
        return telephoneF;
    }

    public void setTelephoneF(int telephoneF) {
        this.telephoneF = telephoneF;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getEmailF() {
        return emailF;
    }

    public void setEmailF(String emailF) {
        this.emailF = emailF;
    }

    @Override
    public String toString() {
        return "Fournisseur{" + "idF=" + idF + ", nomF=" + nomF + ", telephoneF=" + telephoneF + ", categorie=" + categorie + ", emailF=" + emailF + '}';
    }
    
    
}
