/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import static com.jfoenix.svg.SVGGlyphLoader.clear;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Reclamation;
import entities.TypeReclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import static javax.management.remote.JMXConnectorFactory.connect;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.controlsfx.control.Notifications;
import services.ReclamationCRUD;
import services.TypeReclamationCRUD;
import tests.ModifierReclamationController;
import utils.MyConnection;


/**
 * FXML Controller class
 *
 * @author barki
 */
public class DashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection cnx;
    @FXML
    private TableColumn<Reclamation, Integer> idRec;
    @FXML
    private TableColumn<Reclamation, String> typeRec;
    @FXML
    private TableColumn<Reclamation, Integer> idU;
    @FXML
    private TableColumn<Reclamation, String> description;
    @FXML
    private TableColumn<Reclamation, String> etatRec;
    @FXML
    private TableView<Reclamation> tableView;
    @FXML
    private TableColumn colEdit;
    @FXML
    private TableColumn colEdit2;
    @FXML
    private TableColumn coldelete2;
    @FXML
    private TableColumn colmail;
    @FXML
    private TextField refT;
    @FXML
    private TableView<TypeReclamation> tableViewT;
    @FXML
    private TextField tftypeRec;
    @FXML
    private TableColumn<TypeReclamation, String> col_ref;
    @FXML
    private TableColumn<TypeReclamation, String> col_typeRec;
    @FXML
    private TextField filterRec;
    
    private ObservableList<PieChart.Data> data;
    
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Reclamation Reclamation = null ; 
    TypeReclamation TypeReclamation = null ; 
    ObservableList<Reclamation> ReclamationList = FXCollections.observableArrayList();
    ArrayList<String> aList = new ArrayList<String>();
    
    ObservableList<Reclamation> dataList;
    int index = -1 ;
    @FXML
    private TableColumn coldelete;
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        showRec();
        showTypeRec();
        searchRec();
        
        
    }   
    public  ObservableList<Reclamation> getReclamationList() {
         cnx = MyConnection.getInstance().getCnx();
        
        ObservableList<Reclamation> ReclamationList = FXCollections.observableArrayList();
        try {
                String query2="SELECT * FROM  reclam";
                PreparedStatement smt = cnx.prepareStatement(query2);
                Reclamation rec;
                ResultSet rs= smt.executeQuery();
            while(rs.next()){
                rec=new Reclamation(rs.getInt("idRec"),rs.getString("typeRec"),rs.getInt("idU"),rs.getString("description"),rs.getString("etatRec"));
                ReclamationList.add(rec);
            }
                System.out.println(ReclamationList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return ReclamationList;
   
    }
   
     public void affichageRec(){
          ObservableList<Reclamation> list = getReclamationList();
         idRec.setCellValueFactory(new PropertyValueFactory<>("idRec"));
         typeRec.setCellValueFactory(new PropertyValueFactory<>("typeRec"));
         idU.setCellValueFactory(new PropertyValueFactory<>("idU"));
         description.setCellValueFactory(new PropertyValueFactory<>("description"));
         etatRec.setCellValueFactory(new PropertyValueFactory<>("etatRec"));
         tableView.setItems(list);
     
     }
      public void affichageTypeRec(){
          ObservableList<TypeReclamation> listtr = getTypeReclamationList();
          col_typeRec.setCellValueFactory(new PropertyValueFactory<>("typeRec"));
          col_ref.setCellValueFactory(new PropertyValueFactory<>("refT"));
          tableViewT.setItems(listtr);
     
     }
     public void showRec(){
       
         ObservableList<Reclamation> list = getReclamationList();
         idRec.setCellValueFactory(new PropertyValueFactory<>("idRec"));
         typeRec.setCellValueFactory(new PropertyValueFactory<>("typeRec"));
         idU.setCellValueFactory(new PropertyValueFactory<>("idU"));
         description.setCellValueFactory(new PropertyValueFactory<>("description"));
         etatRec.setCellValueFactory(new PropertyValueFactory<>("etatRec"));
         
         
         Callback<TableColumn<Reclamation,String>,TableCell<Reclamation,String>> editcol=(param) -> {
             final TableCell<Reclamation,String> cell=new TableCell<Reclamation,String>(){
                 @Override
                 public void updateItem(String item,boolean empty){
                     super.updateItem(item, empty);
                     if (empty){
                         setGraphic(null);
                         setText(null);
                     }
                 else{
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                  
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            Reclamation = tableView.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("modifierReclamation.fxml"));
                            try {
                                loader.load();
                            } catch (Exception ex) {
                               System.err.println(ex.getMessage());
                            }
                            
                            ModifierReclamationController mrc = loader.getController();
                           // mrc.setUpdate(true);
                            mrc.setTextFields(Reclamation);
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            affichageRec();
                             
                        });

                        HBox managebtn = new HBox(editIcon);
                        managebtn.setStyle("-fx-alignment:center");
                       
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);
                     }
                 }
             };
             return cell;
          
         };
         colEdit.setCellFactory(editcol);
         
         Callback<TableColumn<Reclamation,String>,TableCell<Reclamation,String>> deletecol=(param) -> {
             final TableCell<Reclamation,String> cell=new TableCell<Reclamation,String>(){
                 @Override
                 public void updateItem(String item,boolean empty){
                     super.updateItem(item, empty);
                     if (empty){
                         setGraphic(null);
                         setText(null);
                     }
                 else{
                         FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                     
                            deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                                 Alert alert = new Alert(AlertType.CONFIRMATION);
                                 alert.setTitle("Supression Réclamation");
                                 alert.setHeaderText(null);
                                 alert.setContentText("Are you sure to delete ?");
                                 Optional <ButtonType> action = alert.showAndWait();
                                 if (action.get()== ButtonType.OK){
                                 try {
                                    Reclamation = tableView.getSelectionModel().getSelectedItem();
                                    ReclamationCRUD rec = new ReclamationCRUD() ;
                                    rec.supprimerReclamation(Reclamation);
                                    Notifications notificationBuilder = Notifications.create()
                                    .title("Réclamation")
                                    .text("Réclamation supprimée")
                                    .darkStyle()
                                    //.graphic(new ImageView(img))
                                    .position(Pos.TOP_CENTER)
                                    .hideAfter(javafx.util.Duration.seconds(5));
                                    //.graphic(new ImageView(img))
                                    notificationBuilder.showError();
                                    affichageRec();
                                    searchRec();
                                
                                } catch (Exception ex) {
                                   Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                              System.out.println("hello");

                            }});


                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                       

                        setGraphic(managebtn);

                        setText(null);
                     }
                 }
             };
             return cell;
          
         };
         coldelete.setCellFactory(deletecol);
         Callback<TableColumn<Reclamation,String>,TableCell<Reclamation,String>> mailcol=(param) -> {
             final TableCell<Reclamation,String> cell=new TableCell<Reclamation,String>(){
                 @Override
                 public void updateItem(String item,boolean empty){
                     super.updateItem(item, empty);
                     if (empty){
                         setGraphic(null);
                         setText(null);
                     }
                 else{
                         FontAwesomeIconView mailIcon = new FontAwesomeIconView(FontAwesomeIcon.ENVELOPE);
                        

                        mailIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#FFBB3C;"
                        );
                     
                            mailIcon.setOnMouseClicked((MouseEvent event) -> {
                                
                             try {
                                 Reclamation = tableView.getSelectionModel().getSelectedItem();
                                 
                                 int getidu = Reclamation.getIdU();
                                 String vID = String.valueOf(getidu);
                                 System.out.println(vID);
                                 int i;
                                 String email ;
                                 ArrayList<String> listgetidu = new ArrayList<String>();
                                 ArrayList<String> listgetemail= new ArrayList<String>();
                                  String query1="SELECT email from utilisateur";
                                 Statement st1 = cnx.createStatement();
                                 ResultSet rs1 = st1.executeQuery(query1);
                                 while(rs1.next()){
                                     listgetemail.add(rs1.getString("email"));
                                 }
                                 System.out.println(listgetemail);
                                 String query="SELECT idU from utilisateur";
                                 Statement st = cnx.createStatement();
                                 ResultSet rs = st.executeQuery(query);
                                 while(rs.next()){
                                     listgetidu.add(rs.getString("idU"));
                                 }
                                 System.out.println(listgetidu);
                                 for (i=0 ; i<listgetidu.size() ; i++)
                                 {
                                    
                                     if (listgetidu.get(i).equalsIgnoreCase(vID)){
                                        email=listgetemail.get(i);
                                          Gmail.sendMail(email);
                                          
                                     }
                                 }
                                 Notifications notificationBuilder = Notifications.create()
                                         .title("Réclamation")
                                         .text("Mail envoyée")
                                         .darkStyle()
                                         //.graphic(new ImageView(img))
                                         .position(Pos.TOP_CENTER)
                                         .hideAfter(javafx.util.Duration.seconds(5));
                                 //.graphic(new ImageView(img))
                                 notificationBuilder.showConfirm();
                                 
                                 searchRec();
                             } catch (SQLException ex) {
                                 Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                             }
                                
                             

                            });


                        HBox managebtn = new HBox(mailIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(mailIcon, new Insets(2, 2, 0, 3));
                       

                        setGraphic(managebtn);

                        setText(null);
                     }
                 }
             };
             return cell;
          
         };
         colmail.setCellFactory(mailcol);
         tableView.setItems(list);
         searchRec();
     }
     public  ObservableList<TypeReclamation> getTypeReclamationList() {
         cnx = MyConnection.getInstance().getCnx();
        //ObservableList l=new ArrayList();
        ObservableList<TypeReclamation> TypeReclamationList = FXCollections.observableArrayList();
        try {
                String query2="SELECT * FROM  type_rec";
                PreparedStatement smt = cnx.prepareStatement(query2);
                TypeReclamation tr;
                ResultSet rs= smt.executeQuery();
            while(rs.next()){
                tr=new TypeReclamation(rs.getString("typeRec"),rs.getString("refT"));
                TypeReclamationList.add(tr);
            }
                System.out.println(TypeReclamationList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return TypeReclamationList;
   
    }
  
     public void showTypeRec(){
         ObservableList<TypeReclamation> listtr = getTypeReclamationList();
          col_typeRec.setCellValueFactory(new PropertyValueFactory<>("typeRec"));
          col_ref.setCellValueFactory(new PropertyValueFactory<>("refT"));
          
             Callback<TableColumn<TypeReclamation,String>,TableCell<TypeReclamation,String>> editcol2=(param) -> {
             final TableCell<TypeReclamation,String> cell=new TableCell<TypeReclamation,String>(){
                 @Override
                 public void updateItem(String item,boolean empty){
                     super.updateItem(item, empty);
                     if (empty){
                         setGraphic(null);
                         setText(null);
                     }
                 else{
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                  
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            TypeReclamation = tableViewT.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("ModifierTypeRec.fxml"));
                            try {
                                loader.load();
                            } catch (Exception ex) {
                               System.err.println(ex.getMessage());
                            }
                            
                            ModifierTypeRecController mrc = loader.getController();
                           // mrc.setUpdate(true);
                            mrc.setTextFields(TypeReclamation);
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            showTypeRec();
                             
                        });

                        HBox managebtn = new HBox(editIcon);
                        managebtn.setStyle("-fx-alignment:center");
                       
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);
                     }
                 }
             };
             return cell;
          
         };
         colEdit2.setCellFactory(editcol2);
         
         Callback<TableColumn<TypeReclamation,String>,TableCell<TypeReclamation,String>> deletecol2=(param) -> {
             final TableCell<TypeReclamation,String> cell=new TableCell<TypeReclamation,String>(){
                 @Override
                 public void updateItem(String item,boolean empty){
                     super.updateItem(item, empty);
                     if (empty){
                         setGraphic(null);
                         setText(null);
                     }
                 else{
                         FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                     
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            Alert alert = new Alert(AlertType.CONFIRMATION);
                                 alert.setTitle("Supression Type Réclamation");
                                 alert.setHeaderText(null);
                                 alert.setContentText("Are you sure to delete ?");
                                 Optional <ButtonType> action = alert.showAndWait();
                                 if (action.get()== ButtonType.OK){
                            try {
                                TypeReclamation = tableViewT.getSelectionModel().getSelectedItem();
                                TypeReclamationCRUD tr = new TypeReclamationCRUD() ;
                                tr.supprimerTypeReclamation(TypeReclamation);
                                Notifications notificationBuilder = Notifications.create()
                                .title("Type Réclamation")
                                .text("Type Réclamation supprimée")
                                .darkStyle()
                                //.graphic(new ImageView(img))
                                .position(Pos.TOP_CENTER)
                                .hideAfter(javafx.util.Duration.seconds(5));
                                //.graphic(new ImageView(img))
                                notificationBuilder.showError();
                                affichageTypeRec();
                                
                            } catch (Exception ex) {
                               Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                          
                            
                        }});
                        

                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                       

                        setGraphic(managebtn);

                        setText(null);
                     }
                 }
             };
             return cell;
          
         };
         coldelete2.setCellFactory(deletecol2);
         
         
         tableViewT.setItems(listtr);
     }
    

    
   public void modifierReclamation(javafx.event.ActionEvent event) throws IOException, ParseException {
         Reclamation R=(Reclamation) tableView.getSelectionModel().getSelectedItem();
          //Parent root = FXMLLoader.load(getClass().getResource("/controller/Edit.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
               "modifierReclamation.fxml"));
            Parent root = (Parent) loader.load();
     Scene scene =new Scene(root);
     Stage stage =new Stage();
     stage.setScene(scene);
     stage.show();
     
       ModifierReclamationController modifier=loader.getController() ;
modifier.setText(R);
   }
   
   // @FXML
    private void searchRec() {
        idRec.setCellValueFactory(new PropertyValueFactory<>("idRec"));
         typeRec.setCellValueFactory(new PropertyValueFactory<>("typeRec"));
         idU.setCellValueFactory(new PropertyValueFactory<>("idU"));
         description.setCellValueFactory(new PropertyValueFactory<>("description"));
         etatRec.setCellValueFactory(new PropertyValueFactory<>("etatRec"));
         ObservableList<Reclamation> dataList = getReclamationList();
         tableView.setItems(dataList);
         FilteredList<Reclamation> filteredData = new FilteredList<>(dataList,b->true);
         filterRec.textProperty().addListener((observable,oldValue,newValue)-> {
             filteredData.setPredicate(rec-> {
                 if (newValue == null || newValue.isEmpty()){
                     return true;
                 }
                 String lowerCaseFilter = newValue.toLowerCase();
                 if (rec.getTypeRec().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                 return true;
                 }else if (rec.getEtatRec().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                 return true;
                 }
                 else 
                 return false ;
                 
             });
         });
         SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
         sortedData.comparatorProperty().bind(tableView.comparatorProperty());
         tableView.setItems(sortedData);
         
    }

    @FXML
    private void clearTypeRec(ActionEvent event) {
        refT.clear();
        tftypeRec.clear();
    }

  

    

    @FXML
    private void ajouterTypeRec(ActionEvent event) {
        
        String query = "INSERT INTO type_rec (typeRec,refT) VALUES (?,?)";
            cnx = MyConnection.getInstance().getCnx();
        try{
           
            if(tftypeRec.getText().isEmpty() & refT.getText().isEmpty()){
                refT.setStyle("-fx-border-color:#ef451b;"+"-fx-background-color:#fff5f0;");
                tftypeRec.setStyle("-fx-border-color:#ef451b;"+"-fx-background-color:#fff5f0;");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir les champs !");
                alert.showAndWait();
               
            }else if(refT.getText().isEmpty()){
                refT.setStyle("-fx-border-color:#C3C3C3;"+"-fx-background-color:#F3F1F0;");
                tftypeRec.setStyle("-fx-border-color:#ef451b;"+"-fx-background-color:#fff5f0;");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir le champ réference !");
                alert.showAndWait();
            }else if(tftypeRec.getText().isEmpty()){
                refT.setStyle("-fx-border-color:#ef451b;"+"-fx-background-color:#fff5f0;");
                tftypeRec.setStyle("-fx-border-color:#C3C3C3;"+"-fx-background-color:#F3F1F0;");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir le champ type réclamation !");
                alert.showAndWait();
            }else{
                PreparedStatement pst = cnx.prepareStatement(query);
         
            pst.setString(1, tftypeRec.getText());
            pst.setString(2, refT.getText());
           
            System.out.println("succé");
            pst.executeUpdate();
            showTypeRec();
            //Image img = new Image ("/medeet.images/succés.png");
            Notifications notificationBuilder = Notifications.create()
                       .title("Type réclamation")
                       .text("Type réclamation ajoutée")
                       .darkStyle()
                       //.graphic(new ImageView(img))
                       .position(Pos.TOP_CENTER)
                       .hideAfter(javafx.util.Duration.seconds(5));
                       //.graphic(new ImageView(img))
            notificationBuilder.showConfirm();
                
            }
        }catch(Exception e){
        System.out.println(e.getMessage());
         tftypeRec.setStyle("-fx-border-color:#ef451b;;"+"-fx-background-color:#fff5f0;");
        Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Type réclamation already exist !");
                alert.showAndWait();
        }
       
                
    }

  
    @FXML
    private void refreshReclamation(MouseEvent event) {
         affichageRec();
         searchRec();
    }
    @FXML
    private void refreshTypeRec(ActionEvent event) {
         affichageTypeRec();
    }

    @FXML
    private void pdf_rec(ActionEvent event) {
     
        try{
            JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\barki\\OneDrive\\Documents\\NetBeansProjects\\Medeet\\src\\tests\\report.jrxml");
        
            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, cnx);
            
            JasperViewer viewer = new JasperViewer(jPrint, false);
            
            viewer.setTitle("Liste des réclamations");
            viewer.show();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
     @FXML
    void sendMailRec(ActionEvent event) {
       try {
             FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("Music.fxml"));
                            try {
                                loader.load();
                            } catch (Exception ex) {
                               System.err.println(ex.getMessage());
                            }
                            
                          
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
        } catch (Exception ex) {
           System.out.println(ex.getMessage());
        }
    }
     @FXML
    void statRec(ActionEvent event) {
        try {
             FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("PieChart.fxml"));
                            try {
                                loader.load();
                            } catch (Exception ex) {
                               System.err.println(ex.getMessage());
                            }
                            
                          
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
        } catch (Exception ex) {
           System.out.println(ex.getMessage());
        }

    }

       

}
