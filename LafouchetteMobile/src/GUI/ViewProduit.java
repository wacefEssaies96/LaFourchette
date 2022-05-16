/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Produit;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.app.gui.SideMenu;
import java.util.ArrayList;
import services.ProduitService;

/**
 *
 * @author wacef
 */
public class ViewProduit extends BaseForm {
    Form current;
    SideMenu h;
        public ViewProduit(Resources res, Form previous) {
        super("Produits", BoxLayout.y());
           
        Button Ajouter = new Button("Ajouter");
        Ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                    new AddProduit(res, current).show();
            }
        });
        add(Ajouter);
        ButtonGroup barGroup = new ButtonGroup();
        Container co=new Container(BoxLayout.xCenter());;
        ArrayList <Produit> typeComptabilites = new ArrayList();
        ProduitService sa =new ProduitService();
        typeComptabilites=sa.getList();

        for (Produit fi : typeComptabilites) {
           Container ct = new Container(BoxLayout.y());
           Label l = new Label("Nom Produit : "+fi.getNomProd());
           Label l2 = new Label("Quantité : "+String.valueOf(fi.getQuantite()),"SmallLabel");
           Label l3 = new Label("Prix : "+String.valueOf(fi.getPrix()),"SmallLabel");
           l2.getAllStyles().setFgColor(0xf15f5f);
           ct.add(l);
           ct.add(l2);
           ct.add(l3);

           Button Modif = new Button("Modifier");
           Button Supprimer = new Button("Supprimer");
           Button commander = new Button("Commander");
           Modif.addActionListener((ActionListener) (ActionEvent evt) -> {
               new EditProduit(res,current,fi).show();
           });
            ct.add(Modif);
            Supprimer.addActionListener((ActionListener) (ActionEvent evt) -> {
                if (Dialog.show("Confirmation", "Voulez vous supprimer ce produit ?", "Oui", "Annuler")) {
                    if( ProduitService.getInstance().deleteProduit(fi.getNomProd())){
                        Dialog.show("Success","Produit supprimé",new Command("OK"));
                        new ViewProduit(res,h).show();
                    }
                }
           });
            
            ct.add(Supprimer);
            commander.addActionListener((ActionListener) (ActionEvent evt) -> {
                if (Dialog.show("Confirmation", "Voulez vous commander ce produit ?", "Oui", "Annuler")) {
                    if( ProduitService.getInstance().commander(fi.getNomProd())){
                        Dialog.show("Success","Commande envoyé",new Command("OK"));
                        new ViewProduit(res,h).show();
                    }
                }
           });
            ct.add(commander);
            Label separator = new Label("","Separator");
            ct.add(separator);
            add(ct);
        }
        this.getToolbar().addMaterialCommandToLeftBar(
                "", FontImage.MATERIAL_ARROW_BACK
              , e-> previous.showBack()
        );
    }   
}
