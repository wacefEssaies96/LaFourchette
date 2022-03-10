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
    private int IdT,NbrPlace;
    private String Etat,ImageTable,Vip;
    private double Prix;

    public Table_Resto() {
    }
    
    public Table_Resto(int IdT, int NbrPlace, String Etat, String ImageTable, String Vip, double Prix) {
        this.IdT = IdT;
        this.NbrPlace = NbrPlace;
        this.Etat = Etat;
        this.ImageTable = ImageTable;
        this.Vip = Vip;
        this.Prix = Prix;
    }

    @Override
    public String toString() {
        return "Table_Resto{" + "IdT=" + IdT + ", NbrPlace=" + NbrPlace + ", Etat=" + Etat + ", ImageTable=" + ImageTable + ", Vip=" + Vip + ", Prix=" + Prix + '}';
    }

    public int getIdT() {
        return IdT;
    }

    public void setIdT(int IdT) {
        this.IdT = IdT;
    }

    public int getNbrPlace() {
        return NbrPlace;
    }

    public void setNbrPlace(int NbrPlace) {
        this.NbrPlace = NbrPlace;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public String getImageTable() {
        return ImageTable;
    }

    public void setImageTable(String ImageTable) {
        this.ImageTable = ImageTable;
    }

    public String getVip() {
        return Vip;
    }

    public void setVip(String Vip) {
        this.Vip = Vip;
    }

    public double getPrix() {
        return Prix;
    }

    public void setPrix(double Prix) {
        this.Prix = Prix;
    }
    
    
}
