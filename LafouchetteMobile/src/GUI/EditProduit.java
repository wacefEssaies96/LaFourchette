/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package GUI;

import Entities.Produit;
import com.codename1.capture.Capture;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;
import GUI.BaseForm;
import com.esprit.app.gui.SideMenu;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import services.ProduitService;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class EditProduit extends BaseForm {

    SideMenu h;
    public EditProduit(Resources res,Form previous,Produit fi) {
        
        super("Modifier Produit", BoxLayout.y());

        TextComponent nomProd= new TextComponent().label("Nom produit");
        nomProd.text(String.valueOf(fi.getNomProd()));
        add(nomProd);         
        TextComponent quantite= new TextComponent().label("Quantite");
        quantite.text(String.valueOf(fi.getQuantite()));
        add(quantite);
        TextComponent prix= new TextComponent().label("Prix");
        prix.text(String.valueOf(fi.getPrix()));
        add(prix);
                        
        Container c=new Container(new FlowLayout(Container.CENTER));

        Button Modifier = new Button("Modifier");
        Modifier.addActionListener((evt) -> {
                if (nomProd.getText().equals("") || quantite.getText().equals("") || prix.getText().equals("") )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {

            
           ProduitService sp = new ProduitService();
            fi.setNomProd(nomProd.getText());
            fi.setQuantite(Integer.parseInt(quantite.getText()));
            fi.setPrix(Float.parseFloat(prix.getText()));
            sp.editProduit(fi);
            Dialog.show("Success","Produit Modifié avec succée",new Command("OK"));
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
