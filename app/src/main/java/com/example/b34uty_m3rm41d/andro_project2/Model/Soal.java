package com.example.b34uty_m3rm41d.andro_project2.Model;

public class Soal {
    private int tfSoal;
    private int tfJawab;
    private String j1;

    public Soal(int tfSoal, int tfJawab) {
        this.tfSoal = tfSoal;
        this.tfJawab = tfJawab;
    }

    public Soal() {
    }

    public int getTfSoal() {
        return tfSoal;
    }

    public void setTfSoal(int tfSoal) {
        this.tfSoal = tfSoal;
    }

    public int getTfJawab() {
        return tfJawab;
    }

    public void setTfJawab(int tfJawab) {
        this.tfJawab = tfJawab;
    }

    public String getJ1() {
        return j1;
    }

    public void setJ1(String j1) {
        this.j1 = j1;
    }
}
