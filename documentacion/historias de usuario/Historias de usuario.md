# HISTORIAS DE USUARIO Warlords

**1_**  Como jugador quiero poder tener una cuenta con la cual, mediante nombre de usuario y contraseña para poder entrar al mundo.

**Criterio de aceptacion:**

1. Dado una interface de inicio de sesion con una opcion crear usuario ,cuando se elija esa opcion ,entonces el sistema va a pedir un nombre de usuario no registrado y una contraseña.
2. Dado la creacion de un usuario ,cuando se ingrese un nombre de usuario registrado ,entonces el sistema va a pedir que se ingrese un nombre diferente.
3. Dado un nombre de usuario incorrecto ,cuando se intente iniciar una sesion ,entonces se muestra un mensaje de "usuario o contraseña incorrecta" y no inicia ninguna sesion .
4. Dado una contraseña de usuario incorrecto ,cuando se intente iniciar la sesion del usuario ,entonces se muestra un mensaje de "usuario o contraseña incorrecta" y no inicia ninguna sesion .

**2_** Como jugador quiero poder crear un personaje pudiendo elegir la casta que prefiera y ponerle un nombre a mi personaje.

**Criterio de aceptacion:** 

1. Dado la creacion de un personaje para un jugador ,cuando se pida un nombre para ese personaje ,entonces se verifica que este disponible o no.
2. Dado la creacion de un personaje para un jugador ,cuando se pida un nombre para ese personaje ,entonces se verifica que este no sea mayor a 30 caracteres.

**3_** Como jugador quiero que haya 3 razas para elegir, las cuales seran **orco,humano u elfo**, y 3 castas que seran **guerrerro, paladin y brujo**.

**Criterio de aceptacion:** 

1. Dada la creacion de un personaje, cuando se pide elegir una raza, entonces se puede elegir entre orco, humano u elfo.
2. Dada la creacion de un personaje, cuando se pide elegir una casta, entonces se puede elegir entre guerrerro, paladin o brujo.

**4_** Como jugador quiero que cada raza tenga una potencia propia univoca que la hace diferente a las otras dos razas.

**Criterio de aceptacion:**

1. Dadas las razas humano, orco y elfo, cuando un personaje humano sea creado inicialmente, entonces el humano tendra mas defensa que las otras razas.
2. Dadas las razas humano, orco y elfo, cuando un personaje orco sea creado inicialmente, entonces el orco tendra mas vida y poder de ataque que las otras razas. 
3. Dadas las razas humano, orco y elfo, cuando un personaje elfo sea creado inicialmente, entonces el elfo tendra mas inteligencia y mana que las otras razas. 

**5_** Como jugador quiero que cada raza tenga su propia ciudad, y que no se comparta con las otras razas.

**Criterio de aceptacion:**

1. Dado un personaje de una raza, cuando este entre en una ciudad de otra raza, entonces los habitantes de esa ciudad lo puedan atacar.

**6_** Como jugador quiero poder explorar el mundo y encontrarme con criaturas y otros jugadores.

**Criterio de aceptacion:**

1. Dado un personaje de un jugador ubicado en una pantalla del mundo, cuando el jugador hace click izquierdo sobre un punto de la pantalla, entonces el personaje del jugador se mueve hacia el punto seleccionado o hasta encontrarse con un obstaculo.
2. Dado un personaje de un jugador ubicado en una pantalla del mundo, cuando otro jugador conectado al mismo mundo se ubica sobre la misma pantalla, entonces ambos jugadores pueden ver al personaje del otro e interactuar a travez de sus personajes.
3. Dado un personaje de un jugador ubicado en una pantalla del mundo, cuando ese jugador entre a esa pantalla, entonces aparecera aleatoriamente hasta una cantidad maxima de criaturas enemigas sobre la misma pantalla.

**7_** Como jugador quiero que al encontrarme con una criatuara enemiga u otro jugador que no sea mi aliado pueda entrar en una batalla con ellos.

**Criterio de aceptacion:** 

1. Dados dos personajes de jugadores no aliados, ubicados en la misma pantalla del mundo, cuando los personajes se encuentran dentro un radio maximo establecido respecto del otro, entonces cualquiera de los jugadores puede elegir entrar en batalla con el otro.
2. Dados un personaje de un jugador y una criatura enemiga, ambos ubicados en la misma pantalla del mundo, cuando el personaje se encuentran dentro un radio maximo establecido respecto de la criatura, entonces el jugador puede elegir entrar en batalla con esa criatura.

**8_** Como jugador quiero que al entrar en batalla con una criatura, las criaturas cercanas a esa tambien entren en batalla.

**Criterio de aceptacion:**

1. Dado un personaje de un jugador, cuando el jugador entra en batalla con una criatura, entonces las criaturas cercanas a esa dentro de un radio establecido tambien entran en la batalla.

**9_** Como jugador quiero poder aliarme con otros jugadores para poder entrar en batallas juntos, y poder salir de una alianza cuando lo desee siempre y cuando yo no este en una
batalla.

**Criterio de aceptacion:**

1. Dados dos personajes de jugadores no aliados, ubicados en la misma pantalla del mundo, cuando los personajes se encuentran dentro un radio maximo establecido respecto del otro, entonces cualquiera de los jugadores puede elegir inivtar al otro a ser su aliado, o pedirle entrar en su alianza.
2. Dado un personaje de un jugador, cuando este entre en una batalla y tenga aliados cerca de el dentro de un radio maximo establecido, entonces esos aliados tambien entran en la batalla.
3. Dada una batalla en la que esta peleando una alianza, cuando un integrante de la alianza quiera salir de la alianza, entonces esa opcion no va a estar permitida.
4. Dados dos personajes de jugadores aliados, ubicados en la misma pantalla del mundo, cuando los personajes se encuentran dentro un radio maximo establecido respecto del otro y uno quiera atacar al otro, entonces el jugador no podra entrar en batalla con el otro mientras dure la alianza.

**10_**Como jugador quiero que al matar a un monstruo o un jugador, este deje caer el item mas poderoso que tenga.

**Criterio de aceptacion:**

1. Dado un jugador en una batalla contra una criatura, cuando el jugador mata a esa criatura, entonces la criatura deja caer un item para que sea tomado por el jugador.
2. Dado un jugador en una batalla contra otro jugador, cuando un jugador mata al otro, entonces el perdedor deja caer un item para que sea tomado por el ganador.

**11_** Como jugador quiero que al matar una criatura con aliados, la criatura deje caer la misma cantidad de items, que la cantidad de aliados que haya en la alianza, pero de una calidad inferior, a la que si se hubiera matado sin estar en una alianza.

**Criterio de aceptacion:**

1. Dada una batalla en la que participan una cantidad dada de aliados contra una criatura, cuando los aliados ganan la batalla, entonces la cantidad de items que deja caer este es igual a la cantidad de aliados que participaron en la batalla.
2. Dada una batalla en la que participa una alianza contra una criatura y otra batalla en la que participa un solo jugador, cuando los aliados y el jugador solitario ganan la batalla, entonces los items que deja caer la criatura a los aliados son de calidad inferior a los items que deja caer al jugador solitario.


**12_** Como jugador quiero cuando mate a otro jugador, este reaparesca en un punto de resurreccion cercano.

**Criterio de aceptacion:**

1. Dado un jugador, cuando este muera en una batalla, entonces el jugador reaparecera en un punto de resurreccion cercano preestablecido. 

**13_** Como jugador, quiero al ganar una batalla me sea dada una cantidad de puntos de experiencia para subir de nivel.

1. Dado un jugador, cuando este gana una batalla, entonces se le entregan una cantidad de puntos de experiencia dependiendo de la dificultad del adversario vencido.

**Criterio de aceptacion:**

**14_** Como jugador quiero que mi personaje obtenga habilidades o poderes especiales al subir de nivel, y pueda incrementar el nivel de algunas habilidades.

**Criterio de aceptacion:** 

1. Dado un jugador, cuando este suba de nivel, entonces se lo otorgan una cantidad de puntos de habilidades para que las reparta entre las habilidades que posea.
2. Dado un jugador, cuando este suba a un nivel multiplo de 5, entonces se le otorga una habilidad nueva.
