package castas;


import java.util.HashMap;
import java.util.Map;

import personaje.HechizoOHab;
import personaje.Personaje;
	

public class Casta{
	public String casta;
	protected Map<String, HechizoOHab> hechizos = new HashMap<String, HechizoOHab>();
	
	public void agregarHechizo(String conjuro, HechizoOHab hechizo) {
			this.hechizos.put(conjuro, hechizo);
	}
	
	public int getCantidadDeHechizos() {
		return 3;
	}
	

	public void hechizar(String conjuro, Personaje personaje) {
		this.hechizos.get(conjuro).afectar(personaje);
	}

	public String obtenerCastaElegida() {
		return casta;
	}
}
