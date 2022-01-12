-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2022-01-12 14:49:58
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
  `member_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `coupon_id` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `status` varchar(100) NOT NULL,
  `check_in` date DEFAULT NULL,
  `check_out` date DEFAULT NULL,
  `create` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `orders`
--

INSERT INTO `orders` (`id`, `member_id`, `room_id`, `coupon_id`, `price`, `status`, `check_in`, `check_out`, `create`) VALUES
(1, 1, 2, 2, 1200, '未付款', '2001-04-11', '2022-01-13', '2022-01-10 12:56:12'),
(2, 1, 4, 3, 29000, '未付款', '2022-01-02', '2022-01-05', '2022-01-10 12:56:12'),
(3, 2, 5, 4, 11111, '未付款', '2022-01-02', '2022-01-21', '2022-01-10 12:56:12'),
(4, 2, 10, 2, 20202, '未付款', '2022-01-05', '2022-01-19', '2022-01-10 12:56:12'),
(5, 2, 3, 2, 11111, '未付款', '2022-01-06', '2022-01-10', '2022-01-10 12:56:12'),
(6, 2, 6, 5, 2222, '未付款', '2022-01-12', '2022-01-20', '2022-01-10 12:56:12'),
(7, 3, 7, 1, 123123, '未付款', '2022-01-15', '2022-01-26', '2022-01-10 12:56:12'),
(8, 3, 9, 1, 712312, '未付款', '2022-01-05', '2022-01-14', '2022-01-10 12:56:12'),
(9, 4, 11, 1, 1112000, '未付款', '2022-01-25', '2022-01-28', '2022-01-10 12:56:12');

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
