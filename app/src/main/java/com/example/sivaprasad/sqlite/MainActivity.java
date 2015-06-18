package com.example.sivaprasad.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends ActionBarActivity {

    private SQLiteManager sqLiteManager;
    private SQLiteDatabase db;
    private TextView contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
            Get a handler to the database everytime the activity is created.
        */
        sqLiteManager = new SQLiteManager(this,"",null,1);
        Log.d("TAG","DB Handler ");

        contacts = (TextView) findViewById(R.id.contacts);

        updateView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickSubmit(View view) {

        TextView name = (TextView) findViewById(R.id.value_name);
        TextView phone = (TextView) findViewById(R.id.value_number);
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone", phone.getText().toString());
        contentValues.put("customer_name",name.getText().toString());

        db = sqLiteManager.getWritableDatabase();

        long rowId = db.insert("customers",null,contentValues);

        name.setText("");
        phone.setText("");

        updateView();

    }

    public void updateView(){

        /*
            Now, we got a handle to a writable database.
         */
        db = sqLiteManager.getWritableDatabase();

        /*
            Clear the contacts view
         */
        contacts.setText("");

        /*
            Let's retrieve data
            What columns do you need ? Just put them in a string
         */
        //String[] selection = {"id","customer_name"};

        /*
            Pull data from the table. A generic structure called "Cursor"
            will be used to hold data temporarily in memory to hold the result
            of the SELECT query.
         */
        Cursor cursor = db.query("customers",null,null, null, null, null, null );

        /*  Check if we got any results at all
            cursor.moveToFirst does 2 things
            1. Returns a true value if there is at least one row.
            2. Positions the pointer at the first row.
         */
        if (cursor.moveToFirst()){

            /*  Iterate through the cursor to get values.
             */

            while (!cursor.isAfterLast()){
                // String phone1 = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                String customer_name = cursor.getString(cursor.getColumnIndexOrThrow("customer_name"));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                contacts.append(customer_name+"--"+phone+System.getProperty("line.separator"));
                // Move to the next row
                cursor.moveToNext();
            }

            /* I am done with my cursor. Closing it will remove it from memory
             */
            cursor.close();
        }



    }
}
