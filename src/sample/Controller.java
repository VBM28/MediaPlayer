package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;

public class Controller {

    @FXML
    private MediaView mediaView;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Slider timeSlider;

    private String filePath;

    private MediaPlayer mediaPlayer;


    @FXML
    private void openButtonAction() {


        //selecting the file
        FileChooser selectFile = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select File", "*.mp3;*.mp4");


        selectFile.getExtensionFilters().add(filter);
        File song = selectFile.showOpenDialog(null);


        //retrieving the path
        filePath = song.toURI().toString();

        if (filePath != null) {

            Media media = new Media(filePath);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);

            //auto resize for video
            DoubleProperty width = mediaView.fitWidthProperty();
            width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
            DoubleProperty height = mediaView.fitHeightProperty();
            height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));

            //connecting the volume to the volume slider
            volumeSlider.setValue(mediaPlayer.getVolume() * 100);
            volumeSlider.valueProperty().addListener(e -> mediaPlayer.setVolume(volumeSlider.getValue() / 100));

            //connecting the time value to the  slider
            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    timeSlider.setValue(newValue.toSeconds());
                }
            });

            timeSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(timeSlider.getValue()));
                }
            });

            mediaPlayer.play();
        }
    }

    @FXML
    private void playButtonAction() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
            mediaPlayer.setRate(1); //reset the rate if it was changed
        }
    }

    @FXML
    private void pauseButtonAction() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @FXML
    private void stopButtonAction() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    @FXML
    private void muteButtonAction() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isMute()) {
                mediaPlayer.setMute(false);
            } else if (!mediaPlayer.isMute()) {
                mediaPlayer.setMute(true);
            }
        }
    }

    @FXML
    private void slowerButtonAction() {

        if (mediaPlayer != null) {
            mediaPlayer.setRate(0.5);
        }
    }

    @FXML
    private void slowButtonAction() {

        if (mediaPlayer != null) {
            mediaPlayer.setRate(0.75);
        }
    }

    @FXML
    private void fastButtonAction() {
        if (mediaPlayer != null) {
            mediaPlayer.setRate(1.5);
        }
    }

    @FXML
    private void fasterButtonAction() {

        if (mediaPlayer != null) {
            mediaPlayer.setRate(2);
        }
    }

    @FXML
    private void exitButtonAction() {
        if (mediaPlayer != null) {
            mediaPlayer.dispose();
        }

    }
}
