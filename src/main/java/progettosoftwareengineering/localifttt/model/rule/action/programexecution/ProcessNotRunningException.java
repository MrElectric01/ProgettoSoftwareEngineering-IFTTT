package progettosoftwareengineering.localifttt.model.rule.action.programexecution;

public class ProcessNotRunningException extends Exception {
    public ProcessNotRunningException() {
        super("The process is not running");
    }
}
