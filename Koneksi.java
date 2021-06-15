/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KONEKSI;
import java.sql. *; //variabel koneksi
import javax.swing.JOptionPane;


public class Koneksi {
    Connection conn;
        public Connection getConnection(){
            try{
                    conn = DriverManager.getConnection("jdbc:mysql://localhost/Biodata","root","");
            }catch(SQLException e){
                    JOptionpane.showMessageDialog(null, "Koneksi ke Database gagal periksa kembali koneksi anda","error",JOptionPane.ERROR_MESSAGE);
            }
            return conn;
                    
}

    private static class JOptionpane {

        private static void showMessageDialog(Object object, String koneksi_ke_Database_gagal_periksa_kembali, String error, int ERROR_MESSAGE) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public JOptionpane() {
        }
    }
    
}
