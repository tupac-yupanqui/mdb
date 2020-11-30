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
  `artist_id` varchar(45) NOT NULL DEFAULT '',
  `release` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `albums`
--

LOCK TABLES `albums` WRITE;
/*!40000 ALTER TABLE `albums` DISABLE KEYS */;
INSERT INTO `albums` VALUES (1,'Human :||: Nature','1',NULL),(2,'Decades','1',NULL);
/*!40000 ALTER TABLE `albums` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artists`
--

DROP TABLE IF EXISTS `artists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artists` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artists`
--

LOCK TABLES `artists` WRITE;
/*!40000 ALTER TABLE `artists` DISABLE KEYS */;
INSERT INTO `artists` VALUES (1,'Nightwish'),(2,'Aeternitas'),(3,'Arch Enemy');
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
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tracks`
--

LOCK TABLES `tracks` WRITE;
/*!40000 ALTER TABLE `tracks` DISABLE KEYS */;
INSERT INTO `tracks` VALUES (1,443,'Music',1,1,'','','','',1,1),(2,340,'Noise',1,1,'','','','',2,1),(3,319,'Shoemaker',1,1,'','','','',3,1),(4,313,'Harvest',1,1,'','','','',4,1),(5,318,'Pan',1,1,'','','','',5,1),(6,302,'How\'s the Heart',1,1,'','','','',6,1),(7,331,'Procession',1,1,'','','','',7,1),(8,236,'Tribal',1,1,'','','','',8,1),(9,431,'Endlessness',1,1,'','','','',9,1),(10,239,'All the Works of Nature Which Adorn the World - Vista',1,1,'','','','',1,2),(11,215,'All the Works of Nature Which Adorn the World - The Blue',1,1,'','','','',2,2),(12,282,'All the Works of Nature Which Adorn the World - The Green',1,1,'','','','',3,2),(13,284,'All the Works of Nature Which Adorn the World - Moors',1,1,'','','','',4,2),(14,127,'All the Works of Nature Which Adorn the World - Aurorae',1,1,'','','','',5,2),(15,245,'All the Works of Nature Which Adorn the World - Quiet as the Show',1,1,'','','','',6,2),(16,185,'All the Works of Nature Which Adorn the World - Anthropocene (Including _Hurrian Hymn to Nikkal_)',1,1,'','','','',7,2),(17,281,'All the Works of Nature Which Adorn the World - Ad Astra',1,1,'','','','',8,2),(18,1439,'The Greatest Show On Earth',1,2,'','','Remastered','',1,0),(19,287,'Élan',1,2,'',NULL,'Remastered','',2,0),(20,282,'My Walden',1,2,'','','Remastered','',3,0),(21,330,'Storytime',1,2,'','','Remastered','',4,0),(22,311,'I Want My Tears Back',1,2,'','','Remastered','',5,0),(23,237,'Amaranth',1,2,'','','Remastered','',6,0),(24,834,'The Poet And The Pendulum',1,2,'','','Remastered','',7,0),(25,276,'Nemo',1,2,'','','Remastered','',8,0),(26,242,'Wish I Had An Angel',1,2,'','','Remastered','',9,0),(27,602,'Ghost Love Score',1,2,'','','Remastered','',10,0),(28,274,'Slaying The Dreamer',1,2,'','','Remastered','',11,0),(29,263,'End Of All Hope',1,2,'','','Remastered','',12,0),(30,329,'10th Man Down',1,2,'','','Remastered','',13,0),(31,249,'The Kinslayer',1,2,'','','Remastered','',14,0),(32,412,'Dead Boy\'s Poem',1,2,'','','Remastered','',15,0),(33,322,'Gethsemane',1,2,'','','Remastered','',16,0),(34,286,'Devil & The Deep Dark Ocean',1,2,'','','Remastered','',17,0),(35,254,'Sacrament Of Wilderness',1,2,'','','Remastered','',18,0),(36,273,'Sleeping Sun',1,2,'','','Remastered','',19,0),(37,282,'Elvenpath',1,2,'','','Remastered','',20,0),(38,360,'The Carpemter',1,2,'','','Remastered','',21,0),(39,348,'Nightwish (Demo)',1,2,'','','Remastered','',22,0);
/*!40000 ALTER TABLE `tracks` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-28 14:21:12
