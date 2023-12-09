package progettosoftwareengineering.localifttt.model.rule.action.programexecution;

import java.util.*;
import org.junit.*;

import progettosoftwareengineering.localifttt.model.rule.action.*;

import static org.junit.Assert.assertTrue;

public class ProgramExecutionActionCreatorTest {
    
//    In order to verify that this creator return the right Action,
//    we call the createAction method with the ActionType "PROGRAMEX_ECUTION", and the
//    appropried parameters, and verify that the returned Action is an
//    instance of ProgramExecutionAction.
    @Test
    public void testCreateAction() {
        ProgramExecutionActionCreator PEAC = new ProgramExecutionActionCreator();
        Map<String, String> actParam = new HashMap();
        actParam.put("programExecutionActionInterpreter", "testInterpreter");
        actParam.put("programExecutionActionProgramPath","testProgramPath");
        actParam.put("programExecutionActionProgramArguments","testProgramArguments");
        
        Action action = PEAC.createAction(ActionType.PROGRAMEX_ECUTION, actParam);
        assertTrue(action instanceof ProgramExecutionAction);
    }
}