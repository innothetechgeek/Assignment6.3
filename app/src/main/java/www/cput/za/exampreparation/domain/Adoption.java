package www.cput.za.animalpound.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Admin on 2016/04/03.
 */
public class Adoption implements Serializable{
    private Long adoptionId;
    private String comment;
    private Date adoptionDate;

    public Adoption(Builder value)
    {
        this.adoptionId = value.adoptionId;
        this.comment = value.comment;
        this.adoptionDate = value.adoptionDate;
    }

    public String getComment() {
        return comment;
    }

    public Long getAdoptionId() {
        return adoptionId;
    }

    public Date getAdoptionDate() {
        return adoptionDate;
    }

    public static class Builder{
        String comment;
        Long adoptionId;
        Date adoptionDate;

        public Builder() {

        }

        public Builder comment(String comment){
            this.comment = comment;
            return this;
        }

        public Builder adoptionId(Long adoptionId) {
            this.adoptionId = adoptionId;
            return this;
        }


        public Builder adoptionDate(Date adoptionDate) {
            this.adoptionDate = adoptionDate;
            return this;
        }

        public Builder copy(Adoption value)
        {
            this.adoptionId = value.adoptionId;
            this.comment = value.comment;
            this.adoptionDate = value.adoptionDate;
            return this;
        }

        public Adoption build(){
            return new Adoption(this);
        }
    }
}
