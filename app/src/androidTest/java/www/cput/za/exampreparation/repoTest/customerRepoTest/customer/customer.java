package www.cput.za.exampreparation.repoTest.customerRepoTest.customer;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import www.cput.za.exampreparation.domain.Customer;
import www.cput.za.exampreparation.repository.customer.CustomerRepo;
import www.cput.za.exampreparation.repository.customer.imp.CustomerRepoImpl;

/**
 * Created by Game330 on 2016-06-01.
 */
public class customer extends AndroidTestCase{
    private static final String TAG="CUST TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception{
        CustomerRepo custRepo = new CustomerRepoImpl(this.getContext());
        Customer createEntity = new Customer.Builder()
                .id(1099040L)
                .name("Innocent")
                .surname("Mphokeli")
                .age(17)
                .build();

        Customer insertedEntity = custRepo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE ", insertedEntity);

        Set<Customer> cust =custRepo.findAll();
        Assert.assertTrue(TAG + " READ ALL ", cust.size() > 0);

        Customer entity = custRepo.findById(109876540L);
        Assert.assertNotNull(TAG+" READ ENTITY ",entity);

       Customer updateEntity=new Customer.Builder()
                .copy(entity)
                .name("Innocent")
                .build();
        custRepo.update(updateEntity);
        Customer newEntity= custRepo.findById(56780L);
        Assert.assertEquals(TAG+" UPDATE ENTITY","Innocent",newEntity.getName());

       /* custRepo.delete(updateEntity);
        Customer deletedEntity= custRepo.findById(8093970L);
        Assert.assertNull(TAG+" DELETE ",deletedEntity); */
    }
}
