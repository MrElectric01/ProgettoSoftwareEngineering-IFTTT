package progettosoftwareengineering.localifttt.model.rule.trigger.filedimension;

import java.io.File;
import org.junit.*;
import static org.junit.Assert.*;

public class FileDimensionTriggerTest {
   
    private String filePath = "src/test/resources/progettosoftwareengineering/localifttt/model/rule/trigger/filedimension/fileDimension.jpg";
    private String sizeThreshold = "20";
    private String sizeUnit = "KB";
    private FileDimensionTrigger trigger;

    @Before
    public void setUp() {
        trigger = new FileDimensionTrigger(filePath, sizeThreshold, sizeUnit);
    }

//    In order to verify that checkTrigger for this trigger is triggered when
//    the File dimension of the file specified in the tigger's attributes is bigger than
//    the size specified in tigger's attributes, we use an image in the resources, is bigger than 20KB.
    @Test
    public void testCheckTriggerTrue() {
        assertTrue(trigger.checkTrigger());
    }

//    In order to verify that checkTrigger for this trigger isn't triggered when
//    the File doesn't exist, we use a FakeFile, thath not exists.
    @Test
    public void testCheckTriggerFalseFileNotExist() {
        FileDimensionTrigger trigger2 = new FileDimensionTrigger("FakeFile", "2048", "B");
        assertFalse(trigger2.checkTrigger());
    }
    
    
//    In order to verify that checkTrigger for this trigger isn't triggered when
//    the File dimension of the file specified in the tigger's attributes is smaller than
//    the size specified in tigger's attributes, we use an image in the resources, 
//    that is 20KB, smaller than 1GB.
    @Test
    public void testCheckTriggerFalseFileSmaller() {
        FileDimensionTrigger trigger2 = new FileDimensionTrigger(filePath, "1", "GB");
        assertFalse(trigger2.checkTrigger());
    }

    @Test
    public void testToString() {
        assertEquals("File to check dimension: " + new File(filePath).getName() + "\n(Threshold: " + sizeThreshold + " " + sizeUnit + ")", trigger.toString());
    }
}
