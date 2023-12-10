package progettosoftwareengineering.localifttt.model.rule.trigger.dayofmonth;

import java.time.LocalDate;
import progettosoftwareengineering.localifttt.model.rule.trigger.Trigger;

// Trigger for a specific day of the month.
public class DayOfMonthTrigger extends Trigger {

    private int dayOfMonth;

    public DayOfMonthTrigger(String dayOfMonth) {
        this.dayOfMonth = Integer.valueOf(dayOfMonth);
    }

//     Triggers the Trigger when the current day of the month matches the specified day.
    @Override
    public boolean checkTrigger() {
        return LocalDate.now().getDayOfMonth() == dayOfMonth;
    }

    @Override
    public String toString() {
        return "Day of Month: " + dayOfMonth;
    }
}
