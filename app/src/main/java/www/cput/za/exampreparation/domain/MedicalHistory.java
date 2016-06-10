package www.cput.za.exampreparation.domain;

import java.util.Date;
import java.util.List;

/**
 * Created by Game330 on 2016-04-13.
 */
public class MedicalHistory {

    private List<AnimalHealth> infection;
    private Long medID;
    private String description;
    private Date date;

    private MedicalHistory() {
    }

    public MedicalHistory(Builder value)
    {
        this.date = value.date;
        this.description = value.description;
        this.medID = value.medID;
        this.infection = value.infection;
    }

    public Long getMedID() {
        return medID;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate(){return date;}

    public List<AnimalHealth> getInfection() {
        return infection;
    }

        public static class Builder{
            List<AnimalHealth> infection;
            Long medID;
            String description;
            Date date;

            public Builder() {

            }
            public Builder medID(Long medID) {
              this.medID = medID;
                return this;
            }

            public Builder description(String description) {
                this.description = description;
                return this;
            }

            public Builder date(Date date) {
                this.date = date;
                return this;}


            public Builder infections(List<AnimalHealth> infection) {
                this.infection = infection;
                return this;
            }

            public Builder copy(MedicalHistory value)
            {
                this.date = value.date;
                this.description = value.description;
                this.medID = value.medID;
                this.infection = value.infection;
                return this;
            }

            public MedicalHistory build()
            {
                return new MedicalHistory(this);
            }

        }
    }

