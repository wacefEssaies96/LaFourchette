/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Commentaire;
import entities.Evenement;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.text.Text;
import services.CommentaireCrud;

/**
 * FXML Controller class
 *
 * @author user
 */
public class CommentaireViewController implements Initializable {

    @FXML
    private Text commentaireText;
     int idEvenet;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        commentaireText.setDisable(true);
        
    }    
    void SetData(int idEvent){
      this.idEvenet=idEvent;
        CommentaireCrud cc = new CommentaireCrud();
        List<Commentaire>ListeDesCommentaires= cc.afficherCommentaire();
        ListeDesCommentaires = ListeDesCommentaires.stream().filter(c->c.getIdEvent()==idEvent).collect(Collectors.toList());
       for(Commentaire c :ListeDesCommentaires ){
           
           commentaireText.setText(commentaireText.getText()+"\n\r"+c.getCommentaire());
       }
    
    }
}
