package www.cput.za.animalpound.factories.animal;

import  www.cput.za.animalpound.domain.AnimalRecord;

/**
 * Created by Game330 on 2016-04-13.
 */
public class AnimalRecordFactory {

    //singleton
    private static AnimalRecordFactory instance=null;

    public AnimalRecordFactory(){}

    public static AnimalRecordFactory getInstance(){
        if (instance == null) {
            instance = new AnimalRecordFactory();
        }
        return instance;
    }
    public static AnimalRecord createDonation(Long id,String arrivalDate ,
                                              String leavingDate)
    {
        return new AnimalRecord.Builder()
                .id(id)
                .arrivalDate(arrivalDate)
                .leavingDate(leavingDate)
                .build();
    }

}
