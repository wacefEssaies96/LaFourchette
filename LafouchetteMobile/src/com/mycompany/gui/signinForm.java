/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.esprit.app.gui.HomeForm;
import com.esprit.app.gui.plat.platgui;
import com.mycompany.services.ServiceUtilisateur;
import java.io.IOException;

/**
 *
 * @author pc
 */
public class signinForm extends Form{
    private Resources theme;
    
    
   public signinForm(){
       this.setTitle("SignIn");
//        Form  f = new Form();
                    
        
        TextField email =new TextField("","email");
        TextField password =new TextField("","password");
       
        this.add(email);
         this.add(password);
        Button ins = new Button("SignIn");
        this.add(ins);
        Button Forgot = new Button("Forgot Password !!!");
        this.add(Forgot);
        /*Button cc = new Button("Create account");
        this.add(cc);
          
cc.addActionListener( new ActionListener(){
                       
            @Override
            public void actionPerformed(ActionEvent evt){
                signinForm sf =new signinForm();
                sf.show();
            }
        });*/
        // retour sinup page
         ins.addActionListener( new ActionListener(){
                       
            @Override
            public void actionPerformed(ActionEvent evt){
                // ldate.setText("date","kdsfnhlgmfslgj");
                 if(email.getText().equals("") && password.getText().equals("")){
           Dialog.show("Erreur","Veuillez remplir les champs","ok",null);
           
       }else{
                 /*
       //req.addResponseListener((e)->{
           
           byte[]data = (byte[]) e.getMetaData();
           String responseData = new String(data);
           System.out.println("data===>"+responseData);
           
           
       });
       
       NetworkManager.getInstance().addToQueueAndWait(req);
       
    }
                           */
                 
                // ServiceUtilisateur.getInstance().signup(nom_prenom.getText(), password.getText(), email.getText(), telephone.getText(), addresse.getText());
//                ServiceUtilisateur.getInstance().signup(nom_prenom, password, email, telephone, addresse, res);
          ServiceUtilisateur SU = new ServiceUtilisateur();
//          SU.signup(nom_prenom, password, email, telephone, addresse,picture);
 //Dialog.show(email.getText()+password.getText(),"ok",null);
 //System.out.println("Current_user===>"+SessionManager.getEmail()+" , "+SessionManager.getPassowrd());
//Dialog.show(email.getText()+password.getText(),"ok",null);
   SU.signin(email.getText(), password.getText());
   try {
        new HomeForm(theme).show();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}
            }
        });
        
        //f.show();
        
        
   } 
    
}
