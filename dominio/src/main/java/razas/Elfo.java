package razas;

import castas.Casta;
import personaje.Personaje;

public class Elfo extends Personaje {
	
	int ataquesRecibidos;
	
	public Elfo(Casta castaElegida, String nombrePersonaje, String img){
		this.maxSalud = 85;
		this.maxEnergia = 120;
		this.expMax=100;
		this.manaMax=80;
		this.salud=85;
		this.inteligencia=12;
		this.ataque=10;
		this.defensa=2;
		this.casta=castaElegida;
		this.energia=120;
		this.exp=0;
		this.nivel=1;
		this.ataquesRecibidos=0;
		this.mana=3;
		this.id=nombrePersonaje;
		this.img=img;
		this.idRaza = 2;
		
	}
	@Override
	public void despuesDeAtacar() {
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
	public void serAtacado(int daño) {
		super.serAtacado(daño);
		this.ataquesRecibidos++;
	}

	@Override
	public int obtenerPuntosDeDefensa() {
		return this.defensa;
	}

	

}
