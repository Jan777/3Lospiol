package personaje;

import java.util.*;

public class Alianza {

	protected List<Personaje> personajesAliados;

	public Alianza(List<Personaje> personajes) {
		this.personajesAliados = personajes;
	}

	public void agregarPersonaje(Personaje personaje) {
		this.personajesAliados.add(personaje);
	}
	
	public void atacarEnemigo(Personaje personajeAtacado){
		
	}

}