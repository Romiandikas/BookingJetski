package com.romiandika.bookingjetski;

public class User {

    private int id;
    private String namaLengkap;
    private String tglSewa;
    private String umur;
    private String gender;
    private String modeljetski;



    public int getId() {return id;}

    public void setId(int id) {this.id =id; }

    public String getNamaLengkap() { return namaLengkap;}

    public void setNamaLengkap(String namaLengkap) { this.namaLengkap = namaLengkap;}

    public String getTglSewa() { return tglSewa;}

    public void setTglSewa(String tglLahir) { this.tglSewa = tglLahir;}

    public String getUmur() { return umur;}

    public void setUmur(String umur) { this.umur = umur;}

    public String getGender() { return gender;}

    public void setGender(String gender) { this.gender = gender;}

    public String getModeljetski() { return modeljetski;}

    public void setModeljetski(String service) { this.modeljetski = service;}

}