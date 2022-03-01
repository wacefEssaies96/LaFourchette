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
    private int IdR,IdU;
    private Date DateCreation,DateModification;

    public Reservation() {
    }

    public Reservation(int IdR, int IdU, Date DateCreation, Date DateModification) {
        this.IdR = IdR;
        this.IdU = IdU;
        this.DateCreation = DateCreation;
        this.DateModification = DateModification;
    }

    @Override
    public String toString() {
        return "Reservation{" + "IdR=" + IdR + ", IdU=" + IdU + ", DateCreation=" + DateCreation + ", DateModification=" + DateModification + '}';
    }

    public int getIdR() {
        return IdR;
    }

    public void setIdR(int IdR) {
        this.IdR = IdR;
    }

    public int getIdU() {
        return IdU;
    }

    public void setIdU(int IdU) {
        this.IdU = IdU;
    }

    public Date getDateCreation() {
        return DateCreation;
    }

    public void setDateCreation(Date DateCreation) {
        this.DateCreation = DateCreation;
    }

    public Date getDateModification() {
        return DateModification;
    }

    public void setDateModification(Date DateModification) {
        this.DateModification = DateModification;
    }
    
    
}
