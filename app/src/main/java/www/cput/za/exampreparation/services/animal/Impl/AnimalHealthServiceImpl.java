package www.cput.za.animalpound.services.animal.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import www.cput.za.animalpound.conf.App;
import www.cput.za.animalpound.domain.AnimalHealth;
import www.cput.za.animalpound.repository.animal.AnimalHealthRepository;
import www.cput.za.animalpound.repository.animal.Impl.AnimalHealthRepositoryImpl;
import www.cput.za.animalpound.services.animal.AnimalHealthService;

/**
 * Created by Game330 on 2016-05-13.
 */
public class AnimalHealthServiceImpl extends IntentService implements AnimalHealthService {
    /*I'm using intent service to implement my services because my services don't send or
return anything back to the user, they just saves data to the database*/
    private final AnimalHealthRepository repository;

    private static final String ACTION_ADD = " www.cput.za.animalpound.services.animal.Impl.ADD";
    private static final String ACTION_REMOVE = " www.cput.za.animalpound.services.animal.Impl.REMOVE";

    private static final String EXTRA_ADD = " www.cput.za.animalpound.services.animal.Impl.extra.ADD";


    private static AnimalHealthServiceImpl service = null;

    public static AnimalHealthServiceImpl getInstance() {
        if (service == null)
            service = new  AnimalHealthServiceImpl();
        return service;
    }

     public AnimalHealthServiceImpl(){
        super("AnimalHealthServiceImpl");
        repository = new AnimalHealthRepositoryImpl(App.getAppContext());

    }

    @Override
    public void addHealth(Context context) {
        Intent intent = new Intent(context, AnimalRecordServiceImpl.class);
        intent.setAction(ACTION_ADD);
        context.startService(intent);
    }

    @Override
    public void removeHealth(Context context) {
        Intent intent = new Intent(context, AnimalRecordServiceImpl.class);
        intent.setAction(ACTION_REMOVE);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final AnimalHealth health = (AnimalHealth) intent.getSerializableExtra(EXTRA_ADD);
                saveHealth(health);
            } else if (ACTION_REMOVE.equals(action)) {
                removeAll();
            }
        }
    }

    private void removeAll() {
        repository.deleteAll();
    }

    public void saveHealth(AnimalHealth aHealth) {
        AnimalHealth animalHealth = new AnimalHealth.Builder()
                 .condition(aHealth.getCondition())
                 .build();
        AnimalHealth savedAnimalHealth = repository.save(animalHealth);

    }
}
