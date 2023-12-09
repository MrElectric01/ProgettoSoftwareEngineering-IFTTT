package progettosoftwareengineering.localifttt.model.rule.action.deletefile;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import progettosoftwareengineering.localifttt.model.rule.action.Action;
import progettosoftwareengineering.localifttt.model.rule.action.ActionType;

public class DeleteFileActionCreatorTest {
    
//    In order to verify that this creator return the right Action,
//    we call the createAction method with the ActionType "DELETE_FILE", and the
//    appropried parameters, and verify that the returned Action is an
//    instance of DeleteFileAction.
    @Test
    public void testCreateAction() {
        DeleteFileActionCreator DFAC = new DeleteFileActionCreator();
        Map<String, String> actParam = new HashMap();
        actParam.put("deleteFileActionFilePath", "testFilePath");
        
        Action action = DFAC.createAction(ActionType.DELETE_FILE, actParam);
        assertTrue(action instanceof DeleteFileAction);
    }
}
