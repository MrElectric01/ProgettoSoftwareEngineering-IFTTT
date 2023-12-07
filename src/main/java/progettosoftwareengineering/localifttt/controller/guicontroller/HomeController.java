package progettosoftwareengineering.localifttt.controller.guicontroller;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.application.Platform;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import progettosoftwareengineering.localifttt.model.ModelFacade;
import progettosoftwareengineering.localifttt.model.rule.Rule;
import progettosoftwareengineering.localifttt.model.rule.action.Action;
import progettosoftwareengineering.localifttt.model.rule.trigger.Trigger;

public class HomeController implements Initializable, Observer {

    @FXML
    private TableView<Rule> ruleTable;
    @FXML
    private MenuItem switchStatusContextMenuItem;
    @FXML
    private TableColumn<Rule, String> statusColumn;
    @FXML
    private TableColumn<Rule, String> nameColumn;
    @FXML
    private TableColumn<Rule, Trigger> triggerColumn;
    @FXML
    private TableColumn<Rule, Action> actionColumn;
    @FXML
    private Button switchStatusButton;
    
//    ObservaleList useful to contain the RuleCollection List in the interface.
    private ObservableList<Rule> rules = FXCollections.observableArrayList();
    
    @FXML
    private MenuItem removeContextMenuItem;
    @FXML
    private Button removeButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        When we open the view at first time, or after a switch, we refresh the ObservableList.
        rules.setAll(ModelFacade.getRuleCollection().getRules());
        
//        Disable the SwitchStatusButton if there isn't a selected rule.
        switchStatusContextMenuItem.disableProperty().bind(ruleTable.getSelectionModel().selectedItemProperty().isNull());
        switchStatusButton.disableProperty().bind(ruleTable.getSelectionModel().selectedItemProperty().isNull());
        
//        Unlock the possibility to select more than one rule at the same time.
        ruleTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
//        Disable the removeButton if there isn't a selected rule.
        removeContextMenuItem.disableProperty().bind(ruleTable.getSelectionModel().selectedItemProperty().isNull());
        removeButton.disableProperty().bind(ruleTable.getSelectionModel().selectedItemProperty().isNull());
        
//        Connect the table to the Rule fields.
        nameColumn.setCellValueFactory(new PropertyValueFactory<Rule,String>("name"));
        triggerColumn.setCellValueFactory(new PropertyValueFactory<Rule,Trigger>("trigger"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<Rule,Action>("action"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Rule,String>("status"));
        ruleTable.setItems(rules);

//      Observe the RuleCollection to refresh the interface if the RuleCollection changes.
        ModelFacade.getRuleCollection().addObserver(this);
    }    

//    Handle the update copying the RuleCollection List and refreshing the table.
    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> {
            rules.setAll(ModelFacade.getRuleCollection().getRules());
            ruleTable.refresh();
        });
    }
    
//    Handle the addRule button action switching the views.
    @FXML
    private void addRule(ActionEvent event) throws IOException {
        LocalIFTTT.setRoot("src\\main\\resources\\progettosoftwareengineering\\localifttt\\view\\AddRuleView.fxml");
    }

//    Handle the SwitchStatus buttn changing the status of all the selected rules from the table.
    @FXML
    private void handleSwitchStatus(ActionEvent event) {
        List<Rule> selectedRules = ruleTable.getSelectionModel().getSelectedItems();
        for(Rule rule: selectedRules) {
            ModelFacade.switchRuleStatus(rule);
        }
        ruleTable.getSelectionModel().clearSelection();
    }

//    Deselect the selected rules in the table, when click out of the table.
    @FXML
    private void deselect(MouseEvent event) {
        ruleTable.getSelectionModel().clearSelection();
    }

//   Before removing the selected Rules, a Confirmation Alert appears in order
//    to receive a confirm from the User.
    @FXML
    private void handleRemove(ActionEvent event) {
        List<Rule> selectedRules = ruleTable.getSelectionModel().getSelectedItems();
        
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Remove Rules");
        confirm.setHeaderText("Are you sure you want to remove the following rules?");
        for(Rule rule: selectedRules) {
            confirm.setContentText(confirm.getContentText() + rule.getName() + "\n");
        }

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            for(Rule rule: selectedRules) {
                ModelFacade.getRuleCollection().deleteRule(rule);
            }
            ruleTable.getSelectionModel().clearSelection();
        }
        
    }
}
