/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entities.commande;
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
public class GmailCommande {
    public static void sendMail(String recepient){
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
        Message message = prepareMessage(session, myAccountEmail, recepient);
        try {
            Transport.send(message);
            System.out.println("Msg sent succesfully");
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(myAccountEmail));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            msg.setSubject("Commande");
            String text = "Madame, Monsieur,"
                    + "\nOn vous informe que votre Commande est traitée"
                     ;
            msg.setText(text);
            return msg;
        } catch (MessagingException ex) {
            Logger.getLogger(Gmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
