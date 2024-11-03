package Pertemuan6.StudiKasus;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.SpinnerDateModel;

public class PenerimaanKaryawanApp extends JFrame {
    private JTextField namaField, posisiField, alamatField;
    private JComboBox<String> genderComboBox;
    private JTextArea pernahBekerjaDiArea;
    private JTable karyawanTable;
    private DefaultTableModel tableModel;
    private JSlider pengalamanSlider;
    private JLabel pengalamanLabel;
    private JRadioButton sudahBekerjaRadio, belumBekerjaRadio;
    private JCheckBox persetujuanCheckBox;
    private JSpinner tanggalLahirSpinner;

    public PenerimaanKaryawanApp() {
        setTitle("Aplikasi Penerimaan Karyawan - Mika");
        setSize(700, 600); // Ukuran lebih kecil
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5)); // Mengurangi jarak antar komponen

        // Setup menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opsi");

        JMenuItem hapusDataMenuItem = new JMenuItem("Hapus Form Tersimpan");
        JMenuItem keluarMenuItem = new JMenuItem("Keluar Aplikasi");

        menu.add(hapusDataMenuItem);
        menu.add(keluarMenuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Panel Form di Tengah dengan GridLayout
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5)); // Mengurangi jarak antar elemen
        formPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Mengurangi padding

        formPanel.add(new JLabel("Nama Karyawan:"));
        namaField = new JTextField();
        formPanel.add(namaField);

        formPanel.add(new JLabel("Alamat:"));
        alamatField = new JTextField();
        formPanel.add(alamatField);

        formPanel.add(new JLabel("Tanggal Lahir:"));
        // Mengatur Spinner hanya untuk tanggal
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 0, 1); // Mengatur default tanggal lahir
        tanggalLahirSpinner = new JSpinner(new SpinnerDateModel(calendar.getTime(), null, null, Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(tanggalLahirSpinner, "dd-MM-yyyy");
        tanggalLahirSpinner.setEditor(dateEditor);
        formPanel.add(tanggalLahirSpinner);

        formPanel.add(new JLabel("Jenis Kelamin:"));
        genderComboBox = new JComboBox<>(new String[]{"Laki-laki", "Perempuan"});
        formPanel.add(genderComboBox);

        formPanel.add(new JLabel("Posisi:"));
        posisiField = new JTextField();
        formPanel.add(posisiField);

        formPanel.add(new JLabel("Pengalaman Kerja (Tahun):"));
        JPanel sliderPanel = new JPanel(new BorderLayout());
        pengalamanSlider = new JSlider(0, 20, 0);
        pengalamanSlider.setMajorTickSpacing(5);
        pengalamanSlider.setMinorTickSpacing(1);
        pengalamanSlider.setPaintTicks(true);
        pengalamanSlider.setPaintLabels(true);
        pengalamanLabel = new JLabel("0 Tahun");
        sliderPanel.add(pengalamanSlider, BorderLayout.CENTER);
        sliderPanel.add(pengalamanLabel, BorderLayout.EAST);
        formPanel.add(sliderPanel);

        pengalamanSlider.addChangeListener(e -> pengalamanLabel.setText(pengalamanSlider.getValue() + " Tahun"));

        formPanel.add(new JLabel("Status Pekerjaan:"));
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sudahBekerjaRadio = new JRadioButton("Sudah Pernah Bekerja");
        belumBekerjaRadio = new JRadioButton("Belum Pernah Bekerja");
        ButtonGroup bekerjaGroup = new ButtonGroup();
        bekerjaGroup.add(sudahBekerjaRadio);
        bekerjaGroup.add(belumBekerjaRadio);
        radioPanel.add(sudahBekerjaRadio);
        radioPanel.add(belumBekerjaRadio);
        formPanel.add(radioPanel);

        formPanel.add(new JLabel("Pernah Bekerja di:"));
        pernahBekerjaDiArea = new JTextArea(3, 15); // Ukuran lebih kecil
        pernahBekerjaDiArea.setLineWrap(true);
        pernahBekerjaDiArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(pernahBekerjaDiArea);
        formPanel.add(scrollPane);

        formPanel.add(new JLabel("Persetujuan:"));
        persetujuanCheckBox = new JCheckBox("Apakah Anda menyetujui pengisian data diri ini?");
        formPanel.add(persetujuanCheckBox);

        // Panel Tombol di Bawah Form
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Mengurangi jarak antar tombol
        JButton simpanButton = new JButton("Simpan");
        JButton updateButton = new JButton("Update");
        JButton hapusButton = new JButton("Hapus");

        buttonPanel.add(simpanButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(hapusButton);

        // Tabel Data Karyawan di Bawah
        tableModel = new DefaultTableModel(new String[]{"Nama", "Alamat", "Tanggal Lahir", "Jenis Kelamin", "Posisi", "Pengalaman", "Status Bekerja", "Pernah Bekerja di"}, 0);
        karyawanTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(karyawanTable);

        // Action Listener untuk tombol Simpan
        simpanButton.addActionListener(e -> {
            if (persetujuanCheckBox.isSelected()) {
                String nama = namaField.getText();
                String alamat = alamatField.getText();
                Date tanggalLahir = (Date) tanggalLahirSpinner.getValue(); // Ambil tanggal
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String formattedTanggalLahir = sdf.format(tanggalLahir); // Format tanggal

                String jenisKelamin = (String) genderComboBox.getSelectedItem();
                String posisi = posisiField.getText();
                String pengalaman = pengalamanSlider.getValue() + " Tahun";
                String statusBekerja = sudahBekerjaRadio.isSelected() ? "Sudah Pernah Bekerja" : "Belum Pernah Bekerja";
                String pernahBekerjaDi = pernahBekerjaDiArea.getText();

                tableModel.addRow(new Object[]{nama, alamat, formattedTanggalLahir, jenisKelamin, posisi, pengalaman, statusBekerja, pernahBekerjaDi});
                clearForm();
            } else {
                JOptionPane.showMessageDialog(null, "Harap menyetujui pengisian data diri!");
            }
        });

        // Action Listener untuk tombol Update
        updateButton.addActionListener(e -> {
            int selectedRow = karyawanTable.getSelectedRow();
            if (selectedRow != -1 && persetujuanCheckBox.isSelected()) {
                tableModel.setValueAt(namaField.getText(), selectedRow, 0);
                tableModel.setValueAt(alamatField.getText(), selectedRow, 1);
                Date tanggalLahir = (Date) tanggalLahirSpinner.getValue(); // Ambil tanggal
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String formattedTanggalLahir = sdf.format(tanggalLahir); // Format tanggal
                tableModel.setValueAt(formattedTanggalLahir, selectedRow, 2);
                tableModel.setValueAt(genderComboBox.getSelectedItem(), selectedRow, 3);
                tableModel.setValueAt(posisiField.getText(), selectedRow, 4);
                tableModel.setValueAt(pengalamanSlider.getValue() + " Tahun", selectedRow, 5);
                tableModel.setValueAt(sudahBekerjaRadio.isSelected() ? "Sudah Pernah Bekerja" : "Belum Pernah Bekerja", selectedRow, 6);
                tableModel.setValueAt(pernahBekerjaDiArea.getText(), selectedRow, 7);
                clearForm();
            } else {
                JOptionPane.showMessageDialog(null, "Harap menyetujui pengisian data diri atau pilih baris yang ingin diubah!");
            }
        });

        // Action Listener untuk tombol Hapus
        hapusButton.addActionListener(e -> {
            int selectedRow = karyawanTable.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
                clearForm();
            }
        });

        // Memuat data dari tabel ke form saat baris dipilih
        karyawanTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = karyawanTable.getSelectedRow();
                if (selectedRow != -1) {
                    namaField.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    alamatField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    String tanggalLahir = tableModel.getValueAt(selectedRow, 2).toString();
                    try {
                        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(tanggalLahir);
                        tanggalLahirSpinner.setValue(date); // Mengatur tanggal ke spinner
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    genderComboBox.setSelectedItem(tableModel.getValueAt(selectedRow, 3).toString());
                    posisiField.setText(tableModel.getValueAt(selectedRow, 4).toString());
                    pengalamanSlider.setValue(Integer.parseInt(tableModel.getValueAt(selectedRow, 5).toString().split(" ")[0])); // Mengambil tahun dari teks
                    if (tableModel.getValueAt(selectedRow, 6).toString().equals("Sudah Pernah Bekerja")) {
                        sudahBekerjaRadio.setSelected(true);
                    } else {
                        belumBekerjaRadio.setSelected(true);
                    }
                    pernahBekerjaDiArea.setText(tableModel.getValueAt(selectedRow, 7).toString());
                }
            }
        });

        // Action Listener untuk Hapus Data
        hapusDataMenuItem.addActionListener(e -> tableModel.setRowCount(0));

        // Action Listener untuk Keluar
        keluarMenuItem.addActionListener(e -> System.exit(0));

        // Panel Utama dengan Layout BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
    }

    // Fungsi untuk mengosongkan form
    private void clearForm() {
        namaField.setText("");
        alamatField.setText("");
        tanggalLahirSpinner.setValue(new Date()); // Mengatur ulang ke tanggal saat ini
        genderComboBox.setSelectedIndex(0);
        posisiField.setText("");
        pengalamanSlider.setValue(0);
        sudahBekerjaRadio.setSelected(false);
        belumBekerjaRadio.setSelected(false);
        pernahBekerjaDiArea.setText("");
        persetujuanCheckBox.setSelected(false);
    }
}