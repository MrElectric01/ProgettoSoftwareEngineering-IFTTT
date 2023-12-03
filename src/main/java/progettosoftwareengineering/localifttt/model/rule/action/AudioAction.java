/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt.model.rule.action;

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