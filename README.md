<div align="center"><h1 align="center">Technical Test - Leroy Merlin</h1></div>

## About the project

   Ce projet consiste à développer une application pour afficher une liste de produits et permettre l'ajout de nouveaux produits

## Partie 1 : Backend Java (Spring Boot)

### Endpoints REST

* GET /produits : Permets de lister tous les produits par ordre alphabétique.

* POST /produits : Permets d'ajouter un nouveau produit.

* Validations: Le nom du produit ne peux pas être vide, le prix ne peut pas être négatif, ici j'ai choisi de mettre le prix minimum à 1 pour éviter d'avoir des produits gratuits (à 0 EUR), si un produit existe déjà avec le même nom, on refuse également de l'ajouter.

* Il est possible de tester ces endpoints avec des outils tel que l'extension REST Client sur Firefox. (http://restclient.net/)

On a utilisé Maven dans cette partie (https://maven.apache.org/)

Pour lancer le backend, il faut se placer dans le dossier `server`, et executer la commande suivante pour lancer Spring Boot:

* `mvn spring-boot:run`

## Partie 2 : Frontend (Angular)

Le button s'active au client lorsque les validations sont respectées, si un produit existe déjà avec le même nom, on envoie une notification d'erreur au client.

Pour lancer le programme du frontend, il faut se placer dans le dossier `client`, et executer les commandes suivantes:

Pour installer les modules:

* `npm install`

Pour lancer le serveur en mode dévelopment:

* `ng serve`

## Visualisation

Après avoir lancé les 2 serveurs, il suffit d'aller sur http://localhost:4200 pour visualiser notre application.

![Screenshot](/technicaltest.gif)