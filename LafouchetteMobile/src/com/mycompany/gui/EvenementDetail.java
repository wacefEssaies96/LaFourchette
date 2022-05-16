/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.mycompany.entities.Evenement;
import com.mycompany.services.ServiceEvenement;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Commantaire;
import com.mycompany.services.ServiceCommantaire;

/**
 *
 * @author user
 */

public class EvenementDetail extends Form  {
     Image imgs;
    ImageViewer imgv;
    String url = "/evenement.png";
    
    EncodedImage enc=null;
    public EvenementDetail(Evenement e){
//        EvenementDetail ev = new EvenementDetail(); 
        this.setTitle("evenementdetails");
        SpanLabel description= new SpanLabel("description:" + e.getDescriptione());
        SpanLabel designation= new SpanLabel("designation :" + e.getDesignatione());
        SpanLabel nbrparticipants= new SpanLabel("nbrparticipants :" + e.getNbrparticipants());
             SpanLabel DatePubtxt = new SpanLabel("datee" + e.getDatee());
//             try {
//                url = "/"+e.getImagee();
//                enc = EncodedImage.create("/"+e.getImagee());
//            } catch (Exception ex) {
//                System.out.println(ex.getMessage());
//            }
           // imgs = URLImage.createToStorage(enc,url, url,URLImage.RESIZE_SCALE);
            //imgv = new ImageViewer(imgs);
          
        this.addAll(description,designation,nbrparticipants,DatePubtxt); 
        //,imgv
        TextField tfCommantaire=  new TextField("","commantaire");
        this.add(tfCommantaire);
        Button valider = new Button("valider");
             this.add(valider);
             valider.addActionListener( new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent evt){
                 Commantaire  c = new Commantaire(0,3,e.getIde(),String.valueOf(tfCommantaire.getText())); 
                ServiceCommantaire.getInstance().ajoutcomtr(c);
                
            
               
                
            }
        });
        
        
         Button liste = new Button("liste");
             this.add(liste);
             liste.addActionListener( new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent evt){
                 
                //ServiceCommantaire.getInstance().getAllCommtr(e.getIde());
                new ListComment(e.getIde()).show();
              
               
                
            }
        });
    }
}
