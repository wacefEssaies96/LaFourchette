/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.app.entity;

/**
 *
 * @author barki
 */
public class Reclam {
    
    private int idrec,idu;
    private String description,etatrec,typerec;

    public Reclam() {
    }
    
    public Reclam(int idrec, int idu, String description, String etatrec, String typerec) {
        this.idrec = idrec;
        this.idu = idu;
        this.description = description;
        this.etatrec = etatrec;
        this.typerec = typerec;
    }

    public Reclam(String description, String etatrec, String typerec) {
        this.idu = idu;
        this.description = description;
        this.etatrec = etatrec;
        this.typerec = typerec;
    }

    public int getIdrec() {
        return idrec;
    }

    public void setIdrec(int idrec) {
        this.idrec = idrec;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtatrec() {
        return etatrec;
    }

    public void setEtatrec(String etatrec) {
        this.etatrec = etatrec;
    }

    public String getTyperec() {
        return typerec;
    }

    public void setTyperec(String typerec) {
        this.typerec = typerec;
    }
    
    
    
    
}
