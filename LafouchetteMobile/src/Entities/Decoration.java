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
public class Decoration {
    private int IdD;
    private String Nom;
    private double Prix;
    private String ImageD;

    
    public Decoration() {
    }
    
    public Decoration(int IdD, String Nom, double Prix, String ImageD) {
        this.IdD = IdD;
        this.Nom = Nom;
        this.Prix = Prix;
        this.ImageD = ImageD;
    }

    @Override
    public String toString() {
        return "Decoration{" + "IdD=" + IdD + ", Nom=" + Nom + ", Prix=" + Prix + ", ImageD=" + ImageD + '}';
    }

    
    public int getIdD() {
        return IdD;
    }

    public void setIdD(int IdD) {
        this.IdD = IdD;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getImageD() {
        return ImageD;
    }

    public void setImageD(String ImageD) {
        this.ImageD = ImageD;
    }

    public double getPrix() {
        return Prix;
    }

    public void setPrix(double Prix) {
        this.Prix = Prix;
    }
    
    
}
