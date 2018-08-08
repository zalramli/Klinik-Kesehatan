/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaksi;

import Koneksi.Koneksi;
import Master.User;
import com.mysql.jdbc.Connection;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class RawatInap extends javax.swing.JInternalFrame {

    /**
     * Creates new form RawatInap
     */
    public RawatInap() {
        initComponents();
        tampil_pasien();
        tampil_dokter();
        tampil_kamar();
        auto_numberResep();
        auto_numberRawatInap();
        auto_numberBiayaLain();
        hidden();
        removeDecoration();
    }
    void removeDecoration() {
        for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()) {
            ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
        }
        BasicInternalFrameTitlePane titlePane = (BasicInternalFrameTitlePane) ((BasicInternalFrameUI) this.getUI()).getNorthPane();
        this.remove(titlePane);
    }
    private void kosong(){
        txt_idPasien.setText(null);
        txt_namaPasien.setText(null);
        txt_alamatPasien.setText(null);
        buttonGroup1.clearSelection();
        
        txt_idDokter.setText(null);
        txt_namaDokter.setText(null);
        txt_tarif.setText(null);
        
        txt_idKamar.setText(null);
        txt_namaKamar.setText(null);
        txt_hargaKamar.setText(null);
        jumlah.setText(null);
        terpakai.setText(null);
        
        txt_total.setText(null);
        
    }
    private void hidden(){
        txt_idResep.setVisible(false);
        txt_idBiaya.setVisible(false);
        jumlah.setVisible(false);
        terpakai.setVisible(false);
        txt_idRI.setVisible(false);
    }
    public void auto_numberResep(){
        try {
            Connection c = (Connection) Koneksi.configDB();
            Statement stat = c.createStatement();
            String sql = "SELECT MAX(right(id_resep,3)) AS no FROM resep";
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                if (rs.first()== false)
                {
                    txt_idResep.setText("RSP001");
                } else {
                    rs.last();
                    int set_id = rs.getInt(1)+1;
                    String no = String.valueOf(set_id);
                    int id_next = no.length();
                    for(int a=0; a <3 - id_next; a++){
                        no = "0" +no;
                    }
                    txt_idResep.setText("RSP"+no);
                }
            }
        } catch (SQLException ex) {
            
        }
    }
    public void auto_numberRawatInap(){
        try {
            Connection c = (Connection) Koneksi.configDB();
            Statement stat = c.createStatement();
            String sql = "SELECT MAX(right(id_rawatInap,3)) AS no FROM rawat_inap";
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                if (rs.first()== false)
                {
                    txt_idRI.setText("IRI001");
                } else {
                    rs.last();
                    int set_id = rs.getInt(1)+1;
                    String no = String.valueOf(set_id);
                    int id_next = no.length();
                    for(int a=0; a <3 - id_next; a++){
                        no = "0" +no;
                    }
                    txt_idRI.setText("IRI"+no);
                }
            }
        } catch (SQLException ex) {
            
        }
    }
    public void auto_numberBiayaLain(){
        try {
            Connection c = (Connection) Koneksi.configDB();
            Statement stat = c.createStatement();
            String sql = "SELECT MAX(right(id_biayaLain,3)) AS no FROM biaya_lain";
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                if (rs.first()== false)
                {
                    txt_idBiaya.setText("BYL001");
                } else {
                    rs.last();
                    int set_id = rs.getInt(1)+1;
                    String no = String.valueOf(set_id);
                    int id_next = no.length();
                    for(int a=0; a <3 - id_next; a++){
                        no = "0" +no;
                    }
                    txt_idBiaya.setText("BYL"+no);
                }
            }
        } catch (SQLException ex) {
            
        }
    }
    
    private void tampil_pasien(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Pasien");
        model.addColumn("Nama");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Alamat");
        model.addColumn("Keluhan");
        
        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            String sp="Belum";
            String sql = "select * from pasien where status_periksa='"+sp+"' ORDER BY id_pasien ASC";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString("id_pasien"),res.getString("nama"),res.getString("jenis_kelamin"),res.getString("alamat"),res.getString("keluhan")});
            }
            tabel_pasien.setModel(model);
        } catch (SQLException e) {
        }
    }
    private void tampil_dokter(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Dokter");
        model.addColumn("Nama");
        model.addColumn("Spesialis");
        model.addColumn("Tarif");
        
        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            String sql = "select * from dokter ORDER BY id_dokter ASC";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString("id_dokter"),res.getString("nama"),res.getString("spesialis"),res.getString("tarif")});
            }
            tabel_dokter.setModel(model);
        } catch (SQLException e) {
        }
    }
    private void tampil_kamar(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Kamar");
        model.addColumn("Nama");
        model.addColumn("Jumlah");
        model.addColumn("Terpakai");
        model.addColumn("Harga");
        
        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            String status="Ada";
            String sql = "select * from kamar WHERE status='"+status+"' ORDER BY id_kamar ASC";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString("id_kamar"),res.getString("nama"),res.getString("jumlah_kamar"),res.getString("jumlah_terpakai"),res.getString("harga")});
            }
            tabel_kamar.setModel(model);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_pasien = new javax.swing.JTable();
        txt_idPasien = new javax.swing.JTextField();
        txt_namaPasien = new javax.swing.JTextField();
        txt_alamatPasien = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_cariPasien = new javax.swing.JTextField();
        btn_cariPasien = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        rbYa = new javax.swing.JRadioButton();
        rbTidak = new javax.swing.JRadioButton();
        txt_idResep = new javax.swing.JTextField();
        txt_idBiaya = new javax.swing.JTextField();
        txt_idRI = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JButton();
        txt_total = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_idDokter = new javax.swing.JTextField();
        txt_namaDokter = new javax.swing.JTextField();
        txt_tarif = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_dokter = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        txt_cariDokter = new javax.swing.JTextField();
        btn_cariDokter = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_kamar = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txt_idKamar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_namaKamar = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_hargaKamar = new javax.swing.JTextField();
        txt_cariKamar = new javax.swing.JTextField();
        btn_cariKamar = new javax.swing.JButton();
        terpakai = new javax.swing.JTextField();
        jumlah = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        btn_list = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1150, 670));

        jPanel2.setBackground(new java.awt.Color(1, 152, 117));

        jPanel3.setBackground(new java.awt.Color(1, 152, 117));

        tabel_pasien.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_pasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_pasienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_pasien);

        txt_idPasien.setEditable(false);
        txt_idPasien.setBackground(new java.awt.Color(1, 152, 117));
        txt_idPasien.setForeground(new java.awt.Color(255, 255, 255));
        txt_idPasien.setBorder(null);

        txt_namaPasien.setEditable(false);
        txt_namaPasien.setBackground(new java.awt.Color(1, 152, 117));
        txt_namaPasien.setForeground(new java.awt.Color(255, 255, 255));
        txt_namaPasien.setBorder(null);

        txt_alamatPasien.setEditable(false);
        txt_alamatPasien.setBackground(new java.awt.Color(1, 152, 117));
        txt_alamatPasien.setForeground(new java.awt.Color(255, 255, 255));
        txt_alamatPasien.setBorder(null);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID Pasien");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nama");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Alamat");

        btn_cariPasien.setText("CARI PASIEN");
        btn_cariPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariPasienActionPerformed(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("BPJS");

        rbYa.setBackground(new java.awt.Color(1, 152, 117));
        buttonGroup1.add(rbYa);
        rbYa.setForeground(new java.awt.Color(255, 255, 255));
        rbYa.setText("Ya");
        rbYa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbYaActionPerformed(evt);
            }
        });

        rbTidak.setBackground(new java.awt.Color(1, 152, 117));
        buttonGroup1.add(rbTidak);
        rbTidak.setForeground(new java.awt.Color(255, 255, 255));
        rbTidak.setText("Tidak");
        rbTidak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTidakActionPerformed(evt);
            }
        });

        btn_simpan.setText("SIMPAN");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        txt_total.setEditable(false);
        txt_total.setBackground(new java.awt.Color(1, 152, 117));
        txt_total.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_total.setForeground(new java.awt.Color(255, 255, 255));
        txt_total.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_totalMouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Total ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txt_idRI, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(txt_idResep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(txt_idBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_cariPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_cariPasien))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jSeparator1)
                                        .addComponent(txt_idPasien, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jSeparator2)
                                        .addComponent(txt_namaPasien, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jSeparator3)
                                        .addComponent(txt_alamatPasien, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(18, 18, 18)
                                    .addComponent(rbYa)
                                    .addGap(18, 18, 18)
                                    .addComponent(rbTidak))))))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel16)
                .addGap(26, 26, 26)
                .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cariPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cariPasien)
                    .addComponent(txt_idRI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_idResep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_idBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_idPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_namaPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(txt_alamatPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(rbYa)
                    .addComponent(rbTidak))
                .addGap(82, 82, 82)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(139, 139, 139))
        );

        jPanel4.setBackground(new java.awt.Color(1, 152, 117));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ID Dokter");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nama");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tarif");

        txt_idDokter.setEditable(false);
        txt_idDokter.setBackground(new java.awt.Color(1, 152, 117));
        txt_idDokter.setForeground(new java.awt.Color(255, 255, 255));
        txt_idDokter.setBorder(null);

        txt_namaDokter.setEditable(false);
        txt_namaDokter.setBackground(new java.awt.Color(1, 152, 117));
        txt_namaDokter.setForeground(new java.awt.Color(255, 255, 255));
        txt_namaDokter.setBorder(null);

        txt_tarif.setEditable(false);
        txt_tarif.setBackground(new java.awt.Color(1, 152, 117));
        txt_tarif.setForeground(new java.awt.Color(255, 255, 255));
        txt_tarif.setBorder(null);

        tabel_dokter.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_dokter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_dokterMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_dokter);

        btn_cariDokter.setText("CARI DOKTER");
        btn_cariDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariDokterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator4)
                                .addComponent(txt_idDokter, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator5)
                                .addComponent(txt_namaDokter, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6)
                            .addGap(36, 36, 36)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator6)
                                .addComponent(txt_tarif, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                            .addGap(19, 19, 19)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(txt_cariDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_cariDokter)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_cariDokter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cariDokter, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_idDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt_namaDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tarif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        jPanel5.setBackground(new java.awt.Color(1, 152, 117));

        tabel_kamar.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_kamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_kamarMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabel_kamar);

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ID Kamar");

        txt_idKamar.setBackground(new java.awt.Color(1, 152, 117));
        txt_idKamar.setForeground(new java.awt.Color(255, 255, 255));
        txt_idKamar.setBorder(null);

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nama");

        txt_namaKamar.setBackground(new java.awt.Color(1, 152, 117));
        txt_namaKamar.setForeground(new java.awt.Color(255, 255, 255));
        txt_namaKamar.setBorder(null);

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Harga");

        txt_hargaKamar.setBackground(new java.awt.Color(1, 152, 117));
        txt_hargaKamar.setForeground(new java.awt.Color(255, 255, 255));
        txt_hargaKamar.setBorder(null);

        btn_cariKamar.setText("CARI KAMAR");
        btn_cariKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariKamarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_idKamar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_namaKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addComponent(jLabel9)
                                .addGap(44, 44, 44)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_hargaKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)
                                .addComponent(terpakai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_cariKamar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_cariKamar))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 8, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_cariKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_cariKamar))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(terpakai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_idKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txt_namaKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txt_hargaKamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(144, Short.MAX_VALUE))
        );

        btn_list.setText("Daftar Rawat Inap");
        btn_list.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_listActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(btn_list)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_list, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1137, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 761, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabel_pasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pasienMouseClicked
        // TODO add your handling code here:
        int baris = tabel_pasien.rowAtPoint(evt.getPoint());
        String id_pasien =tabel_pasien.getValueAt(baris, 0).toString();
        txt_idPasien.setText(id_pasien);
        txt_idPasien.setEditable(false);
        String nama_pasien = tabel_pasien.getValueAt(baris,1).toString();
        txt_namaPasien.setText(nama_pasien);
        String alamat_pasien = tabel_pasien.getValueAt(baris,3).toString();
        txt_alamatPasien.setText(alamat_pasien);
    }//GEN-LAST:event_tabel_pasienMouseClicked

    private void btn_cariPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariPasienActionPerformed
        // TODO add your handling code here:
        try{
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID Pasien");
            model.addColumn("Nama");
            model.addColumn("Jenis Kelamin");
            model.addColumn("Alamat");
            model.addColumn("Keluhan");
            String cari = txt_cariPasien.getText();
            String status="Belum";
            String sql = "SELECT * FROM pasien WHERE status_periksa='"+status+"' AND nama LIKE '%"+cari+"%' ORDER BY id_pasien";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString("id_pasien"),res.getString("nama"),res.getString("jenis_kelamin"),res.getString("alamat"),res.getString("keluhan")});
            }
            tabel_pasien.setModel(model);
            txt_cariPasien.setText(null);
        }catch(Exception ex){
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, "Data yang dicari tidak ada !!!!");

        }
    }//GEN-LAST:event_btn_cariPasienActionPerformed

    private void rbYaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbYaActionPerformed
        // TODO add your handling code here:
        txt_total.setText("0");
    }//GEN-LAST:event_rbYaActionPerformed

    private void rbTidakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTidakActionPerformed
        // TODO add your handling code here:
        txt_total.setText(null);
    }//GEN-LAST:event_rbTidakActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        if(txt_idPasien.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Masukkan data pasien !","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if(txt_idDokter.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Masukkan data dokter !","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if(txt_idKamar.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Masukkan data kamar !","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if(txt_total.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Klik 2 pada total !","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else {
            txt_idPasien.setText(txt_idPasien.getText());
            txt_idDokter.setText(txt_idDokter.getText());
            txt_idKamar.setText(txt_idKamar.getText());
        }
        try {
            Date skrg = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String tgl = format.format(skrg);
            String status = "Belum";
            String bpjs = null;
            if(rbYa.isSelected())
            {
                bpjs="Ya";
            }
            else if (rbTidak.isSelected())
            {
                bpjs="Tidak";
            }
            String id_RawatInap = txt_idRI.getText();
            String id_pasien = txt_idPasien.getText();
            String id_dokter = txt_idDokter.getText();
            String id_resep = txt_idResep.getText();
            String id_kamar = txt_idKamar.getText();
            String id_biaya = txt_idBiaya.getText();
            String total = txt_total.getText();
            String total_resep = "0";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            String sql2 = "INSERT INTO resep (id_resep,total_hargaResep) VALUES ('"+id_resep+"','"+total_resep+"')";
            java.sql.PreparedStatement pst2=conn.prepareStatement(sql2);
            pst2.execute();
            String sql3 = "INSERT INTO biaya_lain VALUES ('"+id_biaya+"','"+total_resep+"')";
            java.sql.PreparedStatement pst3=conn.prepareStatement(sql3);
            pst3.execute();
            String sql = "INSERT INTO rawat_inap (id_rawatInap,id_pasien,id_dokter,id_resep,id_kamar,id_biayaLain,bpjs,tgl_masuk,status,total_bayar) VALUES ('"+id_RawatInap+"','"+id_pasien+"','"+id_dokter+"','"+id_resep+"','"+id_kamar+"','"+id_biaya+"','"+bpjs+"','"+tgl+"','"+status+"','"+total+"')";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            
            int a = Integer.parseInt(jumlah.getText());
            int b = Integer.parseInt(terpakai.getText());
            int c = a - b ;
            if(c == 1)
            {
                String status_kamar = "Kosong";
                int e = b+1;
                String sql4 ="UPDATE kamar SET jumlah_terpakai='"+e+"',status = '"+status_kamar+"' WHERE id_kamar = '"+txt_idKamar.getText()+"'";
                java.sql.PreparedStatement pst4=conn.prepareStatement(sql4);
                pst4.execute();
            }
            else
            {
                int d = b + 1;
                String sql5 ="UPDATE kamar SET jumlah_terpakai = '"+d+"' WHERE id_kamar = '"+txt_idKamar.getText()+"'";
                java.sql.PreparedStatement pst5=conn.prepareStatement(sql5);
                pst5.execute();
            }
            String status1="Sudah";
                String sql7 = "UPDATE pasien SET status_periksa='"+status1+"' WHERE id_pasien = '" + txt_idPasien.getText() + "'";
                java.sql.PreparedStatement pst7 = conn.prepareStatement(sql7);
                pst7.execute();
            
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tampil_pasien();
        tampil_dokter();
        tampil_kamar();
        auto_numberResep();
        auto_numberRawatInap();
        auto_numberBiayaLain();
        hidden();
        kosong();
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void txt_totalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_totalMouseClicked
        // TODO add your handling code here:
        if(txt_tarif.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Masukan tarif dokter","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if(txt_hargaKamar.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Masukan harga kamar","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else {
            int tarif = Integer.parseInt(txt_tarif.getText());
            int tot_kamar = Integer.parseInt(txt_hargaKamar.getText());
            int total_semua = tarif + tot_kamar ;
            txt_total.setText(String.valueOf(total_semua));
        }
    }//GEN-LAST:event_txt_totalMouseClicked

    private void tabel_dokterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_dokterMouseClicked
        // TODO add your handling code here:
        int baris = tabel_dokter.rowAtPoint(evt.getPoint());
        String id_dokter =tabel_dokter.getValueAt(baris, 0).toString();
        txt_idDokter.setText(id_dokter);
        txt_idDokter.setEditable(false);
        String nama_dokter = tabel_dokter.getValueAt(baris,1).toString();
        txt_namaDokter.setText(nama_dokter);
        if(rbYa.isSelected()==false && rbTidak.isSelected()==false)
        {
            String tarif = tabel_dokter.getValueAt(baris,3).toString();
            txt_tarif.setText(tarif);
        }
        else if(rbYa.isSelected())
        {
            txt_tarif.setText("0");
        }
        else if(rbTidak.isSelected())
        {
            String tarif = tabel_dokter.getValueAt(baris,3).toString();
            txt_tarif.setText(tarif);
        }
    }//GEN-LAST:event_tabel_dokterMouseClicked

    private void btn_cariDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariDokterActionPerformed
        // TODO add your handling code here:
        try{
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID Dokter");
            model.addColumn("Nama");
            model.addColumn("Spesialis");
            model.addColumn("Tarif");
            String cari = txt_cariDokter.getText();
            String sql = "SELECT * FROM dokter WHERE id_dokter LIKE '%"+cari+"%' OR nama LIKE '%"+cari+"%' OR tarif LIKE '%"+cari+"%' OR spesialis LIKE '%"+cari+"%' ORDER BY id_dokter";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString("id_dokter"),res.getString("nama"),res.getString("spesialis"),res.getString("tarif")});
            }
            tabel_dokter.setModel(model);
            txt_cariDokter.setText(null);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Data yang dicari tidak ada !!!!");

        }
    }//GEN-LAST:event_btn_cariDokterActionPerformed

    private void tabel_kamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_kamarMouseClicked
        // TODO add your handling code here:
        int baris = tabel_kamar.rowAtPoint(evt.getPoint());
        String id_kamar =tabel_kamar.getValueAt(baris, 0).toString();
        txt_idKamar.setText(id_kamar);
        String nama_kamar = tabel_kamar.getValueAt(baris,1).toString();
        txt_namaKamar.setText(nama_kamar);
        String a = tabel_kamar.getValueAt(baris,2).toString();
        jumlah.setText(a);
        String b = tabel_kamar.getValueAt(baris,3).toString();
        terpakai.setText(b);
        if(rbYa.isSelected()==false && rbTidak.isSelected()==false)
        {
            String harga = tabel_kamar.getValueAt(baris,4).toString();
            txt_hargaKamar.setText(harga);
        }
        else if(rbYa.isSelected())
        {
            txt_hargaKamar.setText("0");
        }
        else if(rbTidak.isSelected())
        {
            String harga = tabel_kamar.getValueAt(baris,4).toString();
            txt_hargaKamar.setText(harga);
        }
    }//GEN-LAST:event_tabel_kamarMouseClicked

    private void btn_cariKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariKamarActionPerformed
        // TODO add your handling code here:
        try{
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID Kamar");
            model.addColumn("Nama");
            model.addColumn("Harga");
            String cari = txt_cariKamar.getText();
            String status="Ada";
            String sql = "SELECT * FROM kamar WHERE status='"+status+"' AND nama LIKE '%"+cari+"%' ORDER BY id_kamar";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString("id_kamar"),res.getString("nama"),res.getString("harga")});
            }
            tabel_kamar.setModel(model);
            txt_cariKamar.setText(null);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Data yang dicari tidak ada !!!!");

        }
    }//GEN-LAST:event_btn_cariKamarActionPerformed

    private void btn_listActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_listActionPerformed
        // TODO add your handling code here:
        new Daftar_RI().setVisible(true);
    }//GEN-LAST:event_btn_listActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cariDokter;
    private javax.swing.JButton btn_cariKamar;
    private javax.swing.JButton btn_cariPasien;
    private javax.swing.JButton btn_list;
    private javax.swing.JButton btn_simpan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jumlah;
    private javax.swing.JRadioButton rbTidak;
    private javax.swing.JRadioButton rbYa;
    private javax.swing.JTable tabel_dokter;
    private javax.swing.JTable tabel_kamar;
    private javax.swing.JTable tabel_pasien;
    private javax.swing.JTextField terpakai;
    private javax.swing.JTextField txt_alamatPasien;
    private javax.swing.JTextField txt_cariDokter;
    private javax.swing.JTextField txt_cariKamar;
    private javax.swing.JTextField txt_cariPasien;
    private javax.swing.JTextField txt_hargaKamar;
    private javax.swing.JTextField txt_idBiaya;
    private javax.swing.JTextField txt_idDokter;
    private javax.swing.JTextField txt_idKamar;
    private javax.swing.JTextField txt_idPasien;
    private javax.swing.JTextField txt_idRI;
    private javax.swing.JTextField txt_idResep;
    private javax.swing.JTextField txt_namaDokter;
    private javax.swing.JTextField txt_namaKamar;
    private javax.swing.JTextField txt_namaPasien;
    private javax.swing.JTextField txt_tarif;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
}
