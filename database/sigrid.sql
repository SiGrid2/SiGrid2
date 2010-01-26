- create database `hibernate`;
- create user sigrid2@'localhost' identified by 'sigrid2';
- grant all on hibernate.* to sigrid2@'localhost';
-
-- 
-- Datenbank: `hibernate`
-- 

-- --------------------------------------------------------

-- 
-- Tabellenstruktur für Tabelle `algorithm`
-- 
CREATE TABLE `algorithm` (
  `algorithm_id` int(11) NOT NULL auto_increment,
  `name` varchar(255) collate utf8_bin default NULL,
  PRIMARY KEY  (`algorithm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=4 ;
--
-- Daten für Tabelle `algorithm`
--
insert into `hibernate`.`algorithm` (`algorithm_id`,name) values (1,"bf");
insert into `hibernate`.`algorithm` (`algorithm_id`,name) values (2,"pareto");
insert into `hibernate`.`algorithm` (`algorithm_id`,name) values (3,"greedy");
insert into `hibernate`.`algorithm` (`algorithm_id`,name) values (4,"gsp");
-- --------------------------------------------------------

-- 
-- Tabellenstruktur für Tabelle `layout`
-- 

CREATE TABLE `layout` (
  `layout_id` int(11) NOT NULL auto_increment,
  `name` varchar(255) collate utf8_bin default NULL,
  `numJobs` int(11) NOT NULL,
  `numServer` int(11) NOT NULL,
  PRIMARY KEY  (`layout_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

-- 
-- Tabellenstruktur für Tabelle `layout_jobs`
-- 

CREATE TABLE `layout_jobs` (
  `Layout_layout_id` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `earning` int(11) NOT NULL,
  `dasd` int(11) NOT NULL,
  `startTime` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `relCat` int(11) NOT NULL,
  `speedCat` int(11) NOT NULL,
  `penalty` int(11) NOT NULL,
  KEY `FKDB4BAC2B3C50D976` (`Layout_layout_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

-- 
-- Tabellenstruktur für Tabelle `layout_server`
-- 

CREATE TABLE `layout_server` (
  `Layout_layout_id` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `cost` int(11) NOT NULL,
  `dasd` int(11) NOT NULL,
  `relCat` int(11) NOT NULL,
  `speedCat` int(11) NOT NULL,
  KEY `FK45E75DD83C50D976` (`Layout_layout_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

-- 
-- Tabellenstruktur für Tabelle `simfarm`
-- 

CREATE TABLE `simfarm` (
  `simfarm_id` int(11) NOT NULL auto_increment,
  `firstRegAtMs` bigint(20) NOT NULL,
  `initID` bigint(20) NOT NULL,
  `name` varchar(255) collate utf8_bin default NULL,
  PRIMARY KEY  (`simfarm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

-- 
-- Tabellenstruktur für Tabelle `simfarm_simjob`
-- 

CREATE TABLE `simfarm_simjob` (
  `SimFarm_simfarm_id` int(11) NOT NULL,
  `simJob_simjob_id` int(11) NOT NULL,
  UNIQUE KEY `simJob_simjob_id` (`simJob_simjob_id`),
  KEY `FK120C4678268B9132` (`simJob_simjob_id`),
  KEY `FK120C467827DC0BFB` (`SimFarm_simfarm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

-- 
-- Tabellenstruktur für Tabelle `simjob`
-- 

CREATE TABLE `simjob` (
  `simjob_id` int(11) NOT NULL auto_increment,
  `finished` bit(1) NOT NULL,
  `running` bit(1) NOT NULL,
  `queuedMs` bigint(20) NOT NULL,
  `startedMs` bigint(20) NOT NULL,
  `finishedMS` bigint(20) NOT NULL,
  `queuePosition` int(11) NOT NULL,
  `testrun_testrun_id` int(11) NOT NULL,
  `simStatistic_simstatistic_id` int(11) default NULL,
  `simResult_simresult_id` int(11) default NULL,
  `simFarm_simfarm_id` int(11) NOT NULL,
  PRIMARY KEY  (`simjob_id`),
  KEY `FK939C8F86E3845044` (`simStatistic_simstatistic_id`),
  KEY `FK939C8F869723B394` (`simResult_simresult_id`),
  KEY `FK939C8F8627DC0BFB` (`simFarm_simfarm_id`),
  KEY `FK939C8F86F2E5C98F` (`testrun_testrun_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

-- 
-- Tabellenstruktur für Tabelle `simresult`
-- 

CREATE TABLE `simresult` (
  `simresult_id` int(11) NOT NULL auto_increment,
  `numJobs` int(11) NOT NULL,
  `numServer` int(11) NOT NULL,
  `matrixForDB` text collate utf8_bin,
  `earning` bigint(20) NOT NULL,
  PRIMARY KEY  (`simresult_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

-- 
-- Tabellenstruktur für Tabelle `simstatistics`
-- 

CREATE TABLE `simstatistics` (
  `simstatistic_id` int(11) NOT NULL auto_increment,
  `start` bigint(20) NOT NULL,
  `end` bigint(20) NOT NULL,
  `fields` int(11) NOT NULL,
  `tick` double NOT NULL,
  `numStatisticsAcc` int(11) NOT NULL,
  PRIMARY KEY  (`simstatistic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

-- 
-- Tabellenstruktur für Tabelle `simstatistics_statistic`
-- 

CREATE TABLE `simstatistics_statistic` (
  `SimStatistics_simstatistic_id` int(11) NOT NULL,
  `element` bigint(20) default NULL,
  `arraypos` int(11) NOT NULL,
  PRIMARY KEY  (`SimStatistics_simstatistic_id`,`arraypos`),
  KEY `FK13F4C8B8317BD25` (`SimStatistics_simstatistic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

-- 
-- Tabellenstruktur für Tabelle `testrun`
-- 

CREATE TABLE `testrun` (
  `testrun_id` int(11) NOT NULL auto_increment,
  `name` varchar(255) collate utf8_bin default NULL,
  `statsStart` bigint(20) NOT NULL,
  `statsEnd` bigint(20) NOT NULL,
  `statsNumFields` int(11) NOT NULL,
  `algorithm_algorithm_id` int(11) NOT NULL,
  `layout_layout_id` int(11) NOT NULL,
  PRIMARY KEY  (`testrun_id`),
  KEY `FKE6F74793C50D976` (`layout_layout_id`),
  KEY `FKE6F7479803BA4B9` (`algorithm_algorithm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=3 ;

-- 
-- Constraints der exportierten Tabellen
-- 

-- 
-- Constraints der Tabelle `layout_jobs`
-- 
ALTER TABLE `layout_jobs`
  ADD CONSTRAINT `FKDB4BAC2B3C50D976` FOREIGN KEY (`Layout_layout_id`) REFERENCES `layout` (`layout_id`);

-- 
-- Constraints der Tabelle `layout_server`
-- 
ALTER TABLE `layout_server`
  ADD CONSTRAINT `FK45E75DD83C50D976` FOREIGN KEY (`Layout_layout_id`) REFERENCES `layout` (`layout_id`);

-- 
-- Constraints der Tabelle `simfarm_simjob`
-- 
ALTER TABLE `simfarm_simjob`
  ADD CONSTRAINT `FK120C467827DC0BFB` FOREIGN KEY (`SimFarm_simfarm_id`) REFERENCES `simfarm` (`simfarm_id`),
  ADD CONSTRAINT `FK120C4678268B9132` FOREIGN KEY (`simJob_simjob_id`) REFERENCES `simjob` (`simjob_id`);

-- 
-- Constraints der Tabelle `simjob`
-- 
ALTER TABLE `simjob`
  ADD CONSTRAINT `FK939C8F86F2E5C98F` FOREIGN KEY (`testrun_testrun_id`) REFERENCES `testrun` (`testrun_id`),
  ADD CONSTRAINT `FK939C8F8627DC0BFB` FOREIGN KEY (`simFarm_simfarm_id`) REFERENCES `simfarm` (`simfarm_id`),
  ADD CONSTRAINT `FK939C8F869723B394` FOREIGN KEY (`simResult_simresult_id`) REFERENCES `simresult` (`simresult_id`),
  ADD CONSTRAINT `FK939C8F86E3845044` FOREIGN KEY (`simStatistic_simstatistic_id`) REFERENCES `simstatistics` (`simstatistic_id`);

-- 
-- Constraints der Tabelle `simstatistics_statistic`
-- 
ALTER TABLE `simstatistics_statistic`
  ADD CONSTRAINT `FK13F4C8B8317BD25` FOREIGN KEY (`SimStatistics_simstatistic_id`) REFERENCES `simstatistics` (`simstatistic_id`);

-- 
-- Constraints der Tabelle `testrun`
-- 
ALTER TABLE `testrun`
  ADD CONSTRAINT `FKE6F7479803BA4B9` FOREIGN KEY (`algorithm_algorithm_id`) REFERENCES `algorithm` (`algorithm_id`),
  ADD CONSTRAINT `FKE6F74793C50D976` FOREIGN KEY (`layout_layout_id`) REFERENCES `layout` (`layout_id`);
