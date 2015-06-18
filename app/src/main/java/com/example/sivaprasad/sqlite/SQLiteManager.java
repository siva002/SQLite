package com.example.sivaprasad.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Siva Prasad on 01-06-2015.
 */

public class SQLiteManager  extends SQLiteOpenHelper{
    public static final String DB_NAME = "questions.db";
    public static final int VERSION = 1;
    public static final String DB_CREATE = "CREATE TABLE customers (phone TEXT, " +
            "customer_name TEXT);";

    /* The database creation does NOT happen here. It just gets a handle
     *
     */
    public SQLiteManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, null , null, VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
        Log.w("TAG", "Database Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("TAG","Upgrading from "+oldVersion + "to " + newVersion);
    }
}
