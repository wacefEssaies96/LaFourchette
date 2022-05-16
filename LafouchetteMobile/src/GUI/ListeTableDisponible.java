/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import Entities.Datetimetr;
import Entities.Decoration;
import Entities.Table_Resto;
import com.esprit.app.gui.SideMenu;
import java.util.ArrayList;
import services.DatetimetrService;
import services.DecorationService;
import services.Table_RestoService;

/**
 *
 * @author Iheb
 */
public class ListeTableDisponible extends Form {
    SideMenu h;
    EncodedImage enc=null;
    Image imgs;
    ImageViewer imgv;
    String url = "/imagenotfound.png";
    public ListeTableDisponible(Form previous) {
        
        this.setTitle("Liste Table");
        this.setLayout(BoxLayout.y());
        
        Table_RestoService ts = new Table_RestoService();
        ArrayList<Table_Resto> list = ts.getAllTable_Resto();
        //System.out.println(list.toString());
        for(Table_Resto t : list) {
            
            SpanLabel nbrplace= new SpanLabel("Nombre place:" + t.getNbrPlace());
            SpanLabel prix= new SpanLabel("Prix :" + t.getPrix());
            try {
                url = "/"+t.getImageTable();
                enc = EncodedImage.create("/"+t.getImageTable());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            imgs = URLImage.createToStorage(enc,url, url,URLImage.RESIZE_SCALE);
            imgv = new ImageViewer(imgs);
            
            Button Reserver = new Button("Reserver");
            this.addAll(nbrplace,prix,imgv,Reserver);
            
            
            Reserver.addActionListener( new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt){
                    
                    ListeTableDisponible h1 = new ListeTableDisponible(h);
                    DecorationService ds = new DecorationService();
                    ArrayList<Decoration> listDecoration = ds.getAllDecoration();
                    DatetimetrService dts = new DatetimetrService();
                    ArrayList<Datetimetr> listDatetimetr = dts.getAllDatetimetr();
                    new ConfirmerReservation(t,h1,listDecoration,listDatetimetr).show();
                }
            });
        }
        this.getToolbar().addMaterialCommandToLeftBar(
                "", FontImage.MATERIAL_ARROW_BACK
              , e-> previous.showBack()
        );
        
    }

    
}
