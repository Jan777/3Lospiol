package personajeEquipado;

import personaje.Personaje;

public class ConAnillo extends PersonajeEquipado{
	
	public ConAnillo(Personaje personajeDecorado) {
		super(personajeDecorado);
	}

	@Override
	public int obtenerPuntosDeAtaque() {
		return super.obtenerPuntosDeAtaque()+15;
	}

}
