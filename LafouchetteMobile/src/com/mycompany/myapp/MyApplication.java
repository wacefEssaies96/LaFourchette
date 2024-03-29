package com.mycompany.myapp;


import GUI.ViewFournisseur;
import GUI.ViewProduit;
import static com.codename1.ui.CN.*;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import GUI.Home;
import com.codename1.ui.Button;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.app.gui.HomeForm;
import com.mycompany.gui.signinForm;
import com.mycompany.services.ServiceUtilisateur;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class MyApplication {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if(err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });        
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
      
        
//        Home h = new Home();
//        
//        Form f = new Form();
//        f.setTitle("pp");
//        f.setLayout(BoxLayout.y());
//         Button pp = new Button("Premiere page");
//        f.add(pp);
//        f.show();
//        
//        pp.addActionListener( new ActionListener(){
//            @Override
//            public void actionPerformed(ActionEvent evt){
//                // ldate.setText("date","kdsfnhlgmfslgj");
//                h.show();
//            }
//        });
        //new ViewProduit(theme).show(); 
        //new ViewFournisseur(theme).show();
        
        
        //new HomeForm(theme).show();
        
        
        
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

    public void stop() {
        current = getCurrentForm();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = getCurrentForm();
        }
    }
    
    public void destroy() {
    }

}
