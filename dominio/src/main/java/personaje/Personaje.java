package personaje;

import castas.Casta;
import interfaces.Atacable;

public abstract class Personaje implements Atacable {

	protected int maxSalud;
	protected int maxEnergia;
	protected int salud;
	protected int energia;
	protected int nivel;
	protected int exp;
	protected int expMax;
	protected int manaMax;
	protected int ataque;
	protected int defensa;
	protected boolean defender = false;
	protected int inteligencia;
	public Casta casta;
	protected int mana;
	protected int ataqueAfectado = 0;
	protected int defensaAfectada = 0;
	// protected int poderDeHechizo;
	// protected String raza;

	public final void atacar(Atacable atacado) {
		if (puedeAtacar()) {
			atacado.serAtacado(calcularPuntosDeAtaque());
			energia -= calcularPuntosDeAtaque();
			despuesDeAtacar();

		}
	}

	public void defender() {
		this.defender = true;
	}

	protected void despuesDeAtacar() {
	}

	public abstract boolean puedeAtacar();

	public abstract int calcularPuntosDeAtaque();
	// public abstract int calcularPuntosDeHechizos();

	// public abstract boolean aplicarHechizo(String hechizo,Personaje
	// afectado);
	public abstract int obtenerPuntosDeDefensa();

	public boolean estaVivo() {
		return this.salud > 0;
	}

	@Override
	public void serAtacado(int daño) {
		if (this.defender)
			this.salud -= (daño - this.defensa/2);
		else
			this.salud -= (daño - (this.defensa / 3));
		if (this.salud < 0)
			this.salud = 0;
	}

	public void consumirMana(int i) {
		this.mana -= i;
	}

	public void serCurado() {
		this.salud = this.maxSalud;

	}

	public void serEnergizado() {
		this.energia = this.maxEnergia;
	}
	
	public void subirdeNivel(){
		this.exp=0;
		this.expMax+=20;
		this.maxSalud+=20;
		this.maxEnergia+=20;
		this.inteligencia+=5;
		this.ataque+=10;
		this.defensa+=5;
		this.nivel++;
		this.manaMax+=20;
	}

	public int getSalud() {
		return this.salud;
	}

	public int obtenerPuntosDeAtaque() {
		return calcularPuntosDeAtaque();
	}

	public void setVida(int i) {
		this.salud += i;
		if (this.salud < 0)
			this.salud = 0;
		if (this.salud > this.maxSalud)
			this.salud = this.maxSalud;
	}

	public void setEnergia(int i) {
		this.energia = i;
	}

	public void aumentarAtaque(int i) {
		this.ataqueAfectado += i;
	}

	public void disminuirAtaque(int i) {
		this.ataqueAfectado -= i;

	}

	public void setMana(int i) {
		this.mana = i;

	}

	public int obtenerPuntosDeMana() {
		return mana;
	}

	public int obtenerPuntosDeHechizos() {
		return this.casta.poderHabilidad()+this.inteligencia/2;
	}

	public int calcularPuntosDeHechizos() {
		return this.casta.poderHabilidad();
	}

	public boolean aplicarHechizo(String hechizo, Personaje afectado) {
		int manaIni = this.mana;
		this.mana = this.casta.hechizar(hechizo, afectado, mana,this.inteligencia);
		return manaIni != this.mana;
	}

	public int obtenerPuntosDeInteligencia() {
		return this.inteligencia;
	}

}
