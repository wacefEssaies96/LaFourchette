/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import Entities.Decoration;
import services.DecorationService;
import java.util.ArrayList;
import com.codename1.components.ImageViewer;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.esprit.app.gui.SideMenu;

/**
 *
 * @author Iheb
 */
public class ListeDecoration extends Form{
    SideMenu h;
    EncodedImage enc=null;
    Image imgs;
    ImageViewer imgv;
    String url = "/imagenotfound.png";
    
    public ListeDecoration(Form previous) {
        
        this.setTitle("Liste des Decoration");
        this.setLayout(BoxLayout.y());
        
        DecorationService ds = new DecorationService();
        ArrayList<Decoration> list = ds.getAllDecoration();
        
        for (Decoration d : list) {
            
            SpanLabel nom= new SpanLabel("Nom:" + d.getNom());
            SpanLabel prix= new SpanLabel("Prix :" + d.getPrix());
            try {
                url = "/"+d.getImageD();
                enc = EncodedImage.create("/"+d.getImageD());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            imgs = URLImage.createToStorage(enc,url, url,URLImage.RESIZE_SCALE);
            imgv = new ImageViewer(imgs);
            this.addAll(nom,prix,imgv);

        }
        this.getToolbar().addMaterialCommandToLeftBar(
                "", FontImage.MATERIAL_ARROW_BACK
              , e-> previous.showBack()
        );
        
    }
    
    
    
}
