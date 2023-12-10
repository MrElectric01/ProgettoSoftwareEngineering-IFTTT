package progettosoftwareengineering.localifttt.model.rule.action.movefile;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import progettosoftwareengineering.localifttt.model.rule.action.*;

public class MoveFileActionCreatorTest {

//    In order to verify that this creator return the right Action,
//    we call the createAction method with the ActionType "MOVE_FILE", and the
//    appropried parameters, and verify that the returned Action is an
//    instance of MoveFileAction.
    @Test
    public void testCreateAction() {
        MoveFileActionCreator MFAC = new MoveFileActionCreator();
        Map<String, String> actParam = new HashMap();
        actParam.put("moveFileActionFilePath", "testFilePath");
        actParam.put("moveFileActionDirectoryPath", "testDirectoryPath");
        
        Action action = MFAC.createAction(ActionType.MOVE_FILE, actParam);
        assertTrue(action instanceof MoveFileAction);
    }
    
}
