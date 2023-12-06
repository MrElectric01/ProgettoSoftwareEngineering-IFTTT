/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package progettosoftwareengineering.localifttt.controller.guicontroller;

import progettosoftwareengineering.localifttt.model.rule.trigger.*;
import progettosoftwareengineering.localifttt.model.rule.action.*;
import progettosoftwareengineering.localifttt.model.ModelFacade;
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
    private Spinner<Integer> timeTriggerHoursSpinner;
    @FXML
    private Spinner<Integer> timeTriggerMinutesSpinner;
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
    private TextField messageActionInsertMessage;
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
//        Set the ValueFactories of all the Spinners.
        setSpinnerValueFactory(timeTriggerHoursSpinner, 0, 23);
        setSpinnerValueFactory(timeTriggerMinutesSpinner, 0, 59);
        
//        BooleanBinding "false" if all the rule fields are filled (name, triggger and action).
        BooleanBinding ruleFields = Bindings.or(Bindings.or(insertRuleName.textProperty().isEmpty(), triggerIsSelected.not()), actionIsSelected.not());
//        BooleanBinding "false" if one actionType parameters are filled at least.
        BooleanBinding actionFields = Bindings.and(messageActionInsertMessage.textProperty().isEmpty(), selectedAudioLabel.textProperty().isEmpty());
//        Disable the Save button if the ruleFields OR field of the selected action are empty.
        saveButton.disableProperty().bind(Bindings.or(ruleFields, actionFields));
    }

//    Set the Spinner min and max value, and the wrapArounf propwerty in order
//    to make it circular.
    private void setSpinnerValueFactory(Spinner spinner, Integer min, Integer max) {
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max);
        valueFactory.setWrapAround(true);
        spinner.setValueFactory(valueFactory);
    }
    
//    Handle the behavior of the selection of a generic Action/Trigger.
    private void selectTriggerOrAction(MenuItem choice, VBox pane, TriggerType triggerType, ActionType actionType) {
        if(triggerType == null) {
            hideAllActions();
            selectedAction = actionType;
            actionIsSelected.setValue(true);
        } else {
            hideAllTriggers();
            selectedTrigger = triggerType;
            triggerIsSelected.setValue(true);
        }
        choice.setDisable(true);
        pane.setVisible(true);
    }
    
    //    Handle the "Time" choice from the "Select Trigger" menu.
    @FXML
    private void selectTimeTrigger(ActionEvent event) {
        timeTriggerHoursSpinner.getValueFactory().setValue(LocalTime.now().getHour());
        timeTriggerMinutesSpinner.getValueFactory().setValue(LocalTime.now().getMinute());
        selectTriggerOrAction(timeTriggerChoice, timeTriggerPane, TriggerType.TIME, null);
    }

//    Handle the "Audio" choice from the "Select Action" menu.
    @FXML
    private void selectAudioAction(ActionEvent event) {
        selectTriggerOrAction(audioActionChoice, audioActionPane, null, ActionType.AUDIO);
    }

//    Handle the "Message" choice from the "Select Action" menu.
    @FXML
    private void selectMessageAction(ActionEvent event) {
        selectTriggerOrAction(messageActionChoice, messageActionPane, null, ActionType.MESSAGE);
    }
    
//    Hide all the possible Trigger panes and reactivate all the MenuItems.
    private void hideAllTriggers() {
        timeTriggerPane.setVisible(false);
        timeTriggerChoice.setDisable(false);
    }
    
//    Hide all the possible Action panes and reactivate all the MenuItems.
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
        messageActionInsertMessage.clear();
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
        Trigger trigger = ModelFacade.createTrigger(selectedTrigger, trigParam);
        putActParam();
        Action action = ModelFacade.createAction(selectedAction, actParam, ChainActionControllersCreator.chain());
        ModelFacade.addToRuleCollection(insertRuleName.getText(), trigger, action);
        LocalIFTTT.setRoot("src\\main\\resources\\progettosoftwareengineering\\localifttt\\view\\HomeView.fxml");
    }
    
//    Put all the possible value for all the Triggers parameters.
    private void putTrigParam() {
        trigParam.put("timeTriggerHours", timeTriggerHoursSpinner.getValue().toString());
        trigParam.put("timeTriggerMinutes", timeTriggerMinutesSpinner.getValue().toString());
    }
    
//    Put all the possible value for all the Actions parameters.
    private void putActParam() {
        actParam.put("messageActionMessage", messageActionInsertMessage.getText());
        actParam.put("audioActionAudioPath", selectedAudio);
    }
}