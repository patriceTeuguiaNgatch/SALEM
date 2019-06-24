# SALEM

## Description du projet



//////////////////

Lien Google slides [Présentation](https://docs.google.com/presentation/d/1jsSE3wgjz8CVAc0T2csk3DubgcpXotaTWzHlRw465WI/edit?usp=sharing)

## Développement
Copiez les dossiers `SDOC` et `DOCAUTO` dans le répertoire `C:/`. `SDOC` contient la base de données dans laquelle la sauvegarde de la relation entre les contenus de contrôles et les options est effectuée. `DOCAUTO` contient les répertoires des laveurs et les bases de données de Steris.

- [SDoc Add-in](https://github.com/Syas95/GLO-3002-Steris/blob/master/SDoc-Add-in/README.md)
- [SDoc API](https://github.com/Syas95/GLO-3002-Steris/blob/master/SDoc-API/Readme.md)

## Fonctionnement de l'Add-in

### Pré-requis

Activer le `Design Mode` dans le menu `Developer`. Ce mode permet d'afficher les tags des contenus de contrôle présents dans le document. Lorsqu'il est désactivé, les contenus de contrôle sont cachés.

Si vous ne voyez pas le menu `Developer` dans la barre de menus Word, suivez les instructions de la vidéo suivante pour l'afficher: [Comment afficher le menu Developer?](https://www.youtube.com/watch?v=GDE_RDXO16o)


### 1- Le document ouvert est un master

Un document master est un document utilisé comme template pour la génération de la documentation d'un laveur propre à un model de produit donné. Le nom d'un document `master` est préfixé par le model d'un produit suivi par d'un underscore _. Par exemple, Un master du model 380PG aurait pour nom `380PG_XXXXXX`et un master du 680PG aurait pour nom `680PG_XXXXXX`.

À l'ouverture de l'add-in, le contenu chargé sera le contenu propre au model du master ouvert. 

#### Scénario A: Visualiser la liste des options

[Image: Comment Visualiser la liste des options?](https://drive.google.com/open?id=1RE2L3sbIRI_W_MQzqUzCJj-SQ2P5MeRF)

#### Scénario B: Créer un contenu de contrôles

[Vidéo: Comment créer un contenu de contrôle?](https://drive.google.com/open?id=1Ee6MNK6mpIgKu1t3ux8x51ERp-zWEUfO)

Si après avoir cliquer sur le bouton ajouté, vous ne voyez aucun tag dans le document, assurez-vous que le mode `Design` est activé (Voir section 0 des pré-requis).

Pour naviguer vers la section de texte associé à un contenu de contrôle, appuyer sur la touche `Enter`. À chaque fois que vous voulez effectuer une opération de modification avec un contenu de contrôle assurez que le bon tag est sélectionné `0 * -> [NOM_TAG]` en appuyant sur la touche `Enter`. Cela permet de valider que c'est la bonne section de texte qui est assignée à l'option.

#### Scénario C: Ajouter un contenu de contrôle à plusieurs options

[Vidéo: Comment ajouter un contenu de contrôle à plusieurs options?](https://drive.google.com/open?id=1hGv8_uNVe8c-5leKjr-JYyy3Ye1i_EG7)

Avant de cliquer sur le bouton mettre à jour les options, s'assurer que le bon tag est sélectionné `0 * -> [NOM_TAG]`. 

#### Scénario D: Visualiser la liste des options associée à un contenu de contrôle

[Vidéo: Comment visualiser les options associées à un contenu de contrôle?](https://drive.google.com/open?id=1PGa9xmKg5QC_VVnl4Yyxcm01IKYvqop3)

#### Scénario E: Supprimer un contenu de contrôle

[Vidéo: Comment supprimer un contenu de contrôle?](https://drive.google.com/open?id=1j22WlqvfSZnYxylRYT6gBKYlQcpJth3T)

Le contenu de contrôle est supprimé du document et de toutes les options qui y sont associées.

#### Scénario F: Ajouter un contenu de contrôle à une option donnée

[Vidéo: Comment ajouter un contenu de contrôle à une option?](https://drive.google.com/open?id=15NMl9RbWp6J0ZitCHoHTA5CGLM7Wxtv3)

#### Scénario G: Effectuer la migration d'un document existant

La migration est un procédé permettant de créer à partir d'un document master existant, un nouveau document où les signets du document source sont remplacés par des contenus de contrôles. Les différents contenus de contrôles créés sont ajoutés à la base de données et son propre au model du master.

Pour générer la migration, il suffit de cliquer sur le bouton `Générer migration` dans le panel contenant la liste des contenus de contrôle.

Si le bouton migration ne s'affiche pas, assurez-vous que votre document respecte bien le format d'un document master (Voir précision plus haut à la section 1).

[Vidéo: Document résultant d'une migration](https://drive.google.com/open?id=1v_rCDZeGIHTs-kFrT4X-KLfkrIFtFSyL)

#### Scénario H: Visualiser le détail d'une option

[Vidéo: Comment visualiser le détail d'une option?](https://drive.google.com/file/d/1kgLW9rtsiy9GcRvXVDEsZP7CBhGXSbRJ/view?usp=sharing)

#### Scénario I: Retirer un contenu de contrôles d'une option donnée

[Vidéo: Comment retirer un contenu de contrôle d'une option?](https://drive.google.com/open?id=1j22WlqvfSZnYxylRYT6gBKYlQcpJth3T)

Le contenu de contrôle est retiré de l'option mais existe toujours dans le document.

#### Scénario J: Naviguer vers un contenu de controle présent dans le détail d'une option

Voir détail option --Scénario I

#### Scénario K: Rechercher une option

[Vidéo: Comment rechercher une option?](https://drive.google.com/open?id=12UrPKwwRp2matXaGRH8Td92O_6pA2e7p)

#### Scénario L: Visualiser la liste des laveurs

[Image: Comment visualiser la liste des laveurs?](https://drive.google.com/open?id=1RE2L3sbIRI_W_MQzqUzCJj-SQ2P5MeRF)

#### Scénario M: Visualiser le détail d'un laveur

[Vidéo: Comment visualiser le détail d'un laveur?](https://drive.google.com/open?id=1sioOVycf8ikzmur81Ogpkkxp6e7xsGCS)

#### Scénario N: Ajouter un contenu de contrôle pré-éditable à partir du détail d'un laveur

[Vidéo: Comment ajouter un contenu de contrôle pré-éditable?](https://drive.google.com/file/d/1kgLW9rtsiy9GcRvXVDEsZP7CBhGXSbRJ/view?usp=sharing)

#### Scénario O: Générer la documentation d'un laveur

Le document généré se trouve dans le répertoire `[NOM_LAVEUR]/DOCUMENTS` et porte le nom du manuel concaténé au numéro de version du document. 

[Vidéo: Comment  Générer la documentation d'un laveur?](https://drive.google.com/open?id=1Q79nsmn9jgWh5qG78qvGPn-NZIappoya)

Une fois le document généré, seul les portions de texte propre au laveur spécifié sont conservés. Les autres sections sont automatiquement supprimées lors de la génération. Toutefois, la table des matières n'est pas mis à jour. Elle doit être fait manuellement par l'utilisateur.

[Vidéo: Comment consulter la documentation d'un laveur généré?](https://drive.google.com/open?id=1W-86a9Kz2tUHZ0jKOfDtQaN0fcI2rp9s)

#### Scénario P: Rechercher un laveur

[Vidéo: Comment rechercher un laveur?](https://drive.google.com/open?id=18b4lEW8vNrAH-mwD0NohBuoGNWuCX1pO)

### 2- Le document ouvert n'est pas un master

L'utilisateur a accès au document essentiellement en mode lecture. L'add-in est utilisé dans ce cas comme un outil de vérification.

#### Scénario A: Rechercher une option

[Vidéo: Comment rechercher une option?](https://drive.google.com/open?id=12UrPKwwRp2matXaGRH8Td92O_6pA2e7p) 

Le bouton `Retirer contenu de contrôle` n'est pas disponible.

#### Scénario B: Rechercher un laveur

[Vidéo: Comment rechercher un laveur?](https://drive.google.com/open?id=1sioOVycf8ikzmur81Ogpkkxp6e7xsGCS)

Le bouton `Générer documentation` n'est pas disponible en mode readonly parce que avant de générer la documentation, il est important d'effectuer une vérification visuelle avec le document master.

## Contributeurs

- Ermine Wankpo: erminesyas@gmail.com
- Larissa Septche: larissaseptche@gmail.com
- Patrice Teuguia: pateuguia@gmail.com
