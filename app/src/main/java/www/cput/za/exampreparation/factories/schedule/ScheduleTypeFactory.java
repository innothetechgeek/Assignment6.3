package www.cput.za.animalpound.factories.schedule;

import  www.cput.za.animalpound.domain.Schedule;
import  www.cput.za.animalpound.domain.ScheduleType;

import java.util.List;

/**
 * Created by Admin on 2016/04/03.
 */
public class ScheduleTypeFactory {

    //singleton
    private static ScheduleTypeFactory instance=null;

    public ScheduleTypeFactory(){}

    public static ScheduleTypeFactory getInstance(){
        if (instance == null) {
            instance = new ScheduleTypeFactory();
        }
        return instance;
    }
    public static ScheduleType createScheduleType(
            String code,
            boolean active,
            List<Schedule> schedules)
    {
        return new ScheduleType.Builder(code)
                .active(active)
                .schedules(schedules)
                .build();
    }
}
