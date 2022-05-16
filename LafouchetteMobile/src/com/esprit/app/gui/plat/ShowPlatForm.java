package com.esprit.app.gui.plat;


import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.app.entity.Plat;
import com.esprit.app.services.Serviceplat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



public class ShowPlatForm extends Form{
    @SuppressWarnings("unused")
    private Resources theme;
    private Serviceplat ts = new Serviceplat();
    private Plat t = new Plat();
    
    public ShowPlatForm(Form previous,Resources theme,String Reference){
        super("Plat Details",BoxLayout.y());
        t = new Serviceplat().getPlat(Reference);
        Button update = new Button("Update");
        
        EncodedImage enc = EncodedImage.createFromImage(Image.createImage(800,800), true);
        String url = t.getImageP();
        ImageViewer img = new ImageViewer(URLImage.createToStorage(enc, url, url));
        img.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);

        SpanLabel title = new SpanLabel("Title : "+t.getReference()+"");
        
             SpanLabel Designation= new SpanLabel("Designation :" + t.getDesignation());
             // SpanLabel datee= new SpanLabel("date :" + d.getDatee());
             SpanLabel Prix= new SpanLabel("prix :" + t.getPrix());
        SpanLabel description = new SpanLabel("Description : "+t.getDescription()+"");
       
        Style s = img.getAllStyles();
        s.setPaddingUnit(Style.UNIT_TYPE_DIPS);
        s.setPadding(6, 6, 6, 6);
        
        this.addAll(img, title, description, Designation, Prix);

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
        
       
        
    }
}
