/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.app.gui.commande;

import com.codename1.components.MultiButton;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.esprit.app.entity.commande;
import com.esprit.app.services.ServiceCommande;

import java.io.IOException;
import java.util.ArrayList;

public class CourseForm extends Form{
    public ArrayList<commande> commandes;
    @SuppressWarnings("unused")
    private Resources theme;
   int  idu=1;
    public CourseForm(Form previous, Resources res)throws IOException{
        super("Courses List", GridLayout.autoFit());
        this.theme = theme;
        
        commandes = new ServiceCommande().affichecommande(idu);
		//this.add(new SpanLabel(new ProduitsService().getAllProducts().toString()));
		Container list = new Container(BoxLayout.y());
                list.setScrollableY(true);
                for (commande commande : commandes) {
                    MultiButton mb = new MultiButton(commande.getReferenceplat());
                    mb.setTextLine3( "Quantity  " + String.valueOf(commande.getQuantity()) );    
                    mb.setTextLine2(commande.getPrixC() > 0 ? String.valueOf(commande.getPrixC()) + " TND" : "FREE");
                    mb.addActionListener((evt) -> {
                        new ShowCourseForm(this, theme, commande).show();
                    });
                    list.add(mb);
                    
		}
		this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
                    previous.showBack();
                });
                this.add(list);
        
    }    
}
