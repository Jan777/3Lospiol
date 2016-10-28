package habDeGuerrero;

import personaje.HechizoOHab;
import personaje.Personaje;

public class Ejecutar extends HechizoOHab{
	
	public Ejecutar(){
		this.costo = 25;
	}
	
	@Override
	public void afectar(Personaje personaje,int poderDeHabilidad) {
		if(personaje.getSalud() < 40)
			personaje.serAtacado(33);
		else
			personaje.serAtacado(14);
	}
}
