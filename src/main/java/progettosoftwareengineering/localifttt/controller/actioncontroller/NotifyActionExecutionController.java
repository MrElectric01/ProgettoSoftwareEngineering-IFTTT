package progettosoftwareengineering.localifttt.controller.actioncontroller;

import javafx.util.Duration;
import java.util.Observable;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import progettosoftwareengineering.localifttt.model.rule.action.Action;
import progettosoftwareengineering.localifttt.model.rule.action.copyfile.CopyFileAction;
import progettosoftwareengineering.localifttt.model.rule.action.movefile.MoveFileAction;
import progettosoftwareengineering.localifttt.model.rule.action.writingtofile.WritingToFileAction;

public class NotifyActionExecutionController extends BaseActionController {

    @Override
    public void observeAction(Action action) {
        if(action instanceof WritingToFileAction || action instanceof MoveFileAction || action instanceof CopyFileAction)
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
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
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
