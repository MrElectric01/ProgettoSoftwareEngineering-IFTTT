/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
