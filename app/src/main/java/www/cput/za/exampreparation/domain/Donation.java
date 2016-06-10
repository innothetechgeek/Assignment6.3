package www.cput.za.animalpound.domain;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Admin on 2016/04/03.
 */
public class Donation implements Serializable{
    private int donationId;
    private Date donationDate;
    private double amount;
    private String comment;

    public Donation(Builder value)
    {
        this.amount = value.amount;
        this.comment = value.comment;
        this.donationDate = value.donationDate;
        this.donationId = value.donationId;
    }

    public Date getDonationDate() {
        return donationDate;
    }

    public int getDonationId() {
        return donationId;
    }

    public double getAmount() {
        return amount;
    }

    public String getComment() {
        return comment;
    }

    public static class Builder {
        Date donationDate;
        int donationId;
        double amount;
        String comment;

        public Builder(Date donationDate) {
            this.donationDate = donationDate;
        }

        public Builder donationId(int donationId) {
            this.donationId = donationId;
            return this;
        }

        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder copy(Donation value){
            this.amount = value.amount;
            this.comment = value.comment;
            this.donationDate = value.donationDate;
            this.donationId = value.donationId;
            return this;
        }

        public Donation build()
        {
            return new Donation(this);
        }
    }
}
