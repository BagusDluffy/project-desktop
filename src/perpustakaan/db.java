package perpustakaan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class db {
    
    public static Connection mycon(){
        Connection con = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_perpustakaan","root","");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        
        return con;
    }
    public static int execute(String SQL) {
        int status = 0;
        Connection koneksi = mycon();
        try {
            Statement st = koneksi.createStatement();
            status = st.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    public static ResultSet executeQuery(String SQL) {
        ResultSet rs = null;
        Connection koneksi = mycon();
        try {
            Statement st = koneksi.createStatement();
            rs = st.executeQuery(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
     public static List<String> retrieveBookTitles() {
        List<String> bookTitles = new ArrayList<>();                                
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = db.mycon();
            statement = connection.createStatement();
            
            // Query untuk mengambil judul buku dari tabel buku
            String query = "SELECT judul FROM buku";
            resultSet = statement.executeQuery(query);
            
            // Mengambil data dari result set
            while (resultSet.next()) {
                bookTitles.add(resultSet.getString("Judul"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Menutup resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bookTitles;
    }
}
