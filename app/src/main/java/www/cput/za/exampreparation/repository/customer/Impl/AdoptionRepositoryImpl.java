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
import www.cput.za.animalpound.domain.Adoption;
import www.cput.za.animalpound.repository.customer.AdoptionRepository;
import www.cput.za.animalpound.repository.customer.CustomerRepository;

/**
 * Created by Game330 on 2016-04-27.
 */
public class AdoptionRepositoryImpl extends SQLiteOpenHelper implements AdoptionRepository {

    public static final String TABLE_NAME = "adoption";
    private SQLiteDatabase db;



    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ADOPTIONDATE = "adoptionDate";
    public static final String COLUMN_COMMENT = "comment";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ADOPTIONDATE + " TEXT UNIQUE NOT NULL , "
            + COLUMN_COMMENT + " TEXT NOT NULL );";


    public AdoptionRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Adoption findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_ADOPTIONDATE,
                        COLUMN_COMMENT},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Adoption adoption = new Adoption.Builder()
                    .adoptionId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .adoptionDate(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_ADOPTIONDATE))))
                    .comment(cursor.getString(cursor.getColumnIndex(COLUMN_COMMENT)))
                    .build();
            return adoption;
        } else {
            return null;
        }
    }

    @Override
    public Adoption save(Adoption entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getAdoptionId());
        values.put(COLUMN_ADOPTIONDATE, entity.getAdoptionDate().toString());
        values.put(COLUMN_COMMENT, entity.getComment());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Adoption insertedEntity = new Adoption.Builder()
                .copy(entity)
                .adoptionId(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Adoption update(Adoption entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getAdoptionId());
        values.put(COLUMN_ADOPTIONDATE, entity.getAdoptionDate().toString());
        values.put(COLUMN_COMMENT, entity.getComment());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getAdoptionId())}
        );
        return entity;
    }

    @Override
    public Adoption delete(Adoption entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getAdoptionId())});
        return entity;
    }

    @Override
    public Set<Adoption> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Adoption> adoptions = new HashSet<>();
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Adoption adopt = new Adoption.Builder()
                        .adoptionId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .adoptionDate(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_ADOPTIONDATE))))
                        .comment(cursor.getString(cursor.getColumnIndex(COLUMN_COMMENT)))
                        .build();
                adoptions.add(adopt);
            } while (cursor.moveToNext());
        }
        return adoptions;
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
