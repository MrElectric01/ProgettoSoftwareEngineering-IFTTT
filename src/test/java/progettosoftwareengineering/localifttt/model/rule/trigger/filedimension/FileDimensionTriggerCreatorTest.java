package progettosoftwareengineering.localifttt.model.rule.trigger.filedimension;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import progettosoftwareengineering.localifttt.model.rule.trigger.Trigger;
import progettosoftwareengineering.localifttt.model.rule.trigger.TriggerType;

public class FileDimensionTriggerCreatorTest {
    
//    In order to verify that this creator return the right Trigger,
//    we call the createTrigger method with the TriggerType "FILE_DIMENSION", and the
//    appropried parameters, and verify that the returned Trigger is an
//    instance of FileDimensionTrigger.
    @Test
    public void testCreateTrigger() {
        FileDimensionTriggerCreator FDTC = new FileDimensionTriggerCreator();
        Map<String, String> trigParam = new HashMap<>();
        trigParam.put("fileDimensionTriggerFilePath", "test.txt");
        trigParam.put("fileDimensionTriggerSizeThreshold", "1024");
        trigParam.put("fileDimensionTriggerSizeUnit", "KB");

        Trigger trigger = FDTC.createTrigger(TriggerType.FILE_DIMENSION, trigParam);
        assertTrue(trigger instanceof FileDimensionTrigger);
    }
    
}
