package progettosoftwareengineering.localifttt.model.rule.action.audio;

import progettosoftwareengineering.localifttt.model.rule.action.Action;

//Action for the audio playing.
public class AudioAction extends Action {

    private String audioFilePath;

    public AudioAction(String audioFilePath) {
        this.audioFilePath = audioFilePath;
    }

    @Override
    public void doAction() {
        setChanged();
        notifyObservers(audioFilePath);
    }

    @Override
    public String toString() {
        return "Audio: " + audioFilePath;
    }
}