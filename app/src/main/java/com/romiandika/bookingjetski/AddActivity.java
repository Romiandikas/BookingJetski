package com.romiandika.bookingjetski;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    private EditText txtNamaLengkap;
    private String namaLengkap;
    private EditText inputtglsewa;
    private String tglSewa;
    private SeekBar seekBar;
    private TextView lbl_umur;
    private String nilaiUmur;
    private String umur;
    private Button btnDaftar;
    private RadioGroup radioGroup;
    private String gender;
    private CheckBox mjetskiyamaha750, mjetskiseadoo750, mjetskiyamaha1000, mjetskiseadoo1100, mjetskiyamaha1800, mjetskiseadoo1700;
    private RadioButton rblaki;
    private RadioButton rbperempuan;
    private String modeljetski;

    private DataHelper db;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);



        txtNamaLengkap = findViewById(R.id.input_namalengkap);
        inputtglsewa = findViewById(R.id.input_tgl_sewa);
        seekBar = findViewById(R.id.seekbar);
        lbl_umur = findViewById(R.id.lbl_umur);
        btnDaftar = findViewById(R.id.daftar);
        radioGroup = findViewById(R.id.radioGroupGender);
        rblaki = findViewById(R.id.laki_laki);
        rbperempuan = findViewById(R.id.perempuan);
        mjetskiyamaha750 = findViewById(R.id.jetskiyamaha750);
        mjetskiseadoo750 = findViewById(R.id.jetskiseadoo750);
        mjetskiyamaha1000 = findViewById(R.id.jetskiyamaha1000);
        mjetskiseadoo1100 = findViewById(R.id.jetskiseadoo1100);
        mjetskiyamaha1800 = findViewById(R.id.jetskiyamaha1800);
        mjetskiseadoo1700 = findViewById(R.id.jetskiseadoo1700);

        db = new DataHelper(this);



        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        inputtglsewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        inputtglsewa.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nilaiUmur = String.valueOf(i);
                lbl_umur.setText("Umur : " + nilaiUmur);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                namaLengkap = txtNamaLengkap.getText().toString();
                tglSewa = inputtglsewa.getText().toString();
                umur = nilaiUmur;
                gender = getGenderSelected();
                modeljetski = getModeljetskiSelected();


                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(AddActivity.this);
                builder.setIcon(R.drawable.warning);
                builder.setTitle("Daftarkan");
                builder.setMessage(
                        "Apakah anda sudah yakin dengan data anda ?\n\n" +
                                "Nama Lengkap : \n" + namaLengkap + "\n\n" +
                                "Tanggal Sewa : \n" + tglSewa + "\n\n" +
                                "Umur : \n" + umur + "\n\n" +
                                "Gender :\n" + gender + "\n\n" +
                                "Model Jetski Yang di Pilih :\n" + modeljetski + ""
                );

                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Data anda berhasil terdaftarkan !", Toast.LENGTH_SHORT).show();

                        Intent layout2 = new Intent(AddActivity.this, MainActivity.class);

                        user = new User();
                        user.setNamaLengkap(namaLengkap);
                        user.setTglSewa(tglSewa);
                        user.setUmur(umur);
                        user.setGender(gender);
                        user.setModeljetski(modeljetski);

                        db.insert(user);

                        startActivity(layout2);
                        finish();

                    }
                });

                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });


    }
    private String getModeljetskiSelected(){
        String modeljetski = "";

        if (mjetskiyamaha750.isChecked()) {
            modeljetski += mjetskiyamaha750.getText().toString() + "\n";
        }
        if (mjetskiseadoo750.isChecked()) {
            modeljetski += mjetskiseadoo750.getText().toString() + "\n";
        }
        if (mjetskiyamaha1000.isChecked()) {
            modeljetski += mjetskiyamaha1000.getText().toString() + "\n";
        }
        if (mjetskiseadoo1100.isChecked()) {
            modeljetski += mjetskiseadoo1100.getText().toString() + "\n";
        }
        if (mjetskiyamaha1800.isChecked()) {
            modeljetski += mjetskiyamaha1800.getText().toString() + "\n";
        }
        if (mjetskiseadoo1700.isChecked()) {
            modeljetski += mjetskiseadoo1700.getText().toString() + "\n";
        }

        return modeljetski;

    }

    private String getGenderSelected(){
        String gender = "";

        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId == rblaki.getId()){
            gender = "Laki-Laki";
        }
        else if (selectedId == rbperempuan.getId()){
            gender = "Perempuan";
        }

        return gender;
    }

}
