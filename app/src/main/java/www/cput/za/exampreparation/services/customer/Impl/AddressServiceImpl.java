package www.cput.za.animalpound.services.customer.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import www.cput.za.animalpound.conf.App;
import www.cput.za.animalpound.domain.Address;
import www.cput.za.animalpound.repository.customer.AddressRepository;
import www.cput.za.animalpound.repository.customer.Impl.AddressRepositoryImpl;
import www.cput.za.animalpound.services.customer.AddressService;

/**
 * Created by Game330 on 2016-05-12.
 */
public class AddressServiceImpl extends IntentService implements AddressService{
    /*I'm using intent service to implement my services because my services don't send or
return anything back to the user, they just saves data to the database*/
    private final AddressRepository repository;

    private static final String ACTION_ADD = "www.cput.za.animalpound.services.customer.Impl;.action.ADD";
    private static final String ACTION_UPDATE = " www.cput.za.animalpound.services.customer.Impl;.action.UPDATE";


    private static final String EXTRA_ADD = " www.cput.za.animalpound.services.customer.Impl;.extra.ADD";
    private static final String EXTRA_UPDATE = " www.cput.za.animalpound.services.customer.Impl;.extra.UPDATE";

    private static AddressServiceImpl service = null;

    public static AddressServiceImpl getInstance() {
        if (service == null)
            service = new AddressServiceImpl();
        return service;
    }

    public AddressServiceImpl() {
        super("AddressServiceImpl");
        repository = new AddressRepositoryImpl(App.getAppContext());
    }


    @Override
    public void addAddress(Context context, Address address) {
        Intent intent = new Intent(context, AddressServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, address);
        context.startService(intent);
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final  Address address = (Address) intent.getSerializableExtra(EXTRA_ADD);
                postAddress(address);
            }
        }
    }

    public void postAddress(Address adrss) {
        Address address = new Address.Builder()
                .street(adrss.getStreet())
                .room(adrss.getRoom())
                .surbub(adrss.getSurbub())
                .zipCode(adrss.getZipCode())
                .build();
        Address savedAdress = repository.save(address);
    }


}

