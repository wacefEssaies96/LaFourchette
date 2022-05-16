/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.app.gui;

import com.codename1.components.ToastBar;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

public class SignInForm extends Form{
    @SuppressWarnings("deprecation")
    public SignInForm(Resources theme){
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        Toolbar.setGlobalToolbar(true);
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                //new Label("Welcome", "WelcomeWhite")
                new Label("Sign in", "WelcomeBlack")
        );

        getTitleArea().setUIID("Container");

      //  Image profilePic = theme.getImage("user.png");
        //Image mask = theme.getImage("round-mask.png");
       // profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
       // Label profilePicLabel = new Label(profilePic, "ProfilePic");
        //profilePicLabel.setMask(mask.createMask());

        TextField login = new TextField("", "Login", 20, TextField.USERNAME) ;
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD) ;

        login.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_DIPS);
        login.getUnselectedStyle().setPadding(1, 1, 1, 1);
        login.getUnselectedStyle().setBorder(
               Border.createLineBorder(3)
                        );

        password.getSelectedStyle().setPaddingUnit(Style.UNIT_TYPE_DIPS);
        password.getSelectedStyle().setPadding(1, 1, 1, 1);
        password.getSelectedStyle().setBorder(
               Border.createLineBorder(3)
                        );


        password.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_DIPS);
        password.getUnselectedStyle().setPadding(1, 1, 1, 1);
        password.getUnselectedStyle().setBorder(
                       Border.createLineBorder(3)
                                );

        password.getSelectedStyle().setPaddingUnit(Style.UNIT_TYPE_DIPS);
        password.getSelectedStyle().setPadding(1, 1, 1, 1);
        password.getSelectedStyle().setBorder(
                       Border.createLineBorder(3)
                                );


        login.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);

        Button loginButton = new Button("LOGIN");
        loginButton.setUIID("LoginButton");
        loginButton.addActionListener(e -> {

            if(login.getText().equals("admin") && password.getText().equals("admin")){
                new HomeForm(theme).show();
            }else if (login.getText().equals("") || password.getText().equals(""))
            {
                ToastBar.showErrorMessage("Please fill all fields!", 5000);
            }else
            {
                ToastBar.showErrorMessage("Username or password incorrect!", 5000);
            }

        });

        /*Button createNewAccount = new Button("CREATE NEW ACCOUNT");
        createNewAccount.setUIID("CreateNewAccountButton");*/

        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }


        Container by = BoxLayout.encloseY(
                welcome,
                //profilePicLabel,
                spaceLabel,
                BorderLayout.center(login).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(password).
                        add(BorderLayout.WEST, passwordIcon),
                loginButton/*,
                createNewAccount*/
        );
        add(BorderLayout.CENTER, by);

        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }
}
