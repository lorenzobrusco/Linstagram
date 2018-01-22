CREATE DATABASE  IF NOT EXISTS `linstagram` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `linstagram`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: linstagram
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `bookmark`
--

DROP TABLE IF EXISTS `bookmark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookmark` (
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`post_id`),
  KEY `FKkm47dr0i09mor5ks9aaebx15u` (`post_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookmark`
--

LOCK TABLES `bookmark` WRITE;
/*!40000 ALTER TABLE `bookmark` DISABLE KEYS */;
INSERT INTO `bookmark` VALUES (83,139),(83,186),(83,205),(105,116),(106,142),(106,186),(202,107),(202,139);
/*!40000 ALTER TABLE `bookmark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `date` datetime NOT NULL,
  `post` int(11) DEFAULT NULL,
  `user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKomrdwc0ub3x7hvvlyu6htn8ti` (`post`),
  KEY `FKlxlm2octv83r6g7kkfxc9iwbi` (`user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (121,'*_*','2018-01-22 15:20:09',112,105),(190,'buonissimo!!!','2018-01-22 15:31:15',186,106),(192,'il dugongo, sto canticchiando la canzoncina','2018-01-22 15:31:17',181,105),(194,'gnam','2018-01-22 15:31:25',186,106),(197,'woooooooow','2018-01-22 15:32:02',139,106),(199,'ma ci sono i filtri!!!','2018-01-22 15:32:28',186,106),(201,'E infatti non ho inserito l\'hashtag nofilter xD','2018-01-22 15:33:34',186,105),(417,'ahahahhaahahhahhaahhaahahahaaaahhahahahhah','2018-01-22 17:17:29',181,83),(420,'lolxD','2018-01-22 17:17:46',139,83),(428,'ue guagliu ha cangiatu mestiaeree..','2018-01-22 17:19:32',116,83);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `following`
--

DROP TABLE IF EXISTS `following`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `following` (
  `followed` int(11) NOT NULL,
  `following` int(11) NOT NULL,
  PRIMARY KEY (`followed`,`following`),
  KEY `FK1w5wwrp7wokus9qsklsli8qh9` (`following`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `following`
--

LOCK TABLES `following` WRITE;
/*!40000 ALTER TABLE `following` DISABLE KEYS */;
INSERT INTO `following` VALUES (83,105),(83,106),(83,111),(83,202),(105,83),(105,106),(105,111),(105,138),(105,202),(105,232),(106,138),(111,83),(111,105),(111,214),(202,105),(202,106),(214,83),(214,106),(214,111),(214,202),(232,105);
/*!40000 ALTER TABLE `following` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hashtag`
--

DROP TABLE IF EXISTS `hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hashtag` (
  `id` int(11) NOT NULL,
  `counter` int(11) DEFAULT NULL,
  `hashtag` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_c2cuvw1i8500gmqxolpx1snuu` (`hashtag`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hashtag`
--

LOCK TABLES `hashtag` WRITE;
/*!40000 ALTER TABLE `hashtag` DISABLE KEYS */;
INSERT INTO `hashtag` VALUES (125,3,'instagood'),(117,1,'lungalievitazione'),(118,1,'pizza'),(119,1,'italia'),(120,1,'napoli'),(108,2,'nofilter'),(113,1,'trex'),(126,2,'instafood'),(127,2,'sud'),(128,2,'instanature'),(129,2,'like'),(130,3,'likesforlikes'),(131,2,'follows'),(132,2,'vitamin'),(133,2,'arancia'),(134,2,'fruits'),(135,2,'orenge'),(136,2,'instafollowers'),(137,3,'follow4follow'),(140,1,'trexfortrex'),(143,2,'tiramisu'),(182,1,'dugonghi'),(183,1,'felici'),(211,1,'nofiilter'),(216,3,'filter'),(217,1,'exam'),(218,1,'project'),(219,1,'group'),(226,2,'london'),(227,3,'travel'),(231,1,'japan'),(234,1,'pokemon'),(235,2,'gif'),(243,1,'lordofrings'),(343,5,'mountains'),(344,6,'landscape_lover'),(345,5,'treestagram'),(346,5,'trees'),(347,5,'amazing'),(348,5,'huntgram'),(349,5,'view'),(350,5,'treescape'),(351,5,'trip'),(352,5,'landscapestyles'),(353,5,'nature_seekers'),(354,5,'naturelovers'),(355,5,'sky'),(356,5,'landscapes'),(357,5,'merveillesdefrance'),(358,5,'cntraveler'),(359,5,'nature'),(360,5,'awesome_earthpix'),(361,6,'landscape_lovers'),(362,5,'tree'),(363,5,'landscapehunter'),(364,5,'communityfirst'),(365,5,'igmasters'),(366,5,'toptags'),(367,5,'naturelover'),(368,5,'guardiantravelsnaps'),(369,5,'beautifuldestinations'),(370,5,'landscapelovers'),(371,4,'landscape'),(372,5,'main_vision'),(384,1,'photooftheday'),(385,1,'haha'),(386,1,'lol'),(387,1,'focus'),(388,1,'pic'),(389,1,'photos'),(390,1,'pictures'),(391,1,'moment'),(392,1,'followforfollow'),(393,1,'beautiful'),(394,1,'humor'),(395,1,'composition'),(396,1,'pics'),(397,1,'laugh'),(398,1,'insta'),(399,1,'art'),(400,1,'photo'),(401,1,'capture'),(402,1,'picture'),(403,1,'friends'),(404,1,'joke'),(405,1,'picoftheday'),(406,1,'exposure'),(407,1,'crazy'),(408,1,'photography'),(409,1,'snapshot'),(432,0,'allegria'),(433,0,'gatto'),(436,0,'video'),(439,0,'spaghetti'),(440,0,'pasta'),(441,0,'manuelilvuoto');
/*!40000 ALTER TABLE `hashtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hashtag_post`
--

DROP TABLE IF EXISTS `hashtag_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hashtag_post` (
  `id_post` int(11) NOT NULL,
  `id_hashtag` int(11) NOT NULL,
  KEY `FKtkmq1pmaj3u0d6qhhqaydacvv` (`id_hashtag`),
  KEY `FK342fp0fcx4nwc6qea7mj9c5ot` (`id_post`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hashtag_post`
--

LOCK TABLES `hashtag_post` WRITE;
/*!40000 ALTER TABLE `hashtag_post` DISABLE KEYS */;
INSERT INTO `hashtag_post` VALUES (107,108),(112,113),(124,128),(124,127),(124,126),(124,125),(116,120),(116,119),(116,118),(124,129),(116,117),(124,130),(124,131),(124,132),(124,133),(124,134),(124,135),(124,136),(124,137),(139,140),(142,108),(142,143),(149,125),(149,126),(149,127),(149,128),(149,129),(149,130),(149,131),(149,132),(149,133),(149,134),(149,135),(149,136),(149,137),(181,182),(181,183),(186,143),(210,211),(210,143),(215,216),(215,217),(215,218),(215,219),(225,216),(225,226),(225,227),(229,216),(229,226),(229,227),(230,231),(230,227),(233,234),(233,235),(237,235),(242,243),(342,343),(342,344),(342,345),(342,346),(342,347),(342,348),(342,349),(342,350),(342,351),(342,352),(342,353),(342,354),(342,355),(342,356),(342,357),(342,358),(342,359),(342,360),(342,361),(342,362),(342,363),(342,364),(342,365),(342,366),(342,367),(342,368),(342,369),(342,370),(342,371),(342,372),(373,344),(373,361),(374,343),(374,344),(374,345),(374,346),(374,347),(374,348),(374,349),(374,350),(374,351),(374,352),(374,353),(374,354),(374,355),(374,356),(374,357),(374,358),(374,359),(374,360),(374,361),(374,362),(374,363),(374,364),(374,365),(374,366),(374,367),(374,368),(374,369),(374,370),(374,371),(374,372),(376,343),(376,344),(376,345),(376,346),(376,347),(376,348),(376,349),(376,350),(376,351),(376,352),(376,353),(376,354),(376,355),(376,356),(376,357),(376,358),(376,359),(376,360),(376,361),(376,362),(376,363),(376,364),(376,365),(376,366),(376,367),(376,368),(376,369),(376,370),(376,371),(376,372),(377,343),(377,344),(377,345),(377,346),(377,347),(377,348),(377,349),(377,350),(377,351),(377,352),(377,353),(377,354),(377,355),(377,356),(377,357),(377,358),(377,359),(377,360),(377,361),(377,362),(377,363),(377,364),(377,365),(377,366),(377,367),(377,368),(377,369),(377,370),(377,372),(382,343),(382,344),(382,345),(382,346),(382,347),(382,348),(382,349),(382,350),(382,351),(382,352),(382,353),(382,354),(382,355),(382,356),(382,357),(382,358),(382,359),(382,360),(382,361),(382,362),(382,363),(382,364),(382,365),(382,366),(382,367),(382,368),(382,369),(382,370),(382,371),(382,372),(383,384),(383,385),(383,386),(383,387),(383,388),(383,389),(383,390),(383,391),(383,392),(383,393),(383,394),(383,395),(383,396),(383,397),(383,125),(383,398),(383,399),(383,130),(383,400),(383,401),(383,402),(383,403),(383,404),(383,405),(383,406),(383,407),(383,408),(383,409),(383,137),(431,432),(431,433),(435,436),(438,439),(438,440),(438,441),(448,405),(448,359);
/*!40000 ALTER TABLE `hashtag_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (458),(458),(458),(458),(458),(458),(458);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `likes` (
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`post_id`,`user_id`),
  KEY `FKi2wo4dyk4rok7v4kak8sgkwx0` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (107,202),(112,105),(116,83),(116,105),(139,83),(139,106),(139,202),(181,83),(181,105),(181,106),(186,83),(186,106),(205,83),(205,202),(210,83),(210,105),(215,83),(215,105),(215,214),(233,83),(233,105),(242,105),(242,202),(373,105);
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media`
--

DROP TABLE IF EXISTS `media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `media` (
  `post` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  KEY `FK8fuq4in2sxrdes3ekqpe2oa9y` (`post`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media`
--

LOCK TABLES `media` WRITE;
/*!40000 ALTER TABLE `media` DISABLE KEYS */;
INSERT INTO `media` VALUES (124,1,'images/83/1.jpg'),(107,1,'images/105/2.jpg'),(112,1,'images/106/1.gif'),(116,1,'images/106/2.jpg'),(139,1,'images/105/3.gif'),(142,1,'images/106/4.jpg'),(147,1,'images/106/5.jpg'),(149,1,'images/83/2.jpg'),(181,1,'images/106/6.gif'),(186,1,'images/105/4.jpg'),(205,1,'images/105/5.jpg'),(210,1,'images/202/1.jpg'),(215,1,'images/202/2.jpg'),(225,1,'images/202/3.jpg'),(229,1,'images/202/4.jpg'),(230,1,'images/202/5.jpg'),(233,1,'images/202/6.gif'),(237,1,'images/202/7.gif'),(242,1,'images/232/1.jpg'),(342,1,'images/83/7.jpg'),(373,1,'images/83/8.jpg'),(374,1,'images/83/9.jpg'),(376,1,'images/83/10.jpg'),(377,1,'images/83/11.jpg'),(382,1,'images/83/12.jpg'),(383,1,'images/83/13.jpg'),(430,1,'images/111/1.gif'),(431,1,'images/111/2.jpg'),(434,1,'images/111/3.jpg'),(435,0,'images/111/4.mp4'),(438,1,'images/111/5.jpg'),(443,0,'images/111/6.mp4'),(448,1,'images/111/7.jpg'),(457,1,'images/214/2.png');
/*!40000 ALTER TABLE `media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `toSee` bit(1) NOT NULL,
  `type` int(11) NOT NULL,
  `comment` int(11) DEFAULT NULL,
  `post` int(11) DEFAULT NULL,
  `userfrom` int(11) DEFAULT NULL,
  `userto` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk98vlyjwa6hj5gnvavvxw40d5` (`comment`),
  KEY `FKo2vbi9f6yfrxgqji8ocuqge2g` (`post`),
  KEY `FKa6w2p9mafd9yogrpeosy5rb23` (`userfrom`),
  KEY `FKf91p55y0d35ic6mtbr9couqn6` (`userto`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (224,'2018-01-22 15:41:42','\0',2,NULL,215,214,202),(223,'2018-01-22 15:41:01','\0',3,NULL,215,202,111),(222,'2018-01-22 15:41:01','',3,NULL,215,202,106),(221,'2018-01-22 15:41:01','\0',3,NULL,215,202,105),(220,'2018-01-22 15:41:01','\0',3,NULL,215,202,214),(213,'2018-01-22 15:39:06','\0',2,NULL,210,105,202),(212,'2018-01-22 15:38:36','',3,NULL,210,202,138),(209,'2018-01-22 15:38:16','\0',0,NULL,NULL,105,111),(207,'2018-01-22 15:37:53','\0',3,NULL,205,105,111),(206,'2018-01-22 15:37:53','\0',3,NULL,205,105,202),(204,'2018-01-22 15:35:51','\0',0,NULL,NULL,202,105),(203,'2018-01-22 15:34:58','\0',0,NULL,NULL,105,202),(200,'2018-01-22 15:32:28','\0',1,NULL,186,106,105),(198,'2018-01-22 15:32:02','\0',1,NULL,139,106,105),(196,'2018-01-22 15:31:51','\0',2,NULL,139,106,105),(195,'2018-01-22 15:31:25','\0',1,NULL,186,106,105),(193,'2018-01-22 15:31:17','\0',1,NULL,181,105,106),(191,'2018-01-22 15:31:15','\0',1,NULL,186,106,105),(189,'2018-01-22 15:31:02','\0',2,NULL,186,106,105),(188,'2018-01-22 15:30:55','\0',2,NULL,181,105,106),(187,'2018-01-22 15:28:17','',3,NULL,186,105,138),(185,'2018-01-22 15:27:11','\0',3,NULL,181,106,105),(184,'2018-01-22 15:27:11','\0',3,NULL,181,106,83),(148,'2018-01-22 15:25:47','\0',3,NULL,147,106,111),(146,'2018-01-22 15:24:47','',0,NULL,NULL,105,138),(145,'2018-01-22 15:24:35','',0,NULL,NULL,106,138),(144,'2018-01-22 15:24:24','',3,NULL,142,106,138),(141,'2018-01-22 15:24:24','\0',3,NULL,139,105,106),(123,'2018-01-22 15:20:19','\0',2,NULL,116,105,106),(115,'2018-01-22 15:20:04','\0',2,NULL,112,105,106),(114,'2018-01-22 15:19:07','\0',3,NULL,112,106,105),(122,'2018-01-22 15:20:09','\0',1,NULL,112,105,106),(109,'2018-01-22 15:17:01','\0',3,NULL,107,105,106),(228,'2018-01-22 15:42:45','\0',2,NULL,215,105,202),(236,'2018-01-22 15:44:08','',3,NULL,233,202,106),(238,'2018-01-22 15:44:42','',0,NULL,NULL,202,106),(239,'2018-01-22 15:44:48','\0',2,NULL,139,202,105),(240,'2018-01-22 15:45:26','\0',2,NULL,107,202,105),(241,'2018-01-22 15:45:36','\0',2,NULL,205,202,105),(244,'2018-01-22 15:45:56','',3,NULL,242,232,138),(245,'2018-01-22 15:47:23','\0',0,NULL,NULL,232,105),(246,'2018-01-22 15:48:09','\0',2,NULL,233,105,202),(247,'2018-01-22 15:48:30','',2,NULL,242,202,232),(248,'2018-01-22 15:49:04','',0,NULL,NULL,105,232),(249,'2018-01-22 15:49:47','',2,NULL,242,105,232),(250,'2018-01-22 15:50:00','\0',0,NULL,NULL,105,83),(375,'2018-01-22 17:02:31','\0',2,NULL,373,105,83),(378,'2018-01-22 17:06:30','\0',3,NULL,377,83,105),(379,'2018-01-22 17:06:30','',3,NULL,377,83,106),(380,'2018-01-22 17:06:30','',3,NULL,377,83,202),(381,'2018-01-22 17:06:30','\0',3,NULL,377,83,111),(410,'2018-01-22 17:09:27','\0',3,NULL,383,83,111),(421,'2018-01-22 17:17:46','\0',1,NULL,139,83,105),(418,'2018-01-22 17:17:29','',1,NULL,181,83,106),(414,'2018-01-22 17:10:49','\0',0,NULL,NULL,83,111),(415,'2018-01-22 17:11:13','',0,NULL,NULL,83,202),(416,'2018-01-22 17:16:48','',2,NULL,215,83,202),(419,'2018-01-22 17:17:32','',2,NULL,181,83,106),(422,'2018-01-22 17:17:50','\0',2,NULL,139,83,105),(423,'2018-01-22 17:18:03','',2,NULL,233,83,202),(424,'2018-01-22 17:18:09','',2,NULL,210,83,202),(425,'2018-01-22 17:18:17','\0',2,NULL,205,83,105),(426,'2018-01-22 17:18:29','\0',2,NULL,186,83,105),(427,'2018-01-22 17:18:36','',2,NULL,116,83,106),(429,'2018-01-22 17:19:32','',1,NULL,116,83,106),(437,'2018-01-22 17:22:41','',3,NULL,435,111,106),(442,'2018-01-22 17:25:02','',3,NULL,438,111,83),(444,'2018-01-22 17:25:30','',3,NULL,443,111,83),(445,'2018-01-22 17:25:30','\0',3,NULL,443,111,105),(446,'2018-01-22 17:25:30','',3,NULL,443,111,106),(447,'2018-01-22 17:25:30','',3,NULL,443,111,202),(450,'2018-01-22 17:29:56','\0',0,NULL,NULL,214,111),(451,'2018-01-22 17:30:07','',0,NULL,NULL,214,83),(452,'2018-01-22 17:30:14','',0,NULL,NULL,214,202),(453,'2018-01-22 17:30:21','',0,NULL,NULL,214,106),(454,'2018-01-22 17:30:47','\0',0,NULL,NULL,111,105),(455,'2018-01-22 17:30:49','',0,NULL,NULL,111,83),(456,'2018-01-22 17:30:50','',0,NULL,NULL,111,214);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `id` int(11) NOT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `postDate` datetime NOT NULL,
  `user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1qdb5fhxbh7hg92ni2n9xgsns` (`user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (147,' @lorenzo','2018-01-22 15:25:47',106),(116,' #pizza #lungalievitazione #italia #napoli','2018-01-22 15:20:06',106),(142,' #tiramisu #nofilter @bernardo','2018-01-22 15:24:24',106),(107,' #nofilter @paola','2018-01-22 15:17:01',105),(112,'#trex @eliana','2018-01-22 15:19:06',106),(139,' @paola #trexfortrex','2018-01-22 15:24:24',105),(149,' #orenge #fruits #sud #instagood #follow4follow #vitamin #like #instanature #instafood #likesforlikes #arancia #follows #instafollowers','2018-01-22 15:25:48',83),(181,' #dugonghi #felici @eliana @dragmaf','2018-01-22 15:27:10',106),(186,' #tiramisu @bernardo','2018-01-22 15:28:17',105),(205,'Anche io inserisco foto di paesaggi @lorenzo @alessio','2018-01-22 15:37:53',105),(210,'#tiramisu #nofiilter @bernardo','2018-01-22 15:38:36',202),(215,' #filter #group #project #exam @paola @eliana @lorenzo @manuel','2018-01-22 15:41:00',202),(225,'Londra 2016.. \r\n#filter #london #travel ','2018-01-22 15:42:02',202),(229,' #filter #london #travel','2018-01-22 15:42:57',202),(230,' #japan #travel','2018-01-22 15:43:43',202),(233,' #gif #pokemon @paola','2018-01-22 15:44:08',202),(237,' #gif','2018-01-22 15:44:26',202),(242,' #lordofrings @bernardo','2018-01-22 15:45:56',232),(342,' C\'Ã¨ un sole perfetto ma lei vuole la luna. #landscape #amazing #view #trip #tree #sky #mountains #nature #landscape_lovers #landscapelovers #landscape_lover #landscapehunter #landscapes #toptags #landscapestyles #trees #treestagram #treescape #naturelovers #naturelover #nature_seekers #merveillesdefrance #cntraveler #communityfirst #igmasters #huntgram #main_vision #guardiantravelsnaps #beautifuldestinations #awesome_earthpix\r\n','2018-01-22 16:25:57',83),(373,' #landscape_lovers #landscape_lover','2018-01-22 16:50:01',83),(374,'#landscape #amazing #view #trip #tree #sky #mountains #nature #landscape_lovers #landscapelovers #landscape_lover #landscapehunter #landscapes #toptags #landscapestyles #trees #treestagram #treescape #naturelovers #naturelover #nature_seekers #merveillesdefrance #cntraveler #communityfirst #igmasters #huntgram #main_vision #guardiantravelsnaps #beautifuldestinations #awesome_earthpix\r\n','2018-01-22 17:01:26',83),(376,' #landscape#amazing#view#trip#tree#sky#mountains#nature#landscape_lovers#landscapelovers #landscape_lover #landscapehunter #landscapes #toptags #landscapestyles#trees#treestagram #treescape #naturelovers #naturelover #nature_seekers #merveillesdefrance #cntraveler #communityfirst #igmasters #huntgram#main_vision#guardiantravelsnaps#beautifuldestinations#awesome_earthpix\r\n','2018-01-22 17:03:19',83),(377,' Ansia a grappoli. @eliana @paola @lorenzo @alessio landscape #amazing #view #trip #tree #sky #mountains #nature #landscape_lovers #landscapelovers #landscape_lover #landscapehunter #landscapes #toptags #landscapestyles #trees #treestagram #treescape #naturelovers #naturelover #nature_seekers #merveillesdefrance #cntraveler #communityfirst #igmasters #huntgram #main_vision #guardiantravelsnaps #beautifuldestinations #awesome_earthpix\r\n','2018-01-22 17:06:30',83),(382,'Stranger things. #landscape #amazing #view #trip #tree #sky #mountains #nature #landscape_lovers #landscapelovers #landscape_lover #landscapehunter #landscapes #toptags #landscapestyles #trees #treestagram #treescape #naturelovers #naturelover #nature_seekers #merveillesdefrance #cntraveler #communityfirst #igmasters #huntgram #main_vision #guardiantravelsnaps #beautifuldestinations #awesome_earthpix','2018-01-22 17:08:14',83),(383,' Raccontami una storia. @lorenzo #photography #photo #photos #pic #pics #picture #pictures #snapshot #art #beautiful #instagood #picoftheday #photooftheday #follow4follow #likesforlikes #exposure #composition #focus #capture #moment #followforfollow #lol #laugh #crazy #friends #insta #joke #haha #humor #likesforlikes','2018-01-22 17:09:27',83),(430,' ','2018-01-22 17:20:53',111),(431,' #gatto #allegria ','2018-01-22 17:21:18',111),(434,' ','2018-01-22 17:21:43',111),(435,' #video @paola','2018-01-22 17:22:41',111),(438,'#pasta #manuelilvuoto #spaghetti @dragmaf ','2018-01-22 17:25:02',111),(443,' @paola @eliana @alessio @dragmaf','2018-01-22 17:25:30',111),(448,' #nature #picoftheday','2018-01-22 17:26:33',111),(457,' ','2018-01-22 17:34:55',214);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requestfollow`
--

DROP TABLE IF EXISTS `requestfollow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requestfollow` (
  `id` int(11) NOT NULL,
  `userfrom` int(11) DEFAULT NULL,
  `userto` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK15nw1bny7f3pavpvyqpfr4vjp` (`userfrom`),
  KEY `FKdsqqyaj9ad9mp0mvm0vx3gr9x` (`userto`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requestfollow`
--

LOCK TABLES `requestfollow` WRITE;
/*!40000 ALTER TABLE `requestfollow` DISABLE KEYS */;
/*!40000 ALTER TABLE `requestfollow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `story`
--

DROP TABLE IF EXISTS `story`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `story` (
  `id` int(11) NOT NULL,
  `creationDate` datetime NOT NULL,
  `media_type` int(11) DEFAULT NULL,
  `media_url` varchar(255) DEFAULT NULL,
  `user` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKokqrxj3v1qj2qxecbmq4l0skc` (`user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `story`
--

LOCK TABLES `story` WRITE;
/*!40000 ALTER TABLE `story` DISABLE KEYS */;
/*!40000 ALTER TABLE `story` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tags` (
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`post_id`,`user_id`),
  KEY `FKgtny0im70dxrydf5dg091ql9x` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT INTO `tags` VALUES (107,106),(112,105),(139,106),(142,138),(147,111),(181,83),(181,105),(186,138),(205,111),(205,202),(210,138),(215,105),(215,106),(215,111),(215,214),(233,106),(242,138),(377,105),(377,106),(377,111),(377,202),(383,111),(435,106),(438,83),(443,83),(443,105),(443,106),(443,202);
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `biography` varchar(255) DEFAULT NULL,
  `birthdate` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `gender` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `photoProfile` varchar(255) DEFAULT NULL,
  `privateProfile` bit(1) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (138,NULL,NULL,'cuteri@mat.unical.it',NULL,NULL,'7C4A8D09CA3762AF61E59520943DC26494F8941B','images/138/photoProfile.jpg','',NULL,'bernardo'),(202,NULL,NULL,'alessio@g.it',NULL,NULL,'7C4A8D09CA3762AF61E59520943DC26494F8941B','images/202/photoProfile.jpg','\0',NULL,'alessio'),(214,NULL,'1990-02-03 00:00:00','manuel@gmail.com',0,'Manuele','7C4A8D09CA3762AF61E59520943DC26494F8941B','images/214/photoProfile.jpg','','Brazorf','manuel'),(106,NULL,NULL,'paola@paola.pa',NULL,'Paola','7C4A8D09CA3762AF61E59520943DC26494F8941B','images/106/photoProfile.jpg','\0',NULL,'paola'),(111,NULL,'1992-04-17 00:00:00','lorenzo@gmail.com',0,'lorenzo','7C4A8D09CA3762AF61E59520943DC26494F8941B','images/111/photoProfile.jpg','','brusco','lorenzo'),(83,'e niente...ciao','1993-04-09 00:00:00','dragmaf@gmail.com',0,'Manuel Fortunato','6D7CD9BBCCBE9A319B85DA5634B4E4117727E6F0','images/83/photoProfile.jpg','\0','Drago','dragmaf'),(105,NULL,'1992-10-09 00:00:00','eliana-c@live.it',1,'Eliana','7C4A8D09CA3762AF61E59520943DC26494F8941B','images/105/photoProfile.jpeg','\0','Cannella','eliana'),(232,NULL,NULL,'davide@gmail.com',NULL,NULL,'7C4A8D09CA3762AF61E59520943DC26494F8941B','images/default.png','\0',NULL,'davide');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `viewed_story`
--

DROP TABLE IF EXISTS `viewed_story`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `viewed_story` (
  `story_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`story_id`,`user_id`),
  KEY `FKjdkpyj9ku130mioqsbgru1yc9` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `viewed_story`
--

LOCK TABLES `viewed_story` WRITE;
/*!40000 ALTER TABLE `viewed_story` DISABLE KEYS */;
/*!40000 ALTER TABLE `viewed_story` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-22 17:35:38
