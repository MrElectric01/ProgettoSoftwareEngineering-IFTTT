package progettosoftwareengineering.localifttt.model.rule.action.programexecution;

import java.util.Map;

import progettosoftwareengineering.localifttt.model.rule.action.*;

public class ProgramExecutionActionCreator extends BaseActionCreator{

    @Override
    public Action createAction(ActionType action, Map<String,String> actParam) {
        if(action.equals(ActionType.PROGRAMEXECUTION))
            return new ProgramExecutionAction(actParam.get("programExecutionActionInterpreter"),actParam.get("programExecutionActionProgramPath"),actParam.get("programExecutionActionProgramArguments"));
        else
            return this.nextCreator(action, actParam);
    }
}

