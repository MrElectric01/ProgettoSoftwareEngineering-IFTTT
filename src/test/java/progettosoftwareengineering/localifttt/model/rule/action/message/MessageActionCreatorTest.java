package progettosoftwareengineering.localifttt.model.rule.action.message;

import java.util.*;
import org.junit.*;
import progettosoftwareengineering.localifttt.model.rule.action.*;

import static org.junit.Assert.assertTrue;

public class MessageActionCreatorTest {

//    In order to verify that this creator return the right Action,
//    we call the createAction method with the ActionType "MESSAGE", and the
//    appropried parameters, and verify that the returned Action is an
//    instance of MessageAction.
    @Test
    public void testCreateAction() {
        MessageActionCreator MAC = new MessageActionCreator();
        Map<String,String> actParam = new HashMap();
        actParam.put("messageActionMessage", "test");
        
        Action action = MAC.createAction(ActionType.MESSAGE, actParam);
        assertTrue(action instanceof MessageAction);
    }
}
