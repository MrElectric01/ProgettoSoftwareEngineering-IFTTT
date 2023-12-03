/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt.controller.actioncontroller;

import java.io.File;
import java.util.Observable;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.media.*;
import progettosoftwareengineering.localifttt.model.rule.action.*;

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
    @Override
    public void update(Observable o, Object arg) {
        String audioFilePath = (String) arg;
        String[] split = audioFilePath.split("\\\\");
        String audioName = split[split.length-1];
        
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Audio playing!");
            alert.setHeaderText(null);
            alert.setContentText("The audio "+audioName+" is playing.\n\nPress the 'Stop Audio' button if you want to stop the audio play.");

            alert.getButtonTypes().clear();
            ButtonType stopAudioButton = new ButtonType("Stop Audio");
            alert.getButtonTypes().add(stopAudioButton);


            Media media = new Media(new File(audioFilePath).toURI().toString());
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
    }
    
}
