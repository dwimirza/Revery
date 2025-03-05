/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import controller.ControllerAdmin;
import controller.controllerHome;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import dao.daoItem;
import model.Category;
import model.Item;

/**
 *
 * @author almer
 */
public class GUIAdminpage extends javax.swing.JFrame {

    private ArrayList<String> rentalUnits;

    /**
     * Creates new form GUIAdminpage
     */
    public GUIAdminpage() {
        initComponents();
        setVisible(true);
        this.setTitle("Revery");
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);

        btnShow.setBackground(Color.yellow);
        btnAdd.setBackground(Color.yellow);
        btnUpdate.setBackground(Color.yellow);
        btnDelete.setBackground(Color.yellow);
        textAreaData.setBackground(Color.LIGHT_GRAY);
        textAreaData.setEditable(false);

        btnShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readShowDialog();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addShowDialog();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateShowDialog();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteShowDialog();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textAdmin = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaData = new javax.swing.JTextArea();
        btnShow = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textAdmin.setFont(new java.awt.Font("Cambria Math", 1, 20)); // NOI18N
        textAdmin.setText("Admin Menu");

        textAreaData.setColumns(20);
        textAreaData.setRows(5);
        jScrollPane1.setViewportView(textAreaData);

        btnShow.setText("Show");
        btnShow.setPreferredSize(new java.awt.Dimension(110, 35));

        btnDelete.setText("Delete");
        btnDelete.setPreferredSize(new java.awt.Dimension(110, 35));

        btnAdd.setText("Add");
        btnAdd.setPreferredSize(new java.awt.Dimension(110, 35));

        btnUpdate.setText("Update");
        btnUpdate.setPreferredSize(new java.awt.Dimension(110, 35));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textAdmin)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnShow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(textAdmin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnShow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(GUIAdminpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIAdminpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIAdminpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIAdminpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIAdminpage();
            }
        });
    }

    private void readShowDialog() {
        JPanel panel = new JPanel(new BorderLayout());

        // 🔹 Tambahkan pilihan "Category" dan "Item"
        rentalUnits = new ArrayList<>();
        rentalUnits.add("Category");
        rentalUnits.add("Item");

        // 🔹 Buat ListModel untuk JList
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String item : rentalUnits) {
            listModel.addElement(item);
        }

        // 🔹 Buat JList dengan pilihan
        JList<String> rentalList = new JList<>(listModel);
        rentalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(rentalList);
        scrollPane.setPreferredSize(new Dimension(200, 100));
        panel.add(scrollPane, BorderLayout.CENTER);

        // 🔹 Tampilkan Dialog
        int result = JOptionPane.showConfirmDialog(null, panel, "Choose Data",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // ✅ Jika pengguna menekan OK
        if (result == JOptionPane.OK_OPTION) {
            String selectedChoice = rentalList.getSelectedValue();

            if (selectedChoice != null) {
                // 🔹 Ambil daftar kategori atau item dari database
                String itemList = getDataItemAndCategory(selectedChoice);

                // 🔹 Tampilkan di textAreaData
                textAreaData.setText(itemList);
            } else {
                JOptionPane.showMessageDialog(null, "Please select an option first.");
            }
        }
    }

    private void addShowDialog() {
        JPanel panel = new JPanel(new BorderLayout());

        // Menyiapkan opsi yang bisa dipilih oleh user
        rentalUnits = new ArrayList<>();
        rentalUnits.add("Category");
        rentalUnits.add("Item");

        // Menyiapkan ListModel untuk JList
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String item : rentalUnits) {
            listModel.addElement(item);
        }

        // Membuat JList dengan listModel
        JList<String> rentalList = new JList<>(listModel);
        rentalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Membungkus JList dengan JScrollPane
        JScrollPane scrollPane = new JScrollPane(rentalList);
        scrollPane.setPreferredSize(new Dimension(150, 80));

        // Menambahkan JScrollPane ke panel
        panel.add(scrollPane, BorderLayout.CENTER);

        // Menampilkan Dialog Pilihan
        int result = JOptionPane.showConfirmDialog(null, panel, "Add Data",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Jika User Menekan OK
        if (result == JOptionPane.OK_OPTION) {
            String selectedOption = rentalList.getSelectedValue();

            if ("Category".equals(selectedOption)) {
                tambahKategori(); // Panggil fungsi untuk tambah kategori
            } else if ("Item".equals(selectedOption)) {
                tambahItem(); // Panggil fungsi untuk tambah item
            } else {
                JOptionPane.showMessageDialog(null, "Choose a category or item.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Add Data Failed");
        }
    }

    private void tambahKategori() {
        ControllerAdmin controller = new ControllerAdmin(this);

        JTextField categoryNameField = new JTextField();

        JPanel categoryPanel = new JPanel(new GridLayout(2, 2));
        categoryPanel.add(new JLabel("Category Name:"));
        categoryPanel.add(categoryNameField);

        int result = JOptionPane.showConfirmDialog(null, categoryPanel,
                "Input Category Data", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String categoryName = categoryNameField.getText();
            controller.addCategory(categoryName);
            // Simpan kategori ke database atau struktur data
//            addCategory(categoryId, categoryName);
            JOptionPane.showMessageDialog(null, "Category successfully added");
        }
    }

    private void tambahItem() {
        JTextField nameItemField = new JTextField();
        JTextField categoryIdField = new JTextField();
        JTextField rentalPriceField = new JTextField();
        JTextField stockField = new JTextField();
        ControllerAdmin controller = new ControllerAdmin(this);

        JPanel itemPanel = new JPanel(new GridLayout(5, 2));
        itemPanel.add(new JLabel("Item Name:"));
        itemPanel.add(nameItemField);
        itemPanel.add(new JLabel("Category ID:"));
        itemPanel.add(categoryIdField);
        itemPanel.add(new JLabel("Rental Price:"));
        itemPanel.add(rentalPriceField);
        itemPanel.add(new JLabel("Stock:"));
        itemPanel.add(stockField);

        int result = JOptionPane.showConfirmDialog(null, itemPanel,
                "Input Item Data", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String itemName = nameItemField.getText();
            String categoryId = categoryIdField.getText();
            double rentalPrice = Double.parseDouble(rentalPriceField.getText());
            int stock = Integer.parseInt(stockField.getText());

            Item newItem = new Item();
            newItem.setItem(itemName);
            newItem.setCatId(Integer.parseInt(categoryId));
            newItem.setRentPrice(rentalPrice);
            newItem.setStock(stock);
            controller.addItem(newItem);

            // Simpan item ke database atau struktur data
//            addItem(itemId, itemName, categoryId, rentalPrice, stock);
            JOptionPane.showMessageDialog(null, "Item successfully added!");
        }
    }

    private void updateShowDialog() {
        JPanel panel = new JPanel(new BorderLayout());

        // Menyiapkan opsi yang bisa dipilih oleh user
        rentalUnits = new ArrayList<>();
        rentalUnits.add("Category");
        rentalUnits.add("Item");

        // Menyiapkan ListModel untuk JList
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String item : rentalUnits) {
            listModel.addElement(item);
        }

        // Membuat JList dengan listModel
        JList<String> rentalList = new JList<>(listModel);
        rentalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Membungkus JList dengan JScrollPane
        JScrollPane scrollPane = new JScrollPane(rentalList);
        scrollPane.setPreferredSize(new Dimension(150, 80));

        // Menambahkan JScrollPane ke panel
        panel.add(scrollPane, BorderLayout.CENTER);

        // Menampilkan Dialog Pilihan
        int result = JOptionPane.showConfirmDialog(null, panel, "Update Data",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Jika User Menekan OK
        if (result == JOptionPane.OK_OPTION) {
            String selectedOption = rentalList.getSelectedValue();

            if ("Category".equals(selectedOption)) {
                editKategori(); // Panggil fungsi untuk tambah kategori
            } else if ("Item".equals(selectedOption)) {
                editItem(); // Panggil fungsi untuk tambah item
            } else {
                JOptionPane.showMessageDialog(null, "Choose a category or item.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Update Data Failed");
        }
    }

    private void editKategori1(String categoryName, int categoryId) {
    ControllerAdmin controller = new ControllerAdmin(this);

    // Debug: Tampilkan nilai yang diterima
    System.out.println("Editing category - ID: " + categoryId + ", Name: " + categoryName);

    // Mengisi field dengan nilai awal
    JTextField categoryIdField = new JTextField(String.valueOf(categoryId));
    categoryIdField.setEditable(false); // ID tidak bisa diubah
    JTextField categoryNameField = new JTextField(categoryName);

    // Membuat panel dengan input fields
    JPanel categoryPanel = new JPanel(new GridLayout(2, 2));
    categoryPanel.add(new JLabel("Category ID:"));
    categoryPanel.add(categoryIdField);
    categoryPanel.add(new JLabel("Category Name:"));
    categoryPanel.add(categoryNameField);

    // Menampilkan dialog input
    int result = JOptionPane.showConfirmDialog(null, categoryPanel,
            "Edit Category", JOptionPane.OK_CANCEL_OPTION);

    if (result == JOptionPane.OK_OPTION) {
        String newCategoryName = categoryNameField.getText().trim();

        // Validasi input (pastikan nama tidak kosong)
        if (newCategoryName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Category name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Debug: Tampilkan nilai baru
        System.out.println("New name: " + newCategoryName);

        // Panggil controller untuk update kategori
        controller.updateCategory(newCategoryName, categoryId);

        // Konfirmasi kepada pengguna
        JOptionPane.showMessageDialog(null, "Category successfully updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
    
    private void editKategori() {
        ControllerAdmin controller = new ControllerAdmin(this);

        // Ambil daftar item
        List<Category> categories = controller.getCategories();

        // Tampilkan item dalam format lengkap
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Category category : categories) {
            listModel.addElement(category.getId() + " - " + category.getCatName());
        }

        // Buat JList
        JList<String> itemList = new JList<>(listModel);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(itemList);
        scrollPane.setPreferredSize(new Dimension(300, 150));

        int option = JOptionPane.showConfirmDialog(null, scrollPane,
                "Choose a category to be changed.",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String selectedValue = itemList.getSelectedValue();

            if (selectedValue != null) {
                try {
                    // Pisahkan data item
                    String[] parts = selectedValue.split(" - ");
                    int categoryId = Integer.parseInt(parts[0]);
                    String categoryName = parts[1];

                    // Panggil editItem1
                    editKategori1(categoryName, categoryId);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Item data is not valid!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Choose an item first.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Item changes canceled.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void editItem1(String itemName, int categoryId, double rentPrice, int itemId) {
        ControllerAdmin controller = new ControllerAdmin(this);

        // Form Input
        JTextField itemIdField = new JTextField(String.valueOf(itemId));
        itemIdField.setEditable(false); // ID tidak bisa diedit
        JTextField nameItemField = new JTextField(itemName);
        JTextField categoryIdField = new JTextField(String.valueOf(categoryId));
        JTextField rentalPriceField = new JTextField(String.valueOf(rentPrice));

        JPanel itemPanel = new JPanel(new GridLayout(4, 2));
        itemPanel.add(new JLabel("Item ID:"));
        itemPanel.add(itemIdField);
        itemPanel.add(new JLabel("Item Name:"));
        itemPanel.add(nameItemField);
        itemPanel.add(new JLabel("Category ID:"));
        itemPanel.add(categoryIdField);
        itemPanel.add(new JLabel("Rental Price:"));
        itemPanel.add(rentalPriceField);

        int result = JOptionPane.showConfirmDialog(null, itemPanel,
                "Edit Item Data", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                itemName = nameItemField.getText();
                categoryId = Integer.parseInt(categoryIdField.getText());
                rentPrice = Double.parseDouble(rentalPriceField.getText());

                // Update item
                controller.updateItem(itemName, categoryId, rentPrice, itemId);
                JOptionPane.showMessageDialog(null, "Item successfully updated!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Failed to update item: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editItem() {
        ControllerAdmin controller = new ControllerAdmin(this);

        // Ambil daftar item
        List<Item> items = controller.getItems();

        // Tampilkan item dalam format lengkap
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Item item : items) {
            listModel.addElement(item.getId() + " - " + item.getItem() + " - " + item.getCatId() + " - " + item.getRentPrice());
        }

        // Buat JList
        JList<String> itemList = new JList<>(listModel);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(itemList);
        scrollPane.setPreferredSize(new Dimension(300, 150));

        int option = JOptionPane.showConfirmDialog(null, scrollPane,
                "Choose an item to be changed.",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String selectedValue = itemList.getSelectedValue();

            if (selectedValue != null) {
                try {
                    // Pisahkan data item
                    String[] parts = selectedValue.split(" - ");
                    int itemId = Integer.parseInt(parts[0]);
                    String itemName = parts[1];
                    int categoryId = Integer.parseInt(parts[2]);
                    double rentPrice = Double.parseDouble(parts[3]);

                    // Panggil editItem1
                    editItem1(itemName, categoryId, rentPrice, itemId);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Item data is not valid!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Choose an item first.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Item changes canceled.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void deleteShowDialog() {
        JPanel panel = new JPanel(new BorderLayout());

        // Menyiapkan opsi yang bisa dipilih oleh user
        rentalUnits = new ArrayList<>();
        rentalUnits.add("Category");
        rentalUnits.add("Item");

        // Menyiapkan ListModel untuk JList
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String item : rentalUnits) {
            listModel.addElement(item);
        }

        // Membuat JList dengan listModel
        JList<String> rentalList = new JList<>(listModel);
        rentalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Membungkus JList dengan JScrollPane
        JScrollPane scrollPane = new JScrollPane(rentalList);
        scrollPane.setPreferredSize(new Dimension(150, 80));

        // Menambahkan JScrollPane ke panel
        panel.add(scrollPane, BorderLayout.CENTER);

        // Menampilkan Dialog Pilihan
        int result = JOptionPane.showConfirmDialog(null, panel, "Delete Data",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Jika User Menekan OK
        if (result == JOptionPane.OK_OPTION) {
            String selectedOption = rentalList.getSelectedValue();

            if ("Category".equals(selectedOption)) {
                hapusKategori(); // Panggil fungsi untuk tambah kategori
            } else if ("Item".equals(selectedOption)) {
                hapusItem(); // Panggil fungsi untuk tambah item
            } else {
                JOptionPane.showMessageDialog(null, "Choose a category or item.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Delete Data Failed");
        }
    }

    private void hapusKategori() {
    ControllerAdmin controller = new ControllerAdmin(this);

    // Ambil daftar kategori
    List<Category> categories = controller.getCategories();

    // Buat array untuk menampilkan data kategori dalam format "categoryId - CategoryName"
    DefaultListModel<String> listModel = new DefaultListModel<>();
    for (Category category : categories) {
        listModel.addElement(category.getId() + " - " + category.getCatName());
    }

    // Buat JList dengan model yang telah diisi
    JList<String> categoryList = new JList<>(listModel);
    categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    // Letakkan JList dalam JScrollPane
    JScrollPane scrollPane = new JScrollPane(categoryList);
    scrollPane.setPreferredSize(new Dimension(300, 200));

    // Tampilkan dialog untuk memilih kategori yang akan dihapus
    int option = JOptionPane.showConfirmDialog(null, scrollPane, 
                                                "Choose a category to delete.", 
                                                JOptionPane.OK_CANCEL_OPTION, 
                                                JOptionPane.PLAIN_MESSAGE);

    // Jika tombol OK ditekan dan ada kategori yang dipilih
    if (option == JOptionPane.OK_OPTION) {
        String selectedValue = categoryList.getSelectedValue();

        if (selectedValue != null) {
            // Ambil ID kategori dari hasil yang dipilih
            String[] parts = selectedValue.split(" - ");
            int categoryId = Integer.parseInt(parts[0]);

            try {
                // Panggil controller untuk menghapus kategori
                controller.deleteCategory(categoryId);
                JOptionPane.showMessageDialog(null, "Category successfully deleted!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Failed to delete category: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Choose a category first.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Category deletion canceled.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}

    private void hapusItem() {
    ControllerAdmin controller = new ControllerAdmin(this);

    // Ambil daftar item
    List<Item> items = controller.getItems();

    // Buat array untuk menampilkan data item dalam format "itemId - itemName"
    DefaultListModel<String> listModel = new DefaultListModel<>();
    for (Item item : items) {
        listModel.addElement(item.getId()+ " - " + item.getItem());
    }

    // Buat JList dengan model yang telah diisi
    JList<String> itemList = new JList<>(listModel);
    itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    // Letakkan JList dalam JScrollPane
    JScrollPane scrollPane = new JScrollPane(itemList);
    scrollPane.setPreferredSize(new Dimension(300, 200));

    // Tampilkan dialog untuk memilih item yang akan dihapus
    int option = JOptionPane.showConfirmDialog(null, scrollPane, 
                                                "Choose an item to delete.", 
                                                JOptionPane.OK_CANCEL_OPTION, 
                                                JOptionPane.PLAIN_MESSAGE);

    // Jika tombol OK ditekan dan ada item yang dipilih
    if (option == JOptionPane.OK_OPTION) {
        String selectedValue = itemList.getSelectedValue();

        if (selectedValue != null) {
            // Ambil ID item dari hasil yang dipilih
            String[] parts = selectedValue.split(" - ");
            int itemId = Integer.parseInt(parts[0]);

            try {
                // Panggil controller untuk menghapus item
                controller.deleteItem(itemId);
                JOptionPane.showMessageDialog(null, "Item successfully deleted!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Failed to delete item: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Choose an item first.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Item deletion canceled.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}


    private String getDataItemAndCategory(String choice) {
        daoItem dao = new daoItem();

        // 🔹 Jika user memilih "Category", ambil kategori dari database
        if (choice.equalsIgnoreCase("Category")) {
            List<Category> categories = dao.getCategory();
            List<String> categoryNames = new ArrayList<>();

            for (Category category : categories) {
                categoryNames.add(category.getId() + " - " + category.getCatName());
            }

            return categoryNames.isEmpty() ? "No categories available." : "Category:\n" + String.join("\n", categoryNames);

        }

        // 🔹 Jika user memilih "Item", ambil item dari database
        if (choice.equalsIgnoreCase("Item")) {
            List<Item> items = dao.getItem();
            List<String> itemNames = new ArrayList<>();

            for (Item item : items) {
                itemNames.add(item.getItem()); // Ambil nama item
            }

            return itemNames.isEmpty() ? "No Item available" : "Item:\n" + String.join("\n", itemNames);
        }

        return "Invalid choice.";
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnShow;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel textAdmin;
    private javax.swing.JTextArea textAreaData;
    // End of variables declaration//GEN-END:variables
}
