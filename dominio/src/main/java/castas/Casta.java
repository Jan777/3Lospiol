package castas;


import java.util.HashMap;
import java.util.Map;

import personaje.HechizoOHab;
import personaje.Personaje;
	

public class Casta{
	public String casta;
	public String habilidadCasta;
	protected int poderDeHabilidad;
	
	protected Map<String, HechizoOHab> hechizos = new HashMap<String, HechizoOHab>();
	
	public void agregarHechizo(String conjuro, HechizoOHab hechizo) {
			this.hechizos.put(conjuro, hechizo);
	}
	
	public int getCantidadDeHechizos() {
		return 3;
	}
	

	public int hechizar(String conjuro, Personaje personaje, int mana, int poder) {
		int manaActualizado = mana-this.hechizos.get(conjuro).costoHabilidad();
		if(manaActualizado >= 0)
		{
			this.hechizos.get(conjuro).afectar(personaje, this.poderDeHabilidad+poder/2);
			return manaActualizado;
		}
		
		return mana;
	}

	public String nombreCastaElegida() {
		return casta;
	}
	
	public String nombreHabilidad() {
		return habilidadCasta;
	}
	
	public int poderHabilidad() {
		return poderDeHabilidad;
	}
}
