package www.cput.za.animalpound.factories.animal;

import  www.cput.za.animalpound.domain.AnimalHealth;
import  www.cput.za.animalpound.domain.MedicalHistory;

import java.util.Date;
import java.util.List;

/**
 * Created by Game330 on 2016-04-14.
 */
public class MedicalHistoryFactory {

    //singleton
    private static MedicalHistoryFactory instance=null;

    public MedicalHistoryFactory(){}

    public static MedicalHistoryFactory getInstance(){
        if (instance == null) {
            instance = new MedicalHistoryFactory();
        }
        return instance;
    }
    private List<AnimalHealth> infection;

    public static MedicalHistory createMedHistory(Long medId,
                                                String description,
                                                Date date,
                                                List<AnimalHealth> infection)
    {
        return new MedicalHistory.Builder()
                .medID(medId)
                .description(description)
                .date(date)
                .infections(infection)
                .build();
    }
}
