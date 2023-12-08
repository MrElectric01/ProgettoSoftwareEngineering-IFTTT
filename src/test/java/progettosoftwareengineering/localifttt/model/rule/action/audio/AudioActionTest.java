/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettosoftwareengineering.localifttt.model.rule.action.audio;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Observer;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AudioActionTest {
    
    private final String audio;
    private AudioAction action;
    private Observer obs;
    
//    After retrieving the test audio path using getResource() from the specific project folder, 
//    we first decode it correctly, and then replace the '/' with '\' to obtain the same path format
//    returned by the FileChooser through the UI.
    public AudioActionTest() throws UnsupportedEncodingException {
        this.audio = URLDecoder.decode(AudioActionTest.class.getResource("WindowsXPError.mp3").getPath().substring(1), "UTF-8").replace("/", "\\");
    }

    @Before
    public void setUp() {
        action = new AudioAction(audio);
        obs = mock(Observer.class);
    }

//    In order to verify that the action notify the observer
//    that launches the action if the audioFile exists, we instance 
//    a mock observer and verify if his update method is called with the File.
    @Test
    public void testDoActionAudioExist() {
        action.addObserver(obs);
        action.doAction();
        verify(obs, times(1)).update(action, new File(audio));
    }
    
//    In order to verify that the action notify the observer
//    that launches the error if audioFile doesn't exist, we create
//    an action with a FakeFile and instance a mock observer and 
//    verify, after the doAction execution, if the Observer's update method 
//    is called with the error message.
    @Test
    public void testDoActionAudioNotExist() {
        AudioAction action2 = new AudioAction("test");
        action2.addObserver(obs);
        action2.doAction();
        verify(obs, times(1)).update(action2, "The file \"test\" doesn't exist anymore!");
    }

    @Test
    public void testToString() {
        assertEquals("Audio: "+audio, action.toString());
    }
    
}
