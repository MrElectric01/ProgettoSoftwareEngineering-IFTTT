/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettosoftwareengineering.localifttt.controller.actioncontroller;

import java.io.UnsupportedEncodingException;
import static java.lang.Thread.sleep;
import java.net.URLDecoder;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import progettosoftwareengineering.localifttt.model.rule.action.audio.AudioAction;

import org.junit.*;
import static org.junit.Assert.*;
import static org.testfx.util.WaitForAsyncUtils.waitForFxEvents;

public class AudioActionControllerTest {
    
    private AudioActionController AAC;
    private  String audio;
    private AudioAction action;
    
//    After retrieving the test audio path using getResource() from the specific project folder, 
//    we first decode it correctly, and then replace the '/' with '\' to obtain the same path format
//    returned by the FileChooser through the UI.
    public AudioActionControllerTest() throws UnsupportedEncodingException {
        this.audio = URLDecoder.decode(AudioActionControllerTest.class.getResource("WindowsXPError.mp3").getPath().substring(1), "UTF-8").replace("/", "\\");
    }
    
    @Before
    public void setUp() throws UnsupportedEncodingException {
        AAC = new AudioActionController();
        action = new AudioAction(audio);
        AAC.observeAction(action);
    }

//    In order to verify that this controller observe the right Action,
//    in the setUp we call the observeAction method with a AudioAction
//    as parameter, and in this test we verify if that action has an observer.
    @Test
    public void testObserveAction() {
        assertEquals(1, action.countObservers());
    }

//    In order to verify that the update of this controller is to play the audio 
//    of the AudioAction that trigger the observer, we execute the doAction 
//    of the action created in the setUp to visually confirm.
    @Test
    public void testUpdate() {
        new JFXPanel();
        Platform.runLater(() -> action.doAction());
        try {
            sleep(4000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        waitForFxEvents();
    }
    
}
