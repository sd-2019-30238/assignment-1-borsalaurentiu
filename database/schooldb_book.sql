-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: schooldb
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(25) NOT NULL,
  `author` varchar(25) NOT NULL,
  `genre` varchar(25) NOT NULL,
  `releaseDate` int(5) NOT NULL,
  `available` tinyint(4) NOT NULL,
  `borrows` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'The Great Gatsby','F. Scott Fitzgerald','Fiction',1925,1,11),(2,'The Grapes of Wrath','John Steinbeck','Fiction',1939,1,22),(3,'Nineteen Eighty-Four','George Orwell','Fiction',1949,1,3),(4,'Ulysses','James Joyce','Fiction',1918,1,5),(5,'Lolita','Vladimir Nabokov','Romance',1955,1,3),(6,'Catch-22','Joseph Heller','Humor',1961,1,16),(7,'The Catcher in the Rye','J. D. Salinger','Romance',1951,1,7),(8,'Beloved','Toni Morrison','Fiction',1987,1,19),(9,'The Sound and the Fury','William Faulkner','American',1929,1,2),(10,'To Kill a Mockingbird','Harper Lee','Fiction',1960,1,4),(11,'The Lord of the Rings','J. R. R. Tolkien','Fantasy',1954,1,11),(12,'Hundred Years of Solitude','Gabriel Garcia Marquez','Fantasy',1967,1,19),(13,'Brave New World','Aldous Huxley','Fantasy',1932,1,21),(14,'To the Lighthouse','Virginia Woolf','Feminism',1927,1,6),(15,'Invisible Man','Ralph Ellison','American',1947,1,7),(16,'Gone with the Wind','Margaret Mitchell','Romance',1936,1,22),(17,'Jane Eyre','Charlotte Bronte','Romance',1987,1,9),(18,'On the Road','Jack Kerouac','Adventure',1957,1,3),(19,'Pride and Prejudice','Jane Austen','Romance',1813,1,14),(20,'Lord of the Flies','William Golding','Dystopia',1954,1,20),(21,'Middlemarch','George Eliot','Romance',1872,1,19),(22,'Anna Karenina','Leo Tolstoy','Romance',1873,1,21),(23,'Animal Farm','George Orwell','Dystopia',1945,1,4),(24,'A Passage to India','E. M. Foster','Fiction',1936,1,22),(25,'In Search of Lost Time','Marcel Proust','Philosophy',1913,1,13);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-11 23:50:40
