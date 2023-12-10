package progettosoftwareengineering.localifttt.controller.guicontroller;

import progettosoftwareengineering.localifttt.model.rule.trigger.*;
import progettosoftwareengineering.localifttt.model.rule.action.*;
import progettosoftwareengineering.localifttt.model.ModelFacade;
import progettosoftwareengineering.localifttt.controller.actioncontroller.ChainActionControllersCreator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import javafx.beans.binding.*;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.stage.FileChooser.ExtensionFilter;

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
    private Label audioActionSelectedAudioLabel;
    private String audioActionSelectedAudio = "";
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
    private Map<String, String> trigParam = new HashMap();
    
//    Reference to the ActionTriggere selected.
    private ActionType selectedAction = null;
//    Boolean to check that a Action is selected.
    private BooleanProperty actionIsSelected = new SimpleBooleanProperty(false);
//    Map that contains all the Action parameters.
    private Map<String, String> actParam = new HashMap();
    
    @FXML
    private CheckBox onlyOnceCheckbox;
    @FXML
    private CheckBox periodicallyCheckBox;
    @FXML
    private HBox periodicallyParameters;
    @FXML
    private Spinner<Integer> periodicallyDaysSpinner;
    @FXML
    private Spinner<Integer> periodicallyHoursSpinner;
    @FXML
    private Spinner<Integer> periodicallyMinutesSpinner;
    @FXML
    private MenuItem writingToFileActionChoice;
    @FXML
    private TextField writingToFileActionInsertText;
    @FXML
    private VBox writingToFileActionPane;
    @FXML
    private Label writingToFileActionSelectedFileLabel;
    private String writingToFileActionSelectedFile = "";
    @FXML
    private MenuItem moveFileActionChoice;
    @FXML
    private VBox moveFileActionPane;
    @FXML
    private Label moveFileActionSelectedFileLabel;
    private String moveFileActionSelectedFile = "";
    @FXML
    private Label moveFileActionSelectedDirectoryLabel;
    private String moveFileActionSelectedDirectory = "";
    @FXML
    private MenuItem copyFileActionChoice;
    @FXML
    private VBox copyFileActionPane;
    @FXML
    private Label copyFileActionSelectedFileLabel;
    private String copyFileActionSelectedFile = "";
    @FXML
    private Label copyFileActionSelectedDirectoryLabel;
    private String copyFileActionSelectedDirectory = "";
    @FXML
    private MenuItem deleteFileActionChoice;
    @FXML
    private VBox deleteFileActionPane;
    @FXML
    private Label deleteFileActionSelectedFileLabel;
    private String deleteFileActionSelectedFile = "";
    @FXML
    private MenuItem programExecutionActionChoice;
    @FXML
    private VBox programExecutionActionPane;
    @FXML
    private Label programExecutionActionSelectedProgramLabel;
    private String programExecutionActionSelectedProgram = "";
    private String programExecutionActionSelectedInterpreter = null;
    @FXML
    private TextField programExecutionActionInsertArguments;
    @FXML
    private VBox argumentsPane;
    @FXML
    private MenuItem dayOfWeekTriggerChoice;
    @FXML
    private VBox dayOfWeekTriggerPane;
    @FXML
    private Spinner<String> dayOfWeekTriggerSpinner;
    @FXML
    private MenuItem dayOfMonthTriggerChoice;
    @FXML
    private VBox dayOfMonthTriggerPane;
    @FXML
    private Spinner<Integer> dayOfMonthTriggerSpinner;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        Set the ValueFactories of all the Integer Spinners.
        setIntegerSpinnerValueFactory(timeTriggerHoursSpinner, 0, 23);
        setIntegerSpinnerValueFactory(timeTriggerMinutesSpinner, 0, 59);
        setIntegerSpinnerValueFactory(periodicallyDaysSpinner, 0, 7);
        setIntegerSpinnerValueFactory(periodicallyHoursSpinner, 0, 23);
        setIntegerSpinnerValueFactory(periodicallyMinutesSpinner, 0, 59);
        setIntegerSpinnerValueFactory(dayOfMonthTriggerSpinner, 1, 31);
        
//        Set the ValueFactory for the DayOfWeekSpinner.
        SpinnerValueFactory<String> dayOfWeekFactory = new SpinnerValueFactory<String>() {
            @Override
            public void increment(int steps) {
                DayOfWeek currentDay = DayOfWeek.valueOf(getValue());
                setValue(currentDay.plus(steps).toString());
            }

            @Override
            public void decrement(int steps) {
                DayOfWeek currentDay = DayOfWeek.valueOf(getValue());
                setValue(currentDay.minus(steps).toString());
            }
        };
        dayOfWeekFactory.setWrapAround(true);
        dayOfWeekTriggerSpinner.setValueFactory(dayOfWeekFactory);
        
//        BooleanBinding "false" if all the rule fields are filled (name, triggger and action).
        BooleanBinding ruleFields = Bindings.or(Bindings.or(
                insertRuleName.textProperty().isEmpty(), 
                triggerIsSelected.not()), 
                actionIsSelected.not()
        );
//        BooleanBinding "false" if one actionType parameters are filled at least.
        BooleanBinding actionFields = Bindings.and(Bindings.and(Bindings.and(Bindings.and(Bindings.and(Bindings.and(
                messageActionInsertMessage.textProperty().isEmpty(), 
                audioActionSelectedAudioLabel.textProperty().isEmpty()), 
                Bindings.or(writingToFileActionInsertText.textProperty().isEmpty(), writingToFileActionSelectedFileLabel.textProperty().isEmpty())),
                Bindings.or(moveFileActionSelectedFileLabel.textProperty().isEmpty(), moveFileActionSelectedDirectoryLabel.textProperty().isEmpty())),
                Bindings.or(copyFileActionSelectedFileLabel.textProperty().isEmpty(), copyFileActionSelectedDirectoryLabel.textProperty().isEmpty())),
                deleteFileActionSelectedFileLabel.textProperty().isEmpty()),
                programExecutionActionSelectedProgramLabel.textProperty().isEmpty()
        );
//        Disable the Save button if the ruleFields OR field of the selected action are empty.
        saveButton.disableProperty().bind(Bindings.or(ruleFields, actionFields));
    }

//    Set the Spinner min and max value, and the wrapArounf propwerty in order
//    to make it circular.
    private void setIntegerSpinnerValueFactory(Spinner<Integer> spinner, Integer min, Integer max) {
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
    
//    Handle the "Day of Week" choice from the "Select Trigger" menu.
    @FXML
    private void selectDayOfWeekTrigger(ActionEvent event) {
        dayOfWeekTriggerSpinner.getValueFactory().setValue(LocalDate.now().getDayOfWeek().toString());
        selectTriggerOrAction(dayOfWeekTriggerChoice, dayOfWeekTriggerPane, TriggerType.DAY_OF_WEEK, null);
    }
    
//    Handle the "Day of Month" choice from the "Select Trigger" menu.
    @FXML
    private void selectDayOfMonthTrigger(ActionEvent event) {
        dayOfMonthTriggerSpinner.getValueFactory().setValue(LocalDate.now().getDayOfMonth());
        selectTriggerOrAction(dayOfMonthTriggerChoice, dayOfMonthTriggerPane, TriggerType.DAY_OF_MONTH, null);
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
    
//    Handle the "Writing to File" choice from the "Select Action" menu.
    @FXML
    private void selectWritingToFIleAction(ActionEvent event) {
        selectTriggerOrAction(writingToFileActionChoice, writingToFileActionPane, null, ActionType.WRITING_TO_FILE);
    }
    
//    Handle the "Move File" choice from the "Select Action" menu.
    @FXML
    private void selectMoveFileAction(ActionEvent event) {
        selectTriggerOrAction(moveFileActionChoice, moveFileActionPane, null, ActionType.MOVE_FILE);
    }
    
//    Handle the "Copy File" choice from the "Select Action" menu.
    @FXML
    private void selectCopyFileAction(ActionEvent event) {
        selectTriggerOrAction(copyFileActionChoice, copyFileActionPane, null, ActionType.COPY_FILE);
    }

//    Handle the "Copy File" choice from the "Select Action" menu.
    @FXML
    private void selectDeleteFileAction(ActionEvent event) {
        selectTriggerOrAction(deleteFileActionChoice, deleteFileActionPane, null, ActionType.DELETE_FILE);
    }
    
//    Handle the "Program Execution" choice from the "Select Action" menu.
    @FXML
    private void selectProgramExecutionAction(ActionEvent event) {
        selectTriggerOrAction(programExecutionActionChoice, programExecutionActionPane, null, ActionType.PROGRAMEX_ECUTION);
        argumentsPane.disableProperty().bind(programExecutionActionSelectedProgramLabel.textProperty().isEmpty());
    }
    
//    Hide a single Trigger panes and reactivate his MenuItems.
    private void hideTrigger(VBox pane, MenuItem choice) {
        pane.setVisible(false);
        choice.setDisable(false);
    }
    
//    Hide all the possible Trigger panes and reactivate all the MenuItems.
    private void hideAllTriggers() {
        hideTrigger(timeTriggerPane, timeTriggerChoice);
        hideTrigger(dayOfWeekTriggerPane, dayOfWeekTriggerChoice);
        hideTrigger(dayOfMonthTriggerPane, dayOfMonthTriggerChoice);
    }
    
//    Hide a single Action panes and reactivate his MenuItems.
    private void hideAction(VBox pane, MenuItem choice) {
        pane.setVisible(false);
        choice.setDisable(false);
    }
    
//    Hide all the possible Action panes, reactivate all the MenuItems and clear all fields.
    private void hideAllActions() {
        clearActionFields();
        
        hideAction(messageActionPane, messageActionChoice);
        hideAction(audioActionPane, audioActionChoice);
        hideAction(writingToFileActionPane, writingToFileActionChoice);
        hideAction(moveFileActionPane, moveFileActionChoice);
        hideAction(copyFileActionPane, copyFileActionChoice);
        hideAction(deleteFileActionPane, deleteFileActionChoice);
        hideAction(programExecutionActionPane, programExecutionActionChoice);
    }
    
//    Clear all fields of the possible Action parameters.
    private void clearActionFields() {
        selectedAction = null;
        actionIsSelected.setValue(false);
        
        messageActionInsertMessage.clear();
        
        audioActionSelectedAudio = "";
        audioActionSelectedAudioLabel.setText(audioActionSelectedAudio);
        
        writingToFileActionInsertText.clear();
        writingToFileActionSelectedFile = ""; 
        writingToFileActionSelectedFileLabel.setText(writingToFileActionSelectedFile);
        
        moveFileActionSelectedFile = ""; 
        moveFileActionSelectedFileLabel.setText(moveFileActionSelectedFile);
        moveFileActionSelectedDirectory = ""; 
        moveFileActionSelectedDirectoryLabel.setText(moveFileActionSelectedDirectory);
        
        copyFileActionSelectedFile = ""; 
        copyFileActionSelectedFileLabel.setText(copyFileActionSelectedFile);
        copyFileActionSelectedDirectory = ""; 
        copyFileActionSelectedDirectoryLabel.setText(copyFileActionSelectedDirectory);
        
        deleteFileActionSelectedFile = ""; 
        deleteFileActionSelectedFileLabel.setText(deleteFileActionSelectedFile);
        
        programExecutionActionSelectedProgram = ""; 
        programExecutionActionSelectedProgramLabel.setText(programExecutionActionSelectedProgram);
        programExecutionActionInsertArguments.clear();
        programExecutionActionSelectedInterpreter = null;
    }

//    Handle the audio selection with the system FileChooser, only for the .MP3 and .WAV.
    @FXML
    private void audioActionSelectAudio(ActionEvent event) {
        ExtensionFilter filter = new ExtensionFilter("File Audio (*.mp3, *.wav)", "*.mp3", "*.wav");
        audioActionSelectedAudio = selectFile("Select Audio File", filter, audioActionSelectedAudioLabel);
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
        ModelFacade.addToRuleCollection(insertRuleName.getText(), trigger, action, periodicallyCheckBox.isSelected(), periodicallyDaysSpinner.getValue(), periodicallyHoursSpinner.getValue(), periodicallyMinutesSpinner.getValue());
        LocalIFTTT.setRoot("src\\main\\resources\\progettosoftwareengineering\\localifttt\\view\\HomeView.fxml");
    }
    
//    Put all the possible value for all the Triggers parameters.
    private void putTrigParam() {
        trigParam.put("timeTriggerHours", timeTriggerHoursSpinner.getValue().toString());
        trigParam.put("timeTriggerMinutes", timeTriggerMinutesSpinner.getValue().toString());
        
        trigParam.put("dayOfWeekTriggerDayOfWeek", dayOfWeekTriggerSpinner.getValue());
        
        trigParam.put("dayOfMonthTriggerDayOfMonth", dayOfMonthTriggerSpinner.getValue().toString());
    }
    
//    Put all the possible value for all the Actions parameters.
    private void putActParam() {
        actParam.put("messageActionMessage", messageActionInsertMessage.getText());
        
        actParam.put("audioActionAudioPath", audioActionSelectedAudio);
        
        actParam.put("writingToFileActionTextToAppend", writingToFileActionInsertText.getText());
        actParam.put("writingToFileActionFilePath", writingToFileActionSelectedFile);
        
        actParam.put("moveFileActionFilePath", moveFileActionSelectedFile);
        actParam.put("moveFileActionDirectoryPath", moveFileActionSelectedDirectory);
        
        actParam.put("copyFileActionFilePath", copyFileActionSelectedFile);
        actParam.put("copyFileActionDirectoryPath", copyFileActionSelectedDirectory);
        
        actParam.put("deleteFileActionFilePath", deleteFileActionSelectedFile);
        
        actParam.put("programExecutionActionInterpreter", programExecutionActionSelectedInterpreter);
        actParam.put("programExecutionActionProgramPath", programExecutionActionSelectedProgram);
        actParam.put("programExecutionActionProgramArguments", programExecutionActionInsertArguments.getText());
    }

//    Handle the OnlyOnce Checkbox selection.
    @FXML
    private void handleOnlyOnce(ActionEvent event) {
        periodicallyCheckBox.setSelected(false);
        periodicallyParameters.setDisable(true);
    }

//    Handle the Periodically Checkbox selection.
    @FXML
    private void handlePeriodically(ActionEvent event) {
        onlyOnceCheckbox.setSelected(false);
        periodicallyParameters.setDisable(false);
    }

//    Handle the file selection with the system FileChooser, only for the .txt.
    @FXML
    private void writingToFileActionSelectFile(ActionEvent event) {
        ExtensionFilter filter = new ExtensionFilter("Text files (*.txt)", "*.txt");
        writingToFileActionSelectedFile = selectFile("Select File to write", filter, writingToFileActionSelectedFileLabel);
    }

//    Handle the generic opening of the file chooser with passed filters.
    private String selectFile(String title, ExtensionFilter filter, Label selectedFileLabel) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        
        if(filter != null)
            fileChooser.getExtensionFilters().add(filter);

        Stage stage = (Stage) saveButton.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        
        if(selectedFile == null)
            return "";
        else {
            selectedFileLabel.setText(selectedFile.getName());
            return selectedFile.getAbsolutePath();
        }
    }
    
    //    Handle the generic opening of the directory chooser.
    private String selectDirectory(String title, Label selectedDirectoryLabel) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(title);
        
        Stage stage = (Stage) saveButton.getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(stage);
        
        if(selectedDirectory == null)
            return "";
        else {
        selectedDirectoryLabel.setText(selectedDirectory.getName());
        return selectedDirectory.getAbsolutePath();
        }
    }

//    Handle the file selection with the system FileChooser.
    @FXML
    private void moveFileActionSelectFile(ActionEvent event) {
        moveFileActionSelectedFile = selectFile("Select File to Move", null, moveFileActionSelectedFileLabel);
    }

//    Handle the directory selection with the system DirectoryChooser.
    @FXML
    private void moveFileActionSelectDirectory(ActionEvent event) {
        moveFileActionSelectedDirectory = selectDirectory("Select a Directory", moveFileActionSelectedDirectoryLabel);
    }

//    Handle the file selection with the system FileChooser
    @FXML
    private void copyFileActionSelectFile(ActionEvent event) {
        copyFileActionSelectedFile = selectFile("Select File to Copy", null, copyFileActionSelectedFileLabel);
    }

//    Handle the directory selection with the system DirectoryChooser.
    @FXML
    private void copyFileActionSelectDirectory(ActionEvent event) {
        copyFileActionSelectedDirectory = selectDirectory("Select a Directory", copyFileActionSelectedDirectoryLabel);
    }

//    Handle the file selection with the system FileChooser
    @FXML
    private void deleteFileActionSelectFile(ActionEvent event) {
        deleteFileActionSelectedFile = selectFile("Select File to Delete", null, deleteFileActionSelectedFileLabel);
    }

//    Handle the executable selection with the system FileChooser
    @FXML
    private void programExecutionActionSelectProgram(ActionEvent event) {
        ExtensionFilter filter = new ExtensionFilter("Executable Files (*.exe, *.jar)", "*.exe", "*.jar");
        programExecutionActionSelectedProgram = selectFile("Select an Executable", filter, programExecutionActionSelectedProgramLabel);
//        In order to generalize the ProgramExecutionAction, we obtain the interpreter here.
        if(programExecutionActionSelectedProgram.split("\\.")[1].equals("jar"))
            programExecutionActionSelectedInterpreter = "java -jar";
    }
}