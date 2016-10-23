package personajeEquipado;

import personaje.Personaje;

public class ConArmadura extends PersonajeEquipado {
	

	public ConArmadura(Personaje personajeDecorado) {
		super(personajeDecorado);
	}

	@Override
	public int obtenerPuntosDeDefensa() {
		return super.obtenerPuntosDeDefensa() + 13;
	}

}
