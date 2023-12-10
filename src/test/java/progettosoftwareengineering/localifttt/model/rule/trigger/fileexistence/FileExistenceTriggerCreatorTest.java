package progettosoftwareengineering.localifttt.model.rule.trigger.fileexistence;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import progettosoftwareengineering.localifttt.model.rule.trigger.*;

public class FileExistenceTriggerCreatorTest {

//    In order to verify that this creator return the right Trigger,
//    we call the createTrigger method with the TriggerType "FILE_EXISTENCE", and the
//    appropried parameters, and verify that the returned Trigger is an
//    instance of FileExistenceTrigger.
    @Test
    public void testCreateTrigger() {
        FileExistenceTriggerCreator FETC = new FileExistenceTriggerCreator();
        Map<String, String> trigParam = new HashMap();
        trigParam.put("fileExistenceTriggerFilePath", "test.txt");

        Trigger trigger = FETC.createTrigger(TriggerType.FILE_EXISTENCE, trigParam);
        assertTrue(trigger instanceof FileExistenceTrigger);
    }
}
