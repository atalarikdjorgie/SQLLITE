package com.si61.databasesql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context ctx; //atribut atau variabel
    private static final String DATABASE_NAME = "db_mahasiswa";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tbl_mahasiswa";
    private static final String FIELD_ID = "id";
    private static final String FIELD_NPM = "npm";
    private static final String FIELD_NAMA = "nama";
    private static final String FIELD_PRODI = "prodi";

    public DatabaseHelper (@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    } //constructor (nama class)

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIELD_NPM + " CHAR(10), " +
                FIELD_NAMA + " VARCHAR(50), " +
                FIELD_PRODI + " VARCHAR(75)" +
                ");";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { // method ada parameter
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);

    }

    // METHODE UNTUK TAMBAH DATA
    public long tambahData(String npm, String nama, String prodi) { // method ada parameter
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIELD_NPM, npm);
        cv.put(FIELD_NAMA, nama);
        cv.put(FIELD_PRODI, prodi);

        long eksekusi = db.insert(TABLE_NAME, null, cv);
        return eksekusi;
    }

    public Cursor bacaDataDestinasi() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor varCursor = null;
        if (db != null) {
            varCursor = db.rawQuery(query, null);
        }

        return varCursor;
    }

    // METHODE UNTUK UBAH DATA
    public long ubahData(String id, String npm, String nama, String prodi) { // method ada parameter
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIELD_NPM, npm);
        cv.put(FIELD_NAMA, nama);
        cv.put(FIELD_PRODI, prodi);

        long eksekusi = db.update(TABLE_NAME, cv, "id = ?", new String[]{id});
        return eksekusi;
    }

    // METHODE UNTUK HAPUS DATA
    public long hapusData(String id) { // method ada parameter
        SQLiteDatabase db = this.getWritableDatabase();

        long eksekusi = db.delete(TABLE_NAME, "id = ?", new String[]{id});
        return eksekusi;
    }
}
