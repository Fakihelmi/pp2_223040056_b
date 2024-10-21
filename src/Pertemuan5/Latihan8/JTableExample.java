package Pertemuan5.Latihan8;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JTableExample {
    public static void main(String[] args) {
        // Membuat frame
        JFrame frame = new JFrame("JTable Example");

        // Membuat tabel dengan data awal dan header kolom
        String[] columnNames = { "ID", "Name", "Age" };
        Object[][] data = {
                { 1, "Fakih", 19 },
                { 2, "Ilham", 20 },
                { 3, "Dzikri", 22 },
        };

        // Membuat model tabel
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // Membuat JTable dengan model
        JTable table = new JTable(model);

        // Membuat JScrollPane untuk menampung JTable
        JScrollPane scrollPane = new JScrollPane(table);

        // Menambahkan JScrollPane ke frame
        frame.add(scrollPane);

        // Konfigurasi frame
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
