-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.30 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for mobileshop
CREATE DATABASE IF NOT EXISTS `mobileshop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mobileshop`;

-- Dumping structure for table mobileshop.brand
CREATE TABLE IF NOT EXISTS `brand` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` bigint DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `status` bit(1) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mobileshop.brand: ~3 rows (approximately)
INSERT INTO `brand` (`id`, `created_by`, `created_date`, `name`, `status`, `updated_by`, `updated_date`) VALUES
	(1, NULL, NULL, 'Apple', b'1', NULL, NULL),
	(2, NULL, NULL, 'Samsung', b'1', NULL, NULL),
	(3, NULL, NULL, 'Xiaomi', b'1', NULL, NULL);

-- Dumping structure for table mobileshop.category
CREATE TABLE IF NOT EXISTS `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` bigint DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `status` bit(1) NOT NULL,
  `updated_by` bigint DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mobileshop.category: ~3 rows (approximately)
INSERT INTO `category` (`id`, `created_by`, `created_date`, `name`, `status`, `updated_by`, `updated_date`) VALUES
	(1, NULL, NULL, 'Điện thoại', b'1', NULL, NULL),
	(2, NULL, NULL, 'Máy tính bảng', b'1', NULL, NULL),
	(3, NULL, NULL, 'Phụ kiện', b'1', NULL, NULL);

-- Dumping structure for table mobileshop.images
CREATE TABLE IF NOT EXISTS `images` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image` varchar(255) DEFAULT NULL,
  `is_view` bit(1) DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8sfun6tj1hqb85ed52o8mkqyh` (`product_id`),
  CONSTRAINT `FK8sfun6tj1hqb85ed52o8mkqyh` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mobileshop.images: ~9 rows (approximately)
INSERT INTO `images` (`id`, `image`, `is_view`, `product_id`) VALUES
	(1, '/images/ImagesUpload/ssgs23ultra.png', b'1', 1),
	(2, '/images/ImagesUpload/iphone14pm.png', b'1', 1),
	(3, '/images/ImagesUpload/iphone8.png', b'1', 1),
	(4, '/images/ImagesUpload/ssgs23ultra.png', b'1', 2),
	(5, '/images/ImagesUpload/iphone14pm.png', b'1', 2),
	(6, '/images/ImagesUpload/iphone8.png', b'1', 2),
	(7, '/images/ImagesUpload/ssgs23ultra.png', b'1', 3),
	(8, '/images/ImagesUpload/iphone14pm.png', b'1', 3),
	(9, '/images/ImagesUpload/iphone8.png', b'1', 3);

-- Dumping structure for table mobileshop.invoices
CREATE TABLE IF NOT EXISTS `invoices` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `invoice_date` datetime(6) DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mobileshop.invoices: ~0 rows (approximately)

-- Dumping structure for table mobileshop.item_invoice
CREATE TABLE IF NOT EXISTS `item_invoice` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `invoice_id` bigint DEFAULT NULL,
  `book_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8ot5g5sufecb8f778asmytul4` (`invoice_id`),
  KEY `FKp0750de5dsemyl2g46j0e6s4l` (`book_id`),
  CONSTRAINT `FK8ot5g5sufecb8f778asmytul4` FOREIGN KEY (`invoice_id`) REFERENCES `invoices` (`id`),
  CONSTRAINT `FKp0750de5dsemyl2g46j0e6s4l` FOREIGN KEY (`book_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mobileshop.item_invoice: ~0 rows (approximately)

-- Dumping structure for table mobileshop.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_date` datetime(6) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`),
  CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mobileshop.orders: ~0 rows (approximately)

-- Dumping structure for table mobileshop.order_detail
CREATE TABLE IF NOT EXISTS `order_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `orders_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7xf2gmq3yok90kilflnu8aa7e` (`orders_id`),
  KEY `FKb8bg2bkty0oksa3wiq5mp5qnc` (`product_id`),
  CONSTRAINT `FK7xf2gmq3yok90kilflnu8aa7e` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FKb8bg2bkty0oksa3wiq5mp5qnc` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mobileshop.order_detail: ~0 rows (approximately)

-- Dumping structure for table mobileshop.product
CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `capacity` int DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `decription` varchar(255) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `discount` int DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `brand_id` bigint DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  `supplier_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs6cydsualtsrprvlf2bb3lcam` (`brand_id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  KEY `FK2kxvbr72tmtscjvyp9yqb12by` (`supplier_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FK2kxvbr72tmtscjvyp9yqb12by` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`),
  CONSTRAINT `FKs6cydsualtsrprvlf2bb3lcam` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mobileshop.product: ~11 rows (approximately)
INSERT INTO `product` (`id`, `capacity`, `color`, `created_by`, `created_date`, `decription`, `detail`, `discount`, `image`, `name`, `price`, `quantity`, `status`, `updated_by`, `updated_date`, `brand_id`, `category_id`, `supplier_id`) VALUES
	(1, 16, 'black', 1, '2023-06-22 13:47:12.000000', 'test', 'test', 0, '/images/ImagesUpload/iphone8.png', 'iphone8', 20000000, 10, b'1', 1, '2023-06-28 13:47:12.000000', 1, 1, 1),
	(2, 128, 'Vàng', NULL, '2023-06-18 21:16:58.000000', NULL, NULL, 20, '/images/ImagesUpload/iphone14pm.png', 'Iphone 14 Pro Max', 26750000, 1, b'1', NULL, NULL, 1, 1, 2),
	(3, 128, 'Đen', NULL, '2023-06-18 21:15:52.000000', NULL, NULL, 20, '/images/ImagesUpload/14pro.png', 'Iphone 14 Pro', 24690000, 1, b'1', NULL, NULL, 1, 1, 2),
	(4, 128, 'Xanh ngọc', NULL, '2023-06-18 21:17:54.000000', NULL, NULL, 15, '/images/ImagesUpload/xiaomi12.webp', 'Xiaomi Redmi Note 12', 4590000, 2, b'1', NULL, NULL, 3, 1, 1),
	(5, 64, 'Vàng', NULL, '2023-06-18 21:31:04.000000', NULL, NULL, 10, '/images/ImagesUpload/iphone8.png', 'Iphone 8', 4000000, 3, b'1', NULL, NULL, 1, 1, 2),
	(6, 64, 'Bạc', NULL, '2023-06-18 21:33:00.000000', NULL, NULL, 20, '/images/ImagesUpload/ipad9wifi.png', 'Ipad Gen 9', 6790000, 2, b'1', NULL, NULL, 1, 2, 1),
	(7, 128, 'Bạc', NULL, '2023-06-18 21:35:01.000000', NULL, NULL, 10, '/images/ImagesUpload/ipadprom1.png', 'Ipad Pro M1', 20000000, 2, b'1', NULL, NULL, 1, 2, 1),
	(8, 1, 'Xám', NULL, '2023-06-18 21:36:49.000000', NULL, NULL, 10, '/images/ImagesUpload/DS286-WB.png', 'Dây sạch Iphone 1m', 300000, 4, b'1', NULL, NULL, 1, 3, 2),
	(9, 1, 'Trắng', NULL, '2023-06-18 21:37:57.000000', NULL, NULL, 10, '/images/ImagesUpload/mgn03.png', 'Củ sạc Iphone 12W', 500000, 2, b'1', NULL, NULL, 1, 3, 2),
	(10, 256, 'Đen', NULL, '2023-06-18 21:45:58.000000', NULL, NULL, 20, '/images/ImagesUpload/xiaomi13.png', 'Xiaomi 13', 10000000, 2, b'1', NULL, NULL, 3, 1, 1),
	(11, 256, 'Đen', NULL, '2023-06-18 00:30:39.000000', NULL, NULL, 10, '/images/ImagesUpload/ssgs23ultra.png', 'Samsung S23 Ultra', 25990000, 2, b'1', NULL, NULL, 2, 1, 1);

-- Dumping structure for table mobileshop.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(250) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mobileshop.role: ~2 rows (approximately)
INSERT INTO `role` (`id`, `description`, `name`) VALUES
	(1, NULL, 'ADMIN'),
	(2, NULL, 'USER');

-- Dumping structure for table mobileshop.supplier
CREATE TABLE IF NOT EXISTS `supplier` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(250) NOT NULL,
  `created_by` bigint DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `updated_by` bigint DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g7qiwwu4vpciysmeeyme9gg1d` (`email`),
  UNIQUE KEY `UK_odw8hcb1lettg4mqax263yyb5` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mobileshop.supplier: ~4 rows (approximately)
INSERT INTO `supplier` (`id`, `address`, `created_by`, `created_date`, `email`, `name`, `phone`, `status`, `updated_by`, `updated_date`) VALUES
	(1, 'test', NULL, NULL, 'fpt@gmail.com', 'FPTShop', '0367676767', b'1', NULL, NULL),
	(2, 'test', NULL, NULL, 'tgdt@gmail.com', 'TGDT', '0368686868', b'1', NULL, NULL),
	(3, 'test', NULL, NULL, 'hhmobile@gmail.com', 'HoangHaMobile', '0369696969', b'1', NULL, NULL),
	(4, 'test', NULL, NULL, 'cps@gmail.com', 'CellPhoneS', '0370707070', b'1', NULL, NULL);

-- Dumping structure for table mobileshop.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(250) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `provider` varchar(50) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_589idila9li6a4arw1t8ht1gx` (`phone`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mobileshop.user: ~4 rows (approximately)
INSERT INTO `user` (`id`, `created_date`, `email`, `password`, `phone`, `provider`, `updated_date`, `username`) VALUES
	(1, NULL, 'phaybucphu9@gmail.com', '123', '0342886207', NULL, '2023-06-17 16:44:31.000000', 'khang123'),
	(6, NULL, 'anhloc123@gmail.com', '$2a$10$DMVhawKrotNMLvUe4MH43.cl3SKQ.onjngkalQX38C6ZtsceR/YVe', '0353854616', NULL, NULL, 'loclord2'),
	(7, NULL, 'bienhuynhcongkhang2@gmail.com', '$2a$10$BRmMAht.9Z1uhRiGY9LzsOWTQFctifB1LDUVK6ue4BFPnfrbugaaK', '0348723452', NULL, NULL, 'loclord22'),
	(8, NULL, 'khangtorikow990@gmail.com', '$2a$10$gS8CaGoAoQ2u1sPa8jVZ5ukCEt76HFRfug2HslY.5x4MrEffrJoe.', '1123312313', NULL, NULL, 'khanglord2');

-- Dumping structure for table mobileshop.user_role
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table mobileshop.user_role: ~3 rows (approximately)
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
	(6, 1),
	(1, 2),
	(8, 2);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
