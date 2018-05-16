package com.example.pc_76.database;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper= new DatabaseHelper(this);
        insertRecord("avnish","panwaravi101@gmail.com");


        readContact("name");
        //readAllContacts();
    }
    public void insertRecord(String name,String email){
        helper.open();
        if(helper.insertContact(name,email))
        //Toast.makeText(this,"record inserted",Toast.LENGTH_SHORT).show();
            Log.d("msg1","created");
        helper.close();
    };
    public void readContact(String id){
        helper.open();
        Log.d("msg2","func");
        Cursor cursor= helper.getContact(id);
        if(cursor.moveToFirst())
        //Toast.makeText(this,cursor.getString(1)+": "+
          //      cursor.getString(2),Toast.LENGTH_SHORT).show();
            Log.d("msg2",cursor.getCount()+"  hgfdg");
        else{
            //Toast.makeText(this,"not found",Toast.LENGTH_SHORT).show();
            Log.d("msg2",cursor.getCount()+"  75777577");
        }
        helper.close();
    }
    public void readAllContacts(){
        helper.open();
        Log.d("msg1","read all");
        Cursor cursor= helper.getAllContacts();
        if(cursor!=null)
            cursor.moveToFirst();
        do{
       // Toast.makeText(this,cursor.getString(1)+": "+
               // cursor.getString(2),Toast.LENGTH_SHORT).show();}
            Log.d("msg",cursor.getString(1));}
                while (cursor.moveToNext());
        helper.close();
    }
    public void deleteContacts(){

    }
}
