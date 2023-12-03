/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package progettosoftwareengineering.localifttt.controller.guicontroller;

import progettosoftwareengineering.localifttt.model.rule.*;
import progettosoftwareengineering.localifttt.model.rule.trigger.*;
import progettosoftwareengineering.localifttt.model.rule.action.*;
import progettosoftwareengineering.localifttt.controller.actioncontroller.ChainActionControllersCreator;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.*;
import javafx.beans.binding.*;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.*;

public class AddRuleController implements Initializable {

    @FXML
    private MenuItem timeTriggerChoice;
    @FXML
    private VBox timeTriggerPane;
    @FXML
    private Spinner<Integer> hourSpinner;
    @FXML
    private Spinner<Integer> minutesSpinner;
    @FXML
    private MenuItem audioActionChoice;
    @FXML
    private MenuItem messageActionChoice;
    @FXML
    private VBox audioActionPane;
    @FXML
    private Label selectedAudioLabel;
    private String selectedAudio = "";
    @FXML
    private VBox messageActionPane;
    @FXML
    private TextField insertMessage;
    @FXML
    private TextField insertRuleName;
    @FXML
    private Button saveButton;
    
//    Reference to the TypeTriggere selected.
    private TriggerType selectedTrigger = null;
//    Boolean to check that a Trigger is selected.
    private BooleanProperty triggerIsSelected = new SimpleBooleanProperty(false);
//    Map that contains all the Trigger parameters.
    private Map<String, String> trigParam = new HashMap<>();
    
//    Reference to the ActionTriggere selected.
    private ActionType selectedAction = null;
//    Boolean to check that a Action is selected.
    private BooleanProperty actionIsSelected = new SimpleBooleanProperty(false);
//    Map that contains all the Action parameters.
    private Map<String, String> actParam = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        BooleanBinding "false" if all the rule fields are filled (name, triggger and action).
        BooleanBinding ruleFields = Bindings.or(Bindings.or(insertRuleName.textProperty().isEmpty(), triggerIsSelected.not()), actionIsSelected.not());
//        BooleanBinding "false" if one actionType parameters are filled at least.
        BooleanBinding actionFields = Bindings.and(insertMessage.textProperty().isEmpty(), selectedAudioLabel.textProperty().isEmpty());
//        Disable the Save button if the ruleFields OR field of the selected action are empty.
        saveButton.disableProperty().bind(Bindings.or(ruleFields, actionFields));
    }    

//    Handle the "Time" choice from the "Select Trigger" menu.
    @FXML
    private void selectTimeTrigger(ActionEvent event) {
        hideAllTriggers();
        timeTriggerChoice.setDisable(true);
        hourSpinner.getValueFactory().setValue(LocalTime.now().getHour());
        minutesSpinner.getValueFactory().setValue(LocalTime.now().getMinute());
        selectedTrigger = TriggerType.TIME;
        triggerIsSelected.setValue(true);
        timeTriggerPane.setVisible(true);
    }
    
//    Hide all the possible Trigger panes.
    private void hideAllTriggers() {
        timeTriggerPane.setVisible(false);
        timeTriggerChoice.setDisable(false);
    }

//    Handle the "Audio" choice from the "Select Action" menu.
    @FXML
    private void selectAudioAction(ActionEvent event) {
        hideAllActions();
        audioActionChoice.setDisable(true);
        selectedAction = ActionType.AUDIO;
        actionIsSelected.setValue(true);
        audioActionPane.setVisible(true);
    }

//    Handle the "Message" choice from the "Select Action" menu.
    @FXML
    private void selectMessageAction(ActionEvent event) {
        hideAllActions();
        messageActionChoice.setDisable(true);
        selectedAction = ActionType.MESSAGE;
        actionIsSelected.setValue(true);
        messageActionPane.setVisible(true);
    }
    
//    Hide all the possible Action panes.
    private void hideAllActions(){
        clearActionFields();
        
        messageActionPane.setVisible(false);
        messageActionChoice.setDisable(false);
        
        audioActionPane.setVisible(false);
        audioActionChoice.setDisable(false);
    }
    
//    Clear all fields of the possible Action parameters.
    private void clearActionFields() {
        selectedAction = null;
        actionIsSelected.setValue(false);
        insertMessage.clear();
        selectedAudio = "";
        selectedAudioLabel.setText(selectedAudio);
    }

//    Handle the audio selection with the system FileChooser, only for the .MP3 and .WAV.
    @FXML
    private void selectAudio(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Audio File");
        
        FileChooser.ExtensionFilter extFilterMp3 = new FileChooser.ExtensionFilter("File MP3 (*.mp3)", "*.mp3");
        FileChooser.ExtensionFilter extFilterWav = new FileChooser.ExtensionFilter("File WAV (*.wav)", "*.wav");
        fileChooser.getExtensionFilters().addAll(extFilterMp3, extFilterWav);

        Stage stage = (Stage) audioActionPane.getScene().getWindow();
        java.io.File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            selectedAudio = selectedFile.getAbsolutePath();
            selectedAudioLabel.setText(selectedFile.getName());
        } else {
            selectedAudio = "";
            selectedAudioLabel.setText("");
        }
    }

//    Handle the Cancel button action switching the views.
    @FXML
    private void handleCancel(ActionEvent event) throws IOException {
        LocalIFTTT.setRoot("src\\main\\resources\\progettosoftwareengineering\\localifttt\\view\\HomeView.fxml");
    }

//    Handle the Save button action saving the rule, start the CheckingService and switching the views.
    @FXML
    private void handleSave(ActionEvent event) throws IOException {
        putTrigParam();
        Trigger trigger = ChainTriggerCreatorsCreator.chain().createTrigger(selectedTrigger, trigParam);
        putActParam();
        Action action = ChainActionCreatorsCreator.chain().createAction(selectedAction, actParam);
        ChainActionControllersCreator.chain().observeAction(action);
        RuleCollection.getInstance().addRule(new Rule(insertRuleName.getText(), trigger, action));
        RulesCheckThread.startChecking();
        LocalIFTTT.setRoot("src\\main\\resources\\progettosoftwareengineering\\localifttt\\view\\HomeView.fxml");
    }
    
//    Put all the possible value for all the Triggers parameters.
    private void putTrigParam() {
        trigParam.put("timeTriggerHour", hourSpinner.getValue().toString());
        trigParam.put("timeTriggerMinutes", minutesSpinner.getValue().toString());
    }
    
//    Put all the possible value for all the Actions parameters.
    private void putActParam() {
        actParam.put("messageActionMessage", insertMessage.getText());
        actParam.put("audioActionAudioPath", selectedAudio);
    }
}