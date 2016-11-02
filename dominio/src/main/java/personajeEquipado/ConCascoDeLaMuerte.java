package personajeEquipado;

import personaje.Personaje;

public class ConCascoDeLaMuerte extends PersonajeEquipado{
	
	
	public ConCascoDeLaMuerte(Personaje personajeDecorado) {
		super(personajeDecorado);
		this.prioridad = 3;
		this.nombreDelItem = "ConCascoDeLaMuerte";
	}

	@Override
	public int obtenerPuntosDeDefensa() {
		return super.obtenerPuntosDeDefensa() + 4;
	}
	
	@Override
	public int getCantidadDeItems() {
		return personajeDecorado.getCantidadDeItems()+1;
}
}
