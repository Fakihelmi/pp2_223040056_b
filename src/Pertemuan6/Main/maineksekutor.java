package Pertemuan6.Main;

import Pertemuan6.StudiKasus.PenerimaanKaryawanApp;

import javax.swing.*;

public class maineksekutor {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PenerimaanKaryawanApp app = new PenerimaanKaryawanApp();
            app.setVisible(true);
        });
    }
}

