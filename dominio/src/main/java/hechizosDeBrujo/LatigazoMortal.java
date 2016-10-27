package hechizosDeBrujo;

import personaje.HechizoOHab;
import personaje.Personaje;

public class LatigazoMortal extends HechizoOHab{
	
	public LatigazoMortal(){
		this.costo = 30;
	}
	
	
	@Override
	public void afectar(Personaje personaje,int poderDeHabilidad) {
		
		personaje.serAtacado(poderDeHabilidad * 4);
		//personaje.setVida(personaje.getSalud()-20-personaje.obtenerPuntosDeHechizos());
	}
}
