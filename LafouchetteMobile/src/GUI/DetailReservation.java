package GUI;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import Entities.Decoration;
import Entities.Reservation;
import Entities.Table_Resto;
import com.esprit.app.gui.SideMenu;
import java.util.ArrayList;
import services.ReservationService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Iheb
 */
public class DetailReservation extends Form{
    SideMenu h;
    EncodedImage enc=null;
    Image imgs;
    ImageViewer imgv;
    String url = "/imagenotfound.png";
    //int idr=2;
    public DetailReservation(Reservation r,Form previous ) {
        
        this.setTitle("Detail des Reservations");
        this.setLayout(BoxLayout.y());
        ReservationService rs = new ReservationService();
        ArrayList<Object> list = rs.DetailleReservation(r.getIdR());
        
        
        ArrayList<Reservation> listR = new ArrayList();
        ArrayList<Table_Resto> listTR = new ArrayList();
        ArrayList<Decoration> listD = new ArrayList();
        Table_Resto [] listTR1={};
        //ArrayList<Reservation> listReservation;
        
       // System.out.println(" list gui detaill = "+list.toString());
        for (Object ob : list) {
            if(ob instanceof Reservation){
                listR.add((Reservation)ob);
            }
            if(ob instanceof Table_Resto){
                listTR1[0]=(Table_Resto)ob;
                listTR.add((Table_Resto)ob);
            }
            if(ob instanceof Decoration){
                listD.add((Decoration)ob);
            }
        }
        /*
            System.out.println("listR in detail"+listR.toString());
            System.out.println("listTR in detail"+listTR.toString());
            for(Table_Resto t : listTR1) {
            System.out.println("listTR1 in detail"+t.getImageTable());
            }
            System.out.println("listD in detail"+listD.toString());*/
        SpanLabel idr= new SpanLabel("IdR:" +Float.parseFloat(String.valueOf(r.getIdR())));
        SpanLabel idu= new SpanLabel("IdU:" +String.valueOf(r.getIdU()));
        SpanLabel dc= new SpanLabel("Date Creation:" + r.getDateCreation());
        SpanLabel dm= new SpanLabel("Date Modification:" + r.getDateModification());
        this.addAll(idr,idu,dc,dm);

        for(Table_Resto t : listTR) {
            
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
            this.addAll(nbrplace,prix,imgv);

        }
        for (Decoration d : listD) {
            
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
