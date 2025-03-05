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
import model.Item;
import model.Returns;

/**
 *
 * @author almer
 */
public class GUIHomepage extends javax.swing.JFrame {

    private ArrayList<String> rentalUnits;
    private JLabel out_category;

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
                showReturnDialog();
            }
        });


    }

    public void setOutCategory(int category) {
        out_category.setText(Integer.toString(category));
    }
//    public void setOutItem(int item){
//        out_item.setText(Integer.toString(item));
//    }

    public void setOutItem(int item) {
//        out_item.setText(item);
    }

    private void showRentDialog() {
        JPanel panel = new JPanel(new BorderLayout()); // Menggunakan BorderLayout untuk menambahkan JScrollPane

        //Manggil data 
        controllerHome controller = new controllerHome(this);
        List<Category> categories = controller.getCategories();

        rentalUnits = new ArrayList<>();
        rentalUnits.add(String.valueOf(out_category));
//        rentalUnits.add(String.valueOf(out_item));

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Category category : categories) {
            listModel.addElement(category.getId() + " - " + category.getCatName()); // Add category name
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

    public void showReturnDialog() {
    controllerHome controller = new controllerHome(this);

    // Menampilkan dialog input untuk payment id
    String paymentId = JOptionPane.showInputDialog(null, "Masukkan Payment ID:");
    
    if (paymentId != null && !paymentId.trim().isEmpty()) {
        try {
            List<Returns> returnDataList = controller.getOneReturn(paymentId);

            if (!returnDataList.isEmpty()) {
                StringBuilder formattedOutput = new StringBuilder("<html>");
                
                for (Returns returnData : returnDataList) {
                    formattedOutput.append("Nama Penyewa     : ").append(returnData.getBorrowerName()).append("<br>")
                            .append("Barang Dipinjam  : ").append(returnData.getitemName()).append("<br>")
                            .append("Tanggal Meminjam : ").append(returnData.getRentalDate()).append("<br>")
                            .append("Status           : ").append(returnData.getStatus()).append("<br><br>");
                }
                formattedOutput.append("</html>");

                // Menampilkan detail return
                JOptionPane.showMessageDialog(null, formattedOutput.toString());

                // Membuat array untuk opsi button
                Object[] options = {"Return", "Cancel"};

                // Menampilkan dialog dengan opsi button Return dan Cancel
                int choice = JOptionPane.showOptionDialog(null, formattedOutput.toString(), "Return Details",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                // Menangani pilihan user
                if (choice == 0) {
                    // User memilih Return
                    int idNum = Integer.parseInt(paymentId);
                    controller.insertReturn(idNum);
                    JOptionPane.showMessageDialog(null, "Proses Return dilakukan untuk Payment ID: " + paymentId);
                } else {
                    // User memilih Cancel atau menutup dialog
                    JOptionPane.showMessageDialog(null, "Proses Return dibatalkan.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan untuk Payment ID: " + paymentId);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Payment ID harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        // Jika pengguna membatalkan atau tidak memasukkan payment id
        JOptionPane.showMessageDialog(null, "Input Payment ID dibatalkan atau kosong.");
    }
}


    private void showSelectItemDialog(String category) {
        // Misalnya kita punya barang yang berbeda berdasarkan kategori yang dipilih
        controllerHome controller = new controllerHome(this);
        List<Item> items = controller.getItemByCat(category);
        rentalUnits = new ArrayList<>();
//        rentalUnits.add(String.valueOf(out_item));

        String expectedCategory;
        expectedCategory = category;
//        if (category.equalsIgnoreCase(expectedCategory)) {
//            items.add(out_item); // Tambahkan out_item ke dalam list items
//        } else {
//            System.out.println("Item not found for category: " + category); // Pesan error yang lebih spesifik
//        }

        // Menyiapkan ListModel untuk JList
        DefaultListModel<String> itemListModel = new DefaultListModel<>();
        for (Item item : items) {
            itemListModel.addElement(item.getId() + " - " + item.getItem());  // Menambahkan setiap item dalam list ke list model
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
                JPanel panel = new JPanel(new GridLayout(4, 2)); // 3 baris, 2 kolom
                JTextField namaField = new JTextField();
                JTextField tanggalPinjamField = new JTextField();
                JTextField tanggalKembaliField = new JTextField();
                JTextField paymentMethodField = new JTextField();

                // Menambahkan komponen ke panel
                panel.add(new JLabel("Name:"));
                panel.add(namaField);
                panel.add(new JLabel("Rent Date (YYYY-MM-DD):"));
                panel.add(tanggalPinjamField);
                panel.add(new JLabel("Return Date (YYYY-MM-DD):"));
                panel.add(tanggalKembaliField);
                panel.add(new JLabel("Payment Method:"));
                panel.add(paymentMethodField);

                // Menampilkan dialog dengan panel
                int result = JOptionPane.showConfirmDialog(null, panel, "Input Detail Peminjaman", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    // Mengambil nilai dari input
                    String namaPeminjam = namaField.getText();
                    String tanggalMeminjam = tanggalPinjamField.getText();
                    String tanggalPengembalian = tanggalKembaliField.getText();
                    String paymentMethod = paymentMethodField.getText();
                    controller.addData(namaPeminjam,tanggalMeminjam,tanggalPengembalian,selectedItem, paymentMethod);

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
        btnRent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRentActionPerformed(evt);
            }
        });

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

    private void btnRentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRentActionPerformed

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
