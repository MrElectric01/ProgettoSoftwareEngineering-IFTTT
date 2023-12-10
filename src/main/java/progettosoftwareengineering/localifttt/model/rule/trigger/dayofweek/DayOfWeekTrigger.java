package progettosoftwareengineering.localifttt.model.rule.trigger.dayofweek;

import java.time.*;

import progettosoftwareengineering.localifttt.model.rule.trigger.Trigger;

// Trigger for a specific day of the week.
public class DayOfWeekTrigger extends Trigger {

    private DayOfWeek dayOfWeek;

    public DayOfWeekTrigger(String dayOfWeek) {
        this.dayOfWeek = DayOfWeek.valueOf(dayOfWeek.toUpperCase());
    }

//     Triggers the Trigger when the current day of the week matches the specified day.
    @Override
    public boolean checkTrigger() {
        return LocalDate.now().getDayOfWeek().equals(dayOfWeek);
    }

    @Override
    public String toString() {
        return "Day of Week: " + dayOfWeek;
    }
}
