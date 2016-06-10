package www.cput.za.animalpound.repository.treatment.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import www.cput.za.animalpound.conf.DBConstants;
import www.cput.za.animalpound.domain.Treatment;
import www.cput.za.animalpound.domain.TreatmentSupplier;
import www.cput.za.animalpound.repository.treatment.TreatmentSuplierRepository;

/**
 * Created by Game330 on 2016-04-28.
 */
public class TreatmentSupplierRepoImpl extends SQLiteOpenHelper implements TreatmentSuplierRepository {
    public static final String TABLE_NAME = "treatmentSupplier";
    private SQLiteDatabase db;

    public static final String COLUMN_CODE= "code";
    public static final String COLUMN_SUPLIERNAME = "supplierName";
    public static final String COLUMN_TREATMENTNAME = "treatmentName";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_CODE + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SUPLIERNAME + " TEXT  NOT NULL , "
            + COLUMN_TREATMENTNAME + " TEXT NOT NULL );";


    public TreatmentSupplierRepoImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public TreatmentSupplier findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_CODE,
                        COLUMN_SUPLIERNAME,
                        COLUMN_TREATMENTNAME},
                COLUMN_CODE + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final TreatmentSupplier treatmentSuplier = new TreatmentSupplier.Builder()
                    .code(cursor.getLong(cursor.getColumnIndex(COLUMN_CODE)))
                    .supplierName(cursor.getString(cursor.getColumnIndex(COLUMN_SUPLIERNAME)))
                    .treatmentName(cursor.getString(cursor.getColumnIndex(COLUMN_TREATMENTNAME)))
                    .build();
            return treatmentSuplier;
        } else {
            return null;
        }
    }

    @Override
    public TreatmentSupplier save(TreatmentSupplier entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_CODE, entity.getSupplierCode());
        values.put(COLUMN_SUPLIERNAME, entity.getSupplierName());
        values.put(COLUMN_TREATMENTNAME, entity.getTreetmentName());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        TreatmentSupplier insertedEntity = new TreatmentSupplier.Builder()
                .copy(entity)
                .code(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public TreatmentSupplier update(TreatmentSupplier entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_CODE, entity.getSupplierCode());
        values.put(COLUMN_SUPLIERNAME, entity.getSupplierName());
        values.put(COLUMN_TREATMENTNAME, entity.getTreetmentName());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_CODE + " =? ",
                new String[]{String.valueOf(entity.getSupplierCode())}
        );
        return entity;
    }

    @Override
    public TreatmentSupplier delete(TreatmentSupplier entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.delete(
                TABLE_NAME,
                COLUMN_CODE + " =? ",
                new String[]{String.valueOf(entity.getSupplierCode())});
        return entity;
    }

    @Override
    public Set<TreatmentSupplier> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<TreatmentSupplier> suppliers = new HashSet<>();
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final TreatmentSupplier setting = new TreatmentSupplier.Builder()
                        .code(cursor.getLong(cursor.getColumnIndex(COLUMN_CODE)))
                        .supplierName(cursor.getString(cursor.getColumnIndex(COLUMN_SUPLIERNAME)))
                        .treatmentName(cursor.getString(cursor.getColumnIndex(COLUMN_TREATMENTNAME)))
                        .build();
                suppliers.add(setting);
            } while (cursor.moveToNext());
        }
        return suppliers;
    }

    @Override
    public int deleteAll() {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

}
