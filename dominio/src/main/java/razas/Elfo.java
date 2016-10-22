package razas;

import personaje.Personaje;

public class Elfo extends Personaje {
	int ataquesRecibidos;
	
	@Override
	protected void despuesDeAtacar() {
		salud++;
	}
	
	@Override
	public int calcularPuntosDeAtaque() {
		return ataque + ataquesRecibidos;
	}

	@Override
	public boolean puedeAtacar() {
		return energia >= calcularPuntosDeAtaque();
	}

	@Override
	public void serAtacado(int daño) {
		super.serAtacado(daño);
		this.ataquesRecibidos++;
	}

	@Override
	public int obtenerPuntosDeDefensa() {
		return 0;
	}

	@Override
	public void serCurado() {
		this.salud=100;
	}

}
