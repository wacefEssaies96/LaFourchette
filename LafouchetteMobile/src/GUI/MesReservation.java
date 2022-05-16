/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import Entities.Reservation;
import java.util.ArrayList;
import services.ReservationService;

/**
 *
 * @author Iheb
 */
public class MesReservation extends Form{
    //current user session
    int idu=1;
    public MesReservation(Form previous) {
        
        this.setTitle("Liste de Mes Reservations");
        this.setLayout(BoxLayout.y());
        ReservationService ds = new ReservationService();
        ArrayList<Reservation> list = ds.MesReservation(idu);
        //System.out.println(list.toString());
        for (Reservation r : list) {
            
            SpanLabel idr= new SpanLabel("IdR:" +Float.parseFloat(String.valueOf(r.getIdR())));
            SpanLabel idu= new SpanLabel("IdU:" +String.valueOf(r.getIdU()));
            SpanLabel dc= new SpanLabel("Date Creation:" + r.getDateCreation());
            SpanLabel dm= new SpanLabel("Date Modification:" + r.getDateModification());
           
            //DetailReservation dres = new DetailReservation(this);
            Button detaill = new Button("detaill");
            this.addAll(idr,idu,dc,dm,detaill);
            
            detaill.addActionListener( new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt){
                    
                    Home h = new Home();
                    new DetailReservation(r,h).show();
                }
            });

        }
        this.getToolbar().addMaterialCommandToLeftBar(
            "", FontImage.MATERIAL_ARROW_BACK
          , e-> previous.showBack()
        );
    }
    
}
