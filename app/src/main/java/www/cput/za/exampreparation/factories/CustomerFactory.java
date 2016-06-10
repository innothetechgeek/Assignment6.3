package www.cput.za.exampreparation.factories;

import www.cput.za.exampreparation.domain.Customer;

/**
 * Created by Game330 on 2016-05-30.
 */
public class CustomerFactory {


    public static Customer createCustomer( String name, String surname,
                                          String age)
    {
        return new Customer.Builder()
                .name(name)
                .surname(surname)
                .age(age)
                .build();
    }
}
