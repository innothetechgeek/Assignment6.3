package www.cput.za.animalpound.services.treatment.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import www.cput.za.animalpound.conf.App;
import www.cput.za.animalpound.domain.Treatment;
import www.cput.za.animalpound.repository.treatment.Impl.TreatmentRepositoryImpl;
import www.cput.za.animalpound.repository.treatment.TreatmentRepository;
import www.cput.za.animalpound.services.treatment.TreatmentService;

/**
 * Created by Game330 on 2016-05-13.
 */
public class TreatmentServiceImpl extends IntentService implements TreatmentService{
    private final TreatmentRepository repository;

    /*I'm using intent service to implement my services because my services don't send or
    return anything back to the user, they just saves data to the database*/

    private static final String ACTION_ADD = "package www.cput.za.animalpound.services.treatment.Impl.ADD";
    private static final String ACTION_REMOVE = "package www.cput.za.animalpound.services.treatment.Impl.REMOVE";

    private static final String EXTRA_ADD = "package www.cput.za.animalpound.services.treatment.Impl.ADD";

    private static TreatmentServiceImpl service = null;

    public static TreatmentServiceImpl getInstance() {
        if (service == null)
            service = new TreatmentServiceImpl();
        return service;
    }

    public TreatmentServiceImpl() {
        super("TreatmentServiceImpl");
        repository = new TreatmentRepositoryImpl(App.getAppContext());

    }

    @Override
    public void addTreatment(Context context) {
        Intent intent = new Intent(context, TreatmentServiceImpl.class);
        intent.setAction(ACTION_ADD);
        context.startService(intent);
    }

    @Override
    public void removeTreatment(Context context) {
        Intent intent = new Intent(context, TreatmentServiceImpl.class);
        intent.setAction(ACTION_REMOVE);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Treatment treatment = (Treatment) intent.getSerializableExtra(EXTRA_ADD);
                saveTreatment(treatment);
            } else if (ACTION_REMOVE.equals(action)) {
                removeAll();
            }
        }
    }

    private void removeAll() {
        repository.deleteAll();
    }

    public void saveTreatment(Treatment treatment) {
        Treatment trtment = new Treatment.Builder()
                .treatmentNO(treatment.getTreatmentNO())
                .Instructions(treatment.getInstructions())
                .build();
        Treatment savedTreatment = repository.save(trtment);

    }
}
