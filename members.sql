-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2022-01-14 05:39:31
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
-- 資料表結構 `members`
--

CREATE TABLE `members` (
  `id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `login_times` int(11) DEFAULT 0,
  `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `members`
--

INSERT INTO `members` (`id`, `name`, `email`, `password`, `modified`, `created`, `login_times`, `phone`) VALUES
(2, '劉德安', 'steph.20010103@gmail.com', '1234567890Aaa', '2021-12-13 14:57:10', '2021-12-13 14:46:44', 57, '0912837623'),
(3, '賴宥安', '345@gmail.com', '7CE0359F12857F2A90C7DE465F40A95F01CB5DA9', '2022-01-13 05:56:42', '2022-01-13 05:56:42', 56, '0921560213'),
(4, '國紹', 'sss@gmail.com', '7CE0359F12857F2A90C7DE465F40A95F01CB5DA9', '2022-01-13 06:17:53', '2022-01-13 06:17:53', 17, '0921560159'),
(5, 'aaa', 'aaa@gmail.com', '7CE0359F12857F2A90C7DE465F40A95F01CB5DA9', '2022-01-13 06:19:55', '2022-01-13 06:19:55', 19, '0921560154'),
(6, 'ccc', 'ccc@gmail.com', '7CE0359F12857F2A90C7DE465F40A95F01CB5DA9', '2022-01-13 06:27:00', '2022-01-13 06:27:00', 27, '0921560153'),
(7, 'vvv', 'vvv@gmail.com', '7CE0359F12857F2A90C7DE465F40A95F01CB5DA9', '2022-01-13 11:58:08', '2022-01-13 11:58:08', 58, '0921560151'),
(8, '張州豪', 'jack@gmail.com', '7CE0359F12857F2A90C7DE465F40A95F01CB5DA9', '2022-01-14 02:21:17', '2022-01-14 02:21:17', 21, '0987654320');

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`id`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `members`
--
ALTER TABLE `members`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
