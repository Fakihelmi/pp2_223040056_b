package dao;

import model.Karyawan;
import java.util.List;

public interface KaryawanMapper {
    List<Karyawan> getAllKaryawan();
    void addKaryawan(Karyawan karyawan);
    void updateKaryawan(Karyawan karyawan);
    void deleteKaryawan(int idKaryawan);
}
