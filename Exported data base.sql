-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 30, 2024 at 03:13 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `praktika_2`
--
CREATE DATABASE IF NOT EXISTS `praktika_2` DEFAULT CHARACTER SET utf8 COLLATE utf8_lithuanian_ci;
USE `praktika_2`;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `Id` int(11) NOT NULL,
  `First_Name` varchar(255) COLLATE utf8_lithuanian_ci DEFAULT NULL,
  `Last_Name` varchar(255) COLLATE utf8_lithuanian_ci DEFAULT NULL,
  `Login_User_Name` varchar(255) COLLATE utf8_lithuanian_ci DEFAULT NULL,
  `Login_Password` varchar(255) COLLATE utf8_lithuanian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_lithuanian_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`Id`, `First_Name`, `Last_Name`, `Login_User_Name`, `Login_Password`) VALUES
(404, 'Admin1', 'AdminLastName1', 'Admin1', 'AdminLastName1');

-- --------------------------------------------------------

--
-- Table structure for table `comune`
--

CREATE TABLE `comune` (
  `House_Id` int(11) NOT NULL,
  `Person_Id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_lithuanian_ci;

--
-- Dumping data for table `comune`
--

INSERT INTO `comune` (`House_Id`, `Person_Id`) VALUES
(1, 101),
(2, 102),
(3, 103),
(3, 104),
(4, 104);

-- --------------------------------------------------------

--
-- Table structure for table `house`
--

CREATE TABLE `house` (
  `Id` int(11) NOT NULL,
  `Address` varchar(255) COLLATE utf8_lithuanian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_lithuanian_ci;

--
-- Dumping data for table `house`
--

INSERT INTO `house` (`Id`, `Address`) VALUES
(1, '123 Main St'),
(2, '456 Oak St'),
(3, '789 Pine St'),
(4, 'Bandomasis Adresas'),
(5, 'Žirmūnų g. 94-10, Vilnius');

-- --------------------------------------------------------

--
-- Table structure for table `house_management`
--

CREATE TABLE `house_management` (
  `Manager_Id` int(11) NOT NULL,
  `House_Id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_lithuanian_ci;

--
-- Dumping data for table `house_management`
--

INSERT INTO `house_management` (`Manager_Id`, `House_Id`) VALUES
(9991, 1),
(9992, 2),
(9992, 3),
(9992, 4),
(9996, 5);

-- --------------------------------------------------------

--
-- Table structure for table `house_utilities`
--

CREATE TABLE `house_utilities` (
  `House_Id` int(11) NOT NULL,
  `Utility_Id` int(11) NOT NULL,
  `Price` decimal(19,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_lithuanian_ci;

--
-- Dumping data for table `house_utilities`
--

INSERT INTO `house_utilities` (`House_Id`, `Utility_Id`, `Price`) VALUES
(1, 1, '120.20'),
(2, 1, '49.00'),
(3, 1, '51.00'),
(5, 1, '50.00'),
(1, 2, '21.11'),
(2, 2, '20.00'),
(3, 2, '20.00'),
(4, 2, '17.41'),
(1, 3, '21.27'),
(3, 3, '32.00');

-- --------------------------------------------------------

--
-- Table structure for table `manager`
--

CREATE TABLE `manager` (
  `Id` int(11) NOT NULL,
  `First_Name` varchar(255) COLLATE utf8_lithuanian_ci DEFAULT NULL,
  `Last_Name` varchar(255) COLLATE utf8_lithuanian_ci DEFAULT NULL,
  `Login_User_Name` varchar(255) COLLATE utf8_lithuanian_ci DEFAULT NULL,
  `Login_Password` varchar(255) COLLATE utf8_lithuanian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_lithuanian_ci;

--
-- Dumping data for table `manager`
--

INSERT INTO `manager` (`Id`, `First_Name`, `Last_Name`, `Login_User_Name`, `Login_Password`) VALUES
(9991, 'Manager1', 'ManagerLastName1', 'Manager1', 'ManagerLastName1'),
(9992, 'Manager2', 'ManagerLastName2', 'Manager2', 'ManagerLastName2'),
(9993, 'Manager3', 'ManagerLastName3', 'Manager3', 'ManagerLastName3'),
(9994, 'Deivydas', 'Makackas', 'Deivydas', 'Makackas'),
(9995, 'Manager4', 'ManagerLastName4', 'Manager4', 'ManagerLastName4'),
(9996, 'Deivydas', 'Makackis', 'Deivydas', 'Makackis');

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `Id` int(11) NOT NULL,
  `First_Name` varchar(255) COLLATE utf8_lithuanian_ci DEFAULT NULL,
  `Last_Name` varchar(255) COLLATE utf8_lithuanian_ci DEFAULT NULL,
  `Login_User_Name` varchar(255) COLLATE utf8_lithuanian_ci DEFAULT NULL,
  `Login_Password` varchar(255) COLLATE utf8_lithuanian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_lithuanian_ci;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`Id`, `First_Name`, `Last_Name`, `Login_User_Name`, `Login_Password`) VALUES
(101, 'John', 'Doe', 'John', '123'),
(102, 'Alice', 'Smith', 'Alice', '1234'),
(103, 'Bob', 'Johnson', 'Bob', '12345'),
(104, 'Sam', 'Lunamerth', 'Sam', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `utility`
--

CREATE TABLE `utility` (
  `Id` int(11) NOT NULL,
  `Name` varchar(50) COLLATE utf8_lithuanian_ci DEFAULT NULL,
  `Basic_Price` decimal(19,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_lithuanian_ci;

--
-- Dumping data for table `utility`
--

INSERT INTO `utility` (`Id`, `Name`, `Basic_Price`) VALUES
(1, 'Electricity', '52.31'),
(2, 'Water', '20.00'),
(3, 'Gas', '30.00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `comune`
--
ALTER TABLE `comune`
  ADD PRIMARY KEY (`House_Id`,`Person_Id`),
  ADD KEY `FK_comune_person_Id` (`Person_Id`);

--
-- Indexes for table `house`
--
ALTER TABLE `house`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `house_management`
--
ALTER TABLE `house_management`
  ADD PRIMARY KEY (`Manager_Id`,`House_Id`),
  ADD KEY `FK_house_management_house_Id` (`House_Id`);

--
-- Indexes for table `house_utilities`
--
ALTER TABLE `house_utilities`
  ADD PRIMARY KEY (`House_Id`,`Utility_Id`,`Price`),
  ADD KEY `FK_house_utilities_utility_Id` (`Utility_Id`);

--
-- Indexes for table `manager`
--
ALTER TABLE `manager`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `utility`
--
ALTER TABLE `utility`
  ADD PRIMARY KEY (`Id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comune`
--
ALTER TABLE `comune`
  ADD CONSTRAINT `FK_comune_house_Id` FOREIGN KEY (`House_Id`) REFERENCES `house` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_comune_person_Id` FOREIGN KEY (`Person_Id`) REFERENCES `person` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `house_management`
--
ALTER TABLE `house_management`
  ADD CONSTRAINT `FK_house_management_house_Id` FOREIGN KEY (`House_Id`) REFERENCES `house` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_house_management_manager_Id` FOREIGN KEY (`Manager_Id`) REFERENCES `manager` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `house_utilities`
--
ALTER TABLE `house_utilities`
  ADD CONSTRAINT `FK_house_utilities_house_Id` FOREIGN KEY (`House_Id`) REFERENCES `house` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_house_utilities_utility_Id` FOREIGN KEY (`Utility_Id`) REFERENCES `utility` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
