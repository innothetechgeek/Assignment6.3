package www.cput.za.animalpound.factories.treatment;

import  www.cput.za.animalpound.domain.Treatment;

/**
 * Created by Game330 on 2016-04-13.
 */
public class TreatmentFactory {

    //singleton
    private static TreatmentFactory instance=null;

    private TreatmentFactory(){}

    public static TreatmentFactory getInstance(){
        if (instance == null) {
            instance = new TreatmentFactory();
        }
        return instance;
    }
    public static Treatment createTreatment(Long treatmentNO,
                String expiryDate,
                String instructions){

        return new Treatment.Builder()
                .treatmentNO(treatmentNO)
                .Instructions(instructions)
                .ExpiryDate(expiryDate)
                .build();
    }
}
