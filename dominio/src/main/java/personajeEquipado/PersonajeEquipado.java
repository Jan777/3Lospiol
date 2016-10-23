package personajeEquipado;

import personaje.Personaje;

public class PersonajeEquipado extends Personaje{
	Personaje personajeDecorado;
	
	public PersonajeEquipado(Personaje personajeDecorado) {
		this.personajeDecorado= personajeDecorado;
	}
	@Override
	public boolean puedeAtacar() {
		return this.personajeDecorado.puedeAtacar();
	}

	@Override
	public int obtenerPuntosDeAtaque() {
		return this.personajeDecorado.obtenerPuntosDeAtaque();
	}
	
	@Override
	public int calcularPuntosDeAtaque() {
		return this.personajeDecorado.calcularPuntosDeAtaque();
	}

	@Override
	public int obtenerPuntosDeDefensa() {
		return this.personajeDecorado.obtenerPuntosDeDefensa();
	}
	
	@Override
	public int obtenerPuntosDeHechizos() {
		return this.personajeDecorado.obtenerPuntosDeHechizos();
	}

	@Override
	public void serCurado() {
		this.personajeDecorado.serCurado();
	}

	@Override
	public boolean aplicarHechizo(String hechizo, Personaje afectado) {
		return this.personajeDecorado.aplicarHechizo(hechizo, afectado);
	}

	@Override
	public int calcularPuntosDeHechizos() {
		return this.personajeDecorado.calcularPuntosDeHechizos();
	}

}
