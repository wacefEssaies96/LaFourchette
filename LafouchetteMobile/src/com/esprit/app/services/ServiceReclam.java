/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.app.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.app.entity.Reclam;
import com.esprit.app.entity.TypeReclamation;
//import com.esprit.app.utils.Statics;

import utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author barki
 */
public class ServiceReclam {
    
    //singleton
    public static ServiceReclam instance = null;
    
    //initialisation connection request
    private ConnectionRequest req;
    public ArrayList<Reclam> Reclam;
    public ArrayList<TypeReclamation> TypeReclamation;
    public static ServiceReclam getInstance(){
        if (instance == null)
            instance = new ServiceReclam();
        return instance;
    }
    
    public ServiceReclam(){
        req = new ConnectionRequest();
        
    }
    
    public void addReclam(Reclam reclam){
        
        String url ="http://127.0.0.1:8000/addReclamJSON?description="+reclam.getDescription();
        req = new ConnectionRequest();
        req.setPost(false);
        req.setUrl(url);
        req.addResponseListener((e) -> {
//          String str = new String(req.getResponseData());
//          System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
     public ArrayList<Reclam> parseCat(String jsonText){
        try {
            Reclam=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> ArticleListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)ArticleListJson.get("root");
            for(Map<String,Object> obj : list){
                     Reclam e = new Reclam();
                      try {
                      e.setTyperec(obj.get("typerec").toString());
                      } catch (Exception e2) {
                    System.out.println("typerec");
        }
                     
                      try {
                      e.setDescription(obj.get("description").toString());
                      } catch (Exception e4) {
                    System.out.println("description");
        }
                      try {
                      e.setEtatrec(obj.get("etatrec").toString());
                      } catch (Exception e5) {
                    System.out.println("etat");
        }
                      
                
             try {
                Reclam.add(e);
                } catch (Exception e6) {
                    System.out.println("Barkia");
        }
            }
        } catch (IOException ex) {
            
        }
      
        return Reclam;
    }
     public ArrayList<TypeReclamation> parseCatt(String jsonText){
        try {
            TypeReclamation=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> ArticleListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)ArticleListJson.get("root");
            for(Map<String,Object> obj : list){
                     TypeReclamation e = new TypeReclamation();
                      try {
                      e.setTypeRec(obj.get("typerec").toString());
                      } catch (Exception e2) {
                    System.out.println("typerec");
        }
                     
                  
                      
                
             try {
                TypeReclamation.add(e);
                } catch (Exception e6) {
                    System.out.println("Barkia");
        }
            }
        } catch (IOException ex) {
            
        }
      
        return TypeReclamation;
    }
    public ArrayList<Reclam> getAllReclam(){
         String url = "http://127.0.0.1:8000/afficheTypeReclamJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Reclam = parseCat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Reclam;
    }
     public ArrayList<Reclam> getReclamUser(int idu){
         String url ="http://127.0.0.1:8000/afficheReclamUserJSON/"+idu;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Reclam = parseCat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Reclam;
    }
     public ArrayList<TypeReclamation> getAllTypeRec(){
         String url ="http://127.0.0.1:8000/afficheTypeReclamJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TypeReclamation = parseCatt(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return TypeReclamation;
    }
     
    
}
