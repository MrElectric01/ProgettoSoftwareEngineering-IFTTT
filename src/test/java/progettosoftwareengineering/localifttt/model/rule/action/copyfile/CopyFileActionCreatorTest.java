package progettosoftwareengineering.localifttt.model.rule.action.copyfile;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import progettosoftwareengineering.localifttt.model.rule.action.*;

public class CopyFileActionCreatorTest {
    
//    In order to verify that this creator return the right Action,
//    we call the createAction method with the ActionType "COPY_FILE", and the
//    appropried parameters, and verify that the returned Action is an
//    instance of CopyFileAction.
    @Test
    public void testCreateAction() {
        CopyFileActionCreator CFAC = new CopyFileActionCreator();
        Map<String, String> actParam = new HashMap();
        actParam.put("copyFileActionFilePath", "testFilePath");
        actParam.put("copyFileActionDirectoryPath", "testDirectoryPath");
        
        Action action = CFAC.createAction(ActionType.COPY_FILE, actParam);
        assertTrue(action instanceof CopyFileAction);
    }
}
