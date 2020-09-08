package com.example.samsungapp1.ui.dashboard;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class GetRandomFromCountries {

    MyDataBase helper;
    SQLiteDatabase countries;
    public GetRandomFromCountries(MyDataBase helper) {
        this.helper = helper;
        countries = helper.getReadableDatabase();
    }

    public String[] getCountryAndCapital() {
        int id = (int) (Math.random() * 188 + 1);
        Cursor cursor = countries.query(
                MyDataBase.TABLE_NAME,
                new String[]{MyDataBase.KEY_COUNTRY, MyDataBase.KEY_CAPITAL},
                "_id = " + id,
                null,
                null,
                null,
                null
        );
        cursor.moveToFirst();
        String country = cursor.getString(cursor.getColumnIndex(MyDataBase.KEY_COUNTRY)),
               capital =  cursor.getString(cursor.getColumnIndex(MyDataBase.KEY_CAPITAL));
        return new String[]{country, capital};
    }
}
