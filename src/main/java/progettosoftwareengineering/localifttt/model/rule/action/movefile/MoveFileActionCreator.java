package progettosoftwareengineering.localifttt.model.rule.action.movefile;

import java.util.Map;
import progettosoftwareengineering.localifttt.model.rule.action.*;

public class MoveFileActionCreator extends BaseActionCreator{

    @Override
    public Action createAction(ActionType action, Map<String, String> actParam) {
        if(action.equals(ActionType.MOVEFILE))
            return new MoveFileAction(actParam.get("moveFileActionFilePath"), actParam.get("moveFileActionDirectoryPath"));
        else
            return this.nextCreator(action, actParam);
    }
}
