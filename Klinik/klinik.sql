-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 18, 2018 at 01:34 PM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `klinik`
--

-- --------------------------------------------------------

--
-- Table structure for table `biaya_lain`
--

CREATE TABLE `biaya_lain` (
  `id_biayaLain` varchar(8) NOT NULL,
  `total_hargaBiaya` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `detail_biayalain`
--

CREATE TABLE `detail_biayalain` (
  `id_detailBiaya` int(11) NOT NULL,
  `id_biayaLain` varchar(8) NOT NULL,
  `id_tindakan` varchar(8) NOT NULL,
  `tgl_tindakan` date NOT NULL,
  `total_harga` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `detail_resep`
--

CREATE TABLE `detail_resep` (
  `id_detailResep` int(11) NOT NULL,
  `id_resep` varchar(8) NOT NULL,
  `id_obat` varchar(8) NOT NULL,
  `jumlah_satuan` int(3) NOT NULL,
  `total_harga` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_resep`
--

INSERT INTO `detail_resep` (`id_detailResep`, `id_resep`, `id_obat`, `jumlah_satuan`, `total_harga`) VALUES
(3, 'RSP001', 'OBT001', 2, 6000);

-- --------------------------------------------------------

--
-- Table structure for table `dokter`
--

CREATE TABLE `dokter` (
  `id_dokter` varchar(8) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `jenis_kelamin` enum('Pria','Wanita') NOT NULL,
  `alamat` varchar(35) NOT NULL,
  `no_hp` varchar(13) NOT NULL,
  `spesialis` varchar(15) NOT NULL,
  `tarif` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dokter`
--

INSERT INTO `dokter` (`id_dokter`, `nama`, `tanggal_lahir`, `jenis_kelamin`, `alamat`, `no_hp`, `spesialis`, `tarif`) VALUES
('DKT001', 'Rizkika Zakka', '1976-05-14', 'Pria', 'Kunir', '0888923443', 'Gigi', 40000),
('DKT002', 'Dimas Yudha', '1980-11-05', 'Pria', 'Rowokangkung', '08225647896', 'Jantung', 50000),
('DKT003', 'Ali Rahmatullah', '1982-06-23', 'Pria', 'Randuagung', '085243589631', 'Hati', 30000);

-- --------------------------------------------------------

--
-- Table structure for table `kamar`
--

CREATE TABLE `kamar` (
  `id_kamar` varchar(8) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `jumlah_kamar` int(2) NOT NULL,
  `jumlah_terpakai` int(2) NOT NULL,
  `harga` int(10) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kamar`
--

INSERT INTO `kamar` (`id_kamar`, `nama`, `jumlah_kamar`, `jumlah_terpakai`, `harga`, `status`) VALUES
('KMR001', 'Melati', 3, 0, 200000, 'Ada'),
('KMR002', 'Mawar', 3, 0, 300000, 'Ada'),
('KMR003', 'Anggrek', 2, 0, 400000, 'Ada');

-- --------------------------------------------------------

--
-- Table structure for table `obat`
--

CREATE TABLE `obat` (
  `id_obat` varchar(8) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `bentuk_obat` varchar(10) NOT NULL,
  `dosis` int(1) NOT NULL,
  `harga_beli` int(10) NOT NULL,
  `harga_jual` int(10) NOT NULL,
  `stok` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `obat`
--

INSERT INTO `obat` (`id_obat`, `nama`, `bentuk_obat`, `dosis`, `harga_beli`, `harga_jual`, `stok`) VALUES
('OBT001', 'Paramex', 'Kapsul', 2, 2000, 3000, 16),
('OBT002', 'Paracetamol', 'Tablet', 3, 1000, 2000, 28);

-- --------------------------------------------------------

--
-- Table structure for table `pasien`
--

CREATE TABLE `pasien` (
  `id_pasien` varchar(8) NOT NULL,
  `no_antrian` varchar(8) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `tempat_lahir` varchar(20) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `jenis_kelamin` enum('Pria','Wanita') NOT NULL,
  `alamat` varchar(30) NOT NULL,
  `nama_ortu` varchar(30) NOT NULL,
  `no_hp` varchar(13) NOT NULL,
  `keluhan` varchar(40) NOT NULL,
  `status_periksa` varchar(10) NOT NULL,
  `tgl_akhirPeriksa` date NOT NULL,
  `tgl_antrian` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pasien`
--

INSERT INTO `pasien` (`id_pasien`, `no_antrian`, `nama`, `tempat_lahir`, `tanggal_lahir`, `jenis_kelamin`, `alamat`, `nama_ortu`, `no_hp`, `keluhan`, `status_periksa`, `tgl_akhirPeriksa`, `tgl_antrian`) VALUES
('PSN001', '1', 'Rizkika', 'Solo', '2018-06-08', 'Pria', 'kunir', 'bopak', '0896618268', 'pusing,mual', 'Belum', '2018-07-11', '2018-06-29'),
('PSN002', '2', 'Aisyah', 'Lumajang', '2018-06-22', 'Wanita', 'lumajang', 'hanik', '0828216389', 'mual,muntah', 'Sudah', '2018-07-18', '2018-06-29');

-- --------------------------------------------------------

--
-- Table structure for table `petugas`
--

CREATE TABLE `petugas` (
  `id_petugas` varchar(8) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `jenis_kelamin` enum('Pria','Wanita') NOT NULL,
  `alamat` varchar(35) NOT NULL,
  `no_hp` varchar(13) NOT NULL,
  `jabatan` varchar(30) NOT NULL,
  `role` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `petugas`
--

INSERT INTO `petugas` (`id_petugas`, `nama`, `tanggal_lahir`, `jenis_kelamin`, `alamat`, `no_hp`, `jabatan`, `role`) VALUES
('PTG001', 'Rizki Afrilian', '1988-05-06', 'Pria', 'Lumajang', '087757893693', 'Administrasi', '1'),
('PTG002', 'Abda Rasyid', '1982-07-03', 'Pria', 'Yosowilangun', '085638123946', 'Cleaning Service', '0'),
('PTG003', 'Tutik', '1972-07-08', 'Wanita', 'Kunir', '089374891754', 'Perawat', '0'),
('PTG004', 'xwyz', '2018-07-18', 'Pria', 'xwyz', '08888888888', 'Administrasi', '1'),
('PTG005', 'admin', '2018-07-13', 'Pria', 'solo', '0897896986', 'Administrasi', '1');

-- --------------------------------------------------------

--
-- Table structure for table `rawat_inap`
--

CREATE TABLE `rawat_inap` (
  `id_rawatInap` varchar(8) NOT NULL,
  `id_pasien` varchar(8) NOT NULL,
  `id_dokter` varchar(8) NOT NULL,
  `id_resep` varchar(8) NOT NULL,
  `id_kamar` varchar(8) NOT NULL,
  `id_biayaLain` varchar(8) NOT NULL,
  `bpjs` enum('Ya','Tidak') NOT NULL,
  `tgl_masuk` date NOT NULL,
  `tgl_keluar` date DEFAULT NULL,
  `status` varchar(15) NOT NULL,
  `total_bayar` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `rawat_jalan`
--

CREATE TABLE `rawat_jalan` (
  `id_rawatJalan` varchar(8) NOT NULL,
  `id_pasien` varchar(8) NOT NULL,
  `id_dokter` varchar(8) NOT NULL,
  `id_resep` varchar(8) NOT NULL,
  `bpjs` enum('Ya','Tidak') NOT NULL,
  `tgl_periksa` date NOT NULL,
  `total_bayar` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rawat_jalan`
--

INSERT INTO `rawat_jalan` (`id_rawatJalan`, `id_pasien`, `id_dokter`, `id_resep`, `bpjs`, `tgl_periksa`, `total_bayar`) VALUES
('IRJ001', 'PSN002', 'DKT002', 'RSP001', 'Ya', '2018-07-18', 0);

-- --------------------------------------------------------

--
-- Table structure for table `resep`
--

CREATE TABLE `resep` (
  `id_resep` varchar(8) NOT NULL,
  `total_hargaResep` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `resep`
--

INSERT INTO `resep` (`id_resep`, `total_hargaResep`) VALUES
('RSP001', 0);

-- --------------------------------------------------------

--
-- Table structure for table `tindakan`
--

CREATE TABLE `tindakan` (
  `id_tindakan` varchar(8) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `biaya` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tindakan`
--

INSERT INTO `tindakan` (`id_tindakan`, `nama`, `biaya`) VALUES
('TND001', 'Pemberian infus', 15000),
('TND002', 'Suntik vitamin', 20000),
('TND003', 'Pemberian darah', 80000);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` varchar(8) NOT NULL,
  `id_petugas` varchar(8) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `akses` varchar(15) NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `id_petugas`, `username`, `password`, `akses`, `status`) VALUES
('USR001', 'PTG001', 'afri', 'afri', 'Receptionist', 'Aktif'),
('USR002', 'PTG004', 'super', 'super', 'Super Admin', 'Aktif'),
('USR003', 'PTG005', 'admin', 'admin', 'Admin', 'Aktif');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `biaya_lain`
--
ALTER TABLE `biaya_lain`
  ADD PRIMARY KEY (`id_biayaLain`);

--
-- Indexes for table `detail_biayalain`
--
ALTER TABLE `detail_biayalain`
  ADD PRIMARY KEY (`id_detailBiaya`),
  ADD KEY `id_biayaLain` (`id_biayaLain`),
  ADD KEY `id_tindakan` (`id_tindakan`);

--
-- Indexes for table `detail_resep`
--
ALTER TABLE `detail_resep`
  ADD PRIMARY KEY (`id_detailResep`),
  ADD KEY `id_resep` (`id_resep`),
  ADD KEY `id_obat` (`id_obat`);

--
-- Indexes for table `dokter`
--
ALTER TABLE `dokter`
  ADD PRIMARY KEY (`id_dokter`);

--
-- Indexes for table `kamar`
--
ALTER TABLE `kamar`
  ADD PRIMARY KEY (`id_kamar`);

--
-- Indexes for table `obat`
--
ALTER TABLE `obat`
  ADD PRIMARY KEY (`id_obat`);

--
-- Indexes for table `pasien`
--
ALTER TABLE `pasien`
  ADD PRIMARY KEY (`id_pasien`);

--
-- Indexes for table `petugas`
--
ALTER TABLE `petugas`
  ADD PRIMARY KEY (`id_petugas`);

--
-- Indexes for table `rawat_inap`
--
ALTER TABLE `rawat_inap`
  ADD PRIMARY KEY (`id_rawatInap`),
  ADD KEY `id_pasien` (`id_pasien`,`id_dokter`,`id_resep`,`id_kamar`,`id_biayaLain`),
  ADD KEY `id_biayaLain` (`id_biayaLain`),
  ADD KEY `id_dokter` (`id_dokter`),
  ADD KEY `id_resep` (`id_resep`),
  ADD KEY `id_kamar` (`id_kamar`);

--
-- Indexes for table `rawat_jalan`
--
ALTER TABLE `rawat_jalan`
  ADD PRIMARY KEY (`id_rawatJalan`),
  ADD KEY `id_dokter` (`id_dokter`,`id_resep`),
  ADD KEY `id_resep` (`id_resep`),
  ADD KEY `id_pasien` (`id_pasien`);

--
-- Indexes for table `resep`
--
ALTER TABLE `resep`
  ADD PRIMARY KEY (`id_resep`);

--
-- Indexes for table `tindakan`
--
ALTER TABLE `tindakan`
  ADD PRIMARY KEY (`id_tindakan`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `id_petugas` (`id_petugas`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_biayalain`
--
ALTER TABLE `detail_biayalain`
  MODIFY `id_detailBiaya` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `detail_resep`
--
ALTER TABLE `detail_resep`
  MODIFY `id_detailResep` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_biayalain`
--
ALTER TABLE `detail_biayalain`
  ADD CONSTRAINT `detail_biayalain_ibfk_2` FOREIGN KEY (`id_biayaLain`) REFERENCES `biaya_lain` (`id_biayaLain`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `detail_biayalain_ibfk_3` FOREIGN KEY (`id_tindakan`) REFERENCES `tindakan` (`id_tindakan`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `detail_resep`
--
ALTER TABLE `detail_resep`
  ADD CONSTRAINT `detail_resep_ibfk_1` FOREIGN KEY (`id_resep`) REFERENCES `resep` (`id_resep`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `detail_resep_ibfk_2` FOREIGN KEY (`id_obat`) REFERENCES `obat` (`id_obat`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `rawat_inap`
--
ALTER TABLE `rawat_inap`
  ADD CONSTRAINT `rawat_inap_ibfk_1` FOREIGN KEY (`id_pasien`) REFERENCES `pasien` (`id_pasien`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `rawat_inap_ibfk_2` FOREIGN KEY (`id_biayaLain`) REFERENCES `biaya_lain` (`id_biayaLain`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `rawat_inap_ibfk_3` FOREIGN KEY (`id_dokter`) REFERENCES `dokter` (`id_dokter`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `rawat_inap_ibfk_4` FOREIGN KEY (`id_resep`) REFERENCES `resep` (`id_resep`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `rawat_inap_ibfk_5` FOREIGN KEY (`id_kamar`) REFERENCES `kamar` (`id_kamar`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `rawat_jalan`
--
ALTER TABLE `rawat_jalan`
  ADD CONSTRAINT `rawat_jalan_ibfk_1` FOREIGN KEY (`id_resep`) REFERENCES `resep` (`id_resep`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `rawat_jalan_ibfk_2` FOREIGN KEY (`id_dokter`) REFERENCES `dokter` (`id_dokter`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `rawat_jalan_ibfk_3` FOREIGN KEY (`id_pasien`) REFERENCES `pasien` (`id_pasien`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`id_petugas`) REFERENCES `petugas` (`id_petugas`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
