package www.cput.za.animalpound.domain;

import java.io.Serializable;

/**
 * Created by Game330 on 2016-04-12.
 */
public class Treatment implements Serializable {
    private Long treatmentNO;
    private String expiryDate;
    private String instructions;
    private String treatment;

    public  Treatment(){}

    private Treatment(Builder build) {
        this.treatmentNO = build.treatmentNO;
        this.expiryDate = build.expiryDate;
        this.instructions = build.instructions;
    }

    public Long getTreatmentNO() {
        return treatmentNO;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getInstructions() {
        return instructions;
    }



    public static class Builder {
        Long treatmentNO;
        String expiryDate;
        String instructions;

        public Builder(){

        }

        public Builder treatmentNO(Long treatmentNO) {
            this.treatmentNO = treatmentNO;
            return this;
        }

        public Builder ExpiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public Builder Instructions(String instructions) {
            this.instructions = instructions;
            return this;
        }


        public Builder treatment(Treatment treat) {
            expiryDate = treat.getExpiryDate();
            instructions = treat.getInstructions();
            treatmentNO = treat.getTreatmentNO();
            return this;
        }

        public Treatment build() {
            return new Treatment(this);
        }

        public Builder copy(Treatment value)
        {
            this.treatmentNO = value.treatmentNO;
            this.expiryDate = value.expiryDate;
            this.instructions = value.instructions;
            return this;
        }


    }
}

