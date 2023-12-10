package progettosoftwareengineering.localifttt.model.rule.action.message;

import progettosoftwareengineering.localifttt.model.rule.action.Action;

//Action for the dialog box message.
public class MessageAction extends Action {
    
    private String message;

    public MessageAction(String message) {
        this.message = message;
    }
    
    @Override
    public void doAction() {
        setChanged();
        notifyObservers(message);
    }

    @Override
    public String toString() {
        return "Message: " + message;
    }
}
