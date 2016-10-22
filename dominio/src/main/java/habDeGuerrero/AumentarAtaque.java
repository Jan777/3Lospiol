package habDeGuerrero;

import personaje.HechizoOHab;
import personaje.Personaje;

public class AumentarAtaque extends HechizoOHab{

	@Override
	public void afectar(Personaje personaje) {
		personaje.setAtaque(4);
	}
	
	

}
