/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package perpustakaan;

import java.sql.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class Rent extends javax.swing.JFrame {
     private Connection dbConnection;
    
    public Rent() {
        initComponents();
        this.kotak1 = kotak1;
        this.booktitle = booktitle;
        
        setTitle("Book Titles JComboBox");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        // Membuat JComboBox
        kotak1.add(booktitle);
        add(kotak1, BorderLayout.CENTER);
        // Mengambil data dari database dan mengisi JComboBox
        try {
            fillComboBoxFromDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectData(){
            String nm = Nama_siswa.getText();
            String kolom[] = {"Id_pinjam","Nama_siswa","tgl_pinjam","tgl_kembali","denda","Status"};
            DefaultTableModel dtm = new DefaultTableModel(null, kolom);
            String SQL = "SELECT * FROM peminjaman Where Nama_siswa='"+nm+"'";
            ResultSet rs = db.executeQuery(SQL);
            try {
                while(rs.next()) {
                    String Id_pinjam = rs.getString(1);
                    String Nama_siswa = rs.getString(5);
                    String tgl_pinjam = rs.getString(6);
                    String tgl_kembali = rs.getString(7);
                    String denda = rs.getString(8);
                    String status = rs.getString(9);

                    String data[] = {Id_pinjam,Nama_siswa,tgl_pinjam,tgl_kembali,denda,status};
                    dtm.addRow(data);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Admin_acount.class.getName()).log(Level.SEVERE, null, ex);
            }
            table.setModel(dtm);
    }
    private int getIdUser(String namaSiswa){
        int idUser = -1;
        namaSiswa = Nama_siswa.getText();
        Connection dbConnection = db.mycon();
    // Implementasikan logika untuk mendapatkan Id_user dari tabel user berdasarkan namaSiswa
    // Misalnya, lakukan query ke database dan ambil Id_user
    String query = "SELECT Id_user FROM user WHERE Username = '" + namaSiswa + "'";
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getInt("Id_User");
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idUser; // Return -1 if no user found
    }
    private int getBookId(String namaBuku) {
        namaBuku = booktitle.getSelectedItem().toString();
        Connection dbConnection = db.mycon();
        String query = "SELECT Id_buku FROM buku WHERE Judul = '" + namaBuku + "'";
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getInt("Id_buku");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if no book found
    }
    private int getIdStaff(String namaStaff){
        namaStaff = Nama_staff.getText();
        Connection dbConnection = db.mycon();
        String query = "SELECT Id_staff FROM staff WHERE Nama_staff = '" + namaStaff + "'";
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getInt("Id_staff");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if no staff found
}
//    private void handleSubmit() {
//        String username = Nama_siswa.getText();
//        String id = "select Id_user from user where Username="+username;
//        try {
//            Connection conn = getConnection();
//            Date tglPeminjaman = new Date();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Object tglKembali = rentTime.getSelectedItem();
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(tglPeminjaman);
//            if (tglKembali.equals("1 Day")){
//                calendar.add(Calendar.DATE, 1);   
//            }
//            Date tglKembalibaru = calendar.getTime();
//            String tanggalPengembalianFormatted = dateFormat.format(tglKembalibaru);
//            // Query to join three tables
//            String joinQuery = "SELECT u.Id_user, b.Id_buku, s.Id_staff FROM user u" +
//                               "Left JOIN buku ON b.Id_buku = u.Id_user" +
//                               "Right JOIN staff ON s.Id_staff = u.Id_user" +
//                               "Where Username="+id;
//
//            PreparedStatement joinStmt = conn.prepareStatement(joinQuery);
//            ResultSet rs = joinStmt.executeQuery();
//
//            // Insert data into peminjaman table if join data is found
//            String insertQuery = "INSERT INTO peminjaman (Id_user, Id_buku, Id_staff, tgl_peminjaman, tgl_kembali, denda) " +
//                                 "VALUES (?, ?, ?, ?, ?, ?)";
//            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
//
//            while (rs.next()) {
//                int idUser = rs.getInt("Id_user");
//                int idBuku = rs.getInt("Id_buku");
//                int idStaff = rs.getInt("Id_staff");
//                String denda = "Baik";
//
//                insertStmt.setInt(1, idUser);
//                insertStmt.setInt(2, idBuku);
//                insertStmt.setInt(3, idStaff);
//                insertStmt.setString(4, tanggalPengembalianFormatted);
//                insertStmt.setString(5, (String)tglKembali);
//                insertStmt.setString(6, denda); 
//                insertStmt.addBatch();
//            }
//
//            insertStmt.executeBatch();
//            JOptionPane.showMessageDialog(this, "Data berhasil dimasukkan ke tabel peminjaman.");
//
//            rs.close();
//            joinStmt.close();
//            insertStmt.close();
//            conn.close();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage());
//        }
//    }
//
//    private Connection getConnection() throws Exception {
//        String url = "jdbc:mysql://localhost:3306/db_perpustakaan"; // Ganti dengan URL database Anda
//        String username = "root"; // Ganti dengan username database Anda
//        String password = ""; // Ganti dengan password database Anda
//        Connection conn = DriverManager.getConnection(url, username, password);
//        return conn;
//    }
    
    private void fillComboBoxFromDatabase() throws SQLException {
        List<String> bookTitles = db.retrieveBookTitles();
        for (String title : bookTitles) {
            booktitle.addItem(title);
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

        kotak1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Nama_siswa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Nama_staff = new javax.swing.JTextField();
        booktitle = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        rentTime = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        Submit = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table.setGridColor(new java.awt.Color(0, 0, 0));
        table.setShowGrid(true);
        jScrollPane1.setViewportView(table);

        jPanel2.setBackground(new java.awt.Color(51, 153, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Rent a book");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(213, 213, 213)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Username :");

        Nama_siswa.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Staff Name :");

        Nama_staff.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("Choose Book :");

        rentTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 Day", "2 Days", "3 Days", "4 Days", "5 Days", "6 Days", "7 Days" }));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("Rent Time :");

        Submit.setText("Submit");
        Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt);
            }
        });

        refresh.setText("Refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("History :");

        javax.swing.GroupLayout kotak1Layout = new javax.swing.GroupLayout(kotak1);
        kotak1.setLayout(kotak1Layout);
        kotak1Layout.setHorizontalGroup(
            kotak1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(kotak1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kotak1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kotak1Layout.createSequentialGroup()
                        .addGroup(kotak1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kotak1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Nama_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(kotak1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(booktitle, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(kotak1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(kotak1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rentTime, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Nama_staff, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kotak1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Submit)))
                .addContainerGap())
        );
        kotak1Layout.setVerticalGroup(
            kotak1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kotak1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(kotak1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Nama_siswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(Nama_staff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(kotak1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(booktitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rentTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                .addGroup(kotak1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kotak1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Submit)
                        .addComponent(refresh))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kotak1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kotak1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitActionPerformed
        // TODO add your handling code here:
        if("".equals(Nama_siswa.getText())||"".equals(Nama_staff.getText())){
            JOptionPane.showMessageDialog(this, "Please fill data correctly", "Error", JOptionPane.WARNING_MESSAGE);
        }else{
            Date tglPeminjaman = new Date();
            String tglKembali = "";
            String tglKembaliValue = rentTime.getSelectedItem().toString();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String tglPeminjamanFormat = dateFormat.format(tglPeminjaman);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(tglPeminjaman);
            if (tglKembaliValue.equals("1 Day")){
                calendar.add(Calendar.DATE, 1);
                Date tglKembalibaru = calendar.getTime();
                String tanggalPengembalianFormatted = dateFormat.format(tglKembalibaru);
                tglKembali = tanggalPengembalianFormatted;
            } else if (tglKembaliValue.equals("2 Days")){
                calendar.add(Calendar.DATE, 2);
                Date tglKembalibaru = calendar.getTime();
                String tanggalPengembalianFormatted = dateFormat.format(tglKembalibaru);
                tglKembali = tanggalPengembalianFormatted;
            } else if (tglKembaliValue.equals("3 Days")){
                calendar.add(Calendar.DATE,3);
                Date tglKembalibaru = calendar.getTime();
                String tanggalPengembalianFormatted = dateFormat.format(tglKembalibaru);
                tglKembali = tanggalPengembalianFormatted;
            } else if (tglKembaliValue.equals("4 Days")){
                calendar.add(Calendar.DATE,4);
                Date tglKembalibaru = calendar.getTime();
                String tanggalPengembalianFormatted = dateFormat.format(tglKembalibaru);
                tglKembali = tanggalPengembalianFormatted;
            } else if (tglKembaliValue.equals("5 Days")){
                calendar.add(Calendar.DATE,5);
                Date tglKembalibaru = calendar.getTime();
                String tanggalPengembalianFormatted = dateFormat.format(tglKembalibaru);
                tglKembali = tanggalPengembalianFormatted;
            } else if (tglKembaliValue.equals("6 Days")){
                calendar.add(Calendar.DATE, 6);
                Date tglKembalibaru = calendar.getTime();
                String tanggalPengembalianFormatted = dateFormat.format(tglKembalibaru);
                tglKembali = tanggalPengembalianFormatted;
            } else if (tglKembaliValue.equals("7 Days")){
                calendar.add(Calendar.DATE, 7);
                Date tglKembalibaru = calendar.getTime();
                String tanggalPengembalianFormatted = dateFormat.format(tglKembalibaru);
                tglKembali = tanggalPengembalianFormatted;
            }
            String Status = "Belum Kembali";
            int Id_user = getIdUser(Nama_siswa.getText()); // Metode untuk mendapatkan Id_user berdasarkan namaSiswa
            int Id_buku = getBookId(booktitle.getSelectedItem().toString()); // Metode untuk mendapatkan Id_buku berdasarkan namaBuku
            int Id_staff = getIdStaff(Nama_staff.getText()); // Metode untuk mendapatkan Id_staff berdasarkan namaStaff
            String SQL = "INSERT INTO peminjaman (Id_user,Id_buku,Id_staff,Nama_siswa, tgl_pinjam, tgl_kembali,Status)"+
            "VALUES('"+Id_user+"','"+Id_buku+"','"+Id_staff+"','"+Nama_siswa.getText()+"','"+tglPeminjamanFormat+"','"+tglKembali+"','"+Status+"')";
            int status = db.execute(SQL);
            if(status == 1){
                JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                selectData();
            }else{
                JOptionPane.showMessageDialog(this, "Data gagal ditambahkan", "Gagal", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_SubmitActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
        selectData();
    }//GEN-LAST:event_refreshActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Rent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Rent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Rent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Rent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Rent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Nama_siswa;
    private javax.swing.JTextField Nama_staff;
    private javax.swing.JButton Submit;
    private javax.swing.JComboBox<String> booktitle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel kotak1;
    private javax.swing.JButton refresh;
    private javax.swing.JComboBox<String> rentTime;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
