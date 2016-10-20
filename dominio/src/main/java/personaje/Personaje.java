package personaje;

import interfaces.*;

public abstract class Personaje implements Atacable {

	protected long salud;
	protected long energia;
	protected long ataque;
	protected long defensa;

	protected int ENERGIA_POR_ATAQUE = 10; // energia que consume atacar

	protected int PUNTOS_DE_ATAQUE = 10;
	protected String raza;

	public void serAtacado(int daño) {
		this.salud -= daño;
	}

	public void serCurado() {
		this.salud = 100;
	}

	public void serEnergizado() {
		this.energia = 100;
	}

	private void serCurado(long salud) {
		this.salud += salud;

	}

	public final void atacar(Atacable atacado) {
		if (puedeAtacar()) {
			atacado.serAtacado(calcularPuntosDeAtaque());
			energia -= calcularPuntosDeAtaque();
			despuesDeAtacar();
		}
	}

	public int obtenerPuntosDeAtaque() {
		return calcularPuntosDeAtaque();
	}

	protected void despuesDeAtacar() {
	}

	protected abstract boolean puedeAtacar();

	protected abstract int calcularPuntosDeAtaque();

	public abstract int obtenerPuntosDeDefensa();

	public abstract int calcularPuntos();

}
