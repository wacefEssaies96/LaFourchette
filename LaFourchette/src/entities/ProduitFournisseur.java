/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author wacef
 */
public class ProduitFournisseur {
    private int id;
    private String nomProd;
    private int idF;

    public ProduitFournisseur(){
        
    }
    public ProduitFournisseur(int id, String nomProd, int idF) {
        this.id = id;
        this.nomProd = nomProd;
        this.idF = idF;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public int getIdF() {
        return idF;
    }

    public void setIdF(int idF) {
        this.idF = idF;
    }

    @Override
    public String toString() {
        return "ProduitFournisseur{" + "id=" + id + ", nomProd=" + nomProd + ", idF=" + idF + '}';
    }
    
    
}
