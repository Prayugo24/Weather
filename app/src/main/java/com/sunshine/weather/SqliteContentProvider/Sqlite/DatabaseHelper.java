package com.sunshine.weather.SqliteContentProvider.Sqlite;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Randy Arba on 11/16/17.
 * This apps contains WeatherApp
 *
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;
    private Resources resources;

    static final String TAG = DatabaseHelper.class.getSimpleName();
    static final String DATABASE_NAME = "user.db";
    static final Integer DATABASE_VERSION = 1;

    static final String KEY_ID = "id";
    static final String NAMA = "nama";
    static final String HOBBY = "hobby";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_TABLE_DATABASE = "CREATE TABLE " + TAG +
                " ( " +
                KEY_ID + " INTEGER PRIMARY KEY, " +
                NAMA + " TEXT, " +
                HOBBY + " TEXT" +
                " ) ";

        Log.e("query",CREATE_TABLE_DATABASE);
        sqLiteDatabase.execSQL(CREATE_TABLE_DATABASE);
        try {
            this.sqLiteDatabase = sqLiteDatabase;
        } catch (Exception e){
            Log.e("SqlDatabase","Error");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int lama, int baru) {
        if (lama != baru) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TAG);
            onCreate(sqLiteDatabase);
        }
    }
}
