package hechizosYHabPaladin;

import personaje.HechizoOHab;
import personaje.Personaje;

public class GolpeHeroico extends HechizoOHab{
	
	public GolpeHeroico(){
		this.costo = 37;
	}
	
	@Override
	public void afectar(Personaje personaje,int poderDeHabilidad) {
		personaje.serAtacado(40);
	}
	

}
