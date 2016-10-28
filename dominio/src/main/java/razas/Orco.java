package razas;

import castas.Casta;
import personaje.Personaje;

public class Orco extends Personaje{
	
	private int cantidadDeAtaques;
	
	public Orco(Casta castaElegida){
		this.maxSalud = 120;
		this.maxEnergia = 100;
		this.salud=120;
		this.ataque=12;
		this.manaMax=80;
		this.defensa=3;
		this.inteligencia=5;
		this.casta=castaElegida;
		this.energia=100;
		this.exp=0;
		this.nivel=1;
		this.cantidadDeAtaques=0;
		this.mana=0;
	}
	
	@Override
	protected void despuesDeAtacar() {
		cantidadDeAtaques++;
		mana+=10;
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
}
