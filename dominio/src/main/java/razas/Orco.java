package razas;

import personaje.Personaje;

public class Orco extends Personaje{
	private int cantidadDeAtaques;
	
	@Override
	protected void despuesDeAtacar() {
		cantidadDeAtaques++;
	}
	
	@Override
	public int calcularPuntosDeAtaque() {
		return ataque + cantidadDeAtaques;
	}

	@Override
	public boolean puedeAtacar() {
		return energia >= calcularPuntosDeAtaque();
	}

	@Override
	public int obtenerPuntosDeDefensa() {
		return 0;
	}

	@Override
	public void serCurado() {
		this.salud=120;
	}

}
