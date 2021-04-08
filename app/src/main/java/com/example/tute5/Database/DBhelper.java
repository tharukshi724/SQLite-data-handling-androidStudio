package com.example.tute5.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "UserInfo.db";
    public DBhelper(Context context ) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String SQL_CREATE_ENTRIES =
               "CREATE TABLE " + UsersMatser.Users.TABLE_NAME+"("+
               UsersMatser.Users._ID+"INTEGER PRIMARY KEY,"+
               UsersMatser.Users.COLUMN_NAME_USERNAME+"TEXT,"+
               UsersMatser.Users.COLUMN_NAME_PASSWORD+"TEXT)";

       db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addInfo(String username, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UsersMatser.Users.COLUMN_NAME_USERNAME,username);
        values.put(UsersMatser.Users.COLUMN_NAME_PASSWORD,password);

        long newRowId = db.insert(UsersMatser.Users.TABLE_NAME,null,values);
        return newRowId;


    }

    public List readAllinfo(){

        SQLiteDatabase db = getReadableDatabase();

       String[] projection = {

               UsersMatser.Users._ID,
               UsersMatser.Users.COLUMN_NAME_USERNAME,
               UsersMatser.Users.COLUMN_NAME_PASSWORD
       };

       String sortOrder = UsersMatser.Users.COLUMN_NAME_USERNAME + "DESC";

       Cursor cursor = db.query(
               UsersMatser.Users.TABLE_NAME,
               projection,
               null,
               null,
               null,
               null,
               sortOrder

       );

      List userNames = new ArrayList<>();
        List passwords = new ArrayList<>();

        while(cursor.moveToNext()){

            String username = cursor.getString(cursor.getColumnIndexOrThrow(UsersMatser.Users.COLUMN_NAME_USERNAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UsersMatser.Users.COLUMN_NAME_PASSWORD));
            userNames.add(username);
            passwords.add(password);

        }

        cursor.close();
        return userNames;






    }

    public int deleteInfo(String username){
        SQLiteDatabase db = getReadableDatabase();

        String selection = UsersMatser.Users.COLUMN_NAME_USERNAME +"LIKE ?";
        String[] selectionArgs = {username};

        db.delete(UsersMatser.Users.TABLE_NAME,selection,selectionArgs);
        return 0;
    }

    public void updateInfo(String username,String password){

        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersMatser.Users.COLUMN_NAME_PASSWORD,password);

        String selection = UsersMatser.Users.COLUMN_NAME_USERNAME + "LIKE?";
        String[] selectionArgs = {username};

        int count = db.update(
                UsersMatser.Users.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
    }
}
