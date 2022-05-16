/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.signinForm;
import com.mycompany.gui.signinForm;
import com.mycompany.services.ServiceUtilisateur;

/**
 *
 * @author Iheb
 */
class sinupForm extends Form{
    private Resources theme;
    
    
   public sinupForm(){
    
    Form  f = new Form();
                    Label sig = new Label("SignUp");
                                     f.add(sig);
        TextField nom_prenom =new TextField("","Nom");
         TextField email =new TextField("","email");
         TextField password=new TextField("","password");
          TextField addresse =new TextField("","addresse");
           TextField telephone =new TextField("","telephone");
            TextField picture =new TextField("","picture");
            //TextField picture =new TextField("","Picture");
           // ComboBox role = new ComboBox("","User","Admin");
           
        f.add(nom_prenom);
        f.add(password);
        f.add(email);
          f.add(telephone);
        f.add(addresse);
       f.add(picture);
        //f.add(picture);
        //f.add(role);

  Button inscription = new Button("SignUp");
  f.add(inscription);
  signinForm sf =new signinForm();
                   inscription.addActionListener( new ActionListener(){
                       
            @Override
            public void actionPerformed(ActionEvent evt){
                // ldate.setText("date","kdsfnhlgmfslgj");
                 if(nom_prenom.getText().equals("") && password.getText().equals("") && email.getText().equals("")){
           Dialog.show("Erreur","Veuillez remplir les champs","ok",null);
           
       }else{
                 
       /*req.addResponseListener((e)->{
           
           byte[]data = (byte[]) e.getMetaData();
           String responseData = new String(data);
           System.out.println("data===>"+responseData);
           
           
       });
       
       NetworkManager.getInstance().addToQueueAndWait(req);
       
    }
        /comment/*/
                           
                 
                // ServiceUtilisateur.getInstance().signup(nom_prenom.getText(), password.getText(), email.getText(), telephone.getText(), addresse.getText());
//                ServiceUtilisateur.getInstance().signup(nom_prenom, password, email, telephone, addresse, res);
          ServiceUtilisateur SU = new ServiceUtilisateur();
//          SU.signup(nom_prenom, password, email, telephone, addresse,picture);
// Dialog.show("Erreur",nom_prenom.getText()+password.getText()+email.getText()+telephone.getText() + addresse.getText()+picture.getText(),"ok",null);
SU.signup(nom_prenom.getText(), password.getText(), email.getText(), telephone.getText(), addresse.getText(), picture.getText());
//SU.sendMail(email.getText());
SU.getPasswordByEmail(email.getText());
                sf.show();}
            }
        });
        
        
        
        
  Button alreadyHaveAccount = new Button("Already have account !!");
   f.add(alreadyHaveAccount);
   f.show();
         //ServiceUtilisateur.getInstance().signup(nom_prenom, password, email, telephone, addresse, role,res);
        //ServiceUtilisateur.getInstance().signup(nom_prenom, password, email, telephone, addresse, role);
        // inscription.addActionListener(e-> new signup(nom_prenom, password, email, telephone, addresse, role).show());
   
alreadyHaveAccount.addActionListener( new ActionListener(){
                       
            @Override
            public void actionPerformed(ActionEvent evt){
                sf.show();
            }
        });
    
}   
}
