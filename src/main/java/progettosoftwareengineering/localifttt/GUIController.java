package progettosoftwareengineering.localifttt;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
//    TODO when a concrete trigger will be implemented
//    @FXML
//    private TableColumn<Rule, Trigger> triggerColumn;  
//    TODO when a concrete action will be implemented
//    @FXML
//    private TableColumn<Rule, Action> actionColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveButton.disableProperty().bind(Bindings.isEmpty(insertRuleName.textProperty()));
        
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
//        add when a concrete trigger will be implemented
//        triggerColumn.setCellValueFactory(new PropertyValueFactory("trigger"));

//        add when a concrete action will be implemented
//        actionColumn.setCellValueFactory(new PropertyValueFactory("action"));
        ruleTable.setItems(RuleCollection.getInstance().getRules());
    }
    
    @FXML
    private void addRule(ActionEvent event) {
        homePane.setVisible(false);
        addRulePane.setVisible(true);
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        fromAddToHomePane();
    }

    @FXML
    private void handleSave(ActionEvent event) {
        RuleCollection.getInstance().addRule(new Rule(insertRuleName.getText(), null, null));
        fromAddToHomePane();
    }
    
    private void fromAddToHomePane() {
        clearAllFields();
        addRulePane.setVisible(false);
        homePane.setVisible(true);
    }

    private void clearAllFields() {
        insertRuleName.clear();
    }
}
