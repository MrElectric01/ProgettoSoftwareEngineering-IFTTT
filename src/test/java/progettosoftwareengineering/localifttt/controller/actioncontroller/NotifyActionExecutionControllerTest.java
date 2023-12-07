package progettosoftwareengineering.localifttt.controller.actioncontroller;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.testfx.util.WaitForAsyncUtils.waitForFxEvents;

import org.junit.Before;
import org.junit.Test;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import progettosoftwareengineering.localifttt.model.rule.action.writingtofile.WritingToFileAction;

public class NotifyActionExecutionControllerTest {

    private NotifyActionExecutionController NAEC;
    private WritingToFileAction action;

    @Before
    public void setUp() {
        NAEC = new NotifyActionExecutionController();
        action = spy(new WritingToFileAction("test.txt","toAppendStringTest"));
        NAEC.observeAction(action);
    }

//    In order to verify that this controller observe the right Action,
//    in the setUp we call the observeAction method with a WritingToFileAction
//    as parameter, and in this test we verify if that action has an observer.
    @Test
    public void testObserveAction() {
        assertEquals(1, action.countObservers());
    } 
    
//    In order to verify that the update of this controller is to open 
//    a dialog box with the message of the WritingToFileAction that trigger 
//    the observer, we execute the doAction of the action created in 
//    the setUp to visually confirm.
    @Test
    public void testUpdate() {
        new JFXPanel();
        Platform.runLater(() -> action.doAction());
         try {
            sleep(4000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        waitForFxEvents();
    }

}
