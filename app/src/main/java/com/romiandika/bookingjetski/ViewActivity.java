package com.romiandika.bookingjetski;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ViewActivity extends AppCompatActivity {
    TextView txtNamaLengkap;
    TextView txtTglSewa;
    TextView txtUmur;
    TextView txtGender;
    TextView txtModeljetski;
    String namaLengkap;
    String tglSewa;
    String umur;
    String gender;
    String modeljetski;

    private DataHelper db;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        db = new DataHelper(this);
        userList = db.selectUserData();


        txtNamaLengkap = findViewById(R.id.isi_namalengkap);
        txtTglSewa = findViewById(R.id.isi_tgl_sewa);
        txtUmur = findViewById(R.id.isi_umur);
        txtGender = findViewById(R.id.isi_gender);
        txtModeljetski = findViewById(R.id.isi_modeljetski);

        namaLengkap = userList.get(userList.size()-1).getNamaLengkap();
        tglSewa = userList.get(userList.size()-1).getTglSewa();
        umur = userList.get(userList.size()-1).getUmur();
        gender = userList.get(userList.size()-1).getGender();
        modeljetski = userList.get(userList.size()-1).getModeljetski();

        txtNamaLengkap.setText(namaLengkap);
        txtTglSewa.setText(tglSewa);
        txtUmur.setText(umur);
        txtGender.setText(gender);
        txtModeljetski.setText(modeljetski);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Menampilkan Activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"Menjeda Activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Toast.makeText(this," Memulai Activity Kembali", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Menghancurkan activity", Toast.LENGTH_SHORT).show();
    }


}