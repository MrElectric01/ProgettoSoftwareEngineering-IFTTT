/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt.rule.action;


import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioAction implements Action {

    private String audioFilePath;

    public AudioAction(String audioFilePath) {
        this.audioFilePath = audioFilePath;
    }

    @Override
    public void doAction() {
        Alert alert = createAlert();
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
    }
    
    private Alert createAlert() {
        String[] split = audioFilePath.split("\\\\");
        String audioName = split[split.length-1];
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Audio playing!");
        alert.setHeaderText(null);
        alert.setContentText("The audio "+audioName+" is playing.\nPress the 'Stop Audio' button to stop the audio play.");
        
        // Rimuovi il pulsante OK predefinito
        alert.getButtonTypes().clear();

        // Aggiungi un nuovo pulsante personalizzato con il testo "Stop Audio"
        ButtonType stopAudioButton = new ButtonType("Stop Audio");
        alert.getButtonTypes().add(stopAudioButton);

        return alert;
    }

    @Override
    public String toString() {
        return "Audio: " + audioFilePath;
    }
}