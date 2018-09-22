package com.poly.dell.qunlsch.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.poly.dell.qunlsch.Constant;
import com.poly.dell.qunlsch.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "BookManager";
    private static final int DATABASE_VERSION = 1;

    public final static String USER_TABLE = "users";
    public final static String COLUMN_USERNAME = "username";
    public final static String COLUMN_PASSWORD = "password";
    public final static String COLUMN_NAME = "name";
    public final static String COLUMN_PHONE_NUMBER = "phone_number";

    public final static String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + USER_TABLE + "(" +
            COLUMN_USERNAME + " VARCHAR PRIMARY KEY," +
            COLUMN_PASSWORD + " VARCHAR," +
            COLUMN_NAME + " VARCHAR," +
            COLUMN_PHONE_NUMBER + " VARCHAR" +
            ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // create User Table
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        if (Constant.isDEBUG) Log.e("CREATE_USER_TABLE", CREATE_USER_TABLE);
    }

    public void insertUser(User user) {

        /*SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_USERNAME, user.getUsername());
        contentValues.put(COLUMN_PASSWORD, user.getPassword());
        contentValues.put(COLUMN_NAME, user.getName());
        contentValues.put(COLUMN_PHONE_NUMBER, user.getPhonenumber());

        long id = db.insert(USER_TABLE, null, contentValues);

        if (Constant.isDEBUG) Log.e("insertUser", "insertUser ID : " + id);

        db.close();*/

    }

    public User getUser(String username) {

        User user = null;

        SQLiteDatabase db = this.getReadableDatabase();

        // Truyen vao Ten bang, array bao gom ten cot, ten cot khoa chinh, gia tri khoa chinh, cac tham so con lai la null

        Cursor cursor = db.query(USER_TABLE, new String[]{COLUMN_USERNAME, COLUMN_PASSWORD, COLUMN_NAME, COLUMN_PHONE_NUMBER}, COLUMN_USERNAME + "=?", new String[]{username}, null, null, null, null);

        // moveToFirst : kiem tra xem cursor co chua du lieu khong, ham nay tra ve gia tri la true or false
        if (cursor != null && cursor.moveToFirst()) {

            String user_name = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));

            String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));

            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));

            String phoneNumber = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER));

            // khoi tao user voi cac gia tri lay duoc
            user = new User(user_name, password, name, phoneNumber);


        }
        cursor.close();
        return user;
    }

    public User getAllUsers() {
        User user = null;
        // Select All Query
        String selectQuery = "SELECT  * FROM " + USER_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String user_name = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));

                String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));

                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));

                String phoneNumber = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER));

                // khoi tao user voi cac gia tri lay duoc
                user = new User(user_name, password, name, phoneNumber);
            } while (cursor.moveToNext());
        }

        db.close();
        return user;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);

        onCreate(sqLiteDatabase);
    }
}
