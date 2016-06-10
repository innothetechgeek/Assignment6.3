package www.cput.za.animalpound.factories.schedule;

import  www.cput.za.animalpound.domain.*;

/**
 * Created by Admin on 2016/04/03.
 */
public class ScheduleFactory {

    //singleton
    private static ScheduleFactory instance=null;

    public ScheduleFactory(){}

    public static ScheduleFactory getInstance(){
        if (instance == null) {
            instance = new ScheduleFactory();
        }
        return instance;
    }

    public static Schedule createSchedule(String activity,
                                          Long scheduleId,
                                          String time)
    {
        return new Schedule.Builder()
                .scheduleId(scheduleId)
                .activity(activity)
                .time(time)
                .build();
    }

}
