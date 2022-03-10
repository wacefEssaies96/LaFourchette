/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import entities.commande;
        import entities.commandeplat;
import entities.Plat;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
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
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import service.gestionCommande;
import service.gestionPlat;
import utiles.MyConnection;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class PanierController implements Initializable {

    /**
     * Initializes the controller class.
     */
    gestionCommande fs = new gestionCommande();
    
   
    @FXML
    private GridPane gridpanecommande;
    @FXML
    private Button btpanier;
    @FXML
    private Label labeltotal;
    
    //gestionPlat gp = new gestionPlat();
               
     List<Plat> Listepanier = new ArrayList();
             //=gp.getplatlist();

    public void setListepanier(List<Plat> Listepanier) {
        this.Listepanier = Listepanier;
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try{
               
               
               int col=0;
               int row=0;
               gestionPlat gp = new gestionPlat();
               
               List<Plat>Listepanier = this.Listepanier;
               try{
                   for (Plat Pl : Listepanier){
                       FXMLLoader fxmlloader = new FXMLLoader();
                       fxmlloader.setLocation(getClass().getResource("itemmenu.fxml"));
                       AnchorPane anchorPane = fxmlloader.load();
                       
                       ItemmenuController itemControler = fxmlloader.getController();
                       itemControler.SetData(Pl);
                       if(col==3){
                           col=0;
                           row++;
                       }
                       gridpanecommande.add(anchorPane,col++,row);
                       GridPane.setMargin(anchorPane, new Insets(10));
                   }
               }catch(IOException e){
                   e.printStackTrace();
                   
               }
               
               
           }catch(Exception ex){
               Logger.getLogger(menucontroller.class.getName()).log(Level.SEVERE, null, ex);
               
           }
      
    }
    
    
   
      /*
     
      public void add(commandeplat cp) throws SQLException {

        Statement st = cnx.createStatement();
        //    String req = "insert into personne values ("+p.getId()+" , " +p.getNom()+ ", " +p.getPrenom() +")";
        String req = " insert into commandeplat (idC , reference) values (" + cp.getIdC() +",'" + cp.getReference() +"')";
        st.executeUpdate(req);

    }
     
      public ObservableList<commandeplat> showpanier() throws SQLException{
          List commandep = fs.afficherListcommande();
        gestionCommande gp=new gestionCommande();
        commandeplat cpp;
         cnx = MyConnection.getInstance().getCnx();
        ObservableList<commandeplat> ls = FXCollections.observableArrayList();
        Statement st = cnx.createStatement();
        String query2="SELECT DISTINCT * FROM commande c left join commandeplat cp ON c.idC = cp.idC left JOIN plat p ON p.reference = cp.reference order BY c.idC";
       // String req = "SELECT a.category,a.name,a.date,a.type,b.id as id,etat,b.id_formation as idf ,idd,name,price from panier b INNER JOIN course a on b.id_formation=a.id where b.id = 55 and etat='0'";
        ResultSet rs = st.executeQuery(query2);

        while (rs.next()) {
            int id = rs.getInt("id");
            String  reference= rs.getString("reference");
            int idC = rs.getInt("idC");
            String name = rs.getString("name");

             cpp = new commandeplat(id,idC,reference);
            ls.add(cpp);
            //System.out.println( p.getId() +","+ p.getIdf() + ", " + p.getPrice() + ", " + name);
        }

        return ls;

    }
      
      public void showReview(commandeplat c) {
          gestionCommande gc=new gestionCommande();
        
                  
           ObservableList<commandeplat> list;
                  
            List commande = gc.afficherListcommande();
          tfnomplat.setText(c.getReference());
          

        }

    }
*/

    @FXML
    private void panieraction(ActionEvent event) {
    }

  
    
    
    }

    
