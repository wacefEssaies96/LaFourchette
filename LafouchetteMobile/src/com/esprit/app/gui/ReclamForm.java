/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.app.gui;


import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
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
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.app.entity.Reclam;
import com.esprit.app.entity.TypeReclamation;
import com.esprit.app.services.ServiceReclam;
import com.esprit.app.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import com.esprit.app.gui.ReclamForm;



/**
 *
 * @author barki
 */
public class ReclamForm extends BaseForm {
    SideMenu h;
   private Resources theme;
    public ReclamForm(Form previous) throws IOException{
        super("Reclam",BoxLayout.y());
         ServiceReclam es = new ServiceReclam();
         
        ArrayList<TypeReclamation> list = es.getAllTypeRec();
    BaseForm current;
   
    String[] typerecs = { "Reclamation livraison", "Reclamation event", "Reclamation plat","Reclamation service","Reclamation technique","Reclamation reservation"};
     /*for (TypeReclamation tr : list) {
    
    }*/
    // String[] typerecs= {};
     
    /* for(int i = 0 ; i <list.size() ; i++){
        // typerecs[i]=list.get(i).getTypeRec();
        System.out.println(list.get(i).getTypeRec().toString());
        //typerecs.add(list.get(i).getTypeRec());
       
     }
     for (TypeReclamation tr : list){
       // typerecs.add(tr.getTypeRec());
     }*/
    
        
        Toolbar tb = new Toolbar(true);
        current = this;
        setTitle("Ajout Reclamation");
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        
        getContentPane().setScrollVisible(false);
        
        tb.addSearchCommand(e -> {
           Tabs swipe = new Tabs();
           Label s1 =new Label();
           Label s2 =new Label();
           
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Mes Reclamations", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Reclamer", barGroup);
        partage.setUIID("SelectBar");
      //  Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((f) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        //  ListReclamationForm a = new ListReclamationForm(res);
          //  a.show();
            refreshTheme();
        });

        
        });
        
        AutoCompleteTextField typerec = new AutoCompleteTextField(typerecs);
        typerec.setUIID("TextFieldBlack");
        addStringValue("Type Rec :",typerec);
        
        TextField description = new TextField("","Tapez votre reclamation ..");
        description.setUIID("TextFieldBlack");
        addStringValue("Description :",description);
        
        TextField etatrec = new TextField("","En attente");
        etatrec.setUIID("TextFieldBlack");
        addStringValue("Etat Rec :",etatrec);
      //  int iduser;
       // iduser = Integer.parseInt(idu.getText().toString());
        
       
        InfiniteProgress p = new InfiniteProgress();; //loading after insert data
                    final Dialog iD = p.showInfiniteBlocking();
                    Reclam reclam = new Reclam(String.valueOf(description.getText()).toString(),String.valueOf(etatrec.getText()).toString(),String.valueOf(typerec.getText()).toString());
        Button btnAjouter= new Button("Ajouter");
        addStringValue("",btnAjouter);
        
        
        //onclick button event
        btnAjouter.addActionListener((e) -> {
            
            try {
                if (description.getText()==""){
                    Dialog.show("Description est vide !!","","Annuler","OK");
                }
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //loading after insert data
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    Reclam reclamA = new Reclam(String.valueOf(description.getText()).toString(),String.valueOf(etatrec.getText()).toString(),String.valueOf(typerec.getText()).toString());
                    
                    Dialog.show("Réclamation envoyée","","fermer","OK");
                    System.out.println("Data reclamation =="+reclamA);
                    ServiceReclam.getInstance().addReclam(reclamA);
                    iDialog.dispose();
                    refreshTheme();
                }
            } catch (Exception ex){
                ex.printStackTrace();
            }
        });
        setLayout(BoxLayout.y());

        add(new Label("Choisissez une option"));
        Button btnAddTask = new Button("Mes réclamations");
        Button btnAddTask1 = new Button("Statistiques réclamations");
    
            
       //  ReclamForm rr = new ReclamForm();
    
        btnAddTask.addActionListener(e -> {
            
                new ListReclam(h).show();
            
        });
        btnAddTask1.addActionListener(e -> new StatReclam().createPieChartForm(current).show());
       
        addAll(btnAddTask,btnAddTask1);
        
        
        this.getToolbar().addMaterialCommandToLeftBar(
                "", FontImage.MATERIAL_ARROW_BACK
              , e-> previous.showBack()
        );
    }

    private void addStringValue(String s, Component v) {
        
        
       add(BorderLayout.west(new Label(s,"PaddedLabel")).add(BorderLayout.CENTER,v));
       add(createLineSeparator(0xeeeeee));
       
    }

   
}
