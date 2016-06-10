package www.cput.za.animalpound.factories.animal;

import  www.cput.za.animalpound.domain.Animal;
import  www.cput.za.animalpound.domain.Schedule;

import java.util.List;

/**
 * Created by Admin on 2016/04/03.
 */
public class AnimalFactory {

    //singleton
   private static AnimalFactory instance=null;

    public AnimalFactory(){}

    public static AnimalFactory getInstance(){
        if (instance == null) {
            instance = new AnimalFactory();
        }
        return instance;
    }
    public static Animal createAnimal(
            Long animalId,
            List<Schedule> schedules,
            String breed)
    {
        return new Animal.Builder()
                .animalId(animalId)
                .breed(breed)
                .schedules(schedules)
                .build();
    }
}
