package www.cput.za.animalpound.factories.animal;

import  www.cput.za.animalpound.domain.Animal;
import  www.cput.za.animalpound.domain.LivingArea;

import java.util.List;

/**
 * Created by Admin on 2016/04/03.
 */
public class LivingAreaFactory {

    //singleton
    private static LivingAreaFactory instance=null;

    public LivingAreaFactory(){}

    public static LivingAreaFactory getInstance(){
        if (instance == null) {
            instance = new LivingAreaFactory();
        }
        return instance;
    }
    public static LivingArea createLivingArea(Long livingAreaId,
            String leavingAreaName,
            List<Animal> animals){
        return new LivingArea.Builder()
                .livingAreaId(livingAreaId)
                .livingAreaName(leavingAreaName)
                .animals(animals)
                .build();
    }
}
