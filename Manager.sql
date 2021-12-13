-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2021-12-13 18:05:52
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
-- 資料表結構 `manager`
--

CREATE TABLE `manager` (
  `id` int(11) NOT NULL,
  `name` varchar(4) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(12) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `modified` date NOT NULL,
  `created` date NOT NULL,
  `login_times` int(255) NOT NULL,
  `root` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 資料表結構 `members`
--

CREATE TABLE `members` (
  `id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `login_times` int(11) DEFAULT 0,
  `status` varchar(255) DEFAULT '偶數會員'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `members`
--

INSERT INTO `members` (`id`, `name`, `email`, `password`, `modified`, `created`, `login_times`, `status`) VALUES
(1, '測試人員', 'test@cc.ncu.edu.tw', 'Test1234567', '2019-10-18 11:30:28', '2019-10-18 11:30:28', 0, '偶數會員'),
(2, 'peter', '123@gmail.com', '123123abc', '2021-12-04 10:23:46', '2021-12-04 10:23:46', 23, '奇數會員');

-- --------------------------------------------------------

--
-- 資料表結構 `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `last_name` varchar(10) DEFAULT NULL,
  `first_name` varchar(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(12) DEFAULT NULL,
  `create` datetime DEFAULT NULL,
  `modify` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `orders`
--

INSERT INTO `orders` (`id`, `last_name`, `first_name`, `email`, `address`, `phone`, `create`, `modify`) VALUES
(1, '測試', '人員', '', '(320)桃園市中壢區中大路300號', '0911-123-456', '2019-11-08 16:13:41', '2019-11-08 16:13:41'),
(2, '中央', '資管', '', '(320)桃園市中壢區中大路300號', '0922-789-012', '2019-11-08 16:15:08', '2019-11-08 16:15:08'),
(3, '中央', '大學', '', '(320)桃園市中壢區中大路300號', '0922-456-123', '2019-11-08 16:32:07', '2019-11-08 16:32:07'),
(4, 'NCU', 'MIS', '', '(32001) 桃園市中壢區中大路300 號', '0911-234-977', '2019-11-09 03:28:48', '2019-11-09 03:28:48');

-- --------------------------------------------------------

--
-- 資料表結構 `order_product`
--

CREATE TABLE `order_product` (
  `id` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(5) DEFAULT NULL,
  `subtotal` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `order_product`
--

INSERT INTO `order_product` (`id`, `order_id`, `product_id`, `price`, `quantity`, `subtotal`) VALUES
(1, 1, 3, 399.99, 12, 4799.88),
(2, 1, 4, 499.99, 14, 6999.86),
(3, 2, 1, 199.99, 11, 2199.89),
(4, 2, 3, 399.99, 5, 1999.95),
(5, 2, 2, 299.99, 7, 2099.93),
(6, 2, 4, 499.99, 3, 1499.97),
(7, 3, 1, 199.99, 10, 1999.9),
(8, 3, 4, 499.99, 7, 3499.93),
(9, 4, 2, 299.99, 3, 899.97),
(10, 4, 4, 499.99, 5, 2499.95);

-- --------------------------------------------------------

--
-- 資料表結構 `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(250) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `image` varchar(250) DEFAULT NULL,
  `describe` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `products`
--

INSERT INTO `products` (`id`, `name`, `price`, `image`, `describe`) VALUES
(1, 'Rob Cond', 10000, 'p1.jpg', '如果您想尋找一家交通方便的沖繩住宿，那沒有比Rob Cond公寓更合適的選擇了。 在這裡，旅客可輕鬆前往市區內各大旅遊、購物、餐飲地點。 住宿位置優越讓旅客前往市區內的熱門景點變得方便快捷。\r\n\r\nRob Cond公寓一直致力於為您提供最尊貴的服務與一流的設施，確保您下榻期間愉快、愜意。 住宿提供的服務，包括所有房型皆附免費WiFi, 自助洗衣設備, 可寄放行李, 公共區域WiFi, 停車場。'),
(2, 'Extended Suites Cancun Cumbres', 12000, 'p2.jpg', 'Extended Suites Cancun Cumbres酒店位于坎昆（Cancún）。所有客房均配有平板卫星电视和私人浴室。酒店提供健身中心和24小时服务的前台。 酒店的每间客房均拥有书桌。部分客房配有带微波炉的小厨房。Extended Suites Cancun Cumbres酒店的客房配有空调和衣柜。 Extended Suites Cancun Cumbres酒店配有露台。');

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `order_product`
--
ALTER TABLE `order_product`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `members`
--
ALTER TABLE `members`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `order_product`
--
ALTER TABLE `order_product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
