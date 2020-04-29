CREATE DATABASE  IF NOT EXISTS `bombapatch` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bombapatch`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: bombapatch
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `campeonato`
--

DROP TABLE IF EXISTS `campeonato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `campeonato` (
  `idCampeonato` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idCampeonato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campeonato`
--

LOCK TABLES `campeonato` WRITE;
/*!40000 ALTER TABLE `campeonato` DISABLE KEYS */;
/*!40000 ALTER TABLE `campeonato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `campeonato_estatistica`
--

DROP TABLE IF EXISTS `campeonato_estatistica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `campeonato_estatistica` (
  `idEstatistica` int NOT NULL AUTO_INCREMENT,
  `idCampeonato` int DEFAULT NULL,
  `ganhadorId` int DEFAULT NULL,
  PRIMARY KEY (`idEstatistica`),
  KEY `FK_q6qolcytvxpofo51isoomoc67` (`idCampeonato`),
  KEY `FK_6fqk1i7bcjsco7ieqa9plxh1f` (`ganhadorId`),
  CONSTRAINT `FK_6fqk1i7bcjsco7ieqa9plxh1f` FOREIGN KEY (`ganhadorId`) REFERENCES `usuario` (`idUsuario`),
  CONSTRAINT `FK_q6qolcytvxpofo51isoomoc67` FOREIGN KEY (`idCampeonato`) REFERENCES `campeonato` (`idCampeonato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campeonato_estatistica`
--

LOCK TABLES `campeonato_estatistica` WRITE;
/*!40000 ALTER TABLE `campeonato_estatistica` DISABLE KEYS */;
/*!40000 ALTER TABLE `campeonato_estatistica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jogador_time`
--

DROP TABLE IF EXISTS `jogador_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jogador_time` (
  `idJogador` int NOT NULL AUTO_INCREMENT,
  `eh_artilheiro` bit(1) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `idTime` int DEFAULT NULL,
  PRIMARY KEY (`idJogador`),
  KEY `FK_igux5viklelycd4141dseo8j8` (`idTime`),
  CONSTRAINT `FK_igux5viklelycd4141dseo8j8` FOREIGN KEY (`idTime`) REFERENCES `time` (`idTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jogador_time`
--

LOCK TABLES `jogador_time` WRITE;
/*!40000 ALTER TABLE `jogador_time` DISABLE KEYS */;
/*!40000 ALTER TABLE `jogador_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partida`
--

DROP TABLE IF EXISTS `partida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partida` (
  `idPartida` int NOT NULL AUTO_INCREMENT,
  `golsdeArtilheiroTime1` int DEFAULT NULL,
  `golsdeArtilheiroTime2` int DEFAULT NULL,
  `pontuacaoTime1` int DEFAULT NULL,
  `pontuacaoTime2` int DEFAULT NULL,
  `idCampeonato` int DEFAULT NULL,
  `time1` int DEFAULT NULL,
  `time2` int DEFAULT NULL,
  PRIMARY KEY (`idPartida`),
  KEY `FK_rafhwhhhiwr8xiitwo77cxmy9` (`idCampeonato`),
  KEY `FK_9gcid6ghv1nymg6emtssqc7do` (`time1`),
  KEY `FK_1ho4pywye9bfts2ts8cicsm8u` (`time2`),
  CONSTRAINT `FK_1ho4pywye9bfts2ts8cicsm8u` FOREIGN KEY (`time2`) REFERENCES `time` (`idTime`),
  CONSTRAINT `FK_9gcid6ghv1nymg6emtssqc7do` FOREIGN KEY (`time1`) REFERENCES `time` (`idTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partida`
--

LOCK TABLES `partida` WRITE;
/*!40000 ALTER TABLE `partida` DISABLE KEYS */;
/*!40000 ALTER TABLE `partida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time`
--

DROP TABLE IF EXISTS `time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time` (
  `idTime` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `pontuacao_total` double DEFAULT NULL,
  `idCampeonatoEstatistica` int DEFAULT NULL,
  `idUsuario` int DEFAULT NULL,
  PRIMARY KEY (`idTime`),
  KEY `FK_14gt88cqmgrvacgdsh7md5bcv` (`idCampeonatoEstatistica`),
  KEY `iduser_idx` (`idUsuario`),
  CONSTRAINT `FK_14gt88cqmgrvacgdsh7md5bcv` FOREIGN KEY (`idCampeonatoEstatistica`) REFERENCES `campeonato_estatistica` (`idEstatistica`),
  CONSTRAINT `iduser` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time`
--

LOCK TABLES `time` WRITE;
/*!40000 ALTER TABLE `time` DISABLE KEYS */;
INSERT INTO `time` VALUES (1,'Barcelona',NULL,NULL,NULL),(2,'Juls',NULL,NULL,NULL),(3,'Flamengo',NULL,NULL,NULL),(4,'Barcelona',NULL,NULL,NULL),(5,'Barcelona',NULL,NULL,NULL),(6,'Barcelona',NULL,NULL,NULL),(7,'Barcelona',NULL,NULL,NULL),(8,'Barcelona',NULL,NULL,NULL);
/*!40000 ALTER TABLE `time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `ehAdmin` tinyint DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `login` varchar(15) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `idTime` int DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `UK_g1orfqvgih1w8s3vyg15fq2b8` (`login`),
  UNIQUE KEY `UK_e7l4s47jhwm6uwx2klw7e6dgi` (`idTime`),
  CONSTRAINT `FK_e7l4s47jhwm6uwx2klw7e6dgi` FOREIGN KEY (`idTime`) REFERENCES `time` (`idTime`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,0,'marte','dsdsd','8d990fba61741dcee59d706ee9b39624',NULL),(3,0,'marte@marte','jnjn','8d990fba61741dcee59d706ee9b39624',NULL),(4,0,'marffte@marte','jffnjn','181f1ac6e0124bd98fc3140cc02918a2',NULL),(6,1,'atum@atum','atum','atum',1),(15,0,'aaa@aaa','aaa','d4fbb7d8d5603db43ac2094f5955787c',NULL),(16,0,'pablow@pablow','pablow','9d385852185d99601bfdaeacf3cd668f',2);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-26  2:49:48
