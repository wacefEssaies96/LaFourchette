/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Iheb
 */
public class Reservation {
    private int idR,idT,idU;
    private Date dateCreation,dateModification;

    public Reservation() {
    }

    public Reservation(int idR, int idT, int idU, Date dateCreation, Date dateModification) {
        this.idR = idR;
        this.idT = idT;
        this.idU = idU;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
    }

    @Override
    public String toString() {
        return "Reservation{" + "idR=" + idR + ", idT=" + idT + ", idU=" + idU + ", dateCreation=" + dateCreation + ", dateModification=" + dateModification + '}';
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public int getIdT() {
        return idT;
    }

    public void setIdT(int idT) {
        this.idT = idT;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }
    
    
    
}
