package com.imtiaj.m5education;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;

//import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {

    String JsobjImg="",JsobjTitle="",JsobjDes="";


    public DBhelper( Context context) {
        super(context, Dbconncet.DB_NAME,null,Dbconncet.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String InspireImg = "create table if not exists "+Dbconncet.INSPIRE_TABLE_NAME+"(id integer primary key not null ,"
        +Dbconncet.INSPIRE_IMG_COLUMN_NAME+ "text,"+Dbconncet.INSPIRE_TITLE_COLUMN_NAME+" text)";  /*"+Dbconncet.INSPIRE_DDES_COLUMN_NAME+" text*/
        db.execSQL(InspireImg);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists "+ Dbconncet.INSPIRE_TABLE_NAME);
        onCreate(db);
    }

    public void inspireImageSave(String table, String Image,String Title){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Dbconncet.INSPIRE_IMG_COLUMN_NAME,Image);
        contentValues.put(Dbconncet.INSPIRE_TITLE_COLUMN_NAME,Title);
        /*contentValues.put(Dbconncet.INSPIRE_DDES_COLUMN_NAME,Des);*/
        sqLiteDatabase.insert(table,null,contentValues);
    }

    public void inspireImageRead(String table, SQLiteDatabase sqLiteDatabase){
        String sqlquery = " select * from "+table;
        Cursor cursor = sqLiteDatabase.rawQuery(sqlquery,null);
        if (cursor!=null && cursor.moveToFirst()){
            do {
                JsobjImg = cursor.getString(cursor.getColumnIndex(Dbconncet.INSPIRE_IMG_COLUMN_NAME));
                JsobjTitle= cursor.getString(cursor.getColumnIndex(Dbconncet.INSPIRE_TITLE_COLUMN_NAME));
                JsobjDes= cursor.getString(cursor.getColumnIndex(Dbconncet.INSPIRE_DDES_COLUMN_NAME));
            } while (cursor.moveToNext());
        }

    }

}
