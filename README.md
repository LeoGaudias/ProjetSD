# Projet de SD
## Réaliser avec Java (jdk_8), Rmi et OpenGl
### Pour executer le programme
Exécution du Client
* Usage : java Client \<adresseIp> \<port>

Execution du Serveur
* De manière graphique
	* Usage : java MainFrame
* Par ligne de commande
	* Usage : java ServerImpl \<adresseIp>  \<port>  \<nombre_Obstacle>  \<Size_x>  \<Size_y>  \<taille_pas>  \<nb_individu>  \<nb_Client>

### Librairies et version de java utilisées
* Pour la version en ligne de commande peu importe la version du jdk
* Pour exécuter il faut jdk_8 car on utilise JavaFx 
* librairies Jogamp pour OpenGl
	* Pour installer Jogamp :
		* http://jogamp.org/wiki/index.php/Downloading_and_installing_JOGL
