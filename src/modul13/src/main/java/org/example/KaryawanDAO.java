package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KaryawanDAO {

    public List<Karyawan> getAllKaryawan() {
        List<Karyawan> karyawanList = new ArrayList<>();
        String sql = "SELECT * FROM karyawan";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                String jabatan = rs.getString("jabatan");
                Karyawan karyawan = new Karyawan(id, nama, jabatan);
                karyawanList.add(karyawan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return karyawanList;
    }


    public void addKaryawan(Karyawan karyawan) {
        String sql = "INSERT INTO karyawan (nama, jabatan) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, karyawan.getNama());
            stmt.setString(2, karyawan.getJabatan());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
