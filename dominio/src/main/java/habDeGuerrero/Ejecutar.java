package habDeGuerrero;

import personaje.HechizoOHab;
import personaje.Personaje;

public class Ejecutar extends HechizoOHab{
	
	@Override
	public void afectar(Personaje personaje) {
		if(personaje.getSalud()==30)
			personaje.setVida(0);
	}

}
