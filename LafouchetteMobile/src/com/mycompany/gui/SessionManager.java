/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author pc
 */
public class SessionManager {
     public static Preferences pref ;
       private static int idU ; 
    private static String nom_prenom ; 
    private static String email; 
    private static String passowrd ;
    private static String picture;
    private static String addresse;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("idU",idU);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setId(int idU) {
        pref.set("idU",idU);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    public static String getnom_prenom() {
        return pref.get("username",nom_prenom);
    }

    public static void setUserName(String nom_prenom) {
         pref.set("nom_prenom",nom_prenom);
    }

    public static String getEmail() {
        return pref.get("email",email);
    }

    public static void setEmail(String email) {
         pref.set("email",email);
    }

    public static String getPassowrd() {
        return pref.get("passowrd",passowrd);
    }

    public static void setPassowrd(String passowrd) {
         pref.set("passowrd",passowrd);
    }

    public static String getPicture() {
        return pref.get("picture",picture);
    }

    public static void setPicture(String picture) {
         pref.set("picture",picture);
          

    
    
    
    }
    
    
    
    
}
