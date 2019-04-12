package com.example.mysqllite.db;

import android.provider.BaseColumns;

public class DatabaseContract {
//    Kelas contract ini akan digunakan untuk mempermudah akses nama tabel dan nama kolom di dalam database kita.
    static String TABLE_NAME ="note";

    static final class NoteColumns implements BaseColumns{
//        Dengan mengimplementasikan BaseColumns akan secara otomatis kolom _id akan secara otomatis menjadi bagian dari kolom yang ditentukan pada sebuah tabel.
        static String TITLE = "title";
        static String DESCRIPTION = "description";
        static String DATE= "date";

    }
}
