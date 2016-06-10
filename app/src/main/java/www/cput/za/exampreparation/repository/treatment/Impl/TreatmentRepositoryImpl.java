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

import www.cput.za.animalpound.conf.AppUtil;
import www.cput.za.animalpound.conf.DBConstants;
import www.cput.za.animalpound.domain.Treatment;
import www.cput.za.animalpound.repository.treatment.TreatmentRepository;

/**
 * Created by Game330 on 2016-04-28.
 */
public class TreatmentRepositoryImpl extends SQLiteOpenHelper implements TreatmentRepository {
    public static final String TABLE_NAME = "treatment";
    private SQLiteDatabase db;

    public static final String COLUMN_TREATMENTNO = "treatmentNO";
    public static final String COLUMN_EXPIRYDATE = "expiryDate";
    public static final String COLUMN_INSTRUCTIONS = "instructions";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_TREATMENTNO + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_EXPIRYDATE + " TEXT  NOT NULL , "
            + COLUMN_INSTRUCTIONS + " TEXT  NOT NULL );";


    public TreatmentRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Treatment findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_TREATMENTNO,
                        COLUMN_EXPIRYDATE,
                        COLUMN_INSTRUCTIONS},
                COLUMN_TREATMENTNO + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Treatment treatment = new Treatment.Builder()
                    .treatmentNO(cursor.getLong(cursor.getColumnIndex(COLUMN_TREATMENTNO)))
                    .ExpiryDate(cursor.getString(cursor.getColumnIndex(COLUMN_EXPIRYDATE)))
                    .Instructions(cursor.getString(cursor.getColumnIndex(COLUMN_INSTRUCTIONS)))
                    .build();

            return treatment;
        } else {
            return null;
        }
    }



    @Override
    public Treatment save(Treatment entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_TREATMENTNO, entity.getTreatmentNO());
        values.put(COLUMN_EXPIRYDATE, entity.getExpiryDate());
        values.put(COLUMN_INSTRUCTIONS, entity.getInstructions());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Treatment insertedEntity = new Treatment.Builder()
                .copy(entity)
                .treatmentNO(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Treatment update(Treatment entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_TREATMENTNO, entity.getTreatmentNO());
        values.put(COLUMN_EXPIRYDATE, entity.getExpiryDate());
        values.put(COLUMN_INSTRUCTIONS, entity.getInstructions());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_TREATMENTNO + " =? ",
                new String[]{String.valueOf(entity.getTreatmentNO())}
        );
        return entity;
    }

    @Override
    public Treatment delete(Treatment entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.delete(
                TABLE_NAME,
                COLUMN_TREATMENTNO+ " =? ",
                new String[]{String.valueOf(entity.getTreatmentNO())});
        return entity;
    }

    @Override
    public Set<Treatment> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Treatment> treatmentSet = new HashSet<>();
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Treatment treatment = new Treatment.Builder()
                        .treatmentNO(cursor.getLong(cursor.getColumnIndex(COLUMN_TREATMENTNO)))
                        .ExpiryDate(cursor.getString(cursor.getColumnIndex(COLUMN_EXPIRYDATE)))
                        .Instructions (cursor.getString(cursor.getColumnIndex(COLUMN_INSTRUCTIONS)))
                        .build();
                treatmentSet.add(treatment);
            } while (cursor.moveToNext());
        }
        return treatmentSet;
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
