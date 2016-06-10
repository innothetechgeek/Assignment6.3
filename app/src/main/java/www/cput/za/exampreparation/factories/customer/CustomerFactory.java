package www.cput.za.animalpound.factories.customer;

import www.cput.za.animalpound.domain.Customer;

/**
 * Created by Game330 on 2016-04-13.
 */
public class CustomerFactory {
    //singleton
    private static CustomerFactory instance=null;

    public CustomerFactory(){}

    public static CustomerFactory getInstance(){
        if (instance == null) {
            instance = new CustomerFactory();
        }
        return instance;
    }

    public static Customer createCustomer(Long id, String name,
                                          String surname)
    {
        return new Customer.Builder()
                .id(id)
                .custName(name)
                .custSurname(surname)
                .build();
    }

}
