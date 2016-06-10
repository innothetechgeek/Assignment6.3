package www.cput.za.animalpound.services.customer.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import www.cput.za.animalpound.conf.App;
import www.cput.za.animalpound.domain.Adoption;
import www.cput.za.animalpound.repository.customer.AdoptionRepository;
import www.cput.za.animalpound.repository.customer.Impl.AdoptionRepositoryImpl;
import www.cput.za.animalpound.services.customer.AdoptionService;

/**
 * Created by Game330 on 2016-05-13.
 */
public class AdoptionServiceImpl extends IntentService implements AdoptionService {
    /*I'm using intent service to implement my services because my services don't send or
return anything back to the user, they just saves data to the database*/
    private final AdoptionRepository repository;

    private static final String ACTION_ADD = "package www.cput.za.animalpound.services.customer.Impl.ADD";
    private static final String ACTION_REMOVE = "package www.cput.za.animalpound.services.customer.Impl.REMOVE";

    private static final String EXTRA_ADD = "package www.cput.za.animalpound.services.customer.Impl.extra.ADD";

    private static  AdoptionServiceImpl service = null;

    public static  AdoptionServiceImpl getInstance() {
        if (service == null)
            service = new  AdoptionServiceImpl();
        return service;
    }

    public AdoptionServiceImpl(){
        super("AdoptionServiceImpl");
        repository = new AdoptionRepositoryImpl(App.getAppContext());

    }

    @Override
    public void addAdoption(Context context) {
        Intent intent = new Intent(context, CustomerServiceImpl.class);
        intent.setAction(ACTION_ADD);
        context.startService(intent);
    }

    @Override
    public void removeAdoption(Context context) {
        Intent intent = new Intent(context, CustomerServiceImpl.class);
        intent.setAction(ACTION_REMOVE);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Adoption adoption  = (Adoption) intent.getSerializableExtra(EXTRA_ADD);
                saveAdoption(adoption);
            } else if (ACTION_REMOVE.equals(action)) {
                removeAll();
            }
        }
    }

    private void removeAll() {
        repository.deleteAll();
    }

   public void saveAdoption(Adoption adoptn) {
        Adoption adoption = new Adoption.Builder()
                .adoptionId(adoptn.getAdoptionId())
                .adoptionDate(adoptn.getAdoptionDate())
                .comment(adoptn.getComment())
                .build();
        Adoption savedAdoption = repository.save(adoption);

    }
}
