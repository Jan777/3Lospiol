package personaje;

import castas.Casta;
import interfaces.Atacable;
public abstract class Personaje implements Atacable {

	protected int MaxSalud;
	protected int MaxEnergia;
	protected int salud;
	protected int energia;
	protected int nivel;
	protected int exp;
	protected int ataque;
	protected int defensa;
	protected boolean defender=false;
	protected int inteligencia;
	public Casta casta;
	protected int mana;
	protected int ataqueAfectado=0;
	protected int defensaAfectada=0;
	//protected int poderDeHechizo;
	//protected String raza;
	
	public final void atacar(Atacable atacado) {
		if (puedeAtacar()) {
			atacado.serAtacado(calcularPuntosDeAtaque());
			energia -= calcularPuntosDeAtaque();
			despuesDeAtacar();
			
		}
	}
	
	public void defender(){
		this.defender = true;
	}

	protected void despuesDeAtacar() { }
	
	public abstract boolean puedeAtacar();
	public abstract int calcularPuntosDeAtaque();
	//public abstract int calcularPuntosDeHechizos();
	
	//public abstract boolean aplicarHechizo(String hechizo,Personaje afectado);
	public abstract int obtenerPuntosDeDefensa();
	
	public boolean estaVivo() {
		return this.salud > 0;
	}
	
	@Override
	public void serAtacado(int daño) {
		if(this.defender)
		this.salud -= (daño - this.defensa);
		else
			this.salud -= (daño - (this.defensa / 2)) ;
		if(this.salud<0)
			this.salud=0;
	}
	
	public void consumirMana(int i) {
		this.mana-=i;
	}

	public void serCurado() {
		this.salud= this.MaxSalud;
		
	}
	
	public void serEnergizado() {
		this.energia = this.MaxEnergia;
	}
	
	public int getSalud() {
		return this.salud;
	}

	public int obtenerPuntosDeAtaque() {
		return calcularPuntosDeAtaque();
	}

	public void setVida(int i) {
		this.salud +=i;
		if(this.salud<0)
			this.salud=0;
		if(this.salud > this.MaxSalud)
			this.salud=this.MaxSalud;
	}

	public void setEnergia(int i) {
		this.energia=i;	
	}

	public void aumentarAtaque(int i) {
		this.ataqueAfectado +=i;
	}

	public void disminuirAtaque(int i) {
		this.ataqueAfectado -=i;
		
	}

	public void setMana(int i) {
		this.mana=i;
		
	}

	public Object getMana() {
		return mana;
	}
	

	public int obtenerPuntosDeHechizos() {
		return this.casta.poderHabilidad();
		
	}
	
	public int calcularPuntosDeHechizos() {
		return this.casta.poderHabilidad();
	}
	
	public boolean aplicarHechizo(String hechizo,Personaje afectado){
		int manaIni = this.mana;
		this.mana = this.casta.hechizar(hechizo, afectado, mana);
		return manaIni != this.mana;
	}
	
}
