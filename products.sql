-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2021-12-21 10:01:12
-- 伺服器版本： 10.4.14-MariaDB
-- PHP 版本： 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `missa`
--

-- --------------------------------------------------------

--
-- 資料表結構 `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(250) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `image` varchar(250) DEFAULT NULL,
  `address` varchar(100) NOT NULL,
  `baby` tinyint(1) NOT NULL,
  `breakfast` tinyint(1) NOT NULL,
  `wifi` tinyint(1) NOT NULL,
  `smoking` tinyint(1) NOT NULL,
  `shower` tinyint(1) NOT NULL,
  `KTV` tinyint(1) NOT NULL,
  `van` tinyint(1) NOT NULL,
  `parking` tinyint(1) NOT NULL,
  `bath` tinyint(1) NOT NULL,
  `swimming` tinyint(1) NOT NULL,
  `beach` tinyint(1) NOT NULL,
  `TV` tinyint(1) NOT NULL,
  `air` tinyint(1) NOT NULL,
  `laundry` tinyint(1) NOT NULL,
  `bar` tinyint(1) NOT NULL,
  `business` tinyint(1) NOT NULL,
  `game` tinyint(1) NOT NULL,
  `SPA` tinyint(1) NOT NULL,
  `describe` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `products`
--

INSERT INTO `products` (`id`, `name`, `price`, `image`, `address`, `baby`, `breakfast`, `wifi`, `smoking`, `shower`, `KTV`, `van`, `parking`, `bath`, `swimming`, `beach`, `TV`, `air`, `laundry`, `bar`, `business`, `game`, `SPA`, `describe`) VALUES
(1, 'Rob Cond', 10000, 'p1.jpg', '台北', 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, ''),
(2, 'Cancun Cumbres', 13000, 'p2.jpg', '台北', 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, ''),
(3, 'Oriental Hotel Tokyo Bay', 800, 'p6.jpg', '桃園', 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, ''),
(4, 'APA度假飯店', 12000, 'p5.jpg', '新竹', 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, '');

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
