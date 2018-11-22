-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 22, 2018 at 07:58 PM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kuliah_kel12`
--

-- --------------------------------------------------------

--
-- Table structure for table `dosen`
--

CREATE TABLE `dosen` (
  `nip` int(11) NOT NULL,
  `nama_dosen` char(20) NOT NULL,
  `alamat` char(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dosen`
--

INSERT INTO `dosen` (`nip`, `nama_dosen`, `alamat`) VALUES
(1001, 'Pak Rizal', 'Tembalang'),
(1003, 'Pak Teguh', 'Sampangan'),
(1004, 'Bu Oky', 'Meteseh'),
(1007, 'Pak Didik', 'Seteran'),
(1015, 'Pak Kuntoro', 'Sukoharjo');

-- --------------------------------------------------------

--
-- Stand-in structure for view `kuliah`
-- (See below for the actual view)
--
CREATE TABLE `kuliah` (
`id_matkul` varchar(40)
,`mata_kuliah` varchar(40)
,`nama_dosen` char(20)
);

-- --------------------------------------------------------

--
-- Table structure for table `matkul`
--

CREATE TABLE `matkul` (
  `id_matkul` varchar(40) NOT NULL,
  `mata_kuliah` varchar(40) NOT NULL,
  `nip` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `matkul`
--

INSERT INTO `matkul` (`id_matkul`, `mata_kuliah`, `nip`) VALUES
('A001', 'Kewirausahaan', 1001),
('A007', 'Robotika', 1007),
('A013', 'Pengolahan Citra', 1004),
('A032', 'Kecerdasan Buatan', 1015),
('A050', 'Fisika Dasar', 2010);

-- --------------------------------------------------------

--
-- Structure for view `kuliah`
--
DROP TABLE IF EXISTS `kuliah`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `kuliah`  AS  select `m`.`id_matkul` AS `id_matkul`,`m`.`mata_kuliah` AS `mata_kuliah`,`d`.`nama_dosen` AS `nama_dosen` from (`matkul` `m` left join `dosen` `d` on((`m`.`nip` = `d`.`nip`))) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dosen`
--
ALTER TABLE `dosen`
  ADD PRIMARY KEY (`nip`);

--
-- Indexes for table `matkul`
--
ALTER TABLE `matkul`
  ADD PRIMARY KEY (`id_matkul`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
