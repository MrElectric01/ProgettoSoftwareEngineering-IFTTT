package progettosoftwareengineering.localifttt.model.rule.action.deletefile;

import java.util.Map;
import progettosoftwareengineering.localifttt.model.rule.action.Action;
import progettosoftwareengineering.localifttt.model.rule.action.ActionType;
import progettosoftwareengineering.localifttt.model.rule.action.BaseActionCreator;

public class DeleteFileActionCreator extends BaseActionCreator {

    @Override
    public Action createAction(ActionType action, Map<String, String> actParam) {
                if(action.equals(ActionType.DELETE_FILE))
            return new DeleteFileAction(actParam.get("deleteFileActionFilePath"));
        else
            return this.nextCreator(action, actParam);
    }
    
}
