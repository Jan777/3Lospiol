package hechizosDeBrujo;

import personaje.HechizoOHab;
import personaje.Personaje;

public class BolaDeLaOscuridad extends HechizoOHab{
	
	
	public BolaDeLaOscuridad(){
		this.costo = 23;
	}
	
	@Override
	public void afectar(Personaje personaje,int poderDeHabilidad) {
		int ataque = poderDeHabilidad * 3;
		personaje.serAtacado(ataque);
		//personaje.setVida(personaje.getSalud()-30-personaje.obtenerPuntosDeHechizos());
	}
	
	
}
