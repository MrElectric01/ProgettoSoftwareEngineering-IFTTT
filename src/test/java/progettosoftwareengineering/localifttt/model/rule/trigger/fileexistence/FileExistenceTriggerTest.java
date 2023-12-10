package progettosoftwareengineering.localifttt.model.rule.trigger.fileexistence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileExistenceTriggerTest {

    private String filePath = "existenceTest.txt";
    private FileExistenceTrigger trigger;

    @Before
    public void setUp() {
        trigger = new FileExistenceTrigger(filePath);
    }

//    In order to verify that checkTrigger for this trigger is triggered when
//    the File specified in the tigger's attributes exists, we call it after the file creation.
    @Test
    public void testCheckTriggerTrue() throws IOException {
        Files.createFile(Paths.get(filePath));
        assertTrue(trigger.checkTrigger());
        Files.delete(Paths.get(filePath));
    }

//    In order to verify that checkTrigger for this trigger isn't triggered when
//    the File specified in the tigger's attributes doesn't exist, we call it without create the file.
    @Test
    public void testCheckTriggerFalse() {
        assertFalse(trigger.checkTrigger());
    }

    @Test
    public void testToString() {
        assertEquals("File to check existence: " + new File(filePath).getAbsolutePath(), trigger.toString());
    }
}
