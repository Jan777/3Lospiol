package razas;

import castas.Casta;
import personaje.Personaje;

public class Humano extends Personaje {

	public Humano(Casta castaElegida) {
		this.maxSalud = 100;
		this.maxEnergia = 100;
		this.salud = 100;
		this.ataque = 10;
		this.defensa = 10;
		this.inteligencia = 10;
		this.casta = castaElegida;
		this.energia = 100;
		this.exp = 0;
		this.nivel = 1;
		this.mana = 0;
	}


	@Override
	protected void despuesDeAtacar() {
		mana += 10;
	}

	@Override
	public int calcularPuntosDeAtaque() {
		return ataque + this.ataqueAfectado;
	}

	@Override
	public boolean puedeAtacar() {
		return energia >= 10;
	}

	@Override
	public int obtenerPuntosDeDefensa() {
		return this.defensa;
	}

}
