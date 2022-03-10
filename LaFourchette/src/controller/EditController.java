/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Plat;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.gestionPlat;


/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class EditController implements Initializable {
    boolean flag = false;
    @FXML
    private TextField tfreferenceedit;
    @FXML
    private TextField tfdesignationedit;
    @FXML
    private TextField tfprixedit;
    @FXML
    private TextField tfdescriptionedit;
    @FXML
    private TextField tfproduitsedit;
    @FXML
    private Button editbutton;
    @FXML
    private ImageView imageviewedit;
    @FXML
    private Button insertimagebutton;
    @FXML
    private Label imagepath;
    Plat P=null;
    @FXML
    private BorderPane BorderPane;
@FXML
    private AnchorPane leftmain;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void onCancel(ActionEvent event) {
    }
   /* private void open_sidebar(ActionEvent event) throws IOException {
         BorderPane border_pane = (BorderPane) ((Node) event.getSource()).getScene().getRoot();
        if (flag == true) {
            Parent sidebar = FXMLLoader.load(getClass().getResource("/app/views/platgui.fxml"));
            border_pane.setLeft(sidebar);
            flag = false;
        } else {
            border_pane.setLeft(null);
            flag = true;
        }
    }


    */
    
    
    /*
    private void getcatnale(MouseEvent event) throws SQLException {
        Connection cnx ;
        cnx = Database.getInstance().getConn();
        Statement st = cnx.createStatement();
        String req = "select * from  categorie";
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            String id_pack = rs.getString("name");

            categorieBox.getItems().add(id_pack);


    }
    }*/
     
    
    public void setText(Plat t) 
    {
      
       // System.out.println(t.getId());
        tfreferenceedit.setText(t.getReference());
        tfdesignationedit.setText(t.getDesignation());
       String prix =String.valueOf(t.getPrix());
        tfprixedit.setText(prix);
        tfdescriptionedit.setText(t.getDescription());
        imagepath.setText(t.getImageP());
        tfproduitsedit.setText(t.getNomProd());
        
        String imageE = "file:" + t.getImageP();

        Image imageg = new Image(imageE, 110, 110, false, true);

        imageviewedit.setImage(imageg);
        //SimpleDateFormat formatter2=new SimpleDateFormat("dd-MMM-yyyy");
       // Date date2=(Date) formatter2.parse(t.getDate_naiss()); 
      P=t;
       
        
       
    }
    @FXML
    private void modifierFormateur(ActionEvent event) throws SQLException, IOException {
         gestionPlat gp= new gestionPlat();
        String reference= tfreferenceedit.getText();
      String designation= tfdesignationedit.getText();
           double prix = Double.parseDouble(tfprixedit.getText());
        String description= tfdescriptionedit.getText();
         String nomProd= tfproduitsedit.getText() ;
         String imageP= imagepath.getText();
         Plat p;
           p = new Plat(reference,designation,prix,description,imageP,nomProd);
           gp.modifier(p);
       
            


        
    }

    @FXML
   public void insertImage(){
        
        FileChooser open = new FileChooser();
        
        Stage stage = (Stage)leftmain.getScene().getWindow();
        
        File file = open.showOpenDialog(stage);
        
        if(file != null){
            
            String path = file.getAbsolutePath();
            
            path = path.replace("\\", "\\\\");
            
            imagepath.setText(path);

            Image image = new Image(file.toURI().toString(), 110, 110, false, true);
            
            imageviewedit.setImage(image);
            
        }else{
            
            System.out.println("NO DATA EXIST!");
            
        }
    }
    
    
}
