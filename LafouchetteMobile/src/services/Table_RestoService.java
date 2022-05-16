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
import com.codename1.ui.events.ActionListener;
import Entities.Table_Resto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author Iheb
 */
public class Table_RestoService {


//singleton
    public static Table_RestoService instance = null;
    
    //initialisation connection request
    private ConnectionRequest req;
    public ArrayList<Table_Resto> Table_Resto;
    
    public static Table_RestoService getInstance(){
        if (instance == null)
            instance = new Table_RestoService();
        return instance;
    }
    
    public Table_RestoService(){
        req = new ConnectionRequest();
        
    }
    
    public ArrayList<Table_Resto> parseTableResto(String jsonText){
        try {
             Table_Resto=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> TableRestoListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)TableRestoListJson.get("root");
            for(Map<String,Object> obj : list){
                Table_Resto e = new Table_Resto();
                try { 
                    float idt = Float.parseFloat(obj.get("idt").toString());                        
                    e.setIdT((int)idt); 
                } catch (Exception e1) {
                    System.out.println("idt");
                }
                try { 
                    float nbrp = Float.parseFloat(obj.get("nbrplace").toString());                        
                    e.setNbrPlace((int)nbrp); 
                } catch (Exception e2) {
                    System.out.println("nbrp");
                }
                try {                        
                    e.setImageTable(obj.get("imagetable").toString()); 
                } catch (Exception e3) {
                    System.out.println("imagetable");
                }
                try {                        
                    e.setVip(obj.get("vip").toString()); 
                } catch (Exception e4) {
                    System.out.println("vip");
                }
                try {                        
                    e.setPrix(Double.parseDouble(obj.get("prix").toString())); 
                } catch (Exception e5) {
                    System.out.println("prix");
                }
                try {
                    Table_Resto.add(e);
                } catch (Exception e6) {
                    System.out.println("iheb");
                }
            }
            } catch (Exception e7) {
                System.out.println("iheb2");
            }
     // System.out.println("Table_Resto"+Table_Resto.toString());
        return Table_Resto;
    }
    public ArrayList<Table_Resto> getAllTable_Resto(){
         String url = Statics.BASE_URL + "/tabledisponiblejson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Table_Resto = parseTableResto(new String(req.getResponseData()));
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Table_Resto;
    }
        
    
    /*
    public void ajouter(Table_Resto t) {
        
        try {
            String query="INSERT INTO Table_Resto(NbrPlace,Etat,ImageTable,Vip,Prix) values(?,?,?,?,?)";
            PreparedStatement smt = cnx.prepareStatement(query);
            smt.setInt(1, t.getNbrPlace());
            smt.setString(2, t.getEtat());
            smt.setString(3, t.getImageTable());
            smt.setString(4, t.getVip());
            smt.setDouble(5, t.getPrix());
            smt.executeUpdate();
            System.out.println(" Table_Resto ajouter avec succée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void modifier(Table_Resto t) {
        try {
            
            String query2="update Table_Resto set  NbrPlace=?, Etat=?,ImageTable=?,Vip=?,Prix=? where IdT=?";
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setInt(1, t.getNbrPlace());
            smt.setString(2, t.getEtat());
            smt.setString(3, t.getImageTable());
            smt.setString(4, t.getVip());
            smt.setDouble(5, t.getPrix());
            smt.setInt(6, t.getIdT());
            if(smt.executeUpdate() == 1){
                smt.executeUpdate();
                System.out.println("Table_Resto modifier avec succée");
            }else{
                System.out.println("Problem : Table_Resto modification echoue \n");
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void supprimer(Table_Resto t) {
        try {
            String query3="delete from Table_Resto where IdT=?";
            PreparedStatement smt = cnx.prepareStatement(query3);
            smt.setInt(1, t.getIdT());
            if(smt.executeUpdate() == 1){
                smt.executeUpdate();
                System.out.println("Table_Resto supprimer avec succée");
            }else{
                System.out.println("Problem : Table_Resto supprission echoue \n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Table_Resto detailleTable_Resto(int idt) {
        try {
            String query5="select * from Table_Resto where IdT=?";
            PreparedStatement smt = cnx.prepareStatement(query5);
            smt.setInt(1, idt);
            Table_Resto TR;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               TR=new Table_Resto(rs.getInt("IdT"),rs.getInt("NbrPlace"),rs.getString("Etat"),rs.getString("ImageTable"),rs.getString("Vip"),rs.getDouble("Prix"));
               System.out.println(TR.toString());
               return TR;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    public List<Table_Resto> find() {
        ArrayList l=new ArrayList(); 
        
        try {
            String query4="select * from Table_Resto";
            PreparedStatement smt = cnx.prepareStatement(query4);
            Table_Resto TR;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               TR=new Table_Resto(rs.getInt("IdT"),rs.getInt("NbrPlace"),rs.getString("Etat"),rs.getString("ImageTable"),rs.getString("Vip"),rs.getDouble("Prix"));
               l.add(TR);
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
    

    
    public List<Table_Resto> TR_Dispo() {
        ArrayList l=new ArrayList(); 
        
        try {
            String query6="select * from Table_Resto where Etat = 'Disponible'";
            PreparedStatement smt = cnx.prepareStatement(query6);
            Table_Resto TR;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               TR=new Table_Resto(rs.getInt("IdT"),rs.getInt("NbrPlace"),rs.getString("Etat"),rs.getString("ImageTable"),rs.getString("Vip"),rs.getDouble("Prix"));
               l.add(TR);
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
    public List<Table_Resto> TR_Dispo_Vip() {
        ArrayList l=new ArrayList(); 
        
        try {
            String query7="select * from Table_Resto where Etat = 'Disponible' and Vip='Oui'";
            PreparedStatement smt = cnx.prepareStatement(query7);
            Table_Resto TR;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               TR=new Table_Resto(rs.getInt("IdT"),rs.getInt("NbrPlace"),rs.getString("Etat"),rs.getString("ImageTable"),rs.getString("Vip"),rs.getDouble("Prix"));
               l.add(TR);
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
    
    public void table_Reserve(Table_Resto t) {
        try {
            
            String query2="update Table_Resto set  Etat=? where IdT=?";
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setString(1, "Reserve");
            smt.setInt(2, t.getIdT());
            if(smt.executeUpdate() == 1){
                smt.executeUpdate();
                System.out.println("Table_Resto reserve avec succée");
            }else{
                System.out.println("Problem : Table_Resto reserve echoue \n");
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/
    
}
