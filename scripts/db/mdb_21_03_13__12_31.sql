-- MariaDB dump 10.18  Distrib 10.4.16-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: musicdb
-- ------------------------------------------------------
-- Server version	10.4.16-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `albums`
--

DROP TABLE IF EXISTS `albums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `albums` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '',
  `artist_id` int(10) unsigned NOT NULL,
  `release` date DEFAULT NULL,
  `cover` varchar(255) NOT NULL,
  `add` varchar(255) NOT NULL,
  `coversmall` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`name`) USING BTREE,
  KEY `FKalbum2aartist` (`artist_id`),
  CONSTRAINT `FK72gqyi6l1j674radjyitcm86f` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`id`),
  CONSTRAINT `FKalbum2artist` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `albums`
--

LOCK TABLES `albums` WRITE;
/*!40000 ALTER TABLE `albums` DISABLE KEYS */;
INSERT INTO `albums` VALUES (1,'Human :||: Nature',1,'2020-04-10','cover - HumanNature.jpg','','small - HumanNature.jpg'),(2,'Decades',1,'2019-12-06','cover - Decades.jpg','','small - Decades.jpg'),(3,'House of Usher',2,'2016-10-21','cover - HouseOfUsher.jpg','','small - HouseOfUsher.jpg'),(4,'Darkness of Eternity',4,'2017-11-10','cover - DarknessOfEternity.jpg','','small - DarknessOfEternity.jpg'),(5,'End of Eden',4,'2010-10-20','cover - EndOfEden.jpg','','small - EndOfEden.jpg'),(6,'Looking For You',4,'2020-01-31','cover - LookingForYou.jpg','','small - LookingForYou.jpg'),(7,'Magic Forest',4,'2014-06-27','cover - MagicForest.jpg','','small - MagicForest.jpg'),(8,'River of Tuoni',4,'2008-01-30','cover - RiverOfTuoni.jpg','','small - RiverOfTuoni.jpg'),(9,'The Clouds of Northland Thunder',4,'2009-05-13','cover - TheCloudsOfNorthlandThunder.jpg','','small - TheCloudsOfNorthlandThunder.jpg'),(10,'Under The Red Cloud',5,'2015-09-04','cover - UnderTheRedCloud.jpg','','small - UnderTheRedCloud.jpg'),(11,'Reflections Revised',6,'2003-11-17','cover - ReflectionsRevised.jpg','','small - ReflectionsRevised.jpg'),(12,'War Eternal',3,'2014-06-09','cover - WarEternal.jpg','','small - WarEternal.jpg'),(13,'Will To Power',3,'2017-09-08','cover - WillToPower.jpg','[Explicit]','small - WillToPower.jpg'),(14,'Bringer of Pain',7,'2017-02-17','cover - BringerOfPain.jpg','[Explicit]','small - BringerOfPain.jpg'),(15,'No More Hollywood Endings',7,'2019-03-22','cover - NoMoreHollywoodEndings.jpg','','small - NoMoreHollywoodEndings.jpg'),(16,'Berserker',8,'2017-11-03','cover - Berserker.jpg','','small - Berserker.jpg'),(17,'From Hell With Love',8,'2019-02-08','cover - FromHellWithLove.jpg','','small - FromHellWithLove.jpg'),(18,'Heart Of The Hurricane',9,'2018-08-31','cover - HeartOfTheHurricane.jpg','','small - HeartOfTheHurricane.jpg'),(19,'Horizons',9,'2020-06-17','cover - Horizons.jpg','','small - Horizons.jpg'),(20,'Lost In Forever',9,'2016-02-12','cover - LostInForever.jpg','','small - LostInForever.jpg'),(21,'Lost In Forever',9,'2016-02-12','cover - LostInForeverDeluxe.jpg','[Deluxe Edition]','small - LostInForeverDeluxe.jpg'),(22,'Songs Of Love And Death',9,'2015-02-13','cover - SongsOfLoveAndDeath.jpg','','small - SongsOfLoveAndDeath.jpg'),(23,'Behind The Black Veil',10,'2015-05-08','cover - BehindTheBlackVeil.jpg','','small - BehindTheBlackVeil.jpg'),(24,'The Puzzle',10,'2016-11-18','cover - ThePuzzle.jpg','','small - ThePuzzle.jpg'),(25,'Hunter\'s Moon',11,'2019-02-22','cover - HuntersMoon.jpg','','small - HuntersMoon.jpg'),(26,'Interlude',11,'2013-05-03','cover - Interlude.jpg','','small - Interlude.jpg'),(27,'Lucidity',11,'2006-09-04','cover - Lucidity.jpg','','small - Lucidity.jpg'),(28,'Moonbathers',11,'2016-08-26','cover - Moonbathers.jpg','','small - Moonbathers.jpg'),(29,'The Human Contradiction',11,'2014-04-04','cover - TheHumanContradiction.jpg','','small - TheHumanContradiction.jpg'),(30,'Argia',12,'2014-04-11','cover - Argia.jpg','','small - Argia.jpg'),(31,'Dynamind',13,'2019-10-25','cover - Dynamind.jpg','','small - Dynamind.jpg'),(32,'Soliaire',13,'2010-07-22','cover - Solitaire.jpg','','small - Solitaire.jpg'),(33,'The Bonding',13,'2013-06-21','cover - TheBonding.jpg','','small - TheBonding.jpg'),(34,'Until Dawn',14,'2018-06-01','cover - UntilDawn.jpg','','small - UntilDawn.jpg'),(35,'Best Of',15,'2013-10-14','cover - BestOfEpica.png','','small - BestOfEpica.jpg'),(36,'Consign To Oblivion',15,'2015-11-24','cover - ConsignToOblivion.jpg','(Expanded Edition)','small - ConsignToOblivion.jpg'),(37,'Design Your Universe',15,'2009-10-16','cover - DesignYourUniverse.jpg','','small - DesignYourUniverse.jpg'),(38,'Epica vs. Attack on Titan Songs',15,'2018-07-20','cover - EpicaVsAttackOnTitanSongs.jpg','','small - EpicaVsAttackOnTitanSongs.jpg'),(39,'Requiem For The Indifferent',15,'2012-03-26','cover - RequiemForTheIndifferent.jpg','(Special Edition)','small - RequiemForTheIndifferent.jpg'),(40,'The Classical Conspiracy',15,'2009-05-11','cover - TheClassicalConspiracy.jpg','','small - TheClassicalConspiracy.jpg'),(41,'The Divine Conspiracy',15,'2007-09-07','cover - TheDivineConspiracy.png','','small - TheDivineConspiracy.jpg'),(42,'The Holographic Principle',15,'2016-09-30','cover - TheHolographicPrinciple.jpg','','small - TheHolographicPrinciple.jpg'),(43,'The Phantom Agony',15,'2013-03-18','cover - ThePhantomAgony.jpg','(Expanded Edition)','small - ThePhantomAgony.jpg'),(44,'The Quantum Enigma',15,'2014-05-02','cover - TheQuantumEnigma.jpg','','small - TheQuantumEnigma.jpg'),(45,'The Solace System',15,'2017-09-01','cover - TheSolaceSystem.jpg','','small - TheSolaceSystem.jpg'),(46,'Twilight',16,'2001-06-18','cover - Twilight.jpg','','small - Twilight.jpg'),(47,'Fallen',17,'2003-05-19','cover - Fallen.jpg','','small - Fallen.jpg'),(48,'The Legacy Of Atlantis',18,'2018-10-18','cover - TheLegacyOfAtlantis.jpg','','small - TheLegacyOfAtlantis.jpg'),(49,'Sign Of The Dragonheart',19,'2018-01-12','cover - SignOfTheDragonhead.jpg','','small - SignOfTheDragonhead.jpg'),(50,'S&M2',20,'2020-08-28','cover - SM2.png','','small - SM2.jpg'),(51,'Fallen Angels In The Hell',21,'2016-05-12','cover - FallenAngelsInTheHell.jpg','','small - FallenAngelsInTheHell.jpg'),(52,'For All Beyond',21,'2018-04-19','cover - ForAllBeyond.jpg','','small - ForAllBeyond.jpg'),(53,'Destined Ways',22,'2014-07-11','cover - DestinedWays.jpg','','small - DestinedWays.jpg'),(54,'Angels Fall First',1,'1997-11-01','cover - AngelsFallFirst.jpg','','small - AngelsFallFirst.jpg'),(55,'Century Child',1,'2002-06-24','cover - CenturyChild.jpg','','small - CenturyChild.jpg'),(56,'Dark Passion Play',1,'2007-09-26','cover - DarkPassionPlay.jpg','(Special Deluxe Edition)','small - DarkPassionPlay.jpg'),(57,'Élan',1,'2015-02-13','cover - Elan.jpg','','small - Elan.jpg'),(58,'End of an Era',1,'2006-06-22','cover - EndOfAnEra.png','','small - EndOfAnEra.jpg'),(59,'Endless Forms Most Beautiful',1,'2015-03-27','cover - EndlessFormsMostBeautiful.jpg','(Deluxe Version)','small - EndlessFormsMostBeautiful.jpg'),(60,'Highest Hopes',1,'2005-09-23','cover - HighestHopes.jpg','','small - HighestHopes.jpg'),(61,'Imaginaerum',1,'2011-12-02','cover - Imaginaerum.jpg','','small - Imaginaerum.jpg'),(62,'Oceanborn',1,'1999-08-30','cover - Oceanborn.jpg','','small - Oceanborn.jpg'),(63,'Once',1,'2004-06-06','cover - Once.jpg','','small - Once.jpg'),(64,'Over th Hills and Far Away',1,'2001-06-25','cover - OverTheHillsAndFarAway.jpg','','small - OverTheHillsAndFarAway.jpg'),(65,'Showtime, Storytime',1,'2013-11-29','cover - ShowtimeStorytime.jpg','','small - ShowtimeStorytime.jpg'),(66,'Sleeping Sun',1,'2005-10-19','cover - SleepingSun.jpg','','small - SleepingSun.jpg'),(67,'Tales from Elvenpath',1,'2004-10-18','cover - TalesFromElvenpath.jpg','','small - TalesFromElvenpath.jpg'),(68,'Walking in the Air',1,'2011-05-27','cover - WalkingInTheAir.jpg','The Greatest Ballads','small - WalkingInTheAir.jpg'),(69,'Wishmaster',1,'2000-05-29','cover - Wishmaster.jpg','','small - Wishmaster.jpg'),(70,'Best Of The Blessed',23,'2020-07-03','cover - BestOfTheBlessed.jpg','','small - BestOfTheBlessed.jpg'),(71,'Bible Of The Beast',23,'2009-04-27','cover - BibleOfTheBeast.jpg','','small - BibleOfTheBeast.jpg'),(72,'Blessed & Possessed',23,'2015-07-17','cover - BlessedPossessed.jpg','','small - BlessedPossessed.jpg'),(73,'Lupus Dei',23,'2007-05-04','cover - LupusDei.jpg','','small - LupusDei.jpg'),(74,'Metallum Nostrum',23,'2019-01-11','cover - MetallumNostrum.jpg','','small - MetallumNostrum.jpg'),(75,'Preachers Of The Night',23,'2013-06-28','cover - PreachersOfTheNight.jpg','','small - PreachersOfTheNight.jpg'),(76,'The Sacrament Of Sin',23,'2018-07-20','cover - TheSacramentOfSin.jpg','','small - TheSacramentOfSin.jpg'),(77,'Blood Of The Saints',23,'2011-07-29','cover - BloodOfTheSaints.jpg','','small - BloodOfTheSaints.jpg'),(78,'Bismarck',24,'2019-05-17','cover - Bismarck.jpg','','small - Bismarck.jpg'),(79,'Carolus Rex',24,'2012-05-25','cover - CarolusRex.jpg','','small - CarolusRex.jpg'),(80,'Heroes',24,'2014-05-16','cover - Heroes.jpg','','small - Heroes.jpg'),(81,'Primo Victoria',24,'2010-09-26','cover - PrimoVictoria.jpg','(Re-Armed)','small - PrimoVictoria.jpg'),(82,'The Great War',24,'2019-07-19','cover - TheGreatWar.jpg','[Explicit]','small - TheGreatWar.jpg'),(83,'Lack Of Light',25,'2018-08-31','cover - LackOfLight.jpg','','small - LackOfLight.jpg'),(84,'The Serpent\'s Kiss',26,'2018-01-01','cover - TheSerpentsKiss.jpg','(Second Edition)','small - TheSerpentsKiss.jpg'),(85,'Best Of',27,'2016-05-06','cover - BestOfStratovarius.jpg','','small - BestOfStratovarius.jpg'),(86,'Act II',28,'2018-07-27','cover - ActII.jpg','','small - ActII.jpg'),(87,'In The Raw',28,'2019-08-30','cover - InTheRaw.jpg','','small - InTheRaw.jpg'),(88,'Left In The Dark',28,'2014-07-04','cover - LeftInTheDark.jpg','','small - LeftInTheDark.jpg'),(89,'My Winter Storm',28,'2007-11-14','cover - MyWinterStorm.jpg','','small - MyWinterStorm.jpg'),(90,'The Shadow Self',28,'2016-08-05','cover - TheShadowSelf.jpg','','small - TheShadowSelf.jpg'),(91,'Aegis',29,'1998-05-11','cover - Aegis.jpg','','small - Aegis.jpg'),(92,'Crowning Of Atlantis',30,'1999-06-07','cover - CrowningOfAtlantis.jpg','','small - CrowningOfAtlantis.jpg'),(93,'Deggial',30,'2000-01-31','cover - Deggial.jpg','','small - Deggial.jpg'),(94,'Gothic Kabbalah',30,'2007-01-12','cover - GothicKabbalah.jpg','','small - GothicKabbalah.jpg'),(95,'Lemuria',30,'2004-05-24','cover - Lemuria.jpg','','small - Lemuria.jpg'),(96,'Les Fleurs du Mal',30,'2012-09-28','cover - LesFleursDuMal.jpg','','small - LesFleursDuMal.jpg'),(97,'Secret Of The Runes',30,'2001-10-08','cover - SecretOfTheRunes.jpg','','small - SecretOfTheRunes.jpg'),(98,'Sirius B',30,'2004-05-24','cover - SiriusB.jpg','','small - SiriusB.jpg'),(99,'Sitra Ahra',30,'2010-09-17','cover - SitraAhra.jpg','','small - SitraAhra.jpg'),(100,'Theli',30,'1996-08-09','cover - Theli.jpg','','small - Theli.jpg'),(101,'Vovin',30,'1998-05-04','cover - Vovin.jpg','','small - Vovin.jpg'),(102,'Cast Away',31,'2004-11-29','cover - CastAway.jpg','','small - CastAway.jpg'),(103,'Old Routes - New Waters',31,'2016-04-29','cover - OldRoutesNewWaters.jpg','','small - OldRoutesNewWaters.jpg'),(104,'The Deep & The Dark',31,'2018-02-16','cover - TheDeepTheDark.jpg','','small - TheDeepTheDark.jpg'),(105,'Wanderers',31,'2019-08-30','cover - Wanderers.jpg','','small - Wanderers.jpg'),(106,'Hydra',32,'2014-01-31','cover - Hydra.jpg','','small - Hydra.jpg'),(107,'Mother Earth',32,'2003-01-27','cover - MotherEarth.jpg','','small - MotherEarth.jpg'),(108,'Resist',32,'2019-02-01','cover - Resist.jpg','(Deluxe)','small - Resist.jpg'),(109,'The Heart Of Everything',32,'2007-03-09','cover - TheHeartOfEverything.jpg','','small - TheHeartOfEverything.jpg'),(110,'The Silent Force',32,'2004-11-15','cover - TheSilentForce.jpg','','small - TheSilentForce.jpg'),(111,'The Unforgiving',32,'2011-03-25','cover - TheUnforgiving.jpg','','small - TheUnforgiving.jpg'),(120,'Fire & Ashes',34,'2015-07-31','cover - FireAshes.jpg','','small - FireAshes.jpg'),(128,'India',34,'2005-08-22','cover - India.jpg','','small - India.jpg'),(129,'Kill The Sun',34,'2003-05-03','cover - KillTheSun.jpg','','small - KillTheSun.jpg'),(130,'Neverworlds End',34,'2012-02-22','cover - NeverworldsEnd.jpg','','small - NeverworldsEnd.jpg'),(131,'Ravenheart',34,'2004-05-24','cover - Ravenheart.jpg','','small - Ravenheart.jpg'),(132,'Sacrificium',34,'2014-05-02','cover - Sacrificium.jpg','(Deluxe Edition)','small - Sacrificium.jpg'),(133,'Salomé - The Seventh Veil',34,'2007-05-25','cover - SalomeTheSeventhVeil.jpg','','small - SalomeTheSeventhVeil.jpg'),(135,'Theatre Of Dimensions',34,'2017-01-27','cover - TheatreOfDimensions.png','','small - TheatreOfDimensions.jpg'),(136,'The Frozen Throne',35,'2018-11-23','cover - TheFrozenThrone.jpg','','small - TheFrozenThrone.jpg');
/*!40000 ALTER TABLE `albums` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `archives`
--

DROP TABLE IF EXISTS `archives`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `archives` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `host_id` int(10) unsigned NOT NULL,
  `path` varchar(255) NOT NULL,
  `descr` varchar(255) NOT NULL,
  `type` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archives`
--

LOCK TABLES `archives` WRITE;
/*!40000 ALTER TABLE `archives` DISABLE KEYS */;
INSERT INTO `archives` VALUES (1,1,'d:/Archiv/metal','','METAL','Metal'),(2,1,'d:/Archiv/Musik','','COMPLETE','Musik'),(4,2,'c:/Daten/metal','','METAL','Metal'),(5,3,'/music','','COMPLETE','Musik FTP'),(6,3,'/musi2','','METAL','Metal FTP'),(7,7,'/','','METAL','TestFTP'),(11,8,'/metal','Metal Mobil','METAL','Metal');
/*!40000 ALTER TABLE `archives` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artists`
--

DROP TABLE IF EXISTS `artists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artists` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artists`
--

LOCK TABLES `artists` WRITE;
/*!40000 ALTER TABLE `artists` DISABLE KEYS */;
INSERT INTO `artists` VALUES (1,'Nightwish'),(2,'Aeternitas'),(3,'Arch Enemy'),(4,'Amberian Dawn'),(5,'Amorphis'),(6,'Apocalyptica'),(7,'Battle Beast'),(8,'Beast In Black'),(9,'Beyond The Black'),(10,'Dark Sarah'),(11,'Delain'),(12,'Diabulus In Musica'),(13,'Edenbridge'),(14,'Elvellon'),(15,'Epica'),(16,'Erben der Schöpfung'),(17,'Evanescence'),(18,'Imperial Age'),(19,'Leaves\' Eyes'),(20,'Metallica'),(21,'Metalwings'),(22,'Neopera'),(23,'Powerwolf'),(24,'Sabaton'),(25,'Scarlet Dorn'),(26,'Serpentyne'),(27,'Stratovarius'),(28,'Tarja'),(29,'Theatre of Tragedy'),(30,'Therion'),(31,'Visions Of Atlantis'),(32,'Within Temptation'),(34,'Xandria'),(35,'Kalidia'),(36,'Yazoo');
/*!40000 ALTER TABLE `artists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `covers`
--

DROP TABLE IF EXISTS `covers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `covers` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `album_id` int(10) unsigned NOT NULL,
  `fname` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `covers`
--

LOCK TABLES `covers` WRITE;
/*!40000 ALTER TABLE `covers` DISABLE KEYS */;
INSERT INTO `covers` VALUES (1,'cover',1,'human_nature.jpg'),(2,'cover',2,'decades.jpg');
/*!40000 ALTER TABLE `covers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `files`
--

DROP TABLE IF EXISTS `files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `files` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `object_id` int(10) unsigned NOT NULL,
  `folder_id` int(10) unsigned NOT NULL,
  `filename` varchar(255) NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=237 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files`
--

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
INSERT INTO `files` VALUES (1,1,93,'01 - Music.mp3','MP3'),(2,2,93,'02 - Noise.mp3','MP3'),(3,3,93,'03 - Shoemaker.mp3','MP3'),(4,4,93,'04 - Harvest.mp3','MP3'),(5,5,93,'05 - Pan.mp3','MP3'),(6,6,93,'06 - How\'s the Heart_.mp3','MP3'),(7,7,93,'07 - Procession.mp3','MP3'),(8,8,93,'08 - Tribal.mp3','MP3'),(9,9,93,'09 - Endlessness.mp3','MP3'),(10,10,94,'10 - All the Works of Nature Which Adorn the World - Vista.mp3','MP3'),(11,11,94,'11 - All the Works of Nature Which Adorn the World - The Blue.mp3','MP3'),(12,12,94,'12 - All the Works of Nature Which Adorn the World - The Green.mp3','MP3'),(13,13,94,'13 - All the Works of Nature Which Adorn the World - Moors.mp3','MP3'),(14,14,94,'14 - All the Works of Nature Which Adorn the World - Aurorae.mp3','MP3'),(15,15,94,'15 - All the Works of Nature Which Adorn the World - Quiet as the Show.mp3','MP3'),(16,16,94,'16 - All the Works of Nature Which Adorn the World - Anthropocene (Including _Hurrian Hymn to Nikkal_).mp3','MP3'),(17,17,94,'17 - All the Works of Nature Which Adorn the World - Ad Astra.mp3','MP3'),(18,18,94,'01 - The Greatest Show On Earth (Remastered).mp3','MP3'),(19,19,87,'02 - Élan (Remastered).mp3','MP3'),(20,20,87,'03 - My Walden (Remastered).mp3','MP3'),(21,21,87,'04 - Storytime (Remastered).mp3','MP3'),(22,22,87,'05 - I Want My Tears Back (Remastered).mp3','MP3'),(23,23,87,'06 - Amaranth (Remastered).mp3','MP3'),(24,24,87,'07 - The Poet And The Pendulum (Remastered).mp3','MP3'),(25,25,87,'08 - Nemo (Remastered).mp3','MP3'),(26,26,87,'09 - Wish I Had An Angel (Remastered).mp3','MP3'),(27,27,87,'10 - Ghost Love Score (Remastered).mp3','MP3'),(28,28,87,'11 - Slaying The Dreamer (Remastered).mp3','MP3'),(29,29,87,'12 - End Of All Hope (Remastered).mp3','MP3'),(30,30,87,'13 - 10th Man Down (Remastered).mp3','MP3'),(31,31,87,'14 - The Kinslayer (Remastered).mp3','MP3'),(32,32,87,'15 - Dead Boy\'s Poem (Remastered).mp3','MP3'),(33,33,87,'16 - Gethsemane (Remastered).mp3','MP3'),(34,34,87,'17 - Devil & The Deep Dark Ocean (Remastered).mp3','MP3'),(35,35,87,'18 - Sacrament Of Wilderness (Remastered).mp3','MP3'),(36,36,87,'19 - Sleeping Sun (Remastered).mp3','MP3'),(37,37,87,'20 - Elvenpath (Remastered).mp3','MP3'),(38,38,87,'21 - The Carpenter (Remastered).mp3','MP3'),(39,39,87,'22 - Nightwish (Demo, Remastered).mp3','MP3'),(40,40,215,'01 - Aeternitas - Le cœur.mp3','MP3'),(41,41,215,'02 - Aeternitas - House of Usher.mp3','MP3'),(42,42,215,'03 - Aeternitas - The Prophecy.mp3','MP3'),(43,43,215,'04 - Aeternitas - Roderick.mp3','MP3'),(44,44,215,'05 - Aeternitas - Madeline.mp3','MP3'),(45,45,215,'06 - Aeternitas - Fear.mp3','MP3'),(46,46,215,'07 - Aeternitas - Forbidden Love.mp3','MP3'),(47,47,215,'08 - Aeternitas - The Haunted Place.mp3','MP3'),(48,48,215,'09 - Aeternitas - Tears.mp3','MP3'),(49,49,215,'10 - Aeternitas - Buried Alive.mp3','MP3'),(50,50,215,'11 - Aeternitas - Can You Hear The Demons.mp3','MP3'),(51,51,215,'12 - Aeternitas - The Fall.mp3','MP3'),(52,52,215,'13 - Aeternitas - Falling Star.mp3','MP3'),(53,53,215,'14 - Aeternitas - Open Your Eyes.mp3','MP3'),(54,54,215,'15 - Aeternitas - Ethelred.mp3','MP3'),(57,57,216,'01 - Amberian Dawn - I\'m the One.mp3','MP3'),(58,58,216,'02 - Amberian Dawn - Sky Is Falling.mp3','MP3'),(59,59,216,'03 - Amberian Dawn - Dragonflies.mp3','MP3'),(60,60,216,'04 - Amberian Dawn - Maybe.mp3','MP3'),(61,61,216,'05 - Amberian Dawn - Golden Coins.mp3','MP3'),(62,62,216,'06 - Amberian Dawn - Luna My Darling.mp3','MP3'),(63,63,216,'07 - Amberian Dawn - Abyss.mp3','MP3'),(64,64,216,'08 - Amberian Dawn - Ghostwoman.mp3','MP3'),(65,65,216,'09 - Amberian Dawn - Breathe Again.mp3','MP3'),(67,67,216,'10 - Amberian Dawn - Symphony Nr. 1, Pt. 2: Darkness of Eternity.mp3','MP3'),(68,68,217,'01 - Amberian Dawn - Talisman.mp3','MP3'),(69,69,217,'02 - Amberian Dawn - Come Now Follow.mp3','MP3'),(70,70,217,'03 - Amberian Dawn - Arctica.mp3','MP3'),(71,71,217,'04 - Amberian Dawn - Ghostly Echoes.mp3','MP3'),(72,72,217,'05 - Amberian Dawn - Sampo.mp3','MP3'),(73,73,217,'06 - Amberian Dawn - Blackbird.mp3','MP3'),(74,74,217,'07 - Amberian Dawn - Field Of Serpents.mp3','MP3'),(75,75,217,'08 - Amberian Dawn - City Of Destruction.mp3','MP3'),(76,76,217,'09 - Amberian Dawn - Virvatulen Laulu.mp3','MP3'),(77,77,217,'10 - Amberian Dawn - War In Heaven.mp3','MP3'),(78,78,218,'01 - United.mp3','MP3'),(79,79,218,'02 - Eternal Fire Burning.mp3','MP3'),(80,80,218,'03 - Looking for You.mp3','MP3'),(81,81,218,'04 - Two Blades.mp3','MP3'),(82,82,218,'05 - Symphony Nr.1 Part 3 - Awakening [feat. Fabio Lione].mp3','MP3'),(83,83,218,'06 - Go for a Ride.mp3','MP3'),(84,84,218,'07 - Butterfly.mp3','MP3'),(85,85,218,'08 - Universe.mp3','MP3'),(86,86,218,'09 - Lay All Your Love On Me.mp3','MP3'),(87,87,218,'10 - Au Revoir.mp3','MP3'),(88,88,218,'11 - Cherish My Memory (Remastered).mp3','MP3'),(89,89,219,'01 - Cherish My Memory.mp3','MP3'),(90,90,219,'02 - Dance Of Life.mp3','MP3'),(91,91,219,'03 - Magic Forest.mp3','MP3'),(92,92,219,'04 - Agonizing Night.mp3','MP3'),(93,93,219,'05 - Warning.mp3','MP3'),(94,94,219,'06 - Sons Of The Rainbow.mp3','MP3'),(95,95,219,'07 - I\'m Still Here.mp3','MP3'),(96,96,219,'08 - Memorial.mp3','MP3'),(97,97,219,'09 - Endless Silence.mp3','MP3'),(98,98,219,'10 - Green-Eyed.mp3','MP3'),(99,99,219,'11 - Dance Of Life (Bonus Instrumental Version).mp3','MP3'),(100,100,219,'12 - Warning (Bonus Instrumental Version).mp3','MP3'),(101,101,220,'01 - River of Tuoni.mp3','MP3'),(102,102,220,'02 - Wings Are My Eyes.mp3','MP3'),(103,103,220,'03 - Lullaby.mp3','MP3'),(104,104,220,'04 - Valkyries.mp3','MP3'),(105,105,220,'05 - Face of the Maiden.mp3','MP3'),(106,106,220,'06 - My Only Star.mp3','MP3'),(107,107,220,'07 - The Curse.mp3','MP3'),(108,108,220,'08 - Passing Bells.mp3','MP3'),(109,109,220,'09 - Sunrise.mp3','MP3'),(110,110,220,'10 - Evil Inside Me.mp3','MP3'),(111,111,220,'11 - Dreamchaser (Japanese Bonus Track).mp3','MP3'),(112,112,221,'01 - He Sleeps in a Grove.mp3','MP3'),(113,113,221,'02 - Incubus.mp3','MP3'),(114,114,221,'03 - Kokko- Eagle of Fire.mp3','MP3'),(115,115,221,'04 - Willow of Tears.mp3','MP3'),(116,116,221,'05 - Shallow Waters.mp3','MP3'),(117,117,221,'06 - Lost Soul.mp3','MP3'),(118,118,221,'07 - Sons of Seven Stars.mp3','MP3'),(119,119,221,'08 - Saga.mp3','MP3'),(120,120,221,'09 - Snowmaiden.mp3','MP3'),(121,121,221,'10 - Lionheart.mp3','MP3'),(122,122,221,'11 - Morning Star.mp3','MP3'),(123,123,221,'12 - Birth of the Harp.mp3','MP3'),(124,124,222,'01 - Under The Red Cloud.mp3','MP3'),(125,125,222,'02 - The Four Wise Ones.mp3','MP3'),(126,126,222,'03 - Bad Blood.mp3','MP3'),(127,127,222,'04 - The Skull.mp3','MP3'),(128,128,222,'05 - Death Of A King.mp3','MP3'),(129,129,222,'06 - Sacrifice.mp3','MP3'),(130,130,222,'07 - Dark Path.mp3','MP3'),(131,131,222,'08 - Enemy At The Gates.mp3','MP3'),(132,132,222,'09 - Tree Of Ages.mp3','MP3'),(133,133,222,'10 - White Night.mp3','MP3'),(134,134,222,'11 - Come The Spring (Bonus Track).mp3','MP3'),(135,135,222,'12 - Winter\'s Sleep (Bonus Track).mp3','MP3'),(136,136,223,'01 - Apocalyptica - Prologue (Apprehension).mp3','MP3'),(137,137,223,'02 - Apocalyptica - No Education.mp3','MP3'),(138,138,223,'03 - Apocalyptica - Faraway.mp3','MP3'),(139,139,223,'04 - Apocalyptica - Somewhere Around Nothing.mp3','MP3'),(140,140,223,'05 - Apocalyptica - Drive.mp3','MP3'),(141,141,223,'06 - Apocalyptica - Cohkka.mp3','MP3'),(142,142,223,'07 - Apocalyptica - Conclusion.mp3','MP3'),(143,143,223,'08 - Apocalyptica - Resurrection.mp3','MP3'),(144,144,223,'09 - Apocalyptica - Heat.mp3','MP3'),(145,145,223,'10 - Apocalyptica - Cortége.mp3','MP3'),(146,146,223,'11 - Apocalyptica - Pandemonium.mp3','MP3'),(147,147,223,'12 - Apocalyptica - Toreador II.mp3','MP3'),(148,148,223,'13 - Apocalyptica - Epilogue (Relief).mp3','MP3'),(154,154,223,'14 - Apocalyptica - Seemann [Album Version][*].mp3','MP3'),(155,155,223,'15 - Apocalyptica - Faraway Vol. 2 [Extended Version][*].mp3','MP3'),(156,156,223,'16 - Apocalyptica - Delusion [*].mp3','MP3'),(157,157,223,'17 - Apocalyptica - Perdition [*].mp3','MP3'),(158,158,223,'18 - Apocalyptica - Leave Me Alone [*].mp3','MP3'),(159,159,224,'01 - Arch Enemy - Tempore Nihil Sanat (Prelude in F minor).mp3','MP3'),(160,160,224,'02 - Arch Enemy - Never Forgive, Never Forget.mp3','MP3'),(161,161,224,'03 - Arch Enemy - War Eternal.mp3','MP3'),(162,162,224,'04 - Arch Enemy - As The Pages Burn.mp3','MP3'),(163,163,224,'05 - Arch Enemy - No More Regrets.mp3','MP3'),(164,164,224,'06 - Arch Enemy - You Will Know My Name.mp3','MP3'),(165,165,224,'07 - Arch Enemy - Graveyard Of Dreams.mp3','MP3'),(166,166,224,'08 - Arch Enemy - Stolen Life.mp3','MP3'),(167,167,224,'09 - Arch Enemy - Time Is Black.mp3','MP3'),(168,168,224,'10 - Arch Enemy - On And On.mp3','MP3'),(169,169,224,'11 - Arch Enemy - Avalanche.mp3','MP3'),(170,170,224,'12 - Arch Enemy - Down To Nothing.mp3','MP3'),(171,171,224,'13 - Arch Enemy - Not Long For This World.mp3','MP3'),(172,172,224,'14 - Arch Enemy - Shadow On The Wall.mp3','MP3'),(173,173,225,'01 - Arch Enemy - Set Flame to the Night.mp3','MP3'),(174,174,225,'02 - Arch Enemy - The Race.mp3','MP3'),(175,175,225,'03 - Arch Enemy - Blood in the Water.mp3','MP3'),(176,176,225,'04 - Arch Enemy - The World Is Yours.mp3','MP3'),(177,177,225,'05 - Arch Enemy - The Eagle Flies Alone (edit).mp3','MP3'),(178,178,225,'06 - Arch Enemy - Reason to Believe.mp3','MP3'),(179,179,225,'07 - Arch Enemy - Murder Scene.mp3','MP3'),(180,180,225,'08 - Arch Enemy - First Day in Hell.mp3','MP3'),(181,181,225,'09 - Arch Enemy - Saturnine.mp3','MP3'),(182,182,225,'10 - Arch Enemy - Dreams of Retribution.mp3','MP3'),(183,183,225,'11 - Arch Enemy - My Shadow and I.mp3','MP3'),(184,184,225,'12 - Arch Enemy - A Fight I Must Win.mp3','MP3'),(185,185,225,'13 - Arch Enemy - City Baby Attacked by Rats (cover version) [Explicit].mp3','MP3'),(186,186,226,'01 - Battle Beast - Straight to the Heart.mp3','MP3'),(187,187,226,'02 - Battle Beast - Bringer of Pain.mp3','MP3'),(188,188,226,'03 - Battle Beast - King for a Day.mp3','MP3'),(189,189,226,'04 - Battle Beast - Beyond the Burning Skies.mp3','MP3'),(190,190,226,'05 - Battle Beast - Familiar Hell [Explicit].mp3','MP3'),(191,191,226,'06 - Battle Beast - Lost in Wars (feat. Tomi Joutsen).mp3','MP3'),(192,192,226,'07 - Battle Beast - Bastard Son of Odin.mp3','MP3'),(193,193,226,'08 - Battle Beast - We Will Fight.mp3','MP3'),(194,194,226,'09 - Battle Beast - Dancing with the Beast.mp3','MP3'),(195,195,226,'10 - Battle Beast - Far from Heaven.mp3','MP3'),(196,196,226,'11 - Battle Beast - God of War.mp3','MP3'),(197,197,226,'12 - Battle Beast - The Eclipse.mp3','MP3'),(198,198,226,'13 - Battle Beast - Rock Trash.mp3','MP3'),(199,199,227,'01 - Battle Beast - Unbroken.mp3','MP3'),(200,200,227,'02 - Battle Beast - No More Hollywood Endings.mp3','MP3'),(201,201,227,'03 - Battle Beast - Eden.mp3','MP3'),(202,202,227,'04 - Battle Beast - Unfairy Tales.mp3','MP3'),(203,203,227,'05 - Battle Beast - Endless Summer.mp3','MP3'),(204,204,227,'06 - Battle Beast - The Hero.mp3','MP3'),(205,205,227,'07 - Battle Beast - Piece of Me.mp3','MP3'),(206,206,227,'08 - Battle Beast - I Wish.mp3','MP3'),(207,207,227,'09 - Battle Beast - Raise Your Fists.mp3','MP3'),(208,208,227,'10 - Battle Beast - The Golden Horde.mp3','MP3'),(209,209,227,'11 - Battle Beast - World on Fire.mp3','MP3'),(210,210,227,'12 - Battle Beast - Bent and Broken.mp3','MP3'),(211,211,227,'13 - Battle Beast - My Last Dream.mp3','MP3'),(212,212,229,'01 - Beast In Black - Beast in Black.mp3','MP3'),(213,213,229,'02 - Beast In Black - Blind and Frozen.mp3','MP3'),(214,214,229,'03 - Beast In Black - Blood of a Lion.mp3','MP3'),(215,215,229,'04 - Beast In Black - Born Again.mp3','MP3'),(216,216,229,'05 - Beast In Black - Zodd the Immortal.mp3','MP3'),(217,217,229,'06 - Beast In Black - The Fifth Angel.mp3','MP3'),(218,218,229,'07 - Beast In Black - Crazy, Mad, Insane.mp3','MP3'),(219,219,229,'08 - Beast In Black - Hell for All Eternity.mp3','MP3'),(220,220,229,'09 - Beast In Black - Eternal Fire.mp3','MP3'),(221,221,229,'10 - Beast In Black - Go to Hell.mp3','MP3'),(222,222,229,'11 - Beast In Black - End of the World.mp3','MP3'),(223,223,229,'12 - Beast In Black - Ghost in the Rain.mp3','MP3'),(224,224,228,'01 - Beast In Black - Cry out for a Hero.mp3','MP3'),(225,225,228,'02 - Beast In Black - From Hell with Love.mp3','MP3'),(226,226,228,'03 - Beast In Black - Sweet True Lies.mp3','MP3'),(227,227,228,'04 - Beast In Black - Repentless.mp3','MP3'),(228,228,228,'05 - Beast In Black - Die by the Blade.mp3','MP3'),(229,229,228,'06 - Beast In Black - Oceandeep.mp3','MP3'),(230,230,228,'07 - Beast In Black - Unlimited Sin.mp3','MP3'),(231,231,228,'08 - Beast In Black - True Believer.mp3','MP3'),(232,232,228,'09 - Beast In Black - This Is War.mp3','MP3'),(233,233,228,'10 - Beast In Black - Heart of Steel.mp3','MP3'),(234,234,228,'11 - Beast In Black - No Surrender.mp3','MP3'),(235,235,228,'12 - Beast In Black - Killed by Death.mp3','MP3'),(236,236,228,'13 - Beast In Black - No Easy Way Out.mp3','MP3');
/*!40000 ALTER TABLE `files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folder_archives`
--

DROP TABLE IF EXISTS `folder_archives`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder_archives` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `folder_id` int(10) unsigned NOT NULL,
  `archives_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder_archives`
--

LOCK TABLES `folder_archives` WRITE;
/*!40000 ALTER TABLE `folder_archives` DISABLE KEYS */;
/*!40000 ALTER TABLE `folder_archives` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folders`
--

DROP TABLE IF EXISTS `folders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folders` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `parent_id` int(10) unsigned NOT NULL,
  `archive_id` int(10) unsigned NOT NULL,
  `type` varchar(45) NOT NULL,
  `object_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=232 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folders`
--

LOCK TABLES `folders` WRITE;
/*!40000 ALTER TABLE `folders` DISABLE KEYS */;
INSERT INTO `folders` VALUES (1,'Aeternitas',172,1,'ARTIST',2),(2,'House of Usher',1,1,'ALBUM',3),(3,'Amberian Dawn',172,1,'ARTIST',4),(4,'Darkness of Eternity',3,1,'ALBUM',4),(5,'End Of Eden',3,1,'ALBUM',5),(6,'Looking For You',3,1,'ALBUM',6),(7,'Magic Forest',3,1,'ALBUM',7),(8,'River of Tuoni',3,1,'ALBUM',8),(9,'The Clounds of Northland Thunder',3,1,'ALBUM',9),(10,'Amorphis',172,1,'ARTIST',5),(11,'Under The Red Cloud',10,1,'ALBUM',10),(12,'Apocalyptica',172,1,'ARTIST',6),(13,'Reflections Revised Disc 1',12,1,'ALBUM',11),(14,'Arch Enemy',172,1,'ARTIST',3),(15,'War Eternal',14,1,'ALBUM',12),(16,'Will To Power [Explicit]',14,1,'ALBUM',13),(17,'Battle Beast',172,1,'ARTIST',7),(18,'Bringer of Pain [Explicit]',17,1,'ALBUM',14),(19,'No More Hollywood Endings',17,1,'ALBUM',15),(20,'Beast In Black',172,1,'ARTIST',8),(21,'Berserker',20,1,'ALBUM',16),(22,'From Hell With Love',20,1,'ALBUM',17),(23,'Beyond The Black',172,1,'ARTIST',9),(24,'Heart Of The Hurricane',23,1,'ALBUM',18),(25,'Horizons',23,1,'ALBUM',19),(26,'Lost In Forever',23,1,'ALBUM',20),(27,'Lost In Forever [Deluxe Edition]',23,1,'ALBUM',21),(28,'Songs Of Love And Death',23,1,'ALBUM',22),(29,'Dark Sarah',172,1,'ARTIST',10),(30,'Behind the Black Veil',29,1,'ALBUM',23),(31,'The Puzzle',29,1,'ALBUM',24),(32,'Delain',172,1,'ARTIST',11),(33,'Hunter\'s Moon',32,1,'ALBUM',25),(34,'Interlude',32,1,'ALBUM',26),(35,'Licidity',32,1,'ALBUM',27),(36,'Moonbathers',32,1,'ALBUM',28),(37,'The Human Contradiction',32,1,'ALBUM',29),(38,'Diabulus In Musica',172,1,'ARTIST',12),(39,'Argia',38,1,'ALBUM',30),(40,'Edenbridge',172,1,'ARTIST',13),(41,'Dynamind',40,1,'ALBUM',31),(42,'Solitaire',40,1,'ALBUM',32),(43,'The Bonding',40,1,'ALBUM',33),(44,'Elvellon',172,1,'ARTIST',14),(45,'Until Dawn',44,1,'ALBUM',34),(46,'Epica',172,1,'ARTIST',15),(47,'Best Of',46,1,'ALBUM',35),(48,'Consign to Oblivion (Expanded Edition)',46,1,'ALBUM',36),(49,'Design Your Universe',46,1,'ALBUM',37),(50,'Epica vs. Attack on Titan Songs',46,1,'ALBUM',38),(51,'Requiem For The Indifferent (Special Edition)',46,1,'ALBUM',39),(52,'The Classical Conspiracy',46,1,'ALBUM',40),(53,'CD1 Classical Set',52,1,'SUBALBUM',3),(54,'CD2 Epica Set',52,1,'SUBALBUM',4),(55,'The Divine Conspiracy',46,1,'ALBUM',41),(56,'The Holographic Principle',46,1,'ALBUM',42),(57,'The Acoustic Principle',56,1,'SUBALBUM',5),(58,'The Holographic Principle',56,1,'SUBALBUM',6),(59,'The Phantom Agony (Expanded Edition)',46,1,'ALBUM',43),(60,'The Quantum Enigma',46,1,'ALBUM',44),(61,'CD1',60,1,'SUBALBUM',7),(62,'CD2',60,1,'SUBALBUM',8),(63,'The Solace System',46,1,'ALBUM',45),(64,'Erben der Schöpfung',172,1,'ARTIST',16),(65,'Twilight',64,1,'ALBUM',46),(66,'Evanescence',172,1,'ARTIST',17),(67,'Fallen',66,1,'ALBUM',47),(68,'Imperial Age',172,1,'ARTIST',18),(69,'The Legacy of Atlantis',68,1,'ALBUM',48),(70,'Kalidia',172,1,'ARTIST',35),(71,'The Frozen Throne',70,1,'ALBUM',136),(72,'Leaves\' Eyes',172,1,'ARTIST',19),(73,'Sign of the Dragonhead',72,1,'ALBUM',49),(74,'CD 1',73,1,'SUBALBUM',9),(75,'CD 2',73,1,'SUBALBUM',10),(76,'Metallica',172,1,'ARTIST',20),(77,'S&M2',76,1,'ALBUM',50),(78,'Metalwings',172,1,'ARTIST',21),(79,'Fallen Angel in the Hell',78,1,'ALBUM',51),(80,'For All Beyond',78,1,'ALBUM',52),(81,'Neopera',172,1,'ARTIST',22),(82,'Destined Ways',81,1,'ALBUM',53),(83,'Nightwish',172,1,'ARTIST',1),(84,'Angel fall First',83,1,'ALBUM',54),(85,'Century Child',83,1,'ALBUM',55),(86,'Dark Passion Play (Special Deluxe Edition)',83,1,'ALBUM',56),(87,'Decades',83,1,'ALBUM',2),(88,'Élan [+ digital booklet]',83,1,'ALBUM',57),(89,'End of an Era',83,1,'ALBUM',58),(90,'Endless Forms Most Beautiful (Deluxe Version)',83,1,'ALBUM',59),(91,'Highest Hopes',83,1,'ALBUM',60),(92,'Human. -II- Nature',83,1,'ALBUM',1),(93,'CD1',92,1,'SUBALBUM',1),(94,'CD2',92,1,'SUBALBUM',2),(95,'Imaginaerum',83,1,'ALBUM',61),(96,'Oceanborn',83,1,'ALBUM',62),(97,'Once',83,1,'ALBUM',63),(98,'Over the Hills and far away',83,1,'ALBUM',64),(99,'Showtime, Storytime',83,1,'ALBUM',65),(100,'Sleeping Sun',83,1,'ALBUM',66),(101,'Tales from Elvenpath',83,1,'ALBUM',67),(102,'Walking in the Air  The Greatest Ballads',83,1,'ALBUM',68),(103,'Wishmaster',83,1,'ALBUM',69),(104,'Powerwolf',172,1,'ARTIST',23),(105,'Best Of The Blessed',104,1,'ALBUM',70),(106,'Bible of the Beast',104,1,'ALBUM',71),(107,'Blessed & Possessed',104,1,'ALBUM',72),(108,'Blood Of The Saints',104,1,'ALBUM',77),(109,'Lupus Dei',104,1,'ALBUM',73),(110,'Metallum Nostrum',104,1,'ALBUM',74),(111,'Preachers Of The Night',104,1,'ALBUM',75),(112,'MyList',104,1,'LIST',0),(113,'The Sacrament Of Sin',104,1,'ALBUM',76),(114,'Sabaton',172,1,'ARTIST',24),(115,'Bismarck',114,1,'ALBUM',78),(116,'Carolus Rex',114,1,'ALBUM',79),(117,'Heroes',114,1,'ALBUM',80),(118,'Primo Victoria (Re-Armed)',114,1,'ALBUM',81),(119,'The Great War [Explicit]',114,1,'ALBUM',82),(120,'Scarlet Dorn',172,1,'ARTIST',25),(121,'Lack of Light',120,1,'ALBUM',83),(122,'Serpentyne',172,1,'ARTIST',26),(123,'The Serpent\'s Kiss (Second Edition)',122,1,'ALBUM',84),(124,'Stratovarius',172,1,'ARTIST',27),(125,'Best Of',124,1,'ALBUM',85),(126,'Tarja',172,1,'ARTIST',28),(127,'Act II',126,1,'ALBUM',86),(128,'In The Raw',126,1,'ALBUM',87),(129,'Left in the Dark',126,1,'ALBUM',88),(130,'My Winter Storm',126,1,'ALBUM',89),(131,'The Shadow Self',126,1,'ALBUM',90),(132,'Theatre of Tragedy',172,1,'ARTIST',29),(133,'Aegis',132,1,'ALBUM',91),(134,'Therion',172,1,'ARTIST',30),(135,'Crowning Of Atlantis',134,1,'ALBUM',92),(136,'Deggial',134,1,'ALBUM',93),(137,'Gothic Kabbalah',134,1,'ALBUM',94),(138,'Disc 1',137,1,'SUBALBUM',11),(139,'Disc 2',137,1,'SUBALBUM',12),(140,'Lemuria',134,1,'ALBUM',95),(141,'Les Fleurs du Mal',134,1,'ALBUM',96),(142,'Secrets Of The Runes',134,1,'ALBUM',97),(143,'Sirius B',134,1,'ALBUM',98),(144,'Sitra Ahra',134,1,'ALBUM',99),(145,'Theli',134,1,'ALBUM',100),(146,'Vovin',134,1,'ALBUM',101),(147,'Visions Of Atlantis',172,1,'ARTIST',31),(148,'Cast Away',147,1,'ALBUM',102),(149,'Old Routes   New Waters',147,1,'ALBUM',103),(150,'The Deep & The Dark',147,1,'ALBUM',104),(151,'Wanderers',147,1,'ALBUM',105),(152,'Within Temptation',172,1,'ARTIST',32),(153,'Hydra',152,1,'ALBUM',106),(154,'Mother Earth',152,1,'ALBUM',107),(155,'Resist (Deluxe)',152,1,'ALBUM',108),(156,'The Heart Of Everything',152,1,'ALBUM',109),(157,'The Silent Force',152,1,'ALBUM',110),(158,'The Unforgiving',152,1,'ALBUM',111),(159,'Xandria',172,1,'ARTIST',33),(160,'Fire & Ashes',159,1,'ALBUM',120),(161,'India',159,1,'ALBUM',128),(162,'Kill The Sun',159,1,'ALBUM',129),(163,'Neverworlds End',159,1,'ALBUM',130),(164,'Ravenheart',159,1,'ALBUM',131),(165,'Sacrificium (Deluxe Edition)',159,1,'ALBUM',132),(166,'CD 1',165,1,'SUBALBUM',13),(167,'CD 2 ',165,1,'SUBALBUM',14),(168,'Salomé - The Seventh Veil',159,1,'ALBUM',133),(169,'Theatre Of Dimensions',159,1,'ALBUM',135),(171,'The Frozen Throne',170,1,'ALBUM',136),(172,'',0,1,'ROOT',1),(173,'',0,4,'ROOT',4),(178,'Aeternitas',173,4,'ARTIST',2),(179,'Amberian Dawn',173,4,'ARTIST',4),(180,'Amorphis',173,4,'ARTIST',5),(181,'Apocalyptica',173,4,'ARTIST',6),(182,'Arch Enemy',173,4,'ARTIST',3),(183,'Battle Beast',173,4,'ARTIST',7),(184,'Beast In Black',173,4,'ARTIST',8),(185,'Beyond The Black',173,4,'ARTIST',9),(186,'Dark Sarah',173,4,'ARTIST',10),(187,'Delain',173,4,'ARTIST',11),(188,'Diabulus In Musica',173,4,'ARTIST',12),(189,'Edenbridge',173,4,'ARTIST',13),(190,'Elvellon',173,4,'ARTIST',14),(191,'Epica',173,4,'ARTIST',15),(192,'Erben der Schöpfung',173,4,'ARTIST',16),(193,'Evanescence',173,4,'ARTIST',17),(194,'Imperial Age',173,4,'ARTIST',18),(195,'Kalidia',173,4,'ARTIST',35),(196,'Leaves\' Eyes',173,4,'ARTIST',19),(197,'Metallica',173,4,'ARTIST',20),(198,'Metalwings',173,4,'ARTIST',21),(199,'Neopera',173,4,'ARTIST',22),(200,'Nightwish',173,4,'ARTIST',1),(201,'Powerwolf',173,4,'ARTIST',23),(202,'Sabaton',173,4,'ARTIST',24),(203,'Scarlet Dorn',173,4,'ARTIST',25),(204,'Serpentyne',173,4,'ARTIST',26),(205,'Stratovarius',173,4,'ARTIST',27),(206,'Tarja',173,4,'ARTIST',28),(207,'Theatre of Tragedy',173,4,'ARTIST',29),(208,'Therion',173,4,'ARTIST',30),(212,'Visions Of Atlantis',173,4,'ARTIST',31),(213,'Within Temptation',173,4,'ARTIST',32),(214,'Xandria',173,4,'ARTIST',34),(215,'House of Usher',178,4,'ALBUM',3),(216,'Darkness of Eternity',179,4,'ALBUM',4),(217,'End Of Eden',179,4,'ALBUM',5),(218,'Looking For You',179,4,'ALBUM',6),(219,'Magic Forest',179,4,'ALBUM',7),(220,'River of Tuoni',179,4,'ALBUM',8),(221,'The Clouds of Northland Thunder',179,4,'ALBUM',9),(222,'Under The Red Cloud',180,4,'ALBUM',10),(223,'Reflections Revised Disc 1',181,4,'ALBUM',11),(224,'War Eternal',182,4,'ALBUM',12),(225,'Will To Power [Explicit]',182,4,'ALBUM',13),(226,'Bringer of Pain [Explicit]',183,4,'ALBUM',14),(227,'No More Hollywood Endings',183,4,'ALBUM',15),(228,'From Hell with Love',184,4,'ALBUM',17),(229,'Berserker',184,4,'ALBUM',16),(230,'Heart Of The Hurricane',185,4,'ALBUM',18),(231,'Horizons',185,4,'ALBUM',19);
/*!40000 ALTER TABLE `folders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (15);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hosts`
--

DROP TABLE IF EXISTS `hosts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hosts` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `drive` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hosts`
--

LOCK TABLES `hosts` WRITE;
/*!40000 ALTER TABLE `hosts` DISABLE KEYS */;
INSERT INTO `hosts` VALUES (1,'tupacamaru','127.0.0.1','HARDDRIVE','','',NULL),(2,'w4deumsy9002167','192.168.138.233','HARDDRIVE','','',NULL),(3,'Synology','wkoenig.info','FTP','admin','Wokoko1.',NULL),(4,'Stick 1','TOSHIBA 32','MOBILE','','',NULL),(7,'Local FTP','localhost','FTP','admin','Wokoko1.',NULL),(8,'WD Elements 1','Elements 1','MOBILE',NULL,NULL,NULL);
/*!40000 ALTER TABLE `hosts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instances`
--

DROP TABLE IF EXISTS `instances`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instances` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `archive_id` int(10) unsigned NOT NULL,
  `host_id` int(10) unsigned NOT NULL,
  `path` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instances`
--

LOCK TABLES `instances` WRITE;
/*!40000 ALTER TABLE `instances` DISABLE KEYS */;
INSERT INTO `instances` VALUES (1,1,1,'d:/Archiv/metal'),(2,2,1,'d:/Archiv/Music');
/*!40000 ALTER TABLE `instances` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlistentry`
--

DROP TABLE IF EXISTS `playlistentry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playlistentry` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `track_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlistentry`
--

LOCK TABLES `playlistentry` WRITE;
/*!40000 ALTER TABLE `playlistentry` DISABLE KEYS */;
/*!40000 ALTER TABLE `playlistentry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlists`
--

DROP TABLE IF EXISTS `playlists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playlists` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlists`
--

LOCK TABLES `playlists` WRITE;
/*!40000 ALTER TABLE `playlists` DISABLE KEYS */;
/*!40000 ALTER TABLE `playlists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_USER'),(2,'ROLE_MODERATOR'),(3,'ROLE_ADMIN'),(4,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subalbums`
--

DROP TABLE IF EXISTS `subalbums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subalbums` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `album_id` int(10) unsigned NOT NULL DEFAULT 0,
  `name` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `FKsubalbum2album` (`album_id`),
  CONSTRAINT `FKkcadvwefd1d6v9kfuub28p0t4` FOREIGN KEY (`album_id`) REFERENCES `albums` (`id`),
  CONSTRAINT `FKsubalbum2album` FOREIGN KEY (`album_id`) REFERENCES `albums` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subalbums`
--

LOCK TABLES `subalbums` WRITE;
/*!40000 ALTER TABLE `subalbums` DISABLE KEYS */;
INSERT INTO `subalbums` VALUES (1,1,'CD 1'),(2,1,'CD 2'),(3,40,'CD1 Classical Set'),(4,40,'CD2 Epica Set'),(5,42,'The Acoustic Principle'),(6,42,'The Holographic Principle'),(7,44,'CD1'),(8,44,'CD2'),(9,49,'CD 1'),(10,49,'CD 2'),(11,94,'Disc 1'),(12,94,'Disc 2'),(13,132,'CD 1'),(14,132,'CD 2'),(15,2,'Titelliste'),(16,3,'Titelliste'),(17,4,'Titelliste'),(18,5,'Titelliste'),(19,6,'Titelliste'),(20,7,'Titelliste'),(21,8,'Titelliste'),(22,9,'Titelliste'),(23,10,'Titelliste'),(24,11,'Titelliste'),(25,12,'Titelliste'),(26,13,'Titelliste'),(27,14,'Titelliste'),(28,15,'Titelliste'),(29,16,'Titelliste'),(30,17,'Titelliste'),(31,18,'Titelliste'),(32,19,'Titelliste'),(33,20,'Titelliste'),(34,21,'Titelliste'),(35,22,'Titelliste'),(36,23,'Titelliste'),(37,24,'Titelliste'),(38,25,'Titelliste'),(39,26,'Titelliste'),(40,27,'Titelliste'),(41,28,'Titelliste'),(42,29,'Titelliste'),(43,30,'Titelliste'),(44,31,'Titelliste'),(45,32,'Titelliste'),(46,33,'Titelliste'),(47,34,'Titelliste'),(48,35,'Titelliste'),(49,36,'Titelliste'),(50,37,'Titelliste'),(51,38,'Titelliste'),(52,39,'Titelliste'),(53,41,'Titelliste'),(54,43,'Titelliste'),(55,45,'Titelliste'),(56,46,'Titelliste'),(57,47,'Titelliste'),(58,48,'Titelliste'),(59,50,'Titelliste'),(60,51,'Titelliste'),(61,52,'Titelliste'),(62,53,'Titelliste'),(63,54,'Titelliste'),(64,55,'Titelliste'),(65,56,'Titelliste'),(66,57,'Titelliste'),(67,58,'Titelliste'),(68,59,'Titelliste'),(69,60,'Titelliste'),(70,61,'Titelliste'),(71,62,'Titelliste'),(72,63,'Titelliste'),(73,64,'Titelliste'),(74,65,'Titelliste'),(75,66,'Titelliste'),(76,67,'Titelliste'),(77,68,'Titelliste'),(78,69,'Titelliste'),(79,70,'Titelliste'),(80,71,'Titelliste'),(81,72,'Titelliste'),(82,73,'Titelliste'),(83,74,'Titelliste'),(84,75,'Titelliste'),(85,76,'Titelliste'),(86,77,'Titelliste'),(87,78,'Titelliste'),(88,79,'Titelliste'),(89,80,'Titelliste'),(90,81,'Titelliste'),(91,82,'Titelliste'),(92,83,'Titelliste'),(93,84,'Titelliste'),(94,85,'Titelliste'),(95,86,'Titelliste'),(96,87,'Titelliste'),(97,88,'Titelliste'),(98,89,'Titelliste'),(99,90,'Titelliste'),(100,91,'Titelliste'),(101,92,'Titelliste'),(102,93,'Titelliste'),(103,95,'Titelliste'),(104,96,'Titelliste'),(105,97,'Titelliste'),(106,98,'Titelliste'),(107,99,'Titelliste'),(108,100,'Titelliste'),(109,101,'Titelliste'),(110,102,'Titelliste'),(111,103,'Titelliste'),(112,104,'Titelliste'),(113,105,'Titelliste'),(114,106,'Titelliste'),(115,107,'Titelliste'),(116,108,'Titelliste'),(117,109,'Titelliste'),(118,110,'Titelliste'),(119,111,'Titelliste'),(120,120,'Titelliste'),(121,128,'Titelliste'),(122,129,'Titelliste'),(123,130,'Titelliste'),(124,131,'Titelliste'),(125,133,'Titelliste'),(127,135,'Titelliste'),(128,136,'Titelliste');
/*!40000 ALTER TABLE `subalbums` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `titel_albums`
--

DROP TABLE IF EXISTS `titel_albums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `titel_albums` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `titel_id` int(10) unsigned NOT NULL,
  `album_id` int(10) unsigned NOT NULL,
  `subalbum_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `titel_albums`
--

LOCK TABLES `titel_albums` WRITE;
/*!40000 ALTER TABLE `titel_albums` DISABLE KEYS */;
/*!40000 ALTER TABLE `titel_albums` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `titels`
--

DROP TABLE IF EXISTS `titels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `titels` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `length` int(10) unsigned DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `artist_id` int(10) unsigned NOT NULL,
  `album_id` int(10) unsigned DEFAULT NULL,
  `year` varchar(4) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `trackno` int(10) unsigned DEFAULT NULL,
  `subalbum_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq4mucdveifyd8e99kgdjopnpv` (`artist_id`),
  CONSTRAINT `FKq4mucdveifyd8e99kgdjopnpv` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=237 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `titels`
--

LOCK TABLES `titels` WRITE;
/*!40000 ALTER TABLE `titels` DISABLE KEYS */;
INSERT INTO `titels` VALUES (1,443,'Music',1,1,'','','','',1,1),(2,340,'Noise',1,1,'','','','',2,1),(3,319,'Shoemaker',1,1,'','','','',3,1),(4,313,'Harvest',1,1,'','','','',4,1),(5,318,'Pan',1,1,'','','','',5,1),(6,302,'How\'s the Heart',1,1,'','','','',6,1),(7,331,'Procession',1,1,'','','','',7,1),(8,236,'Tribal',1,1,'','','','',8,1),(9,431,'Endlessness',1,1,'','','','',9,1),(10,239,'All the Works of Nature Which Adorn the World - Vista',1,1,'','','','',1,2),(11,215,'All the Works of Nature Which Adorn the World - The Blue',1,1,'','','','',2,2),(12,282,'All the Works of Nature Which Adorn the World - The Green',1,1,'','','','',3,2),(13,284,'All the Works of Nature Which Adorn the World - Moors',1,1,'','','','',4,2),(14,127,'All the Works of Nature Which Adorn the World - Aurorae',1,1,'','','','',5,2),(15,245,'All the Works of Nature Which Adorn the World - Quiet as the Show',1,1,'','','','',6,2),(16,185,'All the Works of Nature Which Adorn the World - Anthropocene (Including _Hurrian Hymn to Nikkal_)',1,1,'','','','',7,2),(17,281,'All the Works of Nature Which Adorn the World - Ad Astra',1,1,'','','','',8,2),(18,1439,'The Greatest Show On Earth',1,2,'','','Remastered','',1,15),(19,287,'Élan',1,2,'',NULL,'Remastered','',2,15),(20,282,'My Walden',1,2,'','','Remastered','',3,15),(21,330,'Storytime',1,2,'','','Remastered','',4,15),(22,311,'I Want My Tears Back',1,2,'','','Remastered','',5,15),(23,237,'Amaranth',1,2,'','','Remastered','',6,15),(24,834,'The Poet And The Pendulum',1,2,'','','Remastered','',7,15),(25,276,'Nemo',1,2,'','','Remastered','',8,15),(26,242,'Wish I Had An Angel',1,2,'','','Remastered','',9,15),(27,602,'Ghost Love Score',1,2,'','','Remastered','',10,15),(28,274,'Slaying The Dreamer',1,2,'','','Remastered','',11,15),(29,263,'End Of All Hope',1,2,'','','Remastered','',12,15),(30,329,'10th Man Down',1,2,'','','Remastered','',13,15),(31,249,'The Kinslayer',1,2,'','','Remastered','',14,15),(32,412,'Dead Boy\'s Poem',1,2,'','','Remastered','',15,15),(33,322,'Gethsemane',1,2,'','','Remastered','',16,15),(34,286,'Devil & The Deep Dark Ocean',1,2,'','','Remastered','',17,15),(35,254,'Sacrament Of Wilderness',1,2,'','','Remastered','',18,15),(36,273,'Sleeping Sun',1,2,'','','Remastered','',19,15),(37,282,'Elvenpath',1,2,'','','Remastered','',20,15),(38,360,'The Carpemter',1,2,'','','Remastered','',21,15),(39,348,'Nightwish (Demo)',1,2,'','','Remastered','',22,15),(40,72,'Le coeur',2,3,NULL,NULL,NULL,NULL,1,16),(41,265,'House of Usher',2,3,NULL,NULL,NULL,NULL,2,16),(42,214,'The Prophecy',2,3,NULL,NULL,NULL,NULL,3,16),(43,207,'Roderick',2,3,NULL,NULL,NULL,NULL,4,16),(44,243,'Madeline',2,3,NULL,NULL,NULL,NULL,5,16),(45,244,'Fear',2,3,NULL,NULL,NULL,NULL,6,16),(46,260,'Forbidden Love',2,3,NULL,NULL,NULL,NULL,7,16),(47,249,'The Haunted Place',2,3,NULL,NULL,NULL,NULL,8,16),(48,217,'Tears',2,3,NULL,NULL,NULL,NULL,9,16),(49,243,'Buried Alive',2,3,NULL,NULL,NULL,NULL,10,16),(50,263,'Can You Hear The Demons',2,3,NULL,NULL,NULL,NULL,11,16),(51,251,'The Fall',2,3,NULL,NULL,NULL,NULL,12,16),(52,280,'Falling Star',2,3,NULL,NULL,NULL,NULL,13,16),(53,204,'Open Your Eyes',2,3,NULL,NULL,NULL,NULL,14,16),(54,224,'Ethelred',2,3,NULL,NULL,NULL,NULL,15,16),(57,246,'I\'m the One',4,4,'',NULL,NULL,'',1,17),(58,245,'Sky Is Falling',4,4,'',NULL,NULL,'',2,17),(59,344,'Dragonflies',4,4,'',NULL,NULL,'',3,17),(60,217,'Maybe',4,4,'',NULL,NULL,'',4,17),(61,234,'Golden Coins',4,4,'',NULL,NULL,'',5,17),(62,298,'Luna My Darling',4,4,'',NULL,NULL,'',6,17),(63,208,'Abyss',4,4,'',NULL,NULL,'',7,17),(64,244,'Ghostwoman',4,4,'',NULL,NULL,'',8,17),(65,292,'Breathe Again',4,4,'',NULL,NULL,'',9,17),(67,257,'Symphony Nr. 1, Pt. 2: Darkness of Eternity',4,4,'','Metal',NULL,'',10,17),(68,221,'Talisman',4,5,'','Hard Rock & Metal',NULL,'Amazon.com Song ID: 218726293',1,18),(69,227,'Come Now Follow',4,5,'','Hard Rock & Metal',NULL,'Amazon.com Song ID: 218726294',2,18),(70,299,'Arctica',4,5,'','Hard Rock & Metal',NULL,'Amazon.com Song ID: 218726295',3,18),(71,344,'Ghostly Echoes',4,5,'','Hard Rock & Metal',NULL,'Amazon.com Song ID: 218726296',4,18),(72,192,'Sampo',4,5,'','Hard Rock & Metal',NULL,'Amazon.com Song ID: 218726297',5,18),(73,238,'Blackbird',4,5,'','Hard Rock & Metal',NULL,'Amazon.com Song ID: 218726298',6,18),(74,219,'Field Of Serpents',4,5,'','Hard Rock & Metal',NULL,'Amazon.com Song ID: 218726299',7,18),(75,259,'City Of Destruction',4,5,'','Hard Rock & Metal',NULL,'Amazon.com Song ID: 218726300',8,18),(76,226,'Virvatulen Laulu',4,5,'','Hard Rock & Metal',NULL,'Amazon.com Song ID: 218726301',9,18),(77,444,'War In Heaven',4,5,'','Hard Rock & Metal',NULL,'Amazon.com Song ID: 218726302',10,18),(78,222,'United',4,6,'2020','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000330951479',1,19),(79,256,'Eternal Fire Burning',4,6,'2020','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000330951481',2,19),(80,217,'Looking for You',4,6,'2020','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000330951477',3,19),(81,221,'Two Blades',4,6,'2020','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000330951482',4,19),(82,358,'Symphony Nr.1 Part 3 - Awakening [feat. Fabio Lione]',4,6,'2020','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000330951483',5,19),(83,235,'Go for a Ride',4,6,'2020','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000330951480',6,19),(84,214,'Butterfly',4,6,'2020','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000330951476',7,19),(85,224,'Universe',4,6,'2020','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000330951478',8,19),(86,233,'Lay All Your Love On Me',4,6,'2020','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000330951484',9,19),(87,123,'Au Revoir',4,6,'2020','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000330951475',10,19),(88,259,'Cherish My Memory (Remastered)',4,6,'2020','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000330951474',11,19),(89,259,'Cherish My Memory',4,7,'','Hard Rock & Metal',NULL,'Amazon.com Song ID: 248739631',1,20),(90,191,'Dance Of Life',4,7,'','Hard Rock & Metal',NULL,'Amazon.com Song ID: 248739627',2,20),(91,279,'Magic Forest',4,7,'','Hard Rock & Metal',NULL,'Amazon.com Song ID: 248739633',3,20),(92,230,'Agonizing Night',4,7,'','Hard Rock & Metal',NULL,'Amazon.com Song ID: 248739632',4,20),(93,200,'Warning',4,7,'','Hard Rock & Metal',NULL,'Amazon.com Song ID: 248739630',5,20),(94,208,'Sons Of The Rainbow',4,7,'','Hard Rock & Metal',NULL,'Amazon.com Song ID: 248739621',6,20),(95,231,'I\'m Still Here',4,7,'','Hard Rock & Metal',NULL,'Amazon.com Song ID: 248739623',7,20),(96,227,'Memorial',4,7,'','Hard Rock & Metal',NULL,'Amazon.com Song ID: 248739629',8,20),(97,222,'Endless Silence',4,7,'','Hard Rock & Metal',NULL,'Amazon.com Song ID: 248739624',9,20),(98,267,'Green-Eyed',4,7,'','Hard Rock & Metal',NULL,'Amazon.com Song ID: 248739626',10,20),(99,193,'Dance Of Life (Bonus Instrumental Version)',4,7,'','Hard Rock & Metal',NULL,'Amazon.com Song ID: 248739628',11,20),(100,219,'Warning (Bonus Instrumental Version)',4,7,'','Hard Rock & Metal',NULL,'Amazon.com Song ID: 248739625',12,20),(101,181,'River of Tuoni',4,8,'2009','Hard Rock & Metal',NULL,'Amazon.com Song ID: 210653725',1,21),(102,194,'Wings Are My Eyes',4,8,'2009','Hard Rock & Metal',NULL,'Amazon.com Song ID: 210653726',2,21),(103,221,'Lullaby',4,8,'2009','Hard Rock & Metal',NULL,'Amazon.com Song ID: 210653727',3,21),(104,208,'Valkyries',4,8,'2009','Hard Rock & Metal',NULL,'Amazon.com Song ID: 210653728',4,21),(105,170,'Face of the Maiden',4,8,'2009','Hard Rock & Metal',NULL,'Amazon.com Song ID: 210653729',5,21),(106,263,'My Only Star',4,8,'2009','Hard Rock & Metal',NULL,'Amazon.com Song ID: 210653730',6,21),(107,245,'The Curse',4,8,'2009','Hard Rock & Metal',NULL,'Amazon.com Song ID: 210653731',7,21),(108,242,'Passing Bells',4,8,'2009','Hard Rock & Metal',NULL,'Amazon.com Song ID: 210653732',8,21),(109,210,'Sunrise',4,8,'2009','Hard Rock & Metal',NULL,'Amazon.com Song ID: 210653733',9,21),(110,230,'Evil Inside Me',4,8,'2009','Hard Rock & Metal',NULL,'Amazon.com Song ID: 210653734',10,21),(111,242,'Dreamchaser (Japanese Bonus Track)',4,8,'2009','Hard Rock & Metal',NULL,'Amazon.com Song ID: 210653735',11,21),(112,200,'He Sleeps in a Grove',4,9,'2009','Hard Rock & Metal',NULL,'Amazon.com Song ID: 211786589',1,22),(113,303,'Incubus',4,9,'2009','Hard Rock & Metal',NULL,'Amazon.com Song ID: 211786590',2,22),(114,197,'Kokko- Eagle of Fire',4,9,'2009','Hard Rock & Metal',NULL,'Amazon.com Song ID: 211786591',3,22),(115,250,'Willow of Tears',4,9,'2009','Hard Rock & Metal',NULL,'Amazon.com Song ID: 211786592',4,22),(116,218,'Shallow Waters',4,9,'2009','Hard Rock & Metal',NULL,'Amazon.com Song ID: 211786593',5,22),(117,222,'Lost Soul',4,9,'2009','Hard Rock & Metal',NULL,'Amazon.com Song ID: 211786594',6,22),(118,224,'Sons of Seven Stars',4,9,'2009','Hard Rock & Metal',NULL,'Amazon.com Song ID: 211786595',7,22),(119,247,'Saga',4,9,'2009','Hard Rock & Metal',NULL,'Amazon.com Song ID: 211786596',8,22),(120,225,'Snowmaiden',4,9,'2009','Hard Rock & Metal',NULL,'Amazon.com Song ID: 211786597',9,22),(121,223,'Lionheart',4,9,'2009','Hard Rock & Metal',NULL,'Amazon.com Song ID: 211786598',10,22),(122,270,'Morning Star',4,9,'2009','Hard Rock & Metal',NULL,'Amazon.com Song ID: 211786599',11,22),(123,250,'Birth of the Harp',4,9,'2009','Hard Rock & Metal',NULL,'Amazon.com Song ID: 211786600',12,22),(124,333,'Under The Red Cloud',5,10,'2015','Hard Rock & Metal',NULL,'Amazon.com Song ID: 260207787',1,23),(125,281,'The Four Wise Ones',5,10,'2015','Hard Rock & Metal',NULL,'Amazon.com Song ID: 260207784',2,23),(126,323,'Bad Blood',5,10,'2015','Hard Rock & Metal',NULL,'Amazon.com Song ID: 260207780',3,23),(127,304,'The Skull',5,10,'2015','Hard Rock & Metal',NULL,'Amazon.com Song ID: 260207786',4,23),(128,314,'Death Of A King',5,10,'2015','Hard Rock & Metal',NULL,'Amazon.com Song ID: 260207790',5,23),(129,236,'Sacrifice',5,10,'2015','Hard Rock & Metal',NULL,'Amazon.com Song ID: 260207782',6,23),(130,309,'Dark Path',5,10,'2015','Hard Rock & Metal',NULL,'Amazon.com Song ID: 260207789',7,23),(131,307,'Enemy At The Gates',5,10,'2015','Hard Rock & Metal',NULL,'Amazon.com Song ID: 260207791',8,23),(132,277,'Tree Of Ages',5,10,'2015','Hard Rock & Metal',NULL,'Amazon.com Song ID: 260207779',9,23),(133,315,'White Night',5,10,'2015','Hard Rock & Metal',NULL,'Amazon.com Song ID: 260207781',10,23),(134,207,'Come The Spring (Bonus Track)',5,10,'2015','Hard Rock & Metal',NULL,'Amazon.com Song ID: 260207783',11,23),(135,397,'Winter\'s Sleep (Bonus Track)',5,10,'2015','Hard Rock & Metal',NULL,'Amazon.com Song ID: 260207788',12,23),(136,192,'Prologue (Apprehension)',6,11,NULL,'Metal',NULL,NULL,1,24),(137,198,'No Education',6,11,NULL,'Metal',NULL,NULL,2,24),(138,313,'Faraway',6,11,NULL,'Metal',NULL,NULL,3,24),(139,249,'Somewhere Around Nothing',6,11,NULL,'Metal',NULL,NULL,4,24),(140,202,'Drive',6,11,NULL,'Metal',NULL,NULL,5,24),(141,268,'Cohkka',6,11,NULL,'Metal',NULL,NULL,6,24),(142,247,'Conclusion',6,11,NULL,'Metal',NULL,NULL,7,24),(143,215,'Resurrection',6,11,NULL,'Metal',NULL,NULL,8,24),(144,202,'Heat',6,11,NULL,'Metal',NULL,NULL,9,24),(145,269,'Cortége',6,11,NULL,'Metal',NULL,NULL,10,24),(146,126,'Pandemonium',6,11,NULL,'Metal',NULL,NULL,11,24),(147,237,'Toreador II',6,11,NULL,'Metal',NULL,NULL,12,24),(148,209,'Epilogue (Relief)',6,11,NULL,'Metal',NULL,NULL,13,24),(154,283,'Seemann [Album Version][*]',6,11,NULL,'Metal',NULL,NULL,14,24),(155,313,'Faraway Vol. 2 [Extended Version][*]',6,11,NULL,'Metal',NULL,NULL,15,24),(156,251,'Delusion [*]',6,11,NULL,'Metal',NULL,NULL,16,24),(157,250,'Perdition [*]',6,11,NULL,'Metal',NULL,NULL,17,24),(158,250,'Leave Me Alone [*]',6,11,NULL,'Metal',NULL,NULL,18,24),(159,72,'Tempore Nihil Sanat (Prelude in F minor)',3,12,'2014','Hard Rock & Metal',NULL,'Amazon.com Song ID: 247158834',1,25),(160,223,'Never Forgive, Never Forget',3,12,'2014','Hard Rock & Metal',NULL,'Amazon.com Song ID: 247158828',2,25),(161,255,'War Eternal',3,12,'2014','Hard Rock & Metal',NULL,'Amazon.com Song ID: 247158826',3,25),(162,239,'As The Pages Burn',3,12,'2014','Hard Rock & Metal',NULL,'Amazon.com Song ID: 247158835',4,25),(163,244,'No More Regrets',3,12,'2014','Hard Rock & Metal',NULL,'Amazon.com Song ID: 247158825',5,25),(164,277,'You Will Know My Name',3,12,'2014','Hard Rock & Metal',NULL,'Amazon.com Song ID: 247158838',6,25),(165,70,'Graveyard Of Dreams',3,12,'2014','Hard Rock & Metal',NULL,'Amazon.com Song ID: 247158833',7,25),(166,178,'Stolen Life',3,12,'2014','Hard Rock & Metal',NULL,'Amazon.com Song ID: 247158837',8,25),(167,323,'Time Is Black',3,12,'2014','Hard Rock & Metal',NULL,'Amazon.com Song ID: 247158836',9,25),(168,244,'On And On',3,12,'2014','Hard Rock & Metal',NULL,'Amazon.com Song ID: 247158832',10,25),(169,277,'Avalanche',3,12,'2014','Hard Rock & Metal',NULL,'Amazon.com Song ID: 247158830',11,25),(170,226,'Down To Nothing',3,12,'2014','Hard Rock & Metal',NULL,'Amazon.com Song ID: 247158829',12,25),(171,208,'Not Long For This World',3,12,'2014','Hard Rock & Metal',NULL,'Amazon.com Song ID: 247158827',13,25),(172,182,'Shadow On The Wall',3,12,'2014','Hard Rock & Metal',NULL,'Amazon.com Song ID: 247158831',14,25),(173,78,'Set Flame to the Night',3,13,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 284605279',1,26),(174,195,'The Race',3,13,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 284605280',2,26),(175,235,'Blood in the Water',3,13,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 284605283',3,26),(176,293,'The World Is Yours',3,13,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 284605282',4,26),(177,299,'The Eagle Flies Alone (edit)',3,13,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 284605281',5,26),(178,287,'Reason to Believe',3,13,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 284605276',6,26),(179,230,'Murder Scene',3,13,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 284605284',7,26),(180,288,'First Day in Hell',3,13,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 284605274',8,26),(181,69,'Saturnine',3,13,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 284605272',9,26),(182,400,'Dreams of Retribution',3,13,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 284605278',10,26),(183,245,'My Shadow and I',3,13,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 284605273',11,26),(184,395,'A Fight I Must Win',3,13,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 284605277',12,26),(185,168,'City Baby Attacked by Rats (cover version) [Explicit]',3,13,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 284605275',13,26),(186,211,'Straight to the Heart',7,14,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000316724960',1,27),(187,184,'Bringer of Pain',7,14,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000316724953',2,27),(188,275,'King for a Day',7,14,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000316724955',3,27),(189,279,'Beyond the Burning Skies',7,14,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000316724959',4,27),(190,246,'Familiar Hell [Explicit]',7,14,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000316724949',5,27),(191,274,'Lost in Wars (feat. Tomi Joutsen)',7,14,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000316724947',6,27),(192,216,'Bastard Son of Odin',7,14,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000316724958',7,27),(193,210,'We Will Fight',7,14,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000316724956',8,27),(194,225,'Dancing with the Beast',7,14,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000316724952',9,27),(195,264,'Far from Heaven',7,14,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000316724951',10,27),(196,237,'God of War',7,14,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000316724948',11,27),(197,270,'The Eclipse',7,14,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000316724950',12,27),(198,193,'Rock Trash',7,14,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000316724957',13,27),(199,249,'Unbroken',7,15,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000308642709',1,28),(200,235,'No More Hollywood Endings',7,15,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000308642711',2,28),(201,240,'Eden',7,15,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000308642706',3,28),(202,213,'Unfairy Tales',7,15,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000308642713',4,28),(203,217,'Endless Summer',7,15,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000308642705',5,28),(204,253,'The Hero',7,15,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000308642707',6,28),(205,220,'Piece of Me',7,15,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000308642716',7,28),(206,266,'I Wish',7,15,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000308642708',8,28),(207,370,'Raise Your Fists',7,15,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000308642717',9,28),(208,239,'The Golden Horde',7,15,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000308642714',10,28),(209,243,'World on Fire',7,15,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000308642715',11,28),(210,230,'Bent and Broken',7,15,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000308642712',12,28),(211,230,'My Last Dream',7,15,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000308642710',13,28),(212,269,'Beast in Black',8,16,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 287954988',1,29),(213,302,'Blind and Frozen',8,16,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 287954991',2,29),(214,303,'Blood of a Lion',8,16,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 287954997',3,29),(215,231,'Born Again',8,16,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 287954995',4,29),(216,213,'Zodd the Immortal',8,16,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 287954985',5,29),(217,210,'The Fifth Angel',8,16,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 287954986',6,29),(218,207,'Crazy, Mad, Insane',8,16,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 287954989',7,29),(219,286,'Hell for All Eternity',8,16,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 287954994',8,29),(220,212,'Eternal Fire',8,16,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 287954996',9,29),(221,180,'Go to Hell',8,16,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 287954993',10,29),(222,308,'End of the World',8,16,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 287954990',11,29),(223,335,'Ghost in the Rain',8,16,'2017','Hard Rock & Metal',NULL,'Amazon.com Song ID: 287954992',12,29),(224,208,'Cry out for a Hero',8,17,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000306375190',1,30),(225,235,'From Hell with Love',8,17,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000306375182',2,30),(226,206,'Sweet True Lies',8,17,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000306375186',3,30),(227,243,'Repentless',8,17,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000306375185',4,30),(228,195,'Die by the Blade',8,17,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000306375189',5,30),(229,346,'Oceandeep',8,17,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000306375187',6,30),(230,214,'Unlimited Sin',8,17,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000306375181',7,30),(231,209,'True Believer',8,17,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000306375192',8,30),(232,220,'This Is War',8,17,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000306375191',9,30),(233,263,'Heart of Steel',8,17,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000306375184',10,30),(234,255,'No Surrender',8,17,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000306375193',11,30),(235,232,'Killed by Death',8,17,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000306375183',12,30),(236,245,'No Easy Way Out',8,17,'2019','Hard Rock & Metal',NULL,'Amazon.com Song ID: 200000306375188',13,30);
/*!40000 ALTER TABLE `titels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `track_folders`
--

DROP TABLE IF EXISTS `track_folders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `track_folders` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `track_id` int(10) unsigned NOT NULL,
  `folder_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `track_folders`
--

LOCK TABLES `track_folders` WRITE;
/*!40000 ALTER TABLE `track_folders` DISABLE KEYS */;
/*!40000 ALTER TABLE `track_folders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,3),(6,4);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK3g1j96g94xpk3lpxl2qbl985x` (`username`) USING BTREE,
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','$2a$10$v6bD3dtbwyR8H7IpBbsY2uyThOHTrQQZqatsuGuJeRZ9lH2QpoKCG',''),(6,'wko','$2a$10$H7YU30EtIw9S2vJ/efLOWOUqKP1n7mjq0fYE0sfiGqz8euTYJ6CZq','wko@mms-dresden.de');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'musicdb'
--
/*!50003 DROP FUNCTION IF EXISTS `levenshtein` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `levenshtein`(s1 VARCHAR(255), s2 VARCHAR(255) ) RETURNS int(11)
    DETERMINISTIC
BEGIN
  DECLARE s1_len, s2_len, i, j, c, c_temp, cost INT;
  DECLARE s1_char CHAR;
  -- max strlen=255
  DECLARE cv0, cv1 VARBINARY(256);
  SET s1_len = CHAR_LENGTH(s1), s2_len = CHAR_LENGTH(s2), cv1 = 0x00, j = 1, i = 1, c = 0;
  IF s1 = s2 THEN
   RETURN 0;
  ELSEIF s1_len = 0 THEN
   RETURN s2_len;
  ELSEIF s2_len = 0 THEN
   RETURN s1_len;
  ELSE
  WHILE j <= s2_len DO
   SET cv1 = CONCAT(cv1, UNHEX(HEX(j))), j = j + 1;
  END WHILE;
  WHILE i <= s1_len DO
   SET s1_char = SUBSTRING(s1, i, 1), c = i, cv0 = UNHEX(HEX(i)), j = 1;
   WHILE j <= s2_len DO
    SET c = c + 1;
    IF s1_char = SUBSTRING(s2, j, 1) THEN
     SET cost = 0; ELSE SET cost = 1;
    END IF;
    SET c_temp = CONV(HEX(SUBSTRING(cv1, j, 1)), 16, 10) + cost;
    IF c > c_temp THEN SET c = c_temp; END IF;
    SET c_temp = CONV(HEX(SUBSTRING(cv1, j+1, 1)), 16, 10) + 1;
    IF c > c_temp THEN
     SET c = c_temp;
    END IF;
    SET cv0 = CONCAT(cv0, UNHEX(HEX(c))), j = j + 1;
   END WHILE;
   SET cv1 = cv0, i = i + 1;
  END WHILE;
 END IF;
RETURN c;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-13 12:31:21
