# Criterios de aceptación de mensajeria

**01. LogInNuevo**
Como Personaje quiero Registrarme para poder logearme y jugar.
```sh
{"Personaje":"usuario","Contraseña":"contra123"}
```
**02. LogIn**
Como Personaje quiero logearme para poder jugar.
```sh
{"Personaje":"usuario","Contraseña":"contra123"}
```
**03. Elección de personaje**  
Como Personaje quiero elegir el nombre, la raza y la casta para incrementar mis atributos.
```sh
{"Personaje":"Jugador1","Raza":"Humano","Casta":"Ladron"}
```
**04. Elección de terreno**  
Como Personaje quiero elegir un terreno entre los disponibles para dezplazarme sobre el.
```sh
{"Personaje":"Jugador1","Mapa":"Chernobyl"}
```
**05. Posición del jugador**   
Como Personaje quiero moverme en el terreno del juego para encontrar enemigos y enfrentarme a ellos.
```sh
{"Personaje":"Jugador1","CoordenadaX":"20","CoordenadaY":"50"}
```
**06. Alianza**  
Como Personaje quiero aliarme con otros jugadores para aumentar mis probabilidades de victoria.
```sh
{"Personaje":"Jugador1","Alianza":"true","Personaje2":"Jugador2"}
```
**07. Batalla**  
Como Personaje quiero iniciar una batalla para aumentar mi experiencia.
```sh
{"Personaje":"Jugador1","Batalla":"true","Personaje2":"Jugador2"}
```
**08. Ataque**  
Como Personaje quiero lanzar un ataque a mi contricante para derrotarlo.
```sh
{"Personaje":"Jugador1","Ataque":"Golpe Furia","Enemigo":"Jugador2"}
```
**09. Defensa**  
Como Personaje quiero defenderme de un ataque de mi contricante para que su ataque no sea tan eficaz.
```sh
{"Personaje":"Jugador1","Defensa":"Esquivar","Enemigo":"Jugador2"}
```
**10. Confirmacion**  
Como Personaje quiero espero una confirmacion de algunos cambios.
```sh
{"Personaje":"Jugador1","Respuesta":"true"}
```

