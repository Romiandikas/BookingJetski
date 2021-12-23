package com.romiandika.bookingjetski;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DataHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "FormSewaJetski";
    private static final String TABLE_NAME = "tbl_customer";
    private static final String KEY_ID = "_id";
    private static final String KEY_NAMALENGKAP = "nama_lengkap";
    private static final String KEY_TGLSEWA = "tgl_sewa";
    private static final String KEY_UMUR = "umur";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_MODELJETSKI = "modeljetski";



    public DataHelper(@Nullable Context context) { super(context, DB_NAME, null, DB_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUserTable = "CREATE TABLE " + TABLE_NAME +
                " (" + KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_NAMALENGKAP + " TEXT, " +
                KEY_TGLSEWA + " TEXT, " +
                KEY_UMUR + " TEXT, " +
                KEY_GENDER + " TEXT, " +
                KEY_MODELJETSKI + " TEXT " + ")";
        sqLiteDatabase.execSQL(createUserTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;

        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public void insert (User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAMALENGKAP,user.getNamaLengkap());
        values.put(KEY_TGLSEWA,user.getTglSewa());
        values.put(KEY_UMUR,user.getUmur());
        values.put(KEY_GENDER,user.getGender());
        values.put(KEY_MODELJETSKI,user.getModeljetski());

        db.insert(TABLE_NAME, null, values);
    }

    public List<User> selectUserData() {
        ArrayList<User> users = new ArrayList<User>();

        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {KEY_ID, KEY_NAMALENGKAP, KEY_TGLSEWA, KEY_UMUR, KEY_GENDER, KEY_MODELJETSKI};

        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);

        while (cursor.moveToNext()) {

            int id =cursor.getInt(0);
            String namaLengkap = cursor.getString(1);
            String tglSewa = cursor.getString(2);
            String umur = cursor.getString(3);
            String gender = cursor.getString(4);
            String modeljetski = cursor.getString(5);

            User user = new User();
            user.setId(id);
            user.setNamaLengkap(namaLengkap);
            user.setTglSewa(tglSewa);
            user.setUmur(umur);
            user.setGender(gender);
            user.setModeljetski(modeljetski);

            users.add(user);
        }

        return users;
    }

    public void update(User user){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAMALENGKAP,user.getNamaLengkap());
        values.put(KEY_TGLSEWA,user.getTglSewa());
        values.put(KEY_UMUR,user.getUmur());
        values.put(KEY_GENDER,user.getGender());
        values.put(KEY_MODELJETSKI,user.getModeljetski());

        String whereClause = KEY_ID + " = '" + user.getId() +"'";

        db.update(TABLE_NAME, values,whereClause,null);
    }


    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();

        String whereClause = KEY_ID + " = '" + id + "'";

        db.delete(TABLE_NAME, whereClause, null);
    }
}
