package www.cput.za.animalpound.services.customer.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import www.cput.za.animalpound.conf.App;
import www.cput.za.animalpound.domain.AnimalRecord;
import www.cput.za.animalpound.domain.Customer;
import www.cput.za.animalpound.repository.animal.AnimalRecordRepository;
import www.cput.za.animalpound.repository.animal.Impl.AnimalRecordRepoImpl;
import www.cput.za.animalpound.repository.customer.CustomerRepository;
import www.cput.za.animalpound.repository.customer.Impl.CustomerRepositoryImpl;
import www.cput.za.animalpound.services.animal.AnimalRecordService;
import www.cput.za.animalpound.services.customer.CustomerService;

/**
 * Created by Game330 on 2016-05-11.
 */
public class CustomerServiceImpl extends IntentService implements CustomerService {

    /*I'm using intent service to implement my services because my services don't send or
return anything back to the user, they just saves data to the database*/
    private final CustomerRepository repository;

    private static final String ACTION_ADD = "ADD";
    private static final String ACTION_REMOVE = "REMOVE";

    private static final String EXTRA_ADD = "zm.hashcode.hashdroidpvt.services.election.Impl.extra.ADD";

    private static CustomerServiceImpl service = null;

    public static CustomerServiceImpl getInstance() {
        if (service == null)
            service = new CustomerServiceImpl();
        return service;
    }

   public CustomerServiceImpl() {
        super("CustomerServiceImpl");
        repository = new CustomerRepositoryImpl(App.getAppContext());

    }

    @Override
    public void addCustomer(Context context) {
        Intent intent = new Intent(context, CustomerServiceImpl.class);
        intent.setAction(ACTION_ADD);
        context.startService(intent);
    }

    @Override
    public void removeCustomer(Context context) {
        Intent intent = new Intent(context, CustomerServiceImpl.class);
        intent.setAction(ACTION_REMOVE);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Customer customer = (Customer) intent.getSerializableExtra(EXTRA_ADD);
                saveCustomer(customer);
            } else if (ACTION_REMOVE.equals(action)) {
                removeAll();
            }
        }
    }

    private void removeAll() {
        repository.deleteAll();
    }

    public void saveCustomer(Customer cust) {
                 Customer customer = new Customer.Builder()
                 .id(cust.getID())
                 .custName(cust.getCustName())
                 .custSurname(cust.getCustSurname())
                 .build();
                  Customer savedCustomer = repository.save(customer);

    }

}
