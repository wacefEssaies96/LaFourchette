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
import Entities.Reservation;
import services.ReservationService;
import java.util.ArrayList;

/**
 *
 * @author Iheb
 */
public class ListeReservation extends Form{
    
    public ListeReservation(Form previous) {
        
        this.setTitle("Liste des Reservations");
        this.setLayout(BoxLayout.y());
        ReservationService ds = new ReservationService();
        ArrayList<Reservation> list = ds.getAllReservation();
        //System.out.println(list.toString());
        for (Reservation r : list) {
            
            SpanLabel idr= new SpanLabel("IdR:" +Float.parseFloat(String.valueOf(r.getIdR())));
            SpanLabel idu= new SpanLabel("IdU:" +String.valueOf(r.getIdU()));
            SpanLabel dc= new SpanLabel("Date Creation:" + r.getDateCreation());
            SpanLabel dm= new SpanLabel("Date Modification:" + r.getDateModification());
            this.addAll(idr,idu,dc,dm);

        }
        this.getToolbar().addMaterialCommandToLeftBar(
            "", FontImage.MATERIAL_ARROW_BACK
          , e-> previous.showBack()
        );
    }
    
}
