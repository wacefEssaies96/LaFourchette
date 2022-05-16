/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Fournisseur;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.app.gui.SideMenu;
import java.util.ArrayList;
import services.FournisseurService;

/**
 *
 * @author wacef
 */
public class ViewFournisseur extends BaseForm {
    SideMenu h;
    Form current;
    public ViewFournisseur(Resources res) {
        super("Fournisseur", BoxLayout.y());
        
        Button Ajouter = new Button("Ajouter");
        Ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                    new AddFournisseur(res, current).show();
            }
        });
        add(Ajouter);
        ButtonGroup barGroup = new ButtonGroup();
        Container co=new Container(BoxLayout.xCenter());;
        ArrayList <Fournisseur> typeComptabilites = new ArrayList();
        FournisseurService sa =new FournisseurService();
        typeComptabilites=sa.getList();

        for (Fournisseur fi : typeComptabilites) {
           Container ct = new Container(BoxLayout.y());
           Label l = new Label("Nom fournisseur : "+fi.getNomF());
           Label l2 = new Label("Télephone : "+String.valueOf(fi.getTelephoneF()),"SmallLabel");
           Label l3 = new Label("Email : "+String.valueOf(fi.getEmailF()),"SmallLabel");
           Label l4 = new Label("Level : "+String.valueOf(fi.getLvl()),"SmallLabel");
           l2.getAllStyles().setFgColor(0xf15f5f);
           ct.add(l);
           ct.add(l2);
           ct.add(l3);
           ct.add(l4);

           Button Modif = new Button("Modifier");
           Button Supprimer = new Button("Supprimer");
           Modif.addActionListener((ActionListener) (ActionEvent evt) -> {
               new EditFournisseur(res,current,fi).show();
           });
            ct.add(Modif);
            Supprimer.addActionListener((ActionListener) (ActionEvent evt) -> {
                if (Dialog.show("Confirmation", "Voulez vous supprimer ce fournisseur ?", "Oui", "Annuler")) {
                    if( FournisseurService.getInstance().deleteFournisseur(fi.getIdF())){
                        Dialog.show("Success","Fournisseur supprimé",new Command("OK"));
                        new ViewFournisseur(res).show();
                    }
                }
           });
            ct.add(Supprimer);
            Label separator = new Label("","Separator");
            ct.add(separator);
            add(ct);
        }
    }
}
