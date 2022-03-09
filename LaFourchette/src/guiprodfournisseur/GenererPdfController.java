/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiprodfournisseur;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Fournisseur;
import entities.Produit;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import services.FournisseurService;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author wacef
 */
public class GenererPdfController implements Initializable {

    @FXML private Button ajouter;
    @FXML private Button annuler;
    @FXML private TableView<Fournisseur> tableFournisseur;
    @FXML private TableView<Produit> tableProduit;
    @FXML private TextField quantite;
    
    FournisseurService fs = new FournisseurService();
    ProduitService ps = new ProduitService();
    @FXML
    private TableColumn<Fournisseur, String> nomF;
    @FXML
    private TableColumn<Fournisseur, String> emailF;
    @FXML
    private TableColumn<Produit, String> nomP;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List fournisseur = fs.afficherFournisseur();
        ObservableList listF = FXCollections.observableArrayList(fournisseur);
        tableFournisseur.setItems(listF);
        nomF.setCellValueFactory(new PropertyValueFactory<>("nomF"));
        emailF.setCellValueFactory(new PropertyValueFactory<>("emailF"));
        List produit = ps.afficherListeProduits();
        ObservableList listP = FXCollections.observableArrayList(produit);
        tableProduit.setItems(listP);
        nomP.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
    }    

    @FXML
    private void insert(ActionEvent event) {
        Fournisseur f = (Fournisseur) tableFournisseur.getSelectionModel().getSelectedItem();
        Produit p = (Produit) tableProduit.getSelectionModel().getSelectedItem();
        Document doc = new Document(); 
        
        if(p == null || quantite.getText().isEmpty() || f == null){
            Alerts.ajoutAlertFailControl();
        }
        else{
            try{
                int q = Integer.parseInt(quantite.getText());
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String s = dtf.format(now).replace("/", "");
                s = s.replace(" ", "");
                s = s.replace(":", "");
                JFileChooser jf = new JFileChooser();
                jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
                jf.showSaveDialog(null);
                String path = jf.getSelectedFile().toString().replace("\\", "/")+"\\Reçu"+s+".pdf";
                PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(path));
                doc.open(); 
                doc.add(new Paragraph("La Fourchette"));
                doc.add(new Paragraph("\n"));
                doc.add(new Paragraph("                                                                          Reçu"));
                doc.add(new Paragraph("\n"));
                doc.add(new Paragraph("Nom du fournisseur : "+f.getNomF()
                    + "\n\nEmail : "+f.getEmailF()
                    + "\n\nTélèphone : "+f.getTelephoneF()
                    + "\n\nProduit : "+p.getNomProd()
                    + "\n\nQuantité reçu : "+quantite.getText()
                ));    
                doc.close();   
                writer.close();  
                Alerts.ajoutAlertSuccess();
                ajouter.getScene().getWindow().hide();
                this.redirect();
            }catch (DocumentException | FileNotFoundException | NumberFormatException e){  
                Alerts.ajoutAlertFail();
            }  
            
        }
    }

    @FXML
    private void annuler(ActionEvent event) {
        annuler.getScene().getWindow().hide();
        this.redirect();
    }
    private void redirect(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ListProduit.fxml"));
            Stage mainStage = new Stage();
            mainStage.setTitle("Liste des Produits");
            mainStage.setResizable(false);
            Scene scene= new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException ex) {
             Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
