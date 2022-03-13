-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 04 nov. 2019 à 12:29
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `equida19`
--

-- --------------------------------------------------------

--
-- Structure de la table `acheteur`
--

DROP TABLE IF EXISTS `acheteur`;
CREATE TABLE IF NOT EXISTS `acheteur` (
  `ach_cli_id` int(3) NOT NULL,
  PRIMARY KEY (`ach_cli_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `acheteur`
--

INSERT INTO `acheteur` (`ach_cli_id`) VALUES
(22),
(23),
(24),
(26),
(27),
(28),
(29);

-- --------------------------------------------------------

--
-- Structure de la table `categvente`
--

DROP TABLE IF EXISTS `categvente`;
CREATE TABLE IF NOT EXISTS `categvente` (
  `code` varchar(5) NOT NULL,
  `libelle` varchar(30) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `categvente`
--

INSERT INTO `categvente` (`code`, `libelle`) VALUES
('AUT', 'Vente d\'automne'),
('ELVG', 'Vente d\'élevage'),
('ETE', 'Vente d\'été'),
('HIV', 'Vente d\'hiver'),
('PRIN', 'Vente de Printemps'),
('XFEV', 'Vente mixte de février');

-- --------------------------------------------------------

--
-- Structure de la table `cheval`
--

DROP TABLE IF EXISTS `cheval`;
CREATE TABLE IF NOT EXISTS `cheval` (
  `che_id` int(3) NOT NULL,
  `che_nom` varchar(25) DEFAULT NULL,
  `che_sexe` varchar(20) DEFAULT NULL,
  `che_dateNaissance` date NOT NULL,
  `che_race` int(3) NOT NULL,
  `che_siret` varchar(13) DEFAULT NULL,
  `che_id_pere` int(3) DEFAULT NULL,
  `che_id_mere` int(3) DEFAULT NULL,
  `che_client` int(3) NOT NULL,
  `che_archive` int(1) NOT NULL DEFAULT '0',
  `che_active` int(1) NOT NULL DEFAULT '0',
  `che_entraineur` int(3) DEFAULT NULL,
  PRIMARY KEY (`che_id`),
  KEY `FK_Pere` (`che_id_pere`),
  KEY `FK_Mere` (`che_id_mere`),
  KEY `FK_client` (`che_client`),
  KEY `FK_race` (`che_race`),
  KEY `FK_EntCheval` (`che_entraineur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `cheval`
--

INSERT INTO `cheval` (`che_id`, `che_nom`, `che_sexe`, `che_dateNaissance`, `che_race`, `che_siret`, `che_id_pere`, `che_id_mere`, `che_client`, `che_archive`, `che_active`, `che_entraineur`) VALUES
(1, 'Trais d’Or', 'F', '2015-05-12', 2, '536DS12EM37IP', NULL, NULL, 1, 0, 2, NULL),
(2, 'Black Caviar', 'M', '2017-03-25', 1, '23AMX7LEDH5TU', NULL, NULL, 1, 0, 2, NULL),
(3, 'Valdack', 'M', '2014-12-02', 3, '546DFE1C2455E', 2, 1, 3, 0, 2, NULL),
(4, 'Trais d\'argent', 'M', '2017-05-05', 4, '4D5W265DC12DC', 3, 1, 6, 0, 2, NULL),
(5, 'Herricka', 'F', '2016-03-04', 3, '8FE145585CB5A', 2, 1, 3, 0, 2, 4),
(6, 'Nuage', 'M', '2019-03-31', 4, '12FDS568C4KIW', 3, 5, 3, 0, 2, 4),
(7, 'Desperado', 'M', '2019-06-24', 4, '6KAB5DDHL12MO', 4, 1, 9, 0, 2, 4),
(8, 'Axel', 'M', '2000-05-05', 1, 'D58E59DE5985', 7, 6, 52, 0, 2, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(40) NOT NULL,
  `prenom` varchar(40) NOT NULL,
  `rue` varchar(60) NOT NULL,
  `copos` varchar(5) NOT NULL,
  `ville` varchar(40) NOT NULL,
  `mail` varchar(60) DEFAULT NULL,
  `codePays` varchar(3) DEFAULT NULL,
  `archive` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_cli_pays` (`codePays`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `nom`, `prenom`, `rue`, `copos`, `ville`, `mail`, `codePays`, `archive`) VALUES
(1, 'Deltour', 'Charles', '4 rue du Pont', '14680', 'Bretteville Sur Laize', 'cdeltour@hotmail.com', 'FRA', 0),
(2, 'Fime', 'Nadia', '5 rue du Montparnasse', '14220', 'Boulon', 'nadfime14@gmail.com', 'ENG', 0),
(3, 'Ertau', 'Frank', '4 Avenue du président Wilson', '14190', 'Urville', 'frank.ertau@laposte.net', 'FRA', 0),
(4, 'Maneur', 'David', '6 rue Charles Péguy', '14220', 'Mutrécy', '', 'FRA', 0),
(5, 'Berezovski', 'Sylvie', '18 rue du Château', '14680', 'Barbery', '', 'FRA', 0),
(6, 'Finley', 'Pascale', '25 rue de Tolbiac', '14680', 'Caillouet', 'pascfinley@yahoo.fr', 'FRA', 0),
(7, 'Vofur', 'Hector', '18 rue Deparcieux', '14190', 'Cauvicourt', 'hvofur@free.fr', 'ENG', 0),
(8, 'Derzou', 'Fred', '230 avenue de la libert', '14220', 'Espins', 'ouzala@aol.com', 'FRA', 0),
(9, 'Serty', 'Julie', '23 rue du Calvaire', '14220', 'Fresney le Vieux', '', 'FRA', 0),
(10, 'Vofur', 'Victor', '18 rue Deparcieux', '14680', 'Bretteville Sur Laize', 'victor.vofur@laposte.net', 'FRA', 0),
(11, 'Calende', 'Hugo', '22 rue des jardins', '14680', 'Bretteville Sur Laize', '', 'FRA', 0),
(12, 'Jemba', 'Hubert', '10 rue du 8 mai 1945', '14680', 'Bretteville Sur Laize', 'jaimeba@yahoo.fr', 'FRA', 0),
(13, 'Morin', 'S?verine', '4 rue du bac', '93000', 'Paris', 'morinsev@hotmail.com', 'FRA', 0),
(14, 'Benrech', 'Tarek', '79 rue de Caen', '14320', 'May Sur Orne', '', 'FRA', 0),
(15, 'Nguyen', 'Marc', '53 impasse Tourneur', '14320', 'Fontenay Le Marmion', 'nguyen774@wanadoo.fr', 'FRA', 0),
(16, 'Louali', 'Karima', '89 avenue Poincar', '14320', 'Saint Martin de Fontenay', 'kloua@caramail.fr', 'FRA', 0),
(17, 'Paolo', 'Marco', '14 rue du Caire', '14320', 'Fontenay Le Marmion', '', 'FRA', 0),
(18, 'Map', 'Joanna', '120 boulevard Voltaire', '75012', 'Paris', '', 'FRA', 0),
(19, 'Kound', 'Fatoumata', '4 Place Carr', '14190', 'Urville', '', 'FRA', 0),
(20, 'Derissam', 'Bachir', '1 rue des Indes', '14190', 'Urville', '', 'FRA', 0),
(21, 'Villechalane', 'Louis', '8 rue des Charmes', '14680', 'Bretteville Sur Laize', '', 'FRA', 0),
(22, 'Andre', 'David', '1 rue Petit', '14220', 'Boulon', '', 'FRA', 0),
(23, 'Bedos', 'Christian', '1 rue Peranud', '14320', 'Fontenay Le Marmion', '', 'FRA', 0),
(24, 'Tusseau', 'Louis', '22 rue des Ternes', '14680', 'Bretteville Sur Laize', '', 'FRA', 0),
(25, 'Bentot', 'Pascal', '11 allée des Cerises', '14220', 'Boulon', '', 'FRA', 0),
(26, 'Bioret', 'Luc', '1 Avenue gambetta', '14320', 'Fontenay Le Marmion', '', 'FRA', 0),
(27, 'Bunisset', 'Francis', '10 rue des Perles', '14220', 'Espins', '', 'FRA', 0),
(28, 'Bunisset', 'Denise', '23 rue Manin', '14320', 'Saint Martin de Fontenay', '', 'FRA', 0),
(29, 'Cacheux', 'Bernard', '114 rue Blanche', '14320', 'Fontenay Le Marmion', '', 'FRA', 0),
(30, 'Cadic', 'Eric', '123 avenue de la République', '14680', 'Bretteville Sur Laize', '', 'FRA', 0),
(31, 'Charoze', 'Catherine', '100 rue Petit', '14220', 'Boulon', '', 'FRA', 0),
(32, 'Clepkens', 'Christophe', '12 allée des Anges', '14680', 'Bretteville Sur Laize', '', 'FRA', 0),
(33, 'Cottin', 'Vincenne', '36 rue Des Roches', '14220', 'Boulon', '', 'FRA', 0),
(34, 'Daburon', 'François', '13 rue de Chanzy', '14220', 'Mutrécy', '', 'FRA', 0),
(35, 'De', 'Philippe', '13 rue Barthes', '14320', 'Fontenay Le Marmion', '', 'FRA', 0),
(36, 'Debelle', 'Michel', '181 avenue Barbusse', '14220', 'Espins', '', 'FRA', 0),
(37, 'Debelle', 'Jeanne', '134 allée des Joncs', '14320', 'Saint Martin de Fontenay', '', 'FRA', 0),
(38, 'Debroise', 'Michel', '2 Bld Jourdain', '14680', 'Bretteville Sur Laize', '', 'FRA', 0),
(39, 'Desmarquest', 'Nathalie', '14 Place d Arc', '14220', 'Boulon', '', 'FRA', 0),
(40, 'Desnost', 'Pierre', '16 avenue des Cèdres', '14220', 'Mutrécy', '', 'FRA', 0),
(41, 'Dudouit', 'Frédéric', '18 rue de l église', '14320', 'Fontenay Le Marmion', '', 'FRA', 0),
(42, 'Duncombe', 'Claude', '19 rue de la tour', '14680', 'Bretteville Sur Laize', '', 'FRA', 0),
(43, 'Enault-Pascreau', 'Céline', '25 place de la gare', '14680', 'Bretteville Sur Laize', '', 'FRA', 0),
(44, 'Eynde', 'Valérie', '3 Grand Place', '14220', 'Mutrécy', '', 'FRA', 0),
(45, 'Finck', 'Jacques', '10 avenue du Prado', '14320', 'Fontenay Le Marmion', '', 'FRA', 0),
(46, 'Frémont', 'Fernande', '4 route de la mer', '14220', 'Espins', '', 'FRA', 0),
(47, 'Gest', 'Alain', '30 avenue des terres', '14320', 'Saint Martin de Fontenay', '', 'FRA', 0),
(48, 'Testemale', 'tarek', 'du trek', '14000', 'Tarascon', NULL, 'FRA', 0),
(49, 'Trieste', 'Thierry', 'du tertre', '14000', 'Tologne', NULL, 'FRA', 0),
(50, 'test04sept', 'luc', 'de ahjk', '121', 'jkjklj', NULL, 'FRA', 0),
(51, 'Leconte', 'Thomas', '18 rue de la guÃ©rande', '14123', 'Cormelles Le Royal', NULL, NULL, 0),
(52, ' Equida', 'Admin', '98 route d\'Ifs', '14000', 'Caen', 'admin@equida.com', 'FRA', 0),
(53, ' Equida', 'Employé', '98 route d\'Ifs', '14000', 'Caen', 'employe@equida.com', 'FRA', 0),
(54, 'Lalin', 'Quentin', '36 rue Charles de Gaulle', '50660', 'Quettreville-sur-Sienne', NULL, 'FRA', 0);

-- --------------------------------------------------------

--
-- Structure de la table `clientcategvente`
--

DROP TABLE IF EXISTS `clientcategvente`;
CREATE TABLE IF NOT EXISTS `clientcategvente` (
  `codeClient` int(11) NOT NULL,
  `codeCategVente` varchar(5) NOT NULL,
  PRIMARY KEY (`codeClient`,`codeCategVente`),
  KEY `FK_cliCat_Categ` (`codeCategVente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `clientcategvente`
--

INSERT INTO `clientcategvente` (`codeClient`, `codeCategVente`) VALUES
(6, 'AUT'),
(49, 'AUT'),
(1, 'ELVG'),
(51, 'ELVG'),
(1, 'ETE'),
(3, 'ETE'),
(6, 'ETE'),
(7, 'ETE'),
(16, 'ETE'),
(49, 'ETE'),
(50, 'ETE'),
(1, 'HIV'),
(4, 'HIV'),
(7, 'HIV'),
(1, 'PRIN'),
(4, 'PRIN'),
(7, 'PRIN'),
(6, 'XFEV');

-- --------------------------------------------------------

--
-- Structure de la table `compteconnexion`
--

DROP TABLE IF EXISTS `compteconnexion`;
CREATE TABLE IF NOT EXISTS `compteconnexion` (
  `com_id` int(3) NOT NULL,
  `com_identifiant` varchar(50) NOT NULL,
  `com_mdp` varchar(20) NOT NULL,
  `com_role` int(3) NOT NULL,
  `com_cli` int(3) DEFAULT NULL,
  PRIMARY KEY (`com_id`),
  KEY `FK_cli` (`com_cli`),
  KEY `FK_role` (`com_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `compteconnexion`
--

INSERT INTO `compteconnexion` (`com_id`, `com_identifiant`, `com_mdp`, `com_role`, `com_cli`) VALUES
(1, 'dcharles0', 'mpdcharles0', 3, 1),
(2, 'efrank0', 'mpefrank0', 3, 3),
(3, 'fpascale0', 'mpfpascale0', 3, 6),
(4, 'sjulie0', 'mpsjulie0', 3, 9),
(5, 'btarek0', 'mpbtarek0', 3, 14),
(6, 'adavid0', 'mpadavid0', 3, 22),
(7, 'bchristian0', 'mpbchristian0', 3, 23),
(8, 'bpascal0', 'mpbpascal0', 3, 25),
(9, 'employe', 'mpemploye0', 2, 53),
(10, 'admin', 'mpadmin0', 1, 52),
(11, 'lthomas0', 'mplthomas0', 3, 51),
(12, 'lquentin0', 'mplquentin0', 3, 54);

-- --------------------------------------------------------

--
-- Structure de la table `courriel`
--

DROP TABLE IF EXISTS `courriel`;
CREATE TABLE IF NOT EXISTS `courriel` (
  `cou_id` int(3) NOT NULL,
  `cou_dateEnvoiMessage` date DEFAULT NULL,
  `cou_objetMessage` varchar(50) DEFAULT NULL,
  `cou_corpMessage` varchar(255) DEFAULT NULL,
  `cou_vente` int(11) NOT NULL,
  PRIMARY KEY (`cou_id`),
  KEY `FK_Vente2` (`cou_vente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `courriel`
--

INSERT INTO `courriel` (`cou_id`, `cou_dateEnvoiMessage`, `cou_objetMessage`, `cou_corpMessage`, `cou_vente`) VALUES
(1, '2019-09-10', 'URGENT - Vente d\'été', 'Bonjour, une vente va bientôt avoir lieu.', 210817),
(2, '2019-09-10', 'URGENT - Vente d\'été n°2', 'Bonjour, encore une nouvelle vente !', 210817),
(3, '2019-02-10', 'URGENT - Vente mixte de Février', 'Bonjour, une vente va bientôt avoir lieu. 	', 250217),
(4, '2019-10-10', 'URGENT - Vente d\'élevage', 'Bonjour, une vente va bientôt avoir lieu. 	', 30917),
(5, '2018-02-10', 'URGENT - Vente mixte de Février', 'Bonjour, une vente va bientôt avoir lieu.', 90217),
(6, '2019-09-10', 'URGENT - Vente d\'été', 'Bonjour, une vente va bientôt avoir lieu.', 210717),
(7, '2019-10-05', 'URGENT - Vente d\'automne', 'Bonjour, une vente va bientôt avoir lieu.', 250221),
(8, '2018-12-05', 'URGENT - Vente d\'hiver', 'Bonjour, une vente va bientôt avoir lieu.', 250222),
(9, '2019-03-28', 'URGENT - Vente de Printemps', 'Bonjour, une vente va bientôt avoir lieu.', 250223);

-- --------------------------------------------------------

--
-- Structure de la table `course`
--

DROP TABLE IF EXISTS `course`;
CREATE TABLE IF NOT EXISTS `course` (
  `crs_id` int(3) NOT NULL,
  `crs_nom` varchar(50) DEFAULT NULL,
  `crs_lieu` int(3) DEFAULT NULL,
  `crs_date` varchar(10) DEFAULT NULL,
  `crs_archive` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`crs_id`),
  KEY `crs_lieu` (`crs_lieu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `course`
--

INSERT INTO `course` (`crs_id`, `crs_nom`, `crs_lieu`, `crs_date`, `crs_archive`) VALUES
(1, 'Prix Dahman', 1, '2017-06-10', 0),
(2, 'Prix Danbik', 1, '2017-05-25', 0),
(3, 'Prix Pierre Pechdo', 4, '2017-05-06', 0),
(4, 'Prix Chirac', 3, '2019-04-15', 0),
(5, 'Prix de Garonne', 2, '2019-05-26', 0),
(6, 'Prix du Vieux Port', 5, '2019-06-08', 0);

-- --------------------------------------------------------

--
-- Structure de la table `enchere`
--

DROP TABLE IF EXISTS `enchere`;
CREATE TABLE IF NOT EXISTS `enchere` (
  `enc_id` int(3) NOT NULL,
  `enc_idLot` int(3) NOT NULL,
  `enc_prix` int(7) NOT NULL,
  `enc_acheteur` int(3) NOT NULL,
  PRIMARY KEY (`enc_id`),
  KEY `FK_Lot` (`enc_idLot`),
  KEY `FK_acheteurEnc` (`enc_acheteur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `enchere`
--

INSERT INTO `enchere` (`enc_id`, `enc_idLot`, `enc_prix`, `enc_acheteur`) VALUES
(1, 3, 58000, 22),
(2, 3, 65000, 23),
(3, 3, 75000, 22),
(4, 3, 80000, 23),
(5, 3, 95000, 22),
(6, 1, 12000, 22),
(7, 1, 15000, 23),
(8, 2, 8000, 23),
(9, 2, 10000, 22),
(10, 4, 7000, 22),
(11, 4, 8000, 23),
(12, 5, 61000, 22),
(13, 6, 16000, 24),
(14, 6, 17000, 26),
(15, 6, 20000, 24),
(16, 7, 21000, 27),
(17, 7, 22000, 28),
(18, 7, 25000, 27),
(19, 8, 33000, 28),
(20, 8, 34000, 29),
(21, 8, 35000, 28),
(22, 8, 37000, 29);

-- --------------------------------------------------------

--
-- Structure de la table `entraineur`
--

DROP TABLE IF EXISTS `entraineur`;
CREATE TABLE IF NOT EXISTS `entraineur` (
  `ent_id` int(3) NOT NULL,
  `ent_nom` varchar(50) NOT NULL,
  `ent_prenom` varchar(50) NOT NULL,
  `ent_adresse` varchar(50) NOT NULL,
  `ent_copos` varchar(6) NOT NULL,
  `ent_ville` varchar(50) NOT NULL,
  `ent_tel` varchar(10) NOT NULL,
  `ent_nbChevaux` int(3) NOT NULL DEFAULT '0',
  `ent_nbVictoires` int(3) NOT NULL,
  PRIMARY KEY (`ent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `entraineur`
--

INSERT INTO `entraineur` (`ent_id`, `ent_nom`, `ent_prenom`, `ent_adresse`, `ent_copos`, `ent_ville`, `ent_tel`, `ent_nbChevaux`, `ent_nbVictoires`) VALUES
(1, 'Lalin', 'Quentin', '98 route d\'ifs', '14123', 'Ifs', '0607080910', 0, 8),
(2, 'Brionne', 'Axel', '98 route de caen', '14123', 'Caen', '0607080910', 0, 6),
(3, 'Atemani', 'Younes', '98 route d\'ifs', '14000', 'Fleury Sur Orne', '0607080910', 0, 4),
(4, 'Leconte', 'Thomas', '18 rue de la guÃ©rande', '14123', 'Cormelles Le Royal', '0607080910', 0, 3);

-- --------------------------------------------------------

--
-- Structure de la table `lieu`
--

DROP TABLE IF EXISTS `lieu`;
CREATE TABLE IF NOT EXISTS `lieu` (
  `lie_id` int(3) NOT NULL,
  `lie_ville` varchar(50) DEFAULT NULL,
  `lie_nbBoxes` int(3) DEFAULT NULL,
  `lie_commentaires` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`lie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `lieu`
--

INSERT INTO `lieu` (`lie_id`, `lie_ville`, `lie_nbBoxes`, `lie_commentaires`) VALUES
(1, 'Caen', 36, 'Un commentaire parmi d\'autres'),
(2, 'Bordeaux', 37, 'Encore un commentaire'),
(3, 'Paris', 50, 'capitale France'),
(4, 'Lyon', 42, 'ville du cinéma'),
(5, 'Marseille', 30, 'Sud France');

-- --------------------------------------------------------

--
-- Structure de la table `lot`
--

DROP TABLE IF EXISTS `lot`;
CREATE TABLE IF NOT EXISTS `lot` (
  `lot_id` int(3) NOT NULL,
  `lot_idCheval` int(3) NOT NULL,
  `lot_prixDepart` int(7) NOT NULL,
  `lot_ven` int(3) DEFAULT NULL,
  `lot_vente` int(11) DEFAULT NULL,
  PRIMARY KEY (`lot_id`),
  KEY `FK_Cheval` (`lot_idCheval`),
  KEY `FK_Vendeur1` (`lot_ven`),
  KEY `FK_Vente1` (`lot_vente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `lot`
--

INSERT INTO `lot` (`lot_id`, `lot_idCheval`, `lot_prixDepart`, `lot_ven`, `lot_vente`) VALUES
(1, 3, 10000, 14, 30917),
(2, 4, 7000, 25, 210817),
(3, 5, 56000, 14, 250217),
(4, 6, 6500, 25, 90217),
(5, 4, 60000, 14, 210717),
(6, 1, 15000, 30, 250221),
(7, 2, 20000, 31, 250222),
(8, 7, 32500, 33, 250223);

-- --------------------------------------------------------

--
-- Structure de la table `participer`
--

DROP TABLE IF EXISTS `participer`;
CREATE TABLE IF NOT EXISTS `participer` (
  `par_che_id` int(3) NOT NULL,
  `par_crs_id` int(3) NOT NULL,
  `par_place` int(3) DEFAULT NULL,
  PRIMARY KEY (`par_che_id`,`par_crs_id`),
  KEY `FK_Par_Course` (`par_crs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `participer`
--

INSERT INTO `participer` (`par_che_id`, `par_crs_id`, `par_place`) VALUES
(1, 2, 3),
(1, 4, 4),
(1, 5, 1),
(1, 6, 4),
(2, 1, 1),
(3, 1, 2),
(3, 4, 1),
(3, 6, 3),
(4, 3, 1),
(4, 4, 3),
(4, 5, 4),
(5, 1, 3),
(5, 2, 1),
(5, 3, 4),
(6, 1, 4),
(6, 3, 3),
(6, 4, 2),
(6, 5, 2),
(7, 2, 4),
(7, 3, 2),
(7, 5, 3),
(7, 6, 1),
(8, 2, 2),
(8, 6, 2);

-- --------------------------------------------------------

--
-- Structure de la table `pays`
--

DROP TABLE IF EXISTS `pays`;
CREATE TABLE IF NOT EXISTS `pays` (
  `code` varchar(3) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `archive` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `pays`
--

INSERT INTO `pays` (`code`, `nom`, `archive`) VALUES
('ALL', 'Allemagne', 0),
('CHI', 'Chine', 0),
('ENG', 'Angleterre', 0),
('ESP', 'Espagne', 0),
('FRA', 'France', 0),
('ITA', 'Italie', 0),
('JPN', 'Japon', 0),
('USA', 'Etats-Unis', 0);

-- --------------------------------------------------------

--
-- Structure de la table `piecemail`
--

DROP TABLE IF EXISTS `piecemail`;
CREATE TABLE IF NOT EXISTS `piecemail` (
  `pm_idCourriel` int(3) NOT NULL,
  `pm_idPieceJointe` int(3) NOT NULL,
  PRIMARY KEY (`pm_idCourriel`,`pm_idPieceJointe`),
  KEY `FK_PieceJointe` (`pm_idPieceJointe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `piecemail`
--

INSERT INTO `piecemail` (`pm_idCourriel`, `pm_idPieceJointe`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(1, 2),
(3, 2),
(5, 2),
(7, 2);

-- --------------------------------------------------------

--
-- Structure de la table `piecesjointes`
--

DROP TABLE IF EXISTS `piecesjointes`;
CREATE TABLE IF NOT EXISTS `piecesjointes` (
  `pie_id` int(3) NOT NULL,
  `pie_chemin` varchar(255) DEFAULT NULL,
  `pie_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `piecesjointes`
--

INSERT INTO `piecesjointes` (`pie_id`, `pie_chemin`, `pie_desc`) VALUES
(1, '../img/piecejointe.jpg', 'Une image de cheval'),
(2, '../img/piecejointe2.jpg', 'Encore une image de cheval');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` int(3) NOT NULL,
  `role_libelle` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`role_id`, `role_libelle`) VALUES
(1, 'ADMIN'),
(2, 'EMPLOYE'),
(3, 'CLIENT');

-- --------------------------------------------------------

--
-- Structure de la table `typecheval`
--

DROP TABLE IF EXISTS `typecheval`;
CREATE TABLE IF NOT EXISTS `typecheval` (
  `typ_id` int(3) NOT NULL,
  `typ_libelle` varchar(20) NOT NULL,
  `typ_desc` varchar(255) NOT NULL,
  PRIMARY KEY (`typ_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `typecheval`
--

INSERT INTO `typecheval` (`typ_id`, `typ_libelle`, `typ_desc`) VALUES
(1, 'Arabe', 'cheval arabe'),
(2, 'Pur-sang', 'cheval pur_sang'),
(3, 'Pur-Sang Anglais', 'Cheval anglais'),
(4, 'Yearling', 'Cheval Robuste');

-- --------------------------------------------------------

--
-- Structure de la table `vendeur`
--

DROP TABLE IF EXISTS `vendeur`;
CREATE TABLE IF NOT EXISTS `vendeur` (
  `ven_cli_id` int(3) NOT NULL,
  PRIMARY KEY (`ven_cli_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `vendeur`
--

INSERT INTO `vendeur` (`ven_cli_id`) VALUES
(14),
(25),
(30),
(31),
(32),
(33),
(34),
(35);

-- --------------------------------------------------------

--
-- Structure de la table `vente`
--

DROP TABLE IF EXISTS `vente`;
CREATE TABLE IF NOT EXISTS `vente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(40) NOT NULL,
  `dateDebut` varchar(10) NOT NULL,
  `ven_lieu` int(3) DEFAULT NULL,
  `codeCategVente` varchar(5) NOT NULL,
  `archive` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `codeCategVente` (`codeCategVente`),
  KEY `FK_Lieu` (`ven_lieu`)
) ENGINE=InnoDB AUTO_INCREMENT=250225 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `vente`
--

INSERT INTO `vente` (`id`, `nom`, `dateDebut`, `ven_lieu`, `codeCategVente`, `archive`) VALUES
(30917, 'Garibaldi Princess', '2017-03-09', 1, 'ELVG', 0),
(90217, 'Mixing brain', '2017-09-02', 2, 'XFEV', 0),
(210717, 'Rapsberry Sailing', '2017-07-17', 3, 'ETE', 0),
(210817, 'Jelly Bay', '2017-08-17', 4, 'ETE', 0),
(250217, 'Djezair Star', '2017-02-25', 5, 'XFEV', 0),
(250221, 'La Java des Chevaux', '2019-10-15', 1, 'AUT', 0),
(250222, 'Christmas\' Jouney', '2019-12-20', 1, 'HIV', 0),
(250223, 'Des Cloches et des Chevaux', '2020-04-02', 4, 'PRIN', 0);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `acheteur`
--
ALTER TABLE `acheteur`
  ADD CONSTRAINT `FK_acheteur` FOREIGN KEY (`ach_cli_id`) REFERENCES `client` (`id`);

--
-- Contraintes pour la table `cheval`
--
ALTER TABLE `cheval`
  ADD CONSTRAINT `FK_EntCheval` FOREIGN KEY (`che_entraineur`) REFERENCES `entraineur` (`ent_id`),
  ADD CONSTRAINT `FK_Mere` FOREIGN KEY (`che_id_mere`) REFERENCES `cheval` (`che_id`),
  ADD CONSTRAINT `FK_Pere` FOREIGN KEY (`che_id_pere`) REFERENCES `cheval` (`che_id`),
  ADD CONSTRAINT `FK_client` FOREIGN KEY (`che_client`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `FK_race` FOREIGN KEY (`che_race`) REFERENCES `typecheval` (`typ_id`);

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FK_cli_pays` FOREIGN KEY (`codePays`) REFERENCES `pays` (`code`);

--
-- Contraintes pour la table `clientcategvente`
--
ALTER TABLE `clientcategvente`
  ADD CONSTRAINT `FK_cliCat_Categ` FOREIGN KEY (`codeCategVente`) REFERENCES `categvente` (`code`),
  ADD CONSTRAINT `FK_cliCat_client` FOREIGN KEY (`codeClient`) REFERENCES `client` (`id`);

--
-- Contraintes pour la table `compteconnexion`
--
ALTER TABLE `compteconnexion`
  ADD CONSTRAINT `FK_cli` FOREIGN KEY (`com_cli`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `FK_role` FOREIGN KEY (`com_role`) REFERENCES `role` (`role_id`);

--
-- Contraintes pour la table `courriel`
--
ALTER TABLE `courriel`
  ADD CONSTRAINT `FK_Vente2` FOREIGN KEY (`cou_vente`) REFERENCES `vente` (`id`);

--
-- Contraintes pour la table `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `course_ibfk_1` FOREIGN KEY (`crs_lieu`) REFERENCES `lieu` (`lie_id`);

--
-- Contraintes pour la table `enchere`
--
ALTER TABLE `enchere`
  ADD CONSTRAINT `FK_Lot` FOREIGN KEY (`enc_idLot`) REFERENCES `lot` (`lot_id`),
  ADD CONSTRAINT `FK_acheteurEnc` FOREIGN KEY (`enc_acheteur`) REFERENCES `acheteur` (`ach_cli_id`);

--
-- Contraintes pour la table `lot`
--
ALTER TABLE `lot`
  ADD CONSTRAINT `FK_Cheval` FOREIGN KEY (`lot_idCheval`) REFERENCES `cheval` (`che_id`),
  ADD CONSTRAINT `FK_Vendeur1` FOREIGN KEY (`lot_ven`) REFERENCES `vendeur` (`ven_cli_id`),
  ADD CONSTRAINT `FK_Vente1` FOREIGN KEY (`lot_vente`) REFERENCES `vente` (`id`);

--
-- Contraintes pour la table `participer`
--
ALTER TABLE `participer`
  ADD CONSTRAINT `FK_Par_Cheval` FOREIGN KEY (`par_che_id`) REFERENCES `cheval` (`che_id`),
  ADD CONSTRAINT `FK_Par_Course` FOREIGN KEY (`par_crs_id`) REFERENCES `course` (`crs_id`);

--
-- Contraintes pour la table `piecemail`
--
ALTER TABLE `piecemail`
  ADD CONSTRAINT `FK_Courriel` FOREIGN KEY (`pm_idCourriel`) REFERENCES `courriel` (`cou_id`),
  ADD CONSTRAINT `FK_PieceJointe` FOREIGN KEY (`pm_idPieceJointe`) REFERENCES `piecesjointes` (`pie_id`);

--
-- Contraintes pour la table `vendeur`
--
ALTER TABLE `vendeur`
  ADD CONSTRAINT `FK_Vendeur` FOREIGN KEY (`ven_cli_id`) REFERENCES `client` (`id`);

--
-- Contraintes pour la table `vente`
--
ALTER TABLE `vente`
  ADD CONSTRAINT `FK_Lieu` FOREIGN KEY (`ven_lieu`) REFERENCES `lieu` (`lie_id`),
  ADD CONSTRAINT `FK_Ven_Categ` FOREIGN KEY (`codeCategVente`) REFERENCES `categvente` (`code`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
