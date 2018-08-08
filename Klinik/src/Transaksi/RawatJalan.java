/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaksi;

import Koneksi.Koneksi;
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
 * @author Andri Fanky K
 */
public class RawatJalan extends javax.swing.JInternalFrame {
    
    DefaultTableModel tableModel = new DefaultTableModel(
    new Object [ ][ ] {},
    new String [ ] {
    "ID Obat", "Nama","Harga","Jumlah","Stok","Total"
    });
    private int row;
    
    /**
     * Creates new form RawatJalan
     */
    public RawatJalan() {
        initComponents();
        
        tampil_pasien();
        tampil_obat();
        tampil_dokter();
        inisialisasi_tabel();
        auto_numberResep();
        auto_numberRJ();
        removeDecoration();
        hidden();
    }
    private void hidden(){
        jLabel1.setVisible(false);
        txt_idPasien.setVisible(false);
        txt_idObat.setVisible(false);
        txt_stok.setVisible(false);
        jLabel9.setVisible(false);
        jLabel6.setVisible(false);
        txt_harga.setVisible(false);
        jLabel10.setVisible(false);
        
        txt_idResep.setVisible(false);
        txt_idRJ.setVisible(false);
    }
    void removeDecoration() {
        for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()) {
            ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
        }
        BasicInternalFrameTitlePane titlePane = (BasicInternalFrameTitlePane) ((BasicInternalFrameUI) this.getUI()).getNorthPane();
        this.remove(titlePane);
    }
    
    private void inisialisasi_tabel() {  //INISIALISASI TABEL
        tabel_resep.setModel(tableModel);
    }
    private void simpan_ditabel() {    //SIMPAN SEMENTARA
        try{
          int jml = Integer.parseInt(txt_jumlah.getText());
          int hrg = Integer.parseInt(txt_harga.getText());
          int s = Integer.parseInt(txt_stok.getText()) - Integer.parseInt(txt_jumlah.getText()) ;
          int tot = Integer.parseInt(txt_total.getText());
          
            //JIKA STRING
          
          String kd_obat= String.valueOf(txt_idObat.getText());
          String nama=String.valueOf(txt_namaObat.getText());
        tableModel.addRow(new Object[]{kd_obat,nama,hrg,jml,s,tot});
        inisialisasi_tabel();
    }
        catch(NumberFormatException exception)
    {
    System.out.println("Error ss : "+exception);
    }

    }
    public void getsum(){  //MENJUMLAHKAN HARGA DATA
        int batas = tabel_resep.getRowCount();
        int sum = 0;
        for(int i=0; i < batas; i++)
        {
            sum = sum + Integer.parseInt(tabel_resep.getValueAt(i,5).toString());
        }
        txt_totalResep.setText(Integer.toString(sum));
    } 
    private void kosong(){
        txt_idPasien.setText(null);
        txt_namaPasien.setText(null);
        txt_alamatPasien.setText(null);
        buttonGroup1.clearSelection();
        
        txt_idDokter.setText(null);
        txt_namaDokter.setText(null);
        txt_tarif.setText(null);
        
        txt_idObat.setText(null);
        txt_namaObat.setText(null);
        txt_stok.setText(null);
        txt_harga.setText(null);
        txt_jumlah.setText(null);
        txt_total.setText(null);
        
        txt_totalSemua.setText(null);
        txt_totalResep.setText(null);
        
        DefaultTableModel model = (DefaultTableModel) tabel_resep.getModel();
        model.setRowCount(0);
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
    
    private void tampil_obat(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Obat");
        model.addColumn("Nama");
        model.addColumn("Stok");
        model.addColumn("Harga");
        
        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            String sql = "select * from obat ORDER BY id_obat ASC";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString("id_obat"),res.getString("nama"),res.getString("stok"),res.getString("harga_jual")});
            }
            tabel_obat.setModel(model);
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
     public void auto_numberRJ(){
        try {
            Connection c = (Connection) Koneksi.configDB();
            Statement stat = c.createStatement();
            String sql = "SELECT MAX(right(id_rawatJalan,3)) AS no FROM rawat_jalan";
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                if (rs.first()== false)
                {
                    txt_idRJ.setText("IRJ001");
                } else {
                    rs.last();
                    int set_id = rs.getInt(1)+1;
                    String no = String.valueOf(set_id);
                    int id_next = no.length();
                    for(int a=0; a <3 - id_next; a++){
                        no = "0" +no;
                    }
                    txt_idRJ.setText("IRJ"+no);
                }
            }
        } catch (SQLException ex) {
            
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_pasien = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_dokter = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_obat = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabel_resep = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rbYa = new javax.swing.JRadioButton();
        rbTidak = new javax.swing.JRadioButton();
        txt_idPasien = new javax.swing.JTextField();
        txt_namaPasien = new javax.swing.JTextField();
        txt_alamatPasien = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_idObat = new javax.swing.JTextField();
        txt_namaObat = new javax.swing.JTextField();
        txt_jumlah = new javax.swing.JTextField();
        txt_stok = new javax.swing.JTextField();
        txt_harga = new javax.swing.JTextField();
        txt_total = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_idDokter = new javax.swing.JTextField();
        txt_namaDokter = new javax.swing.JTextField();
        txt_tarif = new javax.swing.JTextField();
        txt_idRJ = new javax.swing.JTextField();
        txt_idResep = new javax.swing.JTextField();
        txt_totalSemua = new javax.swing.JTextField();
        txt_cariObat = new javax.swing.JTextField();
        btn_cariObat = new javax.swing.JButton();
        txt_cariPasien = new javax.swing.JTextField();
        btn_cariPasie = new javax.swing.JButton();
        txt_cariDokter = new javax.swing.JTextField();
        btn_carDokter = new javax.swing.JButton();
        btn_tambahObat = new javax.swing.JButton();
        btn_kurangObat = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txt_totalResep = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        setClosable(true);
        setPreferredSize(new java.awt.Dimension(1150, 670));

        jPanel1.setBackground(new java.awt.Color(1, 152, 117));

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
        tabel_obat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_obatMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabel_obat);

        tabel_resep.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tabel_resep);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID Pasien");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nama");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Alamat");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("BPJS");

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

        txt_idPasien.setEditable(false);
        txt_idPasien.setBackground(new java.awt.Color(255, 255, 255));
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

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ID Obat");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nama");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Jumlah");

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Stok");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Harga");

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Total");

        txt_idObat.setEditable(false);
        txt_idObat.setBackground(new java.awt.Color(255, 255, 255));
        txt_idObat.setForeground(new java.awt.Color(255, 255, 255));
        txt_idObat.setBorder(null);

        txt_namaObat.setEditable(false);
        txt_namaObat.setBackground(new java.awt.Color(1, 152, 117));
        txt_namaObat.setForeground(new java.awt.Color(255, 255, 255));
        txt_namaObat.setBorder(null);

        txt_jumlah.setBackground(new java.awt.Color(1, 152, 117));
        txt_jumlah.setForeground(new java.awt.Color(255, 255, 255));
        txt_jumlah.setBorder(null);
        txt_jumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_jumlahKeyTyped(evt);
            }
        });

        txt_stok.setEditable(false);
        txt_stok.setBackground(new java.awt.Color(255, 255, 255));
        txt_stok.setForeground(new java.awt.Color(255, 255, 255));
        txt_stok.setBorder(null);

        txt_harga.setEditable(false);
        txt_harga.setBackground(new java.awt.Color(255, 255, 255));
        txt_harga.setForeground(new java.awt.Color(255, 255, 255));
        txt_harga.setBorder(null);

        txt_total.setEditable(false);
        txt_total.setBackground(new java.awt.Color(1, 152, 117));
        txt_total.setForeground(new java.awt.Color(255, 255, 255));
        txt_total.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_totalMouseClicked(evt);
            }
        });

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("ID Dokter");

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Nama");

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Tarif");

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

        txt_idRJ.setEditable(false);

        txt_idResep.setEditable(false);

        txt_totalSemua.setEditable(false);
        txt_totalSemua.setBackground(new java.awt.Color(1, 152, 117));
        txt_totalSemua.setForeground(new java.awt.Color(255, 255, 255));
        txt_totalSemua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_totalSemuaMouseClicked(evt);
            }
        });

        btn_cariObat.setText("CARI OBAT");
        btn_cariObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariObatActionPerformed(evt);
            }
        });

        btn_cariPasie.setText("CARI PASIEN");
        btn_cariPasie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariPasieActionPerformed(evt);
            }
        });

        btn_carDokter.setText("CARI DOKTER");
        btn_carDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_carDokterActionPerformed(evt);
            }
        });

        btn_tambahObat.setText("Tambah Obat");
        btn_tambahObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahObatActionPerformed(evt);
            }
        });

        btn_kurangObat.setText("Hapus Obat");
        btn_kurangObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kurangObatActionPerformed(evt);
            }
        });

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Total Resep");

        txt_totalResep.setEditable(false);
        txt_totalResep.setBackground(new java.awt.Color(1, 152, 117));
        txt_totalResep.setForeground(new java.awt.Color(255, 255, 255));
        txt_totalResep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_totalResepMouseClicked(evt);
            }
        });

        btn_simpan.setText("SIMPAN");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Total Semua");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Daftar Pasien");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Daftar Obat");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Tabel Resep");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Daftar Dokter");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel20)
                            .addGap(76, 76, 76)
                            .addComponent(txt_cariDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btn_carDokter))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_idDokter, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_namaDokter, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14)
                                .addGap(53, 53, 53)
                                .addComponent(txt_tarif, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator2)
                            .addComponent(txt_namaPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_alamatPasien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addGap(30, 30, 30)
                        .addComponent(txt_totalSemua, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel18)
                                        .addGap(50, 50, 50))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(38, 38, 38)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jSeparator6)
                                            .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(49, 49, 49)
                                        .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txt_cariObat, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_cariObat))))
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txt_namaObat, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGap(71, 71, 71)
                                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rbYa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbTidak)
                                .addGap(302, 302, 302))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(46, 46, 46)
                                .addComponent(txt_cariPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_cariPasie)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                                .addComponent(txt_idResep, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_idRJ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btn_tambahObat)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_kurangObat)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel15)
                                        .addGap(27, 27, 27)
                                        .addComponent(txt_totalResep, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(61, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel19)
                                .addGap(219, 219, 219))))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_idPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(txt_stok, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_idObat, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cariDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_carDokter)
                    .addComponent(txt_cariPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cariPasie)
                    .addComponent(jLabel17)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txt_namaPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txt_alamatPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbYa)
                            .addComponent(rbTidak)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txt_idDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txt_namaDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_tarif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(82, 82, 82)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_totalSemua, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_namaObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGap(22, 22, 22))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_cariObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_cariObat)
                                    .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_tambahObat)
                                    .addComponent(btn_kurangObat)
                                    .addComponent(jLabel15)
                                    .addComponent(txt_totalResep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txt_idResep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_idRJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(29, 29, 29))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_stok, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txt_idObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(txt_idPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel9)))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        else if(tabel_resep.getRowCount() == 0)
        {
            JOptionPane.showMessageDialog(null, "Masukan data resep","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if(txt_totalResep.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Klik 2x pada input total resep !","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if(txt_totalSemua.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Klik 2x pada input total Semua !","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else {
            txt_idPasien.setText(txt_idPasien.getText());
            txt_idDokter.setText(txt_idDokter.getText());
            txt_totalResep.setText(txt_totalResep.getText());
            txt_totalSemua.setText(txt_totalSemua.getText());
        }
        try {
            Date skrg = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String tgl = format.format(skrg);
            String sql = "INSERT INTO resep VALUES ('"+txt_idResep.getText()+"','"+txt_totalResep.getText()+"')";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            
            //update stok
            int t = tabel_resep.getRowCount();
            for(int i=0; i < t ; i++)
            {
                String id_obat= tabel_resep.getValueAt(i,0).toString();
                double jumlah= Integer.parseInt(tabel_resep.getValueAt(i,3).toString());
                double total_harga= Integer.parseInt(tabel_resep.getValueAt(i,5).toString());
                double stok= Integer.parseInt(tabel_resep.getValueAt(i,4).toString());

                String sql2="insert into detail_resep (id_resep,id_obat,jumlah_satuan,total_harga) values('"+txt_idResep.getText()+"','"+id_obat+"','"+jumlah+"','"+total_harga+"')";
                java.sql.PreparedStatement pst2=conn.prepareStatement(sql2);
                pst2.execute();
                String sql5 ="UPDATE obat SET stok = '"+stok+"' WHERE id_obat = '"+id_obat+"'";
                java.sql.PreparedStatement pst5=conn.prepareStatement(sql5);
                pst5.execute();
            }
            String bpjs = null;
            if(rbYa.isSelected())
            {
                bpjs="Ya";
            }
            else if (rbTidak.isSelected())
            {
                bpjs="Tidak";
            }
            String sql3 = "INSERT INTO rawat_jalan VALUES ('"+txt_idRJ.getText()+"','"+txt_idPasien.getText()+"','"+txt_idDokter.getText()+"','"+txt_idResep.getText()+"','"+bpjs+"','"+tgl+"','"+txt_totalSemua.getText()+"')";
            java.sql.PreparedStatement pst3=conn.prepareStatement(sql3);
            pst3.execute();

            String sp="Sudah";
            String sql4 ="UPDATE pasien SET status_periksa = '"+sp+"',tgl_akhirPeriksa='"+tgl+"' WHERE id_pasien = '"+txt_idPasien.getText()+"'";
            java.sql.PreparedStatement pst4=conn.prepareStatement(sql4);
            pst4.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tampil_pasien();
        tampil_obat();
        tampil_dokter();
        inisialisasi_tabel();
        auto_numberResep();
        auto_numberRJ();
        kosong();
    }//GEN-LAST:event_btn_simpanActionPerformed
    // Memanggil getsum
    private void txt_totalResepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_totalResepMouseClicked
        // TODO add your handling code here:
        getsum();
    }//GEN-LAST:event_txt_totalResepMouseClicked

    private void btn_kurangObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kurangObatActionPerformed
        // TODO add your handling code here:
        tableModel.removeRow(row);
    }//GEN-LAST:event_btn_kurangObatActionPerformed

    private void btn_tambahObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahObatActionPerformed
        // TODO add your handling code here:
        if(txt_idObat.getText().equals("")
            ||txt_jumlah.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Masukkan data obat dengan benar !","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if(txt_total.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Klik 2x pada input total !","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else
        {
            txt_idObat.setText(txt_idObat.getText());
            txt_jumlah.setText(txt_jumlah.getText());
            txt_total.setText(txt_total.getText());
        }
        simpan_ditabel();
        txt_idObat.setText(null);
        txt_namaObat.setText(null);
        txt_stok.setText(null);
        txt_harga.setText(null);
        txt_jumlah.setText(null);
        txt_total.setText(null);
    }//GEN-LAST:event_btn_tambahObatActionPerformed

    private void btn_carDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_carDokterActionPerformed
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
    }//GEN-LAST:event_btn_carDokterActionPerformed

    private void btn_cariPasieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariPasieActionPerformed
        // TODO add your handling code here:
        try{
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID Pasien");
            model.addColumn("Nama");
            model.addColumn("Jenis Kelamin");
            model.addColumn("Alamat");
            model.addColumn("Keluhan");
            String cari = txt_cariPasien.getText();
            String status = "Belum";
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
    }//GEN-LAST:event_btn_cariPasieActionPerformed

    private void btn_cariObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariObatActionPerformed
        // TODO add your handling code here:
        try{
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID Obat");
            model.addColumn("Nama");
            model.addColumn("Dosis");
            model.addColumn("Harga");
            String cari = txt_cariObat.getText();
            String sql = "SELECT * FROM obat WHERE id_obat LIKE '%"+cari+"%' OR nama LIKE '%"+cari+"%' OR dosis LIKE '%"+cari+"%' OR harga_jual LIKE '%"+cari+"%' ORDER BY id_obat";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString("id_obat"),res.getString("nama"),res.getString("dosis"),res.getString("harga_jual")});
            }
            tabel_obat.setModel(model);
            txt_cariObat.setText(null);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Data yang dicari tidak ada !!!!");

        }
    }//GEN-LAST:event_btn_cariObatActionPerformed

    private void txt_totalSemuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_totalSemuaMouseClicked
        // TODO add your handling code here:
        if(txt_totalResep.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Klik 2x pada total resep","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else {
            int tarif = Integer.parseInt(txt_tarif.getText());
            int tot_resep = Integer.parseInt(txt_totalResep.getText());
            int total_semua = tarif + tot_resep ;
            txt_totalSemua.setText(String.valueOf(total_semua));
        }
    }//GEN-LAST:event_txt_totalSemuaMouseClicked

    private void txt_totalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_totalMouseClicked
        // TODO add your handling code here:
        try{
            int jml = Integer.parseInt(txt_jumlah.getText());
            int hrg = Integer.parseInt(txt_harga.getText());
            int z = jml*hrg;

            txt_total.setText(String.valueOf(z));
        }
        catch(NumberFormatException exception)
        {
            System.out.println("Error ss : "+exception);
        }
    }//GEN-LAST:event_txt_totalMouseClicked

    private void txt_jumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jumlahKeyTyped
        // TODO add your handling code here:
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_txt_jumlahKeyTyped

    private void rbTidakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTidakActionPerformed
        // TODO add your handling code here:

        txt_totalResep.setText(null);
        txt_totalSemua.setText(null);
    }//GEN-LAST:event_rbTidakActionPerformed

    private void rbYaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbYaActionPerformed
        // TODO add your handling code here:
        txt_totalResep.setText("0");
        txt_totalSemua.setText("0");
    }//GEN-LAST:event_rbYaActionPerformed

    private void tabel_obatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_obatMouseClicked
        // TODO add your handling code here:
        int baris = tabel_obat.rowAtPoint(evt.getPoint());
        String id_obat =tabel_obat.getValueAt(baris, 0).toString();
        txt_idObat.setText(id_obat);
        txt_idObat.setEditable(false);
        String nama_obat = tabel_obat.getValueAt(baris,1).toString();
        txt_namaObat.setText(nama_obat);
        String dosis = tabel_obat.getValueAt(baris,2).toString();
        txt_stok.setText(dosis);
        String harga = tabel_obat.getValueAt(baris,3).toString();
        txt_harga.setText(harga);
    }//GEN-LAST:event_tabel_obatMouseClicked

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_carDokter;
    private javax.swing.JButton btn_cariObat;
    private javax.swing.JButton btn_cariPasie;
    private javax.swing.JButton btn_kurangObat;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambahObat;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JRadioButton rbTidak;
    private javax.swing.JRadioButton rbYa;
    private javax.swing.JTable tabel_dokter;
    private javax.swing.JTable tabel_obat;
    private javax.swing.JTable tabel_pasien;
    private javax.swing.JTable tabel_resep;
    private javax.swing.JTextField txt_alamatPasien;
    private javax.swing.JTextField txt_cariDokter;
    private javax.swing.JTextField txt_cariObat;
    private javax.swing.JTextField txt_cariPasien;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_idDokter;
    private javax.swing.JTextField txt_idObat;
    private javax.swing.JTextField txt_idPasien;
    private javax.swing.JTextField txt_idRJ;
    private javax.swing.JTextField txt_idResep;
    private javax.swing.JTextField txt_jumlah;
    private javax.swing.JTextField txt_namaDokter;
    private javax.swing.JTextField txt_namaObat;
    private javax.swing.JTextField txt_namaPasien;
    private javax.swing.JTextField txt_stok;
    private javax.swing.JTextField txt_tarif;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_totalResep;
    private javax.swing.JTextField txt_totalSemua;
    // End of variables declaration//GEN-END:variables
}
