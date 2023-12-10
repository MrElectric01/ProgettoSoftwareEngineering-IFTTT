package progettosoftwareengineering.localifttt.controller.actioncontroller;

import javafx.util.Duration;
import java.util.Observable;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import progettosoftwareengineering.localifttt.model.rule.action.Action;
import progettosoftwareengineering.localifttt.model.rule.action.copyfile.CopyFileAction;
import progettosoftwareengineering.localifttt.model.rule.action.deletefile.DeleteFileAction;
import progettosoftwareengineering.localifttt.model.rule.action.movefile.MoveFileAction;
import progettosoftwareengineering.localifttt.model.rule.action.programexecution.ProgramExecutionAction;
import progettosoftwareengineering.localifttt.model.rule.action.writingtofile.WritingToFileAction;

//Class useful to manage all that actions that use the GUI only to show 
//an Alert that notifies that the action is done.
public class NotifyActionExecutionController extends BaseActionController {
//    Duration of the Alert in second. 
    private int notifyDuration = 3;

    @Override
    public void observeAction(Action action) {
        if(
                action instanceof WritingToFileAction || 
                action instanceof MoveFileAction || 
                action instanceof CopyFileAction || 
                action instanceof DeleteFileAction ||
                action instanceof ProgramExecutionAction
            )
            action.addObserver(this);
        else
            this.nextController(action);
    }


//    Show a timed dialog box in order to notify the user that the action has been executed.
//    In addition manage the show of the Alert for the error. 
    @Override
    public void update(Observable o, Object arg) {
        String[] arrayArg = (String[]) arg;
        
        if(arrayArg.length == 1) {
            Platform.runLater(() -> {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Rule activated");
                alert.setHeaderText(null);
                alert.setContentText(arrayArg[0]);
                alert.show();
                alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);
                PauseTransition delay = new PauseTransition(Duration.seconds(notifyDuration));
                delay.setOnFinished(event -> alert.close());
                delay.play();
            });
        } else {
            Platform.runLater(() -> {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle(arrayArg[1]);
                alert.setHeaderText(null);
                alert.setContentText(arrayArg[0]);
                alert.show();
            });
        }
    }
}
