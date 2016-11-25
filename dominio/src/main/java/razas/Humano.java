package razas;

import castas.Casta;
import personaje.Personaje;

public class Humano extends Personaje {

	public Humano(Casta castaElegida, String nombrePersonaje, String img) {
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
		this.expMax=100;
		this.id=nombrePersonaje;
		this.img=img;
		this.idRaza = 1;
	}
	
	public Humano(Casta casta, String id, String img, int salud, int ataque, int defensa, int energia, int experiencia, int nivel, int mana, int idRaza){
		this.maxSalud = 100;
		this.maxEnergia = 100;
		this.salud = salud;
		this.ataque = ataque;
		this.defensa = defensa;
		this.inteligencia = 10;
		this.casta = casta;
		this.energia = energia;
		this.exp = experiencia;
		this.nivel = nivel;
		this.mana = mana;
		this.expMax=100;
		this.id=id;
		this.img=img;
		this.idRaza = idRaza;
	}


	@Override
	public void despuesDeAtacar() {
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
