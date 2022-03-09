/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author pc
 */
public class job {
    int nb_heure ;
    String job_EM; 

    public job(int nb_heure, String job_EM) {
        this.nb_heure = nb_heure;
        this.job_EM = job_EM;
    }

    public int getNb_heure() {
        return nb_heure;
    }

    public void setNb_heure(int nb_heure) {
        this.nb_heure = nb_heure;
    }

    public String getJob_EM() {
        return job_EM;
    }

    public void setJob_EM(String job_EM) {
        this.job_EM = job_EM;
    }

    @Override
    public String toString() {
        return "job{" + "nb_heure=" + nb_heure + ", job_EM=" + job_EM + '}';
    }
   
    
    
   
  
    
    
}
