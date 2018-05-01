DROP DATABASE `bd_quizminado`;
CREATE DATABASE  IF NOT EXISTS `bd_quizminado` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bd_quizminado`;

--
-- Table structure for table `tb_disciplina`
--
DROP TABLE IF EXISTS `tb_disciplina`;
CREATE TABLE `tb_disciplina` (
  `id_disciplina` int(11) NOT NULL AUTO_INCREMENT,
  `ds_disciplina` varchar(200) NOT NULL,
  PRIMARY KEY (`id_disciplina`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tb_disciplina`
--
INSERT INTO `tb_disciplina` VALUES (1,'Ciencias'),(2,'Historia');

--
-- Table structure for table `tb_nivel`
--
DROP TABLE IF EXISTS `tb_nivel`;
CREATE TABLE `tb_nivel` (
  `id_nivel` int(11) NOT NULL AUTO_INCREMENT,
  `ds_nivel` varchar(200) NOT NULL,
  `nu_dificuldade` int(11) NOT NULL,
  PRIMARY KEY (`id_nivel`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tb_nivel`
--
INSERT INTO `tb_nivel` VALUES (1,'Baixo',1),(2,'Medio',2),(3,'Alto',3);

--
-- Table structure for table `tb_questao`
--
DROP TABLE IF EXISTS `tb_questao`;
CREATE TABLE `tb_questao` (
  `id_questao` int(11) NOT NULL AUTO_INCREMENT,
  `ds_questao` varchar(200) NOT NULL,
  `id_disciplina` int(11) NOT NULL,
  `id_nivel` int(11) NOT NULL,
  PRIMARY KEY (`id_questao`),
  KEY `fk_disciplina_questao` (`id_disciplina`),
  KEY `fk_nivel_questao` (`id_nivel`),
  CONSTRAINT `fk_disciplina_questao` FOREIGN KEY (`id_disciplina`) REFERENCES `tb_disciplina` (`id_disciplina`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_nivel_questao` FOREIGN KEY (`id_nivel`) REFERENCES `tb_nivel` (`id_nivel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `tb_resposta`
--
DROP TABLE IF EXISTS `tb_resposta`;
CREATE TABLE `tb_resposta` (
  `id_resposta` int(11) NOT NULL AUTO_INCREMENT,
  `ds_resposta` varchar(500) NOT NULL,
  `st_resposta` char(1) NOT NULL,
  `lt_resposta` char(1) NOT NULL,
  `id_questao` int(11) NOT NULL,
  PRIMARY KEY (`id_resposta`),
  KEY `fk_questao_resposa` (`id_questao`),
  CONSTRAINT `fk_questao_resposa` FOREIGN KEY (`id_questao`) REFERENCES `tb_questao` (`id_questao`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;