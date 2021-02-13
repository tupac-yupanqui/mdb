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
INSERT INTO `albums` VALUES (1,'Human :||: Nature',1,'2020-04-10','cover - HumanNature.jpg','','small - HumanNature.jpg'),(2,'Decades',1,'2019-12-06','cover - Decades.jpg','','small - Decades.jpg'),(3,'House of Usher',2,'2016-10-21','cover - HouseOfUsher.jpg','','small - HouseOfUsher.jpg'),(4,'Darkness of Eternity',4,'2017-11-10','cover - DarknessOfEternity.jpg','','small - DarknessOfEternity.jpg'),(5,'End of Eden',4,'2010-10-20','cover - EndOfEden.jpg','','small - EndOfEden.jpg'),(6,'Looking For You',4,'2020-01-31','cover - LookingForYou.jpg','','small - LookingForYou.jpg'),(7,'Magic Forest',4,'2014-06-27','cover - MagicForest.jpg','','small - MagicForest.jpg'),(8,'River of Tuoni',4,'2008-01-30','cover - RiverOfTuoni.jpg','','small - RiverOfTuoni.jpg'),(9,'The Clounds of Northland Thunder',4,'2009-05-13','cover - TheCloudsOfNorthlandThunder.jpg','','small - TheCloudsOfNorthlandThunder.jpg'),(10,'Under The Red Cloud',5,'2015-09-04','cover - UnderTheRedCloud.jpg','','small - UnderTheRedCloud.jpg'),(11,'Reflections Revised',6,'2003-11-17','cover - ReflectionsRevised.jpg','','small - ReflectionsRevised.jpg'),(12,'War Eternal',3,'2014-06-09','cover - WarEternal.jpg','','small - WarEternal.jpg'),(13,'Will To Power',3,'2017-09-08','cover - WillToPower.jpg','[Explicit]','small - WillToPower.jpg'),(14,'Bringer of Pain',7,'2017-02-17','cover - BringerOfPain.jpg','[Explicit]','small - BringerOfPain.jpg'),(15,'No More Hollywood Endings',7,'2019-03-22','cover - NoMoreHollywoodEndings.jpg','','small - NoMoreHollywoodEndings.jpg'),(16,'Berserker',8,'2017-11-03','cover - Berserker.jpg','','small - Berserker.jpg'),(17,'From Hell With Love',8,'2019-02-08','cover - FromHellWithLove.jpg','','small - FromHellWithLove.jpg'),(18,'Heart Of The Hurricane',9,'2018-08-31','cover - HeartOfTheHurricane.jpg','','small - HeartOfTheHurricane.jpg'),(19,'Horizons',9,'2020-06-17','cover - Horizons.jpg','','small - Horizons.jpg'),(20,'Lost In Forever',9,'2016-02-12','cover - LostInForever.jpg','','small - LostInForever.jpg'),(21,'Lost In Forever',9,'2016-02-12','cover - LostInForeverDeluxe.jpg','[Deluxe Edition]','small - LostInForeverDeluxe.jpg'),(22,'Songs Of Love And Death',9,'2015-02-13','cover - SongsOfLoveAndDeath.jpg','','small - SongsOfLoveAndDeath.jpg'),(23,'Behind The Black Veil',10,'2015-05-08','cover - BehindTheBlackVeil.jpg','','small - BehindTheBlackVeil.jpg'),(24,'The Puzzle',10,'2016-11-18','cover - ThePuzzle.jpg','','small - ThePuzzle.jpg'),(25,'Hunter\'s Moon',11,'2019-02-22','cover - HuntersMoon.jpg','','small - HuntersMoon.jpg'),(26,'Interlude',11,'2013-05-03','cover - Interlude.jpg','','small - Interlude.jpg'),(27,'Lucidity',11,'2006-09-04','cover - Lucidity.jpg','','small - Lucidity.jpg'),(28,'Moonbathers',11,'2016-08-26','cover - Moonbathers.jpg','','small - Moonbathers.jpg'),(29,'The Human Contradiction',11,'2014-04-04','cover - TheHumanContradiction.jpg','','small - TheHumanContradiction.jpg'),(30,'Argia',12,'2014-04-11','cover - Argia.jpg','','small - Argia.jpg'),(31,'Dynamind',13,'2019-10-25','cover - Dynamind.jpg','','small - Dynamind.jpg'),(32,'Soliaire',13,'2010-07-22','cover - Solitaire.jpg','','small - Solitaire.jpg'),(33,'The Bonding',13,'2013-06-21','cover - TheBonding.jpg','','small - TheBonding.jpg'),(34,'Until Dawn',14,'2018-06-01','cover - UntilDawn.jpg','','small - UntilDawn.jpg'),(35,'Best Of',15,'2013-10-14','cover - BestOfEpica.png','','small - BestOfEpica.jpg'),(36,'Consign To Oblivion',15,'2015-11-24','cover - ConsignToOblivion.jpg','(Expanded Edition)','small - ConsignToOblivion.jpg'),(37,'Design Your Universe',15,'2009-10-16','cover - DesignYourUniverse.jpg','','small - DesignYourUniverse.jpg'),(38,'Epica vs. Attack on Titan Songs',15,'2018-07-20','cover - EpicaVsAttackOnTitanSongs.jpg','','small - EpicaVsAttackOnTitanSongs.jpg'),(39,'Requiem For The Indifferent',15,'2012-03-26','cover - RequiemForTheIndifferent.jpg','(Special Edition)','small - RequiemForTheIndifferent.jpg'),(40,'The Classical Conspiracy',15,'2009-05-11','cover - TheClassicalConspiracy.jpg','','small - TheClassicalConspiracy.jpg'),(41,'The Divine Conspiracy',15,'2007-09-07','cover - TheDivineConspiracy.png','','small - TheDivineConspiracy.jpg'),(42,'The Holographic Principle',15,'2016-09-30','cover - TheHolographicPrinciple.jpg','','small - TheHolographicPrinciple.jpg'),(43,'The Phantom Agony',15,'2013-03-18','cover - ThePhantomAgony.jpg','(Expanded Edition)','small - ThePhantomAgony.jpg'),(44,'The Quantum Enigma',15,'2014-05-02','cover - TheQuantumEnigma.jpg','','small - TheQuantumEnigma.jpg'),(45,'The Solace System',15,'2017-09-01','cover - TheSolaceSystem.jpg','','small - TheSolaceSystem.jpg'),(46,'Twilight',16,'2001-06-18','cover - Twilight.jpg','','small - Twilight.jpg'),(47,'Fallen',17,'2003-05-19','cover - Fallen.jpg','','small - Fallen.jpg'),(48,'The Legacy Of Atlantis',18,'2018-10-18','cover - TheLegacyOfAtlantis.jpg','','small - TheLegacyOfAtlantis.jpg'),(49,'Sign Of The Dragonheart',19,'2018-01-12','cover - SignOfTheDragonhead.jpg','','small - SignOfTheDragonhead.jpg'),(50,'S&M2',20,'2020-08-28','cover - SM2.png','','small - SM2.jpg'),(51,'Fallen Angels In The Hell',21,'2016-05-12','cover - FallenAngelsInTheHell.jpg','','small - FallenAngelsInTheHell.jpg'),(52,'For All Beyond',21,'2018-04-19','cover - ForAllBeyond.jpg','','small - ForAllBeyond.jpg'),(53,'Destined Ways',22,'2014-07-11','cover - DestinedWays.jpg','','small - DestinedWays.jpg'),(54,'Angels Fall First',1,'1997-11-01','cover - AngelsFallFirst.jpg','','small - AngelsFallFirst.jpg'),(55,'Century Child',1,'2002-06-24','cover - CenturyChild.jpg','','small - CenturyChild.jpg'),(56,'Dark Passion Play',1,'2007-09-26','cover - DarkPassionPlay.jpg','(Special Deluxe Edition)','small - DarkPassionPlay.jpg'),(57,'Élan',1,'2015-02-13','cover - Elan.jpg','','small - Elan.jpg'),(58,'End of an Era',1,'2006-06-22','cover - EndOfAnEra.png','','small - EndOfAnEra.jpg'),(59,'Endless Forms Most Beautiful',1,'2015-03-27','cover - EndlessFormsMostBeautiful.jpg','(Deluxe Version)','small - EndlessFormsMostBeautiful.jpg'),(60,'Highest Hopes',1,'2005-09-23','cover - HighestHopes.jpg','','small - HighestHopes.jpg'),(61,'Imaginaerum',1,'2011-12-02','cover - Imaginaerum.jpg','','small - Imaginaerum.jpg'),(62,'Oceanborn',1,'1999-08-30','cover - Oceanborn.jpg','','small - Oceanborn.jpg'),(63,'Once',1,'2004-06-06','cover - Once.jpg','','small - Once.jpg'),(64,'Over th Hills and Far Away',1,'2001-06-25','cover - OverTheHillsAndFarAway.jpg','','small - OverTheHillsAndFarAway.jpg'),(65,'Showtime, Storytime',1,'2013-11-29','cover - ShowtimeStorytime.jpg','','small - ShowtimeStorytime.jpg'),(66,'Sleeping Sun',1,'2005-10-19','cover - SleepingSun.jpg','','small - SleepingSun.jpg'),(67,'Tales from Elvenpath',1,'2004-10-18','cover - TalesFromElvenpath.jpg','','small - TalesFromElvenpath.jpg'),(68,'Walking in the Air',1,'2011-05-27','cover - WalkingInTheAir.jpg','The Greatest Ballads','small - WalkingInTheAir.jpg'),(69,'Wishmaster',1,'2000-05-29','cover - Wishmaster.jpg','','small - Wishmaster.jpg'),(70,'Best Of The Blessed',23,'2020-07-03','cover - BestOfTheBlessed.jpg','','small - BestOfTheBlessed.jpg'),(71,'Bible Of The Beast',23,'2009-04-27','cover - BibleOfTheBeast.jpg','','small - BibleOfTheBeast.jpg'),(72,'Blessed & Possessed',23,'2015-07-17','cover - BlessedPossessed.jpg','','small - BlessedPossessed.jpg'),(73,'Lupus Dei',23,'2007-05-04','cover - LupusDei.jpg','','small - LupusDei.jpg'),(74,'Metallum Nostrum',23,'2019-01-11','cover - MetallumNostrum.jpg','','small - MetallumNostrum.jpg'),(75,'Preachers Of The Night',23,'2013-06-28','cover - PreachersOfTheNight.jpg','','small - PreachersOfTheNight.jpg'),(76,'The Sacrament Of Sin',23,'2018-07-20','cover - TheSacramentOfSin.jpg','','small - TheSacramentOfSin.jpg'),(77,'Blood Of The Saints',23,'2011-07-29','cover - BloodOfTheSaints.jpg','','small - BloodOfTheSaints.jpg'),(78,'Bismarck',24,'2019-05-17','cover - Bismarck.jpg','','small - Bismarck.jpg'),(79,'Carolus Rex',24,'2012-05-25','cover - CarolusRex.jpg','','small - CarolusRex.jpg'),(80,'Heroes',24,'2014-05-16','cover - Heroes.jpg','','small - Heroes.jpg'),(81,'Primo Victoria',24,'2010-09-26','cover - PrimoVictoria.jpg','(Re-Armed)','small - PrimoVictoria.jpg'),(82,'The Great War',24,'2019-07-19','cover - TheGreatWar.jpg','[Explicit]','small - TheGreatWar.jpg'),(83,'Lack Of Light',25,'2018-08-31','cover - LackOfLight.jpg','','small - LackOfLight.jpg'),(84,'The Serpent\'s Kiss',26,'2018-01-01','cover - TheSerpentsKiss.jpg','(Second Edition)','small - TheSerpentsKiss.jpg'),(85,'Best Of',27,'2016-05-06','cover - BestOfStratovarius.jpg','','small - BestOfStratovarius.jpg'),(86,'Act II',28,'2018-07-27','cover - ActII.jpg','','small - ActII.jpg'),(87,'In The Raw',28,'2019-08-30','cover - InTheRaw.jpg','','small - InTheRaw.jpg'),(88,'Left In The Dark',28,'2014-07-04','cover - LeftInTheDark.jpg','','small - LeftInTheDark.jpg'),(89,'My Winter Storm',28,'2007-11-14','cover - MyWinterStorm.jpg','','small - MyWinterStorm.jpg'),(90,'The Shadow Self',28,'2016-08-05','cover - TheShadowSelf.jpg','','small - TheShadowSelf.jpg'),(91,'Aegis',29,'1998-05-11','cover - Aegis.jpg','','small - Aegis.jpg'),(92,'Crowning Of Atlantis',30,'1999-06-07','cover - CrowningOfAtlantis.jpg','','small - CrowningOfAtlantis.jpg'),(93,'Deggial',30,'2000-01-31','cover - Deggial.jpg','','small - Deggial.jpg'),(94,'Gothic Kabbalah',30,'2007-01-12','cover - GothicKabbalah.jpg','','small - GothicKabbalah.jpg'),(95,'Lemuria',30,'2004-05-24','cover - Lemuria.jpg','','small - Lemuria.jpg'),(96,'Les Fleurs du Mal',30,'2012-09-28','cover - LesFleursDuMal.jpg','','small - LesFleursDuMal.jpg'),(97,'Secret Of The Runes',30,'2001-10-08','cover - SecretOfTheRunes.jpg','','small - SecretOfTheRunes.jpg'),(98,'Sirius B',30,'2004-05-24','cover - SiriusB.jpg','','small - SiriusB.jpg'),(99,'Sitra Ahra',30,'2010-09-17','cover - SitraAhra.jpg','','small - SitraAhra.jpg'),(100,'Theli',30,'1996-08-09','cover - Theli.jpg','','small - Theli.jpg'),(101,'Vovin',30,'1998-05-04','cover - Vovin.jpg','','small - Vovin.jpg'),(102,'Cast Away',31,'2004-11-29','cover - CastAway.jpg','','small - CastAway.jpg'),(103,'Old Routes - New Waters',31,'2016-04-29','cover - OldRoutesNewWaters.jpg','','small - OldRoutesNewWaters.jpg'),(104,'The Deep & The Dark',31,'2018-02-16','cover - TheDeepTheDark.jpg','','small - TheDeepTheDark.jpg'),(105,'Wanderers',31,'2019-08-30','cover - Wanderers.jpg','','small - Wanderers.jpg'),(106,'Hydra',32,'2014-01-31','cover - Hydra.jpg','','small - Hydra.jpg'),(107,'Mother Earth',32,'2003-01-27','cover - MotherEarth.jpg','','small - MotherEarth.jpg'),(108,'Resist',32,'2019-02-01','cover - Resist.jpg','(Deluxe)','small - Resist.jpg'),(109,'The Heart Of Everything',32,'2007-03-09','cover - TheHeartOfEverything.jpg','','small - TheHeartOfEverything.jpg'),(110,'The Silent Force',32,'2004-11-15','cover - TheSilentForce.jpg','','small - TheSilentForce.jpg'),(111,'The Unforgiving',32,'2011-03-25','cover - TheUnforgiving.jpg','','small - TheUnforgiving.jpg'),(120,'Fire & Ashes',34,'2015-07-31','cover - FireAshes.jpg','','small - FireAshes.jpg'),(128,'India',34,'2005-08-22','cover - India.jpg','','small - India.jpg'),(129,'Kill The Sun',34,'2003-05-03','cover - KillTheSun.jpg','','small - KillTheSun.jpg'),(130,'Neverworlds End',34,'2012-02-22','cover - NeverworldsEnd.jpg','','small - NeverworldsEnd.jpg'),(131,'Ravenheart',34,'2004-05-24','cover - Ravenheart.jpg','','small - Ravenheart.jpg'),(132,'Sacrificium',34,'2014-05-02','cover - Sacrificium.jpg','(Deluxe Edition)','small - Sacrificium.jpg'),(133,'Salomé - The Seventh Veil',34,'2007-05-25','cover - SalomeTheSeventhVeil.jpg','','small - SalomeTheSeventhVeil.jpg'),(135,'Theatre Of Dimensions',34,'2017-01-27','cover - TheatreOfDimensions.png','','small - TheatreOfDimensions.jpg'),(136,'The Frozen Throne',35,'2018-11-23','cover - TheFrozenThrone.jpg','','small - TheFrozenThrone.jpg');
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
) ENGINE=InnoDB AUTO_INCREMENT=215 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folders`
--

LOCK TABLES `folders` WRITE;
/*!40000 ALTER TABLE `folders` DISABLE KEYS */;
INSERT INTO `folders` VALUES (1,'Aeternitas',172,1,'ARTIST',2),(2,'House of Usher',1,1,'ALBUM',3),(3,'Amberian Dawn',172,1,'ARTIST',4),(4,'Darkness of Eternity',3,1,'ALBUM',4),(5,'End Of Eden',3,1,'ALBUM',5),(6,'Looking For You',3,1,'ALBUM',6),(7,'Magic Forest',3,1,'ALBUM',7),(8,'River of Tuoni',3,1,'ALBUM',8),(9,'The Clounds of Northland Thunder',3,1,'ALBUM',9),(10,'Amorphis',172,1,'ARTIST',5),(11,'Under The Red Cloud',10,1,'ALBUM',10),(12,'Apocalyptica',172,1,'ARTIST',6),(13,'Reflections Revised Disc 1',12,1,'ALBUM',11),(14,'Arch Enemy',172,1,'ARTIST',3),(15,'War Eternal',14,1,'ALBUM',12),(16,'Will To Power [Explicit]',14,1,'ALBUM',13),(17,'Battle Beast',172,1,'ARTIST',7),(18,'Bringer of Pain [Explicit]',17,1,'ALBUM',14),(19,'No More Hollywood Endings',17,1,'ALBUM',15),(20,'Beast In Black',172,1,'ARTIST',8),(21,'Berserker',20,1,'ALBUM',16),(22,'From Hell With Love',20,1,'ALBUM',17),(23,'Beyond The Black',172,1,'ARTIST',9),(24,'Heart Of The Hurricane',23,1,'ALBUM',18),(25,'Horizons',23,1,'ALBUM',19),(26,'Lost In Forever',23,1,'ALBUM',20),(27,'Lost In Forever [Deluxe Edition]',23,1,'ALBUM',21),(28,'Songs Of Love And Death',23,1,'ALBUM',22),(29,'Dark Sarah',172,1,'ARTIST',10),(30,'Behind the Black Veil',29,1,'ALBUM',23),(31,'The Puzzle',29,1,'ALBUM',24),(32,'Delain',172,1,'ARTIST',11),(33,'Hunter\'s Moon',32,1,'ALBUM',25),(34,'Interlude',32,1,'ALBUM',26),(35,'Licidity',32,1,'ALBUM',27),(36,'Moonbathers',32,1,'ALBUM',28),(37,'The Human Contradiction',32,1,'ALBUM',29),(38,'Diabulus In Musica',172,1,'ARTIST',12),(39,'Argia',38,1,'ALBUM',30),(40,'Edenbridge',172,1,'ARTIST',13),(41,'Dynamind',40,1,'ALBUM',31),(42,'Solitaire',40,1,'ALBUM',32),(43,'The Bonding',40,1,'ALBUM',33),(44,'Elvellon',172,1,'ARTIST',14),(45,'Until Dawn',44,1,'ALBUM',34),(46,'Epica',172,1,'ARTIST',15),(47,'Best Of',46,1,'ALBUM',35),(48,'Consign to Oblivion (Expanded Edition)',46,1,'ALBUM',36),(49,'Design Your Universe',46,1,'ALBUM',37),(50,'Epica vs. Attack on Titan Songs',46,1,'ALBUM',38),(51,'Requiem For The Indifferent (Special Edition)',46,1,'ALBUM',39),(52,'The Classical Conspiracy',46,1,'ALBUM',40),(53,'CD1 Classical Set',52,1,'SUBALBUM',3),(54,'CD2 Epica Set',52,1,'SUBALBUM',4),(55,'The Divine Conspiracy',46,1,'ALBUM',41),(56,'The Holographic Principle',46,1,'ALBUM',42),(57,'The Acoustic Principle',56,1,'SUBALBUM',5),(58,'The Holographic Principle',56,1,'SUBALBUM',6),(59,'The Phantom Agony (Expanded Edition)',46,1,'ALBUM',43),(60,'The Quantum Enigma',46,1,'ALBUM',44),(61,'CD1',60,1,'SUBALBUM',7),(62,'CD2',60,1,'SUBALBUM',8),(63,'The Solace System',46,1,'ALBUM',45),(64,'Erben der Schöpfung',172,1,'ARTIST',16),(65,'Twilight',64,1,'ALBUM',46),(66,'Evanescence',172,1,'ARTIST',17),(67,'Fallen',66,1,'ALBUM',47),(68,'Imperial Age',172,1,'ARTIST',18),(69,'The Legacy of Atlantis',68,1,'ALBUM',48),(70,'Kalidia',172,1,'ARTIST',35),(71,'The Frozen Throne',70,1,'ALBUM',136),(72,'Leaves\' Eyes',172,1,'ARTIST',19),(73,'Sign of the Dragonhead',72,1,'ALBUM',49),(74,'CD 1',73,1,'SUBALBUM',9),(75,'CD 2',73,1,'SUBALBUM',10),(76,'Metallica',172,1,'ARTIST',20),(77,'S&M2',76,1,'ALBUM',50),(78,'Metalwings',172,1,'ARTIST',21),(79,'Fallen Angel in the Hell',78,1,'ALBUM',51),(80,'For All Beyond',78,1,'ALBUM',52),(81,'Neopera',172,1,'ARTIST',22),(82,'Destined Ways',81,1,'ALBUM',53),(83,'Nightwish',172,1,'ARTIST',1),(84,'Angel fall First',83,1,'ALBUM',54),(85,'Century Child',83,1,'ALBUM',55),(86,'Dark Passion Play (Special Deluxe Edition)',83,1,'ALBUM',56),(87,'Decades',83,1,'ALBUM',2),(88,'Élan [+ digital booklet]',83,1,'ALBUM',57),(89,'End of an Era',83,1,'ALBUM',58),(90,'Endless Forms Most Beautiful (Deluxe Version)',83,1,'ALBUM',59),(91,'Highest Hopes',83,1,'ALBUM',60),(92,'Human. -II- Nature',83,1,'ALBUM',1),(93,'CD1',92,1,'SUBALBUM',1),(94,'CD2',92,1,'SUBALBUM',2),(95,'Imaginaerum',83,1,'ALBUM',61),(96,'Oceanborn',83,1,'ALBUM',62),(97,'Once',83,1,'ALBUM',63),(98,'Over the Hills and far away',83,1,'ALBUM',64),(99,'Showtime, Storytime',83,1,'ALBUM',65),(100,'Sleeping Sun',83,1,'ALBUM',66),(101,'Tales from Elvenpath',83,1,'ALBUM',67),(102,'Walking in the Air  The Greatest Ballads',83,1,'ALBUM',68),(103,'Wishmaster',83,1,'ALBUM',69),(104,'Powerwolf',172,1,'ARTIST',23),(105,'Best Of The Blessed',104,1,'ALBUM',70),(106,'Bible of the Beast',104,1,'ALBUM',71),(107,'Blessed & Possessed',104,1,'ALBUM',72),(108,'Blood Of The Saints',104,1,'ALBUM',77),(109,'Lupus Dei',104,1,'ALBUM',73),(110,'Metallum Nostrum',104,1,'ALBUM',74),(111,'Preachers Of The Night',104,1,'ALBUM',75),(112,'MyList',104,1,'LIST',0),(113,'The Sacrament Of Sin',104,1,'ALBUM',76),(114,'Sabaton',172,1,'ARTIST',24),(115,'Bismarck',114,1,'ALBUM',78),(116,'Carolus Rex',114,1,'ALBUM',79),(117,'Heroes',114,1,'ALBUM',80),(118,'Primo Victoria (Re-Armed)',114,1,'ALBUM',81),(119,'The Great War [Explicit]',114,1,'ALBUM',82),(120,'Scarlet Dorn',172,1,'ARTIST',25),(121,'Lack of Light',120,1,'ALBUM',83),(122,'Serpentyne',172,1,'ARTIST',26),(123,'The Serpent\'s Kiss (Second Edition)',122,1,'ALBUM',84),(124,'Stratovarius',172,1,'ARTIST',27),(125,'Best Of',124,1,'ALBUM',85),(126,'Tarja',172,1,'ARTIST',28),(127,'Act II',126,1,'ALBUM',86),(128,'In The Raw',126,1,'ALBUM',87),(129,'Left in the Dark',126,1,'ALBUM',88),(130,'My Winter Storm',126,1,'ALBUM',89),(131,'The Shadow Self',126,1,'ALBUM',90),(132,'Theatre of Tragedy',172,1,'ARTIST',29),(133,'Aegis',132,1,'ALBUM',91),(134,'Therion',172,1,'ARTIST',30),(135,'Crowning Of Atlantis',134,1,'ALBUM',92),(136,'Deggial',134,1,'ALBUM',93),(137,'Gothic Kabbalah',134,1,'ALBUM',94),(138,'Disc 1',137,1,'SUBALBUM',11),(139,'Disc 2',137,1,'SUBALBUM',12),(140,'Lemuria',134,1,'ALBUM',95),(141,'Les Fleurs du Mal',134,1,'ALBUM',96),(142,'Secrets Of The Runes',134,1,'ALBUM',97),(143,'Sirius B',134,1,'ALBUM',98),(144,'Sitra Ahra',134,1,'ALBUM',99),(145,'Theli',134,1,'ALBUM',100),(146,'Vovin',134,1,'ALBUM',101),(147,'Visions Of Atlantis',172,1,'ARTIST',31),(148,'Cast Away',147,1,'ALBUM',102),(149,'Old Routes   New Waters',147,1,'ALBUM',103),(150,'The Deep & The Dark',147,1,'ALBUM',104),(151,'Wanderers',147,1,'ALBUM',105),(152,'Within Temptation',172,1,'ARTIST',32),(153,'Hydra',152,1,'ALBUM',106),(154,'Mother Earth',152,1,'ALBUM',107),(155,'Resist (Deluxe)',152,1,'ALBUM',108),(156,'The Heart Of Everything',152,1,'ALBUM',109),(157,'The Silent Force',152,1,'ALBUM',110),(158,'The Unforgiving',152,1,'ALBUM',111),(159,'Xandria',172,1,'ARTIST',33),(160,'Fire & Ashes',159,1,'ALBUM',120),(161,'India',159,1,'ALBUM',128),(162,'Kill The Sun',159,1,'ALBUM',129),(163,'Neverworlds End',159,1,'ALBUM',130),(164,'Ravenheart',159,1,'ALBUM',131),(165,'Sacrificium (Deluxe Edition)',159,1,'ALBUM',132),(166,'CD 1',165,1,'SUBALBUM',13),(167,'CD 2 ',165,1,'SUBALBUM',14),(168,'Salomé - The Seventh Veil',159,1,'ALBUM',133),(169,'Theatre Of Dimensions',159,1,'ALBUM',135),(171,'The Frozen Throne',170,1,'ALBUM',136),(172,'',0,1,'ROOT',1),(173,'',0,4,'ROOT',4),(178,'Aeternitas',173,4,'ARTIST',2),(179,'Amberian Dawn',173,4,'ARTIST',4),(180,'Amorphis',173,4,'ARTIST',5),(181,'Apocalyptica',173,4,'ARTIST',6),(182,'Arch Enemy',173,4,'ARTIST',3),(183,'Battle Beast',173,4,'ARTIST',7),(184,'Beast In Black',173,4,'ARTIST',8),(185,'Beyond The Black',173,4,'ARTIST',9),(186,'Dark Sarah',173,4,'ARTIST',10),(187,'Delain',173,4,'ARTIST',11),(188,'Diabulus In Musica',173,4,'ARTIST',12),(189,'Edenbridge',173,4,'ARTIST',13),(190,'Elvellon',173,4,'ARTIST',14),(191,'Epica',173,4,'ARTIST',15),(192,'Erben der Schöpfung',173,4,'ARTIST',16),(193,'Evanescence',173,4,'ARTIST',17),(194,'Imperial Age',173,4,'ARTIST',18),(195,'Kalidia',173,4,'ARTIST',35),(196,'Leaves\' Eyes',173,4,'ARTIST',19),(197,'Metallica',173,4,'ARTIST',20),(198,'Metalwings',173,4,'ARTIST',21),(199,'Neopera',173,4,'ARTIST',22),(200,'Nightwish',173,4,'ARTIST',1),(201,'Powerwolf',173,4,'ARTIST',23),(202,'Sabaton',173,4,'ARTIST',24),(203,'Scarlet Dorn',173,4,'ARTIST',25),(204,'Serpentyne',173,4,'ARTIST',26),(205,'Stratovarius',173,4,'ARTIST',27),(206,'Tarja',173,4,'ARTIST',28),(207,'Theatre of Tragedy',173,4,'ARTIST',29),(208,'Therion',173,4,'ARTIST',30),(212,'Visions Of Atlantis',173,4,'ARTIST',31),(213,'Within Temptation',173,4,'ARTIST',32),(214,'Xandria',173,4,'ARTIST',34);
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
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `titels`
--

LOCK TABLES `titels` WRITE;
/*!40000 ALTER TABLE `titels` DISABLE KEYS */;
INSERT INTO `titels` VALUES (1,443,'Music',1,1,'','','','',1,1),(2,340,'Noise',1,1,'','','','',2,1),(3,319,'Shoemaker',1,1,'','','','',3,1),(4,313,'Harvest',1,1,'','','','',4,1),(5,318,'Pan',1,1,'','','','',5,1),(6,302,'How\'s the Heart',1,1,'','','','',6,1),(7,331,'Procession',1,1,'','','','',7,1),(8,236,'Tribal',1,1,'','','','',8,1),(9,431,'Endlessness',1,1,'','','','',9,1),(10,239,'All the Works of Nature Which Adorn the World - Vista',1,1,'','','','',1,2),(11,215,'All the Works of Nature Which Adorn the World - The Blue',1,1,'','','','',2,2),(12,282,'All the Works of Nature Which Adorn the World - The Green',1,1,'','','','',3,2),(13,284,'All the Works of Nature Which Adorn the World - Moors',1,1,'','','','',4,2),(14,127,'All the Works of Nature Which Adorn the World - Aurorae',1,1,'','','','',5,2),(15,245,'All the Works of Nature Which Adorn the World - Quiet as the Show',1,1,'','','','',6,2),(16,185,'All the Works of Nature Which Adorn the World - Anthropocene (Including _Hurrian Hymn to Nikkal_)',1,1,'','','','',7,2),(17,281,'All the Works of Nature Which Adorn the World - Ad Astra',1,1,'','','','',8,2),(18,1439,'The Greatest Show On Earth',1,2,'','','Remastered','',1,15),(19,287,'Élan',1,2,'',NULL,'Remastered','',2,15),(20,282,'My Walden',1,2,'','','Remastered','',3,15),(21,330,'Storytime',1,2,'','','Remastered','',4,15),(22,311,'I Want My Tears Back',1,2,'','','Remastered','',5,15),(23,237,'Amaranth',1,2,'','','Remastered','',6,15),(24,834,'The Poet And The Pendulum',1,2,'','','Remastered','',7,15),(25,276,'Nemo',1,2,'','','Remastered','',8,15),(26,242,'Wish I Had An Angel',1,2,'','','Remastered','',9,15),(27,602,'Ghost Love Score',1,2,'','','Remastered','',10,15),(28,274,'Slaying The Dreamer',1,2,'','','Remastered','',11,15),(29,263,'End Of All Hope',1,2,'','','Remastered','',12,15),(30,329,'10th Man Down',1,2,'','','Remastered','',13,15),(31,249,'The Kinslayer',1,2,'','','Remastered','',14,15),(32,412,'Dead Boy\'s Poem',1,2,'','','Remastered','',15,15),(33,322,'Gethsemane',1,2,'','','Remastered','',16,15),(34,286,'Devil & The Deep Dark Ocean',1,2,'','','Remastered','',17,15),(35,254,'Sacrament Of Wilderness',1,2,'','','Remastered','',18,15),(36,273,'Sleeping Sun',1,2,'','','Remastered','',19,15),(37,282,'Elvenpath',1,2,'','','Remastered','',20,15),(38,360,'The Carpemter',1,2,'','','Remastered','',21,15),(39,348,'Nightwish (Demo)',1,2,'','','Remastered','',22,15),(40,72,'Le coeur',2,3,NULL,NULL,NULL,NULL,1,16),(41,265,'House of Usher',2,3,NULL,NULL,NULL,NULL,2,16),(42,214,'The Prophecy',2,3,NULL,NULL,NULL,NULL,3,16),(43,207,'Roderick',2,3,NULL,NULL,NULL,NULL,4,16),(44,243,'Madeline',2,3,NULL,NULL,NULL,NULL,5,16),(45,244,'Fear',2,3,NULL,NULL,NULL,NULL,6,16),(46,260,'Forbidden Love',2,3,NULL,NULL,NULL,NULL,7,16),(47,249,'The Haunted Place',2,3,NULL,NULL,NULL,NULL,8,16),(48,217,'Tears',2,3,NULL,NULL,NULL,NULL,9,16),(49,243,'Buried Alive',2,3,NULL,NULL,NULL,NULL,10,16),(50,263,'Can You Hear The Demons',2,3,NULL,NULL,NULL,NULL,11,16),(51,251,'The Fall',2,3,NULL,NULL,NULL,NULL,12,16),(52,280,'Falling Star',2,3,NULL,NULL,NULL,NULL,13,16),(53,204,'Open Your Eyes',2,3,NULL,NULL,NULL,NULL,14,16),(54,224,'Ethelred',2,3,NULL,NULL,NULL,NULL,15,16);
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
-- Table structure for table `tracks`
--

DROP TABLE IF EXISTS `tracks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tracks` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `titel_id` int(10) unsigned NOT NULL,
  `folder_id` int(10) unsigned NOT NULL,
  `filename` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tracks`
--

LOCK TABLES `tracks` WRITE;
/*!40000 ALTER TABLE `tracks` DISABLE KEYS */;
INSERT INTO `tracks` VALUES (1,1,93,'01 - Music.mp3'),(2,2,93,'02 - Noise.mp3'),(3,3,93,'03 - Shoemaker.mp3'),(4,4,93,'04 - Harvest.mp3'),(5,5,93,'05 - Pan.mp3'),(6,6,93,'06 - How\'s the Heart_.mp3'),(7,7,93,'07 - Procession.mp3'),(8,8,93,'08 - Tribal.mp3'),(9,9,93,'09 - Endlessness.mp3'),(10,10,94,'10 - All the Works of Nature Which Adorn the World - Vista.mp3'),(11,11,94,'11 - All the Works of Nature Which Adorn the World - The Blue.mp3'),(12,12,94,'12 - All the Works of Nature Which Adorn the World - The Green.mp3'),(13,13,94,'13 - All the Works of Nature Which Adorn the World - Moors.mp3'),(14,14,94,'14 - All the Works of Nature Which Adorn the World - Aurorae.mp3'),(15,15,94,'15 - All the Works of Nature Which Adorn the World - Quiet as the Show.mp3'),(16,16,94,'16 - All the Works of Nature Which Adorn the World - Anthropocene (Including _Hurrian Hymn to Nikkal_).mp3'),(17,17,94,'17 - All the Works of Nature Which Adorn the World - Ad Astra.mp3'),(18,18,94,'01 - The Greatest Show On Earth (Remastered).mp3'),(19,19,87,'02 - Élan (Remastered).mp3'),(20,20,87,'03 - My Walden (Remastered).mp3'),(21,21,87,'04 - Storytime (Remastered).mp3'),(22,22,87,'05 - I Want My Tears Back (Remastered).mp3'),(23,23,87,'06 - Amaranth (Remastered).mp3'),(24,24,87,'07 - The Poet And The Pendulum (Remastered).mp3'),(25,25,87,'08 - Nemo (Remastered).mp3'),(26,26,87,'09 - Wish I Had An Angel (Remastered).mp3'),(27,27,87,'10 - Ghost Love Score (Remastered).mp3'),(28,28,87,'11 - Slaying The Dreamer (Remastered).mp3'),(29,29,87,'12 - End Of All Hope (Remastered).mp3'),(30,30,87,'13 - 10th Man Down (Remastered).mp3'),(31,31,87,'14 - The Kinslayer (Remastered).mp3'),(32,32,87,'15 - Dead Boy\'s Poem (Remastered).mp3'),(33,33,87,'16 - Gethsemane (Remastered).mp3'),(34,34,87,'17 - Devil & The Deep Dark Ocean (Remastered).mp3'),(35,35,87,'18 - Sacrament Of Wilderness (Remastered).mp3'),(36,36,87,'19 - Sleeping Sun (Remastered).mp3'),(37,37,87,'20 - Elvenpath (Remastered).mp3'),(38,38,87,'21 - The Carpenter (Remastered).mp3'),(39,39,87,'22 - Nightwish (Demo, Remastered).mp3');
/*!40000 ALTER TABLE `tracks` ENABLE KEYS */;
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-13  8:10:43
