# effun
MiniProjet sur la F1 avec SpringBoot
# spécification
L'application devra retourner les données sous format JSON.

L'application devra avoir les données suivantes :

    Pilotes :
        id: int,
        Nom: String,
        Prenom: String,
        Acronyme : 3 premières lettres du nom String,
        Nationalité : String (Pays de Naissance),
        Ecurie : Nom de l'ecurie,
        Numero du pilote : entre 1 et 99

    Ecurie :
        id: Long
        Nom : String,
        Nationalité : String,

    Resultat Général:
        id:  Long
        GrandPrix : String,
        Resultat par Pilote: List[
            Pilote : Nom, Prenom, Numero, ecurie
            Meilleur Tour : Temps sous format 1:20:312 pour 1 minute 20, 312 milisecondes
            Place : int entre 1 et 20
        ]
        date : yyyy-MM-dd

L'application devra exposer les routes suivantes :

- GET /pilotes, elle devra retourner la liste des pilotes. 
- POST /pilotes, (ajouter un pilote) (retour Ok + Id du pilote)
- GET /pilotes/{{piloteId}}/victoire, retourne le nombre de victoires selon l'id du pilote

- GET /ecuries
- POST /ecuries (Retourne Ok + Id de l'écurie)

- GET /standings/pilotes/{{annee}}, retourne le classement des pilotes sur l'année demandé. 
Les points sont distribués de la manière suivante : 1er: 25pts, 2e: 18pts, 3e: 15pts, 4e: 12 pts, 5e: 10 pts, 6e: 8pts, 7e: 6 pts, 8e: 4 pts: 9e: 2pts, 10e: 1pts
Un point bonus pour le meilleur tour, si le pilote a finit dans les 10 premiers
Si deux pilotes ont le même nombre de points, celui qui a fini a la meilleur position est devant (en cas d'égalité de points +  meilleur places, celui qui a le plus de victoires)
- GET /standings/ecurie/{{annee}} sur l'année demandé, retourne le classement des ecuries (sommes des points des pilotes). Même règles en cas d'égalité 

Dans les deux cas si année est null => Prend la dernière année en cours

__**Note:**__ Il serait plus simple pour le calcul des points d'ajouter un attibut dans la classe ResultatPilote par exemple, mais nous choisissons volontairement de faire autrement pour avoir l'occasion de mettre en pratique les notions d'algorithmie acquises.

- GET /pilotes/{{piloteId}}/details, retourne toutes les infos du pilote + ses points, sa place au classement de l'année en cours, son nombre de victoires (si 0 sa meilleur place doit pouvoir être affichée), ses titres de champions du monde + années
- GET /pilotes/{{ecurieId}}/details, retourne toutes les infos de l'écurie, les pilotes de l'écurie, titres de champion constructeurs, nombre de victoires de l'écurie, classement de l'année en cours 

- GET /pilotes/{{piloteId}}/lastWin, retourne le nom + date du dernier grand prix gagné par le pilote

Je m'attends a des contraintes sur les objets de domaine, et l'utilisation de builder !
