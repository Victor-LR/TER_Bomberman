# TER_Bomberman

Journal de bord des évolutions du projet Bomberman implémenté en JAVA

1/04/19:

Prise en main d'ecplipse à partir du projet Pacman proposé par le professeur. Prise en main de Gitthub pour pouvoir travailler plus facilement à partir d'un espace ou tout les fichiers sont accessibles pour nous deux. Commencement du projet avec l'aide des fichiers de Pacman, insertion de la lecture de fichier pour la réalisation du plateau de jeu.

02/04/19:

Finition de la lecture du fichier et premier map lu avec succès. Réalisation des premiers agents (ennemis), avec les différentes actions gérées. Equivalence avec les fantômes de Pacman. Réflection pour l'implementation d'image dans la carte pour remplacer les cubes créés à partir de JPanel. Sprites pour les differentes orientations des ennemis. Affichage des ennemis dans la map.

03/04/19:

Journée de réflexion sur un problème de type syntaxique, menant à des threads déffectuées. Cependant en cette fin de journée nous avons resolu ce problème. Cela nous a permis de réaliser le deplacement des agents selon des variables aléatoire et un nombre de tour. Néanmoins l'affichage du jeu n'est visble qu'à la fin des tours, et non à chaque tours.

04/04/19:

Nous avons reussi à afficher les déplacements des différents agents en temps réel. De plus, nous avons implémenté les classes pour bomberman et pour les objets (bombe). Ce qui a permis au bomberman de poser des bombes de façon aléatoire sur le terrain. Pour finir on acherché et implementé les sprites pour les différents éléments du projet (bomberman, mur, ennemis, briques, bombes, terrain).

05/04/19:

Les bombes sont désormais active, en effet elles peuvent détruir les blocs ou l'explosion se ropage (grâce à un effet de range pour la bombes dans sa classe). L'animation de l'explosion de la bombe est plus ou moins terminée dans le sens où elle ne supporte pas encore le changement de range.

08/04/19:

   - Penser que les bombes appartiennent à un unique bomberman. Toutes modifications des propriétés de la bombes n'appartient qu'au bomberman qui à réupéré l'item. (fait)
  - Mort de l'ennemie prit dans la range de la bombe. (fait)
   - Finition de l'animation de l'explosion de la bombe en fonction de la range de celle ci.(fait)
   - Ajout d'un compteur de point pour chaque Bomberman, +100 points à la mort d'un ennemie.


L'ajout du layout pour l'affichage des scores n'est pas encore finalisé. En effet nous avons rencontré quelques problèmes dans le création du layout (score), la mise à jour des scores n'est pas optimal (superposition des nouveaux et anciens scores).

09/04/19:

Questions : 
- Fonctionnement GameObserver / InterfaceGame
- Fonctionnement de l'affichage des scores, quelle gestionnaire de layout et problème de JLabel
- Priorités dans les prochains jour (amélioration animation, avance de l'ia, controle d'un bomberman, ajout d'item, menu ...)
