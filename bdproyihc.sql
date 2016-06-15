-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.0.17-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win32
-- HeidiSQL Versión:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para bdproyihc
CREATE DATABASE IF NOT EXISTS `bdproyihc` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bdproyihc`;


-- Volcando estructura para tabla bdproyihc.equipo
CREATE TABLE IF NOT EXISTS `equipo` (
  `referencia` varchar(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `estado` varchar(15) NOT NULL,
  PRIMARY KEY (`referencia`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bdproyihc.equipo: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
INSERT INTO `equipo` (`referencia`, `nombre`, `estado`) VALUES
	('12345678', 'Regulador 5V', 'En Préstamo'),
	('23123411', 'Procesador Arduino', 'En Préstamo'),
	('34500129', 'Procesador Raspberry', 'En Préstamo'),
	('45678002', 'Fuente de Poder', 'En Préstamo'),
	('54392810', 'Motherboard', 'Disponible');
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;


-- Volcando estructura para tabla bdproyihc.estudiante
CREATE TABLE IF NOT EXISTS `estudiante` (
  `codigo` int(10) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `plan` varchar(4) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bdproyihc.estudiante: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `estudiante` DISABLE KEYS */;
INSERT INTO `estudiante` (`codigo`, `nombre`, `plan`) VALUES
	(201102349, 'José Herrera', '2711'),
	(201234291, 'Jacobo Arenas', '2711'),
	(201255158, 'Christian Noreña', '3743'),
	(201255229, 'Simón Naranjo', '3743'),
	(201402189, 'Karen Quintero', '2710');
/*!40000 ALTER TABLE `estudiante` ENABLE KEYS */;


-- Volcando estructura para tabla bdproyihc.prestamo
CREATE TABLE IF NOT EXISTS `prestamo` (
  `codigoestudiante` int(11) NOT NULL,
  `referenciaequipo` varchar(11) NOT NULL,
  `fechadevolucion` date NOT NULL,
  `observaciones` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`codigoestudiante`,`referenciaequipo`),
  KEY `referenciaequipo_fk` (`referenciaequipo`),
  CONSTRAINT `codigoestudiante_fk` FOREIGN KEY (`codigoestudiante`) REFERENCES `estudiante` (`codigo`),
  CONSTRAINT `referenciaequipo_fk` FOREIGN KEY (`referenciaequipo`) REFERENCES `equipo` (`referencia`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bdproyihc.prestamo: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `prestamo` DISABLE KEYS */;
INSERT INTO `prestamo` (`codigoestudiante`, `referenciaequipo`, `fechadevolucion`, `observaciones`) VALUES
	(201234291, '12345678', '2016-06-15', ''),
	(201234291, '34500129', '2016-06-15', ''),
	(201255158, '45678002', '2016-06-13', 'Prestado con cables'),
	(201402189, '23123411', '2016-06-13', 'Con un par de pines dañados');
/*!40000 ALTER TABLE `prestamo` ENABLE KEYS */;


-- Volcando estructura para tabla bdproyihc.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `idUsuario` varchar(20) NOT NULL,
  `nombrescompletos` varchar(50) NOT NULL,
  `tipo` varchar(11) NOT NULL,
  `clave` varchar(50) NOT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla bdproyihc.usuario: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`idUsuario`, `nombrescompletos`, `tipo`, `clave`) VALUES
	('chris1994', 'Christian Noreña', 'Monitor', 'qwer1234'),
	('garcia12', 'Juan Soto', 'Monitor', 'jsoto234'),
	('jhonblan88', 'Jhon Edinson Blandón', 'Auxiliar', 'joselo123'),
	('kevin94', 'Kevin Ospina', 'Monitor', 'filosorana');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
