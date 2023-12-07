package progettosoftwareengineering.localifttt.controller.actioncontroller;

import javafx.util.Duration;
import java.util.Observable;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import progettosoftwareengineering.localifttt.model.rule.action.Action;
import progettosoftwareengineering.localifttt.model.rule.action.writingtofile.WritingToFileAction;

public class NotifyActionExecutionController extends BaseActionController {

    @Override
    public void observeAction(Action action) {
        if(action instanceof WritingToFileAction)
            action.addObserver(this);
        else
            this.nextController(action);
    }


//      Show a timed dialog box in order to notify the user that the action has been executed.
    @Override
    public void update(Observable o, Object arg) {
        String message = (String) arg;
        
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Rule activated");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.show();
            alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> alert.close());
            delay.play();
        });
        
    }
    
}
