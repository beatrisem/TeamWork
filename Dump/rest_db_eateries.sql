-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: rest_db
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `eateries`
--

DROP TABLE IF EXISTS `eateries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eateries` (
  `id_rest` int NOT NULL AUTO_INCREMENT,
  `name_rest` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `city_rest` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address_rest` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `district_rest` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `free_tables_rest` int NOT NULL,
  `max_free_tables_rest` int NOT NULL,
  `lat_rest` decimal(7,5) NOT NULL,
  `lng_rest` decimal(7,5) NOT NULL,
  `homepage_rest` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `phone_rest` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `username_rest` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_rest`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eateries`
--

LOCK TABLES `eateries` WRITE;
/*!40000 ALTER TABLE `eateries` DISABLE KEYS */;
INSERT INTO `eateries` VALUES (1,'Rocket Bean Roastery','Rīga','Miera iela 29','Centra rajons',11,22,56.96388,24.13171,'www.rocketbeanroastery.com','+371 20 215 120','user1'),(2,'Labietis','Rīga',' Aristida Briāna iela 9a','rajons',7,16,56.96522,24.13050,'www.labietis.lv','+371 25 655 958','user2'),(3,'LIDO Ģertrūde','Rīga','Ģertrūdes iela 54','Latgales priekšpilsēta',6,34,56.95225,24.13166,'lido.lv','+371 27 800 633',NULL),(4,'Folkklubs ALA pagrabs','Rīga','Peldu iela 19','Centra rajons',10,17,56.94614,24.10772,'www.folkklubs.lv','+371 27 796 914',NULL),(5,'DreamWood Riga Shisha Bar&Lounge','Rīga','Augusta Deglava iela 28','Latgales priekšpilsēta',14,17,56.95269,24.15709,'','+371 25 565 252',NULL),(6,'Erebuni Restaurant','Rīga','Augusta Deglava iela 55B','Vidzemes priekšpilsēta,',4,25,56.95074,24.17666,'www.erebuni.lv','+371 67 598 999',NULL),(7,'Hesburger','Rīga','Dzelzavas iela 3',' Vidzemes priekšpilsēta',4,24,56.96479,24.16748,'www.hesburger.lv','+371 26 412 476',NULL),(8,'Picu darbnīca','Rīga','Zemitāna laukums 2','Vidzemes priekšpilsēta',14,20,56.97793,24.18562,'www.picudarbnica.lv','+371 25 522 225',NULL),(9,'Ēlande','Rīga','Brīvības gatve 249','Vidzemes priekšpilsēta',11,17,56.97773,24.18070,'www.elande.lv','+371 67 551 173',NULL),(13,'Anna\'s Garden','Rīga','Mūkusalas iela 44','Zemgales priekšpilsēta',11,14,56.93446,24.10453,NULL,'+371 28 822 608',NULL),(14,'Lido Vērmanītis','Liepāja','Elizabetes iela 65','Centra rajons',8,19,56.95287,24.11978,'www.lido.lv','+371 28 661 642',NULL),(15,'Street Burgers (Blaumaņa)','Rīga','Blaumaņa iela 10','Centra rajons',14,18,56.95490,24.12196,'www.streetburgers.lv','+371 26 627 722',NULL),(16,'Costa Coffee','Rīga','Ieriķu iela 3','Vidzemes priekšpilsēta',3,10,56.96594,24.16191,'www.costa.lv','+371 67 358 182',NULL),(17,'LIDO Domina','Rīga','Domina Shopping, Ieriķu iela 3','Vidzemes priekšpilsēta',10,15,56.96678,24.16195,'lido.lv','+371 67 869 834',NULL),(18,'Arsenal','Daugavpils','Mihaila iela 3',NULL,4,10,55.88344,26.49808,'www.sanmari.lv','+371 27 757 872',NULL),(19,'Caffee','Daugavpils','Unnamed Road',NULL,10,10,55.88140,26.51643,NULL,NULL,NULL),(20,'Hesburger Daugavpils Smilšu 32','Daugavpils','Smilšu iela 32',NULL,4,10,55.87967,26.54259,'hesburger.lv','+371 29 409 371',NULL),(21,'Black Mammut East, LTD','Daugavpils','Vaļņu iela 29',NULL,6,9,55.88730,26.50537,'www.blackmammut.com',NULL,NULL),(22,'Boulangerie','Liepāja','Kuršu iela 2',NULL,7,11,56.50560,21.00817,'www.boulangerie.lv','+371 26 986 336',NULL),(23,'Sushi Boom','Liepāja','Siena iela 12',NULL,7,9,56.50304,21.00867,'www.tiamofood.lv','+371 23 778 483',NULL),(24,'Pakavs','Liepāja','Ganību iela 197/205',NULL,5,9,56.48164,21.01422,NULL,'+371 26 099 841',NULL),(25,'Stender\'s','Kuldīga','Baznīcas iela 17',NULL,5,12,56.96907,21.97115,NULL,'+371 63 322 703',NULL),(26,'Sniedziteskukas','Kuldīga','Planīcas iela 4',NULL,7,10,56.96757,21.95064,NULL,'+371 26 233 552',NULL),(27,'Saules ieleja','Kuldīga','Dzirnavu iela 14',NULL,9,12,56.96443,21.96612,NULL,NULL,NULL),(28,'Terase \"Pīlādzītis\"','Kuldīga','Stendes iela 2A',NULL,12,16,56.97072,21.98081,NULL,'+371 28 960 722',NULL),(29,'Nams, Kafejnīca','Cēsis','Gaujas iela 20',NULL,10,10,57.30927,25.26325,NULL,'+371 26 377 610',NULL),(30,'Kafe PRIEDE','Cēsis','Rīgas iela 27',NULL,7,9,57.08840,25.25460,NULL,'+371 27 212 727',NULL),(31,'Stud','Cēsis','Rīgas iela 19',NULL,8,10,57.31156,25.27214,NULL,NULL,NULL),(32,'Cafe 2Locals','Cēsis','Rīgas iela 24a',NULL,7,7,57.31138,25.27055,'www.cafe2locals.lv','+371 28 811 774',NULL),(33,'Go To Sushi Bar','Cēsis','Rīgas iela 9',NULL,15,17,57.31201,25.27329,NULL,'+371 24 204 050',NULL),(34,'Restaurant Olive','Liepāja','Klaipēdas iela 104C','',13,16,56.48612,21.00524,NULL,NULL,NULL),(53,'Viktorija','Jelgava','Svētes iela 33','',4,8,56.64817,23.71657,'','','');
/*!40000 ALTER TABLE `eateries` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-09 22:25:02
