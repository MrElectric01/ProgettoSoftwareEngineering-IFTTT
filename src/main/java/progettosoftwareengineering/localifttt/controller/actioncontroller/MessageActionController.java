/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package progettosoftwareengineering.localifttt.controller.actioncontroller;

import java.util.Observable;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import progettosoftwareengineering.localifttt.model.rule.action.Action;
import progettosoftwareengineering.localifttt.model.rule.action.MessageAction;

public class MessageActionController  extends BaseActionController {
    @Override
    public void observeAction(Action action) {
        if(action instanceof MessageAction)
            action.addObserver(this);
        else
            this.nextController(action);
    }
    
//    Execute a MessageAction with the message in the parameter.
    @Override
    public void update(Observable o, Object arg) {
        String message = (String) arg;
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Rule activated");
            alert.setHeaderText(null); 
            alert.setContentText(message);
            alert.show();
        });
    }
}
