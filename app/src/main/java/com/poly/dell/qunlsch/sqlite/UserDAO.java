package com.poly.dell.qunlsch.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.poly.dell.qunlsch.model.User;

public class UserDAO {
    private SQLiteDatabase database;

    public UserDAO(Context context) {
       DatabaseHelper databaseHelper =new DatabaseHelper(context);
       database=databaseHelper.getWritableDatabase();
    }
    public int insertUser(String user, String pass, String name, String sdt) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME,user);
        values.put(DatabaseHelper.COLUMN_PASSWORD,pass);
        values.put(DatabaseHelper.COLUMN_NAME,name);
        values.put(DatabaseHelper.COLUMN_PHONE_NUMBER,sdt);
        try {
            if( database.insert(DatabaseHelper.USER_TABLE,null,values)==-1){
                return -1;
            }
        }catch (Exception e){

        }
        return 1;
    }
    public void updateUser(String user,String name,String sdt) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME,name);
        values.put(DatabaseHelper.COLUMN_PHONE_NUMBER,sdt);
        database.update(DatabaseHelper.USER_TABLE, values, DatabaseHelper.COLUMN_USERNAME +" = ?", new String[]{user});
    }
    public void updatePassWord(String user,String password) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_PASSWORD,password);
        database.update(DatabaseHelper.USER_TABLE, values, DatabaseHelper.COLUMN_USERNAME +" = ?", new String[]{user});
    }
    public Cursor getUser() {
        return database.rawQuery("Select * from "+DatabaseHelper.USER_TABLE, null);
    }
    public void deleteUser(String user) {
        database.delete(DatabaseHelper.USER_TABLE, DatabaseHelper.COLUMN_USERNAME + " = ?",new String[]{user});
    }
    public User getUser(String username) {
        User user = null;
        Cursor cursor = database.query(DatabaseHelper.USER_TABLE, new String[]{DatabaseHelper.COLUMN_USERNAME, DatabaseHelper.COLUMN_PASSWORD, DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_PHONE_NUMBER}, DatabaseHelper.COLUMN_USERNAME + "=?", new String[]{username}, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {

            String user_name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME));

            String password = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD));

            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));

            String phoneNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE_NUMBER));

            user = new User(user_name, password, name, phoneNumber);
        }
        cursor.close();
        return user;
    }
}
