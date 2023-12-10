package progettosoftwareengineering.localifttt.model.rule.action.audio;

import java.util.*;
import org.junit.*;
import progettosoftwareengineering.localifttt.model.rule.action.*;

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
