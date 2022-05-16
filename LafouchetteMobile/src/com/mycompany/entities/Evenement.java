/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author user
 */
public class Evenement {
     private int ide,nbrparticipants;
     private String designatione,descriptione,imagee;
     private String datee;

    public Evenement() {
    }

    public Evenement(int ide, int nbrparticipants, String designatione, String descriptione, String imagee, String datee) {
        this.ide = ide;
        this.nbrparticipants = nbrparticipants;
        this.designatione = designatione;
        this.descriptione = descriptione;
        this.imagee = imagee;
        this.datee = datee;
    }

    public Evenement(int nbrparticipants, String designatione, String descriptione, String imagee, String datee) {
        this.nbrparticipants = nbrparticipants;
        this.designatione = designatione;
        this.descriptione = descriptione;
        this.imagee = imagee;
        this.datee = datee;
    }

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public int getNbrparticipants() {
        return nbrparticipants;
    }

    public void setNbrparticipants(int nbrparticipants) {
        this.nbrparticipants = nbrparticipants;
    }

    public String getDesignatione() {
        return designatione;
    }

    public void setDesignatione(String designatione) {
        this.designatione = designatione;
    }

    public String getDescriptione() {
        return descriptione;
    }

    public void setDescriptione(String descriptione) {
        this.descriptione = descriptione;
    }

    public String getImagee() {
        return imagee;
    }

    public void setImagee(String imagee) {
        this.imagee = imagee;
    }

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    

    
    
     
     
}

