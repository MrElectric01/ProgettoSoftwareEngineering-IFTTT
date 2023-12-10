package progettosoftwareengineering.localifttt.controller.guicontroller;

import progettosoftwareengineering.localifttt.model.rule.trigger.Trigger;
import progettosoftwareengineering.localifttt.model.rule.trigger.TriggerType;
import progettosoftwareengineering.localifttt.model.rule.action.Action;
import progettosoftwareengineering.localifttt.model.rule.action.ActionType;
import progettosoftwareengineering.localifttt.model.ModelFacade;
import progettosoftwareengineering.localifttt.controller.actioncontroller.ChainActionControllersCreator;

import java.io.*;
import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javafx.beans.binding.*;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.converter.IntegerStringConverter;

public class AddRuleController implements Initializable {

/*
*   ===================
*   TRIGGER ELEMENTS:
*   ===================
*/
    
//    Reference to the TypeTriggere selected.
    private TriggerType selectedTrigger = null;
//    Boolean to check that a Trigger is selected.
    private BooleanProperty triggerIsSelected = new SimpleBooleanProperty(false);
//    Map that contains all the Trigger parameters.
    private Map<String, String> trigParam = new HashMap();

//    DateTrigger GUI elements.
    @FXML
    private MenuItem dateTriggerChoice;
    @FXML
    private VBox dateTriggerPane;
    @FXML
    private DatePicker dateTriggerDatePicker;

//    DayOfMonthTrigger GUI elements.
    @FXML
    private MenuItem dayOfMonthTriggerChoice;
    @FXML
    private VBox dayOfMonthTriggerPane;
    @FXML
    private Spinner<Integer> dayOfMonthTriggerSpinner;
    
//    DayOfWeekTrigger GUI elements.
    @FXML
    private MenuItem dayOfWeekTriggerChoice;
    @FXML
    private VBox dayOfWeekTriggerPane;
    @FXML
    private Spinner<String> dayOfWeekTriggerSpinner;

//    FileDimensionTrigger GUI elements.
    @FXML
    private MenuItem fileDimensionTriggerChoice;
    @FXML
    private VBox fileDimensionTriggerPane;
//    Attributes useful to flag in the binds if the FileDimensionTrigger is selected.
    private BooleanProperty isFileDimensionTrigger = new SimpleBooleanProperty();
    @FXML
    private Label fileDimensionTriggerSelectedFileLabel;
//    String useful to contains the absolutePath of the fileSelected.
    private String fileDimensionTriggerSelectedFile = "";
    @FXML
    private HBox thresholdPane;
    @FXML
    private TextField fileDimensionTriggerInsertThreshold;
    @FXML
    private ChoiceBox<String> fileDimensionTriggerSizeUnit;
    
//    FileExistenceTrigger GUI elements.
    @FXML
    private MenuItem fileExistenceTriggerChoice;
    @FXML
    private VBox fileExistenceTriggerPane;
//    Attributes useful to flag in the binds if the FileExistenceTrigger is selected.
    private BooleanProperty isFileExistenceTrigger = new SimpleBooleanProperty();
    @FXML
    private Label fileExistenceTriggerSelectedDirectoryLabel;
//    String useful to contains the absolutePath of the directorySelected.
    private String fileExistenceTriggerSelectedDirectory = "";
    @FXML
    private TextField fileExistenceTriggerInsertFileName;
    @FXML
    private HBox fileNamePane;
    
//    TimeTrigger GUI elements.
    @FXML
    private MenuItem timeTriggerChoice;
    @FXML
    private VBox timeTriggerPane;
    @FXML
    private Spinner<Integer> timeTriggerHoursSpinner;
    @FXML
    private Spinner<Integer> timeTriggerMinutesSpinner;

/*
*   ===================
*   ACTION ELEMENTS:
*   ===================
*/
    
//    Reference to the ActionTriggere selected.
    private ActionType selectedAction = null;
//    Boolean to check that an Action is selected.
    private BooleanProperty actionIsSelected = new SimpleBooleanProperty(false);
//    Map that contains all the Action parameters.
    private Map<String, String> actParam = new HashMap();
        
//    AudioAction GUI elements.
    @FXML
    private MenuItem audioActionChoice;
    @FXML
    private MenuItem messageActionChoice;
    @FXML
    private VBox audioActionPane;
    @FXML
    private Label audioActionSelectedAudioLabel;
//    String useful to contains the absolutePath of the audioSelected.
    private String audioActionSelectedAudio = "";

//    CopyFileAction GUI elements.
    @FXML
    private MenuItem copyFileActionChoice;
    @FXML
    private VBox copyFileActionPane;
    @FXML
    private Label copyFileActionSelectedFileLabel;
//    String useful to contains the absolutePath of the fileSelected.
    private String copyFileActionSelectedFile = "";
    @FXML
    private Label copyFileActionSelectedDirectoryLabel;
//    String useful to contains the absolutePath of the directorySelected.
    private String copyFileActionSelectedDirectory = "";
    
//    DeleteFileAction GUI elements.
    @FXML
    private MenuItem deleteFileActionChoice;
    @FXML
    private VBox deleteFileActionPane;
    @FXML
    private Label deleteFileActionSelectedFileLabel;
//    String useful to contains the absolutePath of the fileSelected.
    private String deleteFileActionSelectedFile = "";
    
//    MessageAction GUI elements.
    @FXML
    private VBox messageActionPane;
    @FXML
    private TextField messageActionInsertMessage;
    @FXML
    private TextField insertRuleName;
    
//    MoveFileAction GUI elements
    @FXML
    private MenuItem moveFileActionChoice;
    @FXML
    private VBox moveFileActionPane;
    @FXML
    private Label moveFileActionSelectedFileLabel;
//    String useful to contains the absolutePath of the fileSelected.
    private String moveFileActionSelectedFile = "";
    @FXML
    private Label moveFileActionSelectedDirectoryLabel;
//    String useful to contains the absolutePath of the directorySelected.
    private String moveFileActionSelectedDirectory = "";
    
//    ProgramExecutionAction GUI elements
    @FXML
    private MenuItem programExecutionActionChoice;
    @FXML
    private VBox programExecutionActionPane;
    @FXML
    private Label programExecutionActionSelectedProgramLabel;
//    String useful to contains the absolutePath of the programSelected.
    private String programExecutionActionSelectedProgram = "";
//    String useful to contains the programInterpreter.
    private String programExecutionActionSelectedInterpreter = null;
    @FXML
    private TextField programExecutionActionInsertArguments;
    @FXML
    private VBox argumentsPane;

//    WritingToFileAction GUI elements
    @FXML
    private MenuItem writingToFileActionChoice;
    @FXML
    private TextField writingToFileActionInsertText;
    @FXML
    private VBox writingToFileActionPane;
    @FXML
    private Label writingToFileActionSelectedFileLabel;
//    String useful to contains the absolutePath of the fileSelected.
    private String writingToFileActionSelectedFile = "";
    
/*
*   ===================
*   RULE ELEMENTS:
*   ===================
*/
    
//    Useful to add the bind for the disableProperty.
    @FXML
    private Button saveButton;
    
//    PeriodicallyRules GUI Elements.
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

/*
*   ===================
*   INITIALIZE SECTION:
*   ===================
*/
    
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
        
//        Set the defaultValue for the dateTriggerDatePicker.
        dateTriggerDatePicker.setValue(LocalDate.now());
        
//        Set the formatter for the fileDimensionTriggerInsertThreshold in order to accept
//        only numbers.
        fileDimensionTriggerInsertThreshold.setTextFormatter(
                new TextFormatter<>(new IntegerStringConverter(), 0, change -> {
                    String newText = change.getControlNewText();
                    if (newText.matches("\\d*")) {
                        return change;
                    }
                    return null;
                })
        );
        
//        Set the choises and the default for the fileDimensionTriggerSizeUnit.
        fileDimensionTriggerSizeUnit.getItems().addAll("B", "KB", "MB", "GB");
        fileDimensionTriggerSizeUnit.setValue("B");
        
//        BooleanBinding "false" if all the rule fields are filled (name, triggger and action).
        BooleanBinding ruleFields = Bindings.or(Bindings.or(
                insertRuleName.textProperty().isEmpty(), 
                triggerIsSelected.not()), 
                actionIsSelected.not()
        );
//        BooleanBinding "false" if one of the fileds of the specified TriggerType are filled.
        BooleanBinding triggerFields = Bindings.or(
//                Disable the SaveButton if the FileDimensionTrigger is selected but there isn't a file selected.
                Bindings.and(fileDimensionTriggerSelectedFileLabel.textProperty().isEmpty(), isFileDimensionTrigger),
//                Disable the SaveButton if the FileExistenceTrigger is selected but there isn't a file selected.
                Bindings.and(fileExistenceTriggerSelectedDirectoryLabel.textProperty().isEmpty(), isFileExistenceTrigger)
        );
        
//        BooleanBinding "false" if one actionType parameters are filled at least.
        BooleanBinding actionFields = Bindings.and(Bindings.and(Bindings.and(Bindings.and(Bindings.and(Bindings.and(
//                Enable the SaveButton if the audio of the AudioAction is selected.
                audioActionSelectedAudioLabel.textProperty().isEmpty(), 
//                Enable the SaveButton if both the file and the directory of the CopyFileAction are selected.
                Bindings.or(copyFileActionSelectedFileLabel.textProperty().isEmpty(), copyFileActionSelectedDirectoryLabel.textProperty().isEmpty())),
//                Enable the SaveButton if the file of the DeleteFileAction is selected.
                deleteFileActionSelectedFileLabel.textProperty().isEmpty()),
//                Enable the SaveButton if the message of the MessageAction is inserted.
                messageActionInsertMessage.textProperty().isEmpty()), 
//                Enable the SaveButton if both the file and the directory of the MoveFileAction are selected.
                Bindings.or(moveFileActionSelectedFileLabel.textProperty().isEmpty(), moveFileActionSelectedDirectoryLabel.textProperty().isEmpty())),
//                Enable the SaveButton if the program of the ProgramExecutionFileAction is selected.
                programExecutionActionSelectedProgramLabel.textProperty().isEmpty()),
//                Enable the SaveButton if both the text and the file of the WritingToFileAction are inserted.
                Bindings.or(writingToFileActionInsertText.textProperty().isEmpty(), writingToFileActionSelectedFileLabel.textProperty().isEmpty())
        );
        
//        Disable the Save button if the ruleFields OR the fields of the selected action or trigger are empty.
        saveButton.disableProperty().bind(Bindings.or(Bindings.or(ruleFields, actionFields), triggerFields));
    }

//    Set the IntegerSpinner min and max value, and the wrapAround property in order to make it circular.
    private void setIntegerSpinnerValueFactory(Spinner<Integer> spinner, Integer min, Integer max) {
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max);
        valueFactory.setWrapAround(true);
        spinner.setValueFactory(valueFactory);
    }
    
/*
*   =================================
*   TRIGGER/ACTION SELECTION SECTION:
*   =================================
*/
   
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
//        Disable the choiced MenuItem in the MenuButton.
        choice.setDisable(true);
//        Show the pane of the selected Trigger/Action from the StackPane.
        pane.setVisible(true);
    }
    
//    TRIGGER:
    
//    Handle the "Date" choice from the "Select Trigger" menu
    @FXML
    private void selectDateTrigger(ActionEvent event) {
        selectTriggerOrAction(dateTriggerChoice, dateTriggerPane, TriggerType.DATE, null);
    }
    
//    Handle the "Day of Month" choice from the "Select Trigger" menu.
    @FXML
    private void selectDayOfMonthTrigger(ActionEvent event) {
//        Set the default value.
        dayOfMonthTriggerSpinner.getValueFactory().setValue(LocalDate.now().getDayOfMonth());
        selectTriggerOrAction(dayOfMonthTriggerChoice, dayOfMonthTriggerPane, TriggerType.DAY_OF_MONTH, null);
    }
    
//    Handle the "Day of Week" choice from the "Select Trigger" menu.
    @FXML
    private void selectDayOfWeekTrigger(ActionEvent event) {
//        Set the default value.
        dayOfWeekTriggerSpinner.getValueFactory().setValue(LocalDate.now().getDayOfWeek().toString());
        selectTriggerOrAction(dayOfWeekTriggerChoice, dayOfWeekTriggerPane, TriggerType.DAY_OF_WEEK, null);
    }
    
//    Handle the "File Dimension" choice from the "Select Trigger" menu
    @FXML
    private void selectFileDimensionTrigger(ActionEvent event) {
//        Disable the treshold insert if there isn't a file selected.
        thresholdPane.disableProperty().bind(fileDimensionTriggerSelectedFileLabel.textProperty().isEmpty());
        selectTriggerOrAction(fileDimensionTriggerChoice, fileDimensionTriggerPane, TriggerType.FILE_DIMENSION, null);
        isFileDimensionTrigger.set(selectedTrigger == TriggerType.FILE_DIMENSION);
    }
    
//    Handle the "File Existence" choice from the "Select Trigger" menu
    @FXML
    private void selectFileExistenceTrigger(ActionEvent event) {
//        Disable the file name insert if there isn't a directory selected.
        fileNamePane.disableProperty().bind(fileExistenceTriggerSelectedDirectoryLabel.textProperty().isEmpty());
        selectTriggerOrAction(fileExistenceTriggerChoice, fileExistenceTriggerPane, TriggerType.FILE_EXISTENCE, null);
        isFileExistenceTrigger.set(selectedTrigger == TriggerType.FILE_EXISTENCE);
    }
    
//    Handle the "Time" choice from the "Select Trigger" menu.
    @FXML
    private void selectTimeTrigger(ActionEvent event) {
//        Set the default values.
        timeTriggerHoursSpinner.getValueFactory().setValue(LocalTime.now().getHour());
        timeTriggerMinutesSpinner.getValueFactory().setValue(LocalTime.now().getMinute());
        selectTriggerOrAction(timeTriggerChoice, timeTriggerPane, TriggerType.TIME, null);
    }
    
//    ACTION:

//    Handle the "Audio" choice from the "Select Action" menu.
    @FXML
    private void selectAudioAction(ActionEvent event) {
        selectTriggerOrAction(audioActionChoice, audioActionPane, null, ActionType.AUDIO);
    }
    
//    Handle the "Copy File" choice from the "Select Action" menu.
    @FXML
    private void selectCopyFileAction(ActionEvent event) {
        selectTriggerOrAction(copyFileActionChoice, copyFileActionPane, null, ActionType.COPY_FILE);
    }
    
//    Handle the "Delete File" choice from the "Select Action" menu.
    @FXML
    private void selectDeleteFileAction(ActionEvent event) {
        selectTriggerOrAction(deleteFileActionChoice, deleteFileActionPane, null, ActionType.DELETE_FILE);
    }
    
//    Handle the "Message" choice from the "Select Action" menu.
    @FXML
    private void selectMessageAction(ActionEvent event) {
        selectTriggerOrAction(messageActionChoice, messageActionPane, null, ActionType.MESSAGE);
    }
    
//    Handle the "Move File" choice from the "Select Action" menu.
    @FXML
    private void selectMoveFileAction(ActionEvent event) {
        selectTriggerOrAction(moveFileActionChoice, moveFileActionPane, null, ActionType.MOVE_FILE);
    }
    
//    Handle the "Program Execution" choice from the "Select Action" menu.
    @FXML
    private void selectProgramExecutionAction(ActionEvent event) {
        argumentsPane.disableProperty().bind(programExecutionActionSelectedProgramLabel.textProperty().isEmpty());
        selectTriggerOrAction(programExecutionActionChoice, programExecutionActionPane, null, ActionType.PROGRAM_EXECUTION);
    }
    
//    Handle the "Writing to File" choice from the "Select Action" menu.
    @FXML
    private void selectWritingToFIleAction(ActionEvent event) {
        selectTriggerOrAction(writingToFileActionChoice, writingToFileActionPane, null, ActionType.WRITING_TO_FILE);
    }
    
/*
*   ==============
*   HIDE SECTION:
*   ==============
*/
//    TRIGGER:
    
//    Hide a single Trigger panes and reactivate his MenuItems.
    private void hideTrigger(VBox pane, MenuItem choice) {
        pane.setVisible(false);
        choice.setDisable(false);
    }
    
//    Hide all the Trigger panes and reactivate all the MenuItems and clear all fields.
    private void hideAllTriggers() {
        clearTriggerFields();
        
        hideTrigger(timeTriggerPane, timeTriggerChoice);
        hideTrigger(dayOfWeekTriggerPane, dayOfWeekTriggerChoice);
        hideTrigger(dayOfMonthTriggerPane, dayOfMonthTriggerChoice);
        hideTrigger(dateTriggerPane, dateTriggerChoice);
        hideTrigger(fileExistenceTriggerPane, fileExistenceTriggerChoice);
        hideTrigger(fileDimensionTriggerPane, fileDimensionTriggerChoice);
    }
    
//    Clear all fields of the Trigger parameters.
    private void clearTriggerFields() {
        selectedTrigger = null;
        triggerIsSelected.setValue(false);
        
//        FileDimensionTrigger reset.
        fileDimensionTriggerSelectedFile = ""; 
        fileDimensionTriggerSelectedFileLabel.setText(fileDimensionTriggerSelectedFile);
        fileDimensionTriggerInsertThreshold.setText("0");
        fileDimensionTriggerSizeUnit.setValue("B");
        isFileDimensionTrigger.set(false);
        
//        FileExistenceTrigger reset.
        fileExistenceTriggerSelectedDirectory = ""; 
        fileExistenceTriggerSelectedDirectoryLabel.setText(fileExistenceTriggerSelectedDirectory);
        fileExistenceTriggerInsertFileName.clear();
        isFileExistenceTrigger.set(false);
    }
    
//    Action:
    
//    Hide a single Action panes and reactivate his MenuItems.
    private void hideAction(VBox pane, MenuItem choice) {
        pane.setVisible(false);
        choice.setDisable(false);
    }
    
//    Hide all the Action panes, reactivate all the MenuItems and clear all fields.
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
    
//    Clear all fields of the Action parameters.
    private void clearActionFields() {
        selectedAction = null;
        actionIsSelected.setValue(false);
        
//        AudioAction reset.
        audioActionSelectedAudio = "";
        audioActionSelectedAudioLabel.setText(audioActionSelectedAudio);
        
//        CopyFileAction reset.
        copyFileActionSelectedFile = ""; 
        copyFileActionSelectedFileLabel.setText(copyFileActionSelectedFile);
        copyFileActionSelectedDirectory = ""; 
        copyFileActionSelectedDirectoryLabel.setText(copyFileActionSelectedDirectory);
        
//        DeleteFileAction reset.
        deleteFileActionSelectedFile = ""; 
        deleteFileActionSelectedFileLabel.setText(deleteFileActionSelectedFile);
        
//        MessageAction reset.
        messageActionInsertMessage.clear();
        
//        MoveFileAction reset.
        moveFileActionSelectedFile = ""; 
        moveFileActionSelectedFileLabel.setText(moveFileActionSelectedFile);
        moveFileActionSelectedDirectory = ""; 
        moveFileActionSelectedDirectoryLabel.setText(moveFileActionSelectedDirectory);
        
//        ProgramExecutionAction reset.
        programExecutionActionSelectedProgram = ""; 
        programExecutionActionSelectedProgramLabel.setText(programExecutionActionSelectedProgram);
        programExecutionActionInsertArguments.clear();
        programExecutionActionSelectedInterpreter = null;
        
//        WritingToFileAction reset.
        writingToFileActionInsertText.clear();
        writingToFileActionSelectedFile = ""; 
        writingToFileActionSelectedFileLabel.setText(writingToFileActionSelectedFile);
    }
    
/*
*   =================================
*   FILE/DIRECTORY SELECTION SECTION:
*   =================================
*/
    
//    Handle the generic opening of the file chooser with passed filters.
    private String selectFile(String title, ExtensionFilter filter, Label selectedFileLabel) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        
        if(filter != null)
            fileChooser.getExtensionFilters().add(filter);

//        We need an elements of the window to get the Stage.
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
        
//        We need an elements of the window to get the Stage.
        Stage stage = (Stage) saveButton.getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(stage);
        
        if(selectedDirectory == null)
            return "";
        else {
        selectedDirectoryLabel.setText(selectedDirectory.getName());
        return selectedDirectory.getAbsolutePath();
        }
    }
    
//    TRIGGER:
    
//    Handle the file selection for the FileDimensionTrigger file.
    @FXML
    private void fileDimensionTriggerSelectFile(ActionEvent event) {
        fileDimensionTriggerSelectedFile = selectFile("Select File to check Dimension", null, fileDimensionTriggerSelectedFileLabel);
    }
    
//    Handle the file selection for the FileExistenceTrigger file.
    @FXML
    private void fileExistenceTriggerSelectDirectory(ActionEvent event) {
        fileExistenceTriggerSelectedDirectory = selectDirectory("Select a Directory", fileExistenceTriggerSelectedDirectoryLabel);
    }

//    ACTION:

//    Handle the audio selection for the AudioAction audio, only for the .MP3 and .WAV.
    @FXML
    private void audioActionSelectAudio(ActionEvent event) {
        ExtensionFilter filter = new ExtensionFilter("File Audio (*.mp3, *.wav)", "*.mp3", "*.wav");
        audioActionSelectedAudio = selectFile("Select Audio File", filter, audioActionSelectedAudioLabel);
    }
    
//    Handle the file selection for the CopyFileAction file.
    @FXML
    private void copyFileActionSelectFile(ActionEvent event) {
        copyFileActionSelectedFile = selectFile("Select File to Copy", null, copyFileActionSelectedFileLabel);
    }

//    Handle the directory selection for the CopyFileAction directory.
    @FXML
    private void copyFileActionSelectDirectory(ActionEvent event) {
        copyFileActionSelectedDirectory = selectDirectory("Select a Directory", copyFileActionSelectedDirectoryLabel);
    }
    
//    Handle the file selection for the DeleteFileAction file.
    @FXML
    private void deleteFileActionSelectFile(ActionEvent event) {
        deleteFileActionSelectedFile = selectFile("Select File to Delete", null, deleteFileActionSelectedFileLabel);
    }
    
//    Handle the file selection for the MoveFileAction file.
    @FXML
    private void moveFileActionSelectFile(ActionEvent event) {
        moveFileActionSelectedFile = selectFile("Select File to Move", null, moveFileActionSelectedFileLabel);
    }

//    Handle the directory selection for the MoveFileAction directory.
    @FXML
    private void moveFileActionSelectDirectory(ActionEvent event) {
        moveFileActionSelectedDirectory = selectDirectory("Select a Directory", moveFileActionSelectedDirectoryLabel);
    }

//    Handle the executable selection for the ProgramExecutionAction executable, only for the .EXE and .JAR.
    @FXML
    private void programExecutionActionSelectProgram(ActionEvent event) {
        ExtensionFilter filter = new ExtensionFilter("Executable Files (*.exe, *.jar)", "*.exe", "*.jar");
        programExecutionActionSelectedProgram = selectFile("Select an Executable", filter, programExecutionActionSelectedProgramLabel);
//        In order to generalize the ProgramExecutionAction, we obtain the interpreter here.
        if(programExecutionActionSelectedProgram.split("\\.")[1].equals("jar"))
            programExecutionActionSelectedInterpreter = "java -jar";
    }
    
//    Handle the file selection for the WritingToFileAction file, only for the .TXT.
    @FXML
    private void writingToFileActionSelectFile(ActionEvent event) {
        ExtensionFilter filter = new ExtensionFilter("Text files (*.txt)", "*.txt");
        writingToFileActionSelectedFile = selectFile("Select File to write", filter, writingToFileActionSelectedFileLabel);
    }
    
/*
*   =================================
*   PERIODICALLY RULE SECTION:
*   =================================
*/
    
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
    
/*
*   =================================
*   SAVE/CANCEL SECTION:
*   =================================
*/

//    Handle the Save button action saving the rule and switching the views.
    @FXML
    private void handleSave(ActionEvent event) throws IOException {
        putTrigParam();
        Trigger trigger = ModelFacade.createTrigger(selectedTrigger, trigParam);
        putActParam();
        Action action = ModelFacade.createAction(selectedAction, actParam, ChainActionControllersCreator.chain());
        ModelFacade.addToRuleCollection(insertRuleName.getText(), trigger, action, periodicallyCheckBox.isSelected(), periodicallyDaysSpinner.getValue(), periodicallyHoursSpinner.getValue(), periodicallyMinutesSpinner.getValue());
        LocalIFTTT.setRoot("src/main/resources/progettosoftwareengineering/localifttt/view/HomeView.fxml");
    }
    
//    Put all the possible value for all the Triggers parameters, in order
//    to be indipendent from which TriggerType is selected.
    private void putTrigParam() {
        trigParam.put("timeTriggerHours", timeTriggerHoursSpinner.getValue().toString());
        trigParam.put("timeTriggerMinutes", timeTriggerMinutesSpinner.getValue().toString());
        
        trigParam.put("dayOfWeekTriggerDayOfWeek", dayOfWeekTriggerSpinner.getValue());
        
        trigParam.put("dayOfMonthTriggerDayOfMonth", dayOfMonthTriggerSpinner.getValue().toString());
        
//        We format because the DatePicker use the client system formatting, but we want the general "yyyy-MM-dd".
        trigParam.put("dateTriggerDate", dateTriggerDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        
        trigParam.put("fileExistenceTriggerFilePath", fileExistenceTriggerSelectedDirectory + "/" + fileExistenceTriggerInsertFileName.getText());
        
        trigParam.put("fileDimensionTriggerFilePath", fileDimensionTriggerSelectedFile);
        trigParam.put("fileDimensionTriggerSizeThreshold", fileDimensionTriggerInsertThreshold.getText());
        trigParam.put("fileDimensionTriggerSizeUnit", fileDimensionTriggerSizeUnit.getValue());
    }
    
//    Put all the possible value for all the Actions parameters, in order
//    to be indipendent from which ActionType is selected.
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
    
//    Handle the Cancel button action switching the views.
    @FXML
    private void handleCancel(ActionEvent event) throws IOException {
        LocalIFTTT.setRoot("src/main/resources/progettosoftwareengineering/localifttt/view/HomeView.fxml");
    }
}