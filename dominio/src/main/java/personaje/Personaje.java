package personaje;

import interfaces.*;

public abstract class Personaje implements Atacable {

	protected int salud=100;
	protected int energia=100;
	protected int nivel=1;
	protected int exp=0;
	protected int ataque=10;
	
	public final void atacar(Atacable atacado) {
		if (puedeAtacar()) {
			atacado.serAtacado(calcularPuntosDeAtaque());
			energia -= calcularPuntosDeAtaque();
			despuesDeAtacar();
		}
	}

	protected void despuesDeAtacar() { }
	
	public abstract boolean puedeAtacar();
	public abstract int calcularPuntosDeAtaque();
	public abstract void serCurado();
	public abstract int obtenerPuntosDeDefensa();
	
	public boolean estaVivo() {
		return this.salud > 0;
	}
	
	@Override
	public void serAtacado(int daño) {
		this.salud -= daño;
	}

	public void serEnergizado() {
		this.energia = 100;
	}
	
	public int getSalud() {
		return this.salud;
	}

	public int obtenerPuntosDeAtaque() {
		return calcularPuntosDeAtaque();
	}

	public void setVida(int i) {
		this.salud=i;
		if(this.salud<0)
			this.salud=0;
	}

	public void setEnergia(int i) {
		this.energia=i;	
	}

	public void setAtaque(int i) {
		this.ataque+=i;
	}
}
