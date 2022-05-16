/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.app.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.esprit.app.entity.Plat;

import com.esprit.app.utils.DataSource;
import com.esprit.app.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lenovo
 */
public class Serviceplat {
     public ArrayList<Plat> plat;

    public static Serviceplat instance = null;
    public boolean resultOk;
    private Plat platclass = new Plat();
    private ConnectionRequest req;

    public Serviceplat() {
	req = DataSource.getInstance().getRequest();
    }

 

    public ArrayList<Plat>affichagePlat() {
        ArrayList<Plat> result = new ArrayList<>();
        
        String url = "http://127.0.0.1:8000/showplatjson";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Plat t = new Plat();
                        
                        //dima id fi codename one float 5outhouha
                      
            String reference = obj.get("reference").toString();
            t.setReference(reference);
            String designation = obj.get("designation").toString();
            t.setDesignation(designation);
            String description = obj.get("description").toString();
            t.setDescription(description);
           String imageP = obj.get("imagep").toString();
            t.setImageP(imageP);
            String nomProd = obj.get("nomprod").toString();
            t.setNomProd(nomProd);
           
            
          double prix = Double.parseDouble(obj.get("prix").toString());
            t.setPrix(prix);
                        
                        
                        
                        
                        //insert data into ArrayList result
                        result.add(t);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    
  public Plat getPlat(String Reference ){
        String url = "http://127.0.0.1:8000/json/"+Reference;
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        InfiniteProgress prog = new InfiniteProgress();
        Dialog d = prog.showInfiniteBlocking();
        req.setDisposeOnCompletion(d);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try{
                    platclass = parsePlat(new String(req.getResponseData()));
                }catch(IOException ex){
                    ex.printStackTrace();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return platclass;
    }
    
  public Plat parsePlat(String jsonText) throws IOException{
        plat = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String,Object> tutorialsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        Plat t = new Plat();
        String reference = tutorialsListJson.get("reference").toString();
            t.setReference(reference);
            String designation = tutorialsListJson.get("designation").toString();
            t.setDesignation(designation);
            String description = tutorialsListJson.get("description").toString();
            t.setDescription(description);
           String imageP = tutorialsListJson.get("imagep").toString();
            t.setImageP(imageP);
            String nomProd = tutorialsListJson.get("nomprod").toString();
            t.setNomProd(nomProd);
           
            
          double prix = Double.parseDouble(tutorialsListJson.get("prix").toString());
            t.setPrix(prix);
                        
                        
                        
                        
                        //insert data into ArrayList result
                       plat.add(t);
        return t;
    }
    
}
