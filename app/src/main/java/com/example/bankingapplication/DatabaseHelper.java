package com.example.bankingapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9249731911,'Yash',8736.00,'Yash@gmail.com','XXXXXXXXXXXX5678','ABC09876543')");
        db.execSQL("insert into user_table values(1530580129,'Satendra',82328.67,'Satendra@gmail.com','XXXXXXXXXXXX6578','BCA98765432')");
        db.execSQL("insert into user_table values(2856393374,'Shubham',19231.56,'Shubham@gmail.com','XXXXXXXXXXXX6785','BCE18765432')");
        db.execSQL("insert into user_table values(8261290288,'Mukul',2321.01,'Mukul@gmail.com','XXXXXXXXXXXX6875','ABD86543210')");
        db.execSQL("insert into user_table values(2868636105,'Ritik',233323.48,'Ritik@gmail.com','XXXXXXXXXXXX7685','AED09876576')");
        db.execSQL("insert into user_table values(9720118933,'Saurabh',42343.16,'Saurabh@gmail.com','XXXXXXXXXXXX7586','BCA65432109')");
        db.execSQL("insert into user_table values(8828304365,'Gagan',340383.00,'Gagan@gmail.com','XXXXXXXXXXXX7865','CAB54321098')");
        db.execSQL("insert into user_table values(2390654076,'Devansh',8784.22,'Devansh@gmail.com','XXXXXXXXXXXX8765','ABC10987654')");
        db.execSQL("insert into user_table values(9389685666,'Gaurav',342232.46,'Gaurav@gmail.com','XXXXXXXXXXXX8675','ABE43210987')");
        db.execSQL("insert into user_table values(7842995913,'Anuj',2312.90,'Anuj@gmail.com','XXXXXXXXXXXX8567','CAB21098765')");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}

