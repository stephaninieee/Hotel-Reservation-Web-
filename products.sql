-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2021-12-17 05:11:19
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
  `describe` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `products`
--

INSERT INTO `products` (`id`, `name`, `price`, `image`, `describe`) VALUES
(1, 'Rob Cond', 10000, 'p1.jpg', '如果您想尋找一家交通方便的沖繩住宿，那沒有比Rob Cond公寓更合適的選擇了。 在這裡，旅客可輕鬆前往市區內各大旅遊、購物、餐飲地點。 住宿位置優越讓旅客前往市區內的熱門景點變得方便快捷。\r\n\r\nRob Cond公寓一直致力於為您提供最尊貴的服務與一流的設施，確保您下榻期間愉快、愜意。 住宿提供的服務，包括所有房型皆附免費WiFi, 自助洗衣設備, 可寄放行李, 公共區域WiFi, 停車場。'),
(2, 'Extended Suites Cancun Cumbres', 13000, 'p2.jpg', 'Extended Suites Cancun Cumbres酒店位於坎昆（Cancún）。所有客房均配有平板衛星電視和私人浴室。酒店提供健身中心和24小時服務的前臺。  酒店的每間客房均擁有書桌。部分客房配有帶微波爐的小廚房。Extended Suites Cancun Cumbres酒店的客房配有空調和衣櫃。  Extended Suites Cancun Cumbres酒店配有露臺。'),
(5, 'Oriental Hotel Tokyo Bay', 800, 'p4.jpg', '這家迪士尼官方合作夥伴飯店位於 JR 新浦安站正前方，提供往返東京迪士尼度假區的免費接駁服務，車程 15 分鐘。館內設有 3 間餐廳和酒吧，客人可在此品嚐各種美食。此住宿可提供附設公共停車位，需額外收費。\r\n\r\nOriental Hotel Tokyo Bay 的客房皆擁有溫暖的自然色彩。'),
(7, 'APA度假飯店', 12000, 'p5.jpg', 'APA度假飯店・東京灣幕張搭乘搭乘JR京葉線電車至東京車站只要四十分鐘，轉乘東京地下鐵前往東京市區的景點，如東京鐵塔、晴空塔、淺草、品川、橫濱等都非常方便，如果要去搭飛機飯店提供羽田機場及成田機場的京城巴士，可從飯店門口直接出發往返（單程票價是900日幣）。搭電車距離成田國際機場大約也是四十分鐘。');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
