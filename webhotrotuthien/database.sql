-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bai_viet`
--

LOCK TABLES `bai_viet` WRITE;
/*!40000 ALTER TABLE `bai_viet` DISABLE KEYS */;
INSERT INTO `bai_viet` VALUES (1,1,'Chương trình từ thiện đặc biệt ngày hôm qua','Ngày 1/8, hình ảnh Top 3 Miss World Vietnam 2023 trao quà, thăm hỏi bệnh nhân ở một bệnh viện tư tại TP.HCM được chia sẻ, lan truyền trên mạng xã hội gây xôn xao.',40000,2,NULL,NULL,NULL,'2023-10-08 23:04:40'),(6,1,'Từ thiện đêm trung thu','Nội dung hôm nay tuyệt vời',50000,2,'2023-10-06 07:00:00','2023-10-20 07:00:00',NULL,'2023-10-06 21:26:10');
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
INSERT INTO `bao_cao_thanh_vien` VALUES (1,2,'Ngu','2023-10-12 23:05:03',NULL),(1,2,'Oke','2023-10-14 09:07:35',NULL),(1,3,'Bài viết lừa đảo','2023-10-14 15:26:12',NULL),(1,3,'Quấy rối','2023-10-14 23:04:45',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `du_an_tu_thien`
--

LOCK TABLES `du_an_tu_thien` WRITE;
/*!40000 ALTER TABLE `du_an_tu_thien` DISABLE KEYS */;
INSERT INTO `du_an_tu_thien` VALUES (1,1,'Dự Án Từ thiện ngày hôm nay','Đi xây cầu tại bình định',NULL,NULL,'Bình Định',190000,1,NULL,'2023-10-21 14:53:27'),(5,1,'Dự án từ thiện đặc biệt tại Gia Lai','Hỗ trợ các em nhỏ miền núi',NULL,NULL,'Gia Lai',100000,1,NULL,'2023-10-21 14:40:28'),(6,1,'Dự án từ thiện đặc biệt tại Mỹ','Hỗ trợ các em nhỏ miền núi',NULL,NULL,'Gia Lai',8000000,0,'2023-10-05 00:00:00','2023-10-22 17:14:07'),(9,1,'Dự án đặc biệt từ thiện','Đi xây dựng hồ bơi','2023-10-08 00:00:00','2023-10-26 00:00:00','Bình Định',1000000,1,'2023-10-06 00:00:00','2023-10-22 17:14:27'),(12,1,'Dự án từ thiện gây quỹ trẻ em','Mục Đích hoàn hảo','2023-10-09 07:00:00','2023-10-18 07:00:00','địa chỉ tuyệt vời',1460000,1,'2023-10-08 00:00:00','2023-10-22 17:31:47'),(13,2,'Dự án từ thiện ngày 15/10','Đi xây dựng cầu bình triệu','2023-10-16 00:00:00','2023-10-24 00:00:00','An Giang',1230000,1,'2023-10-15 00:00:00','2023-10-22 17:15:41');
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
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hinh_anh_bai_viet`
--

LOCK TABLES `hinh_anh_bai_viet` WRITE;
/*!40000 ALTER TABLE `hinh_anh_bai_viet` DISABLE KEYS */;
INSERT INTO `hinh_anh_bai_viet` VALUES (76,6,'https://res.cloudinary.com/dibzyjddx/image/upload/v1696433538/f0798d90a6d1bc8d9c06725f6572d039.jpg','2023-10-06 21:26:10',NULL),(77,6,'https://res.cloudinary.com/dibzyjddx/image/upload/v1696433540/700c5fa1e4eaeb34ec97e09f8a8ada02.jpg','2023-10-06 21:26:10',NULL),(78,6,'https://res.cloudinary.com/dibzyjddx/image/upload/v1696433542/0aa358eaaa70278a7351a9ba0a213699.jpg','2023-10-06 21:26:10',NULL),(82,1,'https://firebasestorage.googleapis.com/v0/b/mangxahoituthien.appspot.com/o/images%2F1674032207_anh-tu-thien(1).jpeg?alt=media&token=5f641b3b-c409-4251-b427-e2dcbcfe9204','2023-10-08 23:04:40',NULL),(83,1,'https://firebasestorage.googleapis.com/v0/b/mangxahoituthien.appspot.com/o/images%2Fthuytien-1.jpg?alt=media&token=d7997f0a-e6a6-4b42-9f63-8454c51d3f94','2023-10-08 23:04:40',NULL);
/*!40000 ALTER TABLE `hinh_anh_bai_viet` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hinh_anh_du_an`
--

LOCK TABLES `hinh_anh_du_an` WRITE;
/*!40000 ALTER TABLE `hinh_anh_du_an` DISABLE KEYS */;
INSERT INTO `hinh_anh_du_an` VALUES (89,1,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697874002/c1e1bc5539c6ce6dc248499d86982f29.jpg','2023-10-21 14:40:01',NULL),(90,1,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697874004/3403f8ea547fc675c554da6dc0a4b09b.jpg','2023-10-21 14:40:04',NULL),(91,1,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697874006/805b6dea5b851881dbd5b5817273b1ac.jpg','2023-10-21 14:40:06',NULL),(92,5,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697874002/c1e1bc5539c6ce6dc248499d86982f29.jpg','2023-10-21 14:40:26',NULL),(93,5,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697874006/805b6dea5b851881dbd5b5817273b1ac.jpg','2023-10-21 14:40:28',NULL),(94,6,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697969643/f4baba75c1bebf7f5a0a089702cc18da.jpg','2023-10-22 17:14:04',NULL),(95,6,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697969645/7b5d888e7cbe2235bf1f71295b116c1e.jpg','2023-10-22 17:14:06',NULL),(96,6,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697969646/761146c479f47aa7c199770a10f4c60c.jpg','2023-10-22 17:14:07',NULL),(97,9,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697969643/f4baba75c1bebf7f5a0a089702cc18da.jpg','2023-10-22 17:14:24',NULL),(98,9,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697969645/7b5d888e7cbe2235bf1f71295b116c1e.jpg','2023-10-22 17:14:26',NULL),(99,9,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697969646/761146c479f47aa7c199770a10f4c60c.jpg','2023-10-22 17:14:27',NULL),(103,13,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697969645/7b5d888e7cbe2235bf1f71295b116c1e.jpg','2023-10-22 17:15:37',NULL),(104,13,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697874002/c1e1bc5539c6ce6dc248499d86982f29.jpg','2023-10-22 17:15:39',NULL),(105,13,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697874004/3403f8ea547fc675c554da6dc0a4b09b.jpg','2023-10-22 17:15:41',NULL),(106,12,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697969682/39ad992c064eac9ef81fe24e269ac5fc.jpg','2023-10-22 17:31:47',NULL),(107,12,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697969684/eb9bc0551623be2fbe3b4869b2839a32.jpg','2023-10-22 17:31:47',NULL),(108,12,'https://res.cloudinary.com/dibzyjddx/image/upload/v1697874006/805b6dea5b851881dbd5b5817273b1ac.jpg','2023-10-22 17:31:47',NULL);
/*!40000 ALTER TABLE `hinh_anh_du_an` ENABLE KEYS */;
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
INSERT INTO `tham_gia_du_an` VALUES (1,13,3,NULL,'Lúa gạo',10000,'2023-10-19 20:52:47',NULL),(2,9,3,NULL,'Lúa mì',70000,'2023-10-12 15:19:38',NULL),(2,12,3,NULL,'Góp tiền mỹ',120000,'2023-10-12 21:51:07',NULL),(3,12,3,NULL,'Huy hiệu',40000,'2023-10-12 22:11:43',NULL),(3,13,3,NULL,'Coca cola',20000,'2023-10-19 21:44:32',NULL),(6,9,3,NULL,'Hạt gạo làng ta',123213,'2023-10-10 21:37:10',NULL),(6,12,1,NULL,'Bia lon',400000,'2023-10-11 22:30:40','2023-10-12 21:50:36'),(6,13,3,NULL,'Bia',3000000,'2023-10-19 21:23:25','2023-10-19 21:37:08');
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
INSERT INTO `thanh_vien` VALUES (1,'vuongthai','$2a$10$OtOn4kgNWLYpM86PfKSOSu9oMUgBqFjEjRarHggpmgU9FZqk5f83O','09102939','vuong.codeweb@gmail.com','Thái','Vương Đẹp',NULL,NULL,NULL,'','https://res.cloudinary.com/dibzyjddx/image/upload/v1694095329/578dfc9754d5dcca9fb11f74c940eb29.png',NULL,'2023-10-12 22:08:54'),(2,'thaigiavuong','$2a$10$6QnsiKWmXrcjFHLi/Yu5uOGbFjoIb9W5/DrhkiqSrJJM1utQ2HpXW','098989899','vuongpro1205@gmail.com','Thái','Vương',NULL,1,'2002-02-27 00:00:00','Bình Định','https://res.cloudinary.com/dibzyjddx/image/upload/v1694095650/d42772e9f8c2f4277cf8a89585ef0725.png','2023-09-16 19:02:28',NULL),(3,'khachhang','$2a$10$6QnsiKWmXrcjFHLi/Yu5uOGbFjoIb9W5/DrhkiqSrJJM1utQ2HpXW','091029331','2051010367vuong@ou.edu.vn','Hàng','Khách',NULL,1,'2004-02-13 00:00:00','Gia Lai','https://res.cloudinary.com/dibzyjddx/image/upload/v1694095650/d42772e9f8c2f4277cf8a89585ef0725.png','2023-09-17 00:00:00','2023-10-21 14:22:46'),(6,'giavuong','$2a$10$634RkqDZ8iPSK9yuh3nOHugUySjNPrjY1ZcRL1h0Ad1tfjalkGREe','0989898998','giavuong.1205@gmail.com','Thái','Vương Đẹp',NULL,1,NULL,',','https://res.cloudinary.com/dibzyjddx/image/upload/v1694961090/7bcd43976976b75f2de0e060caa0531d.jpg','2023-09-17 00:00:00','2023-10-21 15:23:46'),(9,'vuonghoctap','$2a$10$7MvjyPfplcWZzSYaS5ufC.m5tSOvGCCYz2sn9HWFYWJO4ldvNd7Xi','9809809809','vuonghoctap@gmail.com','Thái','Vương',NULL,0,NULL,'','https://firebasestorage.googleapis.com/v0/b/mangxahoituthien.appspot.com/o/avatars%2Ffavicon_192x192_created_by_logaster-1-150x150.png?alt=media&token=a5e0da76-b746-493f-a52a-f8f55167c053','2023-10-14 00:00:00','2023-10-21 15:23:57'),(15,'nguyenvuong','$2a$10$2vvk19274moMz6HLBeWefe.bOKtgnVnLoefvtZKR0RQzKUQAii1G2','0920902901','giavuongthai@gmail.com','Nguyen','Vuong',NULL,NULL,NULL,NULL,'https://firebasestorage.googleapis.com/v0/b/mangxahoituthien.appspot.com/o/avatars%2Fthuytien-1.jpg?alt=media&token=001f68ee-939e-4eb7-83bc-7b9c03851212','2023-10-21 18:57:43',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `NgayTao` datetime DEFAULT NULL,
  `NgayCapNhat` datetime DEFAULT NULL,
  PRIMARY KEY (`MaBinhLuan`),
  KEY `MaThanhVienBinhLuanDuAn_idx` (`MaThanhVien`),
  KEY `MaDuAnBinhLuanDuAn_idx` (`MaDuAn`),
  CONSTRAINT `MaDuAnBinhLuanDuAn` FOREIGN KEY (`MaDuAn`) REFERENCES `du_an_tu_thien` (`MaDuAn`),
  CONSTRAINT `MaThanhVienBinhLuanDuAn` FOREIGN KEY (`MaThanhVien`) REFERENCES `thanh_vien` (`MaThanhVien`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tv_binh_luan_da`
--

LOCK TABLES `tv_binh_luan_da` WRITE;
/*!40000 ALTER TABLE `tv_binh_luan_da` DISABLE KEYS */;
INSERT INTO `tv_binh_luan_da` VALUES (2,1,1,'mong là được','2023-10-15 22:34:49',NULL),(3,1,13,'hạnh phúc quá','2023-10-19 20:51:09','2023-10-22 21:59:17'),(4,1,13,'ý nghĩa quá','2023-10-19 22:24:23','2023-10-22 21:59:09');
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

-- Dump completed on 2023-10-23 14:18:40
