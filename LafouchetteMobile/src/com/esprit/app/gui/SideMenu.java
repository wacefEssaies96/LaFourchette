package com.esprit.app.gui;

import GUI.ListeTableDisponible;
import GUI.ViewFournisseur;
import GUI.ViewProduit;
import java.io.IOException;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.esprit.app.gui.commande.CourseForm;

import com.esprit.app.gui.plat.platgui;
import com.mycompany.gui.ListEvent;
import com.mycompany.gui.signinForm;


public abstract class SideMenu extends Form {
    private Resources theme;  
    public SideMenu(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenu(String title) {
        super(title);
    }

    public SideMenu() {
    }

    public SideMenu(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public void setupSideMenu(Resources res) {
       // Image profilePic = res.getImage("user.png");
       // Image mask = res.getImage("round-mask.png");
       // mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        //profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        //Label profilePicLabel = new Label("Hello User", profilePic, "SideMenuTitle");
       // profilePicLabel.setMask(mask.createMask());

        //Container sidemenuTop = BorderLayout.center(profilePicLabel);
        /*Label u = new Label("Hello User");
        sidemenuTop.add(BorderLayout.CENTER, u);*/
        
        //sidemenuTop.setUIID("SidemenuTop");


        
        //getToolbar().addComponentToSideMenu(sidemenuTop);

        /*getToolbar().addCommandToSideMenu("  Add Product", null,	e-> new AddProductForm(this, res).show());
        getToolbar().addCommandToSideMenu("  List Produts", null,  e-> {
			try {
				new ProductsListForm(this,res).show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});*/
       
        //getToolbar().addCommandToSideMenu("  Speedometer", null,  e-> new SpeedometerForm(this, res).show());
        /*getToolbar().addCommandToSideMenu("  Courses", null, e -> {
            try {
                new CourseForm(this, res).show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });*/
        getToolbar().addCommandToSideMenu("  Menu ", null, e -> {
            try {
                new platgui(this, res).show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        getToolbar().addCommandToSideMenu("  Commandes", null, e -> {
            try {
                new CourseForm(this, res).show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
         getToolbar().addCommandToSideMenu("  Réclamations", null, e -> {
            try {
                new ReclamForm(this).show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
         getToolbar().addCommandToSideMenu("  Reservation", null, e -> {
            try {
                new ListeTableDisponible(this).show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
         getToolbar().addCommandToSideMenu("  Produit", null, e -> {
            try {
                new ViewProduit(theme,this).show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
         getToolbar().addCommandToSideMenu("  Fournisseur", null, e -> {
            try {
                new ViewFournisseur(theme).show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
         
         getToolbar().addCommandToSideMenu("  Evenement ", null, e -> {
            try {
                ListEvent le = new ListEvent();
                le.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
       /* getToolbar().addCommandToSideMenu("  Users", null, e -> {
            try {
                new UserForm(this, res).show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });*/
        getToolbar().addCommandToSideMenu("  Logout", null,  e -> new signinForm().show());
        /*getToolbar().addCommandToSideMenu("  Wishlist", null,  e -> new WishlistForm(res).show());
        getToolbar().addCommandToSideMenu("  Cart", null,  e -> new PanierForm(res).show());
        getToolbar().addCommandToSideMenu("  My Orders", null,  e -> new CommandeForm(res).show());
        //getToolbar().addCommandToSideMenu("  Logout", null,  e -> new LoginForm(res).show());*/
    }

    protected abstract void showOtherForm(Resources res);
}
