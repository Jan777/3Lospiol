package habDeGuerrero;

import personaje.HechizoOHab;
import personaje.Personaje;

public class Ejecutar extends HechizoOHab{
	
	public Ejecutar(){
		this.costo = 25;
	}
	
	@Override
	public void afectar(Personaje personaje,int poderDeHabilidad) {
		if(Math.random() < 0.5)
			personaje.serAtacado(poderDeHabilidad * 10);
		else
			personaje.serAtacado(2);
	}
}
