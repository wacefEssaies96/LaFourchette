/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author user
 */
public class Commantaire {
    
     private int idco,nbrlike,idu,idevent;
     private String commantaire;

    public Commantaire() {
    }

    public Commantaire(int idco, int nbrlike, int idu, int idevent, String commantaire) {
        this.idco = idco;
        this.nbrlike = nbrlike;
        this.idu = idu;
        this.idevent = idevent;
        this.commantaire = commantaire;
    }

    public Commantaire(int nbrlike, int idu, int idevent, String commantaire) {
        this.nbrlike = nbrlike;
        this.idu = idu;
        this.idevent = idevent;
        this.commantaire = commantaire;
    }

    public int getIdco() {
        return idco;
    }

    public void setIdco(int idco) {
        this.idco = idco;
    }

    public int getNbrlike() {
        return nbrlike;
    }

    public void setNbrlike(int nbrlike) {
        this.nbrlike = nbrlike;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    public String getCommantaire() {
        return commantaire;
    }

    public void setCommantaire(String commantaire) {
        this.commantaire = commantaire;
    }
     
}
