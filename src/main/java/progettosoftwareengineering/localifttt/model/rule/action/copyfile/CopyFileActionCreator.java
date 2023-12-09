package progettosoftwareengineering.localifttt.model.rule.action.copyfile;

import java.util.Map;
import progettosoftwareengineering.localifttt.model.rule.action.Action;
import progettosoftwareengineering.localifttt.model.rule.action.ActionType;
import progettosoftwareengineering.localifttt.model.rule.action.BaseActionCreator;

public class CopyFileActionCreator extends BaseActionCreator {

    @Override
    public Action createAction(ActionType action, Map<String, String> actParam) {
        if(action.equals(ActionType.COPY_FILE))
            return new CopyFileAction(actParam.get("copyFileActionFilePath"), actParam.get("copyFileActionDirectoryPath"));
        else
            return this.nextCreator(action, actParam);
    }
    
}
