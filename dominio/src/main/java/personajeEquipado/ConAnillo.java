package personajeEquipado;

import personaje.Personaje;

public class ConAnillo extends PersonajeEquipado{
	
	public ConAnillo(Personaje personajeDecorado) {
		super(personajeDecorado);
		this.prioridad = 2;
		this.nombreDelItem = "ConAnillo";
	}

	@Override
	public int obtenerPuntosDeAtaque() {
		return super.obtenerPuntosDeAtaque()+15;
	}

	@Override
	public int getCantidadDeItems() {
		return personajeDecorado.getCantidadDeItems()+1;
}
}
