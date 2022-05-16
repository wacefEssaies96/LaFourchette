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
public class Reservation {
    private int IdR;
    private String IdU,DateCreation,DateModification;

    public Reservation() {
    }

    public Reservation(int IdR, String IdU, String DateCreation, String DateModification) {
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

    public String getIdU() {
        return IdU;
    }

    public void setIdU(String IdU) {
        this.IdU = IdU;
    }

    public String getDateCreation() {
        return DateCreation;
    }

    public void setDateCreation(String DateCreation) {
        this.DateCreation = DateCreation;
    }

    public String getDateModification() {
        return DateModification;
    }

    public void setDateModification(String DateModification) {
        this.DateModification = DateModification;
    }
    
    
}
