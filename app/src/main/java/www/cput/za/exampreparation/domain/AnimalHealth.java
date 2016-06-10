package www.cput.za.animalpound.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Game330 on 2016-04-12.
 */
public class AnimalHealth implements Serializable{
    private Long healthID;
    private String condition;
    private String description;
    private List<Animal> animal;
    private List<Treatment> treatment;



    private AnimalHealth(Builder builder) {
        animal = builder.animal;
        condition = builder.condition;
        description = builder.description;
        healthID = builder.healthID;
        treatment = builder.treatment;

    }

    public static class Builder {

        String condition;
        String description;
        List<Animal> animal;
        List<Treatment> treatment;
        Long healthID;

        public Builder(){
        }

        public Builder condition(String condition){
            this.condition = condition;
            return this;
        }

        public Builder Treatment(List<Treatment> treatment) {
            this.treatment = treatment;
            return this;
        }

        public Builder id(Long value) {
            healthID = value;
            return this;
        }
        public Builder Description(String description) {
            this.description = description;
            return this;
        }

        public Builder animals(List<Animal> animals) {
            this.animal = animals;
            return this;
        }


        public Builder animalHealth(AnimalHealth health) {
            animal = health.getAnimal();
            treatment = health.getTreatment();
            condition = health.getCondition();
            healthID = health.getHealthID();
            description = health.getDescription();
            return this;
        }

        public Builder copy(AnimalHealth val){
            this.condition = val.condition;
            this.description = val.description;
            this.healthID = val.healthID;
            return this;
        }

        public AnimalHealth build() {
            return new AnimalHealth(this);
        }

    }

    public String getCondition() {
        return condition;
    }

    public String getDescription() {
        return description;
    }

    public Long getHealthID() {
        return healthID;
    }

    public List<Animal> getAnimal() {
        return animal;
    }

    public List<Treatment> getTreatment() {
        return treatment;
    }

}
