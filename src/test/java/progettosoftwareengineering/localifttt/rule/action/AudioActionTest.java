/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettosoftwareengineering.localifttt.rule.action;

import java.io.UnsupportedEncodingException;
import static java.lang.Thread.sleep;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.*;
import static org.junit.Assert.assertEquals;

public class AudioActionTest {
    
    private final String audio;
    private AudioAction action;

//    After retrieving the test audio path using getResource() from the specific project folder, 
//    we first decode it correctly, and then replace the '/' with '\' to obtain the same path format
//    returned by the FileChooser through the UI.
    public AudioActionTest() throws UnsupportedEncodingException {
        this.audio = URLDecoder.decode(AudioActionTest.class.getResource("WindowsXPError.mp3").getPath().substring(1), "UTF-8").replace("/", "\\");
    }

    @Before
    public void setUp() {
        action = new AudioAction(audio);
    }

//    In order to verify that the action is to start the audio passed at the 
//    time of creation, we start it to visually confirm.
    @Test
    public void testDoAction() {
        new JFXPanel();
        Platform.runLater(() -> action.doAction());
        try {
                sleep(4000);
            } catch (InterruptedException ex) {
                Logger.getLogger(AudioActionTest.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Test
    public void testToString() {
        assertEquals("Audio: "+audio, action.toString());
    }
    
}
