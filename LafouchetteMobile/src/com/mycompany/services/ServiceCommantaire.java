/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Commantaire;
import com.mycompany.entities.Evenement;
import com.mycompany.utils.statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ServiceCommantaire {
    public static ServiceCommantaire instance = null ; 
     public ArrayList<Commantaire> Commantaire; 
    private ConnectionRequest req; 
    
    public static ServiceCommantaire getInstance(){
        if (instance ==null)
            instance = new ServiceCommantaire();
        return instance; 
    }
    public  ServiceCommantaire(){
        req= new  ConnectionRequest(); 
        
    }
    
    //affichage 
    /*public ArrayList<Commantaire> AffichageCommanataire(int idevent){
        ArrayList<Commantaire> Result= new  ArrayList<>();
        String url = Statics.BASE_URL+"/displayC/"+idevent; 
        req.setUrl(url); 
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp; 
                jsonp= new JSONParser(); 
                try{
                    Map<String,Object>mapCommanataire= jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray() ));
                    List<Map<String,Object>>listOfMaps = (List<Map<String,Object>>)mapCommanataire.get("root");
                    
                    
                    for(Map<String,Object> obj: listOfMaps){
                        Commantaire c= new Commantaire();  
                        System.out.println(c.toString());
                        String Commentaire =obj.get("Commentaire").toString();
                    
                        c.setCommantaire(Commentaire);
                         
                        Result.add(c);
                    }
                    
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(Result);
        return Result; 
        
    }
    */
    
    
    public ArrayList<Commantaire> parseCommantaire(String jsonText){
        try {
            
           Commantaire=new ArrayList<>(); 
            JSONParser j = new JSONParser();
            Map<String,Object> CommantaireListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)CommantaireListJson.get("root");
            for(Map<String,Object> obj : list){
                     Commantaire e = new Commantaire();
                      try { 
                          e.setCommantaire(obj.get("commantaire").toString());
                      } catch (Exception e1) {
                    System.out.println("Commantaire");
        }
                     
                    
                      
                      try {
                Commantaire.add(e);
                } catch (Exception e4) {
                    System.out.println("erreur");
        }
            }
        } catch (Exception ex) {
                    System.out.println("erreur");
        }
      
        return Commantaire;
    }
    public ArrayList<Commantaire> getAllCommtr(int idevent){
       Commantaire = new ArrayList<>(); 
         String url = statics.BASE_URL + "/displayC/"+idevent;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //Commantaire = parseCommantaire(new String(req.getResponseData()));
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    
        System.out.println(Commantaire);
        return Commantaire;
    }
    
     //ajout 
    public void ajoutcomtr(Commantaire c) {
        
        String url =statics.BASE_URL+"/addComtr?idevent="+c.getIdevent()+"&commantaire="+c.getCommantaire()+"&idu="+c.getIdu(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    
    
}
