package dao;

import model.Karyawan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KaryawanDAO {

    public List<Karyawan> getAllKaryawan() {
        List<Karyawan> list = new ArrayList<>();
        String query = "SELECT * FROM karyawan";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Karyawan karyawan = new Karyawan();
                karyawan.setIdKaryawan(rs.getInt("id_karyawan"));
                karyawan.setNama(rs.getString("nama"));
                karyawan.setJabatan(rs.getString("jabatan"));
                karyawan.setGaji(rs.getDouble("gaji"));
                karyawan.setTanggalMasuk(rs.getString("tanggal_masuk"));
                list.add(karyawan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void addKaryawan(Karyawan karyawan) {
        String query = "INSERT INTO karyawan (nama, jabatan, gaji, tanggal_masuk) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, karyawan.getNama());
            stmt.setString(2, karyawan.getJabatan());
            stmt.setDouble(3, karyawan.getGaji());
            stmt.setString(4, karyawan.getTanggalMasuk());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateKaryawan(Karyawan karyawan) {
        String query = "UPDATE karyawan SET nama = ?, jabatan = ?, gaji = ?, tanggal_masuk = ? WHERE id_karyawan = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, karyawan.getNama());
            stmt.setString(2, karyawan.getJabatan());
            stmt.setDouble(3, karyawan.getGaji());
            stmt.setString(4, karyawan.getTanggalMasuk());
            stmt.setInt(5, karyawan.getIdKaryawan());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteKaryawan(int idKaryawan) {
        String query = "DELETE FROM karyawan WHERE id_karyawan = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idKaryawan);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
