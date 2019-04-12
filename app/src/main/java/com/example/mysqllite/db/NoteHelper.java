package com.example.mysqllite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mysqllite.Note;

import java.util.ArrayList;
import java.util.Currency;


import static android.provider.BaseColumns._ID;
import static com.example.mysqllite.db.DatabaseContract.NoteColumns.DATE;
import static com.example.mysqllite.db.DatabaseContract.NoteColumns.DESCRIPTION;
import static com.example.mysqllite.db.DatabaseContract.NoteColumns.TITLE;
import static com.example.mysqllite.db.DatabaseContract.TABLE_NAME;

public class NoteHelper {
//    mengakomodasi kebutuhan DML
    private static final String DATABSE_TABLE = TABLE_NAME;
    private static DatabaseHelper databaseHelper;
    private static NoteHelper INSTANCE;
    private static SQLiteDatabase database;

//    Ciri khas dari sebuah kelas Singleton terletak pada Constructor yang memiliki modifier private.
    private NoteHelper(Context context){
        databaseHelper = new DatabaseHelper(context);
    }
//    metode yang nantinya akan digunakan untuk menginisiasi database.
//    Pembuatan instance dari sebuah kelas Singleton bisa ditambahkan pada sebuah static method.
    public static NoteHelper getInstance(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new NoteHelper(context);
                }
            }
        }
        return INSTANCE;
    }
//    metode untuk membuka dan menutup koneksi ke database-nya.
    public void open() throws SQLException{
        database = databaseHelper.getWritableDatabase();
    }
    public void close(){
        databaseHelper.close();
        if (database.isOpen())
            database.close();
    }
//    metode untuk melakukan proses CRUD-nya, metode pertama adalah untuk mengambil data.
    public ArrayList<Note> getAllNotes(){
        ArrayList<Note> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABSE_TABLE,null,null,null,null,null,_ID+" ASC",null);
        cursor.moveToFirst();
        Note note;
        if (cursor.getCount()>0){
            do {
                note = new Note();
                note.setId(cursor.getColumnIndexOrThrow(_ID));
                note.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                note.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
                note.setDate(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));
                arrayList.add(note);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }
//    /metode untuk menyimpan data.
    public long insertNote(Note note){
        ContentValues args = new ContentValues();
        args.put(TITLE,note.getTitle());
        args.put(DESCRIPTION,note.getDescription());
        args.put(DATE,note.getDate());
        return database.insert(DATABSE_TABLE,null,args);
    }
//    metode untuk memperbaharui data
    public int updateNote(Note note){
        ContentValues args = new ContentValues();
        args.put(TITLE,note.getTitle());
        args.put(DESCRIPTION,note.getDescription());
        args.put(DATE,note.getDate());
        return database.update(DATABSE_TABLE,args,_ID+"= '"+note.getId()+"'",null);
    }
//    metode untuk menghapus data
    public int deleteNote(int id){
        return database.delete(TABLE_NAME,_ID+"= '"+id+"'",null);
    }
}
//    Kelas di atas menggunakan sebuah pattern yang bernama Singleton Pattern