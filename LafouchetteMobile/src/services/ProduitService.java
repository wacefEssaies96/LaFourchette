/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Produit;
import util.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author wacef
 */
public class ProduitService {
    
    public ArrayList<Produit> produit;
    public static ProduitService instance = null;
    public boolean resultOK;
    private ConnectionRequest con;

    public ProduitService() {
        con = new ConnectionRequest();
    }

    public static ProduitService getInstance() {
        if (instance == null) {
            instance = new ProduitService();
        }
        return instance;
    }

    public ArrayList<Produit> getList() {
        ArrayList<Produit> listProduits = new ArrayList<>();
        String url = Statics.BASE_URL + "/api/produit";
        con.setUrl(url);
        con.setPost(false);
        con.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp = new JSONParser();
            System.out.println(new String(con.getResponseData()));
            try {
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(tasks);
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                for (Map<String, Object> obj : list) {
                    Produit p = new Produit();
                    p.setNomProd(obj.get("nomprod").toString());
                    p.setQuantite((int) Float.parseFloat(obj.get("prix").toString()));
                    p.setPrix(Float.parseFloat(obj.get("quantite").toString()));
                    
                    listProduits.add(p);
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println(listProduits);
        return listProduits;
    }
    
    public boolean addProduit(Produit p) {
        String url = Statics.BASE_URL + "/produit/add?nomProd="+p.getNomProd()+"&quantite="+p.getQuantite()+"&prix="+p.getPrix(); //création de l'URL
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return resultOK;
    }

    public boolean editProduit(Produit p) {
        String url = Statics.BASE_URL + "/produit/edit?nomProd="+p.getNomProd()+"&quantite="+p.getQuantite()+"&prix="+p.getPrix(); //création de l'URL
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return resultOK;
    }

    public boolean deleteProduit(String nomProd) {
        String url = Statics.BASE_URL + "/produit/delete?nomProd=" + nomProd;
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return resultOK;
    }
    
    public boolean commander(String nomProd) {
        String url = Statics.BASE_URL + "/produit/commander?nomProd=" + nomProd;
        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return resultOK;
    }

    
    
}
