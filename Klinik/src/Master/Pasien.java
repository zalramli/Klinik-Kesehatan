/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master;

import Koneksi.Koneksi;
import com.mysql.jdbc.Connection;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class Pasien extends javax.swing.JInternalFrame {

    /**
     * Creates new form Pasien
     */
    public Pasien() {
        initComponents();
        auto_number();
        tampil_data();
        button_awal();
        no_antrian();
        removeDecoration();
    }
    void removeDecoration() {
        for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()) {
            ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
        }
        BasicInternalFrameTitlePane titlePane = (BasicInternalFrameTitlePane) ((BasicInternalFrameUI) this.getUI()).getNorthPane();
        this.remove(titlePane);
    }
    public void button_awal()
    {
        btn_simpan.setEnabled(true);
        btn_batal.setEnabled(true);
        btn_update.setEnabled(false);
        btn_hapus.setEnabled(false);
        
    }
    public void button_tabelklik()
    {
        btn_simpan.setEnabled(false);
        btn_batal.setEnabled(true);
        btn_update.setEnabled(true);
        btn_hapus.setEnabled(true);
        
    }
    public void auto_number(){
        try {
            Connection c = (Connection) Koneksi.configDB();
            Statement stat = c.createStatement();
            String sql = "select max(right(id_pasien,3)) as no from pasien";
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                if (rs.first()== false)
                {
                    txt_idPasien.setText("PSN001");
                } else {
                    rs.last();
                    int set_id = rs.getInt(1)+1;
                    String no = String.valueOf(set_id);
                    int id_next = no.length();
                    for(int a=0; a <3 - id_next; a++){
                        no = "0" +no;
                    }
                    txt_idPasien.setText("PSN"+no);
                }
            }
        } catch (SQLException ex) {
            
        }
    }
    public void no_antrian(){
        try {
            Connection c = (Connection) Koneksi.configDB();
            Statement stat = c.createStatement();
            Date skrg = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String tgl = format.format(skrg);
            
            String sql = "select max(right(no_antrian,1)) as no from pasien WHERE tgl_antrian='"+tgl+"'";
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                if (rs.first()== false)
                {
                    txt_noAntrian.setText("1");
                } else {
                    rs.last();
                    int set_id = rs.getInt(1)+1;
                    String no = String.valueOf(set_id);
                    txt_noAntrian.setText(no);
                }
            }
        } catch (SQLException ex) {
            
        }
    }
    private void kosong()
    {
        txt_idPasien.setText(null);
        txt_noAntrian.setText(null);
        txt_nama.setText("Masukan data");
        txt_tempatLahir.setText("Masukan data");
        txt_tanggalLahir.setDate(null);
        cmb_jenisKelamin.setSelectedItem("Pria");
        txt_alamat.setText("Masukan data");
        txt_namaOrtu.setText("Masukan data");
        txt_noHp.setText("Masukan data");
        txt_keluhan.setText("Masukan data");  
    }
    private void tampil_data(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Pasien");
        model.addColumn("No Antrian");
        model.addColumn("Nama");
        model.addColumn("Tempat Lahir");
        model.addColumn("Tanggal_lahir");
        model.addColumn("Jenis_kelamin");
        model.addColumn("Alamat");
        model.addColumn("Nama Ortu");
        model.addColumn("No_hp");
        model.addColumn("Keluhan");
        
        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            String sql = "select * from pasien ORDER BY id_pasien";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8),res.getString(9),res.getString(10)});
            }
            jTable1.setModel(model);
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
        jPanel2 = new javax.swing.JPanel();
        btn_simpan = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txt_tempatLahir = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        txt_noAntrian = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_idPasien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_tanggalLahir = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        cmb_jenisKelamin = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txt_alamat = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_keluhan = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        txt_noHp = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        txt_namaOrtu = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_cari = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1150, 670));

        jPanel1.setBackground(new java.awt.Color(1, 152, 117));

        jPanel2.setBackground(new java.awt.Color(1, 152, 117));

        btn_simpan.setText("SIMPAN");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_batal.setText("BATAL");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });

        btn_update.setText("UPDATE");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_hapus.setText("HAPUS");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_simpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_batal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_simpan)
                .addGap(18, 18, 18)
                .addComponent(btn_batal)
                .addGap(27, 27, 27)
                .addComponent(btn_update)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btn_hapus)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(1, 152, 117));

        txt_tempatLahir.setBackground(new java.awt.Color(1, 152, 117));
        txt_tempatLahir.setForeground(new java.awt.Color(255, 255, 255));
        txt_tempatLahir.setText("Masukan data");
        txt_tempatLahir.setBorder(null);
        txt_tempatLahir.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_tempatLahirFocusGained(evt);
            }
        });
        txt_tempatLahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_tempatLahirKeyTyped(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tempat Lahir");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama");

        txt_nama.setBackground(new java.awt.Color(1, 152, 117));
        txt_nama.setForeground(new java.awt.Color(255, 255, 255));
        txt_nama.setText("Masukan data");
        txt_nama.setBorder(null);
        txt_nama.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_namaFocusGained(evt);
            }
        });
        txt_nama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_namaKeyTyped(evt);
            }
        });

        txt_noAntrian.setEditable(false);
        txt_noAntrian.setBackground(new java.awt.Color(1, 152, 117));
        txt_noAntrian.setForeground(new java.awt.Color(255, 255, 255));
        txt_noAntrian.setBorder(null);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("No Antrian");

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID Pasien");

        txt_idPasien.setEditable(false);
        txt_idPasien.setBackground(new java.awt.Color(1, 152, 117));
        txt_idPasien.setForeground(new java.awt.Color(255, 255, 255));
        txt_idPasien.setBorder(null);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tanggal Lahir");

        txt_tanggalLahir.setDateFormatString("yyyy-MM-dd");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Jenis Kelamin");

        cmb_jenisKelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pria", "Wanita" }));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Alamat");

        txt_alamat.setBackground(new java.awt.Color(1, 152, 117));
        txt_alamat.setForeground(new java.awt.Color(255, 255, 255));
        txt_alamat.setText("Masukan data");
        txt_alamat.setBorder(null);
        txt_alamat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_alamatFocusGained(evt);
            }
        });
        txt_alamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_alamatKeyTyped(evt);
            }
        });

        txt_keluhan.setBackground(new java.awt.Color(1, 152, 117));
        txt_keluhan.setColumns(20);
        txt_keluhan.setForeground(new java.awt.Color(255, 255, 255));
        txt_keluhan.setRows(5);
        txt_keluhan.setText("Masukan data");
        txt_keluhan.setBorder(null);
        txt_keluhan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_keluhanFocusGained(evt);
            }
        });
        txt_keluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_keluhanKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txt_keluhan);

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Keluhan");

        txt_noHp.setBackground(new java.awt.Color(1, 152, 117));
        txt_noHp.setForeground(new java.awt.Color(255, 255, 255));
        txt_noHp.setText("Masukan data");
        txt_noHp.setBorder(null);
        txt_noHp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_noHpKeyTyped(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nama Ortu");

        txt_namaOrtu.setBackground(new java.awt.Color(1, 152, 117));
        txt_namaOrtu.setForeground(new java.awt.Color(255, 255, 255));
        txt_namaOrtu.setText("Masukan data");
        txt_namaOrtu.setBorder(null);
        txt_namaOrtu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_namaOrtuFocusGained(evt);
            }
        });
        txt_namaOrtu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_namaOrtuKeyTyped(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("No Hp");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_nama, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_idPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_noAntrian, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(19, 19, 19)
                                .addComponent(cmb_jenisKelamin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txt_tanggalLahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(23, 23, 23)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator4)
                                    .addComponent(txt_tempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jSeparator7)
                            .addComponent(txt_noHp, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_namaOrtu)
                            .addComponent(jSeparator6)
                            .addComponent(jSeparator5)
                            .addComponent(txt_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(32, 32, 32))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_noAntrian, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_idPasien)
                            .addComponent(jLabel1)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(txt_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(txt_namaOrtu, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(txt_noHp, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(5, 5, 5)))
                        .addGap(64, 64, 64)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_tanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(39, 39, 39))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txt_tempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(104, 104, 104)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cmb_jenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(1, 152, 117));

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
        jScrollPane2.setViewportView(jTable1);

        btn_cari.setText("CARI");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 38, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_cari, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cari, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(144, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    }// </editor-fold>//GEN-END:initComponents

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        int Pilih = JOptionPane.showConfirmDialog(null,"Apakah yakin dihapus?","Pertanyaan",JOptionPane.YES_NO_OPTION);
        if(Pilih == JOptionPane.YES_OPTION){
            try {
                String sql ="delete from pasien where id_pasien='"+txt_idPasien.getText()+"'";
                java.sql.Connection conn=(Connection)Koneksi.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(this, "berhasil di hapus");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            tampil_data();
            kosong();
            auto_number();
            button_awal();
            no_antrian();

        }
        else if(Pilih == JOptionPane.NO_OPTION){
            tampil_data();
            kosong();
            auto_number();
            button_awal();
            no_antrian();
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        if(txt_nama.getText().equals("Masukan data")
            ||txt_tempatLahir.getText().equals("Masukan data")
            ||txt_alamat.getText().equals("Masukan data")
            ||txt_namaOrtu.getText().equals("Masukan data")
            ||txt_noHp.getText().equals("Masukan data")
            ||txt_keluhan.getText().equals("Masukan data"))
        {
            JOptionPane.showMessageDialog(null, "Masukkan data dengan benar !","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if(txt_nama.getText().equals("")
            ||txt_tempatLahir.getText().equals("")
            ||txt_alamat.getText().equals("")
            ||txt_namaOrtu.getText().equals("")
            ||txt_noHp.getText().equals("")
            ||txt_keluhan.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Masukkan data dengan benar !","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        } else
        {
            txt_nama.setText(txt_nama.getText());
            txt_tempatLahir.setText(txt_tempatLahir.getText());
            txt_alamat.setText(txt_alamat.getText());
            txt_namaOrtu.setText(txt_namaOrtu.getText());
            txt_noHp.setText(txt_noHp.getText());
            txt_keluhan.setText(txt_keluhan.getText());
        }
        String tgl_lahir = ((JTextField)txt_tanggalLahir.getDateEditor().getUiComponent()).getText();
        try {
            String sql ="UPDATE pasien SET nama = '"+txt_nama.getText()+"',tempat_lahir = '"+txt_tempatLahir.getText()+"', tanggal_lahir = '"+tgl_lahir+"', jenis_kelamin = '"+cmb_jenisKelamin.getSelectedItem()+"',alamat= '"+txt_alamat.getText()+"',nama_ortu= '"+txt_namaOrtu.getText()+"',no_hp= '"+txt_noHp.getText()+"',keluhan= '"+txt_keluhan.getText()+"' WHERE id_pasien = '"+txt_idPasien.getText()+"'";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "data berhasil di edit");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
        tampil_data();
        kosong();
        auto_number();
        button_awal();
        no_antrian();
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        try{
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID Pasien");
            model.addColumn("No Antrian");
            model.addColumn("Nama");
            model.addColumn("Tempat Lahir");
            model.addColumn("Tanggal Lahir");
            model.addColumn("Jenis Kelamin");
            model.addColumn("Alamat");
            model.addColumn("Nama Ortu");
            model.addColumn("No Hp");
            model.addColumn("Keluhan");
            String cari = txt_cari.getText();
            String status="Belum";
            String sql = "SELECT * FROM pasien WHERE status_periksa='"+status+"'  AND nama LIKE '%"+cari+"%' ORDER BY id_pasien";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8),res.getString(9),res.getString(10)});
            }
            jTable1.setModel(model);
            txt_cari.setText(null);
        }catch(SQLException ex){
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, "Data yang dicari tidak ada !!!!");

        }
    }//GEN-LAST:event_btn_cariActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        button_tabelklik();
        int baris = jTable1.rowAtPoint(evt.getPoint());
        String id_pasien =jTable1.getValueAt(baris, 0).toString();
        txt_idPasien.setText(id_pasien);
        txt_idPasien.setEditable(false);
        String no = jTable1.getValueAt(baris,1).toString();
        txt_noAntrian.setText(no);
        String nama = jTable1.getValueAt(baris,2).toString();
        txt_nama.setText(nama);
        String tempatLahir = jTable1.getValueAt(baris,3).toString();
        txt_tempatLahir.setText(tempatLahir);

        String s=jTable1.getValueAt(baris, 4).toString();
        try{
            SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d=f.parse(s);
            txt_tanggalLahir.setDate(d);
        }catch(ParseException ex){
        }

        String jenis_kelamin = jTable1.getValueAt(baris, 5).toString();
        cmb_jenisKelamin.setSelectedItem(jenis_kelamin);
        String alamat=jTable1.getValueAt(baris, 6).toString();
        txt_alamat.setText(alamat);
        String namaOrtu=jTable1.getValueAt(baris, 7).toString();
        txt_namaOrtu.setText(namaOrtu);
        String no_hp = jTable1.getValueAt(baris, 8).toString();
        txt_noHp.setText(no_hp);
        String keluhan=jTable1.getValueAt(baris, 9).toString();
        txt_keluhan.setText(keluhan);
    }//GEN-LAST:event_jTable1MouseClicked

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        // TODO add your handling code here:
        tampil_data();
        kosong();
        auto_number();
        button_awal();
        no_antrian();
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        if(txt_nama.getText().equals("Masukan data")
            ||txt_tempatLahir.getText().equals("Masukan data")
            ||txt_alamat.getText().equals("Masukan data")
            ||txt_namaOrtu.getText().equals("Masukan data")
            ||txt_noHp.getText().equals("Masukan data")
            ||txt_keluhan.getText().equals("Masukan data"))
        {
            JOptionPane.showMessageDialog(null, "Masukkan data dengan benar !","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if(txt_nama.getText().equals("")
            ||txt_tempatLahir.getText().equals("")
            ||txt_alamat.getText().equals("")
            ||txt_namaOrtu.getText().equals("")
            ||txt_noHp.getText().equals("")
            ||txt_keluhan.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Masukkan data dengan benar !","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        } else
        {
            txt_nama.setText(txt_nama.getText());
            txt_tempatLahir.setText(txt_tempatLahir.getText());
            txt_alamat.setText(txt_alamat.getText());
            txt_namaOrtu.setText(txt_namaOrtu.getText());
            txt_noHp.setText(txt_noHp.getText());
            txt_keluhan.setText(txt_keluhan.getText());
        }
        try {
            String sp = "Belum";
            Date skrg = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String tgl = format.format(skrg);
            String tgl_lahir = ((JTextField)txt_tanggalLahir.getDateEditor().getUiComponent()).getText();
            String sql = "INSERT INTO pasien VALUES ('"+txt_idPasien.getText()+"','"+txt_noAntrian.getText()+"','"+txt_nama.getText()+"','"+txt_tempatLahir.getText()+"','"+tgl_lahir+"','"+cmb_jenisKelamin.getSelectedItem()+"','"+txt_alamat.getText()+"','"+txt_namaOrtu.getText()+"','"+txt_noHp.getText()+"','"+txt_keluhan.getText()+"','"+sp+"','"+tgl+"','"+tgl+"')";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tampil_data();
        kosong();
        auto_number();
        button_awal();
        no_antrian();
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void txt_keluhanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_keluhanKeyTyped
        // TODO add your handling code here:
        if(Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_txt_keluhanKeyTyped

    private void txt_noHpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_noHpKeyTyped
        // TODO add your handling code here:
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_txt_noHpKeyTyped

    private void txt_namaOrtuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_namaOrtuKeyTyped
        // TODO add your handling code here:
        if(Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_txt_namaOrtuKeyTyped

    private void txt_alamatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_alamatKeyTyped
        // TODO add your handling code here:
        if(Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_txt_alamatKeyTyped

    private void txt_tempatLahirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tempatLahirKeyTyped
        // TODO add your handling code here:
        if(Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_txt_tempatLahirKeyTyped

    private void txt_namaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_namaKeyTyped
        // TODO add your handling code here:
        if(Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_txt_namaKeyTyped

    private void txt_namaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_namaFocusGained
txt_nama.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaFocusGained

    private void txt_tempatLahirFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_tempatLahirFocusGained
txt_tempatLahir.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tempatLahirFocusGained

    private void txt_alamatFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_alamatFocusGained
txt_alamat.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_alamatFocusGained

    private void txt_namaOrtuFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_namaOrtuFocusGained
txt_namaOrtu.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaOrtuFocusGained

    private void txt_keluhanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_keluhanFocusGained
txt_keluhan.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_keluhanFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cmb_jenisKelamin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_idPasien;
    private javax.swing.JTextArea txt_keluhan;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_namaOrtu;
    private javax.swing.JTextField txt_noAntrian;
    private javax.swing.JTextField txt_noHp;
    private com.toedter.calendar.JDateChooser txt_tanggalLahir;
    private javax.swing.JTextField txt_tempatLahir;
    // End of variables declaration//GEN-END:variables
}
