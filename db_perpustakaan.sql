-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 04, 2024 at 03:39 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_perpustakaan`
--

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `Id_buku` int(10) NOT NULL,
  `Judul` varchar(50) NOT NULL,
  `Kategori` varchar(30) NOT NULL,
  `Penulis` varchar(40) NOT NULL,
  `Tahun_terbit` year(4) NOT NULL,
  `Gambar` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`Id_buku`, `Judul`, `Kategori`, `Penulis`, `Tahun_terbit`, `Gambar`) VALUES
(1, 'Belajar HTML', 'pelajaran', 'Bilal', '2004', 0x7372635c75706c6f61645c312e6a706567),
(2, 'Belajar Javascript', 'Ensiklopedia', 'Bilal', '2024', 0x7372635c75706c6f61645c322e6a706567);

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman`
--

CREATE TABLE `peminjaman` (
  `Id_pinjam` int(11) NOT NULL,
  `Id_user` int(11) NOT NULL,
  `Id_buku` int(11) NOT NULL,
  `Id_staff` int(11) NOT NULL,
  `Nama_siswa` varchar(30) NOT NULL,
  `tgl_pinjam` date NOT NULL,
  `tgl_kembali` date NOT NULL,
  `denda` enum('Tidak Ada','Rusak(Rp 25.000)','Hilang(Rp 50.000)','Telat(Rp 10.000)') NOT NULL,
  `Status` enum('Kembali','Belum Kembali','Terlambat') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `peminjaman`
--

INSERT INTO `peminjaman` (`Id_pinjam`, `Id_user`, `Id_buku`, `Id_staff`, `Nama_siswa`, `tgl_pinjam`, `tgl_kembali`, `denda`, `Status`) VALUES
(3, 5, 1, 1, 'Bilal', '2024-05-17', '2024-05-18', 'Rusak(Rp 25.000)', 'Kembali'),
(4, 5, 1, 1, 'Bilal', '2024-05-17', '2024-05-21', 'Tidak Ada', 'Kembali'),
(5, 5, 1, 1, 'Bilal', '2024-05-17', '2024-05-23', 'Hilang(Rp 50.000)', 'Terlambat'),
(6, 5, 2, 1, 'Bilal', '2024-05-20', '2024-05-25', 'Telat(Rp 10.000)', 'Terlambat');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `Id_staff` int(10) NOT NULL,
  `Nama_staff` varchar(50) NOT NULL,
  `Email` varchar(40) NOT NULL,
  `Alamat` varchar(40) NOT NULL,
  `No_telp` int(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Level` enum('Admin') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`Id_staff`, `Nama_staff`, `Email`, `Alamat`, `No_telp`, `Password`, `Level`) VALUES
(1, 'Bagus Setyo', 'bagus@gmail.com', 'Pandugo', 852, 'bagus123', 'Admin'),
(2, 'bagus', 'bgs@gmail.com', 'pandugo', 852, 'bagus123', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `Id_User` int(11) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `Email` text NOT NULL,
  `Alamat` varchar(40) NOT NULL,
  `No_Telp` int(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Level` enum('Admin','Siswa') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`Id_User`, `Username`, `Email`, `Alamat`, `No_Telp`, `Password`, `Level`) VALUES
(1, 'MikeMC', 'Mike@gmail.com', 'st.unknown', 8703, 'Mike123', 'Admin'),
(2, 'bagus', 'bgs@gmail.com', 'pandugo', 852, 'bagus123', 'Siswa'),
(5, 'Bilal', 'bilal@gmail.com', 'prapen', 812345, 'bilal123', 'Siswa');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`Id_buku`);

--
-- Indexes for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`Id_pinjam`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`Id_staff`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`Id_User`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `peminjaman`
--
ALTER TABLE `peminjaman`
  MODIFY `Id_pinjam` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `Id_User` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
