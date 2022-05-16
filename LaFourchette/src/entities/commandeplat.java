/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author lenovo
 */
public class commandeplat {
    private int idC;
    private String reference;
    //public  commandeplat() {};

    public commandeplat(int idC, String reference) {
        this.idC = idC;
        this.reference = reference;
    }
  
      public commandeplat(int id,int idC) {
        this.idC = idC;
      
    }
     public commandeplat(int id,int idC, String reference) {
        this.idC = idC;
        this.reference = reference;
    }

    public int getIdC() {
        return idC;
    }

    public String getReference() {
        return reference;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "commandeplat{" + "idC=" + idC + ", reference=" + reference + '}';
    }

    
    
}
