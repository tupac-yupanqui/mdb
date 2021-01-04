-- MySQL dump 10.16  Distrib 10.1.13-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: musicdb
-- ------------------------------------------------------
-- Server version	10.1.13-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
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
  CONSTRAINT `FKalbum2artist` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `albums`
--

LOCK TABLES `albums` WRITE;
/*!40000 ALTER TABLE `albums` DISABLE KEYS */;
INSERT INTO `albums` VALUES (1,'Human :||: Nature',1,'2020-04-10','cover - HumanNature.jpg','','small - HumanNature.jpg'),(2,'Decades',1,'2019-12-06','cover - Decades.jpg','','small - Decades.jpg'),(3,'House of Usher',2,'2016-10-21','cover - HouseOfUsher.jpg','','small - HouseOfUsher.jpg'),(4,'Darkness of Eternity',4,'2017-11-10','cover - DarknessOfEternity.jpg','','small - DarknessOfEternity.jpg'),(5,'End of Eden',4,'2010-10-20','cover - EndOfEden.jpg','','small - EndOfEden.jpg'),(6,'Looking For You',4,'2020-01-31','cover - LookingForYou.jpg','','small - LookingForYou.jpg'),(7,'Magic Forest',4,'2014-06-27','cover - MagicForest.jpg','','small - MagicForest.jpg'),(8,'River of Tuoni',4,'2008-01-30','cover - RiverOfTuoni.jpg','','small - RiverOfTuoni.jpg'),(9,'The Clounds of Northland Thunder',4,'2009-05-13','cover - TheCloudsOfNorthlandThunder.jpg','','small - TheCloudsOfNorthlandThunder.jpg'),(10,'Under The Red Cloud',5,'2015-09-04','cover - UnderTheRedCloud.jpg','','small - UnderTheRedCloud.jpg'),(11,'Reflections Revised',6,'2003-11-17','cover - ReflectionsRevised.jpg','','small - ReflectionsRevised.jpg'),(12,'War Eternal',3,'2014-06-09','cover - WarEternal.jpg','','small - WarEternal.jpg'),(13,'Will To Power',3,'2017-09-08','cover - WillToPower.jpg','[Explicit]','small - WillToPower.jpg'),(14,'Bringer of Pain',7,'2017-02-17','cover - BringerOfPain.jpg','[Explicit]','small - BringerOfPain.jpg'),(15,'No More Hollywood Endings',7,'2019-03-22','cover - NoMoreHollywoodEndings.jpg','','small - NoMoreHollywoodEndings.jpg'),(16,'Berserker',8,'2017-11-03','cover - Berserker.jpg','','small - Berserker.jpg'),(17,'From Hell With Love',8,'2019-02-08','cover - FromHellWithLove.jpg','','small - FromHellWithLove.jpg'),(18,'Heart Of The Hurricane',9,'2018-08-31','cover - HeartOfTheHurricane.jpg','','small - HeartOfTheHurricane.jpg'),(19,'Horizons',9,'2020-06-17','cover - Horizons.jpg','','small - Horizons.jpg'),(20,'Lost In Forever',9,'2016-02-12','cover - LostInForever.jpg','','small - LostInForever.jpg'),(21,'Lost In Forever',9,'2016-02-12','cover - LostInForeverDeluxe.jpg','[Deluxe Edition]','small - LostInForeverDeluxe.jpg'),(22,'Songs Of Love And Death',9,'2015-02-13','cover - SongsOfLoveAndDeath.jpg','','small - SongsOfLoveAndDeath.jpg'),(23,'Behind The Black Veil',10,'2015-05-08','cover - BehindTheBlackVeil.jpg','','small - BehindTheBlackVeil.jpg'),(24,'The Puzzle',10,'2016-11-18','cover - ThePuzzle.jpg','','small - ThePuzzle.jpg'),(25,'Hunter\'s Moon',11,'2019-02-22','cover - HuntersMoon.jpg','','small - HuntersMoon.jpg'),(26,'Interlude',11,'2013-05-03','cover - Interlude.jpg','','small - Interlude.jpg'),(27,'Lucidity',11,'2006-09-04','cover - Lucidity.jpg','','small - Lucidity.jpg'),(28,'Moonbathers',11,'2016-08-26','cover - Moonbathers.jpg','','small - Moonbathers.jpg'),(29,'The Human Contradiction',11,'2014-04-04','cover - TheHumanContradiction.jpg','','small - TheHumanContradiction.jpg'),(30,'Argia',12,'2014-04-11','cover - Argia.jpg','','small - Argia.jpg'),(31,'Dynamind',13,'2019-10-25','cover - Dynamind.jpg','','small - Dynamind.jpg'),(32,'Soliaire',13,'2010-07-22','cover - Solitaire.jpg','','small - Solitaire.jpg'),(33,'The Bonding',13,'2013-06-21','cover - TheBonding.jpg','','small - TheBonding.jpg'),(34,'Until Dawn',14,'2018-06-01','cover - UntilDawn.jpg','','small - UntilDawn.jpg'),(35,'Best Of',15,'2013-10-14','cover - BestOfEpica.png','','small - BestOfEpica.jpg'),(36,'Consign To Oblivion',15,'2015-11-24','cover - ConsignToOblivion.jpg','(Expanded Edition)','small - ConsignToOblivion.jpg'),(37,'Design Your Universe',15,'2009-10-16','cover - DesignYourUniverse.jpg','','small - DesignYourUniverse.jpg'),(38,'Epica vs. Attack on Titan Songs',15,'2018-07-20','cover - EpicaVsAttackOnTitanSongs.jpg','','small - EpicaVsAttackOnTitanSongs.jpg'),(39,'Requiem For The Indifferent',15,'2012-03-26','cover - RequiemForTheIndifferent.jpg','(Special Edition)','small - RequiemForTheIndifferent.jpg'),(40,'The Classical Conspiracy',15,'2009-05-11','cover - TheClassicalConspiracy.jpg','','small - TheClassicalConspiracy.jpg'),(41,'The Divine Conspiracy',15,'2007-09-07','cover - TheDivineConspiracy.png','','small - TheDivineConspiracy.jpg'),(42,'The Holographic Principle',15,'2016-09-30','cover - TheHolographicPrinciple.jpg','','small - TheHolographicPrinciple.jpg'),(43,'The Phantom Agony',15,'2013-03-18','cover - ThePhantomAgony.jpg','(Expanded Edition)','small - ThePhantomAgony.jpg'),(44,'The Quantum Enigma',15,'2014-05-02','cover - TheQuantumEnigma.jpg','','small - TheQuantumEnigma.jpg'),(45,'The Solace System',15,'2017-09-01','cover - TheSolaceSystem.jpg','','small - TheSolaceSystem.jpg'),(46,'Twilight',16,'2001-06-18','cover - Twilight.jpg','','small - Twilight.jpg'),(47,'Fallen',17,'2003-05-19','cover - Fallen.jpg','','small - Fallen.jpg'),(48,'The Legacy Of Atlantis',18,'2018-10-18','cover - TheLegacyOfAtlantis.jpg','','small - TheLegacyOfAtlantis.jpg'),(49,'Sign Of The Dragonheart',19,'2018-01-12','cover - SignOfTheDragonhead.jpg','','small - SignOfTheDragonhead.jpg'),(50,'S&M2',20,'2020-08-28','cover - SM2.png','','small - SM2.jpg'),(51,'Fallen Angels In The Hell',21,'2016-05-12','cover - FallenAngelsInTheHell.jpg','','small - FallenAngelsInTheHell.jpg'),(52,'For All Beyond',21,'2018-04-19','cover - ForAllBeyond.jpg','','small - ForAllBeyond.jpg'),(53,'Destined Ways',22,'2014-07-11','cover - DestinedWays.jpg','','small - DestinedWays.jpg'),(54,'Angels Fall First',1,'1997-11-01','cover - AngelsFallFirst.jpg','','small - AngelsFallFirst.jpg'),(55,'Century Child',1,'2002-06-24','cover - CenturyChild.jpg','','small - CenturyChild.jpg'),(56,'Dark Passion Play',1,'2007-09-26','cover - DarkPassionPlay.jpg','(Special Deluxe Edition)','small - DarkPassionPlay.jpg'),(57,'Élan',1,'2015-02-13','cover - Elan.jpg','','small - Elan.jpg'),(58,'End of an Era',1,'2006-06-22','cover - EndOfAnEra.png','','small - EndOfAnEra.jpg'),(59,'Endless Forms Most Beautiful',1,'2015-03-27','cover - EndlessFormsMostBeautiful.jpg','(Deluxe Version)','small - EndlessFormsMostBeautiful.jpg'),(60,'Highest Hopes',1,'2005-09-23','cover - HighestHopes.jpg','','small - HighestHopes.jpg'),(61,'Imaginaerum',1,'2011-12-02','cover - Imaginaerum.jpg','','small - Imaginaerum.jpg'),(62,'Oceanborn',1,'1999-08-30','cover - Oceanborn.jpg','','small - Oceanborn.jpg'),(63,'Once',1,'2004-06-06','cover - Once.jpg','','small - Once.jpg'),(64,'Over th Hills and Far Away',1,'2001-06-25','cover - OverTheHillsAndFarAway.jpg','','small - OverTheHillsAndFarAway.jpg'),(65,'Showtime, Storytime',1,'2013-11-29','cover - ShowtimeStorytime.jpg','','small - ShowtimeStorytime.jpg'),(66,'Sleeping Sun',1,'2005-10-19','cover - SleepingSun.jpg','','small - SleepingSun.jpg'),(67,'Tales from Elvenpath',1,'2004-10-18','cover - TalesFromElvenpath.jpg','','small - TalesFromElvenpath.jpg'),(68,'Walking in the Air',1,'2011-05-27','cover - WalkingInTheAir.jpg','The Greatest Ballads','small - WalkingInTheAir.jpg'),(69,'Wishmaster',1,'2000-05-29','cover - Wishmaster.jpg','','small - Wishmaster.jpg'),(70,'Best Of The Blessed',23,'2020-07-03','cover - BestOfTheBlessed.jpg','','small - BestOfTheBlessed.jpg'),(71,'Bible Of The Beast',23,'2009-04-27','cover - BibleOfTheBeast.jpg','','small - BibleOfTheBeast.jpg'),(72,'Blessed & Possessed',23,'2015-07-17','cover - BlessedPossessed.jpg','','small - BlessedPossessed.jpg'),(73,'Lupus Dei',23,'2007-05-04','cover - LupusDei.jpg','','small - LupusDei.jpg'),(74,'Metallum Nostrum',23,'2019-01-11','cover - MetallumNostrum.jpg','','small - MetallumNostrum.jpg'),(75,'Preachers Of The Night',23,'2013-06-28','cover - PreachersOfTheNight.jpg','','small - PreachersOfTheNight.jpg'),(76,'The Sacrament Of Sin',23,'2018-07-20','cover - TheSacramentOfSin.jpg','','small - TheSacramentOfSin.jpg'),(77,'Blood Of The Saints',23,'2011-07-29','cover - BloodOfTheSaints.jpg','','small - BloodOfTheSaints.jpg'),(78,'Bismarck',24,'2019-05-17','cover - Bismarck.jpg','','small - Bismarck.jpg'),(79,'Carolus Rex',24,'2012-05-25','cover - CarolusRex.jpg','','small - CarolusRex.jpg'),(80,'Heroes',24,'2014-05-16','cover - Heroes.jpg','','small - Heroes.jpg'),(81,'Primo Victoria',24,'2010-09-26','cover - PrimoVictoria.jpg','(Re-Armed)','small - PrimoVictoria.jpg'),(82,'The Great War',24,'2019-07-19','cover - TheGreatWar.jpg','[Explicit]','small - TheGreatWar.jpg'),(83,'Lack Of Light',25,'2018-08-31','cover - LackOfLight.jpg','','small - LackOfLight.jpg'),(84,'The Serpent\'s Kiss',26,'2018-01-01','cover - TheSerpentsKiss.jpg','(Second Edition)','small - TheSerpentsKiss.jpg'),(85,'Best Of',27,'2016-05-06','cover - BestOfStratovarius.jpg','','small - BestOfStratovarius.jpg'),(86,'Act II',28,'2018-07-27','cover - ActII.jpg','','small - ActII.jpg'),(87,'In The Raw',28,'2019-08-30','cover - InTheRaw.jpg','','small - InTheRaw.jpg'),(88,'Left In The Dark',28,'2014-07-04','cover - LeftInTheDark.jpg','','small - LeftInTheDark.jpg'),(89,'My Winter Storm',28,'2007-11-14','cover - MyWinterStorm.jpg','','small - MyWinterStorm.jpg'),(90,'The Shadow Self',28,'2016-08-05','cover - TheShadowSelf.jpg','','small - TheShadowSelf.jpg'),(91,'Aegis',29,'1998-05-11','cover - Aegis.jpg','','small - Aegis.jpg'),(92,'Crowning Of Atlantis',30,'1999-06-07','cover - CrowningOfAtlantis.jpg','','small - CrowningOfAtlantis.jpg'),(93,'Deggial',30,'2000-01-31','cover - Deggial.jpg','','small - Deggial.jpg'),(94,'Gothic Kabbalah',30,'2007-01-12','cover - GothicKabbalah.jpg','','small - GothicKabbalah.jpg'),(95,'Lemuria',30,'2004-05-24','cover - Lemuria.jpg','','small - Lemuria.jpg'),(96,'Les Fleurs du Mal',30,'2012-09-28','cover - LesFleursDuMal.jpg','','small - LesFleursDuMal.jpg'),(97,'Secret Of The Runes',30,'2001-10-08','cover - SecretOfTheRunes.jpg','','small - SecretOfTheRunes.jpg'),(98,'Sirius B',30,'2004-05-24','cover - SiriusB.jpg','','small - SiriusB.jpg'),(99,'Sitra Ahra',30,'2010-09-17','cover - SitraAhra.jpg','','small - SitraAhra.jpg'),(100,'Theli',30,'1996-08-09','cover - Theli.jpg','','small - Theli.jpg'),(101,'Vovin',30,'1998-05-04','cover - Vovin.jpg','','small - Vovin.jpg'),(102,'Cast Away',31,'2004-11-29','cover - CastAway.jpg','','small - CastAway.jpg'),(103,'Old Routes - New Waters',31,'2016-04-29','cover - OldRoutesNewWaters.jpg','','small - OldRoutesNewWaters.jpg'),(104,'The Deep & The Dark',31,'2018-02-16','cover - TheDeepTheDark.jpg','','small - TheDeepTheDark.jpg'),(105,'Wanderers',31,'2019-08-30','cover - Wanderers.jpg','','small - Wanderers.jpg'),(106,'Hydra',32,'2014-01-31','cover - Hydra.jpg','','small - Hydra.jpg'),(107,'Mother Earth',32,'2003-01-27','cover - MotherEarth.jpg','','small - MotherEarth.jpg'),(108,'Resist',32,'2019-02-01','cover - Resist.jpg','(Deluxe)','small - Resist.jpg'),(109,'The Heart Of Everything',32,'2007-03-09','cover - TheHeartOfEverything.jpg','','small - TheHeartOfEverything.jpg'),(110,'The Silent Force',32,'2004-11-15','cover - TheSilentForce.jpg','','small - TheSilentForce.jpg'),(111,'The Unforgiving',32,'2011-03-25','cover - TheUnforgiving.jpg','','small - TheUnforgiving.jpg'),(120,'Fire & Ashes',34,'2015-07-31','cover - FireAshes.jpg','','small - FireAshes.jpg'),(128,'India',34,'2005-08-22','cover - India.jpg','','small - India.jpg'),(129,'Kill The Sun',34,'2003-05-03','cover - KillTheSun.jpg','','small - KillTheSun.jpg'),(130,'Neverworlds End',34,'2012-02-22','cover - NeverworldsEnd.jpg','','small - NeverworldsEnd.jpg'),(131,'Ravenheart',34,'2004-05-24','cover - Ravenheart.jpg','','small - Ravenheart.jpg'),(132,'Sacrificium',34,'2014-05-02','cover - Sacrificium.jpg','(Deluxe Edition)','small - Sacrificium.jpg'),(133,'Salomé - The Seventh Veil',34,'2007-05-25','cover - SalomeTheSeventhVeil.jpg','','small - SalomeTheSeventhVeil.jpg'),(135,'Theatre Of Dimensions',34,'2017-01-27','cover - TheatreOfDimensions.png','','small - TheatreOfDimensions.jpg');
/*!40000 ALTER TABLE `albums` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artists`
--

LOCK TABLES `artists` WRITE;
/*!40000 ALTER TABLE `artists` DISABLE KEYS */;
INSERT INTO `artists` VALUES (1,'Nightwish'),(2,'Aeternitas'),(3,'Arch Enemy'),(4,'Amberian Dawn'),(5,'Amorphis'),(6,'Apocalyptica'),(7,'Battle Beast'),(8,'Beast In Black'),(9,'Beyond The Black'),(10,'Dark Sarah'),(11,'Delain'),(12,'Diabulus In Musica'),(13,'Edenbridge'),(14,'Elvellon'),(15,'Epica'),(16,'Erben der Schöpfung'),(17,'Evanescence'),(18,'Imperial Age'),(19,'Leaves\' Eyes'),(20,'Metallica'),(21,'Metalwings'),(22,'Neopera'),(23,'Powerwolf'),(24,'Sabaton'),(25,'Scarlet Dorn'),(26,'Serpentyne'),(27,'Stratovarius'),(28,'Tarja'),(29,'Theatre of Tragedy'),(30,'Therion'),(31,'Visions Of Atlantis'),(32,'Within Temptation'),(34,'Xandria');
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
  `album_id` int(10) unsigned NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `FKsubalbum2album` (`album_id`),
  CONSTRAINT `FKkcadvwefd1d6v9kfuub28p0t4` FOREIGN KEY (`album_id`) REFERENCES `albums` (`id`),
  CONSTRAINT `FKsubalbum2album` FOREIGN KEY (`album_id`) REFERENCES `albums` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subalbums`
--

LOCK TABLES `subalbums` WRITE;
/*!40000 ALTER TABLE `subalbums` DISABLE KEYS */;
INSERT INTO `subalbums` VALUES (1,1,'CD 1'),(2,1,'CD 2');
/*!40000 ALTER TABLE `subalbums` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tracks`
--

DROP TABLE IF EXISTS `tracks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tracks` (
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tracks`
--

LOCK TABLES `tracks` WRITE;
/*!40000 ALTER TABLE `tracks` DISABLE KEYS */;
INSERT INTO `tracks` VALUES (1,443,'Music',1,1,'','','','',1,1),(2,340,'Noise',1,1,'','','','',2,1),(3,319,'Shoemaker',1,1,'','','','',3,1),(4,313,'Harvest',1,1,'','','','',4,1),(5,318,'Pan',1,1,'','','','',5,1),(6,302,'How\'s the Heart',1,1,'','','','',6,1),(7,331,'Procession',1,1,'','','','',7,1),(8,236,'Tribal',1,1,'','','','',8,1),(9,431,'Endlessness',1,1,'','','','',9,1),(10,239,'All the Works of Nature Which Adorn the World - Vista',1,1,'','','','',1,2),(11,215,'All the Works of Nature Which Adorn the World - The Blue',1,1,'','','','',2,2),(12,282,'All the Works of Nature Which Adorn the World - The Green',1,1,'','','','',3,2),(13,284,'All the Works of Nature Which Adorn the World - Moors',1,1,'','','','',4,2),(14,127,'All the Works of Nature Which Adorn the World - Aurorae',1,1,'','','','',5,2),(15,245,'All the Works of Nature Which Adorn the World - Quiet as the Show',1,1,'','','','',6,2),(16,185,'All the Works of Nature Which Adorn the World - Anthropocene (Including _Hurrian Hymn to Nikkal_)',1,1,'','','','',7,2),(17,281,'All the Works of Nature Which Adorn the World - Ad Astra',1,1,'','','','',8,2),(18,1439,'The Greatest Show On Earth',1,2,'','','Remastered','',1,0),(19,287,'Élan',1,2,'',NULL,'Remastered','',2,0),(20,282,'My Walden',1,2,'','','Remastered','',3,0),(21,330,'Storytime',1,2,'','','Remastered','',4,0),(22,311,'I Want My Tears Back',1,2,'','','Remastered','',5,0),(23,237,'Amaranth',1,2,'','','Remastered','',6,0),(24,834,'The Poet And The Pendulum',1,2,'','','Remastered','',7,0),(25,276,'Nemo',1,2,'','','Remastered','',8,0),(26,242,'Wish I Had An Angel',1,2,'','','Remastered','',9,0),(27,602,'Ghost Love Score',1,2,'','','Remastered','',10,0),(28,274,'Slaying The Dreamer',1,2,'','','Remastered','',11,0),(29,263,'End Of All Hope',1,2,'','','Remastered','',12,0),(30,329,'10th Man Down',1,2,'','','Remastered','',13,0),(31,249,'The Kinslayer',1,2,'','','Remastered','',14,0),(32,412,'Dead Boy\'s Poem',1,2,'','','Remastered','',15,0),(33,322,'Gethsemane',1,2,'','','Remastered','',16,0),(34,286,'Devil & The Deep Dark Ocean',1,2,'','','Remastered','',17,0),(35,254,'Sacrament Of Wilderness',1,2,'','','Remastered','',18,0),(36,273,'Sleeping Sun',1,2,'','','Remastered','',19,0),(37,282,'Elvenpath',1,2,'','','Remastered','',20,0),(38,360,'The Carpemter',1,2,'','','Remastered','',21,0),(39,348,'Nightwish (Demo)',1,2,'','','Remastered','',22,0),(40,72,'Le coeur',2,3,NULL,NULL,NULL,NULL,1,0),(41,265,'House of Usher',2,3,NULL,NULL,NULL,NULL,2,0),(42,214,'The Prophecy',2,3,NULL,NULL,NULL,NULL,3,0),(43,207,'Roderick',2,3,NULL,NULL,NULL,NULL,4,0),(44,243,'Madeline',2,3,NULL,NULL,NULL,NULL,5,0),(45,244,'Fear',2,3,NULL,NULL,NULL,NULL,6,0),(46,260,'Forbidden Love',2,3,NULL,NULL,NULL,NULL,7,0),(47,249,'The Haunted Place',2,3,NULL,NULL,NULL,NULL,8,0),(48,217,'Tears',2,3,NULL,NULL,NULL,NULL,9,0),(49,243,'Buried Alive',2,3,NULL,NULL,NULL,NULL,10,0),(50,263,'Can You Hear The Demons',2,3,NULL,NULL,NULL,NULL,11,0),(51,251,'The Fall',2,3,NULL,NULL,NULL,NULL,12,0),(52,280,'Falling Star',2,3,NULL,NULL,NULL,NULL,13,0),(53,204,'Open Your Eyes',2,3,NULL,NULL,NULL,NULL,14,0),(54,224,'Ethelred',2,3,NULL,NULL,NULL,NULL,15,0);
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

-- Dump completed on 2021-01-04  7:50:29
