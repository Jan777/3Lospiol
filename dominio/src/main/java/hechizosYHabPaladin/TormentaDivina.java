package hechizosYHabPaladin;

import personaje.HechizoOHab;
import personaje.Personaje;

public class TormentaDivina extends HechizoOHab {
	
	public TormentaDivina(){
		this.costo = 37;
	}
	
	@Override
	public void afectar(Personaje personaje,int poderDeHabilidad) {
		personaje.serAtacado(40);
	}
}
