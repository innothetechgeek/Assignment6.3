package www.cput.za.animalpound.domain;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Admin on 2016/04/03.
 */
public class DateTimeRange {
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
