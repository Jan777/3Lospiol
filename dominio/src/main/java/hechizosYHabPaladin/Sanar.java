package hechizosYHabPaladin;

import personaje.HechizoOHab;
import personaje.Personaje;

public class Sanar extends HechizoOHab{
	
	public Sanar(){
		this.costo = 10;
	}
	
	@Override
	public void afectar(Personaje personaje,int poderDeHabilidad) {
		personaje.setVida(12);
	}
}
