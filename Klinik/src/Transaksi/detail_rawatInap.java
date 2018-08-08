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
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class detail_rawatInap extends javax.swing.JFrame {

    /**
     * Creates new form detail_rawatInap
     */
    DefaultTableModel tableModel = new DefaultTableModel(
    new Object [ ][ ] {},
    new String [ ] {
    "ID Obat", "Nama","Harga","Jumlah","Stok","Total"
    });
    private int row;
    
    DefaultTableModel tableModel2 = new DefaultTableModel(
    new Object [ ][ ] {},
    new String [ ] {
    "ID Tindakan", "Nama","Harga"
    });
    private int row2;
    
    public detail_rawatInap() {
        initComponents();
        data_biayaLain();
        data_resep();
        tampil_tindakan();
        tampil_obat();
        hidden();
        total_hargaBiaya();
        total_hargaResep();
        this.setLocation(208, 99); 
        String a = Session.getId5();
        bpjs.setText(a);

    }
    private void kosong_tindakan(){
        txt_idTindakan.setText(null);
        txt_namaTindakan.setText(null);
        txt_hargaTindakan.setText(null);
        txt_totalTindakan.setText(null);
        DefaultTableModel model = (DefaultTableModel) tabel_tampungTindakan.getModel();
        model.setRowCount(0);
        
    }
    private void kosong_resep(){
        txt_idObat.setText(null);
        txt_harga.setText(null);
        txt_stok.setText(null);
        txt_namaObat.setText(null);
        txt_jumlah.setText(null);
        txt_total.setText(null);
        txt_totalResep.setText(null);
        DefaultTableModel model = (DefaultTableModel) tabel_tampungResep.getModel();
        model.setRowCount(0);
        
    }
    private void total_hargaBiaya(){
        try {
            //int no=1;
            String id_biaya = Session.getId2();
            String sql = "SELECT * FROM biaya_lain WHERE id_biayaLain='"+id_biaya+"'";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                total_hargaBiaya.setText(res.getString("total_hargaBiaya"));
            }   
        } catch (SQLException e) {
        }
    }
    private void total_hargaResep(){
        try {
            //int no=1;
            String id_resep = Session.getId4();
            String sql = "SELECT * FROM resep WHERE id_resep='"+id_resep+"'";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                
                total_hargaResep.setText(res.getString("total_hargaResep"));
            }   
        } catch (SQLException e) {
        }
    }
    private void hidden(){
    txt_idTindakan.setVisible(false);
    txt_idObat.setVisible(false);
    txt_stok.setVisible(false);
    txt_harga.setVisible(false);
    total_hargaBiaya.setVisible(false);
    total_hargaResep.setVisible(false);
    bpjs.setVisible(false);
    
    }
    private void inisialisasi_tabel() {
        tabel_tampungResep.setModel(tableModel);
    }
    private void simpan_ditabel() {
        try{
          int jml = Integer.parseInt(txt_jumlah.getText());
          int hrg = Integer.parseInt(txt_harga.getText());
          int s = Integer.parseInt(txt_stok.getText()) - Integer.parseInt(txt_jumlah.getText()) ;
          int tot = Integer.parseInt(txt_total.getText());

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
    public void getsum(){
        if(bpjs.getText().equals("Ya")){
            txt_totalResep.setText("0");
        } else {
            int batas = tabel_tampungResep.getRowCount();
            int sum = 0;
            for(int i=0; i < batas; i++)
            {
                sum = sum + Integer.parseInt(tabel_tampungResep.getValueAt(i,5).toString());
            }
            txt_totalResep.setText(Integer.toString(sum));
        }
        
    }
    private void inisialisasi_tabel2() {
        tabel_tampungTindakan.setModel(tableModel2);
    }
    private void simpan_ditabel2() {
        try{

          String id_tindakan= String.valueOf(txt_idTindakan.getText());
          String nama=String.valueOf(txt_namaTindakan.getText());
          String harga=String.valueOf(txt_hargaTindakan.getText());
        tableModel2.addRow(new Object[]{id_tindakan,nama,harga});
        inisialisasi_tabel2();
    }
        catch(NumberFormatException exception)
    {
    System.out.println("Error ss : "+exception);
    }

    }
    public void getsum2(){
        
        if(bpjs.getText().equals("Ya")){
            txt_totalTindakan.setText("0");
        } else {
            int batas = tabel_tampungTindakan.getRowCount();
            int sum = 0;
            for(int i=0; i < batas; i++)
            {
                sum = sum + Integer.parseInt(tabel_tampungTindakan.getValueAt(i,2).toString());
            }
            txt_totalTindakan.setText(Integer.toString(sum));
        }
        
    }
    private void data_biayaLain(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Biaya");
        model.addColumn("Nama");
        model.addColumn("Tgl Tindakan");
        model.addColumn("Total");

        
        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            String id = Session.getId();
            String sql = "SELECT detail_biayalain.id_biayaLain,tindakan.nama,detail_biayalain.tgl_tindakan,detail_biayalain.total_harga FROM rawat_inap JOIN biaya_lain ON rawat_inap.id_biayaLain=biaya_lain.id_biayaLain JOIN detail_biayalain ON biaya_lain.id_biayaLain=detail_biayalain.id_biayaLain JOIN tindakan ON detail_biayalain.id_tindakan=tindakan.id_tindakan WHERE id_rawatInap='"+id+"'";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4)});
            }
            tabel_biayaLain.setModel(model);
        } catch (SQLException e) {
        }
    }
    private void data_resep(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Resep");
        model.addColumn("Nama");
        model.addColumn("Jumlah");
        model.addColumn("Total");

        
        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            String id = Session.getId();
            String sql = "SELECT detail_resep.id_resep,obat.nama,detail_resep.jumlah_satuan,detail_resep.total_harga FROM rawat_inap JOIN resep ON rawat_inap.id_resep=resep.id_resep JOIN detail_resep ON resep.id_resep=detail_resep.id_resep JOIN obat ON detail_resep.id_obat=obat.id_obat WHERE id_rawatInap='"+id+"'";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4)});
            }
            tabel_resep.setModel(model);
        } catch (SQLException e) {
        }
    }
    private void tampil_tindakan(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Tindakan");
        model.addColumn("Nama");
        model.addColumn("Biaya");
        

        
        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            String sql = "SELECT * FROM tindakan";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3)});
            }
            
            tabel_tindakan.setModel(model);
            
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_namaObat = new javax.swing.JTextField();
        txt_jumlah = new javax.swing.JTextField();
        txt_totalResep = new javax.swing.JTextField();
        btn_tambahObat = new javax.swing.JButton();
        btn_kurangObat = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabel_tampungTindakan = new javax.swing.JTable();
        btn_tambahTindakan = new javax.swing.JButton();
        btn_hapusTindakan = new javax.swing.JButton();
        txt_totalTindakan = new javax.swing.JTextField();
        btn_cariObat = new javax.swing.JButton();
        txt_cariObat = new javax.swing.JTextField();
        txt_cari = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        btn_simpanTindakan = new javax.swing.JButton();
        total_hargaBiaya = new javax.swing.JTextField();
        total_hargaResep = new javax.swing.JTextField();
        btn_simpanResep = new javax.swing.JButton();
        bpjs = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_resep = new javax.swing.JTable();
        btn_kembali = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_tindakan = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabel_obat = new javax.swing.JTable();
        txt_idTindakan = new javax.swing.JTextField();
        txt_hargaTindakan = new javax.swing.JTextField();
        txt_namaTindakan = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabel_tampungResep = new javax.swing.JTable();
        txt_total = new javax.swing.JTextField();
        txt_harga = new javax.swing.JTextField();
        txt_stok = new javax.swing.JTextField();
        txt_idObat = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_biayaLain = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(1, 152, 117));
        jPanel1.setPreferredSize(new java.awt.Dimension(1136, 644));

        txt_namaObat.setEditable(false);
        txt_namaObat.setBackground(new java.awt.Color(1, 152, 117));
        txt_namaObat.setForeground(new java.awt.Color(255, 255, 255));

        txt_jumlah.setBackground(new java.awt.Color(1, 152, 117));
        txt_jumlah.setForeground(new java.awt.Color(255, 255, 255));
        txt_jumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_jumlahKeyTyped(evt);
            }
        });

        txt_totalResep.setEditable(false);
        txt_totalResep.setBackground(new java.awt.Color(1, 152, 117));
        txt_totalResep.setForeground(new java.awt.Color(255, 255, 255));
        txt_totalResep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_totalResepMouseClicked(evt);
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

        tabel_tampungTindakan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID Tindakan", "Nama", "Harga"
            }
        ));
        jScrollPane6.setViewportView(tabel_tampungTindakan);

        btn_tambahTindakan.setText("Tambah");
        btn_tambahTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahTindakanActionPerformed(evt);
            }
        });

        btn_hapusTindakan.setText("Hapus");
        btn_hapusTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusTindakanActionPerformed(evt);
            }
        });

        txt_totalTindakan.setEditable(false);
        txt_totalTindakan.setBackground(new java.awt.Color(1, 152, 117));
        txt_totalTindakan.setForeground(new java.awt.Color(255, 255, 255));
        txt_totalTindakan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_totalTindakanMouseClicked(evt);
            }
        });

        btn_cariObat.setText("CARI OBAT");
        btn_cariObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariObatActionPerformed(evt);
            }
        });

        btn_cari.setText("CARI TINDAKAN");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });

        btn_simpanTindakan.setText("SIMPAN");
        btn_simpanTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanTindakanActionPerformed(evt);
            }
        });

        total_hargaResep.setEditable(false);

        btn_simpanResep.setText("Simpan");
        btn_simpanResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanResepActionPerformed(evt);
            }
        });

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
        jScrollPane2.setViewportView(tabel_resep);

        btn_kembali.setText("KEMBALI");
        btn_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliActionPerformed(evt);
            }
        });

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
        tabel_tindakan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_tindakanMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabel_tindakan);

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
        jScrollPane4.setViewportView(tabel_obat);

        txt_idTindakan.setEditable(false);

        txt_hargaTindakan.setEditable(false);
        txt_hargaTindakan.setBackground(new java.awt.Color(1, 152, 117));
        txt_hargaTindakan.setForeground(new java.awt.Color(255, 255, 255));

        txt_namaTindakan.setEditable(false);
        txt_namaTindakan.setBackground(new java.awt.Color(1, 152, 117));
        txt_namaTindakan.setForeground(new java.awt.Color(255, 255, 255));

        tabel_tampungResep.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Obat", "Nama", "Harga", "Jumlah", "Stok", "Total"
            }
        ));
        jScrollPane5.setViewportView(tabel_tampungResep);

        txt_total.setEditable(false);
        txt_total.setBackground(new java.awt.Color(1, 152, 117));
        txt_total.setForeground(new java.awt.Color(255, 255, 255));
        txt_total.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_totalMouseClicked(evt);
            }
        });

        txt_harga.setEditable(false);

        txt_stok.setEditable(false);

        txt_idObat.setEditable(false);

        tabel_biayaLain.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabel_biayaLain);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nama Tindakan");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Harga");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Jumlah");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Total");

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Total Tindakan");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total Resep");

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Daftar Tindakan");

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Daftar Resep");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_kembali)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_tambahTindakan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_hapusTindakan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txt_totalTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_simpanTindakan))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(total_hargaResep, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(txt_idTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(total_hargaBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bpjs, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_cari)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(204, 204, 204)
                                .addComponent(txt_idObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_stok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(117, 117, 117)
                                .addComponent(txt_cariObat, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_cariObat))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btn_tambahObat)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btn_kurangObat)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel7))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(18, 18, 18)
                                                .addComponent(txt_namaObat, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel4)
                                                .addGap(15, 15, 15)
                                                .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(32, 32, 32)
                                                .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txt_totalResep, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btn_simpanResep))))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txt_namaTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txt_hargaTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(103, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(294, 294, 294))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_kembali)
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_cari)
                            .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(total_hargaBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bpjs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(total_hargaResep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_idTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_cariObat)
                            .addComponent(txt_cariObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_idObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_stok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_namaObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_namaTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_hargaTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tambahObat)
                    .addComponent(btn_kurangObat)
                    .addComponent(txt_totalTindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_simpanTindakan)
                    .addComponent(btn_tambahTindakan)
                    .addComponent(btn_hapusTindakan)
                    .addComponent(btn_simpanResep)
                    .addComponent(txt_totalResep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new Daftar_RI().setVisible(true);
    }//GEN-LAST:event_btn_kembaliActionPerformed

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

    private void btn_kurangObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kurangObatActionPerformed
        // TODO add your handling code here:
        tableModel.removeRow(row);
    }//GEN-LAST:event_btn_kurangObatActionPerformed

    private void tabel_tindakanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_tindakanMouseClicked
        // TODO add your handling code here:
        int baris = tabel_tindakan.rowAtPoint(evt.getPoint());
        String id_obat =tabel_tindakan.getValueAt(baris, 0).toString();
        txt_idTindakan.setText(id_obat);
        txt_idTindakan.setEditable(false);
        String nama = tabel_tindakan.getValueAt(baris,1).toString();
        txt_namaTindakan.setText(nama);
        String harga_beli = tabel_tindakan.getValueAt(baris, 2).toString();
        txt_hargaTindakan.setText(harga_beli);
    }//GEN-LAST:event_tabel_tindakanMouseClicked

    private void btn_tambahTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahTindakanActionPerformed
        // TODO add your handling code here:
        if(txt_idTindakan.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Masukkan data tindakan dengan benar !","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else
        {
            txt_idTindakan.setText(txt_idTindakan.getText());
            txt_namaTindakan.setText(txt_namaTindakan.getText());
            txt_hargaTindakan.setText(txt_hargaTindakan.getText());
        }
        simpan_ditabel2();
        txt_idTindakan.setText(null);
        txt_namaTindakan.setText(null);
        txt_hargaTindakan.setText(null);
    }//GEN-LAST:event_btn_tambahTindakanActionPerformed

    private void txt_totalTindakanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_totalTindakanMouseClicked
        // TODO add your handling code here:
        getsum2();
    }//GEN-LAST:event_txt_totalTindakanMouseClicked

    private void btn_hapusTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusTindakanActionPerformed
        // TODO add your handling code here:
        tableModel2.removeRow(row2);
    }//GEN-LAST:event_btn_hapusTindakanActionPerformed

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

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        try{
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID Tindakan");
            model.addColumn("Nama");
            model.addColumn("Biaya");
            String cari = txt_cari.getText();
            String sql = "SELECT * FROM tindakan WHERE id_tindakan LIKE '%"+cari+"%' OR nama LIKE '%"+cari+"%' OR biaya LIKE '%"+cari+"%' ORDER BY id_tindakan";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3)});
            }
            tabel_tindakan.setModel(model);
            txt_cari.setText(null);
        }catch(Exception ex){
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, "Data yang dicari tidak ada !!!!");

        }
    }//GEN-LAST:event_btn_cariActionPerformed

    private void btn_simpanTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanTindakanActionPerformed
        // TODO add your handling code here:
        if(tabel_tampungTindakan.getRowCount() == 0)
        {
            JOptionPane.showMessageDialog(null, "Masukan data tindakan","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if(txt_totalTindakan.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Klik 2x pada total Tindakan !","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Date skrg = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String tgl = format.format(skrg);
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            int t = tabel_tampungTindakan.getRowCount();
            for(int i=0; i < t ; i++)
            {
                String id_biaya = Session.getId2();
                String id_tindakan= tabel_tampungTindakan.getValueAt(i,0).toString();
                String nama= tabel_tampungTindakan.getValueAt(i,1).toString();
                String harga= tabel_tampungTindakan.getValueAt(i,2).toString();

                String sql="insert into detail_biayaLain (id_biayaLain,id_tindakan,tgl_tindakan,total_harga) values('"+id_biaya+"','"+id_tindakan+"','"+tgl+"','"+harga+"')";
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
            }
                String id_biaya = Session.getId2();
                int harga = Integer.parseInt(total_hargaBiaya.getText());
                int tot_tindakan = Integer.parseInt(txt_totalTindakan.getText());
                int total_semua = harga + tot_tindakan ;
                String sql2 ="UPDATE biaya_lain SET total_hargaBiaya = '"+total_semua+"' WHERE id_biayaLain = '"+id_biaya+"'";
                java.sql.PreparedStatement pst2=conn.prepareStatement(sql2);
                pst2.execute();
                String total = Session.getId3();
                int totals = Integer.parseInt(total);
                int total_bayar = totals + tot_tindakan;
                String sql3 ="UPDATE rawat_inap SET total_bayar = '"+total_bayar+"' WHERE id_biayaLain = '"+id_biaya+"'";
                java.sql.PreparedStatement pst3=conn.prepareStatement(sql3);
                pst3.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        data_biayaLain();
        data_resep();
        tampil_tindakan();
        tampil_obat();
        hidden();
        total_hargaBiaya();
        total_hargaResep();
        this.setLocation(208, 99);
        inisialisasi_tabel2();
        kosong_tindakan();
    }//GEN-LAST:event_btn_simpanTindakanActionPerformed

    private void btn_simpanResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanResepActionPerformed
        // TODO add your handling code here:
        if(tabel_tampungResep.getRowCount() == 0)
        {
            JOptionPane.showMessageDialog(null, "Masukan data tindakan","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if(txt_totalResep.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Klik 2x pada total Resep !","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            Date skrg = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String tgl = format.format(skrg);
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            int t = tabel_tampungResep.getRowCount();
            for(int i=0; i < t ; i++)
            {
                String id_resep = Session.getId4();
                String id_obat= tabel_tampungResep.getValueAt(i,0).toString();
                double jumlah= Integer.parseInt(tabel_tampungResep.getValueAt(i,3).toString());
                double total_harga= Integer.parseInt(tabel_tampungResep.getValueAt(i,5).toString());
                double stok= Integer.parseInt(tabel_tampungResep.getValueAt(i,4).toString());

                String sql2="insert into detail_resep (id_resep,id_obat,jumlah_satuan,total_harga) values('"+id_resep+"','"+id_obat+"','"+jumlah+"','"+total_harga+"')";
                java.sql.PreparedStatement pst2=conn.prepareStatement(sql2);
                pst2.execute();
                String sql5 ="UPDATE obat SET stok = '"+stok+"' WHERE id_obat = '"+id_obat+"'";
                java.sql.PreparedStatement pst5=conn.prepareStatement(sql5);
                pst5.execute();
            }
                String id_resep = Session.getId4();
                int harga = Integer.parseInt(total_hargaResep.getText());
                int tot_resep = Integer.parseInt(txt_totalResep.getText());
                int total_semua = harga + tot_resep ;
                String sql3 ="UPDATE resep SET total_hargaResep = '"+total_semua+"' WHERE id_resep = '"+id_resep+"'";
                java.sql.PreparedStatement pst3=conn.prepareStatement(sql3);
                pst3.execute();
                String total = Session.getId3();
                int totals = Integer.parseInt(total);
                int total_bayar = totals + tot_resep;
                String sql4 ="UPDATE rawat_inap SET total_bayar = '"+total_bayar+"' WHERE id_resep = '"+id_resep+"'";
                java.sql.PreparedStatement pst4=conn.prepareStatement(sql4);
                pst4.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        data_biayaLain();
        data_resep();
        tampil_tindakan();
        tampil_obat();
        hidden();
        total_hargaBiaya();
        total_hargaResep();
        this.setLocation(208, 99);
        inisialisasi_tabel();
        kosong_resep();
    }//GEN-LAST:event_btn_simpanResepActionPerformed

    private void txt_totalResepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_totalResepMouseClicked
        // TODO add your handling code here:
        getsum();
    }//GEN-LAST:event_txt_totalResepMouseClicked

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
            java.util.logging.Logger.getLogger(detail_rawatInap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(detail_rawatInap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(detail_rawatInap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(detail_rawatInap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new detail_rawatInap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bpjs;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_cariObat;
    private javax.swing.JButton btn_hapusTindakan;
    private javax.swing.JButton btn_kembali;
    private javax.swing.JButton btn_kurangObat;
    private javax.swing.JButton btn_simpanResep;
    private javax.swing.JButton btn_simpanTindakan;
    private javax.swing.JButton btn_tambahObat;
    private javax.swing.JButton btn_tambahTindakan;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable tabel_biayaLain;
    private javax.swing.JTable tabel_obat;
    private javax.swing.JTable tabel_resep;
    private javax.swing.JTable tabel_tampungResep;
    private javax.swing.JTable tabel_tampungTindakan;
    private javax.swing.JTable tabel_tindakan;
    private javax.swing.JTextField total_hargaBiaya;
    private javax.swing.JTextField total_hargaResep;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_cariObat;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_hargaTindakan;
    private javax.swing.JTextField txt_idObat;
    private javax.swing.JTextField txt_idTindakan;
    private javax.swing.JTextField txt_jumlah;
    private javax.swing.JTextField txt_namaObat;
    private javax.swing.JTextField txt_namaTindakan;
    private javax.swing.JTextField txt_stok;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_totalResep;
    private javax.swing.JTextField txt_totalTindakan;
    // End of variables declaration//GEN-END:variables
}
