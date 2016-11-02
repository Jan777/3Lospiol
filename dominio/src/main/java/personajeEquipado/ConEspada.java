package personajeEquipado;

import personaje.Personaje;

public class ConEspada extends PersonajeEquipado{
	

	public ConEspada(Personaje personajeDecorado) {
		super(personajeDecorado);
		this.prioridad = 5;
		this.nombreDelItem = "ConEspada";
	}

	@Override
	public int obtenerPuntosDeAtaque() {
		return super.obtenerPuntosDeAtaque() + 5;
	}
	
	@Override
	public int getCantidadDeItems() {
		return personajeDecorado.getCantidadDeItems()+1;
}

}
