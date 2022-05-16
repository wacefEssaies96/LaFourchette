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
import com.codename1.l10n.SimpleDateFormat;
//import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Evenement;
import com.mycompany.utils.statics;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ServiceEvenement {
    boolean resultOK; 
    
    public static ServiceEvenement instance = null ; 
    
    private ConnectionRequest req; 
    
    public static ServiceEvenement getInstance(){
        if (instance ==null)
            instance = new ServiceEvenement();
        return instance; 
    }
    public  ServiceEvenement(){
        req= new  ConnectionRequest(); 
        
    }
    
    //affichage 
    public ArrayList<Evenement>AffichageEvenement(){
        ArrayList<Evenement> Result= new  ArrayList<>();
        String url = statics.BASE_URL+"/display"; 
        req.setUrl(url); 
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp; 
                jsonp= new JSONParser(); 
                try{
                    Map<String,Object>mapEvenement= jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray() ));
                    List<Map<String,Object>>listOfMaps = (List<Map<String,Object>>)mapEvenement.get("root");
                    
                    
                    for(Map<String,Object> obj: listOfMaps){
                        Evenement e= new Evenement(); 
                        float ide= Float.parseFloat(obj.get("ide").toString());
                        String descriptione =obj.get("descriptione").toString(); 
                        String designatione =obj.get("designatione").toString();
                        //8888888//
                        String datee=obj.get("datee").toString();
                        String imagee =obj.get("imagee").toString();
                        float nbrparticipants= Float.parseFloat(obj.get("nbrparticipants").toString());
                        
                        e.setDescriptione(descriptione);
                        e.setIde((int)ide);
                        e.setDesignatione(designatione);
                        e.setNbrparticipants((int)nbrparticipants);
                        //e.setDatee(datee);
                        e.setImagee(imagee);
                        
                        //date
                     /*
                        String DateConverter= datee.substring(datee.indexOf("timestamp") + 10 ,datee.lastIndexOf(")"));
                        Date CurrentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-DD");
                        String dateString= formatter.format(CurrentTime);
                        e.setDatee(dateString);
                         */
                     
                     try {
                      String DateConverter =  obj.get("datee").toString().substring(obj.get("datee").toString().indexOf("timestamp") + 10 , obj.get("datee").toString().lastIndexOf("}"));
                        
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        
                        SimpleDateFormat formatterch = new SimpleDateFormat("yyyy-mm-dd");
                          String dateString = formatterch.format(currentTime);
                        e.setDatee(dateString);
                    } catch (Exception e4) {
                    System.out.println("date event");
                    }
                        
                      
                        Result.add(e);
                    }
                    
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Result; 
        
    }
    
    
    
    
    
    public Evenement DetailEvenement( String ide) {
        Evenement e= new Evenement(); 
        String url = statics.BASE_URL+"/detail?"+ide;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                e.setDescriptione(obj.get("descriptione").toString());
                e.setDesignatione(obj.get("designatione").toString());
             //   e.setEtat(Integer.parseInt(obj.get("etat").toString()));
                 e.setImagee(obj.get("imagee").toString());
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return e;
        
        
    }
    
    
    public boolean sendmail(int idu)
    {   
            String url = statics.BASE_URL + "/evenement/mail?idu=" + idu;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
           
    
    }
    
    

}

