-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         11.5.2-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para usuariocaja
CREATE DATABASE IF NOT EXISTS `usuariocaja` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_spanish_ci */;
USE `usuariocaja`;

-- Volcando estructura para tabla usuariocaja.alumnos
CREATE TABLE IF NOT EXISTS `alumnos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_completo` varchar(100) NOT NULL,
  `ciclo` enum('DAM','DAW','ASIR') NOT NULL,
  `evaluacion` enum('SEPTIEMBRE','MARZO') NOT NULL,
  `anio_curso` year(4) NOT NULL,
  `empresa_id` int(11) DEFAULT NULL,
  `tutor_docente_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_alumnos_empresa` (`empresa_id`),
  KEY `fk_alumnos_tutor_docente` (`tutor_docente_id`),
  CONSTRAINT `fk_alumnos_empresa` FOREIGN KEY (`empresa_id`) REFERENCES `empresas` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_alumnos_tutor_docente` FOREIGN KEY (`tutor_docente_id`) REFERENCES `tutores_docentes` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;

-- Volcando datos para la tabla usuariocaja.alumnos: ~6 rows (aproximadamente)
INSERT INTO `alumnos` (`id`, `nombre_completo`, `ciclo`, `evaluacion`, `anio_curso`, `empresa_id`, `tutor_docente_id`) VALUES
	(1, 'Juan Pérez', 'DAM', 'SEPTIEMBRE', '2024', 1, 1),
	(2, 'María López', 'DAW', 'MARZO', '2024', 2, 1),
	(3, 'Carlos Gómez', 'ASIR', 'SEPTIEMBRE', '2024', 6, 2),
	(4, 'Juan Pérez', 'DAM', 'SEPTIEMBRE', '2024', 2, 3),
	(5, 'María López', 'DAW', 'MARZO', '2024', 1, 1),
	(6, 'Carlos Gómez', 'ASIR', 'SEPTIEMBRE', '2024', 5, 3);

-- Volcando estructura para tabla usuariocaja.empresas
CREATE TABLE IF NOT EXISTS `empresas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_empresa` varchar(100) NOT NULL,
  `tutor_laboral_nombre` varchar(100) NOT NULL,
  `tutor_laboral_email` varchar(100) NOT NULL,
  `tutor_laboral_telefono` varchar(15) NOT NULL,
  `activo` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;

-- Volcando datos para la tabla usuariocaja.empresas: ~6 rows (aproximadamente)
INSERT INTO `empresas` (`id`, `nombre_empresa`, `tutor_laboral_nombre`, `tutor_laboral_email`, `tutor_laboral_telefono`, `activo`) VALUES
	(1, 'Tech Solutions', 'Pedro Ramírez', 'pedro@techsolutions.com', '123456789', 1),
	(2, 'SoftWeb', 'Ana Torres', 'ana@softweb.com', '987654321', 1),
	(3, 'CyberSecure', 'Luis Martínez', 'luis@cybersecure.com', '112233445', 1),
	(4, 'Ayesa', 'Indalecio', 'ayesa@invent.com', '864432543', 1),
	(5, 'Darwin Collections', 'Rafael Madrigal', 'darwin@collections.com', '987654765', 1),
	(6, 'Wuolah', 'Abel Morillo', 'wuolah@sevilla.com', '667345775', 1);

-- Volcando estructura para tabla usuariocaja.fechas_practicas
CREATE TABLE IF NOT EXISTS `fechas_practicas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `anio_curso` year(4) NOT NULL,
  `evaluacion` enum('SEPTIEMBRE','MARZO') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;

-- Volcando datos para la tabla usuariocaja.fechas_practicas: ~15 rows (aproximadamente)
INSERT INTO `fechas_practicas` (`id`, `fecha`, `anio_curso`, `evaluacion`) VALUES
	(1, '2024-09-15', '2024', 'SEPTIEMBRE'),
	(2, '2024-03-10', '2024', 'MARZO'),
	(3, '2024-09-20', '2024', 'SEPTIEMBRE'),
	(4, '2024-08-03', '2024', 'SEPTIEMBRE'),
	(5, '2024-10-10', '2024', 'MARZO'),
	(6, '2024-10-11', '2024', 'MARZO'),
	(7, '2024-10-12', '2024', 'MARZO'),
	(8, '2024-10-09', '2024', 'SEPTIEMBRE'),
	(9, '2024-10-14', '2024', 'MARZO'),
	(10, '2024-10-17', '2024', 'SEPTIEMBRE'),
	(11, '2024-09-04', '2024', 'SEPTIEMBRE'),
	(12, '2024-10-01', '2024', 'MARZO'),
	(13, '2024-10-04', '2024', 'SEPTIEMBRE'),
	(14, '2024-11-11', '2024', 'MARZO'),
	(15, '2024-10-12', '2024', 'SEPTIEMBRE');

-- Volcando estructura para tabla usuariocaja.registros_practicas
CREATE TABLE IF NOT EXISTS `registros_practicas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alumno_id` int(11) NOT NULL,
  `fecha_id` int(11) NOT NULL,
  `cantidad_horas` int(11) NOT NULL,
  `descripcion` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_alumno` (`alumno_id`),
  KEY `fk_fecha` (`fecha_id`),
  CONSTRAINT `fk_alumno` FOREIGN KEY (`alumno_id`) REFERENCES `alumnos` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_fecha` FOREIGN KEY (`fecha_id`) REFERENCES `fechas_practicas` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;

-- Volcando datos para la tabla usuariocaja.registros_practicas: ~15 rows (aproximadamente)
INSERT INTO `registros_practicas` (`id`, `alumno_id`, `fecha_id`, `cantidad_horas`, `descripcion`) VALUES
	(1, 1, 1, 6, 'Desarrollo de una API REST en Java con Spring Boot.'),
	(2, 2, 2, 5, 'Diseño de una interfaz web responsiva con React y Tailwind CSS.'),
	(3, 3, 3, 7, 'Configuración de servidores y pruebas de ciberseguridad.'),
	(4, 2, 4, 8, 'Uxabilidad y QUA'),
	(5, 3, 5, 7, 'Desarrollo de Back en Java'),
	(6, 1, 6, 6, 'Uxabilidad y QUA'),
	(7, 4, 7, 6, 'Desarrollo Back'),
	(8, 1, 8, 6, 'Diseño de una interfaz web responsiva con React y Tailwind CSS.'),
	(9, 4, 9, 6, 'Configuración de servidores y pruebas de ciberseguridad.'),
	(10, 2, 10, 7, 'Configuración de servidores y pruebas de ciberseguridad.'),
	(11, 3, 11, 2, 'Desarrollo Back'),
	(12, 4, 12, 5, 'Diseño de una interfaz web responsiva con React y Tailwind CSS.'),
	(13, 5, 13, 3, 'Diseño de una interfaz web responsiva con React y Tailwind CSS.'),
	(14, 5, 14, 6, 'Desarrollo Back'),
	(15, 5, 15, 7, 'Desarrollo Back');

-- Volcando estructura para tabla usuariocaja.tutores_docentes
CREATE TABLE IF NOT EXISTS `tutores_docentes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_completo` varchar(100) NOT NULL,
  `activo` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;

-- Volcando datos para la tabla usuariocaja.tutores_docentes: ~5 rows (aproximadamente)
INSERT INTO `tutores_docentes` (`id`, `nombre_completo`, `activo`) VALUES
	(1, 'Laura Fernández', 1),
	(2, 'José Rodríguez', 1),
	(3, 'Marta Sánchez', 1),
	(4, 'Abel Morillo \r\n', 1),
	(5, 'Rafael Madrigal', 1);

-- Volcando estructura para tabla usuariocaja.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(50) NOT NULL,
  `contraseña` varchar(255) NOT NULL,
  `perfil` enum('ALUMNO','TUTOR') NOT NULL,
  `usuario_asociado` int(11) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_usuario` (`nombre_usuario`),
  KEY `fk_usuario_asociado` (`usuario_asociado`),
  CONSTRAINT `fk_usuario_asociado` FOREIGN KEY (`usuario_asociado`) REFERENCES `alumnos` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;

-- Volcando datos para la tabla usuariocaja.usuarios: ~5 rows (aproximadamente)
INSERT INTO `usuarios` (`id`, `nombre_usuario`, `contraseña`, `perfil`, `usuario_asociado`, `activo`) VALUES
	(1, 'juanp', 'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'ALUMNO', 1, 1),
	(2, 'marial', 'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'ALUMNO', 2, 1),
	(3, 'carlosg', 'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'ALUMNO', 3, 1),
	(4, 'joselito', 'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'ALUMNO', 6, 1),
	(5, 'belen', 'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb', 'ALUMNO', 5, 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
