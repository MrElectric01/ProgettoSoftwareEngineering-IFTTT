package progettosoftwareengineering.localifttt.model.rule.action.writingtofile;

import java.util.Map;
import progettosoftwareengineering.localifttt.model.rule.action.*;

public class WritingToFileActionCreator extends BaseActionCreator{

    @Override
    public Action createAction(ActionType action, Map<String,String> actParam) {
        if(action.equals(ActionType.WRITING_TO_FILE))
            return new WritingToFileAction(actParam.get("writingToFileActionFilePath"),actParam.get("writingToFileActionTextToAppend"));
        else
            return this.nextCreator(action, actParam);
    }
}
