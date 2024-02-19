# Nom du Projet

GAMEREVIEW (CAP ENTREPRISE – POEC DÉVELOPPEUR JAVA)


## Description

Ce projet, réalisé dans le cadre du projet CAP ENTREPRISE – POEC JAVA,
est une application web permettant la création et la gestion d'avis sur des jeux vidéos. 
L'application utilise le framework Springboot, Hibernate et les JSP pour offrir une interface web aux joueurs et modérateurs.

## Table des matières

- [Aperçus & fonctionnalités principales](?tab=readme-ov-file#aperçus--fonctionnalités-principales)
- [Installation & configuration](?tab=readme-ov-file#installation--configuration)
- [Architecture logicielle et technologies utilisées](?tab=readme-ov-file#architecture-logicielle-et-technologies-utilisées)
- [Auteur](?tab=readme-ov-file#auteur)

## Aperçus & Fonctionnalités principales

- Lorsque l'on n'est pas connecté, le site nous redirige automatiquement vers la page de connexion et propose un lien vers une page d'inscription.

![Capture d'écran page connexion](Screen%20GameReview/Capture%20d’écran%202024-02-18%20171125.jpg)

- La page d'accueil regroupe tous les avis disponibles sur le site (affichage d'une liste d'avis sous forme de page avec filtres).

- Dans la barre de navigation en haut à droite, un bouton déroulant de navigation permet d'accéder à :
  - Ajouter un commentaire
  - Mes avis
  - Mon compte
  - Liste des jeux
  - Se déconnecter
  
  En tant que modérateur, des options supplémentaires sont disponibles :
  - Avis à modérer
  - Ajouter un jeu.
 
![Capture d'écran page accueil](Screen%20GameReview/Capture%20d’écran%202024-02-18%20171345.jpg)

- Page dédiée aux modérateurs pour valider/supprimer des avis et le telechargement d'un Excel de tous les commentaires du site.

![Capture d'écran page connexion](Screen%20GameReview/Capture%20d’écran%202024-02-18%20172305.jpg)

- Liste des jeux disponibles sur le site.

![Capture d'écran page connexion](Screen%20GameReview/Capture%20d’écran%202024-02-18%20171835.jpg)

- Page individuelle pour chaque jeu avec des fonctionnalités de modération telles que modification, téléversement d'image et suppression.

![Capture d'écran page connexion](Screen%20GameReview/Capture%20d’écran%202024-02-18%20172620.jpg)
![Capture d'écran page connexion](Screen%20GameReview/Capture%20d’écran%202024-02-18%20172959.jpg)

- On peut accéder à la page de chaque utilisateur

![Capture d'écran page connexion](Screen%20GameReview/Capture%20d’écran%202024-02-18%20173126.jpg)


## Installation & Configuration

1. Cloner le repository (de préférence dans IntelliJ).

2. Utiliser Wampserver (ou un autre serveur MySQL de votre choix) pour créer une base de données MySQL vierge en local, nommée "db_game_review".

3. Dans `application.properties`, vous pouvez configurer la base de données :
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/db_game_review
   ...
   ```

4. Exécuter l'application en lançant la classe principale : `src/main/java/fr/nsurget/game_review/GameReviewApplication.java`.

5. Ouvrir un navigateur et accéder au site avec l'URL : [http://localhost:8080/](http://localhost:8080/)

6. Les informations par défaut pour l'administrateur sont :
   - Pseudo: `nco`
   - Mot de passe: `12345`


## Architecture Logicielle et Technologies utilisées

L'application suit une architecture en cinq couches :
1. **Présentation :** Jakarta Server Pages (JSP).
2. **Coordination :** Contrôleurs Springboot.
3. **Service :** Services Springboot.
4. **DAO :** Interfaces héritant de JpaRepository.
5. **Persistance :** Base de données MySQL nommée "db_game_review".

Technologies utilisées :

- **Langage de Programmation :** Java
- **Framework Back-End :** Spring Boot
- **Framework ORM :** Hibernate
- **Langage de requête :** SQL
- **Technologies Front-End :** Jakarta Server Pages (JSP), HTML, CSS et JavaScript
- **Framework CSS :** Bootstrap

## Auteur

Surget Nicolas - 2024 POEC JAVA  
 [![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/nicolas-surget-94435b281/)
