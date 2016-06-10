package www.cput.za.exampreparation.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 2016/04/03.
 */
public class Animal implements Serializable{
    private String breed;
    private Long animalId;
    private double weight;
    private List<Schedule> schedules;

    public Animal(Builder value)
    {
        this.breed = value.breed;
        this.animalId = value.animalId;
        this.weight = value.weight;
    }

    public String getbreed() {
        return breed;
    }

    public Long getAnimalId() {
        return animalId;
    }


    public double getWeight() {
        return weight;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public String getBreed() {
        return breed;
    }

    public static class Builder{
        String breed;
        Long animalId;
        double weight;
        List<Schedule> schedules;

        public Builder(){
        }

        public Builder animalId(Long animalId) {
            this.animalId = animalId;
            return this;
        }

        public Builder weight(double weight) {
            this.weight = weight;
            return this;
        }
        public Builder breed(String breed) {
            this.breed = breed;
            return this;
        }

        public Builder schedules(List<Schedule> schedules) {
            this.schedules = schedules;
            return this;
        }

        public Builder copy(Animal value)
        {
            this.breed = value.breed;
            this.animalId = value.animalId;
            this.weight = value.weight;
            return this;
        }

        public Animal build()
        {
            return new Animal(this);
        }

    }
}
