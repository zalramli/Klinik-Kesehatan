/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaksi;

import Koneksi.Koneksi;
import Koneksi.Session;
import com.mysql.jdbc.Connection;
import java.awt.Component;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Lenovo
 */
public class Daftar_RI extends javax.swing.JFrame {

    /**
     * Creates new form Daftar_RI
     */
    public Daftar_RI() {
        initComponents();
        tampil_data();
        this.setLocation(208, 99);
        hidden();
    }

    private void kosong() {
        txt_idRI.setText(null);
        txt_idBiaya.setText(null);
        txt_idResep.setText(null);
        txt_totalHarga.setText(null);
        tgl_masuk.setText(null);
        harga_kamar.setText(null);
        bpjs.setText(null);
        jumlah_kamar.setText(null);
        jumlah_terpakai.setText(null);
        id_kamar.setText(null);
        id_pasien.setText(null);
        
    }

    private void hidden() {
        txt_idBiaya.setVisible(false);
        txt_idResep.setVisible(false);
        harga_kamar.setVisible(false);
        tgl_masuk.setVisible(false);
        txt_totalHarga.setVisible(false);
        bpjs.setVisible(false);
        jumlah_kamar.setVisible(false);
        jumlah_terpakai.setVisible(false);
        id_kamar.setVisible(false);
        id_pasien.setVisible(false);
        txt_idRI.setVisible(false);
        
        tampil_nama.setVisible(false);
        alamat.setVisible(false);
        kamar.setVisible(false);
        dokter.setVisible(false);
        tampil_bpjs.setVisible(false);
    }

    private void tampil_data() {
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        jTable1.setModel(model);
        model.addColumn("ID");
        model.addColumn("ID Resep");
        model.addColumn("ID Biaya");
        model.addColumn("Nama");
        model.addColumn("Alamat");
        model.addColumn("Dokter");
        model.addColumn("Kamar");
        model.addColumn("Harga");
        model.addColumn("BPJS");
        model.addColumn("Tgl Masuk");
        model.addColumn("Tgl Keluar");
        model.addColumn("Total");
        model.addColumn("Jumlah");
        model.addColumn("Terpakai");
        model.addColumn("id");
        model.addColumn("id2");
        TableColumn col1 = jTable1.getColumnModel().getColumn(1);
        col1.setMinWidth(0);
        col1.setMaxWidth(0);
        col1.setWidth(0);
        col1.setPreferredWidth(0);
        TableColumn col2 = jTable1.getColumnModel().getColumn(2);
        col2.setMinWidth(0);
        col2.setMaxWidth(0);
        col2.setWidth(0);
        col2.setPreferredWidth(0);
        TableColumn col7 = jTable1.getColumnModel().getColumn(7);
        col7.setMinWidth(0);
        col7.setMaxWidth(0);
        col7.setWidth(0);
        col7.setPreferredWidth(0);
        TableColumn col12 = jTable1.getColumnModel().getColumn(12);
        col12.setMinWidth(0);
        col12.setMaxWidth(0);
        col12.setWidth(0);
        col12.setPreferredWidth(0);
        TableColumn col13 = jTable1.getColumnModel().getColumn(13);
        col13.setMinWidth(0);
        col13.setMaxWidth(0);
        col13.setWidth(0);
        col13.setPreferredWidth(0);
        TableColumn col14 = jTable1.getColumnModel().getColumn(14);
        col14.setMinWidth(0);
        col14.setMaxWidth(0);
        col14.setWidth(0);
        col14.setPreferredWidth(0);
        TableColumn col15 = jTable1.getColumnModel().getColumn(15);
        col15.setMinWidth(0);
        col15.setMaxWidth(0);
        col15.setWidth(0);
        col15.setPreferredWidth(0);

        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            String status = "Belum";
            String sql = "SELECT id_rawatInap,id_resep,id_biayaLain,pasien.nama AS nama_pasien,pasien.alamat AS alamat_pasien,dokter.nama AS nama_dokter,kamar.nama AS nama_kamar,kamar.harga,bpjs,tgl_masuk,tgl_keluar,total_bayar,kamar.jumlah_kamar,kamar.jumlah_terpakai,kamar.id_kamar,pasien.id_pasien FROM rawat_inap JOIN pasien ON rawat_inap.id_pasien=pasien.id_pasien JOIN dokter ON rawat_inap.id_dokter=dokter.id_dokter JOIN kamar ON rawat_inap.id_kamar=kamar.id_kamar WHERE rawat_inap.status='" + status + "'";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9), res.getString(10), res.getString(11), res.getString(12), res.getString(13), res.getString(14), res.getString(15), res.getString(16)});
            }
            //jTable1.setModel(model);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txt_idRI = new javax.swing.JTextField();
        txt_idBiaya = new javax.swing.JTextField();
        txt_idResep = new javax.swing.JTextField();
        btn_detail = new javax.swing.JButton();
        btn_kembali = new javax.swing.JButton();
        txt_totalHarga = new javax.swing.JTextField();
        btn_checkout = new javax.swing.JButton();
        tgl_masuk = new javax.swing.JTextField();
        test = new javax.swing.JTextField();
        harga_kamar = new javax.swing.JTextField();
        txt_cari = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        bpjs = new javax.swing.JTextField();
        jumlah_kamar = new javax.swing.JTextField();
        jumlah_terpakai = new javax.swing.JTextField();
        id_kamar = new javax.swing.JTextField();
        id_pasien = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tampil_nama = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        alamat = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        kamar = new javax.swing.JLabel();
        dokter = new javax.swing.JLabel();
        tampil_bpjs = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        tampil_bpjs1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(1, 152, 117));
        jPanel1.setPreferredSize(new java.awt.Dimension(1125, 630));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btn_detail.setText("Lihat Detail");
        btn_detail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_detailActionPerformed(evt);
            }
        });

        btn_kembali.setText("KEMBALI");
        btn_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliActionPerformed(evt);
            }
        });

        btn_checkout.setText("CHECK OUT");
        btn_checkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_checkoutActionPerformed(evt);
            }
        });

        test.setEditable(false);
        test.setBackground(new java.awt.Color(1, 152, 117));
        test.setForeground(new java.awt.Color(255, 255, 255));

        btn_cari.setText("CARI PASIEN");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nama");

        tampil_nama.setForeground(new java.awt.Color(255, 255, 255));
        tampil_nama.setText("jLabel1");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Alamat");

        alamat.setForeground(new java.awt.Color(255, 255, 255));
        alamat.setText("jLabel1");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("BPJS");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Dokter");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Kamar");

        kamar.setForeground(new java.awt.Color(255, 255, 255));
        kamar.setText("jLabel1");

        dokter.setForeground(new java.awt.Color(255, 255, 255));
        dokter.setText("jLabel1");

        tampil_bpjs.setForeground(new java.awt.Color(255, 255, 255));
        tampil_bpjs.setText("jLabel1");

        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tampil_bpjs1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tampil_bpjs1.setForeground(new java.awt.Color(255, 255, 255));
        tampil_bpjs1.setText("Total Bayar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(44, 44, 44)
                                .addComponent(alamat))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(9, 9, 9)))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(dokter, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tampil_bpjs, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(kamar, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tampil_nama, javax.swing.GroupLayout.Alignment.LEADING))))
                        .addGap(349, 349, 349)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_detail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_checkout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tampil_bpjs1)
                        .addGap(26, 26, 26)
                        .addComponent(test, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(txt_idRI, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_idBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_idResep, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_totalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(tgl_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(harga_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bpjs, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jumlah_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(id_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(id_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jumlah_terpakai, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(101, 101, 101)
                            .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn_cari))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 994, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_kembali))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cari)
                    .addComponent(txt_idBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_idResep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_totalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tgl_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(harga_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bpjs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_idRI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jumlah_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jumlah_terpakai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_checkout)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_detail)
                                    .addComponent(test, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tampil_bpjs1))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(tampil_nama)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(alamat)
                                    .addComponent(jLabel3))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(kamar))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(dokter))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tampil_bpjs))))
                .addGap(123, 123, 123))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1136, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int baris = jTable1.rowAtPoint(evt.getPoint());
        String id_RI = jTable1.getValueAt(baris, 0).toString();
        txt_idRI.setText(id_RI);
        String resep = jTable1.getValueAt(baris, 1).toString();
        txt_idResep.setText(resep);
        String nama = jTable1.getValueAt(baris, 2).toString();
        txt_idBiaya.setText(nama);
        String total = jTable1.getValueAt(baris, 11).toString();
        txt_totalHarga.setText(total);
        String tgl_masuks = jTable1.getValueAt(baris, 9).toString();
        tgl_masuk.setText(tgl_masuks);
        String harga_kamars = jTable1.getValueAt(baris, 7).toString();
        harga_kamar.setText(harga_kamars);
        String a = jTable1.getValueAt(baris, 8).toString();
        bpjs.setText(a);
        String b = jTable1.getValueAt(baris, 12).toString();
        jumlah_kamar.setText(b);
        String c = jTable1.getValueAt(baris, 13).toString();
        jumlah_terpakai.setText(c);
        String d = jTable1.getValueAt(baris, 14).toString();
        id_kamar.setText(d);
        String e = jTable1.getValueAt(baris, 15).toString();
        id_pasien.setText(e);
        
        String f = jTable1.getValueAt(baris, 3).toString();
        tampil_nama.setText(f);
        String g = jTable1.getValueAt(baris, 4).toString();
        alamat.setText(g);
        String h = jTable1.getValueAt(baris, 6).toString();
        kamar.setText(h);
        String i = jTable1.getValueAt(baris, 5).toString();
        dokter.setText(i);
        String j = jTable1.getValueAt(baris, 8).toString();
        tampil_bpjs.setText(j);
        
        tampil_nama.setVisible(true);
        alamat.setVisible(true);
        kamar.setVisible(true);
        dokter.setVisible(true);
        tampil_bpjs.setVisible(true);

    }//GEN-LAST:event_jTable1MouseClicked

    private void btn_detailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_detailActionPerformed
        // TODO add your handling code here:
        if (txt_idRI.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih pasien", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            String id = txt_idRI.getText();
            Session.setId(id);
            String id_biaya = txt_idBiaya.getText();
            Session.setId2(id_biaya);
            String total = txt_totalHarga.getText();
            Session.setId3(total);
            String id_resep = txt_idResep.getText();
            Session.setId4(id_resep);
            String a = bpjs.getText();
            Session.setId5(a);

            new detail_rawatInap().setVisible(true);
            this.dispose();
        }

    }//GEN-LAST:event_btn_detailActionPerformed

    private void btn_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_kembaliActionPerformed

    private void btn_checkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_checkoutActionPerformed
        // TODO add your handling code here:
        if (txt_idRI.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih pasien", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {

//Konversi dari string ke tanggal
            Date TanggalCEKIN = df.parse(tgl_masuk.getText());
            Date skrg = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String tgl = format.format(skrg);
            Date TanggalCEKOUT = df.parse(tgl);

            long Hari1 = TanggalCEKIN.getTime();
            long Hari2 = TanggalCEKOUT.getTime();
            long diff = (Hari2 - Hari1) - 1;
            long Lama = diff / (24 * 60 * 60 * 1000);
            long hrgKamar = Integer.parseInt(harga_kamar.getText());
            long totHarga = Integer.parseInt(txt_totalHarga.getText());
            long total = (Lama * hrgKamar) + totHarga;
            String status = "Sembuh";
            String sql = "UPDATE rawat_inap SET tgl_keluar = '" + tgl + "',status='" + status + "',total_bayar='" + total + "' WHERE id_rawatInap = '" + txt_idRI.getText() + "'";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            int a = Integer.parseInt(jumlah_kamar.getText());
            int b = Integer.parseInt(jumlah_terpakai.getText());
            int c = a - b;
            if (c == 0) {
                String status_kamar = "Ada";
                int d = b - 1;
                String sql2 = "UPDATE kamar SET jumlah_terpakai='" + d + "',status = '" + status_kamar + "' WHERE id_kamar = '" + id_kamar.getText() + "'";
                java.sql.PreparedStatement pst2 = conn.prepareStatement(sql2);
                pst2.execute();
            } else {
                int e = b - 1;
                String sql3 = "UPDATE kamar SET jumlah_terpakai='" + e + "' WHERE id_kamar = '" + id_kamar.getText() + "'";
                java.sql.PreparedStatement pst3 = conn.prepareStatement(sql3);
                pst3.execute();
            }
                
            JOptionPane.showMessageDialog(null, "Berhasil CheckOut");
            test.setText(String.valueOf(total));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(Daftar_RI.class.getName()).log(Level.SEVERE, null, ex);
        }
        kosong();
        tampil_data();
        this.setLocation(208, 99);
        hidden();

    }//GEN-LAST:event_btn_checkoutActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel model = new DefaultTableModel();
            jTable1.setModel(model);
            model.addColumn("ID");
            model.addColumn("ID Resep");
            model.addColumn("ID Biaya");
            model.addColumn("Nama");
            model.addColumn("Alamat");
            model.addColumn("Dokter");
            model.addColumn("Kamar");
            model.addColumn("Harga");
            model.addColumn("BPJS");
            model.addColumn("Tgl Masuk");
            model.addColumn("Tgl Keluar");
            model.addColumn("Total");
            model.addColumn("Jumlah");
            model.addColumn("Terpakai");
            model.addColumn("id");
            model.addColumn("id2");
            TableColumn col1 = jTable1.getColumnModel().getColumn(1);
            col1.setMinWidth(0);
            col1.setMaxWidth(0);
            col1.setWidth(0);
            col1.setPreferredWidth(0);
            TableColumn col2 = jTable1.getColumnModel().getColumn(2);
            col2.setMinWidth(0);
            col2.setMaxWidth(0);
            col2.setWidth(0);
            col2.setPreferredWidth(0);
            TableColumn col7 = jTable1.getColumnModel().getColumn(7);
            col7.setMinWidth(0);
            col7.setMaxWidth(0);
            col7.setWidth(0);
            col7.setPreferredWidth(0);
            TableColumn col12 = jTable1.getColumnModel().getColumn(12);
            col12.setMinWidth(0);
            col12.setMaxWidth(0);
            col12.setWidth(0);
            col12.setPreferredWidth(0);
            TableColumn col13 = jTable1.getColumnModel().getColumn(13);
            col13.setMinWidth(0);
            col13.setMaxWidth(0);
            col13.setWidth(0);
            col13.setPreferredWidth(0);
            TableColumn col14 = jTable1.getColumnModel().getColumn(14);
            col14.setMinWidth(0);
            col14.setMaxWidth(0);
            col14.setWidth(0);
            col14.setPreferredWidth(0);
            TableColumn col15 = jTable1.getColumnModel().getColumn(15);
            col15.setMinWidth(0);
            col15.setMaxWidth(0);
            col15.setWidth(0);
            col15.setPreferredWidth(0);
            String cari = txt_cari.getText();
            String status = "Belum";
            String sql = "SELECT id_rawatInap,id_resep,id_biayaLain,pasien.nama AS nama_pasien,pasien.alamat AS alamat_pasien,dokter.nama AS nama_dokter,kamar.nama AS nama_kamar,kamar.harga,bpjs,tgl_masuk,tgl_keluar,total_bayar,kamar.jumlah_kamar,kamar.jumlah_terpakai,kamar.id_kamar,pasien.id_pasien FROM rawat_inap JOIN pasien ON rawat_inap.id_pasien=pasien.id_pasien JOIN dokter ON rawat_inap.id_dokter=dokter.id_dokter JOIN kamar ON rawat_inap.id_kamar=kamar.id_kamar WHERE rawat_inap.status='" + status + "' AND (pasien.nama LIKE '%" + cari + "%' OR pasien.alamat LIKE '%" + cari + "%')";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9), res.getString(10), res.getString(11), res.getString(12), res.getString(13), res.getString(14), res.getString(15), res.getString(16)});
            }
            //jTable1.setModel(model);
            txt_cari.setText(null);
        } catch (Exception ex) {
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, "Data yang dicari tidak ada !!!!");

        }
    }//GEN-LAST:event_btn_cariActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        tampil_nama.setVisible(false);
        alamat.setVisible(false);
        kamar.setVisible(false);
        dokter.setVisible(false);
        tampil_bpjs.setVisible(false);
        test.setText(null);
        
        tampil_data();
        this.setLocation(208, 99);
        kosong();
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
            java.util.logging.Logger.getLogger(Daftar_RI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Daftar_RI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Daftar_RI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Daftar_RI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Daftar_RI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alamat;
    private javax.swing.JTextField bpjs;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_checkout;
    private javax.swing.JButton btn_detail;
    private javax.swing.JButton btn_kembali;
    private javax.swing.JLabel dokter;
    private javax.swing.JTextField harga_kamar;
    private javax.swing.JTextField id_kamar;
    private javax.swing.JTextField id_pasien;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jumlah_kamar;
    private javax.swing.JTextField jumlah_terpakai;
    private javax.swing.JLabel kamar;
    private javax.swing.JLabel tampil_bpjs;
    private javax.swing.JLabel tampil_bpjs1;
    private javax.swing.JLabel tampil_nama;
    private javax.swing.JTextField test;
    private javax.swing.JTextField tgl_masuk;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_idBiaya;
    private javax.swing.JTextField txt_idRI;
    private javax.swing.JTextField txt_idResep;
    private javax.swing.JTextField txt_totalHarga;
    // End of variables declaration//GEN-END:variables
}
