/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.app.gui.commande;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.util.Resources;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.esprit.app.entity.commande;

import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.app.services.ServiceCommande;
import java.util.ArrayList;


public class ShowCourseForm extends Form {
    @SuppressWarnings("unused")
    private Resources theme;
    private ServiceCommande cs = new ServiceCommande();
    
    int idu=2;
    public ArrayList<commande> commandes;
    public ShowCourseForm(Form previous,Resources theme,commande c){
        super("Course Details",BoxLayout.y());
        commandes = new ServiceCommande().affichecommande(idu);
        Button update = new Button("Update");

        SpanLabel nomplat = new SpanLabel("Nomplat "+c.getReferenceplat()+"");
        SpanLabel liv = new SpanLabel("Livraison: "+c.getLivraison());
        SpanLabel Prix= new SpanLabel("prix :" + c.getPrixC());
       
        SpanLabel quan = new SpanLabel("Quantity : "+c.getQuantity());

        this.addAll(nomplat, liv, Prix, quan);



        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
        
        this.getToolbar().addCommandToRightBar("Delete", null , (evt) -> {
           cs.deleteCourse(c);
            previous.showBack();
        });
    }
}
