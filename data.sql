-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: webhotrotuthien
-- ------------------------------------------------------
-- Server version	8.1.0

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
-- Table structure for table `bai_viet`
--

DROP TABLE IF EXISTS `bai_viet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bai_viet` (
  `MaBaiViet` int NOT NULL AUTO_INCREMENT,
  `MaThanhVien` int NOT NULL,
  `TieuDe` varchar(105) NOT NULL,
  `NoiDung` text NOT NULL,
  `GiaKhoiDiem` double DEFAULT NULL,
  `TrangThaiDauGia` int DEFAULT NULL,
  `ThoiGianBatDau` datetime DEFAULT NULL,
  `ThoiGianKetThuc` datetime DEFAULT NULL,
  `NgayTao` datetime DEFAULT NULL,
  `NgayCapNhat` datetime DEFAULT NULL,
  PRIMARY KEY (`MaBaiViet`),
  KEY `MaThanhVien_idx` (`MaThanhVien`),
  KEY `TrangThaiDauGia_idx` (`TrangThaiDauGia`),
  KEY `TrangThaiDauGia_idx_baiviet` (`TrangThaiDauGia`),
  CONSTRAINT `MaThanhVien` FOREIGN KEY (`MaThanhVien`) REFERENCES `thanh_vien` (`MaThanhVien`),
  CONSTRAINT `MaTrangThaiDauGiaBaiViet` FOREIGN KEY (`TrangThaiDauGia`) REFERENCES `trang_thai_dau_gia` (`MaTrangThaiDauGia`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bai_viet`
--

LOCK TABLES `bai_viet` WRITE;
/*!40000 ALTER TABLE `bai_viet` DISABLE KEYS */;
INSERT INTO `bai_viet` VALUES (1,1,'Chương trình từ thiện đặc biệt ngày hôm qua','Ngày 1/8, hình ảnh Top 3 Miss World Vietnam 2023 trao quà, thăm hỏi bệnh nhân ở một bệnh viện tư tại TP.HCM được chia sẻ, lan truyền trên mạng xã hội gây xôn xao.',40000,2,NULL,NULL,NULL,'2023-10-08 23:04:40'),(6,1,'Từ thiện đêm trung thu','<p>quá tuyeejet</p>\n',0,1,NULL,NULL,NULL,'2024-04-01 21:40:37');
/*!40000 ALTER TABLE `bai_viet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bao_cao_thanh_vien`
--

DROP TABLE IF EXISTS `bao_cao_thanh_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bao_cao_thanh_vien` (
  `MaThanhVienBC` int NOT NULL,
  `MaThanhVienBiBaoCao` int NOT NULL,
  `LyDoBaoCao` varchar(45) NOT NULL,
  `NgayTao` datetime DEFAULT NULL,
  `NgayCapNhat` datetime DEFAULT NULL,
  `NgayTao1` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`MaThanhVienBC`,`MaThanhVienBiBaoCao`,`LyDoBaoCao`),
  KEY `MaThanhVien_BaoCao_idx` (`MaThanhVienBC`),
  KEY `MaThanhVien_BiBaoCao_idx` (`MaThanhVienBiBaoCao`),
  CONSTRAINT `MaThanhVien_BaoCao` FOREIGN KEY (`MaThanhVienBC`) REFERENCES `thanh_vien` (`MaThanhVien`),
  CONSTRAINT `MaThanhVien_BiBaoCao` FOREIGN KEY (`MaThanhVienBiBaoCao`) REFERENCES `thanh_vien` (`MaThanhVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bao_cao_thanh_vien`
--

LOCK TABLES `bao_cao_thanh_vien` WRITE;
/*!40000 ALTER TABLE `bao_cao_thanh_vien` DISABLE KEYS */;
INSERT INTO `bao_cao_thanh_vien` VALUES (1,2,'Bài viết lừa đảo','2024-05-10 23:18:59',NULL,NULL),(1,2,'Dự án lừa đảo','2024-05-10 22:26:57',NULL,NULL),(1,2,'Ngu','2023-10-12 23:05:03',NULL,NULL),(1,2,'Oke','2023-10-14 09:07:35',NULL,NULL),(1,2,'Quấy rối','2024-05-10 22:26:39',NULL,NULL),(1,3,'Bài viết lừa đảo','2023-10-14 15:26:12',NULL,NULL),(1,3,'Quấy rối','2023-10-14 23:04:45',NULL,NULL),(2,1,'Dự án lừa đảo','2024-04-17 23:04:26',NULL,NULL);
/*!40000 ALTER TABLE `bao_cao_thanh_vien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dau_gia`
--

DROP TABLE IF EXISTS `dau_gia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dau_gia` (
  `MaThanhVien` int NOT NULL,
  `MaBaiViet` int NOT NULL,
  `GiaTien` double DEFAULT NULL,
  `DaThangDauGia` tinyint DEFAULT NULL,
  `NgayTao` datetime DEFAULT NULL,
  `NgayCapNhat` datetime DEFAULT NULL,
  PRIMARY KEY (`MaThanhVien`,`MaBaiViet`),
  KEY `MaThanhVien_KetQua_idx` (`MaThanhVien`),
  KEY `MaBaiViet_KetQua_idx` (`MaBaiViet`),
  CONSTRAINT `MaBaiViet_KetQua` FOREIGN KEY (`MaBaiViet`) REFERENCES `bai_viet` (`MaBaiViet`),
  CONSTRAINT `MaThanhVien_KetQua` FOREIGN KEY (`MaThanhVien`) REFERENCES `thanh_vien` (`MaThanhVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dau_gia`
--

LOCK TABLES `dau_gia` WRITE;
/*!40000 ALTER TABLE `dau_gia` DISABLE KEYS */;
INSERT INTO `dau_gia` VALUES (1,1,12000,1,'2023-09-23 23:05:37','2023-09-23 23:28:31'),(2,1,12000,0,'2023-09-23 23:07:12','2023-09-23 23:28:31');
/*!40000 ALTER TABLE `dau_gia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `du_an_tu_thien`
--

DROP TABLE IF EXISTS `du_an_tu_thien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `du_an_tu_thien` (
  `MaDuAn` int NOT NULL AUTO_INCREMENT,
  `MaThanhVienTaoDA` int NOT NULL,
  `TenDuAn` varchar(45) NOT NULL,
  `MucDich` varchar(45) DEFAULT NULL,
  `ThoiGianBatDau` datetime DEFAULT NULL,
  `ThoiGianKetThuc` datetime DEFAULT NULL,
  `DiaDiem` varchar(45) DEFAULT NULL,
  `SoTienHuyDong` double DEFAULT NULL,
  `DaDuyet` tinyint DEFAULT NULL,
  `NgayTao` datetime DEFAULT NULL,
  `NgayCapNhat` datetime DEFAULT NULL,
  PRIMARY KEY (`MaDuAn`),
  KEY `MaThanhVienTaoDA_idx` (`MaThanhVienTaoDA`),
  CONSTRAINT `MaThanhVienTaoDA` FOREIGN KEY (`MaThanhVienTaoDA`) REFERENCES `thanh_vien` (`MaThanhVien`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `du_an_tu_thien`
--

LOCK TABLES `du_an_tu_thien` WRITE;
/*!40000 ALTER TABLE `du_an_tu_thien` DISABLE KEYS */;
INSERT INTO `du_an_tu_thien` VALUES (1,1,'Dự Án Từ thiện ngày hôm nay','Đi xây cầu tại bình định',NULL,NULL,'Bình Định',190000,1,NULL,'2023-10-21 14:53:27'),(5,1,'Dự án từ thiện đặc biệt tại Gia Lai','Hỗ trợ các em nhỏ miền núi',NULL,NULL,'Gia Lai',100000,1,NULL,'2023-10-21 14:40:28'),(6,1,'Dự án từ thiện đặc biệt tại Mỹ','Hỗ trợ các em nhỏ miền núi',NULL,NULL,'Gia Lai',8000000,0,'2023-10-05 00:00:00','2023-10-22 17:14:07'),(9,1,'Dự án đặc biệt từ thiện','Đi xây dựng hồ bơi','2023-10-08 00:00:00','2023-10-26 00:00:00','Bình Định',1000000,1,'2023-10-06 00:00:00','2023-10-22 17:14:27'),(12,1,'Dự án từ thiện gây quỹ trẻ em','Mục Đích hoàn hảo','2023-10-09 07:00:00','2023-10-18 07:00:00','địa chỉ tuyệt vời',1460000,1,'2023-10-08 00:00:00','2023-10-22 17:31:47'),(13,2,'Dự án từ thiện ngày 15/10','Đi xây dựng cầu bình triệu, thủ đức','2023-10-16 07:00:00','2023-10-24 07:00:00','An Giang',4260000,1,'2023-10-15 00:00:00','2024-04-17 22:23:52');
/*!40000 ALTER TABLE `du_an_tu_thien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hinh_anh_bai_viet`
--

DROP TABLE IF EXISTS `hinh_anh_bai_viet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hinh_anh_bai_viet` (
  `MaHinhBaiViet` int NOT NULL AUTO_INCREMENT,
  `MaBaiViet` int DEFAULT NULL,
  `DuongDanHinh` varchar(200) DEFAULT NULL,
  `NgayTao` datetime DEFAULT NULL,
  `NgayCapNhat` datetime DEFAULT NULL,
  PRIMARY KEY (`MaHinhBaiViet`),
  KEY `MaBaiViet_HinhAnh_idx` (`MaBaiViet`),
  CONSTRAINT `MaHinh_baiViet` FOREIGN KEY (`MaBaiViet`) REFERENCES `bai_viet` (`MaBaiViet`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hinh_anh_bai_viet`
--

LOCK TABLES `hinh_anh_bai_viet` WRITE;
/*!40000 ALTER TABLE `hinh_anh_bai_viet` DISABLE KEYS */;
INSERT INTO `hinh_anh_bai_viet` VALUES (82,1,'https://firebasestorage.googleapis.com/v0/b/mangxahoituthien.appspot.com/o/images%2F1674032207_anh-tu-thien(1).jpeg?alt=media&token=5f641b3b-c409-4251-b427-e2dcbcfe9204','2023-10-08 23:04:40',NULL),(83,1,'https://firebasestorage.googleapis.com/v0/b/mangxahoituthien.appspot.com/o/images%2Fthuytien-1.jpg?alt=media&token=d7997f0a-e6a6-4b42-9f63-8454c51d3f94','2023-10-08 23:04:40',NULL),(85,6,'https://res.cloudinary.com/dibzyjddx/image/upload/v1696433538/f0798d90a6d1bc8d9c06725f6572d039.jpg','2024-04-01 21:40:37',NULL),(86,6,'https://res.cloudinary.com/dibzyjddx/image/upload/v1696433540/700c5fa1e4eaeb34ec97e09f8a8ada02.jpg','2024-04-01 21:40:37',NULL),(87,6,'https://res.cloudinary.com/dibzyjddx/image/upload/v1696433542/0aa358eaaa70278a7351a9ba0a213699.jpg','2024-04-01 21:40:37',NULL);
/*!40000 ALTER TABLE `hinh_anh_bai_viet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hinh_anh_binh_luan_da`
--

DROP TABLE IF EXISTS `hinh_anh_binh_luan_da`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hinh_anh_binh_luan_da` (
  `MaHinhAnhBinhLuan` int NOT NULL AUTO_INCREMENT,
  `MaBinhLuan` int DEFAULT NULL,
  `DuongDanHinh` varchar(200) DEFAULT NULL,
  `NgayTao` datetime DEFAULT NULL,
  `NgayCapNhat` datetime DEFAULT NULL,
  PRIMARY KEY (`MaHinhAnhBinhLuan`),
  KEY `mabinhluan-hinhanhbinhluan_idx` (`MaBinhLuan`),
  CONSTRAINT `mabinhluan-hinhanhbinhluan` FOREIGN KEY (`MaBinhLuan`) REFERENCES `tv_binh_luan_da` (`MaBinhLuan`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hinh_anh_binh_luan_da`
--

LOCK TABLES `hinh_anh_binh_luan_da` WRITE;
/*!40000 ALTER TABLE `hinh_anh_binh_luan_da` DISABLE KEYS */;
INSERT INTO `hinh_anh_binh_luan_da` VALUES (1,10,'jiojojo','2024-04-15 23:34:55',NULL),(2,11,'https://firebasestorage.googleapis.com/v0/b/mangxahoituthien.appspot.com/o/images%2FAsset%201%402x.png?alt=media&token=88518eb0-02ef-45db-9c5d-5d9005a9845c','2024-04-16 23:17:16',NULL),(3,11,'https://firebasestorage.googleapis.com/v0/b/mangxahoituthien.appspot.com/o/images%2FAsset%202%402x.png?alt=media&token=4f87d9a3-0bf4-4fc8-b057-75843d30fd4d','2024-04-16 23:17:16',NULL),(4,12,'https://firebasestorage.googleapis.com/v0/b/mangxahoituthien.appspot.com/o/images%2Fd2e40ca148f4e6aabfe5.jpg?alt=media&token=dff82f8f-da34-44eb-aa85-cf1c6d6fd7f3','2024-04-16 23:26:47',NULL),(5,12,'https://firebasestorage.googleapis.com/v0/b/mangxahoituthien.appspot.com/o/images%2Fd2e40ca148f4e6aabfe5-removebg-preview.png?alt=media&token=48374fe0-f0d6-468d-bd6f-d7a18b4e2bdb','2024-04-16 23:26:47',NULL),(6,13,'https://firebasestorage.googleapis.com/v0/b/mangxahoituthien.appspot.com/o/images%2FAsset%201%402x.png?alt=media&token=0da06727-094a-45cc-8c89-eeb5cac4461d','2024-04-22 22:58:08',NULL),(7,15,'https://firebasestorage.googleapis.com/v0/b/mangxahoituthien.appspot.com/o/images%2FAsset%202%402x.png?alt=media&token=9da6ddf0-5c3b-42d6-a1e8-be324d6805e6','2024-04-22 23:10:15',NULL),(8,16,'https://firebasestorage.googleapis.com/v0/b/mangxahoituthien.appspot.com/o/images%2Fpexels-pixabay-53594.jpg?alt=media&token=3d3ffa2c-0482-42d5-b2db-73ce62b1de7d','2024-04-23 21:05:37',NULL),(9,22,'https://firebasestorage.googleapis.com/v0/b/mangxahoituthien.appspot.com/o/images%2F9bed70c4ca9c60c2398d.jpg?alt=media&token=fb4e2d53-b8a2-441f-9ff4-32cdb5347154','2024-05-11 19:27:43',NULL),(10,22,'https://firebasestorage.googleapis.com/v0/b/mangxahoituthien.appspot.com/o/images%2F5df034d88e8024de7d91.jpg?alt=media&token=19c586a3-2a8b-4eb8-8ce8-dd3580bc8cdc','2024-05-11 19:27:43',NULL),(11,22,'https://firebasestorage.googleapis.com/v0/b/mangxahoituthien.appspot.com/o/images%2F5df034d88e8024de7d91.png?alt=media&token=b99edf26-1a3d-4993-a75c-610154b5bcce','2024-05-11 19:27:43',NULL),(12,22,'https://firebasestorage.googleapis.com/v0/b/mangxahoituthien.appspot.com/o/images%2F5df034d88e8024de7d911.png?alt=media&token=1c36d0bc-baeb-4b09-90b1-93810f52aa7d','2024-05-11 19:27:43',NULL);
/*!40000 ALTER TABLE `hinh_anh_binh_luan_da` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hinh_anh_du_an`
--

DROP TABLE IF EXISTS `hinh_anh_du_an`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hinh_anh_du_an` (
  `MaHinhDuAn` int NOT NULL AUTO_INCREMENT,
  `MaDuAn` int DEFAULT NULL,
  `DuongDanHinh` varchar(200) DEFAULT NULL,
  `NgayTao` datetime DEFAULT NULL,
  `NgayCapNhat` datetime DEFAULT NULL,
  PRIMARY KEY (`MaHinhDuAn`),
  KEY `MaHinhDuAn_idx` (`MaDuAn`),
  CONSTRAINT `MaHinhDuAn` FOREIGN KEY (`MaDuAn`) REFERENCES `du_an_tu_thien` (`MaDuAn`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hinh_anh_du_an`
--

LOCK TABLES `hinh_anh_du_an` WRITE;
/*!40000 ALTER TABLE `hinh_anh_du_an` DISABLE KEYS */;
INSERT INTO `hinh_anh_du_an` VALUES (89,1,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697874002/c1e1bc5539c6ce6dc248499d86982f29.jpg','2023-10-21 14:40:01',NULL),(90,1,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697874004/3403f8ea547fc675c554da6dc0a4b09b.jpg','2023-10-21 14:40:04',NULL),(91,1,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697874006/805b6dea5b851881dbd5b5817273b1ac.jpg','2023-10-21 14:40:06',NULL),(92,5,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697874002/c1e1bc5539c6ce6dc248499d86982f29.jpg','2023-10-21 14:40:26',NULL),(93,5,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697874006/805b6dea5b851881dbd5b5817273b1ac.jpg','2023-10-21 14:40:28',NULL),(94,6,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697969643/f4baba75c1bebf7f5a0a089702cc18da.jpg','2023-10-22 17:14:04',NULL),(95,6,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697969645/7b5d888e7cbe2235bf1f71295b116c1e.jpg','2023-10-22 17:14:06',NULL),(96,6,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697969646/761146c479f47aa7c199770a10f4c60c.jpg','2023-10-22 17:14:07',NULL),(97,9,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697969643/f4baba75c1bebf7f5a0a089702cc18da.jpg','2023-10-22 17:14:24',NULL),(98,9,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697969645/7b5d888e7cbe2235bf1f71295b116c1e.jpg','2023-10-22 17:14:26',NULL),(99,9,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697969646/761146c479f47aa7c199770a10f4c60c.jpg','2023-10-22 17:14:27',NULL),(106,12,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697969682/39ad992c064eac9ef81fe24e269ac5fc.jpg','2023-10-22 17:31:47',NULL),(107,12,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697969684/eb9bc0551623be2fbe3b4869b2839a32.jpg','2023-10-22 17:31:47',NULL),(108,12,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697874006/805b6dea5b851881dbd5b5817273b1ac.jpg','2023-10-22 17:31:47',NULL),(109,13,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697969645/7b5d888e7cbe2235bf1f71295b116c1e.jpg','2024-04-17 22:23:52',NULL),(110,13,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697874002/c1e1bc5539c6ce6dc248499d86982f29.jpg','2024-04-17 22:23:52',NULL),(111,13,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697874004/3403f8ea547fc675c554da6dc0a4b09b.jpg','2024-04-17 22:23:52',NULL);
/*!40000 ALTER TABLE `hinh_anh_du_an` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieu_yeu_cau`
--

DROP TABLE IF EXISTS `phieu_yeu_cau`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieu_yeu_cau` (
  `MaPhieuYeuCau` int NOT NULL AUTO_INCREMENT,
  `MaThanhVien` int DEFAULT NULL,
  `SoTien` double DEFAULT NULL,
  `XacNhan` tinyint DEFAULT NULL,
  `LoaiPhieu` varchar(45) DEFAULT NULL,
  `NgayTao` datetime DEFAULT NULL,
  `NgayCapNhat` datetime DEFAULT NULL,
  PRIMARY KEY (`MaPhieuYeuCau`),
  KEY `MaThanhVienPhieuYeuCau_idx` (`MaThanhVien`),
  CONSTRAINT `MaThanhVienPhieuYeuCau` FOREIGN KEY (`MaThanhVien`) REFERENCES `thanh_vien` (`MaThanhVien`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieu_yeu_cau`
--

LOCK TABLES `phieu_yeu_cau` WRITE;
/*!40000 ALTER TABLE `phieu_yeu_cau` DISABLE KEYS */;
INSERT INTO `phieu_yeu_cau` VALUES (5,1,15000,1,NULL,'2024-05-10 00:00:00','2024-05-10 22:46:47'),(7,1,35000,1,NULL,'2024-05-10 00:00:00','2024-05-10 23:27:12'),(8,1,35000,1,NULL,'2024-05-10 00:00:00','2024-05-10 23:39:24'),(9,1,100000,1,'rut_tien','2024-05-10 00:00:00','2024-05-11 17:59:31'),(10,1,30000,1,'nap_tien','2024-05-11 00:00:00','2024-05-11 17:58:34'),(11,1,0,0,'nap_tien','2024-05-11 18:37:33',NULL),(12,1,0,0,'rut_tien','2024-05-11 18:37:51',NULL),(13,1,12000,0,'nap_tien','2024-05-11 18:42:06',NULL),(14,1,1000,0,'rut_tien','2024-05-11 18:43:44',NULL),(15,3,80000,1,'nap_tien','2024-05-11 00:00:00','2024-05-11 19:33:50'),(16,1,200000,1,'nap_tien','2024-05-12 00:00:00','2024-05-12 17:01:19'),(17,6,300000,1,'nap_tien','2024-05-12 00:00:00','2024-05-12 17:17:31'),(18,3,200000,1,'nap_tien','2024-05-12 00:00:00','2024-05-12 17:22:21');
/*!40000 ALTER TABLE `phieu_yeu_cau` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quyen`
--

DROP TABLE IF EXISTS `quyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quyen` (
  `MaQuyen` int NOT NULL AUTO_INCREMENT,
  `TenQuyen` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`MaQuyen`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quyen`
--

LOCK TABLES `quyen` WRITE;
/*!40000 ALTER TABLE `quyen` DISABLE KEYS */;
INSERT INTO `quyen` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_MEMBER'),(3,'ROLE_CUSTOMER');
/*!40000 ALTER TABLE `quyen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quyen_cua_thanh_vien`
--

DROP TABLE IF EXISTS `quyen_cua_thanh_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quyen_cua_thanh_vien` (
  `MaQuyen` int NOT NULL,
  `MaThanhVien` int NOT NULL,
  PRIMARY KEY (`MaQuyen`,`MaThanhVien`),
  KEY `MaThanhVien_Quyen_idx` (`MaThanhVien`),
  KEY `MaQuyen_idx` (`MaQuyen`),
  CONSTRAINT `MaQuyen` FOREIGN KEY (`MaQuyen`) REFERENCES `quyen` (`MaQuyen`),
  CONSTRAINT `MaThanhVien_Quyen` FOREIGN KEY (`MaThanhVien`) REFERENCES `thanh_vien` (`MaThanhVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quyen_cua_thanh_vien`
--

LOCK TABLES `quyen_cua_thanh_vien` WRITE;
/*!40000 ALTER TABLE `quyen_cua_thanh_vien` DISABLE KEYS */;
INSERT INTO `quyen_cua_thanh_vien` VALUES (1,1),(2,2),(3,3),(2,6),(3,9),(3,15);
/*!40000 ALTER TABLE `quyen_cua_thanh_vien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tham_gia_du_an`
--

DROP TABLE IF EXISTS `tham_gia_du_an`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tham_gia_du_an` (
  `MaThanhVien` int NOT NULL,
  `MaDuAn` int NOT NULL,
  `MaVaiTroThamGiaDA` int DEFAULT NULL,
  `NgayThamGia` datetime DEFAULT NULL,
  `CacDongGopKhac` varchar(200) DEFAULT NULL,
  `SoTienDongGop` double DEFAULT NULL,
  `NgayTao` datetime DEFAULT NULL,
  `NgayCapNhat` datetime DEFAULT NULL,
  PRIMARY KEY (`MaThanhVien`,`MaDuAn`),
  KEY `MaThanhVien_ThamGia_idx` (`MaThanhVien`),
  KEY `MaDuAn_ThamGia_idx` (`MaDuAn`),
  KEY `MaVaitroThamGiaDuAn_idx` (`MaVaiTroThamGiaDA`),
  CONSTRAINT `MaDuAn_ThamGia` FOREIGN KEY (`MaDuAn`) REFERENCES `du_an_tu_thien` (`MaDuAn`),
  CONSTRAINT `MaThanhVien_ThamGia` FOREIGN KEY (`MaThanhVien`) REFERENCES `thanh_vien` (`MaThanhVien`),
  CONSTRAINT `MaVaitroThamGiaDuAn` FOREIGN KEY (`MaVaiTroThamGiaDA`) REFERENCES `vai_tro_tham_gia_da` (`MaVaiTroThamGiaDA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tham_gia_du_an`
--

LOCK TABLES `tham_gia_du_an` WRITE;
/*!40000 ALTER TABLE `tham_gia_du_an` DISABLE KEYS */;
INSERT INTO `tham_gia_du_an` VALUES (1,13,3,NULL,'fasd',40000,'2024-05-18 00:21:30',NULL),(2,5,3,NULL,'Cufd',88000,'2024-04-27 20:50:03','2024-05-17 23:44:15'),(2,9,3,NULL,'Lúa mì',70000,'2023-10-12 15:19:38',NULL),(2,12,3,NULL,'fsda',0,'2024-05-18 00:13:41',NULL),(3,5,3,NULL,'lúa',80000,'2024-05-11 19:36:09',NULL),(3,12,3,NULL,'Huy hiệu',40000,'2023-10-12 22:11:43',NULL),(3,13,1,NULL,'lúa',20000,'2024-05-11 22:15:14','2024-05-11 22:16:59'),(6,9,3,NULL,'Hạt gạo làng ta',123213,'2023-10-10 21:37:10',NULL),(6,12,1,NULL,'Bia lon',400000,'2023-10-11 22:30:40','2023-10-12 21:50:36'),(6,13,3,NULL,'Bia',3000000,'2023-10-19 21:23:25','2023-10-19 21:37:08');
/*!40000 ALTER TABLE `tham_gia_du_an` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thanh_vien`
--

DROP TABLE IF EXISTS `thanh_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thanh_vien` (
  `MaThanhVien` int NOT NULL AUTO_INCREMENT,
  `TenDangNhap` varchar(45) NOT NULL,
  `MatKhau` varchar(100) NOT NULL,
  `SoDienThoai` varchar(12) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Ho` varchar(45) DEFAULT NULL,
  `Ten` varchar(45) DEFAULT NULL,
  `Tuoi` int DEFAULT NULL,
  `GioiTinh` tinyint DEFAULT NULL,
  `NgaySinh` datetime DEFAULT NULL,
  `DiaChi` varchar(100) DEFAULT NULL,
  `AnhDaiDien` varchar(200) DEFAULT NULL,
  `TongTien` double DEFAULT NULL,
  `NgayTao` datetime DEFAULT NULL,
  `NgayCapNhat` datetime DEFAULT NULL,
  PRIMARY KEY (`MaThanhVien`),
  UNIQUE KEY `TenDangNhap_UNIQUE` (`TenDangNhap`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thanh_vien`
--

LOCK TABLES `thanh_vien` WRITE;
/*!40000 ALTER TABLE `thanh_vien` DISABLE KEYS */;
INSERT INTO `thanh_vien` VALUES (1,'vuongthai','$2a$10$OtOn4kgNWLYpM86PfKSOSu9oMUgBqFjEjRarHggpmgU9FZqk5f83O','09102939','vuong.codeweb@gmail.com','Thái','Vương Đẹp',NULL,NULL,NULL,'','https://res.cloudinary.com/dibzyjddx/image/upload/v1694095329/578dfc9754d5dcca9fb11f74c940eb29.png',143000,NULL,'2024-05-18 00:21:30'),(2,'thaigiavuong','$2a$10$6QnsiKWmXrcjFHLi/Yu5uOGbFjoIb9W5/DrhkiqSrJJM1utQ2HpXW','098989899','vuongpro1205@gmail.com','Thái','Vương',NULL,1,'2002-02-27 00:00:00','Bình Định','https://res.cloudinary.com/dibzyjddx/image/upload/v1694095650/d42772e9f8c2f4277cf8a89585ef0725.png',NULL,'2023-09-16 19:02:28',NULL),(3,'khachhang','$2a$10$6QnsiKWmXrcjFHLi/Yu5uOGbFjoIb9W5/DrhkiqSrJJM1utQ2HpXW','091029331','2051010367vuong@ou.edu.vn','Hàng','Khách',NULL,1,'2004-02-13 00:00:00','Gia Lai','https://res.cloudinary.com/dibzyjddx/image/upload/v1694095650/d42772e9f8c2f4277cf8a89585ef0725.png',160000,'2023-09-17 00:00:00','2024-05-12 17:22:48'),(6,'giavuong','$2a$10$634RkqDZ8iPSK9yuh3nOHugUySjNPrjY1ZcRL1h0Ad1tfjalkGREe','0989898998','giavuong.1205@gmail.com','Thái','Vương Đẹp',NULL,1,NULL,',','https://res.cloudinary.com/dibzyjddx/image/upload/v1694961090/7bcd43976976b75f2de0e060caa0531d.jpg',270000,'2023-09-17 00:00:00','2024-05-12 17:18:03'),(9,'vuonghoctap','$2a$10$7MvjyPfplcWZzSYaS5ufC.m5tSOvGCCYz2sn9HWFYWJO4ldvNd7Xi','9809809809','vuonghoctap@gmail.com','Thái','Vương',NULL,0,NULL,'','https://firebasestorage.googleapis.com/v0/b/mangxahoituthien.appspot.com/o/avatars%2Ffavicon_192x192_created_by_logaster-1-150x150.png?alt=media&token=a5e0da76-b746-493f-a52a-f8f55167c053',NULL,'2023-10-14 00:00:00','2023-10-21 15:23:57'),(15,'nguyenvuong','$2a$10$2vvk19274moMz6HLBeWefe.bOKtgnVnLoefvtZKR0RQzKUQAii1G2','0920902901','giavuongthai@gmail.com','Nguyen','Vuong',NULL,NULL,NULL,NULL,'https://firebasestorage.googleapis.com/v0/b/mangxahoituthien.appspot.com/o/avatars%2Fthuytien-1.jpg?alt=media&token=001f68ee-939e-4eb7-83bc-7b9c03851212',NULL,'2023-10-21 18:57:43',NULL);
/*!40000 ALTER TABLE `thanh_vien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trang_thai_dau_gia`
--

DROP TABLE IF EXISTS `trang_thai_dau_gia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trang_thai_dau_gia` (
  `MaTrangThaiDauGia` int NOT NULL AUTO_INCREMENT,
  `TenTrangThai` varchar(45) NOT NULL,
  PRIMARY KEY (`MaTrangThaiDauGia`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trang_thai_dau_gia`
--

LOCK TABLES `trang_thai_dau_gia` WRITE;
/*!40000 ALTER TABLE `trang_thai_dau_gia` DISABLE KEYS */;
INSERT INTO `trang_thai_dau_gia` VALUES (1,'Không đấu giá'),(2,'Đang đấu giá'),(3,'Kết thúc đấu giá');
/*!40000 ALTER TABLE `trang_thai_dau_gia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tv_binh_luan_bv`
--

DROP TABLE IF EXISTS `tv_binh_luan_bv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tv_binh_luan_bv` (
  `MaBinhLuan` int NOT NULL AUTO_INCREMENT,
  `MaThanhVien` int NOT NULL,
  `MaBaiViet` int NOT NULL,
  `NoiDung` text,
  `NgayTao` datetime DEFAULT NULL,
  `NgayCapNhat` datetime DEFAULT NULL,
  PRIMARY KEY (`MaBinhLuan`),
  KEY `MaThanhVien_BinhLuan_idx` (`MaThanhVien`),
  KEY `MaBaiViet_BinhLuan_idx` (`MaBaiViet`),
  CONSTRAINT `MaBaiViet_BinhLuan` FOREIGN KEY (`MaBaiViet`) REFERENCES `bai_viet` (`MaBaiViet`),
  CONSTRAINT `MaThanhVien_BinhLuan` FOREIGN KEY (`MaThanhVien`) REFERENCES `thanh_vien` (`MaThanhVien`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tv_binh_luan_bv`
--

LOCK TABLES `tv_binh_luan_bv` WRITE;
/*!40000 ALTER TABLE `tv_binh_luan_bv` DISABLE KEYS */;
INSERT INTO `tv_binh_luan_bv` VALUES (1,1,1,'Rất ý nghĩa ạ 1','2023-09-21 22:29:49','2023-09-21 22:30:21'),(3,3,1,'Rất tuyệt ạ','2023-10-12 22:22:02',NULL),(7,1,6,'đỉnh','2023-10-22 19:06:02',NULL);
/*!40000 ALTER TABLE `tv_binh_luan_bv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tv_binh_luan_da`
--

DROP TABLE IF EXISTS `tv_binh_luan_da`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tv_binh_luan_da` (
  `MaBinhLuan` int NOT NULL AUTO_INCREMENT,
  `MaThanhVien` int NOT NULL,
  `MaDuAn` int NOT NULL,
  `NoiDung` text,
  `TrangThai` int DEFAULT NULL,
  `NgayTao` datetime DEFAULT NULL,
  `NgayCapNhat` datetime DEFAULT NULL,
  PRIMARY KEY (`MaBinhLuan`),
  KEY `MaThanhVienBinhLuanDuAn_idx` (`MaThanhVien`),
  KEY `MaDuAnBinhLuanDuAn_idx` (`MaDuAn`),
  CONSTRAINT `MaDuAnBinhLuanDuAn` FOREIGN KEY (`MaDuAn`) REFERENCES `du_an_tu_thien` (`MaDuAn`),
  CONSTRAINT `MaThanhVienBinhLuanDuAn` FOREIGN KEY (`MaThanhVien`) REFERENCES `thanh_vien` (`MaThanhVien`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tv_binh_luan_da`
--

LOCK TABLES `tv_binh_luan_da` WRITE;
/*!40000 ALTER TABLE `tv_binh_luan_da` DISABLE KEYS */;
INSERT INTO `tv_binh_luan_da` VALUES (2,1,1,'mong là được',NULL,'2023-10-15 22:34:49',NULL),(4,1,13,'ý nghĩa quá hih',NULL,'2023-10-19 22:24:23','2024-04-15 16:07:34'),(5,1,13,'okoko',NULL,'2024-04-07 18:38:54','2024-04-15 16:07:43'),(6,1,13,'moi',NULL,'2024-04-15 16:16:51',NULL),(7,1,1,'Rất ý nghĩa ạ 1',NULL,'2024-04-15 23:32:03',NULL),(8,1,1,'Rất ý nghĩa ạ 1',NULL,'2024-04-15 23:32:48',NULL),(9,1,1,'Rất ý nghĩa ạ hihi1',NULL,'2024-04-15 23:34:44',NULL),(10,1,1,'Rất ý nghĩa ạ hihi1',NULL,'2024-04-15 23:34:55',NULL),(11,1,13,'hihihi',NULL,'2024-04-16 23:17:16',NULL),(12,1,13,'huhuhuikik',NULL,'2024-04-16 23:26:47','2024-04-17 17:53:10'),(13,1,5,'huhu',0,'2024-04-22 22:58:08',NULL),(14,1,12,'mong laf dduocjw',0,'2024-04-22 23:00:05',NULL),(15,1,12,'tuyet',1,'2024-04-22 23:10:15',NULL),(16,1,13,'tuyệt',1,'2024-04-23 21:05:36',NULL),(17,1,5,'truyể',0,'2024-04-23 21:46:10',NULL),(18,1,13,'haydf',1,'2024-04-23 21:56:47','2024-04-26 15:47:37'),(19,1,13,'hay',0,'2024-04-23 21:57:04','2024-05-19 15:41:15'),(21,1,5,'fdsa',1,'2024-04-27 20:49:10',NULL),(22,3,13,'2 hình ',1,'2024-05-11 19:27:43',NULL),(23,3,13,'fasd',0,'2024-05-11 19:28:06',NULL),(26,1,13,'<p>hay</p>\n',0,'2024-05-17 23:28:45',NULL);
/*!40000 ALTER TABLE `tv_binh_luan_da` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tv_thich_bv`
--

DROP TABLE IF EXISTS `tv_thich_bv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tv_thich_bv` (
  `MaBaiViet` int NOT NULL,
  `MaThanhVien` int NOT NULL,
  `DaThich` tinyint DEFAULT NULL,
  `NgayTao` datetime DEFAULT NULL,
  `NgayCapNhat` datetime DEFAULT NULL,
  PRIMARY KEY (`MaBaiViet`,`MaThanhVien`),
  KEY `MaThanhVien_thich_idx` (`MaThanhVien`),
  CONSTRAINT `MaBaiViet_thich` FOREIGN KEY (`MaBaiViet`) REFERENCES `bai_viet` (`MaBaiViet`),
  CONSTRAINT `MaThanhVien_thich` FOREIGN KEY (`MaThanhVien`) REFERENCES `thanh_vien` (`MaThanhVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tv_thich_bv`
--

LOCK TABLES `tv_thich_bv` WRITE;
/*!40000 ALTER TABLE `tv_thich_bv` DISABLE KEYS */;
INSERT INTO `tv_thich_bv` VALUES (1,1,1,NULL,'2023-09-21 16:01:33'),(1,3,1,'2023-10-12 22:21:52',NULL),(6,1,1,'2023-10-06 18:00:57',NULL),(6,3,1,'2023-10-12 22:21:50',NULL);
/*!40000 ALTER TABLE `tv_thich_bv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vai_tro_tham_gia_da`
--

DROP TABLE IF EXISTS `vai_tro_tham_gia_da`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vai_tro_tham_gia_da` (
  `MaVaiTroThamGiaDA` int NOT NULL AUTO_INCREMENT,
  `TenVaiTro` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`MaVaiTroThamGiaDA`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vai_tro_tham_gia_da`
--

LOCK TABLES `vai_tro_tham_gia_da` WRITE;
/*!40000 ALTER TABLE `vai_tro_tham_gia_da` DISABLE KEYS */;
INSERT INTO `vai_tro_tham_gia_da` VALUES (1,'Trưởng dự án'),(2,'Phó dự án'),(3,'Thành Viên');
/*!40000 ALTER TABLE `vai_tro_tham_gia_da` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-19 18:26:01
