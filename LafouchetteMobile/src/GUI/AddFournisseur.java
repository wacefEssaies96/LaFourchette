/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Fournisseur;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.esprit.app.gui.SideMenu;
//import static java.time.temporal.TemporalAdjusters.previous;
import services.FournisseurService;

/**
 *
 * @author wacef
 */
public class AddFournisseur extends BaseForm{
    SideMenu h;
    public AddFournisseur(Resources res,Form previous) {
        super("Ajouter", BoxLayout.y());
  
        TextComponent nomF= new TextComponent().label("Nom fournisseur");

        add(nomF);         
        TextComponent emailF= new TextComponent().label("Email");

        add(emailF);
        TextComponent telF= new TextComponent().label("Télephone");

        add(telF);
        TextComponent lvl= new TextComponent().label("lvl");

        add(lvl);

        Container c=new Container(new FlowLayout(Container.CENTER));

        Button Modifier = new Button("Ajouter");
        Modifier.addActionListener((evt) -> {
            if (nomF.getText().equals("") || emailF.getText().equals("") || telF.getText().equals("") || lvl.getText().equals("") )
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            else{
                FournisseurService sp = new FournisseurService();
                Fournisseur fi = new Fournisseur();
                fi.setNomF(nomF.getText());
                fi.setEmailF(emailF.getText());
                fi.setTelephoneF(Integer.parseInt(telF.getText()));
                fi.setLvl(Integer.parseInt(lvl.getText())); 
                sp.addFournisseur(fi);
                Dialog.show("Success","Fournisseur ajouté avec succée",new Command("OK"));
                new ViewFournisseur(res).show();
            }
        });
    addStringValue("", FlowLayout.encloseRightMiddle(Modifier));
//     this.getToolbar().addMaterialCommandToLeftBar(
//            "", FontImage.MATERIAL_ARROW_BACK
//          , e-> previous.showBack()
//        );   
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
//        add(createLineSeparator(0xeeeeee));
 
    }
}
