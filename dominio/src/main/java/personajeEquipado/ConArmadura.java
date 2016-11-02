package personajeEquipado;

import personaje.Personaje;

public class ConArmadura extends PersonajeEquipado {
	

	public ConArmadura(Personaje personajeDecorado) {
		super(personajeDecorado);
		this.prioridad = 4;
		this.nombreDelItem = "ConArmadura";
	}

	@Override
	public int obtenerPuntosDeDefensa() {
		return super.obtenerPuntosDeDefensa() + 13;
	}
	
	@Override
	public int getCantidadDeItems() {
		return personajeDecorado.getCantidadDeItems()+1;
}

}
