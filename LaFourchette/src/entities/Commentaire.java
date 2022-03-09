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
public class Commentaire {
     int idCO,idU,idEvent; 
     String commentaire; 
     int nbrlike;
              

    public Commentaire() {
    }

    public Commentaire( int idCO,String commentaire, int nbrlike, int idU) {
        this.commentaire = commentaire;
        this.nbrlike = nbrlike;
        this.idCO= idCO;
        this.idU=idU;     }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

   
    public int getNbrlike() {
        return nbrlike;
    }

    public void setNbrlike(int nbrlike) {
        this.nbrlike = nbrlike;
    }

   
    public int getIdCO() {
        return idCO;
    }

    public void setIdCO(int idCO) {
        this.idCO = idCO;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public Commentaire(int idCO, int idU, int idEvent, String commentaire, int nbrlike) {
        this.idCO = idCO;
        this.idU = idU;
        this.idEvent = idEvent;
        this.commentaire = commentaire;
        this.nbrlike = nbrlike;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }


   

    





}
