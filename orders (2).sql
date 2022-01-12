-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2022-01-12 18:11:21
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
-- 資料表結構 `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `member_name` varchar(50) NOT NULL,
  `room_name` varchar(50) NOT NULL,
  `coupon_name` varchar(50) NOT NULL,
  `price` int(11) NOT NULL,
  `status` varchar(100) NOT NULL,
  `check_in` date DEFAULT NULL,
  `check_out` date DEFAULT NULL,
  `create` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `manager_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `orders`
--

INSERT INTO `orders` (`id`, `member_name`, `room_name`, `coupon_name`, `price`, `status`, `check_in`, `check_out`, `create`, `manager_id`) VALUES
(1, '賴宥安', 'Rob Cond', '聖誕節', 1200, '未付款', '2001-04-11', '2022-01-13', '2022-01-12 14:44:24', 1),
(2, '賴宥安', 'Cancun Cumbres', '聖誕節', 29000, '未付款', '2022-01-02', '2022-01-05', '2022-01-12 15:12:48', 2),
(3, '賴宥安', 'Oriental Hotel', '聖誕節', 11111, '未付款', '2022-01-02', '2022-01-21', '2022-01-12 15:12:53', 1),
(4, '料國紹', '花漾旅館', '中秋節', 20202, '未付款', '2022-01-05', '2022-01-19', '2022-01-12 14:45:08', 1),
(5, '邵國料', '煙波大飯店台南館', '中元節', 11111, '未付款', '2022-01-06', '2022-01-10', '2022-01-12 14:45:10', 1),
(6, '賴宥安', '新竹老爺大酒店', '春節', 2222, '未付款', '2022-01-12', '2022-01-20', '2022-01-12 15:12:56', 2),
(7, '劉德安', '板橋凱撒大飯店', '雙11', 123123, '未付款', '2022-01-15', '2022-01-26', '2022-01-12 15:13:04', 1),
(8, '劉德安', '台中旭日文旅', '雙10', 712312, '未付款', '2022-01-05', '2022-01-14', '2022-01-12 15:13:10', 1),
(9, '劉德安', '台中博客創意旅店', '雙12', 1112000, '未付款', '2022-01-25', '2022-01-28', '2022-01-12 15:13:13', 2);

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
