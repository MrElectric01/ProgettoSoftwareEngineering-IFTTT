package progettosoftwareengineering.localifttt.model.rule;

import java.io.Serializable;

//Utility Class useful for contain the three part of the SleepingPeriod for the PeriodicallyRule.
public class SleepingPeriod implements Serializable {
    private Integer days;
    private Integer hours;
    private Integer minutes;

    public SleepingPeriod(Integer days, Integer hours, Integer minutes) {
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
    }

    public Integer getDays() {
        return days;
    }

    public Integer getHours() {
        return hours;
    }

    public Integer getMinutes() {
        return minutes;
    }
}
