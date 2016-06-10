package www.cput.za.animalpound.factories.customer;

import  www.cput.za.animalpound.domain.Adoption;

import java.util.Date;

/**
 * Created by Admin on 2016/04/03.
 */
public class AdoptionFactory {

    //singleton
    private static AdoptionFactory instance=null;

    public AdoptionFactory(){}

    public static AdoptionFactory getInstance(){
        if (instance == null) {
            instance = new AdoptionFactory();
        }
        return instance;
    }

    public static Adoption createAdoption( Long adoptionId,String comment,
                                           Date adoptionDate)
    {
        return new Adoption.Builder()
                .adoptionId(adoptionId)
                .adoptionDate(adoptionDate)
                .comment(comment)
                .build();
    }
}
