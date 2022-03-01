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
public class Decoration_Reservation {
    
    private int IdDR,IdR,IdD;

    public Decoration_Reservation() {
    }


    public Decoration_Reservation(int IdDR, int IdR, int IdD) {
        this.IdDR = IdDR;
        this.IdR = IdR;
        this.IdD = IdD;
    }

    @Override
    public String toString() {
        return "Decoration_Reservation{" + "IdDR=" + IdDR + ", IdR=" + IdR + ", IdD=" + IdD + '}';
    }

    public int getIdDR() {
        return IdDR;
    }

    public void setIdDR(int IdDR) {
        this.IdDR = IdDR;
    }

    public int getIdD() {
        return IdD;
    }

    public void setIdD(int IdD) {
        this.IdD = IdD;
    }

    public int getIdR() {
        return IdR;
    }

    public void setIdR(int IdR) {
        this.IdR = IdR;
    }
    
    
}
