/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettosoftwareengineering.localifttt.model.rule.action;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.assertTrue;

public class AudioActionCreatorTest {

//    In order to verify that this creator return the right Action,
//    we call the createAction method with the ActionType "AUDIO", and the
//    appropried parameters, and verify that the returned Action is an
//    instance of AudioAction.
    @Test
    public void testCreateAction() {
        AudioActionCreator AAC = new AudioActionCreator();
        Map<String,String> actParam = new HashMap();
        actParam.put("audioActionAudioPath", "testPath");
        
        Action action = AAC.createAction(ActionType.AUDIO, actParam);
        assertTrue(action instanceof AudioAction);
    }
}
