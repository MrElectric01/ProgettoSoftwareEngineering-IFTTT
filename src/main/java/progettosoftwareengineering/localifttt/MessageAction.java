/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

//Action for the dialog box message.
public class MessageAction implements Action {
    
    private String message;

    public MessageAction(String message) {
        this.message = message;
    }
    
    @Override
    public void doAction() {
        showAlert();
    }
    
    private void showAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Rule activated");
        alert.setHeaderText(null); 
        alert.setContentText(message);
        alert.show();
    }

    @Override
    public String toString() {
        return "Message: " + message;
    }
}
