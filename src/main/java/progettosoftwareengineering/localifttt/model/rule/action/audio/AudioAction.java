package progettosoftwareengineering.localifttt.model.rule.action.audio;

import java.io.File;
import progettosoftwareengineering.localifttt.model.rule.action.Action;

//Action for the audio playing.
public class AudioAction extends Action {

    private File audioFile;

    public AudioAction(String audioFilePath) {
        this.audioFile = new File(audioFilePath);
    }

//    Before launching the action we check that the file is still available.
    @Override
    public void doAction() {
        if(audioFile.exists()) {
            setChanged();
            notifyObservers(audioFile);
        } else {
            setChanged();
            notifyObservers("The file \"" + this.audioFile + "\" doesn't exist anymore!");
        }
    }

    @Override
    public String toString() {
        return "Audio: " + audioFile;
    }
}