package com.example.pc_76.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pc-76 on 11/5/18.
 */

public class DatabaseHelper {
    static final String KEY_ID="_id";
    static final String KEY_NAME="name";
    static final String KEY_EMAIL="email";

    static final String DATABASE_NAME="SAMPLEDB";
    static final String TABLE_NAME="contacts";
    static final int VERSION=1;

    static final String CREATE_TABLE="create table contacts (_id integer primary key " +
            "autoincrement, name text not null, email text not null )";
    SQLiteHelper dbHelper;
    SQLiteDatabase db;
    DatabaseHelper(Context context){
        dbHelper= new SQLiteHelper(context);
    }

    private static class SQLiteHelper extends SQLiteOpenHelper{

        public SQLiteHelper(Context context) {
            super(context, DATABASE_NAME, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{db.execSQL(CREATE_TABLE);}
            catch(SQLException ex){}
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists contacts");
            onCreate(db);
        }
    }

    public DatabaseHelper open()throws SQLException{
       db=dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        db.close();
    }

    public boolean insertContact(String name,String email){
        ContentValues values= new ContentValues();
        values.put(KEY_NAME,name);
        values.put(KEY_EMAIL,email);
        return db.insert(TABLE_NAME,null,values)>0;
    }
    public boolean deleteContact(long id){
        return db.delete(TABLE_NAME,KEY_ID+"="+id,null)>0;
    }
    public Cursor getAllContacts(){
        Cursor cursor;
        cursor= db.query(TABLE_NAME,new String[]{KEY_ID,KEY_NAME,KEY_EMAIL},null,
                null,null,null,null);
        return cursor;
    }
    public Cursor getContact(String id)throws SQLException{
        Cursor cursor;
        cursor= db.query(true,TABLE_NAME,new String[]{KEY_ID,KEY_NAME,KEY_EMAIL},
                KEY_NAME+"="+id,null,null,null,null,
                null);
        if(cursor!=null)
            cursor.moveToFirst();
        return  cursor;
    }
    public boolean updateContact(long id,String name,String email){
        ContentValues values= new ContentValues();
        values.put(KEY_NAME,name);
        values.put(KEY_EMAIL,email);
        return db.update(TABLE_NAME,values,KEY_ID+"="+id,null)>0;
    }
}
