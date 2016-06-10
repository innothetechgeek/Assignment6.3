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

import www.cput.za.animalpound.domain.Animal;
import www.cput.za.animalpound.conf.DBConstants;
import www.cput.za.animalpound.repository.animal.AnimalRepository;

/**
 * Created by Game330 on 2016-04-25.
 */
public class AnimalRepositoryImpl extends SQLiteOpenHelper
implements AnimalRepository {

    public static final String TABLE_NAME = "Animal";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ANIMALID = "animalId";
    public static final String COLUMN_BREED = "breed";
    public static final String COLUMN_WEIGHT = "weight";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ANIMALID + " TEXT UNIQUE NOT NULL , "
            + COLUMN_BREED + " TEXT NOT NULL , "
            + COLUMN_WEIGHT  + " TEXT NOT NULL);";






    public AnimalRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Animal findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ANIMALID,
                        COLUMN_BREED,
                        COLUMN_WEIGHT},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Animal animal = new Animal.Builder()
                    .animalId((Long.parseLong(cursor.getString(cursor.getColumnIndex(COLUMN_ANIMALID)))))
                    .weight(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUMN_WEIGHT))))
                    .breed(cursor.getString(cursor.getColumnIndex(COLUMN_BREED)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }

    @Override
    public Animal save(Animal entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put( COLUMN_ANIMALID, entity.getAnimalId());
        values.put( COLUMN_BREED, entity.getbreed());
        values.put(COLUMN_WEIGHT, entity.getWeight());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Animal insertedEntity = new Animal.Builder()
                .copy(entity)
                .animalId(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Animal update(Animal entity) {

        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put( COLUMN_ANIMALID, entity.getAnimalId());
        values.put( COLUMN_BREED, entity.getbreed());
        values.put(COLUMN_WEIGHT, entity.getWeight());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getAnimalId())}
        );
        return entity;
    }

    @Override
    public Animal delete(Animal entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getAnimalId())});
        return entity;
    }

    @Override
    public Set<Animal> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Animal> animals = new HashSet<>();

        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Animal animal2 = new Animal.Builder()
                        .animalId((Long.parseLong(cursor.getString(cursor.getColumnIndex(COLUMN_ANIMALID)))))
                        .weight(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUMN_WEIGHT))))
                        .breed(cursor.getString(cursor.getColumnIndex(COLUMN_BREED)))
                        .build();
                animals.add(animal2);
            } while (cursor.moveToNext());
        }
        return animals;
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


