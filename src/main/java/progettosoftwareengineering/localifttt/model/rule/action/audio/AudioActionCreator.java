package progettosoftwareengineering.localifttt.model.rule.action.audio;

import java.util.Map;


import progettosoftwareengineering.localifttt.model.rule.action.*;

public class AudioActionCreator extends BaseActionCreator{

    @Override
    public Action createAction(ActionType action, Map<String,String> actParam) {
        if(action.equals(ActionType.AUDIO))
            return new AudioAction(actParam.get("audioActionAudioPath").toString());
        else
            return this.nextCreator(action, actParam);
    }
    
}
