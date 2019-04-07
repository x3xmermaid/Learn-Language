package com.example.b34uty_m3rm41d.andro_project2.Model;

public class Latihan {
    private String aLatihan;
    private String pLatihan;
    private String pilih;
    private String id;
    private String key;

    public Latihan(String aLatihan, String pLatihan, String id, String pilih) {
        this.aLatihan = aLatihan;
        this.pLatihan = pLatihan;
        this.id = id;
        this.pilih = pilih;
    }

    public String getPilih() {
        return pilih;
    }

    public void setPilih(String pilih) {
        this.pilih = pilih;
    }

    public String getaLatihan() {
        return aLatihan;
    }

    public void setaLatihan(String aLatihan) {
        this.aLatihan = aLatihan;
    }

    public String getpLatihan() {
        return pLatihan;
    }

    public void setpLatihan(String pLatihan) {
        this.pLatihan = pLatihan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Latihan() {
    }


}
