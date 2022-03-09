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
public class TypeReclamation {
    private String typeRec; 
    private String refT; 
    
    public TypeReclamation() {
        
    }

    public TypeReclamation(String typeRec, String refT) {
        this.typeRec = typeRec;
        this.refT = refT;
    }

    public TypeReclamation(String string) {
        this.typeRec = typeRec;
    }

    public String getTypeRec() {
        return typeRec;
    }

    public void setTypeRec(String typeRec) {
        this.typeRec = typeRec;
    }

    public String getRefT() {
        return refT;
    }

    public void setRefT(String refT) {
        this.refT = refT;
    }

    @Override
    public String toString() {
        return "TypeReclamation{" + "typeRec=" + typeRec + ", refT=" + refT + '}';
    }
    
    
}
