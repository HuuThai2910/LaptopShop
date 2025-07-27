CREATE DATABASE IF NOT EXISTS `laptopshop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci    */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `laptopshop`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: laptopshop
-- ------------------------------------------------------
-- Server version       8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40101 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
--
-- Table structure for table `products`
--
DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `detail_desc` mediumtext NOT NULL,
                            `factory` varchar(255) DEFAULT NULL,
                            `image` varchar(255) DEFAULT NULL,
                            `name` varchar(255) NOT NULL,
                            `price` double NOT NULL,
                            `quantity` bigint NOT NULL,
                            `short_desc` varchar(255) NOT NULL,
                            `sold` bigint NOT NULL,
                            `target` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            CONSTRAINT `products_chk_1` CHECK ((`quantity` >= 1))
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `products`
--


LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES
                           (1,'ASUS TUF Gaming F15 FX506HF HN017W là chiếc laptop gaming giá rẻ nhưng vô cùng mạnh mẽ. Không chỉ bộ vi xử lý Intel thế hệ thứ 11, card đồ họa RTX 20 series mà điểm mạnh còn đến từ việc trang bị sẵn 16GB RAM, cho bạn hiệu năng xuất sắc mà không cần nâng cấp máy.','ASUS','1711078092373-asus-01.png','Laptop Asus TUF Gaming',17490000,100,'Intel Core i5 11400H', 0,'GAMING'),
                           (2,'Khám phá sức mạnh tối ưu từ Dell Inspiron 15 N3520, chiếc laptop có cấu hình cực mạnh với bộ vi xử lý Intel Core i5 1235U thế hệ mới và dung lượng RAM tới 16GB. Bạn có thể thoải mái xử lý nhiều tác vụ, nâng cao hiệu suất trong công việc mà không gặp bất kỳ trở ngại nào.','DELL','1711078452562-dell-01.png','Laptop Dell Inspiron 15',14290000,96,'15.6 inch FHD (1920x1080) 512GB/16GB', 0,'FHD'),
                           (3,'Bên cạnh kiểu dáng trang nhã, thời thượng một sản phẩm gaming thế hệ mới với hiệu năng mạnh mẽ, thiết kế tối giản, lịch lãm phù hợp cho học tập lẫn chơi game. Tản nhiệt mát mẻ với hệ thống quạt kép kiểm soát được nhiệt độ máy luôn mát mẻ kể cả chơi game.','LENOVO','1711079073759-lenovo-01.png','Lenovo IdeaPad Gaming 3',19500000,150,'i5-10300H, RAM 8GB', 0,'GAMING'),
                           (4,'Tận hưởng âm thanh sống động với âm lượng loa lớn cùng dàn điều chỉnh hàng ngày của dòng máy tính xách tay ASUS K Series sở hữu thiết kế tối ưu, sang trọng. Những mong đợi với lớp vỏ hợp kim tiết giảm kim loại phong cách. Hiệu năng của máy rất mạnh mẽ nhờ trang bị bộ vi xử lý Intel Core™ thế hệ thứ 12, hỗ trợ đa nhiệm mượt mà nhất. Bên cạnh đó, người dùng yêu thích thương hiệu ASUS thu hút bởi kiểu dáng thiết kế sang trọng, bắt mắt.','ASUS','1711079496499-asus-02.png','Asus K501UX',11900000,99,'VGA NVIDIA GTX 950M-4G', 0,'THIET-KE-DO-HOA'),
                           (5,'Chiếc MacBook Air có hiệu năng tốt nhất từ trước tới giờ. Đây là chiếc máy Mac hoàn toàn mới cho năm 2022 với thiết kế mỏng nhẹ đáng kể, màn hình lớn hơn, camera tốt hơn và thời lượng pin dài hơn đáng kể.','APPLE','1711079594099-apple-01.png','MacBook Air 13',17500000,99,'Apple M1 GPU 7 nhân', 0,'GAMING'),
                           (6,'14 inch (2880 x 1800) 120Hz, 500 nits, OLED, 99 Hz, OLED','LG','1711083386941-lg-01.png','Laptop LG Gram Style',31490000,99,'Intel Iris Plus Graphics', 0,'DOANH-NHAN'),
                           (7,'Không chỉ mỏng nhẹ thời thượng với thiết kế, MacBook đời mới 2022 còn được trang bị chip M2 siêu mạnh, thời lượng pin gần như gấp đôi, màn hình Retina sống động, hệ thống camera kết hợp cùng âm thanh tận tình.','APPLE','1711087787179-apple-02.png','MacBook Air 13 M2',24990000,99,'Apple M2 GPU 8 nhân', 0,'MONG-NHE'),
                           (8,'Mỏng nhẹ nhưng mạnh mẽ, hiệu năng vượt trội đến từ Acer Nitro Gaming AN515-58-7691 này còn là ưu điểm khi xử lý Intel Core i7 12700H cực khỏe và card đồ họa RTX 3060, sẵn sàng cùng bạn chinh phục những đỉnh cao.','ACER','1711089849777-acer-01.png','Acer Nitro 5',28900000,99,'Acer i7 12700H', 0,'SHINH-VANPHONG'),
                           (9,'15.6 inches, FHD (1920 x 1080), IPS, 144 Hz, 250 nits, Acer ComfyView LED-backlit','ASUS','1711088108930-asus-03.png','Laptop Acer Nitro V',26990000,99,'NVIDIA GeForce RTX 4050', 0,'MONG-NHE'),
                           (10,'Dell Inspiron N3520 là chiếc laptop lý tưởng cho các sinh viên học tập, với bộ vi xử lý Intel Core i5 thế hệ thứ 12 hiệu suất cao, màn hình Full HD 120Hz mượt mà, thiết kế bền sẽ giúp bạn giải quyết công việc nhanh chóng một cách hiệu quả.','DELL','1711081278418-dell-02.png','Laptop Dell Latitude 3420',21390000,99,'Intel Iris Xe Graphics', 0,'MONG-NHE');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!140101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
-- Dump completed on 2024-03-22 12:37:00

DELETE FROM users WHERE users.id = 4 or users.id = 5 or users.id = 7;