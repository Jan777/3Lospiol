package personajeEquipado;

import personaje.Personaje;

public class ConEspada extends PersonajeEquipado{
	

	public ConEspada(Personaje personajeDecorado) {
		super(personajeDecorado);
	}

	@Override
	public int obtenerPuntosDeAtaque() {
		return super.obtenerPuntosDeAtaque() + 5;
	}

}
