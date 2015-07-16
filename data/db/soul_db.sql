CREATE DATABASE  IF NOT EXISTS `soul_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `soul_db`;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'2015-07-16 00:00:00','fasciati la gamba!',23,25),(2,'2015-07-16 00:00:00','aiuto',23,25);
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
INSERT INTO `emergency_number` VALUES (118,'Ambulanza'),(1522,'Antiviolenza donne'),(112,'Carabinieri'),(1518,'CCISS'),(114,'Emergenza infanzia'),(1530,'Guardia Costiera'),(117,'Guardia di finanzia'),(1515,'Guardia Forestale'),(113,'Polizia di country'),(803555,'Protezione Civile'),(803116,'Soccorso stradale'),(19696,'Telefono Azzurro'),(636225,'Unita di crisi'),(115,'Vigili del fuoco');
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
INSERT INTO `emergency_number_rel` VALUES (112,1),(118,1),(118,2),(118,3),(118,4),(118,5),(118,6),(112,7),(118,7);
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emergency_subtype`
--

LOCK TABLES `emergency_subtype` WRITE;
/*!40000 ALTER TABLE `emergency_subtype` DISABLE KEYS */;
INSERT INTO `emergency_subtype` VALUES (1,'Infarto','Per infarto si intende la necrosi di un tessuto per ischemia, cioè per grave deficit di flusso sanguigno.I sintomi sono diversi a seconda dell\'organo interessato, tuttavia il sintomo principale è rappresentato da dolore acuto (ad insorgenza improvvisa), di varia intensità.\nConsigli:\nCercate di mantenere un peso sano in quanto sovrappeso e obesità sono fattori di rischio per le malattie cardiache; smettete di fumare (per fumo si intende quello attivo e passivo), minimizzate stress, rabbia, depressione, così come atteggiamenti comportamentali di impazienza, eccessiva competitività e ostilità.',5,3),(2,'Frattura','La frattura in medicina è l’interruzione parziale o totale della continuità di un osso. Se la frattura riguarda solo l\'osso è detta \"isolata\", mentre se coinvolge anche i legamenti è detta \"associata.Le fratture sono classificate secondo diversi criteri.\nConsigli:\nDavanti a una frattura, è bene sdraiare ed immobilizzare l\'infortunato evitando che si muova. Se non ci sono particolari problemi di urgenza (rischi di vita) evitare il trasporto finché l\'arto non sia stato completamente immobilizzato. E\' sempre meglio attendere il soccorso qualificato di personale dotato delle attrezzature di immobilizzazione. Fare attenzione che l\'infortunato non entri in uno stato di shock e confortarlo.',3,3),(3,'Febbre','La febbre o piressia è un segno clinico; si definisce come uno stato patologico temporaneo che comporta un\'alterazione del sistema di termoregolazione ipotalamica e una conseguente elevazione della temperatura corporea al di sopra del valore considerato normale (circa 36.8 gradi Celsius per gli esseri umani in condizioni basali). \nConsigli:\nAssumere molti liquidi, per minimizzare il rischio di disidratazione\nApplicare impacchi rinfrescanti per favorire la perdita di calore dal corpo (spugnature con acqua fredda)\nIn caso di terapia antibiotica, si consiglia di incrementare l\'apporto di potassio a scapito del sodio: gli antibiotici, infatti, favoriscono l\'escrezione di potassio e la ritenzione di sodio\nMangiare poco ma spesso: così facendo, viene favorita la funzione digestiva\nMasticare lentamente\nIn caso di digiuno prolungato, assumere formulazioni reidratanti ed alcalinizzanti (citrato di sodio)\nQuando la nausea impedisce di assumere liquidi, è consigliato succhiare del ghiaccio\nIn caso di brividi, immergersi in vasche con acqua calda: i brividi, caratteristici della febbre, possono essere alleviati con bagni caldi\nNon riscaldare eccessivamente gli ambienti\nSe la febbre compare in un neonato (<4 mesi) è necessario il consulto medico\nMonitorare gli sbalzi febbrili: la febbre intermittente potrebbe celare patologie molto gravi\nUtilizzare i farmaci con moderazione\nCoprire la bocca con la mano o, meglio ancora, con un fazzoletto quando si starnutisce o si tossisce\nRimanere a riposo',1,3),(4,'Polmonite','La polmonite è una malattia dei polmoni e del sistema respiratorio caratterizzata dall\'infiammazione degli alveoli polmonari, i quali si riempiono di liquido che ostacola la funzione respiratoria. Di solito è causata da un\'infezione dovuta a virus, batteri e altri microrganismi. Il quadro clinico è tipicamente caratterizzato da tosse, dolore toracico, febbre e difficoltà respiratorie.\nConsigli:\nLa prevenzione dalla polmonite comprende la vaccinazione, l\'adozione delle idonee misure comportamentali ed il trattamento appropriato di eventuali altri problemi di salute.\nSmettere di fumare e ridurre il più possibile l\'esposizione all\'aria inquinata sono strategie fortemente raccomandate.',4,3),(5,'Varicella','La varicella è una malattia esantematica altamente contagiosa ed epidemica causata da un\'infezione primaria con il Virus varicella-zoster (VZV o Herpesvirus umano 3), un virus a DNA appartenente alla famiglia Herpesviridae, sottofamiglia Alphaherpesvirinae, genere Varicellovirus. La condizione inizia solitamente con rash cutaneo vescicolare, principalmente esteso al corpo e alla testa piuttosto che alle estremità. Le vescicole guariscono poi senza lasciare cicatrici. All\'esame, l\'osservatore trova in genere lesioni in vari stadi di guarigione.La varicella è una malattia che si diffonde facilmente per via aerea attraverso colpi di tosse o starnuti di individui malati o attraverso il contatto diretto con le secrezioni del rash. Una persona con la varicella è infettiva uno o due giorni prima che appaia l\'eruzione.Essa rimane contagiosa fino a quando tutte le lesioni vengono ricoperte da una crosta (circa sei giorni).Le lesioni crostose non sono contagiose.Questa malattia, nota fin dall\'antichità, venne nettamente distinta dal vaiolo soltanto ai primi del XIX secolo.La varicella è stata osservata anche in altri primati, compresi gli scimpanzé e i gorilla.\nLa terapia delle forme non complicate è volta semplicemente ad alleviare i sintomi: con antipiretici come il paracetamolo (l\'acido acetilsalicilico è controindicato per il rischio d\'insorgenza della sindrome di Reye) ed antistaminici per via orale per mitigare il prurito e quindi il riflesso di grattamento; per attenuare la sensazione di prurito è oggi sconsigliato il talco mentolato, in quanto ritarda il consolidamento delle lesioni cutanee. Sono più adatte le creme anti-prurito, il semplice borotalco e naturalmente i preparati lenitivi contro le lesioni da herpes.\n\nNelle forme complicate da encefalite o polmonite il trattamento con aciclovir, valaciclovir o famciclovir, riduce i giorni di febbre ed il numero di lesioni (purché iniziato entro 24-48 ore dalla comparsa dei primi sintomi), ma è indicato prevalentemente nei soggetti a rischio; può provocare una possibile interferenza negativa con la risposta immune, a causa dell\'azione inibente sulla replicazione virale, e quindi provocare al soggetto periodiche lievi ricadute. Se insorgono problemi alle vie respiratorie, si è verificata una sovrainfezione batterica e si deve intervenire con antibiotici: in questo caso è da evitare la sovrapposizione con gli antivirali. Può essere utile far indossare ai bambini guanti di cotone.\n\nLa profilassi prevede un periodo di isolamento di durata variabile (di solito 2 settimane per i soggetti colpiti da forme più aggressive, 1 settimana per quelli con forme lievi). La progressiva introduzione del vaccino dovrebbe consentire in futuro una prevenzione più attiva con somministrazione di dosi ai contatti dei malati. Una volta esaurite la febbre e la tosse (bisogna aspettare almeno 60-72 ore consecutive senza febbre), un malato può uscire, ma deve fare molta attenzione ad evitare la rottura accidentale delle vesciche per evitare di contagiare altre persone. Un buon sistema per proteggere eventuali vescicole \"a vista\" può essere la loro copertura con cerotti oppure garze, applicati con la dovuta cautela. Il ricambio d\'aria delle stanze va effettuato solo dopo disinfezione, preferibilmente con un vaporizzatore, onde evitare di disperdere nell\'atmosfera un ingente quantitativo di virus.[23]\n\nNei soggetti ad alto rischio è possibile anche l\'immunoprofilassi passiva con somministrazione di immunoglobuline normali o specifiche.',3,3),(6,'Scarlattina','La scarlattina è una malattia infettiva acuta contagiosa, caratteristica dell\'età pediatrica, che si manifesta con febbre e angina faringea ed è caratterizzata da insorgenza di esantema puntiforme (la capocchia di spillo).Essa si trasmette per via aerea. Non esiste un vaccino, ma può essere trattata efficacemente tramite la somministrazione di antibiotici. La maggior parte delle manifestazioni cliniche sono dovute alle tossine eritrogeniche prodotte dal batterio Streptococcus pyogenes (streptococco di gruppo A) quando viene infettato da un batteriofago. Infatti, a differenza di altre malattie contagiose simili e tipiche dell\'infanzia, come la rosolia e la varicella, la scaralattina è l\'unica provocata da batteri anziché da virus.Prima dell\'avvento degli antibiotici, la scarlattina rappresentava una delle principali cause di morte. Inoltre, talvolta, era responsabile per l\'insorgenza di complicanze tardive come la glomerulonefrite e l\'endocardite, quest\'ultima poi spesso causa di problemi alle valvole cardiache spesso di esito infausto. I ceppi di streptococco di gruppo A che producono la tossina erotrogenica non sono intrinsecamente più pericolosi di altri ceppi che non lo fanno, essi infatti portano ad una diagnosi più facile per via della caratteristica eruzione cutanea.\nLa terapia va attuata con antibiotici attivi sull\'infezione streptococcica (penicillina benzatina, amoxicillina, cefalosporina). Inoltre si somministrano antifebbrili e si cerca di idratare adeguatamente il paziente.',2,3),(7,'Incidente Stradale','Consigli:<br>è bene <b>non rimuovere</b> il casco ai motociclisti, a meno che non si sia padroni della tecnica necessaria; infatti, i danni possono essere abbastanza gravi se questo non viene fatto correttamente (ad esempio si rischia di spostare qualche vertebra o di causare tetraplegia)<br><b>non</b> dare da bere all\'infortunato (si rischia il soffocamento in caso di trauma alle vie aeree).<br>Se la vittima ha subito lesioni dovute a perforazioni e l\'oggetto è rimasto all\'interno della ferita, <b>non</b> va rimosso: l\'oggetto stesso potrebbe fare da ostacolo per il flusso sanguigno e toglierlo potrebbe portare alla fuoriuscita di sangue e alla conseguente morte per dissanguamento.<br>',4,3);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,'Ebola, nuova cura sperimentale per il medico ','<img src =\"images/ebola.jpg\" height=\"200\" >ROMA - Il medico di Emergency affetto da Ebola sta per iniziare un ulteriore trattamento con un farmaco sperimentale appena arrivato dall\'estero. Lo hanno affermato i sanitari dello Spallanzani, dove il paziente è ricoverato. \"Nella serata di ieri c\'è stato un peggioramento delle condizioni generali. Tuttavia, nella nottata e stamattina, il quadro clinico seppur grave, è tornato sovrapponibile a quello della giornata di ieri\", hanno spiegato i medici nell\'ultimo bollettino, aggiungendo che la prognosi rimane riservata.\n\nAnche questo nuovo farmaco sperimentale, hanno spiegato i medici, è stato ottenuto grazie ad una catena di supporto, in Italia costituita dal ministero della Salute, inclusa la rete degli uffici di sanità di frontiera Us (Usmaf) e di solidarietà istituzionale, con l\'aiuto del coordinamento internazionale per la gestione di Ebola dell\'Organizzazione mondiale della sanità.','2015-08-20 00:00:00',4),(2,'Caldo,settimana di fuoco: bollino rosso','<img src =\"images/caldo.jpg\" height=\"200\" >Roma - Altro che tregua. Quella appena cominciata i preannuncia un’altra settimana di fuoco: torna l’allarme ondate di calore.\n\nLo conferma il ministero della Salute nel suo bollettino quotidiano. Domani, martedì, saranno cinque le città da «bollino rosso», dove il caldo diventa un rischio per la salute anche di soggetti sani: si tratta di Bolzano, Campobasso, Latina (tra le città più calde con 39 gradi percepiti), Palermo e Perugia.\n\n|Le previsioni per i prossimi giorni|\n\nMercoledì 15 il numero salirà a otto: si aggiungeranno infatti anche Firenze (dove si toccheranno i 39 gradi), Roma (punte di 37 gradi) e Torino (38 gradi).\n\nA queste vanno sommate altre 9 città con «bollino arancione», cioè con condizioni di rischio per soggetti fragili, anziani, malati cronici e bambini. Si tratta di Bologna, Brescia, Civitavecchia, Frosinone, Genova, Milano, Rieti, Verona e Viterbo. ','2015-06-06 00:00:00',4),(3,'Influenza aprile 2015','<img src =\"images/influenza.jpg\" height=\"200\">E\' finalmente terminata la stagione influenzale, che per quest\'anno ha messo a letto circa sei milioni di italiani. Durante le prossime settimane e fino alla fine di aprile, il bollettino Influnet prevede ancora alcuni casi di contagio ma con un netto calo di malati. Nella prima settimana di aprile, si sono registrati, ancora, circa 116mila casi influenzali con una maggiore incidenza per quanto riguarda i bambini molto piccoli o fino a 14 anni. Per gli adulti, invece, i casi maggiori riportati hanno interessato gli anziani sopra i 65 anni di età. Secondo i dati riportati dall\'Iss, la stagione appena trascorsa è stata di media entità, anche se non sono mancati casi più complicati e gravi. Passata l\'influenza classica, ora il pericolo si riferisce ai virus parainfluenzali, tra cui spicca l\'influenza intestinale, vero malanno primaverile e in genere del cambio stagionale.\n\nInfluenza 2015 al termine: aprile mese del virus intestinale\n\nSecondo i dati resi noti dai virologi ospedalieri, scampato il pericolo per l\'influenza 2015, ora bisognerà fare i conti con il virus intestinale o con i virus affini che, somigliano molto all\'influenza comune con la differenza che durano molto meno. Solitamente, il periodo primaverile è quello in cui ci sono molto più rischi di contrarre questi virus a causa di sbalzi termici che portano alla proliferazione di batteri. I sintomi dei virus parainfluenzali che colpiscono le vie aree, rimangono sostanzialmente simili a quelli dell\'influenza comune con mal di gola, placche, tosse, tracheite o raffreddore, che può essere confuso con le allergie stagionali.\n\nA volte e presente la febbre, anche elevata ma la durata è minore, non superando, di fatto, i 3 o 4 giorni. Differente è il virus intestinale che colpisce stomaco e apparato gastrointestinale. Il contagio, in questo caso, è molto più veloce e bastano anche 24 o 48 ore per la trasmissione da un soggetto all\'altro. Il virus intestinale può essere molto fastidioso: dura circa 2 o 3 giorni e porta sintomi come diarrea, vomito, malessere, febbre bassa o alta sopra i 38 gradi e disidratazione. Valgono i classici consigli per guarire velocemente ed evitare cure fai da te con medicinali.','2015-04-06 00:00:00',4),(4,'Il vaccino esavalente','<img src =\"images/vaccino.jpg\" height=\"200\">La poliomielite è una malattia, causata da 3 tipi di virus intestinali, che si trasmette da uomo a uomo per via alimentare attraverso feci e saliva. In circa il 95% delle persone infettate dai virus della polio non si manifesta alcun disturbo. Sintomi minori possono comprendere mal di gola, febbre moderata, nausea e vomito. In alcuni casi (1-2%) si può manifestare rigidità di collo, della schiena o delle gambe, ma senza paralisi. Invece, in meno dell\'1% dei casi (all\'incirca uno ogni 1000 infezioni) si verifica la paralisi. In talune circostanze i virus poliomielitici possono causare anche paralisi respiratorie rendendo così impossibile la respirazione autonoma. Alcune persone possono recuperare la funzionalità muscolare in modo completo, in altri casi sono possibili ricadute dopo 30-40 anni con dolori muscolari e progressivo indebolimento.\n\nIl tetano è una grave malattia infettiva causata dall’azione di una tossina (tossina tetanica) prodotta da batteri (clostridi del tetano) che vivono nel suolo o nell\'intestino degli animali. La malattia può essere mortale nel 20- 30% circa dei casi.\nA differenza delle altre malattie infettive prevenibili con la vaccinazione, il tetano non si trasmette da persona a persona. L\'infezione deriva spesso da una ferita, anche banale, occorsa ad una persona non adeguatamente vaccinata. Perciò il rischio tetano può essere considerato quotidiano in una persona non vaccinata.\nNei paesi in via di sviluppo il tetano può colpire le donne non vaccinate infettatesi durante il parto oppure i loro neonati per infezione del cordone ombelicale (tetano neonatale, oggi del tutto scomparso in Occidente).\nRaramente, e sempre in persone non vaccinate, il tetano si può contrarre anche attraverso l\'uso di siringhe infette, morsi di animali, ustioni, abrasioni.\nL\'infezione tetanica produce violente contrazioni muscolari, chiamate spasmi. Altri sintomi possono essere febbre, sudorazione, ipertensione arteriosa e tachicardia.\nGli spasmi possono interessare le corde vocali e i muscoli respiratori, tanto da mettere in seria difficoltà la respirazione. Le contrazioni possono essere così violente da produrre anche fratture ossee.\n\nLa difterite è una grave malattia infettiva causata dall’azione di una tossina (tossina difterica) prodotta da batteri che si trasmettono per via aerea. Solitamente la difterite inizia con mal di gola, febbre moderata, tumefazione del collo.\nMolto spesso i batteri della difterite si moltiplicano nella gola (faringe) dove si viene a formare una membrana di colore grigiastro che può soffocare la persona colpita dalla malattia. A volte queste membrane si possono formare anche nel naso, sulla pelle o in altre parti del corpo.\nLa tossina difterica, diffondendosi tramite la circolazione sanguigna, può causare paralisi muscolari, lesioni a carico del muscolo cardiaco con insufficienza cardiaca, lesioni renali, fino a provocare la morte della persona colpita. \nLa letalità è di circa il 5-10% ma in molti casi, nei sopravvissuti, permangono danni permanenti a carico di cuore, reni, sistema nervoso.\n\nLa pertosse (o tosse canina) è una malattia causata da un batterio, la Bordetella pertussis.\nE\' una delle malattie infettive più contagiose che si conoscano, tanto che un bambino con pertosse può contagiare fino al 90% di bambini non immuni con cui viene a contatto. Si trasmette per via aerea da persona a persona con la tosse o gli starnuti.\nLa malattia non complicata dura circa da 6 a 10 settimane e si compone di tre stadi: catarrale, parossistico e della convalescenza. La malattia esordisce solitamente con starnuti, raucedine e una fastidiosa tosse notturna. Successivamente, dopo 10-14 giorni, si manifesta una tosse convulsiva e ostinata che rende difficoltosa la respirazione e persino l\'alimentazione. Questa fase può durare fino a 2-3 settimane. Gli accessi di tosse sono costituito da 5 – 15 colpi di tosse violenti e ravvicinati che si verificano durante una singola esprirazione. Solitamente si concludono con una rapida e profonda ispirazione: il tipico \"urlo inspiratorio\" e l’espulsione di un blocchetto di catarro molto denso e vischioso. Gli attacchi sono seguiti, a volte, dal vomito. Nei lattanti si possono avere crisi di soffocamento. La convalescenza inizia in genere dopo 4 settimane. Gli accessi di tosse diventano meno frequenti e gravi e le condizioni generali del bambino migliorano.\nLa malattia è tanto più grave quanto più precocemente colpisce il bambino.  In media, circa il 20 % dei casi di pertosse devono essere ospedalizzati.\nLe complicanze polmonari si verificano in un caso ogni 20 ma in più di un caso ogni 10 neonati di età inferiore a 6 mesi. Altra grave complicanza l’encefalopatia colpisce da 1 a 2 bambini ogni 1000. La mortalità della pertosse è alta: di 2 decessi ogni 1000 casi, pressoché completamente a carico dei bambini nel primo anno di vita. La causa principale di morte è la polmonite.\n\nIl virus dell\'epatite B (HBV) è trasmesso da una persona all\'altra col sangue e con i fluidi corporei, in genere attraverso i contatti sessuali o l\'uso di siringhe non sterili. Tuttavia circa il 30% delle persone che si sono infettate, non ha fattori di rischio noti. Il virus può essere trasmesso ai neonati dalle madri infette. L\'infezione colpisce in particolare il fegato.\nI sintomi della malattia acuta da HBV variano e possono comprendere perdita di appetito, affaticamento, nausea, ittero (colore giallo degli occhi e della pelle), dolore alle articolazioni e rash (rossore) cutaneo. Più della metà dei bambini che acquisiscono l’infezione non mostrano segni o sintomi, anche se possono diventare portatori cronici.  Circa il 90% dei bambini che sono infettati alla nascita dalla loro madre e il 30%-50% di quelli che si infettano all\'età di 5 anni, diventano portatori cronici dell\'HBV, mentre le persone che si infettano da adulti hanno soltanto un 6-10% di rischio di infezione cronica. I portatori cronici possono sviluppare una epatite cronica o il tumore del fegato. L’epatite B è soprattutto grave per queste complicanze croniche che si sviluppano a distanza di 30 - 40 anni nei portatori cronici.  Più giovane è il paziente quando acquisisce la malattia, più è probabile che sviluppi una malattia cronica del fegato o il tumore.\n\nL’Hib (Haemofilus influenzae di tipo b)  è un batterio che può infettare le membrane che rivestono il sistema nervoso centrale causando una meningite batterica. L’Hib può causare altri seri problemi di salute, come polmonite, gonfiore alla gola con difficoltà a respirare (epiglottide), infezioni del sangue (sepsi).\nL\'Hib si trasmette attraverso le goccioline di saliva emesse con la tosse o lo starnuto.\nLa meningite colpisce soprattutto i bambini, più spesso dai 3 mesi fino ai 3 anni di età, con un picco verso i sei mesi, mentre non è comune dopo i cinque anni.\nNel peggiore dei casi la meningite è fatale. Circa il 5% dei bambini (500 su ogni 100.000) affetti da meningite muore per questa malattia anche se sottoposto a terapia antibiotica. Circa il 15-30% dei bambini che sopravvivono evidenzia danni neurologici permanenti come cecità, sordità, ritardo mentale e difficoltà di apprendimento.\nCon il vaccino esavalente:\n\n    un unico vaccino protegge da tutte queste malattie\n    si riduce il numero di iniezioni da effettuare ad ogni appuntamento vaccinale (ed anche il numero di appuntamenti).\n\nIl vaccino contiene:\n\n        tossoide difterico\n        antigene di superficie ricombinante del virus dell’epatite B\n        polisaccaride del Haemophilus influenzae tipo b\n        antigeni della pertosse: tossoide pertossico, emoagglutinina filamentosa, pertactina\n        virus inattivati della poliomielite tipo 1, 2, 3\n        tossoide tetanico\n\nIl vaccino contiene inoltre:lattosio anidro, sodio cloruro, medium 199 (contenente principalmente aminoacidi, sali minerali, vitamine). E’ adsorbito su composti di alluminio (alluminio ossido idrato, fosfato di alluminio) che migliorano la capacità di stimolare la risposta immune. Può inoltre contenere tracce di neomicina o polimixina B (sostanze che in  fase di preparazione servono a garantire la sterilità del preparato).\n\nÞ  il vaccino esavalente unisce insieme le 6 componenti protettive, senza moltiplicare quindi la presenza delle altre sostanze necessariamente presenti in ogni preparato vaccinale, che verrebbero a sommarsi con la somministrazione separata dei vaccini singoli.\n\nChi dovrebbe essere vaccinato?\nTutti i bambini dovrebbero essere vaccinati contro le sei malattie nel primo anno di vita. La vaccinazione dovrebbe iniziare a due mesi di età.\n\nChi non dovrebbe essere vaccinato?\n\n    i bambini con meno di 6 settimane di vita\n    le persone che hanno avuto una reazione allergica a precedenti dosi di vaccino o a componenti del vaccino\n\n    i bambini con una malattia acuta grave o moderata, con o senza febbre in atto dovrebbero consultare il medico prima di effettuare la vaccinazione\n\nDosi e calendario\nSono necessarie 3 dosi per conferire la migliore protezione possibile. Il vaccino esavalente si esegue al 3°, 5° e 12-13° mese di vita e può essere effettuato contemporaneamente ad altri vaccini. Inoltre, mentre per le componenti Epatite B e Hib non saranno necessarie ulteriori dosi,  per mantenere elevata la protezione nel tempo, è previsto un richiamo a 6 anni per difterite, tetano, pertosse e polio e 14 anni per difterite, tetano, pertosse.\nViene somministrato con un’iniezione intramuscolare nella parte alta del muscolo anteriore della coscia.\n\nEfficacia del vaccino\nIl Vaccino esavalente è molto immunogeno (capace di indurre un livello di anticorpi protettivo) ed elevata è anche l’efficacia protettiva  che è di circa l’85% di protezione nei confronti della pertosse, è superiore al 95 % per la difterite e al 99 % per l’epatite B e le forme invasive dell’HIB e  sostanzialmente il 100% per polio e tetano. La protezione è molto lunga nel tempo e il rispetto del calendario assicura che questa protezione duri sostanzialmente tutta la vita.\nPer maggiori informazioni vai alla scheda di approfondimento.\n\nEffetti collaterali\n\n    qualche ora dopo la vaccinazione il bambino potrà manifestare gonfiore, arrossamento e dolorabilità in corrispondenza della sede di iniezione. La frequenza di queste manifestazioni (circa il 20% dei casi) tende ad aumentare con il numero di dosi somministrate.  Molto raramente il gonfiore si estende a gran parte dell’arto. Il dolore è generalmente modesto. L’applicazione di un panno umido e freddo può ridurre i sintomi. Talvolta il bambino si rifiuta temporaneamente di far forza sull’arto sede di iniezione. Le reazioni locali durano in genere un paio di giorni.\n\n    il bambino può avere febbre nelle 48 ore successive alla vaccinazione (circa 1/3 dei casi); è consigliato farlo riposare , idratarlo bene ed utilizzare un farmaco contro la febbre se la temperatura sale sopra a 38.5°C o quella rettale a 39°C.\n\n    dopo la vaccinazione, in meno dell’1 % dei casi ?, nel sito di iniezione può comparire un piccolo nodulo, generalmente non dolente,\n\nche persiste per alcune settimane.\n\n    il vaccino può causare diminuzione dell’appetito, irritabilità, sonnolenza o viceversa agitazione, pianto insolito e convulsioni febbrili.\n\n    le reazioni allergiche gravi sono estremamente rare. Se compare una reazione allergica, comparirà nei primi minuti successivi alla vaccinazione e sarà trattata dal personale vaccinatore; è per tale motivo che si raccomanda di sostare presso la sede vaccinale per almeno 15 minuti dopo la somministrazione del vaccino.\n\n    sono stati riportati molto raramente (0,5-1 caso su 100.000 dosi) dolori intensi e debolezza dell’arto correlate a vaccini contenenti la componente antitetano; questi disturbi possono permanere alcune settimane.\n\n \n\nPer maggiori informazioni vai alla scheda di approfondimento.\nparte standard\nCirca la metà dei bambini che ricevono vaccini esavalenti, così come il vaccino triplo DTaP o le altre combinazioni di vaccino contro la pertosse, non ha nessuna reazione.\nLa maggior parte degli altri bambini presenta solo reazioni lievi. L’evento più frequente è la febbre che si può avere in circa un terzo dei bambini. Reazioni locali si verificano nel 20 % dei casi. Queste comprendono dolore, rossore e gonfiore nel punto dove è stata eseguita l’iniezione; si verificano  in genere entro 48 ore dalla vaccinazione e durano fino a un paio di giorni. Le reazioni locali aumentano con il numero di dosi eseguite. Circa il 40%  dei bambini ha gonfiore o dolenzia al braccio con la quarta dose di DTaP eseguita a 5-6 anni. Alcuni bambini possono sviluppare un esteso gonfiore temporaneo dell’arto dove l\'iniezione è stata eseguita.\nIn caso di reazioni locali usare panni freddi o farmaci a base di paracetamolo, se necessario, per ridurre il dolore.\n\nIn caso di reazioni febbrili:\n\n    Dare da bere molti liquidi\n    Non vestire troppo il bambino se è caldo\n    Usare farmaci a base di paracetamolo (non aspirina) o panni freddi, se necessario, per ridurre la febbre\n\nNel caso che i sintomi si protraggano per più di due giorni può essere opportuno consultare il medico per verificare se questi rappresentino un comune effetto collaterale ad una vaccinazione o se invece si riferiscano ad un\'altra malattia che deve essere riconosciuta e trattata.\nIn rari casi (1-2 ogni 10.000) si possono avere reazioni più importanti, come convulsioni correlate alla febbre alta. Reazioni allergiche di tipo anafilattico con gonfiore della bocca, difficoltà del respiro, pressione bassa e shock, sono del tutto eccezionali (meno di 1 caso ogni milione di vaccinati).\nSe si verificasse una reazione importante o insolita, rivolgetevi al vostro medico. In questo caso va fatta la segnalazione d\'avvento avverso ed è importante avvisare il servizio di vaccinazione.\n\nTanti vaccini insieme?\nLa somministrazione dei 6 vaccini insieme apparentemente può sembrare un “sovraccarico” di attività per il sistema immune, tuttavia questo sospetto non basa su fatti oggettivi: il nostro organismo (anche quello di un bambino molto piccolo) è infatti in grado di rispondere normalmente ad un numero di stimoli (antigeni) enormemente maggiore. E questo avviene normalmente, con il contatto quotidiano con le diverse sostanze cui il nostro organismo è esposto o, ancor di più, quando viene aggredito da un’agente infettivo (ad esempio l’agente infettivo naturale della pertosse contiene più di 2000 antigeni che stimolano contemporaneamente il sistema immunitario, mentre il vaccino antipertosse ne contiene solo 3).','2015-01-02 00:00:00',4);
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
  `image` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_report`),
  KEY `fk_report_1_idx` (`id_subtype`),
  CONSTRAINT `fk_report_1` FOREIGN KEY (`id_subtype`) REFERENCES `emergency_subtype` (`id_subtype`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (1,'40-60','',0,'2015-06-12 00:00:00','Salerno',1,NULL),(25,'40.77501710000001-14.789330200000002','il barbagianni mi ha attaccato col suo becco e mi ha fratturato una gamba...chiamate la forestale!',0,'2015-07-15 00:00:00','Via Giovanni Paolo II, 84084 Fisciano SA, Italia',2,NULL),(26,'40.7750779-14.789332600000002','Carmine mi ha tamponato',0,'2015-07-16 00:00:00','Via Giovanni Paolo II, 84084 Fisciano SA, Italia',7,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,003,'MarioRossi@email.it','1980-12-13','Mario','Rossi','MRARSS80A13H703M','Valerio Laspro','Salerno',84100,'Italia',0,'111',NULL),(2,001,'test@test.it','1959-01-17','Joseph','Roy','GRDLHR59B17H703Z','Piazzetta Scalette Rubiani','Salerno',84036,'Italia',0,'1','test@test.it 1 2'),(3,000,'FedericoBasso@email.it','1995-05-02','Federico','Basso','BSSFRC95E02F839D','Toledo','Napoli',80121,'Italia',0,'11','federicobasso@email.it 11 3'),(4,000,'MirandaTrentino@email.it','1988-07-08','Miranda','Trentino','TRNMND88L48B172X','Carlo Alberto','Brienno',22010,'Italia',0,'1111',NULL),(5,000,'AntonioRusso@email.it','1976-10-07','Antonio','Russo','RSSNTN76S07H703F','San Giovanni Bosco','Salerno',84100,'Italia',0,'11111',NULL),(6,000,'FrancescaEsposito@email.it','1966-04-12','Francesca','Esposito','SPSFNC66D54F839A','Vespucci','Napoli',84121,'Italia',0,'111111',NULL),(7,000,'GiuseppeRonca@email.it','1992-04-15','Giuseppe','Ronca','RNCGPP92D15C361F','Giuseppe Pellegrino','Cava De\'Tirreni',84013,'Italia',0,'1111111',NULL),(8,000,'DiegoAvella@email.it','1993-07-13','Diego','Avella','DGIVLL93L13C361P','Gaetano Filangieri','Cava De\'Tirreni',84013,'Italia',0,'1111111',NULL),(9,000,'AngeloPassaro@email.it','1993-05-21','Angelo','Passaro','PSSNGL93E21H703I','Salvatore Calenda','Salerno',84100,'Italia',0,'1111111',NULL),(10,000,'AndreaMagnani@email.it','1983-11-21','Andrea','Magnani','MGNNDR83T21H703U','Carmine','Salerno',84100,'Italia',0,'1111111',NULL),(11,000,'GiuliaCirillo@email.it','1990-05-05','Giulia','Cirillo','CRLGLI90E45H703S','Irno','Salerno',84100,'Italia',0,'11111111',NULL),(12,000,'LucaTosi@email.it','1975-05-21','Luca','Tosi','TSOLCU75E21H703N','Luigi Guercio','Salerno',84100,'Italia',0,'11111111',NULL),(13,000,'ChiaraCarboni@email.it','1989-08-17','Chiara','Carboni','CRBCHR89M57H703B','Armando Diaz','Salerno',84100,'Italia',0,'11111111',NULL),(14,000,'MatteoNardi@email.it','1980-10-22','Matteo','Nardi','NRDMTT80R22H703Y','Leonino Vinciprova','Salerno',84100,'Italia',0,'11111111',NULL),(15,000,'ClorindaDeRose@email.it','1969-01-11','Clorinda','DeRose','DRSCRN69B51G113Y','Croce Rossa','Oristano',9080,'Italia',0,'11111111',NULL),(23,000,'paolo@libero.it','1995-05-12','paolo','zirpoli','','','',0,'Italia',0,'piatto','paolo@libero.it piatto 23');
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
INSERT INTO `user_report_rel` VALUES (23,26);
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

-- Dump completed on 2015-07-16 13:31:41
