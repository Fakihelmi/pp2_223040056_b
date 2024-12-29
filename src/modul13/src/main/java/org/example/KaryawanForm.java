package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class KaryawanForm extends JFrame {
    private JProgressBar progressBar;
    private JButton startButton;
    private JLabel infoLabel;
    private List<Karyawan> karyawanList;

    public KaryawanForm() {
        setTitle("Contoh Konkurensi di Swing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel topLabel = new JLabel("Tekan tombol untuk mulai tugas berat", SwingConstants.CENTER);
        mainPanel.add(topLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setString("0%");
        centerPanel.add(progressBar, BorderLayout.NORTH);

        infoLabel = new JLabel(" ", SwingConstants.CENTER);
        centerPanel.add(infoLabel, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        startButton = new JButton("Mulai");
        mainPanel.add(startButton, BorderLayout.SOUTH);

        add(mainPanel);

        KaryawanDAO karyawanDAO = new KaryawanDAO();
        karyawanList = karyawanDAO.getAllKaryawan();

        startButton.addActionListener(e -> startTask());
    }

    private void startTask() {
        startButton.setEnabled(false);
        infoLabel.setText("");
        progressBar.setValue(0);

        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                int total = karyawanList.size();
                for (int i = 0; i < total; i++) {
                    Thread.sleep(50);
                    int progress = (i + 1) * 100 / total;
                    publish(progress);

                    final int index = i;
                    SwingUtilities.invokeLater(() -> {
                        Karyawan karyawan = karyawanList.get(index);
                        infoLabel.setText(karyawan.getNama());
                    });
                }
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                int progress = chunks.get(chunks.size() - 1);
                progressBar.setValue(progress);
                progressBar.setString(progress + "%");
            }

            @Override
            protected void done() {
                startButton.setEnabled(true);
                showResultDialog();
            }
        };

        worker.execute();
    }

    private void showResultDialog() {
        JDialog resultDialog = new JDialog(this, "Data Seluruh Karyawan", true);
        resultDialog.setSize(500, 600);
        resultDialog.setLocationRelativeTo(this);

        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));


        StringBuilder content = new StringBuilder();
        content.append(String.format("%-5s %-30s %-20s%n", "No", "Nama Karyawan", "Jabatan"));
        content.append("=".repeat(55)).append("\n");

        for (int i = 0; i < karyawanList.size(); i++) {
            Karyawan k = karyawanList.get(i);
            content.append(String.format("%-5d %-30s %-20s%n",
                    (i + 1),
                    truncateString(k.getNama(), 30),
                    truncateString(k.getJabatan(), 20)));
        }

        textArea.setText(content.toString());


        JScrollPane scrollPane = new JScrollPane(textArea);
        contentPanel.add(scrollPane, BorderLayout.CENTER);


        JButton closeButton = new JButton("Tutup");
        closeButton.addActionListener(e -> resultDialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(closeButton);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        resultDialog.add(contentPanel);
        resultDialog.setVisible(true);
    }

    private String truncateString(String str, int length) {
        if (str == null) return "";
        return str.length() > length ? str.substring(0, length - 3) + "..." : str;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KaryawanForm form = new KaryawanForm();
            form.setVisible(true);
        });
    }
}