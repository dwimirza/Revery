/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author USER
 */
public class daoItem {
//     Connection connection;
//    final String insert = "INSERT INTO film (judul,sinopsis,tahun) VALUES (?, ?, ?);";
//    final String update = "UPDATE film SET judul=?, sinopsis=?, tahun=? WHERE id=? ;";
//    final String delete = "DELETE FROM film WHERE id=? ;";
//    final String select = "SELECT * FROM film ORDER BY id DESC;";
    // membuat objek koneksi dari Class Connection
    
    
private Connection connection = null;
/* membuat fungsi setConnection untuk menghubungkan aplikasi ke database */
public Connection setConnection() {
    try{
        // melakukan inisialisasi nama database ke variabel url
        String url = "jdbc:mysql://localhost:3306/dbrevery";
        // mengatur user yang akan mengakases localhost (default)
        String user = "root";
        // mengatur pass untuk mengakases localhost (default)
        String pass = "";
        // memuat driver MySQL JDBC dengan fungsi forName
        Class.forName("com.mysql.jdbc.Driver");

        /* memberi nilai koneksi dengan data yang telah diatur sebelum ke

        objek connection */
        connection = DriverManager.getConnection(url, user, pass);
        }catch(Exception e){
            System.out.println("Error "+e.getMessage());
        }
    return connection; // mengembalikan koneksi
  }
}
