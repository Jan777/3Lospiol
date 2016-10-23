package personajeEquipado;

import personaje.Personaje;

public class ConCascoDeLaMuerte extends PersonajeEquipado{
	
	
	public ConCascoDeLaMuerte(Personaje personajeDecorado) {
		super(personajeDecorado);
	}

	@Override
	public int obtenerPuntosDeDefensa() {
		return super.obtenerPuntosDeDefensa() + 4;
	}

}
