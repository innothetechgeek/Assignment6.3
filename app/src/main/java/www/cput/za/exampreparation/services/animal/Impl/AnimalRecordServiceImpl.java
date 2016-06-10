package www.cput.za.animalpound.services.animal.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import www.cput.za.animalpound.conf.App;
import www.cput.za.animalpound.domain.AnimalRecord;
import www.cput.za.animalpound.repository.animal.AnimalRecordRepository;
import www.cput.za.animalpound.repository.animal.Impl.AnimalRecordRepoImpl;
import www.cput.za.animalpound.services.animal.AnimalRecordService;

/**
 * Created by Game330 on 2016-05-11.
 */
public class AnimalRecordServiceImpl extends IntentService implements AnimalRecordService {
    /*I'm using intent service to implement my services because my services don't send or
return anything back to the user, they just saves data to the database*/
    private final AnimalRecordRepository repository;

    private static final String ACTION_ADD = "ADD";
    private static final String ACTION_REMOVE = "REMOVE";

    private static final String EXTRA_ADD = "zm.hashcode.hashdroidpvt.services.election.Impl.extra.ADD";

    private static AnimalRecordServiceImpl service = null;

    public static AnimalRecordServiceImpl getInstance() {
        if (service == null)
            service = new AnimalRecordServiceImpl();
        return service;
    }

    public AnimalRecordServiceImpl() {
        super("AnimalRecordServiceImpl");
        repository = new AnimalRecordRepoImpl(App.getAppContext());

    }

    @Override
    public void addAnimal(Context context) {
        Intent intent = new Intent(context, AnimalRecordServiceImpl.class);
        intent.setAction(ACTION_ADD);
        context.startService(intent);
    }

    @Override
    public void removeAnimal(Context context) {
        Intent intent = new Intent(context, AnimalRecordServiceImpl.class);
        intent.setAction(ACTION_REMOVE);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final AnimalRecord animalRecord = (AnimalRecord) intent.getSerializableExtra(EXTRA_ADD);
                saveAnimal(animalRecord);
            } else if (ACTION_REMOVE.equals(action)) {
                removeAll();
            }
        }
    }

    private void removeAll() {
        repository.deleteAll();
    }

    public void saveAnimal(AnimalRecord animal) {
                 AnimalRecord animl = new AnimalRecord.Builder()
                 .id(animal.getId())
                 .arrivalDate(animal.getarrivalDate())
                 .leavingDate(animal.getleavingDate())
                 .build();
        AnimalRecord savedAnimal = repository.save(animl);

    }

}
