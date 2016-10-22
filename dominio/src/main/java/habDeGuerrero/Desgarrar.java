package habDeGuerrero;

import personaje.HechizoOHab;
import personaje.Personaje;

public class Desgarrar extends HechizoOHab{
	
	@Override
	public void afectar(Personaje personaje) {
		personaje.setVida(personaje.getSalud()-32);

	}
}