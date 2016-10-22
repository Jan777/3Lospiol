package hechizosDeBrujo;

import personaje.HechizoOHab;
import personaje.Personaje;

public class BolaDeLaOscuridad extends HechizoOHab{
	
	@Override
	public void afectar(Personaje personaje) {
		personaje.setVida(personaje.getSalud()-30);
	}

}
