-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Gép: localhost
-- Létrehozás ideje: 2021. Máj 23. 09:53
-- Kiszolgáló verziója: 8.0.18
-- PHP verzió: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `hotel2`
--

DELIMITER $$
--
-- Eljárások
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `addNewRent` (IN `startIN` DATETIME, IN `endIN` DATETIME, IN `useridIN` INT(8), IN `roomidIN` INT(8))  NO SQL
INSERT INTO `rent`(`rent`.`rent_date_start`, `rent`.`rent_date_end`, `rent`.`user_id`, `rent`.`room_id`, `rent`.`rent_status`) VALUES(startIN, endIN, useridIN, roomidIN, 1)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addNewRoom` (IN `nameIN` VARCHAR(200) CHARSET utf8, IN `bedIN` INT(8), IN `descIN` TEXT CHARSET utf8, IN `statusIN` INT(1))  NO SQL
INSERT INTO `room`(`room`.`room_name`,`room`.`room_bed`, `room`.`room_description`, `room`.`room_status`) VALUES(nameIN, bedIN, descIN, statusIN)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addNewUser` (IN `emailIN` VARCHAR(100) CHARSET utf8, IN `passwordIN` VARCHAR(100) CHARSET utf8, IN `nameIN` VARCHAR(30) CHARSET utf8, IN `phoneIN` VARCHAR(15) CHARSET utf8)  NO SQL
INSERT INTO `user`(`user`.`email`, `user`.`password`, `user`.`name`, `user`.`phone_number`, `user`.`user_status`) VALUES (emailIN, SHA1(passwordIN), nameIN, phoneIN, 1)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ChangeUserPassword` (IN `idIN` INT(8), IN `newpwIN` VARCHAR(100) CHARSET utf8)  NO SQL
UPDATE `user` SET `user`.`password` = SHA1(newpwIN) WHERE `user`.`user_id`= idIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllActiveRent` ()  NO SQL
SELECT * FROM `rent` WHERE `rent`.`rent_status` = 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllActiveRoom` ()  NO SQL
SELECT * FROM `room` WHERE `room`.`room_status` = 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllActiveUser` ()  NO SQL
SELECT * FROM `user` WHERE `user`.`user_status` = 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllRentByDate` (IN `timeFromIN` DATETIME, IN `timeToIN` DATETIME)  NO SQL
SELECT * FROM `rent` WHERE (`rent`.`rent_date_start` BETWEEN timeFromIN AND timeToIN)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllRentByRoomId` (IN `idIN` INT(8))  NO SQL
SELECT * FROM `rent` WHERE `rent`.`room_id` = idIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllRentByUserId` (IN `idIN` INT(8))  NO SQL
SELECT * FROM `rent` WHERE `rent`.`user_id` = idIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllRoomByBed` (IN `bedIN` INT(8))  NO SQL
SELECT * FROM `room` WHERE `room`.`room_bed` = bedIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `logicalDeleteRentById` (IN `idIN` INT(8))  NO SQL
UPDATE ˛`rent` SET `rent`.`rent_status` = 0 WHERE `rent`.`rent_id` = idIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `logicalDeleteRoomById` (IN `idIN` INT(8))  NO SQL
UPDATE `room` SET `room`.`room_status` = 0 WHERE `room`.`room_id` = idIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `logicalDeleteUserById` (IN `idIN8` INT)  NO SQL
UPDATE `user` SET `user`.`user_status` = 0 WHERE `user`.`user_id` = idIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateRent` (IN `idIN` INT(8), IN `newStartIN` DATETIME, IN `newndIN` DATETIME, IN `newUseridIN` INT(8), IN `newRoomidIN` INT(8))  NO SQL
UPDATE `rent` SET `rent`.`rent_date_start` = newStartIN, `rent`.`rent_date_end` = newEndIN, `rent`.`user_id` = newuseridIN, `rent`.`room_id` = newRoomidIN WHERE `rent`.`rent_id` = idIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateRoom` (IN `idIN` INT(8), IN `nameIN` VARCHAR(100) CHARSET utf8, IN `bedIN` INT(8), IN `descIN` TEXT CHARSET utf8)  NO SQL
UPDATE `room` SET `room`.`room_name` = nameIN, `room`.`room_bed` = bedIN, `room`.`room_description` = descIN WHERE `room`.`room_id` = idIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateUser` (IN `idIN` INT(8), IN `newEmailIN` VARCHAR(100) CHARSET utf8, IN `newPasswordIN` VARCHAR(100) CHARSET utf8, IN `newNameIN` VARCHAR(100) CHARSET utf8, IN `newPhoneIN` VARCHAR(100) CHARSET utf8)  NO SQL
UPDATE  `user` SET `user`.`email` = newEmailIN, `user`.`password` = SHA1(newPasswordIN), `user`.`name` = newNameIN, `user`.`phone_number` = newPhoneIN WHERE `user`.`user_id` = idIN$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `rent`
--

CREATE TABLE `rent` (
  `rent_id` int(11) NOT NULL,
  `rent_date_start` datetime NOT NULL,
  `rent_date_end` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `rent_status` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `rent`
--

INSERT INTO `rent` (`rent_id`, `rent_date_start`, `rent_date_end`, `user_id`, `room_id`, `rent_status`) VALUES
(1, '2021-05-20 00:00:00', '2021-05-31 00:00:00', 1, 1, 1);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `room`
--

CREATE TABLE `room` (
  `room_id` int(11) NOT NULL,
  `room_name` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `room_bed` int(8) NOT NULL,
  `room_description` text COLLATE utf8mb4_general_ci NOT NULL,
  `room_status` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `room`
--

INSERT INTO `room` (`room_id`, `room_name`, `room_bed`, `room_description`, `room_status`) VALUES
(1, 'Updatelt', 3, 'UPDATE:Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi orci lacus, convallis et vestibulum vitae, venenatis sed risus. Duis consequat non diam ut vehicula. Maecenas vitae felis placerat, blandit turpis quis, tempor mauris. Aliquam consequat purus in tortor mattis aliquet. Ut luctus aliquam metus, ac maximus ligula molestie et. Mauris nec lorem mi. Nulla quam lorem, semper at mi placerat, vulputate ultrices tortor. Cras eget sapien a odio pellentesque sodales. Mauris condimentum nec neque sit amet feugiat.  Phasellus gravida metus at enim convallis, nec tempor quam tincidunt. Aliquam erat volutpat. Maecenas egestas, tortor quis porta posuere, diam leo dapibus dui. ', 0),
(2, 'Java application', 13, 'Kodból', 0),
(3, 'PRoba', 16, 'Kodból írtam', 0),
(4, 'Teszt', 2, 'Letesztelek eggy modositast', 0);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `password` text COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `phone_number` varchar(15) COLLATE utf8mb4_general_ci NOT NULL,
  `user_status` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `user`
--

INSERT INTO `user` (`user_id`, `email`, `password`, `name`, `phone_number`, `user_status`) VALUES
(1, 'ujpelda@example.com', 'e908389d4bbf30e8dc72dc47cdf6b45d89e8b2a0', 'UjPleda', '+3630/7654321', 0);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `rent`
--
ALTER TABLE `rent`
  ADD PRIMARY KEY (`rent_id`);

--
-- A tábla indexei `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`room_id`);

--
-- A tábla indexei `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `rent`
--
ALTER TABLE `rent`
  MODIFY `rent_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT a táblához `room`
--
ALTER TABLE `room`
  MODIFY `room_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT a táblához `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
