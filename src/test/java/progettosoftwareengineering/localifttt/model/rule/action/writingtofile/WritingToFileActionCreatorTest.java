package progettosoftwareengineering.localifttt.model.rule.action.writingtofile;

import java.util.*;
import org.junit.*;

import progettosoftwareengineering.localifttt.model.rule.action.*;

import static org.junit.Assert.assertTrue;

public class WritingToFileActionCreatorTest {
    
//    In order to verify that this creator return the right Action,
//    we call the createAction method with the ActionType "WRITING_TO_FILE", and the
//    appropried parameters, and verify that the returned Action is an
//    instance of WritingToFileAction.
    @Test
    public void testCreateAction() {
        WritingToFileActionCreator WTFAC = new WritingToFileActionCreator();
        Map<String, String> actParam = new HashMap();
        actParam.put("writingToFileActionFilePath", "testPath");
        actParam.put("writingToFileActionTextToAppend","testToAppend");
        
        Action action = WTFAC.createAction(ActionType.WRITING_TO_FILE, actParam);
        assertTrue(action instanceof WritingToFileAction);
    }
}
