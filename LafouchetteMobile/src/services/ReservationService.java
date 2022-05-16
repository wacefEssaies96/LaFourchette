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
import Entities.Reservation;
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
public class ReservationService {
    //singleton
    public static ReservationService instance = null;
    
    //initialisation connection request
    private ConnectionRequest req;
    public ArrayList<Reservation> Reservation;
    
    public static ReservationService getInstance(){
        if (instance == null)
            instance = new ReservationService();
        return instance;
    }
    
    public ReservationService(){
        req = new ConnectionRequest();
        
    }
    
    public ArrayList<Reservation> parseReservation(String jsonText){
        try {
            Reservation=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> ReservationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)ReservationListJson.get("root");
            //System.out.println("Liste parse reservation = "+list.toString());  
            for(Map<String,Object> obj : list){
                     Reservation e = new Reservation();
                    try { 
                        
                        float idr = Float.parseFloat(obj.get("idr").toString());                        
                        e.setIdR((int)idr); 
                    } catch (Exception e1) {
                        System.out.println("id reservation");
                    }
                    try {                       
                        e.setIdU(obj.get("idu").toString()); 
                    } catch (Exception e2) {
                        System.out.println("utilisateur");
                    }
                      try { 
                          String DateConverter =  obj.get("datecreation").toString().substring(obj.get("datecreation").toString().indexOf("timestamp") + 10 , obj.get("datecreation").toString().lastIndexOf("}"));
                        
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        
                        SimpleDateFormat formatterch = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                        String dateString = formatterch.format(currentTime);
                        e.setDateCreation(dateString);
                      } catch (Exception e3) {
                    System.out.println("date creation");
        }
                     
                    try {
                      String DateConverter =  obj.get("datemodification").toString().substring(obj.get("datemodification").toString().indexOf("timestamp") + 10 , obj.get("datemodification").toString().lastIndexOf("}"));
                        
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        
                        SimpleDateFormat formatterch = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                        String dateString = formatterch.format(currentTime);
                        e.setDateModification(dateString);
                    } catch (Exception e4) {
                    System.out.println("date modification");
                    }
                      try {
                Reservation.add(e);
                } catch (Exception e3) {
                    System.out.println("iheb");
        }
            }
        } catch (IOException ex) {
                    System.out.println("iheb2");
        }
      
        return Reservation;
    }
    
    public ArrayList<Reservation> getAllReservation(){
         String url = Statics.BASE_URL + "/afficherReservation";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Reservation = parseReservation(new String(req.getResponseData()));
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        //System.out.println("Reservation = "+Reservation.toString());
        return Reservation;
    }
    
    public ArrayList<Reservation> MesReservation(int idu){
         String url = Statics.BASE_URL + "/Mes_Reservationsjson/"+idu;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Reservation = parseReservation(new String(req.getResponseData()));
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        //System.out.println("mes Reservation = "+Reservation.toString());
        return Reservation;
    }
    
    public ArrayList<Object> DetailleReservation(int idr){
        ArrayList<Object> all = new ArrayList();
        //Object ob = new Object();
         String url = Statics.BASE_URL + "/showMesReservationjson/"+idr;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try{
                    JSONParser jp = new JSONParser();
                    Map<String,Object> allListJson = jp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> list = (List<Map<String,Object>>)allListJson.get("root");
                    all.add(list);
                    
                    req.removeResponseListener(this);
                }catch(IOException ex){
                   // Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
                    
                }
                //ob = parseall(new String(req.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        //all.add(ob);
        //System.out.println("Reservation de all= "+all.toString());
        return all;
    }
    public String ConfirmerReservation(int idt,int idu,int iddr,int idd){
        String iduRJ="";
         String url = Statics.BASE_URL + "/ConfirmerReservationjson/"+idt+"/"+idu+"?datereservation="+iddr+"&decoration="+idd;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String iduRJ;
                iduRJ = new String(req.getResponseData());
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(" id user confirm = "+iduRJ.toString());
        //alert dialog
        return iduRJ;
    }
    
        
/*
    
    Connection cnx;
    
    public ReservationService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    
    public void ajouter(Reservation r) {
        
        try {
            String query="INSERT INTO Reservation(IdU,DateCreation,DateModification) values(?,?,?)";
            PreparedStatement smt = cnx.prepareStatement(query);
            smt.setInt(1, r.getIdU());
            smt.setDate(2, r.getDateCreation());
            smt.setDate(3, r.getDateModification());
            smt.executeUpdate();
            System.out.println(" Reservation ajouter avec succée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void modifier(Reservation r) {
        try {
            String query2="update Reservation set  IdU=?, DateCreation=?, DateModification=? where IdR=?";
            PreparedStatement smt = cnx.prepareStatement(query2);
            smt.setInt(1, r.getIdU());
            smt.setDate(2, r.getDateCreation());
            smt.setDate(3, r.getDateModification());
            smt.setInt(4, r.getIdR());
            if(smt.executeUpdate() == 1){
                smt.executeUpdate();
                System.out.println("Reservation modifier avec succée");
            }else{
                System.out.println("Problem : Reservation modification echoue \n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void supprimer(Reservation r) {
        try {
            String query3="delete from Reservation where IdR=?";
            PreparedStatement smt = cnx.prepareStatement(query3);
            smt.setInt(1, r.getIdR());
            if(smt.executeUpdate() == 1){
                smt.executeUpdate();
                System.out.println("Reservation supprimer avec succée");
            }else{
                System.out.println("Problem : Reservation supprission echoue \n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public List<Reservation> find() {
        ArrayList l=new ArrayList(); 
        
        try {
            String query4="SELECT * FROM reservation r left join reservation_table_resto rtr on r.IdR = rtr.IdR left JOIN table_resto tr on tr.IdT = rtr.IdT left join decoration_reservation dr on dr.IdR = r.IdR left join decoration d on d.IdD = dr.IdD";
            
            PreparedStatement smt = cnx.prepareStatement(query4);
            Reservation RES;
            Table_Resto TR;
            Decoration D;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               RES=new Reservation(rs.getInt("IdR"),rs.getInt("IdU"),rs.getDate("DateCreation"),rs.getDate("DateModification"));
               TR=new Table_Resto(rs.getInt("IdT"),rs.getInt("NbrPlace"),rs.getString("Etat"),rs.getString("ImageTable"),rs.getString("Vip"),rs.getDouble("Prix"));
               D=new Decoration(rs.getInt("IdD"),rs.getString("Nom"),rs.getDouble("Prix"),rs.getString("ImageD"));
               l.add(RES);
               l.add(TR);
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
    public List<Reservation> MesResrvation(int iduser) {
        ArrayList l=new ArrayList(); 
        
        try {
            String query4="SELECT * FROM reservation r inner join reservation_table_resto rtr on r.IdR = rtr.IdR inner JOIN table_resto tr on tr.IdT = rtr.IdT left join decoration_reservation dr on dr.IdR = r.IdR left join decoration d on d.IdD = dr.IdD where IdU=?  ";
            
            PreparedStatement smt = cnx.prepareStatement(query4);
            smt.setInt(1, iduser);
            Reservation RES;
            Table_Resto TR;
            Decoration D;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               RES=new Reservation(rs.getInt("IdR"),rs.getInt("IdU"),rs.getDate("DateCreation"),rs.getDate("DateModification"));
              TR=new Table_Resto(rs.getInt("IdT"),rs.getInt("NbrPlace"),rs.getString("Etat"),rs.getString("ImageTable"),rs.getString("Vip"),rs.getDouble("Prix"));
              D=new Decoration(rs.getInt("IdD"),rs.getString("Nom"),rs.getDouble("Prix"),rs.getString("ImageD"));
               l.add(RES);
//               l.add(TR);
//               
//               l.add(D);
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
    
    public List<Reservation> afficher() {
        ArrayList l=new ArrayList(); 
        
        try {
            String query5="SELECT * FROM reservation ";
            
            PreparedStatement smt = cnx.prepareStatement(query5);
            Reservation RES;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               RES=new Reservation(rs.getInt("IdR"),rs.getInt("IdU"),rs.getDate("DateCreation"),rs.getDate("DateModification"));
               l.add(RES);
            }
            System.out.println(l);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
    public Reservation RecuperedernierReservation(int iduser) {
        Reservation SeulRes=new Reservation(); 
        
        try {
            String query5="SELECT * FROM reservation where idU=?";
            
            PreparedStatement smt = cnx.prepareStatement(query5);
            smt.setInt(1, iduser);
            Reservation RES;
            ResultSet rs= smt.executeQuery();
            while(rs.next()){
               RES=new Reservation(rs.getInt("IdR"),rs.getInt("IdU"),rs.getDate("DateCreation"),rs.getDate("DateModification"));
               SeulRes=RES;
            }
            System.out.println(SeulRes.toString());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return SeulRes;
    }
    */
}
