/*
SQLyog Community v12.04 (64 bit)
MySQL - 5.5.41-0ubuntu0.14.04.1 : Database - soul_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`soul_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `soul_db`;

/*Table structure for table `Attribuito` */

DROP TABLE IF EXISTS `Attribuito`;

CREATE TABLE `Attribuito` (
  `idSottotipo` int(11) NOT NULL,
  `Cod_Priorità` varchar(45) NOT NULL,
  UNIQUE KEY `idSottotipo_UNIQUE` (`idSottotipo`,`Cod_Priorità`),
  KEY `fk_Attribuito_1_idx` (`Cod_Priorità`),
  CONSTRAINT `fk_Attribuito_1` FOREIGN KEY (`Cod_Priorità`) REFERENCES `Cod Priorita` (`Cod Priorita`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Attribuito_2` FOREIGN KEY (`idSottotipo`) REFERENCES `Sottotipo` (`idSottotipo`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `Attribuito` */

insert  into `Attribuito`(`idSottotipo`,`Cod_Priorità`) values (2,'1'),(5,'1'),(1,'5'),(3,'5');

/*Table structure for table `Chat` */

DROP TABLE IF EXISTS `Chat`;

CREATE TABLE `Chat` (
  `idChat` int(11) NOT NULL AUTO_INCREMENT,
  `Numero_partecipanti` tinyint(8) NOT NULL DEFAULT '2',
  PRIMARY KEY (`idChat`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `Chat` */

insert  into `Chat`(`idChat`,`Numero_partecipanti`) values (1,3);

/*Table structure for table `Cod Priorita` */

DROP TABLE IF EXISTS `Cod Priorita`;

CREATE TABLE `Cod Priorita` (
  `Cod Priorita` varchar(45) NOT NULL,
  PRIMARY KEY (`Cod Priorita`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `Cod Priorita` */

insert  into `Cod Priorita`(`Cod Priorita`) values ('1'),('2'),('3'),('4'),('5');

/*Table structure for table `Commento` */

DROP TABLE IF EXISTS `Commento`;

CREATE TABLE `Commento` (
  `idCommento` int(11) NOT NULL AUTO_INCREMENT,
  `Data_pubblicazione` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Messaggio` varchar(255) NOT NULL,
  `idUtente` int(11) NOT NULL DEFAULT '0',
  `idSegnalazione` int(11) NOT NULL,
  PRIMARY KEY (`idCommento`,`idUtente`),
  KEY `idUtente` (`idUtente`),
  KEY `idSegnalazione` (`idSegnalazione`),
  CONSTRAINT `Commento_ibfk_1` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`idUtente`),
  CONSTRAINT `Commento_ibfk_2` FOREIGN KEY (`idSegnalazione`) REFERENCES `Segnalazione` (`idSegnalazione`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `Commento` */

insert  into `Commento`(`idCommento`,`Data_pubblicazione`,`Messaggio`,`idUtente`,`idSegnalazione`) values (1,'2014-07-10 23:51:00','Che didastro',1,1),(3,'2014-07-10 00:00:00','Sono in zona, arrivo a darti una mano!',3,1),(5,'2014-09-14 11:36:53','Cosa è successo?',2,2),(6,'2014-09-14 11:44:31','Si vede anche da casa mia!',2,3),(7,'2014-09-14 21:55:49','Siamo in zona, arriviamo',2,6),(11,'2014-09-15 01:48:07','Grazie!',2,1),(16,'2014-09-22 12:13:37','prova',2,5);

/*Table structure for table `Crea` */

DROP TABLE IF EXISTS `Crea`;

CREATE TABLE `Crea` (
  `Utente_idUtente` int(11) NOT NULL,
  `Segnalazione_idSegnalazione` int(11) NOT NULL,
  UNIQUE KEY `Utente_idUtente_UNIQUE` (`Utente_idUtente`,`Segnalazione_idSegnalazione`),
  KEY `fk_Crea_Segnalazione1_idx` (`Segnalazione_idSegnalazione`),
  CONSTRAINT `fk_Crea_Segnalazione1` FOREIGN KEY (`Segnalazione_idSegnalazione`) REFERENCES `Segnalazione` (`idSegnalazione`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_Crea_Utente1` FOREIGN KEY (`Utente_idUtente`) REFERENCES `Utente` (`idUtente`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `Crea` */

insert  into `Crea`(`Utente_idUtente`,`Segnalazione_idSegnalazione`) values (3,1),(1,2),(14,3),(7,4),(15,5),(8,6);

/*Table structure for table `Incarico` */

DROP TABLE IF EXISTS `Incarico`;

CREATE TABLE `Incarico` (
  `ID.Incarico` int(11) NOT NULL AUTO_INCREMENT,
  `Tipo` varchar(45) NOT NULL,
  PRIMARY KEY (`ID.Incarico`),
  UNIQUE KEY `Tipo_UNIQUE` (`Tipo`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `Incarico` */

insert  into `Incarico`(`ID.Incarico`,`Tipo`) values (2,'Carabiniere'),(6,'Dottore'),(7,'Guardia Costiera'),(5,'Guardia Forestale'),(3,'Poliziotto'),(1,'Vigile del fuoco');

/*Table structure for table `Messaggio` */

DROP TABLE IF EXISTS `Messaggio`;

CREATE TABLE `Messaggio` (
  `idUtente` int(11) NOT NULL,
  `idChat` int(11) NOT NULL,
  `Data_invio` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Nome_utente` varchar(45) NOT NULL,
  `testo` varchar(255) NOT NULL,
  KEY `idChat_idx` (`idChat`),
  CONSTRAINT `idChat` FOREIGN KEY (`idChat`) REFERENCES `Chat` (`idChat`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `Messaggio` */

insert  into `Messaggio`(`idUtente`,`idChat`,`Data_invio`,`Nome_utente`,`testo`) values (1,1,'2014-07-05 12:00:00','Mario','Segnalazione ricevuta.Tutto ok?'),(7,1,'2014-07-05 12:01:00','Alighiero','Si'),(1,1,'2014-07-05 12:00:00','Mario','Va bene. Gli aiuti sono per strada'),(2,1,'2014-09-08 21:06:49','Giuseppe','Ci sono anche io'),(2,1,'2014-09-09 11:45:52','Giuseppe Roncas','mi ricevete?'),(2,1,'2014-09-09 12:01:14','Giuseppe Roncas','Grazie a voi siamo sani e salvi :)'),(2,1,'2014-09-11 10:16:57','Giuseppe Roncas','Messaggio di prova'),(2,1,'2014-09-22 12:18:30','Giuseppe Roncas','ciao');

/*Table structure for table `Notifica` */

DROP TABLE IF EXISTS `Notifica`;

CREATE TABLE `Notifica` (
  `idNotifica` int(11) NOT NULL AUTO_INCREMENT,
  `letta` tinyint(1) NOT NULL DEFAULT '0',
  `messaggio` varchar(45) NOT NULL,
  `data_ricezione` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idUtente` int(11) NOT NULL,
  PRIMARY KEY (`idNotifica`),
  KEY `ForeignKey Utente` (`idUtente`),
  CONSTRAINT `ForeignKey Utente` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`idUtente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `Notifica` */

insert  into `Notifica`(`idNotifica`,`letta`,`messaggio`,`data_ricezione`,`idUtente`) values (1,0,'Ricevuto un commento','2014-07-10 23:51:00',1),(2,0,'Ricevuto un messaggio','2014-07-05 12:00:00',7),(3,1,'Ricevuto un commento','2014-07-10 23:55:00',1),(4,0,'Ricevuto un commento','2014-01-05 00:00:00',13),(5,0,'Ricevuto un messaggio','2014-07-05 12:01:00',10),(6,1,'Ricevuto un messaggio','2014-02-26 12:03:00',1),(8,0,'Pubblicata una notizia','2014-01-05 07:00:00',2),(9,0,'Pubbliciata una notizia','2014-04-16 07:00:00',2),(10,0,'Pubblicata una notizia','2014-03-12 07:00:00',1),(11,0,'Pubblicata una notizia','2014-06-22 07:00:00',5),(12,0,'Pubblicata una notizia','2014-06-02 07:00:00',1),(13,1,'PUbblicata una notizia','2014-02-15 07:00:00',2);

/*Table structure for table `Notizia` */

DROP TABLE IF EXISTS `Notizia`;

CREATE TABLE `Notizia` (
  `idNotizia` int(11) NOT NULL AUTO_INCREMENT,
  `titolo` varchar(45) NOT NULL,
  `Articolo` text NOT NULL,
  `Data_pubblicazione` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idUtente` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idNotizia`,`idUtente`),
  KEY `fk_Notizia_1_idx` (`idUtente`),
  CONSTRAINT `fk_Notizia_1` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`idUtente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `Notizia` */

insert  into `Notizia`(`idNotizia`,`titolo`,`Articolo`,`Data_pubblicazione`,`idUtente`) values (1,'Maltempo, fumine colpisce il santuario di Mux','l maltempo che ha colpito l\'Europa del Nord non ha risparmiato una delle tappe-simbolo del cammino di Santiago di Compostela, il Santuario alla Vergine della Barca a Muxia, colpito da un fulmine è stato distrutto oggi dalle fiamme. «È bruciato», riportano siti online della stampa galiziana ricordando che si tratta di uno dei siti «più antichi e importanti della Galizia». Le fiamme - secondo le prime ricostruzioni della polizia di Muxia - sono scoppiate stamattina, giorno di Natale, distruggendo l\'altare ed il soffitto del Santuario risalente al periodo tra l\'XI e il XII secolo.\r\n\r\nSi ritiene che la causa dell\'incendio sia stato un fulmine che avrebbe colpito un trasformatore elettrico vicino alla costruzione. Il santuario è una delle tappe finali del lungo cammino di Santiago di Compostela, dove i pellegrini possono spingersi per vedere l\'oceano dopo aver toccato completato il percorso che dalla Francia e la Spagna, ma anche dall\'Italia, attraverso la via Francigena, li porta a Santiago.\r\n\r\nLe fiamme hanno impiegato poco tempo a fare il loro corso. Ora si sta facendo la conta dei danni che sembrano gravissimi: La pala dell’altare centrale è stata completamente distrutta dal fuoco. Anche gli altari laterali sono stati bruciati, anche se non nella loro interezza. Sono state risparmiati elementi di poco valore culturale come confessionali e banchi.\r\n\r\nSono intervenute per spegnere il fuoco squadre di vigili e della protezione civile della città e di località vicine. Il sindaco di Muxia Felix Porto ha dichiarato al quotidiano El Mundo che le fiamme hanno fatto la loro comparsa verso le 6:45 di stamane probabilmente a causa di un fulmine. «Le fiamme – ha dichiarato – hanno provocato dannni irreversibili. La costruzione del santuario è completamente distrutta. Le poche cose che si sono salvate sono un confessionale, qualche immagine e poco altro».\r\n\r\nPorto ha poi spiegato di aver avuto una telefonata da parte del presidente della Giunta Alberto Nunez Feijoo con con cui gli veniva comunicata la vicinanza delle istituzioni locali: «E’ una giornata nera per tutta la regione – ha dichiarato – nessuno poteva aspettarsi che il santuario sarebbe stato distrutto in sole due ore».\r\n\r\nAnche Il ministro dell’istruzione e della cultura della Giunta della Galizia Jesús Vázquez si è fatto sentire, assicurando che raggiungerà il sito del disastro quanto prima per fare la valutazione dei danni. ','2014-01-05 07:00:00',1),(2,'Camion va a fuoco\r\nPaura sull\'A20','Un camion carico di legna è andato a fuoco mentre percorreva l\'A20, poco dopo i caselli di Villafranca. Tempestivo e decisivo l\'intervento di vigili del fuoco e polizia stradale\r\nCamion va a fuoco Paura sull\'A20\r\n\r\nTempestivo intervento dei vigili del fuoco e della polizia stradale, che hanno evitato che il principio d\'incendio ad un camion carico di legna avesse conseguenze gravi. Il fatto si è verificato sull\'A20, in direzione Messina, poco dopo i caselli di Villafranca. Il mezzo pesante è stato messo in sicurezza nell\'area di parcheggio Salice. Nessuna conseguenza per il conducente e per la circolazione veicolare.\r\n','2014-04-16 07:00:00',1),(3,'Milano, allagamenti e disagi','Macchine che quasi galleggiano, stivali d\'emergenza e scarponi per affrontare il fango, traffico in tilt. Rabbia per quanto è accaduto ma anche un bel po\' di ironia per sopportare meglio i disagi.\r\nDalla prima mattina, i milanesi hanno cominciato a raccontare sui social network la loro giornata alle prese con la pioggia e gli allagamenti seguiti all\'esondazione del Seveso avvenuta alle 2.50 della notte. Fotografie dall\'alto che mostrano auto impantanate, passerelle improvvisate per consentire il passaggio dei pedoni, strade “guadate” a piedi nudi. Ecco una selezione delle testimonianze dal basso dalla Milano “sommersa”. ','2014-03-12 07:00:00',6),(4,'Tromba d’aria ieri a Montesilvano: ombrelloni','Ieri mattina è stato segnalato e ripreso un “dust devils” a Montesilvano, una sorta di tromba d’aria che si presenza in condizioni di bel tempo\r\n\r\nTromba d’aria ieri a Montesilvano: ombrelloni volati a chilometri di distanza 10/07/2014 – Un fenomeno non troppo frequente è stato segnalato ieri sulla spiaggia di Montesilvano, Abruzzo, dove si è abbattuta una sorta di tromba d’aria a ciel sereno. Il fenomeno è conosciuto con il nome di Dust Devils, o “diavolo di polvere”, e si manifesta nelle belle giornate di Sole, con quasi totale assenza di nubi a sviluppo verticale. A differenza dei tornado (o trombe d’aria), i dust devils non sono collegati a nessuna attività convettiva, si formano sempre in luoghi dove c’è molta sabbia o polvere, come i campeggi, i parcheggi, le spiagge o le campagne, ed in alcuni casi sono in grado di arrecare seri danni.\r\n\r\n\r\nIl dust devils è stato segnalato alle ore 11:30 di ieri sulla spiaggia di Montesilvano. Subito scattato il panico tra i bagnanti, la spiaggia era stata presa d’assalto dai vacanzieri, dopo che i primi ombrelloni sono iniziati a volare a decine di metri di altezza (alcuni sono stati ritrovati a diversi chilometri di distanza!). Si è rapidamente formato un turbine di sabbia alto quasi un chilometro, fortunatamente durato meno di un minuto. Nessun ferito, ma il dust devils ha raggiunto proporzioni davvero notevoli. I dust devils si formano con venti non particolarmente forti (in questo caso c’era il secco vento di Garbino, proveniente da Ovest-NordOvest) e sollevamento di aria calda dal suolo. I contrasti tra aria calda del terreno ed aria fresca proveniente dal mare hanno dato manforte alla genesi del dust devils.\r\n','2014-06-22 07:00:00',6),(5,'Maxi-tamponamento sulla ss16, due feriti','(ANSA) - MONOPOLI (BARI), 6 LUG - Un maxi-tamponamento si è verificato questa mattina sulla statale 16 all\'altezza di uno dei bivi per Monopoli. Otto i veicoli coinvolti e due le persone rimaste ferite, anche se in modo lieve. La serie di tamponamenti a catena ha causato per oltre un\'ora code e rallentamenti alla circolazione, mentre il traffico è stato temporaneamente deviato in direzione dell\'abitato di Monopoli. Sul posto sono intervenuti la Polizia sradale, i vigili del fuoco e personale del \'118\'.','2014-06-02 07:00:00',4),(6,'Tempesta in Germania, bufera di sabbia e piog','Una violenta tempesta con venti fortissimi si è a abbattuta a Mainz, in Germania, durante il Love Family Park mettendo in fuga 17.000 persone\r\nUna bufera di vento e sabbia, con pioggia intensa si è abbattuta sulla cittadina tedesca di Mainz interessando in particolare l’area del Fair Park Mainz allestita per il festival musicale chiamato Love Family Park. Una giornata all’insegna della musica techno che ha richiamato 17.000 persone, forte della giornata soleggiata che però, a cominciare dal tardo pomeriggio, ha iniziato a cambiare. Il cielo si è fatto sempre più scuro finchè intorno alle 21 una violenta tempesta di pioggia e vento si è abbattuta sul festival obbligando gli organizzatori ad annullare l’evento e i 17.000 a mettersi in fuga.\r\nMa la tempesta è sopraggiunta in pochi minuti e così migliaia di persone si sono ritrovate nel bel mezzo di una bufera di venti e sabbia che li ha completamente circondati: sette persono sono rimaste ferite, colpite da oggetti volanti scagliati in aria dal forte vento. Non sono mancate molte critiche perchè, secondo molti dei presenti, gli organizzatori non avrebbero preavvisato dell’arrivo della tempesta con un margine di tempo sufficiente ad abbandonare l’area. Molte persone hanno realizzato video e foto del momento in cui la bufera è sopraggiunta, come quello che vi mostriamo.\r\n','2014-02-15 00:00:00',4),(7,'prova','rovdosafd','2014-09-22 12:12:41',2);

/*Table structure for table `Numero Emergenza` */

DROP TABLE IF EXISTS `Numero Emergenza`;

CREATE TABLE `Numero Emergenza` (
  `numero` int(11) NOT NULL,
  `titolo` varchar(45) NOT NULL,
  PRIMARY KEY (`numero`),
  UNIQUE KEY `Titolo_UNIQUE` (`titolo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `Numero Emergenza` */

insert  into `Numero Emergenza`(`numero`,`titolo`) values (1522,'Antiviolenza donne'),(112,'Carabinieri'),(1518,'CCISS'),(114,'Emergenza infanzia'),(118,'Emergenza sanitaria, Ambulanza'),(1530,'Guardia Costiera'),(117,'Guardia di finanzia'),(1515,'Guardia Forestale'),(113,'Polizia di stato'),(803555,'Protezione Civile'),(803116,'Soccorso stradale'),(19696,'Telefono Azzurro'),(636225,'Unita di crisi'),(115,'Vigili del fuoco');

/*Table structure for table `Partecipa` */

DROP TABLE IF EXISTS `Partecipa`;

CREATE TABLE `Partecipa` (
  `Utente_idUtente` int(11) NOT NULL DEFAULT '0',
  `Chat_idChat` int(11) NOT NULL,
  PRIMARY KEY (`Utente_idUtente`,`Chat_idChat`),
  KEY `fk_Chat_idx` (`Chat_idChat`),
  CONSTRAINT `fk_Chat` FOREIGN KEY (`Chat_idChat`) REFERENCES `Chat` (`idChat`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_Utente` FOREIGN KEY (`Utente_idUtente`) REFERENCES `Utente` (`idUtente`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `Partecipa` */

insert  into `Partecipa`(`Utente_idUtente`,`Chat_idChat`) values (1,1),(2,1);

/*Table structure for table `Puo avere` */

DROP TABLE IF EXISTS `Puo avere`;

CREATE TABLE `Puo avere` (
  `Incarico_id` int(11) NOT NULL,
  `Utente_idUtente` int(11) NOT NULL,
  UNIQUE KEY `Incarico_id_UNIQUE` (`Incarico_id`,`Utente_idUtente`),
  KEY `fk_idUtente_idx` (`Utente_idUtente`),
  CONSTRAINT `fk_idUtente` FOREIGN KEY (`Utente_idUtente`) REFERENCES `Utente` (`idUtente`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_Possono_avere_Incarichi1` FOREIGN KEY (`Incarico_id`) REFERENCES `Incarico` (`ID.Incarico`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `Puo avere` */

insert  into `Puo avere`(`Incarico_id`,`Utente_idUtente`) values (2,1),(1,2),(3,3),(2,4),(6,4),(7,5),(1,6),(3,10),(5,11),(6,12),(7,13),(2,14),(6,15);

/*Table structure for table `Relativo` */

DROP TABLE IF EXISTS `Relativo`;

CREATE TABLE `Relativo` (
  `numero` int(11) NOT NULL,
  `sottotipo` int(11) NOT NULL,
  UNIQUE KEY `numero_UNIQUE` (`numero`,`sottotipo`),
  KEY `fk_Relativo_2_idx` (`sottotipo`),
  CONSTRAINT `fk_Relativo_1` FOREIGN KEY (`numero`) REFERENCES `Numero Emergenza` (`numero`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Relativo_2` FOREIGN KEY (`sottotipo`) REFERENCES `Sottotipo` (`idSottotipo`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `Relativo` */

insert  into `Relativo`(`numero`,`sottotipo`) values (115,1),(112,2),(113,2),(118,2),(115,3),(118,3),(803116,4),(112,5),(115,5),(118,5),(1530,6);

/*Table structure for table `Segnalazione` */

DROP TABLE IF EXISTS `Segnalazione`;

CREATE TABLE `Segnalazione` (
  `idSegnalazione` int(11) NOT NULL AUTO_INCREMENT,
  `Coordinate` varchar(255) DEFAULT NULL,
  `Descrizione` text NOT NULL,
  `Numero_commenti` int(10) unsigned NOT NULL DEFAULT '0',
  `Data_pubblicazione` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Luogo` varchar(255) DEFAULT NULL,
  `idSottotipo` int(11) DEFAULT '-1',
  PRIMARY KEY (`idSegnalazione`),
  KEY `fk_Segnalazione_1_idx` (`idSottotipo`),
  CONSTRAINT `fk_Segnalazione_1` FOREIGN KEY (`idSottotipo`) REFERENCES `Sottotipo` (`idSottotipo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `Segnalazione` */

insert  into `Segnalazione`(`idSegnalazione`,`Coordinate`,`Descrizione`,`Numero_commenti`,`Data_pubblicazione`,`Luogo`,`idSottotipo`) values (1,'(2,4)','Terremoto',3,'2014-06-22 20:32:11','Napoli',5),(2,'(-32,-54)','Guasto',1,'2014-04-21 07:20:00','Salerno',6),(3,'(50,300)','Incendio',1,'2014-07-10 23:50:55','Caserta',1),(4,'(10,15)','Guasto',0,'2014-07-05 11:58:00','Salerno',4),(5,'(20,35)','Incidente',1,'2014-11-03 22:33:54','Oristano',2),(6,'(15,15)','Incendio',1,'2014-04-17 03:32:05','Cava De\'Tirreni',3);

/*Table structure for table `Sottotipo` */

DROP TABLE IF EXISTS `Sottotipo`;

CREATE TABLE `Sottotipo` (
  `idSottotipo` int(11) NOT NULL AUTO_INCREMENT,
  `Cause` varchar(255) DEFAULT NULL,
  `Descrizione` text NOT NULL,
  `Livello_Priorita` bigint(20) NOT NULL DEFAULT '5',
  `idTipo` int(11) NOT NULL,
  PRIMARY KEY (`idSottotipo`,`idTipo`),
  KEY `fk_Sottotipo_1_idx` (`idTipo`),
  CONSTRAINT `fk_Sottotipo_1` FOREIGN KEY (`idTipo`) REFERENCES `Tipo` (`idTipo`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `Sottotipo` */

insert  into `Sottotipo`(`idSottotipo`,`Cause`,`Descrizione`,`Livello_Priorita`,`idTipo`) values (1,'Doloso','Incendio nelle campagne vicino la mia abitazione.Ho chiamato i pompieri e sto aspettando che arrivino',5,2),(2,'Stradale','Tamponamento tra due auto',5,3),(3,'D\'abitazione','Incendio ristorante sotto casa',5,1),(4,'Veicolo','Improvviso guasto al veicolo',5,5),(5,'Terremoto','',5,4),(6,'Imbarcazione','Guasto imbarcazione',5,6);

/*Table structure for table `Tipo` */

DROP TABLE IF EXISTS `Tipo`;

CREATE TABLE `Tipo` (
  `idTipo` int(11) NOT NULL AUTO_INCREMENT,
  `Enti Coinvolte` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTipo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `Tipo` */

insert  into `Tipo`(`idTipo`,`Enti Coinvolte`) values (1,'Vigili del fuoco e Carabinieri'),(2,'Vigili del fuoco e Guardia Forestale'),(3,'Emergenza Sanitaria ,Carabinieri e Polizia'),(4,'Emergenza Sanitari,Vigli del Fuoco e Carabinieri'),(5,'Soccorso Stradale'),(6,'Guardia Costiera');

/*Table structure for table `Utente` */

DROP TABLE IF EXISTS `Utente`;

CREATE TABLE `Utente` (
  `idUtente` int(11) NOT NULL AUTO_INCREMENT,
  `livello_utente` tinyint(3) unsigned zerofill NOT NULL DEFAULT '000',
  `email` varchar(45) NOT NULL,
  `data_di_nascita` date NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `codice_fiscale` varchar(45) NOT NULL,
  `via` varchar(45) NOT NULL,
  `citta` varchar(45) NOT NULL,
  `cap` int(11) NOT NULL,
  `stato` varchar(45) NOT NULL,
  `numero_notifiche_non_lette` int(10) unsigned DEFAULT '0',
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`idUtente`),
  UNIQUE KEY `Email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `Utente` */

insert  into `Utente`(`idUtente`,`livello_utente`,`email`,`data_di_nascita`,`nome`,`cognome`,`codice_fiscale`,`via`,`citta`,`cap`,`stato`,`numero_notifiche_non_lette`,`password`) values (1,003,'MarioRossi@email.it','1980-12-13','Mario','Rossi','MRARSS80A13H703M','Valerio Laspro','Salerno',84100,'Italia',0,'111'),(2,001,'test@test.it','1959-01-17','Giuseppe','Roncas','GRDLHR59B17H703Z','Piazzetta Scalette Rubiani','Salerno',84036,'Italia',0,'1'),(3,000,'FedericoBasso@email.it','1995-05-02','Federico','Basso','BSSFRC95E02F839D','Toledo','Napoli',80121,'Italia',0,'11'),(4,000,'MirandaTrentino@email.it','1988-07-08','Miranda','Trentino','TRNMND88L48B172X','Carlo Alberto','Brienno',22010,'Italia',0,'1111'),(5,000,'AntonioRusso@email.it','1976-10-07','Antonio','Russo','RSSNTN76S07H703F','San Giovanni Bosco','Salerno',84100,'Italia',0,'11111'),(6,000,'FrancescaEsposito@email.it','1966-04-12','Francesca','Esposito','SPSFNC66D54F839A','Vespucci','Napoli',84121,'Italia',0,'111111'),(7,000,'GiuseppeRonca@email.it','1992-04-15','Giuseppe','Ronca','RNCGPP92D15C361F','Giuseppe Pellegrino','Cava De\'Tirreni',84013,'Italia',0,'1111111'),(8,000,'DiegoAvella@email.it','1993-07-13','Diego','Avella','DGIVLL93L13C361P','Gaetano Filangieri','Cava De\'Tirreni',84013,'Italia',0,'1111111'),(9,000,'AngeloPassaro@email.it','1993-05-21','Angelo','Passaro','PSSNGL93E21H703I','Salvatore Calenda','Salerno',84100,'Italia',0,'1111111'),(10,000,'AndreaMagnani@email.it','1983-11-21','Andrea','Magnani','MGNNDR83T21H703U','Carmine','Salerno',84100,'Italia',0,'1111111'),(11,000,'GiuliaCirillo@email.it','1990-05-05','Giulia','Cirillo','CRLGLI90E45H703S','Irno','Salerno',84100,'Italia',0,'11111111'),(12,000,'LucaTosi@email.it','1975-05-21','Luca','Tosi','TSOLCU75E21H703N','Luigi Guercio','Salerno',84100,'Italia',0,'11111111'),(13,000,'ChiaraCarboni@email.it','1989-08-17','Chiara','Carboni','CRBCHR89M57H703B','Armando Diaz','Salerno',84100,'Italia',0,'11111111'),(14,000,'MatteoNardi@email.it','1980-10-22','Matteo','Nardi','NRDMTT80R22H703Y','Leonino Vinciprova','Salerno',84100,'Italia',0,'11111111'),(15,000,'ClorindaDeRose@email.it','1969-01-11','Clorinda','DeRose','DRSCRN69B51G113Y','Croce Rossa','Oristano',9080,'Italia',0,'11111111');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
