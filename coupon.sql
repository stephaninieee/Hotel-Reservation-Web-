-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2021-12-17 05:12:23
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
-- 資料表結構 `coupon`
--

CREATE TABLE `coupon` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `discount` double NOT NULL,
  `start_time` date NOT NULL,
  `end_time` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `coupon`
--

INSERT INTO `coupon` (`id`, `name`, `discount`, `start_time`, `end_time`) VALUES
(1, '聖誕', 0.9, '2022-12-21', '2022-12-22'),
(3, 'haha', 0.8787, '2022-12-12', '2022-12-25'),
(4, 'wow', 0.99, '2022-12-19', '2022-12-25'),
(5, '巴巴', 0.9, '2022-12-12', '2022-12-25'),
(6, '中秋', 0.89, '2022-12-12', '2022-12-25'),
(7, '聖誕', 0.9, '2022-12-13', '2022-12-13'),
(8, '雙十', 0.88, '2022-12-13', '2022-12-26'),
(9, '萬乘', 0.333, '2022-12-13', '2022-12-26'),
(10, '花', 0.9, '2022-12-13', '2022-12-26'),
(11, 'ppp', 0.999, '2022-12-13', '2022-12-26'),
(12, '雙11', 0.11, '2022-01-01', '2022-09-19');

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `coupon`
--
ALTER TABLE `coupon`
  ADD PRIMARY KEY (`id`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `coupon`
--
ALTER TABLE `coupon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
