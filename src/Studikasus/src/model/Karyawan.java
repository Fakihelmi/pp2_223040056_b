package model;

public class Karyawan {
    private int idKaryawan;
    private String nama;
    private String jabatan;
    private double gaji;
    private String tanggalMasuk;

    public Karyawan() {}

    public Karyawan(int idKaryawan, String nama, String jabatan, double gaji, String tanggalMasuk) {
        this.idKaryawan = idKaryawan;
        this.nama = nama;
        this.jabatan = jabatan;
        this.gaji = gaji;
        this.tanggalMasuk = tanggalMasuk;
    }

    public int getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(int idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public double getGaji() {
        return gaji;
    }

    public void setGaji(double gaji) {
        this.gaji = gaji;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(String tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }
}
