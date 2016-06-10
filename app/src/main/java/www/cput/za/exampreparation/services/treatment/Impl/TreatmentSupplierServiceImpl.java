package www.cput.za.animalpound.services.treatment.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import www.cput.za.animalpound.conf.App;
import www.cput.za.animalpound.domain.TreatmentSupplier;
import www.cput.za.animalpound.repository.treatment.Impl.TreatmentSupplierRepoImpl;
import www.cput.za.animalpound.repository.treatment.TreatmentSuplierRepository;
import www.cput.za.animalpound.services.treatment.TreatmentService;
import www.cput.za.animalpound.services.treatment.TreatmentSupplierService;

/**
 * Created by Game330 on 2016-05-13.
 */
public class TreatmentSupplierServiceImpl  extends IntentService implements TreatmentSupplierService {
    /*I'm using intent service to implement my services because my services don't send or
return anything back to the user, they just saves data to the database*/
    private final TreatmentSuplierRepository repository;

    private static final String ACTION_ADD = "package www.cput.za.animalpound.services.treatment.Impl.ADD";
    private static final String ACTION_REMOVE = "package www.cput.za.animalpound.services.treatment.Impl.REMOVE";

    private static final String EXTRA_ADD = "package www.cput.za.animalpound.services.treatment.Impl.ADD";

    private static TreatmentSupplierServiceImpl service = null;

    public static TreatmentSupplierServiceImpl getInstance() {
        if (service == null)
            service = new TreatmentSupplierServiceImpl();
        return service;
    }

    private TreatmentSupplierServiceImpl() {
        super("TreatmentServiceImpl");
        repository = new TreatmentSupplierRepoImpl(App.getAppContext());

    }

    @Override
    public void addTreatmentSupplier(Context context) {
        Intent intent = new Intent(context, TreatmentServiceImpl.class);
        intent.setAction(ACTION_ADD);
        context.startService(intent);
    }

    @Override
    public void removeTreatmentSupplier(Context context) {
        Intent intent = new Intent(context, TreatmentServiceImpl.class);
        intent.setAction(ACTION_REMOVE);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final TreatmentSupplier treatmentSupplier = (TreatmentSupplier) intent.getSerializableExtra(EXTRA_ADD);
                saveTreatmentSupplier(treatmentSupplier);
            } else if (ACTION_REMOVE.equals(action)) {
                removeAll();
            }
        }
    }

    private void removeAll() {
        repository.deleteAll();
    }

    private void saveTreatmentSupplier(TreatmentSupplier treatmentSupplier) {
        TreatmentSupplier trtment = new TreatmentSupplier.Builder()
                .code(treatmentSupplier.getSupplierCode())
                .supplierName(treatmentSupplier.getSupplierName())
                .treatmentName(treatmentSupplier.getTreetmentName())
                .build();
        TreatmentSupplier savedTreatmentSupplier = repository.save(trtment);

    }
}
