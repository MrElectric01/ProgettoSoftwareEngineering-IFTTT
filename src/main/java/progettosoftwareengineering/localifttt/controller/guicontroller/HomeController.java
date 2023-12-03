/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
import progettosoftwareengineering.localifttt.model.rule.*;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        When we open the view at first time, or after a switch, we refresh the ObservableList.
        rules.setAll(RuleCollection.getInstance().getRules());
        
//        Disable the SwitchStatusButton if there isn't a selected rule.
        switchStatusContextMenuItem.disableProperty().bind(ruleTable.getSelectionModel().selectedItemProperty().isNull());
        switchStatusButton.disableProperty().bind(ruleTable.getSelectionModel().selectedItemProperty().isNull());
//        Unlock the possibility to select more than one rule at the same time.
        ruleTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
//        Connect the table to the Rule fields.
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        triggerColumn.setCellValueFactory(new PropertyValueFactory("trigger"));
        actionColumn.setCellValueFactory(new PropertyValueFactory("action"));
         statusColumn.setCellValueFactory(new PropertyValueFactory("status"));
        ruleTable.setItems(rules);

//      Observe the RuleCollection to refresh the interface if the RuleCollection changes.
        RuleCollection.getInstance().addObserver(this);
    }    

//    Handle the update copying the RuleCollection List and refreshing the table.
    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> {
            rules.setAll(RuleCollection.getInstance().getRules());
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
            RuleCollection.getInstance().getRules().get(RuleCollection.getInstance().getRules().indexOf(rule)).switchStatus();
        }
        ruleTable.getSelectionModel().clearSelection();
    }

//    Deselect the selected rules in the table, when click out of the table.
    @FXML
    private void deselect(MouseEvent event) {
        ruleTable.getSelectionModel().clearSelection();
    }
}
