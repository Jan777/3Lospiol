package habDeGuerrero;

import personaje.HechizoOHab;
import personaje.Personaje;

public class Desgarrar extends HechizoOHab{
	
	public Desgarrar(){
		this.costo = 30;
	}
	
	@Override
	public void afectar(Personaje personaje,int poderDeHabilidad) {
		personaje.serAtacado(36);

	}
}