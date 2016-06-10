package www.cput.za.animalpound.repository.schedule.impl;

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
import www.cput.za.animalpound.domain.Schedule;
import www.cput.za.animalpound.repository.schedule.ScheduleRepository;

/**
 * Created by Game330 on 2016-04-28.
 */
public class ScheduleRepositoryImpl extends SQLiteOpenHelper implements ScheduleRepository {
    public static final String TABLE_NAME = "schedule";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "scheduleId";
    public static final String COLUMN_ACTIVITY = "activity";
    public static final String COLUMN_TIME = "time";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ACTIVITY  + " TEXT  NOT NULL , "
            + COLUMN_TIME + " TEXT NOT NULL);";


    public ScheduleRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Schedule findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_ACTIVITY,
                        COLUMN_TIME},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Schedule schedule = new Schedule.Builder()
                    .scheduleId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .activity(cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY)))
                    .time(cursor.getString(cursor.getColumnIndex(COLUMN_TIME)))
                    .build();
            return schedule;
        } else {
            return null;
        }
    }

    @Override
    public Schedule save(Schedule entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getScheduleId());
        values.put(COLUMN_ACTIVITY, entity.getActivity());
        values.put(COLUMN_TIME, entity.getTime());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Schedule insertedEntity = new Schedule.Builder()
                .copy(entity)
                .scheduleId(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Schedule update(Schedule entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getScheduleId());
        values.put(COLUMN_ACTIVITY, entity.getActivity());
        values.put(COLUMN_TIME, entity.getTime());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getScheduleId())}
        );
        return entity;
    }

    @Override
    public Schedule delete(Schedule entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getScheduleId())});
        return entity;
    }

    @Override
    public Set<Schedule> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Schedule> schedule = new HashSet<>();
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Schedule setting = new Schedule.Builder()
                        .scheduleId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .activity(cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY)))
                        .time(cursor.getString(cursor.getColumnIndex(COLUMN_TIME)))
                        .build();
                schedule.add(setting);
            } while (cursor.moveToNext());
        }
        return schedule;
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
