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
        import www.cput.za.animalpound.domain.AnimalRecord;
        import www.cput.za.animalpound.repository.animal.AnimalRecordRepository;

/**
 * Created by Game330 on 2016-04-27.
 */
public class AnimalRecordRepoImpl extends SQLiteOpenHelper implements AnimalRecordRepository {
    public static final String TABLE_NAME = "animalRecord";
    private SQLiteDatabase db;



    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ARRIVALDATE = "arrivalDate";
    public static final String COLUMN_LEAVINGDATE = "leavingDate";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID+ "TEXT NOT NULL"
            + COLUMN_ARRIVALDATE + " TEXT NOT NULL, "
            + COLUMN_LEAVINGDATE+ " TEXT NOT NULL );";


    public AnimalRecordRepoImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public AnimalRecord findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_ARRIVALDATE,
                        COLUMN_LEAVINGDATE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final AnimalRecord animlRec = new AnimalRecord.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .arrivalDate(cursor.getString(cursor.getColumnIndex(COLUMN_ARRIVALDATE)))
                    .leavingDate(cursor.getString(cursor.getColumnIndex(COLUMN_LEAVINGDATE)))
                    .build();
            return animlRec;
        } else {
            return null;
        }
    }

    @Override
    public AnimalRecord save(AnimalRecord entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_ARRIVALDATE, entity.getarrivalDate());
        values.put(COLUMN_ARRIVALDATE, entity.getleavingDate()   );
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        AnimalRecord insertedEntity = new AnimalRecord.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public AnimalRecord update(AnimalRecord entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_ARRIVALDATE, entity.getarrivalDate());
        values.put(COLUMN_LEAVINGDATE, entity.getleavingDate());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public AnimalRecord delete(AnimalRecord entity) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<AnimalRecord> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<AnimalRecord> elections = new HashSet<>();
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final AnimalRecord election = new AnimalRecord.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .arrivalDate(cursor.getString(cursor.getColumnIndex(COLUMN_ARRIVALDATE)))
                        .leavingDate(cursor.getString(cursor.getColumnIndex(COLUMN_LEAVINGDATE)))
                        .build();
                elections.add(election);
            } while (cursor.moveToNext());
        }
        return elections;
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




































