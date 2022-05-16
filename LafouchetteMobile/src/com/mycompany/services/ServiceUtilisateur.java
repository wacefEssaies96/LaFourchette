/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;
import com.codename1.io.CharArrayReader;
import com.mycompany.utils.statics;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import java.util.Vector;
import com.company.entities.Utilisateur;
import com.esprit.app.gui.HomeForm;
import java.util.Map;
import com.mycompany.gui.SessionManager;
import java.io.IOException;
//import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import java.util.Properties;
//import com.mycompany.gui.signupForm;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.annotation.Resource;

/**
 *
 * @author pc
 */
public class ServiceUtilisateur {
    private Resources theme;
    public static ServiceUtilisateur instance = null;
    public static boolean resultok =true;
    String json;
    private ConnectionRequest req;
    
    public static ServiceUtilisateur getInstance(){
        if(instance == null)
            instance = new ServiceUtilisateur();
        return instance;
        
    }
    public ServiceUtilisateur(){
        
    req = new ConnectionRequest();
    
    }

   
 
    public void signup(String nom_prenom ,String password , String email,String telephone ,String addresse,String picture){
         
      
        
       String url = statics.BASE_URL +"/user/signup?email="+email.toString()+""
               +"&password="+password.toString()+""
               +"&telephone="+telephone.toString()+""
               +"&addresse="+addresse.toString()+""
                +"&picture="+picture.toString()+""
               //+"&role"+roles.getText().toString()+""
              // +"&picture"+Picture.getText().toString()+""
               +"&nom_prenom="+nom_prenom.toString();
       req.setUrl(url);
       req.addResponseListener((e)->{
           
           byte[]data = (byte[]) e.getMetaData();
           String responseData = new String(data);
           System.out.println("data===>"+responseData);
           
           
       });
       NetworkManager.getInstance().addToQueueAndWait(req);
    }
    /*
    public boolean AddPublication(Utilisateur ut) {
        String url = statics.BASE_URL +"/user/signup?email="+ut.getEmail()+""
               +"&password"+password.toString()+""
               +"&telephone"+telephone.toString()+""
               +"&addresse"+addresse.toString()+""
                +"&picture"+picture.toString()+""
               //+"&role"+roles.getText().toString()+""
              // +"&picture"+Picture.getText().toString()+""
               +"&nom_prenom"+nom_prenom.toString();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
           
            System.out.println("Publication == " + str);

        }
        );

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
  */
    public void signin(String email,String password) {
        
        
        String url = statics.BASE_URL +"/user/signin?email="+email.toString()+""
                +"&password="+password.toString();
        
//          String url = statics.BASE_URL+"/user/signin?email="+email.toString()+""
//                  +"&password="=+password.toString();
          
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
                           
            
          try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Email ou mot de passe éronné","OK",null);
            }
            else {
               System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                
                System.out.println("data =="+user.toString());
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                System.out.println("data =="+id);
                //Dialog.show("fegg" ,json,"ok",null);
                SessionManager.setId((int)id);
                
//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                SessionManager.setPassowrd(user.get("password").toString());
                //SessionManager.setNom_prenom(user.get("name").toString());
               SessionManager.setUserName(user.get("name").toString());
                SessionManager.setEmail(user.get("email").toString());
                
                //photo 
                
                if(user.get("picture") != null)
                    SessionManager.setPicture(user.get("picture").toString());
                
                System.out.println("Current_user===>"+SessionManager.getEmail()+" , "+SessionManager.getPassowrd());
                
               
                    
      }
                
System.out.println("Current_user===>"+SessionManager.getEmail()+" , "+SessionManager.getPassowrd());
              System.out.println("Current_user===>"+SessionManager.getEmail());
            }catch(Exception ex) {
               System.out.println( ex.getMessage());
               //ex.printStackTrace();
            }
          
            
          
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    /*
      public void sendMail(String recepient){
        Properties props = new Properties();
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");
        
        String myAccountEmail = "lafourchette.esprit@gmail.com";
        String password = "lafourchette123";
        
        Session session = Session.getInstance(props, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail,password);
            }
        });
        Message message = prepareMessage(session, myAccountEmail, recepient);
        try {
            Transport.send(message);
            System.out.println("Msg sent succesfully");
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(myAccountEmail));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            msg.setSubject("Confirmation Creation");
            String text = "Madame, Monsieur,"
                    + "\nPar la présente, je souhaite la bienvenu ."
                    + "\n  ";
                   
            msg.setText(text);
            return msg;
        } catch (MessagingException ex) {
            //
        }
        return null;
    }
    */
    /*
    
     public String getPasswordByEmail(String email) {
        
        String json ;
        String url = statics.BASE_URL+"/user/sendmailjson/"+email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
         req.addResponseListener((e)->{
           
           byte[]data = (byte[]) e.getMetaData();
           String responseData = new String(data);
           System.out.println("data===>"+responseData);
                
           
        NetworkManager.getInstance().addToQueueAndWait(req);
           
       });
       NetworkM
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
              json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                S
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
   return json;
    }
*/
    public String getPasswordByEmail(String email ) {
        
        
        String url = statics.BASE_URL+"/user/sendmailjson/"+email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
             json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
               // Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                Map<String,Object> password = j.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                System.out.println(password);
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }
    
    
}
