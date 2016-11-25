package personaje;

import java.awt.Image;

import castas.Casta;
import interfaces.Atacable;
import personajeEquipado.PersonajeEquipado;
import mapa.*;

public abstract class Personaje implements Atacable, Dibujable {

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
	protected String raza;
	protected int idRaza;
	//
	protected String id;
	protected String img;
	private int alto, ancho;
	private int x, y, sentido, paso = 0;
	private int xMouse, yMouse;

	public String obtenerRaza() {
		return raza;
	}
	
	public Personaje(){
		
	}

	public void caminar() {
		if (x == xMouse && y == yMouse)
			return;

		if (x > xMouse) {
			x -= 4;
			paso = 1;
		}

		if (x < xMouse) {
			x += 4;
			paso = 2;
		}
		if (y > yMouse) {
			y -= 4;
			paso = 3;
		}

		if (y < yMouse) {
			y += 4;
			paso = 0;
		}
		sentido = (sentido + 1) % 3;

	}

	public void setXY(int xm, int ym) {
		this.xMouse = xm - (xm % 4);
		this.yMouse = ym - (ym % 4);
	}

	@Override
	public Image getImagen() {
		BuscarImagen buscar = new BuscarImagen();
		return buscar.subImg("elfoP.png", 32, 32, 1, 0);
	}

	@Override
	public int getPosicionX() {
		return x;
	}

	@Override
	public int getPosicionY() {
		return y;
	}

	public int getPosicionXMouse() {
		return xMouse;
	}

	public int getPosicionYMouse() {
		return yMouse;
	}

	@Override
	public int getAncho() {
		return ancho;
	}

	@Override
	public int getAlto() {
		return alto;
	}

	public boolean seSuperPonen(Dibujable d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getID() {
		return id;
	}

	@Override
	public int compareTo(Dibujable d) {
		return this.id.compareTo(d.getID());
	}
	//

	public final void atacar(Personaje atacado) {
		if (puedeAtacar() && atacado.estaVivo() == true) {
			atacado.serAtacado(calcularPuntosDeAtaque());
			energia -= calcularPuntosDeAtaque();
			despuesDeAtacar();
		} else {
			if (atacado.estaVivo() == false) {
				this.exp += 50;
				if (this.exp == this.expMax)
					this.subirdeNivel();
			}
		}
	}

	// Metodos Abstractos
	public abstract boolean puedeAtacar();

	public abstract void despuesDeAtacar();

	public abstract int calcularPuntosDeAtaque();

	public abstract int obtenerPuntosDeDefensa();

	// Getters and Setters
	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getAtaque() {
		return obtenerPuntosDeAtaque();
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getDefensa() {
		return obtenerPuntosDeDefensa();
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public int getSalud() {
		return this.salud;
	}

	public int obtenerPuntosDeAtaque() {
		return calcularPuntosDeAtaque();
	}

	public int obtenerPuntosDeSalud() {
		return salud;
	}

	public int obtenerPuntosDeInteligencia() {
		return this.inteligencia;
	}

	public int getInteligencia() {
		return inteligencia;
	}

	public void setInteligencia(int inteligencia) {
		this.inteligencia = inteligencia;
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

	public void setMana(int i) {
		this.mana = i;

	}

	public int obtenerPuntosDeMana() {
		return mana;
	}

	public int obtenerPuntosDeHechizos() {
		return this.casta.poderHabilidad() + this.inteligencia / 2;
	}

	public int calcularPuntosDeHechizos() {
		return this.casta.poderHabilidad();
	}

	public int getCantidadDeItems() {
		return 0;
	}

	public String getNombreItem() {
		return null;
	}

	public Personaje getPersonajeDecorado() {
		return null;
	}

	public void setPersonajeDecorado(Personaje p) {

	}

	public int getPrioridad() {
		return 0;
	}

	// Metodos adicionales
	public void defender() {
		this.defender = true;
	}
	// public abstract int calcularPuntosDeHechizos();

	// public abstract boolean aplicarHechizo(String hechizo,Personaje
	// afectado);

	public boolean estaVivo() {
		return this.salud > 0;
	}

	@Override
	public void serAtacado(int daño) {
		if (this.defender)
			this.salud -= (daño - this.defensa / 2);
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

	public void subirdeNivel() {
		this.exp = 0;
		this.expMax += 20;
		this.maxSalud += 20;
		this.maxEnergia += 20;
		this.inteligencia += 5;
		this.ataque += 10;
		this.defensa += 5;
		this.nivel++;
		this.manaMax += 20;
	}

	public void aumentarAtaque(int i) {
		this.ataque += i;
	}

	public void disminuirAtaque(int i) {
		this.ataque -= i;

	}

	public boolean aplicarHechizo(String hechizo, Personaje afectado) {
		int manaIni = this.mana;
		this.mana = this.casta.hechizar(hechizo, afectado, mana, inteligencia);
		return manaIni != this.mana;
	}

	// public boolean tiene(Class decorado) {
	// return false;
	// }
	//
	// public Personaje desequipar(Class decorado) {
	// return this;
	// }

	public Personaje desequiparItem(PersonajeEquipado personaje, String[] nombreDelItem) {
		return null;
	}

	public Personaje desequiparItemConMayorPrioridad() {
		return null;
	}

	public int getMana() {
		return this.mana;
	}

	public Casta getCasta() {
		return this.casta;
	}

	public int getEnergia() {
		return this.energia;
	}

	public int getIdRaza() {
		return this.idRaza;
	}

}
