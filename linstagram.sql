-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Creato il: Gen 17, 2018 alle 12:17
-- Versione del server: 10.1.28-MariaDB
-- Versione PHP: 5.6.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `linstagram`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `bookmark`
--

CREATE TABLE `bookmark` (
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `date` datetime NOT NULL,
  `post` int(11) DEFAULT NULL,
  `user` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `following`
--

CREATE TABLE `following` (
  `followed` int(11) NOT NULL,
  `following` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `hashtag`
--

CREATE TABLE `hashtag` (
  `id` int(11) NOT NULL,
  `counter` int(11) DEFAULT NULL,
  `hashtag` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `hashtag_post`
--

CREATE TABLE `hashtag_post` (
  `id_post` int(11) NOT NULL,
  `id_hashtag` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1),
(1),
(1),
(1),
(1),
(1),
(1);

-- --------------------------------------------------------

--
-- Struttura della tabella `likes`
--

CREATE TABLE `likes` (
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `media`
--

CREATE TABLE `media` (
  `post` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `url` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `toSee` bit(1) NOT NULL,
  `type` int(11) NOT NULL,
  `comment` int(11) DEFAULT NULL,
  `post` int(11) DEFAULT NULL,
  `userfrom` int(11) DEFAULT NULL,
  `userto` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `post`
--

CREATE TABLE `post` (
  `id` int(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `postDate` datetime NOT NULL,
  `user` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `requestFollow`
--

CREATE TABLE `requestFollow` (
  `id` int(11) NOT NULL,
  `userfrom` int(11) DEFAULT NULL,
  `userto` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `story`
--

CREATE TABLE `story` (
  `id` int(11) NOT NULL,
  `creationDate` datetime NOT NULL,
  `media_type` int(11) DEFAULT NULL,
  `media_url` varchar(255) DEFAULT NULL,
  `user` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `tags`
--

CREATE TABLE `tags` (
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `biography` varchar(255) DEFAULT NULL,
  `birthdate` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `gender` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `photoProfile` varchar(255) DEFAULT NULL,
  `privateProfile` bit(1) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `viewed_story`
--

CREATE TABLE `viewed_story` (
  `story_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `bookmark`
--
ALTER TABLE `bookmark`
  ADD PRIMARY KEY (`user_id`,`post_id`),
  ADD KEY `FKkm47dr0i09mor5ks9aaebx15u` (`post_id`);

--
-- Indici per le tabelle `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKomrdwc0ub3x7hvvlyu6htn8ti` (`post`),
  ADD KEY `FKlxlm2octv83r6g7kkfxc9iwbi` (`user`);

--
-- Indici per le tabelle `following`
--
ALTER TABLE `following`
  ADD PRIMARY KEY (`followed`,`following`),
  ADD KEY `FK1w5wwrp7wokus9qsklsli8qh9` (`following`);

--
-- Indici per le tabelle `hashtag`
--
ALTER TABLE `hashtag`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_c2cuvw1i8500gmqxolpx1snuu` (`hashtag`);

--
-- Indici per le tabelle `hashtag_post`
--
ALTER TABLE `hashtag_post`
  ADD KEY `FKtkmq1pmaj3u0d6qhhqaydacvv` (`id_hashtag`),
  ADD KEY `FK342fp0fcx4nwc6qea7mj9c5ot` (`id_post`);

--
-- Indici per le tabelle `likes`
--
ALTER TABLE `likes`
  ADD PRIMARY KEY (`post_id`,`user_id`),
  ADD KEY `FKi2wo4dyk4rok7v4kak8sgkwx0` (`user_id`);

--
-- Indici per le tabelle `media`
--
ALTER TABLE `media`
  ADD KEY `FK8fuq4in2sxrdes3ekqpe2oa9y` (`post`);

--
-- Indici per le tabelle `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKk98vlyjwa6hj5gnvavvxw40d5` (`comment`),
  ADD KEY `FKo2vbi9f6yfrxgqji8ocuqge2g` (`post`),
  ADD KEY `FKa6w2p9mafd9yogrpeosy5rb23` (`userfrom`),
  ADD KEY `FKf91p55y0d35ic6mtbr9couqn6` (`userto`);

--
-- Indici per le tabelle `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1qdb5fhxbh7hg92ni2n9xgsns` (`user`);

--
-- Indici per le tabelle `requestFollow`
--
ALTER TABLE `requestFollow`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK15nw1bny7f3pavpvyqpfr4vjp` (`userfrom`),
  ADD KEY `FKdsqqyaj9ad9mp0mvm0vx3gr9x` (`userto`);

--
-- Indici per le tabelle `story`
--
ALTER TABLE `story`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKokqrxj3v1qj2qxecbmq4l0skc` (`user`);

--
-- Indici per le tabelle `tags`
--
ALTER TABLE `tags`
  ADD PRIMARY KEY (`post_id`,`user_id`),
  ADD KEY `FKgtny0im70dxrydf5dg091ql9x` (`user_id`);

--
-- Indici per le tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  ADD UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`);

--
-- Indici per le tabelle `viewed_story`
--
ALTER TABLE `viewed_story`
  ADD PRIMARY KEY (`story_id`,`user_id`),
  ADD KEY `FKjdkpyj9ku130mioqsbgru1yc9` (`user_id`);

DELIMITER $$
--
-- Eventi
--
CREATE DEFINER=`root`@`localhost` EVENT `DeleteStories` ON SCHEDULE EVERY 1 DAY STARTS '2018-01-18 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO DELETE FROM story WHERE TIMESTAMPDIFF(HOUR,  story.creationDate, CURRENT_TIMESTAMP  ) > 24$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
