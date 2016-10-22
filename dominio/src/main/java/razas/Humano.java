package razas;

import personaje.Personaje;

public class Humano extends Personaje{
	
	@Override
	public int calcularPuntosDeAtaque() {
		return ataque;
	}

	@Override
	public boolean puedeAtacar() {
		return energia >= 10;
	}

	@Override
	public int obtenerPuntosDeDefensa() {
		return 5;
	}

	@Override
	public void serCurado() {
		this.salud=100;
		
	}
}
