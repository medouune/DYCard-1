-- phpMyAdmin SQL Dump
-- version 4.7.3
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le :  mar. 16 jan. 2018 à 16:31
-- Version du serveur :  5.6.35
-- Version de PHP :  7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données :  `DYCard`
--

-- --------------------------------------------------------

--
-- Structure de la table `Admin`
--

CREATE TABLE `Admin` (
  `login` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  `Id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `Admin`
--

INSERT INTO `Admin` (`login`, `password`, `Id`) VALUES
('root', 'root', 1);

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id` int(11) NOT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `texte` text,
  `forme` varchar(30) NOT NULL,
  `format` varchar(10) NOT NULL,
  `envoyer` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `id_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id`, `photo`, `texte`, `forme`, `format`, `envoyer`, `date`, `id_user`) VALUES
(1, 'NPKOI', 'NPKOI', 'Carre', 'Cassic', 12, NULL, 1),
(2, 'pht', 'Bonne année', 'dd', 'dd', 76, '2018-01-09 00:00:00', 2);

-- --------------------------------------------------------

--
-- Structure de la table `destinataire`
--

CREATE TABLE `destinataire` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `code_postal` varchar(10) NOT NULL,
  `ville` varchar(255) NOT NULL,
  `pays` varchar(255) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `destinataire`
--

INSERT INTO `destinataire` (`id`, `nom`, `prenom`, `adresse`, `code_postal`, `ville`, `pays`, `id_user`) VALUES
(2, 'jo', 'jo', '12 PLACE DES FAIENCIERS', '76100', 'rouen', 'france', 2),
(4, 'ZERT', 'GTRE', 'Rue du mouton', '75001', 'Paris', 'France', 1),
(6, 'DELAPIERRE', 'Elyse', 'Rue Parrot', '75020', 'Paris', 'France', 1);

-- --------------------------------------------------------

--
-- Structure de la table `envoi`
--

CREATE TABLE `envoi` (
  `id_commande` int(11) NOT NULL,
  `id_destinataire` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `pseudo` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `pseudo`, `password`, `mail`) VALUES
(1, 'Das', 'Ok', 'a@a.fr'),
(2, 'aa', 'aa', 'aaA@zs');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Admin`
--
ALTER TABLE `Admin`
  ADD PRIMARY KEY (`Id`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`);

--
-- Index pour la table `destinataire`
--
ALTER TABLE `destinataire`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`);

--
-- Index pour la table `envoi`
--
ALTER TABLE `envoi`
  ADD PRIMARY KEY (`id_commande`,`id_destinataire`),
  ADD KEY `destinataire_envoi` (`id_destinataire`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Admin`
--
ALTER TABLE `Admin`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `destinataire`
--
ALTER TABLE `destinataire`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `user_commande` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION;

--
-- Contraintes pour la table `destinataire`
--
ALTER TABLE `destinataire`
  ADD CONSTRAINT `user_destinataire` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `envoi`
--
ALTER TABLE `envoi`
  ADD CONSTRAINT `commande_envoi` FOREIGN KEY (`id_commande`) REFERENCES `commande` (`id`),
  ADD CONSTRAINT `destinataire_envoi` FOREIGN KEY (`id_destinataire`) REFERENCES `destinataire` (`id`);
