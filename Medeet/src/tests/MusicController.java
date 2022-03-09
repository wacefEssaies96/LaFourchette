/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author barki
 */
public class MusicController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private Label songLabel;
    @FXML
    private ProgressBar songProgressBar;
    @FXML
    private Button playButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;
    @FXML
    private ComboBox<String> speedBox;
    @FXML
    private Slider volumeSlider;
    private File directory;
    private File[] files ;
    private ArrayList<File> songs;
    private int[] speeds = {25,50,75,100,125,150,175,200};
    private int songNumber;
    private Timer timer;
    private TimerTask task  ;
    private boolean running;
    private Media media;
    private MediaPlayer mediaPlayer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       songs = new ArrayList<File>();
        directory = new File("music");
        files = directory.listFiles();
        if (files!= null){
            for(File file : files){
                songs.add(file);
                System.out.println(file);
            }
        }
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        songLabel.setText(songs.get(songNumber).getName());  
        
        for(int i=0; i< speeds.length ; i++){
            speedBox.getItems().add(Integer.toString(speeds[i])+"%");
        }
        speedBox.setOnAction(this::changeSpeed);
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>(){
           @Override
           public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
               mediaPlayer.setVolume(volumeSlider.getValue()*0.01);
           }
            
        });
    }    

    @FXML
    private void playMedia(ActionEvent event) {
        beginTimer();
        changeSpeed(null);
        mediaPlayer.play();
    }

    @FXML
    private void pauseMedia(ActionEvent event) {
        cancelTimer();
        mediaPlayer.pause();
    }

    @FXML
    private void resetMedia(ActionEvent event) {
        songProgressBar.setProgress(0);
        mediaPlayer.seek(Duration.seconds(0));
    }

    @FXML
    private void previousMedia(ActionEvent event) {
         if(songNumber > 0){
            songNumber--;
            mediaPlayer.stop();
            if(running){
                cancelTimer();
            }
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer= new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName());
            mediaPlayer.play();
        }
        else {
            songNumber=songs.size() -1;
            mediaPlayer.stop();
              if(running){
                cancelTimer();
            }
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer= new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName());
            mediaPlayer.play();
        }
        
    }

    @FXML
    private void nextMedia(ActionEvent event) {
        if(songNumber < songs.size() -1){
            songNumber++;
            mediaPlayer.stop();
              if(running){
                cancelTimer();
            }
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer= new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName());
            mediaPlayer.play();
        }
        else {
            songNumber=0;
            mediaPlayer.stop();
              if(running){
                cancelTimer();
            }
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer= new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName());
            mediaPlayer.play();
        }
    }

    @FXML
    private void changeSpeed(ActionEvent event) {
        if (speedBox.getValue()== null){
            mediaPlayer.setRate(1);
        }else {
        mediaPlayer.setRate(Integer.parseInt(speedBox.getValue().substring(0,speedBox.getValue().length() -1)) * 0.01);
        }
    }
    public void beginTimer(){
        timer = new Timer();
        task= new TimerTask(){
            public void run(){
                running=true;
                double current=mediaPlayer.getCurrentTime().toSeconds();
                double end=media.getDuration().toSeconds();
                songProgressBar.setProgress(current/end);
                if (current/end == 1){
                    cancelTimer();
                }
            }
        };
    }
     public void cancelTimer(){
         running=false;
         timer.cancel();
     
     }
    
}
