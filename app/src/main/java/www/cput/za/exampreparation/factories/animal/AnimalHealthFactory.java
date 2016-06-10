package www.cput.za.animalpound.factories.animal;

import  www.cput.za.animalpound.domain.Animal;
import  www.cput.za.animalpound.domain.AnimalHealth;
import  www.cput.za.animalpound.domain.Treatment;

import java.util.List;

/**
 * Created by Game330 on 2016-04-12.
 */
public class AnimalHealthFactory {

    //singleton
   private static  AnimalHealthFactory instance=null;

    public  AnimalHealthFactory(){}

    public static AnimalHealthFactory getInstance(){
        if (instance == null) {
            instance = new  AnimalHealthFactory();
        }
        return instance;
    }

    public static AnimalHealth getHealth(String condition, String description, List<Animal> animal, List<Treatment> treatment)
    {
        AnimalHealth myHealth = new AnimalHealth
                .Builder()
                .condition(condition)
                .Description(description)
                .animals(animal)
                .Treatment(treatment)
                .build();

        return myHealth;
    }





}

