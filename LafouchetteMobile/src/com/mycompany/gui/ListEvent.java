/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

//import com.codename1.components.ImageViewer;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
//import com.codename1.ui.EncodedImage;
//import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Evenement;
import com.mycompany.services.ServiceEvenement;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class ListEvent extends Form {

   /* public static void addActionListener(ActionListener actionListener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;
    String url = "/evenement.jpg";  
      int element=51;

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }
    public ListEvent()
    {
        
    
     this.setTitle("Liste Event");
        this.setLayout(BoxLayout.y());
       
        
        ServiceEvenement ds = new ServiceEvenement();
        ArrayList<Evenement> list = ds.AffichageEvenement();
         System.out.println(list.toString());
         
        for (Evenement d : list) {
            
             SpanLabel description= new SpanLabel("description:" + d.getDescriptione());
             SpanLabel designation= new SpanLabel("designation :" + d.getDesignatione());
             // SpanLabel datee= new SpanLabel("date :" + d.getDatee());
             SpanLabel nbrparticipants= new SpanLabel("nbrparticipants :" + d.getNbrparticipants());
             SpanLabel DatePubtxt = new SpanLabel("datee" + d.getDatee());
             //Label DatePubtxt = new Label("" + d.getDatee(), "NewsTopLine2");
            
        
             this.setElement(d.getIde());
             
             Button ajoutcomtr = new Button("commenaire");
             Button participer = new Button("participer");
             
             participer.addActionListener( new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent evt){
                
                ServiceEvenement.getInstance().sendmail(3);
                
            
               
                
            }
        });
             
             
             ajoutcomtr.addActionListener( new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent evt){
                 ListEvent  le = new ListEvent(); 
                    new EvenementDetail(d).show();
            }
        });
             try {
                 
                   url = "/"+d.getImagee();
                   
                   enc = EncodedImage.create("/"+d.getImagee());
                 
        }   
             catch (Exception ex) {
            
           
                  
        }
        

        
        //imgs = URLImage.createToStorage(enc,url, url,URLImage.RESIZE_SCALE);
        imgs= URLImage.createToStorage(enc, url, url,URLImage.RESIZE_SCALE);
        
        imgv = new ImageViewer(imgs);
        
        //this.add(imgv);
        
            // Label image= new Label(FontImage.createMaterial(FontImage.MATERIAL_RADIO, d.getImagee() , 10.0f));
             
            this.addAll(description,designation,nbrparticipants,DatePubtxt,imgv,ajoutcomtr,participer);
            
           

        }
        
        /*
        Home h = new Home();
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
              , e-> previous.showBack()); // Revenir vers l'interface précédente
     //return f; */
}}
