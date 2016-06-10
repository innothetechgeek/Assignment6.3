package www.cput.za.animalpound.domain;

import java.io.Serializable;

/**
 * Created by Admin on 2016/04/03.
 */
public class Schedule implements Serializable{
    private Long scheduleId;
    private String activity;
    private String time;

    public Schedule() {
    }

    public Schedule(Builder value)
    {
        this.activity = value.activity;
        this.time = value.time;
        this.scheduleId = value.scheduleId;
    }
    
    public String getActivity() {
        return activity;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public String getTime() {
        return time;
    }

    public static class Builder{
        String activity;
        Long scheduleId;
        String time;

        public Builder(){

        }

        public Builder activity(String activity) {
            this.activity = activity;
            return this;
        }

        public Builder scheduleId(Long scheduleId) {
            this.scheduleId = scheduleId;
            return this;
        }

        public Builder time(String time) {
            this.time = time;
            return this;
        }

        public Builder copy(Schedule value)
        {
            this.activity = value.activity;
            this.time = value.time;
            this.scheduleId = value.scheduleId;
            return this;
        }

        public Schedule build(){
            return new Schedule(this);
        }
    }
}
