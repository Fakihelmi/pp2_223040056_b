package controller;

import dao.KaryawanDAO;
import model.Karyawan;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class MainForm {
    private JPanel mainPanel;
    private JTable tableKaryawan;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton refreshButton;

    private KaryawanDAO dao;

    public MainForm() {
        dao = new KaryawanDAO();
        loadData();

        addButton.addActionListener(e -> {
            // Logika untuk menambah data
            Karyawan newKaryawan = new Karyawan(1, "Nama Baru", "Jabatan Baru", 5000000, "2024-12-23");
            dao.addKaryawan(newKaryawan);
            loadData();
        });

        updateButton.addActionListener(e -> {
            // Logika untuk memperbarui data
            if (tableKaryawan.getSelectedRow() != -1) {
                int selectedRow = tableKaryawan.getSelectedRow();
                int idKaryawan = (int) tableKaryawan.getValueAt(selectedRow, 0);
                Karyawan karyawan = new Karyawan(idKaryawan, "Nama Diperbarui", "Jabatan Diperbarui", 6000000, "2024-12-23");
                dao.updateKaryawan(karyawan);
                loadData();
            }
        });

        deleteButton.addActionListener(e -> {
            // Logika untuk menghapus data
            if (tableKaryawan.getSelectedRow() != -1) {
                int selectedRow = tableKaryawan.getSelectedRow();
                int idKaryawan = (int) tableKaryawan.getValueAt(selectedRow, 0);
                dao.deleteKaryawan(idKaryawan);
                loadData();
            }
        });

        refreshButton.addActionListener(e -> loadData());
    }

    private void loadData() {
        List<Karyawan> list = dao.getAllKaryawan();
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nama", "Jabatan", "Gaji", "Tanggal Masuk"}, 0);

        for (Karyawan karyawan : list) {
            model.addRow(new Object[]{
                    karyawan.getIdKaryawan(),
                    karyawan.getNama(),
                    karyawan.getJabatan(),
                    karyawan.getGaji(),
                    karyawan.getTanggalMasuk()
            });
        }

        tableKaryawan.setModel(model);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Penerimaan Karyawan");
        frame.setContentPane(new MainForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
