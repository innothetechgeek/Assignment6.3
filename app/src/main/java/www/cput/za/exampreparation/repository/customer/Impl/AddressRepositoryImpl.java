package www.cput.za.animalpound.repository.customer.Impl;

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
import www.cput.za.animalpound.domain.Address;
import www.cput.za.animalpound.repository.customer.AddressRepository;

/**
 * Created by Game330 on 2016-04-29.
 */
public class AddressRepositoryImpl extends SQLiteOpenHelper implements AddressRepository{

    public static final String TABLE_NAME = "adress";
    private SQLiteDatabase db;


    public static final String COLUMN_ROOM = "room";
    public static final String COLUMN_STREET = "street";
    public static final String COLUMN_SURBURB = "surbub";
    public static final String COLUMN_ZIPCODE = "zipCode";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ROOM + "   TEXT  PRIMARY KEY, "
            + COLUMN_STREET + " TEXT NOT NULL , "
            + COLUMN_SURBURB + " TEXT NOT NULL , "
            + COLUMN_ZIPCODE + " TEXT  NOT NULL );";


    public AddressRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Address findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ROOM,
                        COLUMN_STREET,
                        COLUMN_SURBURB,
                        COLUMN_ZIPCODE},
                COLUMN_ROOM + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Address personalAddress = new Address.Builder()
                    .room(cursor.getString(cursor.getColumnIndex(COLUMN_ROOM)))
                    .street(cursor.getString(cursor.getColumnIndex(COLUMN_STREET)))
                    .surbub(cursor.getString(cursor.getColumnIndex(COLUMN_SURBURB)))
                    .zipCode(cursor.getString(cursor.getColumnIndex(COLUMN_ZIPCODE)))
                    .build();

            return personalAddress;
        } else {
            return null;
        }
    }

    @Override
    public Address save(Address entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_ROOM, entity.getRoom());
        values.put(COLUMN_STREET, entity.getStreet());
        values.put(COLUMN_SURBURB, entity.getSurbub());
        values.put(COLUMN_ZIPCODE, entity.getZipCode());
        Long room = db.insertOrThrow(TABLE_NAME, null, values);
       Address insertedEntity = new Address.Builder()
                .copy(entity)
                .room(new  String(room.toString()))
                .build();
        return insertedEntity;
    }

    @Override
    public Address update(Address entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_ROOM, entity.getRoom());
        values.put(COLUMN_STREET, entity.getStreet());
        values.put(COLUMN_SURBURB, entity.getSurbub());
        values.put(COLUMN_ZIPCODE, entity.getZipCode());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ROOM + " =? ",
                new String[]{String.valueOf(entity.getRoom())}
        );
        return entity;
    }

    @Override
    public Address delete(Address entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.delete(
                TABLE_NAME,
                COLUMN_ROOM + " =? ",
                new String[]{String.valueOf(entity.getRoom())});
        return entity;
    }

    @Override
    public Set<Address> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Address> personAddresses = new HashSet<>();
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Address personAddress = new Address.Builder()
                        .room(cursor.getString(cursor.getColumnIndex(COLUMN_ROOM)))
                        .street(cursor.getString(cursor.getColumnIndex(COLUMN_STREET)))
                        .surbub(cursor.getString(cursor.getColumnIndex(COLUMN_SURBURB)))
                        .zipCode(cursor.getString(cursor.getColumnIndex(COLUMN_ZIPCODE)))
                        .build();
                personAddresses.add(personAddress);
            } while (cursor.moveToNext());
        }
        return personAddresses;
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
