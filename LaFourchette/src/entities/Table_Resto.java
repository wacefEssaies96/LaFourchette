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
public class Table_Resto {
    private int idT,nbrPlace;
    private String Etat;


    public Table_Resto(int idT, int nbrPlace, String Etat) {
        this.idT = idT;
        this.nbrPlace = nbrPlace;
        this.Etat = Etat;
    }
    
    public Table_Resto() {
    }

    @Override
    public String toString() {
        return "Table_Resto{" + "idT=" + idT + ", nbrPlace=" + nbrPlace + ", Etat=" + Etat + '}';
    }

    public int getIdT() {
        return idT;
    }

    public void setIdT(int idT) {
        this.idT = idT;
    }

    public int getNbrPlace() {
        return nbrPlace;
    }

    public void setNbrPlace(int nbrPlace) {
        this.nbrPlace = nbrPlace;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String etat) {
        this.Etat = etat;
    }
    
}
