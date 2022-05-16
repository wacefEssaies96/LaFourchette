/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.AutoCompleteTextField;
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
import services.ReservationService;
import services.Table_RestoService;

/**
 *
 * @author Iheb
 */
public class ConfirmerReservation extends Form {
    SideMenu h;
    EncodedImage enc=null;
    Image imgs;
    ImageViewer imgv,imgvt;
    String url = "/imagenotfound.png";
    public ConfirmerReservation(Table_Resto t,Form previous,ArrayList<Decoration> listDecoration,ArrayList<Datetimetr> listDatetimetr) {
        
        this.setTitle("Confirmer Reservation Table");
        this.setLayout(BoxLayout.y());
        
        //Table_RestoService ts = new Table_RestoService();
        //ArrayList<Table_Resto> list = ts.getAllTable_Resto();
        //System.out.println(list.toString());
        //for(Table_Resto t : list) {
            
            SpanLabel nbrplace= new SpanLabel("Nombre place:" + t.getNbrPlace());
            SpanLabel prix= new SpanLabel("Prix :" + t.getPrix());
            try {
                url = "/"+t.getImageTable();
                enc = EncodedImage.create("/"+t.getImageTable());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            imgs = URLImage.createToStorage(enc,url, url,URLImage.RESIZE_SCALE);
            imgvt = new ImageViewer(imgs);
            
            SpanLabel labeltable= new SpanLabel("Table Resto");
            this.add(labeltable);
            this.add(nbrplace);
            this.add(prix);
            this.add(imgvt);
            SpanLabel labelDatetimetr= new SpanLabel("Datetimetr");
            
            this.add(labelDatetimetr);
            
            String[] users = { "1", "4", "6", "9","13"};
            ArrayList<String> users1 = new ArrayList();
            
            for (Datetimetr d : listDatetimetr) {
                users1.add(d.getDate());
                /*
                SpanLabel id= new SpanLabel("id:" + d.getIddt());
                SpanLabel etat= new SpanLabel("Etat:" + d.getEtat());
                SpanLabel date= new SpanLabel("date :" + d.getDate());
                
            this.add(id);
            this.add(etat);
            this.add(date);
*/

            }
            
            AutoCompleteTextField cbd = new AutoCompleteTextField(users1.toString());
            this.add(cbd);
            
            SpanLabel labeldecoration= new SpanLabel("Decoration");
            this.add(labeldecoration);
            
            ArrayList<String> users2 = new ArrayList();
            for (Decoration d : listDecoration) {
                users2.add(d.getNom());
                /*SpanLabel nom= new SpanLabel("Nom:" + d.getNom());
                SpanLabel prixd= new SpanLabel("Prix :" + d.getPrix());
                try {
                    url = "/"+d.getImageD();
                    enc = EncodedImage.create("/"+d.getImageD());
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                imgs = URLImage.createToStorage(enc,url, url,URLImage.RESIZE_SCALE);
                imgv = new ImageViewer(imgs);
                this.addAll(nom,prixd,imgv);*/

            }
            
            AutoCompleteTextField cbdc = new AutoCompleteTextField(users2.toString());
            
            this.add(cbdc);
            Button ConfirmerReservation = new Button("Confirmer Reservation");
            this.add(ConfirmerReservation);
            
            
            ConfirmerReservation.addActionListener( new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt){
                    ReservationService rs = new ReservationService();
                    //int idt,int idu,int iddr,int idd
                    String reponcereservation = rs.ConfirmerReservation(1,1,2,2);
                    
                    new MesReservation(h).show();
                }
            });
            
            
            
            

        //}
        this.getToolbar().addMaterialCommandToLeftBar(
                "", FontImage.MATERIAL_ARROW_BACK
              , e-> previous.showBack()
        );
    
    }
}
