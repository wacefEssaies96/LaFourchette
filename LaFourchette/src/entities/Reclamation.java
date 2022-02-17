/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author barki
 */
public class Reclamation {
    private int idRec; 
    private String typeRec; 
    private int idU;
    private String description;
    private String etatRec;
    
    public Reclamation() {
    
    }

    public Reclamation(int idRec, String typeRec, int idU, String description, String etatRec) {
        this.idRec = idRec;
        this.typeRec = typeRec;
        this.idU = idU;
        this.description = description;
        this.etatRec = etatRec;
    }

    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public String getTypeRec() {
        return typeRec;
    }

    public void setTypeRec(String typeRec) {
        this.typeRec = typeRec;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtatRec() {
        return etatRec;
    }

    public void setEtatRec(String etatRec) {
        this.etatRec = etatRec;
    }

   

    @Override
    public String toString() {
        return "Reclamation{" + "idRec=" + idRec + ", typeRec=" + typeRec + ", idU=" + idU + ", description=" + description + ", etatRec=" + etatRec + '}';
    }
    
    
    
    
}
