package com.example.bitirme;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "sqllite_database";//database adı


    private static final String TABLE_NAME = "kitap_listesi";
    private static final String TABLE_SAY = "TABLO_SAY";
    private static final String TABLE_EA = "TABLO_EA";
    private static final String TABLE_SOZ = "TABLO_SOZ";
    private static String Universite = "Universite";
    private static String Bolum_ID = "id";
    private static String Bolum = "Bolum";
    private static String Taban_puan = "TabanPuan";
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Databesi oluşturuyoruz.Bu methodu biz çağırmıyoruz. Databese de obje oluşturduğumuzda otamatik çağırılıyor.
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + Bolum_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Universite + " TEXT,"
                + Bolum + " TEXT,"
                + Taban_puan + " TEXT" + ")";
        String CREATE_TABLESAY = "CREATE TABLE " + TABLE_SAY + "("
                + Bolum_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Universite + " TEXT,"
                + Bolum + " TEXT,"
                + Taban_puan + " TEXT" + ")";
        String CREATE_TABLEEA = "CREATE TABLE " + TABLE_EA + "("
                + Bolum_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Universite + " TEXT,"
                + Bolum + " TEXT,"
                + Taban_puan + " TEXT" + ")";
        String CREATE_TABLESOZ = "CREATE TABLE " + TABLE_SOZ + "("
                + Bolum_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Universite + " TEXT,"
                + Bolum + " TEXT,"
                + Taban_puan + " TEXT" + ")";
        db.execSQL(CREATE_TABLESOZ);
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLESAY);
        db.execSQL(CREATE_TABLEEA);

    }
public void verigir(String s) {
    SQLiteDatabase db = this.getWritableDatabase();
    db.execSQL(s);

}


    public void deletedatabase(int table) {

        //Bunuda uygulamada kullanmıyoruz. Tüm verileri siler. tabloyu resetler.
        SQLiteDatabase db = this.getWritableDatabase();
        if( table==R.id.Soz_puan)   db.delete(TABLE_SOZ, null, null);
        if( table==R.id.Ea_puan) db.delete(TABLE_EA, null, null);
        if( table==R.id.TYT_puan) db.delete(TABLE_NAME, null, null);
        if( table==R.id.Say_puan)db.delete(TABLE_SAY, null, null);


    }


    public ArrayList<Bolum> satıroku(String s1,int min,int max,int table){
        ArrayList<Bolum> secilenler=new ArrayList<Bolum>();
        String[] selectionArgs={"%"+s1+"%"};
        String selectQuery="";
        if( table==R.id.Soz_puan) selectQuery = "SELECT * FROM " + TABLE_SOZ+ " WHERE "+Bolum+" LIKE ? AND "+Taban_puan+" <="+max+" AND "+Taban_puan+" >="+min;
        if( table==R.id.Ea_puan) selectQuery = "SELECT * FROM " + TABLE_EA+ " WHERE "+Bolum+" LIKE ? AND "+Taban_puan+" <="+max+" AND "+Taban_puan+" >="+min;
        if( table==R.id.TYT_puan) selectQuery = "SELECT * FROM " + TABLE_NAME+ " WHERE "+Bolum+" LIKE ? AND "+Taban_puan+" <="+max+" AND "+Taban_puan+" >="+min;
        if( table==R.id.Say_puan) selectQuery = "SELECT * FROM " + TABLE_SAY+ " WHERE "+Bolum+" LIKE ? AND "+Taban_puan+" <="+max+" AND "+Taban_puan+" >="+min;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, selectionArgs);
        cursor.moveToFirst();

        while(cursor.isAfterLast()==false)
        {  Bolum yenibolum=new Bolum();
           yenibolum.setUniversite_adi( cursor.getString(1));
           yenibolum.setSehir(cursor.getString(2));
           yenibolum.setPuan(cursor.getString(3));

            secilenler.add(yenibolum);
            cursor.moveToNext();

        }
        cursor.close();
      //  db.close();
        return secilenler;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
       db.execSQL(" DROP TABLE IF EXİSTS " + TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXİSTS " + TABLE_SAY);
        db.execSQL(" DROP TABLE IF EXİSTS " + TABLE_EA);
        db.execSQL(" DROP TABLE IF EXİSTS " + TABLE_SOZ);
        // TODO Auto-generated method stub

    }

}