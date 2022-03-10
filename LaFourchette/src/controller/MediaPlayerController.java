/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;


/**
 *
 * @author Ammar
 */
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class MediaPlayerController implements Initializable {
    
    private String path;
    private MediaPlayer mediaPlayer;
    
    @FXML
    private MediaView mediaView;
    
    
    @FXML
    private Slider volumeSlider;
    
    @FXML
    private Slider progressBar;
    
    
    @FXML
    private StackPane pane;
   private File file;
   private Media media;
    
    private void OpenFileMethod() {
        
       
//        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a .mp4 file", ".mp4");
//        fileChooser.getExtensionFilters().add(filter);
    
        
       
              file=new File("C:/Users/lenovo/Documents/NetBeansProjects/firstp/src/videos/Restaurant advertisement epic food reel.mp4");
              
        
       
       
         
        path =file.toURI().toString();

        
            Media media = new Media(path);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            
            //creating bindings
            DoubleProperty widthProp = mediaView.fitWidthProperty();
            DoubleProperty heightProp = mediaView.fitHeightProperty();
            
            widthProp.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
            heightProp.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
            
            volumeSlider.setValue(mediaPlayer.getVolume()*100);
            volumeSlider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volumeSlider.getValue()/100);
                }
            });
            
            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<javafx.util.Duration>() {
                @Override
                public void changed(ObservableValue<? extends javafx.util.Duration> observable, javafx.util.Duration oldValue, javafx.util.Duration newValue) {
                    progressBar.setValue(newValue.toSeconds());
                }
            }
            );
            
            progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(javafx.util.Duration.seconds(progressBar.getValue()));
                }
            });
            
            progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(javafx.util.Duration.seconds(progressBar.getValue()));
                }
            });
            
            mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    javafx.util.Duration total = media.getDuration();
                    progressBar.setMax(total.toSeconds());
                }
            });
            
            mediaPlayer.play();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          OpenFileMethod();
        mediaPlayer.play();
        mediaPlayer.setRate(1);
    }    
    
    @FXML
    public void pauseVideo(ActionEvent event){
        mediaPlayer.pause();
    }
    
    @FXML
    public void stopVideo(ActionEvent event){
        mediaPlayer.stop();
    }
    
    @FXML
    public void playVideo(ActionEvent event){
      
           
      
        mediaPlayer.play();
        mediaPlayer.setRate(1);
    }
    
    @FXML
    public void skip5(ActionEvent event){
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(javafx.util.Duration.seconds(5)));
    }
    
    @FXML
    public void furtherSpeedUpVideo(ActionEvent event){
        mediaPlayer.setRate(2);
    }
    
    @FXML
    public void back5(ActionEvent event){
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(javafx.util.Duration.seconds(-5)));
    }
    
    @FXML
    public void furtherSlowDownVideo(ActionEvent event){
        mediaPlayer.setRate(0.5);

    }

    
    
}
