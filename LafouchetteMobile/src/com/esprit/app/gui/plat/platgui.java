/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.app.gui.plat;

import com.codename1.components.MultiButton;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.esprit.app.entity.Plat;
import com.esprit.app.services.Serviceplat;
import com.esprit.app.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;

public class platgui extends Form{
    public ArrayList<Plat> Plats;
    @SuppressWarnings("unused")
    private Resources theme;
    
    public platgui(Form previous, Resources res)throws IOException{
         super("Plats List", GridLayout.autoFit());
        this.theme = theme;
        
        Plats = new Serviceplat().affichagePlat();
		//this.add(new SpanLabel(new ProduitsService().getAllProducts().toString()));
		Container list = new Container(BoxLayout.y());
                list.setScrollableY(true);
                for (Plat Plat : Plats) {
                    MultiButton mb = new MultiButton(Plat.getReference());
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
                    Image i = URLImage.createToStorage(placeholder,Plat.getImageP(),Statics.BASE_URL+"/uploads/"+Plat.getImageP());
                    mb.setIcon(i.fill(200, 200));
                    mb.setTextLine2(Plat.getPrix() > 0 ? String.valueOf(Plat.getPrix()) + " TND" : "FREE");
                    mb.addActionListener((evt) -> {
                        new ShowPlatForm(this, theme, Plat.getReference()).show();
                    });
                    list.add(mb);
                    /*img.addPointerReleasedListener((evt)->{
                           new ProductDetailsForm(this, theme,prod.getId()).show();

                   });		*/
		}
		this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
                    previous.showBack();
                });
                this.add(list);
        
    }
}
