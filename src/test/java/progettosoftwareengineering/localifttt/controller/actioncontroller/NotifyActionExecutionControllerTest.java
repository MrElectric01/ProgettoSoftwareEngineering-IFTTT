package progettosoftwareengineering.localifttt.controller.actioncontroller;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.nio.file.*;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.testfx.util.WaitForAsyncUtils.waitForFxEvents;
import org.junit.*;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import progettosoftwareengineering.localifttt.model.rule.action.Action;
import progettosoftwareengineering.localifttt.model.rule.action.movefile.MoveFileAction;
import progettosoftwareengineering.localifttt.model.rule.action.writingtofile.WritingToFileAction;

public class NotifyActionExecutionControllerTest {

    private List<Action> actions;
    private NotifyActionExecutionController NAEC;

    @Before
    public void setUp() throws IOException {
        actions = new ArrayList();
        NAEC = new NotifyActionExecutionController();
    }

//    In order to verify that this controller observe the right Actions,
//    we call the testHelper1 that creates the right Actions and observes them with the controller,
//    after that we check that all the actions are observed.
    @Test
    public void testObserveAction() {
        testHelper1("test/test.txt", "MoveTest.txt", "test");
        
        for(Action action: actions)
            assertEquals(1, action.countObservers());
        
        actions.clear();
    }
    
//    In order to verify that the normal update of this controller is to open 
//    a dialog box with the message of the Actions that trigger the update,
//    we create all the necessary for the Actions that are created and executed with testHelper2,
//    and visually check the right behaviour (NOT AUTOMATIC). 
    @Test
    public void testUpdateWithoutError() throws IOException {
        Files.createDirectory(Paths.get("test1"));
        Files.createFile(Paths.get("MoveTest1.txt"));
        
        testHelper2("test1/test1.txt", "MoveTest1.txt", "test1");
        
        Files.delete(Paths.get("test1/test1.txt"));
        Files.delete(Paths.get("test1/MoveTest1.txt"));
        Files.delete(Paths.get("test1"));
    }
    
//    In order to verify that the "error" update of this controller is to open 
//    a dialog box with the error message of the Actions that trigger the update,
//    we create all the necessary for trigger the errors of the Actions 
//    that are created and executed with testHelper2,
//    and visually check the right behaviour (NOT AUTOMATIC). 
    @Test
    public void testUpdateWithError() throws IOException {
        Files.createFile(Paths.get("MoveTest2.txt"));
        
        testHelper2("test2/test2.txt", "MoveTest2.txt", "test2");
        
        Files.delete(Paths.get("MoveTest2.txt"));
    }
    
    private void testHelper1(String WTFAFile, String MFAFile, String MFADirectory) {
        actions.add(new WritingToFileAction(WTFAFile,"toAppendStringTest"));
        actions.add(new MoveFileAction(MFAFile, MFADirectory));
        
        for(Action action: actions)
            NAEC.observeAction(action);
    }
    
    private void testHelper2(String WTFAFile, String MFAFile, String MFADirectory) {
        testHelper1(WTFAFile, MFAFile, MFADirectory);
        
        new JFXPanel();
        for(Action action: actions) {
            Platform.runLater(() -> action.doAction());
             try {
                sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            waitForFxEvents();
        }
        
        actions.clear();
    }
}
