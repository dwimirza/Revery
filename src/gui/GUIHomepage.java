
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import controller.controllerHome;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import model.Category;

/**
 *
 * @author almer
 */
public class GUIHomepage extends javax.swing.JFrame {

    private ArrayList<String> rentalUnits;
    private JLabel out_category;
    private JLabel out_item;
    /**
     * Creates new form GUIHomepage
     */
    public GUIHomepage() {
        initComponents();
        setVisible(true);
        this.setTitle("Revery");
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);

        btnRent.setBackground(Color.yellow);
        btnReturn.setBackground(Color.yellow);
        btnAsAdmin.setBackground(Color.yellow);

        btnAsAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUILoginpage(); // Membuka halaman login
                dispose(); // Menutup halaman saat ini
            }
        });

        btnRent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRentDialog();
            }
        });

        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Menampilkan dialog input untuk payment id
                String paymentId = JOptionPane.showInputDialog(null, "Masukkan Payment ID:");

                // Memeriksa apakah pengguna memasukkan payment id atau membatalkan dialog
                if (paymentId != null && !paymentId.trim().isEmpty()) {
                    // Jika payment id valid, tampilkan pesan konfirmasi
                    JOptionPane.showMessageDialog(null, "Payment ID: " + paymentId + " berhasil dimasukkan.");
                } else {
                    // Jika pengguna membatalkan atau tidak memasukkan payment id
                    JOptionPane.showMessageDialog(null, "Input Payment ID dibatalkan atau kosong.");
                }
            }
        });
    }

    public void setOutCategory(int category){
        out_category.setText(Integer.toString(category));
    }
    public void setOutItem(String item){
        out_item.setText(item);
    }

    private void showRentDialog() {
        JPanel panel = new JPanel(new BorderLayout()); // Menggunakan BorderLayout untuk menambahkan JScrollPane

            
        controllerHome controller = new controllerHome(this);
        List<Category> categories = controller.getCategories();
        
        rentalUnits = new ArrayList<>();    
        rentalUnits.add(String.valueOf(out_category));
        
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Category category : categories) {
            listModel.addElement(category.getCatName()); // Add category name
        }

        JList<String> rentalList = new JList<>(listModel);
        rentalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Membungkus JList dengan JScrollPane untuk mendukung scrolling
        JScrollPane scrollPane = new JScrollPane(rentalList);

        // Menambahkan JScrollPane ke panel
        panel.add(scrollPane, BorderLayout.CENTER);

        JLabel label = new JLabel("Choose category:");

        // Panel untuk input teks
        JPanel inputPanel = new JPanel(new GridLayout(2, 1));
        inputPanel.add(label);

        // Menambahkan panel input di bawah daftar
        panel.add(inputPanel, BorderLayout.SOUTH);

        // 🔹 Menampilkan Dialog
        int result = JOptionPane.showConfirmDialog(null, panel, "Rental Form",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // ✅ Jika User Menekan OK
        if (result == JOptionPane.OK_OPTION) {
            // Jika memilih kategori dan ingin memilih barang dari kategori itu
            String selectedCategory = rentalList.getSelectedValue();
            if (selectedCategory != null) {
                // Menampilkan dialog baru untuk memilih barang dari kategori yang dipilih
                showSelectItemDialog(selectedCategory);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Penyewaan dibatalkan.");
        }
    }

    private void showSelectItemDialog(String category) {
        // Misalnya kita punya barang yang berbeda berdasarkan kategori yang dipilih
        List<String> items = new ArrayList<>();
        String expectedCategory;
        expectedCategory = category;
        if (category.equalsIgnoreCase(expectedCategory)) {
            items.add(String.valueOf(out_item)); // Tambahkan out_item ke dalam list items
        } else {
            System.out.println("Item not found for category: " + category); // Pesan error yang lebih spesifik
        }

        // Menyiapkan ListModel untuk JList
        DefaultListModel<String> itemListModel = new DefaultListModel<>();
        for (String item : items) {
            itemListModel.addElement(item);  // Menambahkan setiap item dalam list ke list model
        }

        // Membuat JList untuk barang-barang berdasarkan kategori
        JList<String> itemList = new JList<>(itemListModel);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Membatasi hanya satu pilihan

        // Membungkus JList dengan JScrollPane untuk mendukung scrolling
        JScrollPane scrollPane = new JScrollPane(itemList);

        // Membuat panel baru untuk dialog kedua
        JPanel itemPanel = new JPanel(new BorderLayout());
        itemPanel.add(new JLabel("Pilih barang dari kategori: " + category), BorderLayout.NORTH);
        itemPanel.add(scrollPane, BorderLayout.CENTER);

        // Menampilkan dialog kedua untuk memilih barang
        int itemResult = JOptionPane.showConfirmDialog(null, itemPanel, "Pilih Barang",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Jika pengguna menekan OK, tampilkan barang yang dipilih
        if (itemResult == JOptionPane.OK_OPTION) {
            String selectedItem = itemList.getSelectedValue();
            if (selectedItem != null && !selectedItem.equals("null")) {
                // Membuat panel untuk input
                JPanel panel = new JPanel(new GridLayout(3, 2)); // 3 baris, 2 kolom
                JTextField namaField = new JTextField();
                JTextField tanggalPinjamField = new JTextField();
                JTextField tanggalKembaliField = new JTextField();

                // Menambahkan komponen ke panel
                panel.add(new JLabel("Name:"));
                panel.add(namaField);
                panel.add(new JLabel("Rent Date (DD/MM/YYYY):"));
                panel.add(tanggalPinjamField);
                panel.add(new JLabel("Return Date (DD/MM/YYYY):"));
                panel.add(tanggalKembaliField);

                // Menampilkan dialog dengan panel
                int result = JOptionPane.showConfirmDialog(null, panel, "Input Detail Peminjaman", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    // Mengambil nilai dari input
                    String namaPeminjam = namaField.getText();
                    String tanggalMeminjam = tanggalPinjamField.getText();
                    String tanggalPengembalian = tanggalKembaliField.getText();

                    // Menampilkan hasil
                    String message = "Detail Peminjaman:\n"
                            + "Item: " + selectedItem + "\n"
                            + "Name: " + namaPeminjam + "\n"
                            + "Rent Date: " + tanggalMeminjam + "\n"
                            + "Return Date: " + tanggalPengembalian;
                    JOptionPane.showMessageDialog(null, message);
                } else {
                    JOptionPane.showMessageDialog(null, "Input detail peminjaman dibatalkan.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pemilihan barang dibatalkan.");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textWelcome = new javax.swing.JLabel();
        btnRent = new javax.swing.JButton();
        btnReturn = new javax.swing.JButton();
        textAsking = new javax.swing.JLabel();
        btnAsAdmin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 400));

        textWelcome.setFont(new java.awt.Font("Cambria Math", 1, 24)); // NOI18N
        textWelcome.setText("Welcome to Revery!");

        btnRent.setFont(new java.awt.Font("Cambria Math", 1, 24)); // NOI18N
        btnRent.setText("Rent");
        btnRent.setPreferredSize(new java.awt.Dimension(200, 100));

        btnReturn.setFont(new java.awt.Font("Cambria Math", 1, 24)); // NOI18N
        btnReturn.setText("Return");
        btnReturn.setPreferredSize(new java.awt.Dimension(200, 100));

        textAsking.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        textAsking.setText("What you need?");

        btnAsAdmin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAsAdmin.setText("As Admin");
        btnAsAdmin.setPreferredSize(new java.awt.Dimension(120, 28));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAsAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(textAsking)
                        .addComponent(textWelcome)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnRent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(56, 56, 56)
                            .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(textWelcome)
                .addGap(38, 38, 38)
                .addComponent(textAsking)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                .addComponent(btnAsAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIHomepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIHomepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIHomepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIHomepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIHomepage();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsAdmin;
    private javax.swing.JButton btnRent;
    private javax.swing.JButton btnReturn;
    private javax.swing.JLabel textAsking;
    private javax.swing.JLabel textWelcome;
    // End of variables declaration//GEN-END:variables
}
