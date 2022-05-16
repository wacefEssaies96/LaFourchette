/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.app.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.app.entity.Reclam;
import com.esprit.app.services.ServiceReclam;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author barki
 */
public class ListReclam extends Form{
    int idu=1;
    private Resources theme;
    public ListReclam(Form previous)  {
        this.theme = theme;
       setTitle("Liste des réclamations");
         
   
 
        
      
        ServiceReclam es = new ServiceReclam();
        ArrayList<Reclam> list = es.getReclamUser(idu);

        
           
            for (Reclam r : list) {

          
 
                Container c3 = new Container(BoxLayout.y());
               
                
                 SpanLabel typerec= new SpanLabel("Type réclamation :" + r.getTyperec());
                 SpanLabel description= new SpanLabel("Description :" + r.getDescription());
                 SpanLabel etatrec= new SpanLabel("Etat réclamation :" + r.getEtatrec());
                 
               
                     
                      
                      
                        c3.add(typerec);
                        c3.add(description);
                        c3.add(etatrec);
       
          
                       
                        
         
                this.add(c3);
 
             
            
          
              
            }
       
//        this.getToolbar().addMaterialCommandToLeftBar(
//                "", FontImage.MATERIAL_ARROW_BACK
//              , e-> previous.showBack()
//        );
          
        
  
    }
    
    
}
