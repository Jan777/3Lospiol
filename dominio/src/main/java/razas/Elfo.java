package razas;

import castas.Casta;
import personaje.Personaje;

public class Elfo extends Personaje {
	
	int ataquesRecibidos;
	
	public Elfo(Casta castaElegida){
		this.maxSalud = 85;
		this.maxEnergia = 120;
		this.expMax=100;
		this.manaMax=80;
		this.salud=85;
		this.inteligencia=6;
		this.ataque=10;
		this.defensa=2;
		this.casta=castaElegida;
		this.energia=120;
		this.exp=0;
		this.nivel=1;
		this.ataquesRecibidos=0;
		this.mana=0;
	}
	@Override
	protected void despuesDeAtacar() {
		mana+=10;
	}
	
	@Override
	public int calcularPuntosDeAtaque() {
		return ataque + ataquesRecibidos + this.ataqueAfectado;
	}

	@Override
	public boolean puedeAtacar() {
		return energia >= calcularPuntosDeAtaque();
	}

	@Override
	public void serAtacado(int da�o) {
		super.serAtacado(da�o);
		this.ataquesRecibidos++;
	}

	@Override
	public int obtenerPuntosDeDefensa() {
		return 0;
	}

	

}
