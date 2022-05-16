/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Iheb
 */
public class Datetimetr {
    int iddt;
    String date,etat;

    public Datetimetr() {
    }

    public Datetimetr(int iddt, String date, String etat) {
        this.iddt = iddt;
        this.date = date;
        this.etat = etat;
    }

    public int getIddt() {
        return iddt;
    }

    public void setIddt(int iddt) {
        this.iddt = iddt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
}
