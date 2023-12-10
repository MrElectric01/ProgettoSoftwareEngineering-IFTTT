package progettosoftwareengineering.localifttt.controller.actioncontroller;

import java.io.*;
import static java.lang.Thread.sleep;
import java.nio.file.*;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.testfx.util.WaitForAsyncUtils.waitForFxEvents;
import org.junit.*;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import progettosoftwareengineering.localifttt.model.rule.action.Action;
import progettosoftwareengineering.localifttt.model.rule.action.copyfile.CopyFileAction;
import progettosoftwareengineering.localifttt.model.rule.action.deletefile.DeleteFileAction;
import progettosoftwareengineering.localifttt.model.rule.action.movefile.MoveFileAction;
import progettosoftwareengineering.localifttt.model.rule.action.programexecution.ProgramExecutionAction;
import progettosoftwareengineering.localifttt.model.rule.action.writingtofile.WritingToFileAction;

public class NotifyActionExecutionControllerTest {

    private Map<String, String> param;
    private List<Action> actions;
    private NotifyActionExecutionController NAEC;

    @Before
    public void setUp() throws IOException {
        actions = new ArrayList();
        NAEC = new NotifyActionExecutionController();
        param = new HashMap();
    }

//    In order to verify that this controller observe the right Actions,
//    we call the testHelper1 that creates the right Actions and observes them with the controller,
//    after that we check that all the actions are observed.
    @Test
    public void testObserveAction() {
        param.put("WTFAFile", "test/test.txt");
        param.put("MFAFile", "MoveTest.txt");
        param.put("MFADirectory", "test");
        param.put("CFAFile", "CopyTest.txt");
        param.put("DFAFile", "DeleteTest.txt");
        param.put("PEAInterpreter", "interpreter");
        param.put("PEAProgramPath", "test.jar");
        
        testHelper1();
        
        for(Action action: actions)
            assertEquals(1, action.countObservers());
        
        actions.clear();
        param.clear();
    }
    
//    In order to verify that the normal update of this controller is to open 
//    a dialog box with the message of the Actions that trigger the update,
//    we create all the necessary for the Actions that are created and executed with testHelper2,
//    and visually check the right behaviour (NOT AUTOMATIC). 
    @Test
    public void testUpdateWithoutError() throws IOException, InterruptedException {
        Files.createDirectory(Paths.get("test1"));
        Files.createFile(Paths.get("MoveTest1.txt"));
        Files.createFile(Paths.get("CopyTest1.txt"));
        Files.createFile(Paths.get("DeleteTest1.txt"));
        
        param.put("WTFAFile", "test1/test1.txt");
        param.put("MFAFile", "MoveTest1.txt");
        param.put("MFADirectory", "test1");
        param.put("CFAFile", "CopyTest1.txt");
        param.put("DFAFile", "DeleteTest1.txt");
        param.put("PEAInterpreter", "java -jar");
//        TestProgram.jar is a build of a test program that, after a sleeping time (to simulate a long execution) write in a file ("outputTestProgram.csv")
//        the command line passed arguments.
        param.put("PEAProgramPath", "src/test/resources/progettosoftwareengineering/localifttt/controller/actioncontroller/TestProgram.jar");
        
        testHelper2();
        
        Files.delete(Paths.get("test1/test1.txt"));
        Files.delete(Paths.get("test1/MoveTest1.txt"));
        Files.delete(Paths.get("test1/CopyTest1.txt"));
        Files.delete(Paths.get("CopyTest1.txt"));
        Files.delete(Paths.get("test1"));
//        We have to wait that the executed program has finished.
        while(!new File("outputTestProgram.csv").exists()) {
            sleep(1000);
        }
        Files.delete(Paths.get("outputTestProgram.csv"));
    }
    
//    In order to verify that the "error" update of this controller is to open 
//    a dialog box with the error message of the Actions that trigger the update,
//    we create all the necessary for trigger the errors of the Actions 
//    that are created and executed with testHelper2,
//    and visually check the right behaviour (NOT AUTOMATIC). 
    @Test
    public void testUpdateWithError() throws IOException {
        Files.createFile(Paths.get("MoveTest2.txt"));
        
        param.put("WTFAFile", "test2/test2.txt");
        param.put("MFAFile", "MoveTest2.txt");
        param.put("MFADirectory", "test2");
        param.put("CFAFile", "CopyTest2.txt");
        param.put("DFAFile", "DeleteTest2.txt");
        param.put("PEAInterpreter", "java -jar");
        param.put("PEAProgramPath", "FakeProgram");
        
        testHelper2();
        
        Files.delete(Paths.get("MoveTest2.txt"));
    }
    
    private void testHelper1() {
        actions.add(new WritingToFileAction(param.get("WTFAFile"),"toAppendStringTest"));
        actions.add(new MoveFileAction(param.get("MFAFile"), param.get("MFADirectory")));
        actions.add(new CopyFileAction(param.get("CFAFile"), param.get("MFADirectory")));
        actions.add(new DeleteFileAction(param.get("DFAFile")));
        actions.add(new ProgramExecutionAction(param.get("PEAInterpreter"), param.get("PEAProgramPath"), null));
        
        for(Action action: actions)
            NAEC.observeAction(action);
    }
    
    private void testHelper2() {
        testHelper1();
        
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
        param.clear();
    }
}
