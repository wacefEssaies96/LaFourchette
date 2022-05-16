/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import GUI.ListeDecoration;
import GUI.ListeReservation;
import GUI.MesReservation;

/**
 *
 * @author Iheb
 */
public class Home extends Form{
    
    public Home() {
        
        ListeDecoration ld = new ListeDecoration(this);
        ListeReservation lr = new ListeReservation(this);
        MesReservation mr = new MesReservation(this);
        ListeTableDisponible tr = new ListeTableDisponible(this);
        
        
        this.setTitle("Home");
        this.setLayout(BoxLayout.y());
        Button listeD = new Button("liste Decorations");
        Button listeR = new Button("liste Reservations");
        Button mesR = new Button("Mes Reservations");
        Button listeTR = new Button("liste Table Resto");
        this.addAll(listeD,listeR,mesR,listeTR);
        this.show();
        
        listeD.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                ld.show();
            }
        });
        listeR.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                lr.show();
            }
        });
        mesR.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                mr.show();
            }
        });
        listeTR.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                tr.show();
            }
        });

    }
    
    
    
}
