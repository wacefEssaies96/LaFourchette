/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Produit;
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
import services.ProduitService;

/**
 *
 * @author wacef
 */
public class AddProduit extends BaseForm {
    SideMenu h;
    
    public AddProduit(Resources res,Form previous) {
        super("Ajouter", BoxLayout.y());

                
        TextComponent nomProd= new TextComponent().label("Nom produit");
       
        add(nomProd);         
        TextComponent quantite= new TextComponent().label("Quantite");
        
        add(quantite);
        TextComponent prix= new TextComponent().label("Prix");
        
        add(prix);
                        
        Container c=new Container(new FlowLayout(Container.CENTER));

        Button Modifier = new Button("Ajouter");
        Modifier.addActionListener((evt) -> {
                if (nomProd.getText().equals("") || quantite.getText().equals("") || prix.getText().equals("") )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {

            Produit fi = new Produit();
           ProduitService sp = new ProduitService();
            fi.setNomProd(nomProd.getText());
            fi.setQuantite(Integer.parseInt(quantite.getText()));
            fi.setPrix(Float.parseFloat(prix.getText()));
            sp.addProduit(fi);
            Dialog.show("Success","Produit ajouté avec succée",new Command("OK"));
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
