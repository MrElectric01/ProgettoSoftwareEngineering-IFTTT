package progettosoftwareengineering.localifttt.model.rule.trigger.date;

import java.time.LocalDate;
import progettosoftwareengineering.localifttt.model.rule.trigger.Trigger;

public class DateTrigger extends Trigger {

    private LocalDate date;

    public DateTrigger(String date) {
        this.date = LocalDate.parse(date);
    }

//     Triggers the Trigger when the current date matches the specified target date.
    @Override
    public boolean checkTrigger() {
        return LocalDate.now().equals(date);
    }

    @Override
    public String toString() {
        return "Date: " + date;
    }
}
