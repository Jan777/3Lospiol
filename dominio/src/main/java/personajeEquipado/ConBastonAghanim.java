package personajeEquipado;

import personaje.Personaje;

public class ConBastonAghanim extends PersonajeEquipado{

	public ConBastonAghanim(Personaje personajeDecorado) {
		super(personajeDecorado);
	}
	
	public int calcularPuntosDeHechizos(){
		return super.obtenerPuntosDeHechizos()+5;
	}
	

}
