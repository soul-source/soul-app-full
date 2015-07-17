/*
SQLyog Community v12.09 (64 bit)
MySQL - 5.5.43-0ubuntu0.14.04.1 : Database - soul_db
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

/*Table structure for table `chat` */

DROP TABLE IF EXISTS `chat`;

CREATE TABLE `chat` (
  `id_chat` int(11) NOT NULL AUTO_INCREMENT,
  `members_amount` tinyint(8) NOT NULL DEFAULT '2',
  PRIMARY KEY (`id_chat`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `chat` */

insert  into `chat`(`id_chat`,`members_amount`) values (1,3);

/*Table structure for table `chat_members` */

DROP TABLE IF EXISTS `chat_members`;

CREATE TABLE `chat_members` (
  `user_id_user` int(11) NOT NULL DEFAULT '0',
  `chat_id_chat` int(11) NOT NULL,
  PRIMARY KEY (`user_id_user`,`chat_id_chat`),
  KEY `fk_chat_idx` (`chat_id_chat`),
  CONSTRAINT `fk_chat` FOREIGN KEY (`chat_id_chat`) REFERENCES `chat` (`id_chat`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_user` FOREIGN KEY (`user_id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `chat_members` */

insert  into `chat_members`(`user_id_user`,`chat_id_chat`) values (1,1),(2,1);

/*Table structure for table `chat_message` */

DROP TABLE IF EXISTS `chat_message`;

CREATE TABLE `chat_message` (
  `id_user` int(11) NOT NULL,
  `id_chat` int(11) NOT NULL,
  `sending_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_name` varchar(45) NOT NULL,
  `text` varchar(255) NOT NULL,
  KEY `id_chat_idx` (`id_chat`),
  CONSTRAINT `id_chat` FOREIGN KEY (`id_chat`) REFERENCES `chat` (`id_chat`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `chat_message` */

insert  into `chat_message`(`id_user`,`id_chat`,`sending_date`,`user_name`,`text`) values (1,1,'2014-07-05 12:00:00','Mario','segnalazione ricevuta.Tutto ok?'),(7,1,'2014-07-05 12:01:00','Alighiero','Si'),(1,1,'2014-07-05 12:00:00','Mario','Va bene. Gli aiuti sono per strada'),(2,1,'2014-09-08 21:06:49','Giuseppe','Ci sono anche io'),(2,1,'2014-09-09 11:45:52','Giuseppe Roncas','mi ricevete?'),(2,1,'2014-09-09 12:01:14','Giuseppe Roncas','Grazie a voi siamo sani e salvi :)'),(2,1,'2014-09-11 10:16:57','Giuseppe Roncas','messaggio di prova');

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

insert  into `comment`(`id_comment`,`publication_date`,`message`,`id_user`,`id_report`) values (1,'2015-07-16 00:00:00','fasciati la gamba!',23,25),(2,'2015-07-16 00:00:00','aiuto',23,25),(3,'2015-07-16 00:01:17','Resta seduto, respira e chiama immediatamente i soccorsi!',4,1);

/*Table structure for table `emergency_number` */

DROP TABLE IF EXISTS `emergency_number`;

CREATE TABLE `emergency_number` (
  `number` int(11) NOT NULL,
  `title` varchar(45) NOT NULL,
  PRIMARY KEY (`number`),
  UNIQUE KEY `title_UNIQUE` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `emergency_number` */

insert  into `emergency_number`(`number`,`title`) values (118,'Ambulanza'),(1522,'Antiviolenza donne'),(112,'Carabinieri'),(1518,'CCISS'),(114,'Emergenza infanzia'),(1530,'Guardia Costiera'),(117,'Guardia di finanzia'),(1515,'Guardia Forestale'),(113,'Polizia di country'),(803555,'Protezione Civile'),(803116,'Soccorso stradale'),(19696,'Telefono Azzurro'),(636225,'Unita di crisi'),(115,'Vigili del fuoco');

/*Table structure for table `emergency_number_rel` */

DROP TABLE IF EXISTS `emergency_number_rel`;

CREATE TABLE `emergency_number_rel` (
  `number` int(11) NOT NULL,
  `subtype` int(11) NOT NULL,
  UNIQUE KEY `number_UNIQUE` (`number`,`subtype`),
  KEY `fk_emergency_number_rel_2_idx` (`subtype`),
  CONSTRAINT `fk_emergency_number_rel_1` FOREIGN KEY (`number`) REFERENCES `emergency_number` (`number`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_emergency_number_rel_2` FOREIGN KEY (`subtype`) REFERENCES `emergency_subtype` (`id_subtype`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `emergency_number_rel` */

insert  into `emergency_number_rel`(`number`,`subtype`) values (112,1),(118,1),(118,2),(118,3),(118,4),(118,5),(118,6),(112,7),(118,7);

/*Table structure for table `emergency_subtype` */

DROP TABLE IF EXISTS `emergency_subtype`;

CREATE TABLE `emergency_subtype` (
  `id_subtype` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` text NOT NULL,
  `priority_level` bigint(20) NOT NULL DEFAULT '5',
  `id_type` int(11) NOT NULL,
  PRIMARY KEY (`id_subtype`,`id_type`),
  KEY `fk_emergency_subtype_1_idx` (`id_type`),
  CONSTRAINT `fk_emergency_subtype_1` FOREIGN KEY (`id_type`) REFERENCES `emergency_type` (`id_type`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `emergency_subtype` */

insert  into `emergency_subtype`(`id_subtype`,`name`,`description`,`priority_level`,`id_type`) values (1,'Infarto','Per infarto si intende la necrosi di un tessuto per ischemia, cioè per grave deficit di flusso sanguigno.I sintomi sono diversi a seconda dell\'organo interessato, tuttavia il sintomo principale è rappresentato da dolore acuto (ad insorgenza improvvisa), di varia intensità.\nConsigli:\nCercate di mantenere un peso sano in quanto sovrappeso e obesità sono fattori di rischio per le malattie cardiache; smettete di fumare (per fumo si intende quello attivo e passivo), minimizzate stress, rabbia, depressione, così come atteggiamenti comportamentali di impazienza, eccessiva competitività e ostilità.',5,3),(2,'Frattura','La frattura in medicina è l’interruzione parziale o totale della continuità di un osso. Se la frattura riguarda solo l\'osso è detta \"isolata\", mentre se coinvolge anche i legamenti è detta \"associata.Le fratture sono classificate secondo diversi criteri.\nConsigli:\nDavanti a una frattura, è bene sdraiare ed immobilizzare l\'infortunato evitando che si muova. Se non ci sono particolari problemi di urgenza (rischi di vita) evitare il trasporto finché l\'arto non sia stato completamente immobilizzato. E\' sempre meglio attendere il soccorso qualificato di personale dotato delle attrezzature di immobilizzazione. Fare attenzione che l\'infortunato non entri in uno stato di shock e confortarlo.',3,3),(3,'Febbre','La febbre o piressia è un segno clinico; si definisce come uno stato patologico temporaneo che comporta un\'alterazione del sistema di termoregolazione ipotalamica e una conseguente elevazione della temperatura corporea al di sopra del valore considerato normale (circa 36.8 gradi Celsius per gli esseri umani in condizioni basali). \nConsigli:\nAssumere molti liquidi, per minimizzare il rischio di disidratazione\nApplicare impacchi rinfrescanti per favorire la perdita di calore dal corpo (spugnature con acqua fredda)\nIn caso di terapia antibiotica, si consiglia di incrementare l\'apporto di potassio a scapito del sodio: gli antibiotici, infatti, favoriscono l\'escrezione di potassio e la ritenzione di sodio\nMangiare poco ma spesso: così facendo, viene favorita la funzione digestiva\nMasticare lentamente\nIn caso di digiuno prolungato, assumere formulazioni reidratanti ed alcalinizzanti (citrato di sodio)\nQuando la nausea impedisce di assumere liquidi, è consigliato succhiare del ghiaccio\nIn caso di brividi, immergersi in vasche con acqua calda: i brividi, caratteristici della febbre, possono essere alleviati con bagni caldi\nNon riscaldare eccessivamente gli ambienti\nSe la febbre compare in un neonato (<4 mesi) è necessario il consulto medico\nMonitorare gli sbalzi febbrili: la febbre intermittente potrebbe celare patologie molto gravi\nUtilizzare i farmaci con moderazione\nCoprire la bocca con la mano o, meglio ancora, con un fazzoletto quando si starnutisce o si tossisce\nRimanere a riposo',1,3),(4,'Polmonite','La polmonite è una malattia dei polmoni e del sistema respiratorio caratterizzata dall\'infiammazione degli alveoli polmonari, i quali si riempiono di liquido che ostacola la funzione respiratoria. Di solito è causata da un\'infezione dovuta a virus, batteri e altri microrganismi. Il quadro clinico è tipicamente caratterizzato da tosse, dolore toracico, febbre e difficoltà respiratorie.\nConsigli:\nLa prevenzione dalla polmonite comprende la vaccinazione, l\'adozione delle idonee misure comportamentali ed il trattamento appropriato di eventuali altri problemi di salute.\nSmettere di fumare e ridurre il più possibile l\'esposizione all\'aria inquinata sono strategie fortemente raccomandate.',4,3),(5,'Varicella','La varicella è una malattia esantematica altamente contagiosa ed epidemica causata da un\'infezione primaria con il Virus varicella-zoster (VZV o Herpesvirus umano 3), un virus a DNA appartenente alla famiglia Herpesviridae, sottofamiglia Alphaherpesvirinae, genere Varicellovirus. La condizione inizia solitamente con rash cutaneo vescicolare, principalmente esteso al corpo e alla testa piuttosto che alle estremità. Le vescicole guariscono poi senza lasciare cicatrici. All\'esame, l\'osservatore trova in genere lesioni in vari stadi di guarigione.La varicella è una malattia che si diffonde facilmente per via aerea attraverso colpi di tosse o starnuti di individui malati o attraverso il contatto diretto con le secrezioni del rash. Una persona con la varicella è infettiva uno o due giorni prima che appaia l\'eruzione.Essa rimane contagiosa fino a quando tutte le lesioni vengono ricoperte da una crosta (circa sei giorni).Le lesioni crostose non sono contagiose.Questa malattia, nota fin dall\'antichità, venne nettamente distinta dal vaiolo soltanto ai primi del XIX secolo.La varicella è stata osservata anche in altri primati, compresi gli scimpanzé e i gorilla.\nLa terapia delle forme non complicate è volta semplicemente ad alleviare i sintomi: con antipiretici come il paracetamolo (l\'acido acetilsalicilico è controindicato per il rischio d\'insorgenza della sindrome di Reye) ed antistaminici per via orale per mitigare il prurito e quindi il riflesso di grattamento; per attenuare la sensazione di prurito è oggi sconsigliato il talco mentolato, in quanto ritarda il consolidamento delle lesioni cutanee. Sono più adatte le creme anti-prurito, il semplice borotalco e naturalmente i preparati lenitivi contro le lesioni da herpes.\n\nNelle forme complicate da encefalite o polmonite il trattamento con aciclovir, valaciclovir o famciclovir, riduce i giorni di febbre ed il numero di lesioni (purché iniziato entro 24-48 ore dalla comparsa dei primi sintomi), ma è indicato prevalentemente nei soggetti a rischio; può provocare una possibile interferenza negativa con la risposta immune, a causa dell\'azione inibente sulla replicazione virale, e quindi provocare al soggetto periodiche lievi ricadute. Se insorgono problemi alle vie respiratorie, si è verificata una sovrainfezione batterica e si deve intervenire con antibiotici: in questo caso è da evitare la sovrapposizione con gli antivirali. Può essere utile far indossare ai bambini guanti di cotone.\n\nLa profilassi prevede un periodo di isolamento di durata variabile (di solito 2 settimane per i soggetti colpiti da forme più aggressive, 1 settimana per quelli con forme lievi). La progressiva introduzione del vaccino dovrebbe consentire in futuro una prevenzione più attiva con somministrazione di dosi ai contatti dei malati. Una volta esaurite la febbre e la tosse (bisogna aspettare almeno 60-72 ore consecutive senza febbre), un malato può uscire, ma deve fare molta attenzione ad evitare la rottura accidentale delle vesciche per evitare di contagiare altre persone. Un buon sistema per proteggere eventuali vescicole \"a vista\" può essere la loro copertura con cerotti oppure garze, applicati con la dovuta cautela. Il ricambio d\'aria delle stanze va effettuato solo dopo disinfezione, preferibilmente con un vaporizzatore, onde evitare di disperdere nell\'atmosfera un ingente quantitativo di virus.[23]\n\nNei soggetti ad alto rischio è possibile anche l\'immunoprofilassi passiva con somministrazione di immunoglobuline normali o specifiche.',3,3),(6,'Scarlattina','La scarlattina è una malattia infettiva acuta contagiosa, caratteristica dell\'età pediatrica, che si manifesta con febbre e angina faringea ed è caratterizzata da insorgenza di esantema puntiforme (la capocchia di spillo).Essa si trasmette per via aerea. Non esiste un vaccino, ma può essere trattata efficacemente tramite la somministrazione di antibiotici. La maggior parte delle manifestazioni cliniche sono dovute alle tossine eritrogeniche prodotte dal batterio Streptococcus pyogenes (streptococco di gruppo A) quando viene infettato da un batteriofago. Infatti, a differenza di altre malattie contagiose simili e tipiche dell\'infanzia, come la rosolia e la varicella, la scaralattina è l\'unica provocata da batteri anziché da virus.Prima dell\'avvento degli antibiotici, la scarlattina rappresentava una delle principali cause di morte. Inoltre, talvolta, era responsabile per l\'insorgenza di complicanze tardive come la glomerulonefrite e l\'endocardite, quest\'ultima poi spesso causa di problemi alle valvole cardiache spesso di esito infausto. I ceppi di streptococco di gruppo A che producono la tossina erotrogenica non sono intrinsecamente più pericolosi di altri ceppi che non lo fanno, essi infatti portano ad una diagnosi più facile per via della caratteristica eruzione cutanea.\nLa terapia va attuata con antibiotici attivi sull\'infezione streptococcica (penicillina benzatina, amoxicillina, cefalosporina). Inoltre si somministrano antifebbrili e si cerca di idratare adeguatamente il paziente.',2,3),(7,'Incidente Stradale','Consigli:<br>è bene <b>non rimuovere</b> il casco ai motociclisti, a meno che non si sia padroni della tecnica necessaria; infatti, i danni possono essere abbastanza gravi se questo non viene fatto correttamente (ad esempio si rischia di spostare qualche vertebra o di causare tetraplegia)<br><b>non</b> dare da bere all\'infortunato (si rischia il soffocamento in caso di trauma alle vie aeree).<br>Se la vittima ha subito lesioni dovute a perforazioni e l\'oggetto è rimasto all\'interno della ferita, <b>non</b> va rimosso: l\'oggetto stesso potrebbe fare da ostacolo per il flusso sanguigno e toglierlo potrebbe portare alla fuoriuscita di sangue e alla conseguente morte per dissanguamento.<br>',4,3),(8,'Altro','',3,3);

/*Table structure for table `emergency_type` */

DROP TABLE IF EXISTS `emergency_type`;

CREATE TABLE `emergency_type` (
  `id_type` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(45) NOT NULL,
  `authorities_involved` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `emergency_type` */

insert  into `emergency_type`(`id_type`,`type_name`,`authorities_involved`) values (3,'Emergenza Sanitaria','Emergenza Sanitaria ,Carabinieri, Vigili del fuoco e Polizia');

/*Table structure for table `id_priority` */

DROP TABLE IF EXISTS `id_priority`;

CREATE TABLE `id_priority` (
  `id_priority` varchar(45) NOT NULL,
  PRIMARY KEY (`id_priority`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `id_priority` */

insert  into `id_priority`(`id_priority`) values ('1'),('2'),('3'),('4'),('5');

/*Table structure for table `news` */

DROP TABLE IF EXISTS `news`;

CREATE TABLE `news` (
  `id_news` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `article` text NOT NULL,
  `publication_date` datetime NOT NULL,
  `id_user` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_news`,`id_user`),
  KEY `fk_news_1_idx` (`id_user`),
  CONSTRAINT `fk_news_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `news` */

insert  into `news`(`id_news`,`title`,`article`,`publication_date`,`id_user`) values (1,'Ebola, nuova cura sperimentale per il medico ','<img src =\"images/ebola.jpg\" height=\"200\" >ROMA - Il medico di Emergency affetto da Ebola sta per iniziare un ulteriore trattamento con un farmaco sperimentale appena arrivato dall\'estero. Lo hanno affermato i sanitari dello Spallanzani, dove il paziente è ricoverato. \"Nella serata di ieri c\'è stato un peggioramento delle condizioni generali. Tuttavia, nella nottata e stamattina, il quadro clinico seppur grave, è tornato sovrapponibile a quello della giornata di ieri\", hanno spiegato i medici nell\'ultimo bollettino, aggiungendo che la prognosi rimane riservata.\n\nAnche questo nuovo farmaco sperimentale, hanno spiegato i medici, è stato ottenuto grazie ad una catena di supporto, in Italia costituita dal ministero della Salute, inclusa la rete degli uffici di sanità di frontiera Us (Usmaf) e di solidarietà istituzionale, con l\'aiuto del coordinamento internazionale per la gestione di Ebola dell\'Organizzazione mondiale della sanità.','2015-08-20 00:00:00',4),(2,'Caldo,settimana di fuoco: bollino rosso','<img src =\"images/caldo.jpg\" height=\"200\" >Roma - Altro che tregua. Quella appena cominciata i preannuncia un’altra settimana di fuoco: torna l’allarme ondate di calore.\n\nLo conferma il ministero della Salute nel suo bollettino quotidiano. Domani, martedì, saranno cinque le città da «bollino rosso», dove il caldo diventa un rischio per la salute anche di soggetti sani: si tratta di Bolzano, Campobasso, Latina (tra le città più calde con 39 gradi percepiti), Palermo e Perugia.\n\n|Le previsioni per i prossimi giorni|\n\nMercoledì 15 il numero salirà a otto: si aggiungeranno infatti anche Firenze (dove si toccheranno i 39 gradi), Roma (punte di 37 gradi) e Torino (38 gradi).\n\nA queste vanno sommate altre 9 città con «bollino arancione», cioè con condizioni di rischio per soggetti fragili, anziani, malati cronici e bambini. Si tratta di Bologna, Brescia, Civitavecchia, Frosinone, Genova, Milano, Rieti, Verona e Viterbo. ','2015-06-06 00:00:00',4),(3,'Influenza aprile 2015','<img src =\"images/influenza.jpg\" height=\"200\">E\' finalmente terminata la stagione influenzale, che per quest\'anno ha messo a letto circa sei milioni di italiani. Durante le prossime settimane e fino alla fine di aprile, il bollettino Influnet prevede ancora alcuni casi di contagio ma con un netto calo di malati. Nella prima settimana di aprile, si sono registrati, ancora, circa 116mila casi influenzali con una maggiore incidenza per quanto riguarda i bambini molto piccoli o fino a 14 anni. Per gli adulti, invece, i casi maggiori riportati hanno interessato gli anziani sopra i 65 anni di età. Secondo i dati riportati dall\'Iss, la stagione appena trascorsa è stata di media entità, anche se non sono mancati casi più complicati e gravi. Passata l\'influenza classica, ora il pericolo si riferisce ai virus parainfluenzali, tra cui spicca l\'influenza intestinale, vero malanno primaverile e in genere del cambio stagionale.\n\nInfluenza 2015 al termine: aprile mese del virus intestinale\n\nSecondo i dati resi noti dai virologi ospedalieri, scampato il pericolo per l\'influenza 2015, ora bisognerà fare i conti con il virus intestinale o con i virus affini che, somigliano molto all\'influenza comune con la differenza che durano molto meno. Solitamente, il periodo primaverile è quello in cui ci sono molto più rischi di contrarre questi virus a causa di sbalzi termici che portano alla proliferazione di batteri. I sintomi dei virus parainfluenzali che colpiscono le vie aree, rimangono sostanzialmente simili a quelli dell\'influenza comune con mal di gola, placche, tosse, tracheite o raffreddore, che può essere confuso con le allergie stagionali.\n\nA volte e presente la febbre, anche elevata ma la durata è minore, non superando, di fatto, i 3 o 4 giorni. Differente è il virus intestinale che colpisce stomaco e apparato gastrointestinale. Il contagio, in questo caso, è molto più veloce e bastano anche 24 o 48 ore per la trasmissione da un soggetto all\'altro. Il virus intestinale può essere molto fastidioso: dura circa 2 o 3 giorni e porta sintomi come diarrea, vomito, malessere, febbre bassa o alta sopra i 38 gradi e disidratazione. Valgono i classici consigli per guarire velocemente ed evitare cure fai da te con medicinali.','2015-04-06 00:00:00',4),(4,'Il vaccino esavalente','<img src =\"images/vaccino.jpg\" height=\"200\">La poliomielite è una malattia, causata da 3 tipi di virus intestinali, che si trasmette da uomo a uomo per via alimentare attraverso feci e saliva. In circa il 95% delle persone infettate dai virus della polio non si manifesta alcun disturbo. Sintomi minori possono comprendere mal di gola, febbre moderata, nausea e vomito. In alcuni casi (1-2%) si può manifestare rigidità di collo, della schiena o delle gambe, ma senza paralisi. Invece, in meno dell\'1% dei casi (all\'incirca uno ogni 1000 infezioni) si verifica la paralisi. In talune circostanze i virus poliomielitici possono causare anche paralisi respiratorie rendendo così impossibile la respirazione autonoma. Alcune persone possono recuperare la funzionalità muscolare in modo completo, in altri casi sono possibili ricadute dopo 30-40 anni con dolori muscolari e progressivo indebolimento.\n\nIl tetano è una grave malattia infettiva causata dall’azione di una tossina (tossina tetanica) prodotta da batteri (clostridi del tetano) che vivono nel suolo o nell\'intestino degli animali. La malattia può essere mortale nel 20- 30% circa dei casi.\nA differenza delle altre malattie infettive prevenibili con la vaccinazione, il tetano non si trasmette da persona a persona. L\'infezione deriva spesso da una ferita, anche banale, occorsa ad una persona non adeguatamente vaccinata. Perciò il rischio tetano può essere considerato quotidiano in una persona non vaccinata.\nNei paesi in via di sviluppo il tetano può colpire le donne non vaccinate infettatesi durante il parto oppure i loro neonati per infezione del cordone ombelicale (tetano neonatale, oggi del tutto scomparso in Occidente).\nRaramente, e sempre in persone non vaccinate, il tetano si può contrarre anche attraverso l\'uso di siringhe infette, morsi di animali, ustioni, abrasioni.\nL\'infezione tetanica produce violente contrazioni muscolari, chiamate spasmi. Altri sintomi possono essere febbre, sudorazione, ipertensione arteriosa e tachicardia.\nGli spasmi possono interessare le corde vocali e i muscoli respiratori, tanto da mettere in seria difficoltà la respirazione. Le contrazioni possono essere così violente da produrre anche fratture ossee.\n\nLa difterite è una grave malattia infettiva causata dall’azione di una tossina (tossina difterica) prodotta da batteri che si trasmettono per via aerea. Solitamente la difterite inizia con mal di gola, febbre moderata, tumefazione del collo.\nMolto spesso i batteri della difterite si moltiplicano nella gola (faringe) dove si viene a formare una membrana di colore grigiastro che può soffocare la persona colpita dalla malattia. A volte queste membrane si possono formare anche nel naso, sulla pelle o in altre parti del corpo.\nLa tossina difterica, diffondendosi tramite la circolazione sanguigna, può causare paralisi muscolari, lesioni a carico del muscolo cardiaco con insufficienza cardiaca, lesioni renali, fino a provocare la morte della persona colpita. \nLa letalità è di circa il 5-10% ma in molti casi, nei sopravvissuti, permangono danni permanenti a carico di cuore, reni, sistema nervoso.\n\nLa pertosse (o tosse canina) è una malattia causata da un batterio, la Bordetella pertussis.\nE\' una delle malattie infettive più contagiose che si conoscano, tanto che un bambino con pertosse può contagiare fino al 90% di bambini non immuni con cui viene a contatto. Si trasmette per via aerea da persona a persona con la tosse o gli starnuti.\nLa malattia non complicata dura circa da 6 a 10 settimane e si compone di tre stadi: catarrale, parossistico e della convalescenza. La malattia esordisce solitamente con starnuti, raucedine e una fastidiosa tosse notturna. Successivamente, dopo 10-14 giorni, si manifesta una tosse convulsiva e ostinata che rende difficoltosa la respirazione e persino l\'alimentazione. Questa fase può durare fino a 2-3 settimane. Gli accessi di tosse sono costituito da 5 – 15 colpi di tosse violenti e ravvicinati che si verificano durante una singola esprirazione. Solitamente si concludono con una rapida e profonda ispirazione: il tipico \"urlo inspiratorio\" e l’espulsione di un blocchetto di catarro molto denso e vischioso. Gli attacchi sono seguiti, a volte, dal vomito. Nei lattanti si possono avere crisi di soffocamento. La convalescenza inizia in genere dopo 4 settimane. Gli accessi di tosse diventano meno frequenti e gravi e le condizioni generali del bambino migliorano.\nLa malattia è tanto più grave quanto più precocemente colpisce il bambino.  In media, circa il 20 % dei casi di pertosse devono essere ospedalizzati.\nLe complicanze polmonari si verificano in un caso ogni 20 ma in più di un caso ogni 10 neonati di età inferiore a 6 mesi. Altra grave complicanza l’encefalopatia colpisce da 1 a 2 bambini ogni 1000. La mortalità della pertosse è alta: di 2 decessi ogni 1000 casi, pressoché completamente a carico dei bambini nel primo anno di vita. La causa principale di morte è la polmonite.\n\nIl virus dell\'epatite B (HBV) è trasmesso da una persona all\'altra col sangue e con i fluidi corporei, in genere attraverso i contatti sessuali o l\'uso di siringhe non sterili. Tuttavia circa il 30% delle persone che si sono infettate, non ha fattori di rischio noti. Il virus può essere trasmesso ai neonati dalle madri infette. L\'infezione colpisce in particolare il fegato.\nI sintomi della malattia acuta da HBV variano e possono comprendere perdita di appetito, affaticamento, nausea, ittero (colore giallo degli occhi e della pelle), dolore alle articolazioni e rash (rossore) cutaneo. Più della metà dei bambini che acquisiscono l’infezione non mostrano segni o sintomi, anche se possono diventare portatori cronici.  Circa il 90% dei bambini che sono infettati alla nascita dalla loro madre e il 30%-50% di quelli che si infettano all\'età di 5 anni, diventano portatori cronici dell\'HBV, mentre le persone che si infettano da adulti hanno soltanto un 6-10% di rischio di infezione cronica. I portatori cronici possono sviluppare una epatite cronica o il tumore del fegato. L’epatite B è soprattutto grave per queste complicanze croniche che si sviluppano a distanza di 30 - 40 anni nei portatori cronici.  Più giovane è il paziente quando acquisisce la malattia, più è probabile che sviluppi una malattia cronica del fegato o il tumore.\n\nL’Hib (Haemofilus influenzae di tipo b)  è un batterio che può infettare le membrane che rivestono il sistema nervoso centrale causando una meningite batterica. L’Hib può causare altri seri problemi di salute, come polmonite, gonfiore alla gola con difficoltà a respirare (epiglottide), infezioni del sangue (sepsi).\nL\'Hib si trasmette attraverso le goccioline di saliva emesse con la tosse o lo starnuto.\nLa meningite colpisce soprattutto i bambini, più spesso dai 3 mesi fino ai 3 anni di età, con un picco verso i sei mesi, mentre non è comune dopo i cinque anni.\nNel peggiore dei casi la meningite è fatale. Circa il 5% dei bambini (500 su ogni 100.000) affetti da meningite muore per questa malattia anche se sottoposto a terapia antibiotica. Circa il 15-30% dei bambini che sopravvivono evidenzia danni neurologici permanenti come cecità, sordità, ritardo mentale e difficoltà di apprendimento.\nCon il vaccino esavalente:\n\n    un unico vaccino protegge da tutte queste malattie\n    si riduce il numero di iniezioni da effettuare ad ogni appuntamento vaccinale (ed anche il numero di appuntamenti).\n\nIl vaccino contiene:\n\n        tossoide difterico\n        antigene di superficie ricombinante del virus dell’epatite B\n        polisaccaride del Haemophilus influenzae tipo b\n        antigeni della pertosse: tossoide pertossico, emoagglutinina filamentosa, pertactina\n        virus inattivati della poliomielite tipo 1, 2, 3\n        tossoide tetanico\n\nIl vaccino contiene inoltre:lattosio anidro, sodio cloruro, medium 199 (contenente principalmente aminoacidi, sali minerali, vitamine). E’ adsorbito su composti di alluminio (alluminio ossido idrato, fosfato di alluminio) che migliorano la capacità di stimolare la risposta immune. Può inoltre contenere tracce di neomicina o polimixina B (sostanze che in  fase di preparazione servono a garantire la sterilità del preparato).\n\nÞ  il vaccino esavalente unisce insieme le 6 componenti protettive, senza moltiplicare quindi la presenza delle altre sostanze necessariamente presenti in ogni preparato vaccinale, che verrebbero a sommarsi con la somministrazione separata dei vaccini singoli.\n\nChi dovrebbe essere vaccinato?\nTutti i bambini dovrebbero essere vaccinati contro le sei malattie nel primo anno di vita. La vaccinazione dovrebbe iniziare a due mesi di età.\n\nChi non dovrebbe essere vaccinato?\n\n    i bambini con meno di 6 settimane di vita\n    le persone che hanno avuto una reazione allergica a precedenti dosi di vaccino o a componenti del vaccino\n\n    i bambini con una malattia acuta grave o moderata, con o senza febbre in atto dovrebbero consultare il medico prima di effettuare la vaccinazione\n\nDosi e calendario\nSono necessarie 3 dosi per conferire la migliore protezione possibile. Il vaccino esavalente si esegue al 3°, 5° e 12-13° mese di vita e può essere effettuato contemporaneamente ad altri vaccini. Inoltre, mentre per le componenti Epatite B e Hib non saranno necessarie ulteriori dosi,  per mantenere elevata la protezione nel tempo, è previsto un richiamo a 6 anni per difterite, tetano, pertosse e polio e 14 anni per difterite, tetano, pertosse.\nViene somministrato con un’iniezione intramuscolare nella parte alta del muscolo anteriore della coscia.\n\nEfficacia del vaccino\nIl Vaccino esavalente è molto immunogeno (capace di indurre un livello di anticorpi protettivo) ed elevata è anche l’efficacia protettiva  che è di circa l’85% di protezione nei confronti della pertosse, è superiore al 95 % per la difterite e al 99 % per l’epatite B e le forme invasive dell’HIB e  sostanzialmente il 100% per polio e tetano. La protezione è molto lunga nel tempo e il rispetto del calendario assicura che questa protezione duri sostanzialmente tutta la vita.\nPer maggiori informazioni vai alla scheda di approfondimento.\n\nEffetti collaterali\n\n    qualche ora dopo la vaccinazione il bambino potrà manifestare gonfiore, arrossamento e dolorabilità in corrispondenza della sede di iniezione. La frequenza di queste manifestazioni (circa il 20% dei casi) tende ad aumentare con il numero di dosi somministrate.  Molto raramente il gonfiore si estende a gran parte dell’arto. Il dolore è generalmente modesto. L’applicazione di un panno umido e freddo può ridurre i sintomi. Talvolta il bambino si rifiuta temporaneamente di far forza sull’arto sede di iniezione. Le reazioni locali durano in genere un paio di giorni.\n\n    il bambino può avere febbre nelle 48 ore successive alla vaccinazione (circa 1/3 dei casi); è consigliato farlo riposare , idratarlo bene ed utilizzare un farmaco contro la febbre se la temperatura sale sopra a 38.5°C o quella rettale a 39°C.\n\n    dopo la vaccinazione, in meno dell’1 % dei casi ?, nel sito di iniezione può comparire un piccolo nodulo, generalmente non dolente,\n\nche persiste per alcune settimane.\n\n    il vaccino può causare diminuzione dell’appetito, irritabilità, sonnolenza o viceversa agitazione, pianto insolito e convulsioni febbrili.\n\n    le reazioni allergiche gravi sono estremamente rare. Se compare una reazione allergica, comparirà nei primi minuti successivi alla vaccinazione e sarà trattata dal personale vaccinatore; è per tale motivo che si raccomanda di sostare presso la sede vaccinale per almeno 15 minuti dopo la somministrazione del vaccino.\n\n    sono stati riportati molto raramente (0,5-1 caso su 100.000 dosi) dolori intensi e debolezza dell’arto correlate a vaccini contenenti la componente antitetano; questi disturbi possono permanere alcune settimane.\n\n \n\nPer maggiori informazioni vai alla scheda di approfondimento.\nparte standard\nCirca la metà dei bambini che ricevono vaccini esavalenti, così come il vaccino triplo DTaP o le altre combinazioni di vaccino contro la pertosse, non ha nessuna reazione.\nLa maggior parte degli altri bambini presenta solo reazioni lievi. L’evento più frequente è la febbre che si può avere in circa un terzo dei bambini. Reazioni locali si verificano nel 20 % dei casi. Queste comprendono dolore, rossore e gonfiore nel punto dove è stata eseguita l’iniezione; si verificano  in genere entro 48 ore dalla vaccinazione e durano fino a un paio di giorni. Le reazioni locali aumentano con il numero di dosi eseguite. Circa il 40%  dei bambini ha gonfiore o dolenzia al braccio con la quarta dose di DTaP eseguita a 5-6 anni. Alcuni bambini possono sviluppare un esteso gonfiore temporaneo dell’arto dove l\'iniezione è stata eseguita.\nIn caso di reazioni locali usare panni freddi o farmaci a base di paracetamolo, se necessario, per ridurre il dolore.\n\nIn caso di reazioni febbrili:\n\n    Dare da bere molti liquidi\n    Non vestire troppo il bambino se è caldo\n    Usare farmaci a base di paracetamolo (non aspirina) o panni freddi, se necessario, per ridurre la febbre\n\nNel caso che i sintomi si protraggano per più di due giorni può essere opportuno consultare il medico per verificare se questi rappresentino un comune effetto collaterale ad una vaccinazione o se invece si riferiscano ad un\'altra malattia che deve essere riconosciuta e trattata.\nIn rari casi (1-2 ogni 10.000) si possono avere reazioni più importanti, come convulsioni correlate alla febbre alta. Reazioni allergiche di tipo anafilattico con gonfiore della bocca, difficoltà del respiro, pressione bassa e shock, sono del tutto eccezionali (meno di 1 caso ogni milione di vaccinati).\nSe si verificasse una reazione importante o insolita, rivolgetevi al vostro medico. In questo caso va fatta la segnalazione d\'avvento avverso ed è importante avvisare il servizio di vaccinazione.\n\nTanti vaccini insieme?\nLa somministrazione dei 6 vaccini insieme apparentemente può sembrare un “sovraccarico” di attività per il sistema immune, tuttavia questo sospetto non basa su fatti oggettivi: il nostro organismo (anche quello di un bambino molto piccolo) è infatti in grado di rispondere normalmente ad un numero di stimoli (antigeni) enormemente maggiore. E questo avviene normalmente, con il contatto quotidiano con le diverse sostanze cui il nostro organismo è esposto o, ancor di più, quando viene aggredito da un’agente infettivo (ad esempio l’agente infettivo naturale della pertosse contiene più di 2000 antigeni che stimolano contemporaneamente il sistema immunitario, mentre il vaccino antipertosse ne contiene solo 3).','2015-01-02 00:00:00',4);

/*Table structure for table `notification` */

DROP TABLE IF EXISTS `notification`;

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

/*Data for the table `notification` */

insert  into `notification`(`id_notification`,`read`,`message`,`receiving_date`,`id_user`) values (1,0,'Ricevuto un commento','2014-07-10 23:51:00',1),(3,1,'Ricevuto un commento','2014-07-10 23:55:00',1),(4,0,'Ricevuto un commento','2014-01-05 00:00:00',13),(5,0,'Ricevuto un messaggio','2014-07-05 12:01:00',10),(6,1,'Ricevuto un messaggio','2014-02-26 12:03:00',1),(8,0,'Pubblicata una notizia','2014-01-05 07:00:00',2),(9,0,'Pubblicata una notizia','2014-04-16 07:00:00',2),(10,0,'Pubblicata una notizia','2014-03-12 07:00:00',1),(11,0,'Pubblicata una notizia','2014-06-22 07:00:00',5),(12,0,'Pubblicata una notizia','2014-06-02 07:00:00',1),(13,1,'Pubblicata una notizia','2014-02-15 07:00:00',2);

/*Table structure for table `priority_rel` */

DROP TABLE IF EXISTS `priority_rel`;

CREATE TABLE `priority_rel` (
  `id_subtype` int(11) NOT NULL,
  `id_priority` varchar(45) NOT NULL,
  UNIQUE KEY `id_subtype_UNIQUE` (`id_subtype`,`id_priority`),
  KEY `fk_priority_rel_1_idx` (`id_priority`),
  CONSTRAINT `fk_priority_rel_1` FOREIGN KEY (`id_priority`) REFERENCES `id_priority` (`id_priority`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_priority_rel_2` FOREIGN KEY (`id_subtype`) REFERENCES `emergency_subtype` (`id_subtype`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `priority_rel` */

insert  into `priority_rel`(`id_subtype`,`id_priority`) values (2,'1'),(5,'1'),(1,'5'),(3,'5');

/*Table structure for table `report` */

DROP TABLE IF EXISTS `report`;

CREATE TABLE `report` (
  `id_report` int(11) NOT NULL AUTO_INCREMENT,
  `coordinates` varchar(255) DEFAULT NULL,
  `description` text NOT NULL,
  `comments_number` int(10) unsigned NOT NULL DEFAULT '0',
  `publication_date` datetime NOT NULL,
  `place` varchar(255) DEFAULT NULL,
  `id_subtype` int(11) DEFAULT '1',
  `image` longtext,
  PRIMARY KEY (`id_report`),
  KEY `fk_report_1_idx` (`id_subtype`),
  CONSTRAINT `fk_report_1` FOREIGN KEY (`id_subtype`) REFERENCES `emergency_subtype` (`id_subtype`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

/*Data for the table `report` */

insert  into `report`(`id_report`,`coordinates`,`description`,`comments_number`,`publication_date`,`place`,`id_subtype`,`image`) values (1,'40.77501710000001-14.789330200000002','Credo di avere dei sintomi di infarto :(',0,'2015-06-12 00:00:00','Salerno',1,NULL),(25,'40.77501710000001-14.789330200000002','il barbagianni mi ha attaccato col suo becco e mi ha fratturato una gamba...chiamate la forestale!',0,'2015-07-15 00:00:00','Via Giovanni Paolo II, 84084 Fisciano SA, Italia',2,NULL),(26,'40.7750779-14.789332600000002','Carmine mi ha tamponato',0,'2015-07-16 00:00:00','Via Giovanni Paolo II, 84084 Fisciano SA, Italia',7,NULL),(45,'40.7050438-14.7109564','Ho la febbre alta, 41° cosa posso fare per farla abbassare?',0,'2015-07-17 00:00:00','Via Eduardo de Filippis, 48, 84013 Cava de\' Tirreni SA, Italia',3,NULL),(47,'40.7740393-14.789255200000001','prova',0,'2015-07-17 00:00:00','Università degli Studi di Salerno, Asse Centrale, 84084 Fisciano SA, Italia',2,'data:image/jpeg;base64,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'),(48,'40.7096696-14.7112745','',0,'2015-07-17 00:00:00','Via Aniello Salsano, 77, 84013 Cava de\' Tirreni SA, Italia',1,'');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id_task` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id_task`),
  UNIQUE KEY `type_UNIQUE` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id_task`,`type`) values (2,'Carabiniere'),(6,'Dottore'),(7,'Guardia Costiera'),(5,'Guardia Forestale'),(3,'Poliziotto'),(1,'Vigile del fuoco');

/*Table structure for table `role_rel` */

DROP TABLE IF EXISTS `role_rel`;

CREATE TABLE `role_rel` (
  `task_id` int(11) NOT NULL,
  `user_id_user` int(11) NOT NULL,
  UNIQUE KEY `task_id_UNIQUE` (`task_id`,`user_id_user`),
  KEY `fk_id_user_idx` (`user_id_user`),
  CONSTRAINT `fk_id_user` FOREIGN KEY (`user_id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_rel_task1` FOREIGN KEY (`task_id`) REFERENCES `role` (`id_task`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_rel` */

insert  into `role_rel`(`task_id`,`user_id_user`) values (2,1),(1,2),(3,3),(2,4),(6,4),(7,5),(1,6),(3,10),(5,11),(6,12),(7,13),(2,14),(6,15);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id_user`,`user_level`,`email`,`birthdate`,`name`,`last_name`,`tax_code`,`street`,`city`,`cap`,`country`,`unread_notifications_number`,`password`,`session_token`) values (1,003,'MarioRossi@email.it','1980-12-13','Mario','Rossi','MRARSS80A13H703M','Valerio Laspro','Salerno',84100,'Italia',0,'111',NULL),(2,001,'test@test.it','1959-01-17','Joseph','Roy','GRDLHR59B17H703Z','Piazzetta Scalette Rubiani','Salerno',84036,'Italia',0,'1','test@test.it 1 2'),(3,000,'FedericoBasso@email.it','1995-05-02','Federico','Basso','BSSFRC95E02F839D','Toledo','Napoli',80121,'Italia',0,'11','federicobasso@email.it 11 3'),(4,000,'MirandaTrentino@email.it','1988-07-08','Anna','Trentino','TRNMND88L48B172X','Carlo Alberto','Brienno',22010,'Italia',0,'1111',NULL),(5,000,'AntonioRusso@email.it','1976-10-07','Antonio','Russo','RSSNTN76S07H703F','San Giovanni Bosco','Salerno',84100,'Italia',0,'11111',NULL),(6,000,'FrancescaEsposito@email.it','1966-04-12','Francesca','Esposito','SPSFNC66D54F839A','Vespucci','Napoli',84121,'Italia',0,'111111',NULL),(8,000,'DiegoAvella@email.it','1993-07-13','Diego','Avella','DGIVLL93L13C361P','Gaetano Filangieri','Cava De\'Tirreni',84013,'Italia',0,'1111111',NULL),(9,000,'AngeloPassaro@email.it','1993-05-21','Angelo','Passaro','PSSNGL93E21H703I','Salvatore Calenda','Salerno',84100,'Italia',0,'1111111',NULL),(10,000,'AndreaMagnani@email.it','1983-11-21','Andrea','Magnani','MGNNDR83T21H703U','Carmine','Salerno',84100,'Italia',0,'1111111',NULL),(11,000,'GiuliaCirillo@email.it','1990-05-05','Giulia','Cirillo','CRLGLI90E45H703S','Irno','Salerno',84100,'Italia',0,'11111111',NULL),(12,000,'LucaTosi@email.it','1975-05-21','Luca','Tosi','TSOLCU75E21H703N','Luigi Guercio','Salerno',84100,'Italia',0,'11111111',NULL),(13,000,'ChiaraCarboni@email.it','1989-08-17','Chiara','Carboni','CRBCHR89M57H703B','Armando Diaz','Salerno',84100,'Italia',0,'11111111',NULL),(14,000,'MatteoNardi@email.it','1980-10-22','Matteo','Nardi','NRDMTT80R22H703Y','Leonino Vinciprova','Salerno',84100,'Italia',0,'11111111',NULL),(15,000,'ClorindaDeRose@email.it','1969-01-11','Clorinda','DeRose','DRSCRN69B51G113Y','Croce Rossa','Oristano',9080,'Italia',0,'11111111',NULL),(23,000,'paolo@libero.it','1995-05-12','paolo','zirpoli','','','',0,'Italia',0,'piatto','paolo@libero.it piatto 23'),(25,000,'GIUSEPPE.RONCA2@GMAIL.COM','1990-04-17','Giuseppe','Ronca','','','',0,'Italia',0,'-1139642029','giuseppe.ronca2@gmail.com -1139642029 25');

/*Table structure for table `user_report_rel` */

DROP TABLE IF EXISTS `user_report_rel`;

CREATE TABLE `user_report_rel` (
  `user_id_user` int(11) NOT NULL,
  `report_id_report` int(11) NOT NULL,
  UNIQUE KEY `user_id_user_UNIQUE` (`user_id_user`,`report_id_report`),
  KEY `fk_user_report_rel_report1_idx` (`report_id_report`),
  CONSTRAINT `fk_user_report_rel_report1` FOREIGN KEY (`report_id_report`) REFERENCES `report` (`id_report`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_report_rel_user1` FOREIGN KEY (`user_id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_report_rel` */

insert  into `user_report_rel`(`user_id_user`,`report_id_report`) values (25,1),(25,48);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
