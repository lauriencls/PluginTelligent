# PluginTelligent - Pense-bête intelligent
Projet de Master 2 - MIAGE

**Réalisation :** 
Robin DELAPORTE - Alexis KARASSEV - Laurie NICOLAS - Baptiste ROUGER

## Contexte du projet
Le but du projet est de créer une plateforme extensible via des plugins. La plateforme PluginTelligent permet de créer des messages en local. Chacun des messages peut disposer d'une alarme, qui sert de rappel à l'utilisateur.

Fonctionnalités implémentées : 
* Ajout de messages de type texte
* Création de canaux pour y ajouter des messages
* Gestion de temps avec des alarmes (rappels) en fonction des messages

Chacune des fonctionnalités est modulable et les plugins implémentés sont gérés et configurés via une interface dédiée. 

## Installation du projet
Assurez vous de disposer d'un IDE permettan de manipuler des projets java se basant sur maven pour la gestion des dépendances, ainsi que d'au moins java 8 et maven. 
Commencez par cloner ce projet localement, à moins que vous ne disposiez déjà en local des fichiers.
Une fois le projet stocké localement, à l'aide de votre IDE importez le projet.
Pour que votre IDE interprète correctement le projet, il faut que vous importiez ce projet en tant que maven, selon votre IDE la méthode sera différente, mais elle devrait toute vous amener à indique le fichier pom.xml à l'IDE.

## Test du projet
Pour tester l'application il vous faut exécuter le main contenu par la classe plugin loader.
L'application chargera automatiquement la configuration contenue dans le fichier config.json afin de savoir quel plugin elle doit charger.
 

