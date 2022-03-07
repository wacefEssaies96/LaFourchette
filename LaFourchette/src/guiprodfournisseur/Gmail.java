/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiprodfournisseur;

import entities.Produit;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author wacef
 */
public class Gmail {
    public static void sendMail(String recepient, Produit p){
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        
        String myAccountEmail = "lafourchette.esprit@gmail.com";
        String password = "lafourchette123";
        
        Session session = Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail,password);
            }
        });
        Message message = prepareMessage(session, myAccountEmail, recepient, p);
        try {
            Transport.send(message);
            System.out.println("Msg sent succesfully");
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, Produit p) {
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(myAccountEmail));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            msg.setSubject("Commande de produit en ligne");
            String text = "Madame, Monsieur,"
                    + "\nPar la présente, je souhaite effectuer une commande auprès de votre entreprise."
                    + "\nEn effet, nous voudrons recevoir 100Kg du produit "+p.getNomProd()+"."
                    + "\nOn vous remercie de bien vouloir nous livrer notre commande dans les plus brefs délais à notre adresse Tunis.LaFourchette."
                    + "\nAprès réception de la facture, nous vous ferons parvenir un chèque afin de règler la commande."
                    + "\nJe vous serais reconnaissant de bien vouloir confirmer notre commande, et vous prie d'accepter, Madame, Mosieur, mes respectueuse salutations.";
            msg.setText(text);
            return msg;
        } catch (MessagingException ex) {
            Logger.getLogger(Gmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
