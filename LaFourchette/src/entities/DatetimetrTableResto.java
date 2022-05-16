/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Iheb
 */
public class DatetimetrTableResto {
    int idDTR,idt,iddt;

    public DatetimetrTableResto() {
    }

    public DatetimetrTableResto(int idDTR, int idt, int iddt) {
        this.idDTR = idDTR;
        this.idt = idt;
        this.iddt = iddt;
    }

    public int getIdDTR() {
        return idDTR;
    }

    public void setIdDTR(int idDTR) {
        this.idDTR = idDTR;
    }

    public int getIdt() {
        return idt;
    }

    public void setIdt(int idt) {
        this.idt = idt;
    }

    public int getIddt() {
        return iddt;
    }

    public void setIddt(int iddt) {
        this.iddt = iddt;
    }
    
}
