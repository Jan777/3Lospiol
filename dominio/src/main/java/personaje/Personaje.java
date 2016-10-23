package personaje;

import castas.Casta;
import interfaces.Atacable;
public abstract class Personaje implements Atacable {

	protected int salud;
	protected int energia;
	protected int nivel;
	protected int exp;
	protected int ataque;
	public Casta casta;
	protected int mana;
	protected int poderDeHechizo;
	protected String raza;
	
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
	public abstract int calcularPuntosDeHechizos();
	public abstract void serCurado();
	public abstract boolean aplicarHechizo(String hechizo,Personaje afectado);
	public abstract int obtenerPuntosDeDefensa();
	
	public boolean estaVivo() {
		return this.salud > 0;
	}
	
	@Override
	public void serAtacado(int daño) {
		this.salud -= daño;
	}
	
	public void consumirMana(int i) {
		this.mana-=i;
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

	public void aumentarAtaque(int i) {
		this.ataque+=i;
	}

	public void disminuirAtaque(int i) {
		this.ataque-=i;
		
	}

	public void setMana(int i) {
		this.mana=i;
		
	}

	public Object getMana() {
		return mana;
	}
	

	public int obtenerPuntosDeHechizos() {
		return calcularPuntosDeHechizos();
	}
}
