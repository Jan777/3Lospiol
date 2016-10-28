package hechizosDeBrujo;

import personaje.HechizoOHab;
import personaje.Personaje;

public class LatigazoMortal extends HechizoOHab{
	
	public LatigazoMortal(){
		this.costo = 30;
	}
	
	
	@Override
	public void afectar(Personaje personaje,int poderDeHabilidad) {
		personaje.serAtacado(35+poderDeHabilidad);
	}
}
