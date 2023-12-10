package progettosoftwareengineering.localifttt.model.rule.action.message;

import java.util.Map;
import progettosoftwareengineering.localifttt.model.rule.action.*;

public class MessageActionCreator extends BaseActionCreator{

    @Override
    public Action createAction(ActionType action, Map<String,String> actParam) {
        if(action.equals(ActionType.MESSAGE))
            return new MessageAction(actParam.get("messageActionMessage"));
        else
            return this.nextCreator(action, actParam);
    }
}
