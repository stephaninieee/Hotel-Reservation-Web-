-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2022-01-05 18:02:12
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
  `avgrate` int(3) NOT NULL,
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
  `describe` varchar(255) NOT NULL,
  `lng` double NOT NULL,
  `lat` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `products`
--

INSERT INTO `products` (`id`, `name`, `price`, `image`, `address`, `avgrate`, `baby`, `breakfast`, `wifi`, `smoking`, `shower`, `KTV`, `van`, `parking`, `bath`, `swimming`, `beach`, `TV`, `air`, `laundry`, `bar`, `business`, `game`, `SPA`, `describe`, `lng`, `lat`) VALUES
(1, 'Rob Cond', 2800, 'https://pix8.agoda.net/hotelImages/5347424/-1/d0978cc3ac584168e16d2833502c57bc.jpg?ca=13&ce=1&s=1024x768', '台北', 3, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, '苅田北九州機場Vessel飯店開幕於2006，不僅為福岡增添風采，也是旅客們的最佳選擇。 在這裡，旅客可輕鬆前往市區內各大旅遊、購物、餐飲地點。 住宿位置優越讓旅客前往市區內的熱門景點變得方便快捷。此外，這裡的所有客房皆配有各種舒適的房內設施。 許多房間甚至還提供了平面電視, 免費WiFi, 禁菸房, 空調, 暖氣以滿足客人的需求。 住宿配備了按摩服務等娛樂設施，必定能讓您流連忘返。', 121.19499969482422, 24.968399047851562),
(2, 'Cancun Cumbres', 3000, 'https://pix8.agoda.net/hotelImages/4888272/0/087ec6fc9a01f72417b884721b974330.jpg?ca=23&ce=0&s=1024x768', '台北', 4, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, '位於北九州的小倉皇冠山飯店是您旅遊探索福岡和其周邊地區的最佳選擇。 在這裡，旅客可輕鬆前往市區內各大旅遊、購物、餐飲地點。 住宿位置優越讓旅客前往市區內的熱門景點變得方便快捷。  小倉皇冠山飯店提供多種多樣的設施，令您在福岡期間的旅程更豐富。 這間住宿提供多樣設施，再講究的客人也能在此得到滿意的服務。', 130.8844757080078, 33.8809700012207),
(3, 'Oriental Hotel ', 2700, 'https://pix8.agoda.net/hotelImages/103481/0/6e2ceb7dfebf4541acb785a18ac7958e.jpg?ca=7&ce=1&s=1024x768', '桃園', 5, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, '', 0, 0),
(4, '新竹老爺大酒店', 1000, 'https://pix8.agoda.net/hotelImages/874/8744/8744_16012516500039378305.jpg?ca=6&ce=1&s=1024x768', '新竹市 光復路一段227號', 2, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, '新竹老爺大酒店座落於新竹縣的新竹市，是帶給您歡樂假期與放鬆身心的完美住宿選擇。 離市中心僅1 km的路程，能確保旅客快捷地前往當地的旅遊景點。 對於喜歡冒險的旅客來說，Hsinchu Yixin Headquarter, The Eastern Gate, Riverside Park再合適不過了。', 121.0237808227539, 24.78271484375),
(5, '煙波大飯店台南館', 1111, 'https://pix8.agoda.net/hotelImages/849/8495698/8495698_19112112430084273673.jpg?s=1024x768', '台南市中西區永福路一段269號', 5, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, '煙波大飯店台南館坐落於市中心精華地段，對面即是台南美術館二館，周邊景點林立，走路就可以到各個市區景點，飯店為配合台南古都特色，不定期推出住宿優惠。全館以商務飯店為主軸，除備有專屬商務會員空間，更規畫義式餐廳「隱糧」，提供入住旅客或一般顧客西餐料理及各式精選酒品，以南方豐厚人情盛宴款待。', 120.20246887207031, 22.989192962646484),
(6, '花漾旅館', 3999, 'https://pix8.agoda.net/hotelImages/1517491/-1/5e1ad1e5afa625ac9d3296908e5e17fb.jpg?ca=7&ce=1&s=1024x768', '桃園市中壢區中和路180號5樓', 5, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, '無論您是因為出差或度假而造訪桃園市，花漾旅館都會是您的最佳住宿選擇。 這家3星級飯店離機場僅有22 km的路程，交通方便。 住宿位置優越讓旅客前往市區內的熱門景點變得方便快捷。\r\n花漾旅館的一流設施和優質服務能確保客人住宿愉快。 入住期間，客人可享受所有房型皆附免費WiFi, 每日客房清潔服務, 自助洗衣設備, 代客叫車服務, 24小時前台服務。', 121.22320556640625, 24.9538516998291),
(7, '新舍商旅', 3751, 'https://pix8.agoda.net/hotelImages/162/1622176/1622176_20071310010091510437.jpg?s=1024x768', '桃園市中壢區中央西路一段120號30樓', 3, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, '位於中壢區的新舍商旅 - 中壢館是您旅遊探索桃園市和其周邊地區的最佳選擇。 這間住宿離市中心僅隔15 km，方便旅客外出。 住宿位置優越讓旅客前往市區內的熱門景點變得方便快捷。\r\n新舍商旅 - 中壢館一直致力於為您提供最尊貴的服務與一流的設施，確保您下榻期間愉快、愜意。 ', 121.22402954101562, 24.95682716369629),
(8, '煙波大飯店新竹湖濱館', 8503, 'https://pix8.agoda.net/hotelImages/412087/-1/5798ca49e752fbc155d5ad765677e13d.jpg?s=1024x768', '明湖路773號', 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, '煙波大飯店新竹湖濱館所屬煙波國際觀光集團，位於新竹市，地理位置優越，煙波大飯店新竹湖濱館是新竹縣短途旅行的理想出發點。 在這裡，旅客可輕鬆前往市區內各大旅遊、購物、餐飲地點。 這家住宿氣氛閒適安逸，而且離Ying Xi Ancient House, Qingcaohu Night Market, Putian Temple等景點僅數步之遙。', 120.97264862060547, 24.775318145751953),
(9, '板橋凱撒大飯店', 5628, 'https://pix8.agoda.net/hotelImages/2089511/-1/b41c3693fd6b6b55f9e77d63b910d06e.jpg?s=1024x768', '新北市板橋區縣民大道2段8號', 3, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, '在這裡，您能真切的感受最極致的舒適住宿體驗，部分客房提供平面電視, 浴室話機, 地毯, 清潔用品, 開放式衣櫥，給住客更完整的服務。 不管您是健身愛好者還是只想在疲憊的一天後放鬆一下自己，住宿的頂級娛樂設施都是不二選擇，例如：健身房, 室外游泳池, Spa, 按摩服務。 專業的服務與豐富的特色活動盡在Caesar Park Hotel Banqiao。', 121.46237182617188, 25.010908126831055),
(12, '台中博客創意旅店', 500, 'https://pix8.agoda.net/hotelImages/928918/-1/24f754e486caa2494ab0dfd67e186f12.jpg?s=1024x768', '台中市北區錦新街40號', 2, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, '台中博客創意旅店座落於台中市的北區，是帶給您歡樂假期與放鬆身心的完美住宿選擇。 離市中心僅0 km的路程，能確保旅客快捷地前往當地的旅遊景點。 住宿位置優越讓旅客前往市區內的熱門景點變得方便快捷。', 120.54989624023438, 24.07819938659668),
(13, '台中旭日文旅 ', 1749, 'https://pix8.agoda.net/hotelImages/6428111/-1/f4e9c21ef564ff56cd4f4ba092d0f6ec.jpg?s=1024x768', '五權路276號, 北區, 台中市', 3, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 'Raise Hotel Taichung提供優質住宿，位於台中市的觀光, 購物, 餐飲，是商務和休閒旅行的熱門之選。 離市中心僅的路程，能確保旅客快捷地前往當地的旅遊景點。 住宿位置優越讓旅客前往市區內的熱門景點變得方便快捷。\nRaise Hotel Taichung提供多種多樣的設施，令您在台中市期間的旅程更豐富。 這間住宿提供多樣設施，再講究的客人也能在此得到滿意的服務。', 120.676485, 24.1507092);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
