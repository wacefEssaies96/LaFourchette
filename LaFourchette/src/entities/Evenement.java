/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author user
 */
public class Evenement {
    int idE,nbrParticipants,idU; 
    String designationE,descriptionE,imageE; 

    public Evenement() {
    }

    public Evenement(int nbrParticipants, int idU, String designationE, String descriptionE, String imageE) {
        this.nbrParticipants = nbrParticipants;
        this.idU = idU;
        this.designationE = designationE;
        this.descriptionE = descriptionE;
        this.imageE = imageE;
    }

    public Evenement(int nbrParticipants, String designationE, String descriptionE, String imageE) {
        this.nbrParticipants = nbrParticipants;
        this.designationE = designationE;
        this.descriptionE = descriptionE;
        this.imageE = imageE;
    }

    public int getIdE() {
        return idE;
    }

    public void setIdE(int idE) {
        this.idE = idE;
    }

    public int getNbr_Participants() {
        return nbrParticipants;
    }

    public void setNbr_Participants(int Nbr_Participants) {
        this.nbrParticipants = Nbr_Participants;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getDesignation() {
        return designationE;
    }

    public void setDesignation(String Designation) {
        this.designationE = Designation;
    }

    public String getDescription() {
        return descriptionE;
    }

    public void setDescription(String Description) {
        this.descriptionE = Description;
    }

    public String getImageE() {
        return imageE;
    }

    public void setImageE(String imageE) {
        this.imageE = imageE;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idE=" + idE + ", Nbr_Participants=" + nbrParticipants + ", idU=" + idU + ", Designation=" + designationE + ", Description=" + descriptionE + ", imageE=" + imageE + '}';
    }
    
}
