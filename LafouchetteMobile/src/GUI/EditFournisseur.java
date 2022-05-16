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
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.esprit.app.gui.SideMenu;
import services.FournisseurService;
import services.ProduitService;

/**
 *
 * @author wacef
 */
public class EditFournisseur extends BaseForm {
    SideMenu h;
    public EditFournisseur(Resources res,Form previous, Fournisseur fi) {
        super("Modifier", BoxLayout.y());
  
        TextComponent nomF= new TextComponent().label("Nom fournisseur");
        nomF.text(String.valueOf(fi.getNomF()));
        add(nomF);         
        TextComponent emailF= new TextComponent().label("Email");
        emailF.text(String.valueOf(fi.getEmailF()));
        add(emailF);
        TextComponent telF= new TextComponent().label("Télephone");
        telF.text(String.valueOf(fi.getTelephoneF()));
        add(telF);
        TextComponent lvl= new TextComponent().label("lvl");
        lvl.text(String.valueOf(fi.getLvl()));
        add(lvl);
        System.out.println(fi.toString());
        Container c=new Container(new FlowLayout(Container.CENTER));

        Button Modifier = new Button("Modifier");
        Modifier.addActionListener((evt) -> {
            if (nomF.getText().equals("") || emailF.getText().equals("") || telF.getText().equals("") || lvl.getText().equals("") )
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            else{

                FournisseurService sp = new FournisseurService();

                fi.setNomF(nomF.getText());
                fi.setEmailF(emailF.getText());
                fi.setTelephoneF(Integer.parseInt(telF.getText()));
                fi.setLvl(Integer.parseInt(lvl.getText()));
                sp.editFournisseur(fi);
                Dialog.show("Success","Fournisseur modifié avec succée",new Command("OK"));
                new ViewProduit(res,h).show();
            }
        });
    addStringValue("", FlowLayout.encloseRightMiddle(Modifier));
        
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
//        add(createLineSeparator(0xeeeeee));
    }
}
