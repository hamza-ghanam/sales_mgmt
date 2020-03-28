-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 28, 2020 at 03:56 AM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sales_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Milk and Dairy'),
(2, 'Meats'),
(3, 'Grocery & Gourmet Food');

-- --------------------------------------------------------

--
-- Table structure for table `category_has_product`
--

CREATE TABLE `category_has_product` (
  `category_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category_has_product`
--

INSERT INTO `category_has_product` (`category_id`, `product_id`) VALUES
(1, 1),
(1, 4),
(1, 29),
(1, 32),
(1, 33),
(2, 2),
(2, 31),
(3, 4),
(3, 31),
(3, 32),
(3, 33),
(3, 34);

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id`, `name`, `last_name`, `mobile`) VALUES
(4, 'Alice', 'Smith', '00963938688607'),
(5, 'Hamza', 'Ghanam', '0938688607');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `price` decimal(5,2) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `description`, `price`, `created_at`) VALUES
(1, 'Mozarella Cheese', 'Italian Mozarella Cheese', '250.00', '2020-03-26 12:53:00'),
(2, 'Beef Steak', 'Deicious Beef Steak', '500.00', '2020-03-26 12:54:00'),
(3, 'Chicken', 'Chicken Meat', '400.00', '2020-03-26 12:55:00'),
(4, 'Full Fat Milk', 'Full Fat Milk', '100.00', '2020-03-27 15:09:53'),
(28, 'Parmesan Cheese', 'Italian Parmesan Cheese', '250.00', '2020-03-27 19:02:18'),
(29, 'Parmesan Cheese', 'Italian Parmesan Cheese', '250.00', '2020-03-27 19:02:18'),
(31, 'Chicken Rosto', 'Pure chicken breast with pistachio stuffed in a net, naturally smoked with the finest types of wood.', '300.00', '2020-03-27 19:28:51'),
(32, 'Cow Yogurt', 'Produced by bacterial fermentation of milk. The bacteria used to make yogurt are known as yogurt cultures.', '150.00', '2020-03-27 19:43:44'),
(33, 'Goat Yogurt', 'Produced by bacterial fermentation of milk. The bacteria used to make yogurt are known as yogurt cultures.', '200.00', '2020-03-27 19:47:20'),
(34, 'Mayonnaise', 'A thick cold condiment or dressing commonly used in sandwiches and composed salads or on French fries.', '120.00', '2020-03-28 02:09:48');

-- --------------------------------------------------------

--
-- Table structure for table `sale`
--

CREATE TABLE `sale` (
  `id` int(11) NOT NULL,
  `total` decimal(6,2) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `client_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sale`
--

INSERT INTO `sale` (`id`, `total`, `created_at`, `client_id`, `seller_id`) VALUES
(29, '2000.00', '2020-03-28 00:09:31', 4, 1),
(30, '2000.00', '2020-03-28 00:12:25', 4, 1),
(31, '2000.00', '2020-03-28 00:13:20', 4, 1),
(32, '2000.00', '2020-03-28 00:21:17', 4, 1),
(33, '2000.00', '2020-03-28 00:24:00', 4, 1),
(34, '2000.00', '2020-03-28 00:28:49', 4, 1),
(35, '2000.00', '2020-03-28 00:31:32', 4, 1),
(36, '2000.00', '2020-03-28 00:33:45', 4, 1),
(37, '2000.00', '2020-03-28 00:39:05', 4, 1),
(38, '2000.00', '2020-03-28 00:45:04', 4, 1),
(39, '4200.00', '2020-03-28 00:58:28', 4, 1),
(40, '2650.00', '2020-03-28 02:13:52', 5, 2);

-- --------------------------------------------------------

--
-- Table structure for table `seller`
--

CREATE TABLE `seller` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `seller`
--

INSERT INTO `seller` (`id`, `name`, `mobile`) VALUES
(1, 'First Seller', '0933333333'),
(2, 'Second Seller', '0988888888');

-- --------------------------------------------------------

--
-- Table structure for table `sold_product`
--

CREATE TABLE `sold_product` (
  `sale_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` smallint(5) UNSIGNED NOT NULL DEFAULT 1,
  `sale_price` decimal(5,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sold_product`
--

INSERT INTO `sold_product` (`sale_id`, `product_id`, `quantity`, `sale_price`) VALUES
(29, 1, 2, '0'),
(29, 2, 3, '0'),
(30, 1, 2, '0'),
(30, 2, 3, '0'),
(31, 1, 2, '0'),
(31, 2, 3, '0'),
(32, 1, 2, '0'),
(32, 2, 3, '0'),
(33, 1, 2, '0'),
(33, 2, 3, '0'),
(34, 1, 2, '0'),
(34, 2, 3, '0'),
(35, 1, 2, '0'),
(35, 2, 3, '0'),
(36, 1, 2, '0'),
(36, 2, 3, '0'),
(37, 1, 2, '0'),
(37, 2, 3, '0'),
(38, 1, 2, '250'),
(38, 2, 3, '500'),
(39, 1, 4, '400'),
(39, 2, 3, '500'),
(40, 1, 3, '250'),
(40, 3, 4, '400'),
(40, 31, 1, '300');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category_has_product`
--
ALTER TABLE `category_has_product`
  ADD PRIMARY KEY (`category_id`,`product_id`),
  ADD KEY `fk_category_has_product_product1` (`product_id`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sale`
--
ALTER TABLE `sale`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_sale_client1` (`client_id`),
  ADD KEY `fk_sale_seller1` (`seller_id`);

--
-- Indexes for table `seller`
--
ALTER TABLE `seller`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sold_product`
--
ALTER TABLE `sold_product`
  ADD PRIMARY KEY (`sale_id`,`product_id`),
  ADD KEY `fk_sold_product_product1` (`product_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `sale`
--
ALTER TABLE `sale`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `seller`
--
ALTER TABLE `seller`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `category_has_product`
--
ALTER TABLE `category_has_product`
  ADD CONSTRAINT `fk_category_has_product_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_category_has_product_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sale`
--
ALTER TABLE `sale`
  ADD CONSTRAINT `fk_sale_client1` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_sale_seller1` FOREIGN KEY (`seller_id`) REFERENCES `seller` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `sold_product`
--
ALTER TABLE `sold_product`
  ADD CONSTRAINT `fk_sold_product_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_sold_product_sale1` FOREIGN KEY (`sale_id`) REFERENCES `sale` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
