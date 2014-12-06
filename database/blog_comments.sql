CREATE DATABASE  IF NOT EXISTS `blog` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `blog`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: blog
-- ------------------------------------------------------
-- Server version	5.6.20

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
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` longtext,
  `time_post` datetime DEFAULT NULL,
  `time_edit` datetime DEFAULT NULL,
  `number_of_like` int(11) DEFAULT NULL,
  `postid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,'first comment make it as long as possible and now i dont know how to make it. And now here we are and dot say','2014-02-12 00:00:00','2014-02-12 00:00:00',0,1,1),(2,'second','2014-02-12 00:00:00','2014-02-12 00:00:00',0,1,2),(3,'wewewe','2014-11-27 11:12:00','2014-11-27 11:12:00',0,1,1),(4,'wewewe','2014-11-27 11:18:03','2014-11-27 11:18:03',0,1,1),(5,'wewewe','2014-11-27 11:20:07','2014-11-27 11:20:07',0,1,1),(6,'123213','2014-11-27 12:56:28','2014-11-27 12:56:28',0,1,1),(7,'565656','2014-11-27 12:57:32','2014-11-27 12:57:32',0,1,1),(8,'321321','2014-11-27 12:57:50','2014-11-27 12:57:50',0,1,1),(9,'nnnnnn','2014-11-27 12:58:12','2014-11-27 12:58:12',0,1,1),(10,'eeeeeeeeeeeeeeee','2014-11-27 13:00:29','2014-11-27 13:00:29',0,1,1),(11,'bbbbb','2014-11-27 13:01:05','2014-11-27 13:01:05',0,1,1),(12,'rrrrrr','2014-11-27 13:01:30','2014-11-27 13:01:30',0,1,1),(13,'222222222','2014-11-27 13:01:47','2014-11-27 13:01:47',0,1,1),(14,'rrrrr','2014-11-27 13:03:11','2014-11-27 13:03:11',0,1,1),(15,'hhhh','2014-11-27 13:04:29','2014-11-27 13:04:29',0,1,1),(16,'ccccc','2014-11-27 13:05:04','2014-11-27 13:05:04',0,1,1),(17,'ytryytr','2014-11-27 13:06:00','2014-11-27 13:06:00',0,1,1),(18,'hhhh','2014-11-27 13:06:44','2014-11-27 13:06:44',0,1,1),(19,'43434343','2014-11-27 13:08:13','2014-11-27 13:08:13',0,1,1),(20,'43434343','2014-11-27 13:08:16','2014-11-27 13:08:16',0,1,1),(21,'ewqe','2014-11-27 13:11:15','2014-11-27 13:11:15',0,1,1),(22,'oh la oh la','2014-11-27 13:12:39','2014-11-27 13:12:39',0,1,1),(23,'fdsfds','2014-11-27 13:19:29','2014-11-27 13:19:29',0,1,1),(24,'may be I can do that','2014-11-27 13:19:40','2014-11-27 13:19:40',0,1,1),(25,'Viet\'s comment hrere','2014-11-27 13:22:58','2014-11-27 13:22:58',0,1,1),(26,'Viet\'s comment hrere ds','2014-11-27 13:26:53','2014-11-27 13:26:53',0,1,1),(27,'4324324','2014-11-27 13:28:01','2014-11-27 13:28:01',0,1,1),(28,'4324324ffffffffffffffff ffffffffffffffffffffffffffffffffffffffffff fffffffffffffffffffffffewrewr ewrwerwerwrwrwerw erwerwrewrewrewrw e rwerewrw','2014-11-27 13:28:12','2014-11-27 13:28:12',0,1,1),(29,'really !','2014-11-27 13:36:13','2014-11-27 13:36:13',0,14,1),(30,'dsad','2014-11-27 13:41:14','2014-11-27 13:41:14',0,1,1),(31,'cxzcz','2014-11-27 14:14:00','2014-11-27 14:14:00',0,89,1),(32,'dsadsad','2014-11-27 14:26:09','2014-11-27 14:26:09',0,89,1),(33,'ahaha','2014-11-27 14:40:08','2014-11-27 14:40:08',0,1,1),(34,'dsssssssss','2014-11-27 14:40:47','2014-11-27 14:40:47',0,1,1),(35,'ho ho ho ','2014-11-27 16:43:23','2014-11-27 16:43:23',0,2,1),(36,'body','2014-11-27 17:13:27','2014-11-27 17:13:27',0,104,1),(37,'sdfksfk','2014-11-27 18:10:22','2014-11-27 18:10:22',0,109,1);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-06 20:59:52
