package www.cput.za.animalpound.repository.animal.Impl;

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
import www.cput.za.animalpound.domain.Animal;
import www.cput.za.animalpound.domain.AnimalHealth;
import www.cput.za.animalpound.repository.animal.AnimalHealthRepository;

/**
 * Created by Game330 on 2016-04-26.
 */

public class AnimalHealthRepositoryImpl extends SQLiteOpenHelper implements AnimalHealthRepository {
    public static final String TABLE_NAME = "animalHealth";
    private SQLiteDatabase db;



    public static final String COLUMN_ID = "healthId";
    public static final String COLUMN_CONDITION = "condition";
    public static final String COLUMN_DESCRIPTION = "description";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CONDITION + " TEXT NOT NULL , "
            + COLUMN_DESCRIPTION + " TEXT NOT NULL );";


    public  AnimalHealthRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public AnimalHealth findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_CONDITION,
                        COLUMN_DESCRIPTION},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final AnimalHealth animalHealth = new AnimalHealth.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .Description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                    .condition(cursor.getString(cursor.getColumnIndex(COLUMN_CONDITION)))
                    .build();
            return animalHealth;
        } else {
            return null;
        }
    }

    @Override
    public AnimalHealth save(AnimalHealth entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getHealthID());
        values.put(COLUMN_CONDITION, entity.getCondition());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        AnimalHealth insertedEntity = new AnimalHealth.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public AnimalHealth update(AnimalHealth entity) {

        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getHealthID());
        values.put(COLUMN_CONDITION, entity.getCondition());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getHealthID())}
        );
        return entity;
    }

    @Override
    public AnimalHealth delete(AnimalHealth entity) {

        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getHealthID())});
        return entity;
    }

    @Override
    public Set<AnimalHealth> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<AnimalHealth>  animlHealth = new HashSet<>();
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final AnimalHealth health= new AnimalHealth.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .condition(cursor.getString(cursor.getColumnIndex(COLUMN_CONDITION)))
                        .Description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                        .build();
                animlHealth.add(health);
            } while (cursor.moveToNext());
        }
        return animlHealth;
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
