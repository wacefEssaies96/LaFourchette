/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import Entities.Datetimetr;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author Iheb
 */
public class DatetimetrService {
//singleton
    public static DatetimetrService instance = null;
    
    //initialisation connection request
    private ConnectionRequest req;
    public ArrayList<Datetimetr> Datetimetr;
    
    public static DatetimetrService getInstance(){
        if (instance == null)
            instance = new DatetimetrService();
        return instance;
    }
    
    public DatetimetrService(){
        req = new ConnectionRequest();
        
    }
    
    public ArrayList<Datetimetr> parseDatetimetr(String jsonText){
        try {
            Datetimetr=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> DatetimetrListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)DatetimetrListJson.get("root");
            for(Map<String,Object> obj : list){
                     Datetimetr e = new Datetimetr();
                      try { 
                          float iddt = Float.parseFloat(obj.get("iddt").toString());                        
                          e.setIddt((int)iddt); 
                          
                      } catch (Exception e1) {
                    System.out.println("iddt");
        }  
                    try {
                      e.setEtat(obj.get("etat").toString());
                    } catch (Exception e2) {
                    System.out.println("etat");
                    }
                      try {
                          
                          String DateConverter =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
                        
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        
                        SimpleDateFormat formatterch = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                        String dateString = formatterch.format(currentTime);
                        e.setDate(dateString);
                       } catch (Exception e3) {
                    System.out.println("date");
        }
                      try {
                Datetimetr.add(e);
                } catch (Exception e4) {
                    System.out.println("iheb");
        }
            }
        } catch (IOException ex) {
                    System.out.println("iheb2");
        }
      
        return Datetimetr;
    }
    public ArrayList<Datetimetr> getAllDatetimetr(){
         String url = Statics.BASE_URL + "/afficherDatetimetr";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Datetimetr = parseDatetimetr(new String(req.getResponseData()));
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Datetimetr;
    }
    
}
