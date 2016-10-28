package hechizosDeBrujo;

import personaje.HechizoOHab;
import personaje.Personaje;

public class BolaDeLaOscuridad extends HechizoOHab{
	
	
	public BolaDeLaOscuridad(){
		this.costo = 23;
	}
	
	@Override
	public void afectar(Personaje personaje,int poderDeHabilidad) {
		personaje.serAtacado(30+poderDeHabilidad);
	}
	
	
}
