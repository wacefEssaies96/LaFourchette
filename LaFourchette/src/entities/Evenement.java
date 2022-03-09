/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
/**
 *
 * @author user
 */
public class Evenement {
    
     private  int idE,nbrParticipants; 
     private  String designationE,descriptionE,imageE;
    // String commentaire ;
     private Date  dateE ;

    public Evenement() {
    }

    public Evenement(int idE, String designationE,String descriptionE,Date dateE,String imageE, int nbrParticipants) {
        this.idE = idE;
        
        this.nbrParticipants = nbrParticipants;
        this.designationE = designationE;
        this.descriptionE = descriptionE;
        //this.commentaire = commentaire;
        this.imageE = imageE;
        this.dateE = dateE;
    }

    public int getIdE() {
        return idE;
    }

    public void setIdE(int idE) {
        this.idE = idE;
    }

    

    public int getNbrParticipants() {
        return nbrParticipants;
    }

    public void setNbrParticipants(int nbrParticipants) {
        this.nbrParticipants = nbrParticipants;
    }

    public String getDesignationE() {
        return designationE;
    }

    public void setDesignationE(String designationE) {
        this.designationE = designationE;
    }

    public String getDescriptionE() {
        return descriptionE;
    }

    public void setDescriptionE(String descriptionE) {
        this.descriptionE = descriptionE;
    }

   /* public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }*/

    public String getImageE() {
        return imageE;
    }

    public void setImageE(String imageE) {
        this.imageE = imageE;
    }

    public Date getDateE() {
        return dateE;
    }

    public void setDateE(Date dateE) {
        this.dateE = dateE;
    }

   /* @Override
    public String toString() {
        return "Evenement{" + "idE=" + idE + ", idU=" + idU + ", nbrParticipants=" + nbrParticipants + ", designationE=" + designationE + ", descriptionE=" + descriptionE + ", commentaire=" + commentaire + ", imageE=" + imageE + ", dateE=" + dateE + '}';
    }*/

    @Override
    public String toString() {
        return "Evenement{" + "idE=" + idE + ", nbrParticipants=" + nbrParticipants + ", designationE=" + designationE + ", descriptionE=" + descriptionE + ", imageE=" + imageE + ", dateE=" + dateE + '}';
    }

    
    




 

 


    
}
