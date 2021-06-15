
package biodata_anak;
    
    import static Koneksi.Koneksi.conn;
    import java.awt.HeadlessException;
    import java.sql.Connection;
    import java.sql.SQLException;
    import javax.swing.JOptionPane; 
    import javax.swing.table.DefaultTableModel;

public class datadata extends javax.swing.JFrame {
   //syntax untuk menampilkan isi table data
    private void tampilkanisitable(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");        
        model.addColumn("Nama");        
        model.addColumn("Kelas");
        model.addColumn("Agama");
        model.addColumn("Alamat");
        try{
            int No= 1;
            String sql = "SELECT * FROM biodata_anak order by Nama";
            Connection conn = conn();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object [ ] {No++, res.getString(1), res.getString(2), res.getString(3), res.getString(4)});
            }
            tabledata.setModel(model);
            tabledata.getColumnModel().getColumn(0).setPreferredWidth(36);
            tabledata.getColumnModel().getColumn(1).setPreferredWidth(150);
            tabledata.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabledata.getColumnModel().getColumn(3).setPreferredWidth(90);
            tabledata.getColumnModel().getColumn(4).setPreferredWidth(90);
        }catch (SQLException e){
            System.out.println("Error : " +e.getMessage());
        }   
        
    }    
    //syntax untuk cari data
            private void caridata(){
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("No");        
                model.addColumn("Nama");        
                model.addColumn("Kelas");
                model.addColumn("Agama");
                model.addColumn("Alamat");
                try{
                            String sql = "SELECT * FROM biodata_anak where Nama like '%" + vcari.getText() +"%' or Nama like '%" + vcari.getText() +"%'";
                            Connection conn = conn();
                            java.sql.Statement stm = conn.createStatement();
                            java.sql.ResultSet res = stm.executeQuery(sql);
                            int No=1;
                            while(res.next()){
                                model.addRow(new Object [ ] {No++, res.getString(1), res.getString(2), res.getString(3), res.getString(4)});
                            }
            tabledata.setModel(model);
            tabledata.getColumnModel().getColumn(0).setPreferredWidth(36);
            tabledata.getColumnModel().getColumn(1).setPreferredWidth(150);
            tabledata.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabledata.getColumnModel().getColumn(3).setPreferredWidth(90);
            tabledata.getColumnModel().getColumn(4).setPreferredWidth(90);
             }catch (SQLException e){
            System.out.println("Error : " +e.getMessage());
             }
        }    
            
           
            //syntax membersihkan form
            private void kosongkan(){
                
                vnama.setText(null);
                vkelas.setText(null);
                cmbagama.getSelectedItem();
                valamat.setText(null);
                vcari.setText(null);
                cmbagama.setSelectedItem(null);
                
                vnama.setEnabled(false);
                vkelas.setEnabled(false);
                cmbagama.setEnabled(false);
                valamat.setEnabled(false);
                vnama.requestFocus();
            }
            //syntax hidupkan form
            private void hidupkan(){
                btnubah.setText("UBAH");
                btnhapus.setText("HAPUS");
             
                vnama.setEnabled(true);
                vkelas.setEnabled(true);
                cmbagama.setSelectedItem("PILIH");
                cmbagama.setEnabled(true);
                valamat.setEnabled(true);
                vnama.requestFocus();          
            }
            //syntax simpan data
            private void simpandata(){
               
                if (vnama.getText().isEmpty()){
                            JOptionPane.showMessageDialog(rootPane, "Nama tidak boleh kosong");
                            vnama.requestFocus();
                }else if (vkelas.getText().isEmpty()){
                            JOptionPane.showMessageDialog(rootPane, "Kelas tidak boleh kosong");
                            vkelas.requestFocus();
                }else if (cmbagama.getSelectedItem().equals("PILIH")){
                            JOptionPane.showMessageDialog(rootPane, "pilih agama terlebih dahulu");
                            cmbagama.requestFocus();
                }else if (valamat.getText().isEmpty()){
                            JOptionPane.showMessageDialog(rootPane, "Alamat tidak boleh kosong");
                            valamat.requestFocus(); 
                }else {
                            try{
                                        String sql ="insert into biodata_anak values ('" +  vnama.getText() +"','" + vkelas.getText() +"','" + cmbagama.getSelectedItem() +"','" + valamat.getText() +"','" ;
                                        Connection conn = conn();
                                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                                        pst.execute(sql);
                                        tampilkanisitable();
                                        JOptionPane.showMessageDialog(rootPane, "simpan data sukses","imformasi", JOptionPane.INFORMATION_MESSAGE);
                                        kosongkan();
                                        }catch(HeadlessException | SQLException e){
                                           JOptionPane.showMessageDialog(this, e.getMessage());
                                        }
                }
            }
               
            

            //syntax untuk ubah data
                private void ubahdata(){
                    if (vnama.getText().isEmpty()){
                        JOptionPane.showMessageDialog(rootPane,"pilih data yang akan di ubah pada table terlebih dahulu");
                        vnama.requestFocus();  
                    }else{
                            if(JOptionPane.showConfirmDialog(rootPane, "apakah anda ingin mengedit" + "?", "Konfirmasi",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                                    try{
                                        String sql ="update biodata_anak set Nama='" + vnama.getText() +"',Kelas='" + vkelas.getText() +"',Agama='" + cmbagama.getSelectedItem() +"',Alamat='" + valamat.getText() + "'";
                                        Connection conn = conn();
                                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                                        pst.execute(sql);
                                        tampilkanisitable();
                                        JOptionPane.showMessageDialog(rootPane, "ubah data sukses","imformasi", JOptionPane.INFORMATION_MESSAGE);
                                        kosongkan();
                                        }catch(HeadlessException | SQLException e){
                                           JOptionPane.showMessageDialog(this, e.getMessage());
                                        }
                            }else{
                                    JOptionPane.showMessageDialog(rootPane,"Hapus data gagal","informasi",JOptionPane.ERROR_MESSAGE);    
                                    kosongkan();
                                    
                            }
                    }
                }
                //syntax untuk menghapus data
                private void hapusdata(){
                    if (vnama.getText().isEmpty()){
                        JOptionPane.showMessageDialog(rootPane,"pilih data yang akan di hapus pada table terlebih dahulu");
                        vnama.requestFocus();  
                    }else{
                            if(JOptionPane.showConfirmDialog(rootPane, "apakah anda ingin menghapus" +"?", "Konfirmasi",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                                    try{
                                        String sql = "Delete from biodata_anak where Nama='" + vnama.getText() +"',Kelas='" + vkelas.getText() +"',Agama='" + cmbagama.getSelectedItem() +"',Alamat='" + valamat.getText() + "'";
                                        Connection conn = conn();
                                        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                                        pst.execute(sql);
                                        tampilkanisitable();
                                        JOptionPane.showMessageDialog(rootPane, "hapus data sukses");
                                        kosongkan();
                                    }catch(HeadlessException | SQLException e){
                                           JOptionPane.showMessageDialog(this, e.getMessage());
                                    }
                            }else{
                                    JOptionPane.showMessageDialog(rootPane,"Hapus data gagal","informasi",JOptionPane.ERROR_MESSAGE);    
                                    kosongkan();
                            }        
                }
                }
                    private void tampilkan_isi_data_table(){
                        hidupkan();
                        int row = tabledata.getSelectedRow();
                        vnama.setText(tabledata.getModel().getValueAt(row,2).toString());
                        vkelas.setText(tabledata.getModel().getValueAt(row,3).toString());
                        cmbagama.setSelectedItem(tabledata.getModel().getValueAt(row,4).toString());
                        valamat.setText(tabledata.getModel().getValueAt(row,5).toString());
                        vnama.requestFocus();
                    }

                    
                    private void tutup(){
                        if (JOptionPane.showConfirmDialog(rootPane, "Apakah anda ingin menutup form biodata ini ?","Komfirmasi",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                                dispose();
                        }else{
                            kosongkan();
                        }        
                        
                    }

                public datadata() {
                   initComponents();
                   Connection conn = conn();
                        tampilkanisitable();
                        kosongkan();
                        setLocationRelativeTo(null);
                }
              
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        vnama = new javax.swing.JTextField();
        vkelas = new javax.swing.JTextField();
        cmbagama = new javax.swing.JComboBox<>();
        valamat = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btntambah = new javax.swing.JButton();
        btnsimpan = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        btnubah = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        vcari = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabledata = new javax.swing.JTable();

        jScrollPane2.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 51, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BIODATA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(273, 273, 273)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("NAMA");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("KELAS");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("AGAMA");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("ALAMAT");

        vnama.setBackground(new java.awt.Color(204, 204, 204));

        vkelas.setBackground(new java.awt.Color(204, 204, 204));

        cmbagama.setBackground(new java.awt.Color(0, 102, 102));
        cmbagama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PILIH", "PROTESTAN", "KATOLIK", "ISLAM", "BUDDHA", "HINDU", "KHONGHUCU" }));

        valamat.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vkelas, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vnama, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbagama, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valamat, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(vnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(vkelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbagama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(valamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 0, 0));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        btntambah.setBackground(new java.awt.Color(255, 204, 255));
        btntambah.setText("TAMBAH");
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });

        btnsimpan.setBackground(new java.awt.Color(255, 204, 204));
        btnsimpan.setText("SIMPAN");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btnhapus.setBackground(new java.awt.Color(255, 204, 204));
        btnhapus.setText("HAPUS");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        btnubah.setBackground(new java.awt.Color(255, 204, 204));
        btnubah.setText("UBAH");
        btnubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnubahActionPerformed(evt);
            }
        });

        btnbatal.setBackground(new java.awt.Color(255, 204, 204));
        btnbatal.setText("BATAL");
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("CARI DATA:");

        vcari.setBackground(new java.awt.Color(204, 204, 255));
        vcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vcariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btntambah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnhapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnubah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnbatal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(vcari)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btntambah)
                            .addComponent(btnsimpan)
                            .addComponent(btnhapus)
                            .addComponent(btnubah)
                            .addComponent(btnbatal))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(vcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3))))
        );

        tabledata.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabledata);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        hidupkan();
    }//GEN-LAST:event_btntambahActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        simpandata();
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        hapusdata();
        hidupkan();
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btnubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubahActionPerformed
        ubahdata();
        hidupkan();
    }//GEN-LAST:event_btnubahActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        kosongkan();
        hidupkan();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void vcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vcariActionPerformed
        caridata();
    }//GEN-LAST:event_vcariActionPerformed

    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(datadata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(datadata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(datadata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(datadata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new datadata().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton btntambah;
    private javax.swing.JButton btnubah;
    private javax.swing.JComboBox<String> cmbagama;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabledata;
    private javax.swing.JTextField valamat;
    private javax.swing.JTextField vcari;
    private javax.swing.JTextField vkelas;
    private javax.swing.JTextField vnama;
    // End of variables declaration//GEN-END:variables
}
