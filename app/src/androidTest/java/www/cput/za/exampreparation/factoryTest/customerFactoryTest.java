package www.cput.za.exampreparation.factoryTest;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import www.cput.za.exampreparation.domain.Customer;
import www.cput.za.exampreparation.factories.CustomerFactory;

/**
 * Created by Game330 on 2016-05-30.
 */
public class customerFactoryTest extends AndroidTestCase {
    public void testCreate() throws Exception
    {
        CustomerFactory factory = new CustomerFactory();
        Customer original = factory.createCustomer(1000L,"Innocent", "Mphokeli", 16);

        Assert.assertEquals("Mphokeli", original.getSurname());
    }


    public void testUpdate()throws Exception
    {
        CustomerFactory factory = new  CustomerFactory();
        Customer original = factory.createCustomer(1000L,"Innocent", "Mphokeli", 16);
        Customer copy = new Customer.Builder().copy(original).name("Innocent").build();

        Assert.assertEquals("Innocent", original.getName());
    }
}
