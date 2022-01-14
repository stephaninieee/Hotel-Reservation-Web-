-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2022-01-14 05:39:12
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
-- 資料表結構 `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `Room_id` int(11) NOT NULL,
  `Member_id` int(11) NOT NULL,
  `star` int(11) NOT NULL,
  `comment` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `comment`
--

INSERT INTO `comment` (`id`, `Room_id`, `Member_id`, `star`, `comment`) VALUES
(2, 1, 1, 4, '接客、お部屋の掃除などは満足しております。ほぼ満室だったと思いますが、他のお客さんの声や音など全くしなかったので夜も快適に過ごせました。 ただ一つ残念だったのが朝食が遅めだったこともありますが、まだ食事をしているのにどんどん片付けが進んでしまい最後のデザートが全然食べれませんでした。 最後のお客が退席するまで片付けないでほしかったです。 朝食はとても美味しかったです！\r\n\r\n接客、お部屋の掃除などは満足しております。ほぼ満室だったと思いますが、他のお客さんの声や音など全くしなかったので夜も快適に過ごせまし'),
(3, 2, 1, 1, '爛報'),
(4, 2, 1, 1, '爛報'),
(5, 1, 2, 1, '好ㄟ'),
(6, 4, 2, 5, '讚喔'),
(7, 3, 4, 2, 'ㄏㄏ'),
(8, 1, 4, 5, '普普'),
(9, 4, 3, 2, 'ㄎㄎㄎ'),
(11, 2, 5, 3, 'wwwwwwwwwwwwwwwwwww'),
(12, 8, 5, 3, '整體都好乾淨，今次住三人房，可以擺到三個大行李，廁所同沖涼嘅分開，所以都好方便.'),
(13, 8, 5, 3, 'この度はアートホテル大阪ベイタワーにご宿泊いただきありがとうございます。 また大阪にお越しの際は、当館をお選びいただけますと幸いでございます。'),
(14, 8, 5, 3, 'この度はアートホテル大阪ベイタワーへご宿泊頂き、誠にありがとうございます。お客様のまたのお越しを、心よりお待ち申し上げております。'),
(15, 17, 3, 3, '房間非常寬裕及舒適，設備也蠻齊全，當中包括游泳池，sauna, jaccuzi 健身房的等。酒店的大門面向大海，前面及旁邊有很多餐館。雖然我是素食者，但早餐也有蠻多選擇。房間的價錢公道，最重要是透過AGODA預訂酒店，其價錢比我們自己預訂便宜馬幣幾十零吉。贊！'),
(16, 17, 3, 2, '1.酒店非常殘舊 2.無守誠諾，幾個月前已在agoda book 了房間指明是deluxe non smoking twin bed, 誰知去到酒店check in 時才得知只剩下smoking room 3.住了三晚，沒有提供機場接送服務，還要付 RM30 的接送服務費 4.酒店房內沒有 wifi, 酒店內lobby 的wifi 很慢且經常斷線 5.位置差，到市中心要走5分鐘以上，還要經過地盤和很臭的污水渠 6.房間很小，洗手間用品不足'),
(17, 16, 3, 1, '好扯 !!!!品質超好跟本6星級ㄏㄏ'),
(18, 13, 3, 2, '爛地方，去你媽'),
(19, 6, 8, 5, '服務真好!!');

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
