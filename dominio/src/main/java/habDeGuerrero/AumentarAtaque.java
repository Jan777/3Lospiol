package habDeGuerrero;

import personaje.HechizoOHab;
import personaje.Personaje;

public class AumentarAtaque extends HechizoOHab{
	
	public AumentarAtaque(){
		this.costo = 15;
	}
	
	@Override
	public void afectar(Personaje personaje,int poderDeHabilidad) {
		personaje.aumentarAtaque(4);
	}
	

}
