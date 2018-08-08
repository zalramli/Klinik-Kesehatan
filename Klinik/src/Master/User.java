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
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Lenovo
 */
public class User extends javax.swing.JInternalFrame {

    /** Creates new form User */
    public User() {
        initComponents();
        auto_number();
        tampil_data();
        button_awal();
        tampil_petugas();
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
            String sql = "SELECT MAX(right(id_user,3)) AS no FROM user";
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                if (rs.first()== false)
                {
                    txt_idUser.setText("USR001");
                } else {
                    rs.last();
                    int set_id = rs.getInt(1)+1;
                    String no = String.valueOf(set_id);
                    int id_next = no.length();
                    for(int a=0; a <3 - id_next; a++){
                        no = "0" +no;
                    }
                    txt_idUser.setText("USR"+no);
                }
            }
        } catch (SQLException ex) {
            
        }
    }

    private void tampil_petugas(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        tabel_petugas.setModel(model);
        model.addColumn("ID Petugas");
        model.addColumn("Nama");
        model.addColumn("Alamat");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Jabatan");
        TableColumn col2 = tabel_petugas.getColumnModel().getColumn(2);
        col2.setMinWidth(0);
        col2.setMaxWidth(0);
        col2.setWidth(0);
        col2.setPreferredWidth(0);
        TableColumn col3 = tabel_petugas.getColumnModel().getColumn(3);
        col3.setMinWidth(0);
        col3.setMaxWidth(0);
        col3.setWidth(0);
        col3.setPreferredWidth(0);
        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            String role="0";
            String sql = "SELECT * FROM petugas WHERE role='"+role+"'";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString("id_petugas"),res.getString("nama"),res.getString("alamat"),res.getString("jenis_kelamin"),res.getString("jabatan")});
            }
            //tabel_petugas.setModel(model);
        } catch (SQLException e) {
        }
    }
    
    private void tampil_data(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        jTable1.setModel(model);
        model.addColumn("ID User");
        model.addColumn("ID Petugas");
        model.addColumn("Nama");
        model.addColumn("Alamat");
        model.addColumn("No Hp");
        model.addColumn("Username");
        model.addColumn("Password");
        model.addColumn("Akses");
        model.addColumn("Status");
        TableColumn col1 = jTable1.getColumnModel().getColumn(1);
        col1.setMinWidth(0);
        col1.setMaxWidth(0);
        col1.setWidth(0);
        col1.setPreferredWidth(0);
        
        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            String sql = "SELECT * FROM user INNER JOIN petugas ON user.id_petugas=petugas.id_petugas ORDER BY id_user ASC";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString("id_user"),res.getString("id_petugas"),res.getString("nama"),res.getString("alamat"),res.getString("no_hp"),res.getString("username"),res.getString("password"),res.getString("akses"),res.getString("status")});
            }
            //jTable1.setModel(model);
        } catch (SQLException e) {
        }
    }
    private void kosong()
    {
        txt_idUser.setText(null);
        txt_idUser.setEditable(false);
        txt_idPetugas.setText(null);
        txt_username.setText("Masukan data");
        txt_password.setText("Masukan data");
        cmb_akses.setSelectedItem("Administrasi");
        cmb_status.setSelectedItem("Aktif");
        txt_nama.setText(null);
        txt_jenkel.setText(null);
        txt_jabatan.setText(null);
     }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_idUser = new javax.swing.JTextField();
        txt_idPetugas = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_username = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        txt_password = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmb_akses = new javax.swing.JComboBox<>();
        cmb_status = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txt_jabatan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        txt_jenkel = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        btn_hapus = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txt_cari = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_petugas = new javax.swing.JTable();
        txt_cari2 = new javax.swing.JTextField();
        btn_cari2 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1150, 670));

        jPanel1.setBackground(new java.awt.Color(1, 152, 117));

        jPanel3.setBackground(new java.awt.Color(1, 152, 117));

        jLabel1.setBackground(new java.awt.Color(1, 152, 117));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID USER");

        txt_idUser.setEditable(false);
        txt_idUser.setBackground(new java.awt.Color(1, 152, 117));
        txt_idUser.setForeground(new java.awt.Color(255, 255, 255));
        txt_idUser.setBorder(null);

        txt_idPetugas.setEditable(false);
        txt_idPetugas.setBackground(new java.awt.Color(1, 152, 117));
        txt_idPetugas.setForeground(new java.awt.Color(255, 255, 255));
        txt_idPetugas.setBorder(null);

        jLabel2.setBackground(new java.awt.Color(1, 152, 117));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID Petugas");

        jLabel3.setBackground(new java.awt.Color(1, 152, 117));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Username");

        txt_username.setBackground(new java.awt.Color(1, 152, 117));
        txt_username.setForeground(new java.awt.Color(255, 255, 255));
        txt_username.setBorder(null);

        txt_password.setBackground(new java.awt.Color(1, 152, 117));
        txt_password.setForeground(new java.awt.Color(255, 255, 255));
        txt_password.setBorder(null);

        jLabel4.setBackground(new java.awt.Color(1, 152, 117));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Password");

        jLabel5.setBackground(new java.awt.Color(1, 152, 117));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Akses");

        cmb_akses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Receptionist", "Admin", "Super Admin" }));

        cmb_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aktif", "Tidak Aktif" }));

        jLabel6.setBackground(new java.awt.Color(1, 152, 117));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Status");

        txt_jabatan.setEditable(false);
        txt_jabatan.setBackground(new java.awt.Color(1, 152, 117));
        txt_jabatan.setForeground(new java.awt.Color(255, 255, 255));
        txt_jabatan.setBorder(null);
        txt_jabatan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_jabatanFocusGained(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(1, 152, 117));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Jabatan");

        txt_jenkel.setEditable(false);
        txt_jenkel.setBackground(new java.awt.Color(1, 152, 117));
        txt_jenkel.setForeground(new java.awt.Color(255, 255, 255));
        txt_jenkel.setBorder(null);
        txt_jenkel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_jenkelFocusGained(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(1, 152, 117));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Jenis Kelamin");

        jLabel7.setBackground(new java.awt.Color(1, 152, 117));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nama");

        txt_nama.setEditable(false);
        txt_nama.setBackground(new java.awt.Color(1, 152, 117));
        txt_nama.setForeground(new java.awt.Color(255, 255, 255));
        txt_nama.setBorder(null);
        txt_nama.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_namaFocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_idUser, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_akses, 0, 117, Short.MAX_VALUE)
                            .addComponent(cmb_status, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                .addComponent(txt_password, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(235, 235, 235)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)))
                            .addComponent(jLabel3)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(24, 24, 24)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_idPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3)
                            .addComponent(txt_nama)
                            .addComponent(txt_jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator5)
                            .addComponent(txt_jenkel)
                            .addComponent(jSeparator4))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_idUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(19, 19, 19)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_jenkel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txt_idPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cmb_akses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(cmb_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(638, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(283, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(1, 152, 117));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        btn_hapus.setText("HAPUS");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_hapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_simpan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_batal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_simpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_batal)
                .addGap(18, 18, 18)
                .addComponent(btn_update)
                .addGap(18, 18, 18)
                .addComponent(btn_hapus)
                .addContainerGap(22, Short.MAX_VALUE))
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

        tabel_petugas.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_petugas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_petugasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_petugas);

        btn_cari2.setText("Cari");
        btn_cari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cari2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(txt_cari2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_cari2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(btn_cari))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_cari2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_cari2))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_cari)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(766, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(454, 454, 454))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(183, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        if(txt_idPetugas.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Masukkan data petugas !","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if(txt_username.getText().equals("Masukan data")
            ||txt_password.getText().equals("Masukan datas"))
        {
            JOptionPane.showMessageDialog(null, "Masukkan data dengan benar !","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if(txt_username.getText().equals("")
            ||txt_password.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Masukkan data dengan benar !","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else {
            txt_idPetugas.setText(txt_idPetugas.getText());
            txt_username.setText(txt_username.getText());
            txt_password.setText(txt_password.getText());
        }
        try {
            String role = "1";
            String sql = "INSERT INTO user VALUES ('"+txt_idUser.getText()+"','"+txt_idPetugas.getText()+"','"+txt_username.getText()+"','"+txt_password.getText()+"','"+cmb_akses.getSelectedItem()+"','"+cmb_status.getSelectedItem()+"')";
            String sql2 ="UPDATE petugas SET role = '"+role+"' WHERE id_petugas = '"+txt_idPetugas.getText()+"'";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            java.sql.PreparedStatement pst2=conn.prepareStatement(sql2);
            pst2.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tampil_data();
        kosong();
        auto_number();
        button_awal();
        tampil_petugas();
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        // TODO add your handling code here:
        tampil_data();
        kosong();
        auto_number();
        button_awal();
        tampil_petugas();
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        if(txt_idPetugas.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Masukkan data petugas !","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if(txt_username.getText().equals("Masukan data")
            ||txt_password.getText().equals("Masukan datas"))
        {
            JOptionPane.showMessageDialog(null, "Masukkan data dengan benar !","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if(txt_username.getText().equals("")
            ||txt_password.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Masukkan data dengan benar !","Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else {
            txt_idPetugas.setText(txt_idPetugas.getText());
            txt_username.setText(txt_username.getText());
            txt_password.setText(txt_password.getText());
        }
        try {
            String sql ="UPDATE user SET username = '"+txt_username.getText()+"',password = '"+txt_password.getText()+"', akses = '"+cmb_akses.getSelectedItem()+"', status = '"+cmb_status.getSelectedItem()+"' WHERE id_user = '"+txt_idUser.getText()+"'";
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
        tampil_petugas();
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        int Pilih = JOptionPane.showConfirmDialog(null,"Apakah yakin dihapus?","Pertanyaan",JOptionPane.YES_NO_OPTION);
        if(Pilih == JOptionPane.YES_OPTION){
            try {
                String role = "0";
                String sql ="delete from user where id_user='"+txt_idUser.getText()+"'";
                String sql2 ="UPDATE petugas SET role = '"+role+"' WHERE id_petugas = '"+txt_idPetugas.getText()+"'";
                java.sql.Connection conn=(Connection)Koneksi.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                java.sql.PreparedStatement pst2=conn.prepareStatement(sql2);
                pst2.execute();
                JOptionPane.showMessageDialog(this, "berhasil di hapus");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            tampil_data();
            kosong();
            auto_number();
            button_awal();
            tampil_petugas();
        }
        else if(Pilih == JOptionPane.NO_OPTION){
            tampil_data();
            kosong();
            auto_number();
            button_awal();
            tampil_petugas();
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_cari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cari2ActionPerformed
        // TODO add your handling code here:
        try{
            DefaultTableModel model = new DefaultTableModel();
            tabel_petugas.setModel(model);
            model.addColumn("ID Petugas");
            model.addColumn("Nama");
            model.addColumn("Alamat");
            model.addColumn("Jenis Kelamin");
            model.addColumn("Jabatan");
            TableColumn col2 = tabel_petugas.getColumnModel().getColumn(2);
            col2.setMinWidth(0);
            col2.setMaxWidth(0);
            col2.setWidth(0);
            col2.setPreferredWidth(0);
            TableColumn col3 = tabel_petugas.getColumnModel().getColumn(3);
            col3.setMinWidth(0);
            col3.setMaxWidth(0);
            col3.setWidth(0);
            col3.setPreferredWidth(0);
            String cari = txt_cari2.getText();
            String role="0";
            String sql = "SELECT * FROM petugas WHERE role='"+role+"' AND nama LIKE '%"+cari+"%'  ORDER BY id_petugas";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString("id_petugas"),res.getString("nama"),res.getString("alamat"),res.getString("jenis_kelamin"),res.getString("jabatan")});
            }
            //tabel_petugas.setModel(model);
            txt_cari2.setText(null);
        }catch(Exception ex){
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, "Data yang dicari tidak ada !!!!");

        }
    }//GEN-LAST:event_btn_cari2ActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        try{
            DefaultTableModel model = new DefaultTableModel();
            jTable1.setModel(model);
            model.addColumn("ID User");
            model.addColumn("ID Petugas");
            model.addColumn("Nama");
            model.addColumn("Alamat");
            model.addColumn("No Hp");
            model.addColumn("Username");
            model.addColumn("Password");
            model.addColumn("Akses");
            model.addColumn("Status");
            TableColumn col1 = jTable1.getColumnModel().getColumn(1);
            col1.setMinWidth(0);
            col1.setMaxWidth(0);
            col1.setWidth(0);
            col1.setPreferredWidth(0);
            String cari = txt_cari.getText();
            String sql = "SELECT * FROM user INNER JOIN petugas ON user.id_petugas=petugas.id_petugas WHERE id_user LIKE '%"+cari+"%' OR nama LIKE '%"+cari+"%' OR username LIKE '%"+cari+"%' OR akses LIKE '%"+cari+"%' OR status LIKE '%"+cari+"%' ORDER BY id_user";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString("id_user"),res.getString("id_petugas"),res.getString("nama"),res.getString("alamat"),res.getString("no_hp"),res.getString("username"),res.getString("password"),res.getString("akses"),res.getString("status")});
            }
            //jTable1.setModel(model);
            txt_cari.setText(null);
        }catch(Exception ex){
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, "Data yang dicari tidak ada !!!!");

        }
    }//GEN-LAST:event_btn_cariActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        button_tabelklik();
        int baris = jTable1.rowAtPoint(evt.getPoint());
        String id_user =jTable1.getValueAt(baris, 0).toString();
        txt_idUser.setText(id_user);
        txt_idUser.setEditable(false);
        String hidden = jTable1.getValueAt(baris,1).toString();
        txt_idPetugas.setText(hidden);
        String nama = jTable1.getValueAt(baris,2).toString();
        txt_nama.setText(nama);
        String alamat = jTable1.getValueAt(baris,3).toString();
        txt_jenkel.setText(alamat);
        String no_hp = jTable1.getValueAt(baris,4).toString();
        txt_jabatan.setText(no_hp);
        String username = jTable1.getValueAt(baris,5).toString();
        txt_username.setText(username);
        String password = jTable1.getValueAt(baris,6).toString();
        txt_password.setText(password);
        String akses = jTable1.getValueAt(baris, 7).toString();
        cmb_akses.setSelectedItem(akses);
        String status = jTable1.getValueAt(baris, 8).toString();
        cmb_status.setSelectedItem(status);
    }//GEN-LAST:event_jTable1MouseClicked

    private void tabel_petugasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_petugasMouseClicked
        // TODO add your handling code here:
        int baris = tabel_petugas.rowAtPoint(evt.getPoint());
        String hidden = tabel_petugas.getValueAt(baris,0).toString();
        txt_idPetugas.setText(hidden);
        String nama = tabel_petugas.getValueAt(baris,1).toString();
        txt_nama.setText(nama);
        String jenkel = tabel_petugas.getValueAt(baris,3).toString();
        txt_jenkel.setText(jenkel);
        String jabatan = tabel_petugas.getValueAt(baris,4).toString();
        txt_jabatan.setText(jabatan);
    }//GEN-LAST:event_tabel_petugasMouseClicked

    private void txt_namaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_namaFocusGained
txt_nama.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaFocusGained

    private void txt_jenkelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_jenkelFocusGained
txt_jenkel.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jenkelFocusGained

    private void txt_jabatanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_jabatanFocusGained
txt_jabatan.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jabatanFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_cari2;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cmb_akses;
    private javax.swing.JComboBox<String> cmb_status;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tabel_petugas;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_cari2;
    private javax.swing.JTextField txt_idPetugas;
    private javax.swing.JTextField txt_idUser;
    private javax.swing.JTextField txt_jabatan;
    private javax.swing.JTextField txt_jenkel;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables

}
