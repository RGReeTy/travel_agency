CREATE DATABASE  IF NOT EXISTS `bustravelagency` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bustravelagency`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bustravelagency
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `defrayal`
--

DROP TABLE IF EXISTS `defrayal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `defrayal` (
  `id_Defrayal` int NOT NULL,
  `Date_of_payment` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Id_Tour` int NOT NULL,
  `Count` decimal(7,2) DEFAULT NULL,
  `Payment_percentage` int DEFAULT '0',
  `Id_User` int NOT NULL,
  `id_Discount` int NOT NULL DEFAULT '1',
  `Annotation` varchar(225) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_Defrayal`,`Id_Tour`,`Id_User`,`id_Discount`),
  UNIQUE KEY `idPayment_UNIQUE` (`id_Defrayal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `id_Discount` int NOT NULL,
  `Size_of_discount` int DEFAULT NULL,
  PRIMARY KEY (`id_Discount`),
  UNIQUE KEY `id_Discount_UNIQUE` (`id_Discount`),
  UNIQUE KEY `Size_of_discount_UNIQUE` (`Size_of_discount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel` (
  `id_Hotel` int NOT NULL,
  `Title` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `country` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `City` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Stars` int NOT NULL,
  `Free_rooms` int NOT NULL,
  `Nutrition` int NOT NULL,
  `Min_price_per_room` decimal(7,2) DEFAULT NULL,
  `Url_wallpaper` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_Hotel`,`Nutrition`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `nutrition`
--

DROP TABLE IF EXISTS `nutrition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nutrition` (
  `id_Nutrition` int NOT NULL,
  `Type` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_Nutrition`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tours`
--

DROP TABLE IF EXISTS `tours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tours` (
  `id_Tour` int NOT NULL,
  `Title` varchar(55) COLLATE utf8_unicode_ci NOT NULL,
  `Price` decimal(7,2) NOT NULL,
  `Type` int NOT NULL,
  `Hot_tour` tinyint(1) NOT NULL DEFAULT '0',
  `Number_of_places` int NOT NULL,
  `Date_start` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `Date_end` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `id_Discount` int NOT NULL,
  `id_Hotel` int NOT NULL,
  `Description` text COLLATE utf8_unicode_ci,
  `Url_wallpaper` varchar(225) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_Tour`,`Type`,`id_Discount`,`id_Hotel`),
  UNIQUE KEY `idTours_UNIQUE` (`id_Tour`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `typeoftour`
--

DROP TABLE IF EXISTS `typeoftour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `typeoftour` (
  `id_TypeOfTour` int NOT NULL,
  `TypeOfTour` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_TypeOfTour`),
  UNIQUE KEY `idTypeOfTour_UNIQUE` (`id_TypeOfTour`),
  UNIQUE KEY `TypeOfTour_UNIQUE` (`TypeOfTour`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id_User` int NOT NULL,
  `Login` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Firstname` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Lastname` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Phone` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_Discount` int NOT NULL,
  `Level_access` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_User`,`id_Discount`),
  UNIQUE KEY `idClients_UNIQUE` (`id_User`),
  UNIQUE KEY `login_UNIQUE` (`Login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-28 15:52:26
