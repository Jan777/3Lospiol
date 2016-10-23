package hechizosDeBrujo;

import personaje.HechizoOHab;
import personaje.Personaje;

public class LatigazoMortal extends HechizoOHab{
	@Override
	public void afectar(Personaje personaje) {
		personaje.setVida(personaje.getSalud()-20-personaje.obtenerPuntosDeHechizos());
	}
}
