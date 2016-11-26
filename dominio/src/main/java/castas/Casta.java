package castas;

import java.util.HashMap;
import java.util.Map;

import personaje.HechizoOHab;
import personaje.Personaje;

public class Casta {
	protected String casta;
	private String habilidadCasta;
	protected int poderDeHabilidad;
	protected int idCasta;

	protected Map<String, HechizoOHab> hechizos = new HashMap<String, HechizoOHab>();

	public void agregarHechizo(String conjuro, HechizoOHab hechizo) {
		this.hechizos.put(conjuro, hechizo);
	}

	public int getCantidadDeHechizos() {
		return 3;
	}

	public int hechizar(String conjuro, Personaje personaje, int mana, int poder) {
		int manaActualizado = mana - this.hechizos.get(conjuro).costoHabilidad();
		if (manaActualizado >= 0) {
			this.hechizos.get(conjuro).afectar(personaje, this.poderDeHabilidad + poder / 2);
			return manaActualizado;
		}

		return mana;
	}

	public String nombreCastaElegida() {
		return casta;
	}

	public String nombreHabilidad() {
		return getHabilidadCasta();
	}

	public int poderHabilidad() {
		return poderDeHabilidad;
	}

	public int getIdCasta() {
		return this.idCasta;
	}

	public String getHabilidadCasta() {
		return habilidadCasta;
	}

	public void setHabilidadCasta(String habilidadCasta) {
		this.habilidadCasta = habilidadCasta;
	}
}
