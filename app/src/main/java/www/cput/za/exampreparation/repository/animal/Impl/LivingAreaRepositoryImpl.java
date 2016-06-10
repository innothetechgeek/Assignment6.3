package www.cput.za.animalpound.repository.animal.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import www.cput.za.animalpound.conf.DBConstants;
import www.cput.za.animalpound.domain.LivingArea;
import www.cput.za.animalpound.repository.animal.LivingAreaRepository;

/**
 * Created by Game330 on 2016-04-27.
 */
public class LivingAreaRepositoryImpl extends SQLiteOpenHelper implements LivingAreaRepository {

    public static final String TABLE_NAME = "LivingArea";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "livingAreaName";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT  NOT NULL);";


    public LivingAreaRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public LivingArea findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final LivingArea livingArea = new LivingArea.Builder()
                    .livingAreaId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .livingAreaName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .build();
            return livingArea;
        } else {
            return null;
        }
    }
    @Override
    public LivingArea save(LivingArea entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getLivingAreaId());
        values.put(COLUMN_NAME, entity.getName());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        LivingArea insertedEntity = new LivingArea.Builder()
                .copy(entity)
                .livingAreaId(new Long(id))
                .build();
        return insertedEntity;
    }



    @Override
    public LivingArea update(LivingArea entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getLivingAreaId());
        values.put(COLUMN_NAME, entity.getName());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getLivingAreaId())}
        );
        return entity;
    }

    @Override
    public LivingArea delete(LivingArea entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getLivingAreaId())});
        return entity;
    }

    @Override
    public Set<LivingArea> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<LivingArea> pollingStations = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                final LivingArea pollingStation = new LivingArea.Builder()
                        .livingAreaId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .livingAreaName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .build();
                pollingStations.add(pollingStation);
            } while (cursor.moveToNext());
        }
        return pollingStations;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME, null, null);
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
