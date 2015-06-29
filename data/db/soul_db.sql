-- MySQL dump 10.13  Distrib 5.5.43, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: soul_db
-- ------------------------------------------------------
-- Server version	5.5.43-0ubuntu0.14.10.1

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
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat` (
  `id_chat` int(11) NOT NULL AUTO_INCREMENT,
  `members_amount` tinyint(8) NOT NULL DEFAULT '2',
  PRIMARY KEY (`id_chat`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES (1,3);
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_members`
--

DROP TABLE IF EXISTS `chat_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_members` (
  `user_id_user` int(11) NOT NULL DEFAULT '0',
  `chat_id_chat` int(11) NOT NULL,
  PRIMARY KEY (`user_id_user`,`chat_id_chat`),
  KEY `fk_chat_idx` (`chat_id_chat`),
  CONSTRAINT `fk_chat` FOREIGN KEY (`chat_id_chat`) REFERENCES `chat` (`id_chat`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_user` FOREIGN KEY (`user_id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_members`
--

LOCK TABLES `chat_members` WRITE;
/*!40000 ALTER TABLE `chat_members` DISABLE KEYS */;
INSERT INTO `chat_members` VALUES (1,1),(2,1);
/*!40000 ALTER TABLE `chat_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_message`
--

DROP TABLE IF EXISTS `chat_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_message` (
  `id_user` int(11) NOT NULL,
  `id_chat` int(11) NOT NULL,
  `sending_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_name` varchar(45) NOT NULL,
  `text` varchar(255) NOT NULL,
  KEY `id_chat_idx` (`id_chat`),
  CONSTRAINT `id_chat` FOREIGN KEY (`id_chat`) REFERENCES `chat` (`id_chat`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_message`
--

LOCK TABLES `chat_message` WRITE;
/*!40000 ALTER TABLE `chat_message` DISABLE KEYS */;
INSERT INTO `chat_message` VALUES (1,1,'2014-07-05 10:00:00','Mario','segnalazione ricevuta.Tutto ok?'),(7,1,'2014-07-05 10:01:00','Alighiero','Si'),(1,1,'2014-07-05 10:00:00','Mario','Va bene. Gli aiuti sono per strada'),(2,1,'2014-09-08 19:06:49','Giuseppe','Ci sono anche io'),(2,1,'2014-09-09 09:45:52','Giuseppe Roncas','mi ricevete?'),(2,1,'2014-09-09 10:01:14','Giuseppe Roncas','Grazie a voi siamo sani e salvi :)'),(2,1,'2014-09-11 08:16:57','Giuseppe Roncas','messaggio di prova');
/*!40000 ALTER TABLE `chat_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id_comment` int(11) NOT NULL AUTO_INCREMENT,
  `publication_date` datetime NOT NULL,
  `message` varchar(255) NOT NULL,
  `id_user` int(11) NOT NULL DEFAULT '0',
  `id_report` int(11) NOT NULL,
  PRIMARY KEY (`id_comment`,`id_user`),
  KEY `id_user` (`id_user`),
  KEY `id_report` (`id_report`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`id_report`) REFERENCES `report` (`id_report`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emergency_number`
--

DROP TABLE IF EXISTS `emergency_number`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emergency_number` (
  `number` int(11) NOT NULL,
  `title` varchar(45) NOT NULL,
  PRIMARY KEY (`number`),
  UNIQUE KEY `title_UNIQUE` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emergency_number`
--

LOCK TABLES `emergency_number` WRITE;
/*!40000 ALTER TABLE `emergency_number` DISABLE KEYS */;
INSERT INTO `emergency_number` VALUES (1522,'Antiviolenza donne'),(112,'Carabinieri'),(1518,'CCISS'),(114,'Emergenza infanzia'),(118,'Emergenza sanitaria, Ambulanza'),(1530,'Guardia Costiera'),(117,'Guardia di finanzia'),(1515,'Guardia Forestale'),(113,'Polizia di country'),(803555,'Protezione Civile'),(803116,'Soccorso stradale'),(19696,'Telefono Azzurro'),(636225,'Unita di crisi'),(115,'Vigili del fuoco');
/*!40000 ALTER TABLE `emergency_number` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emergency_number_rel`
--

DROP TABLE IF EXISTS `emergency_number_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emergency_number_rel` (
  `number` int(11) NOT NULL,
  `subtype` int(11) NOT NULL,
  UNIQUE KEY `number_UNIQUE` (`number`,`subtype`),
  KEY `fk_emergency_number_rel_2_idx` (`subtype`),
  CONSTRAINT `fk_emergency_number_rel_1` FOREIGN KEY (`number`) REFERENCES `emergency_number` (`number`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_emergency_number_rel_2` FOREIGN KEY (`subtype`) REFERENCES `emergency_subtype` (`id_subtype`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emergency_number_rel`
--

LOCK TABLES `emergency_number_rel` WRITE;
/*!40000 ALTER TABLE `emergency_number_rel` DISABLE KEYS */;
INSERT INTO `emergency_number_rel` VALUES (112,1),(118,1);
/*!40000 ALTER TABLE `emergency_number_rel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emergency_subtype`
--

DROP TABLE IF EXISTS `emergency_subtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emergency_subtype` (
  `id_subtype` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` text NOT NULL,
  `priority_level` bigint(20) NOT NULL DEFAULT '5',
  `id_type` int(11) NOT NULL,
  PRIMARY KEY (`id_subtype`,`id_type`),
  KEY `fk_emergency_subtype_1_idx` (`id_type`),
  CONSTRAINT `fk_emergency_subtype_1` FOREIGN KEY (`id_type`) REFERENCES `emergency_type` (`id_type`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emergency_subtype`
--

LOCK TABLES `emergency_subtype` WRITE;
/*!40000 ALTER TABLE `emergency_subtype` DISABLE KEYS */;
INSERT INTO `emergency_subtype` VALUES (1,'Infarto','L\'infarto è un...',5,3);
/*!40000 ALTER TABLE `emergency_subtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emergency_type`
--

DROP TABLE IF EXISTS `emergency_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emergency_type` (
  `id_type` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(45) NOT NULL,
  `authorities_involved` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emergency_type`
--

LOCK TABLES `emergency_type` WRITE;
/*!40000 ALTER TABLE `emergency_type` DISABLE KEYS */;
INSERT INTO `emergency_type` VALUES (3,'Emergenza Sanitaria','Emergenza Sanitaria ,Carabinieri, Vigili del fuoco e Polizia');
/*!40000 ALTER TABLE `emergency_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `id_news` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `article` text NOT NULL,
  `publication_date` datetime NOT NULL,
  `id_user` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_news`,`id_user`),
  KEY `fk_news_1_idx` (`id_user`),
  CONSTRAINT `fk_news_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,'Maltempo, fumine colpisce il santuario di Mux','l maltempo che ha colpito l\'Europa del Nord non ha risparmiato una delle tappe-simbolo del cammino di Santiago di Compostela, il Santuario alla Vergine della Barca a Muxia, colpito da un fulmine è stato distrutto oggi dalle fiamme. «È bruciato», riportano siti online della stampa galiziana ricordando che si tratta di uno dei siti «più antichi e importanti della Galizia». Le fiamme - secondo le prime ricostruzioni della polizia di Muxia - sono scoppiate stamattina, giorno di Natale, distruggendo l\'altare ed il soffitto del Santuario risalente al periodo tra l\'XI e il XII secolo.\r\n\r\nSi ritiene che la causa dell\'incendio sia stato un fulmine che avrebbe colpito un trasformatore elettrico vicino alla costruzione. Il santuario è una delle tappe finali del lungo cammino di Santiago di Compostela, dove i pellegrini possono spingersi per vedere l\'oceano dopo aver toccato completato il percorso che dalla Francia e la Spagna, ma anche dall\'Italia, attraverso la via Francigena, li porta a Santiago.\r\n\r\nLe fiamme hanno impiegato poco tempo a fare il loro corso. Ora si sta facendo la conta dei danni che sembrano gravissimi: La pala dell’altare centrale è stata completamente distrutta dal fuoco. Anche gli altari laterali sono stati bruciati, anche se non nella loro interezza. Sono state risparmiati elementi di poco valore culturale come confessionali e banchi.\r\n\r\nSono intervenute per spegnere il fuoco squadre di vigili e della protezione civile della città e di località vicine. Il sindaco di Muxia Felix Porto ha dichiarato al quotidiano El Mundo che le fiamme hanno fatto la loro comparsa verso le 6:45 di stamane probabilmente a causa di un fulmine. «Le fiamme – ha dichiarato – hanno provocato dannni irreversibili. La costruzione del santuario è completamente distrutta. Le poche cose che si sono salvate sono un confessionale, qualche immagine e poco altro».\r\n\r\nPorto ha poi spiegato di aver avuto una telefonata da parte del presidente della Giunta Alberto Nunez Feijoo con con cui gli veniva comunicata la vicinanza delle istituzioni locali: «E’ una giornata nera per tutta la regione – ha dichiarato – nessuno poteva aspettarsi che il santuario sarebbe stato distrutto in sole due ore».\r\n\r\nAnche Il ministro dell’istruzione e della cultura della Giunta della Galizia Jesús Vázquez si è fatto sentire, assicurando che raggiungerà il sito del disastro quanto prima per fare la valutazione dei danni. ','2014-01-05 07:00:00',1),(2,'Camion va a fuoco\r\nPaura sull\'A20','Un camion carico di legna è andato a fuoco mentre percorreva l\'A20, poco dopo i caselli di Villafranca. Tempestivo e decisivo l\'intervento di vigili del fuoco e polizia stradale\r\nCamion va a fuoco Paura sull\'A20\r\n\r\nTempestivo intervento dei vigili del fuoco e della polizia stradale, che hanno evitato che il principio d\'incendio ad un camion carico di legna avesse conseguenze gravi. Il fatto si è verificato sull\'A20, in direzione Messina, poco dopo i caselli di Villafranca. Il mezzo pesante è stato messo in sicurezza nell\'area di parcheggio Salice. Nessuna conseguenza per il conducente e per la circolazione veicolare.\r\n','2014-04-16 07:00:00',1),(3,'Milano, allagamenti e disagi','Macchine che quasi galleggiano, stivali d\'emergenza e scarponi per affrontare il fango, traffico in tilt. Rabbia per quanto è accaduto ma anche un bel po\' di ironia per sopportare meglio i disagi.\r\nDalla prima mattina, i milanesi hanno cominciato a raccontare sui social network la loro giornata alle prese con la pioggia e gli allagamenti seguiti all\'esondazione del Seveso avvenuta alle 2.50 della notte. Fotografie dall\'alto che mostrano auto impantanate, passerelle improvvisate per consentire il passaggio dei pedoni, strade “guadate” a piedi nudi. Ecco una selezione delle testimonianze dal basso dalla Milano “sommersa”. ','2014-03-12 07:00:00',6),(4,'Tromba d’aria ieri a Montesilvano: ombrelloni','Ieri mattina è stato segnalato e ripreso un “dust devils” a Montesilvano, una sorta di tromba d’aria che si presenza in condizioni di bel tempo\r\n\r\nTromba d’aria ieri a Montesilvano: ombrelloni volati a chilometri di distanza 10/07/2014 – Un fenomeno non troppo frequente è stato segnalato ieri sulla spiaggia di Montesilvano, Abruzzo, dove si è abbattuta una sorta di tromba d’aria a ciel sereno. Il fenomeno è conosciuto con il nome di Dust Devils, o “diavolo di polvere”, e si manifesta nelle belle giornate di Sole, con quasi totale assenza di nubi a sviluppo verticale. A differenza dei tornado (o trombe d’aria), i dust devils non sono collegati a nessuna attività convettiva, si formano sempre in luoghi dove c’è molta sabbia o polvere, come i campeggi, i parcheggi, le spiagge o le campagne, ed in alcuni casi sono in grado di arrecare seri danni.\r\n\r\n\r\nIl dust devils è stato segnalato alle ore 11:30 di ieri sulla spiaggia di Montesilvano. Subito scattato il panico tra i bagnanti, la spiaggia era stata presa d’assalto dai vacanzieri, dopo che i primi ombrelloni sono iniziati a volare a decine di metri di altezza (alcuni sono stati ritrovati a diversi chilometri di distanza!). Si è rapidamente formato un turbine di sabbia alto quasi un chilometro, fortunatamente durato meno di un minuto. Nessun ferito, ma il dust devils ha raggiunto proporzioni davvero notevoli. I dust devils si formano con venti non particolarmente forti (in questo caso c’era il secco vento di Garbino, proveniente da Ovest-NordOvest) e sollevamento di aria calda dal suolo. I contrasti tra aria calda del terreno ed aria fresca proveniente dal mare hanno dato manforte alla genesi del dust devils.\r\n','2014-06-22 07:00:00',6),(5,'Maxi-tamponamento sulla ss16, due feriti','(ANSA) - MONOPOLI (BARI), 6 LUG - Un maxi-tamponamento si è verificato questa mattina sulla statale 16 all\'altezza di uno dei bivi per Monopoli. Otto i veicoli coinvolti e due le persone rimaste ferite, anche se in modo lieve. La serie di tamponamenti a catena ha causato per oltre un\'ora code e rallentamenti alla circolazione, mentre il traffico è stato temporaneamente deviato in direzione dell\'abitato di Monopoli. Sul posto sono intervenuti la Polizia sradale, i vigili del fuoco e personale del \'118\'.','2014-06-02 07:00:00',4),(6,'Tempesta in Germania, bufera di sabbia e piog','Una violenta tempesta con venti fortissimi si è a abbattuta a Mainz, in Germania, durante il Love Family Park mettendo in fuga 17.000 persone\r\nUna bufera di vento e sabbia, con pioggia intensa si è abbattuta sulla cittadina tedesca di Mainz interessando in particolare l’area del Fair Park Mainz allestita per il festival musicale chiamato Love Family Park. Una giornata all’insegna della musica techno che ha richiamato 17.000 persone, forte della giornata soleggiata che però, a cominciare dal tardo pomeriggio, ha iniziato a cambiare. Il cielo si è fatto sempre più scuro finchè intorno alle 21 una violenta tempesta di pioggia e vento si è abbattuta sul festival obbligando gli organizzatori ad annullare l’evento e i 17.000 a mettersi in fuga.\r\nMa la tempesta è sopraggiunta in pochi minuti e così migliaia di persone si sono ritrovate nel bel mezzo di una bufera di venti e sabbia che li ha completamente circondati: sette persono sono rimaste ferite, colpite da oggetti volanti scagliati in aria dal forte vento. Non sono mancate molte critiche perchè, secondo molti dei presenti, gli organizzatori non avrebbero preavvisato dell’arrivo della tempesta con un margine di tempo sufficiente ad abbandonare l’area. Molte persone hanno realizzato video e foto del momento in cui la bufera è sopraggiunta, come quello che vi mostriamo.\r\n','2014-02-15 00:00:00',4);
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `id_notification` int(11) NOT NULL AUTO_INCREMENT,
  `read` tinyint(1) NOT NULL DEFAULT '0',
  `message` varchar(45) NOT NULL,
  `receiving_date` datetime NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_notification`),
  KEY `ForeignKey user` (`id_user`),
  CONSTRAINT `ForeignKey user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,0,'Ricevuto un commento','2014-07-10 23:51:00',1),(2,0,'Ricevuto un messaggio','2014-07-05 12:00:00',7),(3,1,'Ricevuto un commento','2014-07-10 23:55:00',1),(4,0,'Ricevuto un commento','2014-01-05 00:00:00',13),(5,0,'Ricevuto un messaggio','2014-07-05 12:01:00',10),(6,1,'Ricevuto un messaggio','2014-02-26 12:03:00',1),(8,0,'Pubblicata una notizia','2014-01-05 07:00:00',2),(9,0,'Pubblicata una notizia','2014-04-16 07:00:00',2),(10,0,'Pubblicata una notizia','2014-03-12 07:00:00',1),(11,0,'Pubblicata una notizia','2014-06-22 07:00:00',5),(12,0,'Pubblicata una notizia','2014-06-02 07:00:00',1),(13,1,'Pubblicata una notizia','2014-02-15 07:00:00',2);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `id_report` int(11) NOT NULL AUTO_INCREMENT,
  `coordinates` varchar(255) DEFAULT NULL,
  `description` text NOT NULL,
  `comments_number` int(10) unsigned NOT NULL DEFAULT '0',
  `publication_date` datetime NOT NULL,
  `place` varchar(255) DEFAULT NULL,
  `id_subtype` int(11) DEFAULT '1',
  PRIMARY KEY (`id_report`),
  KEY `fk_report_1_idx` (`id_subtype`),
  CONSTRAINT `fk_report_1` FOREIGN KEY (`id_subtype`) REFERENCES `emergency_subtype` (`id_subtype`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (1,'(20,30)','ANABHHA',0,'0000-00-00 00:00:00','zimbawe',1);
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id_task` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id_task`),
  UNIQUE KEY `type_UNIQUE` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (2,'Carabiniere'),(6,'Dottore'),(7,'Guardia Costiera'),(5,'Guardia Forestale'),(3,'Poliziotto'),(1,'Vigile del fuoco');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_rel`
--

DROP TABLE IF EXISTS `role_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_rel` (
  `task_id` int(11) NOT NULL,
  `user_id_user` int(11) NOT NULL,
  UNIQUE KEY `task_id_UNIQUE` (`task_id`,`user_id_user`),
  KEY `fk_id_user_idx` (`user_id_user`),
  CONSTRAINT `fk_id_user` FOREIGN KEY (`user_id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_rel_task1` FOREIGN KEY (`task_id`) REFERENCES `role` (`id_task`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_rel`
--

LOCK TABLES `role_rel` WRITE;
/*!40000 ALTER TABLE `role_rel` DISABLE KEYS */;
INSERT INTO `role_rel` VALUES (2,1),(1,2),(3,3),(2,4),(6,4),(7,5),(1,6),(3,10),(5,11),(6,12),(7,13),(2,14),(6,15);
/*!40000 ALTER TABLE `role_rel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `user_level` tinyint(3) unsigned zerofill NOT NULL DEFAULT '000',
  `email` varchar(45) NOT NULL,
  `birthdate` date NOT NULL,
  `name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `tax_code` varchar(45) NOT NULL,
  `street` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `cap` int(11) NOT NULL,
  `country` varchar(45) NOT NULL,
  `unread_notifications_number` int(10) unsigned DEFAULT '0',
  `password` varchar(255) NOT NULL,
  `session_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,003,'MarioRossi@email.it','1980-12-13','Mario','Rossi','MRARSS80A13H703M','Valerio Laspro','Salerno',84100,'Italia',0,'111',NULL),(2,001,'test@test.it','1959-01-17','Joseph','Roy','GRDLHR59B17H703Z','Piazzetta Scalette Rubiani','Salerno',84036,'Italia',0,'1','test@test.it1434638052259'),(3,000,'FedericoBasso@email.it','1995-05-02','Federico','Basso','BSSFRC95E02F839D','Toledo','Napoli',80121,'Italia',0,'11',NULL),(4,000,'MirandaTrentino@email.it','1988-07-08','Miranda','Trentino','TRNMND88L48B172X','Carlo Alberto','Brienno',22010,'Italia',0,'1111',NULL),(5,000,'AntonioRusso@email.it','1976-10-07','Antonio','Russo','RSSNTN76S07H703F','San Giovanni Bosco','Salerno',84100,'Italia',0,'11111',NULL),(6,000,'FrancescaEsposito@email.it','1966-04-12','Francesca','Esposito','SPSFNC66D54F839A','Vespucci','Napoli',84121,'Italia',0,'111111',NULL),(7,000,'GiuseppeRonca@email.it','1992-04-15','Giuseppe','Ronca','RNCGPP92D15C361F','Giuseppe Pellegrino','Cava De\'Tirreni',84013,'Italia',0,'1111111',NULL),(8,000,'DiegoAvella@email.it','1993-07-13','Diego','Avella','DGIVLL93L13C361P','Gaetano Filangieri','Cava De\'Tirreni',84013,'Italia',0,'1111111',NULL),(9,000,'AngeloPassaro@email.it','1993-05-21','Angelo','Passaro','PSSNGL93E21H703I','Salvatore Calenda','Salerno',84100,'Italia',0,'1111111',NULL),(10,000,'AndreaMagnani@email.it','1983-11-21','Andrea','Magnani','MGNNDR83T21H703U','Carmine','Salerno',84100,'Italia',0,'1111111',NULL),(11,000,'GiuliaCirillo@email.it','1990-05-05','Giulia','Cirillo','CRLGLI90E45H703S','Irno','Salerno',84100,'Italia',0,'11111111',NULL),(12,000,'LucaTosi@email.it','1975-05-21','Luca','Tosi','TSOLCU75E21H703N','Luigi Guercio','Salerno',84100,'Italia',0,'11111111',NULL),(13,000,'ChiaraCarboni@email.it','1989-08-17','Chiara','Carboni','CRBCHR89M57H703B','Armando Diaz','Salerno',84100,'Italia',0,'11111111',NULL),(14,000,'MatteoNardi@email.it','1980-10-22','Matteo','Nardi','NRDMTT80R22H703Y','Leonino Vinciprova','Salerno',84100,'Italia',0,'11111111',NULL),(15,000,'ClorindaDeRose@email.it','1969-01-11','Clorinda','DeRose','DRSCRN69B51G113Y','Croce Rossa','Oristano',9080,'Italia',0,'11111111',NULL),(17,000,'pd@pd.it','2015-06-12','Diego','Avella','','','',0,'',0,'1','pd@pd.it 1 17');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_report_rel`
--

DROP TABLE IF EXISTS `user_report_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_report_rel` (
  `user_id_user` int(11) NOT NULL,
  `report_id_report` int(11) NOT NULL,
  UNIQUE KEY `user_id_user_UNIQUE` (`user_id_user`,`report_id_report`),
  KEY `fk_user_report_rel_report1_idx` (`report_id_report`),
  CONSTRAINT `fk_user_report_rel_report1` FOREIGN KEY (`report_id_report`) REFERENCES `report` (`id_report`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_report_rel_user1` FOREIGN KEY (`user_id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_report_rel`
--

LOCK TABLES `user_report_rel` WRITE;
/*!40000 ALTER TABLE `user_report_rel` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_report_rel` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-26 19:20:06
