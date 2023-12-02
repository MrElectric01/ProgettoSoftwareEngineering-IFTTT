package progettosoftwareengineering.localifttt;

import progettosoftwareengineering.localifttt.rule.RuleCollection;
import progettosoftwareengineering.localifttt.rule.Rule;
import progettosoftwareengineering.localifttt.rule.trigger.ChainTriggerCreatorsCreator;
import progettosoftwareengineering.localifttt.rule.trigger.TriggerType;
import progettosoftwareengineering.localifttt.rule.trigger.Trigger;
import progettosoftwareengineering.localifttt.rule.action.Action;
import progettosoftwareengineering.localifttt.rule.action.ActionType;
import progettosoftwareengineering.localifttt.rule.action.ChainActionCreatorsCreator;
import java.net.URL;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import progettosoftwareengineering.localifttt.rule.RulesCheckService;

public class GUIController implements Initializable, Observer{

    @FXML
    private VBox homePane;
    @FXML
    private VBox addRulePane;
    @FXML
    private TextField insertRuleName;
    @FXML
    private Button saveButton;
    @FXML
    private TableView<Rule> ruleTable;
    @FXML
    private TableColumn<Rule, String> nameColumn;
    @FXML
    private TableColumn<Rule, Trigger> triggerColumn;  
    @FXML
    private TableColumn<Rule, Action> actionColumn;
        
    private TriggerType selectedTrigger = null;
    private BooleanProperty triggerIsSelected = new SimpleBooleanProperty(false);
    private Map<String, String> trigParam = new HashMap<>();
    
    private ActionType selectedAction = null;
    private BooleanProperty actionIsSelected = new SimpleBooleanProperty(false);
    private Map<String, String> actParam = new HashMap<>();
    
    @FXML
    private VBox timeTriggerPane;
    @FXML
    private Spinner<Integer> hourSpinner;
    @FXML
    private Spinner<Integer> minutesSpinner;
    @FXML
    private VBox messageActionPane;
    @FXML
    private TextField insertMessage;
    @FXML
    private VBox audioActionPane;
    @FXML
    private Label selectedAudioLabel;
    
    private String selectedAudio = "";
    @FXML
    private MenuItem timeTriggerChoice;
    @FXML
    private MenuItem audioActionChoice;
    @FXML
    private MenuItem messageActionChoice;
    @FXML
    private MenuItem switchStatusContextMenuItem;
    @FXML
    private Button switchStatusButton;
    @FXML
    private TableColumn<Rule, String> statusColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        BooleanBinding "false" if all the rule fields are filled (name, triggger and action).
        BooleanBinding ruleFields = Bindings.or(Bindings.or(insertRuleName.textProperty().isEmpty(), triggerIsSelected.not()), actionIsSelected.not());
//        BooleanBinding "false" if one actionType parameters are filled at least.
        BooleanBinding actionFields = Bindings.and(insertMessage.textProperty().isEmpty(), selectedAudioLabel.textProperty().isEmpty());
//        Disable the Save button if the ruleFields OR field of the selected action are empty.
        saveButton.disableProperty().bind(Bindings.or(ruleFields, actionFields));
       
        switchStatusContextMenuItem.disableProperty().bind(ruleTable.getSelectionModel().selectedItemProperty().isNull());
        switchStatusButton.disableProperty().bind(ruleTable.getSelectionModel().selectedItemProperty().isNull());
        ruleTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
//        Connect the table to the Rule fields.
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        triggerColumn.setCellValueFactory(new PropertyValueFactory("trigger"));
        actionColumn.setCellValueFactory(new PropertyValueFactory("action"));
         statusColumn.setCellValueFactory(new PropertyValueFactory("status"));
        ruleTable.setItems(RuleCollection.getInstance().getRules());

//      Observe the RuleCollection to refresh the interface if it changes.
        RuleCollection.getInstance().addObserver(this);
        
    }
    
//    Handle the addRule button action switching the panes.
    @FXML
    private void addRule(ActionEvent event) {
        homePane.setVisible(false);
        addRulePane.setVisible(true);
    }

//    Handle the Cancel button action switching the panes.
    @FXML
    private void handleCancel(ActionEvent event) {
        fromAddToHomePane();
    }

//    Handle the Save button action saving the rule, start the CheckingService and switching the panes.
    @FXML
    private void handleSave(ActionEvent event) {
        putTrigParam();
        Trigger trigger = ChainTriggerCreatorsCreator.chain().createTrigger(selectedTrigger, trigParam);
        putActParam();
        Action action = ChainActionCreatorsCreator.chain().createAction(selectedAction, actParam);
        RuleCollection.getInstance().addRule(new Rule(insertRuleName.getText(), trigger, action));
        RulesCheckService.startChecking();
        fromAddToHomePane();
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
    
//    Handle the pass from the addPane to the homePane,
//    clearing the fields and hidinf the Trigger pane (and Action pane TODO)
    private void fromAddToHomePane() {
        hideAllTriggers();
        hideAllActions();
        insertRuleName.clear();
        addRulePane.setVisible(false);
        homePane.setVisible(true);
    }
    
//    Clear all fields of the possible Action parameters.
    private void clearActionFields() {
        selectedAction = null;
        actionIsSelected.setValue(false);
        insertMessage.clear();
        selectedAudio = "";
        selectedAudioLabel.setText(selectedAudio);
    }
    
//    Clear all fields of the possible Trigger parameters.
    private void clearTriggerFields() {
        selectedTrigger = null;
        triggerIsSelected.setValue(false);
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
    private void hideAllTriggers(){
        clearTriggerFields();
        
        timeTriggerPane.setVisible(false);
        timeTriggerChoice.setDisable(false);
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

//    Handle the "Audio" choice from the "Select Action" menu.
    @FXML
    private void selectAudioAction(ActionEvent event) {
        hideAllActions();
        audioActionChoice.setDisable(true);
        selectedAction = ActionType.AUDIO;
        actionIsSelected.setValue(true);
        audioActionPane.setVisible(true);
    }

    @FXML
    private void handleSwitchStatus(ActionEvent event) {
        List<Rule> rules = ruleTable.getSelectionModel().getSelectedItems();
        for(Rule rule: rules) {
            RuleCollection.getInstance().getRules().get(RuleCollection.getInstance().getRules().indexOf(rule)).switchStatus();
        }
        ruleTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void deselect(MouseEvent event) {
        ruleTable.getSelectionModel().clearSelection();
    }
   
    @Override
    public void update(Observable o, Object arg) {
        ruleTable.refresh(); 
    }
}
