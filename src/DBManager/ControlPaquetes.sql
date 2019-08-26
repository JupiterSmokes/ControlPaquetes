-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ControlPaquetes
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `almacen`
--

DROP TABLE IF EXISTS `almacen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `almacen` (
  `CodEnvio` int(11) NOT NULL,
  `CodPC` int(11) DEFAULT NULL,
  `CodRuta` int(11) DEFAULT NULL,
  `Destino` varchar(100) DEFAULT NULL,
  `Tiempo` int(11) DEFAULT '1',
  `TarifaA` decimal(10,0) NOT NULL,
  `Costo` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`CodEnvio`),
  KEY `FK_PuntoControl` (`CodPC`,`CodRuta`,`Destino`),
  CONSTRAINT `FK_Envio` FOREIGN KEY (`CodEnvio`) REFERENCES `envio` (`Codigo`),
  CONSTRAINT `FK_PuntoControl` FOREIGN KEY (`CodPC`, `CodRuta`, `Destino`) REFERENCES `puntocontrol` (`Codigo`, `CodRuta`, `Destino`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `almacen`
--

LOCK TABLES `almacen` WRITE;
/*!40000 ALTER TABLE `almacen` DISABLE KEYS */;
INSERT INTO `almacen` VALUES (1,1,1,'Test',2,4,12);
/*!40000 ALTER TABLE `almacen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `NIT` varchar(50) NOT NULL,
  `Nombre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`NIT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('235847-6','L'),('4587e','D'),('48979ae','A'),('48A7E9','N'),('566014-4','E'),('893437-K','I');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `destino`
--

DROP TABLE IF EXISTS `destino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `destino` (
  `Direccion` varchar(100) NOT NULL,
  `Cuota` decimal(10,0) NOT NULL,
  PRIMARY KEY (`Direccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destino`
--

LOCK TABLES `destino` WRITE;
/*!40000 ALTER TABLE `destino` DISABLE KEYS */;
INSERT INTO `destino` VALUES ('Aloha',120),('Infinito',540),('Mas alla',600),('Test',1),('Test2',5);
/*!40000 ALTER TABLE `destino` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `envio`
--

DROP TABLE IF EXISTS `envio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `envio` (
  `Codigo` int(11) NOT NULL,
  `CodPaquete` int(11) NOT NULL,
  `CodRuta` int(11) NOT NULL,
  `Destino` varchar(100) NOT NULL,
  `CostoT` decimal(10,0) DEFAULT NULL,
  `TiempoT` decimal(10,0) DEFAULT NULL,
  `Recibido` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  KEY `FK_Paquete` (`CodPaquete`),
  KEY `FK_RutaEnvio` (`CodRuta`,`Destino`),
  CONSTRAINT `FK_Paquete` FOREIGN KEY (`CodPaquete`) REFERENCES `paquete` (`Codigo`),
  CONSTRAINT `FK_RutaEnvio` FOREIGN KEY (`CodRuta`, `Destino`) REFERENCES `ruta` (`Codigo`, `Destino`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `envio`
--

LOCK TABLES `envio` WRITE;
/*!40000 ALTER TABLE `envio` DISABLE KEYS */;
INSERT INTO `envio` VALUES (1,1,1,'Test',0,0,0),(2,2,1,'Test',0,0,0),(4,45,1,'Aloha',0,0,1);
/*!40000 ALTER TABLE `envio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paquete`
--

DROP TABLE IF EXISTS `paquete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paquete` (
  `Codigo` int(11) NOT NULL,
  `Peso` decimal(10,0) NOT NULL,
  `NIT` varchar(50) NOT NULL,
  `Priorizado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  KEY `FK_Cliente` (`NIT`),
  CONSTRAINT `FK_Cliente` FOREIGN KEY (`NIT`) REFERENCES `cliente` (`NIT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paquete`
--

LOCK TABLES `paquete` WRITE;
/*!40000 ALTER TABLE `paquete` DISABLE KEYS */;
INSERT INTO `paquete` VALUES (1,4,'4587e',0),(2,6,'4587e',0),(3,12,'235847-6',0),(4,5,'48979ae',0),(5,10,'48A7E9',0),(6,18,'566014-4',0),(7,18,'566014-4',0),(8,10,'566014-4',0),(9,10,'48979ae',0),(10,20,'48979ae',0),(11,22,'893437-K',0),(12,120,'893437-K',0),(13,85,'235847-6',0),(45,72,'4587e',0),(78,20,'893437-K',0);
/*!40000 ALTER TABLE `paquete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `puntocontrol`
--

DROP TABLE IF EXISTS `puntocontrol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `puntocontrol` (
  `Codigo` int(11) NOT NULL,
  `CodRuta` int(11) NOT NULL,
  `Destino` varchar(100) NOT NULL,
  `Tarifa` decimal(10,0) NOT NULL,
  `TarifaG` decimal(10,0) NOT NULL,
  `Limite` int(11) NOT NULL,
  `Ubicacion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Codigo`,`CodRuta`,`Destino`),
  KEY `FK_Ruta` (`CodRuta`,`Destino`),
  CONSTRAINT `FK_Ruta` FOREIGN KEY (`CodRuta`, `Destino`) REFERENCES `ruta` (`Codigo`, `Destino`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `puntocontrol`
--

LOCK TABLES `puntocontrol` WRITE;
/*!40000 ALTER TABLE `puntocontrol` DISABLE KEYS */;
INSERT INTO `puntocontrol` VALUES (1,1,'Aloha',12,0,8,'h'),(1,1,'Test',4,0,4,'H'),(1,1,'Test2',7,0,7,'T21'),(1,8,'Infinito',5,0,15,'I1'),(1,1000,'Mas alla',15,0,6,'M1'),(2,1,'Aloha',10,0,9,'H2'),(2,1,'Test',5,0,10,'T2'),(2,1,'Test2',8,0,7,'T22'),(2,8,'Infinito',8,0,12,'I2'),(2,1000,'Mas alla',9,0,9,'m2'),(3,1,'Aloha',14,0,7,'H3'),(3,1,'Test',4,0,12,'T3'),(3,1,'Test2',5,0,10,'T23'),(3,8,'Infinito',4,0,18,'I3'),(3,1000,'Mas alla',30,0,1,'M3'),(4,1,'Aloha',8,0,15,'H4'),(4,1,'Test',6,0,10,'T4'),(4,1,'Test2',10,0,10,'T24'),(4,8,'Infinito',4,0,20,'I4'),(4,1000,'Mas alla',8,0,13,'M4');
/*!40000 ALTER TABLE `puntocontrol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ruta`
--

DROP TABLE IF EXISTS `ruta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ruta` (
  `Codigo` int(11) NOT NULL,
  `Destino` varchar(100) NOT NULL,
  `Estado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Codigo`,`Destino`),
  KEY `Fk_Destino` (`Destino`),
  CONSTRAINT `Fk_Destino` FOREIGN KEY (`Destino`) REFERENCES `destino` (`Direccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ruta`
--

LOCK TABLES `ruta` WRITE;
/*!40000 ALTER TABLE `ruta` DISABLE KEYS */;
INSERT INTO `ruta` VALUES (1,'Aloha',1),(1,'Test',1),(1,'Test2',1),(2,'Aloha',1),(2,'Test',1),(8,'Infinito',1),(1000,'Mas alla',1);
/*!40000 ALTER TABLE `ruta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `Usuario` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Nombre` varchar(50) DEFAULT NULL,
  `Tipo` tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`Usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('Admin1','administrador','A1',1),('Admin2','2administrador','A2',1),('Operador1','Operador','O1',2),('Operador2','2Operador','Op2',2),('Recepcionista1','recepcionista1','R1',3),('Recepcionista2','recepcionistA','Re2',3),('test','test','Developer',1),('testOp','test','Operador',2),('testRe','test','Recepcionista',3);
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

-- Dump completed on 2019-08-26 13:57:25
