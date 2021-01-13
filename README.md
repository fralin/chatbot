# Client serveur multi-thread en java

Le code principal du serveur est dans la classe ````Serveur````

Le serveur écoute sur le port 12000. Pour tester le serveur on peut 
utiliser la commande système ```telnet```:
```bash
>>> telnet localhost 12000
```
Pour vérifier le fonctionnement multi-thread il suffit de
lancer la commande ```telnet``` dans plusieurs terminaux.

Le répertoire source contient 4 classes : 
- ```Serveur.java``` : Cette classe est la classe principale. C'est à partir de cette
classe que sont générés les threads à chaque nouvelle connexion d'un client;
- ```app.util.ServiceClient.java```: cette classe contient le code executer par chaque thread;
- ```Csv2map.java``` : cette classe assure la conversion du fichier dictionnaire
au format csv en une structure de type map beaucoup plus efficace qu'un
tableau pour acceder à des données sous forme ```<clé, valeur>```
- ```Chatbot.java``` : Cette classe reccupère une chaine de caractères, la vectorise, 
calcul la distance à toutes les chaines connues, et renvoit la réponse au serveur

**Attention** le fichier n'est pas disponible sur le git (trop gros). Il faut donc le reccupérer
à part.