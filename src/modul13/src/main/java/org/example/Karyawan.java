package org.example;

public class Karyawan {
    private int id;
    private String nama;
    private String jabatan;

    public Karyawan(int id, String nama, String jabatan) {
        this.id = id;
        this.nama = nama;
        this.jabatan = jabatan;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    @Override
    public String toString() {
        return nama;
    }
}