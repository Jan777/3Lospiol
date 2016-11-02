package personajeEquipado;

import personaje.Personaje;

public class ConBastonAghanim extends PersonajeEquipado{

	public ConBastonAghanim(Personaje personajeDecorado) {
		super(personajeDecorado);
		this.prioridad = 3;
		this.nombreDelItem = "ConBastonAghanim";
	}
	
	public int calcularPuntosDeHechizos(){
		return super.obtenerPuntosDeHechizos()+5;
	}
	
	@Override
	public int getCantidadDeItems() {
		return personajeDecorado.getCantidadDeItems()+1;
}
	

}
