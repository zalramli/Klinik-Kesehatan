/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaksi;

import Koneksi.Koneksi;
import Koneksi.Session;
import com.mysql.jdbc.Connection;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author Lenovo
 */
public class Riwayat extends javax.swing.JInternalFrame {

    /**
     * Creates new form Riwayat2
     */
    public Riwayat() {
        initComponents();
        tampil_RJ();
        tampil_RI();
        removeDecoration();
        hidden();
        Color customColor = new Color(1, 152, 117);
        rawat_jalan.setForeground(Color.black);
        rawat_jalan.setBackground(Color.white);
        JTableHeader Theader = rawat_jalan.getTableHeader();
        Theader.setBackground(Color.black);
        Theader.setForeground(customColor);
        rawat_inap.setForeground(Color.black);
        rawat_inap.setBackground(Color.white);
        JTableHeader ss = rawat_inap.getTableHeader();
        ss.setBackground(Color.black);
        ss.setForeground(customColor);
    }

    private void kosong() {
        id_pasien2.setText(null);
        bpjs2.setText(null);
        id_dokter2.setText(null);
        id_resep2.setText(null);
    }

    private void kosong2() {
        id_kamar.setText(null);
        id_resep.setText(null);
        bpjs.setText(null);
        id_biayaLain.setText(null);
        id_pasien.setText(null);
        id_dokter.setText(null);
        txt_idRI.setText(null);
    }

    private void hidden() {
        id_resep2.setVisible(false);
        id_dokter2.setVisible(false);
        bpjs2.setVisible(false);
        id_pasien2.setVisible(false);

        txt_idRI.setVisible(false);
        id_biayaLain.setVisible(false);
        id_resep.setVisible(false);
        id_kamar.setVisible(false);
        id_dokter.setVisible(false);
        bpjs.setVisible(false);
        id_pasien.setVisible(false);

        nama_pasien2.setVisible(false);
        alamat2.setVisible(false);
        no_hp.setVisible(false);
        nama_pasien.setVisible(false);
        alamat3.setVisible(false);
        no_hp1.setVisible(false);
        total_bayar2.setVisible(false);
        total_bayar.setVisible(false);

    }

    void removeDecoration() {
        for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()) {
            ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
        }
        BasicInternalFrameTitlePane titlePane = (BasicInternalFrameTitlePane) ((BasicInternalFrameUI) this.getUI()).getNorthPane();
        this.remove(titlePane);
    }

    private void tampil_RJ() {
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        rawat_jalan.setModel(model);
        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Alamat");
        model.addColumn("No_hp");
        model.addColumn("Dokter");
        model.addColumn("BPJS");
        model.addColumn("Tanggal Periksa");
        model.addColumn("Total Bayar");
        model.addColumn("id");
        model.addColumn("id");
        model.addColumn("id");
        TableColumn col9 = rawat_jalan.getColumnModel().getColumn(9);
        col9.setMinWidth(0);
        col9.setMaxWidth(0);
        col9.setWidth(0);
        col9.setPreferredWidth(0);
        TableColumn col10 = rawat_jalan.getColumnModel().getColumn(10);
        col10.setMinWidth(0);
        col10.setMaxWidth(0);
        col10.setWidth(0);
        col10.setPreferredWidth(0);
        TableColumn col11 = rawat_jalan.getColumnModel().getColumn(11);
        col11.setMinWidth(0);
        col11.setMaxWidth(0);
        col11.setWidth(0);
        col11.setPreferredWidth(0);
        //menampilkan data database kedalam tabel

        try {
            //int no=1;
            String status = "Sudah";
            String sql = "SELECT id_rawatJalan,pasien.nama,pasien.jenis_kelamin,pasien.alamat,pasien.no_hp,dokter.nama,bpjs,pasien.tgl_akhirPeriksa,total_bayar,rawat_jalan.id_resep,rawat_jalan.id_dokter,pasien.id_pasien FROM rawat_jalan JOIN pasien ON pasien.id_pasien=rawat_jalan.id_pasien JOIN dokter ON dokter.id_dokter=rawat_jalan.id_dokter WHERE pasien.status_periksa='"+status+"' AND id_rawatJalan IN (SELECT MAX(id_rawatJalan)  AS id FROM rawat_jalan  GROUP BY id_pasien)" ;
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9), res.getString(10), res.getString(11), res.getString(12)});
            }
            //rawat_jalan.setModel(model);
        } catch (SQLException e) {
        }
    }

    private void tampil_RI() {
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        rawat_inap.setModel(model);
        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("Alamat");
        model.addColumn("Dokter");
        model.addColumn("Kamar");
        model.addColumn("BPJS");
        model.addColumn("Tanggal Masuk");
        model.addColumn("Tanggal Keluar");
        model.addColumn("Total Bayar");
        model.addColumn("id_biaya");
        model.addColumn("id_resep");
        model.addColumn("id_kamar");
        model.addColumn("id_dokter");
        model.addColumn("id");
        TableColumn col9 = rawat_inap.getColumnModel().getColumn(9);
        col9.setMinWidth(0);
        col9.setMaxWidth(0);
        col9.setWidth(0);
        col9.setPreferredWidth(0);
        TableColumn col10 = rawat_inap.getColumnModel().getColumn(10);
        col10.setMinWidth(0);
        col10.setMaxWidth(0);
        col10.setWidth(0);
        col10.setPreferredWidth(0);
        TableColumn col11 = rawat_inap.getColumnModel().getColumn(11);
        col11.setMinWidth(0);
        col11.setMaxWidth(0);
        col11.setWidth(0);
        col11.setPreferredWidth(0);
        TableColumn col12 = rawat_inap.getColumnModel().getColumn(12);
        col12.setMinWidth(0);
        col12.setMaxWidth(0);
        col12.setWidth(0);
        col12.setPreferredWidth(0);
        TableColumn col13 = rawat_inap.getColumnModel().getColumn(13);
        col13.setMinWidth(0);
        col13.setMaxWidth(0);
        col13.setWidth(0);
        col13.setPreferredWidth(0);

        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            String status="Sudah";
            String status2="Sembuh";
            String sql = "SELECT id_rawatInap,pasien.nama AS nama_pasien,pasien.alamat AS alamat_pasien,dokter.nama AS nama_dokter,kamar.nama AS nama_kamar,bpjs,tgl_masuk,tgl_keluar,total_bayar,rawat_inap.id_biayaLain,rawat_inap.id_resep,kamar.id_kamar,dokter.id_dokter,pasien.id_pasien FROM rawat_inap JOIN pasien ON rawat_inap.id_pasien=pasien.id_pasien JOIN dokter ON rawat_inap.id_dokter=dokter.id_dokter JOIN kamar ON rawat_inap.id_kamar=kamar.id_kamar WHERE pasien.status_periksa='"+status+"' AND rawat_inap.status='"+status2+"' AND id_rawatInap IN (SELECT MAX(id_rawatInap)  AS id FROM rawat_inap  GROUP BY id_pasien)";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9), res.getString(10), res.getString(11), res.getString(12), res.getString(13), res.getString(14)});
            }
            //rawat_inap.setModel(model);
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
        rawat_jalan = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        rawat_inap = new javax.swing.JTable();
        btn_detailRI = new javax.swing.JButton();
        txt_idRI = new javax.swing.JTextField();
        id_biayaLain = new javax.swing.JTextField();
        id_resep = new javax.swing.JTextField();
        id_kamar = new javax.swing.JTextField();
        id_dokter = new javax.swing.JTextField();
        bpjs = new javax.swing.JTextField();
        id_resep2 = new javax.swing.JTextField();
        btn_detailRJ = new javax.swing.JButton();
        id_dokter2 = new javax.swing.JTextField();
        bpjs2 = new javax.swing.JTextField();
        cari_RJ = new javax.swing.JTextField();
        btn_cariRJ = new javax.swing.JButton();
        cari_RI = new javax.swing.JTextField();
        btn_cariRI = new javax.swing.JButton();
        id_pasien2 = new javax.swing.JTextField();
        id_pasien = new javax.swing.JTextField();
        nama_pasien2 = new javax.swing.JLabel();
        alamat2 = new javax.swing.JLabel();
        no_hp = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nama_pasien = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        alamat3 = new javax.swing.JLabel();
        no_hp1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btn_clear = new javax.swing.JButton();
        btn_clear2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        total_bayar2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        total_bayar = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1150, 670));

        jPanel1.setBackground(new java.awt.Color(1, 152, 117));

        rawat_jalan.setModel(new javax.swing.table.DefaultTableModel(
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
        rawat_jalan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rawat_jalanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(rawat_jalan);

        rawat_inap.setModel(new javax.swing.table.DefaultTableModel(
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
        rawat_inap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rawat_inapMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(rawat_inap);

        btn_detailRI.setText("Detail");
        btn_detailRI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_detailRIActionPerformed(evt);
            }
        });

        btn_detailRJ.setText("Detail");
        btn_detailRJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_detailRJActionPerformed(evt);
            }
        });

        btn_cariRJ.setText("Cari");
        btn_cariRJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariRJActionPerformed(evt);
            }
        });

        btn_cariRI.setText("Cari");
        btn_cariRI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariRIActionPerformed(evt);
            }
        });

        nama_pasien2.setForeground(new java.awt.Color(255, 255, 255));
        nama_pasien2.setText("jLabel1");

        alamat2.setForeground(new java.awt.Color(255, 255, 255));
        alamat2.setText("jLabel1");

        no_hp.setForeground(new java.awt.Color(255, 255, 255));
        no_hp.setText("jLabel1");

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nama");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Alamat");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("BPJS");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nama");

        nama_pasien.setForeground(new java.awt.Color(255, 255, 255));
        nama_pasien.setText("jLabel1");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Alamat");

        alamat3.setForeground(new java.awt.Color(255, 255, 255));
        alamat3.setText("jLabel1");

        no_hp1.setForeground(new java.awt.Color(255, 255, 255));
        no_hp1.setText("jLabel1");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("BPJS");

        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        btn_clear2.setText("Clear");
        btn_clear2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clear2ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total Bayar");

        total_bayar2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        total_bayar2.setForeground(new java.awt.Color(255, 255, 255));
        total_bayar2.setText("jLabel8");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Total Bayar");

        total_bayar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        total_bayar.setForeground(new java.awt.Color(255, 255, 255));
        total_bayar.setText("jLabel8");

        jButton1.setText("Ubah Status");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ubah Status");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(id_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(id_resep, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(bpjs, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(id_biayaLain, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(id_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(id_dokter, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_idRI, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(46, 46, 46)
                            .addComponent(cari_RI, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btn_cariRI, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(no_hp1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(alamat3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nama_pasien, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(88, 88, 88)
                                .addComponent(jLabel8)
                                .addGap(29, 29, 29)
                                .addComponent(total_bayar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_clear2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_detailRI)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 949, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(id_pasien2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(bpjs2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(id_dokter2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(id_resep2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(36, 36, 36)
                            .addComponent(cari_RJ, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btn_cariRJ)
                            .addGap(13, 13, 13))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(no_hp, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(alamat2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nama_pasien2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_clear)
                                .addGap(18, 18, 18)
                                .addComponent(btn_detailRJ)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addGap(2, 2, 2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(jLabel7)
                                .addGap(29, 29, 29)
                                .addComponent(total_bayar2)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cari_RJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cariRJ)
                    .addComponent(id_resep2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id_dokter2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bpjs2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id_pasien2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_detailRJ)
                            .addComponent(btn_clear)
                            .addComponent(jButton1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nama_pasien2)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(alamat2)
                                    .addComponent(total_bayar2)
                                    .addComponent(jLabel7))
                                .addGap(11, 11, 11)
                                .addComponent(no_hp)))))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cari_RI)
                        .addComponent(txt_idRI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(id_dokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(id_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(id_biayaLain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bpjs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(id_resep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(id_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_cariRI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_detailRI)
                        .addComponent(btn_clear2)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(nama_pasien)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(alamat3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(total_bayar)
                                    .addComponent(jLabel8))))
                        .addGap(18, 18, 18)
                        .addComponent(no_hp1)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1134, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_detailRIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_detailRIActionPerformed
        // TODO add your handling code here:
        if (id_resep.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih data yang di inginkan !", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            String id = id_biayaLain.getText();
            Session.setId(id);
            String id2 = id_resep.getText();
            Session.setId2(id2);
            String id3 = id_kamar.getText();
            Session.setId3(id3);
            String id4 = id_dokter.getText();
            Session.setId4(id4);
            String id5 = bpjs.getText();
            Session.setId5(id5);
            new detail_RI().setVisible(true);
        }

    }//GEN-LAST:event_btn_detailRIActionPerformed

    private void rawat_inapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rawat_inapMouseClicked
        // TODO add your handling code here:
        int baris = rawat_inap.rowAtPoint(evt.getPoint());
        String id_RI = rawat_inap.getValueAt(baris, 0).toString();
        txt_idRI.setText(id_RI);
        String id_biaya = rawat_inap.getValueAt(baris, 9).toString();
        id_biayaLain.setText(id_biaya);
        String id2 = rawat_inap.getValueAt(baris, 10).toString();
        id_resep.setText(id2);
        String id3 = rawat_inap.getValueAt(baris, 11).toString();
        id_kamar.setText(id3);
        String id4 = rawat_inap.getValueAt(baris, 12).toString();
        id_dokter.setText(id4);
        String id5 = rawat_inap.getValueAt(baris, 5).toString();
        bpjs.setText(id5);
        String pasien = rawat_inap.getValueAt(baris, 13).toString();
        id_pasien.setText(pasien);
        String a = rawat_inap.getValueAt(baris, 1).toString();
        nama_pasien.setText(a);
        String b = rawat_inap.getValueAt(baris, 2).toString();
        alamat3.setText(b);
        String c = rawat_inap.getValueAt(baris, 5).toString();
        no_hp1.setText(c);
        String d = rawat_inap.getValueAt(baris, 8).toString();
        total_bayar.setText(d);
        nama_pasien.setVisible(true);
        alamat3.setVisible(true);
        no_hp1.setVisible(true);
        total_bayar.setVisible(true);
    }//GEN-LAST:event_rawat_inapMouseClicked

    private void rawat_jalanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rawat_jalanMouseClicked
        // TODO add your handling code here:
        int baris = rawat_jalan.rowAtPoint(evt.getPoint());
        String id_resep = rawat_jalan.getValueAt(baris, 9).toString();
        id_resep2.setText(id_resep);
        String id_dokter = rawat_jalan.getValueAt(baris, 10).toString();
        id_dokter2.setText(id_dokter);
        String bpjs = rawat_jalan.getValueAt(baris, 6).toString();
        bpjs2.setText(bpjs);
        String pasien = rawat_jalan.getValueAt(baris, 11).toString();
        id_pasien2.setText(pasien);
        String nama = rawat_jalan.getValueAt(baris, 1).toString();
        nama_pasien2.setText(nama);
        String alamat = rawat_jalan.getValueAt(baris, 2).toString();
        alamat2.setText(alamat);
        String dokter = rawat_jalan.getValueAt(baris, 6).toString();
        no_hp.setText(dokter);
        String a = rawat_jalan.getValueAt(baris, 8).toString();
        total_bayar2.setText(a);

        nama_pasien2.setVisible(true);
        alamat2.setVisible(true);
        no_hp.setVisible(true);
        total_bayar2.setVisible(true);
    }//GEN-LAST:event_rawat_jalanMouseClicked

    private void btn_detailRJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_detailRJActionPerformed
        // TODO add your handling code here:
        if (id_resep2.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih data yang di inginkan !", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            String id = id_resep2.getText();
            Session.setId(id);
            String id2 = id_dokter2.getText();
            Session.setId2(id2);
            String id3 = bpjs2.getText();
            Session.setId3(id3);
            new detail_RJ().setVisible(true);
        }

    }//GEN-LAST:event_btn_detailRJActionPerformed

    private void btn_cariRJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariRJActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel model = new DefaultTableModel();
            rawat_jalan.setModel(model);
            model.addColumn("ID");
            model.addColumn("Nama");
            model.addColumn("Jenis Kelamin");
            model.addColumn("Alamat");
            model.addColumn("No_hp");
            model.addColumn("Dokter");
            model.addColumn("BPJS");
            model.addColumn("Tanggal Periksa");
            model.addColumn("Total Bayar");
            model.addColumn("id");
            model.addColumn("id");
            model.addColumn("id");
            TableColumn col9 = rawat_jalan.getColumnModel().getColumn(9);
            col9.setMinWidth(0);
            col9.setMaxWidth(0);
            col9.setWidth(0);
            col9.setPreferredWidth(0);
            TableColumn col10 = rawat_jalan.getColumnModel().getColumn(10);
            col10.setMinWidth(0);
            col10.setMaxWidth(0);
            col10.setWidth(0);
            col10.setPreferredWidth(0);
            TableColumn col11 = rawat_jalan.getColumnModel().getColumn(11);
            col11.setMinWidth(0);
            col11.setMaxWidth(0);
            col11.setWidth(0);
            col11.setPreferredWidth(0);
            String cari = cari_RJ.getText();
            String status="Sudah";
            String sql = "SELECT id_rawatJalan,pasien.nama,pasien.jenis_kelamin,pasien.alamat,pasien.no_hp,dokter.nama,bpjs,pasien.tgl_akhirPeriksa,total_bayar,rawat_jalan.id_resep,rawat_jalan.id_dokter,pasien.id_pasien FROM rawat_jalan JOIN pasien ON pasien.id_pasien=rawat_jalan.id_pasien JOIN dokter ON dokter.id_dokter=rawat_jalan.id_dokter WHERE pasien.status_periksa='"+status+"' AND id_rawatJalan IN (SELECT MAX(id_rawatJalan)  AS id FROM rawat_jalan  GROUP BY id_pasien) AND (pasien.nama LIKE '%" + cari + "%' OR pasien.alamat LIKE '%" + cari + "%' ) ";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9), res.getString(10), res.getString(11), res.getString(12)});
            }
            rawat_jalan.setModel(model);
            cari_RJ.setText(null);
        } catch (Exception ex) {
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, "Data yang dicari tidak ada !!!!");

        }
    }//GEN-LAST:event_btn_cariRJActionPerformed

    private void btn_cariRIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariRIActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel model = new DefaultTableModel();
            rawat_inap.setModel(model);
            model.addColumn("ID");
            model.addColumn("Nama");
            model.addColumn("Alamat");
            model.addColumn("Dokter");
            model.addColumn("Kamar");
            model.addColumn("BPJS");
            model.addColumn("Tanggal Masuk");
            model.addColumn("Tanggal Keluar");
            model.addColumn("Total Bayar");
            model.addColumn("id_biaya");
            model.addColumn("id_resep");
            model.addColumn("id_kamar");
            model.addColumn("id_dokter");
            model.addColumn("id");
            TableColumn col9 = rawat_inap.getColumnModel().getColumn(9);
            col9.setMinWidth(0);
            col9.setMaxWidth(0);
            col9.setWidth(0);
            col9.setPreferredWidth(0);
            TableColumn col10 = rawat_inap.getColumnModel().getColumn(10);
            col10.setMinWidth(0);
            col10.setMaxWidth(0);
            col10.setWidth(0);
            col10.setPreferredWidth(0);
            TableColumn col11 = rawat_inap.getColumnModel().getColumn(11);
            col11.setMinWidth(0);
            col11.setMaxWidth(0);
            col11.setWidth(0);
            col11.setPreferredWidth(0);
            TableColumn col12 = rawat_inap.getColumnModel().getColumn(12);
            col12.setMinWidth(0);
            col12.setMaxWidth(0);
            col12.setWidth(0);
            col12.setPreferredWidth(0);
            TableColumn col13 = rawat_inap.getColumnModel().getColumn(13);
            col13.setMinWidth(0);
            col13.setMaxWidth(0);
            col13.setWidth(0);
            col13.setPreferredWidth(0);
            String cari = cari_RI.getText();
            String status="Sudah";
            String status2="Sembuh";
            String sql = "SELECT id_rawatInap,pasien.nama AS nama_pasien,pasien.alamat AS alamat_pasien,dokter.nama AS nama_dokter,kamar.nama AS nama_kamar,bpjs,tgl_masuk,tgl_keluar,total_bayar,rawat_inap.id_biayaLain,rawat_inap.id_resep,kamar.id_kamar,dokter.id_dokter,pasien.id_pasien FROM rawat_inap JOIN pasien ON rawat_inap.id_pasien=pasien.id_pasien JOIN dokter ON rawat_inap.id_dokter=dokter.id_dokter JOIN kamar ON rawat_inap.id_kamar=kamar.id_kamar WHERE pasien.status_periksa='"+status+"' AND rawat_inap.status='"+status2+"' AND id_rawatInap IN (SELECT MAX(id_rawatInap)  AS id FROM rawat_inap  GROUP BY id_pasien)  AND (pasien.nama LIKE '%" + cari + "%' OR pasien.alamat LIKE '%" + cari + "%')";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9), res.getString(10), res.getString(11), res.getString(12), res.getString(13), res.getString(14)});
            }
            //rawat_inap.setModel(model);
            cari_RI.setText(null);
        } catch (Exception ex) {
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, "Data yang dicari tidak ada !!!!");

        }
    }//GEN-LAST:event_btn_cariRIActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        // TODO add your handling code here:
        nama_pasien2.setVisible(false);
        alamat2.setVisible(false);
        no_hp.setVisible(false);
        total_bayar2.setVisible(false);
        tampil_RJ();
        tampil_RI();
        kosong();
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_clear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear2ActionPerformed
        // TODO add your handling code here:
        nama_pasien.setVisible(false);
        alamat3.setVisible(false);
        no_hp1.setVisible(false);
        total_bayar.setVisible(false);
        tampil_RJ();
        tampil_RI();
        kosong2();
    }//GEN-LAST:event_btn_clear2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (id_resep2.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih data yang di inginkan !", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }  
            int Pilih = JOptionPane.showConfirmDialog(null,"Apakah yakin ingin mengubah status?","Pertanyaan",JOptionPane.YES_NO_OPTION);
            if(Pilih == JOptionPane.YES_OPTION){
            try {
            String status = "Belum";
            String sql ="UPDATE pasien SET status_periksa = '"+status+"' WHERE id_pasien = '"+id_pasien2.getText()+"'";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Berhasil Mengubah Status");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
            nama_pasien2.setVisible(false);
            alamat2.setVisible(false);
            no_hp.setVisible(false);
            total_bayar2.setVisible(false);
            tampil_RJ();
            tampil_RI();
            kosong();
        } else if(Pilih == JOptionPane.NO_OPTION){
            nama_pasien2.setVisible(false);
            alamat2.setVisible(false);
            no_hp.setVisible(false);
            total_bayar2.setVisible(false);
            tampil_RJ();
            tampil_RI();
            kosong();
        } 
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (id_resep.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih data yang di inginkan !", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }   
            int Pilih = JOptionPane.showConfirmDialog(null,"Apakah yakin ingin mengubah status?","Pertanyaan",JOptionPane.YES_NO_OPTION);
            if(Pilih == JOptionPane.YES_OPTION){
            try {
            String status = "Belum";
            String sql ="UPDATE pasien SET status_periksa = '"+status+"' WHERE id_pasien = '"+id_pasien.getText()+"'";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Berhasil Mengubah Status");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
            nama_pasien.setVisible(false);
            alamat3.setVisible(false);
            no_hp1.setVisible(false);
            total_bayar.setVisible(false);
            tampil_RJ();
            tampil_RI();
            kosong2();
            
        } else if(Pilih == JOptionPane.NO_OPTION){
            nama_pasien.setVisible(false);
            alamat3.setVisible(false);
            no_hp1.setVisible(false);
            total_bayar.setVisible(false);
            tampil_RJ();
            tampil_RI();
            kosong2();;
        } 
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alamat2;
    private javax.swing.JLabel alamat3;
    private javax.swing.JTextField bpjs;
    private javax.swing.JTextField bpjs2;
    private javax.swing.JButton btn_cariRI;
    private javax.swing.JButton btn_cariRJ;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_clear2;
    private javax.swing.JButton btn_detailRI;
    private javax.swing.JButton btn_detailRJ;
    private javax.swing.JTextField cari_RI;
    private javax.swing.JTextField cari_RJ;
    private javax.swing.JTextField id_biayaLain;
    private javax.swing.JTextField id_dokter;
    private javax.swing.JTextField id_dokter2;
    private javax.swing.JTextField id_kamar;
    private javax.swing.JTextField id_pasien;
    private javax.swing.JTextField id_pasien2;
    private javax.swing.JTextField id_resep;
    private javax.swing.JTextField id_resep2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel nama_pasien;
    private javax.swing.JLabel nama_pasien2;
    private javax.swing.JLabel no_hp;
    private javax.swing.JLabel no_hp1;
    private javax.swing.JTable rawat_inap;
    private javax.swing.JTable rawat_jalan;
    private javax.swing.JLabel total_bayar;
    private javax.swing.JLabel total_bayar2;
    private javax.swing.JTextField txt_idRI;
    // End of variables declaration//GEN-END:variables
}
