package www.cput.za.animalpound.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 2016/04/03.
 */
public class LivingArea implements Serializable{
    private Long livingAreaId;
    private String name;
    private List<Animal> animals;

    public LivingArea(Builder value)
    {
        this.livingAreaId = value.livingAreaId;
        this.animals = value.animals;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public Long getLivingAreaId() {
        return livingAreaId;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        Long livingAreaId;
        String livingAreaName;
        List<Animal> animals;

        public Builder() {
        }

        public Builder livingAreaName(String livingAreaName){
            this.livingAreaName = livingAreaName;
            return this;
        }

        public Builder livingAreaId(Long livingAreaId) {
            this.livingAreaId = livingAreaId;
            return this;
        }
        public Builder animals(List<Animal> animals) {
            this.animals = animals;
            return this;
        }


        public Builder copy(LivingArea value)
        {
            this.livingAreaId = value.livingAreaId;
            this.animals = value.animals;
            return this;
        }

        public LivingArea build()
        {
            return new LivingArea(this);
        }
    }
}
