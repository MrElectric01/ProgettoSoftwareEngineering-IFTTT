package progettosoftwareengineering.localifttt;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class GUIController implements Initializable{

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Disable the Save button if the ruleName TextField is empty OR no Trigger is selected.
        saveButton.disableProperty().bind(Bindings.or(Bindings.or(insertRuleName.textProperty().isEmpty(), triggerIsSelected.not()), actionIsSelected.not()));
        
//        Connect the table to the Rule fields.
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        triggerColumn.setCellValueFactory(new PropertyValueFactory("trigger"));
        actionColumn.setCellValueFactory(new PropertyValueFactory("action"));
        ruleTable.setItems(RuleCollection.getInstance().getRules());
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

//    Handle the Save button action saving the rule and switching the panes.
    @FXML
    private void handleSave(ActionEvent event) {
        putTrigParam();
        Trigger trigger = ChainTriggerCreatorsCreator.chain().createTrigger(selectedTrigger, trigParam);
        putActParam();
        Action action = ChainActionCreatorsCreator.chain().createAction(selectedAction, actParam);
        RuleCollection.getInstance().addRule(new Rule(insertRuleName.getText(), trigger, action));
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
    }
    
//    Handle the pass from the addPane to the homePane,
//    clearing the fields and hidinf the Trigger pane (and Action pane TODO)
    private void fromAddToHomePane() {
        clearAllFields();
        hideAllTriggers();
        hideAllActions();
        addRulePane.setVisible(false);
        homePane.setVisible(true);
    }

//    Clear all fiels of the possible parameters of every pane.
    private void clearAllFields() {
        insertRuleName.clear();
        selectedTrigger = null;
        triggerIsSelected.setValue(false);
        actionIsSelected.setValue(false);
        hourSpinner.getValueFactory().setValue(0);
        minutesSpinner.getValueFactory().setValue(0);
        insertMessage.clear();
    }

//    Handle the "Time" choice from the "Select Trigger" menu.
    @FXML
    private void selectTimeTrigger(ActionEvent event) {
        hideAllTriggers();
        selectedTrigger = TriggerType.TIME;
        triggerIsSelected.setValue(true);
        timeTriggerPane.setVisible(true);
    }
    
//    Hide all the possible Trigger panes.
    private void hideAllTriggers(){
        timeTriggerPane.setVisible(false);
    }

//    Handle the "Message" choice from the "Select Action" menu.
    @FXML
    private void selectMessageAction(ActionEvent event) {
        hideAllActions();
        selectedAction = ActionType.MESSAGE;
        actionIsSelected.setValue(true);
        messageActionPane.setVisible(true);
    }
    
//    Hide all the possible Action panes.
    private void hideAllActions(){
        messageActionPane.setVisible(false);
    }
}
