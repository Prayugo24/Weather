package com.sunshine.weather.SqliteContentProvider.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sunshine.weather.SqliteContentProvider.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Randy Arba on 11/16/17.
 * This apps contains WeatherApp
 *
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

public class DatabaseImp {

    private Context contextLocal;
    private DatabaseHelper databaseHelper;
    private static DatabaseImp databaseImp;

    public static synchronized DatabaseImp getInstance(Context context){
        if (databaseImp == null) {
            databaseImp = new DatabaseImp(context);
        }
        return databaseImp;
    }

    DatabaseImp(Context context){
        this.contextLocal = context;
        databaseHelper = new DatabaseHelper(context);
    }

    public void deleteAll(){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            sqLiteDatabase.delete(DatabaseHelper.TAG,null,null);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e){
            Log.e("Error delete db","Error");
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    public void insertDatabase(User user){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAMA,user.getNama());
        contentValues.put(DatabaseHelper.HOBBY,user.getHobby());

        sqLiteDatabase.insert(DatabaseHelper.TAG,null,contentValues);
        sqLiteDatabase.close();
    }

    public List<User> getAllUser(){
        List<User> listUser = new ArrayList<>();

        String queryAll = "SELECT * FROM " + DatabaseHelper.TAG;
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(queryAll,null);

        if (cursor.moveToFirst()){
            do {
                User user = new User();
                user.setNama(cursor.getString(1));
                user.setHobby(cursor.getString(2));
                listUser.add(user);
            } while (cursor.moveToNext());
        }

        Log.e("cursor",String.valueOf(listUser.size()));
        if (listUser.size() != 0){
            Log.e("hasil",listUser.get(0).getNama());
            Log.e("hasil",listUser.get(0).getHobby());
        }
        cursor.close();
        return listUser;
    }
}
