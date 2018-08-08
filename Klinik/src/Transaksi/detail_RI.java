/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaksi;

import Koneksi.Koneksi;
import Koneksi.Session;
import com.mysql.jdbc.Connection;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class detail_RI extends javax.swing.JFrame {

    /**
     * Creates new form detail_RI
     */
    public detail_RI() {
        initComponents();
        tampil_tindakan();
        tampil_obat();
        total_tindakan();
        total_resep();
        kamar();
        dokter();

        
    }
    void total_tindakan(){
        String id5 = Session.getId5();
        if(id5.equals("Ya")){
            txt_totalTindakan.setText("0");
        }
        else {
            int batas = tabel_tindakan.getRowCount();
            int sum = 0;
            for(int i=0; i < batas; i++)
            {
                sum = sum + Integer.parseInt(tabel_tindakan.getValueAt(i,3).toString());
            }
            txt_totalTindakan.setText(Integer.toString(sum));
        }
        
    }
    void total_resep(){
        String id5 = Session.getId5();
        if(id5.equals("Ya")){
        txt_totalResep.setText("0");
        } else {
            int batas = tabel_obat.getRowCount();
            int sum = 0;
            for(int i=0; i < batas; i++)
            {
                sum = sum + Integer.parseInt(tabel_obat.getValueAt(i,4).toString());
            }
            txt_totalResep.setText(Integer.toString(sum));
        }
        
    }
    
    private void tampil_tindakan(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nama Tindakan");
        model.addColumn("Tanggal Tindakan");
        model.addColumn("Jumlah Bayar");

        
        //menampilkan data database kedalam tabel
        try {
            int no=1;
            String id = Session.getId();
            String sql = "SELECT tindakan.nama,tgl_tindakan,total_harga FROM detail_biayalain JOIN tindakan on tindakan.id_tindakan=detail_biayalain.id_tindakan where id_biayaLain='"+id+"'";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{no++,res.getString(1),res.getString(2),res.getString(3)});
            }
            tabel_tindakan.setModel(model);
        } catch (SQLException e) {
        }
    }
    private void tampil_obat(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nama Obat");
        model.addColumn("Jumlah Satuan");
        model.addColumn("Harga");
        model.addColumn("Jumlah Bayar");

        
        //menampilkan data database kedalam tabel
        try {
            int no=1;
            String id2 = Session.getId2();
            String sql = "SELECT obat.nama,jumlah_satuan,obat.harga_jual,total_harga FROM detail_resep JOIN obat on obat.id_obat=detail_resep.id_obat where id_resep='"+id2+"'";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{no++,res.getString(1),res.getString(2),res.getString(3),res.getString(4)});
            }
            tabel_obat.setModel(model);
        } catch (SQLException e) {
        }
    }
    void kamar(){
        try {
            //int no=1;
            String id3 = Session.getId3();
            String sql = "SELECT * FROM kamar WHERE id_kamar='"+id3+"'";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                String id5 = Session.getId5();
                if(id5.equals("Ya")){
                nama_kamar.setText(res.getString("nama"));
                harga_kamar.setText("0");
                } else {
                nama_kamar.setText(res.getString("nama"));
                harga_kamar.setText(res.getString("harga"));   
                }
                
                
            }   
        } catch (SQLException e) {
        }
    }
    void dokter(){
        try {
            //int no=1;
            String id4 = Session.getId4();
            String sql = "SELECT * FROM dokter WHERE id_dokter='"+id4+"'";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                String id5 = Session.getId5();
                if(id5.equals("Ya")){
                nama_dokter.setText(res.getString("nama"));
                jenkel_dokter.setText(res.getString("jenis_kelamin"));
                spesialis.setText(res.getString("spesialis"));
                tarif.setText("0");
                } else {
                nama_dokter.setText(res.getString("nama"));
                jenkel_dokter.setText(res.getString("jenis_kelamin"));
                spesialis.setText(res.getString("spesialis"));
                tarif.setText(res.getString("tarif"));  
                }  
      
            }   
        } catch (SQLException e) {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nama_kamar = new javax.swing.JLabel();
        harga_kamar = new javax.swing.JLabel();
        tarif = new javax.swing.JLabel();
        spesialis = new javax.swing.JLabel();
        jenkel_dokter = new javax.swing.JLabel();
        nama_dokter = new javax.swing.JLabel();
        txt_totalResep = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_obat = new javax.swing.JTable();
        txt_totalTindakan = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_tindakan = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Jenis Kelamin");

        nama_kamar.setForeground(new java.awt.Color(255, 255, 255));
        nama_kamar.setText("Nama Kamar");

        harga_kamar.setForeground(new java.awt.Color(255, 255, 255));
        harga_kamar.setText("Harga Kamar");

        tarif.setForeground(new java.awt.Color(255, 255, 255));
        tarif.setText("Tarif");

        spesialis.setForeground(new java.awt.Color(255, 255, 255));
        spesialis.setText("Spesialis");

        jenkel_dokter.setForeground(new java.awt.Color(255, 255, 255));
        jenkel_dokter.setText("Jenis Kelamin");

        nama_dokter.setForeground(new java.awt.Color(255, 255, 255));
        nama_dokter.setText("Nama Dokter");

        txt_totalResep.setEditable(false);
        txt_totalResep.setBackground(new java.awt.Color(102, 102, 102));
        txt_totalResep.setForeground(new java.awt.Color(255, 255, 255));

        tabel_obat.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabel_obat);

        txt_totalTindakan.setEditable(false);
        txt_totalTindakan.setBackground(new java.awt.Color(102, 102, 102));
        txt_totalTindakan.setForeground(new java.awt.Color(255, 255, 255));

        tabel_tindakan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabel_tindakan);

        jButton1.setText("KEMBALI");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nama");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Harga");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Spesialis");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nama");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tarif");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Detail Dokter");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Daftar Tindakan");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Daftar Obat");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Detail Kamar");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Total Tindakan");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Total Resep");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(harga_kamar)
                                            .addComponent(nama_kamar)))
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(18, 18, 18))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel4)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jLabel5))
                                                .addGap(41, 41, 41)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(nama_dokter)
                                            .addComponent(spesialis)
                                            .addComponent(jenkel_dokter)
                                            .addComponent(tarif))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(218, 218, 218))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_totalResep, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(24, 24, 24))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txt_totalTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(213, 213, 213))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nama_kamar)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(harga_kamar)
                            .addComponent(jLabel3)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_totalTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(nama_dokter))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jenkel_dokter)
                            .addComponent(jLabel1))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spesialis)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tarif)
                            .addComponent(jLabel6))))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_totalResep, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new Riwayat().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(detail_RI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(detail_RI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(detail_RI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(detail_RI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new detail_RI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel harga_kamar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jenkel_dokter;
    private javax.swing.JLabel nama_dokter;
    private javax.swing.JLabel nama_kamar;
    private javax.swing.JLabel spesialis;
    private javax.swing.JTable tabel_obat;
    private javax.swing.JTable tabel_tindakan;
    private javax.swing.JLabel tarif;
    private javax.swing.JTextField txt_totalResep;
    private javax.swing.JTextField txt_totalTindakan;
    // End of variables declaration//GEN-END:variables
}
