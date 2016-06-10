package www.cput.za.exampreparation.domain;

import java.io.Serializable;

/**
 * Created by Game330 on 2016-05-30.
 */
public class Customer implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String age;


    private Customer(Builder build)
    {
        this.name = build.name;
        this.surname = build.surname;
        this.age = build.age;
        this.id = build.id;


    }

    public Long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public static class Builder{
        private Long id;
        private String name;
        private String surname;
        private String age;

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder surname(String surname){
            this.surname = surname;
            return this;
        }

        public Builder age(String age){
            this.age = age;
            return this;
        }

        public Builder customer(Customer cust) {
            name = cust.getName();
            surname = cust.getSurname();
            age = cust.getAge();
            id = cust.getId();
            return this;
        }

        public Builder copy(Customer value){
            name = value.name;
            surname = value.surname;
            age = value.age;
            id = value.id;
            return this;
        }

        public Customer build(){
            return new Customer(this);
        }
    }


    @Override
    public String toString()
    {
        return String.format("Id : %d\nName :%s\nSurname :%s\nAge :%s",id,name,surname,age);

    }
}
