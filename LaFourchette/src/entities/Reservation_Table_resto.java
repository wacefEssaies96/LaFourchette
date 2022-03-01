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
public class Reservation_Table_resto {
    
    private int IdRTR,IdR,IdT;

    public Reservation_Table_resto() {
    }

    public Reservation_Table_resto(int IdRTR, int IdR, int IdT) {
        this.IdRTR = IdRTR;
        this.IdR = IdR;
        this.IdT = IdT;
    }

    @Override
    public String toString() {
        return "reservation_table_resto{" + "IdRTR=" + IdRTR + ", IdR=" + IdR + ", IdT=" + IdT + '}';
    }

    public int getIdRTR() {
        return IdRTR;
    }

    public void setIdRTR(int IdRTR) {
        this.IdRTR = IdRTR;
    }

    public int getIdR() {
        return IdR;
    }

    public void setIdR(int IdR) {
        this.IdR = IdR;
    }

    public int getIdT() {
        return IdT;
    }

    public void setIdT(int IdT) {
        this.IdT = IdT;
    }
    
    
}
