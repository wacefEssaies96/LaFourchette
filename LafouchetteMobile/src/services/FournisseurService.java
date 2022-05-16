/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Fournisseur;
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
import util.Statics;

/**
 *
 * @author wacef
 */
public class FournisseurService {
    public ArrayList<Fournisseur> fournisseur;
    public static FournisseurService instance = null;
    public boolean resultOK;
    private ConnectionRequest con;

    public FournisseurService() {
        con = new ConnectionRequest();
    }

    public static FournisseurService getInstance() {
        if (instance == null) {
            instance = new FournisseurService();
        }
        return instance;
    }

    public ArrayList<Fournisseur> getList() {
        ArrayList<Fournisseur> listProduits = new ArrayList<>();
        String url = Statics.BASE_URL + "/json/fournisseur";
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
                    Fournisseur p = new Fournisseur();
                    p.setIdF((int) Float.parseFloat(obj.get("idf").toString()));
                    p.setNomF(obj.get("nomf").toString());
                    p.setEmailF(obj.get("emailf").toString());
                    p.setTelephoneF((int) Float.parseFloat(obj.get("telephonef").toString()));
                    p.setLvl((int) Float.parseFloat(obj.get("lvl").toString()));
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
    
    public boolean addFournisseur(Fournisseur p) {
        String url = Statics.BASE_URL + "/fournisseur/add?nomF="+p.getNomF()+"&emailF="+p.getEmailF()+"&lvl="+p.getLvl()+"&telephoneF="+p.getTelephoneF(); //création de l'URL
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

    public boolean editFournisseur(Fournisseur p) {
        String url = Statics.BASE_URL + "/fournisseur/edit?idF="+p.getIdF()+"&nomF="+p.getNomF()+"&emailF="+p.getEmailF()+"&lvl="+p.getLvl()+"&telephoneF="+p.getTelephoneF(); //création de l'URL
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

    public boolean deleteFournisseur(int id) {
        String url = Statics.BASE_URL + "/fournisseur/delete?idF=" + id;
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
