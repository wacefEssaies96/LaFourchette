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
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Commantaire;
import com.mycompany.entities.Evenement;
import com.mycompany.services.ServiceCommantaire;
import com.mycompany.services.ServiceEvenement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class ListComment extends Form {
    
    
     public ListComment(int ide)
    {
        
    
     this.setTitle("Liste commentaires");
        this.setLayout(BoxLayout.y());
       
        
       ServiceCommantaire ds = new ServiceCommantaire();
        ArrayList<Commantaire> list = ds.getAllCommtr(ide);
         System.out.println(list.toString());
        for (Commantaire d : list) {
            
             SpanLabel description= new SpanLabel("description:" + d.getCommantaire());
             Button ajoutcomtr = new Button("ajoutcomtr");
             Button participer = new Button("participer");
           //  EvenementDetail ev = new EvenementDetail(51); 
             ajoutcomtr.addActionListener( new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent evt){
                
                // ldate.setText("date","kdsfnhlgmfslgj");
                //ev.show();
                
            }
        });
             
            this.addAll(description,participer);
            
           

        }
        
        /*
        Home h = new Home();
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
              , e-> previous.showBack()); // Revenir vers l'interface précédente
     //return f; */
}
    
    
    
}
