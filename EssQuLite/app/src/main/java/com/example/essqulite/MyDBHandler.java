package com.example.essqulite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import androidx.annotation.Nullable;

public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="items.db";
    public static final  String TABLE_ITEMS="myitems";
    public static final String  COLUMN_ID="_id";
    public static final String COLUMN_ITEMNAME="ItemName";

    public MyDBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String query="create table "+TABLE_ITEMS+"("+
                COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COLUMN_ITEMNAME+" TEXT "+
                ");";

        db.execSQL(query);//Much like statement.execute in JDBC



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_ITEMS); //If our Database changes structure, we drop the existing one and create a new, updated one
        onCreate(db);

    }

    //Add a new row to the table

    public void addNewItems(Items items)
    {
        ContentValues values=new ContentValues(); //Much like using the preparedstatement in JDBC
        //We are essentially creating a capsule of values and then inserting it into the database in bulk.
        //As _id is autoincrement, we need'nt worry about it.
        values.put(COLUMN_ITEMNAME,items.get_itemName());
        //Smart! Takes in the COLUMN NAME as well as the data to be inserted into that COLUMN NAME

        SQLiteDatabase db=getReadableDatabase();
        db.insert(TABLE_ITEMS,null,values);
        db.close(); //Housekeeping stuff to return memory back to android.

    }

    //Delete a row from the database

    public void deleteItems(String itemName)
    {
        SQLiteDatabase db=getReadableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_ITEMS +" WHERE "+ COLUMN_ITEMNAME+ "= \""+itemName+"\";"); // '\' is used as an escape character
    }

    //Print out the database as a string
    public String  dbToString()
    {
        String  dbString="";
        SQLiteDatabase db=getReadableDatabase();
        String query="SELECT * FROM "+TABLE_ITEMS +";";

        //Cursor point to different locations in your database

        Cursor c=db.rawQuery(query,null);
        //Move the cursor to the first row of the results

        c.moveToFirst();

        while (!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex(COLUMN_ITEMNAME))!=null)
            {
               dbString+= c.getString(c.getColumnIndex(COLUMN_ITEMNAME));
               dbString+="\n";
                c.moveToNext();
            }
        }
        db.close();

        return dbString;
    }
}



