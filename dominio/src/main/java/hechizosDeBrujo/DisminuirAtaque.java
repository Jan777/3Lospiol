package hechizosDeBrujo;

import personaje.HechizoOHab;
import personaje.Personaje;

public class DisminuirAtaque extends HechizoOHab {
	
	public DisminuirAtaque(){
		this.costo = 15;
	}
	@Override
	public void afectar(Personaje personaje ,int poderDeHabilidad) {
		personaje.disminuirAtaque(6);
	}
}
