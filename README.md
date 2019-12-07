# SPRING-API-DB project

Il s'agit d'un projet d'API sur SpringBoot avec JDBC template.

On parle d'un projet de gestion de lignes de train. 
Pour l'instant est géré la création des Stations de chemin de fer.
Il s'agit de pouvoir à la fin créer des lignes de chemin de fer.

Choses à faire : 
    
- [ ] Permettre de récuperer une station de chemin de faire au travers de l'API avec son id.
- [ ] Etre capable de créer des lignes de chemin de fer au travers de l'API.
(avec la vérification que le nom de la ligne n'est pas vide)
- [ ] Etre capable de récupérer une ligne de chemin de fer au travers de l'API avec son id.
(avec la vérification que le nom de la ligne n'est pas vide)
- [ ] Être capable de rajouter des arrêts de chemin de fer au travers de l'API. 
(rajouter des arrêts c'est faire le lien être un station et une ligne)
- [ ] Être capable de récupérer au travers de l'API la liste de toutes les lignes qui passent 
par une station. (on donne l'id d'une station et cela nous renvoie la liste des lignes)

Règles du jeu : 

- Pour chacune des fonctionnalités, il faut qu'elle soit testée.
- Les controlleurs ne communiquent qu'avec la couche service, seule la couche service peut communiquer
 avec les repositories.
- Les controlleurs communiquent en JSON. 



 

