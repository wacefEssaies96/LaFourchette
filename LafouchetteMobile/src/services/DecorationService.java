/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Entities.Decoration;
import com.codename1.io.CharArrayReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author Iheb
 */
public class DecorationService {
//singleton
    public static DecorationService instance = null;
    
    //initialisation connection request
    private ConnectionRequest req;
    public ArrayList<Decoration> Decoration;
    
    public static DecorationService getInstance(){
        if (instance == null)
            instance = new DecorationService();
        return instance;
    }
    
    public DecorationService(){
        req = new ConnectionRequest();
        
    }
    
    public ArrayList<Decoration> parseDecoration(String jsonText){
        try {
            Decoration=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> DecorationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)DecorationListJson.get("root");
            for(Map<String,Object> obj : list){
                     Decoration e = new Decoration();
                      try { 
                          e.setNom(obj.get("nom").toString());
                      } catch (Exception e1) {
                    System.out.println("Nom");
        }
                     
                    try {
                      e.setPrix( Double.parseDouble(obj.get("prix").toString()));
                    } catch (Exception e2) {
                    System.out.println("Prix");
                    }
                      try {
                      e.setImageD(obj.get("imaged").toString());
                       } catch (Exception e3) {
                    System.out.println("ImageD");
        }
                      try {
                Decoration.add(e);
                } catch (Exception e4) {
                    System.out.println("iheb");
        }
            }
        } catch (IOException ex) {
                    System.out.println("iheb2");
        }
      
        return Decoration;
    }
    public ArrayList<Decoration> getAllDecoration(){
         String url = Statics.BASE_URL + "/afficherdecoration";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Decoration = parseDecoration(new String(req.getResponseData()));
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Decoration;
    }
        
    
    /*
    public void ajouter(Decoration d) {
        
        try {
            String query="INSERT INTO Decoration(Nom,Prix,ImageD) values(?,?,?)";
            PreparedStatement smt = cnx.prepareStatement(query);
            smt.setString(1, d.getNom());
            smt.setDouble(2, d.getPrix());
            smt.setString(3, d.getImageD());
            smt.executeUpdate();
            System.out.println(" Decoration ajouter avec succée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void modifier(Decoration d) {
        try {
            String query2="update Decoration set  Nom=?, Prix=?, ImageD=? where IdD=?";
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setString(1, d.getNom());
            smt.setDouble(2, d.getPrix());
            smt.setString(3, d.getImageD());
            smt.setInt(4, d.getIdD());
            if(smt.executeUpdate() == 1){
                smt.executeUpdate();
                System.out.println("Decoration modifier avec succée");
            }else{
                System.out.println("Problem : Decoration modification echoue \n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void supprimer(Decoration d) {
        try {
            String query3="delete from Decoration where IdD=?";
            PreparedStatement smt = cnx.prepareStatement(query3);
            smt.setInt(1, d.getIdD());
            if(smt.executeUpdate() == 1){
                smt.executeUpdate();
                System.out.println("Decoration supprimer avec succée");
            }else{
                System.out.println("Problem : Decoration supprission echoue \n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public List<Decoration> find() {
        ArrayList l=new ArrayList(); 
        
        try {
            String query4="select * from Decoration ";
            PreparedStatement smt = cnx.prepareStatement(query4);
            Decoration D;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               D=new Decoration(rs.getInt("IdD"),rs.getString("Nom"),rs.getDouble("Prix"),rs.getString("ImageD"));
               l.add(D);
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
    */
}
