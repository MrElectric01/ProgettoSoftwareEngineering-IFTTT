package progettosoftwareengineering.localifttt.controller.actioncontroller;

import java.io.File;
import java.util.Observable;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.media.*;
import progettosoftwareengineering.localifttt.model.rule.action.*;
import progettosoftwareengineering.localifttt.model.rule.action.audio.AudioAction;

public class AudioActionController extends BaseActionController {

    @Override
    public void observeAction(Action action) {
        if(action instanceof AudioAction)
            action.addObserver(this);
        else
            this.nextController(action);
    }

//    Execute an AudioAction with the audio in the parameter, showing a dialog
//    box in order to give the possibility to stop the play.
//    In addition manage the show of the Alert for the error. 
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof File)
            Platform.runLater(() -> {
                File audioFile = (File) arg;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Audio playing!");
                alert.setHeaderText(null);
                alert.setContentText("The audio "+audioFile.getName()+" is playing.\n\nPress the 'Stop Audio' button if you want to stop the audio play.");

                alert.getButtonTypes().clear();
                ButtonType stopAudioButton = new ButtonType("Stop Audio");
                alert.getButtonTypes().add(stopAudioButton);


                Media media = new Media(audioFile.toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);

                alert.setOnCloseRequest(onClose -> {
                    mediaPlayer.stop();
                    mediaPlayer.dispose();
                });

                mediaPlayer.setOnEndOfMedia(() -> {
                    mediaPlayer.stop();
                    mediaPlayer.dispose();
                    alert.close();
                });

            alert.show();
            mediaPlayer.play();
            });
        else
            Platform.runLater(() -> {
                String error = (String) arg;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Audio not found");
                alert.setHeaderText(null);
                alert.setContentText(error);
                alert.show();
            });
    }
    
}
