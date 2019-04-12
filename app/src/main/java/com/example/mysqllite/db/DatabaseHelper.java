package com.example.mysqllite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.mysqllite.db.DatabaseContract.TABLE_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbnoteapp";
    private static final int DATABSE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_NOTE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            TABLE_NAME,
            DatabaseContract.NoteColumns._ID,
            DatabaseContract.NoteColumns.TITLE,
            DatabaseContract.NoteColumns.DESCRIPTION,
            DatabaseContract.NoteColumns.DATE
    );

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_NOTE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
//    Tanggung jawab utama dari kelas di atas adalah menciptakan database dengan tabel yang dibutuhkan dan handle ketika terjadi perubahan skema pada tabel(terjadi pada metode onUpgrade()).
}
